package com.zino.mobilization.weatheryamblz.repository.places;

import com.zino.mobilization.weatheryamblz.data.network.api.PlacesApi;
import com.zino.mobilization.weatheryamblz.data.network.response.places.PlaceResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.places.SuggestionsResponse;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

public class PlacesRepositoryImpl implements PlacesRepository {
    private PlacesApi api;

    @Inject
    public PlacesRepositoryImpl(PlacesApi api) {
        this.api = api;
    }

    @Override
    public Single<SuggestionsResponse> getSuggestions(String input) {
        return api.getSuggestions(input);
    }

    @Override
    public Single<PlaceResponse> getPlace(String cityId) {
        return api.getPlace(cityId);
    }
}
