package com.example.kumarGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletAttendance extends AppCompatActivity {
    RecyclerView recyclerviewwalletattendance;
    String cat_id;
    String emp;
    String cat_list ;
    SharedPreferences sp;
    ProgressDialog progressDialog;
    List<WalletList> listData;

    String date1,date2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_attendance);

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp.getString("emp_id","");
    //    Toast.makeText(this, emp, Toast.LENGTH_SHORT).show();
        cat_list=getIntent().getStringExtra("cat_list");
        getSupportActionBar().setTitle("Wallet Attendance");
        cat_id = getIntent().getStringExtra("cat_id");
        //    emp = getIntent().getStringExtra("cat_id");
        
        date1 = getIntent().getStringExtra("StartingDateOne");
        date2 = getIntent().getStringExtra("EndingDateTwo");

        recyclerviewwalletattendance = findViewById( R.id.recyclerviewwalletattendance );
        allocateMemory();

        recyclerviewwalletattendance.setHasFixedSize(true);
        recyclerviewwalletattendance.setLayoutManager(new LinearLayoutManager(this));

        jsonLoad();
    }
    private void jsonLoad()
    {

        progressDialog= new ProgressDialog(WalletAttendance.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );
        WebService.getClient().getdataattendancewallet(emp,date1, date2).enqueue( new Callback<WalletModel>()
        {
            @Override
            public void onResponse(@NotNull Call<WalletModel> call, @NotNull Response<WalletModel> response)
            {

                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    listData = response.body().getAttandance();
                    if (listData.size() == 0) {
                        Utils.showErrorToast(WalletAttendance.this,"No Data Found");

                    } else {
                       // Utils.showErrorToast(WalletAttendance.this,"data found");

                        Adapterattemdancewallet adapterattemdancewallet = new Adapterattemdancewallet(listData, WalletAttendance.this);
                        // Log.d("data", "onResponse: "+cat);
                        recyclerviewwalletattendance.setAdapter(adapterattemdancewallet);
                    }
                }
                else {

                    Utils.showNormalToast(WalletAttendance.this,"Server error please try again later");

                }
            }

            @Override
            public void onFailure(@NotNull Call<WalletModel> call, @NotNull Throwable t)
            {
                progressDialog.dismiss();
                Toast.makeText(WalletAttendance.this, "Fail", Toast.LENGTH_SHORT).show();

                Log.d("OnFailureRL", "onFailure: "+t.getCause());
            }
        });
    }
    private void allocateMemory()
    {
        recyclerviewwalletattendance=findViewById(R.id.recyclerviewwalletattendance);
    }
}