package com.example.gimmyeongsu.termproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gimmyeongsu on 2016. 12. 5..
 */

public class GoalDBManager extends SQLiteOpenHelper {

    GoalDBManager(Context context, String name, CursorFactory cursorfactory, int version) {
        super(context, name, cursorfactory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE GOAL_LIST( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, gro TEXT, content TEXT, " +
                "date TEXT, time TEXT, " +
                "check_term INTEGER, term_time INTEGER, term_updown INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void insert(String title, String group, String content, String date, String time, int check_term, int term_time, int term_updown){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("title", title);
        values.put("gro", group);
        values.put("content", content);
        values.put("date", date);
        values.put("time", time);
        values.put("check_term", check_term);
        values.put("term_time", term_time);
        values.put("term_updown", term_updown);
        db.insert("GOAL_LIST",null,values);
        db.close();
    }


    public String PrintData(){
        SQLiteDatabase db = getWritableDatabase();
        String str="";

        Cursor cursor = db.rawQuery("select * from GOAL_LIST", null);
        while(cursor.moveToNext()){
            str += cursor.getInt(0)
                    + " : 제목:" + cursor.getString(1)
                    + " , 주제:" + cursor.getString(2) + "\n"
                    + "내용:" + cursor.getString(3) + "\n"
                    + "날짜:" + cursor.getString(4) + ", 시간:" + cursor.getString(5) + "\n";
            if(cursor.getInt(6)==0){
                str += "기한 : " + cursor.getInt(7);
                if(cursor.getInt(8)==0){
                    str += " 이하\n";
                }
                else{
                    str += " 이상\n";
                }
            }
        }



        return str;
    }
}
