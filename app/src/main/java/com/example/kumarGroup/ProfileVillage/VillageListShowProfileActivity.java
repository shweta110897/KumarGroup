package com.example.kumarGroup.ProfileVillage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kumarGroup.MyProfile.FourButtonMainActivity;
import com.example.kumarGroup.R;
import com.example.kumarGroup.VillageListShowProfile;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.myProfileNew.MyProfileMainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VillageListShowProfileActivity extends AppCompatActivity {

    String vid,emp_id,cat_id;
    RecyclerView recyclerView;
    VillageListShowProfileAdapter villageListShowProfileAdapter;
    ProgressDialog pro;
    SharedPreferences sp1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_list_show_profile);

        vid = getIntent().getStringExtra("Vid");

        recyclerView = findViewById(R.id.recyclerview_VillageListShow);

        SharedPreferences sharedPreferences = getSharedPreferences("dealstage3cateid", Context.MODE_PRIVATE);
        cat_id = sharedPreferences.getString("catId_eid","");


        pro = new ProgressDialog(this);
        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");

//        Log.d("TAG", "onResponse: VillageListShowProfileActivity123 "+ vid+" "+cat_id+" "+emp_id);

        if (FourButtonMainActivity.Village_list_View_Profile_Check){

            SharedPreferences sharedPreferencesS = getSharedPreferences("MyProfileId",MODE_PRIVATE);
            emp_id = sharedPreferencesS.getString("StateId","");

            Log.d("TAG", "onResponse: VillageListShowProfileActivity123 "+ vid+" "+cat_id+" "+emp_id);
            WebService.getClient().VillageListShowProfile(vid,cat_id,emp_id).enqueue(new Callback<VillageListShowProfile>() {
                @Override
                public void onResponse(Call<VillageListShowProfile> call, Response<VillageListShowProfile> response) {
                    if (response.body().getCat().size()==0){
                        finish();
                        Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                    }
//                Log.d("TAG", "onResponse: VillageListShowProfileActivity123"+response.body().getCat().get(0).getAutoid());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    villageListShowProfileAdapter = new VillageListShowProfileAdapter(VillageListShowProfileActivity.this,VillageListShowProfileActivity.this,response.body().getCat());
                    recyclerView.setAdapter(villageListShowProfileAdapter);
                    pro.dismiss();
                }

                @Override
                public void onFailure(Call<VillageListShowProfile> call, Throwable t) {

                }
            });
        }else if (MyProfileMainActivity.Village_list_My_Profile_Check){
            sp1 = getSharedPreferences("Login", MODE_PRIVATE);
            emp_id = sp1.getString("emp_id", "");

            WebService.getClient().VillageListShowProfile(vid,cat_id,emp_id).enqueue(new Callback<VillageListShowProfile>() {
                @Override
                public void onResponse(Call<VillageListShowProfile> call, Response<VillageListShowProfile> response) {
                    if (response.body().getCat().size()==0){
                        finish();
                        Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                    }
//                Log.d("TAG", "onResponse: VillageListShowProfileActivity123"+response.body().getCat().get(0).getAutoid());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    villageListShowProfileAdapter = new VillageListShowProfileAdapter(VillageListShowProfileActivity.this,VillageListShowProfileActivity.this,response.body().getCat());
                    recyclerView.setAdapter(villageListShowProfileAdapter);
                    pro.dismiss();
                }

                @Override
                public void onFailure(Call<VillageListShowProfile> call, Throwable t) {

                }
            });
        }


    }
}