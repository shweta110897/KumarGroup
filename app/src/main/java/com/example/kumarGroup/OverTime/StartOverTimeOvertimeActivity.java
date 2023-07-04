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

import com.example.kumarGroup.R;
import com.example.kumarGroup.StartOverTimeModel;
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


public class StartOverTimeOvertimeActivity extends AppCompatActivity {

    ImageView imgStartOverTime;
    TextView txtStartOverTimeSOTCheckIn;
    Button btnStartOverTimeNextPage;

    DataStorage ds;

    Integer REQUEST_CAMERA = 4, SELECT_FILE = 0;

    ProgressDialog progressDialog;
    String emp;
    SharedPreferences sp,sp1;
    String flag;

    Uri uriclient;
    String currentPhotoPathClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_over_time_overtime);
        getSupportActionBar().setTitle("Start Overtime");

        ds=new DataStorage(this);

        sp1 = getSharedPreferences("CheckIn", MODE_PRIVATE);

        imgStartOverTime=findViewById(R.id.imgStartOverTime);
        txtStartOverTimeSOTCheckIn=findViewById(R.id.txtStartOverTimeSOTCheckIn);
        btnStartOverTimeNextPage=findViewById(R.id.btnStartOverTimeNextPage);

        sp = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp.getString("emp_id", "");

        btnStartOverTimeNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(StartOverTimeOvertimeActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                ds.write("id",100);
                 if (currentPhotoPath == null) {
                    Utils.showErrorToast(StartOverTimeOvertimeActivity.this, "Please upload Selfie Photo");
                }
                 else {
                     Log.d("upload image", "onClick: " + currentPhotoPath);
                     File file1 = new File(currentPhotoPath);
                     final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                     MultipartBody.Part imagePartStartOverTime = MultipartBody.Part.createFormData("image8", file1.getName(), requestBody1);

                     WebService.getClient().getStartOverTime(RequestBody.create(MediaType.parse("text/plain"), emp),
                             imagePartStartOverTime ,
                             RequestBody.create(MediaType.parse("text/plain"),"overtime")
                             ).enqueue(new Callback<StartOverTimeModel>() {
                         @Override
                         public void onResponse(@NotNull Call<StartOverTimeModel> call, @NotNull Response<StartOverTimeModel> response) {
                             progressDialog.dismiss();

                             Log.d("Response", response.body().toString());
                             Toast.makeText(StartOverTimeOvertimeActivity.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();

                           /*  if (response.body().getPhase() == 1) {
                                 Intent i = new Intent(StartOverTimeOvertimeActivity.this,CheckOutStartOvertimeActivity.class);
                                 i.putExtra("idCheckIn",String.valueOf(response.body().getId()));
                                 startActivity(i);
                             }*/

                             String Phase = String.valueOf(response.body().getPhase());
                             String idCheckIn = String.valueOf(response.body().getId());

                             sp1.edit().putString("idPhaseOt", Phase).apply();
                             sp1.edit().putString("idCheckInOt", idCheckIn).apply();

                             Log.d("idCheckInOt", "onResponse: "+idCheckIn);

                             Intent i = new Intent(StartOverTimeOvertimeActivity.this,CheckOutStartOvertimeActivity.class);
                             i.putExtra("idCheckIn",String.valueOf(response.body().getId()));
                             String id =String.valueOf(response.body().getId());
                             sp1.edit().putString("idCheckInStartOverTime", id).apply();
                             startActivity(i);
                         }

                         @Override
                         public void onFailure(@NotNull Call<StartOverTimeModel> call, @NotNull Throwable t) {
                             progressDialog.dismiss();

                             Log.d("Response2", t.getMessage());
                             Toast.makeText(StartOverTimeOvertimeActivity.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();
                         }
                     });
                 }
            }
        });


        txtStartOverTimeSOTCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });


    }

    private void SelectImage() {
        final CharSequence[] items = {"Camera", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(StartOverTimeOvertimeActivity.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {
//                    dispatchTakePictureIntent(StartOverTimeOvertimeActivity.this);
                    try {
                        currentPhotoPath=  dispatchTakePictureIntent1(StartOverTimeOvertimeActivity.this).getAbsolutePath();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
            getImageBitmap(currentPhotoPath,imgStartOverTime,f,this);

        } else if (requestCode == SELECT_FILE) {
            Uri selectedImageUri = data.getData();
            imgStartOverTime.setImageURI(selectedImageUri);
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