package com.example.kumarGroup.Notification;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.kumarGroup.DataNotification;
import com.example.kumarGroup.NotificationCallRemoveModel;
import com.example.kumarGroup.NotificationViewModel;
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

public class NotificationMainActivity extends AppCompatActivity {

    RecyclerView rclvDisplayNotification;

    SharedPreferences sp1;
    String emp;

    SharedPreferences sharedPreferences;
    String type;
    public static String WhichType;

    //SharePref sp_noti;
    SharePref sp;
    Utils utils;


    List<DataNotification> data_notifications;

    ProgressDialog progressDialog;

    SwipeRefreshLayout swipeRefreshLayouttask;

    String GetType;
    Cursor managedCursor;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_main);

        getSupportActionBar().setTitle("Notification");

        rclvDisplayNotification=findViewById(R.id.rclvDisplayNotification);

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");


        utils = new Utils(this);
        sp = new SharePref(utils.getActivity());

     //   sharedPreferences = getSharedPreferences("Noti_Day",MODE_PRIVATE);
     //   type = sharedPreferences.getString("type","");

     //   Toast.makeText(this, ""+type, Toast.LENGTH_SHORT).show();

        swipeRefreshLayouttask=findViewById(R.id.swipeRefreshLayouttask);

        rclvDisplayNotification.setHasFixedSize(true);
        rclvDisplayNotification.setLayoutManager(new LinearLayoutManager(this));


        if (!utils.userPermission.checkCallPermission()) {
            utils.userPermission.requestCallPermission();
        } else if (!utils.userPermission.checkCallLogPermission()) {
            utils.userPermission.requestCallLogPermission();
        } else {
            getLastNumberNoti();
        }


        progressDialog= new ProgressDialog(NotificationMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        sharedPreferences = getSharedPreferences("Noti_Day", MODE_PRIVATE);

        WebService.getClient().getNotificationView(emp).enqueue(new Callback<NotificationViewModel>() {
            @Override
            public void onResponse(Call<NotificationViewModel> call, Response<NotificationViewModel> response) {
                progressDialog.dismiss();
                if(response.body().getData().size()==0){
                    sharedPreferences.edit().putInt("notification_day",0).apply();
                    Utils.showErrorToast(NotificationMainActivity.this,"No data available");
                }
                else
                {
                    NotificationMainAdapter adapter = new NotificationMainAdapter(NotificationMainActivity.this,
                            response.body().getData());
                    rclvDisplayNotification.setAdapter(adapter);
                    getLastNumberNoti();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NotificationViewModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });


        swipeRefreshLayouttask.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                WebService.getClient().getNotificationView(emp).enqueue(new Callback<NotificationViewModel>() {
                    @Override
                    public void onResponse(@NotNull Call<NotificationViewModel> call,
                                           @NotNull Response<NotificationViewModel> response) {
                        progressDialog.dismiss();
                        swipeRefreshLayouttask.setRefreshing(false);
                        if(response.body().getData().size()==0){
                            Utils.showErrorToast(NotificationMainActivity.this,"No data available");
                            swipeRefreshLayouttask.setRefreshing(false);

                          /*  NotificationMainAdapter adapter = new NotificationMainAdapter(NotificationMainActivity.this, null);
                            rclvDisplayNotification.setAdapter(adapter);*/
                        }
                        else
                        {
                            NotificationMainAdapter adapter = new NotificationMainAdapter(NotificationMainActivity.this,
                                    response.body().getData());
                            rclvDisplayNotification.setAdapter(adapter);
                            getLastNumberNoti();
                            adapter.notifyDataSetChanged();
                            swipeRefreshLayouttask.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<NotificationViewModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            }
        });

        sharedPreferences = getSharedPreferences("Noti_Day",MODE_PRIVATE);
        GetType = sharedPreferences.getString("notification_type","");

     //   Toast.makeText(this, ""+GetType, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getLastNumberNoti()
    {

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
            utils.printLog("Details", "Phone Number:--- " + phNumber + " ,Call Date:--- " + callDayTime + " ,Call duration in sec :--- " + callDuration);

//            call_id = new String[dataTaskModel.getTask().size()];
//            for (int i = 0; i < dataTaskModel.getTask().size(); i++){
//                call_id[i] = dataTaskModel.getTask().get(i).getCall_id();
//            }
//            int pos = new ArrayList<>(Arrays.asList(call_id)).indexOf(sp.getSharedPref(sp.CALL_ID));

            if (sp.getSharedPref(sp.CALL_ID) != null && !TextUtils.isEmpty(sp.getSharedPref(sp.CALL_ID))
                    && sp.getSharedPref(sp.PHONE_NUMBER) != null && !TextUtils.isEmpty(sp.getSharedPref(sp.PHONE_NUMBER))
                    && sp.getSharedPref(sp.NOTI_TYPE) != null && !TextUtils.isEmpty(sp.getSharedPref(sp.NOTI_TYPE)))
            {
                if (dircode == CallLog.Calls.OUTGOING_TYPE && callDuration > 0
                        && sp.getSharedPref(sp.PHONE_NUMBER).equals(phNumber)) {
//                float callDurationMinut = (float) callDuration / 60;
                    fillAttendance_noti(sp.getSharedPref(sp.CALL_ID), phNumber,sp.getSharedPref(sp.NOTI_TYPE));
                }
            }

        } else {
            Toast.makeText(this, "Log is Blank !!", Toast.LENGTH_SHORT).show();
        }

    }

   /* public static void getdata(String type, final Context context)
    {
        Log.d("type", "getdata: "+type);
        WhichType = type;
       // Toast.makeText(context, ""+type, Toast.LENGTH_SHORT).show();
    }*/

    private void fillAttendance_noti(String callId_noti, String phNumber, String NOTI_TYPE)
    {
      //  Toast.makeText(this, "Fill Notification Called ..", Toast.LENGTH_LONG).show();

      //  Toast.makeText(this, ""+emp+" "+callId_noti+" "+phNumber+" "+NOTI_TYPE, Toast.LENGTH_LONG).show();
        Log.d("logggg", "fillAttendance_noti: "+emp+" "+callId_noti+" "+phNumber+" "+NOTI_TYPE);

        WebService.getClient().getNotificationCallRemove(emp,callId_noti,NOTI_TYPE,phNumber).enqueue(new Callback<NotificationCallRemoveModel>() {
            @Override
            public void onResponse(@NotNull Call<NotificationCallRemoveModel> call, @NotNull Response<NotificationCallRemoveModel> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().getSuccess()) {
                        Toast.makeText(NotificationMainActivity.this, "Call Done !!", Toast.LENGTH_SHORT).show();
                        sp.ClearPref();
                    } else {
                        Toast.makeText(NotificationMainActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(NotificationMainActivity.this, "Server Error !!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<NotificationCallRemoveModel> call, @NotNull Throwable t) {
                Toast.makeText(NotificationMainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}