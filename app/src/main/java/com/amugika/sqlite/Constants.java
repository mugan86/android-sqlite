package com.amugika.sqlite;

/**********************************
 * Created by anartzmugika on 3/3/16.
 */
public class Constants {

    //Bundle key strings

    public static final String MOUNTAIN_ITEM_URL = "mountain_item_url";

    //Directory constants

    public static final String CONTENT_PROVIDER = "content://com.google.android.apps.photos.contentprovider";
    public static final String CONTENT_MEDIA = "content://media/";

    //PERMISSIONS CODES
    public static final int PERMISSION_GRANTED = 198;

    //LOGIN DATA
    public static final String USER_SESSION_DATA = "USER_SESSION_DATA";
    public static final String USER_CHECK_DATA = "USER_CHECK_DATA";
    public static final String USER_DATA_SINCRO_DATE = "USER_DATA_SINCRO_DATE";

    public static final String CURRENT_USER_LOCATION_LAT = "CURRENT_USER_LOCATION_LAT";
    public static final String CURRENT_USER_LOCATION_LON = "CURRENT_USER_LOCATION_LON";


    //SQLite Database local constants

    /**
     * Table name constant
     */
    public static final String C_TABLE_MOUNTAIN = "mountain" ;
    public static final String C_TABLE_CLIMBS = "climbs" ;
    /**
     * Table columns constants (MOUNTAINS)
     */
    public static final String C_COLUMN_ID   = "_id"; //Xehetasunak Mendiarenak
    public static final String C_COLUMN_ALTITUDE = "altitude";
    public static final String C_COLUMN_CITY   = "city";
    public static final String C_COLUMN_CODE = "code"; //Excel ID
    public static final String C_COLUMN_COMMENT = "comment";
    public static final String C_COLUMN_EHUN_MENDI = "ehun_mendi";
    public static final String C_COLUMN_EXTRA_PHOTO = "extraphoto";
    public static final String C_COLUMN_FOR_CHILDREN = "for_children";
    public static final String C_COLUMN_IMPORTANCE = "importance";
    public static final String C_COLUMN_INACTIVE = "inactive";
    public static final String C_COLUMN_ITEMURL = "itemurl";
    public static final String C_COLUMN_LAT = "lat";
    public static final String C_COLUMN_LON = "lon";
    public static final String C_COLUMN_MAIN_PHOTO = "mainphoto";
    public static final String C_COLUMN_MAIN_PHOTO_ID = "mainphoto_id";
    public static final String C_COLUMN_MENDIKAT_PROVINCE = "mendikat_province";
    public static final String C_COLUMN_MOUNTAIN_RANGE = "mountain_range";
    public static final String C_COLUMN_NAME = "name";
    public static final String C_COLUMN_PROVINCE = "province";

    public static final String [] COLUMNS_MOUNTAINS = new String[]{
            Constants.C_COLUMN_ID,
            Constants.C_COLUMN_ALTITUDE,
            Constants.C_COLUMN_CITY,
            Constants.C_COLUMN_CODE,
            Constants.C_COLUMN_COMMENT,
            Constants.C_COLUMN_EHUN_MENDI,
            Constants.C_COLUMN_EXTRA_PHOTO,
            Constants.C_COLUMN_FOR_CHILDREN,
            Constants.C_COLUMN_IMPORTANCE,
            Constants.C_COLUMN_INACTIVE,
            Constants.C_COLUMN_ITEMURL,
            Constants.C_COLUMN_LAT,
            Constants.C_COLUMN_LON,
            Constants.C_COLUMN_MAIN_PHOTO,
            Constants.C_COLUMN_MAIN_PHOTO_ID,
            Constants.C_COLUMN_MENDIKAT_PROVINCE,
            Constants.C_COLUMN_MOUNTAIN_RANGE,
            Constants.C_COLUMN_NAME,
            Constants.C_COLUMN_PROVINCE
    } ;

    /**
     * Table name constant
     */
    public static final String C_TABLE_RACES = "races" ;
    public static final String C_TABLE_USERS = "users" ;
    /**
     * Table columns constants (MOUNTAINS)
     */
    public static final String C_COLUMN_RACE_CODE   = "_id"; //Xehetasunak Mendiarenak
    public static final String C_COLUMN_CIRCLE = "circle_circuit";
    public static final String C_COLUMN_CLIMB   = "climb";
    public static final String C_COLUMN_DATA = "data";
    public static final String C_COLUMN_DISTANCE = "distance";
    public static final String C_COLUMN_DISTANCE_FOR_LOCATION = "distance_for_location";
    public static final String C_COLUMN_EDITION = "edition";
    public static final String C_COLUMN_FINISH_HERRI_KODEA = "finish_herri_kodea";
    public static final String C_COLUMN_FINISH_LATITUDE = "finish_lat";
    public static final String C_COLUMN_FINISH_LONGITUDE = "finish_lng";
    public static final String C_COLUMN_RACE_LAT = "lat";
    public static final String C_COLUMN_RACE_LNG = "lng";
    public static final String C_COLUMN_RACE_NAME = "name";
    public static final String C_COLUMN_ROUTE_SOURCE = "route_source";
    public static final String C_COLUMN_SHORT_URL = "short_url";
    public static final String C_COLUMN_START_TIME = "start_time";
    public static final String C_COLUMN_TOWN = "town";
    public static final String C_COLUMN_TRACK_ID = "track_id";
    public static final String C_COLUMN_TYPE = "type";
    public static final String C_COLUMN_VIDEO = "video";
    public static final String C_COLUMN_WEB = "web";

    //SQL

    public static final String COUNT_ROW_FROM = "select count(*) from ";
    public static final String TEXT_TYPE = " TEXT, ";
}