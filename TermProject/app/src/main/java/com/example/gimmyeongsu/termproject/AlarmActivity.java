package com.example.gimmyeongsu.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by gimmyeongsu on 2016. 11. 25..
 */



public class AlarmActivity extends AppCompatActivity{


    Button button_alarmAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alram);


        button_alarmAdd = (Button)findViewById(R.id.button_alarmadd);
        button_alarmAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent alarmadd = new Intent(getApplicationContext(), AlarmAddActivity.class);
                startActivity(alarmadd);
            }
        });
    }
}
