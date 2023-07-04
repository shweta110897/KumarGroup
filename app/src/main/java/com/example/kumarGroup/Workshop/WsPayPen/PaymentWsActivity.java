package com.example.kumarGroup.Workshop.WsPayPen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.PaymentWSModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentWsActivity extends AppCompatActivity {

    RecyclerView rclvWorkShopPayment;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    String idPaymentWs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_ws);

        rclvWorkShopPayment=findViewById(R.id.rclvWorkShopPayment);
        rclvWorkShopPayment.setHasFixedSize(true);
        rclvWorkShopPayment.setLayoutManager(new LinearLayoutManager(this));


        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        idPaymentWs= getIntent().getStringExtra("id");

       // Toast.makeText(this, ""+idPaymentWs, Toast.LENGTH_SHORT).show();

        progressDialog= new ProgressDialog(PaymentWsActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getPaymentWs(idPaymentWs,emp).enqueue(new Callback<PaymentWSModel>() {
            @Override
            public void onResponse(@NotNull Call<PaymentWSModel> call, @NotNull Response<PaymentWSModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;

                if(response.body().getCat().size() == 0){
                    Utils.showErrorToast(PaymentWsActivity.this,"No Data Available");
                }
                else {
                    PaymentWSAdapter adapter = new PaymentWSAdapter(PaymentWsActivity.this,
                            response.body().getCat());
                    rclvWorkShopPayment.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<PaymentWSModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });

    }
}