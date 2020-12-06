package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SpareParts_orderNow extends AppCompatActivity implements View.OnClickListener {

    TextView spareparts_name,car_name,spareparts_type,company,rate,showroom,description;
    Button btn_purchase;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_parts_order_now);

        SharedPreferences sh=getSharedPreferences("user",MODE_PRIVATE);
        userid=sh.getString("userid","");

        Intent i=getIntent();
        String carid=i.getStringExtra("carid");
        String sparepartsname=i.getStringExtra("spareparts_name");
        String showid=i.getStringExtra("showroomid");
        String type=i.getStringExtra("sparepartstype");
        String compid=i.getStringExtra("companyid");
        String cost=i.getStringExtra("sparepartscost");

        car_name=findViewById(R.id.car_name);
        spareparts_name=findViewById(R.id.spare_name);
        spareparts_type=findViewById(R.id.spareparts_type);
        company=findViewById(R.id.company);
        rate=findViewById(R.id.spareparts_rate);
        showroom=findViewById(R.id.showroom);
        description=findViewById(R.id.showroom);
        btn_purchase=findViewById(R.id.btn_purchase);


        spareparts_name.setText(sparepartsname);
        spareparts_type.setText(type);
        company.setText(compid);
        rate.setText(cost);
        showroom.setText(showid);
        car_name.setText(carid);

        btn_purchase.setOnClickListener(this);

    }
     public class SpareOrders extends AsyncTask<String,String,String>{

         @Override
         protected String doInBackground(String... strings) {
             WebServiceCaller wc=new WebServiceCaller();
             wc.setSoapObject("SpareOrders");
             wc.addProperty("carid",strings[0]);
             wc.addProperty("userid",strings[1]);
             wc.addProperty("spareparts_name",strings[2]);
             wc.addProperty("spareparts_type",strings[3]);
             wc.addProperty("company_name",strings[4]);
             wc.addProperty("rate",strings[5]);
             wc.addProperty("showroomid",strings[6]);
             wc.callWebService();

             return wc.getResponse();
         }
     }

    @Override
    public void onClick(View v) {

        String sparename=spareparts_name.getText().toString();
        String companyid=company.getText().toString();
        String showroomid=showroom.getText().toString();
        String cost=rate.getText().toString();
        String type=spareparts_type.getText().toString();
        String carid=car_name.getText().toString();

        SharedPreferences sh=getSharedPreferences("user",MODE_PRIVATE);
        userid=sh.getString("userid","");

        Toast.makeText(this, "sparepartsname=="+sparename, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "company=="+companyid, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "rate"+cost, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "showroom=="+showroomid, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "sparepartstype"+type, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "carid=="+carid, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "userid=="+userid, Toast.LENGTH_SHORT).show();

        SpareOrders o=new SpareOrders();
        o.execute(carid,userid,sparename,type,companyid,cost,showroomid);


    }
}
