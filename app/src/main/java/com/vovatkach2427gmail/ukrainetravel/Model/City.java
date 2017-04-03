package com.vovatkach2427gmail.ukrainetravel.Model;

/**
 * Created by vovat on 03.04.2017.
 */

public class City {
    private int Id;
    private   String Name;
    private String Coordinates;
    private int[] imgs;
    public City(int id,String name,String coordinates,int[] imgs)
    {
        this.Id=id;
        this.Name=name;
        this.Coordinates=coordinates;
        this.imgs=imgs;
    }
    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getCoordinates() {
        return Coordinates;
    }

    public int[] getImgs() {
        return imgs;
    }
}
