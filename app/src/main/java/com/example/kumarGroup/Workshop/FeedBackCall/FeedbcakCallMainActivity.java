package com.example.kumarGroup.Workshop.FeedBackCall;

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
import android.widget.Button;
import android.widget.Toast;

import com.example.kumarGroup.DataTask;
import com.example.kumarGroup.FeedbackCallWSModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.Workshop.WorkshopMainActivity;
import com.example.kumarGroup.WorkshopFeedbackCallremoveModel;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbcakCallMainActivity extends AppCompatActivity {

    RecyclerView rclvFeedbackCall;

    String emp, id;
    SharedPreferences sp1,sp2;

    ProgressDialog progressDialog;

    SwipeRefreshLayout swipeRefreshWSRemoveCall;

    SharePref sp;
    Utils utils;

    List<DataTask> listtask;

    Button txtvhidetask;
    Cursor managedCursor;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbcak_call_main);

        sp1 = getSharedPreferences("Login", MODE_PRIVATE);

        emp = sp1.getString("emp_id", "");
        id = sp1.getString("id", "");

        utils = new Utils(this);
        sp = new SharePref(utils.getActivity());


        swipeRefreshWSRemoveCall=findViewById(R.id.swipeRefreshWSRemoveCall);

        rclvFeedbackCall=findViewById(R.id.rclvFeedbackCall);
        rclvFeedbackCall.setHasFixedSize(true);
        rclvFeedbackCall.setLayoutManager(new LinearLayoutManager(this));


        sp2 = getSharedPreferences("FeedbackCall",MODE_PRIVATE);

        if (!utils.userPermission.checkCallPermission()) {
            utils.userPermission.requestCallPermission();
        } else if (!utils.userPermission.checkCallLogPermission()) {
            utils.userPermission.requestCallLogPermission();
        } else {
            getLastNumber();
        }



        progressDialog= new ProgressDialog(FeedbcakCallMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        WebService.getClient().getFeedbackWs().enqueue(new Callback<FeedbackCallWSModel>() {
            @Override
            public void onResponse(@NotNull Call<FeedbackCallWSModel> call,
                                   @NotNull Response<FeedbackCallWSModel> response) {

                progressDialog.dismiss();
                if(response.body().getData().size()==0){
                    Utils.showErrorToast(FeedbcakCallMainActivity.this,"No Data Available");
                    sp2.edit().putInt("FeedbackCall",0).apply();

                }
                else{
                    sp2.edit().putInt("FeedbackCall",1).apply();

                    FeedbackMainAdapter adapter = new FeedbackMainAdapter(FeedbcakCallMainActivity.this,
                            response.body().getData());
                    rclvFeedbackCall.setAdapter(adapter);;

                    Log.d("me1", "onResponse: "+response.body().getData());

                    getLastNumber();
                    adapter.notifyDataSetChanged();

                   /* FeedbackMainAdapter adpater1 = new FeedbackMainAdapter(FeedbcakCallMainActivity.this,
                            response.body().getData());
                    rclvFeedbackCall.setAdapter(adpater1);*/
                }

            }

            @Override
            public void onFailure(@NotNull Call<FeedbackCallWSModel> call,
                                  @NotNull Throwable t) {
                progressDialog.dismiss();

            }
        });



        swipeRefreshWSRemoveCall.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                WebService.getClient().getFeedbackWs().enqueue(new Callback<FeedbackCallWSModel>() {
                    @Override
                    public void onResponse(@NotNull Call<FeedbackCallWSModel> call,
                                           @NotNull Response<FeedbackCallWSModel> response) {
                        swipeRefreshWSRemoveCall.setRefreshing(false);

                       // progressDialog.dismiss();
                        assert response.body() != null;
                        if(response.body().getData().size()==0){

                            Utils.showErrorToast(FeedbcakCallMainActivity.this,"No Data Available");

                            Intent i = new Intent(FeedbcakCallMainActivity.this, WorkshopMainActivity.class);
                            startActivity(i);

                        }
                        else{

                            FeedbackMainAdapter adapter = new FeedbackMainAdapter(FeedbcakCallMainActivity.this,
                                    response.body().getData());
                            rclvFeedbackCall.setAdapter(adapter);;

                            Log.d("me1", "onResponse: "+response.body().getData());

                           // adapter.notifyDataSetChanged();

                            getLastNumber();

                            adapter.notifyDataSetChanged();
                            swipeRefreshWSRemoveCall.setRefreshing(false);
                        }

                    }

                    @Override
                    public void onFailure(@NotNull Call<FeedbackCallWSModel> call,
                                          @NotNull Throwable t) {
                        progressDialog.dismiss();

                    }
                });
            }
        });
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
            utils.printLog("Details", "Phone Number:--- " + phNumber + "" +
                    " ,Call Date:--- " + callDayTime + " ,Call duration in sec :--- " + callDuration);

            if (sp.getSharedPref(sp.CALL_ID) != null && !TextUtils.isEmpty(sp.getSharedPref(sp.CALL_ID))
                    && sp.getSharedPref(sp.PHONE_NUMBER) != null && !TextUtils.isEmpty(sp.getSharedPref(sp.PHONE_NUMBER))) {
                if (dircode == CallLog.Calls.OUTGOING_TYPE && callDuration > 0 && sp.getSharedPref(sp.PHONE_NUMBER).equals(phNumber)) {
//                float callDurationMinut = (float) callDuration / 60;
                    fillAttendance(sp.getSharedPref(sp.CALL_ID), phNumber);
                }
            }

        } else {
            Toast.makeText(this, "Log is Blank !!", Toast.LENGTH_SHORT).show();
        }


    }

    private void fillAttendance(String callId, String phoneNumber) {

        Toast.makeText(this, "Fill Attendance Called ..", Toast.LENGTH_LONG).show();

        Log.d("FillAtten", "fillAttendance: "+callId+" "+phoneNumber+" "+emp);


        WebService.getClient().getRemoveCallWs(emp,callId, phoneNumber).enqueue(new Callback<WorkshopFeedbackCallremoveModel>() {
            @Override
            public void onResponse(@NotNull Call<WorkshopFeedbackCallremoveModel> call,
                                   @NotNull Response<WorkshopFeedbackCallremoveModel> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().getSuccess()) {
                        Toast.makeText(FeedbcakCallMainActivity.this, "Call Done !!", Toast.LENGTH_SHORT).show();
                        sp.ClearPref();
                    } else {
                        Toast.makeText(FeedbcakCallMainActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(FeedbcakCallMainActivity.this, "Server Error !!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<WorkshopFeedbackCallremoveModel> call,
                                  @NotNull Throwable t) {
                Toast.makeText(FeedbcakCallMainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}