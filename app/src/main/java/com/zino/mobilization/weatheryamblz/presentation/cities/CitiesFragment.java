package com.zino.mobilization.weatheryamblz.presentation.cities;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.WeatherApplication;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.presentation.add_city.AddCityFragment;
import com.zino.mobilization.weatheryamblz.presentation.common.BaseFragment;
import com.zino.mobilization.weatheryamblz.presentation.main.OnCitySelectedListener;
import com.zino.mobilization.weatheryamblz.utils.GridDividerItemDecoration;
import com.zino.mobilization.weatheryamblz.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Denis Buzmakov on 02.08.17.
 * <buzmakov.da@gmail.com>
 */

public class CitiesFragment extends BaseFragment implements CitiesView {
    private OnCitySelectedListener onCitySelectedListener;

    @InjectPresenter
    CitiesPresenter presenter;

    @BindView(R.id.list_cities) RecyclerView rvCities;
    @BindView(R.id.view_empty) View viewEmpty;
    @BindView(R.id.layout_coordinator) CoordinatorLayout coordinatorLayout;

    private final ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            if(citiesAdapter != null) {
                City city = citiesAdapter.getData().get(position);
                presenter.onSwipeCity(city);
            }
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                float width = (float) viewHolder.itemView.getWidth();
                float alpha = 1.0f - Math.abs(dX) / width;
                viewHolder.itemView.setAlpha(alpha);
                viewHolder.itemView.setTranslationX(dX);
            } else {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY,
                        actionState, isCurrentlyActive);
            }
        }
    };
    private final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);

    private CitiesAdapter citiesAdapter;

    @OnClick(R.id.btn_add)
    void onClickAddCity() {
        presenter.onClickAddCity();
    }

    @ProvidePresenter
    CitiesPresenter provideWeatherPresenter() {
        return WeatherApplication.getAppComponent().getCitiesPresenter();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnCitySelectedListener) {
            onCitySelectedListener = (OnCitySelectedListener) context;
        } else {
            throw new ClassCastException(getClass().getName() + " must implement OnCitySelectedListener");
        }
    }

    @LayoutRes
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cities;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCities.setLayoutManager(getLayoutManager());
        rvCities.addItemDecoration(getItemDecoration());
        rvCities.setHasFixedSize(true);

        itemTouchHelper.attachToRecyclerView(rvCities);
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return new GridLayoutManager(getActivity(), 2);
        } else return new LinearLayoutManager(getActivity());
    }

    private RecyclerView.ItemDecoration getItemDecoration() {
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        int orientation = getResources().getConfiguration().orientation;
        if(isTablet && orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return new GridDividerItemDecoration(
                    (int) Utils.dpToPx(getResources(), 16),
                    2);
        } else {
            final DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
            itemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.divider_cities));
            return itemDecoration;
        }
    }

    @Override
    public void setEmptyViewVisible(boolean visible) {
        viewEmpty.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setCitiesListVisible(boolean visible) {
        rvCities.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void updateCities(List<City> cities) {
        if(citiesAdapter == null) {
            citiesAdapter = new CitiesAdapter(cities, city -> onCitySelectedListener.onCitySelected(city.getId()));
            rvCities.setAdapter(citiesAdapter);
        } else {
            citiesAdapter.updateData(cities);
        }
    }

    @Override
    public void openChooseCity() {
        AddCityFragment.newInstance()
                .show(getFragmentManager(), "add_city");
    }

    @Override
    public void showInfoMessage(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    public static CitiesFragment newInstance() {
        return new CitiesFragment();
    }
}
