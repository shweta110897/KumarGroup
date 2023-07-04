package com.example.kumarGroup.Inquiry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kumarGroup.InquiryGeneralVisitMainModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewInquiry.ViewInqAllCatActivity;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralInquiryMainActivity extends AppCompatActivity {

    RecyclerView rclvInquiryGeneral;

    String pushnotification;

    SharedPreferences sp1;
    String emp;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_inquiry_main);

        getSupportActionBar().setTitle("Inquiry General Visit");

        pushnotification= getIntent().getStringExtra("pushnotification");

        if (pushnotification!=null){

            Log.d("pushnotification",pushnotification);
            InquiryMainActivity.deal_stage_category_flag=true;
//            emp="";
            sp1 = getSharedPreferences("Login",MODE_PRIVATE);
            emp=sp1.getString("emp_id","");
            Log.d("emppppp",emp);

        }else{
            sp1 = getSharedPreferences("Login",MODE_PRIVATE);
            emp=sp1.getString("emp_id","");

        }

        rclvInquiryGeneral=findViewById(R.id.rclvInquiryGeneral);

        rclvInquiryGeneral.setHasFixedSize(true);
        rclvInquiryGeneral.setLayoutManager(new LinearLayoutManager(this));




        progressDialog= new ProgressDialog(GeneralInquiryMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        if(InquiryMainActivity.deal_stage_category_flag || InquiryMainActivity.deal_stage_village_list || InquiryMainActivity.deal_stage_IDMunit_flag){
            SharedPreferences sharedPreferences = getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
            String selectemp = sharedPreferences.getString("Slected_EMPID","");
            String selectemp1 = sharedPreferences.getString("Slected_EMPID1","");
            WebService.getClient().getInquiryGeneralMain_dealstage(emp,selectemp1).enqueue(new Callback<InquiryGeneralVisitMainModel>() {
                @Override
                public void onResponse(@NotNull Call<InquiryGeneralVisitMainModel> call,
                                       @NotNull Response<InquiryGeneralVisitMainModel> response) {
                    progressDialog.dismiss();

                    assert response.body() != null;

                    if(response.body().getCat().size()==0){
                        Utils.showErrorToast(GeneralInquiryMainActivity.this,"Data not Available");
                    }
                    else{
                        Log.d("TAG", "onResponse: GeneralInquiryMainActivityABCD");
                        GeneralInquiryAdapter adapter = new GeneralInquiryAdapter(GeneralInquiryMainActivity.this,
                                response.body().getCat());

                        rclvInquiryGeneral.setAdapter(adapter);
                    }

                }

                @Override
                public void onFailure(@NotNull Call<InquiryGeneralVisitMainModel> call, @NotNull Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(GeneralInquiryMainActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (ViewInqAllCatActivity.deal_stage_viewINQ_flag || ViewInqAllCatActivity.deal_stage_Village_List || ViewInqAllCatActivity.deal_stage_IDMunit_flag){

            SharedPreferences sharedPreferences = getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
            String selectemp = sharedPreferences.getString("Slected_EMPID","");
            String selectemp1 = sharedPreferences.getString("Slected_EMPID1","");


            //Toast.makeText(GeneralInquiryMainActivity.this, "id "+selectemp, Toast.LENGTH_SHORT).show();
            WebService.getClient().getInquiryGeneralMain_dealstage(selectemp,selectemp1).enqueue(new Callback<InquiryGeneralVisitMainModel>() {
                @Override
                public void onResponse(@NotNull Call<InquiryGeneralVisitMainModel> call,
                                       @NotNull Response<InquiryGeneralVisitMainModel> response) {
                    progressDialog.dismiss();

                    assert response.body() != null;

                    if(response.body().getCat().size()==0){
                        Utils.showErrorToast(GeneralInquiryMainActivity.this,"Data not Available");
                    }
                    else{
                        Log.d("TAG", "onResponse: GeneralInquiryMainActivityABCD-1");
                        GeneralInquiryAdapter adapter = new GeneralInquiryAdapter(GeneralInquiryMainActivity.this,
                                response.body().getCat());

                        rclvInquiryGeneral.setAdapter(adapter);
                    }

                }

                @Override
                public void onFailure(@NotNull Call<InquiryGeneralVisitMainModel> call, @NotNull Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(GeneralInquiryMainActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        else {

            InquiryMainActivity.deal_stage_category_flag = false;
            WebService.getClient().getInquiryGeneralMain(emp).enqueue(new Callback<InquiryGeneralVisitMainModel>() {
                @Override
                public void onResponse(@NotNull Call<InquiryGeneralVisitMainModel> call,
                                       @NotNull Response<InquiryGeneralVisitMainModel> response) {
                    progressDialog.dismiss();

                    assert response.body() != null;

                    if (response.body().getCat().size() == 0) {
                        Utils.showErrorToast(GeneralInquiryMainActivity.this, "Data not Available");
                    } else {
                        Log.d("TAG", "onResponse: GeneralInquiryMainActivityABCD-2");

                        GeneralInquiryAdapter adapter = new GeneralInquiryAdapter(GeneralInquiryMainActivity.this,
                                response.body().getCat());

                        rclvInquiryGeneral.setAdapter(adapter);
                    }

                }

                @Override
                public void onFailure(@NotNull Call<InquiryGeneralVisitMainModel> call, @NotNull Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(GeneralInquiryMainActivity.this, "" + t.getCause(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        InquiryMainActivity.deal_stage_category_flag = false;
        InquiryMainActivity.deal_stage_village_list = false;
        ViewInqAllCatActivity.deal_stage_viewINQ_flag = false;
        ViewInqAllCatActivity.deal_stage_Village_List = false;
    }
}