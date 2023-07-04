package com.example.kumarGroup.ViewInquiryDealStage;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.Catnotattend;
import com.example.kumarGroup.Common_Search.CommonSearchAdapter;
import com.example.kumarGroup.ItemDateComparator;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.Village_List.VillageListShowAdapter;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.Deal_notattend_model;
import com.example.kumarGroup.deal_stage_call_sms_what_model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DealstageRecyclerActivityViewINQ extends AppCompatActivity {

    RecyclerView recyclerView_deal;
    ProgressDialog pro;
    SharedPreferences sp1;
    String emp_id="",catID,multiaction = "Kumar Group",click_id,cat_id;

    SharePref sp;
    Utils utils;

    AdapterShowButtonDataViewINQ adapterShowButtonData;

    private SearchView searchView;
    List<Catnotattend> catShowRCGVS;


    public static boolean notattend_flag_sms_call_what = false;

    public static boolean notattend_flag = false;
    public static boolean hotInquiry_flag = false;
    public static boolean firstmeeting_flag = false;
    public static boolean firstmeeting_otpsend_flag = false;
    public static boolean deal_overDue_flag = false;
    public static boolean deal_warmInquiry_flag = false;
    public static boolean deal_coldInquiry_flag = false;
    public static boolean deal_bookingInquiry_flag = false;
    public static boolean deal_deliveryInquiry_flag = false;
    public static boolean deal_deliveryInquiry_flag_showonly_attachbutton = false;
    public static boolean deal_sell_lost_Inquiry_flag = false;
    public static boolean deal_dropInquiry_flag = false;
    public static boolean deal_nextactivityplanInquiry_flag = false;
    public static boolean deal_nextactivityplanInquiry_flag_sendimage = false;
    public static boolean makeandoffer_dropdown_flag = false;
    public static boolean deal_NegotiationandfinalInquiry_flag = false;
    public static boolean deal_NegotiationandfinalInquiry_flag_other = false;
    public static boolean deal_followup_Inquiry = false;
    public static boolean overandnew_followup = false;
    public static boolean overandnew_negotiaion = false;

    //skip Check
    public static boolean Not_attend_skip_check = false;


    Cursor managedCursor;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealstage_recycler_view_inq);

        multiaction = getIntent().getStringExtra("actionbar");
        click_id = getIntent().getStringExtra("click_id");


        getSupportActionBar().setTitle(multiaction+" View Inquiry");

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

        SharedPreferences sharedPreferencesS = getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
        emp_id = sharedPreferencesS.getString("Slected_EMPID","");


        //Toast.makeText(DealstageRecyclerActivityViewINQ.this, ""+emp_id, Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences2 = getSharedPreferences("catid",MODE_PRIVATE);
        catID = sharedPreferences2.getString("id","");



        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");

        if (notattend_flag){
            notattend_flag = false;

            notattend_flag_sms_call_what = true;

            WebService.getClient().deal_not_attend_web(emp_id,catID,"","","","","","","","").enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-1");
                        Not_attend_skip_check = true;
                        adapterShowButtonData = new AdapterShowButtonDataViewINQ(DealstageRecyclerActivityViewINQ.this, DealstageRecyclerActivityViewINQ.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
//                        Log.d("TAG", "onResponse: Check_id_in_activity"+click_id);
                        AutoSearch();

                        // getLastNumber();
                        //adapterShowButtonData.notifyDataSetChanged();
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,t.getMessage());
                }
            });
        }
        else {

            notattend_flag_sms_call_what = false;
        }

        if (firstmeeting_flag){
            firstmeeting_flag = false;

            firstmeeting_otpsend_flag = true;

            WebService.getClient().deal_firstMeeting_web(emp_id,catID,"","","","","","","","").enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-2");
                        adapterShowButtonData = new AdapterShowButtonDataViewINQ(DealstageRecyclerActivityViewINQ.this, DealstageRecyclerActivityViewINQ.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        AutoSearch();
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,t.getMessage());
                }
            });
        }
        else {
            firstmeeting_otpsend_flag = false;
        }

        if (deal_overDue_flag){
            deal_overDue_flag = false;

            WebService.getClient().deal_overDue_web(emp_id,catID,"","","","","","","","").enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,"No data found");
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-3");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-3");
                        adapterShowButtonData = new AdapterShowButtonDataViewINQ(DealstageRecyclerActivityViewINQ.this, DealstageRecyclerActivityViewINQ.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,t.getMessage());
                }
            });
        }

        if (hotInquiry_flag){
            hotInquiry_flag = false;

            if ("Make An Offer".equals(multiaction)) {

                makeandoffer_dropdown_flag = true;
//                Toast.makeText(DealstageRecyclerActivityViewINQ.this, ""+multiaction, Toast.LENGTH_SHORT).show();
            }
            else {
                makeandoffer_dropdown_flag = false;
            }

            WebService.getClient().deal_hotInquiry_web(emp_id,"HOT",catID,"","","","","","","","").enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Collections.sort(catShowRCGVS, new ItemDateComparator());
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-4");
                        adapterShowButtonData = new AdapterShowButtonDataViewINQ(DealstageRecyclerActivityViewINQ.this, DealstageRecyclerActivityViewINQ.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        AutoSearch();
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,t.getMessage());
                }
            });

        }

        if (deal_warmInquiry_flag){
            deal_warmInquiry_flag = false;

            WebService.getClient().deal_hotInquiry_web(emp_id,"WARM",catID,"","","","","","","","").enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,"No data found");
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-5");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Collections.sort(catShowRCGVS, new ItemDateComparator());
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-5");
                        adapterShowButtonData = new AdapterShowButtonDataViewINQ(DealstageRecyclerActivityViewINQ.this, DealstageRecyclerActivityViewINQ.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,t.getMessage());
                }
            });

        }

        if (deal_nextactivityplanInquiry_flag){
            deal_nextactivityplanInquiry_flag = false;

            deal_nextactivityplanInquiry_flag_sendimage = true;

            Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-6 "+emp_id+" Cat_id "+catID);


            WebService.getClient().deal_hotInquiry_web(emp_id,"WARM",catID,"","","","","","","","").enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,"No data found");
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-6 "+response.body().getCat().size());
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Collections.sort(catShowRCGVS, new ItemDateComparator());
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-6 else "+response.body().getCat().size());
                        adapterShowButtonData = new AdapterShowButtonDataViewINQ(DealstageRecyclerActivityViewINQ.this, DealstageRecyclerActivityViewINQ.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        AutoSearch();
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,t.getMessage());
                }
            });
        }
        else {
            deal_nextactivityplanInquiry_flag_sendimage = false;
        }

        if (deal_coldInquiry_flag){
            deal_coldInquiry_flag = false;

            WebService.getClient().deal_hotInquiry_web(emp_id,"COLD",catID,"","","","","","","","").enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,"No data found");
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-7");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Collections.sort(catShowRCGVS, new ItemDateComparator());
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-7");
                        adapterShowButtonData = new AdapterShowButtonDataViewINQ(DealstageRecyclerActivityViewINQ.this, DealstageRecyclerActivityViewINQ.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        AutoSearch();
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,t.getMessage());
                }
            });

        }

        if (deal_bookingInquiry_flag){
            deal_bookingInquiry_flag = false;

            WebService.getClient().deal_booking_web(emp_id,catID,"","","","","","","","").enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,"No data found");
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-8");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-8");
                        adapterShowButtonData = new AdapterShowButtonDataViewINQ(DealstageRecyclerActivityViewINQ.this, DealstageRecyclerActivityViewINQ.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,t.getMessage());
                }
            });

        }

        if (deal_deliveryInquiry_flag){
            getSupportActionBar().setTitle(multiaction);
            deal_deliveryInquiry_flag = false;

            deal_deliveryInquiry_flag_showonly_attachbutton = true;
            String delivery = getIntent().getStringExtra("Id");
//            SharedPreferences sharedPreferences1 = getSharedPreferences("dealstage3cateid", Context.MODE_PRIVATE);
//            cat_id = sharedPreferences1.getString("catId_eid","");

            WebService.getClient().deal_delivery_web(emp_id,delivery,catID,"","","","","","","").enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,"No data found");
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-9");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-9");
                        adapterShowButtonData = new AdapterShowButtonDataViewINQ(DealstageRecyclerActivityViewINQ.this, DealstageRecyclerActivityViewINQ.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,t.getMessage());
                }
            });

        }

        if (deal_sell_lost_Inquiry_flag){
            deal_sell_lost_Inquiry_flag = false;

            WebService.getClient().deal_selllost_web(emp_id,catID,"","","","","","","","").enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,"No data found");
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-10");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-10");
                        adapterShowButtonData = new AdapterShowButtonDataViewINQ(DealstageRecyclerActivityViewINQ.this, DealstageRecyclerActivityViewINQ.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,t.getMessage());
                }
            });

        }

        if (deal_dropInquiry_flag){
            deal_dropInquiry_flag = false;

            WebService.getClient().deal_dropinq_web(emp_id,catID,"","","","","","","","").enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,"No data found");
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-11");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-11");
                        adapterShowButtonData = new AdapterShowButtonDataViewINQ(DealstageRecyclerActivityViewINQ.this, DealstageRecyclerActivityViewINQ.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,t.getMessage());
                }
            });

        }

        if (deal_NegotiationandfinalInquiry_flag){
            deal_NegotiationandfinalInquiry_flag = false;

            deal_NegotiationandfinalInquiry_flag_other = true;

            overandnew_negotiaion = true;
            Toast.makeText(DealstageRecyclerActivityViewINQ.this, " empid "+emp_id+"catid "+catID, Toast.LENGTH_SHORT).show();
            WebService.getClient().deal_negosiation_web(emp_id,catID,"","","","","","","","").enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,"No data found");
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-12");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-12");
                        adapterShowButtonData = new AdapterShowButtonDataViewINQ(DealstageRecyclerActivityViewINQ.this, DealstageRecyclerActivityViewINQ.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,t.getMessage());
                }
            });

        }
        else {
            overandnew_negotiaion = false;
            deal_NegotiationandfinalInquiry_flag_other = false;
        }

        if (deal_followup_Inquiry){
            deal_followup_Inquiry = false;


            overandnew_followup = true;

            WebService.getClient().deal_followup1_web(emp_id,catID,"","","","","","","","").enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        adapterShowButtonData = new AdapterShowButtonDataViewINQ(DealstageRecyclerActivityViewINQ.this, DealstageRecyclerActivityViewINQ.this, response.body().getCat());
                        recyclerView_deal.setAdapter(adapterShowButtonData);
                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this,t.getMessage());
                }
            });

        }
        else {
            overandnew_followup = false;
        }
    }

    private void AutoSearch() {
        if (CommonSearchAdapter.Common_Search_AutoFill_Search_Check || VillageListShowAdapter.Village_List_AutoFill_Search_Check){
            adapterShowButtonData.getFilter().filter(click_id);
            CommonSearchAdapter.Common_Search_AutoFill_Search_Check = false;
            VillageListShowAdapter.Village_List_AutoFill_Search_Check = false;

            Log.d("TAG", "AutoSearch: VillageList VillageList_Check "+click_id);
        }else {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getLastNumber() {
        Uri contacts = CallLog.Calls.CONTENT_URI;
        Bundle bundle = new Bundle();
        bundle.putInt(ContentResolver.QUERY_ARG_LIMIT, 100);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            utils.userPermission.requestCallLogPermission();
        }

        try {
            managedCursor = getApplicationContext().getContentResolver().query(contacts, null, null, null, CallLog.Calls.DATE + " DESC limit 1;");
        }
        catch (Exception e){

            managedCursor = getApplicationContext().getContentResolver().query(contacts, null, bundle, null);
        }

        if (managedCursor != null && managedCursor.getCount() != 0) {


            int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
            int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
            int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
            int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);

            managedCursor.moveToLast();
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            String callDayTime = new Date(Long.parseLong(callDate)).toString();
            int callDuration = managedCursor.getInt(duration);
            managedCursor.close();

            int dircode = Integer.parseInt(callType);
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
                    Log.d("TAG", "getLastNumber: Check_phone _detail "+sp.getSharedPref(sp.CALL_ID));

                    WebService.getClient().deal_send_call_sms_what_web(sp.getSharedPref(sp.CALL_ID),"Not Attend","").enqueue(new Callback<deal_stage_call_sms_what_model>() {
                        @Override
                        public void onResponse(Call<deal_stage_call_sms_what_model> call, Response<deal_stage_call_sms_what_model> response) {
                            Log.d("TAG", "call_sms_what " + response.body().getMsg());
                            sp.ClearPref();

                            if (notattend_flag_sms_call_what) {
                                WebService.getClient().deal_not_attend_web(emp_id,catID,"","","","","","","","").enqueue(new Callback<Deal_notattend_model>() {
                                    @Override
                                    public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {
                                        if (0 == response.body().getCat().size()) {
                                            Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this, "No data found");
                                            pro.dismiss();
                                        } else {
                                            Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-13");
                                            AdapterShowButtonDataViewINQ adapterShowButtonData = new AdapterShowButtonDataViewINQ(DealstageRecyclerActivityViewINQ.this, DealstageRecyclerActivityViewINQ.this, response.body().getCat());
                                            recyclerView_deal.setAdapter(adapterShowButtonData);
                                            // getLastNumber();
                                            //  adapterShowButtonData.notifyDataSetChanged();
                                            pro.dismiss();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                                        pro.dismiss();
                                        Utils.showErrorToast(DealstageRecyclerActivityViewINQ.this, t.getMessage());
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