package com.example.kumarGroup.DealstageAK;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.DatabankMakeModel;
import com.example.kumarGroup.DatabankModelListModel;
import com.example.kumarGroup.Deal_model;
import com.example.kumarGroup.ModelNameProductModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.deal_Remainder_model;
import com.example.kumarGroup.model_msg;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DealFormGeneralActivity extends AppCompatActivity {

    EditText edtDescriptionIG,edtNextVisitIG;

    Spinner spnFollowUpTypeIG,spnPassingType,spnPaymentType,spnDealType,sp_make_name,sp_model_name,spn_AddPD_ProductName,sp_model_emp1;
    EditText edt_MFGYear,edt_CustomerPrice,edt_MarketVal,edt_Diff;

    Button btnSubmitIG,getlocation,btnEdit;

    String message,Whatsappnumber,ProductName,String_modelget;
    String strName="",strNmbr="",strModel="",strNote="",strLname="",strOtherNmbr="",strPayment="",strPassing="",strOthernmbr="",lname;

    String FollowUptype;
    String[] FollowUpType_list={"Select FollowUp","Call", "Visit","Visit with Dealer","Showroom Visit","Visit with SM"};

    String Passingtype="";
    String[] PassingType_list={"Select PassingType","Agriculture", "Commercial"};

    String Paymenttype="";
    String[] PaymentType_list={"Select PaymentType","Loan", "Cash"};
    SharedPreferences sp1;
    String emp;

    int day,month,year;
    Calendar mcurrentdate;


    String name,id="",cat_id,sname="",mobileNo,Vemp,nextplan="",login_emp,desc,cat_name,model_name,fname;
    String cat1,passingtype="",paymenttype="";

    ProgressDialog pro;

    ImageView imgUpload;
    TextView txtUploadPhoto;
    LinearLayout ll_imageview;

    String  waypath;

    List<String> l_make_name = new ArrayList<>();
    List<String> l_model_list = new ArrayList<>();
    List<String> modelname_list=new ArrayList<>();
    int total;


    String Dealtype="";
    String[] DealType_list={"Select Deal Type","Exchange", "Fresh"};

    String  makedata = "", modellistdata = "", ProductName1 = "",cur_stage;

    TextView locationget,txtEdit;
    private FusedLocationProviderClient fusedLocationClient;




    String[] Products_List = {"Select Product", "New Tractor","Old Tractor","Implement"};
    List<String> ModelName = new ArrayList<>();
    List<String> ModelID = new ArrayList<>();
    String dealType,model_id;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_form_general);

        getSupportActionBar().setTitle("First Meeting General Form ");

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        Date currentTime = Calendar.getInstance().getTime();
        Log.d("currentTimeNotAttend", String.valueOf(currentTime));
//                    SimpleDateFormat spf=new SimpleDateFormat("MM dd, yyyy hh:mm:ss");
        SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date_str = spf.format(currentTime);
        Log.d("date_str",date_str);

        cat_id=getIntent().getStringExtra("cat_id");
        sname=getIntent().getStringExtra("sname");
        Vemp=getIntent().getStringExtra("vemp");
        passingtype=getIntent().getStringExtra("passing_type");
        paymenttype=getIntent().getStringExtra("payment_type");
        Log.d("Jemin", "onCreateViewHolder: Reminder-2"+paymenttype);
        mobileNo=getIntent().getStringExtra("mobo");
        strOthernmbr=getIntent().getStringExtra("other_nmbr");
        id=getIntent().getStringExtra("id");
        nextplan=getIntent().getStringExtra("nextplan");
        login_emp = getIntent().getStringExtra("login_emp");
        Whatsappnumber = getIntent().getStringExtra("Whatsappnumber");
        cat1=getIntent().getStringExtra("Cat");
        fname=getIntent().getStringExtra("fname");
        lname=getIntent().getStringExtra("lname");
        model_name=getIntent().getStringExtra("model_name");
        desc=getIntent().getStringExtra("desc");
        cat_name=getIntent().getStringExtra("cat_name");
        cat1=getIntent().getStringExtra("Cat");
        cur_stage=getIntent().getStringExtra("cur_stage");

//        Log.d("catnameee",cat_name);
        if (cat_name != null && cat_name.equalsIgnoreCase("")) {
            if (cat_name.contains("Rotavetor")){
                ProductName="Implement";
            }else if (cat_name.contains("Old Tractor")){
                ProductName="Old Tractor";
            }else if (cat_name.contains("New Tractor")){
                ProductName="New Tractor";
            }else if (cat_name.contains("TIGER Inquiry")){
                ProductName="New Tractor";
            }else {
                ProductName="";
            }

            Log.e("ProductName",ProductName);
        }

//        if (cat_name!=null){
//            if (cat_name.contains("Rotavetor")){
//                ProductName="Implement";
//            }else if (cat_name.contains("Old Tractor")){
//                ProductName="Old Tractor";
//            }else if (cat_name.contains("New Tractor")){
//                ProductName="New Tractor";
//            }else if (cat_name.contains("TIGER Inquiry")){
//                ProductName="New Tractor";
//            }else {
//                ProductName="";
//            }
//
//            Log.e("ProductName",ProductName);
//        }

        SharedPreferences sharedPreferences = getSharedPreferences("stage_name",MODE_PRIVATE);
        name = sharedPreferences.getString("name","");

        edtDescriptionIG=findViewById(R.id.edtDescriptionIG);
        edtNextVisitIG=findViewById(R.id.edtNextVisitIG);
        txtEdit=findViewById(R.id.txtEdit);
        btnEdit=findViewById(R.id.btnEdit);

        spnFollowUpTypeIG=findViewById(R.id.spnFollowUpTypeIG);
        spnPassingType=findViewById(R.id.spnPassingType);
        spnPaymentType=findViewById(R.id.spnPaymentType);
        spnDealType=findViewById(R.id.spnDealType);
        sp_model_emp1 = findViewById(R.id.sp_model_emp);
        spn_AddPD_ProductName = findViewById(R.id.spn_AddPD_ProductName);
        sp_make_name=findViewById(R.id.sp_make_name);
        sp_model_name=findViewById(R.id.sp_model_name);
        edt_MFGYear=findViewById(R.id.edt_MFGYear);
        edt_CustomerPrice=findViewById(R.id.edt_CustomerPrice);
        edt_MarketVal=findViewById(R.id.edt_MarketVal);
        edt_Diff=findViewById(R.id.edt_Diff);
        getlocation=findViewById(R.id.getlocation);

        imgUpload=findViewById(R.id.imgUpload);
        txtUploadPhoto=findViewById(R.id.txtUploadPhoto);
        ll_imageview=findViewById(R.id.ll_imageview1);

        btnSubmitIG=findViewById(R.id.btnSubmitIG);
        locationget=findViewById(R.id.locationget);

        if (!name.equals("first meeting")){
            spnPaymentType.setVisibility(View.GONE);
            spnPassingType.setVisibility(View.GONE);
            spn_AddPD_ProductName.setVisibility(View.GONE);
            sp_model_emp1.setVisibility(View.GONE);
            spnDealType.setVisibility(View.GONE);
            btnEdit.setVisibility(View.GONE);
            ll_imageview.setVisibility(View.GONE);
            edtDescriptionIG.setHint("Reason");
            Passingtype=passingtype;
            Paymenttype=paymenttype;


            Log.e("Passingtype",Passingtype+" Paymenttype: "+Paymenttype);
        }

