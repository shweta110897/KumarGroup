package com.example.kumarGroup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskWoek extends AppCompatActivity{

    private static final String TAG = TaskWoek.class.getSimpleName();

    String emp, id;
    SharedPreferences sp1,sp2;
    RecyclerView recyclerview;
    SharePref sp;
    Utils utils;
    //  DataTaskModel dataTaskModel = null;
    List<DataTask> listtask;
    ProgressDialog progressDialog;
    Button txtvhidetask;
    SwipeRefreshLayout swipeRefreshLayouttask;

    String checkInPhase;
    Cursor managedCursor;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_woek);

        getSupportActionBar().setTitle(("Your Today Task"));

        sp1 = getSharedPreferences("Login", MODE_PRIVATE);

        emp = sp1.getString("emp_id", "");
        Log.d("emppppp", "onCreate: " + emp);
        id = sp1.getString("id", "");

        utils = new Utils(this);
        sp = new SharePref(utils.getActivity());

        /*sp2 = getSharedPreferences("CheckInPhase", MODE_PRIVATE);
        checkInPhase = sp2.getString("CheckInPhase","1");*/


        recyclerview = findViewById(R.id.recyclerviewTask);

        swipeRefreshLayouttask=findViewById(R.id.swipeRefreshLayouttask);

        txtvhidetask = findViewById(R.id.txtvhidetask);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        if (!utils.userPermission.checkCallPermission()) {
            utils.userPermission.requestCallPermission();
        } else if (!utils.userPermission.checkCallLogPermission()) {
            utils.userPermission.requestCallLogPermission();
        } else {

            getLastNumber();
        }
        jsonLoad();
    }

    private void jsonLoad() {

        progressDialog = new ProgressDialog(TaskWoek.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().gettask(emp, "attendant").enqueue(new Callback<DataTaskModel>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(@NotNull Call<DataTaskModel> call, @NotNull Response<DataTaskModel> response) {
                progressDialog.dismiss();

                //   recyclerview.setAdapter(AdapterTaskinfo); Log.d("ManageRL", "onResponse: "+response);
                if (response.isSuccessful()) {
                    listtask = response.body().getTask();
                    Log.d("shanawaz", "onResponse: " + listtask);
                    if (listtask.size() == 0) {
                        Toast.makeText(TaskWoek.this, "Task Is complete", Toast.LENGTH_SHORT).show();
                        // Utils.showErrorToast(TaskWoek.this,"No Data Found");
                        txtvhidetask.setVisibility(View.VISIBLE);
                        txtvhidetask.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                progressDialog = new ProgressDialog(TaskWoek.this);
                                progressDialog.show();
                                progressDialog.setContentView(R.layout.progress_dialog);
                                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                                WebService.getClient().getcompettask(emp, id).enqueue(new Callback<DataTaskCompletModel>() {
                                    @Override
                                    public void onResponse(@NotNull Call<DataTaskCompletModel> call,
                                                           @NotNull Response<DataTaskCompletModel> response) {
                                        Intent i = new Intent(TaskWoek.this, LanchBreak.class);
                                        startActivity(i);
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(@NotNull Call<DataTaskCompletModel> call, @NotNull Throwable t) {
                                        progressDialog.dismiss();
                                    }
                                });
                            }
                        });

                    }
                    else {
                        AdapterTaskinfo adapter = new AdapterTaskinfo(response.body().getTask(), TaskWoek.this);
                        Log.d("me", "onResponse: "+response.body().getTask());
                        Log.d(TAG, "onResponse: Checkresponce-1 ");
                        recyclerview.setAdapter(adapter);
                        getLastNumber();
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Utils.showNormalToast(TaskWoek.this, "Server error please try again later");
                }
            }

            @Override
            public void onFailure(@NotNull Call<DataTaskModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();

                Log.e(TAG, t.getMessage());
            }
        });


       swipeRefreshLayouttask.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
       {
            @Override
            public void onRefresh() {
                WebService.getClient().gettask(emp,"attendant").enqueue(new Callback<DataTaskModel>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(@NotNull Call<DataTaskModel> call, @NotNull Response<DataTaskModel> response) {
                        progressDialog.dismiss();
                        //   recyclerview.setAdapter(AdapterTaskinfo); Log.d("ManageRL", "onResponse: "+response);
                      if (response.isSuccessful()) {
                            listtask = response.body().getTask();

                            if (listtask.size() == 0) {
                                Toast.makeText(TaskWoek.this, "Task is complete", Toast.LENGTH_SHORT).show();
                               //  Utils.showErrorToast(TaskWoek.this,"No Data Found");
                                txtvhidetask.setVisibility(View.VISIBLE);
                                txtvhidetask.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent i = new Intent(TaskWoek.this,LanchBreak.class);
                                        startActivity(i);
                                        finish();
                                    }
                                });
                                swipeRefreshLayouttask.setRefreshing(false);

                            }

                            else {
                                AdapterTaskinfo adapter=new AdapterTaskinfo(response.body().getTask(), TaskWoek.this);
                                Log.d(TAG, "onResponse: Checkresponce-2");

                                recyclerview.setAdapter(adapter);
                               /* adapter.setViewItemInterface(new AdapterTaskinfo.CallItemInterface() {

                                    @Override
                                    public void onItemClick(int position, String callId) {
                                        listtask.remove(position);
                                        adapter.notifyDataSetChanged();
                                        getLastNumber();
                                        swipeRefreshLayouttask.setRefreshing(false);


                                    }
                                });*/
                                getLastNumber();
                                adapter.notifyDataSetChanged();
                                swipeRefreshLayouttask.setRefreshing(false);
                            }
                        }
                        else {
                            Utils.showNormalToast(TaskWoek.this,"Server error please try again later");
                        }
                    }
                    @Override
                    public void onFailure(@NotNull Call<DataTaskModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                        Log.e("", t.getMessage());
                    }
                });
            }
        });
    }
