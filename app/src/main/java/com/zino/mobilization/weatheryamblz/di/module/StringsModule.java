package com.zino.mobilization.weatheryamblz.di.module;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

@Module
public class StringsModule {

    @Named("places_key")
    @Provides
    String providePlacesApiKey() {
        return "AIzaSyAl-VRGxp-ALqrx0AdW9X7ZQm00ZSVLm7A";
    }

    @Named("weather_key")
    @Provides
    String provideWeatherApiKey() {
        return "f8781bfa9ae7b5eb3a08cd6ca5be358b";
    }

    @Named("places_url")
    @Provides
    String providePlacesUrl() {
        return "https://maps.googleapis.com/maps/api/place/";
    }

    @Named("weather_url")
    @Provides
    String provideWeatherUrl() {
        return "http://api.openweathermap.org/data/2.5/";
    }
}
