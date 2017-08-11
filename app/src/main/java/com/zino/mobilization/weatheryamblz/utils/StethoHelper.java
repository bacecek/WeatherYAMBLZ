package com.zino.mobilization.weatheryamblz.utils;

import android.content.Context;

import okhttp3.OkHttpClient;

/**
 * Created by Denis Buzmakov on 11.08.17.
 * <buzmakov.da@gmail.com>
 */

public interface StethoHelper {
    void init(Context context);
    void configureNetworkClient(OkHttpClient.Builder builder);
}
