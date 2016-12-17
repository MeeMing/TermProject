package com.example.gimmyeongsu.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by gimmyeongsu on 2016. 11. 29..
 */

public class AnalysisMapActivity extends AppCompatActivity {

    Button button_time;
    GoogleMap gmap;

    LoggerDBManager loggerDBManager;

    LatLng location = new LatLng(37.559603, 126.982203);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_map);

        loggerDBManager = new LoggerDBManager(getApplicationContext(), ":LOGGER.db", null, 1);

        gmap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapView)).getMap();
        //gmap.addMarker(new MarkerOptions().position(location).title("good").snippet("hello"));
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));

        int tableNumber = loggerDBManager.getTableNumber();
        MarkerOptions[] markerOptions = loggerDBManager.getMarkers();
        for (int i=0; i<tableNumber; i++){
            gmap.addMarker(markerOptions[i]);
        }




        button_time = (Button)findViewById(R.id.button_Time);
        button_time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
                Intent time = new Intent(getApplicationContext(), AnalysisTimeActivity.class);
                startActivity(time);
            }
        });
    }


}
