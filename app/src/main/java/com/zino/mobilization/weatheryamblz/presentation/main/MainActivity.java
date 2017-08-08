package com.zino.mobilization.weatheryamblz.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.presentation.cities.CitiesFragment;
import com.zino.mobilization.weatheryamblz.presentation.settings.SettingsActivity;
import com.zino.mobilization.weatheryamblz.presentation.weather.WeatherActivity;
import com.zino.mobilization.weatheryamblz.presentation.weather.WeatherFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements OnCitySelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setTitle(R.string.title_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_cities, CitiesFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCitySelected(String cityId) {
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        if(isTablet) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_weather, WeatherFragment.newInstance(cityId))
                    .commit();
        } else {
            startActivity(WeatherActivity.newIntent(this, cityId));
        }
    }
}
