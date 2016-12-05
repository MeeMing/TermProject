package com.example.gimmyeongsu.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by gimmyeongsu on 2016. 12. 1..
 */

public class LoggerListActivity extends AppCompatActivity{


    TextView title_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger_list);


        title_name = (TextView)findViewById(R.id.textView);
        Intent logger_main = getIntent();
        title_name.setText(logger_main.getStringExtra("TITLE_NAME"));
    }

    public void addEvent(View view) {
        finish();
        Intent addevent = new Intent(getApplicationContext(), LoggerEventActivity.class);
        startActivity(addevent);
    }

}
