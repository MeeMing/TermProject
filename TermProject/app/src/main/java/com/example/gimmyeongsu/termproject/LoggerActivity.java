package com.example.gimmyeongsu.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by gimmyeongsu on 2016. 11. 29..
 */

public class LoggerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger_main);
    }

    public void viewList(View view) {
        Intent viewlist = new Intent(getApplicationContext(), LoggerListActivity.class);
        startActivity(viewlist);
    }

    public void addEvent(View view) {
        Intent addevent = new Intent(getApplicationContext(), LoggerEventActivity.class);
        startActivity(addevent);
    }
}
