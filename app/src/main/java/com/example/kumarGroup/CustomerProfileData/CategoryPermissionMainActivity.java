package com.example.kumarGroup.CustomerProfileData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.R;
import com.example.kumarGroup.ReportCollection.RCGVCategoryAdapter;
import com.example.kumarGroup.ReportCollectionCustomerModel;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPermissionMainActivity extends AppCompatActivity {


    RecyclerView rclvRCGVCategoryList;

    SharedPreferences sp1;
    String emp;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_permission_main);

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");


        rclvRCGVCategoryList=findViewById(R.id.rclvRCGVCategoryList);

        rclvRCGVCategoryList.setHasFixedSize(true);
        rclvRCGVCategoryList.setLayoutManager(new LinearLayoutManager(this));

        progressDialog= new ProgressDialog(CategoryPermissionMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getRCCategoryList(emp).enqueue(new Callback<ReportCollectionCustomerModel>() {
            @Override
            public void onResponse(@NotNull Call<ReportCollectionCustomerModel> call,
                                   @NotNull Response<ReportCollectionCustomerModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;
                if(response.body().getCat().size()==0){
                    Utils.showErrorToast(CategoryPermissionMainActivity.this,"Data not Available");
                }
                else{
                    RCGVCategoryAdapter adapter = new RCGVCategoryAdapter(CategoryPermissionMainActivity.this,response.body().getCat());
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