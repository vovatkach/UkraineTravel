package com.vovatkach2427gmail.ukrainetravel.Model.ModelWeatherFromApi;

/**
 * Created by vovat on 25.04.2017.
 */

public class Clouds
{
    private String all;

    public String getAll ()
    {
        return all;
    }

    public void setAll (String all)
    {
        this.all = all;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [all = "+all+"]";
    }
}