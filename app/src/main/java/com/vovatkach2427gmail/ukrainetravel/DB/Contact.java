package com.vovatkach2427gmail.ukrainetravel.DB;

/**
 * Created by vovat on 31.03.2017.
 */

public class Contact {
    public final static String DATABASE_NAME="mydb.sqlite";
    public final static int DATABASE_VERSION=1;
    public final class TABLE_CITY {
        final public static String TABLE_NAME="city";
        final public static  String ID="id";
        final public static String NAME="name";
        final public static String COORDINATES="coordinates";
        final public static String PICTURES="pictures";
    }
    public final class TABLE_PLACE
    {
        final public static String TABLE_NAME="place";
         final public static String ID="id";
         final public static String NAME="name";
        final public static String TYPE="type";
        final public static String PICTURES="pictures";
        final public static String WEBSITE="website";
        final public static String PHONE="phone";
        final public static String AUDIO="audio";
        final public static String COORDINATES="coordinates";
        final public static String ADDRESS="address";
        final public static String HOURS_OF_WORK="hours_of_work";
        final public static String DESCRIPTION="description";
        final public static String RATING="rating";
        final public static String TOP="TOP";
        final public static String ID_CITY="id_city";
    }
    public final class TABLE_TAXI {
        final public static String TABLE_NAME="taxi";
        final public static String ID="id";
        final public static String NAME="name";
        final public static String NUMBER="number";
        final public static String ID_CITY="id_city";
    }
    public final static class TABLE_COMENT {
        final public static String TABLE_NAME="coment";
        final public static String ID="id";
        final public static String FIRST_NAME="first_name";
        final public static String SURNAME="surname";
        final public static String DATE="date";
        final public static String DESCRIPTION="description";
        final public static String RATING="rating";
        final public static String ID_PLACE="id_place";
    }
}
