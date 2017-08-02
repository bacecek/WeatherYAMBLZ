package com.zino.mobilization.weatheryamblz.data.network.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Denis Buzmakov on 12.07.2017.
 * <buzmakov.da@gmail.com>
 */


public class KeyInterceptor implements Interceptor {

    @NonNull
    private String keyName;

    @Nullable
    private String key;

    public KeyInterceptor(@NonNull String keyName, @Nullable String key) {
        this.keyName = keyName;
        this.key = key;
    }

    public KeyInterceptor(@NonNull String keyName) {
        this.keyName = keyName;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter(keyName, key)
                .build();
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
