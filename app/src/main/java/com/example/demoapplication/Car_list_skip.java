package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Car_list_skip extends AppCompatActivity implements View.OnClickListener {

    CardView card1,card2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list_skip);

        card1=findViewById(R.id.btn_myOrders);
        card2=findViewById(R.id.btn_servicebooking);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v==card1){

            Toast.makeText(this, "Please register", Toast.LENGTH_SHORT).show();

//            Intent i=new Intent(Car_list_skip.this,My_orders.class);
//            startActivity(i);

        }else if (v==card2){

            Toast.makeText(this, "Please register", Toast.LENGTH_SHORT).show();

//            Intent n=new Intent(Cars_lists.this, Service_booking.class);
//            startActivity(n);

        }

    }
}
