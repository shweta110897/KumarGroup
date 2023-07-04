package com.example.kumarGroup.ReportCollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.R;
import com.example.kumarGroup.RcMonthModel;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonthlyRCActivity extends AppCompatActivity {

    RecyclerView rclvMonthRC;

    SharedPreferences sp1;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_r_c);

        getSupportActionBar().setTitle("Monthly Payment Collection");

        rclvMonthRC=findViewById(R.id.rclvMonthRC);

        rclvMonthRC.setHasFixedSize(true);
        rclvMonthRC.setLayoutManager(new LinearLayoutManager(this));

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        progressDialog= new ProgressDialog(MonthlyRCActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


      //  WebService.getClient().getRcMonth(emp).enqueue(new Callback<RcMonthModel>() {
        WebService.getClient().getRcMonth().enqueue(new Callback<RcMonthModel>() {
            @Override
            public void onResponse(@NotNull Call<RcMonthModel> call, @NotNull Response<RcMonthModel> response) {
                progressDialog.dismiss();
                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(MonthlyRCActivity.this,"No Data Available");
                }
                else{
                    MonthlyRCAdapater adpater1 = new MonthlyRCAdapater(MonthlyRCActivity.this,response.body().getDetail());
                    rclvMonthRC.setAdapter(adpater1);
                }
            }

            @Override
            public void onFailure(@NotNull Call<RcMonthModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });

    }
}