package com.vovatkach2427gmail.ukrainetravel.Act;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.vovatkach2427gmail.ukrainetravel.R;

public class WelcomeAct extends AppCompatActivity {
    TextView tvWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_act);
        tvWelcome=(TextView)findViewById(R.id.tvWelcomeName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Interpolator interpolator=new AccelerateInterpolator();
        ObjectAnimator animator=ObjectAnimator.ofFloat(tvWelcome, View.ALPHA,0.0f,1.0f);
        animator.setDuration(5000);
        animator.setInterpolator(interpolator);
        tvWelcome.setVisibility(View.VISIBLE);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Intent goToPriewAct=new Intent(WelcomeAct.this,PriewAct.class);
                startActivity(goToPriewAct);
                overridePendingTransition(R.anim.in_left,R.anim.out_right);
            }
        });

    }
}
