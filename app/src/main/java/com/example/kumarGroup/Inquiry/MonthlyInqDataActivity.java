package com.example.kumarGroup.Inquiry;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.Toast;

import com.example.kumarGroup.InquiryAllDatWeekDayMonthModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonthlyInqDataActivity extends AppCompatActivity {

    RecyclerView rclvInqMonthlyData;

    SharedPreferences sp1,sharedPreferences;
    String emp;

    ProgressDialog progressDialog;
    String id;

    SharePref sp;
    Utils utils;
    Cursor managedCursor;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_inq_data);

        rclvInqMonthlyData=findViewById(R.id.rclvInqMonthlyData);

        rclvInqMonthlyData.setLayoutManager(new LinearLayoutManager(this));
        rclvInqMonthlyData.setHasFixedSize(true);

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        id = getIntent().getStringExtra("id");


        sharedPreferences = getSharedPreferences("DateCurrent2_inq", MODE_PRIVATE);


         utils = new Utils(this);
        sp = new SharePref(utils.getActivity());

        if (!utils.userPermission.checkCallPermission()) {
            utils.userPermission.requestCallPermission();
        } else if (!utils.userPermission.checkCallLogPermission()) {
            utils.userPermission.requestCallLogPermission();
        }  else {
            getLastNumber();
        }



        progressDialog= new ProgressDialog(MonthlyInqDataActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getInquiryAllData(id,emp).enqueue(new Callback<InquiryAllDatWeekDayMonthModel>() {
            @Override
            public void onResponse(@NotNull Call<InquiryAllDatWeekDayMonthModel> call,
                                   @NotNull Response<InquiryAllDatWeekDayMonthModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;
                if(response.body().getCat().size()==0){
                    Utils.showErrorToast(MonthlyInqDataActivity.this,"No Visit Available");
                    sharedPreferences.edit().putInt("CurrentDateOrNull1_inq",0).apply();

                }
                else{
                    MonthlyInqDataDisplayAdapter monthlyInqDataDisplayAdapter = new
                            MonthlyInqDataDisplayAdapter(MonthlyInqDataActivity.this,MonthlyInqDataActivity.this,
                            response.body().getCat());
                    rclvInqMonthlyData.setAdapter(monthlyInqDataDisplayAdapter);
                    getLastNumber();
                }

               /* MonthlyInqDataDisplayAdapter monthlyInqDataDisplayAdapter = new
                        MonthlyInqDataDisplayAdapter(MonthlyInqDataActivity.this,
                        response.body().getCat());
                rclvInqMonthlyData.setAdapter(monthlyInqDataDisplayAdapter);*/
            }

            @Override
            public void onFailure(@NotNull Call<InquiryAllDatWeekDayMonthModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
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
            utils.printLog("Details", "Phone Number:--- " + phNumber + " ,Call Date:--- " + callDayTime + " ,Call duration in sec :--- " + callDuration);

//            call_id = new String[dataTaskModel.getTask().size()];
//            for (int i = 0; i < dataTaskModel.getTask().size(); i++){
//                call_id[i] = dataTaskModel.getTask().get(i).getCall_id();
//            }
//            int pos = new ArrayList<>(Arrays.asList(call_id)).indexOf(sp.getSharedPref(sp.CALL_ID));
            if (sp.getSharedPref(sp.CALL_ID) != null && !TextUtils.isEmpty(sp.getSharedPref(sp.CALL_ID)) && sp.getSharedPref(sp.PHONE_NUMBER) != null && !TextUtils.isEmpty(sp.getSharedPref(sp.PHONE_NUMBER))){
                if (dircode == CallLog.Calls.OUTGOING_TYPE && callDuration > 0 && sp.getSharedPref(sp.PHONE_NUMBER).equals(phNumber)) {
//                float callDurationMinut = (float) callDuration / 60;
                    fillAttendance(sp.getSharedPref(sp.CALL_ID), phNumber,sp.getSharedPref(sp.task_type));
                }
            }

        } else {
            Toast.makeText(this, "Log is Blank !!", Toast.LENGTH_SHORT).show();
        }


    }

    private void fillAttendance(String callId, String phoneNumber,String task_type) {
        Toast.makeText(this, "Fill Attendance Called ..", Toast.LENGTH_LONG).show();

    }


}