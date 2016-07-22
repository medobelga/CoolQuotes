package com.justinmutsito.coolquotes.coolquotes.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by justin on 7/5/16.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favourites.db";
    private static final int DATABASE_VERSION = 1;


    public static final String TABLE_NAME = "FAVOURITES";
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_FAVOURITE = "FAVOURITE";

    private static final String DB_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FAVOURITE + " TEXT)";


    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        onCreate(db);
    }


    public boolean addFavourite(String favourite) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FAVOURITE, favourite);

        long t = db.insert(TABLE_NAME, null, values);

        if (t == -1) return false;

        else return true;


    }


    public Cursor getFavourites() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }


    public void delete(String text) {
        SQLiteDatabase db = this.getWritableDatabase();

        int deleted = db.delete(TABLE_NAME, COLUMN_FAVOURITE + " = ?", new String[]{text});


    }

}
