package com.example.kumarGroup.Workshop.WsManager;

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

import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WSManagerEditModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WsManagerEditFormActivity extends AppCompatActivity {


    Spinner spn_WSM_AddCustomerProfile,spn_WSM_Remark,spn_WSM_inq;

    EditText edt_WSM_CustomerValue,edt_WSM_Remark,edt_WSM_NextVisitDate,
            edt_WSm_EngineeringNo,edt_WSm_ChasisNo;

    LinearLayout linWSCustomerWith,linChasisPrint,linWSCustomerWith1;

    ImageView img_WSM_WithCustomer,img_WSM_ChasisPrint,img_WSM_WithCustomer1;

    TextView txt_WSM_WithCustomer,txt_WSM_ChasisPrint,txt_WSM_WithCustomer1;


    String AddCProfile;
    String[] AddC_Profile = {"Add Customer Profile","Yes","No"};


    String Remark;
    String[] Remark_val = {"Select Remark","Yes","No"};

    String Inquiry;
    String[] Inquiry_val = {"Pending","Clear"};

    ProgressDialog progressDialog;

    SharedPreferences sp1;
    String emp;

    Button Btn_WSM_Submit;



    Uri uriWithCustomer, uriChasisPrint, uriWithCustomer1;


    String waypathWithCustomer,waypathChasisPrint,waypathWithCustomer1;


    Calendar mcurrentdate;
    int day, month, year;


    String MobileNo,PartId,ChasisNo, EngieerNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws_manager_edit_form);


        sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp1.getString("emp_id", "");


        MobileNo=getIntent().getStringExtra("MobileNo");
        PartId=getIntent().getStringExtra("PartId");
        ChasisNo =getIntent().getStringExtra("ChasisNo");
        EngieerNo=getIntent().getStringExtra("EngieerNo");

        spn_WSM_AddCustomerProfile = findViewById(R.id.spn_WSM_AddCustomerProfile);
        spn_WSM_Remark = findViewById(R.id.spn_WSM_Remark);
        spn_WSM_inq = findViewById(R.id.spn_WSM_inq);

        edt_WSM_CustomerValue = findViewById(R.id.edt_WSM_CustomerValue);

        edt_WSM_Remark = findViewById(R.id.edt_WSM_Remark);
        edt_WSM_NextVisitDate = findViewById(R.id.edt_WSM_NextVisitDate);

        edt_WSm_EngineeringNo = findViewById(R.id.edt_WSm_EngineeringNo);
        edt_WSm_ChasisNo = findViewById(R.id.edt_WSm_ChasisNo);

        linWSCustomerWith = findViewById(R.id.linWSCustomerWith);
        linChasisPrint = findViewById(R.id.linChasisPrint);
        linWSCustomerWith1 = findViewById(R.id.linWSCustomerWith1);

        img_WSM_WithCustomer = findViewById(R.id.img_WSM_WithCustomer);
        img_WSM_ChasisPrint = findViewById(R.id.img_WSM_ChasisPrint);
        img_WSM_WithCustomer1 = findViewById(R.id.img_WSM_WithCustomer1);

        txt_WSM_WithCustomer = findViewById(R.id.txt_WSM_WithCustomer);
        txt_WSM_ChasisPrint = findViewById(R.id.txt_WSM_ChasisPrint);
        txt_WSM_WithCustomer1 = findViewById(R.id.txt_WSM_WithCustomer1);

        Btn_WSM_Submit = findViewById(R.id.Btn_WSM_Submit);



        edt_WSm_ChasisNo.setText(ChasisNo);
        edt_WSm_EngineeringNo.setText(EngieerNo);


        ArrayAdapter adapterBankPassBook = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                AddC_Profile);
        spn_WSM_AddCustomerProfile.setAdapter(adapterBankPassBook);

        spn_WSM_AddCustomerProfile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AddCProfile = AddC_Profile[position];

                if(AddCProfile.equals("Add Customer Profile") || AddCProfile.equals("No")){
                    edt_WSM_CustomerValue.setVisibility(View.GONE);
                }
                else {
                    edt_WSM_CustomerValue.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Inquiry_val);
        spn_WSM_inq.setAdapter(adapter1);

        spn_WSM_inq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Inquiry = Inquiry_val[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Remark_val);
        spn_WSM_Remark.setAdapter(adapter);

        spn_WSM_Remark.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Remark = Remark_val[position];

                if(Remark.equals("Select Remark") || Remark.equals("No")){
                    edt_WSM_Remark.setVisibility(View.GONE);
                    edt_WSM_NextVisitDate.setVisibility(View.GONE);

                    edt_WSM_Remark.setText("no");
                    edt_WSM_NextVisitDate.setText("no");
                }
                else {


                    edt_WSM_Remark.setText("");
                    edt_WSM_NextVisitDate.setText("");

                    edt_WSM_Remark.setVisibility(View.VISIBLE);
                    edt_WSM_NextVisitDate.setVisibility(View.VISIBLE);                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH);
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR);
        // month = month+1;


        edt_WSM_NextVisitDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(WsManagerEditFormActivity.this,
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
                        //2021-05-29
                        // EdtWalletSalarySlipDateOne.setText(dy+"/"+mt+"/"+year);
                        edt_WSM_NextVisitDate.setText(year + "-" + mt + "-" + dy);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_WSM_NextVisitDate.setFocusable(false);




        txt_WSM_WithCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 2);
            }
        });


        txt_WSM_ChasisPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 3);
            }
        });


        txt_WSM_WithCustomer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 4);
            }
        });




        Btn_WSM_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(WsManagerEditFormActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                if(AddCProfile.equals("Add Customer Profile")){
                    Utils.showErrorToast(WsManagerEditFormActivity.this,"Please select Add Yes or No");
                }

                if(AddCProfile.equals("Yes")){

                    edt_WSM_CustomerValue.setText("");

                    if(edt_WSM_CustomerValue.getText().toString().equals("")){
                        edt_WSM_CustomerValue.setError("Please enter Customer Value");
                    }
                }
                else {
                    edt_WSM_CustomerValue.setText("no");
                }

                if(Remark.equals("Select Remark")){
                    Utils.showErrorToast(WsManagerEditFormActivity.this,"Please select Add Yes or No");
                }

                if(Remark.equals("Yes")){
                    if(edt_WSM_Remark.getText().toString().equals("")){
                        edt_WSM_Remark.setError("Please enter Remark");
                    }

                    if(edt_WSM_NextVisitDate.getText().toString().equals("")){
                        edt_WSM_NextVisitDate.setError("Please enter Next Visit Date");
                    }
                }

                MultipartBody.Part imagePartCustomer1 = null;

                if(waypathWithCustomer1 != null){
                    File file3 = new File(waypathWithCustomer);
                    final RequestBody requestBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                     imagePartCustomer1 = MultipartBody.Part.createFormData("image8",
                            file3.getName(), requestBody3);
                }



                if (waypathChasisPrint == null) {
                    Utils.showErrorToast(WsManagerEditFormActivity.this, "Please upload Chasis Print Photo");
                }
                else if(waypathWithCustomer == null){
                    Utils.showErrorToast(WsManagerEditFormActivity.this, "Please upload Customer Photo");
                }
                else {

                    File file2 = new File(waypathWithCustomer);
                    final RequestBody requestBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                    MultipartBody.Part imagePartCustomer = MultipartBody.Part.createFormData("image1",
                            file2.getName(), requestBody2);

                    File file1 = new File(waypathChasisPrint);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    MultipartBody.Part imagePartChasisPrint = MultipartBody.Part.createFormData("image2",
                            file1.getName(), requestBody1);



                   /* File file3 = new File(waypathWithCustomer1);
                    final RequestBody requestBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                    MultipartBody.Part imagePartCustomer1 = MultipartBody.Part.createFormData("image8",
                            file3.getName(), requestBody3);*/

                    if (!AddCProfile.equals("Add Customer Profile") &&
                            !Remark.equals("Select Remark") ) {

//                        Log.d("TAG",
//                                " peraismobile "+MobileNo +
//                                " PartId "+PartId +
//                                " loginid "+emp +
//                                " cuprofile "+AddCProfile +
//                                " prodes "+edt_WSM_CustomerValue.getText().toString() +
//                                " Remark "+Remark +
//                                " remarkval "+edt_WSM_Remark.getText().toString() +
//
//                                " next visit "+edt_WSM_NextVisitDate.getText().toString() +
//                                " engin no "+edt_WSm_EngineeringNo.getText().toString() +
//                                " chasis no "+edt_WSm_ChasisNo.getText().toString() +
//                                " imageparcusomer "+imagePartCustomer +
//                                " imageparcusomer print "+imagePartChasisPrint
//                        );

                        WebService.getClient().getWsManagerEdit(
                                RequestBody.create(MediaType.parse("text/plain"), MobileNo),
                                RequestBody.create(MediaType.parse("text/plain"), PartId),
                                RequestBody.create(MediaType.parse("text/plain"), emp),
                                RequestBody.create(MediaType.parse("text/plain"), AddCProfile),
                                RequestBody.create(MediaType.parse("text/plain"), edt_WSM_CustomerValue.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"), Remark),
                                RequestBody.create(MediaType.parse("text/plain"), edt_WSM_Remark.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"), edt_WSM_NextVisitDate.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"), edt_WSm_EngineeringNo.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"), edt_WSm_ChasisNo.getText().toString()),
                                imagePartCustomer,
                                imagePartChasisPrint
                        ).enqueue(new Callback<WSManagerEditModel>() {
                            @Override
                            public void onResponse(@NotNull Call<WSManagerEditModel> call,
                                                   @NotNull Response<WSManagerEditModel> response) {
                                progressDialog.dismiss();

                                Log.d("Response", response.body().toString());
                                Toast.makeText(WsManagerEditFormActivity.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();

                                Intent i = new Intent(WsManagerEditFormActivity.this, FoDashbord.class);
                                startActivity(i);
                            }

                            @Override
                            public void onFailure(@NotNull Call<WSManagerEditModel> call,
                                                  @NotNull Throwable t) {
                                Toast.makeText(getApplicationContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                            }
                        });
                    }

                }

                }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriWithCustomer = data.getData();
                    Log.d("PanImageUri", uriWithCustomer.toString());
                    waypathWithCustomer = getFilePath(this, uriWithCustomer);
                    Log.d("PanImageWayPath", waypathWithCustomer);
                    String[] name = waypathWithCustomer.split("/");
                    txt_WSM_WithCustomer.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_WSM_WithCustomer.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriChasisPrint = data.getData();
                    Log.d("Pan Image Uri", uriChasisPrint.toString());
                    waypathChasisPrint = getFilePath(this, uriChasisPrint);
                    Log.d("Pan Image Uri", waypathChasisPrint);
                    String[] name = waypathChasisPrint.split("/");
                    txt_WSM_ChasisPrint.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_WSM_ChasisPrint.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriWithCustomer1 = data.getData();
                    Log.d("PanImageUri", uriWithCustomer1.toString());
                    waypathWithCustomer1 = getFilePath(this, uriWithCustomer1);
                    Log.d("PanImageWayPath", waypathWithCustomer1);
                    String[] name = waypathWithCustomer1.split("/");
                    txt_WSM_WithCustomer1.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_WSM_WithCustomer1.setImageURI(selectedImageUri);
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