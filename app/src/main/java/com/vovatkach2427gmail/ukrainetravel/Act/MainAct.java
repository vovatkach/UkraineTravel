package com.vovatkach2427gmail.ukrainetravel.Act;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vovatkach2427gmail.ukrainetravel.DB.DataBaseWorker;
import com.vovatkach2427gmail.ukrainetravel.Model.City;
import com.vovatkach2427gmail.ukrainetravel.R;

import java.util.List;

public class MainAct extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    int id_city=3;
    City currectCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);
        ////----зчитування яке місто було вибрано
        SharedPreferences preferences = getSharedPreferences("work", MODE_PRIVATE);
        id_city = preferences.getInt("city_id", 3);
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
        TextView tvNavHeaderCityName=(TextView) headerView.findViewById(R.id.tvNavDrowerName);
        FrameLayout flNavHeader=(FrameLayout)headerView.findViewById(R.id.nav_header_frame_layot);
        //------зчитування вибраного міста з БД
        DataBaseWorker dataBaseWorker=new DataBaseWorker(MainAct.this);
        currectCity=dataBaseWorker.loadCity(id_city);
        dataBaseWorker.close();
        //--------картинка міста і назва в меню Drover
        flNavHeader.setBackgroundResource(currectCity.getImgs()[0]);
        tvNavHeaderCityName.setText(currectCity.getName());
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

       /* if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
