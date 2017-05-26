package com.vovatkach2427gmail.ukrainetravel.Act;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.vovatkach2427gmail.ukrainetravel.Adapter.ViewPagerMainAdapter;
import com.vovatkach2427gmail.ukrainetravel.DB.DataBaseWorker;
import com.vovatkach2427gmail.ukrainetravel.Model.City;
import com.vovatkach2427gmail.ukrainetravel.MyLocationListener;
import com.vovatkach2427gmail.ukrainetravel.R;

public class MainAct extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    int id_city=1;
    City currectCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);
      //  MultiDex.install(this);
        ////----зчитування яке місто було вибрано
        SharedPreferences preferences = getSharedPreferences("work", MODE_PRIVATE);
        id_city = preferences.getInt("city_id", 1);
        //------зчитування вибраного міста з БД
        DataBaseWorker dataBaseWorker=new DataBaseWorker(MainAct.this);
        currectCity=dataBaseWorker.loadCity(id_city);
        dataBaseWorker.close();
        //------csrouselView
        CarouselView carouselViewCity=(CarouselView)findViewById(R.id.caroselViewCity);
        carouselViewCity.setPageCount(currectCity.getImgs().length);
        carouselViewCity.setImageListener(imageListenerCarouselViewCity);
        //--------ініціалізація view pager і TabsLayout
        ViewPager viewPagerMain=(ViewPager)findViewById(R.id.viewpager_main);
        viewPagerMain.setAdapter(new ViewPagerMainAdapter(getSupportFragmentManager(),MainAct.this,id_city));
        //-----
        TabLayout tabLayoutMain=(TabLayout)findViewById(R.id.tabs_layout_main);
        tabLayoutMain.setupWithViewPager(viewPagerMain);
        //-------пошук елементів
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setBackgroundResource(R.color.colorBlueGrey);
        navigationView.setNavigationItemSelectedListener(this);
        //-----пошук header в navigation droiver і його елементів
        View headerView = navigationView.getHeaderView(0);
        FrameLayout flNavHeader=(FrameLayout)headerView.findViewById(R.id.nav_header_frame_layot);
        //--------картинка міста і назва в меню Drover
        getSupportActionBar().setTitle(currectCity.getName());
        flNavHeader.setBackgroundResource(currectCity.getImgs()[0]);
        //-----------------------------------

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.nav_select_city:
                Intent intentGoSelectCity=new Intent(MainAct.this,SelectCity.class);
                startActivity(intentGoSelectCity);
                overridePendingTransition(R.anim.in_left,R.anim.out_right);
                break;
            case R.id.nav_taxi:
                Intent intentGoTaxi=new Intent(MainAct.this,TaxiAct.class);
                startActivity(intentGoTaxi);
                overridePendingTransition(R.anim.in_left,R.anim.out_right);
                break;
            case R.id.nav_near_places:
                Intent intentGoToNearPlace=new Intent(MainAct.this,NearPlaceAct.class);
                startActivity(intentGoToNearPlace);
                overridePendingTransition(R.anim.in_left,R.anim.out_right);
                break;
            case R.id.nav_weather:
                Intent intentGoToWeather=new Intent(MainAct.this,WeatherAct.class);
                startActivity(intentGoToWeather);
                overridePendingTransition(R.anim.in_left,R.anim.out_right);
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    ImageListener imageListenerCarouselViewCity=new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(currectCity.getImgs()[position]);
        }
    };
}
