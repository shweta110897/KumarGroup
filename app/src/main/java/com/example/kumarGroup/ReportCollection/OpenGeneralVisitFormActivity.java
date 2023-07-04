package com.example.kumarGroup.ReportCollection;

import static com.example.kumarGroup.ChackIn.imageUri;
import static com.example.kumarGroup.Utils.CAMERA_REQUEST_CODE;
import static com.example.kumarGroup.Utils.currentPhotoPath;
import static com.example.kumarGroup.Utils.getImageBitmap;

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

import com.example.kumarGroup.OpenVisitRCGVModel;
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

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenGeneralVisitFormActivity extends AppCompatActivity {

    String Name,Category,cat_id,sname,id,MobileNo;
    EditText edtOGVFCategoryName,edtOGVFEmployeeName,edtOGVFVisitReason,
            edtOGVFBookAmount,edtOGVFModelName,edtOGVFPaymentCollection,
            edtOGVFSellLost,edtOGVFSetVisit;

    Spinner spnOGVFBooking,spnOGVFDelivery,spnOGVFPaymentCollection,spnOGVFAddgcust,spnOGVFSellLost,spnBookingTYpe;

    Button btnOGVFSubmit,btngetLocation;

    TextView locationget,txt_Customer_visit;

    ProgressDialog pro;

    ImageView img_Customer_visit;

    Uri Customer_visit;
    String Customer_visit1;

    Integer REQUEST_CAMERA = 4, SELECT_FILE = 0;


    private FusedLocationProviderClient fusedLocationClient;


    String Loaction;

    String sellLostOGVS;
    String[] sellLost1_OGVS={"Sell Lost","Yes","No"};

    String BookingTypeOGVS;
    String[] BookingType1_OGVS = {"Booking","Yes","No"};

    String DeliveryOGVS;
    String[] Delivery1_OGVS = {"Delivery","Yes","No"};

    String PaymentCollectionOGVS;
    String[] PaymentCollection1_OGVS= {"Payment Collection","Yes","No"};

    String AddgestingCustomer_OGVS;
    String[] addgestingCustomer1_OGVS = {"Addgesting Customer","Yes","No"};

    String TypeInq;
    String[] Type_inq = {"Select Type","Hot","Could"};

    ProgressDialog progressDialog;

    SharedPreferences sp1;
    String emp;

    int day,month,year;
    Calendar mcurrentdate;
    String dayOfWeek;
    String dayCount;


    String Date2;

    String add_id, add_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_general_visit_form);



        /** Allocate Memory */

        edtOGVFCategoryName=findViewById(R.id.edtOGVFCategoryName);
        edtOGVFEmployeeName=findViewById(R.id.edtOGVFEmployeeName);
        edtOGVFVisitReason=findViewById(R.id.edtOGVFVisitReason);
        edtOGVFBookAmount=findViewById(R.id.edtOGVFBookAmount);
        edtOGVFModelName=findViewById(R.id.edtOGVFModelName);
        edtOGVFPaymentCollection=findViewById(R.id.edtOGVFPaymentCollection);
        edtOGVFSellLost=findViewById(R.id.edtOGVFSellLost);
        edtOGVFSetVisit=findViewById(R.id.edtOGVFSetVisit);

        spnOGVFBooking=findViewById(R.id.spnOGVFBooking);
        spnOGVFDelivery=findViewById(R.id.spnOGVFDelivery);
        spnOGVFPaymentCollection=findViewById(R.id.spnOGVFPaymentCollection);
        spnOGVFAddgcust=findViewById(R.id.spnOGVFAddgcust);
        spnOGVFSellLost=findViewById(R.id.spnOGVFSellLost);
