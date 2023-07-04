package com.example.kumarGroup.MyProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.WeeklyViewProfile;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeekViewProfileActivity extends AppCompatActivity {

    RecyclerView rclv_WeekViewProfile;

    SharedPreferences sp1,sharedPreferences;
    String emp;

    ProgressDialog progressDialog;

    String state,stateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view_profile);

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        state=getIntent().getStringExtra("state");
        stateId=getIntent().getStringExtra("stateId");





        rclv_WeekViewProfile=findViewById(R.id.rclv_WeekViewProfile);
        rclv_WeekViewProfile.setHasFixedSize(true);
        rclv_WeekViewProfile.setLayoutManager(new LinearLayoutManager(this));

        progressDialog= new ProgressDialog(WeekViewProfileActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getWeeklyViewProfile(stateId).enqueue(new Callback<WeeklyViewProfile>() {
            @Override
            public void onResponse(@NotNull Call<WeeklyViewProfile> call,
                                   @NotNull Response<WeeklyViewProfile> response) {


                progressDialog.dismiss();

                assert response.body() != null;

                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(WeekViewProfileActivity.this,"Data not Available");
                }
                else{
                    WeekViewProfileAdapter adapter = new WeekViewProfileAdapter(WeekViewProfileActivity.this,
                            response.body().getDetail());
                    rclv_WeekViewProfile.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(@NotNull Call<WeeklyViewProfile> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}