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

import com.example.kumarGroup.EndTravelingModel;
import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
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

public class EndTravelingActivity extends AppCompatActivity {

    ImageView imgEndTravelling;
    TextView txtEndTravelling;
    EditText edtEndTravellingKM;
    Button btnEndTravelling;


    Integer REQUEST_CAMERA = 4, SELECT_FILE = 0;

    ProgressDialog progressDialog;
    String emp, phase;
    SharedPreferences sp,sp1;

    Uri uriclient;
    String waypathClient, waypath;

    String idCheckInTravelling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_traveling);

        imgEndTravelling=findViewById(R.id.imgEndTravelling);
        txtEndTravelling=findViewById(R.id.txtEndTravelling);
        edtEndTravellingKM=findViewById(R.id.edtEndTravellingKM);
        btnEndTravelling=findViewById(R.id.btnEndTravelling);


        sp = getSharedPreferences("StartTravelling", MODE_PRIVATE);

        idCheckInTravelling = sp.getString("idCheckInStartTravelling","");

        //  Toast.makeText(this, "id"+idCheckInTravelling, Toast.LENGTH_SHORT).show();


        txtEndTravelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });


        btnEndTravelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(EndTravelingActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                if (edtEndTravellingKM.getText().toString().equals("")) {
                    //  Utils.showErrorToast(FoLogin.this, "Please Enter Password");
                    edtEndTravellingKM.setError("Please Enter Km");
                }

                if ( waypath == null) {
                    Utils.showErrorToast(EndTravelingActivity.this, "Please upload Selfie Photo");
                }
                else {
                    Log.d("upload image", "onClick: " + waypath);
                    File file1 = new File(waypath);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    MultipartBody.Part imagePartEndTraveling = MultipartBody.Part.createFormData("image3", file1.getName(), requestBody1);


                    Log.d("TAG", "peralog "+idCheckInTravelling +" biju "+edtEndTravellingKM.getText().toString()+" img "+imagePartEndTraveling);
                    WebService.getClient().getEndTraveling(
                            RequestBody.create(MediaType.parse("text/plain"), idCheckInTravelling),
                            RequestBody.create(MediaType.parse("text/plain"), edtEndTravellingKM.getText().toString()),
                            imagePartEndTraveling
                    ).enqueue(new Callback<EndTravelingModel>() {
                        @Override
                        public void onResponse(@NotNull Call<EndTravelingModel> call,
                                               @NotNull Response<EndTravelingModel> response) {
                            progressDialog.dismiss();

                            assert response.body() != null;
                            Log.d("Response", response.body().toString());
                            Toast.makeText(EndTravelingActivity.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();
                            Intent i = new Intent(EndTravelingActivity.this, FoDashbord.class);

                            sp = getSharedPreferences("StartTravelling", MODE_PRIVATE);
                            sp.edit().putString("idPhaseTravelling", "2").apply();

                          /*  sp1 = getSharedPreferences("StartTravelling", MODE_PRIVATE);

                          //  Phase =  sp1.getString("idPhaseTravelling", "");
                            sp1.edit().putString("idPhaseTravelling", "2").apply();*/
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(@NotNull Call<EndTravelingModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(EndTravelingActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });


    }

    private void SelectImage()
    {
        final CharSequence[] items = {"Camera", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(EndTravelingActivity.this);
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
            imgEndTravelling.setImageBitmap(photo);
            savebitmap(photo);

        } else if (requestCode == SELECT_FILE) {
            Uri selectedImageUri = data.getData();
            imgEndTravelling.setImageURI(selectedImageUri);
            uriclient = data.getData();
            Log.d("AadharImageUri", uriclient.toString());
            waypathClient = getFilePath(this, uriclient);
            Log.d("AadharmageUri", waypathClient);

        }

    }


    private File savebitmap(Bitmap bmp) {
//        String extStorageDirectory;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//            extStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
//        } else {
//            extStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
//        }
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