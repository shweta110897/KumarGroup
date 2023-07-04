package com.example.kumarGroup.ProfileVillage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.Catlist;
import com.example.kumarGroup.MyProfile.FourButtonMainActivity;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.myProfileNew.MyProfileMainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatlistActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CatlistAdapter catlistAdapter;
    ProgressDialog pro;

    String emp_id;
    SharedPreferences sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catlist);

        recyclerView = findViewById(R.id.recyclerview_CatList);

        pro = new ProgressDialog(this);
        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");

        if (FourButtonMainActivity.Village_list_View_Profile_Check){
            SharedPreferences sharedPreferencesS = getSharedPreferences("MyProfileId",MODE_PRIVATE);
            emp_id = sharedPreferencesS.getString("StateId","");
        }else if (MyProfileMainActivity.Village_list_My_Profile_Check){
            sp1 = getSharedPreferences("Login", MODE_PRIVATE);
            emp_id = sp1.getString("emp_id", "");
        }
        WebService.getClient().Catlist(emp_id).enqueue(new Callback<Catlist>() {
            @Override
            public void onResponse(Call<Catlist> call, Response<Catlist> response) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                catlistAdapter = new CatlistAdapter(CatlistActivity.this,CatlistActivity.this,response.body().getCat());
                recyclerView.setAdapter(catlistAdapter);
                pro.dismiss();
            }

            @Override
            public void onFailure(Call<Catlist> call, Throwable t) {

            }
        });
    }
}