package com.example.gimmyeongsu.termproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by gimmyeongsu on 2016. 12. 1..
 */

public class GoalAddActivity extends AppCompatActivity {


    EditText title;
    EditText group;
    EditText context;
    TextView date;
    TextView time;
    CheckBox limit;
    EditText goalTime;
    Spinner spinner;
    Button goalAdd;


    SimpleDateFormat df;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_add);

        final GoalDBManager goalDBManager = new GoalDBManager(getApplicationContext(), "GOAL.db", null, 1);


        title = (EditText)findViewById(R.id.editText_Title);
        group = (EditText)findViewById(R.id.editText_Group);
        context = (EditText)findViewById(R.id.editText_Context);
        date = (TextView)findViewById(R.id.editText_Date);
        time = (TextView)findViewById(R.id.editText_Time);
        limit = (CheckBox)findViewById(R.id.checkBox_Limit);
        goalTime = (EditText)findViewById(R.id.editText_GoalTime);
        spinner = (Spinner)findViewById(R.id.spinner);
        goalAdd = (Button)findViewById(R.id.button_GoalSave);



        df = new SimpleDateFormat("yyyy-MM-dd-HH-mm", Locale.KOREA);
        final String today_year = df.format(new Date(System.currentTimeMillis())).split("-")[0];
        final String today_month = twoLength(df.format(new Date(System.currentTimeMillis())).split("-")[1]);
        final String today_day = twoLength(df.format(new Date(System.currentTimeMillis())).split("-")[2]);
        final String today_hour = twoLength(df.format(new Date(System.currentTimeMillis())).split("-")[3]);
        final String today_minute = twoLength(df.format(new Date(System.currentTimeMillis())).split("-")[4]);


        date.setText(today_year+"-"+today_month+"-"+today_day);
        time.setText(today_hour+":"+today_minute);


        final String[] iTem = new String[1];
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                iTem[0] = (String) adapterView.getItemAtPosition(i);
                //Toast.makeText(getApplicationContext(), iTem[0], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(GoalAddActivity.this, date_Listener,
                        Integer.parseInt(today_year), Integer.parseInt(today_month)-1, Integer.parseInt(today_day));
                dialog.show();
            }
        });


        time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(GoalAddActivity.this, time_Listener,
                        Integer.parseInt(today_hour), Integer.parseInt(today_minute), false);
                dialog.show();
            }
        });


        goalAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String input_title = title.getText().toString();
                String input_group = group.getText().toString();
                String input_context = context.getText().toString();
                String input_date = date.getText().toString();
                String input_time = time.getText().toString();


                int check_limit = limit.isChecked()?1:0;
                int limitTime = 0;
                if(!goalTime.getText().toString().equals("")){
                    limitTime = Integer.parseInt(goalTime.getText().toString());
                }
                int check_updown = ((check_limit==1)&&(iTem[0].equals("이하")))?1:0;

                AlertDialog.Builder dialog = new AlertDialog.Builder(GoalAddActivity.this);
                dialog.setTitle("입력 오류");
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                String error_Message = "";
                int error_check = 0;

                if(input_title.equals("")||input_group.equals("")||input_context.equals("")){
                    error_Message+= "입력값을 입력하지 않았습니다.\n" +
                            "입력값을 입력해 주세요\n";
                    error_check=1;
                }
                if((check_limit==1)&&(limitTime==0)){
                    error_Message+="기한없을을 체크하셨습니다.\n" +
                            "시간값을 입력해 주세요.\n";
                    error_check=1;
                }

                if(error_check==1){
                    dialog.setMessage(error_Message);
                    dialog.show();
                }
                else{

                    /*Toast.makeText(getApplicationContext(), title.getText().toString()+"\n" +
                            group.getText().toString()+"\n" +
                            context.getText().toString()+"\n" +
                            date.getText().toString()+"\n" +
                            time.getText().toString()+"\n" +
                            Integer.toString(check_limit)+"\n" +
                            Integer.toString(limitTime)+"\n" +
                            Integer.toString(check_updown), Toast.LENGTH_LONG).show();*/


                    goalDBManager.insert(input_title, input_group, input_context,
                            input_date, input_time,
                            check_limit, limitTime, check_updown);

                    finish();
                    Intent goalActivity = new Intent(getApplicationContext(), GoalActivity.class);
                    startActivity(goalActivity);
                }
            }
        });





    }


    @Override
    public void onBackPressed() {
        finish();
        Intent goalActivity = new Intent(getApplicationContext(), GoalActivity.class);
        startActivity(goalActivity);
    }

    private DatePickerDialog.OnDateSetListener date_Listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            date.setText(Integer.toString(i)+"-"+twoLength(Integer.toString(i1+1))+"-"+twoLength(Integer.toString(i2)));
            Toast.makeText(getApplicationContext(), date.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    };


    private TimePickerDialog.OnTimeSetListener time_Listener = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            time.setText(twoLength(Integer.toString(i))+":"+twoLength(Integer.toString(i1)));
            Toast.makeText(getApplicationContext(), time.getText().toString(), Toast.LENGTH_SHORT).show();
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
