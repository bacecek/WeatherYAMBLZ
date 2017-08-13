package com.zino.mobilization.weatheryamblz.repository;

import android.arch.persistence.room.EmptyResultSetException;

import com.zino.mobilization.weatheryamblz.common.TestData;
import com.zino.mobilization.weatheryamblz.data.db.dao.CityDao;
import com.zino.mobilization.weatheryamblz.data.db.entity.CityEntity;
import com.zino.mobilization.weatheryamblz.repository.city.CitiesRepository;
import com.zino.mobilization.weatheryamblz.repository.city.CitiesRepositoryImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Buzmakov on 13.08.17.
 * <buzmakov.da@gmail.com>
 */

public class CitiesRepositoryTest {

    @Mock
    private CityDao cityDao;

    private CitiesRepository citiesRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        citiesRepository = new CitiesRepositoryImpl(cityDao);
    }

    @Test
    public void shouldReturnCity() {
        CityEntity[] array = {TestData.getCityEntity()};
        when(cityDao.getCityById(anyString()))
                .thenReturn(Flowable.just(array));

        citiesRepository.getCity("kek")
                .test()
                .assertNoErrors()
                .assertValue(TestData.getCityEntity());

        verify(cityDao).getCityById("kek");
    }

    @Test
    public void shouldReturnExceptionCityDeleted() {
        CityEntity[] array = new CityEntity[0];
        when(cityDao.getCityById(anyString()))
                .thenReturn(Flowable.just(array));

        citiesRepository.getCity("kek")
                .test()
                .assertError(EmptyResultSetException.class);

        verify(cityDao).getCityById("kek");
    }

    @Test
    public void shouldReturnAllCities() {
        List<CityEntity> list = new ArrayList<>();
        list.add(TestData.getCityEntity());
        list.add(TestData.getCityEntity());

        when(cityDao.getAllCities())
                .thenReturn(Flowable.just(list));

        citiesRepository.getAllCities()
                .test()
                .assertNoErrors()
                .assertValue(list);

        verify(cityDao).getAllCities();
    }

    @Test
    public void shouldAddCity() {
        doNothing().when(cityDao).insertCity(any());

        citiesRepository.addCity(TestData.getCityEntity())
                .test()
                .assertNoErrors()
                .assertComplete();

        verify(cityDao).insertCity(TestData.getCityEntity());
    }

    @Test
    public void shouldFailAddCity() {
        doThrow(IllegalArgumentException.class).when(cityDao).insertCity(any());

        citiesRepository.addCity(TestData.getCityEntity())
                .test()
                .assertError(IllegalArgumentException.class)
                .assertNotComplete();

        verify(cityDao).insertCity(TestData.getCityEntity());
    }

    @Test
    public void shouldUpdateCity() {
        doNothing().when(cityDao).updateCity(any());

        citiesRepository.updateCity(TestData.getCityEntity())
                .test()
                .assertNoErrors()
                .assertComplete();

        verify(cityDao).updateCity(TestData.getCityEntity());
    }

    @Test
    public void shouldFailUpdateCity() {
        doThrow(IllegalArgumentException.class).when(cityDao).updateCity(any());

        citiesRepository.updateCity(TestData.getCityEntity())
                .test()
                .assertError(IllegalArgumentException.class)
                .assertNotComplete();

        verify(cityDao).updateCity(TestData.getCityEntity());
    }

    @Test
    public void shouldRemoveCity() {
        doNothing().when(cityDao).removeCity(anyString());

        citiesRepository.removeCity("kek")
                .test()
                .assertNoErrors()
                .assertComplete();

        verify(cityDao).removeCity("kek");
    }

    @Test
    public void shouldFailRemoveCity() {
        doThrow(NullPointerException.class).when(cityDao).removeCity(anyString());

        citiesRepository.removeCity("kek")
                .test()
                .assertError(NullPointerException.class)
                .assertNotComplete();

        verify(cityDao).removeCity("kek");
    }
}
