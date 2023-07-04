package com.example.kumarGroup.ViewInquiry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.kumarGroup.DealstageAK.DeliveryButtonActivity;
import com.example.kumarGroup.Inquiry.GeneralInquiryMainActivity;
import com.example.kumarGroup.R;

public class ViewInqAllCatActivity extends AppCompatActivity {

    TextView txtViewInquiryGeneralVisit,txtViewInquiryVMonthly,ViewInqPerformance,ViewInqAgeing,txtViewInquiryVGenral,
            txtViewInquiryVWeekly,txtViewInquiryVDaily,viewInquiryDealstage,viewInquiryVillageList,viewInquiryIDMUnit;

    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9;

    String state,stateId,stateId1;

    public static boolean deal_stage_viewINQ_flag = false;
    public static boolean deal_stage_Village_List = false;
    public static boolean deal_stage_IDMunit_flag= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inq_all_cat);


        getSupportActionBar().setTitle("View Inquiry");


        txtViewInquiryGeneralVisit=findViewById(R.id.txtViewInquiryGeneralVisit);
        txtViewInquiryVMonthly=findViewById(R.id.txtViewInquiryVMonthly);
        txtViewInquiryVWeekly=findViewById(R.id.txtViewInquiryVWeekly);
        txtViewInquiryVDaily=findViewById(R.id.txtViewInquiryVDaily);
        viewInquiryDealstage=findViewById(R.id.viewInquiryDealstage);
        viewInquiryVillageList=findViewById(R.id.viewInquiryVillageList);
        viewInquiryIDMUnit=findViewById(R.id.viewInquiryIDMUnit);
        ViewInqPerformance=findViewById(R.id.ViewInqPerformance);
        ViewInqAgeing=findViewById(R.id.ViewInqAgeing);
        txtViewInquiryVGenral=findViewById(R.id.txtViewInquiryVGenral);

        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        txt4=findViewById(R.id.txt4);
        txt5=findViewById(R.id.txt5);
        txt6=findViewById(R.id.txt6);
        txt7=findViewById(R.id.txt7);
        txt8=findViewById(R.id.txt8);
        txt9=findViewById(R.id.txt9);



        state = getIntent().getStringExtra("state");
        stateId =getIntent().getStringExtra("stateId");
        stateId1 =getIntent().getStringExtra("stateId1");
        Log.d("TAG", "onCreate: state :-"+state);


        viewInquiryDealstage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.enable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
//                txt7.setBackgroundResource(R.drawable.disable_page);
//                txt8.setBackgroundResource(R.drawable.disable_page);
//                txt9.setBackgroundResource(R.drawable.disable_page);
                deal_stage_viewINQ_flag = true;
                deal_stage_Village_List = false;
                deal_stage_IDMunit_flag= false;
                startActivity(new Intent(ViewInqAllCatActivity.this, GeneralInquiryMainActivity.class));

            }
        });

        viewInquiryVillageList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.enable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
//                txt7.setBackgroundResource(R.drawable.disable_page);
//                txt8.setBackgroundResource(R.drawable.disable_page);
//                txt9.setBackgroundResource(R.drawable.disable_page);

                deal_stage_Village_List = true;
                deal_stage_viewINQ_flag = false;
                deal_stage_IDMunit_flag= false;
                startActivity(new Intent(ViewInqAllCatActivity.this, GeneralInquiryMainActivity.class));

            }
        });

        txtViewInquiryGeneralVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.enable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
//                txt7.setBackgroundResource(R.drawable.disable_page);
//                txt8.setBackgroundResource(R.drawable.disable_page);
//                txt9.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(ViewInqAllCatActivity.this, GeneralViewInquiryOneActivity.class);
                i.putExtra("state",state);
                i.putExtra("stateId",stateId);
                i.putExtra("stateId1",stateId1);
                startActivity(i);
            }
        });

        txtViewInquiryVGenral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.enable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
