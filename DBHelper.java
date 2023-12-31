package com.location.recyclerviewsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT primary key, email TEXT, age TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");

    }
    public boolean insertUserdata(String name, String email, String age){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("age",age);
        long results = DB.insert("Userdetails", null,contentValues);
        if(results==-1){
            return false;
        }
        else {
            return true;
        }
    }


    public Cursor getData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select * from userdetails",null);
        return cursor;
    }


    public boolean updateUserdata(String name, String email, String age){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("email",email);
        contentValues.put("age",age);
        Cursor cursor= DB.rawQuery("Select * from userdetails where name=?",new String[] {name});
        if(cursor.getCount()>0) {
            long results = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (results == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
            }
    }
    public boolean deletdata(String name){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor= DB.rawQuery("Select * from userdetails where name=?",new String[] {name});
        if(cursor.getCount()>0) {
            long results = DB.delete("Userdetails", "name=?", new String[]{name});
            if (results == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }
}
