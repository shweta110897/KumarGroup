package com.example.kumarGroup.EmployeeTracker.ak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.emp_startloc_endloc_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapDetailsEMActivity extends AppCompatActivity {
    RecyclerView map_details_recyclerView;
    List<String> startloc;

    AdapterMapDetails adapterMapDetails;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_details_emactivity);

        map_details_recyclerView = findViewById(R.id.map_details_recyclerView);

        id = getIntent().getStringExtra("id");


        WebService.getClient().get_start_end_location(id).enqueue(new Callback<emp_startloc_endloc_model>() {
            @Override
            public void onResponse(Call<emp_startloc_endloc_model> call, Response<emp_startloc_endloc_model> response) {

                //        LinearLayoutManager layoutManager = new LinearLayoutManager(MapDetailsEMActivity.this, LinearLayoutManager.HORIZONTAL, false);
                adapterMapDetails = new AdapterMapDetails(MapDetailsEMActivity.this,response.body().getData());
                //  map_details_recyclerView.setLayoutManager(layoutManager);
                map_details_recyclerView.setAdapter(adapterMapDetails);

            }

            @Override
            public void onFailure(Call<emp_startloc_endloc_model> call, Throwable t) {

            }
        });

    }
}