//                txt7.setBackgroundResource(R.drawable.disable_page);
//                txt8.setBackgroundResource(R.drawable.disable_page);
//                txt9.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(ViewInqAllCatActivity.this, ViewInqGeneralCatActivity.class);
                i.putExtra("state",state);
                i.putExtra("stateId",stateId);
                i.putExtra("stateId1",stateId1);
                startActivity(i);
            }
        });


        txtViewInquiryVMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.enable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
//                txt7.setBackgroundResource(R.drawable.disable_page);
//                txt8.setBackgroundResource(R.drawable.disable_page);
//                txt9.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(ViewInqAllCatActivity.this, MonthlyViewInqOneActivity.class);
                i.putExtra("state",state);
                i.putExtra("stateId",stateId);
                i.putExtra("stateId1",stateId1);
                startActivity(i);

            }
        });


        txtViewInquiryVWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.enable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
//                txt7.setBackgroundResource(R.drawable.disable_page);
//                txt8.setBackgroundResource(R.drawable.disable_page);
//                txt9.setBackgroundResource(R.drawable.disable_page);


                Intent i = new Intent(ViewInqAllCatActivity.this, WeeklyViewInqOneActivity.class);
                i.putExtra("state",state);
                i.putExtra("stateId",stateId);
                i.putExtra("stateId1",stateId1);
                startActivity(i);

            }
        });


        txtViewInquiryVDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.enable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
//                txt7.setBackgroundResource(R.drawable.disable_page);
//                txt8.setBackgroundResource(R.drawable.disable_page);
//                txt9.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(ViewInqAllCatActivity.this, DailyViewInqOneActivity.class);
                i.putExtra("state",state);
                i.putExtra("stateId",stateId);
                i.putExtra("stateId1",stateId1);
                startActivity(i);

                // Intent i = new Intent(InquiryMainActivity.this, .class);
                // startActivity(i);

            }
        });


        viewInquiryIDMUnit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    txt1.setBackgroundResource(R.drawable.disable_page);
                    txt2.setBackgroundResource(R.drawable.disable_page);
                    txt3.setBackgroundResource(R.drawable.disable_page);
                    txt4.setBackgroundResource(R.drawable.disable_page);
                    txt5.setBackgroundResource(R.drawable.enable_page);
                    txt6.setBackgroundResource(R.drawable.disable_page);
//                    txt7.setBackgroundResource(R.drawable.enable_page);
//                    txt8.setBackgroundResource(R.drawable.disable_page);
//                    txt9.setBackgroundResource(R.drawable.disable_page);

                    deal_stage_IDMunit_flag = true;
                    deal_stage_viewINQ_flag = false;
                    deal_stage_Village_List = false;

                    startActivity(new Intent(ViewInqAllCatActivity.this, GeneralInquiryMainActivity.class));


                    // Intent i = new Intent(InquiryMainActivity.this, .class);
                    // startActivity(i);

            }
        });

        ViewInqPerformance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeliveryButtonActivity.perfomanceFlag_INQ = true;

                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
//                txt7.setBackgroundResource(R.drawable.disable_page);
//                txt8.setBackgroundResource(R.drawable.enable_page);
//                txt9.setBackgroundResource(R.drawable.disable_page);


                startActivity(new Intent(ViewInqAllCatActivity.this, DeliveryButtonActivity.class));


                // Intent i = new Intent(InquiryMainActivity.this, .class);
                // startActivity(i);

            }
        });

        

        ViewInqAgeing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeliveryButtonActivity.perfomanceFlag_INQ = true;

                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.enable_page);
//                txt7.setBackgroundResource(R.drawable.disable_page);
//                txt8.setBackgroundResource(R.drawable.disable_page);
//                txt9.setBackgroundResource(R.drawable.enable_page);


                startActivity(new Intent(ViewInqAllCatActivity.this, AgeingViewInqActivity.class));


                // Intent i = new Intent(InquiryMainActivity.this, .class);
                // startActivity(i);

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        deal_stage_Village_List = false;
        deal_stage_viewINQ_flag = false;
        deal_stage_IDMunit_flag = false;
    }
}