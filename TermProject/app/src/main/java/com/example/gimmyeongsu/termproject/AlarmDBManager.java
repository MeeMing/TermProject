package com.example.gimmyeongsu.termproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gimmyeongsu on 2016. 12. 3..
 */

public class AlarmDBManager extends SQLiteOpenHelper {

    AlarmDBManager(Context context, String name, CursorFactory cursorfactory, int version){
        super(context, name, cursorfactory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ALARM_LIST( _id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, time TEXT, check_loop INTEGER, check_hardmode INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(String date, String time, int check_loop, int check_hardmode){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("date", date);
        values.put("time", time);
        values.put("check_loop", check_loop);
        values.put("check_hardmode", check_hardmode);
        db.insert("ALARM_LIST",null,values);
        db.close();
    }

    public void update(String table){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(table);
        db.close();
    }

    public void delete(String table){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(table);
        db.close();
    }

    public String PrintData(){
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from ALARM_LIST", null);
        while(cursor.moveToNext()) {
            str += cursor.getInt(0)
                    + " : Date = "
                    + cursor.getString(1)
                    + ", time = "
                    + cursor.getString(2)
                    + "     " + cursor.getInt(3) + ", " + cursor.getInt(4)
                    + "\n";
        }

        return str;
    }

    public void deleteDB(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from ALARM_LIST");
        db.close();
    }
}
