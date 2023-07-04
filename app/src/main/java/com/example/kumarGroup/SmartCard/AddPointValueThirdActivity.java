package com.example.kumarGroup.SmartCard;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.AddSmartCardThirdModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ThirdSmartCardVendorModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPointValueThirdActivity extends AppCompatActivity {

    Spinner spn_select_vendor;

    EditText edt_sell_date, edt_product_name, edt_deal_price,
            edt_customer_name,edt_Village;

    LinearLayout lin_invoice;
    ImageView img_invoice;
    TextView txt_invoice;

    Button btn_Submit_thirdPoint;

    String id;

    Calendar mcurrentdate;
    int day, month, year;

    List<String> EmpVenID = new ArrayList<>();
    List<String> EmpVenName = new ArrayList<>();
    List<String> EmpVenMobile = new ArrayList<>();


    String waypath;

    Uri uri_CD_UploadPhoto;

    String emp_ven_name, emp_ven_id, emp_ven_mobile;

    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;
    Integer ID;
    String CUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_point_value_third);

        getSupportActionBar().setTitle("Add Point value");

        spn_select_vendor = findViewById(R.id.spn_select_vendor);
        edt_sell_date = findViewById(R.id.edt_sell_date);
        edt_product_name = findViewById(R.id.edt_product_name);
        edt_deal_price = findViewById(R.id.edt_deal_price);
        lin_invoice = findViewById(R.id.lin_invoice);
        img_invoice = findViewById(R.id.img_invoice);
        txt_invoice = findViewById(R.id.txt_invoice);
        edt_customer_name = findViewById(R.id.edt_customer_name);
        edt_Village = findViewById(R.id.edt_Village);

        btn_Submit_thirdPoint = findViewById(R.id.btn_Submit_thirdPoint);


        sharedPreferences = getSharedPreferences("ID", MODE_PRIVATE);
        ID= sharedPreferences.getInt("id",0);
        CUID = sharedPreferences.getString("cuid", "");


        id= getIntent().getStringExtra("id");

        //Toast.makeText(this, ""+ID, Toast.LENGTH_SHORT).show();

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH);
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR);


        edt_sell_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddPointValueThirdActivity.this,
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


        WebService.getClient().getSmartCardVendor().enqueue(new Callback<ThirdSmartCardVendorModel>() {
            @Override
            public void onResponse(@NotNull Call<ThirdSmartCardVendorModel> call,
                                   @NotNull Response<ThirdSmartCardVendorModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        EmpVenName.clear();
                        EmpVenID.clear();
                        EmpVenMobile.clear();


                        EmpVenName.add("Select Vendor");
                        EmpVenID.add("0");
                        EmpVenMobile.add("0");



                        for (int i = 0; i < response.body().getData().size(); i++) {
                            EmpVenName.add(response.body().getData().get(i).getName());
                            EmpVenID.add(response.body().getData().get(i).getId());
                            EmpVenMobile.add(response.body().getData().get(i).getMobile());
                        }

                        ArrayAdapter arrayAdapterEV = new ArrayAdapter(AddPointValueThirdActivity.this,
                                android.R.layout.simple_dropdown_item_1line, EmpVenName);
                        spn_select_vendor.setAdapter(arrayAdapterEV);

                        spn_select_vendor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                emp_ven_name = EmpVenName.get(position);
                                emp_ven_id = EmpVenID.get(position);
                                emp_ven_mobile = EmpVenMobile.get(position);
                                //   Toast.makeText(MaintenaceActivity.this, ""+emp_ven_id+emp_ven_name, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<ThirdSmartCardVendorModel> call, @NotNull Throwable t) {

            }
        });


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


                progressDialog= new ProgressDialog(AddPointValueThirdActivity.this);
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



                WebService.getClient().getAddSmartCardThird(RequestBody.create(MediaType.parse("text/plain"), String.valueOf(ID)),
                        RequestBody.create(MediaType.parse("text/plain"), emp_ven_mobile),
                        RequestBody.create(MediaType.parse("text/plain"), emp_ven_id),
                        RequestBody.create(MediaType.parse("text/plain"), edt_sell_date.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_product_name.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_deal_price.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_customer_name.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_Village.getText().toString()),
                        UploadPhoto
                        ).enqueue(new Callback<AddSmartCardThirdModel>() {
                    @Override
                    public void onResponse(@NotNull Call<AddSmartCardThirdModel> call,
                                           @NotNull Response<AddSmartCardThirdModel> response) {

                        progressDialog.dismiss();
                        assert response.body() != null;
                        Toast.makeText(AddPointValueThirdActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();



                        Intent i = new Intent(AddPointValueThirdActivity.this, AddPointValueFourthActivity.class);
                        startActivity(i);

                    }

                    @Override
                    public void onFailure(@NotNull Call<AddSmartCardThirdModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(AddPointValueThirdActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();

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