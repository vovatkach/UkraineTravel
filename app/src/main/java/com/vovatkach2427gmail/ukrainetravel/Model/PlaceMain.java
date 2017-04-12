package com.vovatkach2427gmail.ukrainetravel.Model;

/**
 * Created by vovat on 11.04.2017.
 */

public class PlaceMain {
    private int Id;
    private String Name;
    private int[] imgs;
    private int Top;
    public PlaceMain(int id, String name, int[] imgs,int top)
    {
        this.Id=id;
        this.Name=name;
        this.imgs=imgs;
        this.Top=top;
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
}
