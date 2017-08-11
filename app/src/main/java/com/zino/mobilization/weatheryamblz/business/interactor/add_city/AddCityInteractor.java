package com.zino.mobilization.weatheryamblz.business.interactor.add_city;

import com.zino.mobilization.weatheryamblz.business.entity.Place;
import com.zino.mobilization.weatheryamblz.business.entity.Suggestion;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

public interface AddCityInteractor {
    Single<List<Suggestion>> getSuggestions(String input);
    Single<Place> getPlace(String cityId);
    Completable savePlace(Place place);
}
