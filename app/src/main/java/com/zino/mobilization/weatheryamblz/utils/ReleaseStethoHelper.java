package com.zino.mobilization.weatheryamblz.utils;

import android.content.Context;

import okhttp3.OkHttpClient;

/**
 * Created by Denis Buzmakov on 11.08.17.
 * <buzmakov.da@gmail.com>
 */

public class ReleaseStethoHelper implements StethoHelper {
    @Override
    public void init(Context context) {
        //no implementation
    }

    @Override
    public void configureNetworkClient(OkHttpClient.Builder builder) {
        //no implementation
    }
}
