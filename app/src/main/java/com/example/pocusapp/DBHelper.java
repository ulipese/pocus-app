package com.example.pocusapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private final Context context;
    public static final String DATABASE_NAME = "pocus.db";
    public static final int DATABASE_VERSION = 1;
    public static final String USER_TABLE_NAME = "tbUser";
    public static final String USER_COLUMN_USERNAME = "username";
    public static final String USER_COLUMN_ID = "_id";
    public static final String USER_COLUMN_PASSWORD = "password";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + USER_TABLE_NAME +
                        "( " + USER_COLUMN_ID + " integer primary key autoincrement," +
                        USER_COLUMN_USERNAME + " text , " +
                        USER_COLUMN_PASSWORD + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS tbUser");
        onCreate(db);
    }

    public boolean Register(String user_username, String user_password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_COLUMN_USERNAME, user_username);
        cv.put(USER_COLUMN_PASSWORD, user_password);

        long resultInsert = db.insert(USER_TABLE_NAME, null, cv);

        if (resultInsert == -1){
            return false;
        } else {
            return true;
        }
    }


    public boolean checkUser(String user_username){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE username = ?", new String[] {user_username});
        if(cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPassword(String user_username, String user_password) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE username = ? and password = ?", new String[] {user_username, user_password});
        if(cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor getData(String user_username) {
        SQLiteDatabase myDb = this.getReadableDatabase();
        Cursor cursor = myDb.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE username = ?", new String[]{user_username});
        return cursor;
    }
}
