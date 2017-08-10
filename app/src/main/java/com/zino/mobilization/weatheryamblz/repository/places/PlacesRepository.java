package com.zino.mobilization.weatheryamblz.repository.places;

import com.zino.mobilization.weatheryamblz.data.network.response.places.PlaceResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.places.SuggestionsResponse;

import io.reactivex.Single;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

public interface PlacesRepository {
    Single<SuggestionsResponse> getSuggestions(String input);
    Single<PlaceResponse> getPlace(String cityId);
}
