package com.example.kumarGroup.ReportCollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.kumarGroup.R;
import com.example.kumarGroup.ShowVisitRCModel;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitRCActivity extends AppCompatActivity {

    RecyclerView rclvRCVisit;
    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    String sname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_r_c);

        rclvRCVisit=findViewById(R.id.rclvRCVisit);
        rclvRCVisit.setHasFixedSize(true);

        rclvRCVisit.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        Log.d("EMP_ID", "onCreate: "+emp);

        sname=getIntent().getStringExtra("sname");

        progressDialog= new ProgressDialog(VisitRCActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getVisitRC(sname).enqueue(new Callback<ShowVisitRCModel>() {
            @Override
            public void onResponse(@NotNull Call<ShowVisitRCModel> call, @NotNull Response<ShowVisitRCModel> response) {
                progressDialog.dismiss();

                if(response.body().getCat().size()==0){
                    Utils.showErrorToast(VisitRCActivity.this,"No Visit Available");
                }
                else{
                    VisitRCAdapter adapter = new VisitRCAdapter(VisitRCActivity.this,response.body().getCat());
                    rclvRCVisit.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ShowVisitRCModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }
}