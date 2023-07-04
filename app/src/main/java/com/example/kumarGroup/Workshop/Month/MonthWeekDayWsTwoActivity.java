package com.example.kumarGroup.Workshop.Month;

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

import com.example.kumarGroup.CatWsMonthWeekDay;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.WsMonthWeekDailyModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonthWeekDayWsTwoActivity extends AppCompatActivity {

    RecyclerView rclvWSMonthWeekDay;


    ProgressDialog progressDialog;

    SharedPreferences sp1;
    String emp;

    String id;


    private SearchView searchView;
    List<CatWsMonthWeekDay> catShowRCGVS;
    MonthWeekDayWsAdapter allEntryMonthWeekDayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_week_day_ws_two);

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        id = getIntent().getStringExtra("id");

        Log.d("idData", "onCreate: "+id+" "+emp);


        rclvWSMonthWeekDay=findViewById(R.id.rclvWSMonthWeekDay);
        rclvWSMonthWeekDay.setHasFixedSize(true);
        rclvWSMonthWeekDay.setLayoutManager(new LinearLayoutManager(this));


        progressDialog= new ProgressDialog(MonthWeekDayWsTwoActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getWsMonthWeekDay(id).enqueue(new Callback<WsMonthWeekDailyModel>() {
            @Override
            public void onResponse(@NotNull Call<WsMonthWeekDailyModel> call,
                                   @NotNull Response<WsMonthWeekDailyModel> response) {

                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    catShowRCGVS = response.body().getCat();
                    if (catShowRCGVS.size() == 0) {
                        Utils.showErrorToast(MonthWeekDayWsTwoActivity.this,"No Data Found");

                    } else {
                        allEntryMonthWeekDayAdapter = new MonthWeekDayWsAdapter(MonthWeekDayWsTwoActivity.this,
                                catShowRCGVS);
                        rclvWSMonthWeekDay.setAdapter(allEntryMonthWeekDayAdapter);
                    }
                }
                else {
                    Utils.showNormalToast(MonthWeekDayWsTwoActivity.this,"Server error please try again later");
                }
            }

            @Override
            public void onFailure(@NotNull Call<WsMonthWeekDailyModel> call, @NotNull Throwable t) {
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
                if (catShowRCGVS == null) {
                    //        Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();

                }else if (catShowRCGVS.size() == 0){
                    //         Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();
                }else{
                    allEntryMonthWeekDayAdapter.getFilter().filter(query);
                    // showDetailGVAdapter.getFilter().filter(query);
                    Log.d("ssss", "onQueryTextChange: "+ allEntryMonthWeekDayAdapter);
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

