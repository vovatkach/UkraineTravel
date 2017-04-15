package com.vovatkach2427gmail.ukrainetravel.Model;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by vovat on 03.04.2017.
 */

public class City {
    private int Id;
    private   String Name;
    private double[] Coordinates;
    private int[] imgs;
    private float distanceToUser;
    public City(int id,String name,double[] coordinates,int[] imgs)
    {
        this.Id=id;
        this.Name=name;
        this.Coordinates=coordinates;
        this.imgs=imgs;
        distanceToUser=0;
    }
    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public double[] getCoordinates() {
        return Coordinates;
    }
    public int[] getImgs() {
        return imgs;
    }
    public float getDistanceToUser() {
        return distanceToUser;
    }
    //--------методи для роботи з місцезнаходженням
    public void setDistanceToUser(Location userLocation)
    {
        Location cityLocation=new Location("City Location");
        cityLocation.setLatitude(Coordinates[0]);
        cityLocation.setLongitude(Coordinates[1]);
        distanceToUser=userLocation.distanceTo(cityLocation)/1000;
    }
    public static final Comparator<City> COMPARATOR_BY_DISTANT_TO_USER = new Comparator<City>() {

        public int compare(City city1, City city2) {
            return Float.compare(city1.distanceToUser,city2.distanceToUser);
        }
    };


}
