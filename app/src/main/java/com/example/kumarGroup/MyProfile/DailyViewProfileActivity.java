package com.example.kumarGroup.MyProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kumarGroup.DailyViewProfileModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.myProfileNew.MonthWeekDaySecondActivity;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyViewProfileActivity extends AppCompatActivity
{
    TextView txtDailyInqOne_myProfile,txtDailyInqOneCount_myProfile;
    LinearLayout lin_Main_InqDaily_myProfile;

    SharedPreferences sp,sharedPreferences;
    String emp;

    ProgressDialog progressDialog;


    String state,stateId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_view_profile);

        txtDailyInqOne_myProfile=findViewById(R.id.txtDailyInqOne_myProfile);
        txtDailyInqOneCount_myProfile=findViewById(R.id.txtDailyInqOneCount_myProfile);
        lin_Main_InqDaily_myProfile=findViewById(R.id.lin_Main_InqDaily_myProfile);

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        Log.d("EMP_ID", "onCreate: "+emp);

        state=getIntent().getStringExtra("state");
        stateId=getIntent().getStringExtra("stateId");


        progressDialog= new ProgressDialog(DailyViewProfileActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);



        WebService.getClient().getDailyViewProfile(stateId).enqueue(new Callback<DailyViewProfileModel>() {
            @Override
            public void onResponse(@NotNull Call<DailyViewProfileModel> call,
                                   @NotNull Response<DailyViewProfileModel> response) {
                progressDialog.dismiss();
                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(DailyViewProfileActivity.this,"No Visit Available");
                }
                else{
                    txtDailyInqOneCount_myProfile.setText(String.valueOf(response.body().getDetail().get(0).getCount()));

                    Log.d("DailyID", "onResponse: "+response.body().getDetail().get(0).getId());

                    lin_Main_InqDaily_myProfile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new  Intent(DailyViewProfileActivity.this, MonthWeekDaySecondActivity.class);
                            i.putExtra("catId",response.body().getDetail().get(0).getId());
                            startActivity(i);
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NotNull Call<DailyViewProfileModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}