package com.example.gimmyeongsu.termproject;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

/**
 * Created by gimmyeongsu on 2016. 10. 31..
 */



public class GpsInfo extends Service implements LocationListener {

    private final Context mContext;

    // 현재 gps 사용유무
    boolean isGPSEnabled = false;
    // 네트워크 사용유무
    boolean isNetworkEnabled = false;
    // gps 상태값
    boolean isGetLocation = false;




    Location location;
    double lat;     // 위도
    double lon;     // 경도


    // 최소 gps 정보 업데이트 거리 : 미터로 계산  ::  현재 1미터
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;
    // 최소 gps 정보 업데이트 시간 : 밀리세컨드로 계산  ::  현재 1초
    private static final long MIN_TIME_BW_UPDATES = 1000 * 1;


    protected LocationManager locationManager;

    public GpsInfo(Context context){
        this.mContext = context;
        getLocation();
    }



    public Location getLocation(){
        try{
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);


            // gps 정보 가져오기
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // 현재 네트워크 상태 값 알아오기
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


            if(!isGPSEnabled && !isNetworkEnabled){
                // GPS 와 네트워크 사용이 가능하지 않을때 소스 구현
            }
            else{
                this.isGetLocation = true;
                // 네트워크 정보로부터 위치값 가져오기
                if(isNetworkEnabled){
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, (LocationListener) this);

                    if(locationManager != null){
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        if(location != null){
                            // 위도 경도 저장
                            lat = location.getLatitude();
                            lon = location.getLongitude();
                        }
                    }

                }
            }

            if (isGPSEnabled) {
                if(location == null){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, (LocationListener) this);
                    if(locationManager != null){
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if(location != null){
                            lat = location.getLatitude();
                            lon = location.getLongitude();
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return location;
    }

    // GPS 종료
    public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates((LocationListener) GpsInfo.this);
        }
    }

    // 위도값을 가져온다
    public double getLatitude(){
        if(location != null){
            lat = location.getLatitude();
        }
        return lat;
    }

    // 경도값을 가져온다
    public double getLongitude(){
        if(location != null){
            lon = location.getLongitude();
        }
        return lon;
    }

    // GPS 나 wife 정보가 켜져있는지 확인
    public boolean isGetLocation(){
        return this.isGetLocation;
    }





    @Override
    public IBinder onBind(Intent arg0){
        return null;
    }
    public void onLocationChanged(Location location){

    }
    public void onStatusChanged(String provider, int status, Bundle extras){

    }
    public void onProviderEnabled(String provider){

    }
    public void onProviderDisabled(String provider){

    }



}
