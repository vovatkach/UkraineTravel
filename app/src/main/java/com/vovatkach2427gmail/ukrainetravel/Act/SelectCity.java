package com.vovatkach2427gmail.ukrainetravel.Act;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.vovatkach2427gmail.ukrainetravel.Adapter.RVAdapterSelectCity;
import com.vovatkach2427gmail.ukrainetravel.DB.DataBaseWorker;
import com.vovatkach2427gmail.ukrainetravel.Model.City;
import com.vovatkach2427gmail.ukrainetravel.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

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
        locationUser=getLocationUser();
        rvCities = (RecyclerView) findViewById(R.id.rvSelectCity);
        rvCities.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(SelectCity.this);
        dataBaseWorker = new DataBaseWorker(SelectCity.this);
        cities = dataBaseWorker.loadCities();
        dataBaseWorker.close();
        locationUser=getLocationUser();
        //----перевірка чи можна сортувати
        if(locationUser!=null)
        {
            for (City city:cities){city.setDistanceToUser(locationUser);}
            Collections.sort(cities,City.COMPARATOR_BY_DISTANT_TO_USER);
        }
        rvAdapterSelectCity = new RVAdapterSelectCity(SelectCity.this, cities);
    }

    @Override
    protected void onResume() {
        super.onResume();
        rvCities.setLayoutManager(layoutManager);
        rvCities.setAdapter(rvAdapterSelectCity);
    }

    private Location getLocationUser() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast toastError=Toast.makeText(this,"Не вдалося получити дані про ваше місцезнаходження.\n Виключений інтернет і GPS.",Toast.LENGTH_SHORT);
            toastError.show();
        }
        Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location locationNETWORK=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if((locationGPS!=null)&&(locationNETWORK!=null))
        {
            if(locationNETWORK.getTime()>locationGPS.getTime())return locationNETWORK;else return locationGPS;
        }else
            if(locationNETWORK!=null)
            {
                return locationNETWORK;
            }else
                if(locationGPS!=null)
                {
                    return locationGPS;
                }else return null;


    }
}


