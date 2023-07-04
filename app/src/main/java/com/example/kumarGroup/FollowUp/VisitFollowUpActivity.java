package com.example.kumarGroup.FollowUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.VisitFollowUpModel;
import com.example.kumarGroup.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitFollowUpActivity extends AppCompatActivity {

    RecyclerView rclvVisitFollowUp;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    String sname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_follow_up);

        rclvVisitFollowUp = findViewById(R.id.rclvVisitFollowUp);
        rclvVisitFollowUp.setHasFixedSize(true);
        rclvVisitFollowUp.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        Log.d("EMP_ID", "onCreate: "+emp);

        sname=getIntent().getStringExtra("sname");
    //  Toast.makeText(this, ""+sname, Toast.LENGTH_SHORT).show();

        progressDialog= new ProgressDialog(VisitFollowUpActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getVisitFollowUp(emp,sname).enqueue(new Callback<VisitFollowUpModel>() {
            @Override
            public void onResponse(Call<VisitFollowUpModel> call, Response<VisitFollowUpModel> response) {
                progressDialog.dismiss();

                if(response.body().getCat().size()==0){
                    Utils.showErrorToast(VisitFollowUpActivity.this,"No Visit Available");
                }
                else{
                   VisitFollowUpAdapter adapter = new VisitFollowUpAdapter(VisitFollowUpActivity.this,response.body().getCat());
                    rclvVisitFollowUp.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<VisitFollowUpModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }
}