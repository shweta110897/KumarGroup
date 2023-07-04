package com.example.kumarGroup.ViewInquiry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.VisitVIModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitVIActivity extends AppCompatActivity {

    RecyclerView rclvVisitVi;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    String sname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_v_i);


        rclvVisitVi=findViewById(R.id.rclvVisitVi);
        rclvVisitVi.setHasFixedSize(true);
        rclvVisitVi.setLayoutManager(new LinearLayoutManager(this));


        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        Log.d("EMP_ID", "onCreate: "+emp);

        sname=getIntent().getStringExtra("sname");

       // Toast.makeText(this, ""+sname, Toast.LENGTH_SHORT).show();

        progressDialog= new ProgressDialog(VisitVIActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getVisitVi(sname).enqueue(new Callback<VisitVIModel>() {
            @Override
            public void onResponse(@NotNull Call<VisitVIModel> call, @NotNull Response<VisitVIModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;
                if(response.body().getCat().size()==0){
                    Utils.showErrorToast(VisitVIActivity.this,"No Visit Available");
                }
                else{
                    VisitViAdapter adapter = new VisitViAdapter(VisitVIActivity.this,
                            response.body().getCat());
                    rclvVisitVi.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<VisitVIModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();

            }
        });

    }
}