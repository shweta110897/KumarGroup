package com.example.kumarGroup;

import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
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
import static com.example.kumarGroup.Utils.currentPhotoPath;

public class ChackIn extends AppCompatActivity implements LocationListener {

    TextView btnchackin;
    ImageView mImageView;
    Integer SELECT_FILE = 0;
    Button btnnextsatape;

    ProgressDialog progressDialog;
    String emp;
    SharedPreferences sp,sp1;

    public static Uri imageUri;

    String address_line;

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

        if(module_name.contains("Overtime")) {

            if (idCheckInOt == null || idCheckInOt.equals("")) {
                //   Toast.makeText(OverTimeMainActivity.this, "CheckIn", Toast.LENGTH_SHORT).show();
                Toast.makeText(ChackIn.this, "You Should Check In", Toast.LENGTH_SHORT).show();

                btnnextsatape.setClickable(true);
            } else {

                WebService.getClient().getCheckOtPhase(idCheckInOt).enqueue(new Callback<CheckOtPhaseModel>() {
                    @Override
                    public void onResponse(@NotNull Call<CheckOtPhaseModel> call, @NotNull Response<CheckOtPhaseModel> response) {
                        if (response.body().getPhase().equals("")) {
                            //   Toast.makeText(ChackIn.this, "CheckIn", Toast.LENGTH_SHORT).show();
                            Toast.makeText(ChackIn.this, "You Should Check In", Toast.LENGTH_SHORT).show();

                            btnnextsatape.setClickable(true);

                        } else if (response.body().getPhase().equals("1")) {

                            Utils.showErrorToast(ChackIn.this, "Please do Overtime CheckOut First");

                            btnnextsatape.setClickable(false);
                        }

                        else {
                            Toast.makeText(ChackIn.this, "You Should Check In", Toast.LENGTH_SHORT).show();
                            btnnextsatape.setClickable(true);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<CheckOtPhaseModel> call, @NotNull Throwable t) {
                        Toast.makeText(ChackIn.this, "" + t.getCause(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
        else{
            Toast.makeText(ChackIn.this, "You Should Check In", Toast.LENGTH_SHORT).show();
            btnnextsatape.setClickable(true);
        }

        btnnextsatape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(ChackIn.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                if (currentPhotoPath == null) {
                    Utils.showErrorToast(ChackIn.this, "Please upload Selfie Photo");
                } else {
                    Log.d("upload image", "onClick: " + currentPhotoPath);
                    File file1 = new File(currentPhotoPath);

                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    MultipartBody.Part imagePartAdhar = MultipartBody.Part.createFormData("image8",
                            file1.getName(), requestBody1);

                    Log.d("TAG", "onClick: dfhakjsdff " + imagePartAdhar
                            + "\nwaypath " + currentPhotoPath
                    );
                    WebService.getClient().getchackin(RequestBody.create(MediaType.parse("text/plain"), emp),
                            imagePartAdhar,
                            RequestBody.create(MediaType.parse("text/plain"), ""))
                            .enqueue(new Callback<ChackModelclass>() {
                                @Override
                                public void onResponse(@NotNull Call<ChackModelclass> call, @NotNull Response<ChackModelclass> response) {
                                    progressDialog.dismiss();

                                    Log.d("Response", response.body().toString());
                                    Toast.makeText(ChackIn.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();



                                    Intent i = new Intent(ChackIn.this, TaskWoek.class);
                                    String id = response.body().getId().toString();
                                    sp.edit().putString("id", id).apply();

                                    startActivity(i);
                                }

                                @Override
                                public void onFailure(@NotNull Call<ChackModelclass> call, @NotNull Throwable t) {
                                    progressDialog.dismiss();

                                    Log.d("Response2", t.getMessage());
                                    Toast.makeText(ChackIn.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });

        btnchackin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

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

            address_line = address + " " + city + " " + state + " " + country + " " + postalCode + " " + knownName;
            Log.d("address_location", "onLocationChanged: " + address_line);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(ChackIn.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {

                  /*  try {
                        currentPhotoPath=  dispatchTakePictureIntent1(ChackIn.this).getAbsolutePath();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 4);

//                    dispatchTakePictureIntent();
//                    dispatchTakePictureIntent(ChackIn.this);

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

        if (requestCode == 4) {

            Bundle bundle = data.getExtras();
            final Bitmap photo = (Bitmap) bundle.get("data");
            mImageView.setImageBitmap(photo);
            savebitmap(photo);

        }

       /* if(requestCode == CAMERA_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){

                File file1 = new File (currentPhotoPath);
              getImageBitmap(currentPhotoPath,mImageView,file1,this);

            }

        }*/
    }

    private File savebitmap(Bitmap bmp) {


        String extStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();/* Environment.getExternalStorageDirectory().getPath();*/
        FileOutputStream outStream = null;
        // String temp = null;

        final int random = new Random().nextInt(6000000) + 20;
        File file = new File(extStorageDirectory, random + ".png");
        currentPhotoPath = getFilePath(this, Uri.fromFile(file));
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, random + ".png");
            currentPhotoPath = getFilePath(this, Uri.fromFile(file));
        }
        try {
            outStream = new FileOutputStream(file);
//            Bitmap scaledBitmap = scaleDown(bmp, 30, true);
            bmp.compress(Bitmap.CompressFormat.PNG, 50, outStream);
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

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE, CAMERA, WRITE_EXTERNAL_STORAGE}, 1);

    }


}


