package com.example.kumarGroup.TrueValue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.TrueValueBrandModel;
import com.example.kumarGroup.TrueValueBrandModel_Model;
import com.example.kumarGroup.TvAddFormTwoModel;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvThirdFormActivity extends AppCompatActivity {


    Spinner spn_TvBrand,spn_TvModel,spn_Owner,spn_Variant,spn_Stearing,
            spn_Gear,spn_TireCondition;

    EditText edt_MFGYear;

    Button btn_ThirdForm_TV;


    String Owner;
    String[] OwnerOption = {"Select Owners", "1st", "2nd","3rd","4th","4th Above"};


    String Variant;
    String[] VariantOption = {"Select Variant", "2WD", "4WD"};



    String Steering;
    String[] SteeringOption = {"Select Steering Type", "Power", "Manual"};


    String Gear;
    String[] GEarOption = {"Select Gear Type", "Side Shift", "Center"};


    String tireCondition;
    String[] TireConditionOption = {"Select TireCondition", "10-20%", "30-40%","50-60%",
    "70-80%","90-100%"};



    String BrandName, BrandID;
    String BrandModelName, BrandModelID;

    List<String> Brand = new ArrayList<>();
    List<String> BrandId = new ArrayList<>();


    List<String> BrandModel = new ArrayList<>();
    List<String> BrandModelId = new ArrayList<>();

    String Id_res;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_third_form);

        getSupportActionBar().setTitle("True Value");


        spn_TvBrand=findViewById(R.id.spn_TvBrand);
        spn_TvModel=findViewById(R.id.spn_TvModel);
        spn_Owner=findViewById(R.id.spn_Owner);
        spn_Variant=findViewById(R.id.spn_Variant);
        spn_Stearing=findViewById(R.id.spn_Stearing);
        spn_Gear=findViewById(R.id.spn_Gear);
        spn_TireCondition=findViewById(R.id.spn_TireCondition);

        edt_MFGYear=findViewById(R.id.edt_MFGYear);

        btn_ThirdForm_TV=findViewById(R.id.btn_ThirdForm_TV);


        Id_res= getIntent().getStringExtra("Id_res");
      //  Toast.makeText(this, ""+Id_res, Toast.LENGTH_SHORT).show();

        ArrayAdapter TractorArray = new ArrayAdapter(TvThirdFormActivity.this,
                android.R.layout.simple_spinner_dropdown_item, OwnerOption);
        spn_Owner.setAdapter(TractorArray);

        spn_Owner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Owner = OwnerOption[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter variant = new ArrayAdapter(TvThirdFormActivity.this,
                android.R.layout.simple_spinner_dropdown_item, VariantOption);
        spn_Variant.setAdapter(variant);

        spn_Variant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Variant = VariantOption[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter steering = new ArrayAdapter(TvThirdFormActivity.this,
                android.R.layout.simple_spinner_dropdown_item, SteeringOption);
        spn_Stearing.setAdapter(steering);

        spn_Stearing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Steering = SteeringOption[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter gear = new ArrayAdapter(TvThirdFormActivity.this,
                android.R.layout.simple_spinner_dropdown_item, GEarOption);
        spn_Gear.setAdapter(gear);

        spn_Gear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Gear = GEarOption[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




       /* for (int i=0; i<=100; i++){
            if(i%10!=0){
                tireValue.add(i);
            }
        }*/



        ArrayAdapter tireCondi = new ArrayAdapter(TvThirdFormActivity.this,
                android.R.layout.simple_spinner_dropdown_item, TireConditionOption);
        spn_TireCondition.setAdapter(tireCondi);

        spn_TireCondition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tireCondition = TireConditionOption[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        WebService.getClient().getTrueValueBrand().enqueue(new Callback<TrueValueBrandModel>() {
            @Override
            public void onResponse(@NotNull Call<TrueValueBrandModel> call,
                                   @NotNull Response<TrueValueBrandModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        Brand.clear();
                        BrandId.clear();

                        Brand.add("Select Brand");
                        BrandId.add("0");

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            Brand.add(response.body().getData().get(i).getBrand());
                            BrandId.add(response.body().getData().get(i).getId());
                        }


                        ArrayAdapter category = new ArrayAdapter(TvThirdFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, Brand);
                        spn_TvBrand.setAdapter(category);

                        spn_TvBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                BrandName= Brand.get(position);
                                BrandID= BrandId.get(position);


                                WebService.getClient().getTrueValBrandModel(BrandID).enqueue(new Callback<TrueValueBrandModel_Model>() {
                                    @Override
                                    public void onResponse(@NotNull Call<TrueValueBrandModel_Model> call,
                                                           @NotNull Response<TrueValueBrandModel_Model> response) {
                                        BrandModel.clear();
                                        BrandModelId.clear();


                                        if (response.isSuccessful()) {
                                            if (response.body() != null) {
                                                BrandModel.add("Select Model");
                                                BrandModelId.add("0");

                                                for (int i = 0; i < response.body().getData().size(); i++) {
                                                    BrandModel.add(response.body().getData().get(i).getModel_name());
                                                    BrandModelId.add(response.body().getData().get(i).getModel_id());
                                                }

                                                ArrayAdapter adapter3 = new ArrayAdapter(TvThirdFormActivity.this,
                                                        android.R.layout.simple_spinner_dropdown_item, BrandModel);
                                                spn_TvModel.setAdapter(adapter3);

                                                spn_TvModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        BrandModelID = BrandModelId.get(position);
                                                        BrandModelName = BrandModel.get(position);
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });
                                            }
                                        }

                                    }

                                    @Override
                                    public void onFailure(@NotNull Call<TrueValueBrandModel_Model> call,
                                                          @NotNull Throwable t) {

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
            public void onFailure(@NotNull Call<TrueValueBrandModel> call, @NotNull Throwable t) {

            }
        });




        btn_ThirdForm_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(TvThirdFormActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                if (edt_MFGYear.getText().toString().equals("")) {
                    edt_MFGYear.setError("Please Enter MFG Year");
                }

               else if (BrandName.equals("Select Brand")) {
                    progressDialog.dismiss();
                    Utils.showErrorToast(TvThirdFormActivity.this, "Please select Brand");
                }

                else if (BrandModelName.equals("Select Model")) {
                    progressDialog.dismiss();
                    Utils.showErrorToast(TvThirdFormActivity.this, "Please select Model");
                }

                else if (Owner.equals("Select Owners")) {
                    progressDialog.dismiss();
                    Utils.showErrorToast(TvThirdFormActivity.this, "Please select Owner");
                }

                else if (Variant.equals("Select Variant")) {
                    progressDialog.dismiss();
                    Utils.showErrorToast(TvThirdFormActivity.this, "Please select Variant");
                }

                else if (Steering.equals("Select Steering Type")) {
                    progressDialog.dismiss();
                    Utils.showErrorToast(TvThirdFormActivity.this, "Please select Steering Type");
                }

                else  if (Gear.equals("Select Gear Type")) {
                    progressDialog.dismiss();
                    Utils.showErrorToast(TvThirdFormActivity.this, "Please select Gear Type");
                }

               /* else if (tireCondition.equals("Select TireCondition")) {
                    Utils.showErrorToast(TvThirdFormActivity.this, "Please select Tire Condition");
                    progressDialog.dismiss();
                }*/

                else {
                    Log.d("TAG",
                            "chcckeperea "+ Id_res +
                           " brandid "+ BrandID +
                           " BrandModelID "+ BrandModelID +
                           " mfgyear "+ edt_MFGYear.getText().toString() +
                           " Owner "+ Owner +
                           " Variant "+ Variant +
                           " Steering "+ Steering +
                           " Gear "+ Gear +
                           " tireCondition "+ tireCondition +
                           " staus "+ "2");
                    WebService.getClient().getTrueValThirdAddForm(
                            Id_res,
                            BrandID,
                            BrandModelID,
                            edt_MFGYear.getText().toString(),
                            Owner,
                            Variant,
                            Steering,
                            Gear,
                            "",
                            "2"
                    ).enqueue(new Callback<TvAddFormTwoModel>() {
                        @Override
                        public void onResponse(@NotNull Call<TvAddFormTwoModel> call,
                                               @NotNull Response<TvAddFormTwoModel> response) {
                            progressDialog.dismiss();
                            assert response.body() != null;
                            Toast.makeText(TvThirdFormActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(TvThirdFormActivity.this, PriceOfTractorActivity.class);
                            i.putExtra("Price", response.body().getPrice());
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(@NotNull Call<TvAddFormTwoModel> call, @NotNull Throwable t) {
                            Toast.makeText(getApplicationContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });

                   /* WebService.getClient().getTVAddTwoTrueVal(
                            Id_res,
                            BrandID,
                            BrandModelID,
                            edt_MFGYear.getText().toString(),
                            Owner,
                            Variant,
                            Steering,
                            Gear,
                            tireCondition,
                            "2").enqueue(new Callback<TvAddFormTwoModel>() {
                        @Override
                        public void onResponse(@NotNull Call<TvAddFormTwoModel> call,
                                               @NotNull Response<TvAddFormTwoModel> response) {

                            progressDialog.dismiss();
                            assert response.body() != null;
                            Toast.makeText(TvThirdFormActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(TvThirdFormActivity.this, PriceOfTractorActivity.class);
                            i.putExtra("Price", response.body().getPrice());
                            startActivity(i);

                        }

                        @Override
                        public void onFailure(@NotNull Call<TvAddFormTwoModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();

                        }
                    });*/
                }
            }
        });

    }
}