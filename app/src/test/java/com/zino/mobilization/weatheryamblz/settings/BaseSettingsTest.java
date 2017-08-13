package com.zino.mobilization.weatheryamblz.settings;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.zino.mobilization.weatheryamblz.BuildConfig;
import com.zino.mobilization.weatheryamblz.TestApplication;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManager;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManagerImpl;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * Created by Denis Buzmakov on 13.08.17.
 * <buzmakov.da@gmail.com>
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = TestApplication.class)
public abstract class BaseSettingsTest {

    SettingsManager settingsManager;

    @Before
    public void init() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(RuntimeEnvironment.application);
        RxSharedPreferences rxSharedPreferences = RxSharedPreferences.create(preferences);
        settingsManager = new SettingsManagerImpl(rxSharedPreferences);
    }
}
