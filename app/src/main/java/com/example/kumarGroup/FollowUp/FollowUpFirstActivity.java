package com.example.kumarGroup.FollowUp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.kumarGroup.R;


public class FollowUpFirstActivity extends AppCompatActivity {

    TextView txtFollowUpGeneralVisit,txtFollowUpTotalInquiry,txtFollowUpMonthlyInquiry,
            txtFollowUpWeeklyInquiry,txtFollowUpDailyInquiry;

    TextView txt1,txt2,txt3,txt4,txt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_up_first);
        getSupportActionBar().setTitle("Follow Up");

        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        txt4=findViewById(R.id.txt4);
      //  txt5=findViewById(R.id.txt5);

        txtFollowUpGeneralVisit=findViewById(R.id.txtFollowUpGeneralVisit);
     //txtFollowUpTotalInquiry=findViewById(R.id.txtFollowUpTotalInquiry);
        txtFollowUpMonthlyInquiry=findViewById(R.id.txtFollowUpMonthlyInquiry);
        txtFollowUpWeeklyInquiry=findViewById(R.id.txtFollowUpWeeklyInquiry);
        txtFollowUpDailyInquiry=findViewById(R.id.txtFollowUpDailyInquiry);


        txtFollowUpGeneralVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.enable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(FollowUpFirstActivity.this, FollowUpMainActivity.class);
                startActivity(i);
            }
        });

        txtFollowUpMonthlyInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.enable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(FollowUpFirstActivity.this, MonthlyInquiryFollowUpActivity.class);
                startActivity(i);

            }
        });


        txtFollowUpWeeklyInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.enable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(FollowUpFirstActivity.this, WeeklyFollowUpActivity.class);
                startActivity(i);

            }
        });

        txtFollowUpDailyInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.enable_page);

                Intent i = new Intent(FollowUpFirstActivity.this, DailyFollowUpActivity.class);
                startActivity(i);
            }
        });

    }
}