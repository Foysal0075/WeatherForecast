package com.codex.weatherforecast;

import com.codex.weatherforecast.api_class.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoint {

    @GET("forecast")
    Call<WeatherResponse> getForecastData(@Query("q") String location ,@Query("appid") String key);
}