//        spnBookingTYpe=findViewById(R.id.spnBookingTYpe);


        locationget=findViewById(R.id.locationget);
        txt_Customer_visit=findViewById(R.id.txt_Customer_visit);
        img_Customer_visit=findViewById(R.id.img_Customer_visit);

        btnOGVFSubmit=findViewById(R.id.btnOGVFSubmit);
        btngetLocation=findViewById(R.id.btngetLocation);

        /** -------------------------------------------------------  */



        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        Name= getIntent().getStringExtra("Name");
        Category=getIntent().getStringExtra("Category");
        cat_id=getIntent().getStringExtra("cat_id");
        sname=getIntent().getStringExtra("sname");
        id=getIntent().getStringExtra("id");
        MobileNo=getIntent().getStringExtra("MobileNo");

        add_id=getIntent().getStringExtra("add_id");
        add_type=getIntent().getStringExtra("add_type");


        edtOGVFCategoryName.setText(Category);
        edtOGVFEmployeeName.setText(Name);

       /* edtOGVFEmployeeName.setFocusable(false);
        edtOGVFCategoryName.setFocusable(false);*/
        edtOGVFSetVisit.setFocusable(false);

    //    Toast.makeText(this, "data:"+Name+" "+sname+" "+emp, Toast.LENGTH_SHORT).show();


    //    Log.d("Sname_Emp", "onCreate: "+sname+" "+emp);


        /** Next Visit date Picker */

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );
        // month = month+1;


        txt_Customer_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent gal = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                gal.setType("image/*");
