package com.example.kumarGroup.OverTime;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.OverTimeCheckOutModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

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

public class CheckOutStartOvertimeActivity extends AppCompatActivity {

    ImageView imgStartOverTimeCheckOut;
    TextView  txtStartOverTimeCheckOut;
    Button btnStartOverTimeNextPageCheckOut;

    Integer REQUEST_CAMERA = 4, SELECT_FILE = 0;

    ProgressDialog progressDialog;
    String emp;
    SharedPreferences sp;

    DataStorage ds;

    Uri uriclient;
    String currentPhotoPathClient;

    String idCheckIn;
    Utils utils;
    SharePref sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_start_overtime);
        getSupportActionBar().setTitle("End OverTime");

        ds=new DataStorage(this);
        sp = getSharedPreferences("CheckIn", MODE_PRIVATE);

       // emp = sp.getString("emp_id", "");
      //  idCheckIn = sp.getString("idCheckIn", "");

        imgStartOverTimeCheckOut=findViewById(R.id.imgStartOverTimeCheckOut);
        txtStartOverTimeCheckOut=findViewById(R.id.txtStartOverTimeCheckOut);
        btnStartOverTimeNextPageCheckOut=findViewById(R.id.btnStartOverTimeNextPageCheckOut);

      // idCheckIn = getIntent().getStringExtra("idCheckIn");

        idCheckIn = sp.getString("idCheckInStartOverTime","");
        Log.d("idCheckIn", "onCreate: "+idCheckIn);
      /*  utils = new Utils(this);
        sp1 = new SharePref(utils.getActivity());*/

        btnStartOverTimeNextPageCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ds.write("id",-1);
                progressDialog = new ProgressDialog(CheckOutStartOvertimeActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                  if ( currentPhotoPath == null) {
                    Utils.showErrorToast(CheckOutStartOvertimeActivity.this, "Please upload Selfie Photo");
                }
                 else {
                      Log.d("upload image", "onClick: " + currentPhotoPath);
                      File file1 = new File(currentPhotoPath);
                      final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                      MultipartBody.Part imagePartStartOverTimeCheckOut = MultipartBody.Part.createFormData("image3", file1.getName(), requestBody1);

                      WebService.getClient().getOverTimeCheckOut(imagePartStartOverTimeCheckOut,
                              RequestBody.create(MediaType.parse("text/plain"), idCheckIn)).enqueue(new Callback<OverTimeCheckOutModel>() {
                          @Override
                          public void onResponse(@NotNull Call<OverTimeCheckOutModel> call,
                                                 @NotNull Response<OverTimeCheckOutModel> response) {
                              progressDialog.dismiss();

                              Log.d("Response", response.body().toString());
                              Toast.makeText(CheckOutStartOvertimeActivity.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();
                              Intent i = new Intent(CheckOutStartOvertimeActivity.this, FoDashbord.class);
                              startActivity(i);
                          }

                          @Override
                          public void onFailure(@NotNull Call<OverTimeCheckOutModel> call, @NotNull Throwable t) {
                              progressDialog.dismiss();
                          }
                      });
                  }
            }
        });

        txtStartOverTimeCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });
    }

    private void SelectImage() {
        final CharSequence[] items = {"Camera", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(CheckOutStartOvertimeActivity.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {

                    try {
                        currentPhotoPath=  dispatchTakePictureIntent1(CheckOutStartOvertimeActivity.this).getAbsolutePath();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

//                    dispatchTakePictureIntent(CheckOutStartOvertimeActivity.this);
                   /* File filename = new File( Environment.getExternalStorageDirectory().getPath() + "/test/");
                    try {
                        filename.mkdirs();
                    }
                    catch (Exception e){
                        Toast.makeText(CheckOutStartOvertimeActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                    File file = new File(filename, "image" + ".jpg");
                    imageUri = Uri.fromFile(file);
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                            imageUri);

                    if (cameraIntent.resolveActivity(CheckOutStartOvertimeActivity.this.getPackageManager()) != null) {
                        Log.e("DATA", String.valueOf(cameraIntent.getData()));
                        startActivityForResult(cameraIntent, 4);
                    }*/
                   /* Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 4);*/
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
            getImageBitmap(currentPhotoPath,imgStartOverTimeCheckOut,f,this);


        } else if (requestCode == SELECT_FILE) {
            Uri selectedImageUri = data.getData();
            imgStartOverTimeCheckOut.setImageURI(selectedImageUri);
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