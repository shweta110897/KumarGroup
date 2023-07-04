package com.example.kumarGroup.BookingDeliveryUpload.Delivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.DeliveryDataDisplayModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryMainActivity extends AppCompatActivity {

    RecyclerView rclvDeliveryData;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_main);

        getSupportActionBar().setTitle("Delivery Data");

        rclvDeliveryData = findViewById(R.id.rclvDeliveryData);

        rclvDeliveryData.setHasFixedSize(true);
        rclvDeliveryData.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        progressDialog= new ProgressDialog(DeliveryMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getDeliveryDataDisplay(emp).enqueue(new Callback<DeliveryDataDisplayModel>() {
            @Override
            public void onResponse(Call<DeliveryDataDisplayModel> call, Response<DeliveryDataDisplayModel> response) {
                progressDialog.dismiss();
                if(response.body().getData().size() == 0){
                    Utils.showErrorToast(DeliveryMainActivity.this,"No Data Available");
                }
                else {
                    DeliveryMainAdapter adapter = new DeliveryMainAdapter(DeliveryMainActivity.this,
                            response.body().getData());
                    rclvDeliveryData.setAdapter(adapter);

                    /*LoanDataAdapter adapter = new LoanDataAdapter(DeliveryMainActivity.this,
                    response.body().getData());
                    rclvDeliveryData.setAdapter(adapter);*/
                }
            }

            @Override
            public void onFailure(Call<DeliveryDataDisplayModel> call, Throwable t) {
                progressDialog.dismiss();
                // Toast.makeText(BookingUploadMainActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
                Utils.showErrorToast(DeliveryMainActivity.this,"No Data Available");
            }
        });

    }
}