package com.example.kumarGroup.Maintenance;

import static com.example.kumarGroup.Utils.CAMERA_REQUEST_CODE;
import static com.example.kumarGroup.Utils.currentPhotoPath;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kumarGroup.MainTainanceType;
import com.example.kumarGroup.MaintananceEmpVenList;
import com.example.kumarGroup.MaintenanceAddModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMaintenanceActivity extends AppCompatActivity {

    EditText edtMAType,edtMAAmount,edtMADate,edtMADescription;
    Spinner spnMAVenEmp,spnMAListEmpVen,spnAddLoanType;
    ImageView edtAddLoanDescription;
    TextView txtUploadPhoto;
    Button btnAddMaintenance;

    Calendar mcurrentdate;
    int day,month,year;

    String venEmp;
    String venCash;
    String[] Ven_Emp = {"Employee","Vendor"};
    String[] Ven_CAsh = {"Select LoanType","Bank","Cash"};
    String[] Invoice = {"Add GST Invoice","YES ","No"};

    ProgressDialog progressDialog;

    SharedPreferences sp1;
    String emp;

    String emp_ven_name,emp_ven_id,invoice;
    String emp_cash_name,emp_cash_id;

    List<String> EmpVenID = new ArrayList<>();
    List<String> EmpVenName = new ArrayList<>();

    List<String> EmpCashID = new ArrayList<>();
    List<String> EmpCashName = new ArrayList<>();

    Spinner sp_maintenance,spnAddInvoice;
    String  MTypedata = "", mlistdata = "", strModel = "";


    Uri uriPhoto;
    List<String> l_name = new ArrayList<>();
    List<String> l_type_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_maintenance);
        getSupportActionBar().setTitle("Add Maintenance");

        spnAddInvoice=findViewById(R.id.spnAddInvoice);
        sp_maintenance=findViewById(R.id.sp_maintenance);
        edtMAType=findViewById(R.id.edtMAType);
        edtMAAmount=findViewById(R.id.edtMAAmount);
        edtMADate=findViewById(R.id.edtMADate);
        edtMADescription=findViewById(R.id.edtMADescription);
        spnMAVenEmp=findViewById(R.id.spnMAVenEmp);
        spnMAListEmpVen=findViewById(R.id.spnMAListEmpVen);
        spnAddLoanType=findViewById(R.id.spnAddCashTYpe);
        edtAddLoanDescription=findViewById(R.id.edtAddLoanDescription);
        txtUploadPhoto=findViewById(R.id.txtUploadPhoto);
        btnAddMaintenance=findViewById(R.id.btnAddMaintenance);

        edtMADate.setFocusable(false);
        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");


        WebService.getClient().getMaintananceList().enqueue(new Callback<MainTainanceType>() {
            @Override
            public void onResponse(Call<MainTainanceType> call, Response<MainTainanceType> response1) {
                Log.d("MakeName", response1.body().toString());
                if (response1.isSuccessful()) {
                    if (response1.body() != null) {
                        l_type_list.clear();
                        l_type_list.add("Select Maintenance Type");

                        for (int i = 0; i < response1.body().getData().size(); i++)
                        {
                            l_type_list.add(response1.body().getData().get(i).getType());
                        }

                        ArrayAdapter adapter2 = new ArrayAdapter(AddMaintenanceActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, l_type_list);
                        sp_maintenance.setAdapter(adapter2);

                        sp_maintenance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                               //
                                Log.d("ModelList", MTypedata);
                                if( l_type_list.get(position)=="Select Maintenance Type"){
                                    MTypedata = "";
                                }
                                else{
                                    MTypedata = l_type_list.get(position);

                                    Log.d("MTypedata",MTypedata);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<MainTainanceType> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong... Please try again after sometime! \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Ven_Emp);
        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Ven_CAsh);
        ArrayAdapter adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Invoice);
        spnMAVenEmp.setAdapter(adapter);
        spnAddLoanType.setAdapter(adapter1);
        spnAddInvoice.setAdapter(adapter2);

        spnMAVenEmp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                venEmp = Ven_Emp[position];
              //  Toast.makeText(MaintenaceActivity.this, ""+venEmp, Toast.LENGTH_SHORT).show();

                WebService.getClient().getMaintanaceEmpVenList(venEmp).enqueue(new Callback<MaintananceEmpVenList>() {
                    @Override
                    public void onResponse(@NotNull Call<MaintananceEmpVenList> call,
                                           @NotNull Response<MaintananceEmpVenList> response) {
                        if(response.isSuccessful()){
                            if(response.body()!=null){
                                EmpVenName.clear();
                                EmpVenID.clear();
                                for (int i = 0;i<response.body().getType().size();i++){
                                    EmpVenName.add(response.body().getType().get(i).getName());
                                    EmpVenID.add(response.body().getType().get(i).getId());
                                }
                                ArrayAdapter arrayAdapterEV = new ArrayAdapter(AddMaintenanceActivity.this, android.R.layout.simple_dropdown_item_1line,EmpVenName);
                                spnMAListEmpVen.setAdapter(arrayAdapterEV);

                                spnMAListEmpVen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        emp_ven_name = EmpVenName.get(position);
                                        emp_ven_id = EmpVenID.get(position);
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
                    public void onFailure(@NotNull Call<MaintananceEmpVenList> call, @NotNull Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spnAddLoanType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                venCash = Ven_CAsh[position];

                if( Ven_CAsh[position]=="Select LoanType"){
                    venCash = "";
                }
                else{
                    venCash = Ven_CAsh[position];
                }
//                  Toast.makeText(AddMaintenanceActivity.this, ""+venCash, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spnAddInvoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if( Invoice[position]=="Add GST Invoice"){
                    invoice = "";
                }
                else{
                    invoice = Invoice[position];
                    Log.d("invoice",invoice);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                txtUploadPhoto.setEnabled(false);
            }
        });

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );
        // month = month+1;

        edtMADate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddMaintenanceActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth)
                    {
                        monthofYear = monthofYear+1;

                        String mt,dy;   //local variable
                        if(monthofYear<10) {
                            mt = "0" + monthofYear; //if month less than 10 then ad 0 before month
                        }
                        else{
                            mt=String.valueOf(monthofYear);
                        }

                        if(dayOfMonth<10) {
                            dy = "0" + dayOfMonth;
                        }
                        else{
                            dy = String.valueOf(dayOfMonth);
                        }
                        //2020-09-22
                        //  edtAddLoanDate.setText(mt+"/"+dy+"/"+year);
                        edtMADate.setText(year+"-"+mt+"-"+dy);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        // Upload Button:- Photo

        txtUploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("invoiceclick",invoice);

                SelectImage();

            }
        });


        btnAddMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog= new ProgressDialog(AddMaintenanceActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );

                if(edtMAType.getText().toString().equals(""))
                {
                    edtMAType.setError("Please Fill Maintenance Type");
                }

                if(edtMAAmount.getText().toString().equals("")){
                    edtMAAmount.setError("Please Fill Amount");
                }

                if(edtMADate.getText().toString().equals("")){
                    edtMADate.setError("Please Fill Date");
                }

                if(edtMADescription.getText().toString().equals("")){
                    edtMADescription.setError("Please Fill Description");
                }




                if(spnMAVenEmp == null && spnMAVenEmp.getSelectedItem() ==null ) {
                    Utils.showErrorToast(AddMaintenanceActivity.this,"Please select feild");
                }

                if(MTypedata == null && sp_maintenance.getSelectedItem() ==null ) {
                    Utils.showErrorToast(AddMaintenanceActivity.this,"Please select Maintenance Type");
                }

                if(invoice == null && spnAddInvoice.getSelectedItem() ==null ) {
                    Utils.showErrorToast(AddMaintenanceActivity.this,"Please select GST Invoice");
                }

                if(spnAddLoanType == null && spnAddLoanType.getSelectedItem() =="Select LoanType" ) {
                    Utils.showErrorToast(AddMaintenanceActivity.this,"Please Select LoanType");
                }

                if(currentPhotoPath!=null){


                    File file1 = new File(currentPhotoPath);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    MultipartBody.Part imagePartPhoto = MultipartBody.Part.createFormData("image8", file1.getName(), requestBody1);


                    WebService.getClient().getMaintenanceAdd(
                            RequestBody.create( MediaType.parse("text/plain"),emp),
                            RequestBody.create( MediaType.parse("text/plain"),MTypedata),
                            RequestBody.create( MediaType.parse("text/plain"),venEmp),
                            RequestBody.create( MediaType.parse("text/plain"),venCash),
                            RequestBody.create( MediaType.parse("text/plain"),emp_ven_id),
                            RequestBody.create( MediaType.parse("text/plain"),edtMAAmount.getText().toString()),
                            RequestBody.create( MediaType.parse("text/plain"),edtMADate.getText().toString()),
                            RequestBody.create( MediaType.parse("text/plain"),edtMADescription.getText().toString()),
                            RequestBody.create( MediaType.parse("text/plain"),invoice),
                            imagePartPhoto).enqueue(new Callback<MaintenanceAddModel>() {
                        @Override
                        public void onResponse(@NotNull Call<MaintenanceAddModel> call,
                                               @NotNull Response<MaintenanceAddModel> response) {
                            progressDialog.dismiss();

                            assert response.body() != null;
                            Log.d("Response", response.body().toString());
                            Toast.makeText(AddMaintenanceActivity.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();

                            Intent i = new Intent(AddMaintenanceActivity.this, MaintananceMainActivity.class);
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(@NotNull Call<MaintenanceAddModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();
                            Log.d("error_response",t.getMessage());
                        }
                    });
                }else{
                    Utils.showErrorToast(AddMaintenanceActivity.this,"Please upload Photo");
                }


            }
        });
    }

    private void SelectImage() {
        final CharSequence[] items = {"Camera", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AddMaintenanceActivity.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 4);
                } else if (items[i].equals("Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Select File"), 1);
                } else if (items[i].equals("Cancel"))
                    dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 4) {
            if(resultCode==RESULT_OK) {
                Bundle bundle = data.getExtras();
                final Bitmap photo = (Bitmap) bundle.get("data");
                edtAddLoanDescription.setImageBitmap(photo);
                savebitmap(photo,txtUploadPhoto);

         /*   File file=new File(waypath);
            saveBitmaptoFile(file);*/
            }

        }else if (requestCode == CAMERA_REQUEST_CODE) {
            if(resultCode==RESULT_OK) {
                File f = new File(currentPhotoPath);

                Glide.with(AddMaintenanceActivity.this).load(currentPhotoPath).into(edtAddLoanDescription);
           /*     File file1 = new File( Utils.compressImage(panimagepath));
                getImageBitmap(currentPhotoPath, edtAddLoanDescription, f, this);*/
                String[] name = currentPhotoPath.split("/");
                txtUploadPhoto.setText(name[name.length-1]);
            }

        }

        /*if(requestCode==2)
        {
            if(resultCode==RESULT_OK)
            {
                if(data!=null)
                {
                    uriPhoto=data.getData();
                    Log.d("Pan Image Uri",uriPhoto.toString());
                    currentPhotoPath = getFilePath(this,uriPhoto);
                    Log.d("Pan Image Uri",currentPhotoPath);
                    String[] name = currentPhotoPath.split("/");
                    txtUploadPhoto.setText(name[name.length-1]);

                    Uri selectedImageUri = data.getData();
                    edtAddLoanDescription.setImageURI(selectedImageUri);

                }
            }
        }*/
    }

    private File savebitmap(Bitmap bmp, TextView txtUploadPhoto) {

    /*    String extStorageDirectory;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            extStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
        } else {
            extStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
        }
*/
        String extStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();/* Environment.getExternalStorageDirectory().getPath();*/
        FileOutputStream outStream = null;
        // String temp = null;

        final int random = new Random().nextInt(6000000) + 20;
        File file = new File(extStorageDirectory, random + ".png");

        currentPhotoPath = getFilePath(this, Uri.fromFile(file));
       /* if (!file.exists())
        {
            // Make it, if it doesn't exit
            boolean success = file.getParentFile().mkdirs();
            if (!success)
            {
                file = null;
            }
        }else*/ if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, random + ".png");
            currentPhotoPath = getFilePath(this, Uri.fromFile(file));
        }
        String[] name = currentPhotoPath.split("/");
        txtUploadPhoto.setText(name[name.length-1]);
        try {
            outStream = new FileOutputStream(file);
//            Bitmap scaledBitmap = scaleDown(bmp, 65, true);
//            bmp.compress(Bitmap.CompressFormat.PNG, 30, outStream);
//            scaledBitmap.compress(Bitmap.CompressFormat.PNG, 65, outStream);
            bmp.compress(Bitmap.CompressFormat.PNG, 70, outStream);
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