//                startActivityForResult(gal, 1);
                SelectImage();
            }
        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(OpenGeneralVisitFormActivity.this);
        pro = new ProgressDialog(OpenGeneralVisitFormActivity.this);

        btngetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getlocation();
            }
        });

        edtOGVFSetVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(OpenGeneralVisitFormActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
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
                        edtOGVFSetVisit.setText(year+"-"+mt+"-"+dy);
                       // Date2 = year+"-"+monthofYear+"-"+dy ;
                        Date2 = year+"-"+mt+"-"+dy ;
                        Log.d("NewDate", "onDateSet: "+Date2);
                    }
                },year,month,day);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

            }
        });


     /*   ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Type_inq);
        spnBookingTYpe.setAdapter(arrayAdapter1);

        spnBookingTYpe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/


        ArrayAdapter adapterSellLost = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,sellLost1_OGVS);
        spnOGVFSellLost.setAdapter(adapterSellLost);

        spnOGVFSellLost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sellLostOGVS= sellLost1_OGVS[position];
                if(sellLost1_OGVS[position]=="Yes"){
                    edtOGVFSetVisit.setVisibility(View.GONE);
                    edtOGVFSellLost.setVisibility(View.VISIBLE);
                }else {
                    edtOGVFSetVisit.setVisibility(View.VISIBLE);
                    edtOGVFSellLost.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,addgestingCustomer1_OGVS);
        spnOGVFAddgcust.setAdapter(arrayAdapter);

        spnOGVFAddgcust.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AddgestingCustomer_OGVS = addgestingCustomer1_OGVS[position];
                if(addgestingCustomer1_OGVS[position]=="Yes"){
                    edtOGVFSetVisit.setVisibility(View.GONE);
                }else {
                    edtOGVFSetVisit.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,BookingType1_OGVS);
        spnOGVFBooking.setAdapter(adapter);

        spnOGVFBooking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BookingTypeOGVS = BookingType1_OGVS[position];
                if(BookingType1_OGVS[position]=="Yes"){
                    //BookingType1 = "Yes";
                    edtOGVFBookAmount.setVisibility(View.VISIBLE);
                }
                else{
                    //BookingType1 = "1";
                    edtOGVFBookAmount.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter deliveryAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Delivery1_OGVS);
        spnOGVFDelivery.setAdapter(deliveryAdapter);

        spnOGVFDelivery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DeliveryOGVS = Delivery1_OGVS[position];
                if(Delivery1_OGVS[position]=="Yes"){
                    edtOGVFModelName.setVisibility(View.VISIBLE);
                    edtOGVFSetVisit.setVisibility(View.GONE);
                }
                else{
                    edtOGVFModelName.setVisibility(View.GONE);
                    edtOGVFSetVisit.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter PaymentCollectionAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,PaymentCollection1_OGVS);
        spnOGVFPaymentCollection.setAdapter(PaymentCollectionAdapter);

        spnOGVFPaymentCollection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PaymentCollectionOGVS = PaymentCollection1_OGVS[position];
                if(PaymentCollection1_OGVS[position]=="Yes"){
                    edtOGVFPaymentCollection.setVisibility(View.VISIBLE);
                }
                else{
                    edtOGVFPaymentCollection.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnOGVFSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Sname", "onCreate: "+sname+" "+emp);
                Toast.makeText(OpenGeneralVisitFormActivity.this, ""+cat_id, Toast.LENGTH_SHORT).show();

                if (edtOGVFCategoryName.getText().toString().equals("")) {
                    edtOGVFCategoryName.setError("Please Enter Category Name");
                }

                if (edtOGVFEmployeeName.getText().toString().equals("")) {
                    edtOGVFEmployeeName.setError("Please Enter Employee Name");
                }

                if (edtOGVFVisitReason.getText().toString().equals("")) {
                    edtOGVFVisitReason.setError("Please Enter Visit Reason");
                }

                if(PaymentCollectionOGVS.equals("Payment Collection")){
                    Utils.showErrorToast(OpenGeneralVisitFormActivity.this,
                            "Please Select Yes or No Payment Collection");
                }

                if (edtOGVFPaymentCollection.getText().toString().equals("")) {
                    edtOGVFPaymentCollection.setError("Please Enter Payment Collection Amount");
                }

                if (edtOGVFSetVisit.getText().toString().equals("")) {
                    edtOGVFSetVisit.setError("Please Enter Visit Date");
                }


                if (!edtOGVFCategoryName.getText().toString().equals("") &&
                        !edtOGVFEmployeeName.getText().toString().equals("")  &&
                        !edtOGVFVisitReason.getText().toString().equals("")  &&
                        !edtOGVFSetVisit.getText().toString().equals("")) {

                    progressDialog= new ProgressDialog(OpenGeneralVisitFormActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    /*WebService.getClient().getOpenVisitRCGV(id,
                            emp,
                            sname,
                            cat_id,
                            edtOGVFVisitReason.getText().toString(),
                            BookingTypeOGVS,
                            edtOGVFBookAmount.getText().toString(),
                            DeliveryOGVS,
                            edtOGVFModelName.getText().toString(),
                            PaymentCollectionOGVS,
                            edtOGVFPaymentCollection.getText().toString(),
                            Date2,
                            dayCount,
                            AddgestingCustomer_OGVS,
                            sellLostOGVS,
                            edtOGVFSellLost.getText().toString(),
                            MobileNo,
                            add_id,
                            add_type
                            ).enqueue(new Callback<OpenVisitRCGVModel>() {
                        @Override
                        public void onResponse(@NotNull Call<OpenVisitRCGVModel> call,
                                               @NotNull Response<OpenVisitRCGVModel> response) {
                            progressDialog.dismiss();
                            assert response.body() != null;
                            Toast.makeText(OpenGeneralVisitFormActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(OpenGeneralVisitFormActivity.this, ReportCollectionMainActivity.class);
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(@NotNull Call<OpenVisitRCGVModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(OpenGeneralVisitFormActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });*/


                    MultipartBody.Part img_cus = null;
                    if(Customer_visit1 != null){
                        File file1 = new File(Customer_visit1);
                        final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        img_cus = MultipartBody.Part.createFormData("image1",
                                file1.getName(), requestBody1);
                    }

                   /* if (currentPhotoPath == null) {
                        Utils.showErrorToast(OpenGeneralVisitFormActivity.this, "Please upload Selfie Photo");
                    } else {
                        Log.d("upload image", "onClick: " + currentPhotoPath);
                        File file1 = new File(currentPhotoPath);

                        final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        MultipartBody.Part imagePartAdhar = MultipartBody.Part.createFormData("image1",
                                file1.getName(), requestBody1);

                        Log.d("TAG", "onClick: dfhakjsdff " + imagePartAdhar
                                + "\ncurrentPhotoPath " + currentPhotoPath
                        );*/
                        WebService.getClient().getOpenVisitRCGV(RequestBody.create(MediaType.parse("text/plain"), ""),
                                RequestBody.create(MediaType.parse("text/plain"), emp),
                                RequestBody.create(MediaType.parse("text/plain"), sname),
                                RequestBody.create(MediaType.parse("text/plain"), cat_id),
                                RequestBody.create(MediaType.parse("text/plain"), edtOGVFVisitReason.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"), ""),
                                RequestBody.create(MediaType.parse("text/plain"), ""),
                                RequestBody.create(MediaType.parse("text/plain"), ""),
                                RequestBody.create(MediaType.parse("text/plain"), ""),
                                RequestBody.create(MediaType.parse("text/plain"), PaymentCollectionOGVS),
                                RequestBody.create(MediaType.parse("text/plain"), edtOGVFPaymentCollection.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"), Date2),
                                RequestBody.create(MediaType.parse("text/plain"), ""),
                                RequestBody.create(MediaType.parse("text/plain"), ""),
                                RequestBody.create(MediaType.parse("text/plain"), ""),
                                RequestBody.create(MediaType.parse("text/plain"), ""),
                                RequestBody.create(MediaType.parse("text/plain"), MobileNo),
                                RequestBody.create(MediaType.parse("text/plain"), add_id),
                                RequestBody.create(MediaType.parse("text/plain"), add_type),
                                RequestBody.create(MediaType.parse("text/plain"), Loaction),
                                null
                        ).enqueue(new Callback<OpenVisitRCGVModel>() {
                            @Override
                            public void onResponse(@NotNull Call<OpenVisitRCGVModel> call,
                                                   @NotNull Response<OpenVisitRCGVModel> response) {
                                progressDialog.dismiss();
                                assert response.body() != null;
                                Toast.makeText(OpenGeneralVisitFormActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                                //  Toast.makeText(OpenGeneralVisitFormActivity.this, "Data"+sname+" "+emp, Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(OpenGeneralVisitFormActivity.this,
                                        ReportCollectionMainActivity.class);
                                startActivity(i);
                            }

                            @Override
                            public void onFailure(@NotNull Call<OpenVisitRCGVModel> call, @NotNull Throwable t) {
                                progressDialog.dismiss();
                                Toast.makeText(OpenGeneralVisitFormActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
//                    }
                }
            }
        });
    }
    private void getlocation()  {
        pro.show();
        pro.setMessage("Please wait get location...");
        pro.setCancelable(false);
        LocationManager locationManager = (LocationManager) OpenGeneralVisitFormActivity.this.getSystemService(OpenGeneralVisitFormActivity.this.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(OpenGeneralVisitFormActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(OpenGeneralVisitFormActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        try {
                            Geocoder geocoder = new Geocoder(OpenGeneralVisitFormActivity.this, Locale.getDefault());
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
                                    Geocoder geocoder = new Geocoder(OpenGeneralVisitFormActivity.this, Locale.getDefault());
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
                        if (ActivityCompat.checkSelfPermission(OpenGeneralVisitFormActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(OpenGeneralVisitFormActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            return;
                        }
                        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                    }
                }
            });
        }
        else {
            pro.dismiss();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OpenGeneralVisitFormActivity.this);


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
        AlertDialog.Builder builder = new AlertDialog.Builder(OpenGeneralVisitFormActivity.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {
//                    MediaStore.ACTION_IMAGE_CAPTURE
                    File filename = new File( Environment.getExternalStorageDirectory().getPath() + "/test/");
                    try {
                        filename.mkdirs();
                    }
                    catch (Exception e){
                        Toast.makeText(OpenGeneralVisitFormActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                    File file = new File(filename, "image" + ".jpg");
                    imageUri = Uri.fromFile(file);
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                            imageUri);

                    if (cameraIntent.resolveActivity(OpenGeneralVisitFormActivity.this.getPackageManager()) != null) {
                        Log.e("DATA", String.valueOf(cameraIntent.getData()));
                        startActivityForResult(cameraIntent, 4);
                    }
                  /*  Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);*/
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
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {

                    File f = new File(currentPhotoPath);
                    getImageBitmap(currentPhotoPath,img_Customer_visit,f,this);

                }

            }
        }
    }

    private File savebitmap(Bitmap bmp) {

//        String extStorageDirectory;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//            extStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
//        } else {
//            extStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
//        }
        String extStorageDirectory = Environment.getExternalStorageDirectory().getPath()+ "/test/";
        FileOutputStream outStream = null;
        // String temp = null;
        File file = new File(extStorageDirectory, "image" + ".jpg");
        currentPhotoPath = getFilePath(this, Uri.fromFile(file));
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, "image" + ".jpg");
            currentPhotoPath = getFilePath(this, Uri.fromFile(file));
        }
        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
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