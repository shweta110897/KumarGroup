package com.example.kumarGroup.WalletOverTime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.kumarGroup.OverTimeWalletModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverTimeWalletDisplayActivity extends AppCompatActivity {

    String date1,date2;
    SharedPreferences sp;
    String emp;
    RecyclerView RclvDisplayOverTime;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_time_wallet_display);

        getSupportActionBar().setTitle("Over Time");

        RclvDisplayOverTime = findViewById(R.id.RclvDisplayOverTime);
        RclvDisplayOverTime.setHasFixedSize(true);
        RclvDisplayOverTime.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        date1 = getIntent().getStringExtra("StartingDateOne");
        date2 = getIntent().getStringExtra("EndingDateTwo");


        Log.d("EMP_ID", "onCreate: "+emp);

        progressDialog= new ProgressDialog(OverTimeWalletDisplayActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getOverTimeWallet(emp,date1,date2).enqueue(new Callback<OverTimeWalletModel>() {
            @Override
            public void onResponse(@NotNull Call<OverTimeWalletModel> call, @NotNull Response<OverTimeWalletModel> response) {
                progressDialog.dismiss();

                if(response.body().getOvertime().size()==0){
                    Utils.showErrorToast(OverTimeWalletDisplayActivity.this,"No Visit Available");
                }
                else{
                    OverTimeDisplayAdapter adapter = new OverTimeDisplayAdapter(OverTimeWalletDisplayActivity.this,
                            response.body().getOvertime());
                    RclvDisplayOverTime.setAdapter(adapter);
                }

              /*  OverTimeDisplayAdapter adapter = new OverTimeDisplayAdapter(OverTimeWalletDisplayActivity.this,
                        response.body().getOvertime());
                RclvDisplayOverTime.setAdapter(adapter);*/

                /* if(response.body().getCat().size()==0){
                    Utils.showErrorToast(VisitRCActivity.this,"No Visit Available");
                }
                else{
                    VisitRCAdapter adapter = new VisitRCAdapter(VisitRCActivity.this,response.body().getCat());
                    rclvRCVisit.setAdapter(adapter);
                }*/
            }

            @Override
            public void onFailure(@NotNull Call<OverTimeWalletModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}