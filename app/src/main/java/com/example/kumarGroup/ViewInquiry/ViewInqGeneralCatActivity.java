package com.example.kumarGroup.ViewInquiry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.kumarGroup.R;

public class ViewInqGeneralCatActivity extends AppCompatActivity {

    TextView txtViewInquiryVMonthly,
            txtViewInquiryVWeekly,txtViewInquiryVDaily;


    String state,stateId,stateId1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inq_general_cat);

        getSupportActionBar().setTitle("View Schedule Inquiry");


      
        txtViewInquiryVMonthly=findViewById(R.id.txtViewInquiryVMonthly);
        txtViewInquiryVWeekly=findViewById(R.id.txtViewInquiryVWeekly);
        txtViewInquiryVDaily=findViewById(R.id.txtViewInquiryVDaily);
       


        state = getIntent().getStringExtra("state");
        stateId =getIntent().getStringExtra("stateId");
        stateId1 =getIntent().getStringExtra("stateId1");
        Log.d("TAG", "onCreate: state :-"+state);




        txtViewInquiryVMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              

                Intent i = new Intent(ViewInqGeneralCatActivity.this, MonthlyViewInqOneActivity.class);
                i.putExtra("state",state);
                i.putExtra("stateId",stateId);
                i.putExtra("stateId1",stateId1);
                startActivity(i);

            }
        });


        txtViewInquiryVWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(ViewInqGeneralCatActivity.this, WeeklyViewInqOneActivity.class);
                i.putExtra("state",state);
                i.putExtra("stateId",stateId);
                i.putExtra("stateId1",stateId1);
                startActivity(i);

            }
        });


        txtViewInquiryVDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              

                Intent i = new Intent(ViewInqGeneralCatActivity.this, DailyViewInqOneActivity.class);
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