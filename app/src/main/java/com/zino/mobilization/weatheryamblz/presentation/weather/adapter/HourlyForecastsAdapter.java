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
import com.zino.mobilization.weatheryamblz.business.entity.HourlyForecast;
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

public class HourlyForecastsAdapter extends RecyclerView.Adapter<HourlyForecastsAdapter.ViewHolder> {
    private List<HourlyForecast> data = new ArrayList<>();

    public HourlyForecastsAdapter(List<HourlyForecast> data) {
        this.data.addAll(data);
    }

    @Override
    public HourlyForecastsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hourly_forecast, parent, false);
        return new HourlyForecastsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(List<HourlyForecast> newData) {
        Timber.d("update data: " + newData.toString());
        this.data.clear();
        this.data.addAll(newData);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_time)
        TextView txtTime;

        @BindView(R.id.txt_hourly_temp)
        TextView txtTemp;

        @BindView(R.id.img_hourly_condition)
        ImageView imgCondition;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(HourlyForecast forecast) {
            txtTime.setText(forecast.getTime());
            txtTemp.setText(forecast.getTemperature());
            imgCondition.setImageResource(Utils.getImageIdByWeatherCondition(forecast.getIconId()));
            bindColors(forecast);
        }

        private void bindColors(HourlyForecast forecast) {
            @ColorRes int backgroundColorRes = Utils.getColorIdByWeatherCondition(forecast.getIconId());
            int backgroundColor = ContextCompat.getColor(itemView.getContext(), backgroundColorRes);
            itemView.setBackgroundColor(backgroundColor);

            int itemsColor = Utils.getContrastColor(backgroundColor);
            txtTime.setTextColor(itemsColor);
            txtTemp.setTextColor(itemsColor);
            imgCondition.setColorFilter(itemsColor);
        }
    }
}
