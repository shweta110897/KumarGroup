package com.example.kumarGroup.Workshop.General;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WSGeneralModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WSGeneralVisitActivity extends AppCompatActivity {

    RecyclerView rclvGeneralWSDisplay,rclvComplainBoxDisplay;

    SharedPreferences sp;
    String emp;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w_s_general_visit);

        rclvGeneralWSDisplay=findViewById(R.id.rclvGeneralWSDisplay);
        rclvGeneralWSDisplay.setHasFixedSize(true);
        rclvGeneralWSDisplay.setLayoutManager(new LinearLayoutManager(this));



        rclvComplainBoxDisplay=findViewById(R.id.rclvComplainBoxDisplay);
        rclvComplainBoxDisplay.setHasFixedSize(true);
        rclvComplainBoxDisplay.setLayoutManager(new LinearLayoutManager(this));


        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        progressDialog= new ProgressDialog(WSGeneralVisitActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getGeneralWS().enqueue(new Callback<WSGeneralModel>() {
            @Override
            public void onResponse(@NotNull Call<WSGeneralModel> call, @NotNull Response<WSGeneralModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;

                if(response.body().getData().size() == 0){
                    Utils.showErrorToast(WSGeneralVisitActivity.this,"No Data Available");
                }
                else {
                    WSGeneralAdapter adapter = new WSGeneralAdapter(WSGeneralVisitActivity.this,
                            response.body().getData());
                    rclvGeneralWSDisplay.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<WSGeneralModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });



       /* WebService.getClient().getComplainBoxDisplay().enqueue(new Callback<ComplainBoxDisplayModel>() {
            @Override
            public void onResponse(@NotNull Call<ComplainBoxDisplayModel> call,
                                   @NotNull Response<ComplainBoxDisplayModel> response)
            {

                progressDialog.dismiss();

                assert response.body() != null;

                if(response.body().getCat().size() == 0){
                    Utils.showErrorToast(WSGeneralVisitActivity.this,"No Data Available");
                }
                else {
                    ComplainBoxDisplayAdapter adapter = new ComplainBoxDisplayAdapter(WSGeneralVisitActivity.this,
                            response.body().getCat());
                    rclvComplainBoxDisplay.setAdapter(adapter);
                }


            }

            @Override
            public void onFailure(@NotNull Call<ComplainBoxDisplayModel> call, @NotNull Throwable t) {

            }
        });*/
    }
}