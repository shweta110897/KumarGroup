package com.example.kumarGroup.WalletTraveling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.TravelingDataDisplayModel;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TravelingDataDisplayActivity extends AppCompatActivity {

    String date1,date2;
    SharedPreferences sp;
    String emp;
    RecyclerView rclvWalletTravelingData;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traveling_data_display);

        getSupportActionBar().setTitle("Traveling");

        rclvWalletTravelingData = findViewById(R.id.rclvWalletTravelingData);
        rclvWalletTravelingData.setHasFixedSize(true);
        rclvWalletTravelingData.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        date1 = getIntent().getStringExtra("StartingDateOne");
        date2 = getIntent().getStringExtra("EndingDateTwo");

        Log.d("EMP_ID", "onCreate: "+emp);

        progressDialog= new ProgressDialog(TravelingDataDisplayActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getTravelingData(emp,date1,date2).enqueue(new Callback<TravelingDataDisplayModel>() {
            @Override
            public void onResponse(@NotNull Call<TravelingDataDisplayModel> call,
                                   @NotNull Response<TravelingDataDisplayModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;
                if(response.body().getTravaling().size()==0){
                    Utils.showErrorToast(TravelingDataDisplayActivity.this,"No Data Available");
                }
                else{
                    WalletTravelingDataAdapter adapter = new WalletTravelingDataAdapter(TravelingDataDisplayActivity.this,
                            response.body().getTravaling());
                    rclvWalletTravelingData.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<TravelingDataDisplayModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Log.d("Travelling", "onFailure: "+t.getCause());
                Toast.makeText(TravelingDataDisplayActivity.this,""+t.getCause(),Toast.LENGTH_LONG).show();
            }
        });

    }
}