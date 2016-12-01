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

public class GoalActivity extends AppCompatActivity {

    Button add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        add = (Button)findViewById(R.id.button1);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent goaladd = new Intent(getApplicationContext(), GoalAddActivity.class);
                startActivity(goaladd);
            }
        });
    }
}
