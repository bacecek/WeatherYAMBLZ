package com.zino.mobilization.weatheryamblz.presentation.cities;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.presentation.common.BaseDiffUtilCallBack;

import java.util.List;

/**
 * Created by Denis Buzmakov on 04.08.17.
 * <buzmakov.da@gmail.com>
 */

public class CityDiffCallback extends BaseDiffUtilCallBack<City> {

    CityDiffCallback(@NonNull List<City> oldList, @NonNull List<City> newList) {
        super(oldList, newList);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        City oldCity = oldList.get(oldItemPosition);
        City newCity = newList.get(newItemPosition);

        return TextUtils.equals(oldCity.getName(), newCity.getName())
                && TextUtils.equals(oldCity.getAddress(), newCity.getAddress())
                && oldCity.getCurrentWeather() != null
                && newCity.getCurrentWeather() != null
                && TextUtils.equals(oldCity.getCurrentWeather().getTemperature(), newCity.getCurrentWeather().getTemperature())
                && TextUtils.equals(oldCity.getCurrentWeather().getHumidity(), newCity.getCurrentWeather().getHumidity())
                && oldCity.getCurrentWeather().getConditionId() == newCity.getCurrentWeather().getConditionId()
                && TextUtils.equals(oldCity.getCurrentWeather().getIconId(), newCity.getCurrentWeather().getIconId());
    }
}
