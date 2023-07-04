package com.example.kumarGroup.myProfileNew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.MyProfileSecondAllDetailModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonthWeekDaySecondActivity extends AppCompatActivity {

    RecyclerView rclvMonthWeekDay;

    String catId;

    SharedPreferences sp1;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_week_day_second);

        catId= getIntent().getStringExtra("catId");

        rclvMonthWeekDay= findViewById(R.id.rclvMonthWeekDay);
        rclvMonthWeekDay.setHasFixedSize(true);
        rclvMonthWeekDay.setLayoutManager(new LinearLayoutManager(this));

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        progressDialog= new ProgressDialog(MonthWeekDaySecondActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getMyProfileSecond(catId, emp).enqueue(new Callback<MyProfileSecondAllDetailModel>() {
            @Override
            public void onResponse(@NotNull Call<MyProfileSecondAllDetailModel> call,
                                   @NotNull Response<MyProfileSecondAllDetailModel> response) {

                progressDialog.dismiss();
                assert response.body() != null;
                if(response.body().getCat().size()==0){
                    Utils.showErrorToast(MonthWeekDaySecondActivity.this,"No Visit Available");
                }
                else{
                    MonthWeekDaySecondAdapter monthlyInqDataDisplayAdapter = new
                            MonthWeekDaySecondAdapter(MonthWeekDaySecondActivity.this,
                            response.body().getCat());
                    rclvMonthWeekDay.setAdapter(monthlyInqDataDisplayAdapter);
                }

                //Toast.makeText(MonthWeekDaySecondActivity.this, "yed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NotNull Call<MyProfileSecondAllDetailModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}