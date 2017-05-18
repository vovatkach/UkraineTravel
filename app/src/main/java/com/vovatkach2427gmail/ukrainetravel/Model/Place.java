package com.vovatkach2427gmail.ukrainetravel.Model;

/**
 * Created by vovat on 13.04.2017.
 */

public class Place {
    private int Id;
    private String Name;
    private String Type;
    private int[] imgs;
    private String Website;
    private String Phone;
    private String Audio;
    private String Addrres;
    private String HoursOFWork;
    private String Description;
    private int Rating;
    private int Id_City;
    private int IsTOP;
    public Place(int id, String name, String type, int[] imgs, String website, String phone, String audio, String addrres, String hoursOFWork, String description, int rating, int id_city, int isTOP)
    {
        Id = id;
        Name = name;
        Type = type;
        this.imgs = imgs;
        Website = website;
        Phone = phone;
        Audio = audio;

        Addrres = addrres;
        HoursOFWork = hoursOFWork;
        Description = description;
        Rating = rating;
        Id_City = id_city;
        IsTOP = isTOP;
    }


    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getType() {
        return Type;
    }

    public int[] getImgs() {
        return imgs;
    }

    public String getWebsite() {
        return Website;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAudio() {
        return Audio;
    }


    public String getAddrres() {
        return Addrres;
    }

    public String getHoursOFWork() {
        return HoursOFWork;
    }

    public String getDescription() {
        return Description;
    }

    public int getRating() {
        return Rating;
    }

    public int getId_City() {
        return Id_City;
    }

    public int getIsTOP() {
        return IsTOP;
    }
}
