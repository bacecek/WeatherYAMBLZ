package com.zino.mobilization.weatheryamblz.data.network.util;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.Locale;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Denis Buzmakov on 02.08.17.
 * <buzmakov.da@gmail.com>
 */

public class LanguageInterceptor implements Interceptor {

    @NonNull
    private String query;

    public LanguageInterceptor(@NonNull String query) {
        this.query = query;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter(query, Locale.getDefault().getLanguage())
                .build();
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
