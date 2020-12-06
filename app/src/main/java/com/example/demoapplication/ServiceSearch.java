package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class ServiceSearch extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner car,place;
    Button btn_service;

    RecyclerView rcv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_search);

        car=findViewById(R.id.spinner1);
        place=findViewById(R.id.spinner2);
        rcv=findViewById(R.id.rcv_service);

        btn_service.setOnClickListener(this);

        car.setOnItemSelectedListener(this);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public void onClick(View v) {

    }
}
