package com.example.kumarGroup.Village_List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.kumarGroup.Inquiry.InquiryMainActivity;
import com.example.kumarGroup.R;
import com.example.kumarGroup.VillageListShow;
import com.example.kumarGroup.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VillageListShowActivity extends AppCompatActivity {

    String vid,emp_id,cat_id;
    SharedPreferences sp1;
    ProgressDialog pro;
    RecyclerView recyclerView;
    VillageListShowAdapter villageListShowAdapter;
    private SearchView searchView;
    List<VillageListShow.Cat> VillageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_list_show);

        recyclerView =findViewById(R.id.recyclerview_Village_List_Show);

        vid = getIntent().getStringExtra("vid");

        sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        emp_id = sp1.getString("emp_id", "");

        SharedPreferences sharedPreferences = getSharedPreferences("dealstage3cateid", Context.MODE_PRIVATE);
        cat_id = sharedPreferences.getString("catId_eid","");

        pro = new ProgressDialog(this);
        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");

        Log.d("TAG", "onCreate: Check_Vid "+vid);
        Log.d("TAG", "onCreate: Check_Vid "+cat_id);

        if (Village_List_Activity.Village_List_Check_View_Inquire){
            SharedPreferences sharedPreferencesS = getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
            emp_id = sharedPreferencesS.getString("Slected_EMPID","");

            Log.d("TAG", "onCreate: Check_Vid "+emp_id);

            WebService.getClient().VillageListShow(vid,cat_id,emp_id).enqueue(new Callback<VillageListShow>() {
                @Override
                public void onResponse(Call<VillageListShow> call, Response<VillageListShow> response) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                Log.d("TAG", "onResponse: chechhcec  " + response.body().getName().get(0).getVname());


                    if (response.body().getCat().size()==0){
                        Log.d("TAG", "onCreate: Check_Vid if "+emp_id);

                        Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                        finish();
                        pro.dismiss();
                    }else {
                        Log.d("TAG", "onCreate: Check_Vid Else "+emp_id);

                        villageListShowAdapter = new VillageListShowAdapter(VillageListShowActivity.this,VillageListShowActivity.this,response.body().getCat());
                        recyclerView.setAdapter(villageListShowAdapter);
                        pro.dismiss();
                    }
                    VillageList = response.body().getCat();

                }

                @Override
                public void onFailure(Call<VillageListShow> call, Throwable t) {

                }
            });
        }else if (Village_List_Activity.Village_List_Check_My_Inquire){
            sp1 = getSharedPreferences("Login", MODE_PRIVATE);
            emp_id = sp1.getString("emp_id", "");

            InquiryMainActivity.deal_stage_village_list = false;

            WebService.getClient().VillageListShow(vid,cat_id,emp_id).enqueue(new Callback<VillageListShow>() {
                @Override
                public void onResponse(Call<VillageListShow> call, Response<VillageListShow> response) {
                    Log.d("TAG", "onCreate: Check_Vid my "+emp_id);


                    if (response.body().getCat().size()==0){
                        Log.d("TAG", "onCreate: Check_Vid if my"+emp_id);

                        Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                        finish();
                        pro.dismiss();
                    }else {
                        Log.d("TAG", "onCreate: Check_Vid Else View"+emp_id);

                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                Log.d("TAG", "onResponse: chechhcec  " + response.body().getName().get(0).getVname());
                        villageListShowAdapter = new VillageListShowAdapter(VillageListShowActivity.this,VillageListShowActivity.this,response.body().getCat());
                        recyclerView.setAdapter(villageListShowAdapter);
                        pro.dismiss();
                    }
                    VillageList = response.body().getCat();

                }

                @Override
                public void onFailure(Call<VillageListShow> call, Throwable t) {

                }
            });
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                // return true;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                //Log.d("TAG", "onQueryTextChange: asgdjhasgd " + feedbackCallAdapter);


                //filter recycler view when text is changed
                if (VillageList == null) {
                    Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();

                }else if (VillageList.size() == 0){
                    Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
                }else{
                    villageListShowAdapter.getFilter().filter(query);
                    //feedbackCallAdapter.getFilter().filter(query);
                    // showDetailGVAdapter.getFilter().filter(query);
                    Log.d("ssss", "onQueryTextChange: "+ query);
                    //           Toast.makeText(FoVisit.this, "Data", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if(id == R.id.action_search ){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Village_List_Activity.Village_List_Check_My_Inquire = false;
        Village_List_Activity.Village_List_Check_View_Inquire = false;
    }
}