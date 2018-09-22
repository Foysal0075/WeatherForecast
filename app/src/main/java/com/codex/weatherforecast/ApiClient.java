package com.codex.weatherforecast;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static String baseUrl = "https://api.openweathermap.org/data/2.5/";

    public static Retrofit getRetrofit(){

        return new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
