package com.example.kumarGroup.Inquiry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kumarGroup.DealstageAK.DeliveryButtonActivity;
import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ViewInquiry.AgeingViewInqActivity;

public class InquiryMainActivity extends AppCompatActivity {

    TextView txtInquiryGeneralVisit,txtInquiryVMonthly,InqAgeing,txtInquiryVGenral,
            txtInquiryVWeekly,txtInquiryVDaily,txtInquiryDealstage,txtInquiryVillageList,viewInquiryIDMUnit,ViewInqPerformance;

    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8;
    public static boolean deal_stage_category_flag = false;
    public static boolean deal_stage_village_list = false;
    public static boolean deal_stage_IDMunit_flag= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry_main);

        getSupportActionBar().setTitle("My Inquiry");

        txtInquiryGeneralVisit=findViewById(R.id.txtInquiryGeneralVisit);
        txtInquiryVMonthly=findViewById(R.id.txtInquiryVMonthly);
        txtInquiryVWeekly=findViewById(R.id.txtInquiryVWeekly);
        txtInquiryVDaily=findViewById(R.id.txtInquiryVDaily);
        txtInquiryDealstage=findViewById(R.id.txtInquiryDealstage);
        txtInquiryVillageList=findViewById(R.id.txtInquiryVillageList);
        viewInquiryIDMUnit=findViewById(R.id.viewInquiryIDMUnit);
        ViewInqPerformance=findViewById(R.id.ViewInqPerformance);
        InqAgeing=findViewById(R.id.InqAgeing);
        txtInquiryVGenral=findViewById(R.id.txtInquiryVGenral);

        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        txt4=findViewById(R.id.txt4);
        txt5=findViewById(R.id.txt5);
//        txt6=findViewById(R.id.txt6);
//        txt7=findViewById(R.id.txt7);
//        txt8=findViewById(R.id.txt8);



        txtInquiryDealstage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.enable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
//                txt6.setBackgroundResource(R.drawable.disable_page);
//                txt7.setBackgroundResource(R.drawable.disable_page);
//                txt8.setBackgroundResource(R.drawable.disable_page);
                deal_stage_category_flag = true;
                deal_stage_village_list = false;
                deal_stage_IDMunit_flag = false;
                startActivity(new Intent(InquiryMainActivity.this, GeneralInquiryMainActivity.class));
            }
        });
        txtInquiryVillageList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.enable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
//                txt6.setBackgroundResource(R.drawable.enable_page);
//                txt7.setBackgroundResource(R.drawable.disable_page);
//                txt8.setBackgroundResource(R.drawable.disable_page);

                deal_stage_village_list = true;
                deal_stage_IDMunit_flag = false;
                deal_stage_category_flag = false;
                startActivity(new Intent(InquiryMainActivity.this, GeneralInquiryMainActivity.class));
            }
        });

        txtInquiryGeneralVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.enable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
//                txt6.setBackgroundResource(R.drawable.disable_page);
//                txt7.setBackgroundResource(R.drawable.disable_page);
//                txt8.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(InquiryMainActivity.this, GeneralInquiryMainActivity.class);
                startActivity(i);
            }
        });

        txtInquiryVGenral.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txt1.setBackgroundResource(R.drawable.disable_page);
                        txt2.setBackgroundResource(R.drawable.enable_page);
                        txt3.setBackgroundResource(R.drawable.disable_page);
                        txt4.setBackgroundResource(R.drawable.disable_page);
                        txt5.setBackgroundResource(R.drawable.disable_page);
        //                txt6.setBackgroundResource(R.drawable.disable_page);
        //                txt7.setBackgroundResource(R.drawable.disable_page);
        //                txt8.setBackgroundResource(R.drawable.disable_page);

                        Intent i = new Intent(InquiryMainActivity.this, InquiryMainGeneralActivity.class);
                        startActivity(i);
                    }
                });


        viewInquiryIDMUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
//                txt6.setBackgroundResource(R.drawable.disable_page);
//                txt7.setBackgroundResource(R.drawable.enable_page);
//                txt8.setBackgroundResource(R.drawable.disable_page);

                deal_stage_IDMunit_flag = true;
                deal_stage_village_list = false;
                deal_stage_category_flag = false;
                startActivity(new Intent(InquiryMainActivity.this, GeneralInquiryMainActivity.class));


                // Intent i = new Intent(InquiryMainActivity.this, .class);
                // startActivity(i);

            }
        });

        ViewInqPerformance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeliveryButtonActivity.perfomanceFlag = true;
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
//                txt6.setBackgroundResource(R.drawable.disable_page);
//                txt7.setBackgroundResource(R.drawable.disable_page);
//                txt8.setBackgroundResource(R.drawable.enable_page);


                startActivity(new Intent(InquiryMainActivity.this, DeliveryButtonActivity.class));

                // Intent i = new Intent(InquiryMainActivity.this, .class);
                // startActivity(i);

            }
        });

        InqAgeing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeliveryButtonActivity.perfomanceFlag = true;
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.enable_page);
//                txt6.setBackgroundResource(R.drawable.disable_page);
//                txt7.setBackgroundResource(R.drawable.enable_page);
//                txt8.setBackgroundResource(R.drawable.disable_page);

                FoDashbord.Common_Search_Check=true;
                startActivity(new Intent(InquiryMainActivity.this, AgeingViewInqActivity.class));

                // Intent i = new Intent(InquiryMainActivity.this, .class);
                // startActivity(i);

            }
        });

        
        
        

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        InquiryMainActivity.deal_stage_IDMunit_flag = false;
        InquiryMainActivity.deal_stage_village_list = false;
        InquiryMainActivity.deal_stage_category_flag = false;
    }
}