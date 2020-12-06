package com.example.demoapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class find_location extends AppCompatActivity {

    private static final int REQUEST_CODE_LOCATION_PERMISSIN=1;
    private TextView txtLatLog,Longitude,address;
    private ProgressBar progress_bar;
    private ResultReceiver resultReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_location);

        resultReceiver = new AddressResultReciever(new Handler());

        txtLatLog=findViewById(R.id.txt_latitude);
        Longitude=findViewById(R.id.txt_longitude);
        progress_bar=findViewById(R.id.progress_bar);
        address=findViewById(R.id.txt_address);

        findViewById(R.id.btn_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){

                    ActivityCompat.requestPermissions(find_location.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_LOCATION_PERMISSIN);

                }else{

                    getCurrentLocation();

                }

//                String lat=txtLatLog.getText().toString();
//                String longt=Longitude.getText().toString();
//
//                Intent i=new Intent(find_location.this,Service_booking.class);
//                i.putExtra("latitude",lat);
//                i.putExtra("longitude",longt);
//                startActivity(i);

            }
        });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_LOCATION_PERMISSIN && grantResults.length > 0){

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

                getCurrentLocation();

            }else {

                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();

            }

        }

    }

    private void getCurrentLocation() {

        progress_bar.setVisibility(View.VISIBLE);

        LocationRequest locationRequest=new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(locationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(find_location.this).requestLocationUpdates(locationRequest,new LocationCallback(){


            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);


                LocationServices.getFusedLocationProviderClient(find_location.this).removeLocationUpdates(this);

                if (locationResult != null && locationResult.getLocations().size() > 0){


                    int latestLocationIndex=locationResult.getLocations().size() - 1;

                    double latitude=locationResult.getLocations().get(latestLocationIndex).getLatitude();
                    double longitude=locationResult.getLocations().get(latestLocationIndex).getLongitude();

                    txtLatLog.setText(String.format("latitude : %s",latitude));

                    Longitude.setText(String.format("longitude : %s",longitude));

                    Location location=new Location("providerNA");
                    location.setLatitude(latitude);
                    location.setLongitude(longitude);
                    fetchAddressFromLatLong(location);

                }else {
                    progress_bar.setVisibility(View.GONE);
                }
            }
        }, Looper.getMainLooper());


    }

     private void fetchAddressFromLatLong (Location location) {

        Intent intent=new Intent(this,FetchAddressIntentService.class);
        intent.putExtra(Constants.RECIEVER, resultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, location);
        startService(intent);

     }

     private class AddressResultReciever extends ResultReceiver {

         public AddressResultReciever(Handler handler) {
             super(handler);
         }

         @Override
         protected void onReceiveResult(int resultCode, Bundle resultData) {
             super.onReceiveResult(resultCode, resultData);

             if (resultCode == Constants.SUCCESS_RESULT){

                 address.setText(resultData.getString(Constants.RESULT_DATA_KEY));

             }else {

                 Toast.makeText(find_location.this, resultData.getString(Constants.RESULT_DATA_KEY), Toast.LENGTH_SHORT).show();
                 
             }
              progress_bar.setVisibility(View.GONE);

         }
     }

}
