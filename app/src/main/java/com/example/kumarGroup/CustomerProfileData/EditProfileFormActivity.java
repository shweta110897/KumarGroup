package com.example.kumarGroup.CustomerProfileData;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kumarGroup.CUEditProfileEmpListModel;
import com.example.kumarGroup.DataCityModel;
import com.example.kumarGroup.DataDistrictModel;
import com.example.kumarGroup.DataTehsilModel;
import com.example.kumarGroup.DataVillageModel;
import com.example.kumarGroup.DatabankMakeModel;
import com.example.kumarGroup.DatabankModelListModel;
import com.example.kumarGroup.DatafonameModel;
import com.example.kumarGroup.EditProfileModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.StateModel;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewCustomerProfileEditModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileFormActivity extends AppCompatActivity {

    String MobileNo,Status;

    EditText edt_categoryName_EP, edt_FnameName_EP, edt_LastName_EP, edt_mobile_number_EP,
            edt_whatsapp_number_EP, edt_modelTractorName_EP, edt_mEngineNo_EP,edt_TractorRegisterNo,
            edt_ChasisNO_EP, edt_MfgYear_EP,edt_DescriptionBox_EP;

    Spinner spn_state_EP, spn_city_EP, spn_District_EP, spn_Tehsil_EP, spn_Village_EP,
            spn_TractorType_EP, spn_Rotavetor_EP, spn_SpeeDetail_EP, spn_Pelough_EP,
            sp_Fild_office_EP,spn_Cat_EP,
            spn_categoryName_EP,spn_Threshor_EP,spn_Trailer_EP,spn_Cultivator_EP,spn_Lavlor_EP;

    Button btn_Submit_EP;


    ImageView img_PicOne_EP, img_PicTwo_EP;

    TextView txt_PicOne_EP, txt_PicTwo_EP;


    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog, progressDialog1, progressDialog2;


    String Tractor;
    String[] TractorOptions = {"Tractor", "Big", "Mini"};


    String Rotavetor;
    String[] RotavetorOptions = {"Rotavetor", "Yes", "No"};


    String SpeedDrill;
    String[] SpeedDrillOptions = {"Speed Drill", "Yes", "No"};


    String Pelough;
    String[] PeloughOptions = {"Pelough", "Yes", "No"};

    String Threshor;
    String[] ThreshorOptions = {"Threshor", "Yes", "No"};

    String Trailor;
    String[] TrailorOptions = {"Trailor", "Yes", "No"};

    String Cultivator;
    String[] CultivatorOptions = {"Cultivator", "Yes", "No"};

    String Lavlor;
    String[] LavlorOptions = {"Lavlor", "Yes", "No"};


    Uri uriPicOne, uriPicTwo;

    String waypathPicOne, waypathPicTwo;

    List<String> state = new ArrayList<>();
    List<String> stateid = new ArrayList<>();

    List<String> City = new ArrayList<>();
    List<String> cityid = new ArrayList<>();

    List<String> District = new ArrayList<>();
    List<String> districtid = new ArrayList<>();

    List<String> Tehsil = new ArrayList<>();
    List<String> tehsilid = new ArrayList<>();

    List<String> Village = new ArrayList<>();
    List<String> villageid = new ArrayList<>();

    List<String> foname = new ArrayList<>();
    List<String> foid = new ArrayList<>();


    List<String> CategoryName = new ArrayList<>();
    List<String> CategoryID = new ArrayList<>();
    List<String> CategoryStatus = new ArrayList<>();


    String sid = "";
    String cid = "";
    String did = "";
    String tid = "";
    String vid = "";
    String eid = "";

    String mainId;

    String statedata, citydata, districtdata, tehsildata, villagedata, employedata,
            catName="", cateId = "", catStatus="",
            prTractor, prRotavetor, prSpeedDrill, prPelough;

    String preThreshor,preTrailor,preCultivator,preLavlor;


    String prState, prCity, prDistrict, prTaluko, prVillage, prFiledOf, prCategoryName;

    String prStateID, prCityID, prDistrictID, prTalukoID, prVillageID, prFiledOfID,
            prCategoryId, prCategoryStatus;


    int flag = 0;

    Spinner sp_make_name ,sp_model_name;
    List<String> l_make_name = new ArrayList<>();
    List<String> l_model_list = new ArrayList<>();

    String  makedata = "", modellistdata = "", strModel = "";

    Spinner sp_paymenttype ,sp_passingtype;

    String PassingType;
    String[] PassingType_list={"Select PassingType","Agriculture", "Commercial"};

    String PaymentType;
    String[] PaymentType_list={"Select PaymentType","Loan", "Cash"};

    String MainCat;
    String[] Main_Category = {"Select Item","General", "Inquiry"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_form);

        getSupportActionBar().setTitle("Edit Profile");


        edt_categoryName_EP = findViewById(R.id.edt_categoryName_EP);
        edt_FnameName_EP = findViewById(R.id.edt_FnameName_EP);
        edt_LastName_EP = findViewById(R.id.edt_LastName_EP);
        edt_mobile_number_EP = findViewById(R.id.edt_mobile_number_EP);
        edt_whatsapp_number_EP = findViewById(R.id.edt_whatsapp_number_EP);
        edt_modelTractorName_EP = findViewById(R.id.edt_modelTractorName_EP);
        edt_TractorRegisterNo = findViewById(R.id.edt_TractorRegisterNo);
        edt_mEngineNo_EP = findViewById(R.id.edt_mEngineNo_EP);
        edt_ChasisNO_EP = findViewById(R.id.edt_ChasisNO_EP);
        edt_MfgYear_EP = findViewById(R.id.edt_MfgYear_EP);

        spn_state_EP = findViewById(R.id.spn_state_EP);
        spn_city_EP = findViewById(R.id.spn_city_EP);
        spn_District_EP = findViewById(R.id.spn_District_EP);
        spn_Tehsil_EP = findViewById(R.id.spn_Tehsil_EP);
        spn_Village_EP = findViewById(R.id.spn_Village_EP);
        spn_Cat_EP = findViewById(R.id.spn_Cat_EP);
        spn_TractorType_EP = findViewById(R.id.spn_TractorType_EP);
        spn_Rotavetor_EP = findViewById(R.id.spn_Rotavetor_EP);
        spn_SpeeDetail_EP = findViewById(R.id.spn_SpeeDetail_EP);
        spn_Pelough_EP = findViewById(R.id.spn_Pelough_EP);
        sp_Fild_office_EP = findViewById(R.id.sp_Fild_office_EP);
        spn_categoryName_EP = findViewById(R.id.spn_categoryName_EP);
        spn_Threshor_EP = findViewById(R.id.spn_Threshor_EP);
        spn_Trailer_EP = findViewById(R.id.spn_Trailer_EP);
        spn_Cultivator_EP = findViewById(R.id.spn_Cultivator_EP);
        spn_Lavlor_EP = findViewById(R.id.spn_Lavlor_EP);
        sp_make_name = findViewById(R.id.sp_make_name);
        sp_model_name = findViewById(R.id.sp_model_name);

        img_PicOne_EP = findViewById(R.id.img_PicOne_EP);
        img_PicTwo_EP = findViewById(R.id.img_PicTwo_EP);

        txt_PicOne_EP = findViewById(R.id.txt_PicOne_EP);
        txt_PicTwo_EP = findViewById(R.id.txt_PicTwo_EP);

         sp_paymenttype = findViewById(R.id.sp_paymenttype);
         sp_passingtype = findViewById(R.id.sp_passingtype);

        btn_Submit_EP = findViewById(R.id.btn_Submit_EP);
        edt_DescriptionBox_EP = findViewById(R.id.edt_DescriptionBox_EP);


        MobileNo = getIntent().getStringExtra("MobileNo");


        sp = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp.getString("emp_id", "");


        Log.d("EMP_ID", "onCreate: " + emp);

        progressDialog = new ProgressDialog(EditProfileFormActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getViewCuProfileEdit(MobileNo).enqueue(new Callback<ViewCustomerProfileEditModel>() {
            @Override
            public void onResponse(@NotNull Call<ViewCustomerProfileEditModel> call,
                                   @NotNull Response<ViewCustomerProfileEditModel> response) {

                assert response.body() != null;
                if (response.body().getCustomer_profile().size() == 0) {
                    Utils.showErrorToast(EditProfileFormActivity.this, "No Data Available");
                }
                else {

                    edt_categoryName_EP.setText(response.body().getCustomer_profile().get(0).getCat_name());
                    edt_FnameName_EP.setText(response.body().getCustomer_profile().get(0).getFname());
                    edt_LastName_EP.setText(response.body().getCustomer_profile().get(0).getLname());
                    edt_mobile_number_EP.setText(response.body().getCustomer_profile().get(0).getMoblino());
                    edt_whatsapp_number_EP.setText(response.body().getCustomer_profile().get(0).getWhatsappno());
                    edt_modelTractorName_EP.setText(response.body().getCustomer_profile().get(0).getModel_t_name());
                    edt_TractorRegisterNo.setText(response.body().getCustomer_profile().get(0).getTractor());
                    edt_mEngineNo_EP.setText(response.body().getCustomer_profile().get(0).getEngineno());
                    edt_ChasisNO_EP.setText(response.body().getCustomer_profile().get(0).getChasisno());
                    edt_MfgYear_EP.setText(response.body().getCustomer_profile().get(0).getMfgy());


                    edt_DescriptionBox_EP.setText(response.body().getCustomer_profile().get(0).getDesc());

                    prState = response.body().getCustomer_profile().get(0).getState();
                    prCity = response.body().getCustomer_profile().get(0).getCity();
                    prDistrict = response.body().getCustomer_profile().get(0).getDistric();
                    prTaluko = response.body().getCustomer_profile().get(0).getTehsil();
                    prVillage = response.body().getCustomer_profile().get(0).getVilage();
                    prFiledOf = response.body().getCustomer_profile().get(0).getEmployee_name();

                    prCategoryName = response.body().getCustomer_profile().get(0).getCat_name();
                    prCategoryId = response.body().getCustomer_profile().get(0).getCat_id();
                    prCategoryStatus = response.body().getCustomer_profile().get(0).getCate_type();


                    prStateID = response.body().getCustomer_profile().get(0).getStateid();
                    prCityID = response.body().getCustomer_profile().get(0).getCityid();
                    prDistrictID = response.body().getCustomer_profile().get(0).getDisid();
                    prTalukoID = response.body().getCustomer_profile().get(0).getTehsilid();
                    prVillageID = response.body().getCustomer_profile().get(0).getVilageid();
                    prFiledOfID = response.body().getCustomer_profile().get(0).getEmpid();


                    mainId = response.body().getCustomer_profile().get(0).getId();

                    prTractor = response.body().getCustomer_profile().get(0).getTractor();
                    prRotavetor = response.body().getCustomer_profile().get(0).getRotavator();
                    prSpeedDrill = response.body().getCustomer_profile().get(0).getSpeeddrel();
                    prPelough = response.body().getCustomer_profile().get(0).getPelough();


                    if(response.body().getCustomer_profile().get(0).getImage1()==null){

                    }else{
                        Glide.with(EditProfileFormActivity.this)
                                .load(response.body().getCustomer_profile().get(0).getPath() +
                                        response.body().getCustomer_profile().get(0).getImage1())
                                .into(img_PicOne_EP);
                    }

                    if(response.body().getCustomer_profile().get(0).getImage2()==null){

                    }else {
                        Glide.with(EditProfileFormActivity.this)
                                .load(response.body().getCustomer_profile().get(0).getPath() +
                                        response.body().getCustomer_profile().get(0).getImage2())
                                .into(img_PicTwo_EP);
                    }

                    /*Glide.with(EditProfileFormActivity.this)
                            .load(response.body().getCustomer_profile().get(0).getPath() +
                                    response.body().getCustomer_profile().get(0).getImage1())
                            .into(img_PicOne_EP);*/


                    /*Glide.with(EditProfileFormActivity.this)
                            .load(response.body().getCustomer_profile().get(0).getPath() +
                                    response.body().getCustomer_profile().get(0).getImage2())
                            .into(img_PicTwo_EP);*/


                    if(prTractor == null){

                         String[] TractorOptions = {"Tractor", "Big", "Mini"};

                        ArrayAdapter TractorArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, TractorOptions);
                        spn_TractorType_EP.setAdapter(TractorArray);

                        spn_TractorType_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Tractor = TractorOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }


                   else if (prTractor.equals("Tractor")) {
                        String[] TractorOptions = {"Tractor", "Big", "Mini"};

                        ArrayAdapter TractorArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, TractorOptions);
                        spn_TractorType_EP.setAdapter(TractorArray);

                        spn_TractorType_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Tractor = TractorOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                  else  if (prTractor.equals("Big")) {

                        String[] TractorYes = {"Big", "Tractor", "Mini"};


                        ArrayAdapter TractorArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, TractorYes);
                        spn_TractorType_EP.setAdapter(TractorArray);

                        spn_TractorType_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Tractor = TractorOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                 else   if (prTractor.equals("Mini")) {
                        String[] TractorNo = {"Mini", "Big", "Tractor"};

                        ArrayAdapter TractorArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, TractorNo);
                        spn_TractorType_EP.setAdapter(TractorArray);

                        spn_TractorType_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Tractor = TractorOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                 else{
                        String[] TractorOptions = {"Tractor", "Big", "Mini"};

                        ArrayAdapter TractorArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, TractorOptions);
                        spn_TractorType_EP.setAdapter(TractorArray);

                        spn_TractorType_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Tractor = TractorOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                    //*******************************************************

                    if(prRotavetor==null){
                        String[] RotavetorOptions = {"Rotavetor", "Yes", "No"};


                        ArrayAdapter PaymentOptionArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, RotavetorOptions);
                        spn_Rotavetor_EP.setAdapter(PaymentOptionArray);

                        spn_Rotavetor_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Rotavetor = RotavetorOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }


                  else  if (prRotavetor.equals("Rotavetor")) {

                        String[] RotavetorOptions = {"Rotavetor", "Yes", "No"};


                        ArrayAdapter PaymentOptionArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, RotavetorOptions);
                        spn_Rotavetor_EP.setAdapter(PaymentOptionArray);

                        spn_Rotavetor_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Rotavetor = RotavetorOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                  else  if (prRotavetor.equals("Yes")) {
                        String[] RotavetorYes = {"Yes", "Rotavetor", "No"};


                        ArrayAdapter PaymentOptionArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, RotavetorYes);
                        spn_Rotavetor_EP.setAdapter(PaymentOptionArray);

                        spn_Rotavetor_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Rotavetor = RotavetorOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                 else  if (prRotavetor.equals("No")) {
                        String[] RotavetorNo = {"No", "Yes", "Rotavetor"};

                        ArrayAdapter PaymentOptionArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, RotavetorNo);
                        spn_Rotavetor_EP.setAdapter(PaymentOptionArray);

                        spn_Rotavetor_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Rotavetor = RotavetorOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                  else{
                        String[] RotavetorOptions = {"Rotavetor", "Yes", "No"};


                        ArrayAdapter PaymentOptionArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, RotavetorOptions);
                        spn_Rotavetor_EP.setAdapter(PaymentOptionArray);

                        spn_Rotavetor_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Rotavetor = RotavetorOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                    //******************************************************************************

                    if(prSpeedDrill==null){
                        String[] SpeedDrillOptions = {"Speed Drill", "Yes", "No"};


                        ArrayAdapter SpeedDrillArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, SpeedDrillOptions);
                        spn_SpeeDetail_EP.setAdapter(SpeedDrillArray);

                        spn_SpeeDetail_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                SpeedDrill = SpeedDrillOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                 else   if (prSpeedDrill.equals("Speed Drill")) {

                        String[] SpeedDrillOptions = {"Speed Drill", "Yes", "No"};


                        ArrayAdapter SpeedDrillArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, SpeedDrillOptions);
                        spn_SpeeDetail_EP.setAdapter(SpeedDrillArray);

                        spn_SpeeDetail_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                SpeedDrill = SpeedDrillOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }


                  else  if (prSpeedDrill.equals("Yes")) {

                        String[] SpeedDrillYes = {"Yes", "Speed Drill", "No"};


                        ArrayAdapter SpeedDrillArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, SpeedDrillYes);
                        spn_SpeeDetail_EP.setAdapter(SpeedDrillArray);

                        spn_SpeeDetail_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                SpeedDrill = SpeedDrillOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }


                  else  if (prSpeedDrill.equals("No")) {

                        String[] SpeedDrillNo = {"No", "Yes", "Speed Drill"};


                        ArrayAdapter SpeedDrillArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, SpeedDrillNo);
                        spn_SpeeDetail_EP.setAdapter(SpeedDrillArray);

                        spn_SpeeDetail_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                SpeedDrill = SpeedDrillOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                  else{
                       String[] SpeedDrillOptions = {"Speed Drill", "Yes", "No"};


                        ArrayAdapter SpeedDrillArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, SpeedDrillOptions);
                        spn_SpeeDetail_EP.setAdapter(SpeedDrillArray);

                        spn_SpeeDetail_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                SpeedDrill = SpeedDrillOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                    //**************************************************************************

                    if(prPelough==null){
                        String[] PeloughOptions = {"Pelough", "Yes", "No"};

                        ArrayAdapter PeloughArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, PeloughOptions);
                        spn_Pelough_EP.setAdapter(PeloughArray);

                        spn_Pelough_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Pelough = PeloughOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }



                  else  if (prPelough.equals("Pelough")) {

                        String[] PeloughOptions = {"Pelough", "Yes", "No"};


                        ArrayAdapter PeloughArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, PeloughOptions);
                        spn_Pelough_EP.setAdapter(PeloughArray);

                        spn_Pelough_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Pelough = PeloughOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }


                   else if (prPelough.equals("Yes")) {

                        String[] PeloughYes = {"Yes", "Pelough", "No"};


                        ArrayAdapter PeloughArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, PeloughYes);
                        spn_Pelough_EP.setAdapter(PeloughArray);

                        spn_Pelough_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Pelough = PeloughOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }


                   else if (prPelough.equals("No")) {


                        String[] PeloughNo = {"No", "Yes", "Pelough"};


                        ArrayAdapter PeloughArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, PeloughNo);
                        spn_Pelough_EP.setAdapter(PeloughArray);

                        spn_Pelough_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Pelough = PeloughOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                   else{
                        String[] PeloughOptions = {"Pelough", "Yes", "No"};


                        ArrayAdapter PeloughArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, PeloughOptions);
                        spn_Pelough_EP.setAdapter(PeloughArray);

                        spn_Pelough_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Pelough = PeloughOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }


                    //**************************************************************************

                    if(preThreshor==null){
//                        String[] ThreshorOptions = {"Pelough", "Yes", "No"};

                        ArrayAdapter PeloughArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, ThreshorOptions);
                        spn_Threshor_EP.setAdapter(PeloughArray);

                        spn_Threshor_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Threshor = ThreshorOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }


                    //**************************************************************************

                    if(preTrailor==null){
//                        String[] TrailorOptions = {"Pelough", "Yes", "No"};

                        ArrayAdapter PeloughArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, TrailorOptions);
                        spn_Trailer_EP.setAdapter(PeloughArray);

                        spn_Trailer_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Trailor = TrailorOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }


                    //**************************************************************************

                    if(preCultivator==null){
//                        String[] ThreshorOptions = {"Pelough", "Yes", "No"};

                        ArrayAdapter PeloughArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, CultivatorOptions);
                        spn_Cultivator_EP.setAdapter(PeloughArray);

                        spn_Cultivator_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Cultivator = CultivatorOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                    //**************************************************************************

                    if(preLavlor==null){
//                        String[] ThreshorOptions = {"Pelough", "Yes", "No"};

                        ArrayAdapter PeloughArray = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, LavlorOptions);
                        spn_Lavlor_EP.setAdapter(PeloughArray);

                        spn_Lavlor_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Lavlor = LavlorOptions[position];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }


                    progressDialog.dismiss();


                    WebService.getClient().getDatabankMake().enqueue(new Callback<DatabankMakeModel>() {
                        @Override
                        public void onResponse(Call<DatabankMakeModel> call, Response<DatabankMakeModel> response1) {
                            Log.d("MakeName", response1.body().toString());
                            if (response1.isSuccessful()) {
                                if (response1.body() != null) {
                                    l_make_name.clear();
                                    l_make_name.add("Select Make");

                                    for (int i = 0; i < response1.body().getState().size(); i++)
                                    {
                                        l_make_name.add(response1.body().getState().get(i).getMake());
                                    }

                                    ArrayAdapter adapter2 = new ArrayAdapter(EditProfileFormActivity.this,
                                            android.R.layout.simple_spinner_dropdown_item, l_make_name);
                                    sp_make_name.setAdapter(adapter2);

                                    sp_make_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                            makedata = l_make_name.get(position);  //
                                            Log.d("ModelList", makedata);

                                            if(!makedata.equals("Select Make")) {
                                                WebService.getClient().getDatabankModelList(makedata).enqueue(new Callback<DatabankModelListModel>() {
                                                    @Override
                                                    public void onResponse(Call<DatabankModelListModel> call, Response<DatabankModelListModel> response2) {
                                                        l_model_list.clear();

                                                        if (response2.isSuccessful()) {
                                                            if (response2.body() != null) {
                                                                l_model_list.add("Select Model");
                                                                int xz = response2.body().getModel().size();
                                                                Log.d("ModelList", "" + xz +"");
                                                                for (int j = 0; j < response2.body().getModel().size();j++)
                                                                {
                                                                    l_model_list.add(response2.body().getModel().get(j).getModelName());
                                                                    Log.d("ModelList", "onResponse: " + response2.body().getModel().get(j).getModelName());
                                                                }

                                                                ArrayAdapter adapter3 = new ArrayAdapter(EditProfileFormActivity.this,
                                                                        android.R.layout.simple_spinner_dropdown_item, l_model_list);
                                                                sp_model_name.setAdapter(adapter3);


                                                                if (!(strModel != null ? strModel.equalsIgnoreCase("") : false)){

//                            if (strModel!=null  || !strModel.equals("")){
                                                                    int spinnerPosition = adapter3.getPosition(strModel);
                                                                    sp_model_name.setSelection(spinnerPosition);
                                                                }

                                                                sp_model_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                    @Override
                                                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                        modellistdata = l_model_list.get(i);
                                                                    }

                                                                    @Override
                                                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                                                    }
                                                                });
                                                            }
                                                        }

                                                    }

                                                    @Override
                                                    public void onFailure(Call<DatabankModelListModel> call, Throwable t) {
                                                        Toast.makeText(getApplicationContext(), "Something went wrong... Please try again after sometime! \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            } else {
                                                l_model_list.clear();
                                                l_model_list.add("Select Model");
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<DatabankMakeModel> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Something went wrong... Please try again after sometime! \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                  //  Toast.makeText(EditProfileFormActivity.this, ""+prCategoryName+" "+prCategoryId+" "+ prCategoryStatus, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(@NotNull Call<ViewCustomerProfileEditModel> call, @NotNull Throwable t) {

            }
        });


        ArrayAdapter adapterPassing = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, PassingType_list);
        sp_passingtype.setAdapter(adapterPassing);
       /* if (!(passingtype != null ? passingtype.equalsIgnoreCase("") : false)){
            int spinnerPosition = adapterPassing.getPosition(passingtype);
            sp_passingtype.setSelection(spinnerPosition);
        }*/

        sp_passingtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( PassingType_list[position]=="Select PassingType"){
                    PassingType = "";
                }
                else{
                    PassingType = PassingType_list[position];
                }
                //  TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapterPayment = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, PaymentType_list);
        sp_paymenttype.setAdapter(adapterPayment);

        sp_paymenttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( PaymentType_list[position]=="Select PaymentType"){
                    PaymentType = "";
                }
                else{
                    PaymentType = PaymentType_list[position];
                }
                //  TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //*********************************************************


        txt_PicOne_EP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 2);
            }
        });

        txt_PicTwo_EP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 3);
            }
        });

        progressDialog2 = new ProgressDialog(EditProfileFormActivity.this);
        progressDialog2.show();
        progressDialog2.setContentView(R.layout.progress_dialog);
        progressDialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


       WebService.getClient().getEditCuProfileCat().enqueue(new Callback<CUEditProfileEmpListModel>() {
            @Override
            public void onResponse(@NotNull Call<CUEditProfileEmpListModel> call,
                                   @NotNull Response<CUEditProfileEmpListModel> response) {

                progressDialog2.dismiss();
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        CategoryName.clear();
                        CategoryID.clear();
                        CategoryStatus.clear();

                        CategoryName.add("Select Category");
                        CategoryID.add("0");
                        CategoryStatus.add("0");

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            CategoryName.add(response.body().getData().get(i).getCat_list());
                            CategoryID.add(response.body().getData().get(i).getCat_id());
                            CategoryStatus.add(response.body().getData().get(i).getStatus());
                        }


                        ArrayAdapter category = new ArrayAdapter(EditProfileFormActivity.this, android.R.layout.simple_spinner_dropdown_item, CategoryName);
                        spn_categoryName_EP.setAdapter(category);

                       spn_categoryName_EP.setSelection(CategoryName.indexOf(prCategoryName));
                       spn_categoryName_EP.setSelection(CategoryID.indexOf(prCategoryId));
                       //spn_categoryName_EP.setSelection(CategoryStatus.indexOf(prCategoryStatus));

                        spn_categoryName_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                               // spn_categoryName_EP.setSelection(CategoryName.indexOf(prCategoryName));
                               // spn_categoryName_EP.setSelection(CategoryID.indexOf(prCategoryId));
                              //  spn_categoryName_EP.setSelection(CategoryStatus.indexOf(prCategoryStatus));
/*
                                catName = CategoryName.get(position);
                                cateId = CategoryID.get(position);
                                catStatus = CategoryStatus.get(position);*/

                                catName = "";
                                cateId = "";
                                catStatus = "";


                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<CUEditProfileEmpListModel> call, @NotNull Throwable t) {

            }
        });


        progressDialog1 = new ProgressDialog(EditProfileFormActivity.this);
        progressDialog1.show();
        progressDialog1.setContentView(R.layout.progress_dialog);
        progressDialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


       WebService.getClient().getstate().enqueue(new Callback<StateModel>() {
            @Override
            public void onResponse(@NotNull Call<StateModel> call, @NotNull Response<StateModel> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        state.clear();
                        stateid.clear();

                        state.add("Select State");
                        stateid.add("0");
                        Log.d("Banks", response.body().toString());
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            state.add(response.body().getData().get(i).getState());
                            stateid.add(response.body().getData().get(i).getId());
                        }
                        Log.d("Banks", state.toString());
                        ArrayAdapter adapter2 = new ArrayAdapter(EditProfileFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, state);
                        spn_state_EP.setAdapter(adapter2);

                        spn_state_EP.setSelection(state.indexOf(prState));
                        spn_state_EP.setSelection(stateid.indexOf(prStateID));

                        spn_state_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                                statedata = state.get(position);
                                sid = stateid.get(position);


                                WebService.getClient().getCity(sid).enqueue(new Callback<DataCityModel>() {
                                    @Override
                                    public void onResponse(@NotNull Call<DataCityModel> call,
                                                           @NotNull Response<DataCityModel> response1) {
                                        City.clear();
                                        cityid.clear();
                                        if (response1.isSuccessful()) {
                                            if (response1.body() != null) {
                                                City.add("Select City");
                                                cityid.add("0");


//                                                Log.d("City", response1.body().getCity().get(0).getCity());
                                                for (int i = 0; i < response1.body().getCity().size(); i++) {
                                                    City.add(response1.body().getCity().get(i).getCity());
                                                    cityid.add(response1.body().getCity().get(i).getId());
                                                }
                                                //       final List<String> cityarray = Arrays.asList(response1.body().getCity().toString().replace("city="," ").split(","));

                                                ArrayAdapter adapter3 = new ArrayAdapter(EditProfileFormActivity.this,
                                                        android.R.layout.simple_spinner_dropdown_item, City);
                                                spn_city_EP.setAdapter(adapter3);

                                                spn_city_EP.setSelection(City.indexOf(prCity));
                                                spn_city_EP.setSelection(cityid.indexOf(prCityID));


                                                spn_city_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                                                        citydata = City.get(position);
                                                        cid = cityid.get(position);

                                                        WebService.getClient().getDistrict(cid).enqueue(new Callback<DataDistrictModel>() {
                                                            @Override
                                                            public void onResponse(@NotNull Call<DataDistrictModel> call, @NotNull Response<DataDistrictModel> response3) {

                                                                District.clear();
                                                                districtid.clear();
                                                                if (response3.isSuccessful()) {
                                                                    if (response3.body() != null) {
                                                                        District.add("Select District");
                                                                        districtid.add("0");
//                                                                        Log.d("District",response3.body().getDistrict().get(0).getDistrict());
                                                                        for (int i = 0; i < response3.body().getDistrict().size(); i++) {

                                                                            District.add(response3.body().getDistrict().get(i).getDistrict());
                                                                            districtid.add(response3.body().getDistrict().get(i).getId());
                                                                        }
                                                                        ArrayAdapter adapter4 = new ArrayAdapter(EditProfileFormActivity.this,
                                                                                android.R.layout.simple_spinner_dropdown_item, District);
                                                                        spn_District_EP.setAdapter(adapter4);

                                                                        spn_District_EP.setSelection(District.indexOf(prDistrict));
                                                                        spn_District_EP.setSelection(districtid.indexOf(prDistrictID));


                                                                        spn_District_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                            @Override
                                                                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


                                                                                districtdata = District.get(position);
                                                                                did = districtid.get(position);


                                                                                WebService.getClient().getTeshill(did).enqueue(new Callback<DataTehsilModel>() {
                                                                                    @Override
                                                                                    public void onResponse(@NotNull Call<DataTehsilModel> call,
                                                                                                           @NotNull Response<DataTehsilModel> response4) {

                                                                                        progressDialog1.dismiss();
                                                                                        Tehsil.clear();
                                                                                        tehsilid.clear();
                                                                                        if (response4.isSuccessful()) {

                                                                                            if (response4.body() != null) {
                                                                                                Tehsil.add("Select Taluko");
                                                                                                tehsilid.add("0");
//                                                                                                Log.d("Tehsil",response4.body().getTehsil().get(0).getTehsil_name());

                                                                                                for (int i = 0; i < response4.body().getTehsil().size(); i++) {
                                                                                                    Tehsil.add(response4.body().getTehsil().get(i).getTehsil_name());
                                                                                                    tehsilid.add(response4.body().getTehsil().get(i).getId());

                                                                                                }
                                                                                                ArrayAdapter adapter5 = new ArrayAdapter(EditProfileFormActivity.this,
                                                                                                        android.R.layout.simple_spinner_dropdown_item, Tehsil);
                                                                                                spn_Tehsil_EP.setAdapter(adapter5);

                                                                                                spn_Tehsil_EP.setSelection(Tehsil.indexOf(prTaluko));
                                                                                                spn_Tehsil_EP.setSelection(tehsilid.indexOf(prTalukoID));


                                                                                                spn_Tehsil_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                    @Override
                                                                                                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


                                                                                                        tehsildata = Tehsil.get(position);
                                                                                                        tid = tehsilid.get(position);

                                                                                                        WebService.getClient().getVillage(tid).enqueue(new Callback<DataVillageModel>() {
                                                                                                            @Override
                                                                                                            public void onResponse(@NotNull Call<DataVillageModel> call, @NotNull Response<DataVillageModel> response5) {
                                                                                                                Village.clear();
                                                                                                                villageid.clear();
                                                                                                                if (response5.isSuccessful()) {

                                                                                                                    if (response5.body() != null) {
                                                                                                                        Village.add("Select Village");
                                                                                                                        villageid.add("0");
//                                                                                                                      Log.d("Village",response5.body().getVillage().get(0).getVillage_name());
                                                                                                                        for (int i = 0; i < response5.body().getVillage().size(); i++) {
                                                                                                                            Village.add(response5.body().getVillage().get(i).getVillage_name());
                                                                                                                            villageid.add(response5.body().getVillage().get(i).getId());
                                                                                                                        }
                                                                                                                    }
                                                                                                                    ArrayAdapter adapter6 = new ArrayAdapter(EditProfileFormActivity.this,
                                                                                                                            android.R.layout.simple_spinner_dropdown_item, Village);
                                                                                                                    spn_Village_EP.setAdapter(adapter6);

                                                                                                                    spn_Village_EP.setSelection(Village.indexOf(prVillage));
                                                                                                                    spn_Village_EP.setSelection(villageid.indexOf(prVillageID));


                                                                                                                    spn_Village_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                        @Override
                                                                                                                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


                                                                                                                            villagedata = Village.get(position);
                                                                                                                            vid = villageid.get(position);

                                                                                                                            ArrayAdapter adapter = new ArrayAdapter(EditProfileFormActivity.this, android.R.layout.simple_spinner_dropdown_item, Main_Category);
                                                                                                                            spn_Cat_EP.setAdapter(adapter);

                                                                                                                            spn_Cat_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                @Override
                                                                                                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                                                                                    MainCat = Main_Category[position];


                                                                                                                                    if (Main_Category[position].equals("General")) {

                                                                                                                                        Status="0";
                                                                                                                                        WebService.getClient().getEMPname(vid,prCategoryId,Status).enqueue(new Callback<DatafonameModel>() {
                                                                                                                                            @Override
                                                                                                                                            public void onResponse(@NotNull Call<DatafonameModel> call, @NotNull Response<DatafonameModel> response6) {
                                                                                                                                                foname.clear();
                                                                                                                                                foid.clear();

                                                                                                                                                //  sp_Fild_office_EP.setText(prFiledOf);


                                                                                                                                                if (response6.isSuccessful()) {

                                                                                                                                                    if (response6.body() != null) {

                                                                                                                                                        //sp_Fild_office_EP.setText(response6.body().getName().get(0).getEname());
                                                                                                                                                        for (int i = 0; i < response6.body().getName().size(); i++) {

                                                                                                                                                            foname.add(response6.body().getName().get(i).getEname());
                                                                                                                                                            foid.add(response6.body().getName().get(i).getEmp_id());
                                                                                                                                                        }


                                                                                                                                                        ArrayAdapter adapter7 = new ArrayAdapter(EditProfileFormActivity.this,
                                                                                                                                                                android.R.layout.simple_spinner_dropdown_item, foname);
                                                                                                                                                        sp_Fild_office_EP.setAdapter(adapter7);

                                                                                                                                                        sp_Fild_office_EP.setSelection(foname.indexOf(prFiledOf));
                                                                                                                                                        sp_Fild_office_EP.setSelection(foid.indexOf(prFiledOfID));


                                                                                                                                                        sp_Fild_office_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                                            @Override
                                                                                                                                                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                                                                                                                                                                employedata = foname.get(position);
                                                                                                                                                                eid = foid.get(position);
                                                                                                                                                            }

                                                                                                                                                            @Override
                                                                                                                                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                                                                                                                                            }
                                                                                                                                                        });


                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }

                                                                                                                                            @Override
                                                                                                                                            public void onFailure(@NotNull Call<DatafonameModel> call, @NotNull Throwable t) {

                                                                                                                                            }
                                                                                                                                        });


                                                                                                                                    }else if (Main_Category[position].equals("Inquiry")) {
                                                                                                                                        Status="1";
                                                                                                                                        WebService.getClient().getEMPname(vid,prCategoryId,Status).enqueue(new Callback<DatafonameModel>() {
                                                                                                                                            @Override
                                                                                                                                            public void onResponse(@NotNull Call<DatafonameModel> call, @NotNull Response<DatafonameModel> response6) {
                                                                                                                                                foname.clear();
                                                                                                                                                foid.clear();

                                                                                                                                                //  sp_Fild_office_EP.setText(prFiledOf);


                                                                                                                                                if (response6.isSuccessful()) {

                                                                                                                                                    if (response6.body() != null) {

                                                                                                                                                        //sp_Fild_office_EP.setText(response6.body().getName().get(0).getEname());
                                                                                                                                                        for (int i = 0; i < response6.body().getName().size(); i++) {

                                                                                                                                                            foname.add(response6.body().getName().get(i).getEname());
                                                                                                                                                            foid.add(response6.body().getName().get(i).getEmp_id());
                                                                                                                                                        }


                                                                                                                                                        ArrayAdapter adapter7 = new ArrayAdapter(EditProfileFormActivity.this,
                                                                                                                                                                android.R.layout.simple_spinner_dropdown_item, foname);
                                                                                                                                                        sp_Fild_office_EP.setAdapter(adapter7);

                                                                                                                                                        sp_Fild_office_EP.setSelection(foname.indexOf(prFiledOf));
                                                                                                                                                        sp_Fild_office_EP.setSelection(foid.indexOf(prFiledOfID));


                                                                                                                                                        sp_Fild_office_EP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                                            @Override
                                                                                                                                                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                                                                                                                                                                employedata = foname.get(position);
                                                                                                                                                                eid = foid.get(position);
                                                                                                                                                            }

                                                                                                                                                            @Override
                                                                                                                                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                                                                                                                                            }
                                                                                                                                                        });


                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }

                                                                                                                                            @Override
                                                                                                                                            public void onFailure(@NotNull Call<DatafonameModel> call, @NotNull Throwable t) {

                                                                                                                                            }
                                                                                                                                        });


                                                                                                                                    }


                                                                                                                                }

                                                                                                                                @Override
                                                                                                                                public void onNothingSelected(AdapterView<?> parent) {

                                                                                                                                }
                                                                                                                            });


                                                                                                                        }

                                                                                                                        @Override
                                                                                                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                                                                                                        }
                                                                                                                    });

                                                                                                                }
                                                                                                            }
                                                                                                            @Override
                                                                                                            public void onFailure(@NotNull Call<DataVillageModel> call, @NotNull Throwable t) {

                                                                                                            }
                                                                                                        });

                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                                                                                    }
                                                                                                });

                                                                                            }
                                                                                        }

                                                                                    }

                                                                                    @Override
                                                                                    public void onFailure(@NotNull Call<DataTehsilModel> call, @NotNull Throwable t) {

                                                                                    }
                                                                                });


                                                                            }

                                                                            @Override
                                                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                                                            }
                                                                        });

                                                                    }


                                                                }

                                                            }

                                                            @Override
                                                            public void onFailure(@NotNull Call<DataDistrictModel> call, @NotNull Throwable t) {

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
                                    public void onFailure(@NotNull Call<DataCityModel> call, @NotNull Throwable t) {

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
            public void onFailure(@NotNull Call<StateModel> call, @NotNull Throwable t) {
                Log.d("error", "onFailure: " + t);
                Toast.makeText(EditProfileFormActivity.this, "Error Rety", Toast.LENGTH_SHORT).show();

            }
        });


        btn_Submit_EP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isvalidate()) {
                    progressDialog = new ProgressDialog(EditProfileFormActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(
                            android.R.color.transparent);

                    MultipartBody.Part imagePartPicOne = null;
                    MultipartBody.Part imagePartPicTwo = null;

                    if (waypathPicOne != null) {
                        File file1 = new File(waypathPicOne);
                        final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        imagePartPicOne = MultipartBody.Part.createFormData("image1",
                                file1.getName(), requestBody1);
                    }

                    if (waypathPicTwo != null) {
                        File file2 = new File(waypathPicTwo);
                        final RequestBody requestBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                        imagePartPicTwo = MultipartBody.Part.createFormData("image2",
                                file2.getName(), requestBody2);
                    }


                    WebService.getClient().getCuEditProfile(
                            RequestBody.create(MediaType.parse("text/plain"), mainId),
                            RequestBody.create(MediaType.parse("text/plain"), edt_FnameName_EP.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), edt_LastName_EP.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), sid),
                            RequestBody.create(MediaType.parse("text/plain"), cid),
                            RequestBody.create(MediaType.parse("text/plain"), did),
                            RequestBody.create(MediaType.parse("text/plain"), tid),
                            RequestBody.create(MediaType.parse("text/plain"), vid),
                            RequestBody.create(MediaType.parse("text/plain"), edt_mobile_number_EP.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), edt_whatsapp_number_EP.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), ""),
                            RequestBody.create(MediaType.parse("text/plain"), edt_modelTractorName_EP.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), edt_MfgYear_EP.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), edt_mEngineNo_EP.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), edt_ChasisNO_EP.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), Rotavetor),
                            RequestBody.create(MediaType.parse("text/plain"), SpeedDrill),
                            RequestBody.create(MediaType.parse("text/plain"), Pelough),
                            RequestBody.create(MediaType.parse("text/plain"), cateId),
                            RequestBody.create(MediaType.parse("text/plain"), eid),
                            RequestBody.create(MediaType.parse("text/plain"), catStatus),
                            RequestBody.create(MediaType.parse("text/plain"), emp),
                            RequestBody.create(MediaType.parse("text/plain"), edt_DescriptionBox_EP.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), MobileNo),
                            RequestBody.create(MediaType.parse("text/plain"), makedata),
                            RequestBody.create(MediaType.parse("text/plain"), modellistdata),
                            RequestBody.create(MediaType.parse("text/plain"), edt_TractorRegisterNo.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), Threshor),
                            RequestBody.create(MediaType.parse("text/plain"), Trailor),
                            RequestBody.create(MediaType.parse("text/plain"), Cultivator),
                            RequestBody.create(MediaType.parse("text/plain"), Lavlor),
                            RequestBody.create(MediaType.parse("text/plain"), PaymentType),
                            RequestBody.create(MediaType.parse("text/plain"), PassingType),
                            imagePartPicOne,
                            imagePartPicTwo
                    ).enqueue(new Callback<EditProfileModel>() {
                        @Override
                        public void onResponse(@NotNull Call<EditProfileModel> call,
                                               @NotNull Response<EditProfileModel> response) {
                            progressDialog.dismiss();


                            assert response.body() != null;
                            Toast.makeText(EditProfileFormActivity.this, "" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                            finish();

                        }

                        @Override
                        public void onFailure(@NotNull Call<EditProfileModel> call,
                                              @NotNull Throwable t) {
                            Toast.makeText(EditProfileFormActivity.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });

                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriPicOne = data.getData();
                    Log.d("PanImageUri", uriPicOne.toString());
                    waypathPicOne = getFilePath(this, uriPicOne);
                    Log.d("PanImageWayPath", waypathPicOne);
                    String[] name = waypathPicOne.split("/");
                    txt_PicOne_EP.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_PicOne_EP.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriPicTwo = data.getData();
                    Log.d("Pan Image Uri", uriPicTwo.toString());
                    waypathPicTwo = getFilePath(this, uriPicTwo);
                    Log.d("Pan Image Uri", waypathPicTwo);
                    String[] name = waypathPicTwo.split("/");
                    txt_PicTwo_EP.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_PicTwo_EP.setImageURI(selectedImageUri);
                }
            }
        }
    }

    private boolean isvalidate() {

        if (edt_DescriptionBox_EP.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Description", Toast.LENGTH_SHORT).show();
            return false;
        }else if (!makedata.equals("Select Make") ){
            if (modellistdata.equals("Select Model") ){
                Toast.makeText(this, "Enter Model", Toast.LENGTH_SHORT).show();
                return false;
            }
            if ( edt_MfgYear_EP.getText().toString().equals("")){
                Toast.makeText(this, "Enter Model Year", Toast.LENGTH_SHORT).show();
                return false;
            }
        }



        return true;
    }


    public String getFilePath(Context context, Uri uri) {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/all_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

}