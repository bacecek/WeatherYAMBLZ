package com.zino.mobilization.weatheryamblz.common;

import com.google.gson.Gson;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.business.entity.CurrentWeather;
import com.zino.mobilization.weatheryamblz.business.entity.Place;
import com.zino.mobilization.weatheryamblz.business.entity.Suggestion;
import com.zino.mobilization.weatheryamblz.data.db.entity.CityEntity;
import com.zino.mobilization.weatheryamblz.data.db.entity.WeatherEntity;
import com.zino.mobilization.weatheryamblz.data.network.response.places.SuggestionsResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.weather.WeatherResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis Buzmakov on 29.07.17.
 * <buzmakov.da@gmail.com>
 */

public class TestData {

    public static WeatherResponse getCorrectWeatherResponse() {
        return new Gson().fromJson(getMoscowWeatherJson(), WeatherResponse.class);
    }

    public static WeatherResponse getIncorrectWeatherResponse() {
        return new Gson().fromJson(getPermWeatherJson(), WeatherResponse.class);
    }

    public static SuggestionsResponse getCorrectSuggestionsReponse() {
        return new Gson().fromJson(getCorrectSuggestionsJson(), SuggestionsResponse.class);
    }

    public static SuggestionsResponse getEmptySuggestionsResponse() {
        return new Gson().fromJson(getEmptySuggestionsJson(), SuggestionsResponse.class);
    }

    public static CityEntity getCityEntity() {
        return new CityEntity("afvnajdfjv",
                143513456,
                "Mosckw",
                "Mosckw, Rssia",
                33.3,
                66.6,
                getCorrectWeatherEntity());
    }

    public static City getCity() {
        return new City("afvnajdfjv",
                143513456,
                "Mosckw",
                "Mosckw, Rssia",
                33.3,
                66.6,
                getCurrentWeather()
        );
    }

    public static City getCityWithoutWeather() {
        return new City("afvnajdfjv",
                143513456,
                "Mosckw",
                "Mosckw, Rssia",
                33.3,
                66.6,
                null
        );
    }

    public static CurrentWeather getCurrentWeather() {
        return new CurrentWeather(
                "300.91",
                "scattered clouds",
                "51",
                "1007",
                "1501177452",
                "1501118798",
                "6",
                "10000",
                "40",
                802,
                "03d"
        );
    }

    public static List<Suggestion> getCorrectSuggestions() {
        List<Suggestion> list = new ArrayList<>();

        Suggestion suggestion = new Suggestion("ChIJpTvG15DL1IkRd8S0KlBVNTI", "Toronto, ON, Canada");
        list.add(suggestion);
        suggestion = new Suggestion("ChIJ4dG5s4K3wogRY7SWr4kTX6c", "Tampa, FL, United States");
        list.add(suggestion);
        suggestion = new Suggestion("ChIJK-0sC0Fl1oYRFccWTTgtw3M", "Tucson, AZ, United States");
        list.add(suggestion);
        suggestion = new Suggestion("ChIJJb4YZBJtiEcRv3ec1gP4A4k", "Torino, TO, Italia");
        list.add(suggestion);
        suggestion = new Suggestion("ChIJ_1J17G-7rhIRMBBBL5z2BgQ", "Toulouse, France");
        list.add(suggestion);

        return list;
    }

    public static WeatherEntity getCorrectWeatherEntity() {
        WeatherEntity entity = new WeatherEntity();
        entity.setTemperature(300.91);
        entity.setPressure(1007);
        entity.setHumidity(51);
        entity.setCloudiness(40);
        entity.setWindSpeed(6);
        entity.setVisibility(10000);
        entity.setConditionId(802);
        entity.setIconId("03d");
        entity.setSunsetTime(1501177452);
        entity.setSunriseTime(1501118798);
        entity.setDescription("scattered clouds");
        return entity;
    }

    public static WeatherEntity getIncorrectWeatherEntity() {
        WeatherEntity entity = new WeatherEntity();
        entity.setTemperature(294.845);
        entity.setPressure(1001.94);
        entity.setHumidity(78);
        entity.setCloudiness(8);
        entity.setWindSpeed(2.47);
        return entity;
    }

    public static Place getCorrectPlace() {
        return new Place("afvnajdfjv",
                "Mosckw",
                "Mosckw, Rssia",
                33.3,
                66.6);
    }

