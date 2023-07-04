package com.example.kumarGroup.Village_List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kumarGroup.Inquiry.InquiryMainActivity;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ViewInquiry.ViewInqAllCatActivity;
import com.example.kumarGroup.VillageList;
import com.example.kumarGroup.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Village_List_Activity extends AppCompatActivity {

    String emp_id,cat_id;
    RecyclerView recyclerView;
    VillageListAdapter villageListAdapter;
    ProgressDialog pro;
    SharedPreferences sp1;


    public static boolean Village_List_Check_View_Inquire = false;
    public static boolean Village_List_Check_My_Inquire = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_list);

        recyclerView = findViewById(R.id.rclvVillageList);

        SharedPreferences sharedPreferences = getSharedPreferences("dealstage3cateid", Context.MODE_PRIVATE);
        cat_id = sharedPreferences.getString("catId_eid","");

        pro = new ProgressDialog(this);
        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");

        if (ViewInqAllCatActivity.deal_stage_Village_List){

            //Village_List_Check_View_Inquire = true;

            SharedPreferences sharedPreferencesS = getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
            emp_id = sharedPreferencesS.getString("Slected_EMPID","");

            Log.d("TAG", "onCreate: Check_emp View " +emp_id);


            WebService.getClient().VillageList(emp_id,cat_id).enqueue(new Callback<VillageList>() {
                @Override
                public void onResponse(Call<VillageList> call, Response<VillageList> response) {
                    if (response.body().getName().size()==0){
                        Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                        finish();
                        pro.dismiss();
                    }else {
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        Log.d("TAG", "onResponse: chechhcec  " + response.body().getName().get(0).getVname());
                        villageListAdapter = new VillageListAdapter(Village_List_Activity.this,Village_List_Activity.this,response.body().getName());
                        recyclerView.setAdapter(villageListAdapter);
                        pro.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<VillageList> call, Throwable t) {

                }
            });

        }else if (InquiryMainActivity.deal_stage_village_list){
            sp1 = getSharedPreferences("Login", MODE_PRIVATE);
            emp_id = sp1.getString("emp_id", "");

//            Village_List_Check_My_Inquire = true;

            Log.d("TAG", "onCreate: Check_emp " +emp_id);

            WebService.getClient().VillageList(emp_id,cat_id).enqueue(new Callback<VillageList>() {
                @Override
                public void onResponse(Call<VillageList> call, Response<VillageList> response) {
                    if (response.body().getName().size()==0){
                        Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                        finish();
                        pro.dismiss();
                    }else {
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        Log.d("TAG", "onResponse: chechhcec  " + response.body().getName().get(0).getVname());
                        villageListAdapter = new VillageListAdapter(Village_List_Activity.this, Village_List_Activity.this, response.body().getName());
                        recyclerView.setAdapter(villageListAdapter);
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<VillageList> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}