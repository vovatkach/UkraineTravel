package com.vovatkach2427gmail.ukrainetravel.Act;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vovatkach2427gmail.ukrainetravel.DB.DataBaseWorker;
import com.vovatkach2427gmail.ukrainetravel.Model.Place;
import com.vovatkach2427gmail.ukrainetravel.R;

public class InfoPlaceAct extends AppCompatActivity {
    int place_id=1;
    Place currectPlace;
    ImageView ivNavigationBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_place_act);
        //--------------зчитування id вибраного місця
        SharedPreferences preferences=getSharedPreferences("work",MODE_PRIVATE);
        place_id=preferences.getInt("place_id",1);
        //----------------зчитування вибраного місця
        DataBaseWorker db = new DataBaseWorker(InfoPlaceAct.this);
        currectPlace=db.loadPlace(place_id);
        //--------------Робота з тулбаром
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        TextView tvTitle=(TextView)findViewById(R.id.tv_place_title);
        tvTitle.setText(currectPlace.getName());
        ivNavigationBack=(ImageView)findViewById(R.id.iv_act_place_navigation_back);
        //--------------------------------------
    }

    @Override
    protected void onResume() {
        super.onResume();
        ivNavigationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animatorX=ObjectAnimator.ofFloat(ivNavigationBack,View.SCALE_X,1.0f,0.9f,1.1f);
                ObjectAnimator animatorY=ObjectAnimator.ofFloat(ivNavigationBack,View.SCALE_Y,1.0f,0.9f,1.1f);
                AnimatorSet animatorSet=new AnimatorSet();
                animatorSet.play(animatorX).with(animatorY);
                animatorSet.setDuration(85);
                animatorSet.start();
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Intent intent=new Intent(InfoPlaceAct.this, MainAct.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_left,R.anim.out_right);
                    }
                });
            }
        });
    }
}
