package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static com.example.demoapplication.R.layout.activity_ui_design;

public class Ui_design extends AppCompatActivity implements View.OnClickListener {

    CardView card,card2,card3,card4,card5,card6,card7,card8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_ui_design);

//        card=findViewById(R.id.id_card);
//        card2=findViewById(R.id.id_card2);
//        card3=findViewById(R.id.id_card3);
//        card4=findViewById(R.id.id_card4);
//        card5=findViewById(R.id.id_card5);
//        card6=findViewById(R.id.id_card6);
//        card7=findViewById(R.id.id_card7);
//        card8=findViewById(R.id.id_card8);
//
//        card.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent i=new Intent(Ui_design.this,Myprofile.class);
        startActivity(i);

    }
}
