package com.example.kumarGroup.Notification;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.PhotoUploadNotificationModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.example.kumarGroup.Utils.CAMERA_REQUEST_CODE;
import static com.example.kumarGroup.Utils.currentPhotoPath;
import static com.example.kumarGroup.Utils.dispatchTakePictureIntent1;
import static com.example.kumarGroup.Utils.getImageBitmap;


public class takeSelfieNotificationActivity extends AppCompatActivity implements LocationListener {

    ImageView mImageViewNotification;
    TextView txtTakePhotoNotification;
    Button btnPhotoSubmitNotification;

    Integer REQUEST_CAMERA = 4, SELECT_FILE = 0;
    Uri uriclient;
    String currentPhotoPathClient;
    ProgressDialog progressDialog;
    String emp;
    SharedPreferences sp;

    String id_noti;
    String which;

    String address_line;

    List<Address> addresses;

    LocationManager locationManager;
    String latitude, longitude;
    String Address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_selfie_notification);


        mImageViewNotification = findViewById(R.id.mImageViewNotification);
        txtTakePhotoNotification = findViewById(R.id.txtTakePhotoNotification);
        btnPhotoSubmitNotification = findViewById(R.id.btnPhotoSubmitNotification);

        id_noti = getIntent().getStringExtra("id_noti");
        which = getIntent().getStringExtra("which");

      //  Toast.makeText(this, ""+which+id_noti, Toast.LENGTH_SHORT).show();

        sp = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp.getString("emp_id", "");

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }

        progressDialog = new ProgressDialog(takeSelfieNotificationActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        getLocation();

      /*  Calendar cs = Calendar.getInstance();
        int day = cs.get(Calendar.DAY_OF_MONTH);
        int month = cs.get(Calendar.MONTH);
        int year = cs.get(Calendar.YEAR);
        //  String date = day + "/" + (month + 1) + "/" + year;
        String date = year + "-" + (month + 1) + "-" + day;
        Log.d("NewDate", "onCreate: "+date);

        Toast.makeText(this, ""+date, Toast.LENGTH_SHORT).show();*/

        btnPhotoSubmitNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(takeSelfieNotificationActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                if (currentPhotoPath == null) {
                    Utils.showErrorToast(takeSelfieNotificationActivity.this, "Please upload Selfie Photo");
                } else {
                    Log.d("upload image", "onClick: " + currentPhotoPath);
                    File file1 = new File(currentPhotoPath);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    MultipartBody.Part imagePartAdhar = MultipartBody.Part.createFormData("image1", file1.getName(), requestBody1);

                    WebService.getClient().getPhotoUploadNotification(RequestBody.create(MediaType.parse("text/plain"), emp),
                            RequestBody.create(MediaType.parse("text/plain"), id_noti),
                            RequestBody.create(MediaType.parse("text/plain"), address_line),
                            RequestBody.create(MediaType.parse("text/plain"), which),
                            imagePartAdhar).enqueue(new Callback<PhotoUploadNotificationModel>() {
                        @Override
                        public void onResponse(Call<PhotoUploadNotificationModel> call, Response<PhotoUploadNotificationModel> response) {
                            progressDialog.dismiss();
                            assert response.body() != null;
                            Toast.makeText(takeSelfieNotificationActivity.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();

                            Log.d("Response", response.body().toString());
                            Intent i = new Intent(takeSelfieNotificationActivity.this, NotificationMainActivity.class);
                            startActivity(i);

                        }

                        @Override
                        public void onFailure(Call<PhotoUploadNotificationModel> call, Throwable t) {
                            progressDialog.dismiss();

                            Log.d("Response2", t.getMessage());
                            Toast.makeText(takeSelfieNotificationActivity.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        txtTakePhotoNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });
    }

    private void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            assert locationManager != null;
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        //  Toast.makeText(this, "Latitude:"+location.getLatitude()+ "\n Longitude:"+location.getLongitude(), Toast.LENGTH_SHORT).show();

        Log.d("lat_long", "onLocationChanged: " + "Latitude:" + location.getLatitude() +
                "\n Longtitude:" + location.getLongitude());

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            // List<Address> addresses =geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            //  Geocoder geocoder;
            List<Address> addresses;

            try {
                /*progressDialog = new ProgressDialog(ChackIn.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);*/

                geocoder = new Geocoder(this, Locale.getDefault());

                addresses = geocoder.getFromLocation(+location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();

                progressDialog.dismiss();

                address_line = address + " " + city + " " + state + " " + country + " " + postalCode + " " + knownName;

                //  sharedPreferences = getSharedPreferences("Address",MODE_PRIVATE);
                //  sharedPreferences.edit().putString("Address_location",address_line).apply();

                Log.d("address", "onLocationChanged: " + address_line);
                //  Toast.makeText(this, "address:: "+address+city+state+country+postalCode+knownName, Toast.LENGTH_SHORT).show();
              //    Toast.makeText(this, "address:: "+address_line, Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }

            /*address_line =addresses.get(0).getAddressLine(0)+","+
            addresses.get(0).getAddressLine(1)+","+
                    addresses.get(0).getAddressLine(2);

            Toast.makeText(this, "address"+ address_line, Toast.LENGTH_SHORT).show();

          Log.d("address", "onLocationChanged: "+addresses.get(0).getAddressLine(0)+","+
            addresses.get(0).getAddressLine(1)+","+
                    addresses.get(0).getAddressLine(2));*/

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
        AlertDialog.Builder builder = new AlertDialog.Builder(takeSelfieNotificationActivity.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {
                    try {
                        currentPhotoPath=  dispatchTakePictureIntent1(takeSelfieNotificationActivity.this).getAbsolutePath();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    dispatchTakePictureIntent(takeSelfieNotificationActivity.this);
                 
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
        if (requestCode == CAMERA_REQUEST_CODE) {

            File f = new File(currentPhotoPath);
            getImageBitmap(currentPhotoPath,mImageViewNotification,f,this);


        } else if (requestCode == SELECT_FILE) {
            Uri selectedImageUri = data.getData();
            mImageViewNotification.setImageURI(selectedImageUri);
            uriclient = data.getData();
            Log.d("AadharImageUri", uriclient.toString());
            currentPhotoPathClient = getFilePath(this, uriclient);
            Log.d("AadharmageUri", currentPhotoPathClient);
        }
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
}