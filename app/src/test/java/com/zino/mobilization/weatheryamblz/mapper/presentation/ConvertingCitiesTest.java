package com.zino.mobilization.weatheryamblz.mapper.presentation;

import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.common.TestData;
import com.zino.mobilization.weatheryamblz.data.db.entity.CityEntity;
import com.zino.mobilization.weatheryamblz.data.settings.units.PressureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.TemperatureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.Units;
import com.zino.mobilization.weatheryamblz.data.settings.units.WindSpeedUnit;
import com.zino.mobilization.weatheryamblz.mapper.BaseMapperTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Denis Buzmakov on 13.08.17.
 * <buzmakov.da@gmail.com>
 */

public class ConvertingCitiesTest extends BaseMapperTest {

    private final Units testUnits = new Units(TemperatureUnit.CELSIUS, PressureUnit.HPA, WindSpeedUnit.MS);

    @Test
    public void shouldReturnCities() {
        List<CityEntity> cityEntities = new ArrayList<>();
        cityEntities.add(TestData.getCityEntity());
        cityEntities.add(TestData.getCityEntity());

        List<City> cities = new ArrayList<>();
        cities.add(TestData.getCity());
        cities.add(TestData.getCity());

        assertEquals(cities, mapper.convertToCityFromEntities(cityEntities, testUnits));
    }

    @Test
    public void shouldReturnNull() {
        assertNull(mapper.convertToCityFromEntities(null, null));
        assertNull(mapper.convertToCityFromEntities(new ArrayList<>(), null));
        assertNull(mapper.convertToCityFromEntities(null, testUnits));
    }

    @Test
    public void shouldReturnCity() {
        assertEquals(TestData.getCity(), mapper.convertCityEntityToCity(TestData.getCityEntity(), testUnits));
    }

    @Test
    public void shouldReturnCityEntity() {
        assertEquals(TestData.getCityEntity(), mapper.convertCityToCityEntity(TestData.getCity()));
    }

}
