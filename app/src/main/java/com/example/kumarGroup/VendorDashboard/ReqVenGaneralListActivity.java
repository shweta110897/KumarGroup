package com.example.kumarGroup.VendorDashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.VendorReqGaneralModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReqVenGaneralListActivity extends AppCompatActivity {


    RecyclerView rclv_req_ven_ganerallist;


    ProgressDialog progressDialog;

    SharedPreferences sp1;
    String emp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_ven_ganeral_list);


        getSupportActionBar().setTitle("General List Vendor");


        rclv_req_ven_ganerallist=findViewById(R.id.rclv_req_ven_ganerallist);

        rclv_req_ven_ganerallist.setHasFixedSize(true);
        rclv_req_ven_ganerallist.setLayoutManager(new LinearLayoutManager(this));

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp1.getString("emp_id_vendor", "");


        progressDialog= new ProgressDialog(ReqVenGaneralListActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );


        WebService.getClient().getVendorReqGaneralData(emp).enqueue(new Callback<VendorReqGaneralModel>() {
            @Override
            public void onResponse(@NotNull Call<VendorReqGaneralModel> call,
                                   @NotNull Response<VendorReqGaneralModel> response) {
                progressDialog.dismiss();
                if (response.body().getCat().size() == 0) {
                    Utils.showErrorToast(ReqVenGaneralListActivity.this,"No Data Found");

                } else {
                    ReqVenGaneralAdapter adapter= new ReqVenGaneralAdapter(ReqVenGaneralListActivity.this,
                            response.body().getCat());
                    rclv_req_ven_ganerallist.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(@NotNull Call<VendorReqGaneralModel> call, @NotNull Throwable t) {

            }
        });
    }
}