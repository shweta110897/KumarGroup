package com.example.kumarGroup.BookingDeliveryUpload.PaymentPending;

import static com.example.kumarGroup.Utils.isDate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

import com.example.kumarGroup.DataPaymentPending;
import com.example.kumarGroup.PaymentPendingModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentPendingMainActivity extends AppCompatActivity {

    RecyclerView rclvPendingPayment;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

   /* List<CatPayment> payment_payPenModels;
    String y;*/

    String idPayment;
    int i=0;

    SwipeRefreshLayout swipeRefreshLayoutPaymentPending;

    SharePref SP;
    Utils utils;

    private SearchView searchView;
    List<DataPaymentPending> catShow;
    PaymentPendingDocAdapter showDetailAdapter;

    float totalvallllll=0; float tot1=0;

   // List<Payment_PayPenModel> payment_payPenModels_1;
   Cursor managedCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_pending_main);

//        getSupportActionBar().setTitle("Payment/Pending Data");
        getSupportActionBar().setTitle("Total :- "+tot1);

        rclvPendingPayment = findViewById(R.id.rclvPendingPayment);
        swipeRefreshLayoutPaymentPending = findViewById(R.id.swipeRefreshLayoutPaymentPending);

        rclvPendingPayment.setHasFixedSize(true);
        rclvPendingPayment.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        idPayment = getIntent().getStringExtra("idPayment");


        utils = new Utils(this);
        SP = new SharePref(utils.getActivity());

        if (!utils.userPermission.checkCallPermission()) {
            utils.userPermission.requestCallPermission();
        } else if (!utils.userPermission.checkCallLogPermission()) {
            utils.userPermission.requestCallLogPermission();
        }  else {
            getLastNumber();
        }

        progressDialog= new ProgressDialog(PaymentPendingMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getPaymentPending(emp).enqueue(new Callback<PaymentPendingModel>() {
            @Override
            public void onResponse(@NotNull Call<PaymentPendingModel> call, @NotNull Response<PaymentPendingModel> response) {

                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    catShow = response.body().getData();
                    if (catShow.size() == 0) {
                        Utils.showErrorToast(PaymentPendingMainActivity.this,"No Data Found");

                    } else {

                        for (int i=0;i<response.body().getData().size();i++){

                            if (!isDate(response.body().getData().get(i).getFinal_amt())){
                                totalvallllll= Float.parseFloat(response.body().getData().get(i).getFinal_amt());
                                Log.d("totalvallllll",String.valueOf(totalvallllll));

//                                if (totalvallllll>=0){
                                    tot1=tot1+totalvallllll;
//                                }
                                Log.d("tot123",String.valueOf(tot1));
                            }


                        }

                        Log.d("tot1111",String.valueOf(tot1));
                        getSupportActionBar().setTitle("Total:-"+tot1);
                        showDetailAdapter = new PaymentPendingDocAdapter(PaymentPendingMainActivity.this,
                                catShow,idPayment);
                        Log.d("TAG", "onResponse: Payment-1");

                        rclvPendingPayment.setAdapter(showDetailAdapter);
                        showDetailAdapter.setViewItemInterface(new PaymentPendingDocAdapter.RecyclerViewItemInterface1() {
                            @Override
                            public void onItemClick(int position,  List<DataPaymentPending> dataPaymentPendings,String idPayment) {
//                                showDialogEdit(position,mcatlist);
//                                showDetailAdapter.notifyDataSetChanged();
                                swipeRefreshLayoutPaymentPending.callOnClick();
//
                            }

                        });
                        Log.d("MyResponse", "onResponse: "+response.body().getData());
                        getLastNumber();
                    }
                }
                else {
                    Utils.showNormalToast(PaymentPendingMainActivity.this,"Data Not Found");
                }

            }

            @Override
            public void onFailure(@NotNull Call<PaymentPendingModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                // Toast.makeText(BookingUploadMainActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
                Utils.showErrorToast(PaymentPendingMainActivity.this,"No Data Available");
            }
        });


        swipeRefreshLayoutPaymentPending.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                WebService.getClient().getPaymentPending(emp).enqueue(new Callback<PaymentPendingModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PaymentPendingModel> call, @NotNull Response<PaymentPendingModel> response) {

                        progressDialog.dismiss();

                        swipeRefreshLayoutPaymentPending.setRefreshing(false);

                        assert response.body() != null;

                        if(response.body().getData().size() == 0){
                            Utils.showErrorToast(PaymentPendingMainActivity.this,"No Data Available");
                        }
                        else {
                            Log.d("TAG", "onResponse: Payment-2");
                            PaymentPendingDocAdapter adapter = new PaymentPendingDocAdapter(
                                    PaymentPendingMainActivity.this,
                                    response.body().getData(),idPayment);
                            rclvPendingPayment.setAdapter(adapter);
                            getLastNumber();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<PaymentPendingModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                        // Toast.makeText(BookingUploadMainActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
                        Utils.showErrorToast(PaymentPendingMainActivity.this,"No Data Available");
                    }
                });
            }
        });
    }

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

        try {
            Cursor managedCursor;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q ) {
                Log.e("iff--callhistory","ifff");
//                managedCursor = getApplicationContext().getContentResolver().query(contacts, null, bundle, null);
            }else{
//                managedCursor = getApplicationContext().getContentResolver().query(contacts, null, null, null, android.provider.CallLog.Calls.DATE + " DESC limit 1;");
                Log.e("elsee--callhistory","elsee");
            }

            managedCursor = getApplicationContext().getContentResolver().query(CallLog.Calls.CONTENT_URI,
                    null, null, null, android.provider.CallLog.Calls.DATE /*+ " DESC limit 1;"*//*null*/);


            int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
            int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
            int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
            int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);

            if (managedCursor != null && managedCursor.getCount() != 0) {

                Log.d("calldetailcount", String.valueOf(managedCursor.getCount()));


               /* while(managedCursor.moveToNext())
                {
                   *//* if(managedCursor.isFirst())
                    {*//*
                        //Your code goes here in your case
                        phNumber = managedCursor.getString(number);
                        Log.e("nmbr123---",phNumber);
//                    }
                }*/


//                Log.e("nmbr123---::",phNumber);

//                if (managedCursor.moveToFirst()) { // added line

                while (managedCursor.moveToNext()) {

                    phNumber = managedCursor.getString(number);
                    callType = managedCursor.getString(type);
                    callDate = managedCursor.getString(date);
                    callDayTime = new Date(Long.parseLong(callDate)).toString();
                    callDuration = managedCursor.getInt(duration);

                    dircode = Integer.parseInt(callType);

//                        Log.e("nmbr1234---",phNumber);

//                    }


                }
//                Toast.makeText(TaskWoek.this, managedCursor.getString(number)+"/n"+sp.getSharedPref(sp.PHONE_NUMBER), Toast.LENGTH_LONG).show();

                managedCursor.close();

                utils.printLog("Details123", "Phone Number:--- " + phNumber + " " +",prefnumber:---"+SP.getSharedPref(SP.PHONE_NUMBER)+
                        ",Call Date:--- " + callDayTime + " ,Call duration in sec :--- " + callDuration);

                if (SP.getSharedPref(SP.CALL_ID) != null && !TextUtils.isEmpty(SP.getSharedPref(SP.CALL_ID))
                        && SP.getSharedPref(SP.PHONE_NUMBER) != null && !TextUtils.isEmpty(SP.getSharedPref(SP.PHONE_NUMBER))) {
//                    if (dircode == CallLog.Calls.OUTGOING_TYPE && callDuration > 0 && SP.getSharedPref(SP.PHONE_NUMBER).equals(phNumber)) {
                    if (dircode == CallLog.Calls.OUTGOING_TYPE && callDuration > 0 && SP.getSharedPref(SP.PHONE_NUMBER).equals(phNumber)) {
//                float callDurationMinut = (float) callDuration / 60;
                        fillAttendance(SP.getSharedPref(SP.CALL_ID), phNumber, SP.getSharedPref(SP.task_type));
                    }
                }




            } else {
                Toast.makeText(this, "Log is Blank !!", Toast.LENGTH_SHORT).show();
            }

        } catch (SecurityException e) {
            Log.e("Security Exception", "User denied call log permission");

        }

    }


    private void fillAttendance(String callId, String phoneNumber,String task_type) {
        Toast.makeText(this, "Fill Attendance Called ..", Toast.LENGTH_LONG).show();

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
                if (catShow == null) {
                    //        Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();

                }else if (catShow.size() == 0){
                    //         Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();
                }else{
                    showDetailAdapter.getFilter().filter(query);
                    // showDetailGVAdapter.getFilter().filter(query);
                    Log.d("ssss", "onQueryTextChange: "+ showDetailAdapter);
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


        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

}