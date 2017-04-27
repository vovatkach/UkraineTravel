package com.vovatkach2427gmail.ukrainetravel;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by vovat on 18.04.2017.
 */

public class MyLocationListener implements LocationListener {
    private static Location userLocation=null;
    public static void SetUpLocationListener(Context context) {
        LocationManager locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new MyLocationListener();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast toastError=Toast.makeText(context,"Не вдалося получити дані про ваше місцезнаходження.\n Виключіть GPS.",Toast.LENGTH_SHORT);
            toastError.show();
        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                100,
                locationListener);
        userLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    public static Location getUserLocation() {
        return userLocation;
    }

    @Override
    public  void onLocationChanged(Location location) {
        userLocation=location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
