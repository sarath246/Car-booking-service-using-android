package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

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

public class ChangePassword extends AppCompatActivity implements View.OnClickListener {

    EditText et_cpsw,et_newpsw,et_confpsw;
    Button btn_change;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        SharedPreferences sh=getSharedPreferences("user",MODE_PRIVATE);
         userid=sh.getString("userid","");

        et_cpsw=findViewById(R.id.et_cpsw);
        et_newpsw=findViewById(R.id.et_newpsw);
        et_confpsw=findViewById(R.id.et_confpsw);
        btn_change=findViewById(R.id.btn_change);



        btn_change.setOnClickListener(this);
    }
     public class ChPsw extends AsyncTask<String,String,String>{

         @Override
         protected String doInBackground(String... strings) {

             WebServiceCaller ws=new WebServiceCaller();
             ws.setSoapObject("ChPsw");
             ws.addProperty("userid",strings[0]);
             ws.addProperty("Cpsw",strings[1]);
             ws.addProperty("NewPsw",strings[2]);
             ws.addProperty("ConfPsw",strings[3]);
             ws.callWebService();

             return ws.getResponse();
         }
         @Override
         protected void onPostExecute(String s) {
             super.onPostExecute(s);
             Toast.makeText(ChangePassword.this, s, Toast.LENGTH_SHORT).show();
         }

     }
    @Override
    public void onClick(View v) {

        String current=et_cpsw.getText().toString();
        String newpsw=et_newpsw.getText().toString();
        String confirm=et_confpsw.getText().toString();

        Toast.makeText(this, "Password changed", Toast.LENGTH_SHORT).show();

        ChPsw cp=new ChPsw();
        cp.execute(userid,current,newpsw,confirm);

    }
}
