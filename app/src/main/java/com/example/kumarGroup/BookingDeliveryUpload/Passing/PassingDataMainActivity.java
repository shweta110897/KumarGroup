package com.example.kumarGroup.BookingDeliveryUpload.Passing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.PassingDataModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassingDataMainActivity extends AppCompatActivity {


    RecyclerView rclvPassingDataDisplay;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_data_main);

        getSupportActionBar().setTitle("Passing Data");


        rclvPassingDataDisplay = findViewById(R.id.rclvPassingDataDisplay);

        rclvPassingDataDisplay.setHasFixedSize(true);
        rclvPassingDataDisplay.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        progressDialog= new ProgressDialog(PassingDataMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getPassingData(emp).enqueue(new Callback<PassingDataModel>() {
            @Override
            public void onResponse(@NotNull Call<PassingDataModel> call, @NotNull Response<PassingDataModel> response) {
                progressDialog.dismiss();
                assert response.body() != null;
                if(response.body().getData().size() == 0){
                    Utils.showErrorToast(PassingDataMainActivity.this,"No Data Available");
                }
                else {
                    PassingDataAdapter adapter = new PassingDataAdapter(PassingDataMainActivity.this,
                            response.body().getData());
                    rclvPassingDataDisplay.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<PassingDataModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                // Toast.makeText(BookingUploadMainActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
                Utils.showErrorToast(PassingDataMainActivity.this,"No Data Available");
            }
        });

    }
}