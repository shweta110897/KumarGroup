package com.example.kumarGroup.ReportCollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.BorrowOneLedgerModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrowLadgerOneActivity extends AppCompatActivity {

    RecyclerView rclvBorrowLEdgerOne;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    String idBookingUpload;

    String idPayment,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_ladger_one);


        rclvBorrowLEdgerOne = findViewById(R.id.rclvBorrowLEdgerOne);

        rclvBorrowLEdgerOne.setHasFixedSize(true);
        rclvBorrowLEdgerOne.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        idPayment = getIntent().getStringExtra("idPayment");

        mobile = getIntent().getStringExtra("mobile");

        progressDialog= new ProgressDialog(BorrowLadgerOneActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getBorrowLedgerOne(idPayment,mobile).enqueue(new Callback<BorrowOneLedgerModel>() {
            @Override
            public void onResponse(@NotNull Call<BorrowOneLedgerModel> call, @NotNull Response<BorrowOneLedgerModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;

                if(response.body().getCat().size() == 0){
                    Utils.showErrorToast(BorrowLadgerOneActivity.this,"No Data Available");
                }
                else {
                    BorrowledgerOneAdapter adapter = new BorrowledgerOneAdapter(
                            BorrowLadgerOneActivity.this, response.body().getCat());
                    rclvBorrowLEdgerOne.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<BorrowOneLedgerModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                // Toast.makeText(BookingUploadMainActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
                Utils.showErrorToast(BorrowLadgerOneActivity.this,"No Data Available");
            }
        });

    }
}