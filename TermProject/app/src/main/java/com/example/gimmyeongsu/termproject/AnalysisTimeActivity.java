package com.example.gimmyeongsu.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by gimmyeongsu on 2016. 11. 29..
 */

public class AnalysisTimeActivity extends AppCompatActivity {


    Button button_map;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_time);

        button_map=(Button)findViewById(R.id.button_Map);
        button_map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
                Intent map = new Intent(getApplicationContext(), AnalysisMapActivity.class);
                startActivity(map);
            }
        });
    }

}
