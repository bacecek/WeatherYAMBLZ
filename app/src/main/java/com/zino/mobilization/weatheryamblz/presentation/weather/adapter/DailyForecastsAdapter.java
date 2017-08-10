package com.zino.mobilization.weatheryamblz.presentation.weather.adapter;

import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.business.entity.DailyForecast;
import com.zino.mobilization.weatheryamblz.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Denis Buzmakov on 07.08.17.
 * <buzmakov.da@gmail.com>
 */

public class DailyForecastsAdapter extends RecyclerView.Adapter<DailyForecastsAdapter.ViewHolder> {
    private List<DailyForecast> data = new ArrayList<>();

    public DailyForecastsAdapter(List<DailyForecast> data) {
        this.data.addAll(data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_forecast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(List<DailyForecast> newData) {
        Timber.d("update data: " + newData.toString());
        this.data.clear();
        this.data.addAll(newData);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_day)
        TextView txtDay;

        @BindView(R.id.txt_temp_day)
        TextView txtTempDay;

        @BindView(R.id.txt_temp_night)
        TextView txtTempNight;

        @BindView(R.id.img_daily_condition)
        ImageView imgCondition;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(DailyForecast forecast) {
            txtDay.setText(forecast.getDay());
            txtTempDay.setText(forecast.getTempDay());
            txtTempNight.setText(forecast.getTempNight());
            imgCondition.setImageResource(Utils.getImageIdByWeatherCondition(forecast.getIconId()));
            bindColors(forecast);
        }

        private void bindColors(DailyForecast forecast) {
            @ColorRes int backgroundColorRes = Utils.getColorIdByWeatherCondition(forecast.getIconId());
            int backgroundColor = ContextCompat.getColor(itemView.getContext(), backgroundColorRes);
            itemView.setBackgroundColor(backgroundColor);

            int itemsColor = Utils.getContrastColor(backgroundColor);
            txtDay.setTextColor(itemsColor);
            txtTempDay.setTextColor(itemsColor);
            txtTempNight.setTextColor(itemsColor);
            imgCondition.setColorFilter(itemsColor);
        }
    }
}