//


        edt_CustomerPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {


                if (!s.toString().equals("") && !edt_MarketVal.getText().toString().equals("")) {
                    total = Integer.parseInt(edt_CustomerPrice.getText().toString()) - Integer.parseInt(edt_MarketVal.getText().toString());
                    edt_Diff.setText(String.valueOf(total));
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edt_MarketVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                if (!s.toString().equals("") && !edt_CustomerPrice.getText().toString().equals("")) {
                    total = Integer.parseInt(edt_CustomerPrice.getText().toString()) - Integer.parseInt(edt_MarketVal.getText().toString());
                    edt_Diff.setText(String.valueOf(total));
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(DealFormGeneralActivity.this);
        pro = new ProgressDialog(DealFormGeneralActivity.this);

        getlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getlocation();
            }
        });

        txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEdit.callOnClick();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogEdit(cat1);
            }
        });



        ArrayAdapter adapter = new ArrayAdapter(DealFormGeneralActivity.this, android.R.layout.simple_spinner_dropdown_item,
                Products_List);
        spn_AddPD_ProductName.setAdapter(adapter);

        spn_AddPD_ProductName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProductName1 = Products_List[position];

                //  Toast.makeText(ProductDetailsActivity.this, ""+ProductName, Toast.LENGTH_SHORT).show();

                Log.d("product", "onItemSelected: "+ProductName1);

                if (ProductName1!=null  && !ProductName1.equals("Select Product")){

                    WebService.getClient().getModelName(ProductName1).enqueue(new Callback<ModelNameProductModel>() {
                        @Override
                        public void onResponse(@NotNull Call<ModelNameProductModel> call, @NotNull Response<ModelNameProductModel> response)
                        {
                            if(response.isSuccessful()) {
                                if (response.body() != null) {
                                    modelname_list.clear();

                                    modelname_list.add("Select Model (new)");


                                    Log.d("product", response.body().toString());

                                    for (int i = 0; i < response.body().getData().size(); i++) {
                                        modelname_list.add(response.body().getData().get(i).getModel_name());
                                        ModelID.add(response.body().getData().get(i).getId());
                                    }

                                    Log.d("ProductS", "onResponse: "+response.body().getData().size());


                                    ArrayAdapter adapter2 = new ArrayAdapter(DealFormGeneralActivity.this,
                                            android.R.layout.simple_spinner_dropdown_item, modelname_list);
                                    sp_model_emp1.setAdapter(adapter2);

                                    if (!(strModel != null ? strModel.equalsIgnoreCase("") : false)){

//                            if (strModel!=null  || !strModel.equals("")){
                                        int spinnerPosition = adapter2.getPosition(strModel);
                                        sp_model_emp1.setSelection(spinnerPosition);
                                    }


                                    sp_model_emp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                                            model_id = ModelID.get(i);

                                            if ("Select Model ".equals(modelname_list.get(i))){
                                                String_modelget = "";
                                            }
                                            else {
                                                String_modelget = modelname_list.get(i);
                                            }
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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        txtUploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 4);

            }
        });

        edtNextVisitIG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DealFormGeneralActivity.this,
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
                        // EdtWalletSalarySlipDateOne.setText(dy+"/"+mt+"/"+year);
                        // 2021-05-08
                        // edtNextVisitIG.setText(mt + "/" + dy + "/" + year);
                        // edtNextVisitIG.setText(year + "-" + mt + "-" + dy);
                        edtNextVisitIG.setText(year+"-"+mt+"-"+dy);

                    }
                }, year, month, day);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

            }
        });

        edtNextVisitIG.setFocusable(false);

        ArrayAdapter adapterFollowUp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, FollowUpType_list);
        spnFollowUpTypeIG.setAdapter(adapterFollowUp);

        spnFollowUpTypeIG.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( FollowUpType_list[position]=="Select FollowUp"){
                    FollowUptype = "";
                }
                else{
                    FollowUptype = FollowUpType_list[position];
                }
                //  TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapterPassing = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, PassingType_list);
        spnPassingType.setAdapter(adapterPassing);
        if (!(passingtype != null ? passingtype.equalsIgnoreCase("") : false)){


            int spinnerPosition = adapterPassing.getPosition(passingtype);
            spnPassingType.setSelection(spinnerPosition);
        }

        spnPassingType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( PassingType_list[position]=="Select PassingType"){
                    Passingtype = "";
                }
                else{
                    Passingtype = PassingType_list[position];
                }
                //  TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapterPayment = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, PaymentType_list);
        spnPaymentType.setAdapter(adapterPayment);

        if (!(paymenttype != null ? paymenttype.equalsIgnoreCase("") : false)){


            int spinnerPosition = adapterPayment.getPosition(paymenttype);
            spnPaymentType.setSelection(spinnerPosition);
        }

        spnPaymentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( PaymentType_list[position]=="Select PaymentType"){
                    Paymenttype = "";
                }
                else{
                    Paymenttype = PaymentType_list[position];
                }
                //  TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapterDeal = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, DealType_list);
        spnDealType.setAdapter(adapterDeal);

        spnDealType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( DealType_list[position]=="Select Deal Type"){
                    Dealtype = "";
                }
                else{
                    Dealtype = DealType_list[position];
                    if (Dealtype.equals("Exchange")){
                        sp_make_name.setVisibility(View.VISIBLE);
                        sp_model_name.setVisibility(View.VISIBLE);
                        edt_MFGYear.setVisibility(View.VISIBLE);
                        edt_CustomerPrice.setVisibility(View.VISIBLE);
                        edt_MarketVal.setVisibility(View.VISIBLE);
                        edt_Diff.setVisibility(View.VISIBLE);
                    }else{
                        sp_make_name.setVisibility(View.GONE);
                        sp_model_name.setVisibility(View.GONE);
                        edt_MFGYear.setVisibility(View.GONE);
                        edt_CustomerPrice.setVisibility(View.GONE);
                        edt_MarketVal.setVisibility(View.GONE);
                        edt_Diff.setVisibility(View.GONE);
                    }
                }
                //  TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

                        ArrayAdapter adapter2 = new ArrayAdapter(DealFormGeneralActivity.this,
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

                                                    ArrayAdapter adapter3 = new ArrayAdapter(DealFormGeneralActivity.this,
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


        btnSubmitIG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dat = edtNextVisitIG.getText().toString().trim();
                String des = edtDescriptionIG.getText().toString().trim();
                String loc = locationget.getText().toString().trim();
                Log.e("btnSubmitIG", "btnSubmitIG");

                Date currentTime = Calendar.getInstance().getTime();
                Log.d("currentTimeNotAttend", String.valueOf(currentTime));
//                    SimpleDateFormat spf=new SimpleDateFormat("MM dd, yyyy hh:mm:ss");
                SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date_str = spf.format(currentTime);
                Log.d("date_str",date_str);


                if (TextUtils.isEmpty(dat)) {
                    edtNextVisitIG.setError("require filed");
                    edtNextVisitIG.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(des)) {
                    edtDescriptionIG.setError("require filed");
                    edtDescriptionIG.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(dat)) {
                    locationget.setError("require filed");
                    locationget.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(FollowUptype)) {
                    Toast.makeText(DealFormGeneralActivity.this, "Please select follow type", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Passingtype==null || Passingtype.equals("Select PassingType")){
//                        if ((Passingtype == null ? Passingtype.equalsIgnoreCase("") : false) || Passingtype.equals("Select PassingType")){
                    Passingtype="";
                }
                if (Paymenttype==null|| Paymenttype.equals("Select PaymentType")){
//                        if ((Paymenttype == null ? Paymenttype.equalsIgnoreCase("") : false)|| Paymenttype.equals("Select PaymentType")){
                    Paymenttype="";
                }

                if (login_emp==null){
                    login_emp="";
                }

                  if (TextUtils.isEmpty(Passingtype)){
                   Passingtype="";
                }

                if (TextUtils.isEmpty(Paymenttype)){
                    Paymenttype="";
                }


                MultipartBody.Part imagePartPhoto = null;
                Log.e("name123", name);
                if (name.equals("first meeting")) {
                    if (waypath == null) {
                       if ((nextplan == null )) {
                            nextplan = "";
                        }

                        if (Passingtype==null || Passingtype.equals("Select PassingType")){
//                        if ((Passingtype == null ? Passingtype.equalsIgnoreCase("") : false) || Passingtype.equals("Select PassingType")){
                            Passingtype="";
                        }
                        if (Paymenttype==null|| Paymenttype.equals("Select PaymentType")){
//                        if ((Paymenttype == null ? Paymenttype.equalsIgnoreCase("") : false)|| Paymenttype.equals("Select PaymentType")){
                            Paymenttype="";
                        }
                        if (FollowUptype==null){
                            //                    if ((FollowUptype != null ? FollowUptype.equalsIgnoreCase("") : false)){
                            FollowUptype="";
                        }
                        if (login_emp==null){
                            login_emp="";
                        }
                        if (nextplan==null){
                            nextplan="";
                        }
                        if (String_modelget==null){
                            String_modelget="";
                        } if (makedata==null){
                            makedata="";
                        }if (modellistdata==null){
                            modellistdata="";
                        }
                        if (edt_MFGYear.getText().toString().equals("")){
                            modellistdata="";
                        }
                        pro.show();
                        pro.setCancelable(false);
                        pro.setMessage("Please wait ..");
                        Log.d("TAG", "logfortest " + " emp " + emp + " vemp " + Vemp + " followtype " + FollowUptype + " catid " + cat_id + " sname " + sname + " des " + des + " date " + dat + " loc " + loc + " mobile " + mobileNo + " stage " + name + " negotiation " + nextplan + " id " + id);

                        WebService.getClient().deal_remainder_web12(
                                RequestBody.create(MediaType.parse("text/plain"), login_emp),
                                RequestBody.create(MediaType.parse("text/plain"), Vemp),
                                RequestBody.create( MediaType.parse("text/plain"),String_modelget),
                                RequestBody.create(MediaType.parse("text/plain"), Passingtype),
                                RequestBody.create(MediaType.parse("text/plain"), Paymenttype),
                                RequestBody.create(MediaType.parse("text/plain"), Dealtype),
                                RequestBody.create( MediaType.parse("text/plain"),makedata),
                                RequestBody.create( MediaType.parse("text/plain"),modellistdata),
                                RequestBody.create( MediaType.parse("text/plain"),edt_MFGYear.getText().toString()),
                                RequestBody.create( MediaType.parse("text/plain"),edt_CustomerPrice.getText().toString()),
                                RequestBody.create( MediaType.parse("text/plain"),edt_MarketVal.getText().toString()),
                                RequestBody.create( MediaType.parse("text/plain"),edt_Diff.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"), FollowUptype),
                                RequestBody.create(MediaType.parse("text/plain"), cat_id),
                                RequestBody.create(MediaType.parse("text/plain"), sname),
                                RequestBody.create(MediaType.parse("text/plain"), des),
                                RequestBody.create(MediaType.parse("text/plain"), dat),
                                RequestBody.create(MediaType.parse("text/plain"), loc),
                                RequestBody.create(MediaType.parse("text/plain"), mobileNo),
                                RequestBody.create(MediaType.parse("text/plain"), name),
                                RequestBody.create( MediaType.parse("text/plain"),nextplan),
                                RequestBody.create(MediaType.parse("text/plain"), id),
                                RequestBody.create(MediaType.parse("text/plain"), date_str)).enqueue(new Callback<deal_Remainder_model>() {
                            @Override
                            public void onResponse(@NotNull Call<deal_Remainder_model> call,
                                                   @NotNull Response<deal_Remainder_model> response) {
                                pro.dismiss();

                                assert response.body() != null;
                                if ("Record added Succesfully".equals(response.body().getMsg())) {

                                    Log.d("TAG", "onResponse: Check_whatsapp_Message");


                                    Toast.makeText(DealFormGeneralActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                    if (DealstageRecyclerActivity.firstmeeting_otpsend_flag) {
                                        Log.d("TAG", "onResponse: Check_whatsapp_Message-1");

//                                        DealstageRecyclerActivity.firstmeeting_otpsend_flag = false;
                                        startActivity(new Intent(DealFormGeneralActivity.this, OtpDealActivity.class)
                                                .putExtra("id", String.valueOf(response.body().getId()))
                                                .putExtra("Whatsappnumber", Whatsappnumber));
                                        finish();
                                    } else if (DealstageRecyclerActivity.deal_nextactivityplanInquiry_flag_sendimage) {
                                        Log.d("TAG", "onResponse: Check_whatsapp_Message-2");

                                        Intent i = new Intent(DealFormGeneralActivity.this, Warm3StageActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        i.putExtra("vemp", Vemp);
                                        i.putExtra("cat_id", cat_id);
                                        i.putExtra("sname", sname);
                                        i.putExtra("mobo", mobileNo);
                                        i.putExtra("Whatsappnumber", Whatsappnumber);
                                        i.putExtra("id", String.valueOf(response.body().getId()));

                                        startActivity(i);
                                    } else if (DealstageRecyclerActivity.makeandoffer_dropdown_flag) {
                                        Log.d("TAG", "onResponse: Check_whatsapp_Message-3");

                                        Intent i = new Intent(DealFormGeneralActivity.this, MakeandofferDeal.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        i.putExtra("id", String.valueOf(response.body().getId()));
                                        i.putExtra("sname", sname);
                                        i.putExtra("Whatsappnumber", Whatsappnumber);

                                        startActivity(i);

                                    } else {
                                        Log.d("TAG", "onResponse: Check_whatsapp_Message-4");
                                        WhatsappMessage1();
                                        final Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                // Do something after 5s = 2000ms
                                                finish();
                                                startActivity(new Intent(DealFormGeneralActivity.this, DealstageMainActivity.class).putExtra("Whatsappnumber", Whatsappnumber));
                                                finish();
                                            }
                                        }, 2000);
                                    }
                                } else {
                                    Log.d("TAG", "onResponse: Check_whatsapp_Message-5");

                                    Log.d("TAG", "chcekdsfsdfksjk: " + response.body().getMsg());
                                    Utils.showErrorToast(DealFormGeneralActivity.this, "Somethign went wrong" + response.body().getMsg());
                                }


                            }

                            @Override
                            public void onFailure(@NotNull Call<deal_Remainder_model> call, @NotNull Throwable t) {
                                pro.dismiss();
                                Log.d("TAG", "onErrorResponse: " + t.getMessage());
                            }
                        });
                    } else {
                        File file1 = new File(waypath);
                        final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        imagePartPhoto = MultipartBody.Part.createFormData("image1", file1.getName(), requestBody1);

                        if ((nextplan == null )) {
                            nextplan = "";
                        }

                        if (Passingtype==null){
                            //                    if ((Passingtype != null ? Passingtype.equalsIgnoreCase("") : false)){
                            Passingtype="";
                        }
                        if (Paymenttype==null){
                            //                    if ((Paymenttype != null ? Paymenttype.equalsIgnoreCase("") : false)){
                            Paymenttype="";
                        }
                        if (FollowUptype==null){
                            //                    if ((FollowUptype != null ? FollowUptype.equalsIgnoreCase("") : false)){
                            FollowUptype="";
                        }
                        if (login_emp==null){
                            login_emp="";
                        }
                        pro.show();
                        pro.setCancelable(false);
                        pro.setMessage("Please wait ..");
                        Log.d("TAG", "logfortest " + " emp " + emp + " vemp " + Vemp + " Passingtype " + Passingtype + " followtype " + FollowUptype + " catid " + cat_id + " sname " + sname + " des " + des + " date " + dat + " loc " + loc + " mobile " + mobileNo + " stage " + name + " negotiation " + nextplan + " id " + id);
                        WebService.getClient().deal_remainder_web1(
                                RequestBody.create(MediaType.parse("text/plain"), login_emp),
                                RequestBody.create(MediaType.parse("text/plain"), Vemp),
                                RequestBody.create( MediaType.parse("text/plain"),String_modelget),
                                RequestBody.create(MediaType.parse("text/plain"), Passingtype),
                                RequestBody.create(MediaType.parse("text/plain"), Paymenttype),
                                RequestBody.create(MediaType.parse("text/plain"), Dealtype),
                                RequestBody.create( MediaType.parse("text/plain"),makedata),
                                RequestBody.create( MediaType.parse("text/plain"),modellistdata),
                                RequestBody.create( MediaType.parse("text/plain"),edt_MFGYear.getText().toString()),
                                RequestBody.create( MediaType.parse("text/plain"),edt_CustomerPrice.getText().toString()),
                                RequestBody.create( MediaType.parse("text/plain"),edt_MarketVal.getText().toString()),
                                RequestBody.create( MediaType.parse("text/plain"),edt_Diff.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"), FollowUptype),
                                RequestBody.create(MediaType.parse("text/plain"), cat_id),
                                RequestBody.create(MediaType.parse("text/plain"), sname),
                                RequestBody.create(MediaType.parse("text/plain"), des),
                                RequestBody.create(MediaType.parse("text/plain"), dat),
                                RequestBody.create(MediaType.parse("text/plain"), loc),
                                RequestBody.create(MediaType.parse("text/plain"), mobileNo),
                                RequestBody.create(MediaType.parse("text/plain"), name),
                                   RequestBody.create( MediaType.parse("text/plain"),nextplan),
                                RequestBody.create(MediaType.parse("text/plain"), id),
                                RequestBody.create(MediaType.parse("text/plain"), date_str),
                                imagePartPhoto
                        ).enqueue(new Callback<deal_Remainder_model>() {
                            @Override
                            public void onResponse(@NotNull Call<deal_Remainder_model> call,
                                                   @NotNull Response<deal_Remainder_model> response) {
                                pro.dismiss();

                                assert response.body() != null;
                                if ("Record added Succesfully".equals(response.body().getMsg())) {

                                    Toast.makeText(DealFormGeneralActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                    if (DealstageRecyclerActivity.firstmeeting_otpsend_flag) {
                                        DealstageRecyclerActivity.firstmeeting_otpsend_flag = false;
                                        startActivity(new Intent(DealFormGeneralActivity.this, OtpDealActivity.class)
                                                .putExtra("id", String.valueOf(response.body().getId())).putExtra("Whatsappnumber", Whatsappnumber));
                                        finish();
                                    } else if (DealstageRecyclerActivity.deal_nextactivityplanInquiry_flag_sendimage) {

                                        Intent i = new Intent(DealFormGeneralActivity.this, Warm3StageActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        i.putExtra("vemp", Vemp);
                                        i.putExtra("cat_id", cat_id);
                                        i.putExtra("sname", sname);
                                        i.putExtra("mobo", mobileNo);
                                        i.putExtra("Whatsappnumber", Whatsappnumber);
                                        i.putExtra("id", String.valueOf(response.body().getId()));

                                        startActivity(i);
                                    } else if (DealstageRecyclerActivity.makeandoffer_dropdown_flag) {

                                        Intent i = new Intent(DealFormGeneralActivity.this, MakeandofferDeal.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        i.putExtra("id", String.valueOf(response.body().getId()));
                                        i.putExtra("sname", sname);
                                        i.putExtra("Whatsappnumber", Whatsappnumber);

                                        startActivity(i);

                                    } else {
                                        WhatsappMessage1();

                                        final Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                // Do something after 5s = 2000ms
                                                finish();
                                                startActivity(new Intent(DealFormGeneralActivity.this, DealstageMainActivity.class).putExtra("Whatsappnumber", Whatsappnumber));
                                                finish();
                                            }
                                        }, 2000);

                                    }
                                } else {
                                    Log.d("TAG", "chcekdsfsdfksjk: " + response.body().getMsg());
                                    Utils.showErrorToast(DealFormGeneralActivity.this, "Somethign went wrong" + response.body().getMsg());
                                }

                            }

                            @Override
                            public void onFailure(@NotNull Call<deal_Remainder_model> call, @NotNull Throwable t) {
                                pro.dismiss();
                                Log.d("TAG", "onErrorResponse: " + t.getMessage());
                                Utils.showErrorToast(DealFormGeneralActivity.this, "" + t.getMessage());
                            }
                        });
                    }
                }
                else{

                    if ((nextplan == null )) {
                        nextplan = "";
                    }

                    if (login_emp==null){
                        login_emp="";
                    }

                    if (Passingtype==null){
                        //                    if ((Passingtype != null ? Passingtype.equalsIgnoreCase("") : false)){
                        Passingtype="";
                    }
                    if (Paymenttype==null){
                        //                    if ((Paymenttype != null ? Paymenttype.equalsIgnoreCase("") : false)){
                        Paymenttype="";
                    }
                    if (FollowUptype==null){
                        //                    if ((FollowUptype != null ? FollowUptype.equalsIgnoreCase("") : false)){
                        FollowUptype="";
                    }
                    if (nextplan==null){
                        nextplan="";
                    }
                    if (String_modelget==null){
                        String_modelget="";
                    } if (makedata==null){
                        makedata="";
                    }if (modellistdata==null){
                        modellistdata="";
                    }
                    if (edt_MFGYear.getText().toString().equals("")){
                        modellistdata="";
                    }
                    pro.show();
                    pro.setCancelable(false);
                    pro.setMessage("Please wait ..");
                    Log.d("TAG", "logfortest " + " emp " + emp + " vemp " + Vemp + " followtype " + FollowUptype + " catid " + cat_id + " sname " + sname + " des " + des + " date " + dat + " loc " + loc + " mobile " + mobileNo + " stage " + name + " negotiation " + nextplan + " id " + id);

                    WebService.getClient().deal_remainder_web12(
                            RequestBody.create(MediaType.parse("text/plain"), login_emp),
                            RequestBody.create(MediaType.parse("text/plain"), Vemp),
                            RequestBody.create( MediaType.parse("text/plain"),String_modelget),
                            RequestBody.create(MediaType.parse("text/plain"), Passingtype),
                            RequestBody.create(MediaType.parse("text/plain"), Paymenttype),
                            RequestBody.create(MediaType.parse("text/plain"), Dealtype),
                            RequestBody.create( MediaType.parse("text/plain"),makedata),
                            RequestBody.create( MediaType.parse("text/plain"),modellistdata),
                            RequestBody.create( MediaType.parse("text/plain"),edt_MFGYear.getText().toString()),
                            RequestBody.create( MediaType.parse("text/plain"),edt_CustomerPrice.getText().toString()),
                            RequestBody.create( MediaType.parse("text/plain"),edt_MarketVal.getText().toString()),
                            RequestBody.create( MediaType.parse("text/plain"),edt_Diff.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), FollowUptype),
                            RequestBody.create(MediaType.parse("text/plain"), cat_id),
                            RequestBody.create(MediaType.parse("text/plain"), sname),
                            RequestBody.create(MediaType.parse("text/plain"), des),
                            RequestBody.create(MediaType.parse("text/plain"), dat),
                            RequestBody.create(MediaType.parse("text/plain"), loc),
                            RequestBody.create(MediaType.parse("text/plain"), mobileNo),
                            RequestBody.create(MediaType.parse("text/plain"), name),
                               RequestBody.create( MediaType.parse("text/plain"),nextplan),
                            RequestBody.create(MediaType.parse("text/plain"), id),
                            RequestBody.create(MediaType.parse("text/plain"), date_str)).enqueue(new Callback<deal_Remainder_model>() {
                        @Override
                        public void onResponse(@NotNull Call<deal_Remainder_model> call,
                                               @NotNull Response<deal_Remainder_model> response) {
                            pro.dismiss();

                            assert response.body() != null;
                            if ("Record added Succesfully".equals(response.body().getMsg())) {

                                Log.d("TAG", "onResponse: Check_whatsapp_Message");


                                Toast.makeText(DealFormGeneralActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                if (DealstageRecyclerActivity.firstmeeting_otpsend_flag) {
                                    Log.d("TAG", "onResponse: Check_whatsapp_Message-1");

                                    DealstageRecyclerActivity.firstmeeting_otpsend_flag = false;
                                    startActivity(new Intent(DealFormGeneralActivity.this, OtpDealActivity.class)
                                            .putExtra("id", String.valueOf(response.body().getId()))
                                            .putExtra("Whatsappnumber", Whatsappnumber));
                                    finish();
                                } else if (DealstageRecyclerActivity.deal_nextactivityplanInquiry_flag_sendimage) {
                                    Log.d("TAG", "onResponse: Check_whatsapp_Message-2");

                                    Intent i = new Intent(DealFormGeneralActivity.this, Warm3StageActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    i.putExtra("vemp", Vemp);
                                    i.putExtra("cat_id", cat_id);
                                    i.putExtra("sname", sname);
                                    i.putExtra("mobo", mobileNo);
                                    i.putExtra("Whatsappnumber", Whatsappnumber);
                                    i.putExtra("id", String.valueOf(response.body().getId()));

                                    startActivity(i);
                                } else if (DealstageRecyclerActivity.makeandoffer_dropdown_flag) {
                                    Log.d("TAG", "onResponse: Check_whatsapp_Message-3");

                                    Intent i = new Intent(DealFormGeneralActivity.this, MakeandofferDeal.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    i.putExtra("id", String.valueOf(response.body().getId()));
                                    i.putExtra("sname", sname);
                                    i.putExtra("Whatsappnumber", Whatsappnumber);

                                    startActivity(i);

                                } else {
                                    Log.d("TAG", "onResponse: Check_whatsapp_Message-4");
                                    WhatsappMessage1();
                                    final Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Do something after 5s = 2000ms

                                            finish();
                                            startActivity(new Intent(DealFormGeneralActivity.this, DealstageMainActivity.class).putExtra("Whatsappnumber", Whatsappnumber));
                                            finish();
                                        }
                                    }, 2000);
                                }
                            } else {
                                Log.d("TAG", "onResponse: Check_whatsapp_Message-5");

                                Log.d("TAG", "chcekdsfsdfksjk: " + response.body().getMsg());
                                Utils.showErrorToast(DealFormGeneralActivity.this, "Somethign went wrong" + response.body().getMsg());
                            }


                        }

                        @Override
                        public void onFailure(@NotNull Call<deal_Remainder_model> call, @NotNull Throwable t) {
                            pro.dismiss();
                            Log.d("TAG", "onErrorResponse: " + t.getMessage());
                        }
                    });
                }
            }
             
        });
              
    }

    private void WhatsappMessage() {

        dealType="Second Metting(COLD)";

        WebService.getClient().dealstage_msg(dealType).enqueue(new Callback<model_msg>() {
            @Override
            public void onResponse(Call<model_msg> call, Response<model_msg> response) {
                pro.dismiss();


                Log.e("respose",response.body().toString());
                if (response.body().getData().size()==0){
                    WhatsappMessage1();
//                    Toast.makeText(DealFormGeneralActivity.this, "No Data Found !", Toast.LENGTH_SHORT).show();
                }else {

                    String message = "प्रिय किसान मित्र।\n" +
                            "\n" +
                            "     आपके बहुमूल्य समय के लिए बहुत-बहुत धन्यवाद, हम भविष्य में आपको संतोषजनक सेवाएं प्रदान करेंगे, और आपके बहुमूल्य सुझावों की प्रतीक्षा रहेगी।\n" +
                            "\n" +
                            "नमस्ते...\n" +
                            "कुमार ऑटोमोबाइल्स (सोनालिका ट्रैक्टर शोरूम)\n" +
                            "\n" +
                            "अधिक जानकारी के लिए संपर्क करें।\n" +
                            "सेल्स MO:- 7500567770\n" +
                            "सर्विस MO:-7505786792\n\n"+
                            "\uD83D\uDC47 Useful Links \uD83D\uDC47\n" ;
                    for (int i=0;i<response.body().getData().size();i++){
                        if (response.body().getData().get(i).getVideo_link1() != null) {
                            message += "\n" + response.body().getData().get(i).getVideo_link1()+"\n";
                        }
                        if (response.body().getData().get(i).getVideo_link2() != null) {
                            message += "\n" + response.body().getData().get(i).getVideo_link2()+"\n";
                        }
                        if (response.body().getData().get(i).getVideo_link3() != null) {
                            message += "\n" + response.body().getData().get(i).getVideo_link3()+"\n";
                        }
                        if (response.body().getData().get(i).getVideo_link4() != null) {
                            message += "\n" + response.body().getData().get(i).getVideo_link4()+"\n";
                        }
                        if (response.body().getData().get(i).getVideo_link5() != null) {
                            message += "\n" + response.body().getData().get(i).getVideo_link5()+"\n";
                        }
                    }

//                    if (response.body().getData().get(0).getVideo_link1() != null) {
//                        message += "\n" + response.body().getData().get(0).getVideo_link1()+"\n";
//                    }
//                    if (response.body().getData().get(0).getVideo_link2() != null) {
//                        message += "\n" + response.body().getData().get(0).getVideo_link2()+"\n";
//                    }
//                    if (response.body().getData().get(0).getVideo_link3() != null) {
//                        message += "\n" + response.body().getData().get(0).getVideo_link3()+"\n";
//                    }

                    Log.d("TAG", "onResponse: Whatsapp_message" + message);


                    startActivity(
                            new Intent(Intent.ACTION_VIEW,
                                    Uri.parse(
                                            String.format("https://api.whatsapp.com/send?phone=%s&text=%s", "+91" + Whatsappnumber, message)
                                    )
                            )
                    );
                }

            }

            @Override
            public void onFailure(Call<model_msg> call, Throwable t) {
                pro.dismiss();
                Utils.showErrorToast(DealFormGeneralActivity.this,t.getMessage());

            }
        });

    }


    private void WhatsappMessage1() {

        message = "प्रिय किसान मित्र।\n" +
                "\n" +
                "     आपके बहुमूल्य समय के लिए बहुत-बहुत धन्यवाद, हम भविष्य में आपको संतोषजनक सेवाएं प्रदान करेंगे, और आपके बहुमूल्य सुझावों की प्रतीक्षा रहेगी।\n" +
                "\n" +
                "नमस्ते...\n" +
                "कुमार ऑटोमोबाइल्स (सोनालिका ट्रैक्टर शोरूम)\n" +
                "\n" +
                "अधिक जानकारी के लिए संपर्क करें।\n" +
                "सेल्स MO:- 7500567770\n" +
                "सर्विस MO:-7505786792";

        Log.d("TAG", "WhatsappMessage: Check_whatsapp_Message-6"+Whatsappnumber);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+91"+Whatsappnumber + "&text=" + message));
        startActivity(intent);

//        startActivity(
//                new Intent(Intent.ACTION_VIEW,
//                        Uri.parse(
//                                String.format("https://api.whatsapp.com/send?phone=%s&text=%s", "+91" + Whatsappnumber, message)
//                        )
//                )
//        );

    }
    private void getlocation()  {
        pro.show();
        pro.setMessage("Please wait get location...");
        pro.setCancelable(false);
        LocationManager locationManager = (LocationManager) DealFormGeneralActivity.this.getSystemService(DealFormGeneralActivity.this.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(DealFormGeneralActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(DealFormGeneralActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        try {
                            Geocoder geocoder = new Geocoder(DealFormGeneralActivity.this, Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

//                            binding.location.setText(addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName());
                            //  Toast.makeText(DealFormGeneralActivity.this, addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();


                            locationget.setVisibility(View.VISIBLE);
                            locationget.setText(addresses.get(0).getAddressLine(0)+" , "+addresses.get(0).getSubLocality()+" , "+addresses.get(0).getLocality()
                                    +" , "+ addresses.get(0).getAdminArea()+" , "+addresses.get(0).getCountryName()+" , "+String.valueOf(addresses.get(0).getLatitude())+" , "+
                                    String.valueOf(addresses.get(0).getLongitude()));

                            getlocation.setVisibility(View.GONE);
                            pro.dismiss();

                          /*  WebService.getClient().send_location_web_ak(emp_id,
                                    addresses.get(0).getAddressLine(0) +","+
                                            addresses.get(0).getSubLocality() +","+
                                            addresses.get(0).getLocality() + "," +
                                            addresses.get(0).getAdminArea() + "," +
                                            addresses.get(0).getCountryName()
                                    ,String.valueOf(addresses.get(0).getLatitude())
                                    ,String.valueOf(addresses.get(0).getLongitude()),
                                    "1"
                            ).enqueue(new Callback<send_location_model_ak>() {
                                @Override
                                public void onResponse(Call<send_location_model_ak> call, Response<send_location_model_ak> response) {
                                    Toast.makeText(DealFormGeneralActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                    pro.dismiss();
                                }

                                @Override
                                public void onFailure(Call<send_location_model_ak> call, Throwable throwable) {
                                    Toast.makeText(getApplicationContext(), " error 1 "+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    pro.dismiss();
                                }
                            });*/

                        } catch (IOException e) {
                            e.printStackTrace();
                            getlocation();
                            // Toast.makeText(getApplicationContext(), "cathc id "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            pro.dismiss();
                        }
                    }
                    else {
                        LocationRequest locationRequest = new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(10000)
                                .setNumUpdates(1);
                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(@NonNull LocationResult locationResult) {
                                //   super.onLocationResult(locationResult);
                                try {

                                    Location location1 = locationResult.getLastLocation();
                                    Geocoder geocoder = new Geocoder(DealFormGeneralActivity.this, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(location1.getLatitude(), location1.getLongitude(), 0);

                                    //  Toast.makeText(DealFormGeneralActivity.this, addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();

                                    locationget.setVisibility(View.VISIBLE);
                                    locationget.setText(addresses.get(0).getAddressLine(0)+" , "+addresses.get(0).getSubLocality()+" , "+addresses.get(0).getLocality()
                                            +" , "+ addresses.get(0).getAdminArea()+" , "+addresses.get(0).getCountryName()+" , "+String.valueOf(addresses.get(0).getLatitude())+" , "+
                                            String.valueOf(addresses.get(0).getLongitude()));

                                    getlocation.setVisibility(View.GONE);
                                    pro.dismiss();
                                    /*WebService.getClient().send_location_web_ak(emp_id,
                                            addresses.get(0).getAddressLine(0) +","+
                                                    addresses.get(0).getSubLocality() +","+
                                                    addresses.get(0).getLocality() + "," +
                                                    addresses.get(0).getAdminArea() + "," +
                                                    addresses.get(0).getCountryName()
                                            ,String.valueOf(addresses.get(0).getLatitude())
                                            ,String.valueOf(addresses.get(0).getLongitude())
                                            ,"1"
                                    ).enqueue(new Callback<send_location_model_ak>() {
                                        @Override
                                        public void onResponse(Call<send_location_model_ak> call, Response<send_location_model_ak> response) {
                                            Toast.makeText(DealFormGeneralActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                            pro.dismiss();
                                        }

                                        @Override
                                        public void onFailure(Call<send_location_model_ak> call, Throwable throwable) {
                                            Toast.makeText(getApplicationContext(), " error 2 "+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                            pro.dismiss();
                                        }
                                    });*/

                                } catch (Exception e) {
                                    pro.dismiss();
                                    getlocation();
                                    //Toast.makeText(getApplicationContext(), " catch id 2"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        };
                        if (ActivityCompat.checkSelfPermission(DealFormGeneralActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(DealFormGeneralActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            return;
                        }
                        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                    }
                }
            });
        }
        else {
            pro.dismiss();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DealFormGeneralActivity.this);


            alertDialog.setTitle("GPS is not Enabled!");

            alertDialog.setMessage("Do you want to turn on GPS?");
            alertDialog.setCancelable(false);

            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                }
            });

            alertDialog.show();

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 4) {
            Bundle bundle = data.getExtras();
            final Bitmap photo = (Bitmap) bundle.get("data");
            imgUpload.setImageBitmap(photo);
            savebitmap(photo);
         /*   File file=new File(waypath);
            saveBitmaptoFile(file);*/

        }

       /* if (requestCode == CAMERA_REQUEST_CODE) {
            if(resultCode==RESULT_OK) {
                File f = new File(currentPhotoPath);
                getImageBitmap(currentPhotoPath, imgUpload, f, this);
                String[] name = currentPhotoPath.split("/");
                txtUploadPhoto.setText(name[name.length-1]);
            }

        }*/

    }

    private File savebitmap(Bitmap bmp) {

        String extStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();/* Environment.getExternalStorageDirectory().getPath();*/
        FileOutputStream outStream = null;
        // String temp = null;

        final int random = new Random().nextInt(6000000) + 20;
        File file = new File(extStorageDirectory, random + ".png");
        waypath = getFilePath1(this, Uri.fromFile(file));
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, random + ".png");
            waypath = getFilePath1(this, Uri.fromFile(file));
        }
        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 70, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }


    public String getFilePath1(Context context, Uri uri) {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 22 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
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

    private void showDialogEdit(String cat1) {
        Dialog dialog = new Dialog(DealFormGeneralActivity.this);
        dialog.setContentView(R.layout.edit_detail);


//        String catNotAttend=cat1

        EditText customer_name = dialog.findViewById(R.id.customerf_name);
        EditText customerl_name = dialog.findViewById(R.id.customerl_name);
        EditText customer_mnmbr = dialog.findViewById(R.id.customer_mnmbr);
        EditText customer_other_mnmbr = dialog.findViewById(R.id.customer_other_mnmbr);
        TextView locationget = dialog.findViewById(R.id.locationget);
        Button getlocation1 = dialog.findViewById(R.id.getlocation);


        EditText note = dialog.findViewById(R.id.note);
        Button btn_edit = dialog.findViewById(R.id.btn_edit);

        Spinner sp_model_emp =dialog. findViewById(R.id.sp_model_emp);
        Spinner sp_paymenttype =dialog. findViewById(R.id.sp_paymenttype);
        Spinner sp_passingtype =dialog. findViewById(R.id.sp_passingtype);
        Spinner sp_make_name = dialog.findViewById(R.id.sp_make_name);
        Spinner sp_model_name = dialog.findViewById(R.id.sp_model_name);
        Spinner spnDealType = dialog.findViewById(R.id.spnDealType);
        modelname_list = new ArrayList<>();


        sp_paymenttype.setVisibility(View.GONE);
        sp_passingtype.setVisibility(View.GONE);



        ArrayAdapter adapterDeal = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, DealType_list);
        spnDealType.setAdapter(adapterDeal);
//        if (!(Dealtype != null ? Dealtype.equalsIgnoreCase("") : false)){
//
//            int spinnerPosition = adapterDeal.getPosition(Dealtype);
//            spnDealType.setSelection(spinnerPosition);
//        }

        spnDealType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( DealType_list[position]=="Select Deal Type"){
                    Dealtype = "";
                }
                else{
                    Dealtype = DealType_list[position];
                    if (Dealtype.equals("Exchange")){
                        sp_make_name.setVisibility(View.VISIBLE);
                        sp_model_name.setVisibility(View.VISIBLE);
                        edt_MFGYear.setVisibility(View.VISIBLE);
                        edt_CustomerPrice.setVisibility(View.VISIBLE);
                        edt_MarketVal.setVisibility(View.VISIBLE);
                        edt_Diff.setVisibility(View.VISIBLE);
                    }else{
                        sp_make_name.setVisibility(View.GONE);
                        sp_model_name.setVisibility(View.GONE);
                        edt_MFGYear.setVisibility(View.GONE);
                        edt_CustomerPrice.setVisibility(View.GONE);
                        edt_MarketVal.setVisibility(View.GONE);
                        edt_Diff.setVisibility(View.GONE);
                    }
                }
                //  TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapterPassing = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, PassingType_list);
        sp_passingtype.setAdapter(adapterPassing);

        sp_passingtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( PassingType_list[position]=="Select PassingType"){
                    Passingtype = "";
                }
                else{
                    Passingtype = PassingType_list[position];
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
                    Paymenttype = "";
                }
                else{
                    Paymenttype = PaymentType_list[position];
                }
                //  TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



       /* String inqType=id;
        String sname=sname;*/

        customer_name.setText(fname);
        customerl_name.setText(lname);
        customer_mnmbr.setText(mobileNo);
//        customer_other_mnmbr.setText(mcatlist.get(position).getMoblino());

        note.setText(desc);

        strName=customer_name.getText().toString();
        strLname=customerl_name.getText().toString();
        strNmbr=customer_mnmbr.getText().toString();
        strOtherNmbr=customer_other_mnmbr.getText().toString();
        strModel=model_name;
        strNote=note.getText().toString();

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

                        ArrayAdapter adapter2 = new ArrayAdapter(DealFormGeneralActivity.this,
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

                                                    ArrayAdapter adapter3 = new ArrayAdapter(DealFormGeneralActivity.this,
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
//


        if (ProductName!=null  && !ProductName.equals("Select Product")){

            WebService.getClient().getModelName(ProductName).enqueue(new Callback<ModelNameProductModel>() {
                @Override
                public void onResponse(@NotNull Call<ModelNameProductModel> call, @NotNull Response<ModelNameProductModel> response)
                {
                    if(response.isSuccessful()) {
                        if (response.body() != null) {
                            modelname_list.clear();
//                        ModelID.clear();

                            modelname_list.add("Select Model");
//                        ModelID.add("0");

                            Log.d("product", response.body().toString());

                            for (int i = 0; i < response.body().getData().size(); i++) {
                                modelname_list.add(response.body().getData().get(i).getModel_name());
//                            ModelID.add(response.body().getData().get(i).getId());
                            }

                            Log.d("ProductS", "onResponse: "+response.body().getData().size());

                            //   Log.d("MProduct", ModelName.toString());

                            ArrayAdapter adapter2 = new ArrayAdapter(DealFormGeneralActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, modelname_list);
                            sp_model_emp.setAdapter(adapter2);

                            if (!(strModel != null ? strModel.equalsIgnoreCase("") : false)){
                                int spinnerPosition = adapter2.getPosition(strModel);
                                sp_model_emp.setSelection(spinnerPosition);
                            }

                            sp_model_emp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                                model_name = ModelName.get(position);
//                                model_id = ModelID.get(position);

                                    if ("Select Model".equals(modelname_list.get(i))){
                                        String_modelget = "";
                                    }
                                    else {
                                        String_modelget = modelname_list.get(i);
                                    }
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

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(DealFormGeneralActivity.this);
        getlocation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getlocation();
            }

            private void getlocation()  {
                pro.show();
                pro.setMessage("Please wait get location...");
                pro.setCancelable(false);
                LocationManager locationManager = (LocationManager) DealFormGeneralActivity.this.getSystemService(DealFormGeneralActivity.this.LOCATION_SERVICE);
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                        locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    if (ActivityCompat.checkSelfPermission(DealFormGeneralActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(DealFormGeneralActivity.this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location location = task.getResult();
                            if (location != null) {
                                try {
                                    Geocoder geocoder = new Geocoder(DealFormGeneralActivity.this, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

//                            binding.location.setText(addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName());
                                    //  Toast.makeText(DealFormGeneralActivity.this, addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();


                                    locationget.setVisibility(View.VISIBLE);
                                    locationget.setText(addresses.get(0).getAddressLine(0)+" , "+addresses.get(0).getSubLocality()+" , "+addresses.get(0).getLocality()
                                            +" , "+ addresses.get(0).getAdminArea()+" , "+addresses.get(0).getCountryName()+" , "+String.valueOf(addresses.get(0).getLatitude())+" , "+
                                            String.valueOf(addresses.get(0).getLongitude()));

                                    getlocation1.setVisibility(View.GONE);
                                    pro.dismiss();


                                } catch (IOException e) {
                                    e.printStackTrace();
                                    getlocation();
                                    // Toast.makeText(getApplicationContext(), "cathc id "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    pro.dismiss();
                                }
                            }
                            else {
                                LocationRequest locationRequest = new LocationRequest()
                                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                        .setInterval(10000)
                                        .setFastestInterval(10000)
                                        .setNumUpdates(1);
                                LocationCallback locationCallback = new LocationCallback() {
                                    @Override
                                    public void onLocationResult(@NonNull LocationResult locationResult) {
                                        //   super.onLocationResult(locationResult);
                                        try {

                                            Location location1 = locationResult.getLastLocation();
                                            Geocoder geocoder = new Geocoder(DealFormGeneralActivity.this, Locale.getDefault());
                                            List<Address> addresses = geocoder.getFromLocation(location1.getLatitude(), location1.getLongitude(), 0);

                                            //  Toast.makeText(DealFormGeneralActivity.this, addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();

                                            locationget.setVisibility(View.VISIBLE);
                                            locationget.setText(addresses.get(0).getAddressLine(0)+" , "+addresses.get(0).getSubLocality()+" , "+addresses.get(0).getLocality()
                                                    +" , "+ addresses.get(0).getAdminArea()+" , "+addresses.get(0).getCountryName()+" , "+String.valueOf(addresses.get(0).getLatitude())+" , "+
                                                    String.valueOf(addresses.get(0).getLongitude()));

                                            getlocation1.setVisibility(View.GONE);
                                            pro.dismiss();


                                        } catch (Exception e) {
                                            pro.dismiss();
                                            getlocation();
                                            //Toast.makeText(getApplicationContext(), " catch id 2"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                };
                                if (ActivityCompat.checkSelfPermission(DealFormGeneralActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(DealFormGeneralActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                                    return;
                                }
                                fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                            }
                        }
                    });
                }
                else {
                    pro.dismiss();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DealFormGeneralActivity.this);


                    alertDialog.setTitle("GPS is not Enabled!");

                    alertDialog.setMessage("Do you want to turn on GPS?");
                    alertDialog.setCancelable(false);

                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                        }
                    });

                    alertDialog.show();

                }
            }

        });



        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pro.show();
                pro.setCancelable(false);
                pro.setMessage("Please wait ...");


                strName = customer_name.getText().toString();
                strLname = customerl_name.getText().toString();
                strNmbr = customer_mnmbr.getText().toString();
                strOtherNmbr = customer_other_mnmbr.getText().toString();
                strNote = note.getText().toString();

                if (sp_model_emp.getVisibility()==View.VISIBLE){
                    strModel=modellistdata;
                }

                WebService.getClient().edit_deal_stage_info(id, sname,strName,strLname, strNmbr, strModel, strNote,strOtherNmbr,Paymenttype,Passingtype,Dealtype,makedata,emp,getlocation.getText().toString(),cur_stage).enqueue(new Callback<Deal_model>() {
                    @Override
                    public void onResponse(Call<Deal_model> call, Response<Deal_model> response) {
                        pro.dismiss();

                        Log.e("respose",response.body().toString());
                        dialog.dismiss();


                      /*  Intent intent=new Intent(DealFormGeneralActivity.this,DealstageRecyclerActivity.class);
                        intent.putExtra("cat_id",cat_id);
                        intent.putExtra("actionbar","first meeting");
                        DealstageRecyclerActivity.firstmeeting_flag=true;
                        startActivity(intent);
                        finish();*/

                    }

                    @Override
                    public void onFailure(Call<Deal_model> call, Throwable t) {
                        pro.dismiss();
                        Utils.showErrorToast(DealFormGeneralActivity.this,t.getMessage());
                        dialog.dismiss();
                    }
                });


            }
        });
        dialog.show();
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

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE, CAMERA, WRITE_EXTERNAL_STORAGE}, 1);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
       /* Intent intent=new Intent(DealFormGeneralActivity.this,DealstageRecyclerActivity.class);
        intent.putExtra("cat_id",cat_id);
        intent.putExtra("actionbar","first meeting");
        DealstageRecyclerActivity.firstmeeting_flag=true;
        startActivity(intent);
        finish();*/
    }
}