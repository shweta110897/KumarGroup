package com.example.kumarGroup;

import static com.example.kumarGroup.Utils.REQUEST_READ_PHONE_STATE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.SSP.SSPDashboardActivity;
import com.example.kumarGroup.VendorDashboard.VendorDashboardActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import eu.dkaratzas.android.inapp.update.Constants;
import eu.dkaratzas.android.inapp.update.InAppUpdateManager;
import eu.dkaratzas.android.inapp.update.InAppUpdateStatus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class  FoLogin extends AppCompatActivity  implements InAppUpdateManager.InAppUpdateHandler {

    Button btn_login;
    RelativeLayout mainlayout;
    EditText et_mobile_number, et_password;
    SharedPreferences sp;
    ProgressDialog progressDialog;
    TextView tv_forgotPass;


    Spinner spn_emp_ven;
    String token,msg,imei_no;


    String SelectType;
    String[] Select_Type = {"Select Type", "Employee", "Vendor","SSP"};

    String TAG="FologinActivituy";

    private static final int REQ_CODE_VERSION_UPDATE = 530;
    private InAppUpdateManager inAppUpdateManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fo_login);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        inAppUpdateManager = InAppUpdateManager.Builder(this, REQ_CODE_VERSION_UPDATE)
                .resumeUpdates(true) // Resume the update, if the update was stalled. Default is true
                .mode(Constants.UpdateMode.FLEXIBLE)
                // default is false. If is set to true you,
                // have to manage the user confirmation when
                // you detect the InstallStatus.DOWNLOADED status,
                .useCustomNotification(true)
                .handler(FoLogin.this);

        inAppUpdateManager.checkForAppUpdate();

        /*Intent i = new Intent(FoLogin.this,ChackIn.class);
        startActivity(i);*/

        btn_login = findViewById(R.id.btn_login);

        mainlayout = findViewById(R.id.mainlayout);
        et_mobile_number = findViewById(R.id.et_mobile_number);
        et_password = findViewById(R.id.et_password);
        spn_emp_ven = findViewById(R.id.spn_emp_ven);

        //   et_mobile_number.setText("8980737302");
        //   et_password.setText("7302");
        tv_forgotPass = findViewById(R.id.tv_forgotPass);
        tv_forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_mobile_number.getText().toString().equals("")){
                    Toast.makeText(FoLogin.this, "Plaese Enter Mobile Number to get Otp", Toast.LENGTH_SHORT).show();
                }else {

                    /*forgotPassword*/

                    WebService.getClient().forgotPassword(et_mobile_number.getText().toString()
                           ).enqueue(new Callback<Forgotpassword>() {
                        @Override
                        public void onResponse(@NotNull Call<Forgotpassword> call, @NotNull Response<Forgotpassword> response) {


                            assert response.body() != null;
                            if (response.body() == null) {
                                Utils.showErrorToast(FoLogin.this, "Please Enter valid Number");
                            } else {
                                // finish()
                                Intent intent = new Intent(FoLogin.this, OtpVerificationScreenActivity.class);
                                intent.putExtra("Number", et_mobile_number.getText().toString());
                                intent.putExtra("Email", response.body().getDetail().get(0).getEmail());
                                startActivity(intent);

                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<Forgotpassword> call, @NotNull Throwable t) {
                            Log.d("fail", "onFailure: " + t);
                            Toast.makeText(FoLogin.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
//                            progressDialog.dismiss();
                        }
                    });


                }
            }
        });

        sp = getSharedPreferences("Login", MODE_PRIVATE);

      //  startActivity(new Intent(FoLogin.this, CurrentLocationEM.class));

         ArrayAdapter selectMobSmart = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Select_Type);
        spn_emp_ven.setAdapter(selectMobSmart);

        spn_emp_ven.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SelectType = Select_Type[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                         token = task.getResult();

                        // Log and toast
                        msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);

                        int permissionCheck = ContextCompat.checkSelfPermission(FoLogin.this, android.Manifest.permission.READ_PHONE_STATE);

                        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(FoLogin.this, new String[]{android.Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
                        } else {
                            Log.d(TAG+"imei_no :", Utils.getDeviceId(FoLogin.this));
                            imei_no=Utils.getDeviceId(FoLogin.this);
                        }


                    }
                });
        // [END log_reg_token]



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mobile = et_mobile_number.getText().toString().trim();
                if (mobile.isEmpty() || mobile.length() < 10) {
                    Utils.showErrorToast(FoLogin.this, "Please Enter a proper digit mobile number");
                    et_mobile_number.requestFocus();
                    return;
                }
                if (et_password.getText().toString().equals("")) {
                    Utils.showErrorToast(FoLogin.this, "Please Enter Password");
                    return;
                }
                if (SelectType.equals("Select Type")) {
                    Utils.showErrorToast(FoLogin.this, "Please Select Type");
                    return;
                }

                progressDialog = new ProgressDialog(FoLogin.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );

                if (SelectType.equals("Employee")) {


                    if (!et_mobile_number.getText().toString().equals("") &&
                            !et_password.getText().toString().equals("")) {

                        WebService.getClient().getLogin(et_mobile_number.getText().toString(),
                                et_password.getText().toString(),
                                SelectType,imei_no,token).enqueue(new Callback<DataLoginModel>() {
                            @Override
                            public void onResponse(@NotNull Call<DataLoginModel> call, @NotNull Response<DataLoginModel> response) {


                                progressDialog.dismiss();

                                assert response.body() != null;
                                if (response.body().component2() == null) {
                                    Utils.showErrorToast(FoLogin.this, "Please Enter valid Number or Password");
                                }else if (response.body().getData().size()==0){
                                    Utils.showErrorToast(FoLogin.this, response.body().getMsg());
                                } else {


                                    //    progressDialog.show();
                                    Log.d("hhh", "onResponse: " + response.body().getData().get(0).getModule_name().get(0));

                                    Intent i = new Intent(FoLogin.this, FoDashbord.class);

                                    ArrayList<String> yourArray = (ArrayList<String>) response.body().getData().get(0).getModule_id();
                                    i.putExtra("module_id", yourArray);
                                    Log.d("kfhzjxbchvjbhj", "onResponse: " + yourArray);

                                    // i.putExtra("emp_id",response.body().getData().get(0).getEmp_id());

                                    sp.edit().putString("emp_id", response.body().getData().get(0).getEmp_id()).apply();
                                    sp.edit().putString("emp_name", response.body().getData().get(0).getEmp_name()).apply();
                                    sp.edit().putString("emp_designation", response.body().getData().get(0).getDesignation()).apply();
                                    sp.edit().putString("emp_unique_no", response.body().getData().get(0).getUnique_no()).apply();
                                    sp.edit().putString("emp_photo", response.body().getData().get(0).getPhoto()).apply();


                                    Log.d(TAG, "onResponse: "+"emp_id:--"+ response.body().getData().get(0).getEmp_id()+"\nemp_name:--"+
                                            response.body().getData().get(0).getEmp_name());

                                    i.putExtra("emp_name", response.body().getData().get(0).getEmp_name());
                                    i.putExtra("module_id", response.body().getData().get(0).getModule_id().get(0));
                                    i.putExtra("lo_flag","true");
                                    Log.d("ayashushuave", "onResponse: " + response.body().getData().get(0).getModule_id());

                                    String modulename = response.body().getData().get(0).getModule_name().toString();
                                    ArrayList<String> modulenameArray = (ArrayList<String>) response.body().getData().get(0).getModule_name();
                                    Log.d("modulenameArray", "onResponse: " + modulenameArray);

                                    sp.edit().putString("module_name", modulename).apply();
//                                    sp.edit().putString("module_name1", modulename).apply();
                                    //   i.putExtra("module_name",modulename);
                                    Log.d("lksdlsk", "onResponse: " + modulename);

                                    startActivity(i);
                                    // finish()

                                }
                            }

                            @Override
                            public void onFailure(@NotNull Call<DataLoginModel> call, @NotNull Throwable t) {
                                Log.d("fail", "onFailure: " + t);
                                Toast.makeText(FoLogin.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });

                    }
                }

                if(SelectType.equals("Vendor")){


                    if (!et_mobile_number.getText().toString().equals("") &&
                            !et_password.getText().toString().equals("")) {

                        WebService.getClient().getLoginVendor(et_mobile_number.getText().toString(),
                                et_password.getText().toString(),
                                SelectType).enqueue(new Callback<VendorLoginModel>() {
                            @Override
                            public void onResponse(@NotNull Call<VendorLoginModel> call,
                                                   @NotNull Response<VendorLoginModel> response) {

                                progressDialog.dismiss();

                                assert response.body() != null;
                                if (response.body().component2() == null) {
                                    Utils.showErrorToast(FoLogin.this, "Please Enter valid Number or Password");
                                } else {


                                    sp.edit().putString("emp_id_vendor", response.body().getData().get(0).getEmp_id()).apply();
                                    sp.edit().putString("emp_name_vendor", response.body().getData().get(0).getEmp_name()).apply();

                                    Intent i = new Intent(FoLogin.this, VendorDashboardActivity.class);


                                    i.putExtra("emp_id_vendor", response.body().getData().get(0).getEmp_name());
                                    i.putExtra("emp_name_vendor", response.body().getData().get(0).getEmp_name());
                                    startActivity(i);
                                }
                            }

                            @Override
                            public void onFailure(@NotNull Call<VendorLoginModel> call, @NotNull Throwable t) {
                                Log.d("fail", "onFailure: " + t);
                                Toast.makeText(FoLogin.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });



                    }



                }


                if(SelectType.equals("SSP")){

                    if (!et_mobile_number.getText().toString().equals("") &&
                            !et_password.getText().toString().equals("")) {


                        WebService.getClient().getSSPLogin(et_mobile_number.getText().toString(),
                                et_password.getText().toString()).enqueue(new Callback<LoginModelSSP>() {
                            @Override
                            public void onResponse(@NotNull Call<LoginModelSSP> call,
                                                   @NotNull Response<LoginModelSSP> response) {
                                progressDialog.dismiss();

                                assert response.body() != null;

                                if (response.body().component2() == null) {
                                    Utils.showErrorToast(FoLogin.this, "Please Enter valid Number or Password");
                                } else {


                                    sp.edit().putString("emp_id_SSP", response.body().getData().get(0).getEmp_id()).apply();

                                    sp.edit().putString("emp_name_SSP", response.body().getData().get(0).getEmp_name()).apply();

                                    Intent i = new Intent(FoLogin.this, SSPDashboardActivity.class);

                                    i.putExtra("emp_id_SSP", response.body().getData().get(0).getEmp_name());
                                    i.putExtra("emp_name_SSP", response.body().getData().get(0).getEmp_name());

                                    startActivity(i);
                                }

                            }

                            @Override
                            public void onFailure(@NotNull Call<LoginModelSSP> call, @NotNull Throwable t) {
                                Log.d("fail", "onFailure: " + t);
                                Toast.makeText(FoLogin.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });

                    }

                }
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Log.d(TAG+"imei_no :", Utils.getDeviceId(FoLogin.this));
                    imei_no=Utils.getDeviceId(FoLogin.this);
                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQ_CODE_VERSION_UPDATE) {
            if (resultCode == Activity.RESULT_CANCELED) {
                // If the update is cancelled by the user,
                // you can request to start the update again.
                inAppUpdateManager.checkForAppUpdate();

                Log.d(TAG, "Update flow failed! Result code: " + resultCode);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onInAppUpdateError(int code, Throwable error) {


        Log.d(TAG, "onInAppUpdateError: "+error);

    }

    @Override
    public void onInAppUpdateStatus(InAppUpdateStatus status) {

        /*
         * If the update downloaded, ask user confirmation and complete the update
         */

        if (status.isDownloaded()) {

            View rootView = getWindow().getDecorView().findViewById(android.R.id.content);

            Snackbar snackbar = Snackbar.make(rootView,
                    "An update has just been downloaded.",
                    Snackbar.LENGTH_INDEFINITE);

            snackbar.setAction("RESTART", view -> {

                // Triggers the completion of the update of the app for the flexible flow.
                inAppUpdateManager.completeUpdate();

            });

            snackbar.show();

        }

    }
}

