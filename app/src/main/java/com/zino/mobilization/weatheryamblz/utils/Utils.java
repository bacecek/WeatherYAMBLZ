package com.zino.mobilization.weatheryamblz.utils;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.TypedValue;

import com.zino.mobilization.weatheryamblz.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class Utils {

    @DrawableRes
    public static int getImageIdByWeatherCondition(String name) {
        if(name == null) return 0;
        switch (name) {
            case "01d":
            case "01n":
                return R.drawable.sun;
            case "02d":
            case "02n":
                return R.drawable.cloud_sun;
            case "03d":
            case "03n":
            case "04d":
            case "04n":
                return R.drawable.cloud;
            case "09d":
            case "09n":
                return R.drawable.shower_rain;
            case "10d":
            case "10n":
                return R.drawable.rain;
            case "11d":
            case "11n":
                return R.drawable.thunderstorm;
            case "13d":
            case "13n":
                return R.drawable.snow;
            case "50d":
            case "50n":
                return R.drawable.mist;
            default:
                return 0;
        }
    }

    @ColorRes
    public static int getColorIdByWeatherCondition(String condition) {
        if(condition == null) return 0;
        switch (condition) {
            case "01d":
            case "01n":
                return R.color.colorSun;
            case "02d":
            case "02n":
                return R.color.colorCloudsSun;
            case "03d":
            case "03n":
            case "04d":
            case "04n":
                return R.color.colorClouds;
            case "09d":
            case "09n":
            case "10d":
            case "10n":
                return R.color.colorRain;
            case "11d":
            case "11n":
                return R.color.colorThunderstorm;
            case "13d":
            case "13n":
                return R.color.colorSnow;
            case "50d":
            case "50n":
                return R.color.colorMist;
            default:
                return 0;
        }
    }

    public static int getContrastColor(int colorIntValue) {
        int red = Color.red(colorIntValue);
        int green = Color.green(colorIntValue);
        int blue = Color.blue(colorIntValue);
        double lum = (((0.299 * red) + ((0.587 * green) + (0.114 * blue))));
        return lum > 186 ? 0xFF000000 : 0xFFFFFFFF;
    }

    static int metersToKm(int meters) {
        return Math.round((float) meters / 1000);
    }

    public static double convertFromKelvinToChosenUnits(double kelvins, boolean isCelsius) {
        return isCelsius ? kelvins - 273.15 : kelvins * 9 / 5 - 459.67;
    }

    @Nullable
    public static String formatUnixTime(long time, String pattern) {
        if(time == 0) return null;
        Date date = new Date(TimeUnit.SECONDS.toMillis(time));
        SimpleDateFormat convertFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        convertFormat.setTimeZone(TimeZone.getDefault());
        return convertFormat.format(date);
    }

    public static double dpToPx(Resources resources, int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }

    public static String capitalize(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
