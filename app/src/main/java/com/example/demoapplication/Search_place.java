package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Search_place extends FragmentActivity implements OnMapReadyCallback {

    Button btn_find;

    GoogleMap mapAPI;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_place);

        btn_find=findViewById(R.id.btn_find);

        //btn_find.setOnClickListener(this);

        mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapAPI);

        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapAPI=googleMap;

        LatLng Cochi=new LatLng(9.931233,76.267303);
        mapAPI.addMarker(new MarkerOptions().position(Cochi).title("Cochi"));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(Cochi));

    }
}
