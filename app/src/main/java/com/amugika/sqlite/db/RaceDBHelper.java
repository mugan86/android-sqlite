package com.amugika.sqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.amugika.sqlite.Constants;
import com.amugika.sqlite.model.Race;
import com.amugika.sqlite.utils.Actions;

import java.util.ArrayList;


/*****************************************
 * Created by anartzmugika on 3/3/16.
 */
public class RaceDBHelper extends SQLiteOpenHelper {


    public static final int version = 12;
    public static final String name = "RaceDB" ;
    private static CursorFactory factory = null;
    SQLiteDatabase db;

    public RaceDBHelper(Context context) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(this.getClass().toString(), "Create Data Base");

        String text_type = Constants.TEXT_TYPE;

        String sql = "CREATE TABLE " + Constants.C_TABLE_RACES + "(" +
                Constants.C_COLUMN_RACE_CODE + " TEXT PRIMARY KEY, " +
                Constants.C_COLUMN_CIRCLE + text_type +
                Constants.C_COLUMN_CLIMB + text_type +
                Constants.C_COLUMN_DATA + text_type +
                Constants.C_COLUMN_DISTANCE + text_type +
                Constants.C_COLUMN_DISTANCE_FOR_LOCATION + text_type +
                Constants.C_COLUMN_EDITION + text_type +
                Constants.C_COLUMN_FINISH_HERRI_KODEA + text_type +
                Constants.C_COLUMN_FINISH_LATITUDE + text_type +
                Constants.C_COLUMN_FINISH_LONGITUDE + text_type +
                Constants.C_COLUMN_RACE_LAT + text_type +
                Constants.C_COLUMN_RACE_LNG + text_type +
                Constants.C_COLUMN_RACE_NAME + text_type +
                Constants.C_COLUMN_ROUTE_SOURCE + text_type +
                Constants.C_COLUMN_SHORT_URL + text_type +
                Constants.C_COLUMN_START_TIME + text_type +
                Constants.C_COLUMN_TOWN + text_type +
                Constants.C_COLUMN_TRACK_ID + text_type +
                Constants.C_COLUMN_TYPE + text_type +
                Constants.C_COLUMN_VIDEO + text_type +
                Constants.C_COLUMN_WEB  + " TEXT ) ";

        System.out.println(sql);

        db.execSQL(sql);

        Log.i(this.getClass().toString(), "Race table create");


        Log.i(this.getClass().toString(), "Create data base successfully");

        //showAllClimbs();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop books table if already exists
        db.execSQL("DROP TABLE IF EXISTS " + Constants.C_TABLE_RACES);


