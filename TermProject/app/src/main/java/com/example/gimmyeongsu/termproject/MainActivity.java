package com.example.gimmyeongsu.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;

    Button button_alarm;
    Button button_logger;
    Button button_goal;
    Button button_analysis_time;
    Button button_alarm_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        backPressCloseHandler = new BackPressCloseHandler(this);


        // 로거 버튼
        button_logger = (Button)findViewById(R.id.button_Logger);
        button_logger.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent logger = new Intent(getApplicationContext(), LoggerActivity.class);
                startActivity(logger);
            }
        });


        // 알람 버튼
        button_alarm = (Button)findViewById(R.id.button_Alarm);
        button_alarm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent alarm = new Intent(getApplicationContext(), AlarmActivity.class);
                startActivity(alarm);
            }
        });


        // 목표 버튼
        button_goal = (Button)findViewById(R.id.button_Goal);
        button_goal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent goal = new Intent(getApplicationContext(), GoalActivity.class);
                startActivity(goal);
            }
        });


        // 분석 버튼
        button_analysis_time = (Button)findViewById(R.id.button_Analysis);
        button_analysis_time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent analysistime = new Intent(getApplicationContext(), AnalysisTimeActivity.class);
                startActivity(analysistime);
            }
        });


        // 알람 온오프 버튼
        button_alarm_off = (Button)findViewById(R.id.button_AlarmOff);
        button_alarm_off.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


            }
        });

    }


    public void onBackPressed(){
        backPressCloseHandler.onBackPressed();
    }

}
