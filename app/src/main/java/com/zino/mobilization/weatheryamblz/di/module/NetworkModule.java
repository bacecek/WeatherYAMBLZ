package com.zino.mobilization.weatheryamblz.di.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.zino.mobilization.weatheryamblz.BuildConfig;
import com.zino.mobilization.weatheryamblz.data.network.api.PlacesApi;
import com.zino.mobilization.weatheryamblz.data.network.api.WeatherApi;
import com.zino.mobilization.weatheryamblz.data.network.util.KeyInterceptor;
import com.zino.mobilization.weatheryamblz.data.network.util.LanguageInterceptor;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

@Module
public class NetworkModule {

    //common

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    //weather

    @Provides
    @Singleton
    @Named("weather")
    KeyInterceptor provideWeatherKeyInterceptor(@Named("weather_key") String apiKey) {
        return new KeyInterceptor("appid", apiKey);
    }

    @Provides
    @Singleton
    @Named("weather")
    LanguageInterceptor provideWeatherLanguageInterceptor() {
        return new LanguageInterceptor("lang");
    }

    @Provides
    @Singleton
    @Named("weather")
    OkHttpClient provideWeatherOkHttpClient(@Named("weather") LanguageInterceptor languageInterceptor,
                                            @Named("weather") KeyInterceptor keyInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(languageInterceptor)
                .addInterceptor(keyInterceptor);
        if(BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        return builder.build();
    }

    @Provides
    @Singleton
    @Named("weather")
    Retrofit provideWeatherRetrofit(@Named("weather") OkHttpClient client,
                                    GsonConverterFactory factory,
                                    @Named("weather_url") String url) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    WeatherApi provideWeatherApi(@Named("weather") Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }

    //places

    @Provides
    @Singleton
    @Named("places")
    KeyInterceptor providePlacesKeyInterceptor(@Named("places_key") String apiKey) {
        return new KeyInterceptor("key", apiKey);
    }

    @Provides
    @Singleton
    @Named("places")
    LanguageInterceptor providePlacesLanguageInterceptor() {
        return new LanguageInterceptor("language");
    }

    @Provides
    @Singleton
    @Named("places")
    OkHttpClient providePlacesOkHttpClient(@Named("places") LanguageInterceptor languageInterceptor,
                                           @Named("places") KeyInterceptor keyInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(languageInterceptor)
                .addInterceptor(keyInterceptor);
        if(BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        return builder.build();
    }

    @Provides
    @Singleton
    @Named("places")
    Retrofit providePlacesRetrofit(@Named("places") OkHttpClient client,
                                   GsonConverterFactory factory,
                                   @Named("places_url") String url) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    PlacesApi providePlacesApi(@Named("places") Retrofit retrofit) {
        return retrofit.create(PlacesApi.class);
    }
}
