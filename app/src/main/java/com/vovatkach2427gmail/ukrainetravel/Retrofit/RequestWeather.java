package com.vovatkach2427gmail.ukrainetravel.Retrofit;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.vovatkach2427gmail.ukrainetravel.Model.ModelWeatherFromApi.WeatherFromApi;
import com.vovatkach2427gmail.ukrainetravel.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vovat on 25.04.2017.
 */

public class RequestWeather {
    private static WeatherFromApi currectWeather=null;
    public static WeatherFromApi getWeather(Context context,Double lat, Double lon)
    {
        if(currectWeather==null)
        {
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            WeatherApi weatherApi=retrofit.create(WeatherApi.class);
            try {
                Response<WeatherFromApi> response=weatherApi.getWeatherForFourDays(lat,lon,context.getResources().getString(R.string.weather_key_api)).execute();
                if (response!=null)
                {
                    currectWeather = response.body();
            }
            } catch (IOException e) {
            }
        }else
            {
                return currectWeather;
            }
            return currectWeather;
    }

    public static void ClearSaveWeather()
    {
        if(currectWeather!=null)
        {
            currectWeather=null;
        }
    }

}
