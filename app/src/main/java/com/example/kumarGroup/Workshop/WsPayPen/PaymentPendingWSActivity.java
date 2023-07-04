package com.example.kumarGroup.Workshop.WsPayPen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.WsPaymentPendingModel;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentPendingWSActivity extends AppCompatActivity {


    RecyclerView rclvWSPaymentPending;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_pending_w_s);

        getSupportActionBar().setTitle("Payment/Pending Workshop");



        rclvWSPaymentPending=findViewById(R.id.rclvWSPaymentPending);

        rclvWSPaymentPending.setHasFixedSize(true);
        rclvWSPaymentPending.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        progressDialog= new ProgressDialog(PaymentPendingWSActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getWsPaymentPending(emp).enqueue(new Callback<WsPaymentPendingModel>() {
            @Override
            public void onResponse(@NotNull Call<WsPaymentPendingModel> call, @NotNull Response<WsPaymentPendingModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;

                if(response.body().getData().size() == 0){
                    Utils.showErrorToast(PaymentPendingWSActivity.this,"No Data Available");
                }
                else {
                    WSPayPenAdapter adapter = new WSPayPenAdapter(PaymentPendingWSActivity.this,
                            response.body().getData());
                    rclvWSPaymentPending.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<WsPaymentPendingModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                // Toast.makeText(BookingUploadMainActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
                Utils.showErrorToast(PaymentPendingWSActivity.this,"No Data Available");
            }
        });
    }
}