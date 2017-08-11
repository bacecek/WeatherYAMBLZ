package com.zino.mobilization.weatheryamblz.presentation.weather;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.zino.mobilization.weatheryamblz.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherActivity extends AppCompatActivity {
    private static final String KEY_CITY_ID = "city_id";

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        setTitle(R.string.title_weather);

        String cityId = null;
        if(getIntent() != null) {
            cityId = getIntent().getStringExtra(KEY_CITY_ID);
        }
        if(cityId != null) {
            if(savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container_weather, WeatherFragment.newInstance(cityId))
                        .commit();
            }
        } else {
            throw new IllegalArgumentException("City id must be not null");
        }
    }

    public static Intent newIntent(Context context, @NonNull String cityId) {
        Intent intent = new Intent(context, WeatherActivity.class);
        intent.putExtra(KEY_CITY_ID, cityId);
        return intent;
    }
}
