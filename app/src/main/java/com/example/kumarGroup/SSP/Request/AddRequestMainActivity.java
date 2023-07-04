package com.example.kumarGroup.SSP.Request;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kumarGroup.AddRequestSubmitSSPModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.RequestProductListModel;
import com.example.kumarGroup.SSP.SSPDashboardActivity;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.VendorAddRequestSSPMode;
import com.example.kumarGroup.ViewCustomerProfileEditModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRequestMainActivity extends AppCompatActivity {


    Spinner spn_productName_AddR, spn_Vendor_AddR;

    EditText edt_MobileNo_AddR, edt_CustomerName_AddR, edt_Village_AddR;


    List<String> product = new ArrayList<>();
    List<String> productId = new ArrayList<>();


    List<String> City = new ArrayList<>();
    List<String> cityid = new ArrayList<>();

    String pro, proid;
    String ven, venid;

    String emp_id_ws, nextId;
    String MobileNo_WS;
    String EMPName_Ws;
    String Cuid;

    Button btn_Submit_Form;

    SharedPreferences sp;
    String emp_id;
    String emp_name;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request_main);


        getSupportActionBar().setTitle("Request");


        sp = getSharedPreferences("Login", MODE_PRIVATE);

        emp_id = sp.getString("emp_id_SSP", "");
        emp_name = sp.getString("emp_name_SSP", "");



        spn_productName_AddR = findViewById(R.id.spn_productName_AddR);
        spn_Vendor_AddR = findViewById(R.id.spn_Vendor_AddR);
        edt_MobileNo_AddR = findViewById(R.id.edt_MobileNo_AddR);
        edt_CustomerName_AddR = findViewById(R.id.edt_CustomerName_AddR);
        edt_Village_AddR = findViewById(R.id.edt_Village_AddR);
        btn_Submit_Form = findViewById(R.id.btn_Submit_Form);


        WebService.getClient().getRequsteProductAdd().enqueue(new Callback<RequestProductListModel>() {
            @Override
            public void onResponse(@NotNull Call<RequestProductListModel> call,
                                   @NotNull Response<RequestProductListModel> response) {

                product.clear();
                productId.clear();




                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        product.add("Select Product");
                        productId.add("0");
                        Log.d("Banks", response.body().toString());
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            product.add(response.body().getData().get(i).getState());
                            productId.add(response.body().getData().get(i).getId());
                        }


                        ArrayAdapter adapter2 = new ArrayAdapter(AddRequestMainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, product);
                        spn_productName_AddR.setAdapter(adapter2);

                        spn_productName_AddR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                pro = product.get(position);
                                proid = productId.get(position);


                                WebService.getClient().getVendorAddRequestSSP(pro).enqueue(new Callback<VendorAddRequestSSPMode>() {
                                    @Override
                                    public void onResponse(@NotNull Call<VendorAddRequestSSPMode> call,
                                                           @NotNull Response<VendorAddRequestSSPMode> response) {

                                        City.clear();
                                        cityid.clear();



                                        if (response.isSuccessful()) {
                                            if (response.body() != null) {
                                                City.add("Select Vendor");
                                                cityid.add("0");

//                                                Log.d("City", response1.body().getCity().get(0).getCity());
                                                for (int i = 0; i < response.body().getCity().size(); i++) {
                                                    City.add(response.body().getCity().get(i).getVname());
                                                    cityid.add(response.body().getCity().get(i).getVendor_id());
                                                }
                                                //       final List<String> cityarray = Arrays.asList(response1.body().getCity().toString().replace("city="," ").split(","));

                                                ArrayAdapter adapter3 = new ArrayAdapter(AddRequestMainActivity.this, android.R.layout.simple_spinner_dropdown_item, City);
                                                spn_Vendor_AddR.setAdapter(adapter3);

                                                spn_Vendor_AddR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



                                                        ArrayAdapter adapter3 = new ArrayAdapter(AddRequestMainActivity.this, android.R.layout.simple_spinner_dropdown_item, City);
                                                        spn_Vendor_AddR.setAdapter(adapter3);


                                                        spn_Vendor_AddR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                            @Override
                                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                ven = City.get(position);
                                                                venid = cityid.get(position);
                                                            }

                                                            @Override
                                                            public void onNothingSelected(AdapterView<?> parent) {

                                                            }
                                                        });

                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(@NotNull Call<VendorAddRequestSSPMode> call, @NotNull Throwable t) {

                                    }
                                });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }

                }



            }

            @Override
            public void onFailure(@NotNull Call<RequestProductListModel> call, @NotNull Throwable t) {

            }
        });



        edt_MobileNo_AddR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (edt_MobileNo_AddR.getText().toString().length() == 10) {

                    WebService.getClient().getViewCuProfileEdit(edt_MobileNo_AddR.getText().toString()).enqueue(new Callback<ViewCustomerProfileEditModel>() {
                        @Override
                        public void onResponse(@NotNull Call<ViewCustomerProfileEditModel> call,
                                               @NotNull Response<ViewCustomerProfileEditModel> response) {

                            assert response.body() != null;
                            if (response.body().getCustomer_profile().size() == 0) {
                                Utils.showErrorToast(AddRequestMainActivity.this, "No Data Available");
                            } else {



                                edt_CustomerName_AddR.setText(response.body().getCustomer_profile().get(0).getFname()+" "
                                +response.body().getCustomer_profile().get(0).getLname());
                                edt_Village_AddR.setText(response.body().getCustomer_profile().get(0).getVilage());


                                Cuid = response.body().getCustomer_profile().get(0).getId();
                            }

                        }

                        @Override
                        public void onFailure(@NotNull Call<ViewCustomerProfileEditModel> call, @NotNull Throwable t) {

                        }
                    });
                } else {

                }
            }
        });


        btn_Submit_Form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(AddRequestMainActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);



                WebService.getClient().getAddRequestSSpSubmit(pro,
                        venid,
                        edt_MobileNo_AddR.getText().toString(),
                        Cuid,
                        emp_id).enqueue(new Callback<AddRequestSubmitSSPModel>() {
                    @Override
                    public void onResponse(@NotNull Call<AddRequestSubmitSSPModel> call,
                                           @NotNull Response<AddRequestSubmitSSPModel> response) {
                        progressDialog.dismiss();

                        assert response.body() != null;
                        Toast.makeText(AddRequestMainActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(AddRequestMainActivity.this, SSPDashboardActivity.class);

                        startActivity(i);

                    }

                    @Override
                    public void onFailure(@NotNull Call<AddRequestSubmitSSPModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(AddRequestMainActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


    }
}