package com.vovatkach2427gmail.ukrainetravel.Model;

/**
 * Created by vovat on 25.04.2017.
 */

public class MyWeather {
    public String date;
    public String weather;
    public String temperature;
    public MyWeather(String date,String weather,String temperature)
    {
        this.date=date;
        this.weather=weather;
        this.temperature=temperature;
    }
    //--------------------------------------
    public String getDate() {
        return date;
    }

    public String getWeather() {
        return weather;
    }

    public String getTemperature() {
        return temperature+"°C";
    }
}
