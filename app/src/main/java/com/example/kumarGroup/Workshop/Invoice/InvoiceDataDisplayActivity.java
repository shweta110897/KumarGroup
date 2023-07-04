package com.example.kumarGroup.Workshop.Invoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.kumarGroup.InvoiceDataDisplayModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceDataDisplayActivity extends AppCompatActivity {


    String date1,date2;
    SharedPreferences sp;
    String emp;
    RecyclerView rclvInvoiceDataDisplay;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_data_display);

        getSupportActionBar().setTitle("Invoice");

        rclvInvoiceDataDisplay=findViewById(R.id.rclvInvoiceDataDisplay);
        rclvInvoiceDataDisplay.setHasFixedSize(true);
        rclvInvoiceDataDisplay.setLayoutManager(new LinearLayoutManager(this));


        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        date1 = getIntent().getStringExtra("StartingDateOne");
        date2 = getIntent().getStringExtra("EndingDateTwo");

        Log.d("EMP_ID", "onCreate: "+emp);

        progressDialog= new ProgressDialog(InvoiceDataDisplayActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getInvoiceData(date1,date2).enqueue(new Callback<InvoiceDataDisplayModel>() {
            @Override
            public void onResponse(@NotNull Call<InvoiceDataDisplayModel> call,
                                   @NotNull Response<InvoiceDataDisplayModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;

                if(response.body().getWorkshop().size() == 0){
                    Utils.showErrorToast(InvoiceDataDisplayActivity.this,"No Data Available");
                }
                else {
                    InvoiceDataDisplayAdapter adapter = new InvoiceDataDisplayAdapter(InvoiceDataDisplayActivity.this,
                            response.body().getWorkshop());
                    rclvInvoiceDataDisplay.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<InvoiceDataDisplayModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}