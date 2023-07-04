package com.example.kumarGroup.MyProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kumarGroup.ProfileVillage.CatlistActivity;
import com.example.kumarGroup.R;

public class FourButtonMainActivity extends AppCompatActivity {



    TextView txtMyProfileGeneralVisit,txtMyProfileMonthly,
            txtMyProfileWeekly,txtMyProfileDaily,textView_VillageList;

    TextView txt1,txt2,txt3,txt4,txt5;

    String state,stateId;

    public static boolean Village_list_View_Profile_Check = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_button_main);

        getSupportActionBar().setTitle("View Profile");

        state=getIntent().getStringExtra("state");
        stateId=getIntent().getStringExtra("stateId");

        txtMyProfileGeneralVisit=findViewById(R.id.txtMyProfileGeneralVisit);
        txtMyProfileMonthly=findViewById(R.id.txtMyProfileMonthly);
        txtMyProfileWeekly=findViewById(R.id.txtMyProfileWeekly);
        txtMyProfileDaily=findViewById(R.id.txtMyProfileDaily);
        textView_VillageList=findViewById(R.id.textView_VillageList);

        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        txt4=findViewById(R.id.txt4);

        textView_VillageList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Village_list_View_Profile_Check = true;
                Intent intent = new Intent(FourButtonMainActivity.this, CatlistActivity.class);
                startActivity(intent);
            }
        });


        txtMyProfileGeneralVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.enable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(FourButtonMainActivity.this, CategoryMyProfileOneActivity.class);
                i.putExtra("state",state);
                i.putExtra("stateId",stateId);
                startActivity(i);
            }
        });


        txtMyProfileMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.enable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(FourButtonMainActivity.this, MonthlyViewProfileActivity.class);
                i.putExtra("state",state);
                i.putExtra("stateId",stateId);
                startActivity(i);
            }
        });


        txtMyProfileWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.enable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(FourButtonMainActivity.this, WeekViewProfileActivity.class);
                i.putExtra("state",state);
                i.putExtra("stateId",stateId);
                startActivity(i);

            }
        });


        txtMyProfileDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.enable_page);

                Intent i = new Intent(FourButtonMainActivity.this, DailyViewProfileActivity.class);
                i.putExtra("state",state);
                i.putExtra("stateId",stateId);
                startActivity(i);

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Village_list_View_Profile_Check = false;
    }
}