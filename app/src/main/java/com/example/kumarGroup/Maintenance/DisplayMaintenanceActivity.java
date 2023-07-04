package com.example.kumarGroup.Maintenance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.DisplayMaintenanceModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayMaintenanceActivity extends AppCompatActivity {

    RecyclerView rclyDisplayMaintennace;
    ProgressDialog progressDialog;

    String date1,date2;

    SharedPreferences sp1;
    String emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_maintenance);
        getSupportActionBar().setTitle("Maintenance");

        rclyDisplayMaintennace=findViewById(R.id.rclyDisplayMaintennace);
        rclyDisplayMaintennace.setHasFixedSize(true);
        rclyDisplayMaintennace.setLayoutManager(new LinearLayoutManager(this));

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        date1 = getIntent().getStringExtra("StartingDateOne");
        date2 = getIntent().getStringExtra("EndingDateTwo");

     // Toast.makeText(this, ""+emp+" "+date1+" "+date2, Toast.LENGTH_SHORT).show();

        progressDialog= new ProgressDialog(DisplayMaintenanceActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getDisplayMaintenance(emp,date1,date2).enqueue(new Callback<DisplayMaintenanceModel>() {
            @Override
            public void onResponse(@NotNull Call<DisplayMaintenanceModel> call,
                                   @NotNull Response<DisplayMaintenanceModel> response) {
                progressDialog.dismiss();
                assert response.body() != null;
                if(response.body().getMaintainance().size()==0){
                    Utils.showErrorToast(DisplayMaintenanceActivity.this,"No data available");
                }
                else
                {
                    DisplayMaintenanceAdapter adapter = new DisplayMaintenanceAdapter(DisplayMaintenanceActivity.this,response.body().getMaintainance());
                    rclyDisplayMaintennace.setAdapter(adapter);
                }
               // DisplayMaintenanceAdapter adapter = new DisplayMaintenanceAdapter(DisplayMaintenanceActivity.this,response.body().getMaintainance());
               // rclyDisplayMaintennace.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NotNull Call<DisplayMaintenanceModel> call, @NotNull Throwable t) {

            }
        });
    }
}