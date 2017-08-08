package com.zino.mobilization.weatheryamblz.presentation.weather.diffutils;

import android.support.v7.util.DiffUtil;
import android.text.TextUtils;

import com.zino.mobilization.weatheryamblz.business.entity.DailyForecast;

import java.util.List;

/**
 * Created by Denis Buzmakov on 07.08.17.
 * <buzmakov.da@gmail.com>
 */

public class DailyForecastsDiffCallback extends DiffUtil.Callback {
    private List<DailyForecast> oldList;
    private List<DailyForecast> newList;

    public DailyForecastsDiffCallback(List<DailyForecast> oldList, List<DailyForecast> newList) {
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
        DailyForecast oldItem = oldList.get(oldItemPosition);
        DailyForecast newItem = newList.get(newItemPosition);
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        DailyForecast oldItem = oldList.get(oldItemPosition);
        DailyForecast newItem = newList.get(newItemPosition);
        return TextUtils.equals(oldItem.getTempDay(), newItem.getTempDay())
                && TextUtils.equals(oldItem.getTempNight(), newItem.getTempNight())
                && TextUtils.equals(oldItem.getDescription(), newItem.getDescription())
                && TextUtils.equals(oldItem.getDay(), newItem.getDay())
                && oldItem.getConditionId() == newItem.getConditionId()
                && TextUtils.equals(oldItem.getIconId(), newItem.getIconId());
    }
}
