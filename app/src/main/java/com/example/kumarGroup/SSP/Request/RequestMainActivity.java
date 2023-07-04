package com.example.kumarGroup.SSP.Request;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kumarGroup.R;

public class RequestMainActivity extends AppCompatActivity {


    TextView tv_SSP_add,tv_SSP_GeneralList;


    TextView txt1,txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_main);

        getSupportActionBar().setTitle("Request");

        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);


        tv_SSP_add=findViewById(R.id.tv_SSP_add);
        tv_SSP_GeneralList=findViewById(R.id.tv_SSP_GeneralList);


        tv_SSP_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.enable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);


                Intent i = new Intent(RequestMainActivity.this,AddRequestMainActivity.class);
                startActivity(i);
            }
        });




        tv_SSP_GeneralList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.enable_page);


                Intent i = new Intent(RequestMainActivity.this,GanerallistMainActivity.class);
                startActivity(i);
            }
        });


    }
}