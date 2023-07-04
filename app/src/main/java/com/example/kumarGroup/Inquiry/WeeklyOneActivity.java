package com.example.kumarGroup.Inquiry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.WeeklyInquiryOneModel;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeeklyOneActivity extends AppCompatActivity {


    RecyclerView rclvWeeklyInquiryOne;

    SharedPreferences sp1;
    String emp;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_one);

        rclvWeeklyInquiryOne=findViewById(R.id.rclvWeeklyInquiryOne);
        rclvWeeklyInquiryOne.setHasFixedSize(true);
        rclvWeeklyInquiryOne.setLayoutManager(new LinearLayoutManager(this));


        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        progressDialog = new ProgressDialog(WeeklyOneActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getWeeklyInqOne(emp).enqueue(new Callback<WeeklyInquiryOneModel>() {
            @Override
            public void onResponse(@NotNull Call<WeeklyInquiryOneModel> call, @NotNull Response<WeeklyInquiryOneModel> response) {
                progressDialog.dismiss();
                assert response.body() != null;
                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(WeeklyOneActivity.this,"No Data Available");
                }
                else{
                    WeeklyInqOneAdapter adpater1 = new WeeklyInqOneAdapter(WeeklyOneActivity.this,response.body().getDetail());
                    rclvWeeklyInquiryOne.setAdapter(adpater1);
                }
            }

            @Override
            public void onFailure(@NotNull Call<WeeklyInquiryOneModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}