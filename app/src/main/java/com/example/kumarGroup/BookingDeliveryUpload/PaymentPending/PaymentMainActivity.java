package com.example.kumarGroup.BookingDeliveryUpload.PaymentPending;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.Payment_PayPenModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentMainActivity extends AppCompatActivity {

    RecyclerView rclvPayment;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    String idBookingUpload,MobileNo;

    String idPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_main);

        getSupportActionBar().setTitle("Payment");

        rclvPayment = findViewById(R.id.rclvPayment);

        rclvPayment.setHasFixedSize(true);
        rclvPayment.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        idPayment = getIntent().getStringExtra("idPayment");
        MobileNo = getIntent().getStringExtra("MobileNo");

        progressDialog= new ProgressDialog(PaymentMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getPayment(idPayment,emp,MobileNo).enqueue(new Callback<Payment_PayPenModel>() {
            @Override
            public void onResponse(@NotNull Call<Payment_PayPenModel> call, @NotNull Response<Payment_PayPenModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;

                if(response.body().getCat().size() == 0){
                    Utils.showErrorToast(PaymentMainActivity.this,"No Data Available");
                }
                else {
                    PaymentAdapter adapter = new PaymentAdapter(PaymentMainActivity.this, response.body().getCat());
                    rclvPayment.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Payment_PayPenModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                // Toast.makeText(BookingUploadMainActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
                Utils.showErrorToast(PaymentMainActivity.this,"No Data Available");
            }
        });
    }
}