/*
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getLastNumber1() {

        Uri contacts = CallLog.Calls.CONTENT_URI;
        Bundle bundle = new Bundle();
        bundle.putInt(ContentResolver.QUERY_ARG_LIMIT, 100);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            utils.userPermission.requestCallLogPermission();
        }

        try {
//            managedCursor = managedQuery(CallLog.Calls.CONTENT_URI,
//                    null, null, null, android.provider.CallLog.Calls.DATE + " DESC limit 1;");
            managedCursor = getApplicationContext().getContentResolver().query(contacts, null, null, null, android.provider.CallLog.Calls.DATE + " DESC limit 1;");
        }
        catch (Exception e){

            managedCursor = getApplicationContext().getContentResolver().query(contacts, null, bundle, null);
        }
        //ContentResolver cr = getApplicationContext().getContentResolver();

//        int totalCall = 1;
//
//        if (managedCursor != null) {
//            totalCall = 10; // intenger call log limit
//
//            if (managedCursor.moveToLast()) { //starts pulling logs from last - you can use moveToFirst() for first logs
//                for (int j = 0; j < totalCall; j++) {
//                    String phNumber = managedCursor.getString(managedCursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER));
//                    Log.d("TAG", "getLastNumber: Checkcallnumber "+phNumber);
//                }
//            }
//        }
            if (managedCursor != null && managedCursor.getCount() != 0) {
//                if (managedCursor.moveToLast()){
//                    for (int j = 0; j < 1; j++) {
//                    String phNumber = managedCursor.getString(managedCursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER));
//                    Log.d("TAG", "getLastNumber: Checkcallnumber "+phNumber);
//                }
////                    String phNumber = managedCursor.getString(managedCursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER));
////                    Log.d("TAG", "getLastNumber: Checkcallnumber "+phNumber);
//                }
                int number = managedCursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER);
                int type = managedCursor.getColumnIndexOrThrow(CallLog.Calls.TYPE);
                int date = managedCursor.getColumnIndexOrThrow(CallLog.Calls.DATE);
                int duration = managedCursor.getColumnIndexOrThrow(CallLog.Calls.DURATION);

                managedCursor.moveToLast();
                String phNumber = managedCursor.getString(number);
                String callType = managedCursor.getString(type);
                String callDate = managedCursor.getString(date);
                String callDayTime = new Date(Long.parseLong(callDate)).toString();
                int callDuration = managedCursor.getInt(duration);
                managedCursor.close();

                int dircode = Integer.parseInt(callType);
                utils.printLog("Details123", "Phone Number:--- " + phNumber + " " +
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
                        fillAttendance(sp.getSharedPref(sp.CALL_ID), phNumber, sp.getSharedPref(sp.task_type));
                    }
                }

            } else {
                Toast.makeText(this, "Log is Blank !!", Toast.LENGTH_SHORT).show();
            }
      //  }
//        catch (Exception e){
//            Utils.showErrorToast(TaskWoek.this,e.getMessage());
//        }
    }*/

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

                utils.printLog("Details123", "Phone Number:--- " + phNumber + " " +",prefnumber:---"+sp.getSharedPref(sp.PHONE_NUMBER)+
                        ",Call Date:--- " + callDayTime + " ,Call duration in sec :--- " + callDuration);

                if (sp.getSharedPref(sp.CALL_ID) != null && !TextUtils.isEmpty(sp.getSharedPref(sp.CALL_ID))
                        && sp.getSharedPref(sp.PHONE_NUMBER) != null && !TextUtils.isEmpty(sp.getSharedPref(sp.PHONE_NUMBER))) {
//                    if (dircode == CallLog.Calls.OUTGOING_TYPE && callDuration > 0 && sp.getSharedPref(sp.PHONE_NUMBER).equals(phNumber)) {
                    if (dircode == CallLog.Calls.OUTGOING_TYPE && callDuration > 0 && sp.getSharedPref(sp.PHONE_NUMBER).equals(phNumber)) {
//                float callDurationMinut = (float) callDuration / 60;
                        fillAttendance(sp.getSharedPref(sp.CALL_ID), phNumber, sp.getSharedPref(sp.task_type));
                    }
                }




            } else {
                Toast.makeText(this, "Log is Blank !!", Toast.LENGTH_SHORT).show();
            }

        } catch (SecurityException e) {
            Log.e("Security Exception", "User denied call log permission");

        }
    
    }

    private void fillAttendance(String callId, String phoneNumber,String task_type ) {
//        Toast.makeText(this, "Fill Attendance Called ..", Toast.LENGTH_LONG).show();
        Log.d("FillAtten", "fillAttendance: "+callId+" "+phoneNumber+" "+emp);
        WebService.getClient().removeCall(callId, phoneNumber, "", emp,task_type).enqueue(new Callback<DataRemoveCallModel>() {
            @Override
            public void onResponse(@NotNull Call<DataRemoveCallModel> call, @NotNull Response<DataRemoveCallModel> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().getSuccess()) {
                        Toast.makeText(TaskWoek.this, "Call Done !!", Toast.LENGTH_SHORT).show();
                        sp.ClearPref();
                    } else {
                        Toast.makeText(TaskWoek.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TaskWoek.this, "Server Error !!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<DataRemoveCallModel> call, @NotNull Throwable t) {
                Toast.makeText(TaskWoek.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*@Override
    public void onResume(){
        super.onResume();

        WebService.getClient().getphase(id).enqueue(new Callback<ChackphaseModel>() {
            @Override
            public void onResponse(@NotNull Call<ChackphaseModel> call, @NotNull Response<ChackphaseModel> response) {
                assert response.body() != null;
                Log.d("Phase", "onResponse: " + response.body().getPhase());

                if (response.body().getPhase().equals("2")) {
                    Intent i = new Intent(TaskWoek.this, LanchBreak.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ChackphaseModel> call, @NotNull Throwable t) {

                Toast.makeText(TaskWoek.this, "null", Toast.LENGTH_SHORT).show();
            }
        });

    }*/

   /* public static CallDetails getLastCallDetails(Context context) {

        CallDetails callDetails = new CallDetails();

        Uri contacts = CallLog.Calls.CONTENT_URI;
        try {

            Cursor managedCursor = context.getContentResolver().query(contacts, null, null, null, android.provider.CallLog.Calls.DATE + " DESC limit 1;");

            int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
            int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
            int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
            int incomingtype = managedCursor.getColumnIndex(String.valueOf(CallLog.Calls.INCOMING_TYPE));

            if(managedCursor.moveToFirst()){ // added line

                while (managedCursor.moveToNext()) {
                    String callType;
                    String phNumber = managedCursor.getString(number);
                    String callerName = getContactName(context, phNumber);
                    if(incomingtype == -1){
                        callType = "incoming";
                    }
                    else {
                        callType = "outgoing";
                    }
                    String callDate = managedCursor.getString(date);
                    String callDayTime = new      Date(Long.valueOf(callDate)).toString();

                    String callDuration = managedCursor.getString(duration);

                    callDetails.setCallerName(callerName);
                    callDetails.setCallerNumber(phNumber);
                    callDetails.setCallDuration(callDuration);
                    callDetails.setCallType(callType);
                    callDetails.setCallTimeStamp(callDayTime);

                }
            }
            managedCursor.close();

        } catch (SecurityException e) {
            Log.e("Security Exception", "User denied call log permission");

        }

        return callDetails;

    }*/


}
