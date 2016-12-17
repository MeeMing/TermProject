package com.example.gimmyeongsu.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by gimmyeongsu on 2016. 12. 1..
 */

public class LoggerListActivity extends AppCompatActivity{


    TextView title_name;
    TextView list;
    EditText logger_list;
    Button btn_delete;
    Intent logger_main;

    LoggerDBManager loggerDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger_list);


        title_name = (TextView)findViewById(R.id.textView);
        list = (TextView)findViewById(R.id.textView_list);
        logger_list = (EditText)findViewById(R.id.edit_loggerNum);
        btn_delete = (Button) findViewById(R.id.button_delete);
        loggerDBManager = new LoggerDBManager(getApplicationContext(), "LOGGER.db", null, 1);

        logger_main = getIntent();
        title_name.setText(logger_main.getStringExtra("TITLE_NAME"));

        list.setText(loggerDBManager.SelectPrintData(logger_main.getStringExtra("TITLE_NAME")));

    }

    public void addEvent(View view) {
        finish();
        Intent addevent = new Intent(getApplicationContext(), LoggerEventActivity.class);
        addevent.putExtra("TITLE_NAME", title_name.getText().toString());
        startActivity(addevent);
    }

    public void deleteEvent(View view) {
        if(!logger_list.getText().toString().equals("")) {
            int number = Integer.parseInt(logger_list.getText().toString());
            loggerDBManager.Delete(number);
            list.setText(loggerDBManager.SelectPrintData(logger_main.getStringExtra("TITLE_NAME")));
            logger_list.setText("");
        }


    }
}
