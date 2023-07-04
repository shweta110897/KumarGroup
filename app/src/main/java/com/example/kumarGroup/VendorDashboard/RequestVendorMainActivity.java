package com.example.kumarGroup.VendorDashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kumarGroup.R;

public class RequestVendorMainActivity extends AppCompatActivity {


    TextView tv_vendor_GeneralList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_vendor_main);

        getSupportActionBar().setTitle("Request Vendor");


        tv_vendor_GeneralList= findViewById(R.id.tv_vendor_GeneralList);


        tv_vendor_GeneralList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(RequestVendorMainActivity.this,ReqVenGaneralListActivity.class);
                startActivity(i);
            }
        });

    }
}