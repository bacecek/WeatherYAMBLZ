package com.zino.mobilization.weatheryamblz.business.interactor.add_city;

import com.zino.mobilization.weatheryamblz.business.Mapper;
import com.zino.mobilization.weatheryamblz.business.entity.Place;
import com.zino.mobilization.weatheryamblz.business.entity.Suggestion;
import com.zino.mobilization.weatheryamblz.data.db.entity.CityEntity;
import com.zino.mobilization.weatheryamblz.repository.city.CitiesRepository;
import com.zino.mobilization.weatheryamblz.repository.places.PlacesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

public class AddCityInteractorImpl implements AddCityInteractor {
    private PlacesRepository placesRepository;
    private CitiesRepository citiesRepository;
    private Mapper mapper;

    @Inject
    public AddCityInteractorImpl(PlacesRepository placesRepository,
                                 CitiesRepository citiesRepository,
                                 Mapper mapper) {
        this.placesRepository = placesRepository;
        this.citiesRepository = citiesRepository;
        this.mapper = mapper;
    }

    @Override
    public Single<List<Suggestion>> getSuggestions(String input) {
        return placesRepository.getSuggestions(input)
                .map(mapper::convertSuggestionsFromResponse);
    }

    @Override
    public Single<Place> getPlace(String cityId) {
        return placesRepository.getPlace(cityId)
                .map(mapper::convertPlaceFromResponse);
    }

    @Override
    public Completable savePlace(Place place) {
        CityEntity city = mapper.convertPlaceToCityEntity(place);
        if (city != null) {
            return citiesRepository.addCity(city);
        } else {
            return Completable.error(new NullPointerException());
        }
    }
}
