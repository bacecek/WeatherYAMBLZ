package com.zino.mobilization.weatheryamblz.data.network.response.places;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

public class SuggestionsResponse {

    @SerializedName("predictions")
    private List<SuggestionItem> suggestions;

    public List<SuggestionItem> getSuggestions() {
        return suggestions;
    }

    @Override
    public String toString() {
        return "SuggestionsResponse{" +
                "suggestions=" + suggestions +
                '}';
    }
}
