package com.example.kumarGroup.BookingDeliveryUpload.Add;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.BookingDeliveryUpload.BookingDeliveryMainActivity;
import com.example.kumarGroup.CustomerDetailFinalModel;
import com.example.kumarGroup.MobileNumDetailModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;

import com.example.kumarGroup.WebService;
import com.example.kumarGroup.inquiryGenDetailsModel;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCustomerDetailsActivity extends AppCompatActivity {


    EditText edt_AddCD_Fname, edt_AddCD_LastName, edt_AddCD_Surname,
            edt_AddCD_MobileNo, edt_AddCD_WhatsappNo, edt_AddCD_ReferenceName,
            edt_AddCD_ReferenceNo, edt_AddCD_Pickdate;

    EditText edt_AddCD_State, edt_AddCD_City, edt_AddCD_District, edt_AddCD_Taluko, edt_AddCD_Village;

    Spinner spn_AddCD_AggCom,spn_AddCD_BankPassBook,spn_AddCD_ChequeBook,
            spn_AddCD_Payment,spn_AddCD_MobileNo;


    ImageView img_AddCD_Booking, img_AddCD_AdharCard, img_AddCD_ElectionCard,
            img_AddCD_BankPassBook, img_AddCD_Cheque, img_AddCD_PassportSize;

    TextView txt_AddCD_UploadBookingPhoto, txt_AddCD_AdharCard, txt_AddCD_ElectionCard,
            txt_AddCD_BankPassBook, txt_AddCD_Cheque, txt_AddCD_PassportSize;

    //new Image

    ImageView img_AddCD_AdharCard2,img_AddCD_ElectionCard2,img_AddCD_PassportSize2;

    TextView txt_AddCD_AdharCard2,txt_AddCD_ElectionCard2,txt_AddCD_PassportSize2;

    Button btn_AddCD_Next;

    Calendar mcurrentdate;
    int day, month, year;

    String emp_id, login_id;

    String EMPName;


    ProgressDialog progressDialog;

    SharedPreferences sp1;
    String emp;

    SharedPreferences sharedPreferences;
    String NextIDD,CatId,inq_id;

    String AggCom;
    String[] Agg_Com = {"agricultural", "commercial"};

    String BankPassbook;
    String[] BankPassBook = {"Bank Passbook","Yes","No"};

    String Cheque_Book;
    String[] ChequeBook_data = {"CheckBook","Yes","No"};

    String paymentOpt;
    String[] PaymentOptions ={"Payment","Cash","Loan"};

    Uri uriBooking, uriAdharCard, uriElectionCard, uriBankPassBook, uriCheque, uriPassportSize;

    Uri uriAdharCard2,uriPassportSize2,uriElectionCard2;

    String waypathAdharCard2,waypathElectionCard2,waypathPassportSize2;


    String waypathBooking, waypathAdharCard, waypathElectionCard,
            waypathPassBook, waypathCheque, waypathPassportSize;

    String MobileNo,sname;
    String Product_Name;
    String phaseValue;

    String getNum;

    ArrayList<String> NumberList = new ArrayList<>();
    ArrayList<String> inqidsList = new ArrayList<>();
    AutoCompleteTextView auto_AddCD_MobileNo;


    LinearLayout  linBookingPic, linAadharPicOne, linAdharPicTwo, linElectionCardOne, linElectionCardTwo,
            linPassportSizeOne, linPassportSizeTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer_details);
        getSupportActionBar().setTitle("Customer Details");


        sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp1.getString("emp_id", "");

        sharedPreferences = getSharedPreferences("NextId", MODE_PRIVATE);
        NextIDD = sharedPreferences.getString("NextId","");
        CatId = sharedPreferences.getString("CatId","");
        Product_Name =sharedPreferences.getString("Product_Name","");


        /** ------------------------------Memory Allocation---------------------------  */

        edt_AddCD_Fname = findViewById(R.id.edt_AddCD_Fname);
        edt_AddCD_LastName = findViewById(R.id.edt_AddCD_LastName);
        edt_AddCD_Surname = findViewById(R.id.edt_AddCD_Surname);
        edt_AddCD_MobileNo = findViewById(R.id.edt_AddCD_MobileNo);
        edt_AddCD_WhatsappNo = findViewById(R.id.edt_AddCD_WhatsappNo);
        edt_AddCD_ReferenceName = findViewById(R.id.edt_AddCD_ReferenceName);
        edt_AddCD_ReferenceNo = findViewById(R.id.edt_AddCD_ReferenceNo);
        edt_AddCD_Pickdate = findViewById(R.id.edt_AddCD_Pickdate);

        edt_AddCD_State = findViewById(R.id.edt_AddCD_State);
        edt_AddCD_City = findViewById(R.id.edt_AddCD_City);
        edt_AddCD_District = findViewById(R.id.edt_AddCD_District);
        edt_AddCD_Taluko = findViewById(R.id.edt_AddCD_Taluko);
        edt_AddCD_Village = findViewById(R.id.edt_AddCD_Village);

        spn_AddCD_AggCom = findViewById(R.id.spn_AddCD_AggCom);
        spn_AddCD_BankPassBook = findViewById(R.id.spn_AddCD_BankPassBook);
        spn_AddCD_ChequeBook = findViewById(R.id.spn_AddCD_ChequeBook);
        spn_AddCD_Payment = findViewById(R.id.spn_AddCD_Payment);
        spn_AddCD_MobileNo = findViewById(R.id.spn_AddCD_MobileNo);
        auto_AddCD_MobileNo = findViewById(R.id.auto_AddCD_MobileNo);


        img_AddCD_Booking = findViewById(R.id.img_AddCD_Booking);
        img_AddCD_AdharCard = findViewById(R.id.img_AddCD_AdharCard);
        img_AddCD_ElectionCard = findViewById(R.id.img_AddCD_ElectionCard);
      /*  img_AddCD_BankPassBook = findViewById(R.id.img_AddCD_BankPassBook);
        img_AddCD_Cheque = findViewById(R.id.img_AddCD_Cheque);*/
        img_AddCD_PassportSize = findViewById(R.id.img_AddCD_PassportSize);


        img_AddCD_AdharCard2 = findViewById(R.id.img_AddCD_AdharCard2);
        img_AddCD_ElectionCard2 = findViewById(R.id.img_AddCD_ElectionCard2);
        img_AddCD_PassportSize2 = findViewById(R.id.img_AddCD_PassportSize2);

        txt_AddCD_AdharCard2 = findViewById(R.id.txt_AddCD_AdharCard2);
        txt_AddCD_ElectionCard2 = findViewById(R.id.txt_AddCD_ElectionCard2);
        txt_AddCD_PassportSize2 = findViewById(R.id.txt_AddCD_PassportSize2);

        txt_AddCD_UploadBookingPhoto = findViewById(R.id.txt_AddCD_UploadBookingPhoto);
        txt_AddCD_AdharCard = findViewById(R.id.txt_AddCD_AdharCard);
        txt_AddCD_ElectionCard = findViewById(R.id.txt_AddCD_ElectionCard);
       /* txt_AddCD_BankPassBook = findViewById(R.id.txt_AddCD_BankPassBook);
        txt_AddCD_Cheque = findViewById(R.id.txt_AddCD_Cheque);*/
        txt_AddCD_PassportSize = findViewById(R.id.txt_AddCD_PassportSize);

        btn_AddCD_Next = findViewById(R.id.btn_AddCD_Next);

        //-----------------------------------

        linBookingPic  = findViewById(R.id.linBookingPic);
        linAadharPicOne = findViewById(R.id.linAadharPicOne);
        linAdharPicTwo = findViewById(R.id.linAdharPicTwo);
        linElectionCardOne = findViewById(R.id.linElectionCardOne);
        linElectionCardTwo = findViewById(R.id.linElectionCardTwo);
        linPassportSizeOne = findViewById(R.id.linPassportSizeOne);
        linPassportSizeTwo = findViewById(R.id.linPassportSizeTwo);


        /** ------------------------------------------------------------------------------ */

       // if(Product_Name.equals("Implement") || Product_Name.equals("Spar part")){
        if(Product_Name.equals("Implement")){
            spn_AddCD_AggCom.setVisibility(View.GONE);
            spn_AddCD_BankPassBook.setVisibility(View.GONE);
            spn_AddCD_ChequeBook.setVisibility(View.GONE);
          //  spn_AddCD_Payment.setVisibility(View.GONE);

            linBookingPic.setVisibility(View.GONE);
            linAadharPicOne.setVisibility(View.GONE);
            linAdharPicTwo.setVisibility(View.GONE);
            linElectionCardOne.setVisibility(View.GONE);
            linElectionCardTwo.setVisibility(View.GONE);
            linPassportSizeOne.setVisibility(View.GONE);
            linPassportSizeTwo.setVisibility(View.GONE);
        }
        else{
            spn_AddCD_AggCom.setVisibility(View.VISIBLE);
            spn_AddCD_BankPassBook.setVisibility(View.VISIBLE);
            spn_AddCD_ChequeBook.setVisibility(View.VISIBLE);
           // spn_AddCD_Payment.setVisibility(View.VISIBLE);

        }


        if(Product_Name.equals("Other")){
            spn_AddCD_AggCom.setVisibility(View.GONE);
            spn_AddCD_BankPassBook.setVisibility(View.GONE);
            spn_AddCD_ChequeBook.setVisibility(View.GONE);
             spn_AddCD_Payment.setVisibility(View.GONE);

            linBookingPic.setVisibility(View.GONE);
            linAadharPicOne.setVisibility(View.GONE);
            linAdharPicTwo.setVisibility(View.GONE);
            linElectionCardOne.setVisibility(View.GONE);
            linElectionCardTwo.setVisibility(View.GONE);
            linPassportSizeOne.setVisibility(View.GONE);
            linPassportSizeTwo.setVisibility(View.GONE);
        }
        else{
            spn_AddCD_AggCom.setVisibility(View.VISIBLE);
            spn_AddCD_BankPassBook.setVisibility(View.VISIBLE);
            spn_AddCD_ChequeBook.setVisibility(View.VISIBLE);
            spn_AddCD_Payment.setVisibility(View.VISIBLE);

        }


        linBookingPic.setVisibility(View.GONE);

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH);
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR);
        // month = month+1;


        edt_AddCD_Pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddCustomerDetailsActivity.this,
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
                        // EdtWalletSalarySlipDateOne.setText(dy+"/"+mt+"/"+year);
                        edt_AddCD_Pickdate.setText(mt + "/" + dy + "/" + year);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_AddCD_Pickdate.setFocusable(false);


        WebService.getClient().getBookingNumber(CatId).enqueue(new Callback<inquiryGenDetailsModel>() {
            @Override
            public void onResponse(@NotNull Call<inquiryGenDetailsModel> call,
                                   @NotNull Response<inquiryGenDetailsModel> response) {


                if(response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d("ProductS", "onResponse: "+response.body().getCat().size());


                        /*NumberAutoCmpleteAdapter adapter = new NumberAutoCmpleteAdapter(AddCustomerDetailsActivity.this,   response.body().getCat(), response.body().getCat());
                        auto_AddCD_MobileNo.setThreshold(2);
                        auto_AddCD_MobileNo.setAdapter(adapter);

                        auto_AddCD_MobileNo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Object item = adapterView.getItemAtPosition(i);
                                if (item instanceof CatInquiryGenDetail){
                                    CatInquiryGenDetail village=(CatInquiryGenDetail) item;
                                    auto_AddCD_MobileNo.setText(village.getMoblino());
                                    Log.d("villagezIDDDD",village.getMoblino());

                                    getNum=village.getMoblino();
                                    inq_id=village.getInq_id();
                                    WebService.getClient().getDetailsOnMobileNo(getNum).enqueue(new Callback<MobileNumDetailModel>() {
                                        @Override
                                        public void onResponse(@NotNull Call<MobileNumDetailModel> call, @NotNull Response<MobileNumDetailModel> response) {
                                            //progressDialog.dismiss();
                                            assert response.body() != null;
                                            // Log.d("1212", "onResponse: "+response.body().getCat().get(0).getFname());
                                            // Toast.makeText(AddCustomerDetailsActivity.this, ""+response.body().getCat().get(0), Toast.LENGTH_SHORT).show();

                                            try {
                                                Log.d("1212", "onResponse: " + response.body().getCat().size()+MobileNo);
                                                sname=response.body().getCat().get(0).getSname();
                                                edt_AddCD_Fname.setText(response.body().getCat().get(0).getFname());
                                                edt_AddCD_LastName.setText(response.body().getCat().get(0).getLname());
                                                edt_AddCD_WhatsappNo.setText(response.body().getCat().get(0).getWhatsappno());
                                                edt_AddCD_State.setText(response.body().getCat().get(0).getState());
                                                edt_AddCD_City.setText(response.body().getCat().get(0).getCity());
                                                edt_AddCD_District.setText(response.body().getCat().get(0).getDistric());
                                                edt_AddCD_Taluko.setText(response.body().getCat().get(0).getTehsil());
                                                edt_AddCD_Village.setText(response.body().getCat().get(0).getVilage());

                                                emp_id= response.body().getCat().get(0).getEmployee_name();

                                                EMPName = response.body().getCat().get(0).getEid();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onFailure(@NotNull Call<MobileNumDetailModel> call, @NotNull Throwable t) {
                                            // progressDialog.dismiss();
                                            Toast.makeText(AddCustomerDetailsActivity.this, "" + t.getCause(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    adapter.notifyDataSetChanged();

                                }

                            }
                        });*/
                        NumberList.clear();
                        inqidsList.clear();
                        NumberList.add("Select Mobile Number");
                        inqidsList.add("Select Mobile Number");

                        for (int i = 0; i < response.body().getCat().size(); i++)
                        {
                            NumberList.add(response.body().getCat().get(i).getMoblino());
                            inqidsList.add(response.body().getCat().get(i).getInq_id());
                        }




                        ArrayAdapter adapter2 = new ArrayAdapter(AddCustomerDetailsActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, NumberList);
                        spn_AddCD_MobileNo.setAdapter(adapter2);
                        spn_AddCD_MobileNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                getNum = NumberList.get(position);
                                inq_id = inqidsList.get(position);

                                WebService.getClient().getDetailsOnMobileNo(getNum).enqueue(new Callback<MobileNumDetailModel>() {
                                    @Override
                                    public void onResponse(@NotNull Call<MobileNumDetailModel> call, @NotNull Response<MobileNumDetailModel> response) {
                                        //progressDialog.dismiss();
                                        assert response.body() != null;
                                        // Log.d("1212", "onResponse: "+response.body().getCat().get(0).getFname());
                                        // Toast.makeText(AddCustomerDetailsActivity.this, ""+response.body().getCat().get(0), Toast.LENGTH_SHORT).show();

                                        try {
                                            Log.d("1212", "onResponse: " + response.body().getCat().size()+MobileNo);
                                            sname=response.body().getCat().get(0).getSname();
                                            login_id=response.body().getCat().get(0).getLogin_id();
//                                            inq_id=response.body().getCat().get(0).getInq_id();
                                            edt_AddCD_Fname.setText(response.body().getCat().get(0).getFname());
                                            edt_AddCD_LastName.setText(response.body().getCat().get(0).getLname());
                                            edt_AddCD_WhatsappNo.setText(response.body().getCat().get(0).getWhatsappno());
                                            edt_AddCD_State.setText(response.body().getCat().get(0).getState());
                                            edt_AddCD_City.setText(response.body().getCat().get(0).getCity());
                                            edt_AddCD_District.setText(response.body().getCat().get(0).getDistric());
                                            edt_AddCD_Taluko.setText(response.body().getCat().get(0).getTehsil());
                                            edt_AddCD_Village.setText(response.body().getCat().get(0).getVilage());

                                            emp_id= response.body().getCat().get(0).getEmployee_name();

                                            EMPName = response.body().getCat().get(0).getEid();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFailure(@NotNull Call<MobileNumDetailModel> call, @NotNull Throwable t) {
                                        // progressDialog.dismiss();
                                        Toast.makeText(AddCustomerDetailsActivity.this, "" + t.getCause(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                }

            }

            @Override
            public void onFailure(@NotNull Call<inquiryGenDetailsModel> call, @NotNull Throwable t) {
            }
        });




   /*     edt_AddCD_MobileNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                getNum = edt_AddCD_MobileNo.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getNum = edt_AddCD_MobileNo.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
               *//* progressDialog= new ProgressDialog(AddCustomerDetailsActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);*//*

                getNum = edt_AddCD_MobileNo.getText().toString();
              //  Toast.makeText(AddCustomerDetailsActivity.this, ""+getNum, Toast.LENGTH_SHORT).show();

                if (getNum.length() == 10) {


                WebService.getClient().getDetailsOnMobileNo(getNum).enqueue(new Callback<MobileNumDetailModel>() {
                    @Override
                    public void onResponse(@NotNull Call<MobileNumDetailModel> call, @NotNull Response<MobileNumDetailModel> response) {
                        //progressDialog.dismiss();
                        assert response.body() != null;
                        // Log.d("1212", "onResponse: "+response.body().getCat().get(0).getFname());
                        // Toast.makeText(AddCustomerDetailsActivity.this, ""+response.body().getCat().get(0), Toast.LENGTH_SHORT).show();

                        try {
                            Log.d("1212", "onResponse: " + response.body().getCat().size()+MobileNo);
                            //  Toast.makeText(AddCustomerDetailsActivity.this, "" + response.body().getCat().get(0), Toast.LENGTH_SHORT).show();
                            //   Fname,Lname,Monum,StateName,CityName,VillageName,TalukaName, DistrictName,WtaNum;

                       *//* Fname = response.body().getCat().get(0).getFname();
                        Lname = response.body().getCat().get(0).getLname();
                        Monum = response.body().getCat().get(0).getMoblino();
                        WtaNum = response.body().getCat().get(0).getWhatsappno();
                        StateName = response.body().getCat().get(0).getState();
                        CityName = response.body().getCat().get(0).getCity();
                        VillageName = response.body().getCat().get(0).getVilage();
                        TalukaName = response.body().getCat().get(0).getTehsil();
                        DistrictName = response.body().getCat().get(0).getDesc(); *//*

                            // progressDialog.dismiss();
                            edt_AddCD_Fname.setText(response.body().getCat().get(0).getFname());
                            edt_AddCD_LastName.setText(response.body().getCat().get(0).getLname());
                            *//*  edt_AddCD_MobileNo.setText(response.body().getCat().get(0).getMoblino());*//*
                            edt_AddCD_WhatsappNo.setText(response.body().getCat().get(0).getWhatsappno());
                            edt_AddCD_State.setText(response.body().getCat().get(0).getState());
                            edt_AddCD_City.setText(response.body().getCat().get(0).getCity());
                            edt_AddCD_District.setText(response.body().getCat().get(0).getDistric());
                            edt_AddCD_Taluko.setText(response.body().getCat().get(0).getTehsil());
                            edt_AddCD_Village.setText(response.body().getCat().get(0).getVilage());

                            emp_id= response.body().getCat().get(0).getEmployee_name();

                            EMPName = response.body().getCat().get(0).getEid();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<MobileNumDetailModel> call, @NotNull Throwable t) {
                        // progressDialog.dismiss();
                        Toast.makeText(AddCustomerDetailsActivity.this, "" + t.getCause(), Toast.LENGTH_SHORT).show();
                    }
                });

                }
            }
        });

*/

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Agg_Com);
        spn_AddCD_AggCom.setAdapter(adapter);

        spn_AddCD_AggCom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AggCom = Agg_Com[position];

                if (Agg_Com[position].equals("agricultural")) {
                    spn_AddCD_AggCom.setBackgroundColor(getResources().getColor(R.color.Green));
                    img_AddCD_Booking.setVisibility(View.VISIBLE);
                    img_AddCD_AdharCard.setVisibility(View.VISIBLE);
                    img_AddCD_ElectionCard.setVisibility(View.VISIBLE);
                   /* img_AddCD_BankPassBook.setVisibility(View.VISIBLE);
                    img_AddCD_Cheque.setVisibility(View.VISIBLE);*/
                    img_AddCD_PassportSize.setVisibility(View.VISIBLE);
                    img_AddCD_PassportSize2.setVisibility(View.VISIBLE);

                    txt_AddCD_UploadBookingPhoto.setVisibility(View.VISIBLE);
                    txt_AddCD_AdharCard.setVisibility(View.VISIBLE);
                    txt_AddCD_ElectionCard.setVisibility(View.VISIBLE);
                   /* txt_AddCD_BankPassBook.setVisibility(View.VISIBLE);
                    txt_AddCD_Cheque.setVisibility(View.VISIBLE);*/
                    txt_AddCD_PassportSize.setVisibility(View.VISIBLE);
                    txt_AddCD_PassportSize2.setVisibility(View.VISIBLE);

                } else {
                    spn_AddCD_AggCom.setBackgroundColor(getResources().getColor(R.color.Yellow));

                    img_AddCD_Booking.setVisibility(View.VISIBLE);
                    img_AddCD_AdharCard.setVisibility(View.VISIBLE);
                    img_AddCD_ElectionCard.setVisibility(View.VISIBLE);
                  /*  img_AddCD_BankPassBook.setVisibility(View.VISIBLE);
                    img_AddCD_Cheque.setVisibility(View.VISIBLE);*/
                    img_AddCD_PassportSize.setVisibility(View.GONE);
                    img_AddCD_PassportSize2.setVisibility(View.GONE);

                    txt_AddCD_UploadBookingPhoto.setVisibility(View.VISIBLE);
                    txt_AddCD_AdharCard.setVisibility(View.VISIBLE);
                    txt_AddCD_ElectionCard.setVisibility(View.VISIBLE);
                   /* txt_AddCD_BankPassBook.setVisibility(View.VISIBLE);
                    txt_AddCD_Cheque.setVisibility(View.VISIBLE);*/
                    txt_AddCD_PassportSize.setVisibility(View.GONE);
                    txt_AddCD_PassportSize2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapterBankPassBook = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, BankPassBook);
        spn_AddCD_BankPassBook.setAdapter(adapterBankPassBook);


        spn_AddCD_BankPassBook.setVisibility(View.GONE);
        spn_AddCD_BankPassBook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BankPassbook = BankPassBook[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapterCheque = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ChequeBook_data);
        spn_AddCD_ChequeBook.setAdapter(adapterCheque);

        spn_AddCD_ChequeBook.setVisibility(View.GONE);
        spn_AddCD_ChequeBook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cheque_Book = ChequeBook_data[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter PaymentOptionArray = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,PaymentOptions);
        spn_AddCD_Payment.setAdapter(PaymentOptionArray);

        spn_AddCD_Payment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                paymentOpt = PaymentOptions[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        txt_AddCD_UploadBookingPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 2);
            }
        });

        txt_AddCD_AdharCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 3);
            }
        });

        txt_AddCD_ElectionCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 4);
            }
        });


      /*  txt_AddCD_BankPassBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 5);
            }
        });


        txt_AddCD_Cheque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 6);
            }
        });*/


        txt_AddCD_PassportSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 7);
            }
        });

        txt_AddCD_AdharCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 8);
            }
        });

        txt_AddCD_ElectionCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 9);
            }
        });

        txt_AddCD_PassportSize2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 10);
            }
        });


        btn_AddCD_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValidate()){
                    progressDialog = new ProgressDialog(AddCustomerDetailsActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    MultipartBody.Part imagePartBooking = null;
                    MultipartBody.Part imagePartAdharcard  = null;
                    MultipartBody.Part imagePartElection  = null;
                    MultipartBody.Part imagePassBook  = null;
                    MultipartBody.Part imageCheque  = null;
                    MultipartBody.Part image7_12_8_a = null;

                    MultipartBody.Part imagePartAdharcard2 = null;
                    MultipartBody.Part imagePartElection2 = null;
                    MultipartBody.Part image7_12_8_a2 = null;


                    if(waypathBooking != null){
                        File file1 = new File(waypathBooking);
                        final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        imagePartBooking = MultipartBody.Part.createFormData("image1",
                                file1.getName(), requestBody1);
                    }

                    if(waypathAdharCard != null){
                        File file2 = new File(waypathAdharCard);
                        final RequestBody requestBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                        imagePartAdharcard = MultipartBody.Part.createFormData("image2",
                                file2.getName(), requestBody2);
                    }

                    if(waypathElectionCard != null){
                        File file3 = new File(waypathElectionCard);
                        final RequestBody requestBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                        imagePartElection = MultipartBody.Part.createFormData("image3",
                                file3.getName(), requestBody3);
                    }


              /*  if(waypathPassBook != null){
                    File file4 = new File(waypathPassBook);
                    final RequestBody requestBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                     imagePassBook = MultipartBody.Part.createFormData("image4",
                            file4.getName(), requestBody4);
                }

                if(waypathCheque != null){
                    File file5 = new File(waypathCheque);
                    final RequestBody requestBody5 = RequestBody.create(MediaType.parse("image/*"), file5);
                    imageCheque = MultipartBody.Part.createFormData("image5",
                            file5.getName(), requestBody5);
                }*/

                    if(waypathPassportSize != null){
                        File file6 = new File(waypathPassportSize);
                        final RequestBody requestBody6 = RequestBody.create(MediaType.parse("image/*"), file6);
                        image7_12_8_a = MultipartBody.Part.createFormData("image6",
                                file6.getName(), requestBody6);
                    }

                    if(waypathAdharCard2 != null){
                        File file7 = new File(waypathAdharCard2);
                        final RequestBody requestBody7 = RequestBody.create(MediaType.parse("image/*"), file7);
                        imagePartAdharcard2 = MultipartBody.Part.createFormData("imgg1",
                                file7.getName(), requestBody7);
                    }

                    if(waypathElectionCard2 != null){
                        File file8 = new File(waypathElectionCard2);
                        final RequestBody requestBody8 = RequestBody.create(MediaType.parse("image/*"), file8);
                        imagePartElection2 = MultipartBody.Part.createFormData("imgg2",
                                file8.getName(), requestBody8);
                    }

                    if(waypathPassportSize2 != null){
                        File file9 = new File(waypathPassportSize2);
                        final RequestBody requestBody9 = RequestBody.create(MediaType.parse("image/*"), file9);
                        image7_12_8_a2 = MultipartBody.Part.createFormData("imgg3",
                                file9.getName(), requestBody9);
                    }


                    if(AggCom == null){
                        AggCom = " ";
                    }

                    if(BankPassbook == null){
                        BankPassbook=" ";
                    }

                    if(Cheque_Book == null){
                        Cheque_Book=" ";
                    }


                    if(paymentOpt== null){
                        paymentOpt=" ";
                    }

                    String FnameLname = edt_AddCD_Fname.getText().toString()+" "+edt_AddCD_LastName.getText().toString();


                    if(Product_Name.equals("Other")){
                        phaseValue = "6";
                    }
                    else{
                        phaseValue = "3";
                    }


                    if (emp_id==null){
                        emp_id=emp;
                    }



                    //   Toast.makeText(AddCustomerDetailsActivity.this, ""+edt_AddCD_ReferenceNo.getText().toString(), Toast.LENGTH_SHORT).show();
                    //  Toast.makeText(AddCustomerDetailsActivity.this, ""+emp + " " +emp_id, Toast.LENGTH_SHORT).show();

                    WebService.getClient().getCustomerDetailNext(
                            RequestBody.create(MediaType.parse("text/plain"), edt_AddCD_Pickdate.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"),login_id),
                            RequestBody.create(MediaType.parse("text/plain"),FnameLname),
                            RequestBody.create(MediaType.parse("text/plain"),sname),
                            RequestBody.create(MediaType.parse("text/plain"),getNum),
                            RequestBody.create(MediaType.parse("text/plain"),edt_AddCD_WhatsappNo.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"),edt_AddCD_ReferenceName.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"),edt_AddCD_ReferenceNo.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"),edt_AddCD_State.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"),edt_AddCD_City.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"),edt_AddCD_District.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"),edt_AddCD_Taluko.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"),edt_AddCD_Village.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"),emp_id),
                            RequestBody.create(MediaType.parse("text/plain"),phaseValue),
                            RequestBody.create(MediaType.parse("text/plain"),AggCom),
                            RequestBody.create(MediaType.parse("text/plain"),BankPassbook),
                            RequestBody.create(MediaType.parse("text/plain"),Cheque_Book),
                            RequestBody.create(MediaType.parse("text/plain"),paymentOpt),
                            RequestBody.create(MediaType.parse("text/plain"),EMPName),
                            RequestBody.create(MediaType.parse("text/plain"),NextIDD),
                            RequestBody.create(MediaType.parse("text/plain"),inq_id),
                            imagePartBooking,
                            imagePartAdharcard,
                            imagePartElection,
                       /* imagePassBook,
                        imageCheque,*/
                            image7_12_8_a,
                            imagePartAdharcard2,
                            imagePartElection2,
                            image7_12_8_a2
                    ).enqueue(new Callback<CustomerDetailFinalModel>() {
                        @Override
                        public void onResponse(@NotNull Call<CustomerDetailFinalModel> call,
                                               @NotNull Response<CustomerDetailFinalModel> response) {
                            progressDialog.dismiss();


                            Toast.makeText(AddCustomerDetailsActivity.this, ""+response.body().getMsg(), Toast.LENGTH_LONG).show();

                            sharedPreferences.edit().putString("CatId", "").apply();

                            if( Product_Name.equals("Other")){
                                Intent i = new Intent(AddCustomerDetailsActivity.this,
                                        BookingDeliveryMainActivity.class);
//                                 i.putExtra("nextId",nextId);
                                startActivity(i);
                            }
                            else{
                                Intent i = new Intent(AddCustomerDetailsActivity.this, DownPaymentActivity.class);
//                                 i.putExtra("nextId",nextId);
                                startActivity(i);
                            }

                        }

                        @Override
                        public void onFailure(@NotNull Call<CustomerDetailFinalModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();

                            Log.d("bone", "onFailure: "+t.getCause());
                        }
                    });
                }


            }
        });
    }

    private boolean isValidate() {

        if ( edt_AddCD_Pickdate.getText().toString().trim().equals("")) {
            Utils.showErrorToast(AddCustomerDetailsActivity.this, "Please select date");
            return false;
        }/*else if (auto_AddCD_MobileNo.getText().toString().trim().equals("")) {
            Utils.showErrorToast(AddCustomerDetailsActivity.this, "Please enter Mobile No.");
            return false;
        }else if (auto_AddCD_MobileNo.getText().toString().length()<10) {
            Utils.showErrorToast(AddCustomerDetailsActivity.this, "Please enter Mobile No. properly");
            return false;
        }*/else if (spn_AddCD_MobileNo.getSelectedItem().toString().trim().equals("Select Mobile Number")) {
            Utils.showErrorToast(AddCustomerDetailsActivity.this, "Select Mobile Number");
            return false;
        }else if (edt_AddCD_WhatsappNo.getText().toString().equals("")){
            Utils.showErrorToast(AddCustomerDetailsActivity.this, "Please enter Whatsapp No.");
            return false;
        }else if (edt_AddCD_State.getText().toString().equals("")){
            Utils.showErrorToast(AddCustomerDetailsActivity.this, "Please enter State");
            return false;
        }else if (edt_AddCD_City.getText().toString().equals("")){
            Utils.showErrorToast(AddCustomerDetailsActivity.this, "Please enter City");
            return false;
        }else if (edt_AddCD_District.getText().toString().equals("")){
            Utils.showErrorToast(AddCustomerDetailsActivity.this, "Please enter District");
            return false;
        }else if (edt_AddCD_Taluko.getText().toString().equals("")){
            Utils.showErrorToast(AddCustomerDetailsActivity.this, "Please enter Taluko");
            return false;
        }else if (edt_AddCD_Village.getText().toString().equals("")){
            Utils.showErrorToast(AddCustomerDetailsActivity.this, "Please enter Village name");
            return false;
        }else if (spn_AddCD_Payment.getVisibility()==View.VISIBLE) {
            if (spn_AddCD_Payment.getSelectedItem().toString().trim().equals("Payment")) {
                Utils.showErrorToast(AddCustomerDetailsActivity.this, "Select Payment");
                return false;
            }
        }else{
            return true;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriBooking = data.getData();
                    Log.d("PanImageUri", uriBooking.toString());
                    waypathBooking = getFilePath(this, uriBooking);
                    Log.d("PanImageWayPath", waypathBooking);
                    String[] name = waypathBooking.split("/");
                    txt_AddCD_UploadBookingPhoto.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_AddCD_Booking.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriAdharCard = data.getData();
                    Log.d("Pan Image Uri", uriAdharCard.toString());
                    waypathAdharCard = getFilePath(this, uriAdharCard);
                    Log.d("Pan Image Uri", waypathAdharCard);
                    String[] name = waypathAdharCard.split("/");
                    txt_AddCD_AdharCard.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_AddCD_AdharCard.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriElectionCard = data.getData();
                    Log.d("Pan Image Uri", uriElectionCard.toString());
                    waypathElectionCard = getFilePath(this, uriElectionCard);
                    Log.d("Pan Image Uri", waypathElectionCard);
                    String[] name = waypathElectionCard.split("/");
                    txt_AddCD_ElectionCard.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_AddCD_ElectionCard.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 5) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriBankPassBook = data.getData();
                    Log.d("Pan Image Uri", uriBankPassBook.toString());
                    waypathPassBook = getFilePath(this, uriBankPassBook);
                    Log.d("Pan Image Uri", waypathPassBook);
                    String[] name = waypathPassBook.split("/");
                    txt_AddCD_BankPassBook.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_AddCD_BankPassBook.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 6) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriCheque = data.getData();
                    Log.d("Pan Image Uri", uriCheque.toString());
                    waypathCheque = getFilePath(this, uriCheque);
                    Log.d("Pan Image Uri", waypathCheque);
                    String[] name = waypathCheque.split("/");
                    txt_AddCD_Cheque.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_AddCD_Cheque.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 7) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriPassportSize = data.getData();
                    Log.d("Pan Image Uri", uriPassportSize.toString());
                    waypathPassportSize = getFilePath(this, uriPassportSize);
                    Log.d("Pan Image Uri", waypathPassportSize);
                    String[] name = waypathPassportSize.split("/");
                    txt_AddCD_PassportSize.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_AddCD_PassportSize.setImageURI(selectedImageUri);
                }
            }
        }



        if (requestCode == 8) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriAdharCard2 = data.getData();
                    Log.d("Pan Image Uri", uriAdharCard2.toString());
                    waypathAdharCard2 = getFilePath(this, uriAdharCard2);
                    Log.d("Pan Image Uri", waypathAdharCard2);
                    String[] name = waypathAdharCard2.split("/");
                    txt_AddCD_AdharCard2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_AddCD_AdharCard2.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 9) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriElectionCard2 = data.getData();
                    Log.d("Pan Image Uri", uriElectionCard2.toString());
                    waypathElectionCard2 = getFilePath(this, uriElectionCard2);
                    Log.d("Pan Image Uri", waypathElectionCard2);
                    String[] name = waypathElectionCard2.split("/");
                    txt_AddCD_ElectionCard2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_AddCD_ElectionCard2.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriPassportSize2 = data.getData();
                    Log.d("Pan Image Uri", uriPassportSize2.toString());
                    waypathPassportSize2 = getFilePath(this, uriPassportSize2);
                    Log.d("Pan Image Uri", waypathPassportSize2);
                    String[] name = waypathPassportSize2.split("/");
                    txt_AddCD_PassportSize2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_AddCD_PassportSize2.setImageURI(selectedImageUri);
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