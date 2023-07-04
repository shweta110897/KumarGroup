package com.example.kumarGroup.myProfileNew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.MyProfileMonthModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonthlyFirstActivity extends AppCompatActivity {

    RecyclerView rclvMonthlyFirst;

    SharedPreferences sp1,sharedPreferences;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_first);

        rclvMonthlyFirst=findViewById(R.id.rclvMonthlyFirst);
        rclvMonthlyFirst.setHasFixedSize(true);
        rclvMonthlyFirst.setLayoutManager(new LinearLayoutManager(this));

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");


        progressDialog= new ProgressDialog(MonthlyFirstActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getMyProfileMonth(emp).enqueue(new Callback<MyProfileMonthModel>() {
            @Override
            public void onResponse(@NotNull Call<MyProfileMonthModel> call,
                                   @NotNull Response<MyProfileMonthModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;
                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(MonthlyFirstActivity.this,"No Visit Available");


                }
                else{
                    MonthlyFirstAdapter monthlyInqDataDisplayAdapter = new MonthlyFirstAdapter(MonthlyFirstActivity.this,
                            response.body().getDetail());
                    rclvMonthlyFirst.setAdapter(monthlyInqDataDisplayAdapter);
                  //  getLastNumber();
                }
            }

            @Override
            public void onFailure(@NotNull Call<MyProfileMonthModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}