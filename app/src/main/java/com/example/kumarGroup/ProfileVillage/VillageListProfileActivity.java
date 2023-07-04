package com.example.kumarGroup.ProfileVillage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.MyProfile.FourButtonMainActivity;
import com.example.kumarGroup.R;
import com.example.kumarGroup.VillageListProfile;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.myProfileNew.MyProfileMainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VillageListProfileActivity extends AppCompatActivity {

    String cat_id,emp_id;
    RecyclerView recyclerView;
    VillageListProfileAdapter villageListProfileAdapter;
    ProgressDialog pro;
    SharedPreferences sp1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_list_profile);

        recyclerView = findViewById(R.id.recyclerview_VillageList);

        SharedPreferences sharedPreferences = getSharedPreferences("dealstage3cateid", Context.MODE_PRIVATE);
        cat_id = sharedPreferences.getString("catId_eid","");



        pro = new ProgressDialog(this);
        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");

//        Log.d("TAG", "onCreate: VillageListProfileActivity123 "+cat_id+" "+emp_id);

        if (FourButtonMainActivity.Village_list_View_Profile_Check){
            SharedPreferences sharedPreferencesS = getSharedPreferences("MyProfileId",MODE_PRIVATE);
            emp_id = sharedPreferencesS.getString("StateId","");

//            Log.d("TAG", "onCreate: VillageListProfileActivity123 "+cat_id+" "+emp_id);

        }else if(MyProfileMainActivity.Village_list_My_Profile_Check){
            sp1 = getSharedPreferences("Login", MODE_PRIVATE);
            emp_id = sp1.getString("emp_id", "");
        }
        WebService.getClient().VillageListProfile(emp_id,cat_id).enqueue(new Callback<VillageListProfile>() {
            @Override
            public void onResponse(Call<VillageListProfile> call, Response<VillageListProfile> response) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                villageListProfileAdapter = new VillageListProfileAdapter(VillageListProfileActivity.this,VillageListProfileActivity.this,response.body().getName());
                recyclerView.setAdapter(villageListProfileAdapter);
                pro.dismiss();
            }

            @Override
            public void onFailure(Call<VillageListProfile> call, Throwable t) {

            }
        });


    }
}