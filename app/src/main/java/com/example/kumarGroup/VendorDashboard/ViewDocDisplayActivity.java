package com.example.kumarGroup.VendorDashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewDocVendoreModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewDocDisplayActivity extends AppCompatActivity {


    RecyclerView rclv_viewDoc;

    String date1,date2;
    SharedPreferences sp1;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doc_display);


        getSupportActionBar().setTitle("View Document");

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp1.getString("emp_id_vendor", "");

        date1 = getIntent().getStringExtra("StartingDateOne");
        date2 = getIntent().getStringExtra("EndingDateTwo");




        rclv_viewDoc=findViewById(R.id.rclv_viewDoc);

        rclv_viewDoc.setHasFixedSize(true);
        rclv_viewDoc.setLayoutManager(new LinearLayoutManager(this));


        progressDialog= new ProgressDialog(ViewDocDisplayActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );


        WebService.getClient().getViewDocVendore(emp,
                date1,
                date2).enqueue(new Callback<ViewDocVendoreModel>() {
            @Override
            public void onResponse(@NotNull Call<ViewDocVendoreModel> call, @NotNull Response<ViewDocVendoreModel> response) {

                progressDialog.dismiss();
                if (response.body().getCat().size() == 0) {
                    Utils.showErrorToast(ViewDocDisplayActivity.this,"No Data Found");

                } else {
                    ViewDocAdapter adapter= new ViewDocAdapter(ViewDocDisplayActivity.this,
                            response.body().getCat());
                    rclv_viewDoc.setAdapter(adapter);

                    }
            }

            @Override
            public void onFailure(@NotNull Call<ViewDocVendoreModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Log.d("onF", "onFailure: "+t.getCause());
                Toast.makeText(ViewDocDisplayActivity.this,"server issue"+t.getCause(),Toast.LENGTH_LONG).show();

            }
        });
    }
}