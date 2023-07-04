package com.example.kumarGroup.ReportCollection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kumarGroup.New_new_count;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ReportCollection.ByVillageList.DropDownActivity;
import com.example.kumarGroup.ReportCollection.GeneralVisit.General_Visit_TypeActivity;
import com.example.kumarGroup.ReportCollection.NEW.Select_NEW_Type_Activity;
import com.example.kumarGroup.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportCollectionMainActivity extends AppCompatActivity {

    TextView txtRCGVGeneralVisit,txtRCGVMonthlyInquiry,txtRCGVWeeklyInquiry,txtRCGVDailyInquiry,
            txtRCGVDocCollection,txtRCGVillage,txtRCGNEW,txtRCGNEW1;

    TextView txt1,txt2,txt3,txt4,txt5;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_collection_main);

        getSupportActionBar().setTitle("Payment Collection");

        txtRCGVGeneralVisit=findViewById(R.id.txtRCGVGeneralVisit);
        txtRCGVMonthlyInquiry=findViewById(R.id.txtRCGVMonthlyInquiry);
        txtRCGVWeeklyInquiry=findViewById(R.id.txtRCGVWeeklyInquiry);
        txtRCGVDailyInquiry=findViewById(R.id.txtRCGVDailyInquiry);
        txtRCGVDocCollection=findViewById(R.id.txtRCGVDocCollection);
        txtRCGVillage=findViewById(R.id.txtRCGVillage);
        txtRCGNEW=findViewById(R.id.txtRCGNEW);
        txtRCGNEW1=findViewById(R.id.txtRCGNEW1);

        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        txt4=findViewById(R.id.txt4);
        //  txt5=findViewById(R.id.txt5);

        progressDialog= new ProgressDialog(ReportCollectionMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().New_new_count().enqueue(new Callback<New_new_count>() {
            @Override
            public void onResponse(Call<New_new_count> call, Response<New_new_count> response) {
                txtRCGNEW1.setText(response.body().getNew());
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<New_new_count> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

        txtRCGNEW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportCollectionMainActivity.this, Select_NEW_Type_Activity.class);
                startActivity(intent);
            }
        });


        txtRCGVillage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportCollectionMainActivity.this, DropDownActivity.class);
                startActivity(intent);
            }
        });

        txtRCGVGeneralVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.enable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);

//                Intent i = new Intent(ReportCollectionMainActivity.this,RCGVCategoryListActivity.class);
//                startActivity(i);

                Intent intent = new Intent(ReportCollectionMainActivity.this, General_Visit_TypeActivity.class);
                startActivity(intent);
            }
        });


        txtRCGVMonthlyInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.enable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(ReportCollectionMainActivity.this,MonthlyRCActivity.class);
                startActivity(i);

            }
        });


        txtRCGVWeeklyInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.enable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(ReportCollectionMainActivity.this,WeeklyRCActivity.class);
                startActivity(i);

            }
        });


        txtRCGVDailyInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.enable_page);

                Intent i = new Intent(ReportCollectionMainActivity.this,DailyRcActivity.class);
                startActivity(i);
                
            }
        });


        txtRCGVDocCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.enable_page);

                Intent i = new Intent(ReportCollectionMainActivity.this,DocumentCollectionMainActivity.class);
                startActivity(i);
            }
        });

    }
}