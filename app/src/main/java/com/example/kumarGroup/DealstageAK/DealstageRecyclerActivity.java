package com.example.kumarGroup.DealstageAK;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.CallLog;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.Catnotattend;
import com.example.kumarGroup.Common_Search.CommonSearchAdapter;
import com.example.kumarGroup.DatabankMakeModel;
import com.example.kumarGroup.DatabankModelListModel;
import com.example.kumarGroup.Deal_model;
import com.example.kumarGroup.ItemDateComparator;
import com.example.kumarGroup.ModelNameProductModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;

import com.example.kumarGroup.Village_List.VillageListShowAdapter;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.Deal_notattend_model;
import com.example.kumarGroup.deal_stage_call_sms_what_model;
import com.example.kumarGroup.emp_modelname_model;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DealstageRecyclerActivity extends AppCompatActivity {
    RecyclerView recyclerView_deal;
    ProgressDialog pro;
    SharedPreferences sp1,sp2;
    String emp_id,catID,multiaction = "Kumar Group",click_id,VillageId="",ModelName="",String_modelget,catId_list,ProductName;

    SharePref sp;
    Utils utils;

    AdapterShowButtonData adapterShowButtonData;

    private SearchView searchView;
    List<Catnotattend> catShowRCGVS;
    List<Catnotattend> catsWithMissingParams = new ArrayList<>();
    List<String> modelname_list;
    String strName="",strNmbr="",strModel="",strNote="",strLname="",strOtherNmbr="";

    private FusedLocationProviderClient fusedLocationClient;

    List<String> l_make_name = new ArrayList<>();
    List<String> l_model_list = new ArrayList<>();

    String  makedata = "", modellistdata = "", sor_inqData = "";
    int total;


    public static boolean notattend_flag_sms_call_what = false;

    public static boolean notattend_flag = false;
    public static boolean hotInquiry_flag = false;
    public static boolean firstmeeting_flag = false;
    public static boolean firstmeeting_otpsend_flag = false;
    public static boolean deal_overDue_flag = false;
    public static boolean deal_overDue_flag_other = false;
    public static boolean deal_warmInquiry_flag = false;
    public static boolean deal_coldInquiry_flag = false;
    public static boolean deal_bookingInquiry_flag = false;
    public static boolean deal_deliveryInquiry_flag = false;
    public static boolean deal_deliveryInquiry_flag_showonly_attachbutton = false;
    public static boolean deal_sell_lost_Inquiry_flag = false;
    public static boolean deal_sell_lost_Inquiry_flag_other = false;
    public static boolean deal_dropInquiry_flag = false;
    public static boolean deal_nextactivityplanInquiry_flag = false;
    public static boolean deal_nextactivityplanInquiry_flag_sendimage = false;
    public static boolean makeandoffer_dropdown_flag = false;
    public static boolean deal_NegotiationandfinalInquiry_flag = false;
    public static boolean deal_NegotiationandfinalInquiry_flag_other = false;
    public static boolean deal_followup_Inquiry = false;
    public static boolean deal_followup_Inquiry_other = false;
    public static boolean deal_followup_Inquiry2 = false;
    public static boolean deal_followup_Inquiry2_other = false;
    public static boolean deal_followup_Inquiry3 = false;
    public static boolean deal_followup_Inquiry3_other = false;
    public static boolean deal_dealer_Inquiry = false;
    public static boolean deal_dealer_Inquiry_other = false;
    public static boolean overandnew_followup = false;
    public static boolean overandnew_negotiaion = false;

    public static boolean Not_attend_skip_check = false;
    public static boolean deal_input_miss_Inquiry_flag = false;
    public static boolean deal_input_miss_Inquiry_flag_other = false;

    String PassingType;
    String[] PassingType_list={"Select PassingType","Agriculture", "Commercial"};

    String PaymentType;
    String[] PaymentType_list={"Select PaymentType","Loan", "Cash"};


    String DealType="",Maker,Model,MFGYear;
    String[] DealType_list={"Select Deal Type","Exchange", "Fresh"};

    String[] Products_List = {"Select Product", "New Tractor","Old Tractor","Implement"};


    Cursor managedCursor;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealstage_recycler);

        multiaction = getIntent().getStringExtra("actionbar");
        click_id = getIntent().getStringExtra("click_id");
        VillageId = getIntent().getStringExtra("VillageId");
        ModelName = getIntent().getStringExtra("model");
        PaymentType = getIntent().getStringExtra("Paymenttype");
        PassingType = getIntent().getStringExtra("Passingtype");
        DealType = getIntent().getStringExtra("DealType");
        Maker = getIntent().getStringExtra("Maker");
        Model = getIntent().getStringExtra("Model1");
        MFGYear = getIntent().getStringExtra("MFGYear");

       /* if (!ModelName.equals("")){
            Log.e("model--list",ModelName);
        }*/
        getSupportActionBar().setTitle(multiaction);

        SharedPreferences sharedPreferences = getSharedPreferences("stage_name",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",multiaction);
        editor.apply();

        deal_deliveryInquiry_flag_showonly_attachbutton = false;
        firstmeeting_otpsend_flag = false;

        utils = new Utils(this);
        sp = new SharePref(utils.getActivity());

        if (!utils.userPermission.checkCallPermission()) {
            utils.userPermission.requestCallPermission();
        } else if (!utils.userPermission.checkCallLogPermission()) {
            utils.userPermission.requestCallLogPermission();
        } else {
            getLastNumber();
        }

        recyclerView_deal = findViewById(R.id.recyclerView_deal);
        pro = new ProgressDialog(this);

        sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        emp_id = sp1.getString("emp_id", "");
        Log.d("emp_id====>>>",emp_id);


        SharedPreferences sharedPreferences2 = getSharedPreferences("catid",MODE_PRIVATE);
        catID = sharedPreferences2.getString("id","");
        catId_list = sharedPreferences2.getString("model_name","");

        if (catId_list!=null || !catId_list.equals("")){
            if (catId_list.contains("Rotavetor")){
                ProductName="Implement";
            }else if (catId_list.contains("Old Tractor")){
                ProductName="Old Tractor";
            }else if (catId_list.contains("New Tractor")){
                ProductName="New Tractor";
            }else if (catId_list.contains("TIGER Inquiry")){
                ProductName="New Tractor";
            }else {
                ProductName="";
            }

            Log.e("ProductName",ProductName);
        }

        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");
        Log.d("shweta", "onCreate: flag ");

        if (notattend_flag){
            notattend_flag = false;

            Log.d("shweta", "onCreate: flag 1");

            notattend_flag_sms_call_what = true;

            WebService.getClient().deal_not_attend_web(emp_id,catID,VillageId,ModelName,PaymentType,PassingType,DealType,Maker,Model,MFGYear).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivity.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity");
                        Not_attend_skip_check = true;
                        adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                showDialogEdit(position,mcatlist);
                            }

                            @Override
                            public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                showDialogEditModel(position,mcatlist);
                            }
                        });
                        AutoSearch();
                       // getLastNumber();
                        //adapterShowButtonData.notifyDataSetChanged();
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                }
            });
        }
        else {

            notattend_flag_sms_call_what = false;
        }

        if (firstmeeting_flag){
            firstmeeting_flag = false;
            Log.d("shweta", "onCreate: flag 2");

            firstmeeting_otpsend_flag = true;

            WebService.getClient().deal_firstMeeting_web(emp_id,catID,VillageId,ModelName,PaymentType,PassingType,DealType,Maker,Model,MFGYear).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivity.this,"No data found");
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-1");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-1");
                        adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                showDialogEdit(position,mcatlist);
                            }
                            @Override
                            public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                showDialogEditModel(position,mcatlist);
                            }
                        });
                        AutoSearch();

                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                }
            });
        }
        else {
            firstmeeting_otpsend_flag = false;
        }

        if (deal_overDue_flag){
            deal_overDue_flag = false;
            deal_overDue_flag_other = true;

            Log.d("shweta", "onCreate: flag 3");

            WebService.getClient().deal_overDue_web(emp_id,catID,VillageId,ModelName,PaymentType,PassingType,DealType,Maker,Model,MFGYear).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    sp2 = getSharedPreferences("OverDueCheck",MODE_PRIVATE);
                    sp2.edit().putInt("OverDueCheckCont",response.body().getCat().size()).apply();
                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivity.this,"No data found");
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-2");
                        pro.dismiss();
