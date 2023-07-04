package com.example.kumarGroup.Workshop.Daily;

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

import com.example.kumarGroup.DailyWsOneModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.Workshop.Month.MonthWeekDayWsTwoActivity;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyWsOneActivity extends AppCompatActivity {


    TextView txtDailyWs,txtDailyWsCount;
    LinearLayout lin_Main_Ws;

    SharedPreferences sp,sharedPreferences;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_ws_one);


        txtDailyWs=findViewById(R.id.txtDailyWs);
        txtDailyWsCount=findViewById(R.id.txtDailyWsCount);
        lin_Main_Ws=findViewById(R.id.lin_Main_Ws);

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        Log.d("EMP_ID", "onCreate: "+emp);

        sharedPreferences = getSharedPreferences("DateCurrent_ws", MODE_PRIVATE);


        progressDialog= new ProgressDialog(DailyWsOneActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getDailyWSOne().enqueue(new Callback<DailyWsOneModel>() {
            @Override
            public void onResponse(@NotNull Call<DailyWsOneModel> call, @NotNull Response<DailyWsOneModel> response) {
                progressDialog.dismiss();
                assert response.body() != null;
                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(DailyWsOneActivity.this,"No Visit Available");
                    sharedPreferences.edit().putInt("CurrentDateOrNull_ws",0).apply();
                }
                else{
                    sharedPreferences.edit().putInt("CurrentDateOrNull_ws",1).apply();
                    txtDailyWsCount.setText(String.valueOf(response.body().getDetail().get(0).getCount()));
                    Log.d("DailyID", "onResponse: "+response.body().getDetail().get(0).getId());

                    lin_Main_Ws.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new  Intent(DailyWsOneActivity.this,
                                    MonthWeekDayWsTwoActivity.class);
                            i.putExtra("id",response.body().getDetail().get(0).getId());
                            startActivity(i);
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NotNull Call<DailyWsOneModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DailyWsOneActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}