    private static String getMoscowWeatherJson() {
        return "{\n" +
                "    \"coord\": {\n" +
                "        \"lon\": 37.62,\n" +
                "        \"lat\": 55.75\n" +
                "    },\n" +
                "    \"weather\": [\n" +
                "        {\n" +
                "            \"id\": 802,\n" +
                "            \"main\": \"Clouds\",\n" +
                "            \"description\": \"scattered clouds\",\n" +
                "            \"icon\": \"03d\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"base\": \"stations\",\n" +
                "    \"main\": {\n" +
                "        \"temp\": 300.91,\n" +
                "        \"pressure\": 1007,\n" +
                "        \"humidity\": 51,\n" +
                "        \"temp_min\": 300.15,\n" +
                "        \"temp_max\": 302.15\n" +
                "    },\n" +
                "    \"visibility\": 10000,\n" +
                "    \"wind\": {\n" +
                "        \"speed\": 6,\n" +
                "        \"deg\": 260\n" +
                "    },\n" +
                "    \"clouds\": {\n" +
                "        \"all\": 40\n" +
                "    },\n" +
                "    \"dt\": 1501158600,\n" +
                "    \"sys\": {\n" +
                "        \"type\": 1,\n" +
                "        \"id\": 7325,\n" +
                "        \"message\": 0.0044,\n" +
                "        \"country\": \"RU\",\n" +
                "        \"sunrise\": 1501118798,\n" +
                "        \"sunset\": 1501177452\n" +
                "    },\n" +
                "    \"id\": 524901,\n" +
                "    \"name\": \"Moscow\",\n" +
                "    \"cod\": 200\n" +
                "}";
    }

    private static String getPermWeatherJson() {
        return "{\n" +
                "    \"coord\": {\n" +
                "        \"lon\": 56.29,\n" +
                "        \"lat\": 58.02\n" +
                "    },\n" +
                "    \"base\": \"stations\",\n" +
                "    \"main\": {\n" +
                "        \"temp\": 294.845,\n" +
                "        \"pressure\": 1001.94,\n" +
                "        \"humidity\": 78,\n" +
                "        \"temp_min\": 294.845,\n" +
                "        \"temp_max\": 294.845,\n" +
                "        \"sea_level\": 1022.12,\n" +
                "        \"grnd_level\": 1001.94\n" +
                "    },\n" +
                "    \"wind\": {\n" +
                "        \"speed\": 2.47,\n" +
                "        \"deg\": 320.504\n" +
                "    },\n" +
                "    \"clouds\": {\n" +
                "        \"all\": 8\n" +
                "    },\n" +
                "    \"dt\": 1501162005,\n" +
                "    \"id\": 511196,\n" +
                "    \"name\": \"Perm\",\n" +
                "    \"cod\": 200\n" +
                "}";
    }

