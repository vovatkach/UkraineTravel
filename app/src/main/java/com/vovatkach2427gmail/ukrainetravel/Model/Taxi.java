package com.vovatkach2427gmail.ukrainetravel.Model;

/**
 * Created by vovat on 06.04.2017.
 */

public class Taxi {
    private int Id;
    private String Name;
    private String[] Numbers;
    private int Id_city;
    public Taxi(int id, String name, String[] numbers, int id_city)
    {
        this.Id=id;
        this.Name=name;
        this.Numbers=numbers;
        this.Id_city=id_city;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String[] getNumbers() {
        return Numbers;
    }

    public int getId_city() {
        return Id_city;
    }
}
