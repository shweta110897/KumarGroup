package com.example.kumarGroup.MyProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.MonthViewProfileModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonthlyViewProfileActivity extends AppCompatActivity {


    RecyclerView rclvMonthViewProfile;

    SharedPreferences sp1,sharedPreferences;
    String emp;

    ProgressDialog progressDialog;

    String state,stateId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_view_profile);


        rclvMonthViewProfile= findViewById(R.id.rclvMonthViewProfile);
        rclvMonthViewProfile.setHasFixedSize(true);
        rclvMonthViewProfile.setLayoutManager(new LinearLayoutManager(this));


        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        state=getIntent().getStringExtra("state");
        stateId=getIntent().getStringExtra("stateId");

        progressDialog= new ProgressDialog(MonthlyViewProfileActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getMonthViewProfile(stateId).enqueue(new Callback<MonthViewProfileModel>() {
            @Override
            public void onResponse(@NotNull Call<MonthViewProfileModel> call,
                                   @NotNull Response<MonthViewProfileModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;

                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(MonthlyViewProfileActivity.this,"Data not Available");
                }
                else{
                    MonthlyViewProfileAdapter adapter = new MonthlyViewProfileAdapter(MonthlyViewProfileActivity.this,
                            response.body().getDetail());
                    rclvMonthViewProfile.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<MonthViewProfileModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}