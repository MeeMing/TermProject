package com.example.gimmyeongsu.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by gimmyeongsu on 2016. 12. 1..
 */

public class LoggerEventActivity extends AppCompatActivity {


    Button eventSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger_event);

        eventSave = (Button)findViewById(R.id.button_EventSave);

        eventSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
                Intent logger_list = new Intent(getApplicationContext(), LoggerListActivity.class);
                startActivity(logger_list);
            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
        Intent logger_list = new Intent(getApplicationContext(), LoggerListActivity.class);
        startActivity(logger_list);
    }
}
