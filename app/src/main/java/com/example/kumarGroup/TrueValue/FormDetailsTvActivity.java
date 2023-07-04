package com.example.kumarGroup.TrueValue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.TrueValueFormOneModel;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewCustomerProfileEditModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormDetailsTvActivity extends AppCompatActivity {

    String MobileNo;

    EditText edt_state_TV,edt_city_TV,
            edt_District_TV,edt_Tehsil_TV,edt_Village_TV;

    EditText edt_FnameName_TV,edt_LastName_TV,edt_mobile_number_TV,
            edt_categoryName_EP;



    Button btn_Submit_TvFormDetail;

    SharedPreferences sp;
    String emp;


    ProgressDialog progressDialog,progressDialog1;



    String mainId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_details_tv);


        getSupportActionBar().setTitle("True Value");


        edt_categoryName_EP = findViewById(R.id.edt_categoryName_EP);

        edt_FnameName_TV = findViewById(R.id.edt_FnameName_TV);
        edt_LastName_TV = findViewById(R.id.edt_LastName_TV);

        edt_state_TV = findViewById(R.id.edt_state_TV);
        edt_city_TV = findViewById(R.id.edt_city_TV);
        edt_District_TV = findViewById(R.id.edt_District_TV);
        edt_Tehsil_TV = findViewById(R.id.edt_Tehsil_TV);
        edt_Village_TV = findViewById(R.id.edt_Village_TV);

        edt_mobile_number_TV = findViewById(R.id.edt_mobile_number_TV);
        btn_Submit_TvFormDetail = findViewById(R.id.btn_Submit_TvFormDetail);

        MobileNo = getIntent().getStringExtra("MobileNo");


        sp = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp.getString("emp_id", "");


        Log.d("EMP_ID", "onCreate: " + emp);

        edt_categoryName_EP.setFocusable(false);
        edt_FnameName_TV.setFocusable(false);
        edt_LastName_TV.setFocusable(false);
        edt_state_TV.setFocusable(false);
        edt_city_TV.setFocusable(false);
        edt_District_TV.setFocusable(false);
        edt_Tehsil_TV.setFocusable(false);
        edt_Village_TV.setFocusable(false);
        edt_mobile_number_TV.setFocusable(false);


        progressDialog = new ProgressDialog(FormDetailsTvActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getViewCuProfileEdit(MobileNo).enqueue(new Callback<ViewCustomerProfileEditModel>() {
            @Override
            public void onResponse(@NotNull Call<ViewCustomerProfileEditModel> call,
                                   Response<ViewCustomerProfileEditModel> response) {
                assert response.body() != null;
                progressDialog.dismiss();

                if (response.body().getCustomer_profile().size() == 0) {
                    Utils.showErrorToast(FormDetailsTvActivity.this, "No Data Available");

                    btn_Submit_TvFormDetail.setClickable(false);

                } else {

                    edt_categoryName_EP.setText(response.body().getCustomer_profile().get(0).getCat_name());

                    edt_FnameName_TV.setText(response.body().getCustomer_profile().get(0).getFname());
                    edt_LastName_TV.setText(response.body().getCustomer_profile().get(0).getLname());
                    edt_mobile_number_TV.setText(response.body().getCustomer_profile().get(0).getMoblino());

                    edt_state_TV.setText(response.body().getCustomer_profile().get(0).getState());
                    edt_city_TV.setText(response.body().getCustomer_profile().get(0).getCity());
                    edt_District_TV.setText(response.body().getCustomer_profile().get(0).getDistric());
                    edt_Tehsil_TV.setText(response.body().getCustomer_profile().get(0).getTehsil());
                    edt_Village_TV.setText(response.body().getCustomer_profile().get(0).getVilage());

                    mainId = response.body().getCustomer_profile().get(0).getId();

                }
                }

            @Override
            public void onFailure(@NotNull Call<ViewCustomerProfileEditModel> call, @NotNull Throwable t) {

            }
        });


       /* WebService.getClient().getViewProfileDetail(MobileNo).enqueue(new Callback<ViewCustomerProfileDataModel>() {
            @Override
            public void onResponse(@NotNull Call<ViewCustomerProfileDataModel> call,
                                   @NotNull Response<ViewCustomerProfileDataModel> response) {
                assert response.body() != null;
                progressDialog.dismiss();

                if (response.body().getCustomer_profile().size() == 0) {
                    Utils.showErrorToast(FormDetailsTvActivity.this, "No Data Available");

                    btn_Submit_TvFormDetail.setClickable(false);

                } else {

                    edt_categoryName_EP.setText(response.body().getCustomer_profile().get(0).getCat_name());
                    edt_FnameName_TV.setText(response.body().getCustomer_profile().get(0).getFname());
                    edt_LastName_TV.setText(response.body().getCustomer_profile().get(0).getLname());
                    edt_mobile_number_TV.setText(response.body().getCustomer_profile().get(0).getMoblino());

                    edt_state_TV.setText(response.body().getCustomer_profile().get(0).getState());
                    edt_city_TV.setText(response.body().getCustomer_profile().get(0).getCity());
                    edt_District_TV.setText(response.body().getCustomer_profile().get(0).getDistric());
                    edt_Tehsil_TV.setText(response.body().getCustomer_profile().get(0).getTehsil());
                    edt_Village_TV.setText(response.body().getCustomer_profile().get(0).getVilage());

                    mainId = response.body().getCustomer_profile().get(0).getId();

                }
            }

            @Override
            public void onFailure(@NotNull Call<ViewCustomerProfileDataModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();

            }
        });*/


        btn_Submit_TvFormDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog1 = new ProgressDialog(FormDetailsTvActivity.this);
                progressDialog1.show();
                progressDialog1.setContentView(R.layout.progress_dialog);
                progressDialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);




              //  Toast.makeText(FormDetailsTvActivity.this, ""+mainId+" "+MobileNo+" "+emp, Toast.LENGTH_SHORT).show();

                WebService.getClient().getTrueValCheckOneForm(MobileNo,
                        mainId,
                        emp,
                       "1").enqueue(new Callback<TrueValueFormOneModel>() {
                    @Override
                    public void onResponse(@NotNull Call<TrueValueFormOneModel> call,
                                           @NotNull Response<TrueValueFormOneModel> response) {

                        progressDialog1.dismiss();

                        assert response.body() != null;
                        Toast.makeText(FormDetailsTvActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(FormDetailsTvActivity.this, TvThirdFormActivity.class);
                        i.putExtra("Id_res",response.body().getId()+"");
                        startActivity(i);

                     //   Toast.makeText(FormDetailsTvActivity.this, ""+response.body().getId(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(@NotNull Call<TrueValueFormOneModel> call, @NotNull Throwable t) {
                        progressDialog1.dismiss();
                    }
                });


                /*Intent i = new Intent(FormDetailsTvActivity.this, TvThirdFormActivity.class);
                progressDialog1.dismiss();
                startActivity(i);*/
            }
        });

    }
}