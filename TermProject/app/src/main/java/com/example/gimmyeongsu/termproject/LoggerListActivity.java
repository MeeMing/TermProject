package com.example.gimmyeongsu.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by gimmyeongsu on 2016. 12. 1..
 */

public class LoggerListActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger_list);
    }

    public void addEvent(View view) {
        Intent addevent = new Intent(getApplicationContext(), LoggerEventActivity.class);
        startActivity(addevent);
    }

}
