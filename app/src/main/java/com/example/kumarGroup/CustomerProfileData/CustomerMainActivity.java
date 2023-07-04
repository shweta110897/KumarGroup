package com.example.kumarGroup.CustomerProfileData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kumarGroup.Maintenance.MaintananceMainActivity;
import com.example.kumarGroup.R;

public class CustomerMainActivity extends AppCompatActivity {

    TextView tv_CustomerProfile_add,tv_CustomerProfile_View,tv_CustomerProfile_Edit,
            tv_CustomerProfile_CategoryPermission,tv_ComplainBox,tv_DocumentBox,tv_Maintenance;

    TextView txt1,txt2,txt3,txt4,txt5,txt6;

    SharedPreferences sp1;
    String emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_customer_main);

        getSupportActionBar().setTitle("Maintenance");

        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        txt4=findViewById(R.id.txt4);
        txt5=findViewById(R.id.txt5);
        txt6=findViewById(R.id.txt6);

        tv_CustomerProfile_add= findViewById(R.id.tv_CustomerProfile_add);
        tv_CustomerProfile_View= findViewById(R.id.tv_CustomerProfile_View);
        tv_CustomerProfile_Edit= findViewById(R.id.tv_CustomerProfile_Edit);
        tv_ComplainBox= findViewById(R.id.tv_ComplainBox);
        tv_DocumentBox= findViewById(R.id.tv_DocumentBox);
        tv_CustomerProfile_CategoryPermission= findViewById(R.id.tv_CustomerProfile_CategoryPermission);
        tv_Maintenance= findViewById(R.id.tv_Maintenance);


        sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp1.getString("emp_id", "");


        tv_Maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(CustomerMainActivity.this, MaintananceMainActivity.class);
                Intent i = new Intent(CustomerMainActivity.this, MaintananceMainActivity.class);
                startActivity(i);
            }
        });


        tv_CustomerProfile_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.enable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(CustomerMainActivity.this,AddCutomerActivity.class);
                startActivity(i);
            }
        });


        tv_CustomerProfile_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.enable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);


                Intent i = new Intent(CustomerMainActivity.this,ViewMobileAddActivity.class);
                startActivity(i);
            }
        });



        tv_CustomerProfile_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.enable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(CustomerMainActivity.this,EditProfileMainActivity.class);
                startActivity(i);

            }
        });


        tv_CustomerProfile_CategoryPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.enable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(CustomerMainActivity.this, CategoryPermissionMainActivity.class);
                startActivity(i);

            }
        });


        tv_ComplainBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.enable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(CustomerMainActivity.this, ComplainBoxMainActivity.class);
                startActivity(i);

            }
        });


        tv_DocumentBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.enable_page);

                Intent i = new Intent(CustomerMainActivity.this, DocumentBoxMainActivity.class);
                startActivity(i);
            }
        });



    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}