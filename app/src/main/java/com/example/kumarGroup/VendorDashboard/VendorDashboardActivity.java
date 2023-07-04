package com.example.kumarGroup.VendorDashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.kumarGroup.R;
import com.example.kumarGroup.SmartCard.AddSmartCardFirstActivity;
import com.example.kumarGroup.meetingRoomAK.MeetingRoomActivity;

public class VendorDashboardActivity extends AppCompatActivity {

    SharedPreferences sp;
    String emp_id;
    String emp_name;

    CardView ll_AddSmartCard,ll_request_vendor,ll_meetingroom_vendor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_dashboard);

        sp = getSharedPreferences("Login", MODE_PRIVATE);

        emp_id = sp.getString("emp_id_vendor", "");
        emp_name = sp.getString("emp_name_vendor", "");

       // emp_name = getIntent().getStringExtra("emp_name_vendor");


        ll_AddSmartCard= findViewById(R.id.ll_AddSmartCard);
        ll_request_vendor= findViewById(R.id.ll_request_vendor);
        ll_meetingroom_vendor= findViewById(R.id.ll_meetingroom_vendor);


      //  Toast.makeText(this, ""+emp_id+" "+emp_name, Toast.LENGTH_SHORT).show();


        ll_meetingroom_vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VendorDashboardActivity.this, MeetingRoomActivity.class)
                        .putExtra("type","VENDOR")
                        .putExtra("id",emp_id));
            }
        });

        ll_AddSmartCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(VendorDashboardActivity.this, AddSmartCardFirstActivity.class);
                startActivity(i);

            }
        });



        ll_request_vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VendorDashboardActivity.this, RequestVendorMainActivity.class);
                startActivity(i);
            }
        });

    }
}