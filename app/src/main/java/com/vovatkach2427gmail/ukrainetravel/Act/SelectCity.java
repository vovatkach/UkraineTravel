package com.vovatkach2427gmail.ukrainetravel.Act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vovatkach2427gmail.ukrainetravel.Adapter.RVAdapterSelectCity;
import com.vovatkach2427gmail.ukrainetravel.DB.DataBaseWorker;
import com.vovatkach2427gmail.ukrainetravel.Model.City;
import com.vovatkach2427gmail.ukrainetravel.R;

import java.util.List;

public class SelectCity extends AppCompatActivity {
    RecyclerView rvCities;
    RecyclerView.LayoutManager layoutManager;
    List<City> cities;
    DataBaseWorker dataBaseWorker;
    RVAdapterSelectCity rvAdapterSelectCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city_act);
        rvCities =(RecyclerView)findViewById(R.id.rvSelectCity);
        rvCities.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(SelectCity.this);
        dataBaseWorker=new DataBaseWorker(SelectCity.this);
        cities=dataBaseWorker.loadCities();
        dataBaseWorker.close();
        rvAdapterSelectCity=new RVAdapterSelectCity(SelectCity.this,cities);
}

    @Override
    protected void onResume() {
        super.onResume();
        rvCities.setLayoutManager(layoutManager);
        rvCities.setAdapter(rvAdapterSelectCity);
    }
}


