package com.example.kumarGroup.FeedbackCall;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.kumarGroup.FeedBackCall;
import com.example.kumarGroup.FeedBackCall_call;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackCallActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String emp_id;
    String cat_id;
    List<com.example.kumarGroup.Cat1> Cat1;
    private SearchView searchView;
    FeedbackCallAdapter feedbackCallAdapter;
    ProgressDialog pro;

    Cursor managedCursor;

    String phonenumber,callerid;
    SharedPreferences sharedPreferences1;

    SharePref sp;
    Utils utils;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_call);

        utils = new Utils(this);
        sp = new SharePref(utils.getActivity());

        if (!utils.userPermission.checkCallPermission()) {
            utils.userPermission.requestCallPermission();
        } else if (!utils.userPermission.checkCallLogPermission()) {
            utils.userPermission.requestCallLogPermission();
        } else {
            getLastNumber();
        }

        recyclerView = findViewById(R.id.feedback_call_recyclerView);
        pro = new ProgressDialog(this);

        SharedPreferences sharedPreferencesS = getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
        emp_id = sharedPreferencesS.getString("Slected_EMPID","");

        SharedPreferences sharedPreferences = getSharedPreferences("dealstage3cateid", Context.MODE_PRIVATE);
        cat_id = sharedPreferences.getString("catId_eid","");

        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");

        Log.d("TAG", "onCreate: emp_id"+emp_id);
        Log.d("TAG", "onCreate: emp_id"+cat_id);
        WebService.getClient().FeedBackCall(emp_id,cat_id).enqueue(new Callback<FeedBackCall>() {
            @Override
            public void onResponse(Call<FeedBackCall> call, Response<FeedBackCall> response) {
                pro.dismiss();
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//
               //recyclerView.setAdapter(new FeedbackCallAdapter(FeedbackCallActivity.this,response.body().getCat()));
                Cat1 = response.body().getCat();

                if (Cat1.size()>0){
                    feedbackCallAdapter = new FeedbackCallAdapter(FeedbackCallActivity.this,FeedbackCallActivity.this,Cat1);
                    recyclerView.setAdapter(feedbackCallAdapter);

                }else {
                    Utils.showErrorToast(FeedbackCallActivity.this,"No data Found");
                }

            }

            @Override
            public void onFailure(Call<FeedBackCall> call, Throwable t) {

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


                while (managedCursor.moveToNext()) {

                    phNumber = managedCursor.getString(number);
                    callType = managedCursor.getString(type);
                    callDate = managedCursor.getString(date);
                    callDayTime = new Date(Long.parseLong(callDate)).toString();
                    callDuration = managedCursor.getInt(duration);
                    dircode = Integer.parseInt(callType);


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


                        Log.d("TAG", "getLastNumber: Call_doneaj-1"+callDuration);
                        Log.d("TAG", "getLastNumber: Call_doneaj-1"+phonenumber);
                        Log.d("TAG", "getLastNumber: Call_doneaj-1"+sp.getSharedPref(sp.CALL_ID));

                        WebService.getClient().FeedBackCall_call(sp.getSharedPref(sp.CALL_ID)).enqueue(new Callback<FeedBackCall_call>() {
                            @Override
                            public void onResponse(Call<FeedBackCall_call> call, Response<FeedBackCall_call> response) {
                              /*  finish();
                                startActivity(getIntent());*/
                                if (response.isSuccessful()) {
                                    assert response.body() != null;
                                    if (response.body().getSuccess()) {
                                        Toast.makeText(FeedbackCallActivity.this, "Call Done !!", Toast.LENGTH_SHORT).show();
                                        sp.ClearPref();
                                    } else {
                                        Toast.makeText(FeedbackCallActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(FeedbackCallActivity.this, "Server Error !!", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<FeedBackCall_call> call, Throwable t) {

                            }
                        });

                    }
                }




            } else {
                Toast.makeText(this, "Log is Blank !!", Toast.LENGTH_SHORT).show();
            }

        } catch (SecurityException e) {
            Log.e("Security Exception", "User denied call log permission");

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

                Log.d("TAG", "onQueryTextChange: asgdjhasgd " + feedbackCallAdapter);


                 //filter recycler view when text is changed
                if (Cat1 == null) {
                    //        Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();

                }else if (Cat1.size() == 0){
                    //         Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();
                }else{
//                    feedbackCallAdapter.getFilter().filter(query);
                    feedbackCallAdapter.getFilter().filter(query);
                    // showDetailGVAdapter.getFilter().filter(query);
                    //Log.d("ssss", "onQueryTextChange: "+ feedbackCallAdapter);
                    //           Toast.makeText(FoVisit.this, "Data", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if(id == R.id.action_search ){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}