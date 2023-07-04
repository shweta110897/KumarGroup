package com.example.kumarGroup.BookingDeliveryUpload.Add;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.kumarGroup.InquiryDataBankModel;
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

public class ProductDetailsActivity extends AppCompatActivity {

    Spinner spn_AddPD_ProductName,spn_AddPD_ModelName;
    EditText edt_AddPD_description;
    Button btn_AddPD_Next;


    EditText edt_AddPD_date,edt_AddPD_Amount,edt_AddPD_description_other;
    String ProductName;
  //  String[] Product_Name = {"Select Product", "New Tractor","Old Tractor","Implement","Spar part"};
    String[] Product_Name = {"Select Product", "New Tractor","Old Tractor","Implement","Other"};

    List<String> catNames = new ArrayList<>();
    List<String> ModelName = new ArrayList<>();
    List<String> ModelID = new ArrayList<>();


    String model_name,model_id;
    String NextId;

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
    String[] otherData={"Select Other Option", "Out","Debit"};

    String SmartCardYes;
    String[] Smart_CardYes = {"Select Type", "Referral","Repeat"};


    Spinner spn_smartCard_PD,spn_ScardYes_PD,spn_AddPD_desc_other,spn_AddPD_cat_other;

    List<String> category = new ArrayList<>();
    List<String> categoryId = new ArrayList<>();
    List<String> categoryStatus = new ArrayList<>();

    List<String> Inquiry = new ArrayList<>();
    List<String> InquiryId = new ArrayList<>();
    List<String> InquiryStatus = new ArrayList<>();

    String cid ,Status, data;
    int uposition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        getSupportActionBar().setTitle("Product Details");

        spn_AddPD_ProductName = findViewById(R.id.spn_AddPD_ProductName);
        spn_AddPD_ModelName = findViewById(R.id.spn_AddPD_ModelName);
        spn_AddPD_desc_other = findViewById(R.id.spn_AddPD_desc_other);
        spn_AddPD_cat_other = findViewById(R.id.spn_AddPD_cat_other);

        edt_AddPD_description = findViewById(R.id.edt_AddPD_description);


        edt_AddPD_date = findViewById(R.id.edt_AddPD_date);
        edt_AddPD_Amount = findViewById(R.id.edt_AddPD_Amount);
        edt_AddPD_description_other = findViewById(R.id.edt_AddPD_description_other);


        spn_smartCard_PD = findViewById(R.id.spn_smartCard_PD);
        spn_ScardYes_PD = findViewById(R.id.spn_ScardYes_PD);

        btn_AddPD_Next = findViewById(R.id.btn_AddPD_Next);


        sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp1.getString("emp_id", "");


        sharedPreferences = getSharedPreferences("NextId", MODE_PRIVATE);
       // NextIDD = sharedPreferences.getString("NextId","");
      //  sharedPreferences.edit().putInt("NextId",response.body().getId()).apply();

        NextId = getIntent().getStringExtra("nextId");


