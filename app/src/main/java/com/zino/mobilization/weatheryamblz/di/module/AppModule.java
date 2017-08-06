package com.zino.mobilization.weatheryamblz.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManager;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManagerImpl;
import com.zino.mobilization.weatheryamblz.utils.AppResources;
import com.zino.mobilization.weatheryamblz.data.service.AndroidJobHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.zino.mobilization.weatheryamblz.data.settings.SettingsManagerImpl.FILE_NAME;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

@Module
public class AppModule {
    private Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs(Context context) {
        return context.getSharedPreferences(FILE_NAME,  Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    RxSharedPreferences provideRxSharedPreferences(SharedPreferences preferences) {
        return RxSharedPreferences.create(preferences);
    }

    @Provides
    @Singleton
    AppResources provideResources(Context context) {
        return new AppResources(context);
    }

    @Provides
    @Singleton
    SettingsManager providePrefsHelper(SharedPreferences preferences,
                                       RxSharedPreferences rxSharedPreferences,
                                       AppResources appResources) {
        return new SettingsManagerImpl(preferences, rxSharedPreferences, appResources);
    }

    @Provides
    @Singleton
    AndroidJobHelper provideJobHelper(Context context) {
        return new AndroidJobHelper(context);
    }

}
