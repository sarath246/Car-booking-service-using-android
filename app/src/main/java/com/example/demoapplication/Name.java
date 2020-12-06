package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Name extends AppCompatActivity implements View.OnClickListener {

    EditText fname,lname;
    Button merge;
    TextView result;
    String Fname,Lname,Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        fname=findViewById(R.id.etfname);
        lname=findViewById(R.id.etLname);
        merge=findViewById(R.id.btn);
        result=findViewById(R.id.txt_result);

        merge.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Fname=fname.getText().toString();
        Lname=lname.getText().toString();

        Result= Fname+"  "+Lname;

        result.setText(Result);

    }
}
