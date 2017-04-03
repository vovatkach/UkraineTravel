package com.vovatkach2427gmail.ukrainetravel.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vovat on 31.03.2017.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {
    public MyDataBaseHelper(Context context) {
        super(context, Contact.DATABASE_NAME, null, Contact.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+Contact.TABLE_CITY.TABLE_NAME+" ("+
                Contact.TABLE_CITY.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Contact.TABLE_CITY.NAME+" TEXT NOT NULL, "+
                Contact.TABLE_CITY.COORDINATES+" TEXT NOT NULL, "+
                Contact.TABLE_CITY.PICTURES+" TEXT NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
