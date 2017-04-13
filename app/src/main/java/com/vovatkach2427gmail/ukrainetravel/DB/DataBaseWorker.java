package com.vovatkach2427gmail.ukrainetravel.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vovatkach2427gmail.ukrainetravel.Model.City;
import com.vovatkach2427gmail.ukrainetravel.Model.Place;
import com.vovatkach2427gmail.ukrainetravel.Model.PlaceMain;
import com.vovatkach2427gmail.ukrainetravel.Model.Taxi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vovat on 01.04.2017.
 */

public class DataBaseWorker {
    //-----------------змінні конструктори
    private MyDataBaseHelper myDataBaseHelper;
    public DataBaseWorker(Context context)
    {
        myDataBaseHelper=new MyDataBaseHelper(context);
    }
    public void close(){myDataBaseHelper.close();}
    //------------------відкриті методи для роботи
    public List<City> loadCities()
    {
        ArrayList<City> cities=new ArrayList<>();
        SQLiteDatabase db=myDataBaseHelper.getReadableDatabase();
        Cursor cursor=db.query(Contact.TABLE_CITY.TABLE_NAME,null,null,null,null,null,null);
        if(cursor.moveToFirst())
        {
            int idColIndex=cursor.getColumnIndex(Contact.TABLE_CITY.ID);
            int nameColIndex=cursor.getColumnIndex(Contact.TABLE_CITY.NAME);
            int coordinatesColIndex=cursor.getColumnIndex(Contact.TABLE_CITY.COORDINATES);
            int imgsColIndex=cursor.getColumnIndex(Contact.TABLE_CITY.PICTURES);
            do
            {
                cities.add(new City(cursor.getInt(idColIndex),cursor.getString(nameColIndex),cursor.getString(coordinatesColIndex),jsonToImgs(cursor.getString(imgsColIndex))));

            }while (cursor.moveToNext());
        }
        db.close();
        return cities;
    }
    public City loadCity(int idCity)
    {
        City currectCity;
        SQLiteDatabase db=myDataBaseHelper.getReadableDatabase();
        Cursor cursor=db.query(Contact.TABLE_CITY.TABLE_NAME,null,Contact.TABLE_CITY.ID+" = ?",new String[]{Integer.toString(idCity)},null,null,null);
        if(cursor.moveToFirst())
        {
            int idColIndex=cursor.getColumnIndex(Contact.TABLE_CITY.ID);
            int nameColIndex=cursor.getColumnIndex(Contact.TABLE_CITY.NAME);
            int coordinatesColIndex=cursor.getColumnIndex(Contact.TABLE_CITY.COORDINATES);
            int imgsColIndex=cursor.getColumnIndex(Contact.TABLE_CITY.PICTURES);
            currectCity=new City(cursor.getInt(idColIndex),cursor.getString(nameColIndex),cursor.getString(coordinatesColIndex),jsonToImgs(cursor.getString(imgsColIndex)));
        }else currectCity=new City(1,"1","1",new int[]{1,1,1});
        return currectCity;
    }
    public List<Taxi> loadTaxis(int idCity)
    {
        ArrayList<Taxi> taxis=new ArrayList<>();
        SQLiteDatabase db=myDataBaseHelper.getReadableDatabase();
        Cursor cursor=db.query(Contact.TABLE_TAXI.TABLE_NAME,null,Contact.TABLE_TAXI.ID_CITY+" = ?",new String[]{Integer.toString(idCity)},null,null,null);
        if(cursor.moveToFirst())
        {
           int idColIndex=cursor.getColumnIndex(Contact.TABLE_TAXI.ID);
           int nameColIndex=cursor.getColumnIndex(Contact.TABLE_TAXI.NAME);
           int numbersColIndex=cursor.getColumnIndex(Contact.TABLE_TAXI.NUMBER);
           int idCityColIndex=cursor.getColumnIndex(Contact.TABLE_TAXI.ID_CITY);
            do {
                taxis.add(new Taxi(cursor.getInt(idColIndex),cursor.getString(nameColIndex),jsonToPhoneNumbers(cursor.getString(numbersColIndex)),cursor.getInt(idCityColIndex)));
            }while (cursor.moveToNext());
        }
        db.close();
        return taxis;
    }
    public List<PlaceMain> loadPlaces(int idCity, String type)
    {
        ArrayList<PlaceMain> places=new ArrayList<>();
        SQLiteDatabase db=myDataBaseHelper.getReadableDatabase();
        Cursor cursor;
        if (type=="ТОП"){
        cursor=db.query(Contact.TABLE_PLACE.TABLE_NAME,new String[]{Contact.TABLE_PLACE.ID,Contact.TABLE_PLACE.NAME,Contact.TABLE_PLACE.PICTURES,Contact.TABLE_PLACE.TOP},Contact.TABLE_TAXI.ID_CITY+" = ? AND "+Contact.TABLE_PLACE.TOP+" = ?",new String[]{Integer.toString(idCity),Integer.toString(1)},null,null,null);
        }else
            {
                cursor=db.query(Contact.TABLE_PLACE.TABLE_NAME,new String[]{Contact.TABLE_PLACE.ID,Contact.TABLE_PLACE.NAME,Contact.TABLE_PLACE.PICTURES,Contact.TABLE_PLACE.TOP},Contact.TABLE_TAXI.ID_CITY+" = ? AND "+Contact.TABLE_PLACE.TYPE+" = ?",new String[]{Integer.toString(idCity),type},null,null,null);

            }
        if (cursor.moveToFirst()) {
            int idColIndex = cursor.getColumnIndex(Contact.TABLE_PLACE.ID);
            int nameColIndex = cursor.getColumnIndex(Contact.TABLE_PLACE.NAME);
            int imgColIndex = cursor.getColumnIndex(Contact.TABLE_PLACE.PICTURES);
            int topColIndex=cursor.getColumnIndex(Contact.TABLE_PLACE.TOP);
            do {
                places.add(new PlaceMain(cursor.getInt(idColIndex),cursor.getString(nameColIndex),jsonToImgs(cursor.getString(imgColIndex)),cursor.getInt(topColIndex)));
            } while (cursor.moveToNext());
        }
        db.close();
        return places;
        }
    public Place loadPlace(int id_place)
    {
        Place currectPlace;
        SQLiteDatabase db=myDataBaseHelper.getReadableDatabase();
        Cursor cursor=db.query(Contact.TABLE_PLACE.TABLE_NAME,null,Contact.TABLE_PLACE.ID+" =?",new String[]{Integer.toString(id_place)},null,null,null);
        if(cursor.moveToFirst())
        {
            int idColIndex=cursor.getColumnIndex(Contact.TABLE_PLACE.ID);
            int NameColIndex=cursor.getColumnIndex(Contact.TABLE_PLACE.NAME);
            int TypeColIndex=cursor.getColumnIndex(Contact.TABLE_PLACE.TYPE);
            int WebsiteColIndex=cursor.getColumnIndex(Contact.TABLE_PLACE.WEBSITE);
            int PicturesColIndex=cursor.getColumnIndex(Contact.TABLE_PLACE.PICTURES);
            int TopColIndex=cursor.getColumnIndex(Contact.TABLE_PLACE.TOP);
            int AddressColIndex=cursor.getColumnIndex(Contact.TABLE_PLACE.ADDRESS);
            int AudioColIndex=cursor.getColumnIndex(Contact.TABLE_PLACE.AUDIO);
            int CoordinatesColIndex=cursor.getColumnIndex(Contact.TABLE_PLACE.COORDINATES);
            int DescriptionColIndex=cursor.getColumnIndex(Contact.TABLE_PLACE.DESCRIPTION);
            int HoursOfWorkColIndex=cursor.getColumnIndex(Contact.TABLE_PLACE.HOURS_OF_WORK);
            int CityIdColIndex=cursor.getColumnIndex(Contact.TABLE_PLACE.ID_CITY);
            int PhoneColIndex=cursor.getColumnIndex(Contact.TABLE_PLACE.PHONE);
            int RatingColIndex=cursor.getColumnIndex(Contact.TABLE_PLACE.RATING);
            currectPlace=new Place(cursor.getInt(idColIndex),cursor.getString(NameColIndex),cursor.getString(TypeColIndex),jsonToImgs(cursor.getString(PicturesColIndex)),cursor.getString(WebsiteColIndex),cursor.getString(PhoneColIndex),cursor.getString(AudioColIndex),cursor.getString(CoordinatesColIndex),cursor.getString(AddressColIndex),cursor.getString(HoursOfWorkColIndex),cursor.getString(DescriptionColIndex),cursor.getInt(RatingColIndex),cursor.getInt(CityIdColIndex),cursor.getInt(TopColIndex));
        }else currectPlace=new Place(1,"place","", new int[]{}, "", "", "", "", "", "", "",1,1,1);
        return currectPlace;
    }
    //------------------методи для налаштування

