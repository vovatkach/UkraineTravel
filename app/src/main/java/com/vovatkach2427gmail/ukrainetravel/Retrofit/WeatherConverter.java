package com.vovatkach2427gmail.ukrainetravel.Retrofit;

import com.vovatkach2427gmail.ukrainetravel.Model.ModelWeatherFromApi.WeatherFromApi;
import com.vovatkach2427gmail.ukrainetravel.Model.MyWeather;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vovat on 25.04.2017.
 */

public class WeatherConverter {

    public static ArrayList<MyWeather> fromWeatherApiToMyWeather(WeatherFromApi weatherFromApi)
    {
        ArrayList<MyWeather> myWeather=new ArrayList<>();
        if(weatherFromApi!=null){
        for (int i=0;i<weatherFromApi.getList().length;i++)
        {
            String mWeather;
            switch (weatherFromApi.getList()[i].getWeather()[0].getMain())
            {
                case "Clouds":
                    mWeather="Хмарно";
                    break;
                case "Clear":
                    mWeather="Сонячно";
                    break;
                case "Rain":
                    mWeather="Дощ";
                    break;
                case "Snow":
                    mWeather="Сніг";
                    break;
                default:
                    mWeather=weatherFromApi.getList()[i].getWeather()[0].getMain();
                    break;
            }
            String mTemp=Integer.toString((int) Math.round((Double.parseDouble(weatherFromApi.getList()[i].getMain().getTemp())-273.5)));
            String mDate=weatherFromApi.getList()[i].getDt_txt();
            myWeather.add(new MyWeather(mDate,mWeather,mTemp));
        }}
        return myWeather;
    }
}
