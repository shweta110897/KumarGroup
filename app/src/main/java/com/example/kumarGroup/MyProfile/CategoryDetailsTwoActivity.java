package com.example.kumarGroup.MyProfile;

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

import com.example.kumarGroup.CatViewProfDetail;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewProfileCategoryDetailsModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryDetailsTwoActivity extends AppCompatActivity {

    RecyclerView rclvShowDetailProfile;

    SharedPreferences sp1,sp2;
    String emp,sp_stateId ;

    ProgressDialog progressDialog;

    String catId_eid;


    private SearchView searchView;
    List<CatViewProfDetail> catShowRCGVS;
    CategoryDetailsTwoAdapter showDetailGVAdapter;

    SharePref sp;
    Utils utils;

    Cursor managedCursor;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details_two);


        rclvShowDetailProfile=findViewById(R.id.rclvShowDetailProfile);
        rclvShowDetailProfile.setHasFixedSize(true);
        rclvShowDetailProfile.setLayoutManager(new LinearLayoutManager(this));

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");


        /* sp2 = getSharedPreferences("StateId",MODE_PRIVATE);

        sp_stateId = sp2.getString("StateId","");*/

        sp2 = getSharedPreferences("MyProfileId",MODE_PRIVATE);
        sp_stateId =sp2.getString("StateId","");


        catId_eid=getIntent().getStringExtra("catId_eid");


          utils = new Utils(this);
        sp = new SharePref(utils.getActivity());

        if (!utils.userPermission.checkCallPermission()) {
            utils.userPermission.requestCallPermission();
        } else if (!utils.userPermission.checkCallLogPermission()) {
            utils.userPermission.requestCallLogPermission();
        }  else {
            getLastNumber();
        }


        progressDialog = new ProgressDialog(CategoryDetailsTwoActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getViewProfileDetails(catId_eid,sp_stateId).enqueue(new Callback<ViewProfileCategoryDetailsModel>() {
            @Override
            public void onResponse(@NotNull Call<ViewProfileCategoryDetailsModel> call,
                                   @NotNull Response<ViewProfileCategoryDetailsModel> response) {



                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    catShowRCGVS = response.body().getCat();
                    if (catShowRCGVS.size() == 0) {
                        Utils.showErrorToast(CategoryDetailsTwoActivity.this,"No Visit Available");

                    } else {
                        showDetailGVAdapter = new CategoryDetailsTwoAdapter(CategoryDetailsTwoActivity.this, catShowRCGVS);
                        rclvShowDetailProfile.setAdapter(showDetailGVAdapter);
                        getLastNumber();

                    }
                }
                else {
                    Utils.showNormalToast(CategoryDetailsTwoActivity.this,"Server error please try again later");
                }




               /* if (response.body().getCat().size() == 0) {
                    Utils.showErrorToast(CategoryDetailsTwoActivity.this,"No Data Found");

                } else {
                    CategoryDetailsTwoAdapter adapter= new CategoryDetailsTwoAdapter(CategoryDetailsTwoActivity.this,
                            response.body().getCat());
                    rclvShowDetailProfile.setAdapter(adapter);
                }*/
            }

            @Override
            public void onFailure(@NotNull Call<ViewProfileCategoryDetailsModel> call, @NotNull Throwable t) {

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
                    showDetailGVAdapter.getFilter().filter(query);
                    // showDetailGVAdapter.getFilter().filter(query);
                    Log.d("ssss", "onQueryTextChange: "+ showDetailGVAdapter);
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