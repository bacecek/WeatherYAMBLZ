package com.zino.mobilization.weatheryamblz.common;

/**
 * Created by Denis Buzmakov on 13.08.17.
 * <buzmakov.da@gmail.com>
 */

class TestJsons {
    static String getMoscowWeatherJson() {
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

    static String getPermWeatherJson() {
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

    static String getCorrectSuggestionsJson() {
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

    static String getEmptySuggestionsJson() {
        return "{\n" +
                "   \"predictions\" : [\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}";
    }

    static String getHourlyForecastJson() {
        return "{\n" +
                "\t\"city\": {\n" +
                "\t\t\"id\": 4735638,\n" +
                "\t\t\"name\": \"Tarrant County\",\n" +
                "\t\t\"coord\": {\n" +
                "\t\t\t\"lon\": -97.3003,\n" +
                "\t\t\t\"lat\": 32.7668\n" +
                "\t\t},\n" +
                "\t\t\"country\": \"US\",\n" +
                "\t\t\"population\": 1809034\n" +
                "\t},\n" +
                "\t\"cod\": \"200\",\n" +
                "\t\"message\": 18.054382,\n" +
                "\t\"cnt\": 5,\n" +
                "\t\"list\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"dt\": 1502647200,\n" +
                "\t\t\t\"temp\": {\n" +
                "\t\t\t\t\"day\": 302.39,\n" +
                "\t\t\t\t\"min\": 298.68,\n" +
                "\t\t\t\t\"max\": 305.03,\n" +
                "\t\t\t\t\"night\": 299.54,\n" +
                "\t\t\t\t\"eve\": 305.03,\n" +
                "\t\t\t\t\"morn\": 298.68\n" +
                "\t\t\t},\n" +
                "\t\t\t\"pressure\": 1003.37,\n" +
                "\t\t\t\"humidity\": 91,\n" +
                "\t\t\t\"weather\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 501,\n" +
                "\t\t\t\t\t\"main\": \"Rain\",\n" +
                "\t\t\t\t\t\"description\": \"moderate rain\",\n" +
                "\t\t\t\t\t\"icon\": \"10d\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"speed\": 1.96,\n" +
                "\t\t\t\"deg\": 295,\n" +
                "\t\t\t\"clouds\": 68,\n" +
                "\t\t\t\"rain\": 3.92\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"dt\": 1502733600,\n" +
                "\t\t\t\"temp\": {\n" +
                "\t\t\t\t\"day\": 304.89,\n" +
                "\t\t\t\t\"min\": 299.19,\n" +
                "\t\t\t\t\"max\": 308.21,\n" +
                "\t\t\t\t\"night\": 301.62,\n" +
                "\t\t\t\t\"eve\": 307.57,\n" +
                "\t\t\t\t\"morn\": 299.19\n" +
                "\t\t\t},\n" +
                "\t\t\t\"pressure\": 1000.54,\n" +
                "\t\t\t\"humidity\": 68,\n" +
                "\t\t\t\"weather\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 802,\n" +
                "\t\t\t\t\t\"main\": \"Clouds\",\n" +
                "\t\t\t\t\t\"description\": \"scattered clouds\",\n" +
                "\t\t\t\t\t\"icon\": \"03d\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"speed\": 4.21,\n" +
                "\t\t\t\"deg\": 203,\n" +
                "\t\t\t\"clouds\": 48\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"dt\": 1502820000,\n" +
                "\t\t\t\"temp\": {\n" +
                "\t\t\t\t\"day\": 306.38,\n" +
                "\t\t\t\t\"min\": 299.76,\n" +
                "\t\t\t\t\"max\": 308.47,\n" +
                "\t\t\t\t\"night\": 302.37,\n" +
                "\t\t\t\t\"eve\": 307.38,\n" +
                "\t\t\t\t\"morn\": 299.76\n" +
                "\t\t\t},\n" +
                "\t\t\t\"pressure\": 1000.6,\n" +
                "\t\t\t\"humidity\": 59,\n" +
                "\t\t\t\"weather\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 801,\n" +
                "\t\t\t\t\t\"main\": \"Clouds\",\n" +
                "\t\t\t\t\t\"description\": \"few clouds\",\n" +
                "\t\t\t\t\t\"icon\": \"02d\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"speed\": 6.96,\n" +
                "\t\t\t\"deg\": 189,\n" +
                "\t\t\t\"clouds\": 12\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"dt\": 1502906400,\n" +
                "\t\t\t\"temp\": {\n" +
                "\t\t\t\t\"day\": 307.41,\n" +
                "\t\t\t\t\"min\": 300.62,\n" +
                "\t\t\t\t\"max\": 308.5,\n" +
                "\t\t\t\t\"night\": 303.73,\n" +
                "\t\t\t\t\"eve\": 308.5,\n" +
                "\t\t\t\t\"morn\": 300.62\n" +
                "\t\t\t},\n" +
                "\t\t\t\"pressure\": 1001.66,\n" +
                "\t\t\t\"humidity\": 0,\n" +
                "\t\t\t\"weather\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 800,\n" +
                "\t\t\t\t\t\"main\": \"Clear\",\n" +
                "\t\t\t\t\t\"description\": \"sky is clear\",\n" +
                "\t\t\t\t\t\"icon\": \"01d\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"speed\": 7.48,\n" +
                "\t\t\t\"deg\": 201,\n" +
                "\t\t\t\"clouds\": 0\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"dt\": 1502992800,\n" +
                "\t\t\t\"temp\": {\n" +
                "\t\t\t\t\"day\": 307.97,\n" +
                "\t\t\t\t\"min\": 301.7,\n" +
                "\t\t\t\t\"max\": 309.11,\n" +
                "\t\t\t\t\"night\": 304.49,\n" +
                "\t\t\t\t\"eve\": 309.11,\n" +
                "\t\t\t\t\"morn\": 301.7\n" +
                "\t\t\t},\n" +
                "\t\t\t\"pressure\": 1004.95,\n" +
                "\t\t\t\"humidity\": 0,\n" +
                "\t\t\t\"weather\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 500,\n" +
                "\t\t\t\t\t\"main\": \"Rain\",\n" +
                "\t\t\t\t\t\"description\": \"light rain\",\n" +
                "\t\t\t\t\t\"icon\": \"10d\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"speed\": 5.38,\n" +
                "\t\t\t\"deg\": 203,\n" +
                "\t\t\t\"clouds\": 6,\n" +
                "\t\t\t\"rain\": 0.31\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
    }

    static String getDailyForecastJson() {
        return "{\n" +
                "\t\"cod\": \"200\",\n" +
                "\t\"message\": 0.0055,\n" +
                "\t\"cnt\": 8,\n" +
                "\t\"list\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"dt\": 1502636400,\n" +
                "\t\t\t\"main\": {\n" +
                "\t\t\t\t\"temp\": 300,\n" +
                "\t\t\t\t\"temp_min\": 297.424,\n" +
                "\t\t\t\t\"temp_max\": 300,\n" +
                "\t\t\t\t\"pressure\": 1003.5,\n" +
                "\t\t\t\t\"sea_level\": 1026.7,\n" +
                "\t\t\t\t\"grnd_level\": 1003.5,\n" +
                "\t\t\t\t\"humidity\": 99,\n" +
                "\t\t\t\t\"temp_kf\": 2.58\n" +
                "\t\t\t},\n" +
                "\t\t\t\"weather\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 500,\n" +
                "\t\t\t\t\t\"main\": \"Rain\",\n" +
                "\t\t\t\t\t\"description\": \"light rain\",\n" +
                "\t\t\t\t\t\"icon\": \"10d\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"clouds\": {\n" +
                "\t\t\t\t\"all\": 88\n" +
                "\t\t\t},\n" +
                "\t\t\t\"wind\": {\n" +
                "\t\t\t\t\"speed\": 2,\n" +
                "\t\t\t\t\"deg\": 34.5\n" +
                "\t\t\t},\n" +
                "\t\t\t\"rain\": {\n" +
                "\t\t\t\t\"3h\": 0.0075000000000003\n" +
                "\t\t\t},\n" +
                "\t\t\t\"sys\": {\n" +
                "\t\t\t\t\"pod\": \"d\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"dt_txt\": \"2017-08-13 15:00:00\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"dt\": 1502647200,\n" +
                "\t\t\t\"main\": {\n" +
                "\t\t\t\t\"temp\": 302.39,\n" +
                "\t\t\t\t\"temp_min\": 300.454,\n" +
                "\t\t\t\t\"temp_max\": 302.39,\n" +
                "\t\t\t\t\"pressure\": 1003.37,\n" +
                "\t\t\t\t\"sea_level\": 1026.35,\n" +
                "\t\t\t\t\"grnd_level\": 1003.37,\n" +
                "\t\t\t\t\"humidity\": 91,\n" +
                "\t\t\t\t\"temp_kf\": 1.94\n" +
                "\t\t\t},\n" +
                "\t\t\t\"weather\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 500,\n" +
                "\t\t\t\t\t\"main\": \"Rain\",\n" +
                "\t\t\t\t\t\"description\": \"light rain\",\n" +
                "\t\t\t\t\t\"icon\": \"10d\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"clouds\": {\n" +
                "\t\t\t\t\"all\": 68\n" +
                "\t\t\t},\n" +
                "\t\t\t\"wind\": {\n" +
                "\t\t\t\t\"speed\": 1.96,\n" +
                "\t\t\t\t\"deg\": 295.001\n" +
                "\t\t\t},\n" +
                "\t\t\t\"rain\": {\n" +
                "\t\t\t\t\"3h\": 0.009999999999998\n" +
                "\t\t\t},\n" +
                "\t\t\t\"sys\": {\n" +
                "\t\t\t\t\"pod\": \"d\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"dt_txt\": \"2017-08-13 18:00:00\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"dt\": 1502658000,\n" +
                "\t\t\t\"main\": {\n" +
                "\t\t\t\t\"temp\": 305.03,\n" +
                "\t\t\t\t\"temp_min\": 303.736,\n" +
                "\t\t\t\t\"temp_max\": 305.03,\n" +
                "\t\t\t\t\"pressure\": 1001.33,\n" +
                "\t\t\t\t\"sea_level\": 1024.15,\n" +
                "\t\t\t\t\"grnd_level\": 1001.33,\n" +
                "\t\t\t\t\"humidity\": 83,\n" +
                "\t\t\t\t\"temp_kf\": 1.29\n" +
                "\t\t\t},\n" +
                "\t\t\t\"weather\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 800,\n" +
                "\t\t\t\t\t\"main\": \"Clear\",\n" +
                "\t\t\t\t\t\"description\": \"clear sky\",\n" +
                "\t\t\t\t\t\"icon\": \"01d\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"clouds\": {\n" +
                "\t\t\t\t\"all\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"wind\": {\n" +
                "\t\t\t\t\"speed\": 2.06,\n" +
                "\t\t\t\t\"deg\": 256.502\n" +
                "\t\t\t},\n" +
                "\t\t\t\"rain\": {},\n" +
                "\t\t\t\"sys\": {\n" +
                "\t\t\t\t\"pod\": \"d\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"dt_txt\": \"2017-08-13 21:00:00\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"dt\": 1502668800,\n" +
                "\t\t\t\"main\": {\n" +
                "\t\t\t\t\"temp\": 305.03,\n" +
                "\t\t\t\t\"temp_min\": 304.386,\n" +
                "\t\t\t\t\"temp_max\": 305.03,\n" +
                "\t\t\t\t\"pressure\": 999.72,\n" +
                "\t\t\t\t\"sea_level\": 1022.6,\n" +
                "\t\t\t\t\"grnd_level\": 999.72,\n" +
                "\t\t\t\t\"humidity\": 71,\n" +
                "\t\t\t\t\"temp_kf\": 0.65\n" +
                "\t\t\t},\n" +
                "\t\t\t\"weather\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 800,\n" +
                "\t\t\t\t\t\"main\": \"Clear\",\n" +
                "\t\t\t\t\t\"description\": \"clear sky\",\n" +
                "\t\t\t\t\t\"icon\": \"01n\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"clouds\": {\n" +
                "\t\t\t\t\"all\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"wind\": {\n" +
                "\t\t\t\t\"speed\": 1.97,\n" +
                "\t\t\t\t\"deg\": 216.503\n" +
                "\t\t\t},\n" +
                "\t\t\t\"rain\": {},\n" +
                "\t\t\t\"sys\": {\n" +
                "\t\t\t\t\"pod\": \"n\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"dt_txt\": \"2017-08-14 00:00:00\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"dt\": 1502679600,\n" +
                "\t\t\t\"main\": {\n" +
                "\t\t\t\t\"temp\": 300.362,\n" +
                "\t\t\t\t\"temp_min\": 300.362,\n" +
                "\t\t\t\t\"temp_max\": 300.362,\n" +
                "\t\t\t\t\"pressure\": 1000.02,\n" +
                "\t\t\t\t\"sea_level\": 1023.09,\n" +
                "\t\t\t\t\"grnd_level\": 1000.02,\n" +
                "\t\t\t\t\"humidity\": 85,\n" +
                "\t\t\t\t\"temp_kf\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"weather\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 800,\n" +
                "\t\t\t\t\t\"main\": \"Clear\",\n" +
                "\t\t\t\t\t\"description\": \"clear sky\",\n" +
                "\t\t\t\t\t\"icon\": \"01n\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"clouds\": {\n" +
                "\t\t\t\t\"all\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"wind\": {\n" +
                "\t\t\t\t\"speed\": 1.16,\n" +
                "\t\t\t\t\"deg\": 95.5019\n" +
                "\t\t\t},\n" +
                "\t\t\t\"rain\": {},\n" +
                "\t\t\t\"sys\": {\n" +
                "\t\t\t\t\"pod\": \"n\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"dt_txt\": \"2017-08-14 03:00:00\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"dt\": 1502690400,\n" +
                "\t\t\t\"main\": {\n" +
                "\t\t\t\t\"temp\": 299.543,\n" +
                "\t\t\t\t\"temp_min\": 299.543,\n" +
                "\t\t\t\t\"temp_max\": 299.543,\n" +
                "\t\t\t\t\"pressure\": 1000.5,\n" +
                "\t\t\t\t\"sea_level\": 1023.62,\n" +
                "\t\t\t\t\"grnd_level\": 1000.5,\n" +
                "\t\t\t\t\"humidity\": 83,\n" +
                "\t\t\t\t\"temp_kf\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"weather\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 802,\n" +
                "\t\t\t\t\t\"main\": \"Clouds\",\n" +
                "\t\t\t\t\t\"description\": \"scattered clouds\",\n" +
                "\t\t\t\t\t\"icon\": \"03n\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"clouds\": {\n" +
                "\t\t\t\t\"all\": 36\n" +
                "\t\t\t},\n" +
                "\t\t\t\"wind\": {\n" +
                "\t\t\t\t\"speed\": 2.96,\n" +
                "\t\t\t\t\"deg\": 106.007\n" +
                "\t\t\t},\n" +
                "\t\t\t\"rain\": {},\n" +
                "\t\t\t\"sys\": {\n" +
                "\t\t\t\t\"pod\": \"n\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"dt_txt\": \"2017-08-14 06:00:00\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"dt\": 1502701200,\n" +
                "\t\t\t\"main\": {\n" +
                "\t\t\t\t\"temp\": 299.175,\n" +
                "\t\t\t\t\"temp_min\": 299.175,\n" +
                "\t\t\t\t\"temp_max\": 299.175,\n" +
                "\t\t\t\t\"pressure\": 999.85,\n" +
                "\t\t\t\t\"sea_level\": 1023.06,\n" +
                "\t\t\t\t\"grnd_level\": 999.85,\n" +
                "\t\t\t\t\"humidity\": 85,\n" +
                "\t\t\t\t\"temp_kf\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"weather\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 803,\n" +
                "\t\t\t\t\t\"main\": \"Clouds\",\n" +
                "\t\t\t\t\t\"description\": \"broken clouds\",\n" +
                "\t\t\t\t\t\"icon\": \"04n\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"clouds\": {\n" +
                "\t\t\t\t\"all\": 76\n" +
                "\t\t\t},\n" +
                "\t\t\t\"wind\": {\n" +
                "\t\t\t\t\"speed\": 3.92,\n" +
                "\t\t\t\t\"deg\": 114.505\n" +
                "\t\t\t},\n" +
                "\t\t\t\"rain\": {},\n" +
                "\t\t\t\"sys\": {\n" +
                "\t\t\t\t\"pod\": \"n\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"dt_txt\": \"2017-08-14 09:00:00\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"dt\": 1502712000,\n" +
                "\t\t\t\"main\": {\n" +
                "\t\t\t\t\"temp\": 299.191,\n" +
                "\t\t\t\t\"temp_min\": 299.191,\n" +
                "\t\t\t\t\"temp_max\": 299.191,\n" +
                "\t\t\t\t\"pressure\": 1000.05,\n" +
                "\t\t\t\t\"sea_level\": 1023.24,\n" +
                "\t\t\t\t\"grnd_level\": 1000.05,\n" +
                "\t\t\t\t\"humidity\": 79,\n" +
                "\t\t\t\t\"temp_kf\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"weather\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 804,\n" +
                "\t\t\t\t\t\"main\": \"Clouds\",\n" +
                "\t\t\t\t\t\"description\": \"overcast clouds\",\n" +
                "\t\t\t\t\t\"icon\": \"04d\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"clouds\": {\n" +
                "\t\t\t\t\"all\": 88\n" +
                "\t\t\t},\n" +
                "\t\t\t\"wind\": {\n" +
                "\t\t\t\t\"speed\": 3.67,\n" +
                "\t\t\t\t\"deg\": 176.504\n" +
                "\t\t\t},\n" +
                "\t\t\t\"rain\": {},\n" +
                "\t\t\t\"sys\": {\n" +
                "\t\t\t\t\"pod\": \"d\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"dt_txt\": \"2017-08-14 12:00:00\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"city\": {\n" +
                "\t\t\"id\": 4735638,\n" +
                "\t\t\"name\": \"Tarrant County\",\n" +
                "\t\t\"coord\": {\n" +
                "\t\t\t\"lat\": 32.7668,\n" +
                "\t\t\t\"lon\": -97.3003\n" +
                "\t\t},\n" +
                "\t\t\"country\": \"US\",\n" +
                "\t\t\"population\": 1809034\n" +
                "\t}\n" +
                "}";
    }
}
