package com.vovatkach2427gmail.ukrainetravel.Retrofit;

import com.vovatkach2427gmail.ukrainetravel.Model.ModelWeatherFromApi.WeatherFromApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vovat on 25.04.2017.
 */

public interface WeatherApi {
    @GET("data/2.5/forecast")
    Call<WeatherFromApi> getWeatherForFourDays(@Query("lat") double lat,@Query("lon") double lon, @Query("APPID") String key);
}
