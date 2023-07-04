package com.example.kumarGroup.DeliveryReport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.kumarGroup.FirstServiceModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FIrstServiceDRActivity extends AppCompatActivity {

    RecyclerView rclv_firstService;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_irst_service_d_r);

        getSupportActionBar().setTitle("First Service");

        rclv_firstService= findViewById(R.id.rclv_firstService);

        rclv_firstService.setHasFixedSize(true);
        rclv_firstService.setLayoutManager(new LinearLayoutManager(this));


        progressDialog= new ProgressDialog(FIrstServiceDRActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);



        WebService.getClient().getFirstService().enqueue(new Callback<FirstServiceModel>() {
            @Override
            public void onResponse(@NotNull Call<FirstServiceModel> call,
                                   @NotNull Response<FirstServiceModel> response) {

                progressDialog.dismiss();

                FirstServiceAdapter adapter = new FirstServiceAdapter(FIrstServiceDRActivity.this,
                        response.body().getData());
                rclv_firstService.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NotNull Call<FirstServiceModel> call,
                                  @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}