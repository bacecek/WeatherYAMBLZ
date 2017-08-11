package com.zino.mobilization.weatheryamblz;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.zino.mobilization.weatheryamblz.data.service.AndroidJobHelper;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManager;
import com.zino.mobilization.weatheryamblz.di.component.AppComponent;
import com.zino.mobilization.weatheryamblz.di.component.DaggerAppComponent;
import com.zino.mobilization.weatheryamblz.di.module.AppModule;
import com.zino.mobilization.weatheryamblz.di.module.NetworkModule;

import javax.inject.Inject;

import timber.log.Timber;


public class WeatherApplication extends Application {

    private static AppComponent appComponent;

    @Inject
    SettingsManager settingsManager;

    @Inject
    AndroidJobHelper jobHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        initDI();
        initJobHelper();
        initLibraries();
    }

    protected void initDI() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
        appComponent.inject(this);
    }

    protected void initJobHelper() {
        settingsManager.getUpdateTime()
                .subscribe(period -> {
                    if (period == 0) {
                        jobHelper.cancelAllJobs();
                    } else {
                        jobHelper.scheduleIfJobRequestsIsEmpty(period);
                    }
                });
    }

    protected void initLibraries() {
        if (BuildConfig.DEBUG) {
            BuildConfig.STETHO.init(this);
            Timber.plant(new Timber.DebugTree());

            if (LeakCanary.isInAnalyzerProcess(this)) {
                return;
            }
            LeakCanary.install(this);
        }
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
