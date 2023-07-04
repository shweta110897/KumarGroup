package com.example.kumarGroup.ReportCollection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
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
import android.os.Looper;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.InsertRCSecondModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
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

public class RCNewEntryFormActivity extends AppCompatActivity {


    EditText edtRcNewEntryCategory,edtRcNewEntryEmployeeName,
            edtRcNewEntryVisitReason,edtRcNewEntryBookAmount,edtRcNewEntryModelName,
            edtRcNewEntryPaymentCollection,edtRcNewEntrySellLost,edtRcNewEntrySetVisit;

    Spinner spnRcNewEntryBooking,spnRcNewEntryDelivery,spnRcNewEntryPaymentCollection,
            spnRcNewEntryAddgcust,spnRcNewEntrySellLost;

    TextView locationget,txt_Customer_visit;

    ImageView img_Customer_visit;

    Button btnRcNewEntrySubmit,btngetLocation;

    String catname,customername;
    String catId, SnameId,id;

    Uri Customer_visit;
    String Customer_visit1;

    String sellLost;
    String[] sellLost1={"Sell Lost","Yes","No"};

    String BookingType1;
    String[] BookingType = {"Booking","Yes","No"};

    String Delivery1;
    String[] Delivery = {"Delivery","Yes","No"};

    String PaymentCollection;
    String[] PaymentCollection1= {"Payment Collection","Yes","No"};

    String AddgestingCustomer_mdw;
    String[] addgestingCustomer1_mdw = {"Addgesting Customer","Yes","No"};

    ProgressDialog progressDialog;

    SharedPreferences sp1;
    String emp,waypath;

    Integer REQUEST_CAMERA = 4, SELECT_FILE = 0;

    String Loaction;

    private FusedLocationProviderClient fusedLocationClient;

    String Date2;

    ProgressDialog pro;

