package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class spare_parts_view_details extends AppCompatActivity implements View.OnClickListener {

    Button btn_purchase,btn_add;
    TextView spareparts_name,spareparts_type,company,spareparts_rate,car_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_parts_view_details);

        //spareparts_name=findViewById(R.id.)

        btn_purchase=findViewById(R.id.btn_purchase);
        btn_add=findViewById(R.id.btn_add);

        btn_purchase.setOnClickListener(this);
        btn_add.setOnClickListener(this);

        Intent i=getIntent();
        String carid=i.getStringExtra("carid");
        String sparepartsname=i.getStringExtra("spareparts_name");
        String showid=i.getStringExtra("showroomid");
        String type=i.getStringExtra("sparepartstype");
        String compid=i.getStringExtra("companyid");
        String cost=i.getStringExtra("sparepartscost");

    }

    @Override
    public void onClick(View v) {


//            Intent p= new Intent(spare_parts_view_details.this, SpareParts_orderNow.class);
//            startActivity(p);

            //Toast.makeText(this, "hai", Toast.LENGTH_SHORT).show();



    }
}
