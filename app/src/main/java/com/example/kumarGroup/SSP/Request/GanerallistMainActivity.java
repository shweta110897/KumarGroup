package com.example.kumarGroup.SSP.Request;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.kumarGroup.R;
import com.example.kumarGroup.ReqGaneralListSSPModel;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GanerallistMainActivity extends AppCompatActivity {

    RecyclerView rclv_GeneralList;

    ProgressDialog progressDialog;
    SharedPreferences sp;
    String ssp_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganerallist_main);


        getSupportActionBar().setTitle("General List SSP");

        rclv_GeneralList=findViewById(R.id.rclv_GeneralList);


        rclv_GeneralList.setHasFixedSize(true);
        rclv_GeneralList.setLayoutManager(new LinearLayoutManager(this));


        progressDialog= new ProgressDialog(GanerallistMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        sp = getSharedPreferences("Login", MODE_PRIVATE);

        ssp_id = sp.getString("emp_id_SSP", "");

        Log.d("TAG", "onCreate: "+ssp_id);

        WebService.getClient().getReqGeneralListData(ssp_id).enqueue(new Callback<ReqGaneralListSSPModel>() {
            @Override
            public void onResponse(@NotNull Call<ReqGaneralListSSPModel> call,
                                   @NotNull Response<ReqGaneralListSSPModel> response) {
                progressDialog.dismiss();

                progressDialog.dismiss();
                if (response.body().getCat().size() == 0) {
                    Utils.showErrorToast(GanerallistMainActivity.this,"No Data Found");

                } else {
                    GaneralListSSPAdapter adapter= new GaneralListSSPAdapter(GanerallistMainActivity.this,
                            response.body().getCat());
                    rclv_GeneralList.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(@NotNull Call<ReqGaneralListSSPModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}