//                        sp2 = getSharedPreferences("OverDueCheck",MODE_PRIVATE);
//                        sp2.edit().putInt("OverDueCheckCont",response.body().getCat().size()).apply();

                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-2");
                        adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                showDialogEdit(position,mcatlist);
                            }
                            @Override
                            public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                showDialogEditModel(position,mcatlist);
                            }
                        });
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                }
            });
        }else{
            deal_overDue_flag_other=false;
        }

        if (hotInquiry_flag){
            hotInquiry_flag = false;

            Log.d("shweta", "onCreate: flag 4");

            if ("Make An Offer".equals(multiaction)) {

                makeandoffer_dropdown_flag = true;
//                Toast.makeText(DealstageRecyclerActivity.this, ""+multiaction, Toast.LENGTH_SHORT).show();
            }
            else {
                makeandoffer_dropdown_flag = false;
            }

            WebService.getClient().deal_hotInquiry_web(emp_id,"HOT",catID,VillageId,ModelName,PaymentType,PassingType,DealType,Maker,Model,MFGYear).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivity.this,"No data found");
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-3");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Collections.sort(catShowRCGVS, new ItemDateComparator());
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-3");
                        adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                showDialogEdit(position,mcatlist);
                            }
                            @Override
                            public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                showDialogEditModel(position,mcatlist);
                            }
                        });
                        AutoSearch();
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                }
            });

        }

        if (deal_warmInquiry_flag){
            deal_warmInquiry_flag = false;


            Log.d("shweta", "onCreate: flag 5");

            WebService.getClient().deal_hotInquiry_web(emp_id,"WARM",catID,VillageId,ModelName,PaymentType,PassingType,DealType,Maker,Model,MFGYear).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivity.this,"No data found");
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-4");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Collections.sort(catShowRCGVS, new ItemDateComparator());
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-4");
                        adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                showDialogEdit(position,mcatlist);
                            }
                            @Override
                            public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                showDialogEditModel(position,mcatlist);
                            }
                        });
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                }
            });

        }

        if (deal_nextactivityplanInquiry_flag){
            deal_nextactivityplanInquiry_flag = false;

            Log.d("shweta", "onCreate: flag 6");

            deal_nextactivityplanInquiry_flag_sendimage = true;

            WebService.getClient().deal_hotInquiry_web(emp_id,"WARM",catID,VillageId,ModelName,PaymentType,PassingType,DealType,Maker,Model,MFGYear).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivity.this,"No data found");
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-5");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-5");
                        adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                showDialogEdit(position,mcatlist);
                            }
                            @Override
                            public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                showDialogEditModel(position,mcatlist);
                            }
                        });
                        AutoSearch();
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                }
            });
        }
        else {
            deal_nextactivityplanInquiry_flag_sendimage = false;
        }

        if (deal_coldInquiry_flag){
            deal_coldInquiry_flag = false;

            Log.d("shweta", "onCreate: flag 7");

            WebService.getClient().deal_hotInquiry_web(emp_id,"COLD",catID,VillageId,ModelName,PaymentType,PassingType,DealType,Maker,Model,MFGYear).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivity.this,"No data found");
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-6");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Collections.sort(catShowRCGVS, new ItemDateComparator());
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-6");
                        adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                showDialogEdit(position,mcatlist);
                            }
                            @Override
                            public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                showDialogEditModel(position,mcatlist);
                            }
                        });
                        AutoSearch();
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                }
            });

        }

        if (deal_bookingInquiry_flag){
            deal_bookingInquiry_flag = false;

            Log.d("shweta", "onCreate: flag 8");

            WebService.getClient().deal_booking_web(emp_id,catID,VillageId,ModelName,PaymentType,PassingType,DealType,Maker,Model,MFGYear).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivity.this,"No data found");
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-7");

                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-7");
                        adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                showDialogEdit(position,mcatlist);
                            }
                            @Override
                            public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                showDialogEditModel(position,mcatlist);
                            }
                        });
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                }
            });

        }

        if (deal_deliveryInquiry_flag){
            deal_deliveryInquiry_flag = false;

            Log.d("shweta", "onCreate: flag 9");
            deal_deliveryInquiry_flag_showonly_attachbutton = true;

            String delivery = getIntent().getStringExtra("catId_eid");

            WebService.getClient().deal_delivery_web(emp_id,delivery,catID,ModelName,PaymentType,PassingType,DealType,Maker,Model,MFGYear).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivity.this,"No data found");
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-8");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-8");
                        adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                showDialogEdit(position,mcatlist);
                            }
                            @Override
                            public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                showDialogEditModel(position,mcatlist);
                            }
                        });
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                }
            });

        }

        if (deal_sell_lost_Inquiry_flag){
            deal_sell_lost_Inquiry_flag = false;
            deal_sell_lost_Inquiry_flag_other = true;
            Log.d("shweta", "onCreate: flag 10");
            Log.d("Jemin", "onResponse: DealstageRecyclerActivity-9" +emp_id+" "+catID);


            WebService.getClient().deal_selllost_web(emp_id,catID,VillageId,ModelName,PaymentType,PassingType,DealType,Maker,Model,MFGYear).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivity.this,"No data found");
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity no-9");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-9");
                        adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                showDialogEdit(position,mcatlist);
                            }
                            @Override
                            public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                showDialogEditModel(position,mcatlist);
                            }
                        });
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                }
            });

        }else{
            deal_sell_lost_Inquiry_flag_other=false;
        }

        if (deal_input_miss_Inquiry_flag){
            deal_input_miss_Inquiry_flag = false;
            deal_input_miss_Inquiry_flag_other = true;
            Log.d("{TAG}", "onCreate: checkError: 16");

            WebService.getClient().deal_inputmiss_web(emp_id,emp_id,catID).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivity.this,"No data found");
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivity-10");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("missingList==>>before", String.valueOf(catShowRCGVS)+"\nlenght catShowRCGVS :"+catShowRCGVS.size());

                        for (int i=0;i<catShowRCGVS.size();i++){
                            Catnotattend catnotattend=catShowRCGVS.get(i);
                            if (catnotattend.getCat_name()==null || catnotattend.getCat_name().isEmpty() ||catnotattend.getName()==null  || catnotattend.getName().isEmpty() || catnotattend.getVilage()==null  || catnotattend.getVilage().isEmpty()

                                    ||  catnotattend.getMoblino()==null || catnotattend.getMoblino().isEmpty() || catnotattend.getWhatsappno()==null  || catnotattend.getWhatsappno().isEmpty()
                                    ||catnotattend.getModel_name()==null  || catnotattend.getModel_name().equals("")/*  || catnotattend.getModel_name().isEmpty()*/
                                    ||   catnotattend.getSor_of_inq()==null  || catnotattend.getSor_of_inq().isEmpty() || catnotattend.getPassing_type()==null  || catnotattend.getPassing_type().isEmpty() ||catnotattend.getPayment_type()==null || catnotattend.getPayment_type().isEmpty()||catnotattend.getDeal_type()==null  || catnotattend.getDeal_type().isEmpty()
                            ){
                                catsWithMissingParams.add(catnotattend);
                            }
                        }
                        Log.d("missingList==>>after", String.valueOf(catsWithMissingParams));
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivity-10");
                        adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, catsWithMissingParams);
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                showDialogEdit(position,mcatlist);
                            }

                            @Override
                            public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                showDialogEditModel(position,mcatlist);
                            }
                        });
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                }
            });

        }else{
            deal_input_miss_Inquiry_flag=false;
        }

        if (deal_dropInquiry_flag){
            deal_dropInquiry_flag = false;
            Log.d("shweta", "onCreate: flag 11");

            WebService.getClient().deal_dropinq_web(emp_id,catID,VillageId,ModelName,PaymentType,PassingType,DealType,Maker,Model,MFGYear).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivity.this,"No data found");
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-10");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-10");
                        adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                showDialogEdit(position,mcatlist);
                            }
                            @Override
                            public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                showDialogEditModel(position,mcatlist);
                            }
                        });
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                }
            });

        }

        if (deal_NegotiationandfinalInquiry_flag){
            deal_NegotiationandfinalInquiry_flag = false;

            Log.d("shweta", "onCreate: flag 12");

            deal_NegotiationandfinalInquiry_flag_other = true;

            overandnew_negotiaion = true;

            WebService.getClient().deal_negosiation_web(emp_id,catID,VillageId,ModelName,PaymentType,PassingType,DealType,Maker,Model,MFGYear).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivity.this,"No data found");
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-11");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-11");
                        adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                showDialogEdit(position,mcatlist);
                            }
                            @Override
                            public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                showDialogEditModel(position,mcatlist);
                            }
                        });
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                }
            });

        }
        else {
            overandnew_negotiaion = false;
            deal_NegotiationandfinalInquiry_flag_other = false;
        }

        if (deal_followup_Inquiry){
            deal_followup_Inquiry = false;
            Log.d("shweta", "onCreate: flag 13");

            overandnew_followup = true;
            deal_followup_Inquiry_other = true;

            WebService.getClient().deal_followup1_web(emp_id,catID,VillageId,ModelName,PaymentType,PassingType,DealType,Maker,Model,MFGYear).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivity.this,"No data found");
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-12");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onResponse: DealstageRecyclerActivity-12");
                        adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                showDialogEdit(position,mcatlist);
                            }
                            @Override
                            public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                showDialogEditModel(position,mcatlist);
                            }
                        });
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                }
            });

        }
        else {
            overandnew_followup = false;
            deal_followup_Inquiry_other = false;
        }


        if (deal_followup_Inquiry2){
            deal_followup_Inquiry2 = false;
            deal_followup_Inquiry2_other = true;

            Log.d("{TAG}", "onCreate: checkError: 14");
            overandnew_followup = true;

            WebService.getClient().deal_followup2_web(emp_id,catID,VillageId,ModelName,PaymentType,PassingType,DealType,Maker,Model,MFGYear).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivity.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                showDialogEdit(position,mcatlist);
                            }
                            @Override
                            public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                showDialogEditModel(position,mcatlist);
                            }
                        });
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                }
            });

        }
        else {
            overandnew_followup = false;
            deal_followup_Inquiry2_other=false;
        }


        if (deal_followup_Inquiry3){
            deal_followup_Inquiry3 = false;
            deal_followup_Inquiry3_other = true;

            Log.d("{TAG}", "onCreate: checkError: 15");
            overandnew_followup = true;

            WebService.getClient().deal_followup3_web(emp_id,catID,VillageId,ModelName,PaymentType,PassingType,DealType,Maker,Model,MFGYear).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivity.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                showDialogEdit(position,mcatlist);
                            }
                            @Override
                            public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                showDialogEditModel(position,mcatlist);
                            }
                        });
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                }
            });

        }
        else {
            overandnew_followup = false;
            deal_followup_Inquiry3_other = false;
        }

        if (deal_dealer_Inquiry){
            deal_dealer_Inquiry = false;

            Log.d("{TAG}", "onCreate: checkError: 13");
//            overandnew_followup = true;
            deal_dealer_Inquiry_other = true;

            WebService.getClient().dealstage_dealer_meeting(catID,emp_id,VillageId,ModelName,PaymentType,PassingType,DealType).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivity.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                showDialogEdit(position,mcatlist);
                            }
                            @Override
                            public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                showDialogEditModel(position,mcatlist);
                            }
                        });
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                }
            });

        }
        else {
            overandnew_followup = false;
            deal_dealer_Inquiry_other = false;

        }
    }

    private void AutoSearch() {
        if (CommonSearchAdapter.Common_Search_AutoFill_Search_Check || VillageListShowAdapter.Village_List_AutoFill_Search_Check){
            adapterShowButtonData.getFilter().filter(click_id);
            CommonSearchAdapter.Common_Search_AutoFill_Search_Check = false;
            VillageListShowAdapter.Village_List_AutoFill_Search_Check = false;
        }else {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getLastNumber() {

        String phNumber = null, callType = null, callDate, callDayTime = null;
        int callDuration = 0, dircode=0;

        Uri contacts = CallLog.Calls.CONTENT_URI;
        Bundle bundle = new Bundle();
        bundle.putInt(ContentResolver.QUERY_ARG_LIMIT, 100);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            utils.userPermission.requestCallLogPermission();
        }


        /*try {
            managedCursor = getApplicationContext().getContentResolver().query(contacts, null, null, null, android.provider.CallLog.Calls.DATE + " DESC limit 1;");
        }
        catch (Exception e){

            managedCursor = getApplicationContext().getContentResolver().query(contacts, null, bundle, null);
        }*/

        managedCursor = getApplicationContext().getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null, null, android.provider.CallLog.Calls.DATE /*+ " DESC limit 1;"*//*null*/);


        if (managedCursor != null && managedCursor.getCount() != 0) {


            int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
            int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
            int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
            int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);



            while (managedCursor.moveToNext()) {

                phNumber = managedCursor.getString(number);
                callType = managedCursor.getString(type);
                callDate = managedCursor.getString(date);
                callDayTime = new Date(Long.parseLong(callDate)).toString();
                callDuration = managedCursor.getInt(duration);
                dircode = Integer.parseInt(callType);



            }
            managedCursor.close();

            utils.printLog("Details", "Phone Number:--- " + phNumber + " " +
                    ",Call Date:--- " + callDayTime + " ,Call duration in sec :--- " + callDuration);

//            call_id = new String[dataTaskModel.getTask().size()];
//            for (int i = 0; i < dataTaskModel.getTask().size(); i++){
//                call_id[i] = dataTaskModel.getTask().get(i).getCall_id();
//            }
//            int pos = new ArrayList<>(Arrays.asList(call_id)).indexOf(sp.getSharedPref(sp.CALL_ID));

            if (sp.getSharedPref(sp.CALL_ID) != null && !TextUtils.isEmpty(sp.getSharedPref(sp.CALL_ID))
                    && sp.getSharedPref(sp.PHONE_NUMBER) != null && !TextUtils.isEmpty(sp.getSharedPref(sp.PHONE_NUMBER))) {
                if (dircode == CallLog.Calls.OUTGOING_TYPE && callDuration > 0 && sp.getSharedPref(sp.PHONE_NUMBER).equals(phNumber)) {
//                float callDurationMinut = (float) callDuration / 60;
                    //fillAttendance(sp.getSharedPref(sp.CALL_ID), phNumber,sp.getSharedPref(sp.task_type));

                    WebService.getClient().deal_send_call_sms_what_web(sp.getSharedPref(sp.CALL_ID),"Not Attend","").enqueue(new Callback<deal_stage_call_sms_what_model>() {
                        @Override
                        public void onResponse(Call<deal_stage_call_sms_what_model> call, Response<deal_stage_call_sms_what_model> response) {
                            Log.d("TAG", "call_sms_what " + response.body().getMsg());
                            sp.ClearPref();

                            if (notattend_flag_sms_call_what) {
                                WebService.getClient().deal_not_attend_web(emp_id,catID,VillageId,ModelName,PaymentType,PassingType,DealType,Maker,Model,MFGYear).enqueue(new Callback<Deal_notattend_model>() {
                                    @Override
                                    public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {
                                        if (0 == response.body().getCat().size()) {
                                            Utils.showErrorToast(DealstageRecyclerActivity.this, "No data found");
                                            pro.dismiss();
                                        } else {
                                            Log.d("Jemin", "onResponse: DealstageRecyclerActivity-14");
                                            AdapterShowButtonData adapterShowButtonData = new AdapterShowButtonData(DealstageRecyclerActivity.this, DealstageRecyclerActivity.this, response.body().getCat());
                                            recyclerView_deal.setAdapter(adapterShowButtonData);
                                            adapterShowButtonData.setViewItemInterface(new AdapterShowButtonData.RecyclerViewItemInterface1() {
                                                @Override
                                                public void onItemClick(int position, List<Catnotattend> mcatlist) {
                                                    showDialogEdit(position,mcatlist);
                                                }
                                                @Override
                                                public void onModelEditclick(int position, List<Catnotattend> mcatlist) {
                                                    showDialogEditModel(position,mcatlist);
                                                }
                                            });
                                            // getLastNumber();
                                            //  adapterShowButtonData.notifyDataSetChanged();
                                            pro.dismiss();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                                        pro.dismiss();
                                        Utils.showErrorToast(DealstageRecyclerActivity.this, t.getMessage());
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<deal_stage_call_sms_what_model> call, Throwable t) {

                        }
                    });
                }
            }

        }
        else {
            Toast.makeText(this, "Log is Blank !!", Toast.LENGTH_SHORT).show();
        }

    }

    private void showDialogEditModel(int position, List<Catnotattend> mcatlist) {
        Dialog dialog = new Dialog(DealstageRecyclerActivity.this);
        dialog.setContentView(R.layout.edit_modeldetail);


        EditText edt_MFGYear = dialog.findViewById(R.id.edt_MFGYear);
        EditText edt_CustomerPrice = dialog.findViewById(R.id.edt_CustomerPrice);
        EditText edt_MarketVal = dialog.findViewById(R.id.edt_MarketVal);
        EditText edt_Diff = dialog.findViewById(R.id.edt_Diff);
        EditText edt_regno = dialog.findViewById(R.id.edt_regno);

        Button btn_edit = dialog.findViewById(R.id.btn_edit);

        Spinner sp_make_name = dialog.findViewById(R.id.sp_make_name);
        Spinner sp_model_name = dialog.findViewById(R.id.sp_model_name);


        edt_MFGYear.setText(mcatlist.get(position).getModel_yold());
        edt_CustomerPrice.setText(mcatlist.get(position).getCustomer_price());
        edt_MarketVal.setText(mcatlist.get(position).getMarket_value());
        edt_Diff.setText(mcatlist.get(position).getDiffrance());
        edt_regno.setText(mcatlist.get(position).getRegi_noold());

        edt_CustomerPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (!s.toString().equals("") && !edt_MarketVal.getText().toString().equals("")) {
                    total = Integer.parseInt(edt_CustomerPrice.getText().toString()) - Integer.parseInt(edt_MarketVal.getText().toString());
                    edt_Diff.setText(String.valueOf(total));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edt_MarketVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                if (!s.toString().equals("") && !edt_CustomerPrice.getText().toString().equals("")) {
                    total = Integer.parseInt(edt_CustomerPrice.getText().toString()) - Integer.parseInt(edt_MarketVal.getText().toString());
                    edt_Diff.setText(String.valueOf(total));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

//

        WebService.getClient().getDatabankMake().enqueue(new Callback<DatabankMakeModel>() {
            @Override
            public void onResponse(Call<DatabankMakeModel> call, Response<DatabankMakeModel> response1) {
                Log.d("MakeName", response1.body().toString());
                if (response1.isSuccessful()) {
                    if (response1.body() != null) {
                        l_make_name.clear();
                        l_make_name.add("Select Make (Ex)");

                        for (int i = 0; i < response1.body().getState().size(); i++)
                        {
                            l_make_name.add(response1.body().getState().get(i).getMake());
                        }

                        ArrayAdapter adapter2 = new ArrayAdapter(DealstageRecyclerActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, l_make_name);
                        sp_make_name.setAdapter(adapter2);

                        if (!(mcatlist.get(position).getMakerold() != null ? mcatlist.get(position).getMakerold().equalsIgnoreCase("") : false)){

//                            if (strModel!=null  || !strModel.equals("")){
                            int spinnerPosition = adapter2.getPosition(mcatlist.get(position).getMakerold());
                            sp_make_name.setSelection(spinnerPosition);
                        }

                        sp_make_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                makedata = l_make_name.get(position);  //
                                Log.d("ModelList", makedata);

                                if(!makedata.equals("Select Make (Ex)")) {
                                    WebService.getClient().getDatabankModelList(makedata).enqueue(new Callback<DatabankModelListModel>() {
                                        @Override
                                        public void onResponse(Call<DatabankModelListModel> call, Response<DatabankModelListModel> response2) {
                                            l_model_list.clear();

                                            if (response2.isSuccessful()) {
                                                if (response2.body() != null) {
                                                    l_model_list.add("Select Model (Ex)");
                                                    int xz = response2.body().getModel().size();
                                                    Log.d("ModelList", "" + xz +"");
                                                    for (int j = 0; j < response2.body().getModel().size();j++)
                                                    {
                                                        l_model_list.add(response2.body().getModel().get(j).getModelName());
                                                        Log.d("ModelList", "onResponse: " + response2.body().getModel().get(j).getModelName());
                                                    }

                                                    ArrayAdapter adapter3 = new ArrayAdapter(DealstageRecyclerActivity.this,
                                                            android.R.layout.simple_spinner_dropdown_item, l_model_list);
                                                    sp_model_name.setAdapter(adapter3);

                                                    if (!(mcatlist.get(position).getModelold() != null ? mcatlist.get(position).getModelold().equalsIgnoreCase("") : false)){

//                                                    if (!(strModel != null ? strModel.equalsIgnoreCase("") : false)){

//                            if (strModel!=null  || !strModel.equals("")){
                                                        int spinnerPosition = adapter3.getPosition(strModel);
                                                        sp_model_name.setSelection(spinnerPosition);
                                                    }

                                                    sp_model_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override
                                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                            modellistdata = l_model_list.get(i);
                                                        }

                                                        @Override
                                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                                        }
                                                    });
                                                }
                                            }

                                        }

                                        @Override
                                        public void onFailure(Call<DatabankModelListModel> call, Throwable t) {
                                            Toast.makeText(getApplicationContext(), "Something went wrong... Please try again after sometime! \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    l_model_list.clear();
                                    l_model_list.add("Select Model (Ex)");
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
            public void onFailure(Call<DatabankMakeModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong... Please try again after sometime! \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pro.show();
                pro.setCancelable(false);
                pro.setMessage("Please wait ...");


//                WebService.getClient().update_dealtypedata(mcatlist.get(position).getAutoid(),edt_CustomerPrice.getText().toString(),edt_Diff.getText().toString(),edt_MarketVal.getText().toString(),modellistdata,mcatlist.get(position).getId(),makedata,edt_regno.getText().toString(),edt_MFGYear.getText().toString()).enqueue(new Callback<Deal_model>() {
                WebService.getClient().update_dealtypedata(mcatlist.get(position).getAutoid(),edt_CustomerPrice.getText().toString(),edt_Diff.getText().toString(),edt_MarketVal.getText().toString(),modellistdata,mcatlist.get(position).getId(),makedata,edt_regno.getText().toString(),edt_MFGYear.getText().toString()).enqueue(new Callback<Deal_model>() {
                    @Override
                    public void onResponse(Call<Deal_model> call, Response<Deal_model> response1) {
                        Log.d("MakeName", response1.body().toString());
                        if (response1.isSuccessful()) {
                            if (response1.body() != null) {



                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Deal_model> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Something went wrong... Please try again after sometime! \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        dialog.show();
    }


    private void showDialogEdit(int position, List<Catnotattend> mcatlist) {
        Dialog dialog = new Dialog(DealstageRecyclerActivity.this);
        dialog.setContentView(R.layout.edit_detail);

        final String[] locationstr = new String[1];


        EditText customer_name = dialog.findViewById(R.id.customerf_name);
        EditText customerl_name = dialog.findViewById(R.id.customerl_name);
        EditText customer_mnmbr = dialog.findViewById(R.id.customer_mnmbr);
        EditText customer_other_mnmbr = dialog.findViewById(R.id.customer_other_mnmbr);

        EditText note = dialog.findViewById(R.id.note);
        Button btn_edit = dialog.findViewById(R.id.btn_edit);

        Spinner sp_model_emp =dialog. findViewById(R.id.sp_model_emp);
        Spinner spn_AddPD_ProductName =dialog. findViewById(R.id.spn_AddPD_ProductName);
        Spinner sp_paymenttype =dialog. findViewById(R.id.sp_paymenttype);
        Spinner sp_passingtype =dialog. findViewById(R.id.sp_passingtype);
        Spinner sp_make_name = dialog.findViewById(R.id.sp_make_name);
        Spinner sp_model_name = dialog.findViewById(R.id.sp_model_name);
        Spinner spnDealType = dialog.findViewById(R.id.spnDealType);
        TextView locationget = dialog.findViewById(R.id.locationget);
        Button getlocation1 = dialog.findViewById(R.id.getlocation);

        String inqType=mcatlist.get(position).getAutoid();
        String sname=mcatlist.get(position).getId();
        String modelname=mcatlist.get(position).getModel_name();
        String paymenttype=mcatlist.get(position).getPayment_type();
        String passingtype=mcatlist.get(position).getPassing_type();
        String dealtype=mcatlist.get(position).getDeal_type();

        modelname_list = new ArrayList<>();

        ArrayAdapter adapterPassing = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, PassingType_list);
        sp_passingtype.setAdapter(adapterPassing);
        if (!(passingtype != null ? passingtype.equalsIgnoreCase("") : false)){
//        if (passingtype!=null  || !passingtype.equals("")){
            int spinnerPosition = adapterPassing.getPosition(passingtype);
            sp_passingtype.setSelection(spinnerPosition);
        }

        sp_passingtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( PassingType_list[position]=="Select PassingType"){
                    PassingType = "";
                }
                else{
                    PassingType = PassingType_list[position];
                }
                //  TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapterPayment = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, PaymentType_list);
        sp_paymenttype.setAdapter(adapterPayment);
        if (!(paymenttype != null ? paymenttype.equalsIgnoreCase("") : false)){
//        if (paymenttype!=null  || !paymenttype.equals("")){
            int spinnerPosition = adapterPayment.getPosition(paymenttype);
            sp_paymenttype.setSelection(spinnerPosition);
        }

        sp_paymenttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( PaymentType_list[position]=="Select PaymentType"){
                    PaymentType = "";
                }
                else{
                    PaymentType = PaymentType_list[position];
                }
                //  TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapterDeal = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, DealType_list);
        spnDealType.setAdapter(adapterDeal);
        if (!(dealtype != null ? dealtype.equalsIgnoreCase("") : false)){
//        if (dealtype!=null  || !dealtype.equals("")){
            int spinnerPosition = adapterDeal.getPosition(dealtype);
            spnDealType.setSelection(spinnerPosition);
        }

        spnDealType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( DealType_list[position]=="Select Deal Type"){
                    DealType = "";
                }
                else{
                    DealType = DealType_list[position];
                }
                //  TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        customer_name.setText(mcatlist.get(position).getFname());
        customerl_name.setText(mcatlist.get(position).getLname());
        customer_mnmbr.setText(mcatlist.get(position).getMoblino());
        customer_other_mnmbr.setText(mcatlist.get(position).getWhatsappno());
//        model_name.setText(mcatlist.get(position).getModel_name());
        note.setText(mcatlist.get(position).getDesc());

        strName=customer_name.getText().toString();
        strLname=customerl_name.getText().toString();
        strNmbr=customer_mnmbr.getText().toString();
        strOtherNmbr=customer_other_mnmbr.getText().toString();
        strModel=modelname;
        strNote=note.getText().toString();


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Products_List);
        spn_AddPD_ProductName.setAdapter(adapter);

        spn_AddPD_ProductName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProductName = Products_List[position];

                //  Toast.makeText(ProductDetailsActivity.this, ""+ProductName, Toast.LENGTH_SHORT).show();

                Log.d("product", "onItemSelected: "+ProductName);

                if (ProductName!=null  && !ProductName.equals("Select Product")){

                    WebService.getClient().getModelName(ProductName).enqueue(new Callback<ModelNameProductModel>() {
                        @Override
                        public void onResponse(@NotNull Call<ModelNameProductModel> call, @NotNull Response<ModelNameProductModel> response)
                        {
                            if(response.isSuccessful()) {
                                if (response.body() != null) {
                                    modelname_list.clear();
//                        ModelID.clear();



                                    modelname_list.add("Select Model (new)");
//                        ModelID.add("0");

                                    Log.d("product", response.body().toString());

                                    for (int i = 0; i < response.body().getData().size(); i++) {
                                        modelname_list.add(response.body().getData().get(i).getModel_name());
//                            ModelID.add(response.body().getData().get(i).getId());
                                    }

                                    Log.d("ProductS", "onResponse: "+response.body().getData().size());

                                    //   Log.d("MProduct", ModelName.toString());

                                    ArrayAdapter adapter2 = new ArrayAdapter(DealstageRecyclerActivity.this,
                                            android.R.layout.simple_spinner_dropdown_item, modelname_list);
                                    sp_model_emp.setAdapter(adapter2);

                                    if (!(strModel != null ? strModel.equalsIgnoreCase("") : false)){

//                            if (strModel!=null  || !strModel.equals("")){
                                        int spinnerPosition = adapter2.getPosition(strModel);
                                        sp_model_emp.setSelection(spinnerPosition);
                                    }


                                    sp_model_emp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                                model_name = ModelName.get(position);
//                                model_id = ModelID.get(position);

                                            if ("Select Model ".equals(modelname_list.get(i))){
                                                String_modelget = "";
                                            }
                                            else {
                                                String_modelget = modelname_list.get(i);
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });

                                }
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<ModelNameProductModel> call, @NotNull Throwable t) {

                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        WebService.getClient().getDatabankMake().enqueue(new Callback<DatabankMakeModel>() {
            @Override
            public void onResponse(Call<DatabankMakeModel> call, Response<DatabankMakeModel> response1) {
                Log.d("MakeName", response1.body().toString());
                if (response1.isSuccessful()) {
                    if (response1.body() != null) {
                        l_make_name.clear();
                        l_make_name.add("Select Make (New)");

                        for (int i = 0; i < response1.body().getState().size(); i++)
                        {
                            l_make_name.add(response1.body().getState().get(i).getMake());
                        }

                        ArrayAdapter adapter2 = new ArrayAdapter(DealstageRecyclerActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, l_make_name);
                        sp_make_name.setAdapter(adapter2);

                        sp_make_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                makedata = l_make_name.get(position);  //
                                Log.d("ModelList", makedata);

                                if(!makedata.equals("Select Make (New)")) {
                                    WebService.getClient().getDatabankModelList(makedata).enqueue(new Callback<DatabankModelListModel>() {
                                        @Override
                                        public void onResponse(Call<DatabankModelListModel> call, Response<DatabankModelListModel> response2) {
                                            l_model_list.clear();

                                            if (response2.isSuccessful()) {
                                                if (response2.body() != null) {
                                                    l_model_list.add("Select Model (New)");
                                                    int xz = response2.body().getModel().size();
                                                    Log.d("ModelList", "" + xz +"");
                                                    for (int j = 0; j < response2.body().getModel().size();j++)
                                                    {
                                                        l_model_list.add(response2.body().getModel().get(j).getModelName());
                                                        Log.d("ModelList", "onResponse: " + response2.body().getModel().get(j).getModelName());
                                                    }

                                                    ArrayAdapter adapter3 = new ArrayAdapter(DealstageRecyclerActivity.this,
                                                            android.R.layout.simple_spinner_dropdown_item, l_model_list);
                                                    sp_model_name.setAdapter(adapter3);


                                                    if (!(strModel != null ? strModel.equalsIgnoreCase("") : false)){

//                            if (strModel!=null  || !strModel.equals("")){
                                                        int spinnerPosition = adapter3.getPosition(strModel);
                                                        sp_model_name.setSelection(spinnerPosition);
                                                    }

                                                    sp_model_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override
                                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                            modellistdata = l_model_list.get(i);
                                                        }

                                                        @Override
                                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                                        }
                                                    });
                                                }
                                            }

                                        }

                                        @Override
                                        public void onFailure(Call<DatabankModelListModel> call, Throwable t) {
                                            Toast.makeText(getApplicationContext(), "Something went wrong... Please try again after sometime! \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    l_model_list.clear();
                                    l_model_list.add("Select Model (New)");
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
            public void onFailure(Call<DatabankMakeModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong... Please try again after sometime! \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        WebService.getClient().emp_getmodel_name_web().enqueue(new Callback<emp_modelname_model>() {
            @Override
            public void onResponse(Call<emp_modelname_model> call, Response<emp_modelname_model> response) {

                modelname_list.clear();

                modelname_list.add("Select Model");

                if (0 != response.body().getData().size()){
                    for (int i = 0 ; i < response.body().getData().size() ; i ++){
                        modelname_list.add(response.body().getData().get(i).getModel_name());
                    }


                    ArrayAdapter adapterType = new ArrayAdapter(DealstageRecyclerActivity.this, android.R.layout.simple_spinner_dropdown_item, modelname_list);
                    sp_model_emp.setAdapter(adapterType);

                    sp_model_emp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if ("Select Model".equals(modelname_list.get(i))){
                                String_modelget = "";
                            }
                            else {
                                String_modelget = modelname_list.get(i);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<emp_modelname_model> call, Throwable t) {
                Toast.makeText(DealstageRecyclerActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(DealstageRecyclerActivity.this);
        getlocation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getlocation();
            }

            private void getlocation()  {
                pro.show();
                pro.setMessage("Please wait get location...");
                pro.setCancelable(false);
                LocationManager locationManager = (LocationManager) DealstageRecyclerActivity.this.getSystemService(DealstageRecyclerActivity.this.LOCATION_SERVICE);
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                        locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    if (ActivityCompat.checkSelfPermission(DealstageRecyclerActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(DealstageRecyclerActivity.this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location location = task.getResult();
                            if (location != null) {
                                try {
                                    Geocoder geocoder = new Geocoder(DealstageRecyclerActivity.this, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);


                                    locationstr[0] =addresses.get(0).getAddressLine(0)+" , "+addresses.get(0).getSubLocality()+" , "+addresses.get(0).getLocality()
                                            +" , "+ addresses.get(0).getAdminArea()+" , "+addresses.get(0).getCountryName()+" , "+String.valueOf(addresses.get(0).getLatitude())+" , "+
                                            String.valueOf(addresses.get(0).getLongitude());
                                    locationget.setVisibility(View.VISIBLE);
                                    locationget.setText(addresses.get(0).getAddressLine(0)+" , "+addresses.get(0).getSubLocality()+" , "+addresses.get(0).getLocality()
                                            +" , "+ addresses.get(0).getAdminArea()+" , "+addresses.get(0).getCountryName()+" , "+String.valueOf(addresses.get(0).getLatitude())+" , "+
                                            String.valueOf(addresses.get(0).getLongitude()));

                                    getlocation1.setVisibility(View.GONE);
                                    pro.dismiss();


                                } catch (IOException e) {
                                    e.printStackTrace();
                                    getlocation();
                                    // Toast.makeText(getApplicationContext(), "cathc id "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    pro.dismiss();
                                }
                            }
                            else {
                                LocationRequest locationRequest = new LocationRequest()
                                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                        .setInterval(10000)
                                        .setFastestInterval(10000)
                                        .setNumUpdates(1);
                                LocationCallback locationCallback = new LocationCallback() {
                                    @Override
                                    public void onLocationResult(@NonNull LocationResult locationResult) {
                                        //   super.onLocationResult(locationResult);
                                        try {

                                            Location location1 = locationResult.getLastLocation();
                                            Geocoder geocoder = new Geocoder(DealstageRecyclerActivity.this, Locale.getDefault());
                                            List<Address> addresses = geocoder.getFromLocation(location1.getLatitude(), location1.getLongitude(), 0);

                                            //  Toast.makeText(DealstageRecyclerActivity.this, addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();
                                            locationstr[0]=addresses.get(0).getAddressLine(0)+" , "+addresses.get(0).getSubLocality()+" , "+addresses.get(0).getLocality()
                                                    +" , "+ addresses.get(0).getAdminArea()+" , "+addresses.get(0).getCountryName()+" , "+String.valueOf(addresses.get(0).getLatitude())+" , "+
                                                    String.valueOf(addresses.get(0).getLongitude());
                                            locationget.setVisibility(View.VISIBLE);
                                            locationget.setText(addresses.get(0).getAddressLine(0)+" , "+addresses.get(0).getSubLocality()+" , "+addresses.get(0).getLocality()
                                                    +" , "+ addresses.get(0).getAdminArea()+" , "+addresses.get(0).getCountryName()+" , "+String.valueOf(addresses.get(0).getLatitude())+" , "+
                                                    String.valueOf(addresses.get(0).getLongitude()));

                                            getlocation1.setVisibility(View.GONE);
                                            pro.dismiss();


                                        } catch (Exception e) {
                                            pro.dismiss();
                                            getlocation();
                                            //Toast.makeText(getApplicationContext(), " catch id 2"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                };
                                if (ActivityCompat.checkSelfPermission(DealstageRecyclerActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(DealstageRecyclerActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                                    return;
                                }
                                fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                            }
                        }
                    });
                }
                else {
                    pro.dismiss();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DealstageRecyclerActivity.this);


                    alertDialog.setTitle("GPS is not Enabled!");

                    alertDialog.setMessage("Do you want to turn on GPS?");
                    alertDialog.setCancelable(false);

                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                        }
                    });

                    alertDialog.show();

                }
            }

        });



        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pro.show();
                pro.setCancelable(false);
                pro.setMessage("Please wait ...");


                strName = customer_name.getText().toString();
                strLname = customerl_name.getText().toString();
                strNmbr = customer_mnmbr.getText().toString();
                strOtherNmbr = customer_other_mnmbr.getText().toString();
                strNote = note.getText().toString();

                strModel=String_modelget;

                Log.e("inqType",inqType+"\nsname:-"+sname+"\nstrName:-"+strName+"\nstrLName:-"+strLname
                        +"\nstrNmbr:-"+strNmbr+"\nstrModel:-"+String_modelget+"\nstrNote:-"+strNote);

                WebService.getClient().edit_deal_stage_info(inqType, sname,strName,strLname, strNmbr,strModel, strNote,strOtherNmbr,PaymentType,PassingType,DealType,makedata,emp_id,locationstr[0],mcatlist.get(position).getFollow_up_type().toString()).enqueue(new Callback<Deal_model>() {
                    @Override
                    public void onResponse(Call<Deal_model> call, Response<Deal_model> response) {
                        pro.dismiss();

                        Log.d("{shweta}1", "onCreate: checkError: 1 "+notattend_flag);

                        Log.e("respose",response.body().toString());
                        dialog.dismiss();


                        if (!searchView.isIconified()) {
                            searchView.setIconified(true);
                            return;
                        }

                    }

                    @Override
                    public void onFailure(Call<Deal_model> call, Throwable t) {
                        pro.dismiss();
                        Utils.showErrorToast(DealstageRecyclerActivity.this,t.getMessage());
                        dialog.dismiss();
                    }
                });

            }
        });
        dialog.show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                // return true;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {


                // filter recycler view when text is changed
                if (catShowRCGVS == null) {
                    //        Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();

                }else if (catShowRCGVS.size() == 0){
                    //         Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();
                }else{
                    adapterShowButtonData.getFilter().filter(query);

                    // showDetailGVAdapter.getFilter().filter(query);
                    Log.d("ssss", "onQueryTextChange: "+ adapterShowButtonData);
                    //           Toast.makeText(FoVisit.this, "Data", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if(id == R.id.action_search ){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        notattend_flag = false;
        firstmeeting_flag = false;
        deal_overDue_flag = false;
        hotInquiry_flag = false;
        deal_warmInquiry_flag = false;
        deal_coldInquiry_flag = false;
        deal_bookingInquiry_flag = false;
        deal_deliveryInquiry_flag = false;
        deal_sell_lost_Inquiry_flag = false;
        deal_dropInquiry_flag = false;

        Not_attend_skip_check = false;

        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }


}