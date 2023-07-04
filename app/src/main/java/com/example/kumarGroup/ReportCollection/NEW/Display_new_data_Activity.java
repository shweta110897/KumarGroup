package com.example.kumarGroup.ReportCollection.NEW;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.Newdata;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Display_new_data_Activity extends AppCompatActivity {

    RecyclerView rclv_BookingDeliveryData;
    ProgressDialog progressDialog;
    New_data_Adapter adapter;
    LinearLayout lin_MainGeneralVisit;
    TextView txtPaymentCollection,txtPaymentCollectionCount;

    private SearchView searchView;

    List<Newdata.Data> booking_one1;

    List<Newdata.Data> sperpart_one1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_new_data);

        rclv_BookingDeliveryData = findViewById(R.id.rclv_BookingDeliveryData);
        lin_MainGeneralVisit = findViewById(R.id.lin_MainGeneralVisit);
        txtPaymentCollection = findViewById(R.id.txtPaymentCollection);
        txtPaymentCollectionCount = findViewById(R.id.txtPaymentCollectionCount);

        progressDialog= new ProgressDialog(Display_new_data_Activity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        if (Select_NEW_Type_Activity.Type_NEW_Check){
            txtPaymentCollection.setText("Booking Delivery");
            WebService.getClient().Newdata().enqueue(new Callback<Newdata>() {
                @Override
                public void onResponse(Call<Newdata> call, Response<Newdata> response) {
                    if (response.body().getData().size()==0) {
                        progressDialog.dismiss();
                        Toast.makeText(Display_new_data_Activity.this, "No Data", Toast.LENGTH_SHORT).show();
                        txtPaymentCollectionCount.setText("0");
                    }else {
                        progressDialog.dismiss();
                        txtPaymentCollectionCount.setText(String.valueOf(response.body().getData().size()));
                        lin_MainGeneralVisit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                lin_MainGeneralVisit.setVisibility(View.GONE);
                                booking_one1 = response.body().getData();
                                rclv_BookingDeliveryData.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter= new New_data_Adapter(Display_new_data_Activity.this,
                                        Display_new_data_Activity.this,
                                        response.body().getData());
                                rclv_BookingDeliveryData.setAdapter(adapter);
                            }
                        });

                    }
                }

                @Override
                public void onFailure(Call<Newdata> call, Throwable t) {

                }
            });
        }else if (Select_NEW_Type_Activity.Type_NEW_Check==false){
            txtPaymentCollection.setText("Spar Part");

            WebService.getClient().Newdata1().enqueue(new Callback<Newdata>() {
                @Override
                public void onResponse(Call<Newdata> call, Response<Newdata> response) {
                    if (response.body().getData().size()==0) {
                        progressDialog.dismiss();
                        txtPaymentCollectionCount.setText("0");
                        Toast.makeText(Display_new_data_Activity.this, "No Data", Toast.LENGTH_SHORT).show();
                    }else {
                        progressDialog.dismiss();
                        txtPaymentCollectionCount.setText(String.valueOf(response.body().getData().size()));
                        lin_MainGeneralVisit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                lin_MainGeneralVisit.setVisibility(View.GONE);
                                sperpart_one1 = response.body().getData();
                                rclv_BookingDeliveryData.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter= new New_data_Adapter(Display_new_data_Activity.this,
                                        Display_new_data_Activity.this,
                                        response.body().getData());
                                rclv_BookingDeliveryData.setAdapter(adapter);
                            }
                        });

                    }
                }

                @Override
                public void onFailure(Call<Newdata> call, Throwable t) {
                    progressDialog.dismiss();
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
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                if (Select_NEW_Type_Activity.Type_NEW_Check){
                    Log.d("TAG", "onQueryTextChange: Check_search " + query);
                    // filter recycler view when text is changed
                    if (booking_one1 == null) {
                        //        Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();

                    }else if (booking_one1.size() == 0){
                        //         Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();
                    }else{
                        adapter.getFilter().filter(query);
//                    Log.d("ssss", "onQueryTextChange: "+ adapter);
                        //Toast.makeText(FoVisit.this, "Data", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Log.d("TAG", "onQueryTextChange: Check_search spr" + query);

                    if (sperpart_one1 == null) {
                        //        Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();

                    }else if (sperpart_one1.size() == 0){
                        //         Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();
                    }else{
                        adapter.getFilter().filter(query);
//                    Log.d("ssss", "onQueryTextChange: "+ adapter);
                        //Toast.makeText(FoVisit.this, "Data", Toast.LENGTH_SHORT).show();
                    }
                }








               /* if (dataSupBorrowListList == null) {
                    //        Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();

                }else if (dataSupBorrowListList.size() == 0){
                    //         Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();
                }else{
                    adapter2.getFilter().filter(query);
                    Log.d("ssss", "onQueryTextChange: "+ adapter);
                    //Toast.makeText(FoVisit.this, "Data", Toast.LENGTH_SHORT).show();
                }*/

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
}