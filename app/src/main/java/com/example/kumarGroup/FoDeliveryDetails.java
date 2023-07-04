package com.example.kumarGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.myProfileNew.GeneralVisitFormActivity;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoDeliveryDetails extends AppCompatActivity {

    TextView tvd_First_name, tv_customor_do_it, tv_mobilenumber, tvd_district, tv_village, tvd_taluko, tvd_Last_name, tv_Whappnumber;
    TextView lin_whapp, lin_call, lin_email, lin_sms, lin_share;

    String Mobilecall, Whatapp, Customordoit, FirstName, LastName, Mobile, Village, district, Taluko,
            Id,cat_id,eid;
    String sms = "";//The message you want to text to the phone

    SharedPreferences sp;
    String emp;

    SharePref SP;

    Utils utils;
    Activity activity;

    LinearLayout linDataNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fo_delivery_details);
        getSupportActionBar().setTitle("Profile Detail");

        sp = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp.getString("emp_id", "");


        allocateMemory();


        //activity = (Activity) getApplicationContext();
        utils = new Utils(this);
        SP = new SharePref(utils.getActivity());

       /* if (!utils.userPermission.checkCallPermission()) {
            utils.userPermission.requestCallPermission();
        } else if (!utils.userPermission.checkCallLogPermission()) {
            utils.userPermission.requestCallLogPermission();
        }  else {
            getLastNumber();
        }*/


        Mobilecall = getIntent().getStringExtra("Mobilecall");
        Whatapp = getIntent().getStringExtra("Whappnumbercall");

        Customordoit = getIntent().getStringExtra("Customordoit");
        FirstName = getIntent().getStringExtra("FirstName");
        LastName = getIntent().getStringExtra("LastName");

        Mobile = getIntent().getStringExtra("Mobile");
        Village = getIntent().getStringExtra("Village");
        district = getIntent().getStringExtra("district");
        Taluko = getIntent().getStringExtra("Taluko");
        cat_id = getIntent().getStringExtra("cat_id");
        eid = getIntent().getStringExtra("eid");


        Id = getIntent().getStringExtra("Id");


        tvd_First_name.setText(FirstName);
        tvd_Last_name.setText(LastName);
        tv_customor_do_it.setText(Customordoit);
        tv_village.setText(getIntent().getStringExtra("Village"));
        tvd_district.setText(getIntent().getStringExtra("district"));
        tvd_taluko.setText(getIntent().getStringExtra("Taluko"));
        tv_mobilenumber.setText(getIntent().getStringExtra("Mobile"));
        tv_Whappnumber.setText(getIntent().getStringExtra("Whappnumber"));


        lin_whapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean installed = appInstallOrNot("com.whatsapp");
                if (installed) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+91" + Whatapp));
                    startActivity(intent);



                    WebService.getClient().getMyProfile_CSW(emp,
                            Id,
                            tv_Whappnumber.getText().toString(),
                            "Whatsapp").enqueue(new Callback<MyProfileCSWModel>() {
                        @Override
                        public void onResponse(@NotNull Call<MyProfileCSWModel> call,
                                               @NotNull Response<MyProfileCSWModel> response) {
                           // Toast.makeText(FoDeliveryDetails.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(@NotNull Call<MyProfileCSWModel> call, @NotNull Throwable t) {

                        }
                    });

                } else {
                    Toast.makeText(FoDeliveryDetails.this, "Whats app not installed on your device ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        lin_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              /*  Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + Mobilecall));
                Log.d("call", "onClick: " + Mobilecall);
                startActivity(intent);*/

                SP.putSharedPref(SP.PHONE_NUMBER, "+91" + Mobilecall);
                SP.putSharedPref(SP.CALL_ID, "");
                SP.putSharedPref(SP.task_type, "");

                if (!utils.userPermission.checkCallPermission()) {
                    utils.userPermission.requestCallPermission();
                } else if (!utils.userPermission.checkCallLogPermission()) {
                    utils.userPermission.requestCallLogPermission();
                } else {
                    Mobilecall = Mobilecall;

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:+91" + Mobilecall));
                    if (ActivityCompat.checkSelfPermission(FoDeliveryDetails.this, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(intent);
                    finish();
                }

                WebService.getClient().getMyProfile_CSW(emp,
                        Id,
                        tv_mobilenumber.getText().toString(),
                        "Call").enqueue(new Callback<MyProfileCSWModel>() {
                    @Override
                    public void onResponse(@NotNull Call<MyProfileCSWModel> call,
                                           @NotNull Response<MyProfileCSWModel> response) {
                      //  Toast.makeText(FoDeliveryDetails.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(@NotNull Call<MyProfileCSWModel> call, @NotNull Throwable t) {

                    }
                });
            }
        });

        lin_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"some@email.address"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                intent.putExtra(Intent.EXTRA_TEXT, "mail body");
                intent.setPackage("com.google.android.gm");

                startActivity(Intent.createChooser(intent, ""));
            }
        });

        lin_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", Mobilecall, null));
                smsIntent.putExtra("sms_body", sms);
                startActivity(smsIntent);

                WebService.getClient().getMyProfile_CSW(emp,
                        Id,
                        tv_mobilenumber.getText().toString(),
                        "Sms").enqueue(new Callback<MyProfileCSWModel>() {
                    @Override
                    public void onResponse(@NotNull Call<MyProfileCSWModel> call,
                                           @NotNull Response<MyProfileCSWModel> response) {
                      //  Toast.makeText(FoDeliveryDetails.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(@NotNull Call<MyProfileCSWModel> call, @NotNull Throwable t) {

                    }
                });
            }
        });


        lin_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject test");
                i.putExtra(android.content.Intent.EXTRA_TEXT, "** Kumar Group ** \n" + "Customer Detail:\n" + FirstName + "\n" + LastName + "\n" + Customordoit + "\n" + Mobile + "\n" + Village + "\n" + district + "\n" + Taluko);
                startActivity(Intent.createChooser(i, "Share via"));


            }
        });



        linDataNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FoDeliveryDetails.this, GeneralVisitFormActivity.class);
                i.putExtra("CustomerName",FirstName);
                i.putExtra("CustomerName1",LastName);
                i.putExtra("Customordoit",Customordoit);
                i.putExtra("cat_id",cat_id);
                i.putExtra("Id",Id);
                i.putExtra("eid",eid);
                startActivity(i);
            }
        });
    }

    private boolean appInstallOrNot(String url) {
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try {

            packageManager.getPackageInfo(url, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void allocateMemory() {

        tvd_First_name = findViewById(R.id.tvd_First_name);
        tv_customor_do_it = findViewById(R.id.tv_customor_do_it);
        tv_mobilenumber = findViewById(R.id.tv_mobilenumber);
        tvd_district = findViewById(R.id.tvd_district);
        tv_village = findViewById(R.id.tv_village);
        tvd_taluko = findViewById(R.id.tvd_taluko);
        tvd_Last_name = findViewById(R.id.tvd_Last_name);
        tv_Whappnumber = findViewById(R.id.tv_Whappnumber);
        lin_whapp = findViewById(R.id.lin_whapp);
        lin_call = findViewById(R.id.lin_call);
        lin_email = findViewById(R.id.lin_email);
        lin_sms = findViewById(R.id.lin_sms);
        lin_share = findViewById(R.id.lin_share);


        linDataNext = findViewById(R.id.linDataNext);

    }



    /*private void getLastNumber() {
        Uri contacts = CallLog.Calls.CONTENT_URI;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            utils.userPermission.requestCallLogPermission();
        }
        Cursor managedCursor = getApplicationContext().getContentResolver().query(contacts, null, null, null, android.provider.CallLog.Calls.DATE + " DESC limit 1;");
        if (managedCursor != null && managedCursor.getCount() != 0){
            int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
            int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
            int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
            int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);

            managedCursor.moveToNext();
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            String callDayTime = new Date(Long.parseLong(callDate)).toString();
            int callDuration = managedCursor.getInt(duration);
            managedCursor.close();

            int dircode = Integer.parseInt(callType);
            utils.printLog("Details", "Phone Number:--- " + phNumber + " ,Call Date:--- " + callDayTime + " ,Call duration in sec :--- " + callDuration);

//            call_id = new String[dataTaskModel.getTask().size()];
//            for (int i = 0; i < dataTaskModel.getTask().size(); i++){
//                call_id[i] = dataTaskModel.getTask().get(i).getCall_id();
//            }
//            int pos = new ArrayList<>(Arrays.asList(call_id)).indexOf(sp.getSharedPref(sp.CALL_ID));
            if (SP.getSharedPref(SP.CALL_ID) != null && !TextUtils.isEmpty(SP.getSharedPref(SP.CALL_ID))
                    && SP.getSharedPref(SP.PHONE_NUMBER) != null &&
                    !TextUtils.isEmpty(SP.getSharedPref(SP.PHONE_NUMBER))){
                if (dircode == CallLog.Calls.OUTGOING_TYPE && callDuration > 0 && SP.getSharedPref(SP.PHONE_NUMBER).equals(phNumber)) {
//                float callDurationMinut = (float) callDuration / 60;
                    fillAttendance(SP.getSharedPref(SP.CALL_ID), phNumber,SP.getSharedPref(SP.task_type));
                }
            }

        } else {
            Toast.makeText(this, "Log is Blank !!", Toast.LENGTH_SHORT).show();
        }
    }

    private void fillAttendance(String callId, String phoneNumber,String task_type) {
        Toast.makeText(this, "Fill Attendance Called ..", Toast.LENGTH_LONG).show();

    }*/

}
