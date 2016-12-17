package com.example.gimmyeongsu.termproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by gimmyeongsu on 2016. 11. 29..
 */

public class LoggerActivity extends AppCompatActivity {

    Switch[] loggerOnOff;
    Button[] loggerList;
    TextView[] loggerText;
    GpsInfo gpsInfo;

    LoggerDBManager loggerDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger_main);


        loggerDBManager = new LoggerDBManager(getApplicationContext(), "LOGGER.db", null, 1);
        //gpsInfo = new GpsInfo(LoggerActivity.this);


        loggerOnOff = new Switch[5];
        loggerOnOff[0] = (Switch)findViewById(R.id.switch_LoggerOnOff1);
        loggerOnOff[1] = (Switch)findViewById(R.id.switch_LoggerOnOff2);
        loggerOnOff[2] = (Switch)findViewById(R.id.switch_LoggerOnOff3);
        loggerOnOff[3] = (Switch)findViewById(R.id.switch_LoggerOnOff4);
        loggerOnOff[4] = (Switch)findViewById(R.id.switch_LoggerOnOff5);

        loggerList = new Button[5];
        loggerList[0] = (Button)findViewById(R.id.button_LoggerList1);
        loggerList[1] = (Button)findViewById(R.id.button_LoggerList2);
        loggerList[2] = (Button)findViewById(R.id.button_LoggerList3);
        loggerList[3] = (Button)findViewById(R.id.button_LoggerList4);
        loggerList[4] = (Button)findViewById(R.id.button_LoggerList5);

        loggerText = new TextView[5];
        loggerText[0] = (TextView)findViewById(R.id.textView1);
        loggerText[1] = (TextView)findViewById(R.id.textView2);
        loggerText[2] = (TextView)findViewById(R.id.textView3);
        loggerText[3] = (TextView)findViewById(R.id.textView4);
        loggerText[4] = (TextView)findViewById(R.id.textView5);


    }







    public void viewList(View view) {
        int number=0;
        if(view.equals(loggerList[0]))
            number=1;
        else if(view.equals(loggerList[1]))
            number=2;
        else if(view.equals(loggerList[2]))
            number=3;
        else if(view.equals(loggerList[3]))
            number=4;
        else if(view.equals(loggerList[4]))
            number=5;


        AlertDialog.Builder dialog = new AlertDialog.Builder(LoggerActivity.this);
        dialog.setTitle("입력 오류");
        dialog.setMessage("주제가 정해저 있지 않습니다.\n" +
                "주제를 정하고 눌러주세요\n");

        if(loggerText[number-1].getText().toString().equals("")){
            dialog.show();
        }
        else{
            Intent loggerlist = new Intent(getApplicationContext(), LoggerListActivity.class);
            loggerlist.putExtra("TITLE_NAME", loggerText[number-1].getText().toString());
            startActivity(loggerlist);
        }

    }


    public void startLogger(View view) {
        int number = 0;
        if(view.equals(loggerOnOff[0]))
            number=1;
        else if(view.equals(loggerOnOff[1]))
            number=2;
        else if(view.equals(loggerOnOff[2]))
            number=3;
        else if(view.equals(loggerOnOff[3]))
            number=4;
        else if(view.equals(loggerOnOff[4]))
            number=5;


        if(loggerOnOff[number-1].isChecked() == true){
            final EditText edt_title = new EditText(LoggerActivity.this);
            final String[] title = {""};
            final AlertDialog.Builder dialog = new AlertDialog.Builder(LoggerActivity.this);
            dialog.setTitle("주제 입력");
            dialog.setView(edt_title);
            final int finalNumber = number-1;
            dialog.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(edt_title.getText().toString().equals("")){
                        AlertDialog.Builder error_dialog = new AlertDialog.Builder(LoggerActivity.this);
                        error_dialog.setTitle("입력 오류");
                        error_dialog.setMessage("입력값이 없습니다.\n" +
                                "다시 입력해 주세요.\n");
                        error_dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                loggerOnOff[finalNumber].setChecked(false);
                                dialogInterface.cancel();
                            }
                        });
                        error_dialog.setCancelable(false);
                        error_dialog.show();
                    }
                    else {
                        String title[] = edt_title.getText().toString().split("\n");
                        loggerText[finalNumber].setText(title[0]);
                        loggerOnOff[finalNumber].setChecked(true);
                        // 좌표 받고 텍스트를 intent할때 같이 넘겨줘야한다
                        gpsInfo = new GpsInfo(LoggerActivity.this);
                        if(gpsInfo.isGetLocation){
                            double lat = gpsInfo.getLatitude();
                            double lon = gpsInfo.getLongitude();
                            String coordinate = Double.toString(lat)+","+Double.toString(lon);
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.KOREA);
                            String time = df.format(new Date(System.currentTimeMillis()));

                            loggerDBManager.insert(loggerText[finalNumber].getText().toString(),
                                    "", "", coordinate, time, 0);


                        }


                    }
                }
            });
            dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    loggerOnOff[finalNumber].setChecked(false);
                    dialogInterface.cancel();
                }
            });
            dialog.setCancelable(false);
            dialog.show();
            //Toast.makeText(getApplicationContext(), "스위치-ON", Toast.LENGTH_SHORT).show();

        }
        else{
            gpsInfo = new GpsInfo(LoggerActivity.this);
            if(gpsInfo.isGetLocation){
                double lat = gpsInfo.getLatitude();
                double lon = gpsInfo.getLongitude();
                String coordinate = Double.toString(lat)+","+Double.toString(lon);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.KOREA);
                String time = df.format(new Date(System.currentTimeMillis()));

                loggerDBManager.insert(loggerText[number-1].getText().toString(),
                        "", "", coordinate, time, 1);

            }
            loggerText[number-1].setText("");
            //Toast.makeText(getApplicationContext(), "스위치-OFF", Toast.LENGTH_SHORT).show();
            loggerOnOff[number-1].setChecked(false);
        }
    }



}
