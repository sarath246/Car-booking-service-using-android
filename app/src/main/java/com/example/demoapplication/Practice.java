package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.xml.transform.Result;

public class Practice extends AppCompatActivity implements View.OnClickListener {

    Button one,two,three,four,five,six,seven,eight,nine,zero,sum,diff,product,quocient,result,clear;
    TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        one=findViewById(R.id.btn_1);
        two=findViewById(R.id.btn_2);
        three=findViewById(R.id.btn_3);
        four=findViewById(R.id.btn_4);
        five=findViewById(R.id.btn_5);
        six=findViewById(R.id.btn_6);
        seven=findViewById(R.id.btn_7);
        eight=findViewById(R.id.btn_8);
        nine=findViewById(R.id.btn_9);
        zero=findViewById(R.id.btn_0);
        quocient=findViewById(R.id.btn_div);
        sum=findViewById(R.id.btn_add);
        product=findViewById(R.id.btn_mul);
        result=findViewById(R.id.btn_result);
        res=findViewById(R.id.tvresult);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        quocient.setOnClickListener(this);
        sum.setOnClickListener(this);
        product.setOnClickListener(this);
        result.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {



    }
}
