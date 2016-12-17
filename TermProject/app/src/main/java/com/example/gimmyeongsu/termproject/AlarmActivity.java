package com.example.gimmyeongsu.termproject;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.GregorianCalendar;

/**
 * Created by gimmyeongsu on 2016. 11. 25..
 */



public class AlarmActivity extends AppCompatActivity implements OnDateChangedListener, TimePicker.OnTimeChangedListener {

    Button button_alarmAdd;
    TextView textView_sample;

    AlarmManager alarmManager;
    GregorianCalendar gregorianCalendar;
    DatePicker datePicker;
    TimePicker timePicker;
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alram);


        final AlarmDBManager alarmDBManager = new AlarmDBManager(getApplicationContext(), "ALARM.db", null, 1);

        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        gregorianCalendar = new GregorianCalendar();

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



    }

    public void setAlarm(){
        alarmManager.set(AlarmManager.RTC_WAKEUP, gregorianCalendar.getTimeInMillis(), pendingIntent());
    }

    public void resetAlarm(){
        alarmManager.cancel(pendingIntent());
    }


    PendingIntent pendingIntent(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        return pi;

    }

    @Override
    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {

    }

    public void onTimeChanged(TimePicker view, int i, int i1){

    }
}
