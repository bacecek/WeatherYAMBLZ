package com.zino.mobilization.weatheryamblz.data.network.api;

import com.zino.mobilization.weatheryamblz.data.network.response.places.PlaceResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.places.SuggestionsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

public interface PlacesApi {
    @GET("autocomplete/json?types=(cities)")
    Single<SuggestionsResponse> getSuggestions(@Query("input") String input);

    @GET("details/json")
    Single<PlaceResponse> getPlace(@Query("placeid") String placeId);
}
