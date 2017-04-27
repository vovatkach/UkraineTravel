package com.vovatkach2427gmail.ukrainetravel.Act;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vovatkach2427gmail.ukrainetravel.Adapter.RVAdapterSelectCity;
import com.vovatkach2427gmail.ukrainetravel.DB.DataBaseWorker;
import com.vovatkach2427gmail.ukrainetravel.Model.City;
import com.vovatkach2427gmail.ukrainetravel.Model.ModelWeatherFromApi.WeatherFromApi;
import com.vovatkach2427gmail.ukrainetravel.Model.MyWeather;
import com.vovatkach2427gmail.ukrainetravel.MyLocationListener;
import com.vovatkach2427gmail.ukrainetravel.R;
import com.vovatkach2427gmail.ukrainetravel.Retrofit.RequestWeather;
import com.vovatkach2427gmail.ukrainetravel.Retrofit.WeatherConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static com.vovatkach2427gmail.ukrainetravel.Retrofit.RequestWeather.getWeather;
import static com.vovatkach2427gmail.ukrainetravel.Retrofit.WeatherConverter.fromWeatherApiToMyWeather;

public class SelectCity extends AppCompatActivity {
    RecyclerView rvCities;
    RecyclerView.LayoutManager layoutManager;
    List<City> cities;
    DataBaseWorker dataBaseWorker;
    RVAdapterSelectCity rvAdapterSelectCity;
    Location locationUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city_act);
        locationUser= MyLocationListener.getUserLocation();
        rvCities = (RecyclerView) findViewById(R.id.rvSelectCity);
        rvCities.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(SelectCity.this);
        dataBaseWorker = new DataBaseWorker(SelectCity.this);
        cities = dataBaseWorker.loadCities();
        dataBaseWorker.close();
        // Log.d("myLog",DataBaseWorker.imgsToJson(new int[]{R.drawable.lviv_img_1,R.drawable.lviv_img_2,R.drawable.lviv_img_3}));
        // Log.d("myLog",DataBaseWorker.imgsToJson(new int[]{R.drawable.v_img_place_1,R.drawable.v_img_place_2,R.drawable.v_img_place_3}));
        //----перевірка чи можна сортувати
        if(locationUser!=null)
        {
            Log.d("myLog","є локація");
            for (City city:cities){city.setDistanceToUser(locationUser);}
            Collections.sort(cities,City.COMPARATOR_BY_DISTANT_TO_USER);
        }else
            {
                Collections.sort(cities,City.COMPARATOR_BY_NAME);
            }
        rvAdapterSelectCity = new RVAdapterSelectCity(SelectCity.this, cities);
    }

    @Override
    protected void onResume() {
        super.onResume();
        rvCities.setLayoutManager(layoutManager);
        rvCities.setAdapter(rvAdapterSelectCity);
        RequestWeather.ClearSaveWeather();

    }
}


