package com.example.kumarGroup.ReportCollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kumarGroup.PaymentCollectionBorrowListCount;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ReportCollectionCustomerModel;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RCGVCategoryListActivity extends AppCompatActivity {

    RecyclerView rclvRCGVCategoryList;

    SharedPreferences sp1;
    String emp;
    ProgressDialog progressDialog;

    LinearLayout lin_MainGeneralVisit;
    TextView txtPaymentCollection,txtPaymentCollectionCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_c_g_v_category_list);

        getSupportActionBar().setTitle("General Visit");

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        lin_MainGeneralVisit=findViewById(R.id.lin_MainGeneralVisit);
        txtPaymentCollection=findViewById(R.id.txtPaymentCollection);
        txtPaymentCollectionCount=findViewById(R.id.txtPaymentCollectionCount);

        rclvRCGVCategoryList=findViewById(R.id.rclvRCGVCategoryList);

        rclvRCGVCategoryList.setHasFixedSize(true);
        rclvRCGVCategoryList.setLayoutManager(new LinearLayoutManager(this));

        progressDialog= new ProgressDialog(RCGVCategoryListActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);



        WebService.getClient().getPCBorrowListCount("Booking Delivery").enqueue(new Callback<PaymentCollectionBorrowListCount>() {
            @Override
            public void onResponse(@NotNull Call<PaymentCollectionBorrowListCount> call,
                                   @NotNull Response<PaymentCollectionBorrowListCount> response)
            {
                assert response.body() != null;
                txtPaymentCollectionCount.setText(String.valueOf(response.body().getCount()));
            }

            @Override
            public void onFailure(@NotNull Call<PaymentCollectionBorrowListCount> call, @NotNull Throwable t) {

            }
        });



        lin_MainGeneralVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(RCGVCategoryListActivity.this,GenVisitPaymentCollectionActivity.class);
                startActivity(i);
            }
        });


        WebService.getClient().getRCCategoryList(emp).enqueue(new Callback<ReportCollectionCustomerModel>() {
            @Override
            public void onResponse(@NotNull Call<ReportCollectionCustomerModel> call,
                                   @NotNull Response<ReportCollectionCustomerModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;
                if(response.body().getCat().size()==0){
                     Utils.showErrorToast(RCGVCategoryListActivity.this,"Data not Available");
                }
                else{
                    RCGVCategoryAdapter adapter = new RCGVCategoryAdapter(RCGVCategoryListActivity.this,response.body().getCat());
                    rclvRCGVCategoryList.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ReportCollectionCustomerModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}