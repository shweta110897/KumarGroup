package com.example.kumarGroup.Inquiry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kumarGroup.InqMonthlyModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonthlyInquiryActivity extends AppCompatActivity {

    RecyclerView rclvMonthlyInquiry;

    SharedPreferences sp1;
    String emp;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_inquiry);

        rclvMonthlyInquiry=findViewById(R.id.rclvMonthlyInquiry);

        rclvMonthlyInquiry.setHasFixedSize(true);
        rclvMonthlyInquiry.setLayoutManager(new LinearLayoutManager(this));


        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");


        progressDialog= new ProgressDialog(MonthlyInquiryActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getMonthlyInq(emp).enqueue(new Callback<InqMonthlyModel>() {
            @Override
            public void onResponse(@NotNull Call<InqMonthlyModel> call, @NotNull Response<InqMonthlyModel> response) {
                progressDialog.dismiss();
                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(MonthlyInquiryActivity.this,"No Data Available");
                }
                else{
                    MonthlyInquiryAdapter adpater1 = new MonthlyInquiryAdapter(MonthlyInquiryActivity.this,
                            response.body().getDetail());
                    rclvMonthlyInquiry.setAdapter(adpater1);
                }
            }

            @Override
            public void onFailure(@NotNull Call<InqMonthlyModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}