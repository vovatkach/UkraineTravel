package com.vovatkach2427gmail.ukrainetravel.Act;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vovatkach2427gmail.ukrainetravel.Adapter.RVAdapterWeather;
import com.vovatkach2427gmail.ukrainetravel.DB.DataBaseWorker;
import com.vovatkach2427gmail.ukrainetravel.Model.City;
import com.vovatkach2427gmail.ukrainetravel.Model.MyWeather;
import com.vovatkach2427gmail.ukrainetravel.R;
import com.vovatkach2427gmail.ukrainetravel.Retrofit.RequestWeather;
import com.vovatkach2427gmail.ukrainetravel.Retrofit.WeatherConverter;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;

public class WeatherAct extends AppCompatActivity {
    ImageView ivNavigationBack;
    TextView  tvTitle;
    RecyclerView rvWeather;
    LinearLayoutManager layoutManager;
    //---------
    TextView tvError;
    Button btnError;
    DilatingDotsProgressBar progressBar;
    //----------
    int cityId;
    City currectCity;
    ArrayList<MyWeather> myWeathers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_act);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        //-------------------------------
        ivNavigationBack=(ImageView)findViewById(R.id.iv_weather_act_navigation_back);
        tvTitle=(TextView)findViewById(R.id.tv_weather_act_title);
        //----------------------------
        SharedPreferences preferences=getSharedPreferences("work",MODE_PRIVATE);
        cityId=preferences.getInt("city_id",1);
        //--------------------------
        DataBaseWorker dataBaseWorker=new DataBaseWorker(WeatherAct.this);
        currectCity=dataBaseWorker.loadCity(cityId);
        dataBaseWorker.close();
        //-----------------------
        tvTitle.setText("Погода "+currectCity.getName());
        rvWeather=(RecyclerView)findViewById(R.id.rvWeather);
        rvWeather.setHasFixedSize(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //------------------------
        tvError=(TextView)findViewById(R.id.tv_is_not_weather);
        btnError=(Button)findViewById(R.id.btnWeatherError);
        progressBar=(DilatingDotsProgressBar)findViewById(R.id.progressWeather);
        layoutManager=new LinearLayoutManager(WeatherAct.this);
        rvWeather.setLayoutManager(layoutManager);
        myWeathers=new ArrayList<>();
        showWeather();
        //-------------------------
        btnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWeather();
            }
        });
        //-------------------------
        ivNavigationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animatorX=ObjectAnimator.ofFloat(ivNavigationBack,View.SCALE_X,1.0f, 0.85f, 1.15f, 1.0f);
                ObjectAnimator animatorY=ObjectAnimator.ofFloat(ivNavigationBack,View.SCALE_Y,1.0f, 0.85f, 1.15f, 1.0f);
                AnimatorSet animatorSet=new AnimatorSet();
                animatorSet.play(animatorX).with(animatorY);
                animatorSet.setDuration(50);
                animatorSet.start();
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Intent intent=new Intent(WeatherAct.this, MainAct.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_left,R.anim.out_right);
                    }
                });
            }
        });
    }

    private void showWeather()
    {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.showNow();
                        tvError.setVisibility(View.INVISIBLE);
                        btnError.setVisibility(View.INVISIBLE);
                    }
                });
                myWeathers= WeatherConverter.fromWeatherApiToMyWeather(RequestWeather.getWeather(WeatherAct.this,currectCity.getCoordinates()[0],currectCity.getCoordinates()[1]));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.hideNow();
                        if(!myWeathers.isEmpty())
                        {
                            RVAdapterWeather adapterWeather=new RVAdapterWeather(myWeathers);
                            rvWeather.setAdapter(adapterWeather);
                        }else
                        {
                            tvError.setVisibility(View.VISIBLE);
                            btnError.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
        thread.start();
    }
}
