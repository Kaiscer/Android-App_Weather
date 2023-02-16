package com.example.appweather.retrofitdata;


import com.example.appweather.retrofiltutils.WeatherRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIRestService {

    public static final String BASE_URL = "https://api.darksky.net/forecast/";

    @GET("{key}/{latitude},{longitude}")
    Call<WeatherRes> getWeather(@Path("key") String key,
                                @Path("latitude") Double latitude,
                                @Path("longitude") Double longitude,
                                @Query("exclude") String exclude,
                                @Query("lang") String lang);

}