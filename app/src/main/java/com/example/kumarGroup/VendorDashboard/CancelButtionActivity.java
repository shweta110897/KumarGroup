package com.example.kumarGroup.VendorDashboard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.cancel_buttion_model;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelButtionActivity extends AppCompatActivity {
    TextView txt_invoice_ak;
    Uri uri_CD_UploadPhoto;
    String waypath;
    ImageView img_invoice;
    Button btn_Submit_thirdPoint;
    ProgressDialog pro;
    EditText edt_sell_date;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_buttion);

        txt_invoice_ak = findViewById(R.id.txt_invoice_ak);
        img_invoice = findViewById(R.id.img_invoice);
        btn_Submit_thirdPoint = findViewById(R.id.btn_Submit_thirdPoint);
        edt_sell_date = findViewById(R.id.edt_sell_date);

        pro= new ProgressDialog(CancelButtionActivity.this);

        id = getIntent().getStringExtra("id");

        btn_Submit_thirdPoint.setVisibility(View.GONE);
        txt_invoice_ak.setOnClickListener(new View.OnClickListener() {
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

                pro.show();
                pro.setMessage("Please wait..");
                pro.setCancelable(false);

                MultipartBody.Part UploadPhoto = null;

                if(waypath != null){
                    File file15 = new File(waypath);
                    final RequestBody requestBody15 = RequestBody.create(MediaType.parse("image/*"), file15);
                    UploadPhoto = MultipartBody.Part.createFormData("images",
                            file15.getName(), requestBody15);
                }



                WebService.getClient().get_cancel_buttion_web(
                        RequestBody.create(MediaType.parse("text/plain"),id),
                        RequestBody.create(MediaType.parse("text/plain"), edt_sell_date.getText().toString()),
                        UploadPhoto

                ).enqueue(new Callback<cancel_buttion_model>() {
                    @Override
                    public void onResponse(@NotNull Call<cancel_buttion_model> call,
                                           @NotNull Response<cancel_buttion_model> response) {

                        pro.dismiss();
                        assert response.body() != null;
                        Toast.makeText(getApplicationContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getApplicationContext(), RequestVendorMainActivity.class);
                        startActivity(i);
                        finish();

                    }

                    @Override
                    public void onFailure(@NotNull Call<cancel_buttion_model> call, @NotNull Throwable t) {
                        pro.dismiss();
                        Toast.makeText(getApplicationContext(), ""+t.getCause(), Toast.LENGTH_SHORT).show();

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
                    txt_invoice_ak.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_invoice.setImageURI(selectedImageUri);

                    btn_Submit_thirdPoint.setVisibility(View.VISIBLE);
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