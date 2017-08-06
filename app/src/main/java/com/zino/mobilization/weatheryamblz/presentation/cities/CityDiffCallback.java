package com.zino.mobilization.weatheryamblz.presentation.cities;

import android.support.v7.util.DiffUtil;
import android.text.TextUtils;

import com.zino.mobilization.weatheryamblz.business.entity.City;

import java.util.List;

/**
 * Created by Denis Buzmakov on 04.08.17.
 * <buzmakov.da@gmail.com>
 */

public class CityDiffCallback extends DiffUtil.Callback {
    private List<City> oldList;
    private List<City> newList;

    public CityDiffCallback(List<City> oldList, List<City> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList == null ? 0 : oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList == null ? 0 : newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        City oldCity = oldList.get(oldItemPosition);
        City newCity = newList.get(newItemPosition);

        return TextUtils.equals(oldCity.getName(), newCity.getName()) &&
                TextUtils.equals(oldCity.getAddress(), newCity.getAddress()) &&
                oldCity.getCurrentWeather() != null &&
                newCity.getCurrentWeather() != null &&
                TextUtils.equals(oldCity.getCurrentWeather().getTemperature(), newCity.getCurrentWeather().getTemperature()) &&
                TextUtils.equals(oldCity.getCurrentWeather().getHumidity(), newCity.getCurrentWeather().getHumidity()) &&
                oldCity.getCurrentWeather().getConditionId() == newCity.getCurrentWeather().getConditionId() &&
                TextUtils.equals(oldCity.getCurrentWeather().getIconId(), newCity.getCurrentWeather().getIconId());
    }
}
