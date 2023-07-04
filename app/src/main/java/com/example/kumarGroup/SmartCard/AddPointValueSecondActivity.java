package com.example.kumarGroup.SmartCard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kumarGroup.AddPointValueSecondModel;
import com.example.kumarGroup.AddSmartCardSecondSubmitModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPointValueSecondActivity extends AppCompatActivity {


    EditText edt_categoryNameAPVS,edt_FnameNameAPVS,edt_StateAPVS,edt_CityAPVS,
            edt_DistrictAPVS,edt_TalukoAPVS,edt_VillageAPVS,
            edt_mobile_numberAPVS,edt_whatsapp_numberAPVS,
            edt_EmployeeName_APVS;


    Button btn_SubmitAPVS;

    ProgressDialog progressDialog;

    String cuid,id;

    SharedPreferences sp;
    String emp,emp_id,emp_name;

    SharedPreferences sharedPreferences;
    Integer ID;
    String CUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_point_value_second);


        getSupportActionBar().setTitle("Add Point Value");

        edt_categoryNameAPVS=findViewById(R.id.edt_categoryNameAPVS);
        edt_FnameNameAPVS=findViewById(R.id.edt_FnameNameAPVS);
        edt_StateAPVS=findViewById(R.id.edt_StateAPVS);
        edt_CityAPVS=findViewById(R.id.edt_CityAPVS);
        edt_DistrictAPVS=findViewById(R.id.edt_DistrictAPVS);
        edt_TalukoAPVS=findViewById(R.id.edt_TalukoAPVS);
        edt_VillageAPVS=findViewById(R.id.edt_VillageAPVS);
        edt_mobile_numberAPVS=findViewById(R.id.edt_mobile_numberAPVS);
        edt_whatsapp_numberAPVS=findViewById(R.id.edt_whatsapp_numberAPVS);
        edt_EmployeeName_APVS=findViewById(R.id.edt_EmployeeName_APVS);

        btn_SubmitAPVS=findViewById(R.id.btn_SubmitAPVS);


        cuid= getIntent().getStringExtra("cuid");
        id= getIntent().getStringExtra("id");

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        emp_id = sp.getString("emp_id_vendor", "");
        emp_name = sp.getString("emp_name_vendor", "");

        sharedPreferences = getSharedPreferences("ID", MODE_PRIVATE);
        ID= sharedPreferences.getInt("id",0);
        CUID = sharedPreferences.getString("cuid", "");

        progressDialog= new ProgressDialog(AddPointValueSecondActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );


        WebService.getClient().getAddPointValueSecond(cuid).enqueue(new Callback<AddPointValueSecondModel>() {
            @Override
            public void onResponse(@NotNull Call<AddPointValueSecondModel> call,
                                   @NotNull Response<AddPointValueSecondModel> response) {

                progressDialog.dismiss();

                assert response.body() != null;
                edt_categoryNameAPVS.setText(response.body().getCat().get(0).getCat_name());
                edt_FnameNameAPVS.setText(response.body().getCat().get(0).getCuname());
                edt_StateAPVS.setText(response.body().getCat().get(0).getState());
                edt_CityAPVS.setText(response.body().getCat().get(0).getCity());
                edt_DistrictAPVS.setText(response.body().getCat().get(0).getDistric());
                edt_TalukoAPVS.setText(response.body().getCat().get(0).getTehsil());
                edt_VillageAPVS.setText(response.body().getCat().get(0).getVilage());
                edt_mobile_numberAPVS.setText(response.body().getCat().get(0).getMoblino());
                edt_whatsapp_numberAPVS.setText(response.body().getCat().get(0).getWhatsappno());
                edt_EmployeeName_APVS.setText(response.body().getCat().get(0).getEmployee_name());

            }

            @Override
            public void onFailure(@NotNull Call<AddPointValueSecondModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });


        btn_SubmitAPVS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog= new ProgressDialog(AddPointValueSecondActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );



                WebService.getClient().getAddSmartCardSecond(emp_id,
                        CUID,
                        String.valueOf(ID)
                        ).enqueue(new Callback<AddSmartCardSecondSubmitModel>() {
                    @Override
                    public void onResponse(@NotNull Call<AddSmartCardSecondSubmitModel> call,
                                           @NotNull Response<AddSmartCardSecondSubmitModel> response) {

                        progressDialog.dismiss();

                        assert response.body() != null;
                        Toast.makeText(AddPointValueSecondActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(AddPointValueSecondActivity.this,AddPointValueThirdActivity.class);
                        i.putExtra("id",id);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(@NotNull Call<AddSmartCardSecondSubmitModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();

                    }
                });




            }
        });

    }
}