package com.example.kumarGroup.BookingDeliveryUpload.Loan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.BookingDeliveryUpload.BookingDeliveryMainActivity;
import com.example.kumarGroup.LoanDataDisplayModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanDataMainActivity extends AppCompatActivity {

    RecyclerView rclvBookingLoanData;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_data_main);

        getSupportActionBar().setTitle("Loan Data");

        rclvBookingLoanData = findViewById(R.id.rclvBookingLoanData);

        rclvBookingLoanData.setHasFixedSize(true);
        rclvBookingLoanData.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        progressDialog= new ProgressDialog(LoanDataMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);




        WebService.getClient().getLoanDataDisplay(emp).enqueue(new Callback<LoanDataDisplayModel>() {
            @Override
            public void onResponse(Call<LoanDataDisplayModel> call, Response<LoanDataDisplayModel> response) {
                progressDialog.dismiss();
                if(response.body().getData().size() == 0){
                    Utils.showErrorToast(LoanDataMainActivity.this,"No Data Available");
                }
                else {
                    LoanDataAdapter adapter = new LoanDataAdapter(LoanDataMainActivity.this,LoanDataMainActivity.this,response.body().getData());
                    rclvBookingLoanData.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<LoanDataDisplayModel> call, Throwable t) {
                progressDialog.dismiss();
                // Toast.makeText(BookingUploadMainActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
                Utils.showErrorToast(LoanDataMainActivity.this,"No Data Available");
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        BookingDeliveryMainActivity.Next_Button_Check = false;
    }
}