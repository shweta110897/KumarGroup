package com.example.kumarGroup.DealstageAK;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.Deal_model;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.getModelData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DealfirstMeetingActivity extends AppCompatActivity {

    EditText edtBookAmountVI,edtSellLostVI, edtdropen;

    Spinner spnBookingVI, spnDeliveryVI, spnSellLostVI, spndropen,
            loanclearVI,dpclearVI,oldrcbookVI,spinner_modelname;

    Button btnSubmitVI;

    String sellLostVI;
    String[] sellLost1_VI = {"Sell Lost", "Yes", "No"};

    String BookingTypeVI;
    String[] BookingType1_VI = {"Booking", "Yes", "No"};

    String DeliveryVI;
    String[] Delivery1_VI = {"Delivery", "Yes", "No"};

    String drop;
    String[] drop_VI = {"Drop", "Yes", "No"};


    String loan="";
    String[] loan_VI = {"Loan Clear", "Yes", "No"};


    String dpclear="";
    String[] dpclear_VI = {"D.P clear", "Yes", "No"};


    String oldrcbook="";
    String[] oldrcbook_VI = {"Old RC Book & Vehicle", "Yes", "No"};

    SharedPreferences sp1,sharedPreferences;
    String emp,toastFlag;


    String modelname="", Category, cat_id, sname, id, MobileNo, Vemp,login_emp;


    ProgressDialog progressDialog;
    String chcekedit = "",NextIDD,phase;
    ImageView booking_imge, delivery_image;
    TextView booking_txt, delivery_tx,tvBooking;
    Uri uriPhoto;

    String waypathPhoto_book, waypathPhoto_deli,mobile;
    boolean booking_falg = false, delivery_falg = false;
    LinearLayout main_booking, main_delivery;
    List<String> modellist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealfirst_meeting);


        sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp1.getString("emp_id", "");


        sharedPreferences = getSharedPreferences("NextId1", MODE_PRIVATE);
        NextIDD = sharedPreferences.getString("NextId1","");
        phase = sharedPreferences.getString("phase","");

        getSupportActionBar().setTitle("Deal");

        cat_id = getIntent().getStringExtra("cat_id");
        sname = getIntent().getStringExtra("sname");
        Vemp = getIntent().getStringExtra("vemp");
        mobile = getIntent().getStringExtra("mobo");
        login_emp = getIntent().getStringExtra("login_emp");

        progressDialog = new ProgressDialog(DealfirstMeetingActivity.this);

        edtBookAmountVI = findViewById(R.id.edtBookAmountVI);

        edtSellLostVI = findViewById(R.id.edtSellLostVI);
        edtdropen = findViewById(R.id.edtdropen);


        spnBookingVI = findViewById(R.id.spnBookingVI);
        spnDeliveryVI = findViewById(R.id.spnDeliveryVI);
        spnSellLostVI = findViewById(R.id.spnSellLostVI);
        spndropen = findViewById(R.id.spndropen);
        spinner_modelname = findViewById(R.id.spinner_modelname);
        loanclearVI = findViewById(R.id.loanclearVI);
        dpclearVI = findViewById(R.id.dpclearVI);
        oldrcbookVI = findViewById(R.id.oldrcbookVI);
        tvBooking = findViewById(R.id.tvBooking);

        btnSubmitVI = findViewById(R.id.btnSubmitVI);
        booking_imge = findViewById(R.id.booking_imge);
        delivery_image = findViewById(R.id.delivery_image);
        booking_txt = findViewById(R.id.booking_txt);
        delivery_tx = findViewById(R.id.delivery_tx);
        main_delivery = findViewById(R.id.main_delivery);
        main_booking = findViewById(R.id.main_booking);

        /*if (NextIDD!=null  && phase.equals("6")){
            spnDeliveryVI.setEnabled(true);
            spnDeliveryVI.setClickable(true);
        }else{
            spnDeliveryVI.setEnabled(false);
            spnDeliveryVI.setClickable(false);
        }
*/

     /*   tvBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(NextIDD.equals(""))
                {
                    Intent i = new Intent(DealfirstMeetingActivity.this, ProductDetailsDealstageActivity.class);
                    startActivity(i);
                }
                else {
                    // Toast.makeText(BookingDeliveryMainActivity.this, "Else Condition Call"+NextIDD, Toast.LENGTH_SHORT).show();

                    WebService.getClient().getPhaseAddBooking(NextIDD).enqueue(new Callback<PhaseAddBookingModel>() {
                        @Override
                        public void onResponse(@NotNull Call<PhaseAddBookingModel> call,
                                               @NotNull Response<PhaseAddBookingModel> response) {

                            if (response.body().getPhase() == null) {
                                //  if (response.body().getPhase().equals("0")) {
                                //Intent i = new Intent(BookingDeliveryMainActivity.this, AddCustomerDetailsActivity.class);
                                Intent i = new Intent(DealfirstMeetingActivity.this, ProductDetailsDealstageActivity.class);
                                i.putExtra("phase",response.body().getPhase());
                                startActivity(i);
                            } else if (response.body().getPhase().equals("1")) {
                                //Intent i = new Intent(BookingDeliveryMainActivity.this, ProductDetailsDealstageActivity.class);
                                Intent i = new Intent(DealfirstMeetingActivity.this, PriceDetailsAddActivity.class);
                                i.putExtra("phase",response.body().getPhase());
                                startActivity(i);
                            } else if (response.body().getPhase().equals("2")) {
                                //Intent i = new Intent(BookingDeliveryMainActivity.this, PriceDetailsAddActivity.class);
                                Intent i = new Intent(DealfirstMeetingActivity.this, AddCustomerDetailsActivity.class);
                                i.putExtra("phase",response.body().getPhase());
                                startActivity(i);
                            } else if (response.body().getPhase().equals("3")) {
                                // Intent i = new Intent(BookingDeliveryMainActivity.this, RtoDetailsActivity.class);
                                Intent i = new Intent(DealfirstMeetingActivity.this, DownPaymentActivity.class);
                                i.putExtra("phase",response.body().getPhase());
                                startActivity(i);
                            } else if (response.body().getPhase().equals("4")) {
                                // Intent i = new Intent(BookingDeliveryMainActivity.this, DownPaymentActivity.class);
                                Intent i = new Intent(DealfirstMeetingActivity.this, RtoDetailsActivity.class);
                                i.putExtra("phase",response.body().getPhase());
                                startActivity(i);
                            } else if (response.body().getPhase().equals("5")) {
                                Intent i = new Intent(DealfirstMeetingActivity.this, CustomerSkimActivity.class);
                                i.putExtra("phase",response.body().getPhase());
                                startActivity(i);
                            }
                            else  if(response.body().getPhase().equals("6")){
                                // Intent i = new Intent(BookingDeliveryMainActivity.this, AddCustomerDetailsActivity.class);
                                Intent i = new Intent(DealfirstMeetingActivity.this, ProductDetailsDealstageActivity.class);
                                i.putExtra("phase",response.body().getPhase());
                                startActivity(i);
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<PhaseAddBookingModel> call, @NotNull Throwable t) {

                        }
                    });
                }
            }
        });
*/


        booking_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 2);
            }
        });

        delivery_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 2);
            }
        });

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, BookingType1_VI);
        spnBookingVI.setAdapter(adapter);

        spnBookingVI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BookingTypeVI = BookingType1_VI[position];
                if (BookingType1_VI[position] == "Yes") {
                    //BookingType1 = "Yes";
                    edtBookAmountVI.setVisibility(View.VISIBLE);
                    main_booking.setVisibility(View.VISIBLE);

                    chcekedit = "edtBookAmountVI";
                    booking_falg = true;
                    spnDeliveryVI.setEnabled(false);
                    spnSellLostVI.setEnabled(false);
                    spndropen.setEnabled(false);
                }
               /* else if(BookingType1_VI[position] == "Booking"){

                    spnDeliveryVI.setEnabled(true);
                    spnSellLostVI.setEnabled(true);
                    spndropen.setEnabled(true);
                }*/
                else {
                    //BookingType1 = "1";
                    edtBookAmountVI.setVisibility(View.GONE);
                    main_booking.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter deliveryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Delivery1_VI);
        spnDeliveryVI.setAdapter(deliveryAdapter);

        spnDeliveryVI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DeliveryVI = Delivery1_VI[position];
                if (Delivery1_VI[position] == "Yes") {
                    spinner_modelname.setVisibility(View.VISIBLE);
                    main_delivery.setVisibility(View.VISIBLE);

                    loanclearVI.setVisibility(View.VISIBLE);
                    dpclearVI.setVisibility(View.VISIBLE);
                    oldrcbookVI.setVisibility(View.VISIBLE);

                    chcekedit = "edtModelNameVI";
                    toastFlag = "deliveryflag";
                    delivery_falg = true;

                    spnBookingVI.setEnabled(false);
                    spnSellLostVI.setEnabled(false);
                    spndropen.setEnabled(false);

                    ModelNameGetMethod();

                }
                /*else if (Delivery1_VI[position] == "Delivery"){
                    spnBookingVI.setEnabled(true);
                    spnSellLostVI.setEnabled(true);
                    spndropen.setEnabled(true);
                }*/
                else {
                    spinner_modelname.setVisibility(View.GONE);
                    main_delivery.setVisibility(View.GONE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        loanclearVI,dpclearVI,oldrcbookVI;

        ArrayAdapter loanclearVIAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, loan_VI);
        loanclearVI.setAdapter(loanclearVIAdapter);

        loanclearVI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loan = loan_VI[position];

                if ("Loan Clear".equals(loan)){
                    loan = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter dpclearVIAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dpclear_VI);
        dpclearVI.setAdapter(dpclearVIAdapter);

        dpclearVI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dpclear = dpclear_VI[position];

                if (dpclear.equals("D.P clear")){
                    dpclear = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter oldrcbookVIAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, oldrcbook_VI);
        oldrcbookVI.setAdapter(oldrcbookVIAdapter);

        oldrcbookVI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                oldrcbook = oldrcbook_VI[position];

                if (oldrcbook.equals("Old RC Book & Vehicle")){
                    oldrcbook = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapterSellLost = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sellLost1_VI);
        spnSellLostVI.setAdapter(adapterSellLost);

        spnSellLostVI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sellLostVI = sellLost1_VI[position];
                if (sellLost1_VI[position] == "Yes") {
                    edtSellLostVI.setVisibility(View.VISIBLE);
                    chcekedit = "edtSellLostVI";

                    spnBookingVI.setEnabled(false);
                    spnDeliveryVI.setEnabled(false);
                    spndropen.setEnabled(false);

                }
                /*
                else if(sellLost1_VI[position] == "Sell Lost"){

                    spnBookingVI.setEnabled(true);
                    spnDeliveryVI.setEnabled(true);
                    spndropen.setEnabled(true);
                }*/
                else {

                    edtSellLostVI.setVisibility(View.GONE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter spndropen_array = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, drop_VI);
        spndropen.setAdapter(spndropen_array);

        spndropen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                drop = drop_VI[position];
                if (drop_VI[position] == "Yes") {
                    edtdropen.setVisibility(View.VISIBLE);
                    chcekedit = "edtdropen";

                    spnBookingVI.setEnabled(false);
                    spnDeliveryVI.setEnabled(false);
                    spnSellLostVI.setEnabled(false);

                }
                /*else if (drop_VI[position] == "Drop"){
                    spnBookingVI.setEnabled(true);
                    spnDeliveryVI.setEnabled(true);
                    spnSellLostVI.setEnabled(true);
                }*/
                else {
                    edtdropen.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSubmitVI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bktamt = edtBookAmountVI.getText().toString().trim();
                String selllost = edtSellLostVI.getText().toString().trim();
                String dropdes = edtdropen.getText().toString().trim();

                /*if (chcekedit.equals("edtBookAmountVI") || chcekedit.equals("edtModelNameVI")
                    || chcekedit.equals("edtSellLostVI") || chcekedit.equals("edtdropen")){
                */
                if (chcekedit.equals("edtBookAmountVI")) {
                    if (TextUtils.isEmpty(bktamt)) {
                        Utils.showErrorToast(DealfirstMeetingActivity.this, "Please Select Booking in Amount ");
                        return;
                    }
                }
                else if (chcekedit.equals("edtModelNameVI")) {
                    if (TextUtils.isEmpty(modelname)) {
                        Utils.showErrorToast(DealfirstMeetingActivity.this, "Please Select Delivery in Model name ");
                        return;
                    }
                } else if (chcekedit.equals("edtSellLostVI")) {
                    if (TextUtils.isEmpty(selllost)) {
                        Utils.showErrorToast(DealfirstMeetingActivity.this, "Please Select selllost in details ");
                        return;
                    }
                } else if (chcekedit.equals("edtdropen")) {
                    if (TextUtils.isEmpty(dropdes)) {
                        Utils.showErrorToast(DealfirstMeetingActivity.this, "Please Select dropdes in Description ");
                        return;
                    }
                } else {
                    Utils.showErrorToast(DealfirstMeetingActivity.this, "Please Select anyone type");
                    return;
                }


                if ("deliveryflag".equals(toastFlag)) {
//                loan dpclear oldrcbook
                    if (TextUtils.isEmpty(loan)) {
                        Utils.showErrorToast(DealfirstMeetingActivity.this, "Please select Loan Clear");
                        return;
                    }

                    if (TextUtils.isEmpty(dpclear)) {
                        Utils.showErrorToast(DealfirstMeetingActivity.this, "Please select D.P clear");
                        return;
                    }

                    if (TextUtils.isEmpty(oldrcbook)) {
                        Utils.showErrorToast(DealfirstMeetingActivity.this, "Please select Old RC Book & Vehicle");
                        return;
                    }
                }

                MultipartBody.Part imagePartPhoto_book = null;
                MultipartBody.Part imagePartPhoto_deli = null;

                if (booking_falg) {
                    if (waypathPhoto_book == null) {
                        Utils.showErrorToast(DealfirstMeetingActivity.this, "Please upload Photo");
                        return;
                    }
                    else {
                        File file1 = new File(waypathPhoto_book);
                        final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        imagePartPhoto_book = MultipartBody.Part.createFormData("image1", file1.getName(), requestBody1);

                    }
                }

                if (delivery_falg) {
                    if (waypathPhoto_deli == null) {
                        Utils.showErrorToast(DealfirstMeetingActivity.this, "Please upload Photo");
                        return;
                    }
                    else {

                        File file1 = new File(waypathPhoto_deli);

                       /* try {
                            Bitmap bitmap = BitmapFactory.decodeFile (file1.getPath());
                            bitmap.compress (Bitmap.CompressFormat.JPEG, 50, new FileOutputStream(file1));
                        }
                        catch (Throwable t) {
                            Log.e("ERROR", "Error compressing file." + t.toString ());
                            t.printStackTrace();
                        }*/

                        final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        imagePartPhoto_deli = MultipartBody.Part.createFormData("image2", file1.getName(), requestBody1);

                    }
                }

                progressDialog.show();
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait ..");


                if (BookingTypeVI==null){
                    BookingTypeVI="";
                }


                if ("".equals(login_emp) || login_emp.equals(null)){
                    Log.d("TAG", "onClickloggg"+login_emp);
                    login_emp = "";
                }

                WebService.getClient().deal_first_meeting_web(
                        RequestBody.create(MediaType.parse("text/plain"), login_emp),
                        RequestBody.create(MediaType.parse("text/plain"), Vemp),
                        RequestBody.create(MediaType.parse("text/plain"), cat_id),
                        RequestBody.create(MediaType.parse("text/plain"), sname),
                        RequestBody.create(MediaType.parse("text/plain"), BookingTypeVI),
                        RequestBody.create(MediaType.parse("text/plain"), bktamt),
                        RequestBody.create(MediaType.parse("text/plain"), DeliveryVI),
                        RequestBody.create(MediaType.parse("text/plain"), modelname),
                        RequestBody.create(MediaType.parse("text/plain"), sellLostVI),
                        RequestBody.create(MediaType.parse("text/plain"), selllost),
                        RequestBody.create(MediaType.parse("text/plain"), drop),
                        RequestBody.create(MediaType.parse("text/plain"), dropdes),
                        RequestBody.create(MediaType.parse("text/plain"), loan),
                        RequestBody.create(MediaType.parse("text/plain"), dpclear),
                        RequestBody.create(MediaType.parse("text/plain"), oldrcbook),
                        imagePartPhoto_book,
                        imagePartPhoto_deli

                ).enqueue(new Callback<Deal_model>() {
                    @Override
                    public void onResponse(Call<Deal_model> call, Response<Deal_model> response) {
                        progressDialog.dismiss();
                        Toast.makeText(DealfirstMeetingActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DealfirstMeetingActivity.this, DealstageMainActivity.class)
                              //  .putExtra("id",String.valueOf(response.body().getId()))
                        );
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Deal_model> call, Throwable t) {
                        progressDialog.dismiss();
                        Utils.showErrorToast(DealfirstMeetingActivity.this, "errrr"+t.getMessage());
                    }
                });
            }
        });
    }

    private void ModelNameGetMethod() {
        WebService.getClient().getModel_firstmeeting().enqueue(new Callback<getModelData>() {
            @Override
            public void onResponse(Call<getModelData> call, Response<getModelData> response) {
                if (0 == response.body().getData().size())
                {
                    Utils.showErrorToast(DealfirstMeetingActivity.this,"No Data Found");
                }
                else {
                    modellist.add("Select Model");

                    for (int i = 0 ; i<response.body().getData().size() ; i++){
                        modellist.add(response.body().getData().get(i).getModel_name());
                    }

                    ArrayAdapter spndropen_array = new ArrayAdapter(DealfirstMeetingActivity.this, android.R.layout.simple_spinner_dropdown_item, modellist);
                    spinner_modelname.setAdapter(spndropen_array);

                    spinner_modelname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            modelname = modellist.get(position);
                            if ("Select Model".equals(modelname)){
                                modelname = "";
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<getModelData> call, Throwable t) {
                Utils.showErrorToast(DealfirstMeetingActivity.this,t.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriPhoto = data.getData();
                    Log.d("Pan Image Uri", uriPhoto.toString());


                    if (booking_falg) {

                        waypathPhoto_book = getFilePath(this, uriPhoto);
                        Log.d("Pan Image Uri", waypathPhoto_book);
                        String[] name = waypathPhoto_book.split("/");

                        booking_txt.setText(name[name.length - 1]);
                        Uri selectedImageUri = data.getData();
                        booking_imge.setImageURI(selectedImageUri);
                    }

                    if (delivery_falg) {

                        waypathPhoto_deli = getFilePath(this, uriPhoto);
                        Log.d("Pan Image Uri", waypathPhoto_deli);
                        String[] name = waypathPhoto_deli.split("/");

                        delivery_tx.setText(name[name.length - 1]);
                        Uri selectedImageUri = data.getData();
                        delivery_image.setImageURI(selectedImageUri);
                    }
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