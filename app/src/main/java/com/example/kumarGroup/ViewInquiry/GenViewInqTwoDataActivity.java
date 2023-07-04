package com.example.kumarGroup.ViewInquiry;

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

import com.example.kumarGroup.CatGenCatDataVI;
import com.example.kumarGroup.GeneralCatDataViewInqModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenViewInqTwoDataActivity extends AppCompatActivity {

    SharedPreferences sp1,sp2;
    String emp,sp_stateId ;

    ProgressDialog progressDialog;

    String catId_eid;

    RecyclerView rclvShowDetailGeneralViewInquiry;


    private SearchView searchView;
    List<CatGenCatDataVI> catShow;
    GenViewInqDataTwoAdapter showDetailAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_view_inq_two_data);

        getSupportActionBar().setTitle("View Inquiry General Visit");

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        catId_eid=getIntent().getStringExtra("catId_eid");

        sp2 = getSharedPreferences("StateId",MODE_PRIVATE);

        sp_stateId = sp2.getString("StateId","");

       // Toast.makeText(this, ""+sp_stateId+" "+catId_eid , Toast.LENGTH_SHORT).show();


        rclvShowDetailGeneralViewInquiry= findViewById(R.id.rclvShowDetailGeneralViewInquiry);
        rclvShowDetailGeneralViewInquiry.setHasFixedSize(true);
        rclvShowDetailGeneralViewInquiry.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(GenViewInqTwoDataActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getGenCatDataVI(catId_eid,sp_stateId).enqueue(new Callback<GeneralCatDataViewInqModel>() {
            @Override
            public void onResponse(@NotNull Call<GeneralCatDataViewInqModel> call,
                                   @NotNull Response<GeneralCatDataViewInqModel> response) {
                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    catShow = response.body().getCat();
                    if (catShow.size() == 0) {
                        Utils.showErrorToast(GenViewInqTwoDataActivity.this,"No Data Found");

                    } else {
                        showDetailAdapter = new GenViewInqDataTwoAdapter(GenViewInqTwoDataActivity.this,GenViewInqTwoDataActivity.this,
                                catShow);
                        rclvShowDetailGeneralViewInquiry.setAdapter(showDetailAdapter);
                    }
                }
                else {
                    Utils.showNormalToast(GenViewInqTwoDataActivity.this,"Server error please try again later");
                }
            }

            @Override
            public void onFailure(@NotNull Call<GeneralCatDataViewInqModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
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
                if (catShow == null) {
                    //        Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();

                }else if (catShow.size() == 0){
                    //         Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();
                }else{
                    showDetailAdapter.getFilter().filter(query);
                    // showDetailGVAdapter.getFilter().filter(query);
                    Log.d("ssss", "onQueryTextChange: "+ showDetailAdapter);
                    //           Toast.makeText(FoVisit.this, "Data", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if(id == R.id.action_search ){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {


        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}
