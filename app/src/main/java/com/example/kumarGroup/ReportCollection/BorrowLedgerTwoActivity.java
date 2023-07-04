package com.example.kumarGroup.ReportCollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.kumarGroup.BorrowLedgerTwoModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrowLedgerTwoActivity extends AppCompatActivity {

    RecyclerView rclvBorrowLedgerTwo;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    String idPaymentWs,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_ledger_two);

        rclvBorrowLedgerTwo=findViewById(R.id.rclvBorrowLedgerTwo);
        rclvBorrowLedgerTwo.setHasFixedSize(true);
        rclvBorrowLedgerTwo.setLayoutManager(new LinearLayoutManager(this));


        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        idPaymentWs= getIntent().getStringExtra("id");
        mobile= getIntent().getStringExtra("mobile");


        progressDialog= new ProgressDialog(BorrowLedgerTwoActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Log.d("borroLedTwo", "onResponse: "+idPaymentWs+" "+mobile);


        WebService.getClient().getBorrowLedgerTwo(idPaymentWs,mobile).enqueue(new Callback<BorrowLedgerTwoModel>() {
            @Override
            public void onResponse(@NotNull Call<BorrowLedgerTwoModel> call,
                                   @NotNull Response<BorrowLedgerTwoModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;

                if(response.body().getCat().size() == 0){
                    Utils.showErrorToast(BorrowLedgerTwoActivity.this,"No Data Available");
                }
                else {
                   // Toast.makeText(BorrowLedgerTwoActivity.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                    Log.d("borroLedTwo", "onResponse: "+response.body());

                    BorrowLedgerTwoAdapter adapter = new BorrowLedgerTwoAdapter(BorrowLedgerTwoActivity.this,
                            response.body().getCat());
                    rclvBorrowLedgerTwo.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<BorrowLedgerTwoModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}