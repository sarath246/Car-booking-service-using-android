package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Car_details extends AppCompatActivity  {

    TextView showroom_id,car_milage,engin_displacement,max_tork,max_power,boot_space,fueltank_cappacity,body_type,power_stering,powerWindow_front,abs,driver_airbag,passenger_airbag,auto_climate_control,flyLight_front,alloy_weal,engin_transmission,car_interior,car_exterior,car_safty,car_price;
    Button btn_purchase;
    ImageView Car_photo;
    String carphoto,carprice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        Car_photo=findViewById(R.id.Car_photo);
        showroom_id=findViewById(R.id.showroom_id);
        car_milage=findViewById(R.id.milage);
        engin_displacement=findViewById(R.id.engine_displacement);
        max_tork=findViewById(R.id.max_tork);
        max_power=findViewById(R.id.max_power);
        boot_space=findViewById(R.id.boot_space);
        fueltank_cappacity=findViewById(R.id.fuelTank_cappacity);
        body_type=findViewById(R.id.body_type);
        power_stering=findViewById(R.id.power_stering);
        powerWindow_front=findViewById(R.id.powerWindow_front);
        abs=findViewById(R.id.ABS);
        driver_airbag=findViewById(R.id.driver_airbag);
        passenger_airbag=findViewById(R.id.passenger_airbag);
        auto_climate_control=findViewById(R.id.auto_climate_control);
        flyLight_front=findViewById(R.id.flyLight_front);
        alloy_weal=findViewById(R.id.alloy_weal);
        engin_transmission=findViewById(R.id.engine_transmission);
        car_interior=findViewById(R.id.car_interior);
        car_exterior=findViewById(R.id.car_exterior);
        car_safty=findViewById(R.id.car_safty);
        car_price=findViewById(R.id.car_price);

        //1 get intent

        btn_purchase=findViewById(R.id.btn_purchase);


        Intent i=getIntent();
        String carid=i.getStringExtra("carid");

        Cardetails cd=new Cardetails();
        cd.execute(carid);

        //Intent j=getIntent();
        //String showid=j.getStringExtra("showroomid");

        btn_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String showid=showroom_id.getText().toString();

                //2 get intent

                btn_purchase=findViewById(R.id.btn_purchase);
                Intent i=getIntent();
                String carid=i.getStringExtra("carid");
                //String showid=i.getStringExtra("showid");


             Intent e=new Intent(Car_details.this,Car_booking.class);
                e.putExtra("carphoto",carphoto);
                e.putExtra("carprice",carprice);
                e.putExtra("carid",carid);
                e.putExtra("showid",showid);
                startActivity(e);
            }
        });




        Toast.makeText(this, "carid=="+carid, Toast.LENGTH_SHORT).show();



    }


    public class Cardetails extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            WebServiceCaller ws=new WebServiceCaller();
            ws.setSoapObject("Cardetails");
            ws.addProperty("carid",strings[0]);
            ws.callWebService();

            return ws.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("qw",s);

            Toast.makeText(Car_details.this, s, Toast.LENGTH_SHORT).show();

            try {
                JSONArray j=new JSONArray(s);
                JSONObject obj=j.getJSONObject(0);


                String car=obj.getString("carname");
                String showroom=obj.getString("showroomid");
                String carmilage=obj.getString("carmilage");
                String engindisplacement=obj.getString("engine_displacement");
                String maxtork=obj.getString("max_tork");
                String maxpower=obj.getString("max_power");
                String bootspace=obj.getString("boot_space");

                String fueltankCappacity=obj.getString("fueltank_cappacity");
                String bodytype=obj.getString("body_type");
                String powerstering=obj.getString("power_stering");
                String powerWindowfront=obj.getString("powerwindow_front");
                String Abs=obj.getString("abs");
                String driverairbag=obj.getString("driver_airbag");
                String passengerairbag=obj.getString("passenger_airbag");

                String auto_climatecontrol=obj.getString("automatic_climatecontrol");
                String flyLightfront=obj.getString("flylight_front");
                String alloyweal=obj.getString("alloy_weal");
                String engintransmission=obj.getString("engin_transmission");
                String carinterior=obj.getString("car_interior");
                String carexterior=obj.getString("car_exterior");
                String carsafty=obj.getString("car_safty");
                carprice=obj.getString("car_price");
                carphoto=obj.getString("car_photo");


               // Log.d("qw",car);


                showroom_id.setText(showroom);
                car_milage.setText(carmilage);
                engin_displacement.setText(engindisplacement);
                max_tork.setText(maxtork);
                max_power.setText(maxpower);
                boot_space.setText(bootspace);
                fueltank_cappacity.setText(fueltankCappacity);
                body_type.setText(bodytype);
                power_stering.setText(powerstering);
                powerWindow_front.setText(powerWindowfront);
                abs.setText(Abs);
                driver_airbag.setText(driverairbag);
                passenger_airbag.setText(passengerairbag);
                auto_climate_control.setText(auto_climatecontrol);
                flyLight_front.setText(flyLightfront);
                alloy_weal.setText(alloyweal);
                engin_transmission.setText(engintransmission);
                car_interior.setText(carinterior);
                car_exterior.setText(carexterior);
                car_safty.setText(carsafty);
                car_price.setText(carprice);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}
