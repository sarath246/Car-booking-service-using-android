package com.example.demoapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Skip extends AppCompatActivity implements View.OnClickListener {

    Button btn_mypro,btn_editpro,btn_changepsw,btn_booking,btn_carsearch,btn_carDetails;
    String carname[],showroomid[],cartypeid[],carprice[];
    CardView card;

    List<HomePageBean> homePageBeanList;
    HomePageAdapter homePageAdapter;
    ImageView swift,jaguar,hondaamaze,tatanexon;

    RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip);


        rcv=findViewById(R.id.rcv_homepagecollection);

        homePageBeanList=new ArrayList<>();

       // btn_booking=findViewById(R.id.btn_booking);
        //btn_carsearch=findViewById(R.id.btn_carsearch);
        card=findViewById(R.id.card_register);
        swift=findViewById(R.id.swift);
        jaguar=findViewById(R.id.jaguar);
        hondaamaze=findViewById(R.id.honda_amaze);
        tatanexon=findViewById(R.id.new_tata_nexon_bs6);

        //btn_booking.setOnClickListener(this);
        //btn_carsearch.setOnClickListener(this);
        card.setOnClickListener(this);
        swift.setOnClickListener(this);
        jaguar.setOnClickListener(this);
        hondaamaze.setOnClickListener(this);
        tatanexon.setOnClickListener(this);


        BottomNavigationView bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottom_navbar);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.navigation_home:

                    case R.id.navigation_cars:

                        Intent m=new Intent(getApplicationContext(),Car_list_skip.class);
                        startActivity(m);
                        finish();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_search:

                        Intent i=new Intent(getApplicationContext(),SearchHomePage.class);
                        startActivity(i);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                }


                return true;
            }
        });

    }



    @Override
    public void onClick(View v) {

        if (v==btn_booking){
            Intent b=new Intent(Skip.this,SparePartsBooking.class);
            startActivity(b);
        }else if (v==btn_carsearch) {
            Intent s = new Intent(Skip.this, CarSearch.class);
            startActivity(s);
        }else if (v==card) {
            Intent c = new Intent(Skip.this, Registration.class);
            startActivity(c);
        }else if (v==swift) {
            Toast.makeText(this, "Please register to book", Toast.LENGTH_SHORT).show();
            Intent c = new Intent(Skip.this, Registration.class);
            startActivity(c);
        }else if (v==jaguar) {
            Toast.makeText(this, "Please register to book", Toast.LENGTH_SHORT).show();
            Intent c = new Intent(Skip.this, Registration.class);
            startActivity(c);
        }else if (v==hondaamaze) {
            Toast.makeText(this, "Please register to book", Toast.LENGTH_SHORT).show();
            Intent c = new Intent(Skip.this, Registration.class);
            startActivity(c);
        }else if (v==tatanexon) {
            Toast.makeText(this, "Please register to book", Toast.LENGTH_SHORT).show();
            Intent c = new Intent(Skip.this, Registration.class);
            startActivity(c);
        }

    }


    public class Home extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {

            WebServiceCaller ws=new WebServiceCaller();
            ws.setSoapObject("Home");
            ws.callWebService();

            return ws.getResponse();
        }

        protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Log.d("zz",s);

        Toast.makeText(Skip.this, s, Toast.LENGTH_SHORT).show();

        try {

            JSONArray j=new JSONArray(s);

            carname=new String[j.length()];
            showroomid=new String[j.length()];
            cartypeid=new String[j.length()];
            carprice=new String[j.length()];


            for(int i=0;i<j.length();i++){

                JSONObject obj=j.getJSONObject(i);
                carname[i]=obj.getString("car_name");
                showroomid[i]=obj.getString("showroom_id");
                cartypeid[i]=obj.getString("cartype_id");
                carprice[i]=obj.getString("car_price");

                HomePageBean homePageBean=new HomePageBean();

                homePageBean.setCarname(carname[i]);
                homePageBean.setCartypeid(cartypeid[i]);
                homePageBean.setShowroomid(showroomid[i]);
                homePageBean.setCarprice(carprice[i]);

                homePageBeanList.add(homePageBean);

            }
            homePageAdapter=new HomePageAdapter(homePageBeanList);
            rcv.setLayoutManager(new LinearLayoutManager(Skip.this));
            rcv.setAdapter(homePageAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    }
}
