package com.vovatkach2427gmail.ukrainetravel.Act;

import android.location.Location;
import android.location.LocationListener;
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
        //--------------------------------------------------------------------------------------
        //----перевірка чи можна сортувати
        if(locationUser!=null)
        {
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
        //---------------------------------------------
        //-----перші 100
        /*
        Log.d("myLog","1 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place1}));
        Log.d("myLog","2 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place2,}));
        Log.d("myLog","3 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place3,R.drawable.place3_1,R.drawable.place3_2}));
        Log.d("myLog","4 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place4}));
        Log.d("myLog","5 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place5,R.drawable.place5_1}));
        Log.d("myLog","6 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place6,R.drawable.place6_1}));
        Log.d("myLog","7 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place7,R.drawable.place7_1}));
        Log.d("myLog","8 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place8}));
        Log.d("myLog","9 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place9,R.drawable.place9_1}));
        Log.d("myLog","10 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place10}));
        Log.d("myLog","11 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place11,R.drawable.place11_1}));
        Log.d("myLog","12 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place12,R.drawable.place12_1}));
        Log.d("myLog","13 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place13,R.drawable.place13_1}));
        Log.d("myLog","14 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place14,R.drawable.place14_1,R.drawable.place14_2}));
        Log.d("myLog","15 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place15,R.drawable.place15_1}));
        Log.d("myLog","16 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place16,R.drawable.place16_1,R.drawable.place16_2}));
        Log.d("myLog","17 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place17,R.drawable.place17_1}));
        Log.d("myLog","18 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place18}));
        Log.d("myLog","19 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place19}));
        Log.d("myLog","20 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place20,R.drawable.place20_1}));
        Log.d("myLog","21 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place21,R.drawable.place21_1}));
        Log.d("myLog","22 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place22}));
        Log.d("myLog","23 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place23}));
        Log.d("myLog","24 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place24,R.drawable.place24_1}));
        Log.d("myLog","25 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place25}));

        Log.d("myLog","26 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place26}));
        Log.d("myLog","27 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place27}));
        Log.d("myLog","28 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place28}));
        Log.d("myLog","29 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place29,R.drawable.place29_1}));
        Log.d("myLog","31 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place31,R.drawable.place31_1}));
        Log.d("myLog","32 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place32}));
        Log.d("myLog","33 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place33}));
        Log.d("myLog","34 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place34,R.drawable.place34_1}));
        Log.d("myLog","35 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place35}));
        Log.d("myLog","36 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place36}));
        Log.d("myLog","37 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place37}));
        Log.d("myLog","38 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place38,R.drawable.place38_1}));
        Log.d("myLog","39 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place39}));
        Log.d("myLog","40 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place40}));
        Log.d("myLog","41 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place41,R.drawable.place41_1}));
        Log.d("myLog","42 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place42,R.drawable.place42_1}));
        Log.d("myLog","43 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place43}));
        Log.d("myLog","44 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place44}));
        Log.d("myLog","45 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place45}));
        Log.d("myLog","46 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place46,R.drawable.place46_1}));
        Log.d("myLog","47 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place47}));
        Log.d("myLog","48 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place48,R.drawable.place48_1}));
        Log.d("myLog","49 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place49,R.drawable.place49_1}));
        Log.d("myLog","50 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place50}));

        Log.d("myLog","51 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place51}));
        Log.d("myLog","52 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place52}));
        Log.d("myLog","54 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place54}));
        Log.d("myLog","55 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place55}));
        Log.d("myLog","56 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place56}));
        Log.d("myLog","57 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place57}));
        Log.d("myLog","58 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place58}));
        Log.d("myLog","59 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place59}));
        Log.d("myLog","60 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place60}));
        Log.d("myLog","61 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place61}));
        Log.d("myLog","62 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place62}));
        Log.d("myLog","63 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place63}));
        Log.d("myLog","64 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place64}));
        Log.d("myLog","65 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place65}));
        Log.d("myLog","66 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place66}));
        Log.d("myLog","67 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place67}));
        Log.d("myLog","68 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place68}));
        Log.d("myLog","69 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place69}));
        Log.d("myLog","70 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place70}));
        Log.d("myLog","71 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place71}));
        Log.d("myLog","72 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place72}));
        Log.d("myLog","73 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place73}));
        Log.d("myLog","74 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place74}));
        Log.d("myLog","75 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place75}));

        Log.d("myLog","76 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place76}));
        Log.d("myLog","77 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place77}));
        Log.d("myLog","78 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place78}));
        Log.d("myLog","79 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place79}));
        Log.d("myLog","80 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place80}));
        Log.d("myLog","81 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place81}));
        Log.d("myLog","82 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place82}));
        Log.d("myLog","83 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place83}));
        Log.d("myLog","84 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place84}));
        Log.d("myLog","85 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place85}));
        Log.d("myLog","86 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place86}));
        Log.d("myLog","87 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place87}));
        Log.d("myLog","88 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place88,R.drawable.place88_1}));
        Log.d("myLog","89 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place89}));
        Log.d("myLog","90 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place90}));
        Log.d("myLog","91 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place91}));
        Log.d("myLog","92 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place92}));
        Log.d("myLog","93 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place93}));
        Log.d("myLog","94 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place94}));
        Log.d("myLog","95 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place95}));
        Log.d("myLog","96 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place96}));
        Log.d("myLog","97 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place97}));
        Log.d("myLog","98 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place98}));
        Log.d("myLog","99 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place99}));
        Log.d("myLog","100 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place100}));
        */
        //-----------------------------------------------------------------------------------------
        //---101-200
/*
        Log.d("myLog","101 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place101}));
        Log.d("myLog","102 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place102}));
        Log.d("myLog","103 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place103}));
        Log.d("myLog","104 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place104}));
        Log.d("myLog","105 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place105}));
        Log.d("myLog","106 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place106}));
        Log.d("myLog","107 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place107}));
        Log.d("myLog","108 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place108}));
        Log.d("myLog","109 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place109}));
        Log.d("myLog","110 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place110}));
        Log.d("myLog","111 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place111}));
        Log.d("myLog","112 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place112}));
        Log.d("myLog","113 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place113}));
        Log.d("myLog","114 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place114}));
        Log.d("myLog","115 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place115}));
        Log.d("myLog","116 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place116}));
        Log.d("myLog","117 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place117}));
        Log.d("myLog","118 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place118}));
        Log.d("myLog","119 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place119}));
        Log.d("myLog","120 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place120}));
        Log.d("myLog","121 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place121}));
        Log.d("myLog","122 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place122}));
        Log.d("myLog","123 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place123}));
        Log.d("myLog","124 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place124}));
        Log.d("myLog","125 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place125}));

        Log.d("myLog","126 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place126}));
        Log.d("myLog","127 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place127}));
        Log.d("myLog","128 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place128}));
        Log.d("myLog","129 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place129}));
        Log.d("myLog","130 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place130}));
        Log.d("myLog","131 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place131}));
        Log.d("myLog","132 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place132}));
        Log.d("myLog","133 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place133}));
        Log.d("myLog","134 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kuiv134}));
        Log.d("myLog","135 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv135}));
        Log.d("myLog","136 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiiv136}));
        Log.d("myLog","137 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv137}));
        Log.d("myLog","138 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv138}));
        Log.d("myLog","139 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv139}));
        Log.d("myLog","140 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv140}));
        Log.d("myLog","141 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv141}));
        Log.d("myLog","142 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv142}));
        Log.d("myLog","143 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv143}));
        Log.d("myLog","144 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv144}));
        Log.d("myLog","146 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv146}));
        Log.d("myLog","147 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv147}));
        Log.d("myLog","148 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv148}));
        Log.d("myLog","149 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv149}));
        Log.d("myLog","150 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv150}));

        Log.d("myLog","151 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv151}));
        Log.d("myLog","152 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv152}));
        Log.d("myLog","153 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv153}));
        Log.d("myLog","154 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv154}));
        Log.d("myLog","155 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv155}));
        Log.d("myLog","156 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv156}));
        Log.d("myLog","157 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv157}));
        Log.d("myLog","158 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv158}));
        Log.d("myLog","159 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv159}));
        Log.d("myLog","160 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv160}));
        Log.d("myLog","161 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv161}));
        Log.d("myLog","162 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv162}));
        Log.d("myLog","163 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv163}));
        Log.d("myLog","164 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv164}));
        Log.d("myLog","165 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv165}));
        Log.d("myLog","166 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv166}));
        Log.d("myLog","167 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv167}));
        Log.d("myLog","168 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv168}));
        Log.d("myLog","169 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv169}));
        Log.d("myLog","170 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv170}));
        Log.d("myLog","171 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv171}));
        Log.d("myLog","172 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv172}));
        Log.d("myLog","174 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv174}));
        Log.d("myLog","175 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv175}));

        Log.d("myLog","176 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv176}));
        Log.d("myLog","177 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv177}));
        Log.d("myLog","178 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv178}));
        Log.d("myLog","179 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv179}));
        Log.d("myLog","180 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv180}));
        Log.d("myLog","181 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv181}));
        Log.d("myLog","182 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv182}));
        Log.d("myLog","183 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv183}));
        Log.d("myLog","184 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv184}));
        Log.d("myLog","185 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv185}));
        Log.d("myLog","187 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv187}));
        Log.d("myLog","188 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv188}));
        Log.d("myLog","189 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv189}));
        Log.d("myLog","190 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv190}));
        Log.d("myLog","191 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv191}));
        Log.d("myLog","192 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv192}));
        Log.d("myLog","193 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv193}));
        Log.d("myLog","194 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv194}));
        Log.d("myLog","195 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv195}));
        Log.d("myLog","196 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv196}));
        Log.d("myLog","197 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv197}));
        Log.d("myLog","198 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv198}));
        Log.d("myLog","199 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv199}));
        Log.d("myLog","200 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv200}));

        Log.d("myLog","201 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv201}));
        Log.d("myLog","202 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv202}));
        Log.d("myLog","203 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv203}));
        Log.d("myLog","204 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv204}));
        Log.d("myLog","205 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv205}));
        Log.d("myLog","206 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv206}));
        Log.d("myLog","207 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv207}));
        Log.d("myLog","208 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv208}));
        Log.d("myLog","209 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv209}));
        Log.d("myLog","210 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv210}));
        Log.d("myLog","211 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv211}));
        Log.d("myLog","212 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv212}));
        Log.d("myLog","213 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv213}));
        Log.d("myLog","214 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv214}));
        Log.d("myLog","215 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv215}));
        Log.d("myLog","216 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv216}));
        Log.d("myLog","217 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv217}));
        Log.d("myLog","218 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv218}));
        Log.d("myLog","219 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv219}));
        Log.d("myLog","220 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv220}));
        Log.d("myLog","221 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv221}));
        Log.d("myLog","223 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv223}));
        Log.d("myLog","224 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv224}));
        Log.d("myLog","225 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv225}));

        Log.d("myLog","226 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv226}));
        Log.d("myLog","227 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv227}));
        Log.d("myLog","228 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv228}));
        Log.d("myLog","229 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv229}));
        Log.d("myLog","230 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv230}));
        Log.d("myLog","231 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv231}));
        Log.d("myLog","232 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv232}));
        Log.d("myLog","233 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv233}));
        Log.d("myLog","234 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv234}));
        Log.d("myLog","235 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv235}));
        Log.d("myLog","236 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv236}));
        Log.d("myLog","237 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv237}));
        Log.d("myLog","238 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv238}));
        Log.d("myLog","239 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv239}));
        Log.d("myLog","240 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv240}));
        Log.d("myLog","241 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv241}));
        Log.d("myLog","242 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv242}));
        Log.d("myLog","243 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv243}));
        Log.d("myLog","244 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv244}));
        Log.d("myLog","245 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv245}));
        Log.d("myLog","246 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv246}));
        Log.d("myLog","247 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv247_3,R.drawable.kyiv247_1,R.drawable.kyiv247_2}));
        Log.d("myLog","248 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv248_3,R.drawable.kyiv248_1,R.drawable.kyiv248_2}));
        Log.d("myLog","249 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv249_3,R.drawable.kyiv249_1,R.drawable.kyiv249_2}));
        Log.d("myLog","250 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv250_3,R.drawable.kyiv250_1,R.drawable.kyiv250_2}));

        Log.d("myLog","251 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv251_3,R.drawable.kyiv251_1,R.drawable.kyiv251_2}));
        Log.d("myLog","252 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv252_3,R.drawable.kyiv252_1,R.drawable.kyiv252_2}));
        Log.d("myLog","253 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv253_3,R.drawable.kyiv253_1,R.drawable.kyiv253_2}));
        Log.d("myLog","254 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv254_3,R.drawable.kyiv254_1,R.drawable.kyiv254_2}));
        Log.d("myLog","255 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv255_3,R.drawable.kyiv255_1,R.drawable.kyiv255_2}));
        Log.d("myLog","256 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv256_3,R.drawable.kyiv256_1,R.drawable.kyiv256_2}));
        Log.d("myLog","257 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv257_3,R.drawable.kyiv257_1,R.drawable.kyiv257_2}));
        Log.d("myLog","258 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv258_3,R.drawable.kyiv258_1,R.drawable.kyiv258_2}));
        Log.d("myLog","259 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv259_3,R.drawable.kyiv259_1,R.drawable.kyiv259_2}));
        Log.d("myLog","260 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv260_3,R.drawable.kyiv260_1,R.drawable.kyiv260_2}));
        Log.d("myLog","261 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.kyiv261_3,R.drawable.kyiv261_1,R.drawable.kyiv261_2}));
        //провал
        Log.d("myLog","289 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place289}));
        Log.d("myLog","290 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place290}));
        Log.d("myLog","291 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place291}));
        Log.d("myLog","292 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place292}));
        Log.d("myLog","293 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place293}));
        Log.d("myLog","294 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place294}));
        Log.d("myLog","295 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place295}));
        Log.d("myLog","296 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place296}));
        Log.d("myLog","297 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place297,R.drawable.place297_1}));
        Log.d("myLog","298 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place298}));
        Log.d("myLog","299 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place299,R.drawable.place299_1}));
        Log.d("myLog","300 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place300,R.drawable.place300_1}));


        Log.d("myLog","301 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place301,R.drawable.place301_1}));
        Log.d("myLog","302 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place302,R.drawable.place302_1}));
        Log.d("myLog","303 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place303,R.drawable.place303_1,R.drawable.place303_2}));
        Log.d("myLog","304 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place304,R.drawable.place304_1,R.drawable.place304_2}));
        Log.d("myLog","305 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place305,R.drawable.place305_1}));
        Log.d("myLog","306 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place306,R.drawable.place306_1}));
        Log.d("myLog","307 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place307,R.drawable.place307_1}));
        Log.d("myLog","308 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place308,R.drawable.place308_1}));
        Log.d("myLog","309 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place309,R.drawable.place309_1,R.drawable.place309_2}));
        Log.d("myLog","310 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place310,R.drawable.place310_1}));
        Log.d("myLog","311 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place311}));
        Log.d("myLog","312 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place312}));
        Log.d("myLog","313 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place313}));
        Log.d("myLog","314 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place314}));
        Log.d("myLog","315 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place315,R.drawable.place315_1}));
        Log.d("myLog","316 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place316}));
        Log.d("myLog","317 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place317,R.drawable.place317_1}));
        Log.d("myLog","318 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place318,R.drawable.place318_1}));
        Log.d("myLog","319 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place319,R.drawable.place319_1,R.drawable.place319_2}));
        Log.d("myLog","320 "+  DataBaseWorker.imgsToJson(new int[]{R.drawable.place320,R.drawable.place300_1}));
        */




        //--------------------------------------------
        rvCities.setLayoutManager(layoutManager);
        rvCities.setAdapter(rvAdapterSelectCity);
        RequestWeather.ClearSaveWeather();
    }
}


