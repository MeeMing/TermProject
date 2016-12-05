package com.example.gimmyeongsu.termproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TaeWoo on 2016-12-05.
 */

public class LoggerDBManager extends SQLiteOpenHelper {


    public LoggerDBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ALARM_LIST( _id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, pic TEXT, coordinate INTEGER, time_start INTEGER, time_during INTEGER, time_end INTEGER");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(String title, String content, String pic, int coordinate, int time_start, int time_during, int time_end ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("title", title);
        values.put("content", content);
        values.put("pic", pic);
        values.put("coordinate", coordinate);
        values.put("time_start", time_start);
        values.put("time_during", time_during);
        values.put("time_end", time_end);
        db.insert("LOGGER_LIST",null,values);
        db.close();

    }

    public void update(String table){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(table);
        db.close();
    }

}
