package com.example.kumarGroup.BookingDeliveryUpload.Rcbook_Update;

import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.kumarGroup.R;
import com.example.kumarGroup.RcbookUpdate;
import com.example.kumarGroup.WebService;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RC_Customer_UpdateActivity extends AppCompatActivity {

    Spinner spn_rc_update_customer;
    String number_plate_fitment,rc_book_update,rc_update_customer;
    List<String> Y_N = Arrays.asList(new String[]{"Select Option","Yes", "No"});
    LinearLayout linear_rc_picture1,linear_rc_picture2;
    TextView txt_number_plate,txt_rcbook_picture1,txt_rcbook_picture2,tv_tracno;
    Uri number_plate,rcbook_picture1,rcbook_picture2;
    String number_plate1,rcbook_picture11,rcbook_picture21;
    ImageView img_number_plate,img_rcbook_picture1,img_rcbook_picture2;
    Button btn_Passing_Submit;
    EditText edt_register_number;
    String id,register_no;
    ProgressDialog pro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc_customer_update);

        getSupportActionBar().setTitle("RCBook Update Customer");

        pro = new ProgressDialog(this);
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");


        spn_rc_update_customer = findViewById(R.id.spn_rc_update_customer);
        linear_rc_picture1 = findViewById(R.id.linear_rc_picture1);
        linear_rc_picture2 = findViewById(R.id.linear_rc_picture2);
        txt_rcbook_picture1 = findViewById(R.id.txt_rcbook_picture1);
        img_rcbook_picture1 = findViewById(R.id.img_rcbook_picture1);
        txt_rcbook_picture2 = findViewById(R.id.txt_rcbook_picture2);
        img_rcbook_picture2 = findViewById(R.id.img_rcbook_picture2);
        btn_Passing_Submit = findViewById(R.id.btn_Passing_Submit);


        id = getIntent().getStringExtra("idBookingUpload");
        register_no = getIntent().getStringExtra("register_no");

        tv_tracno=findViewById(R.id.tv_tracno);
        if (register_no!=null){
            tv_tracno.setText("Tractor No.:-"+register_no);
        }else {
            tv_tracno.setVisibility(View.GONE);
        }


        ArrayAdapter adapterStage3 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Y_N);
        spn_rc_update_customer.setAdapter(adapterStage3);
        spn_rc_update_customer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rc_update_customer = Y_N.get(i);
                if (rc_update_customer.equals("Yes")){
                    linear_rc_picture1.setVisibility(View.VISIBLE);
                    linear_rc_picture2.setVisibility(View.VISIBLE);
                }else {
                    linear_rc_picture1.setVisibility(View.GONE);
                    linear_rc_picture2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txt_rcbook_picture1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 2);
            }
        });
        txt_rcbook_picture2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 3);
            }
        });

        btn_Passing_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pro.show();

                MultipartBody.Part img_number_platem = null;
                MultipartBody.Part img_rcbook_picture1m  = null;
                MultipartBody.Part img_rcbook_picture2m  = null;


                if(rcbook_picture11 != null){
                    File file2 = new File(rcbook_picture11);
                    final RequestBody requestBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                    img_rcbook_picture1m = MultipartBody.Part.createFormData("image2",
                            file2.getName(), requestBody2);
                }
                if(rcbook_picture21 != null){
                    File file3 = new File(rcbook_picture21);
                    final RequestBody requestBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                    img_rcbook_picture2m = MultipartBody.Part.createFormData("image3",
                            file3.getName(), requestBody3);
                }

                WebService.getClient().RcbookUpdateCustomer(RequestBody.create(MediaType.parse("text/plain"), id),
                        RequestBody.create(MediaType.parse("text/plain"), rc_update_customer),
                        img_rcbook_picture1m,
                        img_rcbook_picture2m
                ).enqueue(new Callback<RcbookUpdate>() {
                    @Override
                    public void onResponse(Call<RcbookUpdate> call, Response<RcbookUpdate> response) {
                        pro.dismiss();
                        if (response.body().getMsg().equals("Record Update Succesfully")){
                            Toast.makeText(RC_Customer_UpdateActivity.this, "Record Update Succesfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(RC_Customer_UpdateActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<RcbookUpdate> call, Throwable t) {
                        pro.dismiss();
                    }
                });

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    number_plate = data.getData();
                    Log.d("PanImageUri", number_plate.toString());
                    number_plate1 = getFilePath(this, number_plate);


                    Log.d("PanImage", number_plate1);
                    String[] name = number_plate1.split("/");
                    txt_number_plate.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
//                    img_number_plate.setImageURI(selectedImageUri);

                    Glide.with(this)
                            .load(Uri.parse("file:///android_asset/"+selectedImageUri))
//                            .load(urlSource)
                            .centerInside()
                            .into(img_number_plate);

//                    savebitmap(photo);
                }

            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    rcbook_picture1 = data.getData();
                    Log.d("PanImageUri", rcbook_picture1.toString());
                    rcbook_picture11 = getFilePath(this, rcbook_picture1);


                    Log.d("PanImage", rcbook_picture11);
                    String[] name = rcbook_picture11.split("/");
                    txt_rcbook_picture1.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_rcbook_picture1.setImageURI(selectedImageUri);
                }

            }
        }
        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    rcbook_picture2 = data.getData();
                    Log.d("PanImageUri", rcbook_picture2.toString());
                    rcbook_picture21 = getFilePath(this, rcbook_picture2);


                    Log.d("PanImage", rcbook_picture21);
                    String[] name = rcbook_picture21.split("/");
                    txt_rcbook_picture2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_rcbook_picture2.setImageURI(selectedImageUri);
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