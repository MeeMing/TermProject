package com.example.gimmyeongsu.termproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by gimmyeongsu on 2016. 11. 28..
 */

public class AlarmAddActivity extends AppCompatActivity{

    TextView editText_date;
    TextView editText_time;
    CheckBox checkBox_loop;
    CheckBox checkBox_hardmode;
    Button add;

    SimpleDateFormat df;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_add);


        final AlarmDBManager alarmDBManager = new AlarmDBManager(getApplicationContext(), "ALARM.db", null, 1);

        df = new SimpleDateFormat("yyyy-MM-dd-HH-mm", Locale.KOREA);
        final String today_year = df.format(new Date(System.currentTimeMillis())).split("-")[0];
        final String today_month = twoLength(df.format(new Date(System.currentTimeMillis())).split("-")[1]);
        final String today_day = twoLength(df.format(new Date(System.currentTimeMillis())).split("-")[2]);
        final String today_hour = twoLength(df.format(new Date(System.currentTimeMillis())).split("-")[3]);
        final String today_minute = twoLength(df.format(new Date(System.currentTimeMillis())).split("-")[4]);


        editText_date = (TextView)findViewById(R.id.editText1);
        editText_time = (TextView)findViewById(R.id.editText2);
        checkBox_loop = (CheckBox)findViewById(R.id.checkBox1);
        checkBox_hardmode = (CheckBox)findViewById(R.id.checkBox3);

        editText_date.setText(today_year+"-"+today_month+"-"+today_day);
        editText_time.setText(today_hour+":"+today_minute);


        editText_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AlarmAddActivity.this, date_Listener,
                        Integer.parseInt(today_year), Integer.parseInt(today_month)-1, Integer.parseInt(today_day));
                dialog.show();
            }
        });

        editText_time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(AlarmAddActivity.this, time_Listener,
                        Integer.parseInt(today_hour), Integer.parseInt(today_minute), false);
                dialog.show();
            }
        });


        add = (Button)findViewById(R.id.button_addalarm);
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int check_loop = checkBox_loop.isChecked()?1:0;
                int check_hardmode = checkBox_hardmode.isChecked()?1:0;
                alarmDBManager.insert(editText_date.getText().toString(), editText_time.getText().toString(), check_loop, check_hardmode);
                finish();
                Intent alarm = new Intent(getApplicationContext(), AlarmActivity.class);
                startActivity(alarm);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent alarm = new Intent(getApplicationContext(), AlarmActivity.class);
        startActivity(alarm);
    }


    private DatePickerDialog.OnDateSetListener date_Listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            editText_date.setText(Integer.toString(i)+"-"+twoLength(Integer.toString(i1+1))+"-"+twoLength(Integer.toString(i2)));
            Toast.makeText(getApplicationContext(), editText_date.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    };

    private TimePickerDialog.OnTimeSetListener time_Listener = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            editText_time.setText(twoLength(Integer.toString(i))+":"+twoLength(Integer.toString(i1)));
            Toast.makeText(getApplicationContext(), editText_time.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    };


    private String twoLength(String str){
        String twolength=str;
        if(twolength.length()==1){
            twolength="0"+twolength;
        }
        return twolength;
    };
}
