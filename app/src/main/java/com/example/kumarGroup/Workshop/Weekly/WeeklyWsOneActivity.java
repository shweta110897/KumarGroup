package com.example.kumarGroup.Workshop.Weekly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.WeekOneWsModel;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeeklyWsOneActivity extends AppCompatActivity {


    RecyclerView rclvWeekWsOne;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_ws_one);


        rclvWeekWsOne=findViewById(R.id.rclvWeekWsOne);
        rclvWeekWsOne.setHasFixedSize(true);
        rclvWeekWsOne.setLayoutManager(new LinearLayoutManager(this));

        progressDialog= new ProgressDialog(WeeklyWsOneActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getWeekWsOne().enqueue(new Callback<WeekOneWsModel>() {
            @Override
            public void onResponse(@NotNull Call<WeekOneWsModel> call,
                                   @NotNull Response<WeekOneWsModel> response) {
                progressDialog.dismiss();
                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(WeeklyWsOneActivity.this,"No Data Available");
                }
                else{
                    WeekWsOneAdapter adpater1 = new WeekWsOneAdapter(WeeklyWsOneActivity.this,
                            response.body().getDetail());
                    rclvWeekWsOne.setAdapter(adpater1);
                }
            }

            @Override
            public void onFailure(@NotNull Call<WeekOneWsModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Utils.showErrorToast(WeeklyWsOneActivity.this,"No Data Available..");
               // Toast.makeText(WeeklyWsOneActivity.this,""+t.getCause(),Toast.LENGTH_LONG).show();
            }
        });
    }
}