package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Registration extends AppCompatActivity implements View.OnClickListener {


    RadioButton male,female;
    EditText fname,lname,email,address,contact,uname,psw;
    Button btn_reg;
    String Fname,Lname,gender,name,Email,adrs,phone,username,password,place_id;
    Spinner place;
    ArrayAdapter<String> placeAdapter;
    String placeid[],placename[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fname=findViewById(R.id.etFName);
        lname=findViewById(R.id.etLname);
        email=findViewById(R.id.email);
        address=findViewById(R.id.adrs);
        contact=findViewById(R.id.contact);
        uname=findViewById(R.id.uname);
        psw=findViewById(R.id.psw);
        male=findViewById(R.id.female);
        female=findViewById(R.id.rdb_female);

        place=findViewById(R.id.place_name);
        btn_reg=findViewById(R.id.btn_reg);


        btn_reg.setOnClickListener(this);

        GetPlace p=new GetPlace();
        p.execute();

    }
     public class GetPlace extends AsyncTask<String,String,String>{

         @Override
         protected String doInBackground(String... strings) {
             WebServiceCaller wc=new WebServiceCaller();
             wc.setSoapObject("GetPlace");
             wc.callWebService();

             return wc.getResponse();
         }

         @Override
         protected void onPostExecute(String s) {
             super.onPostExecute(s);

             try {

                 JSONArray j=new JSONArray(s);
                 placeid=new String[j.length()+1];
                 placename=new String[j.length()+1];

                 placeid[0]="";
                 placename[0]="---Select---";

                 for (int i=0;i<j.length();i++){

                     JSONObject obj=j.getJSONObject(i);
                     placeid[i]=obj.getString("placeid");
                     placename[i]=obj.getString("place_name");

                 }

             } catch (JSONException e) {
                 e.printStackTrace();
             }
             placeAdapter=new ArrayAdapter<String>(Registration.this,android.R.layout.simple_spinner_item,placename);
             placeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
             place.setAdapter(placeAdapter);

         }
     }

    public class InsertUser extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller wb=new WebServiceCaller();
            wb.setSoapObject("InsertUser");
            wb.addProperty("name",strings[0]);
            wb.addProperty("address",strings[1]);
            wb.addProperty("gender",strings[2]);
            wb.addProperty("contact",strings[3]);
            wb.addProperty("username",strings[4]);
            wb.addProperty("password",strings[5]);
            wb.addProperty("email",strings[6]);
            wb.addProperty("placeid",strings[7]);
            wb.callWebService();
            return wb.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(Registration.this, s, Toast.LENGTH_SHORT).show();
        }
    }


    public void getgender(View view) {
        if(view==male)
        {
            gender=male.getText().toString();
            Toast.makeText(this, "Male", Toast.LENGTH_SHORT).show();
        }else
        {
            gender=female.getText().toString();
            Toast.makeText(this, "Female", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {

        Fname=fname.getText().toString();
        Lname=lname.getText().toString();
        name=Fname+" "+Lname;
        Email=email.getText().toString();
        adrs=address.getText().toString();
        phone=contact.getText().toString();
        username=uname.getText().toString();
        password=psw.getText().toString();
        place_id=placeid[place.getSelectedItemPosition()];

        InsertUser iu=new InsertUser();

        iu.execute(name,adrs,gender,phone,username,password,Email,place_id);

    }
}
