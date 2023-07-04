package com.example.kumarGroup.Workshop.Month;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.kumarGroup.MonthlyOneWorkShopModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonthWsOneActivity extends AppCompatActivity {


    RecyclerView rclvMonthWSOne;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_ws_one);

        rclvMonthWSOne=findViewById(R.id.rclvMonthWSOne);
        rclvMonthWSOne.setHasFixedSize(true);
        rclvMonthWSOne.setLayoutManager(new LinearLayoutManager(this));


        progressDialog= new ProgressDialog(MonthWsOneActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);



        WebService.getClient().getMonthlyWSOne().enqueue(new Callback<MonthlyOneWorkShopModel>() {
            @Override
            public void onResponse(@NotNull Call<MonthlyOneWorkShopModel> call,
                                   @NotNull Response<MonthlyOneWorkShopModel> response) {
                progressDialog.dismiss();
                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(MonthWsOneActivity.this,"No Data Available");
                }
                else{
                    MonthWsOneAdapter adpater1 = new MonthWsOneAdapter(MonthWsOneActivity.this,response.body().getDetail());
                    rclvMonthWSOne.setAdapter(adpater1);
                }
            }

            @Override
            public void onFailure(@NotNull Call<MonthlyOneWorkShopModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();

            }
        });

    }
}