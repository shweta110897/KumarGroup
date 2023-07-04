package com.example.kumarGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProfile extends AppCompatActivity {
    RecyclerView recyclerview;
    String emp;
    SharedPreferences sp;
    RelativeLayout rel_Village;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        getSupportActionBar().setTitle("My Profile");

        //   emp = getIntent().getStringExtra("emp_id");
        sp = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp.getString("emp_id", "");

        recyclerview = findViewById(R.id.recyclerview);
        rel_Village = findViewById(R.id.rel_Village);
        allocateMemory();

        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        jsonLoad();
        rel_Village.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewProfile.this, FoPlace.class);
                startActivity(i);
            }
        });
    }

    private void jsonLoad() {
        progressDialog = new ProgressDialog(ViewProfile.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getcount(emp).enqueue(new Callback<DataCountListModel>() {
            @Override
            public void onResponse(@NotNull Call<DataCountListModel> call, @NotNull Response<DataCountListModel> response) {
                progressDialog.dismiss();

                Log.d("ManageRL", "onResponse: " + response);
                assert response.body() != null;
                AdapterfoPersonalDetail adapter = new AdapterfoPersonalDetail(response.body().getCat(), ViewProfile.this);
                recyclerview.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NotNull Call<DataCountListModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();

                Log.d("OnFailureRL", "onFailure: " + t.getCause());
            }
        });
    }

    private void allocateMemory()
    {
        recyclerview = findViewById(R.id.recyclerview);
    }
}


