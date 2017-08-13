package com.zino.mobilization.weatheryamblz.interactor;

import com.zino.mobilization.weatheryamblz.business.Mapper;
import com.zino.mobilization.weatheryamblz.business.entity.Place;
import com.zino.mobilization.weatheryamblz.business.entity.Suggestion;
import com.zino.mobilization.weatheryamblz.business.interactor.add_city.AddCityInteractor;
import com.zino.mobilization.weatheryamblz.business.interactor.add_city.AddCityInteractorImpl;
import com.zino.mobilization.weatheryamblz.common.RxImmediateSchedulerRule;
import com.zino.mobilization.weatheryamblz.common.TestData;
import com.zino.mobilization.weatheryamblz.data.network.response.places.PlaceResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.places.SuggestionsResponse;
import com.zino.mobilization.weatheryamblz.repository.city.CitiesRepository;
import com.zino.mobilization.weatheryamblz.repository.places.PlacesRepository;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Buzmakov on 13.08.17.
 * <buzmakov.da@gmail.com>
 */

public class AddCityInteractorTest {
    @ClassRule
    public static RxImmediateSchedulerRule rxRule;

    @Mock
    private CitiesRepository citiesRepository;
    @Mock
    private PlacesRepository placesRepository;
    @Mock
    private Mapper mapper;

    private AddCityInteractor cityInteractor;
    private final Place place = TestData.getCorrectPlace();
    private final List<Suggestion> suggestions = TestData.getCorrectSuggestions();
    private final Throwable throwable = new Throwable();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        cityInteractor = new AddCityInteractorImpl(placesRepository, citiesRepository, mapper);

        when(placesRepository.getPlace(anyString())).thenReturn(Single.just(new PlaceResponse()));
        when(placesRepository.getSuggestions(anyString())).thenReturn(Single.just(new SuggestionsResponse()));
        when(mapper.convertPlaceFromResponse(any())).thenReturn(place);
        when(mapper.convertSuggestionsFromResponse(any())).thenReturn(suggestions);
    }

    @Test
    public void shouldReturnCorrectPlace() {
        cityInteractor.getPlace("kek")
                .test()
                .assertNoErrors()
                .assertComplete()
                .assertValue(place);

        verify(placesRepository).getPlace("kek");
        verify(mapper).convertPlaceFromResponse(any());
    }

    @Test
    public void shouldReturnCorrectSuggestions() {
        cityInteractor.getSuggestions("lol")
                .test()
                .assertNoErrors()
                .assertComplete()
                .assertValue(suggestions);

        verify(placesRepository).getSuggestions("lol");
        verify(mapper).convertSuggestionsFromResponse(any());
    }

    @Test
    public void shouldReturnFailInsteadPlace() {
        when(placesRepository.getPlace(anyString())).thenReturn(Single.error(throwable));

        cityInteractor.getPlace("lol")
                .test()
                .assertError(throwable)
                .assertNotComplete();

        verify(placesRepository).getPlace("lol");
        verify(mapper, never()).convertPlaceFromResponse(any());
    }

    @Test
    public void shouldReturnFailInsteadSuggestions() {
        when(placesRepository.getSuggestions(anyString())).thenReturn(Single.error(throwable));

        cityInteractor.getSuggestions("lol")
                .test()
                .assertError(throwable)
                .assertNotComplete();

        verify(placesRepository).getSuggestions("lol");
        verify(mapper, never()).convertSuggestionsFromResponse(any());
    }

    @Test
    public void shouldSuccessSavePlace() {
        when(citiesRepository.addCity(any())).thenReturn(Completable.complete());
        when(mapper.convertPlaceToCityEntity(any())).thenReturn(any());

        cityInteractor.savePlace(place)
                .test()
                .assertNoErrors()
                .assertComplete();

        verify(mapper).convertPlaceToCityEntity(any());
        verify(citiesRepository).addCity(any());
    }

    @Test
    public void shouldFailSavePlace() {
        when(citiesRepository.addCity(any())).thenReturn(Completable.error(throwable));

        cityInteractor.savePlace(place)
                .test()
                .assertNoErrors()
                .assertComplete();

        verify(mapper).convertPlaceToCityEntity(any());
        verify(citiesRepository, never()).addCity(any());
    }

    @Test
    public void shouldReturnNull() {
        when(mapper.convertPlaceToCityEntity(any())).thenReturn(null);

        cityInteractor.savePlace(place)
                .test()
                .assertError(NullPointerException.class)
                .assertNotComplete();

        verify(mapper).convertPlaceToCityEntity(any());
        verify(citiesRepository, never()).addCity(any());
    }
}
