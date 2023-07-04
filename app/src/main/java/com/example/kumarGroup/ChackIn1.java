package com.example.kumarGroup;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.example.kumarGroup.R;

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

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.util.Log.d;

public class ChackIn1 extends AppCompatActivity implements LocationListener {

    TextView btnchackin, locationget;
    ImageView mImageView;
    Integer REQUEST_CAMERA = 4, SELECT_FILE = 0;
    Button btnnextsatape,btngetLocation;
    Uri uriclient;
    String waypathClient, waypath;
    ProgressDialog progressDialog;
    String emp;
    SharedPreferences sp,sp1;
    String address_line;
    private FusedLocationProviderClient fusedLocationClient;

    String Loactionget;

    ProgressDialog pro;


    String idPhaseOt,idCheckInOt,module_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chack_in);

        getSupportActionBar().setTitle("CheckIn");

        sp = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp.getString("emp_id", "");
        Log.d("hamaleche", "onCreate: " + emp);
        module_name = sp.getString("module_name", "");

        module_name = sp.getString("module_name", "");

        sp1 = getSharedPreferences("CheckIn", MODE_PRIVATE);

        idPhaseOt = sp1.getString("idPhaseOt","");
        idCheckInOt = sp1.getString("idCheckInOt","");

        requestPermission();
        mImageView = findViewById(R.id.mImageView);

        btnchackin = findViewById(R.id.btnchackin);
        btnnextsatape = findViewById(R.id.btnnextsatape);
        btngetLocation = findViewById(R.id.getlocation);
        locationget = findViewById(R.id.locationget);


        if(module_name.contains("Overtime")) {

            if (idCheckInOt == null || idCheckInOt.equals("")) {
                //   Toast.makeText(OverTimeMainActivity.this, "CheckIn", Toast.LENGTH_SHORT).show();
                Toast.makeText(ChackIn1.this, "You Should Check In", Toast.LENGTH_SHORT).show();

                btnnextsatape.setClickable(true);
            } else {

                WebService.getClient().getCheckOtPhase(idCheckInOt).enqueue(new Callback<CheckOtPhaseModel>() {
                    @Override
                    public void onResponse(@NotNull Call<CheckOtPhaseModel> call, @NotNull Response<CheckOtPhaseModel> response) {
                        if (response.body().getPhase().equals("")) {
                            Toast.makeText(ChackIn1.this, "You Should Check In", Toast.LENGTH_SHORT).show();

                            btnnextsatape.setClickable(true);

                        } else if (response.body().getPhase().equals("1")) {
                           Utils.showErrorToast(ChackIn1.this, "Please do Overtime CheckOut First");

                            btnnextsatape.setClickable(false);
                        }
                        else {
                            Toast.makeText(ChackIn1.this, "You Should Check In", Toast.LENGTH_SHORT).show();
                            btnnextsatape.setClickable(true);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<CheckOtPhaseModel> call, @NotNull Throwable t) {
                        Toast.makeText(ChackIn1.this, "" + t.getCause(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
        else{
            Toast.makeText(ChackIn1.this, "You Should Check In", Toast.LENGTH_SHORT).show();
            btnnextsatape.setClickable(true);
        }


        /********************************************************************************************************/
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(ChackIn1.this);
        pro = new ProgressDialog(ChackIn1.this);

        btngetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getlocation();
            }
        });

        btnnextsatape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(ChackIn1.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                if (waypath == null) {
                    Utils.showErrorToast(ChackIn1.this, "Please upload Selfie Photo");
                } else {
                    Log.d("upload image", "onClick: " + waypath);
                    File file1 = new File(waypath);

                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    MultipartBody.Part imagePartAdhar = MultipartBody.Part.createFormData("image8",
                            file1.getName(), requestBody1);

                    WebService.getClient().getchackin(RequestBody.create(MediaType.parse("text/plain"), emp),
                                    imagePartAdhar,
//                                    RequestBody.create(MediaType.parse("text/plain"), ""))
                              RequestBody.create(MediaType.parse("text/plain"), Loactionget))
                            .enqueue(new Callback<ChackModelclass>() {
                                @Override
                                public void onResponse(@NotNull Call<ChackModelclass> call, @NotNull Response<ChackModelclass> response) {
                                    progressDialog.dismiss();

                                    Log.d("Response", response.body().toString());
                                    Toast.makeText(ChackIn1.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();


                                    Intent i = new Intent(ChackIn1.this, TaskWoek.class);
                                    String id = response.body().getId().toString();
                                    sp.edit().putString("id", id).apply();

                                    startActivity(i);
                                }

                                @Override
                                public void onFailure(@NotNull Call<ChackModelclass> call, @NotNull Throwable t) {
                                    progressDialog.dismiss();

                                    Log.d("Response2", t.getMessage());
                                    Toast.makeText(ChackIn1.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });

        btnchackin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Intent gal3 = new Intent(Intent.ACTION_PICK);
                //gal3.setType("image/*");
                //startActivityForResult(gal3, 3);
                SelectImage();
            }
        });
    }


    @Override
    public void onLocationChanged(Location location) {

        Log.d("LatLong", "onLocationChanged: "
                + "Latitude:" + location.getLatitude() + "\n Longitude:" + location.getLongitude());
        // Toast.makeText(this, "Latitude:"+location.getLatitude()+ "\n Longitude:"+location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            progressDialog.dismiss();

//            address_line = address + " " + city + " " + state + " " + country + " " + postalCode + " " + knownName;
            Loactionget = address + " " + city + " " + state + " " + country + " " + postalCode + " " + knownName;

            Log.d("address_location", "onLocationChanged: " + Loactionget);
        } catch (Exception e) {
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();

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
        AlertDialog.Builder builder = new AlertDialog.Builder(ChackIn1.this);
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
            mImageView.setImageBitmap(photo);
            savebitmap(photo);
         /*   File file=new File(waypath);
            saveBitmaptoFile(file);*/

        } else if (requestCode == SELECT_FILE) {
            Uri selectedImageUri = data.getData();
            mImageView.setImageURI(selectedImageUri);
            uriclient = data.getData();
            Log.d("AadharImageUri", uriclient.toString());
            waypathClient = getFilePath(this, uriclient);
            Log.d("AadharmageUri", waypathClient);
        }
    }


    private File savebitmap(Bitmap bmp) {


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

    private Bitmap scaleDown(Bitmap realImage, float  maxImageSize, boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
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

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE, CAMERA, WRITE_EXTERNAL_STORAGE}, 1);

    }

    private void getlocation()  {
        pro.show();
        pro.setMessage("Please wait get location...");
        pro.setCancelable(false);
        LocationManager locationManager = (LocationManager) ChackIn1.this.getSystemService(ChackIn1.this.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(ChackIn1.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(ChackIn1.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        try {
                            Geocoder geocoder = new Geocoder(ChackIn1.this, Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);


                            locationget.setVisibility(View.VISIBLE);
                            Loactionget = addresses.get(0).getAddressLine(0)+" , "+addresses.get(0).getSubLocality()+" , "+addresses.get(0).getLocality()
                                    +" , "+ addresses.get(0).getAdminArea()+" , "+addresses.get(0).getCountryName()+" , "+String.valueOf(addresses.get(0).getLatitude())+" , "+
                                    String.valueOf(addresses.get(0).getLongitude());
                            locationget.setText(Loactionget);

                            btngetLocation.setVisibility(View.GONE);
                            pro.dismiss();


                        } catch (IOException e) {
                            e.printStackTrace();
                            getlocation();
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
                                    Geocoder geocoder = new Geocoder(ChackIn1.this, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(location1.getLatitude(), location1.getLongitude(), 0);


                                    locationget.setVisibility(View.VISIBLE);
                                    Loactionget=addresses.get(0).getAddressLine(0)+" , "+addresses.get(0).getSubLocality()+" , "+addresses.get(0).getLocality()
                                            +" , "+ addresses.get(0).getAdminArea()+" , "+addresses.get(0).getCountryName()+" , "+String.valueOf(addresses.get(0).getLatitude())+" , "+
                                            String.valueOf(addresses.get(0).getLongitude());
                                    locationget.setText(Loactionget);

                                    btngetLocation.setVisibility(View.GONE);
                                    pro.dismiss();


                                } catch (Exception e) {
                                    pro.dismiss();
                                    getlocation();
                                }
                            }
                        };
                        if (ActivityCompat.checkSelfPermission(ChackIn1.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ChackIn1.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            return;
                        }
                        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                    }
                }
            });
        }
        else {
            pro.dismiss();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ChackIn1.this);


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


