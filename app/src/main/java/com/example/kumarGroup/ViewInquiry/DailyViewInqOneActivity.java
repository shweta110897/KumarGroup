package com.example.kumarGroup.ViewInquiry;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kumarGroup.DailyViewInqModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyViewInqOneActivity extends AppCompatActivity {

    TextView txtDailyInqOneVI,txtDailyInqOneCountVI;
    LinearLayout lin_Main_InqDailyVI;

    SharedPreferences sp,sharedPreferences;
    String emp;

    ProgressDialog progressDialog;

    String state,stateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_view_inq_one);


        txtDailyInqOneVI=findViewById(R.id.txtDailyInqOneVI);
        txtDailyInqOneCountVI=findViewById(R.id.txtDailyInqOneCountVI);
        lin_Main_InqDailyVI=findViewById(R.id.lin_Main_InqDailyVI);

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        Log.d("EMP_ID", "onCreate: "+emp);

        state = getIntent().getStringExtra("state");
        stateId =getIntent().getStringExtra("stateId");


        sharedPreferences = getSharedPreferences("DateCurrent2_inq", MODE_PRIVATE);

        progressDialog= new ProgressDialog(DailyViewInqOneActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getDailyViewInq(stateId).enqueue(new Callback<DailyViewInqModel>() {
            @Override
            public void onResponse(@NotNull Call<DailyViewInqModel> call, @NotNull Response<DailyViewInqModel> response) {
                progressDialog.dismiss();
                assert response.body() != null;
                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(DailyViewInqOneActivity.this,"No Visit Available");
                }
                else{
                    txtDailyInqOneCountVI.setText(String.valueOf(response.body().getDetail().get(0).getCount()));

                    Log.d("DailyID", "onResponse: "+response.body().getDetail().get(0).getId());

                    lin_Main_InqDailyVI.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new  Intent(DailyViewInqOneActivity.this,
                                    MonthlyViewInqTwoDataActivity.class);
                            i.putExtra("catId_eid",response.body().getDetail().get(0).getId());
                            startActivity(i);
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NotNull Call<DailyViewInqModel> call, @NotNull Throwable t) {

            }
        });

    }
}