package com.example.kumarGroup.SSP.ViewAccountDatabank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.SSP_ViewAccountDataBankModel;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAccountDatabankActivity extends AppCompatActivity {


    RecyclerView Rclv_viewAccountDataBank;

    String date1,date2;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account_databank);


        getSupportActionBar().setTitle("View Account");


        Rclv_viewAccountDataBank=findViewById(R.id.Rclv_viewAccountDataBank);

        Rclv_viewAccountDataBank.setHasFixedSize(true);
        Rclv_viewAccountDataBank.setLayoutManager(new LinearLayoutManager(this));

        date1 = getIntent().getStringExtra("StartingDateOne");
        date2 = getIntent().getStringExtra("EndingDateTwo");



        progressDialog= new ProgressDialog(ViewAccountDatabankActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );


        WebService.getClient().getSSPViewAccDataBank(date1,date2).enqueue(new Callback<SSP_ViewAccountDataBankModel>() {
            @Override
            public void onResponse(@NotNull Call<SSP_ViewAccountDataBankModel> call,
                                   @NotNull Response<SSP_ViewAccountDataBankModel> response) {
                progressDialog.dismiss();
                if (response.body().getCat().size() == 0) {
                    Utils.showErrorToast(ViewAccountDatabankActivity.this,"No Data Found");

                } else {
                    ViewAccountDatabankAdapter adapter= new ViewAccountDatabankAdapter(ViewAccountDatabankActivity.this,
                            response.body().getCat());
                    Rclv_viewAccountDataBank.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(@NotNull Call<SSP_ViewAccountDataBankModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Log.d("onF", "onFailure: "+t.getCause());
                Toast.makeText(ViewAccountDatabankActivity.this,"server issue"+t.getCause(),Toast.LENGTH_LONG).show();

            }
        });

    }
}