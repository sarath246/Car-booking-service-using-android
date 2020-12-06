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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchHomePage extends AppCompatActivity implements View.OnClickListener {

    CardView car,spareparts;
    TextView uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_home_page);


        car=findViewById(R.id.id_carsearch);
        spareparts=findViewById(R.id.id_sparepartssearch);
        uname=findViewById(R.id.txt_uname);

        car.setOnClickListener(this);
        spareparts.setOnClickListener(this);

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

                        Intent m=new Intent(getApplicationContext(),Cars_lists.class);
                        startActivity(m);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_search:


                }


                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {

        if (v==car){
            Intent i=new Intent(SearchHomePage.this,CarSearch.class);
            startActivity(i);
        }else if (v==spareparts){
            Intent j=new Intent(SearchHomePage.this,SparePartsBooking.class);
            startActivity(j);
        }

    }
}
