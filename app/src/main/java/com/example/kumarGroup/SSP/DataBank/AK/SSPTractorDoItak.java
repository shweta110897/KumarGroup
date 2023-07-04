package com.example.kumarGroup.SSP.DataBank.AK;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kumarGroup.CategoryModel;
import com.example.kumarGroup.InquiryDataBankModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SSPTractorDoItak extends AppCompatActivity {

    Spinner sp_category,sp_main_Category;
    Button btn_next;

    //  String[] sp_Iteam = {"All Sonalika ", "Sell Lost Tractor ", "Sell Lost Mini Tractor ", "Old Tractor ", "Old Mini Tractor ", "Other Vehicles ", "JCB", "Service Center", "spar- Part shop", "Agro shop", "General store", "Agri-impliment Makers", "Milk Center", "petrol Pump", "small Business Unit", "Building materials Maker", "Hotel", "Hospital", "Sarpanch", "up-sarpanch", "vCE", "Gram sevak", "Talati Mantri", "insurance Agent", "school Teacher", "Broker", "Bank", "Trust /Mandir /Go-shala", "Seating point", "Subsidy List", "Local Mandli"};

    List<String> category = new ArrayList<>();
    List<String> categoryId = new ArrayList<>();
    List<String> categoryStatus = new ArrayList<>();

    List<String> Inquiry = new ArrayList<>();
    List<String> InquiryId = new ArrayList<>();
    List<String> InquiryStatus = new ArrayList<>();

    String cid ,Status, Item;
    String data, state;
    ProgressDialog progressDialog;

    String MainCat;
    String[] Main_Category = {"Select Item","General", "Inquiry"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssptractor_do_itak);

        getSupportActionBar().setTitle("SSP It");

        sp_category = findViewById(R.id.sp_category);
        sp_main_Category = findViewById(R.id.sp_main_Category);
        btn_next = findViewById(R.id.btn_next);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Main_Category);
        sp_main_Category.setAdapter(adapter);

        sp_main_Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainCat = Main_Category[position];


                if (Main_Category[position].equals("General")) {

                    progressDialog= new ProgressDialog(SSPTractorDoItak.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                    WebService.getClient().getcatlist().enqueue(new Callback<CategoryModel>() {
                        @Override
                        public void onResponse(@NotNull Call<CategoryModel> call,
                                               @NotNull Response<CategoryModel> response) {
//                Log.d("Banks", response.body().toString());
                            progressDialog.dismiss();

                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    category.clear();
                                    categoryId.clear();
                                    categoryStatus.clear();

                                    category.add("Select Category");
                                    categoryId.add("0");
                                    categoryStatus.add("0");

                                    Log.d("Banks", response.body().toString());
                                    for (int i = 0; i < response.body().getData().size(); i++)
                                    {
                                        category.add(response.body().getData().get(i).getCat_list());
                                        categoryId.add(response.body().getData().get(i).getCat_id());
                                        categoryStatus.add(response.body().getData().get(i).getStatus());
                                    }
                                    Log.d("Banks", category.toString());
                                    ArrayAdapter adapter2 = new ArrayAdapter(SSPTractorDoItak.this, android.R.layout.simple_spinner_dropdown_item, category);
                                    sp_category.setAdapter(adapter2);

                                    sp_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            data = category.get(position);
                                            cid = categoryId.get(position);
                                            Status =categoryStatus.get(position);

                                            Item = "General";

                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<CategoryModel> call, @NotNull Throwable t) {
                            Log.d("error", "onFailure: " + t);
                            progressDialog.dismiss();

                            Toast.makeText(SSPTractorDoItak.this, "Error Rety", Toast.LENGTH_SHORT).show();

                        }
                    });
                }

                if (Main_Category[position].equals("Inquiry"))
                {
                    progressDialog= new ProgressDialog(SSPTractorDoItak.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    WebService.getClient().getInquiryData().enqueue(new Callback<InquiryDataBankModel>() {
                        @Override
                        public void onResponse(@NotNull Call<InquiryDataBankModel> call,
                                               @NotNull Response<InquiryDataBankModel> response) {
                            progressDialog.dismiss();

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
                                    ArrayAdapter adapter2 = new ArrayAdapter(SSPTractorDoItak.this,
                                            android.R.layout.simple_spinner_dropdown_item, Inquiry);
                                    sp_category.setAdapter(adapter2);

                                    sp_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            data = Inquiry.get(position);
                                            cid = InquiryId.get(position);
                                            Status = InquiryStatus.get(position);

                                            Item = "Inquiry";
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SSPTractorDoItak.this, SSPGaneralInformationAK.class);
                i.putExtra("categoryId",cid);
                i.putExtra("Status",Status);
                i.putExtra("Item",Item);
                startActivity(i);

            }
        });

    }
}