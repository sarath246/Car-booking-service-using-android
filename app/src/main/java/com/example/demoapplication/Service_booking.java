package com.example.demoapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Service_booking extends AppCompatActivity implements View.OnClickListener {

    Spinner car,place;
    Button btn_service;
    String userid;
    EditText user_name,user_address,user_contact,user_email,car_name,max_days,last_service;
    String placeSelected;
    String placeid[],placename[];
    ArrayAdapter<String> placeAdapter;

    RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_booking);

        SharedPreferences sh=getSharedPreferences("user",MODE_PRIVATE);
        userid=sh.getString("userid","");

//        Intent j=getIntent();
//        String latitude=j.getStringExtra("latitude");
//        String longitude=j.getStringExtra("longitude");

        car=findViewById(R.id.spinner1);
        place=findViewById(R.id.spinner_place);
        rcv=findViewById(R.id.rcv_service);
        user_name=findViewById(R.id.user_name);
        user_address=findViewById(R.id.user_address);
        user_email=findViewById(R.id.user_email);
        user_contact=findViewById(R.id.user_contact);
        car_name=findViewById(R.id.car_name);
        max_days=findViewById(R.id.max_days);
        last_service=findViewById(R.id.last_servicedate);
        btn_service=findViewById(R.id.btn_service);

        btn_service.setOnClickListener(this);

        UserDetails d=new UserDetails();
        d.execute(userid);

        SearchPlace pn=new SearchPlace();
        pn.execute();

//        Toast.makeText(this, "latitude=="+latitude, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "longitude=="+longitude, Toast.LENGTH_SHORT).show();

    }
     public void onItemSelected(AdapterView<?> parent,View view,int position,long id){
        placeSelected=placeid[position];

        SearchShow sn=new SearchShow();
        sn.execute(placeSelected);

     }
      public void onNothingSelected(AdapterView<?> parent){

      }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public class SearchShow extends AsyncTask<String,String,String>{

           @Override
           protected String doInBackground(String... strings) {
               WebServiceCaller wc=new WebServiceCaller();
               wc.setSoapObject("SearchShow");
               wc.addProperty("placeSelected",strings[0]);
               wc.callWebService();

               return wc.getResponse();
           }
       }

       public class SearchPlace extends AsyncTask<String,String,String>{

           @Override
           protected String doInBackground(String... strings) {
               WebServiceCaller wc=new WebServiceCaller();
               wc.setSoapObject("SearchPlace");
               wc.callWebService();

               return wc.getResponse();
           }

           @Override
           protected void onPostExecute(String s) {
               super.onPostExecute(s);

               Toast.makeText(Service_booking.this, s, Toast.LENGTH_SHORT).show();

               try {

                   JSONArray j=new JSONArray(s);

                   placeid=new String[j.length()];
                   placename=new String[j.length()];

                   placeid[0]="";
                   placename[0]="---Select---";

                   for (int i=0;i<j.length();i++){

                       JSONObject obj=j.getJSONObject(i);
                       placeid[i]=obj.getString("place_id");
                       placename[i]=obj.getString("place_name");

                   }

               } catch (JSONException e) {
                   e.printStackTrace();
               }
               placeAdapter=new ArrayAdapter<String>(Service_booking.this,android.R.layout.simple_spinner_item,placename);
               placeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
               place.setAdapter(placeAdapter);
           }
       }

    public class UserDetails extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller wc=new WebServiceCaller();
            wc.setSoapObject("UserDetails");
            wc.addProperty("userid",strings[0]);
            wc.callWebService();

            return wc.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {

                JSONArray j=new JSONArray(s);

                JSONObject obj=j.getJSONObject(0);

                String name=obj.getString("user_name");
                String address=obj.getString("user_address");
                String contact=obj.getString("user_contact");
                String email=obj.getString("user_email");

                user_name.setText(name);
                user_address.setText(address);
                user_email.setText(email);
                user_contact.setText(contact);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public class GetShowroom extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller wc=new WebServiceCaller();
            wc.setSoapObject("GetShowroom");

            wc.callWebService();

            return wc.getResponse();
        }
    }
     public class ServiceBooking extends AsyncTask<String,String,String>{

         @Override
         protected String doInBackground(String... strings) {
             WebServiceCaller wc=new WebServiceCaller();
             wc.setSoapObject("ServiceBooking");
             wc.addProperty("name",strings[0]);
             wc.addProperty("email",strings[1]);
             wc.addProperty("address",strings[2]);
             wc.addProperty("phone",strings[3]);
             wc.addProperty("car_name",strings[4]);
             wc.addProperty("Last_service_date",strings[5]);
             wc.addProperty("Max_days",strings[6]);
             wc.addProperty("userid",strings[7]);
             wc.callWebService();

             return wc.getResponse();
         }
     }

    @Override
    public void onClick(View v) {


        String name=user_name.getText().toString();
        String email=user_email.getText().toString();
        String address=user_address.getText().toString();
        String phone=user_contact.getText().toString();
        String car=car_name.getText().toString();
        String placnm=place.toString();
        String date=last_service.getText().toString();
        String maxdays=max_days.getText().toString();

        Toast.makeText(this, "values"+name, Toast.LENGTH_SHORT).show();

        ServiceBooking sb=new ServiceBooking();
        sb.execute(name,email,address,phone,car,placnm,date,maxdays,userid);


    }
}
