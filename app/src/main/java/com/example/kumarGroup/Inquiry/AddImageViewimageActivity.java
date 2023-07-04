package com.example.kumarGroup.Inquiry;

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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.addimage_attachfile_model;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddImageViewimageActivity extends AppCompatActivity {
    String  waypath;
    ImageView mImageView;
    Button addimage,viewimage,submit_image;
    String sid;
    Uri uriPhoto;
    String waypathPhoto;
    ProgressDialog pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image_viewimage);

        mImageView = findViewById(R.id.mImageView);
        addimage = findViewById(R.id.addimage);
        viewimage = findViewById(R.id.viewimage);
        submit_image = findViewById(R.id.submit_image);
        pro = new ProgressDialog(AddImageViewimageActivity.this);

        sid = getIntent().getStringExtra("sname");

        submit_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(waypathPhoto==null){
                    Utils.showErrorToast(AddImageViewimageActivity.this,"Please upload Photo");
                }
                else {

                    pro.show();
                    pro.setCancelable(false);
                    pro.setMessage("Please wait ...");

                    Log.d("upload image", "onClick: " + waypath);

                    File file1 = new File(waypathPhoto);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    MultipartBody.Part imagePartPhoto = MultipartBody.Part.createFormData("image1", file1.getName(), requestBody1);

                    WebService.getClient().add_image_filleattach_web(
                            RequestBody.create(MediaType.parse("text/plain"), sid),
                            imagePartPhoto

                    ).enqueue(new Callback<addimage_attachfile_model>() {
                        @Override
                        public void onResponse(Call<addimage_attachfile_model> call, Response<addimage_attachfile_model> response) {
                            pro.dismiss();
//                            Toast.makeText(AddImageViewimageActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            addimage.setText("Add Image");
                            mImageView.setImageURI(null);
                            submit_image.setVisibility(View.GONE);
                            startActivity(new Intent(AddImageViewimageActivity.this, ViewImageActivity.class)
                            .putExtra("sname",sid));

                        }

                        @Override
                        public void onFailure(Call<addimage_attachfile_model> call, Throwable t) {
                            pro.dismiss();
                            Toast.makeText(AddImageViewimageActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        viewimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddImageViewimageActivity.this, ViewImageActivity.class)
                        .putExtra("sname",sid));
            }
        });

        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 2);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2)
        {
            if(resultCode==RESULT_OK)
            {
                if(data!=null)
                {
                    uriPhoto=data.getData();
                    Log.d("Pan Image Uri",uriPhoto.toString());
                    waypathPhoto = getFilePath(this,uriPhoto);
                    Log.d("Pan Image Uri",waypathPhoto);
                    String[] name = waypathPhoto.split("/");
                    addimage.setText(name[name.length-1]);

                    Uri selectedImageUri = data.getData();
                    mImageView.setImageURI(selectedImageUri);
                    submit_image.setVisibility(View.VISIBLE);

                }
            }
        }
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