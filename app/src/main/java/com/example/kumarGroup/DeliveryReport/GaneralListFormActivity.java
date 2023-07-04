package com.example.kumarGroup.DeliveryReport;

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

import com.bumptech.glide.Glide;
import com.example.kumarGroup.DelievryReportViewGenListModel;
import com.example.kumarGroup.DeliveryGenKListSubmitModel;
import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
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

public class GaneralListFormActivity extends AppCompatActivity {

    Spinner spn_save_mobile_no,
            spn_collect_whatsapp_no, spn_subscribe_youtube_chanel, spn_like_fb_insta_page,
            spn_toolkit_delivery, spn_key_delivery, spn_check_document, edt_warranty_pollicy_discuusion, edt_check_oil_level, edt_check_accessories, edt_installation, Spn_name_plat, edt_tractor_number_plat,
            edt_second_services, edt_fisrt_services, edt_third_services,
            Spn_deliveryPhoto, Spn_ChasisPhoto,
            Spn_engineNo, Spn_ChasisNo,
            spn_Tractortype, spn_Rent, spn_Rotavator, spn_SpeedDrel, spn_Garden;

    Button btn_Submit_gen_list;


    TextView txt_save_mobile_no, txt_collect_whatsapp_no, txt_subscribe_youtube_chanel, txt_like_fb_insta_page,
            txt_toolkit_delivery, txt_key_delivery, txt_check_document, txt_warranty_pollicy_discuusion,
            txt_check_oil_level, txt_check_accessories,
            txt_installation, txt_name_plat, txt_tractor_number_plat, txt_fisrt_services, txt_second_services,
            txt_third_services;


    LinearLayout lin_SaveMobile, lin_CallWhtaNo, lin_subscribeYtChannel, lin_LikeFbInsta, lin_toolkitDelivery,
            lin_keyDelivery, lin_checkAmount, lin_warrentyPolicy, lin_checkOil, lin_checkAccesories,
            linInstallation, lin_NamePlat, lin_tractNoPlat, lin_firstService, lin_SecondService,
            lin_thirdService, lin_DeliveryPho, lin_ChasisPhoto;

    ImageView img_saveMobile, img_colWhatNo, img_SubYtChannel, img_LikeFbInsta, img_toolKitDeli,
            img_keyDelivery, img_checkAmount, img_WarrentyPolicy, img_checkOil, img_checkAsse,
            img_Installation, img_NamePlat, img_tractNoPlat, img_firstService, img_secService,
            img_thirdSer, img_deliveryPhoto, img_ChasisPhoto;


    TextView txt_saveMobile, txt_colWhatNo, txt_SubYtChannel, txt_LikeFbInsta, txt_toolKitDeli,
            txt_keyDelivery, txt_checkAmount, txt_WarrentyPolicy, txt_checkOil, txt_checkAsse,
            txt_Installtion, txt_NamePlat, txt_tractNoPlat, txt_firstService, txt_secService,
            txt_thirdSer, txt_DeliveryPhoto, txt_ChasisPhoto;


    //Extra TextView
    TextView txt_GardenYesNo, txt_SpeedDreelYesNo, txt_RotavetorYesNo, txt_RentYesNo, txt_TarctorTypeYesNo,
            txt_ChasisYesNo, txt_EngineYesNo, txt_ChaisPrintYesNo, txt_DeliveryYesNo;


    String WayPath_saveMobile, WayPath_colWhatNo, WayPath_SubYtChannel, WayPath_LikeFbInsta,
            WayPath_toolKitDeli, WayPath_keyDelivery, WayPath_checkAmount,
            WayPath_WarrentyPolicy, WayPath_checkOil, WayPath_checkAsse, WayPath_Installtion,
            WayPath_NamePlat,
            WayPath_tractNoPlat, WayPath_firstService, WayPath_secService, WayPath_thirdSer,
            WayPath_DeliveryPhoto, WayPath_ChasisPhoto;

    Uri Uri_saveMobile, Uri_colWhatNo, Uri_SubYtChannel, Uri_LikeFbInsta,
            Uri_toolKitDeli, Uri_keyDelivery, Uri_checkAmount,
            Uri_WarrentyPolicy, Uri_scheckOil, Uri_checkAsse, Uri_Installtion,
            Uri_NamePlat,
            Uri_tractNoPlat, Uri_firstService, Uri_secService, Uri_thirdSer,
            Uri_DeliveryPhoto, Uri_ChasisPhoto;


    EditText edt_engine_no, edt_chasis_no,
            edt_modelName, edt_modelYear, edt_LandRecord, edit_collect_whatsapp_no;


    EditText edt_SmartCard_no,edt_SmartCard_Date;


    String Save_mobile;
    String[] SaveMobile = {"Save mobile", "Yes", "No"};
    String collect_whatsapp_no;
    String[] collectWhatsappNo = {"Whats App no", "Yes", "No"};

    String subscribe_youtube_chanel;
    String[] subscribeYoutubeChanel = {"Subscribe Youtube Channel", "Yes", "No"};

    String like_fb_insta_page;
    String[] likeFbInstaPage = {"Like Fb Insta Page", "Yes", "No"};

    String Toolkit_Delivery;
    String[] ToolkitDelivery = {"ToolKit Delivery", "Yes", "No"};

    String key_delivery;
    String[] keyDelivery = {"Key Delivery", "Yes", "No"};

    String check_document;
    String[] checkDocument = {"Check Document", "Yes", "No"};

    String warranty_pollicy_discuusion;
    String[] warrantyPollicyDiscuusion = {"Warranty Policy Discussion", "Yes", "No"};


    String check_oil_level;
    String[] checkOilLevel = {"Check Oil Level", "Yes", "No"};

    String check_accessories;
    String[] checkAccessories = {"Check Accessories", "Yes", "No"};

    String installation;
    String[] Installation = {"Installation", "Yes", "No"};

    String name_plat;
    String[] namePlat = {"Name Plat", "Yes", "No"};

    String tractor_number_plat;
    String[] tractorNumberPlat = {"Tractor Number Plat", "Yes", "No"};

    String second_services;
    String[] secondServices = {"Second Service", "Yes", "No"};

    String fisrt_services;
    String[] fisrtServices = {"First Service", "Yes", "No"};

    String third_services;
    String[] thirdServices = {"Third Service", "Yes", "No"};


    String Delivery_Photo;
    String[] DeliveryPhoto = {"Delivery Photo", "Yes", "No"};

    String Chasis_PrintPhoto;
    String[] ChasisPrintPhoto = {"Chasis PrintPhoto", "Yes", "No"};


    String Engine_Photo;
    String[] EngineNO = {"Engine No", "Yes", "No"};

    String Chasis_No;
    String[] ChasisNo = {"Chasis No", "Yes", "No"};


    String Tractor_Type;
    String[] TractorType = {"Tractor Type", "Big", "Mini"};

    String Rent_Type;
    String[] RentType = {"Rent", "Full Time", "Part Time", "Self"};

    String Rotavetor_Type;
    String[] RotavetorType = {"Rotavator", "Yes", "No"};

    String Speed_Dreel;
    String[] SpeedDreel = {"Seed Dreel", "Yes", "No"};


    String Garden;
    String[] GardenType = {"Garden", "Yes", "No"};

    SharedPreferences sharedPreferences;
    String NextIDD;

    ProgressDialog progressDialog, progressDialog1;

    String SubFlag;

    String id;


    LinearLayout LinTextViewData, LinSpinner;

    String cuid;

