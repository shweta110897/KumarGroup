package com.example.kumarGroup.Traveling;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.StartTravellingModel;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
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

public class StartTravellingActivity extends AppCompatActivity {


    ImageView imgStartTravelling;
    TextView txtStartTravelling;
    EditText edtStartTravellingKm;
    Button btnStartTravellingNextPage;

    ProgressDialog progressDialog;
    String emp;
    SharedPreferences sp,sp1,sp2;

    SharedPreferences sharedPreferences;

    String checkInPhase;

    Integer REQUEST_CAMERA = 4, SELECT_FILE = 0;

    Uri uriclient;
    String waypathClient, waypath;

    String idPhaseOt,idCheckInOt,module_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_travelling);


        imgStartTravelling= findViewById(R.id.imgStartTravelling);
        txtStartTravelling= findViewById(R.id.txtStartTravelling);
        edtStartTravellingKm= findViewById(R.id.edtStartTravellingKm);
        btnStartTravellingNextPage= findViewById(R.id.btnStartTravellingNextPage);


        sp = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp.getString("emp_id", "");

        module_name = sp.getString("module_name", "");


        //   Toast.makeText(this, ""+module_name, Toast.LENGTH_SHORT).show();


        sharedPreferences = getSharedPreferences("StartTravelling", MODE_PRIVATE);

        sp2 = getSharedPreferences("CheckInPhase", MODE_PRIVATE);
        checkInPhase = sp2.getString("CheckInPhase","");



        sp1 = getSharedPreferences("CheckIn", MODE_PRIVATE);

        idPhaseOt = sp1.getString("idPhaseOt","");
        idCheckInOt = sp1.getString("idCheckInOt","");

        //  Toast.makeText(this, "a "+checkInPhase+" o "+idPhaseOt, Toast.LENGTH_SHORT).show();


       /* if(checkInPhase.equals("")||checkInPhase == null){
            btnStartTravellingNextPage.setClickable(false);
            btnStartTravellingNextPage.setEnabled(false);
            Utils.showErrorToast(StartTravellingActivity.this, "Please Do Check In Attendance");

        }

        else{
            btnStartTravellingNextPage.setClickable(true);
            btnStartTravellingNextPage.setEnabled(true);
            Toast.makeText(this, "Do Check In", Toast.LENGTH_SHORT).show();
        }*/


        if(module_name.contains("Overtime")){
            if(idPhaseOt.equals("1")){
                btnStartTravellingNextPage.setClickable(true);
                btnStartTravellingNextPage.setEnabled(true);
                Toast.makeText(this, "Do Check In..", Toast.LENGTH_SHORT).show();
            }
            else if(idPhaseOt.equals("")  && checkInPhase.equals("")){
                btnStartTravellingNextPage.setClickable(false);
                btnStartTravellingNextPage.setEnabled(false);
                Utils.showErrorToast(StartTravellingActivity.this, "Do Check In Overtime or attendence");
            }
            else if(idPhaseOt== null && checkInPhase == null){
                btnStartTravellingNextPage.setClickable(false);
                btnStartTravellingNextPage.setEnabled(false);
                Utils.showErrorToast(StartTravellingActivity.this, "Do Check In Overtime or attendence..");
            }
            else if(idPhaseOt.equals("1") || checkInPhase.equals("1")){
                btnStartTravellingNextPage.setClickable(true);
                btnStartTravellingNextPage.setEnabled(true);
                Toast.makeText(this, "Do Check In", Toast.LENGTH_SHORT).show();
            }
            else{
                btnStartTravellingNextPage.setClickable(true);
                btnStartTravellingNextPage.setEnabled(true);
                Toast.makeText(this, "Do Check In...", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            if(checkInPhase.equals("")||checkInPhase == null){
                btnStartTravellingNextPage.setClickable(false);
                btnStartTravellingNextPage.setEnabled(false);
                Utils.showErrorToast(StartTravellingActivity.this, "Please Do Check In Attendance");
            }
       /* else if(checkInPhase.equals("1")){

            btnStartTravellingNextPage.setClickable(true);
            btnStartTravellingNextPage.setEnabled(true);
            Toast.makeText(this, "Do Check In", Toast.LENGTH_SHORT).show();
        }*/

            else{
                btnStartTravellingNextPage.setClickable(true);
                btnStartTravellingNextPage.setEnabled(true);
                Toast.makeText(this, "Do Check In", Toast.LENGTH_SHORT).show();
            }
        }


        txtStartTravelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });


        btnStartTravellingNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(StartTravellingActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                if (edtStartTravellingKm.getText().toString().equals("")) {
                    //  Utils.showErrorToast(FoLogin.this, "Please Enter Password");
                    edtStartTravellingKm.setError("Please Enter Km");
                }

                if (waypath == null) {
                    Utils.showErrorToast(StartTravellingActivity.this, "Please upload Selfie Photo");
                }
                else {
                    Log.d("upload image", "onClick: " + waypath);
                    File file1 = new File(waypath);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    MultipartBody.Part imagePartStartTravelling = MultipartBody.Part.createFormData("image8", file1.getName(), requestBody1);

                    Log.d("TAG", "empkm: "+emp + " km "+edtStartTravellingKm.getText().toString()+" img "+imagePartStartTravelling);
                    WebService.getClient().getStartTravelling(
                            RequestBody.create(MediaType.parse("text/plain"), emp),
                            RequestBody.create(MediaType.parse("text/plain"), edtStartTravellingKm.getText().toString()),
                            imagePartStartTravelling
                    ).enqueue(new Callback<StartTravellingModel>() {
                        @Override
                        public void onResponse(@NotNull Call<StartTravellingModel> call,
                                               @NotNull Response<StartTravellingModel> response) {
                            progressDialog.dismiss();

                            assert response.body() != null;

                            // Toast.makeText(StartTravellingActivity.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();

                            String Phase = String.valueOf(response.body().getPhase());
                            String idCheckIn = String.valueOf(response.body().getId());

                            //  Toast.makeText(StartTravellingActivity.this, ""+idCheckIn+" "+Phase, Toast.LENGTH_SHORT).show();


                            sharedPreferences.edit().putString("idCheckInTravelling", idCheckIn).apply();

                            sharedPreferences.edit().putString("idPhaseTravelling", Phase).apply();



                            Intent i = new Intent(StartTravellingActivity.this, EndTravelingActivity.class);

                            i.putExtra("idCheckInTravel",String.valueOf(response.body().getId()));

                            String id =String.valueOf(response.body().getId());


                            sp = getSharedPreferences("StartTravelling", MODE_PRIVATE);
                            sp.edit().putString("idPhaseTravelling", String.valueOf(response.body().getPhase())).apply();
                            sp.edit().putString("idCheckInStartTravelling", id).apply();

                            startActivity(i);
                        }

                        @Override
                        public void onFailure(@NotNull Call<StartTravellingModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();

                            Log.d("Response2", t.getMessage());
                            Toast.makeText(StartTravellingActivity.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

    private void SelectImage()
    {
        final CharSequence[] items = {"Camera", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(StartTravellingActivity.this);
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
        if (requestCode == 4) {

            Bundle bundle = data.getExtras();
            final Bitmap photo = (Bitmap) bundle.get("data");
            imgStartTravelling.setImageBitmap(photo);
            savebitmap(photo);

        } else if (requestCode == SELECT_FILE) {
            Uri selectedImageUri = data.getData();
            imgStartTravelling.setImageURI(selectedImageUri);
            uriclient = data.getData();
            Log.d("AadharImageUri", uriclient.toString());
            waypathClient = getFilePath(this, uriclient);
            Log.d("AadharmageUri", waypathClient);

        }

    }

    private File savebitmap(Bitmap bmp) {

       /* String extStorageDirectory;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            extStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
        } else {
            extStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
        }*/
        String extStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();/* Environment.getExternalStorageDirectory().getPath();*/
        FileOutputStream outStream = null;
        // String temp = null;

        final int random = new Random().nextInt(6000000) + 20;
        File file = new File(extStorageDirectory, random + ".png");
        waypath = getFilePath(this, Uri.fromFile(file));
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, random + ".png");
            waypath = getFilePath(this, Uri.fromFile(file));
        }
        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 70, outStream);
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