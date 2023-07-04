package com.example.kumarGroup.myProfileNew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kumarGroup.ProfileVillage.CatlistActivity;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ViewProfile;

public class MyProfileMainActivity extends AppCompatActivity {

    TextView txtMyProfileGeneralVisit,txtMyProfileMonthly,
            txtMyProfileWeekly,txtMyProfileDaily,textView_My_VillageList;

    TextView txt1,txt2,txt3,txt4,txt5;

    public static boolean Village_list_My_Profile_Check = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_main);

        getSupportActionBar().setTitle("My Profile");

        txtMyProfileGeneralVisit=findViewById(R.id.txtMyProfileGeneralVisit);
        txtMyProfileMonthly=findViewById(R.id.txtMyProfileMonthly);
        txtMyProfileWeekly=findViewById(R.id.txtMyProfileWeekly);
        txtMyProfileDaily=findViewById(R.id.txtMyProfileDaily);
        textView_My_VillageList=findViewById(R.id.textView_My_VillageList);

        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        txt4=findViewById(R.id.txt4);


        txtMyProfileGeneralVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.enable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(MyProfileMainActivity.this, ViewProfile.class);
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

                Intent i = new Intent(MyProfileMainActivity.this, MonthlyFirstActivity.class);
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

                Intent i = new Intent(MyProfileMainActivity.this, WeeklyFirstActivity.class);
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

                Intent i = new Intent(MyProfileMainActivity.this, DayFirstActivity.class);
                startActivity(i);


            }
        });

        textView_My_VillageList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Village_list_My_Profile_Check = true;
                Intent intent = new Intent(MyProfileMainActivity.this, CatlistActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Village_list_My_Profile_Check = false;
    }
}