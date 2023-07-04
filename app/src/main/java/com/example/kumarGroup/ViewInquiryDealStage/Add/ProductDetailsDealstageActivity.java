package com.example.kumarGroup.ViewInquiryDealStage.Add;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kumarGroup.ModelNameProductModel;
import com.example.kumarGroup.OtherProductModel;
import com.example.kumarGroup.ProductDetailNextModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsDealstageActivity extends AppCompatActivity {

    Spinner spn_AddPD_ProductName,spn_AddPD_ModelName;
    EditText edt_AddPD_description;
    Button btn_AddPD_Next;


    EditText edt_AddPD_date,edt_AddPD_Amount,edt_AddPD_description_other;
    String ProductName;
  //  String[] Product_Name = {"Select Product", "New Tractor","Old Tractor","Implement","Spar part"};
    String[] Product_Name = {"Select Product", "New Tractor","Old Tractor","Implement","Other"};

    List<String> ModelName = new ArrayList<>();
    List<String> ModelID = new ArrayList<>();


    String model_name,model_id;
    String NextId,phase;

    SharedPreferences sp1;
    String emp;

    SharedPreferences sharedPreferences;
   // String NextIDD;

    ProgressDialog progressDialog;

    String nextId;

    Calendar mcurrentdate;
    int day, month, year;


    String SmartCard;
    String[] Smart_Card = {"Select Smart Card Option", "Yes","No"};

    String other;
    String[] otherData={"Select Other", "Out","Debit"};

    String SmartCardYes;
    String[] Smart_CardYes = {"Select Type", "Referral","Repeat"};


    Spinner spn_smartCard_PD,spn_ScardYes_PD,spn_AddPD_desc_other;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        getSupportActionBar().setTitle("Product Details");

        spn_AddPD_ProductName = findViewById(R.id.spn_AddPD_ProductName);
        spn_AddPD_ModelName = findViewById(R.id.spn_AddPD_ModelName);
        spn_AddPD_desc_other = findViewById(R.id.spn_AddPD_desc_other);


        spn_AddPD_desc_other.setVisibility(View.GONE);

        edt_AddPD_description = findViewById(R.id.edt_AddPD_description);


        edt_AddPD_date = findViewById(R.id.edt_AddPD_date);
        edt_AddPD_Amount = findViewById(R.id.edt_AddPD_Amount);
        edt_AddPD_description_other = findViewById(R.id.edt_AddPD_description_other);


        spn_smartCard_PD = findViewById(R.id.spn_smartCard_PD);
        spn_ScardYes_PD = findViewById(R.id.spn_ScardYes_PD);

        btn_AddPD_Next = findViewById(R.id.btn_AddPD_Next);


        sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp1.getString("emp_id", "");


        sharedPreferences = getSharedPreferences("NextId1", MODE_PRIVATE);
       // NextIDD = sharedPreferences.getString("NextId","");
      //  sharedPreferences.edit().putInt("NextId",response.body().getId()).apply();

        NextId = getIntent().getStringExtra("nextId1");
        phase = getIntent().getStringExtra("phase");


        edt_AddPD_date.setVisibility(View.GONE);
        edt_AddPD_Amount.setVisibility(View.GONE);
        edt_AddPD_description_other.setVisibility(View.GONE);

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH);
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR);
        // month = month+1;

        edt_AddPD_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ProductDetailsDealstageActivity.this,
                        R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
                        monthofYear = monthofYear + 1;

                        String mt, dy;   //local variable
                        if (monthofYear < 10) {
                            mt = "0" + monthofYear; //if month less than 10 then ad 0 before month
                        } else {
                            mt = String.valueOf(monthofYear);
                        }

                        if (dayOfMonth < 10) {
                            dy = "0" + dayOfMonth;
                        } else {
                            dy = String.valueOf(dayOfMonth);
                        }
                        // EdtWalletSalarySlipDateOne.setText(dy+"/"+mt+"/"+year); 2021-04-13
                       // edt_AddPD_date.setText(mt + "/" + dy + "/" + year);
                        edt_AddPD_date.setText(year + "-" + mt + "-" + dy);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_AddPD_date.setFocusable(false);


        ArrayAdapter consumerSkim = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Smart_Card);
        spn_smartCard_PD.setAdapter(consumerSkim);

        spn_smartCard_PD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SmartCard = Smart_Card[position];
                if(Smart_Card[position].equals("Yes")){
                    spn_ScardYes_PD.setVisibility(View.VISIBLE);
                }
                else{
                    spn_ScardYes_PD.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter otheradapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                otherData);
        spn_AddPD_desc_other.setAdapter(otheradapter);

        spn_AddPD_desc_other.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(otherData[position].equals("Select Other Option")){
                    other="";
                }
                else{
                    other = otherData[position];
                    Log.d("other",other);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter ScardYes = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Smart_CardYes);
        spn_ScardYes_PD.setAdapter(ScardYes);

        spn_ScardYes_PD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SmartCardYes = Smart_CardYes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Product_Name);
        spn_AddPD_ProductName.setAdapter(adapter);

        spn_AddPD_ProductName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProductName = Product_Name[position];

                if (Product_Name[position].equals("Other")){

                    spn_AddPD_ModelName.setVisibility(View.GONE);
                    edt_AddPD_description.setVisibility(View.GONE);
                    spn_AddPD_desc_other.setVisibility(View.VISIBLE);

                    edt_AddPD_date.setVisibility(View.VISIBLE);
                    edt_AddPD_Amount.setVisibility(View.VISIBLE);
                    edt_AddPD_description_other.setVisibility(View.VISIBLE);
                }else{
                    spn_AddPD_ModelName.setVisibility(View.VISIBLE);
                    edt_AddPD_description.setVisibility(View.VISIBLE);
                    spn_AddPD_desc_other.setVisibility(View.GONE);


                    edt_AddPD_date.setVisibility(View.GONE);
                    edt_AddPD_Amount.setVisibility(View.GONE);
                    edt_AddPD_description_other.setVisibility(View.GONE);
                }

                if(ProductName.equals("Other")){
                    otherApi();
                }
                else{
                    regularApi();
                }

                    //  Toast.makeText(ProductDetailsActivity.this, ""+ProductName, Toast.LENGTH_SHORT).show();

                Log.d("product", "onItemSelected: "+ProductName);

                WebService.getClient().getModelName(ProductName).enqueue(new Callback<ModelNameProductModel>() {
                    @Override
                    public void onResponse(@NotNull Call<ModelNameProductModel> call, @NotNull Response<ModelNameProductModel> response)
                    {
                        if(response.isSuccessful()) {
                            if (response.body() != null) {
                                ModelName.clear();
                                ModelID.clear();

                                ModelName.add("Select Model");
                                ModelID.add("0");

                            //    Log.d("product", response.body().toString());

                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    ModelName.add(response.body().getData().get(i).getModel_name());
                                    ModelID.add(response.body().getData().get(i).getId());
                                }

                                Log.d("ProductS", "onResponse: "+response.body().getData().size());

                             //   Log.d("MProduct", ModelName.toString());

                                ArrayAdapter adapter2 = new ArrayAdapter(ProductDetailsDealstageActivity.this,
                                        android.R.layout.simple_spinner_dropdown_item, ModelName);
                                spn_AddPD_ModelName.setAdapter(adapter2);

                                spn_AddPD_ModelName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        model_name = ModelName.get(position);
                                        model_id = ModelID.get(position);
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });

                            }
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<ModelNameProductModel> call, @NotNull Throwable t) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


      /*  if(ProductName.equals("Other")){

            btn_AddPD_Next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ProductDetailsActivity.this, "Next Not Working", Toast.LENGTH_SHORT).show();

                }
            });


        }
        else {*/


       /* btn_AddPD_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(ProductDetailsActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                WebService.getClient().getProductDetail(
                        ProductName,
                        model_name,
                        edt_AddPD_description.getText().toString(),
                        "1").enqueue(new Callback<ProductDetailNextModel>() {
                    @Override
                    public void onResponse(Call<ProductDetailNextModel> call, Response<ProductDetailNextModel> response) {
                        progressDialog.dismiss();

                        nextId= String.valueOf(response.body().getId());

                        sharedPreferences = getSharedPreferences("NextId", MODE_PRIVATE);
                        sharedPreferences.edit().putString("NextId", String.valueOf(response.body().getId())).apply();
                        sharedPreferences.edit().putString("Product_Name", ProductName).apply();

                        Toast.makeText(ProductDetailsActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(ProductDetailsActivity.this,PriceDetailsAddActivity.class);
                        i.putExtra("nextId",nextId);
                       // i.putExtra("Product_Name",ProductName);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<ProductDetailNextModel> call, Throwable t) {
                        progressDialog.dismiss();

                    }
                });
            }
        });*/

       // }
    }

    private void regularApi() {
        btn_AddPD_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isvalidate1()) {
                    progressDialog = new ProgressDialog(ProductDetailsDealstageActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    WebService.getClient().getProductDetail(
                            ProductName,
                            model_name,
                            edt_AddPD_description.getText().toString(),
//                        other,
                            phase).enqueue(new Callback<ProductDetailNextModel>() {
                        @Override
                        public void onResponse(@NotNull Call<ProductDetailNextModel> call,
                                               @NotNull Response<ProductDetailNextModel> response) {
                            progressDialog.dismiss();

                            nextId = String.valueOf(response.body().getId());

                            sharedPreferences = getSharedPreferences("NextId", MODE_PRIVATE);
                            sharedPreferences.edit().putString("NextId", String.valueOf(response.body().getId())).apply();
                            sharedPreferences.edit().putString("Product_Name", ProductName).apply();
                            sharedPreferences.edit().putString("phase", phase).apply();


                            Toast.makeText(ProductDetailsDealstageActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(ProductDetailsDealstageActivity.this, PriceDetailsAddDealstageActivity.class);
                            i.putExtra("nextId1", nextId);
                            // i.putExtra("Product_Name",ProductName);
                            startActivity(i);

                        }

                        @Override
                        public void onFailure(@NotNull Call<ProductDetailNextModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();

                        }
                    });
                }


            }
        });
    }

    private void otherApi()
    {
        btn_AddPD_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isvalidate()) {
                    progressDialog = new ProgressDialog(ProductDetailsDealstageActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    // Toast.makeText(ProductDetailsActivity.this, "New Api", Toast.LENGTH_SHORT).show();

                    WebService.getClient().getOtherProduct(
                            ProductName,
                            edt_AddPD_date.getText().toString(),
                            edt_AddPD_Amount.getText().toString(),
                            edt_AddPD_description_other.getText().toString(),
                            other,
                            "2"

                    ).enqueue(new Callback<OtherProductModel>() {
                        @Override
                        public void onResponse(@NotNull Call<OtherProductModel> call,
                                               @NotNull Response<OtherProductModel> response) {
                            progressDialog.dismiss();

                            nextId = String.valueOf(response.body().getId());

                            sharedPreferences = getSharedPreferences("NextId", MODE_PRIVATE);
                            sharedPreferences.edit().putString("NextId1", String.valueOf(response.body().getId())).apply();
                            sharedPreferences.edit().putString("Product_Name", ProductName).apply();

                            Toast.makeText(ProductDetailsDealstageActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(ProductDetailsDealstageActivity.this, AddCustomerDetailsDealstageActivity.class);
                            i.putExtra("nextId1", nextId);
                            // i.putExtra("Product_Name",ProductName);
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(@NotNull Call<OtherProductModel> call,
                                              @NotNull Throwable t) {
                            progressDialog.dismiss();
                        }
                    });

                }
            }
        });
    }

    private boolean isvalidate() {
        if (spn_AddPD_ProductName.getSelectedItem().toString().trim().equals("Select Product")) {
            Utils.showErrorToast(ProductDetailsDealstageActivity.this, "Select Product");
            return false;
        }else if (edt_AddPD_date.getText().toString().equals("")){
            Utils.showErrorToast(ProductDetailsDealstageActivity.this, "Please enter Date");
            return false;
        }else if (edt_AddPD_Amount.getText().toString().equals("")){
            Utils.showErrorToast(ProductDetailsDealstageActivity.this, "Please enter Amount");
            return false;
        }else if (edt_AddPD_description_other.getText().toString().equals("")){
            Utils.showErrorToast(ProductDetailsDealstageActivity.this, "Please enter Other Description");
            return false;
        }else if (spn_AddPD_desc_other.getSelectedItem().toString().trim().equals("Select Other Option")) {
            Utils.showErrorToast(ProductDetailsDealstageActivity.this, "Select Other Option");
            return false;
        }else{
            return true;
        }
    }

    private boolean isvalidate1() {
        if (spn_AddPD_ProductName.getSelectedItem().toString().trim().equals("Select Product")) {
            Utils.showErrorToast(ProductDetailsDealstageActivity.this, "Select Product");
            return false;
        }else if (spn_AddPD_ModelName.getSelectedItem().toString().trim().equals("Select Model")) {
            Utils.showErrorToast(ProductDetailsDealstageActivity.this, "Select Model");
            return false;
        }else if (edt_AddPD_description.getText().toString().equals("")){
            Utils.showErrorToast(ProductDetailsDealstageActivity.this, "Please enter Description");
            return false;
        }else{
            return true;
        }
    }
}