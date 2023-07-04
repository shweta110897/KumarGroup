package com.example.kumarGroup;

import static android.util.Log.d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
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
import android.location.LocationListener;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


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
import java.util.List;
import java.util.Locale;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChackOut extends AppCompatActivity implements LocationListener {

    TextView btnchackout,locationget;
    ImageView mImageViewchackout;
    Integer REQUEST_CAMERA = 4, SELECT_FILE = 0;
    Button btnsubmitchackout,btngetLocation;
    Uri uriclient;
    String emp, id;
    SharedPreferences sp,sp1;
    String waypathClient, waypathchckout,catID;

    ProgressDialog progressDialog;

    String address_line;

    List<Address> addresses;
    LocationManager locationManager;
    String latitude, longitude;
    String Address;
    private FusedLocationProviderClient fusedLocationClient;
    String Loaction;
    // Double latitude;
    // String longitude;
    ProgressDialog pro;


    String PhaseTraveling,idTraveling;

    SharedPreferences sharedPreferences,sp2;

    SharedPreferences spTraveling;


    int date,date1, wsDaily, FeedbackCall_list,overDueCount,overDueCount1;
    String module_name,DailyMechreport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chack_out);

        getSupportActionBar().setTitle(("ChackIn"));
        sp = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp.getString("emp_id", "");
        id = sp.getString("id", "");



        SharedPreferences sharedPreferences2 = getSharedPreferences("catid",MODE_PRIVATE);
        catID = sharedPreferences2.getString("id","");


        sharedPreferences = getSharedPreferences("DateCurrent1",MODE_PRIVATE);
        date = sharedPreferences.getInt("CurrentDateOrNull1",0);


        sp2 = getSharedPreferences("OverDueCheck",MODE_PRIVATE);
        overDueCount = sp2.getInt("OverDueCheckCont",0);


        module_name = sp.getString("module_name", "");


        sharedPreferences = getSharedPreferences("DateCurrent2_inq", MODE_PRIVATE);
        date1 = sharedPreferences.getInt("CurrentDateOrNull1_inq",0);


        sharedPreferences = getSharedPreferences("DateCurrent_ws", MODE_PRIVATE);
        wsDaily= sharedPreferences.getInt("CurrentDateOrNull_ws",0);

        sharedPreferences = getSharedPreferences("FeedbackCall",MODE_PRIVATE);
        FeedbackCall_list=sharedPreferences.getInt("FeedbackCall",0);


        spTraveling = getSharedPreferences("StartTravelling", MODE_PRIVATE);

        PhaseTraveling =  spTraveling.getString("idPhaseTravelling", "");
        idTraveling = spTraveling.getString("idCheckInTravelling", "");


        sharedPreferences = getSharedPreferences("DailyMechanicReport_user", MODE_PRIVATE);
        DailyMechreport = sharedPreferences.getString("DailyMechanicReport_userpera","");


        mImageViewchackout = findViewById(R.id.mImageViewchackout);
        btnchackout = findViewById(R.id.btnchackout);
        btnsubmitchackout = findViewById(R.id.btnsubmitchackout);
        btngetLocation = findViewById(R.id.getlocation);
        locationget = findViewById(R.id.locationget);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(ChackOut.this);
        pro = new ProgressDialog(ChackOut.this);

        btngetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getlocation();
            }
        });



        if(module_name.contains("payment collection") || module_name.contains("Travaling") ||
                module_name.contains("Workshop"))
        {


            Toast.makeText(getApplicationContext(), ""+DailyMechreport, Toast.LENGTH_SHORT).show();

            if(date==1 && date1==1   && PhaseTraveling.equals("1") && wsDaily==1){
                Utils.showErrorToast(this,"Please complete your visit date");
                Utils.showErrorToast_one(this,"Please Traveling CheckOut");
                btnsubmitchackout.setClickable(false);
                btnsubmitchackout.setEnabled(false);
            }

            else if(date==1 || date1==1 )
            {
                Utils.showErrorToast(this,"Please complete your visit date.");
                //Toast.makeText(ChackOut.this, "Please Do CheckIn or checkout in traveling.", Toast.LENGTH_LONG).show();
                btnsubmitchackout.setClickable(false);
                btnsubmitchackout.setEnabled(false);
            }
            else if(wsDaily==1){
                Utils.showErrorToast(this,"complete your visit date Workshop.");
                btnsubmitchackout.setClickable(false);
                btnsubmitchackout.setEnabled(false);
            }

            else if(FeedbackCall_list==1){
                Utils.showErrorToast(this,"Complete Feedback call.");
                btnsubmitchackout.setClickable(false);
                btnsubmitchackout.setEnabled(false);
            }

            else if(PhaseTraveling.equals("1")){
                Utils.showErrorToast_one(this,"Do Checkout in traveling.");
                btnsubmitchackout.setClickable(false);
                btnsubmitchackout.setEnabled(false);
            }

            else if(DailyMechreport.equals("1")){
                Utils.showErrorToast_one(this,"Do Complete Daily mechanic report.");
                btnsubmitchackout.setClickable(false);
                btnsubmitchackout.setEnabled(false);
            }

            else if(module_name.contains("Travaling")){

                if(PhaseTraveling==null || PhaseTraveling.equals(" ") || PhaseTraveling.equals("")){
                    Utils.showErrorToast_one(this,"CheckIn-Checkout in traveling.");

                    //  Utils.showErrorToast(this,"Please complete your visit date.");

                    btnsubmitchackout.setClickable(false);
                    btnsubmitchackout.setEnabled(false);

                }

                else if(PhaseTraveling.equals("2")){
                    Toast.makeText(ChackOut.this, "you should check out...", Toast.LENGTH_SHORT).show();
                    btnsubmitchackout.setClickable(true);
                    btnsubmitchackout.setEnabled(true);
                }
            }else if (overDueCount!=0){
                Utils.showErrorToast_one(ChackOut.this, "Please clear your overdue list");
                btnsubmitchackout.setClickable(false);
                btnsubmitchackout.setEnabled(false);
            }

            else {
                Toast.makeText(this, "you should check out", Toast.LENGTH_SHORT).show();
                btnsubmitchackout.setClickable(true);
                btnsubmitchackout.setEnabled(true);
            }
        }
        else if (overDueCount!=0){
            Utils.showErrorToast_one(ChackOut.this, "Please clear your overdue list");
            btnsubmitchackout.setClickable(false);
            btnsubmitchackout.setEnabled(false);
        }
        else{

            //  if(date1==1 || PhaseTraveling == null || PhaseTraveling.equals("1")){
            if(date1==1){
                Utils.showErrorToast(this,"Please complete your visit date...");
                // Toast.makeText(ChackOut.this, "Please Do checkout in traveling...", Toast.LENGTH_LONG).show();
                btnsubmitchackout.setClickable(false);
                btnsubmitchackout.setEnabled(false);

            }
            else{
                Toast.makeText(this, "you should check out.", Toast.LENGTH_SHORT).show();
                btnsubmitchackout.setClickable(true);
                btnsubmitchackout.setEnabled(true);
            }
        }


        getOverDueCount();

        /****************************************************************************/

        btnsubmitchackout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (overDueCount1 !=0){
                    Utils.showErrorToast(ChackOut.this,"Please complete your Overdue List...");
                    // Toast.makeText(ChackOut.this, "Please Do checkout in traveling...", Toast.LENGTH_LONG).show();
                }else {

                    progressDialog = new ProgressDialog(ChackOut.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                    if (waypathchckout == null) {
                        Utils.showErrorToast(ChackOut.this, "Please upload Selfie Photo");
                    }
                    else {
                        d("upload image", "onClick: " + waypathchckout);
                        File file1 = new File(waypathchckout);
                        final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        MultipartBody.Part imagePartChckout = MultipartBody.Part.createFormData("image1", file1.getName(), requestBody1);

                        WebService.getClient().getChackoutno(
                                RequestBody.create(okhttp3.MediaType.parse("text/plain"), emp),
                                RequestBody.create(okhttp3.MediaType.parse("text/plain"), id),
//                                RequestBody.create(MediaType.parse("text/plain"), address_line),
                        imagePartChckout).enqueue(new Callback<DataChackOutModelno>() {
                            @Override
                            public void onResponse(@NotNull Call<DataChackOutModelno> call,
                                                   @NotNull Response<DataChackOutModelno> response) {
                                progressDialog.dismiss();

                                assert response.body() != null;
                                d("Response", response.body().toString());
                                Toast.makeText(ChackOut.this, "" + response.body().getMsg(),
                                        Toast.LENGTH_LONG).show();

                                Intent i = new Intent(ChackOut.this, FoDashbord.class);
                                //   i.putExtra("emp",emp);
                                startActivity(i);
                                finish();

                            }

                            @Override
                            public void onFailure(@NotNull Call<DataChackOutModelno> call, @NotNull Throwable t) {
                                progressDialog.dismiss();

                                d("Response2", t.getMessage());
                                Toast.makeText(ChackOut.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }
        });


        btnchackout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Intent gal3 = new Intent(Intent.ACTION_PICK);
                //gal3.setType("image/*");
                //startActivityForResult(gal3, 3);
                SelectImage();
            }
        });
    }

    private void getlocation()  {
        pro.show();
        pro.setMessage("Please wait get location...");
        pro.setCancelable(false);
        LocationManager locationManager = (LocationManager) ChackOut.this.getSystemService(ChackOut.this.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(ChackOut.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(ChackOut.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        try {
                            Geocoder geocoder = new Geocoder(ChackOut.this, Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                            locationget.setVisibility(View.VISIBLE);
                            Loaction = addresses.get(0).getAddressLine(0)+" , "+addresses.get(0).getSubLocality()+" , "+addresses.get(0).getLocality()
                                    +" , "+ addresses.get(0).getAdminArea()+" , "+addresses.get(0).getCountryName()+" , "+String.valueOf(addresses.get(0).getLatitude())+" , "+
                                    String.valueOf(addresses.get(0).getLongitude());
                            locationget.setText(Loaction);

                            btngetLocation.setVisibility(View.GONE);
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
                                    Geocoder geocoder = new Geocoder(ChackOut.this, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(location1.getLatitude(), location1.getLongitude(), 0);

                                    //  Toast.makeText(DealFormGeneralActivity.this, addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();

                                    locationget.setVisibility(View.VISIBLE);
                                    locationget.setText(addresses.get(0).getAddressLine(0)+" , "+addresses.get(0).getSubLocality()+" , "+addresses.get(0).getLocality()
                                            +" , "+ addresses.get(0).getAdminArea()+" , "+addresses.get(0).getCountryName()+" , "+String.valueOf(addresses.get(0).getLatitude())+" , "+
                                            String.valueOf(addresses.get(0).getLongitude()));

                                    btngetLocation.setVisibility(View.GONE);
                                    pro.dismiss();

                                } catch (Exception e) {
                                    pro.dismiss();
                                    getlocation();
                                }
                            }
                        };
                        if (ActivityCompat.checkSelfPermission(ChackOut.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ChackOut.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            return;
                        }
                        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                    }
                }
            });
        }
        else {
            pro.dismiss();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ChackOut.this);


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

    private void getLocation() {

        try{
            locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
            assert locationManager != null;
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        //  Toast.makeText(this, "Latitude:"+location.getLatitude()+ "\n Longitude:"+location.getLongitude(), Toast.LENGTH_SHORT).show();

        d("lat_long", "onLocationChanged: "+"Latitude:"+location.getLatitude()+
                "\n Longtitude:"+location.getLongitude());

        try{
            Geocoder geocoder=new Geocoder(this, Locale.getDefault());
            // List<Address> addresses =geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

            //  Geocoder geocoder;
            List<Address> addresses;

            try {

                geocoder = new Geocoder(this, Locale.getDefault());

                addresses = geocoder.getFromLocation(+location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();

                progressDialog.dismiss();

                address_line = address+" "+city+" "+state+" "+country+" "+postalCode+" "+knownName;

                //  sharedPreferences = getSharedPreferences("Address",MODE_PRIVATE);
                //  sharedPreferences.edit().putString("Address_location",address_line).apply();

                d("address", "onLocationChanged: "+address_line);
                //   Toast.makeText(this, "address:: "+address+city+state+country+postalCode+knownName, Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }



        }catch (Exception e){
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Please Enable GPS and Internet", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }


    private void SelectImage() {

        final CharSequence[] items = {"Camera", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(ChackOut.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 4);
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

            }
        }
        if (requestCode == 4) {

            Bundle bundle = data.getExtras();
            final Bitmap photo = (Bitmap) bundle.get("data");
            mImageViewchackout.setImageBitmap(photo);
            savebitmap(photo);

        } else if (requestCode == SELECT_FILE) {
            Uri selectedImageUri = data.getData();
            mImageViewchackout.setImageURI(selectedImageUri);
            uriclient = data.getData();
            d("AadharImageUri", uriclient.toString());
            waypathClient = getFilePath(this, uriclient);
            d("AadharmageUri", waypathClient);

        }

    }

    private File savebitmap(Bitmap bmp) {


        String extStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();/* Environment.getExternalStorageDirectory().getPath();*/
        FileOutputStream outStream = null;
        // String temp = null;

        final int random = new Random().nextInt(6000000) + 20;
        File file = new File(extStorageDirectory, random + ".png");
        waypathchckout = getFilePath(this, Uri.fromFile(file));
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, random + ".png");
            waypathchckout = getFilePath(this, Uri.fromFile(file));
        }
        try {
            outStream = new FileOutputStream(file);
//            Bitmap scaledBitmap = scaleDown(bmp, 30, true);
            bmp.compress(Bitmap.CompressFormat.PNG, 70, outStream);
//            scaledBitmap.compress(Bitmap.CompressFormat.PNG, 30, outStream);
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

    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public int getOverDueCount(){
        if (!catID.equals("") || catID!=null || !catID.equals("null")) {
            WebService.getClient().deal_overDue_web(emp, catID, "", "", "", "", "", "", "", "").enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    sp2 = getSharedPreferences("OverDueCheck", MODE_PRIVATE);
                    sp2.edit().putInt("OverDueCheckCont", response.body().getCat().size()).apply();
                    overDueCount1 = response.body().getCat().size();

                    Log.d("overDueCount1",String.valueOf(overDueCount1));
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(ChackOut.this, t.getMessage());
                }
            });
            return overDueCount1;
        }else{
            Utils.showErrorToast(this,"Please Do visit Deal Stage First.");
        }

        return overDueCount1;
    }


}
