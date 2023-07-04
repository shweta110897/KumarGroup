package com.example.kumarGroup;

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

public class Activity extends AppCompatActivity {
    String emp,id;
    SharedPreferences sp1;
    RecyclerView recyclerview;
    SharePref sp;
    SwipeRefreshLayout swipeRefreshLayout;
    Utils utils;
    //private DataTaskModel dataTaskModel = null;
    List<DataTask> listtask;
    ProgressDialog progressDialog;
    Button txtvhideactivity;
    Cursor managedCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_);
        getSupportActionBar().setTitle( ( "Assignment"));

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);


        emp=sp1.getString("emp_id","");
        id=sp1.getString("id","");

        utils = new Utils(this);
        sp = new SharePref(utils.getActivity());
        recyclerview=findViewById(R.id.recyclerviewActivity);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        txtvhideactivity=findViewById(R.id.txtvhideactivity);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        if (!utils.userPermission.checkCallPermission()) {
            utils.userPermission.requestCallPermission();
        } else if (!utils.userPermission.checkCallLogPermission()) {
            utils.userPermission.requestCallLogPermission();
        }  else {
            getLastNumber();
        }
        jsonLoad();
    }

    private void jsonLoad(){

        progressDialog= new ProgressDialog(Activity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        WebService.getClient().gettask(emp,"assignment").enqueue(new Callback<DataTaskModel>() {
            @Override
            public void onResponse(@NotNull Call<DataTaskModel> call, @NotNull Response<DataTaskModel> response) {
                progressDialog.dismiss();

                //   recyclerview.setAdapter(AdapterTaskinfo); Log.d("ManageRL", "onResponse: "+response);
                if (response.isSuccessful()) {
                    listtask = response.body().getTask();
                    if (listtask.size() == 0) {
                        Toast.makeText(Activity.this, "Assignment Is complete", Toast.LENGTH_SHORT).show();
                        // Utils.showErrorToast(TaskWoek.this,"No Data Found");
                        txtvhideactivity.setVisibility(View.VISIBLE);
                        txtvhideactivity.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i = new Intent(Activity.this,FoDashbord.class);
                                startActivity(i);
                                finish();
                            }
                        });


                    } else {
                        AdapterActivity adapter=new AdapterActivity(response.body().getTask(), Activity.this);
                        recyclerview.setAdapter(adapter);
                        getLastNumber();
                    }
                }
                else {
                    Utils.showNormalToast(Activity.this,"Server error please try again later");
                }
    /*            if (response.isSuccessful()){
                    dataTaskModel = response.body();
                    if (dataTaskModel != null){
                        Log.d(TAG, "onResponse: "+dataTaskModel.getTask());
                        assert response.body() != null;
                        AdapterTaskinfo adapter=new AdapterTaskinfo(dataTaskModel.getTask(), TaskWoek.this);
                        recyclerview.setAdapter(adapter);
                        getLastNumber();

                    } else {
                        Toast.makeText(TaskWoek.this, "List is empty !!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TaskWoek.this, "Server Error !!", Toast.LENGTH_SHORT).show();
                }*/
            }

            @Override
            public void onFailure(@NotNull Call<DataTaskModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();

                Log.e("", t.getMessage());
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                WebService.getClient().gettask(emp,"assignment").enqueue(new Callback<DataTaskModel>() {
                    @Override
                    public void onResponse(@NotNull Call<DataTaskModel> call, @NotNull Response<DataTaskModel> response) {
                        progressDialog.dismiss();

                        //   recyclerview.setAdapter(AdapterTaskinfo); Log.d("ManageRL", "onResponse: "+response);
                        if (response.isSuccessful()) {
                            listtask = response.body().getTask();
                            if (listtask.size() == 0) {
                                Toast.makeText(Activity.this, "Assignment Is Completed", Toast.LENGTH_SHORT).show();
                                // Utils.showErrorToast(TaskWoek.this,"No Data Found");
                                txtvhideactivity.setVisibility(View.VISIBLE);
                                txtvhideactivity.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent i = new Intent(Activity.this,FoDashbord.class);
                                        startActivity(i);
                                        finish();
                                    }
                                });

                                swipeRefreshLayout.setRefreshing(false);

                            } else {
                                AdapterActivity adapter=new AdapterActivity(response.body().getTask(), Activity.this);
                                recyclerview.setAdapter(adapter);
                                getLastNumber();
                                adapter.notifyDataSetChanged();

                                swipeRefreshLayout.setRefreshing(false);

                            }
                        }
                        else {
                            Utils.showNormalToast(Activity.this,"Server error please try again later");
                        }
    /*            if (response.isSuccessful()){
                    dataTaskModel = response.body();
                    if (dataTaskModel != null){
                        Log.d(TAG, "onResponse: "+dataTaskModel.getTask());
                        assert response.body() != null;
                        AdapterTaskinfo adapter=new AdapterTaskinfo(dataTaskModel.getTask(), TaskWoek.this);
                        recyclerview.setAdapter(adapter);
                        getLastNumber();

                    } else {
                        Toast.makeText(TaskWoek.this, "List is empty !!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TaskWoek.this, "Server Error !!", Toast.LENGTH_SHORT).show();
                }*/
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

    private void fillAttendance(String callId, String phoneNumber,String task_type) {
        Toast.makeText(this, "Fill Attendance Called ..", Toast.LENGTH_LONG).show();
        WebService.getClient().removeCallassignment(callId, phoneNumber, "",emp,task_type).enqueue(new Callback<DataremoveassignmentModel>() {
            @Override
            public void onResponse(@NotNull Call<DataremoveassignmentModel> call, @NotNull Response<DataremoveassignmentModel> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    if (response.body().getSuccess()){
                        Toast.makeText(Activity.this, "Call Done !!", Toast.LENGTH_SHORT).show();
                        sp.ClearPref();
                    } else {
                        Toast.makeText(Activity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Activity.this, "Server Error !!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<DataremoveassignmentModel> call, @NotNull Throwable t) {
                Toast.makeText(Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
