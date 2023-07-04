package com.example.kumarGroup.DeliveryReport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.DeliveryReportModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DelievryReportMainActivity extends AppCompatActivity {



    RecyclerView DeliveryReport_RCLV;


    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delievry_report_main);


        DeliveryReport_RCLV=findViewById(R.id.DeliveryReport_RCLV);

        DeliveryReport_RCLV.setHasFixedSize(true);
        DeliveryReport_RCLV.setLayoutManager(new LinearLayoutManager(this));


        progressDialog= new ProgressDialog(DelievryReportMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getDeliveryReport().enqueue(new Callback<DeliveryReportModel>() {
            @Override
            public void onResponse(@NotNull Call<DeliveryReportModel> call, @NotNull Response<DeliveryReportModel> response) {

                progressDialog.dismiss();

                assert response.body() != null;
                if(response.body().getData().size()==0){
                    Utils.showErrorToast(DelievryReportMainActivity.this,"No Data Available");
                }
                else{
                    DeliveryReportMainAdpater adpater = new DeliveryReportMainAdpater(DelievryReportMainActivity.this,
                            response.body().getData());
                    DeliveryReport_RCLV.setAdapter(adpater);
                }

            }

            @Override
            public void onFailure(@NotNull Call<DeliveryReportModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}