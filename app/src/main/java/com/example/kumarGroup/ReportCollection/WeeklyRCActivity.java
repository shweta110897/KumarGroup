package com.example.kumarGroup.ReportCollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.R;
import com.example.kumarGroup.RcWeeklyModel;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeeklyRCActivity extends AppCompatActivity {

    RecyclerView rclvWeeklyRC;

    SharedPreferences sp1;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_r_c);

        getSupportActionBar().setTitle("Weekly Payment Collection");


        rclvWeeklyRC=findViewById(R.id.rclvWeeklyRC);
        rclvWeeklyRC.setHasFixedSize(true);

        rclvWeeklyRC.setLayoutManager(new LinearLayoutManager(this));

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        progressDialog= new ProgressDialog(WeeklyRCActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);



        WebService.getClient().getRcWeekly().enqueue(new Callback<RcWeeklyModel>() {
            @Override
            public void onResponse(@NotNull Call<RcWeeklyModel> call, @NotNull Response<RcWeeklyModel> response) {
                progressDialog.dismiss();
                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(WeeklyRCActivity.this,"No Data Available");
                }
                else{
                    WeeklyRCAdapter adpater1 = new WeeklyRCAdapter(WeeklyRCActivity.this,response.body().getDetail());
                    rclvWeeklyRC.setAdapter(adpater1);
                }
            }

            @Override
            public void onFailure(@NotNull Call<RcWeeklyModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}