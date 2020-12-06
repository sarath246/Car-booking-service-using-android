package com.example.demoapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.demoapplication.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements View.OnClickListener, MenuItem.OnMenuItemClickListener {

    Button btn_booking,btn_carsearch,btn_myOrders,btn_service;
    CardView card,card2,card3;
    TextView uname;
    MenuItem home,search;
    String carname[],showroomid[],cartypeid[],carprice[];
    View view;
    BottomNavigationView bottomNavigationView;

    List<HomePageBean>homePageBeanList;
    HomePageAdapter homePageAdapter;

    RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);


        view=this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.gray);

        card=findViewById(R.id.id_myprofile);
        card2=findViewById(R.id.id_card2);
        card3=findViewById(R.id.id_cpsw);
        uname=findViewById(R.id.txt_uname);

        card.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);

        BottomNavigationView bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottom_navbar);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.navigation_home:

                    case R.id.navigation_cars:

                        Intent m=new Intent(getApplicationContext(),Cars_lists.class);
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
        SharedPreferences sh=getSharedPreferences("user",MODE_PRIVATE);
        String userid=sh.getString("userid","");
        String username=sh.getString("username","");
        Toast.makeText(this, "Welcome "+username, Toast.LENGTH_SHORT).show();

        uname.setText(username);

        rcv=findViewById(R.id.rcv_homepagecollection);

        homePageBeanList=new ArrayList<>();

        //btn_booking=findViewById(R.id.btn_booking);
        //btn_carsearch=findViewById(R.id.btn_carsearch);
        btn_myOrders=findViewById(R.id.btn_myOrders);
        btn_service=findViewById(R.id.btn_servicebooking);

//        btn_booking.setOnClickListener(this);
        //btn_carsearch.setOnClickListener(this);
        //btn_myOrders.setOnClickListener(this);
        //btn_service.setOnClickListener(this);


        Home h=new Home();
        h.execute();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.homepage_nav_bar,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        if (item==home){
            Intent i=new Intent(HomePage.this,HomePage.class);
            startActivity(i);
        }else if (item==search){
            Intent j=new Intent(HomePage.this,SearchHomePage.class);
            startActivity(j);
        }

        return true;
    }

    public class Home extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            WebServiceCaller ws=new WebServiceCaller();
            ws.setSoapObject("Home");
            ws.callWebService();

            return ws.getResponse();
        }

//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//
//            Log.d("zz",s);
//
//            Toast.makeText(HomePage.this, s, Toast.LENGTH_SHORT).show();
//
//            try {
//
//                JSONArray j=new JSONArray(s);
//
//                carname=new String[j.length()];
//                showroomid=new String[j.length()];
//                cartypeid=new String[j.length()];
//                carprice=new String[j.length()];
//
//
//                for(int i=0;i<j.length();i++){
//
//                    JSONObject obj=j.getJSONObject(i);
//                    carname[i]=obj.getString("car_name");
//                    showroomid[i]=obj.getString("showroom_id");
//                    cartypeid[i]=obj.getString("cartype_id");
//                    carprice[i]=obj.getString("car_price");
//
//                    HomePageBean homePageBean=new HomePageBean();
//
//                    homePageBean.setCarname(carname[i]);
//                    homePageBean.setCartypeid(cartypeid[i]);
//                    homePageBean.setShowroomid(showroomid[i]);
//                    homePageBean.setCarprice(carprice[i]);
//
//                    homePageBeanList.add(homePageBean);
//
//                }
//                homePageAdapter=new HomePageAdapter(homePageBeanList);
//                rcv.setLayoutManager(new LinearLayoutManager(HomePage.this));
//                rcv.setAdapter(homePageAdapter);
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
    }


    @Override
    public void onClick(View v) {

        if (v==btn_booking){
            Intent b=new Intent(HomePage.this,SparePartsBooking.class);
            startActivity(b);
       //}
        //else if (v==btn_add){
//            Intent e=new Intent(HomePage.this,Car_addfav.class);
//            startActivity(e);
        }else if (v==card){
            Intent l=new Intent(HomePage.this,Myprofile.class);
            startActivity(l);
        }else if (v==card2){
            Intent c2=new Intent(HomePage.this,EditProfile.class);
            startActivity(c2);
        }else if (v==card3){
            Intent c3=new Intent(HomePage.this,ChangePassword.class);
            startActivity(c3);
        }
    }




}
