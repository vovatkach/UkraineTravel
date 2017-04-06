package com.vovatkach2427gmail.ukrainetravel;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;

/**
 * Created by vovat on 06.04.2017.
 */

public class Call {
    public static void call(final Context context, View view, final String number) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1.0f, 0.85f, 1.15f, 1.0f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.0f, 0.85f, 1.15f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animatorX).with(animatorY);
        animatorSet.setDuration(50);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Intent intentDial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                    context.startActivity(intentDial);
                    return;
                }
                context.startActivity(intentCall);

                }
        });
    }
}
