package com.example.kumarGroup.CustomerProfileData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewCustomerProfileDataModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCustomerDatadisplayMainActivity extends AppCompatActivity {

    String MobileNo;


    RecyclerView rclvViewCustomer;
    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer_datadisplay_main);

        getSupportActionBar().setTitle("Customer Profile View");

        MobileNo = getIntent().getStringExtra("MobileNo");


        rclvViewCustomer=findViewById(R.id.rclvViewCustomer);
        rclvViewCustomer.setHasFixedSize(true);
        rclvViewCustomer.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");


        Log.d("EMP_ID", "onCreate: "+emp);

        progressDialog= new ProgressDialog(ViewCustomerDatadisplayMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getViewProfileDetail(MobileNo).enqueue(new Callback<ViewCustomerProfileDataModel>() {
            @Override
            public void onResponse(@NotNull Call<ViewCustomerProfileDataModel> call,
                                   @NotNull Response<ViewCustomerProfileDataModel> response) {

                progressDialog.dismiss();

                assert response.body() != null;
                if(response.body().getCustomer_profile().size()==0){
                    Utils.showErrorToast(ViewCustomerDatadisplayMainActivity.this,"No Data Available");
                }
                else{
                    ViewCustomerProfileAdapter adapter = new ViewCustomerProfileAdapter(ViewCustomerDatadisplayMainActivity.this,
                            response.body().getCustomer_profile());
                    rclvViewCustomer.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(@NotNull Call<ViewCustomerProfileDataModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Log.d("Travelling", "onFailure: "+t.getCause());
                Toast.makeText(ViewCustomerDatadisplayMainActivity.this,""+t.getCause(),Toast.LENGTH_LONG).show();
            }
        });
    }
}