    int day,month,year;
    Calendar mcurrentdate;
    String dayOfWeek;
    String dayCount, MobileNo,add_type,add_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_c_new_entry_form);

        catname = getIntent().getStringExtra("category");
        customername = getIntent().getStringExtra("customer_name");
        catId = getIntent().getStringExtra("catId");
        SnameId = getIntent().getStringExtra("sname");
        id = getIntent().getStringExtra("id");
        MobileNo = getIntent().getStringExtra("MobileNo");

        add_type = getIntent().getStringExtra("add_type");
        add_id = getIntent().getStringExtra("add_id");

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");


     //   Toast.makeText(this, ""+emp+" "+SnameId, Toast.LENGTH_SHORT).show();


        /** Memory Allocation*/

        edtRcNewEntryCategory=findViewById(R.id.edtRcNewEntryCategory);
        edtRcNewEntryEmployeeName=findViewById(R.id.edtRcNewEntryEmployeeName);
        edtRcNewEntryVisitReason=findViewById(R.id.edtRcNewEntryVisitReason);
        edtRcNewEntryBookAmount=findViewById(R.id.edtRcNewEntryBookAmount);
        edtRcNewEntryModelName=findViewById(R.id.edtRcNewEntryModelName);
        edtRcNewEntryPaymentCollection=findViewById(R.id.edtRcNewEntryPaymentCollection);
        edtRcNewEntrySellLost=findViewById(R.id.edtRcNewEntrySellLost);
        edtRcNewEntrySetVisit=findViewById(R.id.edtRcNewEntrySetVisit);

        locationget=findViewById(R.id.locationget);
        txt_Customer_visit=findViewById(R.id.txt_Customer_visit);

        spnRcNewEntryBooking=findViewById(R.id.spnRcNewEntryBooking);
        spnRcNewEntryDelivery=findViewById(R.id.spnRcNewEntryDelivery);
        spnRcNewEntryPaymentCollection=findViewById(R.id.spnRcNewEntryPaymentCollection);
        spnRcNewEntryAddgcust=findViewById(R.id.spnRcNewEntryAddgcust);
        spnRcNewEntrySellLost=findViewById(R.id.spnRcNewEntrySellLost);

        btnRcNewEntrySubmit=findViewById(R.id.btnRcNewEntrySubmit);
        btngetLocation=findViewById(R.id.btngetLocation);

        img_Customer_visit=findViewById(R.id.img_Customer_visit);

        edtRcNewEntryEmployeeName.setText(customername);
        edtRcNewEntryCategory.setText(catname);

        edtRcNewEntryEmployeeName.setFocusable(false);
        edtRcNewEntryCategory.setFocusable(false);
        edtRcNewEntrySetVisit.setFocusable(false);

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );
        // month = month+1;

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(RCNewEntryFormActivity.this);
        pro = new ProgressDialog(RCNewEntryFormActivity.this);

        txt_Customer_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
            }
        });

        btngetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getlocation();
            }
        });

        edtRcNewEntrySetVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RCNewEntryFormActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth)
                    {
                        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                        Date date = new Date(year, monthofYear, dayOfMonth-1);
                        dayOfWeek = simpledateformat.format(date);
                        Log.d("dayOfWeek", "onDateSet: "+dayOfWeek);

                        if(dayOfWeek.equals("Monday"))
                        {
                            dayCount="0";
                        }

                        if(dayOfWeek.equals("Tuesday"))
                        {
                            dayCount="1";
                        }
                        if(dayOfWeek.equals("Wednesday"))
                        {
                            dayCount="2";
                        }
                        if(dayOfWeek.equals("Tuesday"))
                        {
                            dayCount="3";
                        }
                        if(dayOfWeek.equals("Friday"))
                        {
                            dayCount="4";
                        }
                        if(dayOfWeek.equals("Saturday"))
                        {
                            dayCount="5";
                        }
                        if(dayOfWeek.equals("Sunday"))
                        {
                            dayCount="6";
                        }

                        monthofYear = monthofYear+1;

                        String mt,dy;   //local variable
                        if(monthofYear<10) {
                            mt = "0" + monthofYear; //if month less than 10 then ad 0 before month
                        }
                        else{
                            mt=String.valueOf(monthofYear);
                        }

                        if(dayOfMonth<10) {
                            dy = "0" + dayOfMonth;
                        }
                        else{
                            dy = String.valueOf(dayOfMonth);
                        }

                        //2020-09-22
                        //edtAddLoanDate.setText(mt+"/"+dy+"/"+year);
                        edtRcNewEntrySetVisit.setText(year+"-"+mt+"-"+dy);
                        //Date2 = year+"-"+monthofYear+"-"+dy ;
                        Date2 = year+"-"+mt+"-"+dy;
                        Log.d("NewDate", "onDateSet: "+Date2);
                    }
                },year,month,day);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            }
        });

        sellLost="";

        ArrayAdapter adapterSellLost = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,sellLost1);
        spnRcNewEntrySellLost.setAdapter(adapterSellLost);


        spnRcNewEntrySellLost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sellLost= sellLost1[position];
                if(sellLost1[position]=="Yes"){
                    edtRcNewEntrySetVisit.setVisibility(View.GONE);
                    edtRcNewEntrySellLost.setVisibility(View.VISIBLE);
                }else {
                    edtRcNewEntrySetVisit.setVisibility(View.VISIBLE);
                    edtRcNewEntrySellLost.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        AddgestingCustomer_mdw="";
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,addgestingCustomer1_mdw);
        spnRcNewEntryAddgcust.setAdapter(arrayAdapter);

        spnRcNewEntryAddgcust.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AddgestingCustomer_mdw = addgestingCustomer1_mdw[position];
                if(addgestingCustomer1_mdw[position]=="Yes"){
                    edtRcNewEntrySetVisit.setVisibility(View.GONE);
                }else {
                    edtRcNewEntrySetVisit.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,BookingType);
        spnRcNewEntryBooking.setAdapter(adapter);

        spnRcNewEntryBooking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BookingType1 = BookingType[position];
                if(BookingType[position]=="Yes"){
                    //BookingType1 = "Yes";
                    edtRcNewEntryBookAmount.setVisibility(View.VISIBLE);
                }
                else{
                    //BookingType1 = "1";
                    edtRcNewEntryBookAmount.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Delivery1 = "";

        ArrayAdapter deliveryAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Delivery);
        spnRcNewEntryDelivery.setAdapter(deliveryAdapter);

        spnRcNewEntryDelivery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Delivery1 = Delivery[position];
                if(Delivery[position]=="Yes"){
                    edtRcNewEntryModelName.setVisibility(View.VISIBLE);
                    edtRcNewEntrySetVisit.setVisibility(View.GONE);
                }
                else{
                    edtRcNewEntryModelName.setVisibility(View.GONE);
                    edtRcNewEntrySetVisit.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter PaymentCollectionAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,PaymentCollection1);
        spnRcNewEntryPaymentCollection.setAdapter(PaymentCollectionAdapter);

        spnRcNewEntryPaymentCollection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PaymentCollection = PaymentCollection1[position];
                if(PaymentCollection1[position]=="Yes"){
                    edtRcNewEntryPaymentCollection.setVisibility(View.VISIBLE);
                }
                else{
                    edtRcNewEntryPaymentCollection.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnRcNewEntrySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(RCNewEntryFormActivity.this, ""+edtRcNewEntryCategory.getText().toString()+" "+add_type, Toast.LENGTH_SHORT).show();

                /*if (edtOGVFCategoryName.getText().toString().equals("")) {
                    edtOGVFCategoryName.setError("Please Enter Category Name");
                }*/

                if (edtRcNewEntryEmployeeName.getText().toString().equals("")) {
                    edtRcNewEntryEmployeeName.setError("Please Enter Employee Name");
                }

                if (edtRcNewEntryVisitReason.getText().toString().equals("")) {
                    edtRcNewEntryVisitReason.setError("Please Enter Visit Reason");
                }

                if(PaymentCollection.equals("Payment Collection")){
                    Utils.showErrorToast(RCNewEntryFormActivity.this,
                            "Please Select Yes or No Payment Collection");
                }

                if (edtRcNewEntryPaymentCollection.getText().toString().equals("")) {
                    edtRcNewEntryPaymentCollection.setError("Please Enter Payment Collection Amount");
                }

                if (edtRcNewEntrySetVisit.getText().toString().equals("")) {
                    edtRcNewEntrySetVisit.setError("Please Enter Visit Date");
                }

                if (!edtRcNewEntryEmployeeName.getText().toString().equals("")  &&
                        !edtRcNewEntryVisitReason.getText().toString().equals("")  &&
                        !edtRcNewEntrySetVisit.getText().toString().equals("")) {

                    progressDialog = new ProgressDialog(RCNewEntryFormActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    if (Loaction==null){
                        Loaction="";
                    }

                    MultipartBody.Part img_cus = null;
                    if(Customer_visit1 != null){
                        File file1 = new File(Customer_visit1);
                        final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        img_cus = MultipartBody.Part.createFormData("image1",
                                file1.getName(), requestBody1);
                    }

                    /*if (waypath == null) {
                        Utils.showErrorToast(RCNewEntryFormActivity.this, "Please upload Selfie Photo");
                    } else {
                        Log.d("upload image", "onClick: " + waypath);
                        File file1 = new File(waypath);

                        final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        MultipartBody.Part imagePartAdhar = MultipartBody.Part.createFormData("image1",
                                file1.getName(), requestBody1);

                        Log.d("TAG", "onClick: dfhakjsdff " + imagePartAdhar
                                + "\ncurrentPhotoPath " + waypath
                        );*/
//                        Log.d("TAG", "onClick: CheckBooking " + Delivery1);
                        WebService.getClient().getInsertRcSecond(RequestBody.create(MediaType.parse("text/plain"), id),
                                RequestBody.create(MediaType.parse("text/plain"), emp),
                                RequestBody.create(MediaType.parse("text/plain"), edtRcNewEntryCategory.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"), SnameId),
                                RequestBody.create(MediaType.parse("text/plain"), edtRcNewEntryVisitReason.getText().toString()),
//                            RequestBody.create(MediaType.parse("text/plain"), BookingType1),
                                RequestBody.create(MediaType.parse("text/plain"), edtRcNewEntryBookAmount.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"), Delivery1),
                                RequestBody.create(MediaType.parse("text/plain"), edtRcNewEntryModelName.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"), PaymentCollection),
                                RequestBody.create(MediaType.parse("text/plain"), edtRcNewEntryPaymentCollection.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"), Date2),
                                RequestBody.create(MediaType.parse("text/plain"), dayCount),
                                RequestBody.create(MediaType.parse("text/plain"), AddgestingCustomer_mdw),
                                RequestBody.create(MediaType.parse("text/plain"), sellLost),
                                RequestBody.create(MediaType.parse("text/plain"), edtRcNewEntrySellLost.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"), MobileNo),
                                RequestBody.create(MediaType.parse("text/plain"), add_type),
                                RequestBody.create(MediaType.parse("text/plain"), add_id),
                                RequestBody.create(MediaType.parse("text/plain"), Loaction),
                                null).enqueue(new Callback<InsertRCSecondModel>() {
                            @Override
                            public void onResponse(@NotNull Call<InsertRCSecondModel> call, @NotNull Response<InsertRCSecondModel> response) {
                                progressDialog.dismiss();
                                assert response.body() != null;
                                Toast.makeText(RCNewEntryFormActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                                //   Toast.makeText(RCNewEntryFormActivity.this, ""+SnameId+" "+emp, Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(RCNewEntryFormActivity.this, FoDashbord.class);
                                startActivity(i);
                            }

                            @Override
                            public void onFailure(@NotNull Call<InsertRCSecondModel> call, @NotNull Throwable t) {
                                progressDialog.dismiss();
                                Toast.makeText(RCNewEntryFormActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
//                }
            }
        });

    }
    private void getlocation()  {
        pro.show();
        pro.setMessage("Please wait get location...");
        pro.setCancelable(false);
        LocationManager locationManager = (LocationManager) RCNewEntryFormActivity.this.getSystemService(RCNewEntryFormActivity.this.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(RCNewEntryFormActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(RCNewEntryFormActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        try {
                            Geocoder geocoder = new Geocoder(RCNewEntryFormActivity.this, Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

//                            binding.location.setText(addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName());
                            //  Toast.makeText(DealFormGeneralActivity.this, addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();


                            locationget.setVisibility(View.VISIBLE);
                            Loaction = addresses.get(0).getAddressLine(0)+" , "+addresses.get(0).getSubLocality()+" , "+addresses.get(0).getLocality()
                                    +" , "+ addresses.get(0).getAdminArea()+" , "+addresses.get(0).getCountryName()+" , "+String.valueOf(addresses.get(0).getLatitude())+" , "+
                                    String.valueOf(addresses.get(0).getLongitude());
                            locationget.setText(Loaction);

                            btngetLocation.setVisibility(View.GONE);
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
                                    Geocoder geocoder = new Geocoder(RCNewEntryFormActivity.this, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(location1.getLatitude(), location1.getLongitude(), 0);

                                    //  Toast.makeText(DealFormGeneralActivity.this, addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();

                                    locationget.setVisibility(View.VISIBLE);
                                    locationget.setText(addresses.get(0).getAddressLine(0)+" , "+addresses.get(0).getSubLocality()+" , "+addresses.get(0).getLocality()
                                            +" , "+ addresses.get(0).getAdminArea()+" , "+addresses.get(0).getCountryName()+" , "+String.valueOf(addresses.get(0).getLatitude())+" , "+
                                            String.valueOf(addresses.get(0).getLongitude()));

                                    btngetLocation.setVisibility(View.GONE);
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
                        if (ActivityCompat.checkSelfPermission(RCNewEntryFormActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(RCNewEntryFormActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            return;
                        }
                        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                    }
                }
            });
        }
        else {
            pro.dismiss();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RCNewEntryFormActivity.this);


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
    private void SelectImage() {

        final CharSequence[] items = {"Camera", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(RCNewEntryFormActivity.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {
//                    try {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 4);
//                        currentPhotoPath=  dispatchTakePictureIntent1(RCNewEntryFormActivity.this).getAbsolutePath();
                   /* } catch (IOException e) {
                        e.printStackTrace();
                    }*/
//                    dispatchTakePictureIntent(RCNewEntryFormActivity.this);
//                    MediaStore.ACTION_IMAGE_CAPTURE

                } else if (items[i].equals("Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);
                } else if (items[i].equals("Cancel"))
                    dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4) {
            Bundle bundle = data.getExtras();
            final Bitmap photo = (Bitmap) bundle.get("data");
            img_Customer_visit.setImageBitmap(photo);
            savebitmap(photo);
         /*   File file=new File(waypath);
            saveBitmaptoFile(file);*/

        }
        /*if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {

                    File f = new File(currentPhotoPath);
                    getImageBitmap(currentPhotoPath,img_Customer_visit,f,this);

                }

            }
        }*/
    }

    private File savebitmap(Bitmap bmp) {
//        String extStorageDirectory;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//            extStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
//        } else {
//            extStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
//        }
        String extStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();/* Environment.getExternalStorageDirectory().getPath();*/
        FileOutputStream outStream = null;
        // String temp = null;

        final int random = new Random().nextInt(6000000) + 20;
        File file = new File(extStorageDirectory, random + ".png");
        waypath = getFilePath(this, Uri.fromFile(file));
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, random + ".png");
            waypath = getFilePath(this, Uri.fromFile(file));
        }
        try {
            outStream = new FileOutputStream(file);
//            Bitmap scaledBitmap = scaleDown(bmp, 65, true);
//            bmp.compress(Bitmap.CompressFormat.PNG, 30, outStream);
//            scaledBitmap.compress(Bitmap.CompressFormat.PNG, 65, outStream);
            bmp.compress(Bitmap.CompressFormat.PNG, 70, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
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