package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Advertisement extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);

        img=findViewById(R.id.id_img);




    }
     public class Image extends AsyncTask<String,String,String>{

         @Override
         protected String doInBackground(String... strings) {
             WebServiceCaller wc=new WebServiceCaller();
             wc.setSoapObject("Image");
             wc.callWebService();

             return wc.getResponse();
         }

         @Override
         protected void onPostExecute(String s) {
             super.onPostExecute(s);
             Toast.makeText(Advertisement.this, "images"+s, Toast.LENGTH_SHORT).show();

             try {

                 JSONArray j=new JSONArray(s);

                 JSONObject obj=j.getJSONObject(0);

                 String images=obj.getString("advert_image");

                 String path="http://192.168.225.136:8084/Project_carpurchase/Company/CarDetails_photos"+images;
                 Picasso.get().load(path).into(img);

             } catch (JSONException e) {
                 e.printStackTrace();
             }

         }
     }
}
