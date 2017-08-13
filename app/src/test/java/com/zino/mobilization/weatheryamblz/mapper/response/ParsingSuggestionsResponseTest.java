package com.zino.mobilization.weatheryamblz.mapper.response;

import com.zino.mobilization.weatheryamblz.business.entity.Suggestion;
import com.zino.mobilization.weatheryamblz.common.TestData;
import com.zino.mobilization.weatheryamblz.data.network.response.places.SuggestionsResponse;
import com.zino.mobilization.weatheryamblz.mapper.BaseMapperTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

/**
 * Created by Denis Buzmakov on 12.08.17.
 * <buzmakov.da@gmail.com>
 */

public class ParsingSuggestionsResponseTest extends BaseMapperTest {

    @Test
    public void shouldReturnCorrect() {
        SuggestionsResponse response = TestData.getCorrectSuggestionsReponse();
        List<Suggestion> result = TestData.getCorrectSuggestions();
        List<Suggestion> converted = mapper.convertSuggestionsFromResponse(response);
        assertNotNull(converted);
        assertEquals(converted, result);
    }

    @Test
    public void shouldReturnEmptyList() {
        SuggestionsResponse response = TestData.getEmptySuggestionsResponse();
        List<Suggestion> list = mapper.convertSuggestionsFromResponse(response);
        assertThat(list, hasSize(0));
        assertEquals(list, new ArrayList<Suggestion>());
    }

    @Test
    public void shouldReturnNull() {
        assertNull(mapper.convertSuggestionsFromResponse(null));
        assertNull(mapper.convertSuggestionsFromResponse(new SuggestionsResponse()));
    }

}
