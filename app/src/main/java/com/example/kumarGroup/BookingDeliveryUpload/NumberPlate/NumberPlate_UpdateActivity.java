package com.example.kumarGroup.BookingDeliveryUpload.NumberPlate;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class NumberPlate_UpdateActivity extends AppCompatActivity {

    Spinner spn_number_plate_fitment;
    String number_plate_fitment,rc_book_update,rc_update_customer;
    List<String> Y_N = Arrays.asList(new String[]{"Select Option","Yes", "No"});
    LinearLayout linner_number_plate;
    TextView txt_number_plate;
    Uri number_plate;
    String number_plate1,imagepath ;
    ImageView img_number_plate;
    Button btn_Passing_Submit;
    EditText edt_register_number;
    String id,register_no;
    ProgressDialog pro;
     String path;

    private File imageLogoFilePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_plate_update);

        getSupportActionBar().setTitle("Number Plate Update");

        pro = new ProgressDialog(this);
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");

        linner_number_plate = findViewById(R.id.linner_number_plate);
        spn_number_plate_fitment = findViewById(R.id.spn_number_plate_fitment);
        txt_number_plate = findViewById(R.id.txt_number_plate);
        img_number_plate = findViewById(R.id.img_number_plate);
        btn_Passing_Submit = findViewById(R.id.btn_Passing_Submit);


        id = getIntent().getStringExtra("idBookingUpload");
        register_no = getIntent().getStringExtra("register_no");


//        edt_register_number.setText(register_no);
//        edt_register_number.setFocusable(false);

        ArrayAdapter adapterStage2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Y_N);
        spn_number_plate_fitment.setAdapter(adapterStage2);

        spn_number_plate_fitment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                number_plate_fitment = Y_N.get(i);
                if (number_plate_fitment.equals("Yes")){
                    linner_number_plate.setVisibility(View.VISIBLE);
                }else {
                    linner_number_plate.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txt_number_plate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 1);
*/
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);

                // pass the constant to compare it
                // with the returned requestCode
                startActivityForResult(Intent.createChooser(i, "Select Picture"), 1);

/*
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);*/
            }
        });


        btn_Passing_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pro.show();

                MultipartBody.Part partLogo = null;

       /*     if(imageLogoFilePath != null) {
                try {
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                    String imageFileName = "IMG_" + timeStamp;
                    File storageDir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    File image = File.createTempFile( imageFileName  prefix , ".jpg" suffix , storageDir  directory );


                    Log.e("imageuser",String.valueOf(image));//*storage/emulated/0/Android/data/com.lobalmart.android/files/Pictures/IMG_20221003_1709335424031453849406654.jpg
                    ExifRemover.removeExifMetadata(imageLogoFilePath, image);

                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), image);
                    MultipartBody.Part imageLogo = MultipartBody.Part.createFormData("image", "profile_pic_" + SharedPreference.getString( Constants.UserId ) + ".jpg", requestBody);
                    partLogo = imageLogo;
                }catch (Exception e){
                    e.printStackTrace();
                }

            }else{
                Log.e("partLogo123333", String.valueOf(partLogo));

            }*/

                MultipartBody.Part img_number_platem = null;


                if(number_plate1 != null){
                    File file1 = new File(number_plate1);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    img_number_platem = MultipartBody.Part.createFormData("image1",
                            file1.getName(), requestBody1);
                }

                if (register_no==null){
                    register_no="";
                }


                WebService.getClient().NumberPlateUpdate(RequestBody.create(MediaType.parse("text/plain"), id),
                        RequestBody.create(MediaType.parse("text/plain"), number_plate_fitment),
                        RequestBody.create(MediaType.parse("text/plain"), register_no),
                        img_number_platem,
                        RequestBody.create(MediaType.parse("text/plain"), "5")
                ).enqueue(new Callback<RcbookUpdate>() {
                    @Override
                    public void onResponse(Call<RcbookUpdate> call, Response<RcbookUpdate> response) {
                        pro.dismiss();
                        if (response.body().getMsg().equals("Record Update Succesfully")){
                            Toast.makeText(NumberPlate_UpdateActivity.this, "Record Update Succesfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(NumberPlate_UpdateActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
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

       /* try {
            if (resultCode == RESULT_OK) {
                if (requestCode == 1) {
                    Uri selectedImageUri = data.getData();
                    // Get the path from the Uri
                     path = getPath(NumberPlate_UpdateActivity.this,selectedImageUri);
                    FileOutputStream outStream = null;
                    if (path != null) {
                        File f = new File(path);
                        selectedImageUri = Uri.fromFile(f);
//                        File compressedImageFile = (File) Compressor.compress(NumberPlate_UpdateActivity.this, f);

                        try {
                            outStream = new FileOutputStream(f);
//            Bitmap scaledBitmap = scaleDown(bmp, 65, true);
//            bmp.compress(Bitmap.CompressFormat.PNG, 30, outStream);
//            scaledBitmap.compress(Bitmap.CompressFormat.PNG, 65, outStream);
                            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                            Bitmap bitmap = BitmapFactory.decodeFile(path,bmOptions);
//                            bitmap = Bitmap.createScaledBitmap(bitmap,img_number_plate.getWidth(),img_number_plate.getHeight(),true);

                            bitmap.compress(Bitmap.CompressFormat.PNG, 70, outStream);
                            img_number_plate.setImageBitmap(bitmap);
                            outStream.flush();
                            outStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    String[] name = path.split("/");
                    txt_number_plate.setText(name[name.length - 1]);
                    // Set the image in ImageView
//                    img_number_plate.setImageURI(selectedImageUri);
                }
            }
        } catch (Exception e) {
            Log.e("FileSelectorActivity", "File select error", e);
        }*/
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
                    img_number_plate.setImageURI(selectedImageUri);

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