package com.example.cafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.widget.TabHost;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_USER ="registeruser";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="username";
    public static final String COL_3 ="password";

    public static final String TABLE_HOMEMADE = "registerhomemade";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create tables
        db.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
        db.execSQL("CREATE TABLE registerhomemade (ID INTEGER PRIMARY KEY AUTOINCREMENT, coffeebean TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop existing tables
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USER );
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_HOMEMADE);
        onCreate(db);
    }

    // Insert methods
    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user);
        contentValues.put("password", password);
        long res = db.insert("registeruser", null, contentValues);
        db.close();
        return res;
    }


    // Listar HomemadeCoffee
//    public List<HomemadeExp> getAll(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        List<HomemadeExp> homemadeExp = new ArrayList<>();
//        Cursor cursor = db.query("registerhomemade", new String[]{"id","coffeebean"},
//                null, null, null, null, null);
//        while(cursor.moveToNext()){
//            HomemadeExp homemade = new HomemadeExp();
//            homemade.setId(cursor.getInt(0));
//            homemade.setCoffeebean(cursor.getString(1));
//
//            homemadeExp.add(homemade);
//        }
//        return homemadeExp;
//    }


    public boolean checkUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return true;
        else
            return false;
    }
}
