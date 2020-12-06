package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SparePartsBooking extends AppCompatActivity implements View.OnClickListener
       // implements View.OnClickListener
{

    Spinner car,showroom;
    Button btn_booking;
    String carid[],carname[];
    String carSelected;
    //String showroomid[],showroomname[];
    ArrayAdapter<String> showroomAdapter;
    ArrayAdapter<String> caradapter;
    String spareparts_name[],spareparts_type[],companyid[],spareparts_cost[],description[],showroomid[],userid;

    List<SparePartsBookingBean>sparePartsBookingBeanList;
    SparePartsBookingAdapter sparePartsBookingAdapter;

    RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_parts_booking);

        SharedPreferences sh=getSharedPreferences("user",MODE_PRIVATE);
        userid=sh.getString("userid","");

        rcv=findViewById(R.id.rcv_searchspareparts);

        sparePartsBookingBeanList=new ArrayList<>();

        car=findViewById(R.id.spinner);
        showroom=findViewById(R.id.spinner2);
        btn_booking=findViewById(R.id.btn_booking);

        btn_booking.setOnClickListener(this);

        Car c=new Car();
        c.execute();


    }
    public static class Purchased extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller wc=new WebServiceCaller();
            wc.setSoapObject("Purchased");
            wc.addProperty("spareparts_name",strings[0]);
            wc.addProperty("spareparts_type",strings[1]);

            wc.addProperty("carid",strings[2]);
            wc.addProperty("showroomid",strings[3]);
            wc.addProperty("spareparts_cost",strings[4]);
            wc.addProperty("companyid",strings[5]);
            wc.addProperty("userid",strings[6]);
            wc.callWebService();

            return wc.getResponse();
        }
    }

//    public class CarShow extends AsyncTask<String,String,String>{
//
//        @Override
//        protected String doInBackground(String... strings) {
//            WebServiceCaller ws=new WebServiceCaller();
//            ws.setSoapObject("CarShow");
//            ws.addProperty("carid",strings[0]);
//            ws.callWebService();
//
//            return ws.getResponse();
//        }



//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            Log.d("zz",s);
//
//            Toast.makeText(SparePartsBooking.this, "showid=="+s, Toast.LENGTH_SHORT).show();
//
//            try {
//
//                JSONArray j=new JSONArray(s);
//
//                showroomid=new String[j.length()+1];
//                showroomname=new String[j.length()+1];
//
//                showroomid[0]="";
//                showroomname[0]="----Select----";
//
//                for (int i=0;i<j.length();i++){
//                    JSONObject obj=j.getJSONObject(i);
//                    showroomid[i]=obj.getString("showroomid");
//                    showroomname[i]=obj.getString("showroomname");
//                }
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            showroomAdapter=new ArrayAdapter<String>(SparePartsBooking.this,android.R.layout.simple_spinner_item,showroomname);
//            showroomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
//            showroom.setAdapter(showroomAdapter);
//        }
    //}


    public class Car extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            WebServiceCaller wc=new WebServiceCaller();
            wc.setSoapObject("Car");
            wc.callWebService();

            return wc.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("zz",s);

            Toast.makeText(SparePartsBooking.this, s, Toast.LENGTH_SHORT).show();

            try {
                JSONArray j=new JSONArray(s);

                carid=new String[j.length()];
                carname=new String[j.length()];


                for(int i=0;i<j.length();i++){
                    JSONObject obj=j.getJSONObject(i);
                    carid[i]=obj.getString("carid");
                    carname[i]=obj.getString("carname");
                    Toast.makeText(SparePartsBooking.this, "carid="+i, Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            caradapter=new ArrayAdapter<String>(SparePartsBooking.this,android.R.layout.simple_spinner_item,carname);
            caradapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            car.setAdapter(caradapter);

    }
    }

    public class GetSpareParts extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller ws=new WebServiceCaller();
            ws.setSoapObject("GetSpareParts");
            ws.addProperty("carid",strings[0]);
            ws.callWebService();

            return ws.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("zz",s);

            Toast.makeText(SparePartsBooking.this, s, Toast.LENGTH_SHORT).show();

            try {

                JSONArray j=new JSONArray(s);

                spareparts_name=new String[j.length()];
                spareparts_type=new String[j.length()];
                companyid=new String[j.length()];
                spareparts_cost=new String[j.length()];
                description=new String[j.length()];
                showroomid=new String[j.length()];



                    for (int i=0;i<j.length();i++){

                        JSONObject obj=j.getJSONObject(i);

                        spareparts_name[i]=obj.getString("spareparts_name");
                        spareparts_type[i]=obj.getString("sparepartstype_name");
                        companyid[i]=obj.getString("company_name");
                        spareparts_cost[i]=obj.getString("spareparts_cost");
                        description[i]=obj.getString("car_description");
                        showroomid[i]=obj.getString("showroom_id");


                        SparePartsBookingBean sparePartsBookingBean=new SparePartsBookingBean();

                        sparePartsBookingBean.setSpareparts_name(spareparts_name[i]);
                        sparePartsBookingBean.setSpareparts_type(spareparts_type[i]);
                        sparePartsBookingBean.setCompanyid(companyid[i]);
                        sparePartsBookingBean.setSpareparts_cost(spareparts_cost[i]);
                        sparePartsBookingBean.setCarid(carid[i]);
                        sparePartsBookingBean.setDescription(description[i]);
                        sparePartsBookingBean.setShowroom(showroomid[i]);

                        sparePartsBookingBeanList.add(sparePartsBookingBean);
                    }
                    sparePartsBookingAdapter=new SparePartsBookingAdapter(sparePartsBookingBeanList);
                    rcv.setLayoutManager(new LinearLayoutManager(SparePartsBooking.this));
                    rcv.setAdapter(sparePartsBookingAdapter);

                } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onClick(View v) {


//        String showid=showroomid[showroom.getSelectedItemPosition()];
//        Toast.makeText(this, "showid=="+showid, Toast.LENGTH_SHORT).show();

//        Intent p=new Intent(SparePartsBooking.this,sparePartsBookingAdapter.getClass());
//        p.putExtra("userid",userid);
//        startActivity(p);



        GetSpareParts sp=new GetSpareParts();
        sp.execute(carid);

    }
}
