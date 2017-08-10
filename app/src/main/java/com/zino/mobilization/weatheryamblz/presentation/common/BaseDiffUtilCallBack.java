package com.zino.mobilization.weatheryamblz.presentation.common;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by Denis Buzmakov on 10.08.17.
 * <buzmakov.da@gmail.com>
 */

public abstract class BaseDiffUtilCallBack<T> extends DiffUtil.Callback {
    protected List<T> oldList;
    protected List<T> newList;

    public BaseDiffUtilCallBack(@NonNull List<T> oldList, @NonNull List<T> newList) {
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
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Override
    public abstract boolean areContentsTheSame(int oldItemPosition, int newItemPosition);
}