    Calendar mcurrentdate;
    int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganeral_list_form);


        getSupportActionBar().setTitle("Delivery Report Form");

        /** ************************** Memory Allocation **************************** */

        spn_save_mobile_no = findViewById(R.id.spn_save_mobile_no);
        spn_collect_whatsapp_no = findViewById(R.id.spn_collect_whatsapp_no);
        spn_subscribe_youtube_chanel = findViewById(R.id.spn_subscribe_youtube_chanel);
        spn_like_fb_insta_page = findViewById(R.id.spn_like_fb_insta_page);
        spn_toolkit_delivery = findViewById(R.id.spn_toolkit_delivery);
        spn_key_delivery = findViewById(R.id.spn_key_delivery);
        spn_check_document = findViewById(R.id.spn_check_document);
        edt_warranty_pollicy_discuusion = findViewById(R.id.edt_warranty_pollicy_discuusion);

        edt_warranty_pollicy_discuusion = findViewById(R.id.edt_warranty_pollicy_discuusion);
        edt_check_oil_level = findViewById(R.id.edt_check_oil_level);
        edt_check_accessories = findViewById(R.id.edt_check_accessories);
        edt_installation = findViewById(R.id.edt_installation);
        Spn_name_plat = findViewById(R.id.Spn_name_plat);
        edt_tractor_number_plat = findViewById(R.id.edt_tractor_number_plat);
        edt_second_services = findViewById(R.id.edt_second_services);
        edt_fisrt_services = findViewById(R.id.edt_fisrt_services);
        edt_third_services = findViewById(R.id.edt_third_services);

        btn_Submit_gen_list = findViewById(R.id.btn_Submit_gen_list);

        txt_save_mobile_no = findViewById(R.id.txt_save_mobile_no);
        txt_collect_whatsapp_no = findViewById(R.id.txt_collect_whatsapp_no);
        txt_subscribe_youtube_chanel = findViewById(R.id.txt_subscribe_youtube_chanel);
        txt_like_fb_insta_page = findViewById(R.id.txt_like_fb_insta_page);
        txt_key_delivery = findViewById(R.id.txt_key_delivery);
        txt_check_document = findViewById(R.id.txt_check_document);
        txt_warranty_pollicy_discuusion = findViewById(R.id.txt_warranty_pollicy_discuusion);
        txt_check_oil_level = findViewById(R.id.txt_check_oil_level);
        txt_check_accessories = findViewById(R.id.txt_check_accessories);
        txt_installation = findViewById(R.id.txt_installation);
        txt_tractor_number_plat = findViewById(R.id.txt_tractor_number_plat);
        txt_fisrt_services = findViewById(R.id.txt_fisrt_services);
        txt_second_services = findViewById(R.id.txt_second_services);
        txt_third_services = findViewById(R.id.txt_third_services);
        txt_toolkit_delivery = findViewById(R.id.txt_toolkit_delivery);

        //Extra TextView Memory Allocation

        txt_GardenYesNo = findViewById(R.id.txt_GardenYesNo);
        txt_SpeedDreelYesNo = findViewById(R.id.txt_SpeedDreelYesNo);
        txt_RotavetorYesNo = findViewById(R.id.txt_RotavetorYesNo);
        txt_RentYesNo = findViewById(R.id.txt_RentYesNo);
        txt_TarctorTypeYesNo = findViewById(R.id.txt_TarctorTypeYesNo);
        txt_ChasisYesNo = findViewById(R.id.txt_ChasisYesNo);
        txt_EngineYesNo = findViewById(R.id.txt_EngineYesNo);
        txt_ChaisPrintYesNo = findViewById(R.id.txt_ChaisPrintYesNo);
        txt_DeliveryYesNo = findViewById(R.id.txt_DeliveryYesNo);
        txt_name_plat = findViewById(R.id.txt_name_plat);



        edt_SmartCard_no = findViewById(R.id.edt_SmartCard_no);
        edt_SmartCard_Date = findViewById(R.id.edt_SmartCard_Date);


        edt_engine_no = findViewById(R.id.edt_engine_no);
        edt_chasis_no = findViewById(R.id.edt_chasis_no);

        Spn_deliveryPhoto = findViewById(R.id.Spn_deliveryPhoto);
        Spn_ChasisPhoto = findViewById(R.id.Spn_ChasisPhoto);

        Spn_engineNo = findViewById(R.id.Spn_engineNo);
        Spn_ChasisNo = findViewById(R.id.Spn_ChasisNo);


        spn_Tractortype = findViewById(R.id.spn_Tractortype);
        spn_Rent = findViewById(R.id.spn_Rent);
        spn_Rotavator = findViewById(R.id.spn_Rotavator);
        spn_SpeedDrel = findViewById(R.id.spn_SpeedDrel);
        spn_Garden = findViewById(R.id.spn_Garden);


        edt_modelName = findViewById(R.id.edt_modelName);
        edt_modelYear = findViewById(R.id.edt_modelYear);
        edt_LandRecord = findViewById(R.id.edt_LandRecord);
        edit_collect_whatsapp_no = findViewById(R.id.edit_collect_whatsapp_no);


        //***************************************************************


        lin_SaveMobile = findViewById(R.id.lin_SaveMobile);
        lin_CallWhtaNo = findViewById(R.id.lin_CallWhtaNo);
        lin_subscribeYtChannel = findViewById(R.id.lin_subscribeYtChannel);
        lin_toolkitDelivery = findViewById(R.id.lin_toolkitDelivery);
        lin_LikeFbInsta = findViewById(R.id.lin_LikeFbInsta);
        lin_keyDelivery = findViewById(R.id.lin_keyDelivery);
        lin_checkAmount = findViewById(R.id.lin_checkAmount);
        lin_warrentyPolicy = findViewById(R.id.lin_warrentyPolicy);
        lin_checkOil = findViewById(R.id.lin_checkOil);
        lin_checkAccesories = findViewById(R.id.lin_checkAccesories);
        linInstallation = findViewById(R.id.linInstallation);
        lin_NamePlat = findViewById(R.id.lin_NamePlat);
        lin_tractNoPlat = findViewById(R.id.lin_tractNoPlat);
        lin_firstService = findViewById(R.id.lin_firstService);
        lin_SecondService = findViewById(R.id.lin_SecondService);
        lin_thirdService = findViewById(R.id.lin_thirdService);
        lin_DeliveryPho = findViewById(R.id.lin_DeliveryPho);
        lin_ChasisPhoto = findViewById(R.id.lin_ChasisPhoto);


        img_saveMobile = findViewById(R.id.img_saveMobile);
        img_SubYtChannel = findViewById(R.id.img_SubYtChannel);
        img_colWhatNo = findViewById(R.id.img_colWhatNo);
        img_LikeFbInsta = findViewById(R.id.img_LikeFbInsta);
        img_toolKitDeli = findViewById(R.id.img_toolKitDeli);
        img_keyDelivery = findViewById(R.id.img_keyDelivery);
        img_checkAmount = findViewById(R.id.img_checkAmount);
        img_WarrentyPolicy = findViewById(R.id.img_WarrentyPolicy);
        img_checkOil = findViewById(R.id.img_checkOil);
        img_checkAsse = findViewById(R.id.img_checkAsse);
        img_Installation = findViewById(R.id.img_Installation);
        img_NamePlat = findViewById(R.id.img_NamePlat);
        img_tractNoPlat = findViewById(R.id.img_tractNoPlat);
        img_firstService = findViewById(R.id.img_firstService);
        img_secService = findViewById(R.id.img_secService);
        img_thirdSer = findViewById(R.id.img_thirdSer);
        img_deliveryPhoto = findViewById(R.id.img_deliveryPhoto);
        img_ChasisPhoto = findViewById(R.id.img_ChasisPhoto);


        txt_saveMobile = findViewById(R.id.txt_saveMobile);
        txt_colWhatNo = findViewById(R.id.txt_colWhatNo);
        txt_SubYtChannel = findViewById(R.id.txt_SubYtChannel);
        txt_LikeFbInsta = findViewById(R.id.txt_LikeFbInsta);
        txt_toolKitDeli = findViewById(R.id.txt_toolKitDeli);
        txt_keyDelivery = findViewById(R.id.txt_keyDelivery);
        txt_checkAmount = findViewById(R.id.txt_checkAmount);
        txt_WarrentyPolicy = findViewById(R.id.txt_WarrentyPolicy);
        txt_checkOil = findViewById(R.id.txt_checkOil);
        txt_checkAmount = findViewById(R.id.txt_checkAmount);
        txt_checkAsse = findViewById(R.id.txt_checkAsse);
        txt_Installtion = findViewById(R.id.txt_Installtion);
        txt_NamePlat = findViewById(R.id.txt_NamePlat);
        txt_tractNoPlat = findViewById(R.id.txt_tractNoPlat);
        txt_firstService = findViewById(R.id.txt_firstService);
        txt_secService = findViewById(R.id.txt_secService);
        txt_thirdSer = findViewById(R.id.txt_thirdSer);
        txt_DeliveryPhoto = findViewById(R.id.txt_DeliveryPhoto);
        txt_ChasisPhoto = findViewById(R.id.txt_ChasisPhoto);

        //*************************************************************************************


        LinTextViewData = findViewById(R.id.LinTextViewData);
        LinSpinner = findViewById(R.id.LinSpinner);


        id = getIntent().getStringExtra("id");
        cuid = getIntent().getStringExtra("cuid");

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH);
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR);


        edt_SmartCard_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(GaneralListFormActivity.this,
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
                        //2021-08-18
                        edt_SmartCard_Date.setText(year + "-" + mt + "-" + dy);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        edt_SmartCard_Date.setFocusable(false);

        //   Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();

        //  LinTextViewData.setVisibility(View.GONE);

        //*************************************************************************************


        progressDialog1 = new ProgressDialog(GaneralListFormActivity.this);
        progressDialog1.show();
        progressDialog1.setContentView(R.layout.progress_dialog);
        progressDialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getDRGaneralListView(id).enqueue(new Callback<DelievryReportViewGenListModel>() {
            @Override
            public void onResponse(@NotNull Call<DelievryReportViewGenListModel> call,
                                   @NotNull Response<DelievryReportViewGenListModel> response) {
                progressDialog1.dismiss();

                assert response.body() != null;

                SubFlag = response.body().getData().get(0).getSub_flag();

                //  Toast.makeText(GaneralListFormActivity.this, ""+SubFlag, Toast.LENGTH_SHORT).show();


                if (SubFlag.equals("0")) {
                    //   LinSpinner.setVisibility(View.VISIBLE);
                    // LinTextViewData.setVisibility(View.GONE);


                    txt_save_mobile_no.setVisibility(View.GONE);
                    txt_collect_whatsapp_no.setVisibility(View.GONE);
                    txt_subscribe_youtube_chanel.setVisibility(View.GONE);
                    txt_like_fb_insta_page.setVisibility(View.GONE);
                    txt_toolkit_delivery.setVisibility(View.GONE);
                    txt_key_delivery.setVisibility(View.GONE);
                    txt_check_document.setVisibility(View.GONE);
                    txt_warranty_pollicy_discuusion.setVisibility(View.GONE);
                    txt_check_oil_level.setVisibility(View.GONE);
                    txt_check_accessories.setVisibility(View.GONE);
                    txt_installation.setVisibility(View.GONE);
                    txt_name_plat.setVisibility(View.GONE);
                    txt_tractor_number_plat.setVisibility(View.GONE);
                    txt_DeliveryYesNo.setVisibility(View.GONE);
                    txt_ChaisPrintYesNo.setVisibility(View.GONE);
                    txt_EngineYesNo.setVisibility(View.GONE);
                    txt_ChasisYesNo.setVisibility(View.GONE);
                    txt_TarctorTypeYesNo.setVisibility(View.GONE);
                    txt_RentYesNo.setVisibility(View.GONE);
                    txt_RotavetorYesNo.setVisibility(View.GONE);
                    txt_SpeedDreelYesNo.setVisibility(View.GONE);
                    txt_GardenYesNo.setVisibility(View.GONE);

                } else {
                    //  LinSpinner.setVisibility(View.VISIBLE);
                    //  LinTextViewData.setVisibility(View.GONE);
                    if(response.body().getData().get(0).getSmart_card()==null){
                        edt_SmartCard_no.setFocusable(true);
                    }
                    else{
                        edt_SmartCard_no.setFocusable(false);
                        edt_SmartCard_no.setText(response.body().getData().get(0).getSmart_card());
                    }

                     if(response.body().getData().get(0).getSmart_card_date()==null){
                        edt_SmartCard_Date.setFocusable(true);
                    }
                    else{
                         edt_SmartCard_Date.setFocusable(false);
                         edt_SmartCard_Date.setClickable(false);
                         edt_SmartCard_Date.setText(response.body().getData().get(0).getSmart_card_date());
                     }


                    if (response.body().getData().get(0).getSave_mobile_no() == null) {

                        lin_SaveMobile.setVisibility(View.GONE);
                        txt_save_mobile_no.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getSave_mobile_no().equals("Yes")) {
                        txt_save_mobile_no.setText("Save Mobile: " + response.body().getData().get(0).getSave_mobile_no());
                        spn_save_mobile_no.setVisibility(View.GONE);


                        if (response.body().getData().get(0).getSave_mobile_no_img() == null) {
                            img_saveMobile.setVisibility(View.GONE);
                        } else {

                            img_saveMobile.setVisibility(View.VISIBLE);
                            lin_SaveMobile.setVisibility(View.VISIBLE);
                            txt_saveMobile.setVisibility(View.GONE);
                            Glide.with(GaneralListFormActivity.this)
                                    .load("http://asworldtech.com/sonalika/delivery_report/"
                                            + response.body().getData().get(0).getSave_mobile_no_img())
                                    .into(img_saveMobile);
                        }

                    } else if (response.body().getData().get(0).getSave_mobile_no().equals("No")) {
                       /* txt_save_mobile_no.setText("Save Mobile: " + response.body().getData().get(0).getSave_mobile_no());
                        spn_save_mobile_no.setVisibility(View.GONE);*/

                        txt_save_mobile_no.setVisibility(View.GONE);
                        spn_save_mobile_no.setVisibility(View.VISIBLE);
                    } else {
                        txt_save_mobile_no.setVisibility(View.GONE);
                        spn_save_mobile_no.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getCol_wh_no() == null) {

                        edit_collect_whatsapp_no.setVisibility(View.GONE);
                        txt_collect_whatsapp_no.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getCol_wh_no().equals("Yes")) {
                        txt_collect_whatsapp_no.setText("Whats App No: " + response.body().getData().get(0).getCol_wh_no());
                        spn_collect_whatsapp_no.setVisibility(View.GONE);


                        if (response.body().getData().get(0).getWhatsapp_no() == null) {
                            edit_collect_whatsapp_no.setVisibility(View.GONE);
                        } else {
                            edit_collect_whatsapp_no.setFocusable(false);
                            edit_collect_whatsapp_no.setVisibility(View.VISIBLE);
                            edit_collect_whatsapp_no.setText(response.body().getData().get(0).getWhatsapp_no());
                        }

                    } else if (response.body().getData().get(0).getCol_wh_no().equals("No")) {
                      /*  txt_collect_whatsapp_no.setText("Whats App No: " + response.body().getData().get(0).getCol_wh_no());
                        spn_collect_whatsapp_no.setVisibility(View.GONE);*/
                          txt_collect_whatsapp_no.setVisibility(View.GONE);
                        spn_collect_whatsapp_no.setVisibility(View.VISIBLE);
                    } else {
                        txt_collect_whatsapp_no.setVisibility(View.GONE);
                        spn_collect_whatsapp_no.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getSub_youtube_ch() == null) {

                        lin_subscribeYtChannel.setVisibility(View.GONE);
                        txt_subscribe_youtube_chanel.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getSub_youtube_ch().equals("Yes")) {
                        txt_subscribe_youtube_chanel.setText("Subscribe YouTube Channel: " + response.body().getData().get(0).getSub_youtube_ch());
                        spn_subscribe_youtube_chanel.setVisibility(View.GONE);


                        if (response.body().getData().get(0).getSub_youtube_ch_img() == null) {
                            img_SubYtChannel.setVisibility(View.GONE);
                        } else {
                            img_SubYtChannel.setVisibility(View.VISIBLE);
                            lin_subscribeYtChannel.setVisibility(View.VISIBLE);
                            txt_SubYtChannel.setVisibility(View.GONE);
                            Glide.with(GaneralListFormActivity.this)
                                    .load("http://asworldtech.com/sonalika/delivery_report/"
                                            + response.body().getData().get(0).getSub_youtube_ch_img())
                                    .into(img_SubYtChannel);
                        }

                    } else if (response.body().getData().get(0).getSub_youtube_ch().equals("No")) {
                       /* txt_subscribe_youtube_chanel.setText("Subscribe YouTube Channel: " + response.body().getData().get(0).getSub_youtube_ch());
                        spn_subscribe_youtube_chanel.setVisibility(View.GONE);*/

                        txt_subscribe_youtube_chanel.setVisibility(View.GONE);
                        spn_subscribe_youtube_chanel.setVisibility(View.VISIBLE);

                    } else {
                        txt_subscribe_youtube_chanel.setVisibility(View.GONE);
                        spn_subscribe_youtube_chanel.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getLike_fb_insta_page() == null) {

                        lin_LikeFbInsta.setVisibility(View.GONE);
                        txt_like_fb_insta_page.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getLike_fb_insta_page().equals("Yes")) {
                        txt_like_fb_insta_page.setText("Like Insta/FB Page: " + response.body().getData().get(0).getLike_fb_insta_page());
                        spn_like_fb_insta_page.setVisibility(View.GONE);


                        if (response.body().getData().get(0).getLike_fb_insta_page_img() == null) {
                            img_LikeFbInsta.setVisibility(View.GONE);
                        } else {
                            img_LikeFbInsta.setVisibility(View.VISIBLE);
                            lin_LikeFbInsta.setVisibility(View.VISIBLE);
                            txt_LikeFbInsta.setVisibility(View.GONE);
                            Glide.with(GaneralListFormActivity.this)
                                    .load("http://asworldtech.com/sonalika/delivery_report/"
                                            + response.body().getData().get(0).getLike_fb_insta_page_img())
                                    .into(img_LikeFbInsta);
                        }
                    } else if (response.body().getData().get(0).getLike_fb_insta_page().equals("No")) {
                       /* txt_like_fb_insta_page.setText("Like Insta/FB Page: " + response.body().getData().get(0).getLike_fb_insta_page());
                        spn_like_fb_insta_page.setVisibility(View.GONE);*/

                        txt_like_fb_insta_page.setVisibility(View.GONE);
                        spn_like_fb_insta_page.setVisibility(View.VISIBLE);

                    } else {
                        txt_like_fb_insta_page.setVisibility(View.GONE);
                        spn_like_fb_insta_page.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getLike_fb_insta_page() == null) {

                        lin_toolkitDelivery.setVisibility(View.GONE);
                        txt_toolkit_delivery.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getToolkit_delivery().equals("Yes")) {
                        txt_toolkit_delivery.setText("ToolKit Delivery: " + response.body().getData().get(0).getToolkit_delivery());
                        spn_toolkit_delivery.setVisibility(View.GONE);


                        if (response.body().getData().get(0).getToolkit_delivery_img() == null) {
                            img_toolKitDeli.setVisibility(View.GONE);
                        } else {
                            img_toolKitDeli.setVisibility(View.VISIBLE);
                            lin_toolkitDelivery.setVisibility(View.VISIBLE);
                            txt_toolKitDeli.setVisibility(View.GONE);
                            Glide.with(GaneralListFormActivity.this)
                                    .load("http://asworldtech.com/sonalika/delivery_report/"
                                            + response.body().getData().get(0).getToolkit_delivery_img())
                                    .into(img_toolKitDeli);
                        }

                    } else if (response.body().getData().get(0).getToolkit_delivery().equals("No")) {
                       /* txt_toolkit_delivery.setText("ToolKit Delivery: " + response.body().getData().get(0).getToolkit_delivery());
                        spn_toolkit_delivery.setVisibility(View.GONE);*/

                        txt_toolkit_delivery.setVisibility(View.GONE);
                        spn_toolkit_delivery.setVisibility(View.VISIBLE);

                    } else {
                        txt_toolkit_delivery.setVisibility(View.GONE);
                        spn_toolkit_delivery.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getLike_fb_insta_page() == null) {

                        lin_keyDelivery.setVisibility(View.GONE);
                        txt_key_delivery.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getKey_delivery().equals("Yes")) {
                        txt_key_delivery.setText("Key Delivary: " + response.body().getData().get(0).getKey_delivery());
                        spn_key_delivery.setVisibility(View.GONE);

                        if (response.body().getData().get(0).getKey_delivery_img() == null) {
                            img_keyDelivery.setVisibility(View.GONE);
                        } else {
                            img_keyDelivery.setVisibility(View.VISIBLE);
                            lin_keyDelivery.setVisibility(View.VISIBLE);
                            txt_keyDelivery.setVisibility(View.GONE);
                            Glide.with(GaneralListFormActivity.this)
                                    .load("http://asworldtech.com/sonalika/delivery_report/"
                                            + response.body().getData().get(0).getKey_delivery_img())
                                    .into(img_keyDelivery);
                        }
                    } else if (response.body().getData().get(0).getKey_delivery().equals("No")) {
                       /* txt_key_delivery.setText("Key Delivary: " + response.body().getData().get(0).getKey_delivery());
                        spn_key_delivery.setVisibility(View.GONE);*/

                         txt_key_delivery.setVisibility(View.GONE);
                        spn_key_delivery.setVisibility(View.VISIBLE);
                    } else {
                        txt_key_delivery.setVisibility(View.GONE);
                        spn_key_delivery.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getCheck_document() == null) {

                        lin_checkAmount.setVisibility(View.GONE);
                        txt_check_document.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getCheck_document().equals("Yes")) {
                        txt_check_document.setText("Check Document: " + response.body().getData().get(0).getCheck_document());
                        spn_check_document.setVisibility(View.GONE);

                        if (response.body().getData().get(0).getCheck_document_img() == null) {
                            img_checkAmount.setVisibility(View.GONE);
                        } else {
                            img_checkAmount.setVisibility(View.VISIBLE);
                            lin_checkAmount.setVisibility(View.VISIBLE);
                            txt_checkAmount.setVisibility(View.GONE);
                            Glide.with(GaneralListFormActivity.this)
                                    .load("http://asworldtech.com/sonalika/delivery_report/"
                                            + response.body().getData().get(0).getCheck_document_img())
                                    .into(img_checkAmount);
                        }
                    } else if (response.body().getData().get(0).getCheck_document().equals("No")) {
                       /* txt_check_document.setText("Check Document: " + response.body().getData().get(0).getCheck_document());
                        spn_check_document.setVisibility(View.GONE);*/

                        txt_check_document.setVisibility(View.GONE);
                        spn_check_document.setVisibility(View.VISIBLE);
                    } else {
                        txt_check_document.setVisibility(View.GONE);
                        spn_check_document.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getWarranty_pollicy_discuusion() == null) {

                        lin_warrentyPolicy.setVisibility(View.GONE);
                        txt_warranty_pollicy_discuusion.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getWarranty_pollicy_discuusion().equals("Yes")) {
                        txt_warranty_pollicy_discuusion.setText("Warranty Policy Discussion " + response.body().getData().get(0).getWarranty_pollicy_discuusion());
                        edt_warranty_pollicy_discuusion.setVisibility(View.GONE);


                        if (response.body().getData().get(0).getWarranty_pollicy_discuusion_img() == null) {
                            img_WarrentyPolicy.setVisibility(View.GONE);
                        } else {
                            img_WarrentyPolicy.setVisibility(View.VISIBLE);
                            lin_warrentyPolicy.setVisibility(View.VISIBLE);
                            txt_WarrentyPolicy.setVisibility(View.GONE);
                            Glide.with(GaneralListFormActivity.this)
                                    .load("http://asworldtech.com/sonalika/delivery_report/"
                                            + response.body().getData().get(0).getWarranty_pollicy_discuusion_img())
                                    .into(img_WarrentyPolicy);
                        }
                    } else if (response.body().getData().get(0).getWarranty_pollicy_discuusion().equals("No")) {
                       /* txt_warranty_pollicy_discuusion.setText("Warranty Policy Discussion " + response.body().getData().get(0).getWarranty_pollicy_discuusion());
                        edt_warranty_pollicy_discuusion.setVisibility(View.GONE);*/

                         txt_warranty_pollicy_discuusion.setVisibility(View.GONE);
                        edt_warranty_pollicy_discuusion.setVisibility(View.VISIBLE);
                    } else {
                        txt_warranty_pollicy_discuusion.setVisibility(View.GONE);
                        edt_warranty_pollicy_discuusion.setVisibility(View.VISIBLE);
                    }


                   // Toast.makeText(GaneralListFormActivity.this, ""+response.body().getData().get(0).getCheck_oil_level(), Toast.LENGTH_SHORT).show();

                    if (response.body().getData().get(0).getCheck_oil_level() == null) {

                        lin_checkOil.setVisibility(View.GONE);
                        txt_check_oil_level.setVisibility(View.GONE);

                    } else if (response.body().getData().get(0).getCheck_oil_level().equals("Yes")) {
                        txt_check_oil_level.setText("Check Oil Level: " + response.body().getData().get(0).getCheck_oil_level());
                        edt_check_oil_level.setVisibility(View.GONE);


                        if (response.body().getData().get(0).getCheck_oil_level_img() == null) {
                            img_checkOil.setVisibility(View.GONE);
                        } else {
                            img_checkOil.setVisibility(View.VISIBLE);
                            lin_checkOil.setVisibility(View.VISIBLE);
                            txt_checkOil.setVisibility(View.GONE);
                            Glide.with(GaneralListFormActivity.this)
                                    .load("http://asworldtech.com/sonalika/delivery_report/"
                                            + response.body().getData().get(0).getCheck_oil_level_img())
                                    .into(img_checkOil);
                        }

                    } else if (response.body().getData().get(0).getCheck_oil_level().equals("No")) {
                       /* txt_check_oil_level.setText("Check Oil Level: " + response.body().getData().get(0).getCheck_oil_level());
                        edt_check_oil_level.setVisibility(View.GONE);*/
                        txt_check_oil_level.setVisibility(View.GONE);
                        edt_check_oil_level.setVisibility(View.VISIBLE);
                    } else {
                        txt_check_oil_level.setVisibility(View.GONE);
                        edt_check_oil_level.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getCheck_accessories() == null) {

                        lin_checkAccesories.setVisibility(View.GONE);
                        txt_check_accessories.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getCheck_accessories().equals("Yes")) {
                        txt_check_accessories.setText("Check Accessories: " + response.body().getData().get(0).getCheck_accessories());
                        edt_check_accessories.setVisibility(View.GONE);


                        if (response.body().getData().get(0).getCheck_accessories_img() == null) {
                            img_checkAsse.setVisibility(View.GONE);
                        } else {
                            img_checkAsse.setVisibility(View.VISIBLE);
                            lin_checkAccesories.setVisibility(View.VISIBLE);
                            txt_checkAsse.setVisibility(View.GONE);
                            Glide.with(GaneralListFormActivity.this)
                                    .load("http://asworldtech.com/sonalika/delivery_report/"
                                            + response.body().getData().get(0).getCheck_accessories_img())
                                    .into(img_checkAsse);
                        }

                    } else if (response.body().getData().get(0).getCheck_accessories().equals("No")) {
                       /* txt_check_accessories.setText("Check Accessories: " + response.body().getData().get(0).getCheck_accessories());
                        edt_check_accessories.setVisibility(View.GONE);*/

                        txt_check_accessories.setVisibility(View.GONE);
                        edt_check_accessories.setVisibility(View.VISIBLE);
                    } else {
                        txt_check_accessories.setVisibility(View.GONE);
                        edt_check_accessories.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getInstallation() == null) {

                        linInstallation.setVisibility(View.GONE);
                        txt_installation.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getInstallation().equals("Yes")) {
                        txt_installation.setText("Installtion: " + response.body().getData().get(0).getInstallation());
                        edt_installation.setVisibility(View.GONE);


                        if (response.body().getData().get(0).getInstallation_img() == null) {
                            img_Installation.setVisibility(View.GONE);
                        } else {
                            img_Installation.setVisibility(View.VISIBLE);
                            linInstallation.setVisibility(View.VISIBLE);
                            txt_Installtion.setVisibility(View.GONE);
                            Glide.with(GaneralListFormActivity.this)
                                    .load("http://asworldtech.com/sonalika/delivery_report/"
                                            + response.body().getData().get(0).getInstallation_img())
                                    .into(img_Installation);
                        }
                    } else if (response.body().getData().get(0).getInstallation().equals("No")) {
                       /* txt_installation.setText("Installtion: " + response.body().getData().get(0).getInstallation());
                        edt_installation.setVisibility(View.GONE);*/

                        txt_installation.setVisibility(View.GONE);
                        edt_installation.setVisibility(View.VISIBLE);
                    } else {
                        txt_installation.setVisibility(View.GONE);
                        edt_installation.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getName_plat() == null) {

                        lin_NamePlat.setVisibility(View.GONE);
                        txt_name_plat.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getName_plat().equals("Yes")) {
                        txt_name_plat.setText("Name Plate: " + response.body().getData().get(0).getName_plat());
                        Spn_name_plat.setVisibility(View.GONE);


                        if (response.body().getData().get(0).getCheck_accessories_img() == null) {
                            img_NamePlat.setVisibility(View.GONE);
                        } else {
                            img_NamePlat.setVisibility(View.VISIBLE);
                            lin_NamePlat.setVisibility(View.VISIBLE);
                            txt_NamePlat.setVisibility(View.GONE);
                            Glide.with(GaneralListFormActivity.this)
                                    .load("http://asworldtech.com/sonalika/delivery_report/"
                                            + response.body().getData().get(0).getCheck_accessories_img())
                                    .into(img_checkAsse);
                        }
                    } else if (response.body().getData().get(0).getName_plat().equals("No")) {
                      /*  txt_name_plat.setText("Name Plate: " + response.body().getData().get(0).getName_plat());
                        Spn_name_plat.setVisibility(View.GONE);*/
                          txt_name_plat.setVisibility(View.GONE);
                        Spn_name_plat.setVisibility(View.VISIBLE);
                    } else {
                        txt_name_plat.setVisibility(View.GONE);
                        Spn_name_plat.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getTractor_number_plat() == null) {

                        lin_tractNoPlat.setVisibility(View.GONE);
                        txt_tractor_number_plat.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getTractor_number_plat().equals("Yes")) {
                        txt_tractor_number_plat.setText("Tractor Number Plat:  " + response.body().getData().get(0).getTractor_number_plat());
                        edt_tractor_number_plat.setVisibility(View.GONE);

                        if (response.body().getData().get(0).getTractor_number_plat_img() == null) {
                            img_tractNoPlat.setVisibility(View.GONE);
                        } else {
                            img_tractNoPlat.setVisibility(View.VISIBLE);
                            lin_tractNoPlat.setVisibility(View.VISIBLE);
                            txt_tractNoPlat.setVisibility(View.GONE);
                            Glide.with(GaneralListFormActivity.this)
                                    .load("http://asworldtech.com/sonalika/delivery_report/"
                                            + response.body().getData().get(0).getTractor_number_plat_img())
                                    .into(img_tractNoPlat);
                        }
                    } else if (response.body().getData().get(0).getTractor_number_plat().equals("No")) {
                      /*  txt_tractor_number_plat.setText("Tractor Number Plat:  " + response.body().getData().get(0).getTractor_number_plat());
                        edt_tractor_number_plat.setVisibility(View.GONE);*/

                        txt_tractor_number_plat.setVisibility(View.GONE);
                        edt_tractor_number_plat.setVisibility(View.GONE);
                    } else {
                        txt_tractor_number_plat.setVisibility(View.GONE);
                        edt_tractor_number_plat.setVisibility(View.GONE);
                    }


                    if (response.body().getData().get(0).getDelivey_photo() == null) {

                        lin_DeliveryPho.setVisibility(View.GONE);
                        txt_DeliveryYesNo.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getDelivey_photo().equals("Yes")) {
                        txt_DeliveryYesNo.setText("Delivery Photo: " + response.body().getData().get(0).getDelivey_photo());
                        Spn_deliveryPhoto.setVisibility(View.GONE);

                        if (response.body().getData().get(0).getDelivey_photo_img() == null) {
                            img_deliveryPhoto.setVisibility(View.GONE);
                        } else {
                            img_deliveryPhoto.setVisibility(View.VISIBLE);
                            lin_DeliveryPho.setVisibility(View.VISIBLE);
                            txt_DeliveryPhoto.setVisibility(View.GONE);
                            Glide.with(GaneralListFormActivity.this)
                                    .load("http://asworldtech.com/sonalika/delivery_report/"
                                            + response.body().getData().get(0).getDelivey_photo_img())
                                    .into(img_deliveryPhoto);
                        }
                    } else if (response.body().getData().get(0).getDelivey_photo().equals("No")) {
                       /* txt_DeliveryYesNo.setText("Delivery Photo: " + response.body().getData().get(0).getDelivey_photo());
                        Spn_deliveryPhoto.setVisibility(View.GONE);*/
                          txt_DeliveryYesNo.setVisibility(View.GONE);
                        Spn_deliveryPhoto.setVisibility(View.VISIBLE);
                    } else {
                        txt_DeliveryYesNo.setVisibility(View.GONE);
                        Spn_deliveryPhoto.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getDelivey_photo() == null) {

                        lin_DeliveryPho.setVisibility(View.GONE);
                        txt_DeliveryYesNo.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getChasis_print().equals("Yes")) {
                        txt_ChaisPrintYesNo.setText("Chasis Print: " + response.body().getData().get(0).getChasis_print());
                        Spn_ChasisPhoto.setVisibility(View.GONE);

                        if (response.body().getData().get(0).getChasis_print_img() == null) {
                            img_ChasisPhoto.setVisibility(View.GONE);
                        } else {
                            img_ChasisPhoto.setVisibility(View.VISIBLE);
                            lin_ChasisPhoto.setVisibility(View.VISIBLE);
                            txt_ChasisPhoto.setVisibility(View.GONE);
                            Glide.with(GaneralListFormActivity.this)
                                    .load("http://asworldtech.com/sonalika/delivery_report/"
                                            + response.body().getData().get(0).getChasis_print_img())
                                    .into(img_ChasisPhoto);
                        }
                    } else if (response.body().getData().get(0).getChasis_print().equals("No")) {
                      /*  txt_ChaisPrintYesNo.setText("Chasis Print: " + response.body().getData().get(0).getChasis_print());
                        Spn_ChasisPhoto.setVisibility(View.GONE);*/

                        txt_ChaisPrintYesNo.setVisibility(View.GONE);
                        Spn_ChasisPhoto.setVisibility(View.VISIBLE);
                    } else {
                        txt_ChaisPrintYesNo.setVisibility(View.GONE);
                        Spn_ChasisPhoto.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getEngine_no()== null) {
                        txt_EngineYesNo.setVisibility(View.GONE);
                        Spn_engineNo.setVisibility(View.VISIBLE);

                    }

                   else if (response.body().getData().get(0).getEngine_no().equals("Yes")) {
                        txt_EngineYesNo.setText("Engine No: " + response.body().getData().get(0).getEngine_no());
                        Spn_engineNo.setVisibility(View.GONE);

                    } else if (response.body().getData().get(0).getEngine_no().equals("No")) {
                       /* txt_EngineYesNo.setText("Engine No: " + response.body().getData().get(0).getEngine_no());
                        Spn_engineNo.setVisibility(View.GONE);*/

                        txt_EngineYesNo.setVisibility(View.GONE);
                        Spn_engineNo.setVisibility(View.VISIBLE);

                    } else {
                        txt_EngineYesNo.setVisibility(View.GONE);
                        Spn_engineNo.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getChasis_no() == null) {
                        txt_ChasisYesNo.setVisibility(View.GONE);
                        Spn_ChasisNo.setVisibility(View.VISIBLE);
                    } else if (response.body().getData().get(0).getChasis_no().equals("Yes")) {
                        txt_ChasisYesNo.setText("Chasis No: " + response.body().getData().get(0).getChasis_no());
                        Spn_ChasisNo.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getChasis_no().equals("No")) {
                        /*txt_ChasisYesNo.setText("Chasis No: " + response.body().getData().get(0).getChasis_no());
                        Spn_ChasisNo.setVisibility(View.GONE);*/

                        txt_ChasisYesNo.setVisibility(View.GONE);
                        Spn_ChasisNo.setVisibility(View.VISIBLE);
                    } else {
                        txt_ChasisYesNo.setVisibility(View.GONE);
                        Spn_ChasisNo.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getTractor_type() == null) {
                        txt_TarctorTypeYesNo.setVisibility(View.GONE);
                        spn_Tractortype.setVisibility(View.VISIBLE);
                    } else if (response.body().getData().get(0).getTractor_type().equals("Big") ||
                            response.body().getData().get(0).getTractor_type().equals("Mini")) {
                       /* txt_TarctorTypeYesNo.setText("Tractor Type: " + response.body().getData().get(0).getTractor_type());
                        spn_Tractortype.setVisibility(View.GONE);*/

                        txt_TarctorTypeYesNo.setVisibility(View.GONE);
                        spn_Tractortype.setVisibility(View.VISIBLE);
                    } else {
                        txt_TarctorTypeYesNo.setVisibility(View.GONE);
                        spn_Tractortype.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getRent() == null) {
                        txt_RentYesNo.setVisibility(View.GONE);
                        spn_Rent.setVisibility(View.VISIBLE);
                    } else if (response.body().getData().get(0).getRent().equals("Full Time") ||
                            response.body().getData().get(0).getRent().equals("Part Time") ||
                            response.body().getData().get(0).getRent().equals("Self")) {
                        txt_RentYesNo.setText("Rent: " + response.body().getData().get(0).getRent());
                        spn_Rent.setVisibility(View.GONE);
                    } else {
                        txt_RentYesNo.setVisibility(View.GONE);
                        spn_Rent.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getRotavator() == null) {
                        txt_RotavetorYesNo.setVisibility(View.GONE);
                        spn_Rotavator.setVisibility(View.VISIBLE);
                    } else if (response.body().getData().get(0).getRotavator().equals("Yes")) {
                        txt_RotavetorYesNo.setText("Rotavetor: " + response.body().getData().get(0).getRotavator());
                        spn_Rotavator.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getRotavator().equals("No")) {
                       /* txt_RotavetorYesNo.setText("Rotavetor: " + response.body().getData().get(0).getRotavator());
                        spn_Rotavator.setVisibility(View.GONE);*/
                        txt_RotavetorYesNo.setVisibility(View.GONE);
                        spn_Rotavator.setVisibility(View.VISIBLE);

                    } else {
                        txt_RotavetorYesNo.setVisibility(View.GONE);
                        spn_Rotavator.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getSpeed_dreel() == null) {
                        txt_SpeedDreelYesNo.setVisibility(View.GONE);
                        spn_SpeedDrel.setVisibility(View.VISIBLE);
                    } else if (response.body().getData().get(0).getSpeed_dreel().equals("Yes")) {
                        txt_SpeedDreelYesNo.setText("Seed Dreel: " + response.body().getData().get(0).getSpeed_dreel());
                        spn_SpeedDrel.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getSpeed_dreel().equals("No")) {
                       /* txt_SpeedDreelYesNo.setText("Seed Dreel: " + response.body().getData().get(0).getSpeed_dreel());
                        spn_SpeedDrel.setVisibility(View.GONE);*/

                        txt_SpeedDreelYesNo.setVisibility(View.GONE);
                        spn_SpeedDrel.setVisibility(View.VISIBLE);
                    } else {
                        txt_SpeedDreelYesNo.setVisibility(View.GONE);
                        spn_SpeedDrel.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getGardan() == null) {
                        txt_GardenYesNo.setVisibility(View.GONE);
                        spn_Garden.setVisibility(View.VISIBLE);
                    } else if (response.body().getData().get(0).getGardan().equals("Yes")) {
                        txt_GardenYesNo.setText("Garden: " + response.body().getData().get(0).getGardan());
                        spn_Garden.setVisibility(View.GONE);
                    } else if (response.body().getData().get(0).getGardan().equals("No")) {
                       /* txt_GardenYesNo.setText("Garden: " + response.body().getData().get(0).getGardan());
                        spn_Garden.setVisibility(View.GONE);*/

                        txt_GardenYesNo.setVisibility(View.GONE);
                        spn_Garden.setVisibility(View.VISIBLE);
                    } else {
                        txt_GardenYesNo.setVisibility(View.GONE);
                        spn_Garden.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(0).getModel_name() == null) {
                        edt_modelName.setVisibility(View.VISIBLE);
                    } else {
                        edt_modelName.setText(response.body().getData().get(0).getModel_name());
                        edt_modelName.setFocusable(false);
                    }

                    if (response.body().getData().get(0).getModel_year() == null) {
                        edt_modelYear.setVisibility(View.VISIBLE);
                    } else {
                        edt_modelYear.setText(response.body().getData().get(0).getModel_year());
                        edt_modelYear.setFocusable(false);
                    }


                    if (response.body().getData().get(0).getLand_recored() == null) {
                        edt_LandRecord.setVisibility(View.VISIBLE);
                    } else {
                        edt_LandRecord.setText(response.body().getData().get(0).getLand_recored());
                        edt_LandRecord.setFocusable(false);
                    }


                }

            }

            @Override
            public void onFailure(@NotNull Call<DelievryReportViewGenListModel> call, @NotNull Throwable t) {
                progressDialog1.dismiss();
            }
        });


        ArrayAdapter consumerSkim = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                SaveMobile);
        spn_save_mobile_no.setAdapter(consumerSkim);

        spn_save_mobile_no.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Save_mobile = SaveMobile[position];
                if (Save_mobile.equals("Yes")) {
                    lin_SaveMobile.setVisibility(View.VISIBLE);
                } else {
                    lin_SaveMobile.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter wtaNo = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                collectWhatsappNo);
        spn_collect_whatsapp_no.setAdapter(wtaNo);

        spn_collect_whatsapp_no.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                collect_whatsapp_no = collectWhatsappNo[position];
                if (collect_whatsapp_no.equals("Yes")) {
                    edit_collect_whatsapp_no.setVisibility(View.VISIBLE);

                    // lin_CallWhtaNo.setVisibility(View.VISIBLE);
                } else {
                    // lin_CallWhtaNo.setVisibility(View.GONE);
                    edit_collect_whatsapp_no.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter YTChannel = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                subscribeYoutubeChanel);
        spn_subscribe_youtube_chanel.setAdapter(YTChannel);

        spn_subscribe_youtube_chanel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subscribe_youtube_chanel = subscribeYoutubeChanel[position];
                if (subscribe_youtube_chanel.equals("Yes")) {
                    lin_subscribeYtChannel.setVisibility(View.VISIBLE);
                } else {
                    lin_subscribeYtChannel.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter FbInsta = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                likeFbInstaPage);
        spn_like_fb_insta_page.setAdapter(FbInsta);

        spn_like_fb_insta_page.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                like_fb_insta_page = likeFbInstaPage[position];

                if (like_fb_insta_page.equals("Yes")) {
                    lin_LikeFbInsta.setVisibility(View.VISIBLE);
                } else {
                    lin_LikeFbInsta.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter Toolkit = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                ToolkitDelivery);
        spn_toolkit_delivery.setAdapter(Toolkit);

        spn_toolkit_delivery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toolkit_Delivery = ToolkitDelivery[position];

                if (Toolkit_Delivery.equals("Yes")) {
                    lin_toolkitDelivery.setVisibility(View.VISIBLE);
                } else {
                    lin_toolkitDelivery.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter keyDe = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                keyDelivery);
        spn_key_delivery.setAdapter(keyDe);

        spn_key_delivery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                key_delivery = keyDelivery[position];

                if (key_delivery.equals("Yes")) {
                    lin_keyDelivery.setVisibility(View.VISIBLE);
                } else {
                    lin_keyDelivery.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter cd = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                checkDocument);
        spn_check_document.setAdapter(cd);

        spn_check_document.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                check_document = checkDocument[position];

                if (check_document.equals("Yes")) {
                    lin_checkAmount.setVisibility(View.VISIBLE);
                } else {
                    lin_checkAmount.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter warrentyPD = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                warrantyPollicyDiscuusion);
        edt_warranty_pollicy_discuusion.setAdapter(warrentyPD);

        edt_warranty_pollicy_discuusion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                warranty_pollicy_discuusion = warrantyPollicyDiscuusion[position];

                if (warranty_pollicy_discuusion.equals("Yes")) {
                    lin_warrentyPolicy.setVisibility(View.VISIBLE);
                } else {
                    lin_warrentyPolicy.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter OilLevel = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                checkOilLevel);
        edt_check_oil_level.setAdapter(OilLevel);

        edt_check_oil_level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                check_oil_level = checkOilLevel[position];
                if (check_oil_level.equals("Yes")) {
                    lin_checkOil.setVisibility(View.VISIBLE);
                } else {
                    lin_checkOil.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter checkAcessories = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                checkAccessories);
        edt_check_accessories.setAdapter(checkAcessories);

        edt_check_accessories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                check_accessories = checkAccessories[position];

                if (check_accessories.equals("Yes")) {
                    lin_checkAccesories.setVisibility(View.VISIBLE);
                } else {
                    lin_checkAccesories.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter instala = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Installation);
        edt_installation.setAdapter(instala);

        edt_installation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                installation = Installation[position];

                if (installation.equals("Yes")) {
                    linInstallation.setVisibility(View.VISIBLE);
                } else {
                    linInstallation.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter nameplat = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                namePlat);
        Spn_name_plat.setAdapter(nameplat);

        Spn_name_plat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                name_plat = namePlat[position];

                if (name_plat.equals("Yes")) {
                    lin_NamePlat.setVisibility(View.VISIBLE);
                } else {
                    lin_NamePlat.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter tractNumberPlat = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                tractorNumberPlat);
        edt_tractor_number_plat.setAdapter(tractNumberPlat);

        edt_tractor_number_plat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tractor_number_plat = tractorNumberPlat[position];
                if (tractor_number_plat.equals("Yes")) {
                    lin_tractNoPlat.setVisibility(View.VISIBLE);
                } else {
                    lin_tractNoPlat.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter Fservice = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                fisrtServices);
        edt_fisrt_services.setAdapter(Fservice);

        edt_fisrt_services.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fisrt_services = fisrtServices[position];
                if (fisrt_services.equals("Yes")) {
                    lin_firstService.setVisibility(View.VISIBLE);
                } else {
                    lin_firstService.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter secondS = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                secondServices);
        edt_second_services.setAdapter(secondS);

        edt_second_services.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                second_services = secondServices[position];

                if (second_services.equals("Yes")) {
                    lin_SecondService.setVisibility(View.VISIBLE);
                } else {
                    lin_SecondService.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter ThirdS = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                thirdServices);
        edt_third_services.setAdapter(ThirdS);

        edt_third_services.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                third_services = thirdServices[position];
                if (third_services.equals("Yes")) {
                    lin_thirdService.setVisibility(View.VISIBLE);
                } else {
                    lin_thirdService.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter deliveryPhoto = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                DeliveryPhoto);
        Spn_deliveryPhoto.setAdapter(deliveryPhoto);

        Spn_deliveryPhoto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Delivery_Photo = DeliveryPhoto[position];
                if (Delivery_Photo.equals("Yes")) {
                    lin_DeliveryPho.setVisibility(View.VISIBLE);
                } else {
                    lin_DeliveryPho.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter ChasisPhoto = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                ChasisPrintPhoto);
        Spn_ChasisPhoto.setAdapter(ChasisPhoto);

        Spn_ChasisPhoto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Chasis_PrintPhoto = ChasisPrintPhoto[position];
                if (Chasis_PrintPhoto.equals("Yes")) {
                    lin_ChasisPhoto.setVisibility(View.VISIBLE);
                } else {
                    lin_ChasisPhoto.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter spnEngineNo = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                EngineNO);
        Spn_engineNo.setAdapter(spnEngineNo);

        Spn_engineNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Engine_Photo = EngineNO[position];
                if (Engine_Photo.equals("Yes")) {
                    edt_engine_no.setVisibility(View.VISIBLE);
                } else {
                    edt_engine_no.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter spnCHasisNo = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                ChasisNo);
        Spn_ChasisNo.setAdapter(spnCHasisNo);

        Spn_ChasisNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Chasis_No = ChasisNo[position];
                if (Chasis_No.equals("Yes")) {
                    edt_chasis_no.setVisibility(View.VISIBLE);
                } else {
                    edt_chasis_no.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter tractTYpe = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                TractorType);
        spn_Tractortype.setAdapter(tractTYpe);

        spn_Tractortype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Tractor_Type = TractorType[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter rentType = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                RentType);
        spn_Rent.setAdapter(rentType);

        spn_Rent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Rent_Type = RentType[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter rotavatorType = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                RotavetorType);
        spn_Rotavator.setAdapter(rotavatorType);

        spn_Rotavator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Rotavetor_Type = RotavetorType[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter SpeedDreelAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                SpeedDreel);
        spn_SpeedDrel.setAdapter(SpeedDreelAdapter);

        spn_SpeedDrel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Speed_Dreel = SpeedDreel[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter gardenAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                GardenType);
        spn_Garden.setAdapter(gardenAdapter);


        spn_Garden.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Garden = GardenType[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //***************************************************************
        txt_saveMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 2);
            }
        });


        txt_colWhatNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 3);
            }
        });


        txt_SubYtChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 4);
            }
        });


        txt_LikeFbInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 5);
            }
        });


        txt_toolKitDeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 6);
            }
        });


        txt_keyDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 7);
            }
        });

        txt_checkAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 8);
            }
        });

        txt_WarrentyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 9);
            }
        });

        txt_checkOil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 10);
            }
        });


        txt_checkAsse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 11);
            }
        });

        txt_Installtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 12);
            }
        });

        txt_NamePlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 13);
            }
        });

        txt_tractNoPlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 14);
            }
        });

        txt_firstService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 15);
            }
        });

        txt_secService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 16);
            }
        });

        txt_thirdSer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 17);
            }
        });

        txt_DeliveryPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 18);
            }
        });

        txt_ChasisPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 19);
            }
        });


        //*******************************************************


        btn_Submit_gen_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                progressDialog = new ProgressDialog(GaneralListFormActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                MultipartBody.Part saveMobile = null;
                MultipartBody.Part colWhatNo = null;
                MultipartBody.Part SubYtChannel = null;
                MultipartBody.Part LikeFbInsta = null;
                MultipartBody.Part toolKitDeli = null;
                MultipartBody.Part keyDelivery = null;
                MultipartBody.Part checkAmount = null;
                MultipartBody.Part WarrentyPolicy = null;
                MultipartBody.Part checkOil = null;
                MultipartBody.Part checkAsse = null;

                MultipartBody.Part Installtion = null;
                MultipartBody.Part NamePlat = null;
                MultipartBody.Part tractNoPlat = null;
                MultipartBody.Part firstService = null;

                MultipartBody.Part secService = null;
                MultipartBody.Part thirdSer = null;

                MultipartBody.Part DeliveryPhoto = null;
                MultipartBody.Part ChasisPhoto = null;


                if (WayPath_saveMobile != null) {
                    File file1 = new File(WayPath_saveMobile);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    saveMobile = MultipartBody.Part.createFormData("image1",
                            file1.getName(), requestBody1);
                }

                if (WayPath_colWhatNo != null) {
                    File file2 = new File(WayPath_colWhatNo);
                    final RequestBody requestBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                    colWhatNo = MultipartBody.Part.createFormData("image2",
                            file2.getName(), requestBody2);
                }

                if (WayPath_SubYtChannel != null) {
                    File file3 = new File(WayPath_SubYtChannel);
                    final RequestBody requestBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                    SubYtChannel = MultipartBody.Part.createFormData("image3",
                            file3.getName(), requestBody3);
                }

                if (WayPath_LikeFbInsta != null) {
                    File file4 = new File(WayPath_LikeFbInsta);
                    final RequestBody requestBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                    LikeFbInsta = MultipartBody.Part.createFormData("image4",
                            file4.getName(), requestBody4);
                }


                if (WayPath_toolKitDeli != null) {
                    File file5 = new File(WayPath_toolKitDeli);
                    final RequestBody requestBody5 = RequestBody.create(MediaType.parse("image/*"), file5);
                    toolKitDeli = MultipartBody.Part.createFormData("image5",
                            file5.getName(), requestBody5);
                }

                if (WayPath_keyDelivery != null) {
                    File file6 = new File(WayPath_keyDelivery);
                    final RequestBody requestBody6 = RequestBody.create(MediaType.parse("image/*"), file6);
                    keyDelivery = MultipartBody.Part.createFormData("image6",
                            file6.getName(), requestBody6);
                }


                if (WayPath_checkAmount != null) {
                    File file8 = new File(WayPath_checkAmount);
                    final RequestBody requestBody8 = RequestBody.create(MediaType.parse("image/*"), file8);
                    checkAmount = MultipartBody.Part.createFormData("image7",
                            file8.getName(), requestBody8);
                }

                if (WayPath_WarrentyPolicy != null) {
                    File file9 = new File(WayPath_WarrentyPolicy);
                    final RequestBody requestBody9 = RequestBody.create(MediaType.parse("image/*"), file9);
                    WarrentyPolicy = MultipartBody.Part.createFormData("image8",
                            file9.getName(), requestBody9);
                }

                if (WayPath_checkOil != null) {
                    File file10 = new File(WayPath_checkOil);
                    final RequestBody requestBody10 = RequestBody.create(MediaType.parse("image/*"), file10);
                    checkOil = MultipartBody.Part.createFormData("image9",
                            file10.getName(), requestBody10);
                }

                if (WayPath_checkAsse != null) {
                    File file7 = new File(WayPath_checkAsse);
                    final RequestBody requestBody7 = RequestBody.create(MediaType.parse("image/*"), file7);
                    checkAsse = MultipartBody.Part.createFormData("image10",
                            file7.getName(), requestBody7);
                }


                if (WayPath_Installtion != null) {
                    File file11 = new File(WayPath_Installtion);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file11);
                    Installtion = MultipartBody.Part.createFormData("image11",
                            file11.getName(), requestBody11);
                }

                if (WayPath_NamePlat != null) {
                    File file12 = new File(WayPath_NamePlat);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file12);
                    NamePlat = MultipartBody.Part.createFormData("image12",
                            file12.getName(), requestBody11);
                }

                if (WayPath_tractNoPlat != null) {
                    File file13 = new File(WayPath_tractNoPlat);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file13);
                    tractNoPlat = MultipartBody.Part.createFormData("image13",
                            file13.getName(), requestBody11);
                }

                if (WayPath_firstService != null) {
                    File file14 = new File(WayPath_firstService);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file14);
                    firstService = MultipartBody.Part.createFormData("image14",
                            file14.getName(), requestBody11);
                }

                if (WayPath_secService != null) {
                    File file15 = new File(WayPath_secService);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file15);
                    secService = MultipartBody.Part.createFormData("image15",
                            file15.getName(), requestBody11);
                }


                if (WayPath_thirdSer != null) {
                    File file16 = new File(WayPath_thirdSer);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file16);
                    thirdSer = MultipartBody.Part.createFormData("image16",
                            file16.getName(), requestBody11);
                }


                if (WayPath_DeliveryPhoto != null) {
                    File file16 = new File(WayPath_DeliveryPhoto);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file16);
                    DeliveryPhoto = MultipartBody.Part.createFormData("image17",
                            file16.getName(), requestBody11);
                }

                if (WayPath_ChasisPhoto != null) {
                    File file16 = new File(WayPath_ChasisPhoto);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file16);
                    ChasisPhoto = MultipartBody.Part.createFormData("image18",
                            file16.getName(), requestBody11);
                }


                WebService.getClient().getDRGenListSubmit(
                        RequestBody.create(MediaType.parse("text/plain"), id),
                        RequestBody.create(MediaType.parse("text/plain"), cuid),
                        RequestBody.create(MediaType.parse("text/plain"), Save_mobile),
                        RequestBody.create(MediaType.parse("text/plain"), collect_whatsapp_no),
                        RequestBody.create(MediaType.parse("text/plain"), subscribe_youtube_chanel),
                        RequestBody.create(MediaType.parse("text/plain"), like_fb_insta_page),
                        RequestBody.create(MediaType.parse("text/plain"), Toolkit_Delivery),
                        RequestBody.create(MediaType.parse("text/plain"), key_delivery),
                        RequestBody.create(MediaType.parse("text/plain"), check_document),
                        RequestBody.create(MediaType.parse("text/plain"), warranty_pollicy_discuusion),
                        RequestBody.create(MediaType.parse("text/plain"), check_oil_level),
                        RequestBody.create(MediaType.parse("text/plain"), check_accessories),
                        RequestBody.create(MediaType.parse("text/plain"), installation),
                        RequestBody.create(MediaType.parse("text/plain"), name_plat),
                        RequestBody.create(MediaType.parse("text/plain"), tractor_number_plat),
                        RequestBody.create(MediaType.parse("text/plain"), Delivery_Photo),
                        RequestBody.create(MediaType.parse("text/plain"), Chasis_PrintPhoto),
                        RequestBody.create(MediaType.parse("text/plain"), Engine_Photo),
                        RequestBody.create(MediaType.parse("text/plain"), Chasis_No),
                        RequestBody.create(MediaType.parse("text/plain"), edt_engine_no.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_chasis_no.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), Tractor_Type),
                        RequestBody.create(MediaType.parse("text/plain"), edt_modelName.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_modelYear.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_LandRecord.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), Rent_Type),
                        RequestBody.create(MediaType.parse("text/plain"), Rotavetor_Type),
                        RequestBody.create(MediaType.parse("text/plain"), Speed_Dreel),
                        RequestBody.create(MediaType.parse("text/plain"), Garden),
                        RequestBody.create(MediaType.parse("text/plain"), edit_collect_whatsapp_no.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_SmartCard_no.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_SmartCard_Date.getText().toString()),
                        saveMobile,
                        colWhatNo,
                        SubYtChannel,
                        LikeFbInsta,
                        toolKitDeli,
                        keyDelivery,
                        checkAmount,
                        WarrentyPolicy,
                        checkOil,
                        checkAsse,
                        Installtion,
                        NamePlat,
                        tractNoPlat,
                        firstService,
                        secService,
                        thirdSer,
                        DeliveryPhoto,
                        ChasisPhoto
                ).enqueue(new Callback<DeliveryGenKListSubmitModel>() {
                    @Override
                    public void onResponse(@NotNull Call<DeliveryGenKListSubmitModel> call, @NotNull Response<DeliveryGenKListSubmitModel> response) {

                        progressDialog.dismiss();
                        assert response.body() != null;
                        Toast.makeText(GaneralListFormActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(GaneralListFormActivity.this, FoDashbord.class);
                        startActivity(i);

                    }

                    @Override
                    public void onFailure(@NotNull Call<DeliveryGenKListSubmitModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(GaneralListFormActivity.this, "" + t.getCause(), Toast.LENGTH_SHORT).show();

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
                    Uri_saveMobile = data.getData();
                    Log.d("PanImageUri", Uri_saveMobile.toString());
                    WayPath_saveMobile = getFilePath(this, Uri_saveMobile);


                    Log.d("PanImage", WayPath_saveMobile);
                    String[] name = WayPath_saveMobile.split("/");
                    txt_saveMobile.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_saveMobile.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_colWhatNo = data.getData();
                    Log.d("PanImagUri", Uri_colWhatNo.toString());
                    WayPath_colWhatNo = getFilePath(this, Uri_colWhatNo);

                    Log.d("PanRTGS", WayPath_colWhatNo);
                    String[] name = WayPath_colWhatNo.split("/");
                    txt_colWhatNo.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_colWhatNo.setImageURI(selectedImageUri);
                }

            }
        }


        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_SubYtChannel = data.getData();
                    Log.d("Pan Image Uri", Uri_SubYtChannel.toString());
                    WayPath_SubYtChannel = getFilePath(this, Uri_SubYtChannel);
                    Log.d("Pan Image Uri", WayPath_SubYtChannel);
                    String[] name = WayPath_SubYtChannel.split("/");
                    txt_SubYtChannel.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_SubYtChannel.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 5) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_LikeFbInsta = data.getData();
                    Log.d("Pan Image Uri", Uri_LikeFbInsta.toString());
                    WayPath_LikeFbInsta = getFilePath(this, Uri_LikeFbInsta);
                    Log.d("Pan Image Uri", WayPath_LikeFbInsta);
                    String[] name = WayPath_LikeFbInsta.split("/");
                    txt_LikeFbInsta.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LikeFbInsta.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 6) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_toolKitDeli = data.getData();
                    Log.d("Pan Image Uri", Uri_toolKitDeli.toString());
                    WayPath_toolKitDeli = getFilePath(this, Uri_toolKitDeli);
                    Log.d("Pan Image Uri", WayPath_toolKitDeli);
                    String[] name = WayPath_toolKitDeli.split("/");
                    txt_toolKitDeli.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_toolKitDeli.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 7) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_keyDelivery = data.getData();
                    Log.d("Pan Image Uri", Uri_keyDelivery.toString());
                    WayPath_keyDelivery = getFilePath(this, Uri_keyDelivery);
                    Log.d("Pan Image Uri", WayPath_keyDelivery);
                    String[] name = WayPath_keyDelivery.split("/");
                    txt_keyDelivery.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_keyDelivery.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 8) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_checkAmount = data.getData();
                    Log.d("Pan Image Uri", Uri_checkAmount.toString());
                    WayPath_checkAmount = getFilePath(this, Uri_checkAmount);
                    Log.d("Pan Image Uri", WayPath_checkAmount);
                    String[] name = WayPath_checkAmount.split("/");
                    txt_checkAmount.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_checkAmount.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 9) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_WarrentyPolicy = data.getData();
                    Log.d("Pan Image Uri", Uri_WarrentyPolicy.toString());
                    WayPath_WarrentyPolicy = getFilePath(this, Uri_WarrentyPolicy);
                    Log.d("Pan Image Uri", WayPath_WarrentyPolicy);
                    String[] name = WayPath_WarrentyPolicy.split("/");
                    txt_WarrentyPolicy.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_WarrentyPolicy.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_scheckOil = data.getData();
                    Log.d("Pan Image Uri", Uri_scheckOil.toString());
                    WayPath_checkOil = getFilePath(this, Uri_scheckOil);
                    Log.d("Pan Image Uri", WayPath_checkOil);
                    String[] name = WayPath_checkOil.split("/");
                    txt_checkOil.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_checkOil.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 11) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_checkAsse = data.getData();
                    Log.d("Pan Image Uri", Uri_checkAsse.toString());
                    WayPath_checkAsse = getFilePath(this, Uri_checkAsse);
                    Log.d("Pan Image Uri", WayPath_checkAsse);
                    String[] name = WayPath_checkAsse.split("/");
                    txt_checkAsse.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_checkAsse.setImageURI(selectedImageUri);
                }
            }
        }

        /* Uri_secService, Uri_thirdSer,
            Uri_DeliveryPhoto, Uri_ChasisPhoto;*/


        if (requestCode == 12) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_Installtion = data.getData();
                    Log.d("Pan Image Uri", Uri_Installtion.toString());
                    WayPath_Installtion = getFilePath(this, Uri_Installtion);
                    Log.d("Pan Image Uri", WayPath_Installtion);
                    String[] name = WayPath_Installtion.split("/");
                    txt_Installtion.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_Installation.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 13) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_NamePlat = data.getData();
                    Log.d("Pan Image Uri", Uri_NamePlat.toString());
                    WayPath_NamePlat = getFilePath(this, Uri_NamePlat);
                    Log.d("Pan Image Uri", WayPath_NamePlat);
                    String[] name = WayPath_NamePlat.split("/");
                    txt_NamePlat.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_NamePlat.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 14) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_tractNoPlat = data.getData();
                    Log.d("Pan Image Uri", Uri_tractNoPlat.toString());
                    WayPath_tractNoPlat = getFilePath(this, Uri_tractNoPlat);
                    Log.d("Pan Image Uri", WayPath_tractNoPlat);
                    String[] name = WayPath_tractNoPlat.split("/");
                    txt_tractNoPlat.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_tractNoPlat.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 15) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_firstService = data.getData();
                    Log.d("Pan Image Uri", Uri_firstService.toString());
                    WayPath_firstService = getFilePath(this, Uri_firstService);
                    Log.d("Pan Image Uri", WayPath_firstService);
                    String[] name = WayPath_firstService.split("/");
                    txt_firstService.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_firstService.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 16) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_secService = data.getData();
                    Log.d("Pan Image Uri", Uri_secService.toString());
                    WayPath_secService = getFilePath(this, Uri_secService);
                    Log.d("Pan Image Uri", WayPath_secService);
                    String[] name = WayPath_secService.split("/");
                    txt_secService.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_secService.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 17) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_thirdSer = data.getData();
                    Log.d("Pan Image Uri", Uri_thirdSer.toString());
                    WayPath_thirdSer = getFilePath(this, Uri_thirdSer);
                    Log.d("Pan Image Uri", WayPath_thirdSer);
                    String[] name = WayPath_thirdSer.split("/");
                    txt_thirdSer.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_thirdSer.setImageURI(selectedImageUri);
                }
            }
        }

        //----------------------------------------------------------------------

        if (requestCode == 18) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_DeliveryPhoto = data.getData();
                    Log.d("PanImagUri", Uri_DeliveryPhoto.toString());
                    WayPath_DeliveryPhoto = getFilePath(this, Uri_DeliveryPhoto);

                    Log.d("PanRTGS", WayPath_DeliveryPhoto);
                    String[] name = WayPath_DeliveryPhoto.split("/");
                    txt_DeliveryPhoto.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_deliveryPhoto.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 19) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_ChasisPhoto = data.getData();
                    Log.d("Pan Image Uri", Uri_ChasisPhoto.toString());
                    WayPath_ChasisPhoto = getFilePath(this, Uri_ChasisPhoto);
                    Log.d("Pan Image Uri", WayPath_ChasisPhoto);
                    String[] name = WayPath_ChasisPhoto.split("/");
                    txt_ChasisPhoto.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_ChasisPhoto.setImageURI(selectedImageUri);
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