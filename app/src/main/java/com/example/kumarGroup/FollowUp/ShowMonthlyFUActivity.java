package com.example.kumarGroup.FollowUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.kumarGroup.R;
import com.example.kumarGroup.ShowMonthModel;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowMonthlyFUActivity extends AppCompatActivity {

    RecyclerView rclvDisplayMonth;

    ProgressDialog progressDialog;

    SharedPreferences sp1;
    String emp;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_monthly_f_u);

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        id = getIntent().getStringExtra("id");

        Log.d("idData", "onCreate: "+id+" "+emp);

        rclvDisplayMonth=findViewById(R.id.rclvDisplayMonth);

        rclvDisplayMonth.setHasFixedSize(true);
        rclvDisplayMonth.setLayoutManager(new LinearLayoutManager(this));

        progressDialog= new ProgressDialog(ShowMonthlyFUActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getShowMonthFollowUp(emp,id).enqueue(new Callback<ShowMonthModel>() {
            @Override
            public void onResponse(@NotNull Call<ShowMonthModel> call, @NotNull Response<ShowMonthModel> response)
            {
                progressDialog.dismiss();
                assert response.body() != null;
                if(response.body().getCat().size()==0){
                    Utils.showErrorToast(ShowMonthlyFUActivity.this,"Data not Available");
                }
                else{
                    DisplayMonthAdapter adpater1 = new DisplayMonthAdapter(ShowMonthlyFUActivity.this,response.body().getCat());
                    rclvDisplayMonth.setAdapter(adpater1);
                }


               /* DisplayMonthAdapter adpater1 = new DisplayMonthAdapter(ShowMonthlyFUActivity.this,response.body().getCat());
                rclvDisplayMonth.setAdapter(adpater1);*/
            }

            @Override
            public void onFailure(@NotNull Call<ShowMonthModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });


    }
}