package com.vovatkach2427gmail.ukrainetravel.Act;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.vovatkach2427gmail.ukrainetravel.DB.DataBaseWorker;
import com.vovatkach2427gmail.ukrainetravel.Model.Place;
import com.vovatkach2427gmail.ukrainetravel.R;

public class PlaceAct extends AppCompatActivity {
    int place_id=1;
    Place currectPlace;
    ImageView ivNavigationBack;
    ImageView ivCall;
    ImageView ivWebsite;
    ImageView ivAudio;
    ImageView ivMap;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_act);
        //--------------зчитування id вибраного місця
        SharedPreferences preferences=getSharedPreferences("work",MODE_PRIVATE);
        place_id=preferences.getInt("place_id",1);
        //----------------зчитування вибраного місця
        DataBaseWorker db = new DataBaseWorker(PlaceAct.this);
        currectPlace=db.loadPlace(place_id);
        //--------------Робота з тулбаром
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        TextView tvTitle=(TextView)findViewById(R.id.tv_place_title);
        tvTitle.setText(currectPlace.getName());
        ivNavigationBack=(ImageView)findViewById(R.id.iv_act_place_navigation_back);
        //----------------Перевірка чи ТОП
        if(currectPlace.getIsTOP()==1){
            ImageView ivIsTop=(ImageView)findViewById(R.id.iv_isTop_act_place);
            ivIsTop.setVisibility(View.VISIBLE);
        }
        //--------------------------------------
        ivCall=(ImageView)findViewById(R.id.iv_call_place);
        ivWebsite=(ImageView)findViewById(R.id.iv_website_place);
        ivAudio=(ImageView)findViewById(R.id.iv_audio_place);
        ivMap=(ImageView)findViewById(R.id.iv_map_place);
        //------------carouselView
        CarouselView carouselViewPlace=(CarouselView)findViewById(R.id.carousel_view_item_place_act);
        carouselViewPlace.setPageCount(currectPlace.getImgs().length);
        carouselViewPlace.setImageListener(imageListenerCarouselPlace);
        //----------адреса і години роботи
        TextView tvAddress=(TextView)findViewById(R.id.tv_text_address_place_act);
        tvAddress.setText(currectPlace.getAddrres());
        TextView tvHoursOfWork=(TextView)findViewById(R.id.tv_text_hours_of_work_place_act);
        tvHoursOfWork.setText(currectPlace.getHoursOFWork());
        //----------------опис місця
        TextView tvDescription=(TextView)findViewById(R.id.tv_description_place);
        tvDescription.setText(currectPlace.getDescription());
        //----------------------------------

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mediaPlayer!=null)
            if(mediaPlayer.isPlaying())mediaPlayer.stop();
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
                        Intent intent=new Intent(PlaceAct.this, MainAct.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_left,R.anim.out_right);
                    }
                });
            }
        });

        //-----------------------
        ivCall.setOnClickListener(onClickListenerCallWebsiteAudioMap);
        ivWebsite.setOnClickListener(onClickListenerCallWebsiteAudioMap);
        ivAudio.setOnClickListener(onClickListenerCallWebsiteAudioMap);
        ivMap.setOnClickListener(onClickListenerCallWebsiteAudioMap);
        //----------------перевірка чи є аудіо, сайт, номер
        if(currectPlace.getAudio().equals("no")) {
            ivAudio.setEnabled(false);
            ivAudio.setAlpha(0.2f);
        }
        if(currectPlace.getPhone().equals("no")) {
            ivCall.setEnabled(false);
            ivCall.setAlpha(0.2f);
        }
        if(currectPlace.getWebsite().equals("no")) {
            ivWebsite.setEnabled(false);
            ivWebsite.setAlpha(0.2f);
        }
        if(currectPlace.getCoordinates().equals("no")) {
            ivMap.setEnabled(false);
            ivMap.setAlpha(0.2f);
        }
        //---------------------------------------
    }
    View.OnClickListener onClickListenerCallWebsiteAudioMap =new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            ObjectAnimator animatorX = ObjectAnimator.ofFloat(v, View.SCALE_X, 1.0f, 0.85f, 1.15f, 1.0f);
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(v, View.SCALE_Y, 1.0f, 0.85f, 1.15f, 1.0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(animatorX).with(animatorY);
            animatorSet.setDuration(50);
            animatorSet.start();
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    switch (v.getId())
                    {
                        case R.id.iv_call_place:
                            Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + currectPlace.getPhone()));
                            if (ActivityCompat.checkSelfPermission(PlaceAct.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                Intent intentDial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + currectPlace.getPhone()));
                                startActivity(intentDial);
                                return;
                            }
                            startActivity(intentCall);
                            break;
                        case R.id.iv_website_place:
                            Intent intentWebsite=new Intent(Intent.ACTION_VIEW, Uri.parse(currectPlace.getWebsite()));
                            startActivity(intentWebsite);
                            break;
                        case R.id.iv_audio_place:
                            if(mediaPlayer==null){
                            mediaPlayer=MediaPlayer.create(PlaceAct.this,R.raw.timati);
                            mediaPlayer.start();
                            ivAudio.setImageResource(R.drawable.x_stop);
                            }else
                                {
                                    if(mediaPlayer.isPlaying())mediaPlayer.stop();
                                    mediaPlayer=null;
                                    ivAudio.setImageResource(R.drawable.x_headphones);
                                }
                            break;
                    }
                }
            });
        }
    };
    ImageListener imageListenerCarouselPlace=new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(currectPlace.getImgs()[position]);
        }
    };
}
