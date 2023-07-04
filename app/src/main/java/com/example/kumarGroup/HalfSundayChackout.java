package com.example.kumarGroup;

import static com.example.kumarGroup.Utils.CAMERA_REQUEST_CODE;
import static com.example.kumarGroup.Utils.currentPhotoPath;
import static com.example.kumarGroup.Utils.dispatchTakePictureIntent1;
import static com.example.kumarGroup.Utils.getImageBitmap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HalfSundayChackout extends AppCompatActivity {
    TextView btnhalfsundaychackout;
    ImageView mImageViewhalfsundaychackout;
    Integer REQUEST_CAMERA=4,SELECT_FILE=0;
    Button btnsubmithalfsundaychackout;
    Uri uriclient;
    String emp,id;
    SharedPreferences sp;
    ProgressDialog progressDialog;
    String waypathClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_half_sunday_chackout);
        getSupportActionBar().setTitle(("Half Day Check-out"));
        sp = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp.getString("emp_id", "");
        id = sp.getString("id", "");

        Log.d("hamaleche", "onCreate: " + emp);
        mImageViewhalfsundaychackout = findViewById(R.id.mImageViewhalfsundaychackout);
        btnhalfsundaychackout = findViewById(R.id.btnhalfsundaychackout);
        btnsubmithalfsundaychackout = findViewById(R.id.btnsubmithalfsundaychackout);
        btnsubmithalfsundaychackout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog= new ProgressDialog(HalfSundayChackout.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );
                if (currentPhotoPath == null) {
                    Utils.showErrorToast(HalfSundayChackout.this, "Please upload Selfie Photo");
                } else {
                    Log.d("upload image", "onClick: " + currentPhotoPath);
                    File file1 = new File(currentPhotoPath);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    MultipartBody.Part imagePartChckout = MultipartBody.Part.createFormData("image7", file1.getName(), requestBody1);

                    WebService.getClient().gethalfsundaychackout(
                            RequestBody.create(okhttp3.MediaType.parse("text/plain"), emp),
                            RequestBody.create(okhttp3.MediaType.parse("text/plain"), id),
                            imagePartChckout).enqueue(new Callback<DataHalfSundayChackoutModel>() {
                        @Override
                        public void onResponse(Call<DataHalfSundayChackoutModel> call, Response<DataHalfSundayChackoutModel> response) {
                            progressDialog.dismiss();

                            Log.d("Response", response.body().toString());
                            Toast.makeText(HalfSundayChackout.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();

                            Intent i = new Intent(HalfSundayChackout.this, FoDashbord.class);
                            //   i.putExtra("emp",emp);
                            startActivity(i);
                            finish();

                        }

                        @Override
                        public void onFailure(Call<DataHalfSundayChackoutModel> call, Throwable t) {
                            progressDialog.dismiss();

                            Log.d("Response2", t.getMessage());
                            Toast.makeText(HalfSundayChackout.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });






        btnhalfsundaychackout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                SelectImage();

            }
        });

    }
    private void SelectImage(){


        final CharSequence[] items ={"Camera","Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(HalfSundayChackout.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")){
                    try {
                        currentPhotoPath=  dispatchTakePictureIntent1(HalfSundayChackout.this).getAbsolutePath();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

//                    dispatchTakePictureIntent(HalfSundayChackout.this);
                }
                else if (items[i].equals("Gallery")){
                    Intent intent = new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);
                }else if (items[i].equals("Cancel"))
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
            getImageBitmap(currentPhotoPath,mImageViewhalfsundaychackout,f,this);
        }

        else if (requestCode ==  SELECT_FILE) {
            Uri selectedImageUri = data.getData();
            mImageViewhalfsundaychackout.setImageURI(selectedImageUri);
            uriclient = data.getData();
            Log.d("AadharImageUri",uriclient.toString());
            waypathClient = getFilePath(this,uriclient);
            Log.d("AadharmageUri",waypathClient);

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


    public String getFilePath(Context context, Uri uri)
    {
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


}
