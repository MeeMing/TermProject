package com.example.gimmyeongsu.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by gimmyeongsu on 2016. 11. 25..
 */



public class AlarmActivity extends AppCompatActivity{

    Button button_deleteDB;
    Button button_alarmAdd;
    TextView textView_sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alram);


        final AlarmDBManager alarmDBManager = new AlarmDBManager(getApplicationContext(), "Alarm.db", null, 1);


        textView_sample = (TextView)findViewById(R.id.textView_sample);
        textView_sample.setText(alarmDBManager.PrintData());


        button_alarmAdd = (Button)findViewById(R.id.button_alarmadd);
        button_alarmAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
                Intent alarmadd = new Intent(getApplicationContext(), AlarmAddActivity.class);
                startActivity(alarmadd);
            }
        });


        button_deleteDB = (Button)findViewById(R.id.button_deleteDB);
        button_deleteDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                alarmDBManager.deleteDB();
                textView_sample.setText(alarmDBManager.PrintData());
            }
        });
    }
}
