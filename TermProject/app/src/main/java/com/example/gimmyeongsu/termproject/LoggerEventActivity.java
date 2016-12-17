package com.example.gimmyeongsu.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by gimmyeongsu on 2016. 12. 1..
 */

public class LoggerEventActivity extends AppCompatActivity {

    TextView title_name;
    Button cameraTake;
    Button cameraDelete;
    Button eventSave;
    EditText event_content;

    GpsInfo gpsInfo;
    LoggerDBManager loggerDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger_event);

        title_name = (TextView)findViewById(R.id.textView_Title);
        cameraTake = (Button)findViewById(R.id.button_PictureTake);
        cameraDelete = (Button)findViewById(R.id.button_PictureDelete);
        eventSave = (Button)findViewById(R.id.button_EventSave);
        event_content = (EditText)findViewById(R.id.editText_Event);

        gpsInfo = new GpsInfo(LoggerEventActivity.this);
        loggerDBManager = new LoggerDBManager(getApplicationContext(), "LOGGER.db", null, 1);

        final Intent title = getIntent();
        title_name.setText(title.getStringExtra("TITLE_NAME"));


        eventSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                if(gpsInfo.isGetLocation){
                    finish();
                    Intent logger_list = new Intent(getApplicationContext(), LoggerListActivity.class);
                    logger_list.putExtra("TITLE_NAME", title_name.getText().toString());
                    double lat = gpsInfo.getLatitude();
                    double lon = gpsInfo.getLongitude();
                    String coordinate = Double.toString(lat)+","+Double.toString(lon);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.KOREA);
                    String time = df.format(new Date(System.currentTimeMillis()));
                    loggerDBManager.insert(title.getStringExtra("TITLE_NAME"), event_content.getText().toString(), "", coordinate, time, 0);
                    startActivity(logger_list);
                }




            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
        Intent logger_list = new Intent(getApplicationContext(), LoggerListActivity.class);
        logger_list.putExtra("TITLE_NAME",title_name.getText().toString());
        startActivity(logger_list);
    }
}
