package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class My_orders extends AppCompatActivity {

    TextView car_name,car_color,car_price,showroomname;
    String carname[],carcolor[],carprice[],showroom[];

    List<MyOrderBean>myOrderBeanList;
    MyOrderAdapter myOrderAdapter;

    RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        car_name=findViewById(R.id.car_name);
        car_color=findViewById(R.id.car_color);
        car_price=findViewById(R.id.car_price);
        showroomname=findViewById(R.id.showroom);

        rcv=findViewById(R.id.rcv_myorders);
        myOrderBeanList=new ArrayList<>();

        SharedPreferences sh=getSharedPreferences("user",MODE_PRIVATE);
        String userid=sh.getString("userid","");

        CarOrders o=new CarOrders();
        o.execute(userid);

    }
    public class CarOrders extends AsyncTask<String,String,String>{


        @Override
        protected String doInBackground(String... strings) {

            WebServiceCaller wc=new WebServiceCaller();
            wc.setSoapObject("CarOrders");
            wc.addProperty("userid",strings[0]);
            wc.callWebService();

            return wc.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(My_orders.this, s, Toast.LENGTH_SHORT).show();

            try {

                JSONArray j=new JSONArray(s);

                carname=new String[j.length()];
                carcolor=new String[j.length()];
                carprice=new String[j.length()];
                showroom=new String[j.length()];

                for (int i=0;i<j.length();i++){

                    JSONObject obj=j.getJSONObject(i);

                    carname[i]=obj.getString("car_id");
                    carcolor[i]=obj.getString("car_color");
                    carprice[i]=obj.getString("car_price");
                    showroom[i]=obj.getString("showroom_id");

                    MyOrderBean myOrderBean=new MyOrderBean();

                    myOrderBean.setCarname(carname[i]);
                    myOrderBean.setCarcolor(carcolor[i]);
                    myOrderBean.setCarprice(carprice[i]);
                    myOrderBean.setShowroom(showroom[i]);

                    myOrderBeanList.add(myOrderBean);

                }
                myOrderAdapter=new MyOrderAdapter(myOrderBeanList);
                rcv.setLayoutManager(new LinearLayoutManager(My_orders.this));
                rcv.setAdapter(myOrderAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
