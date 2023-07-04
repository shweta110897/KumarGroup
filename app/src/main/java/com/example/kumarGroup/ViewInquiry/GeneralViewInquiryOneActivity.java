package com.example.kumarGroup.ViewInquiry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewInqGenListCateModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralViewInquiryOneActivity extends AppCompatActivity {

    RecyclerView rclvViewInquiryGeneral;

    SharedPreferences sp1;
    String emp;
    ProgressDialog progressDialog;

    String state,stateId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_view_inquiry_one);


        rclvViewInquiryGeneral=findViewById(R.id.rclvViewInquiryGeneral);
        rclvViewInquiryGeneral.setHasFixedSize(true);
        rclvViewInquiryGeneral.setLayoutManager(new LinearLayoutManager(this));

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        state = getIntent().getStringExtra("state");
        stateId =getIntent().getStringExtra("stateId");

     //   Toast.makeText(this, ""+stateId, Toast.LENGTH_SHORT).show();


        progressDialog= new ProgressDialog(GeneralViewInquiryOneActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);




        WebService.getClient().getViewInqGenList(stateId).enqueue(new Callback<ViewInqGenListCateModel>() {
            @Override
            public void onResponse(@NotNull Call<ViewInqGenListCateModel> call,
                                   @NotNull Response<ViewInqGenListCateModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;

                if(response.body().getCat().size()==0){
                    Utils.showErrorToast(GeneralViewInquiryOneActivity.this,"Data not Available");
                }
                else{
                    GeneralViewInquiryOneAdapter adapter = new GeneralViewInquiryOneAdapter(GeneralViewInquiryOneActivity.this,
                            response.body().getCat());
                    rclvViewInquiryGeneral.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ViewInqGenListCateModel> call,
                                  @NotNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(GeneralViewInquiryOneActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}