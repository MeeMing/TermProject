package com.example.gimmyeongsu.termproject;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by gimmyeongsu on 2016. 11. 25..
 */

public class BackPressCloseHandler{

    private long backKeyPressedTime = 0;
    private Toast toast;
    private Activity activity;

    public BackPressCloseHandler(Activity context){
        this.activity = context;
    }

    public void onBackPressed(){
        if(System.currentTimeMillis() > backKeyPressedTime + 2000){
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if(System.currentTimeMillis() <= backKeyPressedTime + 2000){
            activity .moveTaskToBack(true);
            activity.finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
            toast.cancel();
        }
    }


    public void showGuide(){
        toast = Toast.makeText(activity, "\'뒤로'버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }
}
