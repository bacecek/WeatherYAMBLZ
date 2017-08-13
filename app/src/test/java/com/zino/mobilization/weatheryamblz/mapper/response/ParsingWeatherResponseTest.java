package com.zino.mobilization.weatheryamblz.mapper.response;

import com.zino.mobilization.weatheryamblz.common.TestData;
import com.zino.mobilization.weatheryamblz.data.db.entity.WeatherEntity;
import com.zino.mobilization.weatheryamblz.data.network.response.weather.WeatherResponse;
import com.zino.mobilization.weatheryamblz.mapper.BaseMapperTest;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * Created by Denis Buzmakov on 12.08.17.
 * <buzmakov.da@gmail.com>
 */

public class ParsingWeatherResponseTest extends BaseMapperTest{

    @Test
    public void shouldParseCorrect() {
        WeatherResponse response = TestData.getCorrectWeatherResponse();
        WeatherEntity entity = TestData.getCorrectWeatherEntity();
        assertEquals(entity, mapper.convertResponseToWeatherEntity(response));
    }

    @Test
    public void shouldReturnNull() {
        assertNull(mapper.convertResponseToWeatherEntity(null));
    }

    @Test
    public void shouldNotReturnNull() {
        WeatherResponse response = TestData.getIncorrectWeatherResponse();
        WeatherEntity entity = TestData.getIncorrectWeatherEntity();
        WeatherEntity returnedEntity = mapper.convertResponseToWeatherEntity(response);
        assertNotNull(returnedEntity);
        assertEquals(entity, returnedEntity);
    }

}
