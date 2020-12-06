package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Car_booking extends AppCompatActivity implements View.OnClickListener {

    ImageView Car_img;
    RadioButton red,blue,black,white;
    TextView car_price,car_name,showroom_name;
    Button btn_book;
    String userid,carid,showroomid,showid,color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_booking);

        SharedPreferences sh=getSharedPreferences("user",MODE_PRIVATE);
       userid=sh.getString("userid","");


        Car_img=findViewById(R.id.Car_img);
        red=findViewById(R.id.red);
        blue=findViewById(R.id.blue);
        black=findViewById(R.id.black);
        white=findViewById(R.id.white);
        car_price=findViewById(R.id.car_price);
        btn_book=findViewById(R.id.btn_book);
        car_name=findViewById(R.id.car_name);
        showroom_name=findViewById(R.id.showroom_name);

        btn_book.setOnClickListener(this);

        Intent i=getIntent();
        String carphoto=i.getStringExtra("carphoto");
        String carprice=i.getStringExtra("carprice");
        String carid=i.getStringExtra("carid");
        String showid=i.getStringExtra("showid");

        String path="http://192.168.225.136:8084/Project_carpurchase/Company/CarDetails_photos"+carphoto;
        Picasso.get().load(path).into(Car_img);

        car_price.setText(carprice);
        car_name.setText(carid);
        showroom_name.setText(showid);
        Car_img.setImageDrawable(Drawable.createFromPath(path));

        Toast.makeText(this, "carid"+carid, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "showid"+showid, Toast.LENGTH_SHORT).show();
    }
    public class CarBooking extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller ws=new WebServiceCaller();
            ws.setSoapObject("CarBooking");

            ws.addProperty("carprice",strings[0]);
            ws.addProperty("carid",strings[1]);
            ws.addProperty("showroomid",strings[2]);
            ws.addProperty("color",strings[3]);
            ws.addProperty("userid",strings[4]);
            ws.callWebService();

            return ws.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("ww",s);
            Toast.makeText(Car_booking.this, s, Toast.LENGTH_SHORT).show();

        }
    }
    public void getcolor(View view){


        if(view==red){

            color=red.getText().toString();
            Toast.makeText(this, "red", Toast.LENGTH_SHORT).show();

        }else if (view==blue){

            color=blue.getText().toString();
            Toast.makeText(this, "blue", Toast.LENGTH_SHORT).show();

        }else if (view==black){

            color=black.getText().toString();
            Toast.makeText(this, "black", Toast.LENGTH_SHORT).show();

        }else {

            color=white.getText().toString();
            Toast.makeText(this, "white", Toast.LENGTH_SHORT).show();

        }

    }


    @Override
    public void onClick(View v) {

        String price=car_price.getText().toString();
        String carid=car_name.getText().toString();
        String showid=showroom_name.getText().toString();
        Toast.makeText(this, "showroomid==="+showid, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "Color=="+color, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "Userid=="+userid, Toast.LENGTH_SHORT).show();

        CarBooking cb=new CarBooking();
        cb.execute(price,carid,showid,color,userid);

    }
}
