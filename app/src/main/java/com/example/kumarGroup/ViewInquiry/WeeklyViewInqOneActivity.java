package com.example.kumarGroup.ViewInquiry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.WeeklyViewInqModel;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeeklyViewInqOneActivity extends AppCompatActivity {

    RecyclerView rclvViewInquiryGeneraWeekly;

    ProgressDialog progressDialog;


    SharedPreferences sp1;
    String emp;

    String state,stateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_view_inq_one);

        rclvViewInquiryGeneraWeekly=findViewById(R.id.rclvViewInquiryGeneraWeekly);
        rclvViewInquiryGeneraWeekly.setHasFixedSize(true);
        rclvViewInquiryGeneraWeekly.setLayoutManager(new LinearLayoutManager(this));

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        state = getIntent().getStringExtra("state");
        stateId =getIntent().getStringExtra("stateId");


        progressDialog= new ProgressDialog(WeeklyViewInqOneActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getWeeklyViewInq(stateId).enqueue(new Callback<WeeklyViewInqModel>() {
            @Override
            public void onResponse(@NotNull Call<WeeklyViewInqModel> call, @NotNull Response<WeeklyViewInqModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;

                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(WeeklyViewInqOneActivity.this,"Data not Available");
                }
                else{
                    WeeklyViewInqOneAdapter adapter = new WeeklyViewInqOneAdapter(WeeklyViewInqOneActivity.this,
                            response.body().getDetail());
                    rclvViewInquiryGeneraWeekly.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<WeeklyViewInqModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });

    }
}