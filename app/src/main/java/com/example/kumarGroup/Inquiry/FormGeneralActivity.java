package com.example.kumarGroup.Inquiry;

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

import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.new_visit_model;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormGeneralActivity extends AppCompatActivity {

    EditText edtDescriptionIG,edtNextVisitIG;

    Spinner spnFollowUpTypeIG;

    Button btnSubmitIG,getlocation;

    String TypeInq;
    String[] Type_inq = {"Select Type","Hot","Cold"};

    String FollowUptype;
    String[] FollowUpType_list={"Select FollowUp","Call", "Visit","Visit with Dealer","Showroom Visit","Visit with SM"};

    SharedPreferences sp1;
    String emp;

    int day,month,year;
    Calendar mcurrentdate;


    String Name,Category,cat_id,sname,MobileNo,Vemp;

    ProgressDialog pro;

    TextView locationget;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_general);

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        cat_id=getIntent().getStringExtra("cat_id");
        sname=getIntent().getStringExtra("sname");
        Vemp=getIntent().getStringExtra("vemp");

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

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(FormGeneralActivity.this);
        pro = new ProgressDialog(FormGeneralActivity.this);

        getlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getlocation();
            }
        });

        edtNextVisitIG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(FormGeneralActivity.this,
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

        ArrayAdapter adapterFollowUp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                FollowUpType_list);
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
                    Toast.makeText(FormGeneralActivity.this, "Please select follow type", Toast.LENGTH_SHORT).show();
                    return;
                }

                pro.show();
                pro.setCancelable(false);
                pro.setMessage("Please wait ..");
                Log.d("TAG", "logfortest "+FollowUptype+" date "+dat+" des "+des+" loc "+loc);

                WebService.getClient().new_visit_web(emp,Vemp,FollowUptype,cat_id,sname,des,dat,loc).enqueue(new Callback<new_visit_model>() {
                    @Override
                    public void onResponse(Call<new_visit_model> call, Response<new_visit_model> response) {
                        pro.dismiss();
                        Toast.makeText(FormGeneralActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(FormGeneralActivity.this,GeneralInquiryMainActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<new_visit_model> call, Throwable t) {
                        pro.dismiss();
                        Toast.makeText(FormGeneralActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void getlocation()  {
        pro.show();
        pro.setMessage("Please wait get location...");
        pro.setCancelable(false);
        LocationManager locationManager = (LocationManager) FormGeneralActivity.this.getSystemService(FormGeneralActivity.this.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(FormGeneralActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(FormGeneralActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        try {
                            Geocoder geocoder = new Geocoder(FormGeneralActivity.this, Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

//                            binding.location.setText(addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName());
                            //  Toast.makeText(FormGeneralActivity.this, addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();


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
                                    Toast.makeText(FormGeneralActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
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
                                    Geocoder geocoder = new Geocoder(FormGeneralActivity.this, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(location1.getLatitude(), location1.getLongitude(), 0);

                                    //  Toast.makeText(FormGeneralActivity.this, addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();

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
                                            Toast.makeText(FormGeneralActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
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
                        if (ActivityCompat.checkSelfPermission(FormGeneralActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(FormGeneralActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            return;
                        }
                        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                    }
                }
            });
        }
        else {
            pro.dismiss();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(FormGeneralActivity.this);


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