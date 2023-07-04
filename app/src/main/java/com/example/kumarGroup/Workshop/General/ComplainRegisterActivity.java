package com.example.kumarGroup.Workshop.General;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

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
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.kumarGroup.CatCBDisplay;
import com.example.kumarGroup.ComplainBoxCallModel;
import com.example.kumarGroup.ComplainBoxDisplayModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;

import com.example.kumarGroup.WebService;

import com.example.kumarGroup.Workshop.General.CompalinFragment.AllComplainFragment;
import com.example.kumarGroup.Workshop.General.CompalinFragment.ClearComplainFragment;
import com.example.kumarGroup.Workshop.General.CompalinFragment.PendingComplainFragment;
import com.example.kumarGroup.Workshop.General.CompalinFragment.RemarkComplainFragment;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplainRegisterActivity extends AppCompatActivity {


    RecyclerView rclvComplainBoxDisplay;


    SharedPreferences sp;
    String emp;
    ProgressDialog progressDialog;

    String date1,date2;

    SharePref sp1;
    Utils utils;
    ViewPager2 viewPager2;
    TableLayout tabLayout;

    ComplainFragmentAdapter adapter1;

    TabLayout tabLayout1;
    ViewPager viewPager21;


    private SearchView searchView;
    List<CatCBDisplay> catShowRCGVS;
    ComplainBoxDisplayAdapter allEntryMonthWeekDayAdapter;

    Cursor managedCursor;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_register);


        getSupportActionBar().setTitle("Complaint Box");
        date1 = getIntent().getStringExtra("StartingDateOne");
        date2 = getIntent().getStringExtra("EndingDateTwo");

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        utils = new Utils(this);
        sp1 = new SharePref(utils.getActivity());

      /*  viewPager2=findViewById(R.id.view_pager);
        tabLayout=findViewById(R.id.tab_layout);
*/
        tabLayout1=findViewById(R.id.profile_tab);
        viewPager21=findViewById(R.id.Profile_ViewPager);
        createViewPager(viewPager21,date1,date2);

        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager21.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        rclvComplainBoxDisplay=findViewById(R.id.rclvComplainBoxDisplay);
        rclvComplainBoxDisplay.setHasFixedSize(true);
        rclvComplainBoxDisplay.setLayoutManager(new LinearLayoutManager(this));

       // sp2 = getSharedPreferences("FeedbackCall",MODE_PRIVATE);

        if (!utils.userPermission.checkCallPermission()) {
            utils.userPermission.requestCallPermission();
        } else if (!utils.userPermission.checkCallLogPermission()) {
            utils.userPermission.requestCallLogPermission();
        } else {
            getLastNumber();
        }


        progressDialog= new ProgressDialog(ComplainRegisterActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getComplainBoxDisplay(date1,date2).enqueue(new Callback<ComplainBoxDisplayModel>() {
            @Override
            public void onResponse(@NotNull Call<ComplainBoxDisplayModel> call,
                                   @NotNull Response<ComplainBoxDisplayModel> response)
            {



              /*  if(response.body().getCat().size() == 0){
                    Utils.showErrorToast(ComplainRegisterActivity.this,"No Data Available");
                }
                else {
                    ComplainBoxDisplayAdapter adapter = new ComplainBoxDisplayAdapter(ComplainRegisterActivity.this,
                            response.body().getCat());
                    rclvComplainBoxDisplay.setAdapter(adapter);
                }*/


                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    catShowRCGVS = response.body().getCat();
                    if (catShowRCGVS.size() == 0) {
                        Utils.showErrorToast(ComplainRegisterActivity.this,"No Data Found");

                    } else {
                        allEntryMonthWeekDayAdapter = new ComplainBoxDisplayAdapter(ComplainRegisterActivity.this,
                                catShowRCGVS);
                        rclvComplainBoxDisplay.setAdapter(allEntryMonthWeekDayAdapter);
                    }
                }
                else {
                    Utils.showNormalToast(ComplainRegisterActivity.this,"Server error please try again later");
                }

            }

            @Override
            public void onFailure(@NotNull Call<ComplainBoxDisplayModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });

    }


    private void createViewPager(ViewPager viewPager21,String date1,String date2) {


        Bundle bundle = new Bundle();
        bundle.putString("date1", date1);
        bundle.putString("date2", date2);

        AllComplainFragment allComplainFragment=new AllComplainFragment();
        allComplainFragment.setArguments(bundle);
        ClearComplainFragment clearComplainFragment=new ClearComplainFragment();
        clearComplainFragment.setArguments(bundle);
        PendingComplainFragment pendingComplainFragment=new PendingComplainFragment();
        pendingComplainFragment.setArguments(bundle);
        RemarkComplainFragment remarkComplainFragment=new RemarkComplainFragment();
        remarkComplainFragment.setArguments(bundle);


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),date1,date2);
        adapter.addFragment( allComplainFragment, "All");
        adapter.addFragment( clearComplainFragment, "Clear");
        adapter.addFragment( pendingComplainFragment, "Pending");
        adapter.addFragment(remarkComplainFragment, "Remark");
        viewPager21.setAdapter(adapter);

        tabLayout1.setupWithViewPager(viewPager21);

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
                    allEntryMonthWeekDayAdapter.getFilter().filter(query);
                    // showDetailGVAdapter.getFilter().filter(query);
                    Log.d("ssss", "onQueryTextChange: "+ allEntryMonthWeekDayAdapter);
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

            if (sp1.getSharedPref(sp1.CALL_ID) != null && !TextUtils.isEmpty(sp1.getSharedPref(sp1.CALL_ID))
                    && sp1.getSharedPref(sp1.PHONE_NUMBER) != null && !TextUtils.isEmpty(sp1.getSharedPref(sp1.PHONE_NUMBER))) {
                if (dircode == CallLog.Calls.OUTGOING_TYPE && callDuration > 0 && sp1.getSharedPref(sp1.PHONE_NUMBER).equals(phNumber)) {
//                float callDurationMinut = (float) callDuration / 60;
                    fillAttendance(sp1.getSharedPref(sp1.CALL_ID), phNumber);
                }
            }

        } else {
            Toast.makeText(this, "Log is Blank !!", Toast.LENGTH_SHORT).show();
        }


    }

    private void fillAttendance(String sharedPref, String phNumber)
    {
        Toast.makeText(this, "Fill Attendance Called ..", Toast.LENGTH_LONG).show();

        Log.d("FillAtten", "fillAttendance: "+sharedPref+" "+phNumber+" "+emp);



        WebService.getClient().getComplainBoxCall(sharedPref).enqueue(new Callback<ComplainBoxCallModel>() {
            @Override
            public void onResponse(@NotNull Call<ComplainBoxCallModel> call,
                                   @NotNull Response<ComplainBoxCallModel> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().getSuccess()) {
                        Toast.makeText(ComplainRegisterActivity.this, "Call Done !!", Toast.LENGTH_SHORT).show();
                        sp1.ClearPref();
                    } else {
                        Toast.makeText(ComplainRegisterActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ComplainRegisterActivity.this, "Server Error !!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ComplainBoxCallModel> call, @NotNull Throwable t) {

            }
        });

    }
}