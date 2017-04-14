package com.vovatkach2427gmail.ukrainetravel.Act;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vovatkach2427gmail.ukrainetravel.Adapter.RVAdapterTaxi;
import com.vovatkach2427gmail.ukrainetravel.DB.DataBaseWorker;
import com.vovatkach2427gmail.ukrainetravel.Model.City;
import com.vovatkach2427gmail.ukrainetravel.Model.Taxi;
import com.vovatkach2427gmail.ukrainetravel.R;

import java.util.ArrayList;
import java.util.List;

public class TaxiAct extends AppCompatActivity {
    ImageView ivNavigationBack;
    TextView tvTitle;
    RecyclerView rvTaxi;
    //-----------
    int id_city=1;
    City currectCity;
    List<Taxi> taxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taxi_act);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_act_taxi);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        //------зчитування id вибраного міста
        SharedPreferences preferences = getSharedPreferences("work", MODE_PRIVATE);
        id_city = preferences.getInt("city_id", 1);
        //------зчитування вибраного міста з БД і списку таксі
        DataBaseWorker dataBaseWorker=new DataBaseWorker(TaxiAct.this);
        currectCity=dataBaseWorker.loadCity(id_city);
        taxis=dataBaseWorker.loadTaxis(id_city);
        dataBaseWorker.close();
        //-----------------------------
        ivNavigationBack=(ImageView)findViewById(R.id.iv_act_taxi_navigation_back);
        tvTitle=(TextView)findViewById(R.id.tv_taxi_title);
        tvTitle.setText(currectCity.getName());
        //----------------робота з recycler view
        rvTaxi=(RecyclerView)findViewById(R.id.rvTaxi);
        rvTaxi.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(TaxiAct.this);
        rvTaxi.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RVAdapterTaxi adapterTaxi=new RVAdapterTaxi(taxis,TaxiAct.this);
        rvTaxi.setAdapter(adapterTaxi);
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
                           Intent intent=new Intent(TaxiAct.this, MainAct.class);
                           startActivity(intent);
                           overridePendingTransition(R.anim.in_left,R.anim.out_right);
                    }
                });
            }
        });
    }
}


