package com.example.kumarGroup.ReportCollection.ByVillageList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.VillageList1;
import com.example.kumarGroup.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VillageListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Village_List_Adapter village_list_adapter;
    ProgressDialog pro;
    String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_list2);

        recyclerView = findViewById(R.id.rclvVillageList);

        pro = new ProgressDialog(this);
        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");

        type = getIntent().getStringExtra("type");



        WebService.getClient().VillageList1(type).enqueue(new Callback<VillageList1>() {
            @Override
            public void onResponse(Call<VillageList1> call, Response<VillageList1> response) {
//                Log.d("TAG", "onResponse: chechhcec123  " + response.body().getDetail().get(0).getVillage());
                if (response.body().getDetail().size()==0){
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                    finish();
                    pro.dismiss();
                }else {
                    pro.dismiss();
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    village_list_adapter = new Village_List_Adapter(VillageListActivity.this,VillageListActivity.this,response.body().getDetail());
                    recyclerView.setAdapter(village_list_adapter);
                }

            }

            @Override
            public void onFailure(Call<VillageList1> call, Throwable t) {

            }
        });
    }
}