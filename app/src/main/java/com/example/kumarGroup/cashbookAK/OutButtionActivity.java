package com.example.kumarGroup.cashbookAK;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.cashbook_inButton_model;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutButtionActivity extends AppCompatActivity {
    EditText indate,in_amount,descripin_cash;
    Calendar mcurrentdate,mcurrentTime;
    int day,month,year;
    TextView select_mode_cash,select_mode_bank,bill_attach;
    int cashfalg = 0, bankflag = 0;
    Button save_inbutton;
    Uri uri_CD_UploadPhoto;
    String waypath, sendapiflag;
    ProgressDialog progressDialog;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_buttion);

        indate = findViewById(R.id.indate);
        select_mode_cash = findViewById(R.id.select_mode_cash);
        select_mode_bank = findViewById(R.id.select_mode_bank);
        save_inbutton = findViewById(R.id.save_inbutton);
        bill_attach = findViewById(R.id.bill_attach);
        in_amount = findViewById(R.id.in_amount);
        descripin_cash = findViewById(R.id.descripin_cash);

        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH);
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR);

        indate.setFocusable(false);

        indate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(OutButtionActivity.this,
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
                        indate.setText(year + "-" + mt + "-" + dy);
                    }
                }, year, month, day);
                datePickerDialog.show();
                long minTime = mcurrentdate.getTimeInMillis();
                datePickerDialog.getDatePicker().setMaxDate(minTime);

            }
        });

        select_mode_cash.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                select_mode_bank.setBackgroundResource(R.drawable.cash_bank_round_file_white);
                select_mode_cash.setBackgroundResource(R.drawable.outbuttion);
                cashfalg = 1;
                bankflag = 0;
            }
        });

        select_mode_bank.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                select_mode_cash.setBackgroundResource(R.drawable.cash_bank_round_file_white);;
                select_mode_bank.setBackgroundResource(R.drawable.outbuttion);
                cashfalg = 0;
                bankflag = 1;
            }
        });

        save_inbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog= new ProgressDialog(OutButtionActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                MultipartBody.Part UploadPhoto = null;

                if(waypath != null){
                    File file15 = new File(waypath);
                    final RequestBody requestBody15 = RequestBody.create(MediaType.parse("image/*"), file15);
                    UploadPhoto = MultipartBody.Part.createFormData("image1",
                            file15.getName(), requestBody15);
                }

                if (TextUtils.isEmpty(in_amount.getText().toString().trim()) && "".equals(in_amount.getText().toString().trim())){
                    progressDialog.dismiss();
                    Utils.showErrorToast(OutButtionActivity.this,"Enter amount");
                    return;
                }
                if (TextUtils.isEmpty(descripin_cash.getText().toString().trim()) && "".equals(descripin_cash.getText().toString().trim())){
                    progressDialog.dismiss();
                    Utils.showErrorToast(OutButtionActivity.this,"Enter Description");
                    return;
                }
                if (TextUtils.isEmpty(indate.getText().toString().trim()) && "".equals(indate.getText().toString().trim())){
                    progressDialog.dismiss();
                    Utils.showErrorToast(OutButtionActivity.this,"Select a date");
                    return;
                }

                if (cashfalg == 0 && bankflag == 0){
                    progressDialog.dismiss();
                    Utils.showErrorToast(OutButtionActivity.this,"Please select Payment mode");
                    return;
                }
                else {
                    if (cashfalg == 1){
                        sendapiflag = "Cash";
                    }
                    else {
                        sendapiflag = "Bank";
                    }
                }

                sp = getSharedPreferences("Login", MODE_PRIVATE);

                String emp_id = sp.getString("emp_id", "");

                Log.d("TAG", "imagesss "+UploadPhoto);
                WebService.getClient().cashbook_send_inbutton_data_web(
                        RequestBody.create(MediaType.parse("text/plain"), emp_id),
                        RequestBody.create(MediaType.parse("text/plain"), "OUT"),
                        RequestBody.create(MediaType.parse("text/plain"), sendapiflag),
                        RequestBody.create(MediaType.parse("text/plain"), in_amount.getText().toString().trim()),
                        RequestBody.create(MediaType.parse("text/plain"), descripin_cash.getText().toString().trim()),
                        RequestBody.create(MediaType.parse("text/plain"), indate.getText().toString().trim()),
                        UploadPhoto
                ).enqueue(new Callback<cashbook_inButton_model>() {
                    @Override
                    public void onResponse(@NotNull Call<cashbook_inButton_model> call,
                                           @NotNull Response<cashbook_inButton_model> response) {

                        progressDialog.dismiss();
                        assert response.body() != null;
                        Toast.makeText(OutButtionActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();


                        //   Toast.makeText(DeliveryButtonActivity.this,"response id "+response.body().getId() , Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(OutButtionActivity.this, CashbookDashboard.class);
                        startActivity(i);
                        finish();

                    }

                    @Override
                    public void onFailure(@NotNull Call<cashbook_inButton_model> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(OutButtionActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        bill_attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gal3 = new Intent(Intent.ACTION_PICK);
                gal3.setType("image/*");
                startActivityForResult(gal3, 2);
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
                    bill_attach.setText(name[name.length - 1]);

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