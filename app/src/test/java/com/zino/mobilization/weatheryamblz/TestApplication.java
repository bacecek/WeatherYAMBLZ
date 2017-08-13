package com.zino.mobilization.weatheryamblz;

/**
 * Created by Denis Buzmakov on 25.07.17.
 * <buzmakov.da@gmail.com>
 */

public class TestApplication extends WeatherApplication {

    @Override
    protected void initJobHelper() {
        //no implementation here because Android Job is *******
    }

    @Override
    protected void initLibraries() {
        //no implementation here because Leak Canary and Stetho is  *******
    }
}
