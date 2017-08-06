package com.zino.mobilization.weatheryamblz.presentation.main;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.presentation.cities.CitiesFragment;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity
        implements OnNavigationChanged {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CitiesFragment.newInstance())
                    .commit();
        }

        setSupportActionBar(toolbar);
    }

    @Override
    public void setTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    public interface OnNavigationChanged {
        void setTitle(String title);
    }
}
