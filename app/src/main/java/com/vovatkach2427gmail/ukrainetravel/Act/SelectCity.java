package com.vovatkach2427gmail.ukrainetravel.Act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        int[] imgsLviv={R.drawable.lviv_img_1,R.drawable.lviv_img_2,R.drawable.lviv_img_3};
        int [] imgsCity={R.drawable.city_img_1,R.drawable.city_img_2,R.drawable.city_img_3};

        rvCities =(RecyclerView)findViewById(R.id.rvSelectCity);
        rvCities.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(SelectCity.this);
        dataBaseWorker=new DataBaseWorker(SelectCity.this);
      /*  dataBaseWorker.addCity("Львів","null",imgsLviv);
        dataBaseWorker.addCity("Івано-Франківськ","null",imgsCity);
        dataBaseWorker.addCity("Чернівці","null",imgsCity);
        dataBaseWorker.addCity("Ужгород","null",imgsCity);
        dataBaseWorker.addCity("Севастополь","null",imgsCity);
        dataBaseWorker.addCity("Харків","null",imgsCity);
        dataBaseWorker.addCity("Тернопіль","null",imgsCity);
        dataBaseWorker.addCity("Одеса","null",imgsCity);
        dataBaseWorker.addCity("Київ","null",imgsCity);
        dataBaseWorker.addCity("Херсон","null",imgsCity);
        dataBaseWorker.addCity("Мукачево","null",imgsCity);
        dataBaseWorker.addCity("Кам'янець-Подільський","null",imgsCity);
        dataBaseWorker.addCity("Луцьк","null",imgsCity);
        dataBaseWorker.addCity("Хмельницький","null",imgsCity);
        dataBaseWorker.addCity("Чернігів","null",imgsCity);
        dataBaseWorker.addCity("Донецьк","null",imgsCity);
        dataBaseWorker.addCity("Трускавець","null",imgsCity);
        dataBaseWorker.addCity("Рівне","null",imgsCity);
        dataBaseWorker.addCity("Євпаторія","null",imgsCity);
        dataBaseWorker.addCity("Ялта","null",imgsCity); */
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


