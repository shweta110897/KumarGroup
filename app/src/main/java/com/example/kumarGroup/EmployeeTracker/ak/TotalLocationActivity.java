package com.example.kumarGroup.EmployeeTracker.ak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.All_data_show_tracking_model;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TotalLocationActivity extends AppCompatActivity {

    RecyclerView showall_data_recyclerView;
    List<String> startloc;

    Totallocation_Adapter totallocationAdapter;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_location);

        SharedPreferences sharedPreferences = getSharedPreferences("emp_location_id", MODE_PRIVATE);
        id = sharedPreferences.getString("id", "");

        showall_data_recyclerView = findViewById(R.id.showall_data_recyclerView);

       WebService.getClient().get_all_data_show_tracking(id).enqueue(new Callback<All_data_show_tracking_model>() {
            @Override
            public void onResponse(Call<All_data_show_tracking_model> call, Response<All_data_show_tracking_model> response) {

                if (0 != response.body().getData().size()) {
                    totallocationAdapter = new Totallocation_Adapter(TotalLocationActivity.this, response.body().getData());
                    showall_data_recyclerView.setAdapter(totallocationAdapter);
                } else {
                    Utils.showErrorToast(TotalLocationActivity.this, "No Data Found");
                }
            }

            @Override
            public void onFailure(Call<All_data_show_tracking_model> call, Throwable t) {

            }
        });
    }
}