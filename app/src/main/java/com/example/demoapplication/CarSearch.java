package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

public class CarSearch extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner spinner1,spinner2;
    Button btn_submit;
    String companyid[],companyname[];
    ArrayAdapter<String> CompanyAdapter;
    String companySelected;
    String showroomid[],showroomname[];
    ArrayAdapter<String> showroomAdapter;
    String car_id[],car_name[],car_type_id[],car_type[],car_rate[],showroom_id[];

    List<CarSearchBean>carSearchBeanList;

    CarSearchAdapter carSearchAdapter;

    RecyclerView rcv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_search);

        rcv=findViewById(R.id.rcv_searchcars);

        carSearchBeanList=new ArrayList<>();

        spinner1=findViewById(R.id.spinner1);
        spinner2=findViewById(R.id.spinner2);
        btn_submit=findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(this);

        spinner1.setOnItemSelectedListener(this);

        Search s=new Search();
        s.execute();


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        companySelected=companyid[position];

        SearchShow ss=new SearchShow();
        ss.execute(companySelected);
        //Toast.makeText(this, "companyid:"+companySelected, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    public class SearchShow extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            WebServiceCaller ws=new WebServiceCaller();
            ws.setSoapObject("SearchShow");
            ws.addProperty("companySelected",strings[0]);
            ws.callWebService();

            return ws.getResponse();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONArray j=new JSONArray(s);

                showroomid=new String[j.length()+1];
                showroomname=new String[j.length()+1];

                showroomid[0]="";
                showroomname[0]="----Select----";

                for(int i=0;i<j.length();i++){

                    JSONObject obj=j.getJSONObject(i);
                    showroomid[i]=obj.getString("showroomid");
                    showroomname[i]=obj.getString("showroomname");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            showroomAdapter=new ArrayAdapter<String>(CarSearch.this,android.R.layout.simple_spinner_item,showroomname);
            showroomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinner2.setAdapter(showroomAdapter);

        }
    }

    public class GetCar extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            WebServiceCaller ws=new WebServiceCaller();
            ws.setSoapObject("GetCar");
            ws.addProperty("showid",strings[0]);
            ws.callWebService();

            return ws.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("zz",s);

            try {
                JSONArray j=new JSONArray(s);

                car_id=new String[j.length()];
                car_name=new String[j.length()];
                car_type_id=new String[j.length()];
                car_type=new String[j.length()];
                showroom_id=new String[j.length()];

                car_rate=new String[j.length()];


                for (int i=0;i<j.length();i++){

                    JSONObject obj=j.getJSONObject(i);
                    car_id[i]=obj.getString("car_id");
                    car_name[i]=obj.getString("car_name");
                    car_type_id[i]=obj.getString("car_type_id");
                    car_type[i]=obj.getString("car_type");
                    car_rate[i]=obj.getString("car_rate");
                    showroom_id[i]=obj.getString("showroom_id");

                    Log.d("z",car_name[i]);

                    CarSearchBean carSearchBean=new CarSearchBean();
                    carSearchBean.setCarid(car_id[i]);
                    carSearchBean.setCarname(car_name[i]);
                    carSearchBean.setCartypeid( car_type_id[i]);
                    carSearchBean.setCartypename(car_type[i]);
                    carSearchBean.setCarrate(car_rate[i]);
                    carSearchBean.setShowroomid(showroomid[i]);

                    carSearchBeanList.add(carSearchBean);
                    carSearchBeanList.size();

                }

                carSearchAdapter=new CarSearchAdapter(carSearchBeanList);
                rcv.setLayoutManager(new LinearLayoutManager(CarSearch.this));
                rcv.setAdapter(carSearchAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public class Search extends AsyncTask<String,String,String>{

         @Override
         protected String doInBackground(String... strings) {

             WebServiceCaller ws=new WebServiceCaller();
             ws.setSoapObject("Search");
             ws.callWebService();

             return ws.getResponse();
         }

         @Override
         protected void onPostExecute(String s) {
             super.onPostExecute(s);
             Log.d("ww",s);

           //  Toast.makeText(CarSearch.this, s, Toast.LENGTH_SHORT).show();

             try {
                 JSONArray j=new JSONArray(s);

                 companyid=new String[j.length()];
                 companyname=new String[j.length()];

                companyid[0]="";
                companyname[0]="----Select----";

                 for (int i=0;i<j.length();i++){

                     JSONObject obj=j.getJSONObject(i);
                     companyid[i]=obj.getString("companyid");
                     companyname[i]=obj.getString("companyname");
                 }

             } catch (JSONException e) {
                 e.printStackTrace();
             }

               CompanyAdapter=new ArrayAdapter<String>(CarSearch.this,android.R.layout.simple_spinner_item,companyname);
               CompanyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
               spinner1.setAdapter(CompanyAdapter);

         }
     }
    @Override
    public void onClick(View v) {

       String showid=showroomid[spinner2.getSelectedItemPosition()];


//        Intent e=new Intent(CarSearch.this,Car_details.class);
//        e.putExtra("showid",showid);
//        startActivity(e);

        GetCar c=new GetCar();
        c.execute(showid);

    }
}
