package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText uname,psw;
    Button btn;
    TextView account,skip;
    String username,password;
    String uid,usname,user_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname=findViewById(R.id.uname);
        psw=findViewById(R.id.psw_id);
        account=findViewById(R.id.textView4);
        skip=findViewById(R.id.skip);
        btn=findViewById(R.id.button);

        account.setOnClickListener(this);
        skip.setOnClickListener(this);
        btn.setOnClickListener(this);
    }
    public class InsertLogin extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller wc=new WebServiceCaller();
            wc.setSoapObject("InsertLogin");
            wc.addProperty("uid",strings[0]);
            wc.callWebService();

            return wc.getResponse();
        }
    }

    public class LoginUser extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller wb=new WebServiceCaller();
            wb.setSoapObject("LoginUser");
            wb.addProperty("username",strings[0]);
            wb.addProperty("password",strings[1]);
            wb.callWebService();

            return wb.getResponse();
        }



        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("zz",s);
           // Toast.makeText(Login.this, "", Toast.LENGTH_SHORT).show();

            if(s.equals("[]"))
            {
                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                try {
                    JSONArray j=new JSONArray(s);

                    JSONObject obj=j.getJSONObject(0);
                    uid=obj.getString("userid");
                    usname=obj.getString("username");
                   // user_status=obj.getString("user_status");

                    SharedPreferences.Editor sh=getSharedPreferences("user",MODE_PRIVATE).edit();
                    sh.putString("userid",uid);
                    sh.putString("username",usname);
                    sh.apply();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(Login.this, HomePage.class);
                startActivity(i);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v==account)
        {
            Intent i=new Intent(Login.this,Registration.class);
            startActivity(i);
        }
        else if(v==skip) {

            Intent r = new Intent(Login.this, Skip.class);
            startActivity(r);
        }else if(v==btn)
        {
            username=uname.getText().toString();
            password=psw.getText().toString();
            SharedPreferences sh=getSharedPreferences("user",MODE_PRIVATE);
            String userid=sh.getString("userid",uid);

            LoginUser lg=new LoginUser();
            lg.execute(username,password);

            InsertLogin i=new InsertLogin();
            i.execute(userid);

            Toast.makeText(this, "userid=="+userid, Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.homepage_nav_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.homepage_nav_bar,menu);
//        return true;
//    }

}
