package com.example.kumarGroup.EmployeeTracker.ak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.R;
import com.example.kumarGroup.ShowTripModel;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowTripFilterActivity extends AppCompatActivity {

    String date,id;
    RecyclerView Showdat_recyclerview;
    ShowTripAdpter showTripAdpter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_trip_filter);

        SharedPreferences sharedPreferences = getSharedPreferences("emp_location_id",MODE_PRIVATE);
        id = sharedPreferences.getString("id","");

        date = getIntent().getStringExtra("date");
        Showdat_recyclerview = findViewById(R.id.Showdat_recyclerview);

        progressDialog = new ProgressDialog(ShowTripFilterActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().ShowTripDataTracking(id,date).enqueue(new Callback<ShowTripModel>() {
            @Override
            public void onResponse(Call<ShowTripModel> call, Response<ShowTripModel> response) {
                showTripAdpter = new ShowTripAdpter(ShowTripFilterActivity.this,response.body().getData());
                Showdat_recyclerview.setAdapter(showTripAdpter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ShowTripModel> call, Throwable t) {
                Utils.showErrorToast(ShowTripFilterActivity.this,t.getMessage());
                progressDialog.dismiss();
            }
        });
    }
}