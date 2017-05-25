package com.vovatkach2427gmail.ukrainetravel.Act;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.vovatkach2427gmail.ukrainetravel.MyLocationListener;
import com.vovatkach2427gmail.ukrainetravel.R;

public class WelcomeAct extends AppCompatActivity {
    private final static Integer REQUEST_LOCATION = 2;
    TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_act);
        tvWelcome = (TextView) findViewById(R.id.tvWelcomeName);
        //--------------------------------------------
        //-----включаємо оновлення локації
        if (checkPermission()) {
            MyLocationListener.SetUpLocationListener(WelcomeAct.this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Interpolator interpolator = new AccelerateInterpolator();
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvWelcome, View.ALPHA, 0.0f, 1.0f);
        animator.setDuration(5000);
        animator.setInterpolator(interpolator);
        tvWelcome.setVisibility(View.VISIBLE);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                SharedPreferences preferences = getSharedPreferences("work", MODE_PRIVATE);
                if (preferences.getString("isPreview", "yes").equals("yes")) {
                    Intent goToPriewAct = new Intent(WelcomeAct.this, PriewAct.class);
                    startActivity(goToPriewAct);
                    overridePendingTransition(R.anim.in_left, R.anim.out_right);
                } else {
                    Intent goToSelectCity = new Intent(WelcomeAct.this, SelectCity.class);
                    startActivity(goToSelectCity);
                    overridePendingTransition(R.anim.in_left, R.anim.out_right);
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 2: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyLocationListener.SetUpLocationListener(WelcomeAct.this);
                }
                return;
            }
        }

    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    2);
          //  Toast toastError = Toast.makeText(this, "Не вдалося получити дані про ваше місце*знаходження.\n Виключіть GPS.", Toast.LENGTH_SHORT);
          //  toastError.show();
            return false;
        } else {
            return true;
        }

    }
}



