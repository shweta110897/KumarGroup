package com.example.kumarGroup.MyProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kumarGroup.MyProfileCategoryModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryMyProfileOneActivity extends AppCompatActivity {

    RecyclerView rclvMyProfileCategory;

    String emp;
    SharedPreferences sp;
    ProgressDialog progressDialog;

    String state,stateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_my_profile_one);

        getSupportActionBar().setTitle("View Profile");

        //   emp = getIntent().getStringExtra("emp_id");
        sp = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp.getString("emp_id", "");


        state=getIntent().getStringExtra("state");
        stateId=getIntent().getStringExtra("stateId");


        rclvMyProfileCategory=findViewById(R.id.rclvMyProfileCategory);
        rclvMyProfileCategory.setHasFixedSize(true);
        rclvMyProfileCategory.setLayoutManager(new LinearLayoutManager(this));


        progressDialog= new ProgressDialog(CategoryMyProfileOneActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getMyProfileCategory(stateId).enqueue(new Callback<MyProfileCategoryModel>() {
            @Override
            public void onResponse(@NotNull Call<MyProfileCategoryModel> call,
                                   @NotNull Response<MyProfileCategoryModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;

                if(response.body().getCat().size()==0){
                    Utils.showErrorToast(CategoryMyProfileOneActivity.this,"Data not Available");
                }
                else{
                    CategoryMyProfileAdapter adapter = new CategoryMyProfileAdapter(CategoryMyProfileOneActivity.this,
                            response.body().getCat());
                    rclvMyProfileCategory.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<MyProfileCategoryModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CategoryMyProfileOneActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}