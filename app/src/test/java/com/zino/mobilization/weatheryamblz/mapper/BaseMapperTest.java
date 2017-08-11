package com.zino.mobilization.weatheryamblz.mapper;

import android.content.Context;

import com.zino.mobilization.weatheryamblz.BuildConfig;
import com.zino.mobilization.weatheryamblz.TestApplication;
import com.zino.mobilization.weatheryamblz.business.Mapper;
import com.zino.mobilization.weatheryamblz.utils.AppResources;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * Created by Denis Buzmakov on 11.08.17.
 * <buzmakov.da@gmail.com>
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = TestApplication.class)
public abstract class BaseMapperTest {

    protected Mapper mapper;

    @Before
    public void init() {
        Context context = RuntimeEnvironment.application;
        AppResources resources = new AppResources(context);
        mapper = new Mapper(resources);
    }
}
