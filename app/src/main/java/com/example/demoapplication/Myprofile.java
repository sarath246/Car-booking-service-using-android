package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Myprofile extends AppCompatActivity implements View.OnClickListener {

    TextView name2,gdr_id,tvaddrs,emails,contactno;
    ImageView edit;
    ImageButton edits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        SharedPreferences sh=getSharedPreferences("user",MODE_PRIVATE);
        String userid=sh.getString("userid","");

        name2=findViewById(R.id.name2);
        gdr_id=findViewById(R.id.gdr_id);
        tvaddrs=findViewById(R.id.tvaddrs);
        emails=findViewById(R.id.emails);
        contactno=findViewById(R.id.contactno);
        //edit=findViewById(R.id.img_edit);
        edits=findViewById(R.id.btn_edit);

        edits.setOnClickListener(this);

        UserPro u=new UserPro();
        u.execute(userid);

        //edit.setSelected(true);

//        Intent i=new Intent(Myprofile.this,EditProfile.class);
//
//        edit.isClickable(Intent.getIntent(i));

//        edit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

          Intent i=new Intent(Myprofile.this,EditProfile.class);
          startActivity(i);

    }

    public class UserPro extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            WebServiceCaller ws=new WebServiceCaller();
            ws.setSoapObject("UserPro");
            ws.addProperty("userid",strings[0]);
            ws.callWebService();

            return ws.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONArray j=new JSONArray(s);

                JSONObject obj=j.getJSONObject(0);

                String name=obj.getString("Name");
                String gender=obj.getString("Gender");
                String address=obj.getString("Address");
                String email=obj.getString("Email");
                String phone=obj.getString("Phone");

                name2.setText(name);
                gdr_id.setText(gender);
                tvaddrs.setText(address);
                emails.setText(email);
                contactno.setText(phone);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
