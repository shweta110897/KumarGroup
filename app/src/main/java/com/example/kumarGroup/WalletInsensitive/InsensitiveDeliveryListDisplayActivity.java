package com.example.kumarGroup.WalletInsensitive;

import static com.example.kumarGroup.Utils.isDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.kumarGroup.DataInsensitive;
import com.example.kumarGroup.InsentiveWalletModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsensitiveDeliveryListDisplayActivity extends AppCompatActivity {
    RecyclerView rclyInsensitiveData;
    SwipeRefreshLayout swipeRefreshLayoutDelInsensitive;


    SharedPreferences sp;
    String emp,email;

    float total=0,tot1=0;

    ProgressDialog progressDialog;
    List<DataInsensitive> dataInsensitiveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setTitle("Delivery Inquiry Insensitive");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_insensitive_delivery_list_display);

        sp = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp.getString("emp_id", "");


        rclyInsensitiveData = findViewById(R.id.rclyDelInsensitiveData);

        swipeRefreshLayoutDelInsensitive = findViewById(R.id.swipeRefreshLayoutDelInsensitive);

//        rclyInsensitiveData.setHasFixedSize(true);

        progressDialog = new ProgressDialog(InsensitiveDeliveryListDisplayActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getInsensitiveDeliveryData(emp).enqueue(new Callback<InsentiveWalletModel>() {
            @Override
            public void onResponse(@NotNull Call<InsentiveWalletModel> call, @NotNull Response<InsentiveWalletModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;

                if (response.body().getData().size() == 0) {
                    Utils.showErrorToast(InsensitiveDeliveryListDisplayActivity.this, "No Data Available");
                } else {
                    for (int i=0;i<response.body().getData().size();i++){

                        String dataaa=response.body().getData().get(i).getFinal_amount();

                        if (!isDate(dataaa)){
                            total= Float.parseFloat(response.body().getData().get(i).getFinal_amount());
                            Log.d("totalvallllll",String.valueOf(total));
                            tot1=tot1+total;
                            Log.d("tot123",String.valueOf(tot1));
                        }else{
                            Log.d("totalvallllll",String.valueOf(response.body().getData().get(i).getFinal_amount()));
                        }
                    }

                    Log.d("tot1111",String.valueOf(tot1));
                    getSupportActionBar().setTitle("Total :- Rs."+String.valueOf(tot1));


                    DeliveryInsensitiveAdapter adapter = new DeliveryInsensitiveAdapter(
                            InsensitiveDeliveryListDisplayActivity.this,
                            response.body().getData());

                    rclyInsensitiveData.setLayoutManager(new LinearLayoutManager(InsensitiveDeliveryListDisplayActivity.this));
                    rclyInsensitiveData.setAdapter(adapter);

                    adapter.setOnItemClickListener(new DeliveryInsensitiveAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int i, List<DataInsensitive> dataInsensitiveList1,String email1) {
                            dataInsensitiveList=dataInsensitiveList1;
                            email=email1;
                            Intent intent=new Intent(InsensitiveDeliveryListDisplayActivity.this, WalletOtp.class);
                            intent.putExtra("id",dataInsensitiveList.get(i).getId());
                            intent.putExtra("name","Delivery");
                            intent.putExtra("email",email);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NotNull Call<InsentiveWalletModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });


        swipeRefreshLayoutDelInsensitive.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                WebService.getClient().getInsensitiveDeliveryData(emp).enqueue(new Callback<InsentiveWalletModel>() {
                    @Override
                    public void onResponse(@NotNull Call<InsentiveWalletModel> call, @NotNull Response<InsentiveWalletModel> response) {
                        progressDialog.dismiss();

                        swipeRefreshLayoutDelInsensitive.setRefreshing(false);

                        assert response.body() != null;

                        if (response.body().getData().size() == 0) {
                            Utils.showErrorToast(InsensitiveDeliveryListDisplayActivity.this, "No Data Available");
                        } else {
                            DeliveryInsensitiveAdapter adapter = new DeliveryInsensitiveAdapter(
                                    InsensitiveDeliveryListDisplayActivity.this,
                                    response.body().getData());
                            rclyInsensitiveData.setLayoutManager(new LinearLayoutManager(InsensitiveDeliveryListDisplayActivity.this));
                            rclyInsensitiveData.setAdapter(adapter);

                            adapter.setOnItemClickListener(new DeliveryInsensitiveAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int i, List<DataInsensitive> dataInsensitiveList1,String email1) {
                                    dataInsensitiveList=dataInsensitiveList1;
                                    email=email1;
                                    Intent intent=new Intent(InsensitiveDeliveryListDisplayActivity.this, WalletOtp.class);
                                    intent.putExtra("id",dataInsensitiveList.get(i).getId());
                                    intent.putExtra("name","Delivery");
                                    intent.putExtra("email",email);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<InsentiveWalletModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}