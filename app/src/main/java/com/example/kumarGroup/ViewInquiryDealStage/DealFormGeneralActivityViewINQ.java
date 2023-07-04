package com.example.kumarGroup.ViewInquiryDealStage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DealFormGeneralActivityViewINQ extends AppCompatActivity {

    EditText edtDescriptionIG,edtNextVisitIG;

    Spinner spnFollowUpTypeIG,spn_AddPD_ProductName,sp_model_emp;

    Button btnSubmitIG,getlocation,btnEdit;

    String FollowUptype;
    String[] FollowUpType_list={"Select FollowUp","Call", "Visit","Visit with Dealer","Showroom Visit","Visit with SM"};

    SharedPreferences sp1;
    String emp="";

    int day,month,year;
    Calendar mcurrentdate;

    Spinner spnPassingType,spnPaymentType,spnDealType;


    String name,id="",cat_id,sname,mobileNo,Vemp,nextplan="",login_emp,message,Whatsappnumber;

    ProgressDialog pro;

    String DealType;
    String[] DealType_list={"Select Deal Type","Exchange", "Fresh"};

    TextView locationget;
    private FusedLocationProviderClient fusedLocationClient;
    TextView mobile;

    List<String> modelname_list = new ArrayList<>();

    String[] Products_List = {"Select Product", "New Tractor","Old Tractor","Implement"};
    List<String> ModelName = new ArrayList<>();
    List<String> ModelID = new ArrayList<>();
    String dealType,ProductName,ProductName1,strModel,model_name,model_id,String_modelget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_form_general_view_inq);

        getSupportActionBar().setTitle("First Meeting General Form View Inquiry");

        SharedPreferences sharedPreferencesS = getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
        emp = sharedPreferencesS.getString("Slected_EMPID","");

        cat_id=getIntent().getStringExtra("cat_id");
        sname=getIntent().getStringExtra("sname");
        Vemp=getIntent().getStringExtra("vemp");
        mobileNo=getIntent().getStringExtra("mobo");
        id=getIntent().getStringExtra("id");
        nextplan=getIntent().getStringExtra("nextplan");
        login_emp = getIntent().getStringExtra("login_emp");
        Whatsappnumber = getIntent().getStringExtra("Whatsappnumber");


        SharedPreferences sharedPreferences = getSharedPreferences("stage_name",MODE_PRIVATE);
        name = sharedPreferences.getString("name","");

        spnPassingType=findViewById(R.id.spnPassingType);
        spnPaymentType=findViewById(R.id.spnPaymentType);
        spnDealType=findViewById(R.id.spnDealType);
        btnEdit=findViewById(R.id.btnEdit);

        spnPassingType.setVisibility(View.GONE);
        spnPaymentType.setVisibility(View.GONE);
        spnDealType.setVisibility(View.GONE);
        btnEdit.setVisibility(View.GONE);



        edtDescriptionIG=findViewById(R.id.edtDescriptionIG);
        edtNextVisitIG=findViewById(R.id.edtNextVisitIG);

        spnFollowUpTypeIG=findViewById(R.id.spnFollowUpTypeIG);
        getlocation=findViewById(R.id.getlocation);

        btnSubmitIG=findViewById(R.id.btnSubmitIG);
        locationget=findViewById(R.id.locationget);

        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(DealFormGeneralActivityViewINQ.this);
        pro = new ProgressDialog(DealFormGeneralActivityViewINQ.this);

        getlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getlocation();
            }
        });

        ArrayAdapter adapterDeal = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, DealType_list);
        spnDealType.setAdapter(adapterDeal);

        spnDealType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( DealType_list[position]=="Select Deal Type"){
                    DealType = "";
                }
                else{
                    DealType = DealType_list[position];
                }
                //  TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

         sp_model_emp = findViewById(R.id.sp_model_emp);
         spn_AddPD_ProductName = findViewById(R.id.spn_AddPD_ProductName);


        if (nextplan.equals("Reminder")){
            sp_model_emp.setVisibility(View.GONE);
            spn_AddPD_ProductName.setVisibility(View.GONE);
        }

        ArrayAdapter adapter = new ArrayAdapter(DealFormGeneralActivityViewINQ.this, android.R.layout.simple_spinner_dropdown_item,
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

                                    for (int i = 0; i < response.body().getData().size(); i++) {
                                        modelname_list.add(response.body().getData().get(i).getModel_name());
                                        ModelID.add(response.body().getData().get(i).getId());
                                    }


                                    Log.d("ProductS", "onResponse: "+response.body().getData().size());


                                    ArrayAdapter adapter2 = new ArrayAdapter(DealFormGeneralActivityViewINQ.this,
                                            android.R.layout.simple_spinner_dropdown_item, modelname_list);
                                    sp_model_emp.setAdapter(adapter2);

                                    if (!(strModel != null ? strModel.equalsIgnoreCase("") : false)){

//                            if (strModel!=null  || !strModel.equals("")){
                                        int spinnerPosition = adapter2.getPosition(strModel);
                                        sp_model_emp.setSelection(spinnerPosition);
                                    }


                                    sp_model_emp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        edtNextVisitIG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DealFormGeneralActivityViewINQ.this,
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

        btnSubmitIG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dat = edtNextVisitIG.getText().toString().trim();
                String des = edtDescriptionIG.getText().toString().trim();
                String loc = locationget.getText().toString().trim();

                if (TextUtils.isEmpty(dat)){
                    edtNextVisitIG.setError("require filed");
                    edtNextVisitIG.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(des)){
                    edtDescriptionIG.setError("require filed");
                    edtDescriptionIG.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(dat)){
                    locationget.setError("require filed");
                    locationget.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(FollowUptype)){
                    Toast.makeText(DealFormGeneralActivityViewINQ.this, "Please select follow type", Toast.LENGTH_SHORT).show();
                    return;
                }

                pro.show();
                pro.setCancelable(false);
                pro.setMessage("Please wait ..");
                Log.d("TAG", "logfortest "+" emp "+emp+" vemp "+Vemp+" followtype "+FollowUptype+" catid "+cat_id +" sname "+sname+" des "+des+ " date "+dat+" loc "+loc+" mobile "+mobileNo+" stage "+name+" negotiation "+nextplan+" id "+id);

                WebService.getClient().deal_remainder_web(login_emp,Vemp,String_modelget,FollowUptype,cat_id,sname,des,dat,loc,mobileNo,name,nextplan,DealType,"","","","","","",id).enqueue(new Callback<deal_Remainder_model>() {
                    @Override
                    public void onResponse(Call<deal_Remainder_model> call, Response<deal_Remainder_model> response) {
                        pro.dismiss();



                        if ("Record added Succesfully".equals(response.body().getMsg())){

                            Log.d("TAG", "onResponse: Check_whatsapp_Message");

                            Toast.makeText(DealFormGeneralActivityViewINQ.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            if (DealstageRecyclerActivityViewINQ.firstmeeting_otpsend_flag){
                                Log.d("TAG", "onResponse: Check_whatsapp_Message-1");

                                DealstageRecyclerActivityViewINQ.firstmeeting_otpsend_flag = false;
                                startActivity(new Intent(DealFormGeneralActivityViewINQ.this, OtpDealActivityViewINQ.class)
                                        .putExtra("id",String.valueOf(response.body().getId())));
                                finish();
                            }
                            else if (DealstageRecyclerActivityViewINQ.deal_nextactivityplanInquiry_flag_sendimage){
                                Log.d("TAG", "onResponse: Check_whatsapp_Message-2");

                                Intent i = new Intent(DealFormGeneralActivityViewINQ.this, Warm3StageActivityViewINQ.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.putExtra("vemp", Vemp);
                                i.putExtra("cat_id", cat_id);
                                i.putExtra("sname", sname);
                                i.putExtra("mobo", mobileNo);
                                i.putExtra("id", String.valueOf(response.body().getId()));

                                startActivity(i);
                            }
                            else if (DealstageRecyclerActivityViewINQ.makeandoffer_dropdown_flag){
                                Log.d("TAG", "onResponse: Check_whatsapp_Message-3");

                                Intent i = new Intent(DealFormGeneralActivityViewINQ.this, MakeandofferDealViewINQ.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.putExtra("id",String.valueOf(response.body().getId()));
                                i.putExtra("sname",sname);

                                startActivity(i);

                            }
                            else {
                                WhatsappMessage1();
//                                Log.d("TAG", "onResponse: Check_whatsapp_Message-4");
//                                if (nextplan.equals("Reminder")){
//                                    startActivity(new Intent(DealFormGeneralActivityViewINQ.this, DealViewMainActivityNegotiation.class));
//                                    finish();
//                                }else{
                                    startActivity(new Intent(DealFormGeneralActivityViewINQ.this, DealViewMainActivity.class));
                                    finish();
//                                }

                            }
                        }
                        else {
                            Log.d("TAG", "chcekdsfsdfksjk: "+response.body().getMsg());
                            Utils.showErrorToast(DealFormGeneralActivityViewINQ.this,"Somethign went wrong"+response.body().getMsg());
                        }

                    }

                    @Override
                    public void onFailure(Call<deal_Remainder_model> call, Throwable t) {
                        pro.dismiss();
                        Utils.showErrorToast(DealFormGeneralActivityViewINQ.this, ""+t.getMessage());
                    }
                });
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
//                    Toast.makeText(DealFormGeneralActivityViewINQ.this, "No Data Found !", Toast.LENGTH_SHORT).show();
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
                            "सर्विस MO:-7505786792";

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
                Utils.showErrorToast(DealFormGeneralActivityViewINQ.this,t.getMessage());

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

        Log.d("TAG", "WhatsappMessage: Check_whatsapp_Message-6");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+91"+Whatsappnumber + "&text=" + message));
        startActivity(intent);

    }

    private void getlocation()  {
        pro.show();
        pro.setMessage("Please wait get location...");
        pro.setCancelable(false);
        LocationManager locationManager = (LocationManager) DealFormGeneralActivityViewINQ.this.getSystemService(DealFormGeneralActivityViewINQ.this.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(DealFormGeneralActivityViewINQ.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(DealFormGeneralActivityViewINQ.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        try {
                            Geocoder geocoder = new Geocoder(DealFormGeneralActivityViewINQ.this, Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

//                            binding.location.setText(addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName());
                            //  Toast.makeText(DealFormGeneralActivityViewINQ.this, addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();


                            locationget.setVisibility(View.VISIBLE);
                            locationget.setText(addresses.get(0).getAddressLine(0)+" , "+addresses.get(0).getSubLocality()+" , "+addresses.get(0).getLocality()
                                    +" , "+ addresses.get(0).getAdminArea()+" , "+addresses.get(0).getCountryName()+" , "+String.valueOf(addresses.get(0).getLatitude())+" , "+
                                    String.valueOf(addresses.get(0).getLongitude()));

                            getlocation.setVisibility(View.GONE);
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
                                    Geocoder geocoder = new Geocoder(DealFormGeneralActivityViewINQ.this, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(location1.getLatitude(), location1.getLongitude(), 0);

                                    //  Toast.makeText(DealFormGeneralActivityViewINQ.this, addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();

                                    locationget.setVisibility(View.VISIBLE);
                                    locationget.setText(addresses.get(0).getAddressLine(0)+" , "+addresses.get(0).getSubLocality()+" , "+addresses.get(0).getLocality()
                                            +" , "+ addresses.get(0).getAdminArea()+" , "+addresses.get(0).getCountryName()+" , "+String.valueOf(addresses.get(0).getLatitude())+" , "+
                                            String.valueOf(addresses.get(0).getLongitude()));

                                    getlocation.setVisibility(View.GONE);
                                    pro.dismiss();


                                } catch (Exception e) {
                                    pro.dismiss();
                                    getlocation();
                                    //Toast.makeText(getApplicationContext(), " catch id 2"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        };
                        if (ActivityCompat.checkSelfPermission(DealFormGeneralActivityViewINQ.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(DealFormGeneralActivityViewINQ.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            return;
                        }
                        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                    }
                }
            });
        }
        else {
            pro.dismiss();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DealFormGeneralActivityViewINQ.this);


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
}