package com.example.kumarGroup.ReportCollection.ByVillageList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.ShowVilageDetail;
import com.example.kumarGroup.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VillageListShow2Activity extends AppCompatActivity {

    String type,vname;
    RecyclerView recyclerView;
    VillageListShow2Adapter villageListShow2Adapter;
    ProgressDialog pro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_list_show2);

        type = getIntent().getStringExtra("type");
        vname = getIntent().getStringExtra("vname");

        recyclerView = findViewById(R.id.rclvVillageList);

        Log.d("TAG", "onBindViewHolder: Checksize type"+type);
        Log.d("TAG", "onBindViewHolder: Checksize vname"+vname);

        pro = new ProgressDialog(this);
        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");





        WebService.getClient().VillageListShowon(type,vname).enqueue(new Callback<ShowVilageDetail>() {
            @Override
            public void onResponse(Call<ShowVilageDetail> call, Response<ShowVilageDetail> response) {

//                Log.d("TAG", "onBindViewHolder: Checksize fname"+response.body().getData().get(0).getFname());

                if(response.body().getDetail().size()==0){
                    pro.dismiss();
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();

                }else {
                    pro.dismiss();
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    villageListShow2Adapter = new VillageListShow2Adapter(VillageListShow2Activity.this,VillageListShow2Activity.this,response.body().getDetail());
                    recyclerView.setAdapter(villageListShow2Adapter);
                }

            }

            @Override
            public void onFailure(Call<ShowVilageDetail> call, Throwable t) {
                pro.dismiss();
                Log.d("TAG", "onBindViewHolder: Checksize fname fail "+t);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}