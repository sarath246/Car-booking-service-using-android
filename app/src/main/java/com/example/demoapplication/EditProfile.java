package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {

    EditText et_name,et_address,et_email,et_phone;
    Button btn_update;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        SharedPreferences sh=getSharedPreferences("user",MODE_PRIVATE);
         userid=sh.getString("userid","");

        et_name=findViewById(R.id.et_name);
        et_address=findViewById(R.id.et_address);
        et_email=findViewById(R.id.et_email);
        et_phone=findViewById(R.id.et_phone);
        btn_update=findViewById(R.id.btn_update);

        btn_update.setOnClickListener(this);

        UserPro u=new UserPro();
        u.execute(userid);
    }
    public class EditPro extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller w=new WebServiceCaller();
            w.setSoapObject("EditPro");
            w.addProperty("userid",strings[0]);
            w.addProperty("Name",strings[1]);
            w.addProperty("Address",strings[2]);
            w.addProperty("Email",strings[3]);
            w.addProperty("Phone",strings[4]);
            w.callWebService();
            return w.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(EditProfile.this, s, Toast.LENGTH_SHORT).show();
            Intent r = new Intent(EditProfile.this, EditProfile.class);
            startActivity(r);

        }
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
                JSONArray ja=new JSONArray(s);
                JSONObject obj=ja.getJSONObject(0);

                String name=obj.getString("Name");
                String address=obj.getString("Address");
                String email=obj.getString("Email");
                String phone=obj.getString("Phone");

                et_name.setText(name);
                et_address.setText(address);
                et_email.setText(email);
                et_phone.setText(phone);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {

        String Name=et_name.getText().toString();
        String Address=et_address.getText().toString();
        String Email=et_email.getText().toString();
        String Phone=et_phone.getText().toString();

        EditPro ep=new EditPro();
        ep.execute(userid,Name,Address,Email,Phone);

    }
}