    //------------------допоміжні методи
    //-----------------робота з картинками
    public static String imgsToJson(int[] imgs)
    {
        ImgsContainer container=new ImgsContainer(imgs);
        GsonBuilder builder=new GsonBuilder();
        Gson gson=builder.create();
        return gson.toJson(container);
    }
    private int[] jsonToImgs(String json)
    {
        GsonBuilder builder=new GsonBuilder();
        Gson gson=builder.create();
        ImgsContainer container=gson.fromJson(json,ImgsContainer.class);
        return container.getImgs();
    }
    //--------------------робота з тел. номерами
    public static String PhoneNumbersToJson(String[] numbers)
    {
        PhoneNumberContainer container=new PhoneNumberContainer(numbers);
        GsonBuilder builder=new GsonBuilder();
        Gson gson=builder.create();
        return gson.toJson(container);
    }
    private String[] jsonToPhoneNumbers(String json)
    {
        GsonBuilder builder=new GsonBuilder();
        Gson gson=builder.create();
        PhoneNumberContainer container=gson.fromJson(json,PhoneNumberContainer.class);
        return container.getNumbers();
    }

}
class ImgsContainer
{
    private int[] imgs;
    ImgsContainer(int[] arrayImg)
    {
        imgs=arrayImg;
    }

    public int[] getImgs() {
        return imgs;
    }
}
class PhoneNumberContainer
{
    private String[] numbers;
    PhoneNumberContainer(String[] arrayNumbers)
    {
        numbers=arrayNumbers;
    }

    public String[] getNumbers() {
        return numbers;
    }
}
