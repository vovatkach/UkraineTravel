package com.vovatkach2427gmail.ukrainetravel.Act;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;
import com.vovatkach2427gmail.ukrainetravel.R;

public class PriewAct extends AppCompatActivity {
    Button btn_go_to_select_city;
    CarouselView carouselView;
    CheckBox cbNotShow;
    int [] imgs=
            {
            R.drawable.priew_act_carosel_img1,
            R.drawable.priew_act_carosel_img2,
            R.drawable.priew_act_carosel_img3,
            R.drawable.priew_act_carosel_img4,
            R.drawable.priew_act_carosel_img5,
            R.drawable.priew_act_carosel_img6,
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.priew_act);
        carouselView=(CarouselView)findViewById(R.id.cvPriewAct);
        carouselView.setPageCount(imgs.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(imgs[position]);
            }
        });

        btn_go_to_select_city=(Button)findViewById(R.id.btn_go_to_select_city);
        cbNotShow=(CheckBox)findViewById(R.id.cb_not_to_show);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_go_to_select_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbNotShow.isChecked())
                {
                    SharedPreferences preferences= getSharedPreferences("work", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("isPreview","no");
                    editor.commit();
                }
                Intent goToSelectCity=new Intent(PriewAct.this,SelectCity.class);
                startActivity(goToSelectCity);
                overridePendingTransition(R.anim.in_left,R.anim.out_right);
            }
        });
    }
}
