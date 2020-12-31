package com.example.thuchiapp.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table users(Email TEXT primary key, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
    }
    public boolean InsertData(String Email, String Password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", Email);
        contentValues.put("Password", Password);
        long result = db.insert("users", null, contentValues);
        if(result == -1) return false;
        else
            return true;
    }
    public boolean CheckEmail(String Email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where Email = ?", new String[]{Email});
        if (cursor.getCount() > 0){
            return true;
        }
        else return false;
    }
    public boolean CheckEmailPassword(String Email, String Password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where Email = ? and Password = ?", new String[]{Email, Password});
        if (cursor.getCount() > 0){
            return true;
        }
        else return false;
    }
}
