package com.example.gimmyeongsu.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by gimmyeongsu on 2016. 11. 28..
 */

public class AlarmAddActivity extends AppCompatActivity{

    EditText editText_date;
    EditText editText_hour;
    EditText editText_minute;
    CheckBox checkBox_loop;
    CheckBox checkBox_hardmode;
    Button add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_add);


        final AlarmDBManager alarmDBManager = new AlarmDBManager(getApplicationContext(), "Alarm.db", null, 1);


        editText_date = (EditText)findViewById(R.id.editText1);
        editText_hour = (EditText)findViewById(R.id.editText2);
        editText_minute = (EditText)findViewById(R.id.editText3);
        checkBox_loop = (CheckBox)findViewById(R.id.checkBox1);
        checkBox_hardmode = (CheckBox)findViewById(R.id.checkBox3);


        add = (Button)findViewById(R.id.button_addalarm);
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                String str = "" + editText_date.getText() + "\n"
                        + editText_hour.getText() + ":" + editText_minute.getText() + "\n"
                        + checkBox_loop.isChecked() + "," + checkBox_hardmode.isChecked();

//                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

                int hour_minute = Integer.parseInt(editText_hour.getText().toString()) * 100 + Integer.parseInt(editText_minute.getText().toString());
                int check_loop = checkBox_loop.isChecked()?1:0;
                int check_hardmode = checkBox_hardmode.isChecked()?1:0;


                alarmDBManager.inset("insert into ALARM_LIST values(null, " + editText_date.getText().toString() + ", " + Integer.toString(hour_minute) + ", " + Integer.toString(check_loop) + ", " +Integer.toString(check_hardmode) + ");");

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
}
