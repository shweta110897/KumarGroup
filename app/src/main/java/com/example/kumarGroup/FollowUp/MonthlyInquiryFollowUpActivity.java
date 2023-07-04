package com.example.kumarGroup.FollowUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.MonthFollowUpModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonthlyInquiryFollowUpActivity extends AppCompatActivity {

    RecyclerView rclvMonthFollowUp;
    ArrayList<String> MonthName = new ArrayList<>();

    SharedPreferences sp1;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_inquiry_follow_up);
        getSupportActionBar().setTitle("Monthly Inquiry");

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        MonthName.add("January");
        MonthName.add("February");
        MonthName.add("March");
        MonthName.add("April");
        MonthName.add("May");
        MonthName.add("June");
        MonthName.add("July");
        MonthName.add("August");
        MonthName.add("September");
        MonthName.add("October");
        MonthName.add("November");
        MonthName.add("December");

        rclvMonthFollowUp=findViewById(R.id.rclvMonthFollowUp);

        rclvMonthFollowUp.setHasFixedSize(true);
        rclvMonthFollowUp.setLayoutManager(new LinearLayoutManager(this));

        progressDialog= new ProgressDialog(MonthlyInquiryFollowUpActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getMonthFollowUp(emp).enqueue(new Callback<MonthFollowUpModel>() {
            @Override
            public void onResponse(Call<MonthFollowUpModel> call, Response<MonthFollowUpModel> response) {
                progressDialog.dismiss();
                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(MonthlyInquiryFollowUpActivity.this,"No Data Available");
                }
                else{
                    MonthFollowUpAdpater adpater1 = new MonthFollowUpAdpater(MonthlyInquiryFollowUpActivity.this,MonthName,response.body().getDetail());
                    rclvMonthFollowUp.setAdapter(adpater1);
                }

             //   MonthFollowUpAdpater adpater1 = new MonthFollowUpAdpater(MonthlyInquiryFollowUpActivity.this,MonthName,response.body().getDetail());
             //   rclvMonthFollowUp.setAdapter(adpater1);
            }

            @Override
            public void onFailure(Call<MonthFollowUpModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });


    }
}