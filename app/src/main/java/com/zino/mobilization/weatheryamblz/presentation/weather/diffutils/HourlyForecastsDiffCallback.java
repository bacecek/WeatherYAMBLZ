package com.zino.mobilization.weatheryamblz.presentation.weather.diffutils;

import android.support.v7.util.DiffUtil;
import android.text.TextUtils;

import com.zino.mobilization.weatheryamblz.business.entity.HourlyForecast;

import java.util.List;

/**
 * Created by Denis Buzmakov on 07.08.17.
 * <buzmakov.da@gmail.com>
 */

public class HourlyForecastsDiffCallback extends DiffUtil.Callback {
    private List<HourlyForecast> oldList;
    private List<HourlyForecast> newList;

    public HourlyForecastsDiffCallback(List<HourlyForecast> oldList, List<HourlyForecast> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        HourlyForecast oldItem = oldList.get(oldItemPosition);
        HourlyForecast newItem = newList.get(newItemPosition);
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        HourlyForecast oldItem = oldList.get(oldItemPosition);
        HourlyForecast newItem = newList.get(newItemPosition);
        return TextUtils.equals(oldItem.getTemperature(), newItem.getTemperature())
                && TextUtils.equals(oldItem.getTime(), newItem.getTime())
                && oldItem.getConditionId() == newItem.getConditionId()
                && TextUtils.equals(oldItem.getIconId(), newItem.getIconId());
    }
}
