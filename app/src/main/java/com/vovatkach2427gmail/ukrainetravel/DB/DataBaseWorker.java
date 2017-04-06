package com.vovatkach2427gmail.ukrainetravel.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vovatkach2427gmail.ukrainetravel.Model.City;
import com.vovatkach2427gmail.ukrainetravel.R;

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
