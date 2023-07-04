package com.example.kumarGroup.Workshop.WsManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.WsManagerDataDisplayModel;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WsManagerMainActivity extends AppCompatActivity {

    RecyclerView rclvWorkShopManager;

    SharedPreferences sp,sharedPreferences;
    String emp;

    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws_manager_main);


        rclvWorkShopManager=findViewById(R.id.rclvWorkShopManager);
        rclvWorkShopManager.setHasFixedSize(true);
        rclvWorkShopManager.setLayoutManager(new LinearLayoutManager(this));


        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        sharedPreferences = getSharedPreferences("DailyMechanicReport_user", MODE_PRIVATE);

        progressDialog= new ProgressDialog(WsManagerMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getWsManagerDetails().enqueue(new Callback<WsManagerDataDisplayModel>() {
            @Override
            public void onResponse(@NotNull Call<WsManagerDataDisplayModel> call,
                                   @NotNull Response<WsManagerDataDisplayModel> response) {

                progressDialog.dismiss();

                assert response.body() != null;

                if(response.body().getData().size() == 0){
                    Utils.showErrorToast(WsManagerMainActivity.this,"No Data Available");
                    sharedPreferences.edit().putString("DailyMechanicReport_userpera","0").apply();

                }
                else {
                    sharedPreferences.edit().putString("DailyMechanicReport_userpera","1").apply();

                    WsManagerMainAdapter adapter = new WsManagerMainAdapter(WsManagerMainActivity.this,
                            response.body().getData());
                    rclvWorkShopManager.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<WsManagerDataDisplayModel> call,
                                  @NotNull Throwable t) {
                progressDialog.dismiss();
                Utils.showErrorToast(WsManagerMainActivity.this,"ERROR"+t.getMessage());

            }
        });

    }
}