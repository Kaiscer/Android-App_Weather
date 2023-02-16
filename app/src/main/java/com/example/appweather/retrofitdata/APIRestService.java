package com.example.appweather.retrofitdata;


import com.example.appweather.retrofiltutils.WeatherRes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRestService {

    public static final String BASE_URL = "https://api.darksky.net/forecast/";

    @GET("weather/")
    Call<WeatherRes> getWeather();

}