package com.example.kumarGroup.SSP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.kumarGroup.R;
import com.example.kumarGroup.SSP.DataBank.AK.SSPTractorDoItak;
import com.example.kumarGroup.SSP.Request.RequestMainActivity;
import com.example.kumarGroup.SSP.ViewAccountDatabank.ViewAccountDisplayDateFilterActivity;
import com.example.kumarGroup.meetingRoomAK.MeetingRoomActivity;

public class SSPDashboardActivity extends AppCompatActivity {

    SharedPreferences sp;
    String emp_id;
    String emp_name;


    CardView ll_ViewAccountDataBank,ll_request;
    String module_name;
    CardView ll_ganral_visit,ll_meetingroom_ssp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_s_p_dashboard);


        sp = getSharedPreferences("Login", MODE_PRIVATE);

        emp_id = sp.getString("emp_id_SSP", "");
        emp_name = sp.getString("emp_name_SSP", "");


        ll_ViewAccountDataBank=findViewById(R.id.ll_ViewAccountDataBank);
        ll_request=findViewById(R.id.ll_request);
        ll_ganral_visit = findViewById(R.id.ll_ganral_visit);
        ll_meetingroom_ssp = findViewById(R.id.ll_meetingroom_ssp);

        ll_meetingroom_ssp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SSPDashboardActivity.this, MeetingRoomActivity.class)
                .putExtra("type","SSP")
                .putExtra("id",emp_id));
            }
        });

        ll_ViewAccountDataBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SSPDashboardActivity.this, ViewAccountDisplayDateFilterActivity.class);
                startActivity(i);
            }
        });


        ll_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SSPDashboardActivity.this, RequestMainActivity.class);
                startActivity(i);
            }
        });

        ll_ganral_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(SSPDashboardActivity.this, SSPTractorDoItak.class);
                startActivity(i);
            }
        });
    }
}