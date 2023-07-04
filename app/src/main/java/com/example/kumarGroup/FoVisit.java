package com.example.kumarGroup;

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

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoVisit extends AppCompatActivity {
    RecyclerView recyclerviewVisit,RclvVillageWiseEmp;

    String cat_id;
    String emp;
    String cat_list;
    SharedPreferences sp;
    ProgressDialog progressDialog, progressDialog1 ;

    //   private SearchView searchView;

    // androidx.appcompat.widget.SearchView searchView;

    private SearchView searchView;

    AdapterFoSalesInfo adapterFoSalesInfo;
    List<DataCatagoryDetailModeljava.Cat> cat;


    FoVisitVillageWiseEmpAdapter foVisitVillageWiseEmpAdapter;
    List<CatMyProVillage> catMyProVillages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fo_visit);

        sp = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp.getString("emp_id", "");
        cat_list = getIntent().getStringExtra("cat_list");
        getSupportActionBar().setTitle(getIntent().getStringExtra("cat_list"));
        cat_id = getIntent().getStringExtra("cat_id");
        //    emp = getIntent().getStringExtra("cat_id");

        recyclerviewVisit = findViewById(R.id.recyclerviewVisit);
        RclvVillageWiseEmp=findViewById(R.id.RclvVillageWiseEmp);



//        recyclerviewVisit.setHasFixedSize(true);
//        recyclerviewVisit.setLayoutManager(new LinearLayoutManager(this));


        RclvVillageWiseEmp.setHasFixedSize(true);
        RclvVillageWiseEmp.setLayoutManager(new LinearLayoutManager(this));

        jsonLoad();
    }

    private  void secondmethod(){

        progressDialog1 = new ProgressDialog(FoVisit.this);
        progressDialog1.show();
        progressDialog1.setContentView(R.layout.progress_dialog);
        progressDialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getMyProVillageWiseEmp(cat_id, emp).enqueue(new Callback<MyProfileVillageWiseEmpModel>() {
            @Override
            public void onResponse(@NotNull Call<MyProfileVillageWiseEmpModel> call,
                                   @NotNull Response<MyProfileVillageWiseEmpModel> response) {
                progressDialog1.dismiss();

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    catMyProVillages = response.body().getCat();
                    if (catMyProVillages.size() == 0) {
//                        Utils.showErrorToast(FoVisit.this, "No Data Found");

                    } else {
                        foVisitVillageWiseEmpAdapter = new FoVisitVillageWiseEmpAdapter(FoVisit.this,
                                catMyProVillages);
                        RclvVillageWiseEmp.setAdapter(foVisitVillageWiseEmpAdapter);
                    }
                } else {
                    Utils.showNormalToast(FoVisit.this, "Server error please try again later");
                }
            }

            @Override
            public void onFailure(@NotNull Call<MyProfileVillageWiseEmpModel> call, @NotNull Throwable t) {

            }
        });
    }
    private void jsonLoad() {
        progressDialog = new ProgressDialog(FoVisit.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

      //  Toast.makeText(FoVisit.this, "cat id "+cat_id+" emp "+emp, Toast.LENGTH_SHORT).show();

        WebService.getClient().getDetail(cat_id, emp).enqueue(new Callback<DataCatagoryDetailModeljava>() {
            @Override
            public void onResponse(@NotNull Call<DataCatagoryDetailModeljava> call,
                                   @NotNull Response<DataCatagoryDetailModeljava> response) {

                if (response.isSuccessful()) {
                 //   assert response.body() != null;
                    cat = response.body().getCat();

                    Log.d("data", "onResponsecat "+cat);
                    if (cat.size() == 0) {
                        Utils.showErrorToast(FoVisit.this, "No Data Found");
                        progressDialog.dismiss();
                    } else {

                        adapterFoSalesInfo = new AdapterFoSalesInfo(FoVisit.this, cat);
                         Log.d("data", "onResponsecat "+cat);
                        recyclerviewVisit.setAdapter(adapterFoSalesInfo);
                        progressDialog.dismiss();
                        secondmethod();
                    }
                } else {
                    progressDialog.dismiss();
                    Utils.showNormalToast(FoVisit.this, "Server error please try again later");
                }
            }


            @Override
            public void onFailure(@NotNull Call<DataCatagoryDetailModeljava> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(FoVisit.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("OnFailureRL", "onFailure: " + t.getCause());
            }
        });




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


                // filter recycler view when text is changed
                if (cat == null) {
                    //        Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();

                } else if (cat.size() == 0) {
                    //         Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();
                } else {
                    adapterFoSalesInfo.getFilter().filter(query);
                    Log.d("ssss", "onQueryTextChange: " + adapterFoSalesInfo);
                    //           Toast.makeText(FoVisit.this, "Data", Toast.LENGTH_SHORT).show();
                }




                if (catMyProVillages == null) {
                    //        Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();

                } else if (catMyProVillages.size() == 0) {
                    //         Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();
                } else {
                    foVisitVillageWiseEmpAdapter.getFilter().filter(query);
                    Log.d("ssss", "onQueryTextChange: " + adapterFoSalesInfo);
                    //           Toast.makeText(FoVisit.this, "Data", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        return true;
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                adapterFoSalesInfo.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_search) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onBackPressed() {


        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    /*private void allocateMemory() {
        recyclerview = findViewById(R.id.recyclerviewVisit);
    }*/
}
