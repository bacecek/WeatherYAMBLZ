package com.zino.mobilization.weatheryamblz.presentation.cities;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Denis Buzmakov on 02.08.17.
 * <buzmakov.da@gmail.com>
 */

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {
    private List<City> data = new ArrayList<>();

    @Nullable
    private OnItemClickListener itemClickListener;

    CitiesAdapter(List<City> data, @Nullable OnItemClickListener itemClickListener) {
        this.data.addAll(data);
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cities, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(data.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    void updateData(List<City> data) {
        Timber.d("update data: " + data.toString());
        final CityDiffCallback diffCallback = new CityDiffCallback(this.data, data);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.data.clear();
        this.data.addAll(data);
        diffResult.dispatchUpdatesTo(this);
    }

    public List<City> getData() {
        return this.data;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.view_background)
        CardView viewBackground;

        @BindView(R.id.txt_downloading)
        TextView txtDownloading;

        @BindView(R.id.txt_city)
        TextView txtCity;

        @BindView(R.id.txt_address)
        TextView txtAddress;

        @BindView(R.id.txt_temperature)
        TextView txtTemperature;

        @BindView(R.id.txt_humidity)
        TextView txtHumidity;

        @BindView(R.id.img_umbrella)
        ImageView imgUmbrella;

        @BindView(R.id.img_condition)
        ImageView imgCondition;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(City city, @Nullable OnItemClickListener onItemClickListener) {
            Timber.d("bind city to holder: " + city.toString());
            txtCity.setText(city.getName());
            txtAddress.setText(city.getAddress());
            changeVisibleState(city, city.getCurrentWeather() == null);
            if(city.getCurrentWeather() != null) {
                txtTemperature.setText(String.valueOf(city.getCurrentWeather().getTemperature()));
                txtHumidity.setText(String.valueOf(city.getCurrentWeather().getHumidity()));
            }
            if(onItemClickListener != null) {
                itemView.setOnClickListener(v -> onItemClickListener.onItemClick(city));
            }
        }

        private void changeVisibleState(City city, boolean isDownloading) {
            txtDownloading.setVisibility(isDownloading ? View.VISIBLE : View.GONE);
            txtTemperature.setVisibility(isDownloading ? View.GONE : View.VISIBLE);
            txtHumidity.setVisibility(isDownloading ? View.GONE : View.VISIBLE);
            imgUmbrella.setVisibility(isDownloading ? View.GONE : View.VISIBLE);
            imgCondition.setVisibility(isDownloading ? View.GONE : View.VISIBLE);
            @ColorRes int backgroundColorRes;
            if(isDownloading) {
                backgroundColorRes = R.color.colorDownloadingWeatherBackground;
            } else {
                backgroundColorRes = Utils.getColorIdByWeatherCondition(city.getCurrentWeather().getIconId());
            }
            int backgroundColor = ContextCompat.getColor(itemView.getContext(), backgroundColorRes);
            viewBackground.setCardBackgroundColor(backgroundColor);

            int textColor = Utils.getContrastColor(backgroundColor);

            if(!isDownloading) {
                @DrawableRes int conditionRes = Utils.getImageIdByWeatherCondition(city.getCurrentWeather().getIconId());
                imgCondition.setImageResource(conditionRes);
                imgCondition.setColorFilter(textColor);
            }

            txtCity.setTextColor(textColor);
            txtAddress.setTextColor(textColor);
            txtDownloading.setTextColor(textColor);
            txtTemperature.setTextColor(textColor);
            txtHumidity.setTextColor(textColor);
            imgUmbrella.setColorFilter(textColor);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(City city);
    }
}
