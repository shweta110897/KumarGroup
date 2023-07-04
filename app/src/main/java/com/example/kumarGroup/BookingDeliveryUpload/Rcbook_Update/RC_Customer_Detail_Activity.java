package com.example.kumarGroup.BookingDeliveryUpload.Rcbook_Update;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.R;
import com.example.kumarGroup.RcUpdate;
import com.example.kumarGroup.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RC_Customer_Detail_Activity extends AppCompatActivity {

    ProgressDialog pro;
    RecyclerView rclvPlateUpdate;
    RC_Customer_Update_Detail_Adapter rc_customer_update_detail_adapter;

    SharedPreferences sp1;
    String emp;

    private SearchView searchView;
    List<RcUpdate.rcUpdate> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_plate_update_detail);

        pro = new ProgressDialog(this);
        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");


        rclvPlateUpdate = findViewById(R.id.rclvPlateUpdate);

        Log.d("TAG", "onResponse: responcecheck outer");

        WebService.getClient().GetRCUpdateCustomerList(emp).enqueue(new Callback<RcUpdate>() {
            @Override
            public void onResponse(Call<RcUpdate> call, Response<RcUpdate> response) {
                pro.dismiss();
                Log.d("TAG", "onResponse: responcecheck "+response.body().getMessage());
                if (response.body().getData().size()==0 || response.body().getMessage().equals("No users found.")){
                    Toast.makeText(RC_Customer_Detail_Activity.this, "No Data", Toast.LENGTH_SHORT).show();
                }else {
                    data = response.body().getData();
                    rclvPlateUpdate.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rc_customer_update_detail_adapter = new RC_Customer_Update_Detail_Adapter(RC_Customer_Detail_Activity.this, RC_Customer_Detail_Activity.this, data);
                    rclvPlateUpdate.setAdapter(rc_customer_update_detail_adapter);
                }

//                Log.d("TAG", "onResponse: CheckSize "+response.body().getData().size());
            }

            @Override
            public void onFailure(Call<RcUpdate> call, Throwable t) {

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

                Log.d("query",query);


                // filter recycler view when text is changed
                if (data == null) {
                    //        Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();

                }else if (data.size() == 0){
                    //         Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();
                }else{

                    Log.d("query123",query);
                    rc_customer_update_detail_adapter.getFilter().filter(query);
                    Log.d("ssss", "onQueryTextChange: "+ rc_customer_update_detail_adapter);
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