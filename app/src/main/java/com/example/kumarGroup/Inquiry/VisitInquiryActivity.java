package com.example.kumarGroup.Inquiry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.VisitInquiryModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitInquiryActivity extends AppCompatActivity {

    RecyclerView rclvVisitInquiry;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    String sname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_inquiry);

        rclvVisitInquiry=findViewById(R.id.rclvVisitInquiry);
        rclvVisitInquiry.setHasFixedSize(true);
        rclvVisitInquiry.setLayoutManager(new LinearLayoutManager(this));


        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        Log.d("EMP_ID", "onCreate: "+emp);

        sname=getIntent().getStringExtra("sname");

        progressDialog= new ProgressDialog(VisitInquiryActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getVisitInquiry(emp,sname).enqueue(new Callback<VisitInquiryModel>() {
            @Override
            public void onResponse(@NotNull Call<VisitInquiryModel> call, @NotNull Response<VisitInquiryModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;
                if(response.body().getCat().size()==0){
                    Utils.showErrorToast(VisitInquiryActivity.this,"No Visit Available");
                }
                else{
                    VisitInquiryAdapter adapter = new VisitInquiryAdapter(VisitInquiryActivity.this,
                            response.body().getCat());
                    rclvVisitInquiry.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<VisitInquiryModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });


    }
}