package com.vovatkach2427gmail.ukrainetravel.Act;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.vovatkach2427gmail.ukrainetravel.Adapter.ViewPagerNearPlaceAdapter;
import com.vovatkach2427gmail.ukrainetravel.DB.DataBaseWorker;
import com.vovatkach2427gmail.ukrainetravel.Model.City;
import com.vovatkach2427gmail.ukrainetravel.R;

public class NearPlaceAct extends AppCompatActivity {
    ImageView ivNavigationBack;
    int cityId;
    City currectCity;
    TextView tvProgress;
    SeekBar skProgress;
    ImageView ivUpDate;

    ViewPagerNearPlaceAdapter viewPagerNearPlaceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.near_place_act);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ivNavigationBack=(ImageView)findViewById(R.id.iv_act_near_place_navigation_back);
        //--------зчитування даних про вибране місто
        SharedPreferences preferences=getSharedPreferences("work",MODE_PRIVATE);
        cityId=preferences.getInt("city_id",1);
        //---------зчитуваннч даних з БД
        DataBaseWorker dataBaseWorker=new DataBaseWorker(NearPlaceAct.this);
        currectCity=dataBaseWorker.loadCity(cityId);
        dataBaseWorker.close();
        //----------вивід назви міста
        TextView tvTitle=(TextView)findViewById(R.id.tv_near_place_title);
        tvTitle.setText(currectCity.getName());
        //---------робота з радіусом
        ivUpDate =(ImageView)findViewById(R.id.iv_update_near_place);
        tvProgress=(TextView)findViewById(R.id.tv_near_place_progress);
        skProgress=(SeekBar)findViewById(R.id.sb_near_place_progress);
        skProgress.setOnSeekBarChangeListener(seekBarChangeListener);
        skProgress.setMax(5000);
        skProgress.setProgress(500);
        tvProgress.setText("Радіус "+Integer.toString(skProgress.getProgress())+" м");
        //--------------пошук ViewPager і Tab
        viewPagerNearPlaceAdapter=new ViewPagerNearPlaceAdapter(getSupportFragmentManager(),NearPlaceAct.this,cityId,skProgress.getProgress());
        ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager_near_place);
        viewPager.setAdapter(viewPagerNearPlaceAdapter);

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs_layout_near_place);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onResume() {
        super.onResume();
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
                        Intent intent=new Intent(NearPlaceAct.this, MainAct.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_left,R.anim.out_right);
                    }
                });
            }
        });
        ivUpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animatorX=ObjectAnimator.ofFloat(ivUpDate,View.SCALE_X,1.0f, 0.7f, 1.3f, 1.0f);
                ObjectAnimator animatorY=ObjectAnimator.ofFloat(ivUpDate,View.SCALE_Y,1.0f, 0.7f, 1.3f, 1.0f);
                AnimatorSet animatorSet=new AnimatorSet();
                animatorSet.play(animatorX).with(animatorY);
                animatorSet.setDuration(50);
                animatorSet.start();
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        viewPagerNearPlaceAdapter.setRadiusAndUpDateFragment(skProgress.getProgress());
                    }
                });
            }
        });
    }
    SeekBar.OnSeekBarChangeListener seekBarChangeListener=new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            tvProgress.setText("Радіус "+Integer.toString(progress)+" м");

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
