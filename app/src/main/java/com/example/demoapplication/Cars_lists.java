package com.example.demoapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Cars_lists extends AppCompatActivity implements View.OnClickListener {

    TextView uname;
    CardView card1,card2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_lists);

        uname=findViewById(R.id.txt_uname);
        card1=findViewById(R.id.btn_myOrders);
        card2=findViewById(R.id.btn_servicebooking);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);

        SharedPreferences sh=getSharedPreferences("user",MODE_PRIVATE);
        String userid=sh.getString("userid","");
        String username=sh.getString("username","");

        uname.setText(username);

        BottomNavigationView bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottom_navbar);
        bottomNavigationView.setSelectedItemId(R.id.navigation_search);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.navigation_home:

                        Intent l=new Intent(getApplicationContext(),HomePage.class);
                        startActivity(l);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_cars:


                    case R.id.navigation_search:

                        Intent m=new Intent(getApplicationContext(),SearchHomePage.class);
                        startActivity(m);
                        overridePendingTransition(0,0);
                        return true;
                }


                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {

        if (v==card1){

            Intent i=new Intent(Cars_lists.this,My_orders.class);
            startActivity(i);

        }else if (v==card2){

            Intent n=new Intent(Cars_lists.this, Service_booking.class);
            startActivity(n);

        }

    }
}
