package com.example.kumarGroup.ReportCollection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.DailyRCModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyRcActivity extends AppCompatActivity {


    TextView txtDailyRC,txtDailyRCCount;
    LinearLayout lin_Main_rc;

    SharedPreferences sp,sharedPreferences;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_rc);

        getSupportActionBar().setTitle("Daily Payment Collection");


        txtDailyRC=findViewById(R.id.txtDailyRC);
        txtDailyRCCount=findViewById(R.id.txtDailyRCCount);
        lin_Main_rc=findViewById(R.id.lin_Main_rc);

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        Log.d("EMP_ID", "onCreate: "+emp);

        sharedPreferences = getSharedPreferences("DateCurrent1", MODE_PRIVATE);


        progressDialog= new ProgressDialog(DailyRcActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getRcDaily().enqueue(new Callback<DailyRCModel>() {
            @Override
            public void onResponse(@NotNull Call<DailyRCModel> call, @NotNull Response<DailyRCModel> response) {
                progressDialog.dismiss();
                assert response.body() != null;
                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(DailyRcActivity.this,"No Visit Available");
                    sharedPreferences.edit().putInt("CurrentDateOrNull1",0).apply();
                }
                else{
                    sharedPreferences.edit().putInt("CurrentDateOrNull1",1).apply();
                    txtDailyRCCount.setText(String.valueOf(response.body().getDetail().get(0).getCount()));
                    Log.d("DailyID", "onResponse: "+response.body().getDetail().get(0).getId());

                    lin_Main_rc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new  Intent(DailyRcActivity.this,
                                    AllEntryMonthWeekDayActivity.class);
                            i.putExtra("id",response.body().getDetail().get(0).getId());
                            startActivity(i);
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NotNull Call<DailyRCModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DailyRcActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}