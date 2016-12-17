package com.example.gimmyeongsu.termproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by TaeWoo on 2016-12-05.
 */

public class LoggerDBManager extends SQLiteOpenHelper {


    public LoggerDBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE LOGGER_LIST( _id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, pic TEXT, coordinate TEXT, time_start INTEGER, time_end INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(String title, String content, String pic, String coordinate, String time_start, int time_end ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("title", title);
        values.put("content", content);
        values.put("pic", pic);
        values.put("coordinate", coordinate);
        values.put("time_start", time_start);
        values.put("time_end", time_end);
        db.insert("LOGGER_LIST",null,values);
        db.close();

    }

    public void update(String table){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(table);
        db.close();
    }

    public String PrintData(){
        SQLiteDatabase db = getWritableDatabase();
        String str="";

        Cursor cursor = db.rawQuery("select * from LOGGER_LIST", null);
        while(cursor.moveToNext()){
            str += cursor.getInt(0)
                    + " : 주제:" + cursor.getString(1)
                    + " , 내용:" + cursor.getString(2) + "\n"
                    + "사진:" + cursor.getString(3) + "\n"
                    + "위치:" + cursor.getString(4) + ", 시간:" + cursor.getString(5) + "\n";
        }
        return str;
    }

    public String SelectPrintData(String title){
        SQLiteDatabase db = getWritableDatabase();
        String str="";

        Cursor cursor = db.rawQuery("select * from LOGGER_LIST", null);
        while(cursor.moveToNext()){
            if(cursor.getString(1).equals(title)){
                str += cursor.getInt(0)
                        + " : 주제:" + cursor.getString(1)
                        + " , 내용:" + cursor.getString(2) + "\n"
                        + "사진:" + cursor.getString(3) + "\n"
                        + "위치:" + cursor.getString(4) + ", 시간:" + cursor.getString(5) + "\n";
            }
        }
        return str;
    }


    public int getTableNumber(){
        int num=0;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from LOGGER_LIST", null);
        while(cursor.moveToNext()){
            num++;
        }
        return num;
    }



    public MarkerOptions[] getMarkers(){
        MarkerOptions[] markers = new MarkerOptions[getTableNumber()];
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from LOGGER_LIST", null);
        int i=0;
        while(cursor.moveToNext()){
            String[] location = cursor.getString(4).split(",");
            LatLng marker = new LatLng(Double.parseDouble(location[0]),Double.parseDouble(location[1]));
            markers[i] = new MarkerOptions().snippet(cursor.getString(1)).position(marker).title(cursor.getString(2));

        }



        return markers;
    }


    public void Delete(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("LOGGER_LIST","_id=?", new String[]{Integer.toString(id)});
    }

}
