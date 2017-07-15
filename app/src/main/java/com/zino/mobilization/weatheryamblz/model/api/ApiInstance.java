package com.zino.mobilization.weatheryamblz.model.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Алексей on 15.07.2017.
 */

public class ApiInstance {

    private static WeatherAPI api;


    public static WeatherAPI getAPI() {
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(new OkHttpClient.Builder()
                            .addNetworkInterceptor(new StethoInterceptor())
                            .build())
                    .build();

            api = retrofit.create(WeatherAPI.class);
        }

        return api;
    }


}
