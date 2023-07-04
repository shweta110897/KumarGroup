package com.example.kumarGroup.VendorDashboard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.vendor_delivery_button_model;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryButtonActivity extends AppCompatActivity {


    EditText edt_sell_date,
            edt_Village;

    LinearLayout lin_invoice;
    ImageView img_invoice;
    TextView txt_invoice;

    Button btn_Submit_thirdPoint;

    String id;

    Calendar mcurrentdate;
    int day, month, year;

    String waypath;

    Uri uri_CD_UploadPhoto;

    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;
    String CUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_button);


        getSupportActionBar().setTitle("Delivery");


        edt_sell_date = findViewById(R.id.edt_sell_date);
        lin_invoice = findViewById(R.id.lin_invoice);
        img_invoice = findViewById(R.id.img_invoice);
        txt_invoice = findViewById(R.id.txt_invoice);
        edt_Village = findViewById(R.id.edt_Village);
        btn_Submit_thirdPoint = findViewById(R.id.btn_Submit_thirdPoint);

        CUID= getIntent().getStringExtra("id");

//        Toast.makeText(this, ""+CUID, Toast.LENGTH_SHORT).show();

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH);
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR);


        edt_sell_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DeliveryButtonActivity.this,
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
                        //2021-08-18
                        edt_sell_date.setText(year + "-" + mt + "-" + dy);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        edt_sell_date.setFocusable(false);

        txt_invoice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent gal3 = new Intent(Intent.ACTION_PICK);
                gal3.setType("image/*");
                startActivityForResult(gal3, 2);

            }
        });


        btn_Submit_thirdPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                progressDialog= new ProgressDialog(DeliveryButtonActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                MultipartBody.Part UploadPhoto = null;

                if(waypath != null){
                    File file15 = new File(waypath);
                    final RequestBody requestBody15 = RequestBody.create(MediaType.parse("image/*"), file15);
                    UploadPhoto = MultipartBody.Part.createFormData("images",
                            file15.getName(), requestBody15);
                }

                Log.d("TAG", "imagess: "+UploadPhoto+" cid "+CUID+" date "
                        +edt_sell_date.getText().toString()
                        + " price " + edt_Village.getText().toString());

//                Toast.makeText(getApplicationContext(), " c id"+CUID, Toast.LENGTH_SHORT).show();

                WebService.getClient().get_vendor_delivery_web(
                        RequestBody.create(MediaType.parse("text/plain"), CUID),
                        RequestBody.create(MediaType.parse("text/plain"), edt_sell_date.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_Village.getText().toString()),
                        UploadPhoto
                ).enqueue(new Callback<vendor_delivery_button_model>() {
                    @Override
                    public void onResponse(@NotNull Call<vendor_delivery_button_model> call,
                                           @NotNull Response<vendor_delivery_button_model> response) {

                        progressDialog.dismiss();
                        assert response.body() != null;
                        Toast.makeText(DeliveryButtonActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();


                     //   Toast.makeText(DeliveryButtonActivity.this,"response id "+response.body().getId() , Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(DeliveryButtonActivity.this, VendorOtpVerifyActivity.class);
                        i.putExtra("id",String.valueOf(response.body().getId()));
                        startActivity(i);

                    }

                    @Override
                    public void onFailure(@NotNull Call<vendor_delivery_button_model> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(DeliveryButtonActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_UploadPhoto = data.getData();
                    Log.d("PanImageUri", uri_CD_UploadPhoto.toString());
                    waypath = getFilePath(this, uri_CD_UploadPhoto);


                    Log.d("PanImage", waypath);
                    String[] name = waypath.split("/");
                    txt_invoice.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_invoice.setImageURI(selectedImageUri);
                }

            }
        }
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