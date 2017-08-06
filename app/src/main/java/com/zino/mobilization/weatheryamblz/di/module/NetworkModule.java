package com.zino.mobilization.weatheryamblz.di.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.zino.mobilization.weatheryamblz.BuildConfig;
import com.zino.mobilization.weatheryamblz.data.network.api.WeatherApi;
import com.zino.mobilization.weatheryamblz.data.network.util.KeyInterceptor;
import com.zino.mobilization.weatheryamblz.data.network.util.LanguageInterceptor;

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

    @Provides
    @Singleton
    KeyInterceptor provideKeyInterceptor() {
        return new KeyInterceptor("appid", WeatherApi.API_KEY);
    }

    @Provides
    @Singleton
    LanguageInterceptor provideLanguageInterceptor() {
        return new LanguageInterceptor("lang");
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(LanguageInterceptor languageInterceptor, KeyInterceptor keyInterceptor) {
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
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, GsonConverterFactory factory) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(WeatherApi.BASE_URL)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    WeatherApi provideWeatherApi(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }
}
