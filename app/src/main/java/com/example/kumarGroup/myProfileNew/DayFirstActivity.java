package com.example.kumarGroup.myProfileNew;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kumarGroup.DayMyProfileModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DayFirstActivity extends AppCompatActivity {

    TextView txtDailyInqOne_myProfile,txtDailyInqOneCount_myProfile;
    LinearLayout lin_Main_InqDaily_myProfile;

    SharedPreferences sp,sharedPreferences;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_first);


        txtDailyInqOne_myProfile=findViewById(R.id.txtDailyInqOne_myProfile);
        txtDailyInqOneCount_myProfile=findViewById(R.id.txtDailyInqOneCount_myProfile);
        lin_Main_InqDaily_myProfile=findViewById(R.id.lin_Main_InqDaily_myProfile);

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        Log.d("EMP_ID", "onCreate: "+emp);


        progressDialog= new ProgressDialog(DayFirstActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getDayMyProfile(emp).enqueue(new Callback<DayMyProfileModel>() {
            @Override
            public void onResponse(@NotNull Call<DayMyProfileModel> call, @NotNull Response<DayMyProfileModel> response) {

                progressDialog.dismiss();
                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(DayFirstActivity.this,"No Visit Available");
                }
                else{
                    txtDailyInqOneCount_myProfile.setText(String.valueOf(response.body().getDetail().get(0).getCount()));

                    Log.d("DailyID", "onResponse: "+response.body().getDetail().get(0).getId());

                    lin_Main_InqDaily_myProfile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new  Intent(DayFirstActivity.this, MonthWeekDaySecondActivity.class);
                            i.putExtra("catId",response.body().getDetail().get(0).getId());
                            startActivity(i);
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NotNull Call<DayMyProfileModel> call, @NotNull Throwable t) {

            }
        });

    }
}