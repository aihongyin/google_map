package edu.sjsu.android.hw4;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocationsDB extends SQLiteOpenHelper{

    //Database name
    private static String myDBNAME = "locationmarkersqlite";

    // table name
    private static final String DATABASE_TABLE = "locations";

    //Version of the database
    private static int VERSION = 1;

    //Field 1:primary key
    public static final String FIELD_ROW_ID = "_id";

    //Field 2: latitude
    public static final String FIELD_LAT = "lat";

    //Field 3:  longitude
    public static final String FIELD_LNG = "lng";

    //Field 4: zoom level of map
    public static final String FIELD_ZOOM = "zom";

    //An instance variable for SQLiteDatabase
    private SQLiteDatabase myDB;

    //This is Constructor
    public LocationsDB(Context context) {
        super(context, myDBNAME, null, VERSION);
        this.myDB = getWritableDatabase();
    }

    //This is the callback method that is invoked when the method
    // getReadableDatabase() /
    // getWritableDatabase() is called
    //provided the database does not exists
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =     "create table " + DATABASE_TABLE + " ( " +
                FIELD_ROW_ID + " integer primary key autoincrement , " +
                FIELD_LNG + " double , " +
                FIELD_LAT + " double , " +
                FIELD_ZOOM + " text " +
                " ) ";

        db.execSQL(sql);
    }

    //Inserts a new location to the table locations
    public long insert(ContentValues contentValues){
        long rowID = myDB.insert(DATABASE_TABLE, null, contentValues);
        return rowID;
    }

    //Deletes all locations from the table
    public int del(){
        int cnt = myDB.delete(DATABASE_TABLE, null , null);
        return cnt;
    }

    //Returns all the locations from the table
    public Cursor getAllLocations(){
        return myDB.query(DATABASE_TABLE, new String[] { FIELD_ROW_ID,  FIELD_LAT , FIELD_LNG, FIELD_ZOOM } , null, null, null, null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}