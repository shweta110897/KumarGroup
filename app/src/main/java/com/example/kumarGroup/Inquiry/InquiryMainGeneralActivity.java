package com.example.kumarGroup.Inquiry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.kumarGroup.R;


public class InquiryMainGeneralActivity extends AppCompatActivity {

    TextView txtInquiryVMonthly,
            txtInquiryVWeekly,txtInquiryVDaily;


    String state,stateId,stateId1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry_main_general);

        getSupportActionBar().setTitle(" Schedule Inquiry");



        txtInquiryVMonthly=findViewById(R.id.txtInquiryVMonthly);
        txtInquiryVWeekly=findViewById(R.id.txtInquiryVWeekly);
        txtInquiryVDaily=findViewById(R.id.txtInquiryVDaily);



        state = getIntent().getStringExtra("state");
        stateId =getIntent().getStringExtra("stateId");
        stateId1 =getIntent().getStringExtra("stateId1");
        Log.d("TAG", "onCreate: state :-"+state);




        txtInquiryVMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(InquiryMainGeneralActivity.this, MonthlyInquiryActivity.class);
                i.putExtra("state",state);
                i.putExtra("stateId",stateId);
                i.putExtra("stateId1",stateId1);
                startActivity(i);

            }
        });


        txtInquiryVWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(InquiryMainGeneralActivity.this, WeeklyOneActivity.class);
                i.putExtra("state",state);
                i.putExtra("stateId",stateId);
                i.putExtra("stateId1",stateId1);
                startActivity(i);

            }
        });


        txtInquiryVDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent i = new Intent(InquiryMainGeneralActivity.this, DailyInqOneActivity.class);
                i.putExtra("state",state);
                i.putExtra("stateId",stateId);
                i.putExtra("stateId1",stateId1);
                startActivity(i);

                // Intent i = new Intent(InquiryMainActivity.this, .class);
                // startActivity(i);

            }
        });

    }
}