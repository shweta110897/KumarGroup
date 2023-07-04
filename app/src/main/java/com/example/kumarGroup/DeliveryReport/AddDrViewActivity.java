package com.example.kumarGroup.DeliveryReport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kumarGroup.AddViewDeliveryReportSubmitModel;
import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewCustomerProfileEditModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDrViewActivity extends AppCompatActivity {


    EditText edt_categoryNameVDR,edt_FnameNameVDR,edt_LastNameVDR,edt_StateVDR,
            edt_CityVDR,edt_DistrictVDR,edt_TalukoVDR,edt_VillageVDR,edt_mobile_numberVDR,
            edt_whatsapp_numberVDR,edt_DescriptionVDR;

    Button btn_SubmitVDR;

    String MobileNo, cuid;

    ProgressDialog progressDialog;

    SharedPreferences sp;
    String emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dr_view);

        edt_categoryNameVDR= findViewById(R.id.edt_categoryNameVDR);
        edt_FnameNameVDR= findViewById(R.id.edt_FnameNameVDR);
        edt_LastNameVDR= findViewById(R.id.edt_LastNameVDR);
        edt_StateVDR= findViewById(R.id.edt_StateVDR);
        edt_CityVDR= findViewById(R.id.edt_CityVDR);
        edt_DistrictVDR= findViewById(R.id.edt_DistrictVDR);
        edt_TalukoVDR= findViewById(R.id.edt_TalukoVDR);
        edt_VillageVDR= findViewById(R.id.edt_VillageVDR);
        edt_mobile_numberVDR= findViewById(R.id.edt_mobile_numberVDR);
        edt_whatsapp_numberVDR= findViewById(R.id.edt_whatsapp_numberVDR);

        btn_SubmitVDR= findViewById(R.id.btn_SubmitVDR);


        MobileNo = getIntent().getStringExtra("MobileNo");

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");




        WebService.getClient().getViewCuProfileEdit(MobileNo).enqueue(new Callback<ViewCustomerProfileEditModel>() {
            @Override
            public void onResponse(@NotNull Call<ViewCustomerProfileEditModel> call,
                                   @NotNull Response<ViewCustomerProfileEditModel> response) {

                assert response.body() != null;
                if (response.body().getCustomer_profile().size() == 0) {
                    Utils.showErrorToast(AddDrViewActivity.this, "No Data Available");
                } else {

                    edt_categoryNameVDR.setText(response.body().getCustomer_profile().get(0).getCat_name());
                    edt_FnameNameVDR.setText(response.body().getCustomer_profile().get(0).getFname());
                    edt_LastNameVDR.setText(response.body().getCustomer_profile().get(0).getLname());
                    edt_mobile_numberVDR.setText(response.body().getCustomer_profile().get(0).getMoblino());
                    edt_whatsapp_numberVDR.setText(response.body().getCustomer_profile().get(0).getWhatsappno());
                    edt_StateVDR.setText(response.body().getCustomer_profile().get(0).getState());
                    edt_CityVDR.setText(response.body().getCustomer_profile().get(0).getCity());
                    edt_DistrictVDR.setText(response.body().getCustomer_profile().get(0).getDistric());
                    edt_TalukoVDR.setText(response.body().getCustomer_profile().get(0).getTehsil());
                    edt_VillageVDR.setText(response.body().getCustomer_profile().get(0).getVilage());


                    cuid = response.body().getCustomer_profile().get(0).getId();
                }

            }

            @Override
            public void onFailure(@NotNull Call<ViewCustomerProfileEditModel> call, @NotNull Throwable t) {

            }
        });


        btn_SubmitVDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(AddDrViewActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);



                WebService.getClient().getAddViewDRSubmit(emp,cuid).enqueue(new Callback<AddViewDeliveryReportSubmitModel>() {
                    @Override
                    public void onResponse(@NotNull Call<AddViewDeliveryReportSubmitModel> call,
                                           @NotNull Response<AddViewDeliveryReportSubmitModel> response) {
                        progressDialog.dismiss();


                        assert response.body() != null;
                        Toast.makeText(AddDrViewActivity.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();

                        Intent i = new Intent(AddDrViewActivity.this, FoDashbord.class);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(@NotNull Call<AddViewDeliveryReportSubmitModel> call,
                                          @NotNull Throwable t) {
                        progressDialog.dismiss();
                    }
                });

            }
        });



    }

}