        edt_AddPD_date.setVisibility(View.GONE);
        edt_AddPD_Amount.setVisibility(View.GONE);
        edt_AddPD_description_other.setVisibility(View.GONE);
        spn_AddPD_desc_other.setVisibility(View.GONE);

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH);
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR);
        // month = month+1;

        edt_AddPD_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ProductDetailsActivity.this,
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
                    spn_AddPD_cat_other.setVisibility(View.GONE);

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

                                ArrayAdapter adapter2 = new ArrayAdapter(ProductDetailsActivity.this,
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


        WebService.getClient().getInquiryData().enqueue(new Callback<InquiryDataBankModel>() {
            @Override
            public void onResponse(@NotNull Call<InquiryDataBankModel> call,
                                   @NotNull Response<InquiryDataBankModel> response) {


                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Inquiry.clear();
                        InquiryId.clear();
                        InquiryStatus.clear();

                        Inquiry.add("Select Category");
                        InquiryId.add("0");
                        InquiryStatus.add("0");

                        Log.d("Banks", response.body().toString());

                        for (int i = 0; i < response.body().getData().size(); i++)
                        {
                            Inquiry.add(response.body().getData().get(i).getCat_list());
                            InquiryId.add(response.body().getData().get(i).getCat_id());
                            InquiryStatus.add(response.body().getData().get(i).getStatus());

                        }

                        Log.d("Banks_one", category.toString());
                        ArrayAdapter adapter2 = new ArrayAdapter(ProductDetailsActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, Inquiry);
                        spn_AddPD_cat_other.setAdapter(adapter2);

                        spn_AddPD_cat_other.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                data = Inquiry.get(position);
                                cid = InquiryId.get(position);
                                Status = InquiryStatus.get(position);
                                Log.d("cat__id",cid);
                               /* if (position==0){
                                    uposition = position;
                                }else {
                                    uposition = position-1;
                                }*/

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<InquiryDataBankModel> call, @NotNull Throwable t) {

            }
        });

    }

    private void regularApi() {
        btn_AddPD_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isvalidate1()){
                    progressDialog = new ProgressDialog(ProductDetailsActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    WebService.getClient().getProductDetail(
                            ProductName,
                            model_name,
                            edt_AddPD_description.getText().toString(),
//                        other,
                            "1").enqueue(new Callback<ProductDetailNextModel>() {
                        @Override
                        public void onResponse(@NotNull Call<ProductDetailNextModel> call,
                                               @NotNull Response<ProductDetailNextModel> response) {
                            progressDialog.dismiss();

                            nextId= String.valueOf(response.body().getId());

                            sharedPreferences = getSharedPreferences("NextId", MODE_PRIVATE);
                            sharedPreferences.edit().putString("NextId", String.valueOf(response.body().getId())).apply();
                            sharedPreferences.edit().putString("CatId", cid).apply();
                            Log.d("pref_cid",cid);
                            sharedPreferences.edit().putString("Product_Name", ProductName).apply();


                            Toast.makeText(ProductDetailsActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(ProductDetailsActivity.this,PriceDetailsAddActivity.class);
                            i.putExtra("nextId",nextId);
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

                if (isvalidate()){
                    progressDialog = new ProgressDialog(ProductDetailsActivity.this);
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

                            nextId= String.valueOf(response.body().getId());

                            sharedPreferences = getSharedPreferences("NextId", MODE_PRIVATE);
                            sharedPreferences.edit().putString("NextId", String.valueOf(response.body().getId())).apply();
                            sharedPreferences.edit().putString("Product_Name", ProductName).apply();

                            Toast.makeText(ProductDetailsActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(ProductDetailsActivity.this,AddCustomerDetailsOtherActivity.class);
                            i.putExtra("nextId",nextId);
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
            Utils.showErrorToast(ProductDetailsActivity.this, "Select Product");
            return false;
        }else if (edt_AddPD_date.getText().toString().equals("")){
            Utils.showErrorToast(ProductDetailsActivity.this, "Please enter Date");
            return false;
        }else if (edt_AddPD_Amount.getText().toString().equals("")){
            Utils.showErrorToast(ProductDetailsActivity.this, "Please enter Amount");
            return false;
        }else if (edt_AddPD_description_other.getText().toString().equals("")){
            Utils.showErrorToast(ProductDetailsActivity.this, "Please enter Other Description");
            return false;
        }else if (spn_AddPD_desc_other.getSelectedItem().toString().trim().equals("Select Other Option")) {
            Utils.showErrorToast(ProductDetailsActivity.this, "Select Other Option");
            return false;
        }else{
            return true;
        }
    }

    private boolean isvalidate1() {
        if (spn_AddPD_ProductName.getSelectedItem().toString().trim().equals("Select Product")) {
            Utils.showErrorToast(ProductDetailsActivity.this, "Select Product");
            return false;
        }else if (spn_AddPD_ModelName.getSelectedItem().toString().trim().equals("Select Model")) {
            Utils.showErrorToast(ProductDetailsActivity.this, "Select Model");
            return false;
        }else if (edt_AddPD_description.getText().toString().equals("")){
            Utils.showErrorToast(ProductDetailsActivity.this, "Please enter Description");
            return false;
        }else if (spn_AddPD_cat_other.getSelectedItem().toString().trim().equals("Select Category")) {
            Utils.showErrorToast(ProductDetailsActivity.this, "Select Category");
            return false;
        }else{
            return true;
        }
    }
}