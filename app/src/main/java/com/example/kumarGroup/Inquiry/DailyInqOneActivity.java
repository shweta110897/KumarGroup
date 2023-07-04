package com.example.kumarGroup.Inquiry;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kumarGroup.DailyInqOneModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyInqOneActivity extends AppCompatActivity {

    TextView txtDailyInqOne,txtDailyInqOneCount;
    LinearLayout lin_Main_InqDaily;

    SharedPreferences sp,sharedPreferences;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_inq_one);

        txtDailyInqOne=findViewById(R.id.txtDailyInqOne);
        txtDailyInqOneCount=findViewById(R.id.txtDailyInqOneCount);
        lin_Main_InqDaily=findViewById(R.id.lin_Main_InqDaily);

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        Log.d("EMP_ID", "onCreate: "+emp);


        sharedPreferences = getSharedPreferences("DateCurrent2_inq", MODE_PRIVATE);

        progressDialog= new ProgressDialog(DailyInqOneActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getDailyInqOne(emp).enqueue(new Callback<DailyInqOneModel>() {
            @Override
            public void onResponse(@NotNull Call<DailyInqOneModel> call, @NotNull Response<DailyInqOneModel> response)
            {

                progressDialog.dismiss();
                assert response.body() != null;
                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(DailyInqOneActivity.this,"No Visit Available");
                    sharedPreferences.edit().putInt("CurrentDateOrNull1_inq",0).apply();
                    Log.d("TAG", "onCreate: check_log_here 4");

                }
                else{
                    Log.d("TAG", "onCreate: check_log_here 5");

                    sharedPreferences.edit().putInt("CurrentDateOrNull1_inq",1).apply();
                    txtDailyInqOneCount.setText(String.valueOf(response.body().getDetail().get(0).getCount()));

                    Log.d("DailyID", "onResponse: "+response.body().getDetail().get(0).getId());

                    lin_Main_InqDaily.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new  Intent(DailyInqOneActivity.this, MonthlyInqDataActivity.class);
                            i.putExtra("id",response.body().getDetail().get(0).getId());
                            startActivity(i);
                        }
                    });
                }

            }

            @Override
            public void onFailure(@NotNull Call<DailyInqOneModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });

    }
}