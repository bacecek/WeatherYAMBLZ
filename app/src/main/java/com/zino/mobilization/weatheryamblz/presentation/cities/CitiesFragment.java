package com.zino.mobilization.weatheryamblz.presentation.cities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.WeatherApplication;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.presentation.common.BaseFragment;
import com.zino.mobilization.weatheryamblz.presentation.main.OnCitySelectedListener;
import com.zino.mobilization.weatheryamblz.utils.GridDividerItemDecoration;
import com.zino.mobilization.weatheryamblz.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by Denis Buzmakov on 02.08.17.
 * <buzmakov.da@gmail.com>
 */

public class CitiesFragment extends BaseFragment implements CitiesView {
    private final static int REQUEST_CHOOSE_CITY = 777;
    private OnCitySelectedListener onCitySelectedListener;

    @InjectPresenter
    CitiesPresenter presenter;

    @BindView(R.id.list_cities)
    RecyclerView rvCities;

    @BindView(R.id.view_empty)
    View viewEmpty;

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
        if(isTablet) {
            if(orientation == Configuration.ORIENTATION_PORTRAIT) {
                //return new GridLayoutManager(getActivity(), 2);
                return new LinearLayoutManager(getActivity());
            } else {
                //return new GridLayoutManager(getActivity(), 3);
                return new LinearLayoutManager(getActivity());
            }
        } else {
            if(orientation == Configuration.ORIENTATION_PORTRAIT) {
                return new LinearLayoutManager(getActivity());
            } else {
                return new GridLayoutManager(getActivity(), 2);
            }
        }
    }

    private RecyclerView.ItemDecoration getItemDecoration() {
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        int orientation = getResources().getConfiguration().orientation;
        if(isTablet) {
            if(orientation == Configuration.ORIENTATION_PORTRAIT) {
                /*return new GridDividerItemDecoration(
                        (int) Utils.dpToPx(getResources(), 16),
                        2);*/
                final DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
                itemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.divider_cities));
                return itemDecoration;
            } else {
                /*return new GridDividerItemDecoration(
                        (int) Utils.dpToPx(getResources(), 16),
                        3);*/
                final DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
                itemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.divider_cities));
                return itemDecoration;
            }
        } else {
            if(orientation == Configuration.ORIENTATION_PORTRAIT) {
                final DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
                itemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.divider_cities));
                return itemDecoration;
            } else {
                return new GridDividerItemDecoration(
                        (int) Utils.dpToPx(getResources(), 16),
                        2);
            }
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
        try {
            AutocompleteFilter filter = new AutocompleteFilter.Builder()
                    .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                    .build();
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .setFilter(filter)
                    .build(getActivity());
            startActivityForResult(intent, REQUEST_CHOOSE_CITY);
        } catch (GooglePlayServicesRepairableException e) {
            GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), e.getConnectionStatusCode(), 0);
        } catch (GooglePlayServicesNotAvailableException e) {
            String message = getString(R.string.error_play_services_not_available,
                    GoogleApiAvailability.getInstance().getErrorString(e.errorCode));
            Timber.e(message);
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CHOOSE_CITY) {
            if(resultCode == Activity.RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                presenter.onCityChosen(place);
                Timber.d("returned place from places api: " + place.toString());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                Timber.e(status.getStatusMessage());
                Toast.makeText(getActivity(), status.getStatusMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static CitiesFragment newInstance() {
        return new CitiesFragment();
    }
}
