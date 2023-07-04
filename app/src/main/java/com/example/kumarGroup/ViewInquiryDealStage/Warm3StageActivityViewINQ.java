package com.example.kumarGroup.ViewInquiryDealStage;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.deal_threestage_model;
import com.example.kumarGroup.model_msg;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Warm3StageActivityViewINQ extends AppCompatActivity {

    String Name,autoid,cat_id,sname,mobileNo,Vemp,message,Whatsappnumber;
    String FollowUptype;
    String[] FollowUpType_list={"Select Activities","Product Demo", "Event Invitation","Happy Customer Visit"};
    Spinner spnFollowUpTypeIG;

    Uri uriPhoto;
    ImageView booking_imge;
    String currentPhotoPath;
    TextView booking_txt;
    Button stage3submit;

    SharedPreferences sp1;
    String emp="";

    String waypath;
//    ProgressDialog progressDialog;

    ProgressDialog pro;

    List<String> modelname_list = new ArrayList<>();

    String[] Products_List = {"Select Product", "New Tractor","Old Tractor","Implement"};
    List<String> ModelName = new ArrayList<>();
    List<String> ModelID = new ArrayList<>();
    String dealType,ProductName1,strModel,model_name,model_id,String_modelget;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warm3_stage_view_inq);

        getSupportActionBar().setTitle("Next Activity Plan Photos View Inquiry");

        spnFollowUpTypeIG=findViewById(R.id.spnFollowUpTypeIG);

        cat_id=getIntent().getStringExtra("cat_id");
        sname=getIntent().getStringExtra("sname");
        Vemp=getIntent().getStringExtra("vemp");
        mobileNo=getIntent().getStringExtra("mobo");
        Whatsappnumber=getIntent().getStringExtra("Whatsappnumber");

        autoid=getIntent().getStringExtra("id");

        SharedPreferences sharedPreferencesS = getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
        emp = sharedPreferencesS.getString("Slected_EMPID","");

        booking_imge = findViewById(R.id.booking_imge);
        booking_txt = findViewById(R.id.booking_txt);
        stage3submit = findViewById(R.id.stage3submit);


        pro = new ProgressDialog(this);

        ArrayAdapter adapterFollowUp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, FollowUpType_list);
        spnFollowUpTypeIG.setAdapter(adapterFollowUp);

        spnFollowUpTypeIG.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( FollowUpType_list[position]=="Select FollowUp"){
                    FollowUptype = "";
                }
                else{
                    FollowUptype = FollowUpType_list[position];
                }
                //  TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        booking_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 4);