        this.onCreate(db);
    }

    public void printRegisterCount(RaceDBHelper dbHelper)
    {
        System.out.println("Register Total: " + getRegisterCount(dbHelper));
    }

    public int getRegisterCount(RaceDBHelper dbHelper)
    {
        db = dbHelper.getWritableDatabase();
        final String DATABASE_COMPARE = Constants.COUNT_ROW_FROM + Constants.C_TABLE_RACES;
        int total = (int) DatabaseUtils.longForQuery(db, DATABASE_COMPARE, null);
        db.close();
        return total;
    }


    public ArrayList<Race> getRacesInfo() {
        ArrayList<Race> races = new ArrayList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + Constants.C_TABLE_RACES;


        System.out.println("QUERY: " + query);

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Race race;
        if (cursor.moveToFirst()) {
            do {

                race = new Race();
                race.setRace_code(cursor.getString(0));
                race.setRace_circle_circuit(String.valueOf(cursor.getInt(1)));
                race.setRace_climb(cursor.getString(2));
                race.setRace_data(cursor.getString(3));
                race.setRace_distance(cursor.getString(4));
                race.setRace_distance_for_location(cursor.getString(5));
                race.setRace_edition(cursor.getString(6));
                race.setRace_finish_herri_kodea(cursor.getString(7));
                race.setRace_finish_latitude(cursor.getString(8));
                race.setRace_finish_longitude(cursor.getString(9));
                race.setRace_lat(cursor.getString(10));
                race.setRace_lng(cursor.getString(11));
                race.setRace_name(cursor.getString(12));
                race.setRace_route_source(cursor.getString(13));
                race.setRace_short_url(cursor.getString(14));
                race.setRace_start_time(cursor.getString(15));
                race.setRace_town(cursor.getString(16));
                race.setRace_track_id(cursor.getString(17));
                race.setRace_type(cursor.getString(18));
                race.setRace_video(cursor.getString(19));
                race.setRace_web(cursor.getString(20));
                // Add race to races
                races.add(race);
            } while (cursor.moveToNext());
        }
        db.close();

        return races;
    }

    public void addRaces(ArrayList<Race> races)
    {
        // you can use INSERT only
        String sql = "INSERT OR REPLACE INTO " + Constants.C_TABLE_RACES +
                " (" + Constants.C_COLUMN_RACE_CODE + ", " +
                        Constants.C_COLUMN_CIRCLE + ", "+
                        Constants.C_COLUMN_CLIMB + ", " +
                        Constants.C_COLUMN_DATA + ", " +
                        Constants.C_COLUMN_DISTANCE + ", " +
                        Constants.C_COLUMN_DISTANCE_FOR_LOCATION + ", " +
                        Constants.C_COLUMN_EDITION + ", " +
                        Constants.C_COLUMN_FINISH_HERRI_KODEA + ", " +
                        Constants.C_COLUMN_FINISH_LATITUDE + ", " +
                        Constants.C_COLUMN_FINISH_LONGITUDE + ", " +
                        Constants.C_COLUMN_RACE_LAT + ", " +
                        Constants.C_COLUMN_RACE_LNG + ", " +
                        Constants.C_COLUMN_RACE_NAME + ", " +
                        Constants.C_COLUMN_ROUTE_SOURCE + ", " +
                        Constants.C_COLUMN_SHORT_URL + ", " +
                        Constants.C_COLUMN_START_TIME + ", " +
                        Constants.C_COLUMN_TOWN + ", " +
                        Constants.C_COLUMN_TRACK_ID + ", " +
                        Constants.C_COLUMN_TYPE + ", " +
                        Constants.C_COLUMN_VIDEO + ", " +
                        Constants.C_COLUMN_WEB + " ) " +
                        "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

        SQLiteDatabase db = this.getWritableDatabase();

        /*
         * According to the docs http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html
         * Writers should use beginTransactionNonExclusive() or beginTransactionWithListenerNonExclusive(SQLiteTransactionListener)
         * to start a transaction. Non-exclusive mode allows database file to be in readable by other threads executing queries.
         */
        db.beginTransactionNonExclusive();
        // db.beginTransaction();

        SQLiteStatement stmt = db.compileStatement(sql);

        for (Race race: races)
        {
            stmt.bindString(1,  Actions.checkIfNull(race.getRace_code()));
            stmt.bindString(2, Actions.checkIfNull(race.getRace_circle_circuit()));
            stmt.bindString(3,  Actions.checkIfNull(race.getRace_climb()));
            stmt.bindString(4,  Actions.checkIfNull(race.getRace_data()));
            stmt.bindString(5,  Actions.checkIfNull(race.getRace_distance()));
            stmt.bindString(6,  Actions.checkIfNull(race.getRace_distance_for_location()));
            stmt.bindString(7,  Actions.checkIfNull(race.getRace_edition()));
            stmt.bindString(8,  Actions.checkIfNull(race.getRace_finish_herri_kodea()));
            stmt.bindString(9,  Actions.checkIfNull(race.getRace_finish_latitude()));
            stmt.bindString(10,  Actions.checkIfNull(race.getRace_finish_longitude()));
            stmt.bindString(11,  Actions.checkIfNull(race.getRace_lat()));
            stmt.bindString(12,  Actions.checkIfNull(race.getRace_lng()));
            stmt.bindString(13,  Actions.checkIfNull(race.getRace_name()));
            stmt.bindString(14,  Actions.checkIfNull(race.getRace_route_source()));
            stmt.bindString(15,  Actions.checkIfNull(race.getRace_short_url()));
            stmt.bindString(16,  Actions.checkIfNull(race.getRace_start_time()));
            stmt.bindString(17,  Actions.checkIfNull(race.getRace_town()));
            stmt.bindString(18,  Actions.checkIfNull(race.getRace_track_id()));
            stmt.bindString(19,  Actions.checkIfNull(race.getRace_type()));
            stmt.bindString(20,  Actions.checkIfNull(race.getRace_video()));
            stmt.bindString(21,  Actions.checkIfNull(race.getRace_web()));


            stmt.execute();
            stmt.clearBindings();

        }

        db.setTransactionSuccessful();
        db.endTransaction();

        db.close();

    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }


}
