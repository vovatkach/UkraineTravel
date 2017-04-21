package com.vovatkach2427gmail.ukrainetravel.Model;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by vovat on 11.04.2017.
 */

public class PlaceMain {
    private int Id;
    private String Name;
    private String Type;
    private int[] imgs;
    private int Top;
    private String Address;
    private Integer distanceToUser;
    private LatLng coordinates;
    public PlaceMain(int id, String name, int[] imgs,int top, String type,String address)
    {
        this.Id=id;
        this.Name=name;
        this.imgs=imgs;
        this.Top=top;
        this.Type=type;
        this.Address=address;
        this.distanceToUser=null;
        this.coordinates=null;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public int[] getImgs() {
        return imgs;
    }

    public int getTop() {
        return Top;
    }

    public String getType() {
        return Type;
    }
    public String getAddress() {
        return Address;
    }
    public void setDistanceToUser(Location userLocation, Context context)
    {
        distanceToUser=null;
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        Location locationPlace=null;
        try {
            address = coder.getFromLocationName("місто Львів "+Address,1);
           if (address.isEmpty()) {
               address = coder.getFromLocationName("місто Львів "+Name,1);
                if(address.isEmpty()){return;}

            }
            Address location=address.get(0);
            locationPlace=new Location("Location Place");
            locationPlace.setLongitude(location.getLongitude());
            locationPlace.setLatitude(location.getLatitude());
          //  Log.d("myLog","C "+Name+" "+Double.toString(locationPlace.getLatitude())+" "+Double.toString(locationPlace.getLongitude()));
        } catch (IOException e) {
           return;
        }
       coordinates=new LatLng(locationPlace.getLatitude(),locationPlace.getLongitude());
       distanceToUser=(int)userLocation.distanceTo(locationPlace);
    }


    public Integer getDistanceToUser() {
        return distanceToUser;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }
}