//                dispatchTakePictureIntent(Warm3StageActivityViewINQ.this);
//                try {
//                    currentPhotoPath=  dispatchTakePictureIntent1(Warm3StageActivityViewINQ.this).getAbsolutePath();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });

        stage3submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(FollowUptype)){
                    Utils.showErrorToast(Warm3StageActivityViewINQ.this,"Please select Activities");
                    return;
                }

                MultipartBody.Part imagePartPhoto_book2 = null;
                MultipartBody.Part imagePartPhoto_book3 = null;
                MultipartBody.Part imagePartPhoto_book4 = null;

                if (waypath == null) {
                    Utils.showErrorToast(Warm3StageActivityViewINQ.this, "Please upload Photo");
                    return;
                }
                else {
//                    Product Demo", "Event Invitation","Happy Customer Visit"
                    pro.show();
                    pro.setCancelable(false);
                    pro.setMessage("Please wait ..");

                    if ("Product Demo".equals(FollowUptype)){
                        File file1 = new File(waypath);
                        final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        imagePartPhoto_book2 = MultipartBody.Part.createFormData("image2", file1.getName(), requestBody1);
                    }
                    else if ("Event Invitation".equals(FollowUptype)){

                        File file1 = new File(waypath);
                        final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        imagePartPhoto_book3 = MultipartBody.Part.createFormData("image3", file1.getName(), requestBody1);
                    }
                    else if ("Happy Customer Visit".equals(FollowUptype)){

                        File file1 = new File(waypath);
                        final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        imagePartPhoto_book4 = MultipartBody.Part.createFormData("image4", file1.getName(), requestBody1);
                    }
                    else {
                        pro.dismiss();
                        Utils.showErrorToast(Warm3StageActivityViewINQ.this,"Something went wrong");
                        return;
                    }

                    WebService.getClient().deal_3stage_web(
                            RequestBody.create(MediaType.parse("text/plain"), autoid),
                            RequestBody.create(MediaType.parse("text/plain"), FollowUptype),
                            RequestBody.create(MediaType.parse("text/plain"), sname),

                            imagePartPhoto_book2,
                            imagePartPhoto_book3,
                            imagePartPhoto_book4
                    ).enqueue(new Callback<deal_threestage_model>() {
                        @Override
                        public void onResponse(Call<deal_threestage_model> call, Response<deal_threestage_model> response) {
                            pro.dismiss();
                            WhatsappMessage1();

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Do something after 5s = 2000ms
                                    DealstageRecyclerActivityViewINQ.deal_nextactivityplanInquiry_flag_sendimage = false;
                                    Toast.makeText(Warm3StageActivityViewINQ.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Warm3StageActivityViewINQ.this, DealViewMainActivity.class));
                                    finish();
                                }
                            }, 2000);



                        }

                        @Override
                        public void onFailure(Call<deal_threestage_model> call, Throwable t) {
                            pro.dismiss();
                            Utils.showErrorToast(Warm3StageActivityViewINQ.this,t.getMessage());
                        }


                    });

                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 4) {
            Bundle bundle = data.getExtras();
            final Bitmap photo = (Bitmap) bundle.get("data");
            booking_imge.setImageBitmap(photo);
            savebitmap(photo);

        }

    }

    private File savebitmap(Bitmap bmp) {

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

    private void WhatsappMessage() {

        if (DealstageRecyclerActivityViewINQ1.deal_nextactivityplanInquiry_flag_sendimage){
            Log.d("TAG", "onResponse: Check_whatsapp_Message-2");
            dealType="Next Activity Plan(WARM)";

            DealstageRecyclerActivityViewINQ1.deal_nextactivityplanInquiry_flag_sendimage = false;

            WebService.getClient().dealstage_msg(dealType).enqueue(new Callback<model_msg>() {
                @Override
                public void onResponse(Call<model_msg> call, Response<model_msg> response) {
                    pro.dismiss();


                    Log.e("respose",response.body().toString());
                    if (response.body().getData().size()==0){
                        WhatsappMessage1();
//                        Toast.makeText(Warm3StageActivityViewINQ.this, "No Data Found !", Toast.LENGTH_SHORT).show();
                    }else {

                        String message = "प्रिय किसान मित्र।\n" +
                                "\n" +
                                "     आपके बहुमूल्य समय के लिए बहुत-बहुत धन्यवाद, हम भविष्य में आपको संतोषजनक सेवाएं प्रदान करेंगे, और आपके बहुमूल्य सुझावों की प्रतीक्षा रहेगी।\n" +
                                "\n" +
                                "नमस्ते...\n" +
                                "कुमार ऑटोमोबाइल्स (सोनालिका ट्रैक्टर शोरूम)\n" +
                                "\n" +
                                "अधिक जानकारी के लिए संपर्क करें।\n" +
                                "सेल्स MO:- 7500567770\n" +
                                "सर्विस MO:-7505786792";

                        for (int i=0;i<response.body().getData().size();i++){
                            if (response.body().getData().get(i).getVideo_link1() != null) {
                                message += "\n" + response.body().getData().get(i).getVideo_link1()+"\n";
                            }
                            if (response.body().getData().get(i).getVideo_link2() != null) {
                                message += "\n" + response.body().getData().get(i).getVideo_link2()+"\n";
                            }
                            if (response.body().getData().get(i).getVideo_link3() != null) {
                                message += "\n" + response.body().getData().get(i).getVideo_link3()+"\n";
                            }
                            if (response.body().getData().get(i).getVideo_link4() != null) {
                                message += "\n" + response.body().getData().get(i).getVideo_link4()+"\n";
                            }
                            if (response.body().getData().get(i).getVideo_link5() != null) {
                                message += "\n" + response.body().getData().get(i).getVideo_link5()+"\n";
                            }
                        }

//                        if (response.body().getData().get(0).getVideo_link1() != null) {
//                            message += "\n" + response.body().getData().get(0).getVideo_link1()+"\n";
//                        }
//                        if (response.body().getData().get(0).getVideo_link2() != null) {
//                            message += "\n" + response.body().getData().get(0).getVideo_link2()+"\n";
//                        }
//                        if (response.body().getData().get(0).getVideo_link3() != null) {
//                            message += "\n" + response.body().getData().get(0).getVideo_link3()+"\n";
//                        }

                        Log.d("TAG", "onResponse: Whatsapp_message" + message);


                        startActivity(
                                new Intent(Intent.ACTION_VIEW,
                                        Uri.parse(
                                                String.format("https://api.whatsapp.com/send?phone=%s&text=%s", "+91" + Whatsappnumber, message)
                                        )
                                )
                        );
                    }

                }

                @Override
                public void onFailure(Call<model_msg> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(Warm3StageActivityViewINQ.this,t.getMessage());

                }
            });

        }

    }


    private void WhatsappMessage1() {

        message = "प्रिय किसान मित्र।\n" +
                "\n" +
                "     आपके बहुमूल्य समय के लिए बहुत-बहुत धन्यवाद, हम भविष्य में आपको संतोषजनक सेवाएं प्रदान करेंगे, और आपके बहुमूल्य सुझावों की प्रतीक्षा रहेगी।\n" +
                "\n" +
                "नमस्ते...\n" +
                "कुमार ऑटोमोबाइल्स (सोनालिका ट्रैक्टर शोरूम)\n" +
                "\n" +
                "अधिक जानकारी के लिए संपर्क करें।\n" +
                "सेल्स MO:- 7500567770\n" +
                "सर्विस MO:-7505786792";


        Log.d("TAG", "WhatsappMessage: Check_whatsapp_Message-6");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+91"+Whatsappnumber + "&text=" + message));
        startActivity(intent);

    }
}