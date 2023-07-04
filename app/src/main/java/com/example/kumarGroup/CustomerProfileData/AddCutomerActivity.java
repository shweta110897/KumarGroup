package com.example.kumarGroup.CustomerProfileData;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kumarGroup.AddCustomerModel;
import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.MobileDataAddCustomerProfileModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewCustomerProfileEditModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCutomerActivity extends AppCompatActivity {

    EditText edt_AddCustomer_MobileNo,edt_AddCustomer_Fname,edt_AddCustomer_LastName,
            edt_AddCustomer_WhatsappNo,edt_AddCustomer_State,edt_AddCustomer_City,
            edt_AddCustomer_District,edt_AddCustomer_Taluko,
            edt_AddCustomer_Village,edt_AddCustomer_Description;


    Button btn_AddCustomer_Next;

    ProgressDialog progressDialog;

    String getNum;

    String emp_id, catId,autoid,stateid,cityid,districid,tehsilid,vilageid,MNo;

    String EMPName;
    String MobileNo;

    SharedPreferences sp1;
    String emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cutomer);

        getSupportActionBar().setTitle("Add Customer Profile");


        edt_AddCustomer_MobileNo=findViewById(R.id.edt_AddCustomer_MobileNo);
        edt_AddCustomer_Fname=findViewById(R.id.edt_AddCustomer_Fname);
        edt_AddCustomer_LastName=findViewById(R.id.edt_AddCustomer_LastName);
        edt_AddCustomer_WhatsappNo=findViewById(R.id.edt_AddCustomer_WhatsappNo);
        edt_AddCustomer_State=findViewById(R.id.edt_AddCustomer_State);
        edt_AddCustomer_City=findViewById(R.id.edt_AddCustomer_City);
        edt_AddCustomer_District=findViewById(R.id.edt_AddCustomer_District);
        edt_AddCustomer_Taluko=findViewById(R.id.edt_AddCustomer_Taluko);
        edt_AddCustomer_Village=findViewById(R.id.edt_AddCustomer_Village);

        btn_AddCustomer_Next=findViewById(R.id.btn_AddCustomer_Next);

        edt_AddCustomer_Description=findViewById(R.id.edt_AddCustomer_Description);

        MobileNo = getIntent().getStringExtra("MobileNo");

        sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp1.getString("emp_id", "");

        progressDialog = new ProgressDialog(AddCutomerActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getViewCuProfileEdit(MobileNo).enqueue(new Callback<ViewCustomerProfileEditModel>() {
            @Override
            public void onResponse(@NotNull Call<ViewCustomerProfileEditModel> call,
                                   @NotNull Response<ViewCustomerProfileEditModel> response) {

                Log.d("edt_AddCustomer_Village",response.body().toString());

                assert response.body() != null;
                if (response.body().getCustomer_profile().size() == 0) {
                    Utils.showErrorToast(AddCutomerActivity.this, "No Data Available");
                } else {


                    edt_AddCustomer_Fname.setText(response.body().getCustomer_profile().get(0).getFname());
                    edt_AddCustomer_LastName.setText(response.body().getCustomer_profile().get(0).getLname());
                    edt_AddCustomer_MobileNo.setText(response.body().getCustomer_profile().get(0).getMoblino());
                    edt_AddCustomer_WhatsappNo.setText(response.body().getCustomer_profile().get(0).getWhatsappno());


                    edt_AddCustomer_State.setText(response.body().getCustomer_profile().get(0).getState());
                    edt_AddCustomer_City.setText(response.body().getCustomer_profile().get(0).getCity());
                    edt_AddCustomer_District.setText(response.body().getCustomer_profile().get(0).getDistric());
                    edt_AddCustomer_Taluko.setText(response.body().getCustomer_profile().get(0).getTehsil());
                    edt_AddCustomer_Village.setText(response.body().getCustomer_profile().get(0).getVilage());





                    progressDialog.dismiss();



                }
            }

            @Override
            public void onFailure(@NotNull Call<ViewCustomerProfileEditModel> call, @NotNull Throwable t) {

            }
        });



        edt_AddCustomer_MobileNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                getNum = edt_AddCustomer_MobileNo.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getNum = edt_AddCustomer_MobileNo.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

                getNum = edt_AddCustomer_MobileNo.getText().toString();
                //  Toast.makeText(AddCustomerDetailsActivity.this, ""+getNum, Toast.LENGTH_SHORT).show();

                if (getNum.length() == 10) {


                    WebService.getClient().getMobileDataAddCustomer(getNum).enqueue(new Callback<MobileDataAddCustomerProfileModel>() {
                        @Override
                        public void onResponse(@NotNull Call<MobileDataAddCustomerProfileModel> call,
                                               @NotNull Response<MobileDataAddCustomerProfileModel> response) {
                            try {
                                assert response.body() != null;
                                Log.d("1212", "onResponse: " + response.body().getCat().size()+MobileNo);
                                //  Toast.makeText(AddCustomerDetailsActivity.this, "" + response.body().getCat().get(0), Toast.LENGTH_SHORT).show();

                                edt_AddCustomer_Fname.setText(response.body().getCat().get(0).getFname());
                                edt_AddCustomer_LastName.setText(response.body().getCat().get(0).getLname());
                                edt_AddCustomer_WhatsappNo.setText(response.body().getCat().get(0).getWhatsappno());
                                edt_AddCustomer_State.setText(response.body().getCat().get(0).getState());
                                edt_AddCustomer_City.setText(response.body().getCat().get(0).getCity());
                                edt_AddCustomer_District.setText(response.body().getCat().get(0).getDistric());
                                edt_AddCustomer_Taluko.setText(response.body().getCat().get(0).getTehsil());
                                edt_AddCustomer_Village.setText(response.body().getCat().get(0).getVilage());

                                emp_id = response.body().getCat().get(0).getEid();
                                autoid = response.body().getCat().get(0).getAutoid();
                                stateid = response.body().getCat().get(0).getStateid();
                                cityid = response.body().getCat().get(0).getCityid();
                                districid = response.body().getCat().get(0).getDistricid();
                                tehsilid = response.body().getCat().get(0).getTehsilid();
                                vilageid = response.body().getCat().get(0).getVilageid();
                                MNo= response.body().getCat().get(0).getMoblino();
                                catId= response.body().getCat().get(0).getCat_id();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<MobileDataAddCustomerProfileModel> call, @NotNull Throwable t) {

                        }
                    });


                    /*WebService.getClient().getDetailsOnMobileNo(getNum).enqueue(new Callback<MobileNumDetailModel>() {
                        @Override
                        public void onResponse(@NotNull Call<MobileNumDetailModel> call, @NotNull Response<MobileNumDetailModel> response) {
                            //progressDialog.dismiss();
                            assert response.body() != null;
                            // Log.d("1212", "onResponse: "+response.body().getCat().get(0).getFname());
                            // Toast.makeText(AddCustomerDetailsActivity.this, ""+response.body().getCat().get(0), Toast.LENGTH_SHORT).show();

                            try {
                                Log.d("1212", "onResponse: " + response.body().getCat().size()+MobileNo);
                                //  Toast.makeText(AddCustomerDetailsActivity.this, "" + response.body().getCat().get(0), Toast.LENGTH_SHORT).show();

                                edt_AddCustomer_Fname.setText(response.body().getCat().get(0).getFname());
                                edt_AddCustomer_LastName.setText(response.body().getCat().get(0).getLname());
                                edt_AddCustomer_WhatsappNo.setText(response.body().getCat().get(0).getWhatsappno());
                                edt_AddCustomer_State.setText(response.body().getCat().get(0).getState());
                                edt_AddCustomer_City.setText(response.body().getCat().get(0).getCity());
                                edt_AddCustomer_District.setText(response.body().getCat().get(0).getDistric());
                                edt_AddCustomer_Taluko.setText(response.body().getCat().get(0).getTehsil());
                                edt_AddCustomer_Village.setText(response.body().getCat().get(0).getVilage());


                                emp_id= response.body().getCat().get(0).getEmployee_name();

                                EMPName = response.body().getCat().get(0).getEid();

                                catId= response.body().getCat().get(0).getCat_id();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<MobileNumDetailModel> call, @NotNull Throwable t) {
                            // progressDialog.dismiss();
                            Toast.makeText(AddCutomerActivity.this, "" + t.getCause(), Toast.LENGTH_SHORT).show();
                        }
                    });*/

                }
            }

        });

        btn_AddCustomer_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt_AddCustomer_Description.getText().toString().equals("")) {
                    Toast.makeText(AddCutomerActivity.this, "Please Enter Description First", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog = new ProgressDialog(AddCutomerActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                /*emp_id = response.body().getCat().get(0).getEid();
                                autoid = response.body().getCat().get(0).getAutoid();
                                stateid = response.body().getCat().get(0).getStateid();
                                cityid = response.body().getCat().get(0).getCityid();
                                districid = response.body().getCat().get(0).getDistricid();
                                tehsilid = response.body().getCat().get(0).getTehsilid();
                                vilageid = response.body().getCat().get(0).getVilageid();

                                catId= response.body().getCat().get(0).getCat_id();*/


                    WebService.getClient().getAddCustomerProfile(
                            edt_AddCustomer_MobileNo.getText().toString(),
                            emp,
                            catId,
                            edt_AddCustomer_Fname.getText().toString(),
                            edt_AddCustomer_LastName.getText().toString(),
                            stateid,
                            cityid,
                            districid,
                            tehsilid,
                            vilageid,
                            MNo,
                            edt_AddCustomer_WhatsappNo.getText().toString(),
                            emp_id,
                            edt_AddCustomer_Description.getText().toString(),
                            autoid
                    ).enqueue(new Callback<AddCustomerModel>() {
                        @Override
                        public void onResponse(@NotNull Call<AddCustomerModel> call, @NotNull Response<AddCustomerModel> response) {
                            progressDialog.dismiss();

                            assert response.body() != null;

                            Toast.makeText(AddCutomerActivity.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();

                            Intent i = new Intent(AddCutomerActivity.this, FoDashbord.class);
                            startActivity(i);

                        }

                        @Override
                        public void onFailure(@NotNull Call<AddCustomerModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(AddCutomerActivity.this, "" + t.getCause(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}