    private static String getCorrectSuggestionsJson() {
        return "{\n" +
                "   \"predictions\" : [\n" +
                "      {\n" +
                "         \"description\" : \"Toronto, ON, Canada\",\n" +
                "         \"id\" : \"9cdc0b86ce6052ab269593184e7762372e698584\",\n" +
                "         \"matched_substrings\" : [\n" +
                "            {\n" +
                "               \"length\" : 1,\n" +
                "               \"offset\" : 0\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJpTvG15DL1IkRd8S0KlBVNTI\",\n" +
                "         \"reference\" : \"CjQrAAAA-v8kuHVbvPB-NQh-yxpX6v2-XFc3-RLuw057aCiKxuq1se6aFY6QAZEqQxZIys8sEhDMkJCk1W73l-Ce8i7umtwyGhQAO6VWR2hVEDiqvBljCyJrhcIG1w\",\n" +
                "         \"structured_formatting\" : {\n" +
                "            \"main_text\" : \"Toronto\",\n" +
                "            \"main_text_matched_substrings\" : [\n" +
                "               {\n" +
                "                  \"length\" : 1,\n" +
                "                  \"offset\" : 0\n" +
                "               }\n" +
                "            ],\n" +
                "            \"secondary_text\" : \"ON, Canada\"\n" +
                "         },\n" +
                "         \"terms\" : [\n" +
                "            {\n" +
                "               \"offset\" : 0,\n" +
                "               \"value\" : \"Toronto\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 9,\n" +
                "               \"value\" : \"ON\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 13,\n" +
                "               \"value\" : \"Canada\"\n" +
                "            }\n" +
                "         ],\n" +
                "         \"types\" : [ \"locality\", \"political\", \"geocode\" ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"description\" : \"Tampa, FL, United States\",\n" +
                "         \"id\" : \"9adf33187c5522ad19e75ec85d5537d812467a52\",\n" +
                "         \"matched_substrings\" : [\n" +
                "            {\n" +
                "               \"length\" : 1,\n" +
                "               \"offset\" : 0\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJ4dG5s4K3wogRY7SWr4kTX6c\",\n" +
                "         \"reference\" : \"CjQwAAAAZRRmONgaE6CE5T9R411__CpWg1SwpnHo7acKjvwX-8sVQMprX0gJskQAOXxsuJJuEhB8GQQENl9bP6U5OuUi6Y0xGhSUUacpRft9RT5JEO09_wBU9pE_mA\",\n" +
                "         \"structured_formatting\" : {\n" +
                "            \"main_text\" : \"Tampa\",\n" +
                "            \"main_text_matched_substrings\" : [\n" +
                "               {\n" +
                "                  \"length\" : 1,\n" +
                "                  \"offset\" : 0\n" +
                "               }\n" +
                "            ],\n" +
                "            \"secondary_text\" : \"FL, United States\"\n" +
                "         },\n" +
                "         \"terms\" : [\n" +
                "            {\n" +
                "               \"offset\" : 0,\n" +
                "               \"value\" : \"Tampa\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 7,\n" +
                "               \"value\" : \"FL\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 11,\n" +
                "               \"value\" : \"United States\"\n" +
                "            }\n" +
                "         ],\n" +
                "         \"types\" : [ \"locality\", \"political\", \"geocode\" ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"description\" : \"Tucson, AZ, United States\",\n" +
                "         \"id\" : \"18a0d3df0043eb1e84edb9c94856352a4be45104\",\n" +
                "         \"matched_substrings\" : [\n" +
                "            {\n" +
                "               \"length\" : 1,\n" +
                "               \"offset\" : 0\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJK-0sC0Fl1oYRFccWTTgtw3M\",\n" +
                "         \"reference\" : \"CkQxAAAA_eu4TiuBNQn9njETkRzlWiluxA44pJUkQPXXfSq67b9o_3EOAVa97Q1jkqEpcc15B4CaEYAEtqGS9VAps0k_AxIQM5uhfSig33PIBQWyrsI8xBoUKDf-Jb6ExgPeNLZv6AOYe3wNqjA\",\n" +
                "         \"structured_formatting\" : {\n" +
                "            \"main_text\" : \"Tucson\",\n" +
                "            \"main_text_matched_substrings\" : [\n" +
                "               {\n" +
                "                  \"length\" : 1,\n" +
                "                  \"offset\" : 0\n" +
                "               }\n" +
                "            ],\n" +
                "            \"secondary_text\" : \"AZ, United States\"\n" +
                "         },\n" +
                "         \"terms\" : [\n" +
                "            {\n" +
                "               \"offset\" : 0,\n" +
                "               \"value\" : \"Tucson\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 8,\n" +
                "               \"value\" : \"AZ\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 12,\n" +
                "               \"value\" : \"United States\"\n" +
                "            }\n" +
                "         ],\n" +
                "         \"types\" : [ \"locality\", \"political\", \"geocode\" ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"description\" : \"Torino, TO, Italia\",\n" +
                "         \"id\" : \"6df1d595fb03452c4811e884eb6b6d0bf3a92714\",\n" +
                "         \"matched_substrings\" : [\n" +
                "            {\n" +
                "               \"length\" : 1,\n" +
                "               \"offset\" : 0\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJJb4YZBJtiEcRv3ec1gP4A4k\",\n" +
                "         \"reference\" : \"CjQqAAAA_3yQEhhUG36d0kuDiV1TUQnGDwGkRvmGMDsaPjgMKBzyvsYVh5mOmzXAAetWNUfuEhCF1fTwmaW7zs447zmX6Kd-GhTZaWoRpZg9bQ9gjTO8Yqwht9bRGw\",\n" +
                "         \"structured_formatting\" : {\n" +
                "            \"main_text\" : \"Torino\",\n" +
                "            \"main_text_matched_substrings\" : [\n" +
                "               {\n" +
                "                  \"length\" : 1,\n" +
                "                  \"offset\" : 0\n" +
                "               }\n" +
                "            ],\n" +
                "            \"secondary_text\" : \"TO, Italia\"\n" +
                "         },\n" +
                "         \"terms\" : [\n" +
                "            {\n" +
                "               \"offset\" : 0,\n" +
                "               \"value\" : \"Torino\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 8,\n" +
                "               \"value\" : \"TO\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 12,\n" +
                "               \"value\" : \"Italia\"\n" +
                "            }\n" +
                "         ],\n" +
                "         \"types\" : [ \"locality\", \"political\", \"geocode\" ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"description\" : \"Toulouse, France\",\n" +
                "         \"id\" : \"57be81a36442e5303533878989d72092b99e6ecf\",\n" +
                "         \"matched_substrings\" : [\n" +
                "            {\n" +
                "               \"length\" : 1,\n" +
                "               \"offset\" : 0\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJ_1J17G-7rhIRMBBBL5z2BgQ\",\n" +
                "         \"reference\" : \"CjQoAAAAjs9P3vkOb_UWqIlJznOb2aSC3lVTnCrmlqnBmkbbxKtX7F30Cu9bYqIHo_CU0QUfEhBce6af0BLyvMf7-IhstuDoGhTI9NTfd7HqwOb5F7YEQMx6T6zE3w\",\n" +
                "         \"structured_formatting\" : {\n" +
                "            \"main_text\" : \"Toulouse\",\n" +
                "            \"main_text_matched_substrings\" : [\n" +
                "               {\n" +
                "                  \"length\" : 1,\n" +
                "                  \"offset\" : 0\n" +
                "               }\n" +
                "            ],\n" +
                "            \"secondary_text\" : \"France\"\n" +
                "         },\n" +
                "         \"terms\" : [\n" +
                "            {\n" +
                "               \"offset\" : 0,\n" +
                "               \"value\" : \"Toulouse\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 10,\n" +
                "               \"value\" : \"France\"\n" +
                "            }\n" +
                "         ],\n" +
                "         \"types\" : [ \"locality\", \"political\", \"geocode\" ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}";
    }

    private static String getEmptySuggestionsJson() {
        return "{\n" +
                "   \"predictions\" : [\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}";
    }

}
