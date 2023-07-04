package com.example.kumarGroup.myProfileNew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.WeeklyMyProfileModel;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeeklyFirstActivity extends AppCompatActivity {


    RecyclerView rclvWeeklyFirst;
    SharedPreferences sp1,sharedPreferences;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_first);


        rclvWeeklyFirst=findViewById(R.id.rclvWeeklyFirst);
        rclvWeeklyFirst.setHasFixedSize(true);
        rclvWeeklyFirst.setLayoutManager(new LinearLayoutManager(this));

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");


        progressDialog= new ProgressDialog(WeeklyFirstActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getWeeklyMyProfile(emp).enqueue(new Callback<WeeklyMyProfileModel>() {
            @Override
            public void onResponse(@NotNull Call<WeeklyMyProfileModel> call,
                                   @NotNull Response<WeeklyMyProfileModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;
                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(WeeklyFirstActivity.this,"No Visit Available");


                }
                else{
                    WeeklyFirstAdapter monthlyInqDataDisplayAdapter = new WeeklyFirstAdapter(WeeklyFirstActivity.this,
                            response.body().getDetail());
                    rclvWeeklyFirst.setAdapter(monthlyInqDataDisplayAdapter);
                    //  getLastNumber();
                }
            }

            @Override
            public void onFailure(@NotNull Call<WeeklyMyProfileModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();

            }
        });

    }
}