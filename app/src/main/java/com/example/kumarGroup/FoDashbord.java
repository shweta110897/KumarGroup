package com.example.kumarGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kumarGroup.Alarm.CheckInAlarmReceiver;
import com.example.kumarGroup.Alarm.CheckOutAlarmReceiver;
import com.example.kumarGroup.Alarm.ak.MornigFirstAlarm;
import com.example.kumarGroup.Alarm.ak.MornigFiveAlarm;
import com.example.kumarGroup.Alarm.ak.MornigFourAlarm;
import com.example.kumarGroup.Alarm.ak.MornigThreeAlarm;
import com.example.kumarGroup.Alarm.ak.MornigTwoAlarm;
import com.example.kumarGroup.CustomerProfileData.AddCutomerActivity;
import com.example.kumarGroup.CustomerProfileData.ComplainBoxSecondActivity;
import com.example.kumarGroup.CustomerProfileData.DeleteProfileFormActivity;
import com.example.kumarGroup.CustomerProfileData.DocumentBoxSecondActivity;
import com.example.kumarGroup.CustomerProfileData.EditProfileFormActivity;
import com.example.kumarGroup.CustomerProfileData.NewVisitActivity;
import com.example.kumarGroup.CustomerProfileData.ViewCustomerDatadisplayMainActivity;
import com.example.kumarGroup.CustomerProfileData.VisitSearchActivity;
import com.example.kumarGroup.CustomerProfileData.WalkingEntryActivity;
import com.example.kumarGroup.Dashboard.DashBoardAdapter;
import com.example.kumarGroup.Dashboard.HomeItemss;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;


import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import eu.dkaratzas.android.inapp.update.Constants;
import eu.dkaratzas.android.inapp.update.InAppUpdateManager;
import eu.dkaratzas.android.inapp.update.InAppUpdateStatus;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.util.Log.d;


public class FoDashbord extends AppCompatActivity implements InAppUpdateManager.InAppUpdateHandler {

    String emp_id,emp_photo,emp_designation,emp_name,emp_unique_no;
    String module_id,lo_flage;
    String module_name;
    SharedPreferences sp;

    GridView mainGrid;
    DashBoardAdapter dashBoardAdapter;
    ArrayList<HomeItemss> homeItemLists;
    ArrayList<HomeItemss> homeItemLists1=new ArrayList<>();


    int hour, min;
    String AM_PM;

    private static final int REQ_CODE_VERSION_UPDATE = 530;
    private static final String TAG = "Sample";
    private InAppUpdateManager inAppUpdateManager;

    public static boolean Common_Search_Check = false;
    public static boolean Village_List_Show_Check = false;
    public static boolean Score_Board_Check = false;

    private FusedLocationProviderClient fusedLocationClient;
    ProgressDialog pro;


    EditText et_mobile_number;
    ImageView iv_Search,iv_profileshare;

    CircleImageView profile_image;

    TextView emp_level,emp_name1,emp_designation1,emp_uniq_id;
    RatingBar emp_rate;

    Uri uriclient;
    String  waypath;
    LinearLayout ll_main;
    boolean location_flag = false;
    Integer  SELECT_FILE = 0;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_fo_dashbord);

        inAppUpdateManager = InAppUpdateManager.Builder(this, REQ_CODE_VERSION_UPDATE)
                .resumeUpdates(true) // Resume the update, if the update was stalled. Default is true
                .mode(Constants.UpdateMode.FLEXIBLE)
                // default is false. If is set to true you,
                // have to manage the user confirmation when
                // you detect the InstallStatus.DOWNLOADED status,
                .useCustomNotification(true)
                .handler(this);

        inAppUpdateManager.checkForAppUpdate();

        module_id = getIntent().getStringExtra("module_id");
        lo_flage = getIntent().getStringExtra("lo_flag");

        sp = getSharedPreferences("Login", MODE_PRIVATE);

        emp_id = sp.getString("emp_id", "");
        module_name = sp.getString("module_name", "");
        emp_designation = sp.getString("emp_designation", "");
        emp_name = sp.getString("emp_name", "");
        emp_unique_no = sp.getString("emp_unique_no", "");
        emp_photo = sp.getString("emp_photo", "");

        requestPermission();

        Log.d("ewdfsacf", "onCreate: " + module_name);
        Log.d("jdfjfhhf", "onCreate: " + module_id);

        /***********Profile Share********/

        iv_profileshare = findViewById(R.id.iv_profileshare);
        ll_main = findViewById(R.id.ll_main);
        profile_image = findViewById(R.id.profile_image);
        emp_level = findViewById(R.id.emp_level);
        emp_name1 = findViewById(R.id.emp_name);
        emp_designation1 = findViewById(R.id.emp_designation);
        emp_uniq_id = findViewById(R.id.emp_uniq_id);
        emp_rate = findViewById(R.id.emp_rate);


        emp_name1.setText(emp_name);
        emp_designation1.setText(emp_designation);
        emp_name1.setText(emp_name);
        emp_uniq_id.setText(emp_unique_no);

        if (emp_photo!=null || !emp_photo.equals("No Image")) {
            Glide.with(FoDashbord.this)
                    .load(emp_photo)
                    .into(profile_image);

//            profile_image.setImageURI(Uri.parse(emp_photo));
        }


        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

        iv_profileshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    // Code for API level 29 and higher
                    Log.d("device==>>q",">q");
                    Toast.makeText(FoDashbord.this, "iffff", Toast.LENGTH_SHORT).show();

                    // Convert LinearLayout to Bitmap
                    Bitmap bitmap = Bitmap.createBitmap(ll_main.getWidth(), ll_main.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    ll_main.draw(canvas);

                    // Save Bitmap to a file
                    File cachePath = new File(getExternalCacheDir(), "Profile_asword.jpg");
                    try (  FileOutputStream fos = new FileOutputStream(cachePath)) {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Share Bitmap via WhatsApp intent
                    Uri imageUri = FileProvider.getUriForFile(FoDashbord.this, getPackageName() + ".provider", cachePath);
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("image/png");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    /*  shareIntent.setPackage("com.whatsapp");*/
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(Intent.createChooser(shareIntent, "Share Profile Image"));

                } else {
                    Log.d("device==>>q","<q");
                    Toast.makeText(FoDashbord.this, "elseee", Toast.LENGTH_SHORT).show();
                    // Code for API level 28 and lower
                    ll_main.setDrawingCacheEnabled(true);
                    Bitmap bitmap = Bitmap.createBitmap(ll_main.getDrawingCache());
                    ll_main.setDrawingCacheEnabled(false);

                    try {
                        File file = new File(getExternalCacheDir(), "Profile_asword.jpg");
                        FileOutputStream fOut = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fOut);
                        fOut.flush();
                        fOut.close();
                        file.setReadable(true, false);

                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                        intent.setType("image/jpeg");
                        startActivity(Intent.createChooser(intent, "Share Profile image"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


//            }
            }
        });

        homeItemLists = new ArrayList<>();
        homeItemLists.add(new HomeItemss("Data Bank","Data Bank", R.drawable.general_visit,1,0));
        homeItemLists.add(new HomeItemss("Task","Activity", R.drawable.ic_activity_dp_24dp,2,0));
        homeItemLists.add(new HomeItemss("Attendance","Attendance", R.drawable.ic_attendance_dp,3,0));
        homeItemLists.add(new HomeItemss("Travelling","Travaling", R.drawable.ic_check_box_black_24dp,4,0));
        homeItemLists.add(new HomeItemss("Over Time", "Overtime",R.drawable.ic_over_time,5,0));
        homeItemLists.add(new HomeItemss("Wallet","Wallet", R.drawable.ic_account_balance_wallet_black_24dp,6,0));
        homeItemLists.add(new HomeItemss("Payment Collection","payment collection", R.drawable.ic_baseline_collections_bookmark_24,7,0));
        homeItemLists.add(new HomeItemss("Booking/Delivery Upload", "Booking_delivery",R.drawable.ic_baseline_book_online_24,8,0));
        homeItemLists.add(new HomeItemss("Maintenance", "Maintenance",R.drawable.ic_baseline_account_box_24,9,0));
        homeItemLists.add(new HomeItemss("Workshop", "Workshop",R.drawable.ic_baseline_group_work_24,10,0));
        homeItemLists.add(new HomeItemss("My Inquiry", "inquirymod",R.drawable.ic_golf_course_black_25dp,11,0));
        homeItemLists.add(new HomeItemss("View Inquiry", "viewinquiry",R.drawable.ic_inquiry_dp_24,12,0));
        homeItemLists.add(new HomeItemss("My Profile", "Myprofile",R.drawable.ic_account_dp_24,13,0));
        homeItemLists.add(new HomeItemss("View Profile", "viewProfile",R.drawable.ic_baseline_person_24,14,0));
        homeItemLists.add(new HomeItemss("Feedback/Review","True Value",R.drawable.ic_baseline_check_trueval,15,0));
        homeItemLists.add(new HomeItemss("My Score Board","myscoreboard", R.drawable.ic_baseline_bar_chart_24,16,0));
        homeItemLists.add(new HomeItemss("Score Board","scoreboard_main", R.drawable.ic_baseline_bar_chart_24,17,0));
        homeItemLists.add(new HomeItemss("Delivery Report", "delivery_report",R.drawable.ic_delivery_dp_24dp,18,0));
        homeItemLists.add(new HomeItemss("Employee Tracker", "emp_tracking",R.drawable.ic_baseline_location_on_24,19,0));
        homeItemLists.add(new HomeItemss("Meeting Room","meeting_room", R.drawable.ic_baseline_meeting_room_24,20,0));
        homeItemLists.add(new HomeItemss("CashBook","cash_book", R.drawable.ic_baseline_menu_book_24,21,0));


//        homeItemLists= HomeItemList.getHomeItemsList();
        Log.d("homeItemLists",String.valueOf(homeItemLists));
        for (HomeItemss homeItem : homeItemLists) {
            String moduleName = homeItem.getItem_name2();
            Log.d("moduleName",moduleName);
            Log.d("module_name",module_name);

            // Check if the module name exists in the module_name array
            if (module_name.contains(moduleName)) {
                // Set the flag to 1 indicating a match
                Log.d("moduleName==>>iff",moduleName);
                homeItem.setFlag(1);
                homeItemLists1.add(new HomeItemss(
                        homeItem.getItem_name(),
                        homeItem.getItem_name2(),
                        homeItem.getItem_img()
                        ,homeItem.getId(),
                        1));
            }else{
                Log.d("moduleName==>>elseee",moduleName);
            }
        }

        Log.d("homeItemLists123", String.valueOf(homeItemLists));
        Log.d("homeItemLists1==>> ", String.valueOf(homeItemLists1));


        mainGrid=findViewById(R.id.mainGrid);
        dashBoardAdapter  = new DashBoardAdapter( this, homeItemLists1);

        mainGrid.setAdapter(dashBoardAdapter);




        List<PackageInfo> list = getPackageManager().getInstalledPackages(0);

        Log.d("list", String.valueOf(list));

        et_mobile_number = findViewById(R.id.et_mobile_number);
        iv_Search = findViewById(R.id.iv_Search);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(FoDashbord.this);
        pro = new ProgressDialog(FoDashbord.this);

        if ("true".equals(lo_flage)){
            lo_flage = "false";

            Log.d(TAG, "onCreate:---empid----- "+emp_id);

            WebService.getClient().chcek_or_not(emp_id).enqueue(new Callback<checkornot_model_ak>() {
                @Override
                public void onResponse(Call<checkornot_model_ak> call, Response<checkornot_model_ak> response) {

                    Toast.makeText(FoDashbord.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: ---"+response.body().getMsg());
                    if ("CHEKIN".equals(response.body().getMsg()) || "OK".equals(response.body().getMsg())){
                        getlocation();
                    }else   if ("CHEKIN NOT".equals(response.body().getMsg())) {
                        Log.d(TAG, "onResponse: CHEKIN NOT");
//                        setAlarm();
                    }

                }

                @Override
                public void onFailure(Call<checkornot_model_ak> call, Throwable throwable) {
                    Toast.makeText(FoDashbord.this, call.toString(), Toast.LENGTH_SHORT).show();

                }
            });


            WebService.getClient().check_out_check_or_not(emp_id).enqueue(new Callback<checkornot_model_ak>() {
                @Override
                public void onResponse(Call<checkornot_model_ak> call, Response<checkornot_model_ak> response) {

                    Toast.makeText(FoDashbord.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: ---"+response.body().getMsg());
                    if ("CHEKOUT".equals(response.body().getMsg()) || "OK".equals(response.body().getMsg())){
                        getlocation();
                    }else   if ("CHEKOUT NOT".equals(response.body().getMsg())) {
                        Log.d(TAG, "onResponse: CHEKOUT NOT");
                        setAlarmEvening();
//                        setAlarm();
                    }


                    /*if ("CHEKOUT".equals(response.body().getMsg()) || "OK".equals(response.body().getMsg())){
                        getlocation();
                    }else{
                        setAlarmEvening();
                        Log.d(TAG, "onResponse: not working gps");
                    }*/
                }

                @Override
                public void onFailure(Call<checkornot_model_ak> call, Throwable throwable) {
                    Toast.makeText(FoDashbord.this, call.toString(), Toast.LENGTH_SHORT).show();

                }
            });
        }


//        setAlarm11();


//        setAlarm();

//        setAlarmEvening();

        MornigFirstAl();

        MornigTwoAl();

        MornigThreeAl();

        MornigFourAl();

        MornigFiveAl();

        WebService.getClient().getSupBorrow().enqueue(new Callback<SupBorrowModel>() {
            @Override
            public void onResponse(@NotNull Call<SupBorrowModel> call, @NotNull Response<SupBorrowModel> response) {
                assert response.body() != null;
                //Toast.makeText(FoDashbord.this, "Response success"+response.body().getMsg(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NotNull Call<SupBorrowModel> call, @NotNull Throwable t) {
                //Toast.makeText(FoDashbord.this, "Response success"+t.getCause(), Toast.LENGTH_SHORT).show();

            }
        });

        WebService.getClient().getEmpBorrow(emp_id).enqueue(new Callback<EmpBorrowModel>() {
            @Override
            public void onResponse(@NotNull Call<EmpBorrowModel> call, @NotNull Response<EmpBorrowModel> response) {
                // Toast.makeText(FoDashbord.this, "Response success"+response.body().getMsg(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NotNull Call<EmpBorrowModel> call, @NotNull Throwable t) {
               // Toast.makeText(FoDashbord.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();

            }
        });

        WebService.getClient().getEndTask(emp_id).enqueue(new Callback<EndTaskModel>() {
            @Override
            public void onResponse(@NotNull Call<EndTaskModel> call, @NotNull Response<EndTaskModel> response) {
               // Toast.makeText(FoDashbord.this, "Response success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NotNull Call<EndTaskModel> call, @NotNull Throwable t) {
               // Toast.makeText(FoDashbord.this, "Response Onfailure", Toast.LENGTH_SHORT).show();
            }
        });

        WebService.getClient().getLeftSms().enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(@NotNull Call<RequestBody> call, @NotNull Response<RequestBody> response) {
              //  Toast.makeText(FoDashbord.this, "response", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NotNull Call<RequestBody> call, @NotNull Throwable t) {
             //   Toast.makeText(FoDashbord.this, "response--", Toast.LENGTH_SHORT).show();

            }
        });

        Calendar cs = Calendar.getInstance();
        int day = cs.get(Calendar.DAY_OF_MONTH);
        int month = cs.get(Calendar.MONTH);
        int year = cs.get(Calendar.YEAR);
        //  String date = day + "/" + (month + 1) + "/" + year;
        String date = year + "-" + (month + 1) + "-" + day;
        Log.d("NewDate", "onCreate: " + date);

        // Toast.makeText(this, "qq"+day+" "+month+" "+year, Toast.LENGTH_SHORT).show();

        final String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE);
        int ds = c.get(Calendar.AM_PM);
        if (ds == 0)
            AM_PM = "am";
        else
            AM_PM = "pm";

        if ((hour == 9 && min == 30 && AM_PM.matches("am"))) {
            // Toast.makeText(FoDashbord.this, "Time is between the range", Toast.LENGTH_SHORT).show();
            Log.d("Time111", "onCreate: Time is between the range");
            WebService.getClient().getCurrentDate(date).enqueue(new Callback<CurrentDateModel>() {
                @Override
                public void onResponse(@NotNull Call<CurrentDateModel> call, @NotNull Response<CurrentDateModel> response) {
                    //   Log.d("SMSDate", "onResponse: "+response.body().getMsg());
                   // Toast.makeText(FoDashbord.this, "API Call", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(@NotNull Call<CurrentDateModel> call, @NotNull Throwable t) {
                    //  Log.d("Date", "onResponse: "+t.getMessage());
                   // Toast.makeText(FoDashbord.this, "API not Call", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Log.d("Time222", "onCreate: Time is between the range");
        }

        iv_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_mobile_number.getText().toString().equals("")){
                    et_mobile_number.setError("Please Enter Number");
                }else  if(et_mobile_number.getText().toString().length()<10){
                    et_mobile_number.setError("Please Enter Valid Number");
                }else{

                    InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);

                    PopupMenu popupMenu = new PopupMenu(FoDashbord.this, et_mobile_number);

                    // Inflating popup menu from popup_menu.xml file
                    popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            // Toast message on menu item clicked
                       /* Toast.makeText(FoDashbord.this, "You Clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;*/

                            int menuItem = item.getItemId();
                            switch (menuItem) {
                                case R.id.addProfile:
//                                    Toast.makeText(FoDashbord.this, "You Clicked add" , Toast.LENGTH_SHORT).show();
                                    Intent addintent = new Intent(FoDashbord.this, AddCutomerActivity.class);
                                    addintent.putExtra("MobileNo",et_mobile_number.getText().toString());
                                    startActivity(addintent);
                                    return true;

                                case R.id.viewProfile:
//                                    Toast.makeText(FoDashbord.this, "You Clicked view" , Toast.LENGTH_SHORT).show();
                                    Intent viewintent1 = new Intent(FoDashbord.this, ViewCustomerDatadisplayMainActivity.class);
                                    viewintent1.putExtra("MobileNo",et_mobile_number.getText().toString());
                                    startActivity(viewintent1);
                                    return true;

                                case R.id.editProfile:
//                                    Toast.makeText(FoDashbord.this, "You Clicked edit", Toast.LENGTH_SHORT).show();
                                    Intent editintent = new Intent(FoDashbord.this, EditProfileFormActivity.class);
                                    editintent.putExtra("MobileNo",et_mobile_number.getText().toString());
                                    startActivity(editintent);
                                    return true;

                                case R.id.deleteProfile:
//                                    Toast.makeText(FoDashbord.this, "You Clicked edit", Toast.LENGTH_SHORT).show();
                                    Intent deleteintent = new Intent(FoDashbord.this, DeleteProfileFormActivity.class);
                                    deleteintent.putExtra("MobileNo",et_mobile_number.getText().toString());
                                    startActivity(deleteintent);
                                    return true;

                                case R.id.tv_DocumentBox:
                                    Intent docIntent = new Intent(FoDashbord.this, DocumentBoxSecondActivity.class);
                                    docIntent.putExtra("MobileNo",et_mobile_number.getText().toString());
                                    startActivity(docIntent);
                                    return true;

                                case R.id.complainBox:
                                    Intent complainintent = new Intent(FoDashbord.this, ComplainBoxSecondActivity.class);
                                    complainintent.putExtra("MobileNo",et_mobile_number.getText().toString());
                                    startActivity(complainintent);
                                    return true;


                                case R.id.walikgEntry:
                                    Intent walkintent = new Intent(FoDashbord.this, WalkingEntryActivity.class);
                                    walkintent.putExtra("MobileNo",et_mobile_number.getText().toString());
                                    startActivity(walkintent);
                                    return true;

                                case R.id.activity:
                                    Intent actintent = new Intent(FoDashbord.this, VisitSearchActivity.class);
                                    actintent.putExtra("MobileNo",et_mobile_number.getText().toString());
                                    startActivity(actintent);
                                    return true;


                                case R.id.new_visit:
                                    Intent visitintent = new Intent(FoDashbord.this, NewVisitActivity.class);
                                    visitintent.putExtra("MobileNo",et_mobile_number.getText().toString());
                                    startActivity(visitintent);
                                    return true;

                                case R.id.reminder:
                                    Toast.makeText(FoDashbord.this, "You Clicked add" , Toast.LENGTH_SHORT).show();
                                    return true;

                            }
                            return false;
                        }
                    });
                    // Showing the popup menu
                    popupMenu.show();
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (location_flag){
            //Toast.makeText(FoDashbord.this, "onstart run", Toast.LENGTH_SHORT).show();
            location_flag = false;
            LocationManager locationManager = (LocationManager) FoDashbord.this.getSystemService(FoDashbord.this.LOCATION_SERVICE);

            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                    locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

                getlocation();

            }
            else {
                pro.dismiss();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(FoDashbord.this);


                alertDialog.setTitle("GPS is not Enabled!");

                alertDialog.setMessage("Do you want to turn on GPS?");
                alertDialog.setCancelable(false);

                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        location_flag = true;
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                    }
                });

                alertDialog.show();
            }

        }
    }
    private void getlocation()  {
        pro.show();
        pro.setMessage("Please wait get location...");
        LocationManager locationManager = (LocationManager) FoDashbord.this.getSystemService(FoDashbord.this.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(FoDashbord.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(FoDashbord.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        try {
                            Geocoder geocoder = new Geocoder(FoDashbord.this, Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

//                            binding.location.setText(addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName());
                          //  Toast.makeText(FoDashbord.this, addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();

                            WebService.getClient().send_location_web_ak(emp_id,
                                     addresses.get(0).getAddressLine(0) +","+
                                            addresses.get(0).getSubLocality() +","+
                                            addresses.get(0).getLocality() + "," +
                                            addresses.get(0).getAdminArea() + "," +
                                            addresses.get(0).getCountryName()
                                           ,String.valueOf(addresses.get(0).getLatitude())
                                           ,String.valueOf(addresses.get(0).getLongitude()),
                                            "1"
                            ).enqueue(new Callback<send_location_model_ak>() {
                                @Override
                                public void onResponse(Call<send_location_model_ak> call, Response<send_location_model_ak> response) {
                                    Toast.makeText(FoDashbord.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                    pro.dismiss();
                                }

                                @Override
                                public void onFailure(Call<send_location_model_ak> call, Throwable throwable) {
                                    Toast.makeText(getApplicationContext(), " error 1 "+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    pro.dismiss();
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                            getlocation();
                           // Toast.makeText(getApplicationContext(), "cathc id "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            pro.dismiss();
                        }
                    }
                    else {
                        LocationRequest locationRequest = new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(10000)
                                .setNumUpdates(1);
                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(@NonNull LocationResult locationResult) {
                                //   super.onLocationResult(locationResult);
                                try {

                                    Location location1 = locationResult.getLastLocation();
                                    Geocoder geocoder = new Geocoder(FoDashbord.this, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(location1.getLatitude(), location1.getLongitude(), 0);

                                    WebService.getClient().send_location_web_ak(emp_id,
                                            addresses.get(0).getAddressLine(0) +","+
                                                    addresses.get(0).getSubLocality() +","+
                                                    addresses.get(0).getLocality() + "," +
                                                    addresses.get(0).getAdminArea() + "," +
                                                    addresses.get(0).getCountryName()
                                            ,String.valueOf(addresses.get(0).getLatitude())
                                            ,String.valueOf(addresses.get(0).getLongitude())
                                            ,"1"
                                    ).enqueue(new Callback<send_location_model_ak>() {
                                        @Override
                                        public void onResponse(Call<send_location_model_ak> call, Response<send_location_model_ak> response) {
                                            Toast.makeText(FoDashbord.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                            pro.dismiss();
                                        }

                                        @Override
                                        public void onFailure(Call<send_location_model_ak> call, Throwable throwable) {
                                            Toast.makeText(getApplicationContext(), " error 2 "+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                            pro.dismiss();
                                        }
                                    });

                                } catch (Exception e) {
                                    pro.dismiss();
                                    getlocation();
                                    //Toast.makeText(getApplicationContext(), " catch id 2"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        };
                        if (ActivityCompat.checkSelfPermission(FoDashbord.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(FoDashbord.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            return;
                        }
                        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                    }
                }
            });
        }
        else {
            pro.dismiss();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(FoDashbord.this);


            alertDialog.setTitle("GPS is not Enabled!");

            alertDialog.setMessage("Do you want to turn on GPS?");
            alertDialog.setCancelable(false);

            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    location_flag = true;
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                }
            });

            alertDialog.show();

        }
    }



    private void setAlarm() {


        /********************8am*************************/
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);/*1;00*/
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 1);
        if (calendar.getTime().compareTo(new Date()) < 0) calendar.add(Calendar.HOUR_OF_DAY, 0);
        Intent intent = new Intent(getApplicationContext(), CheckInAlarmReceiver.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        or
// add Intent.FLAG_ACTIVITY_MULTIPLE_TASK which start one more task your applications
//   where activity will not be cleared;
        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        PendingIntent pendingIntent = null;
        int pendingFlags;
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            pendingFlags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
        } else {
            pendingFlags = PendingIntent.FLAG_UPDATE_CURRENT;
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast
                    (this, 0, intent, pendingFlags);
        }
        else
        {
            pendingIntent = PendingIntent.getBroadcast
                    (this, 0, intent,pendingFlags);
        }
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
          /*  alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() +
                            60 * 1000, pendingIntent);*/

        }



        /********************9am*************************/
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 9);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 1);
       /* calendar1.set(Calendar.HOUR_OF_DAY, 9);
        calendar1.set(Calendar.MINUTE, 00);
        calendar1.set(Calendar.SECOND, 01);*/
        if (calendar1.getTime().compareTo(new Date()) < 0) calendar1.add(Calendar.HOUR_OF_DAY, 0);
        Intent intent1 = new Intent(getApplicationContext(), CheckInAlarmReceiver.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        or
// add Intent.FLAG_ACTIVITY_MULTIPLE_TASK which start one more task your applications
//   where activity will not be cleared;
        intent1.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        PendingIntent pendingIntent1 = null;
        int pendingFlags1;
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            pendingFlags1 = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
        } else {
            pendingFlags1 = PendingIntent.FLAG_UPDATE_CURRENT;
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent1 = PendingIntent.getBroadcast
                    (this, 0, intent1, pendingFlags1);
        }
        else
        {
            pendingIntent1 = PendingIntent.getBroadcast
                    (this, 0, intent1,pendingFlags1);
        }
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager1 != null) {
            alarmManager1.setRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent1);
        }
//
//
//        /********************10am*************************/
//
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, 10);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 1);
        if (calendar2.getTime().compareTo(new Date()) < 0) calendar2.add(Calendar.HOUR_OF_DAY, 0);
        Intent intent2 = new Intent(getApplicationContext(), CheckInAlarmReceiver.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        or
// add Intent.FLAG_ACTIVITY_MULTIPLE_TASK which start one more task your applications
//   where activity will not be cleared;
        intent2.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        PendingIntent pendingIntent2 = null;
        int pendingFlags2;
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            pendingFlags2 = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
        } else {
            pendingFlags2 = PendingIntent.FLAG_UPDATE_CURRENT;
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent2 = PendingIntent.getBroadcast
                    (this, 0, intent2, pendingFlags2);
        }
        else
        {
            pendingIntent2 = PendingIntent.getBroadcast
                    (this, 0, intent2,pendingFlags2);
        }
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager2 != null) {
            alarmManager2.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent2);
        }
//
//        /********************11am*************************/
        Calendar calendar11 = Calendar.getInstance();
        calendar11.set(Calendar.HOUR_OF_DAY, 11);
        calendar11.set(Calendar.MINUTE, 0);
        calendar11.set(Calendar.SECOND, 1);
        if (calendar11.getTime().compareTo(new Date()) < 0) calendar11.add(Calendar.HOUR_OF_DAY, 0);
        Intent intent11 = new Intent(getApplicationContext(), CheckInAlarmReceiver.class);
        intent11.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        or
// add Intent.FLAG_ACTIVITY_MULTIPLE_TASK which start one more task your applications
//   where activity will not be cleared;
        intent11.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        PendingIntent pendingIntent11 = null;
        int pendingFlags11;
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            pendingFlags11 = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
        } else {
            pendingFlags11 = PendingIntent.FLAG_UPDATE_CURRENT;
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent11 = PendingIntent.getBroadcast
                    (this, 0, intent11, pendingFlags11);
        }
        else
        {
            pendingIntent11 = PendingIntent.getBroadcast
                    (this, 0, intent11,pendingFlags11);
        }
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager11 = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager11 != null) {
            alarmManager11.setRepeating(AlarmManager.RTC_WAKEUP, calendar11.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent11);
        }
//
//
//        /********************12am*************************/
        Calendar calendar12 = Calendar.getInstance();
        calendar12.set(Calendar.HOUR_OF_DAY, 12);
        calendar12.set(Calendar.MINUTE, 0);
        calendar12.set(Calendar.SECOND, 1);
        if (calendar12.getTime().compareTo(new Date()) < 0) calendar12.add(Calendar.HOUR_OF_DAY, 0);
        Intent intent12 = new Intent(getApplicationContext(), CheckInAlarmReceiver.class);
        intent12.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        or
// add Intent.FLAG_ACTIVITY_MULTIPLE_TASK which start one more task your applications
//   where activity will not be cleared;
        intent12.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        PendingIntent pendingIntent12 = null;
        int pendingFlags12;
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            pendingFlags12= PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
        } else {
            pendingFlags12 = PendingIntent.FLAG_UPDATE_CURRENT;
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent12 = PendingIntent.getBroadcast
                    (this, 0, intent12, pendingFlags12);
        }
        else
        {
            pendingIntent12 = PendingIntent.getBroadcast
                    (this, 0, intent12,pendingFlags12);
        }
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager12 = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager12 != null) {
            alarmManager12.setRepeating(AlarmManager.RTC_WAKEUP, calendar12.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent12);
        }


//        Toast.makeText(this, "SetAlarm Morning Calling", Toast.LENGTH_SHORT).show();

        // Quote in Morning at 09:30:00 AM
       /* Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 50);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Log.d("time", "setAlarm: " + Calendar.HOUR_OF_DAY + " " + Calendar.MINUTE);

       // Toast.makeText(this, "time"+Calendar.HOUR_OF_DAY + " " + Calendar.MINUTE, Toast.LENGTH_SHORT).show();

        Calendar cur = Calendar.getInstance();

        if (cur.after(calendar)) {
            calendar.add(Calendar.DATE, 1);
        }

        Intent myIntent = new Intent(FoDashbord.this, DailyReceiver.class);
        int ALARM1_ID = 10000;

        PendingIntent pendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast
                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_MUTABLE);
        }
        else
        {
            pendingIntent = PendingIntent.getBroadcast
                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
       *//* PendingIntent pendingIntent = PendingIntent.getBroadcast(
                FoDashbord.this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);*//*
        //  AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(MainActivity.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
*/
    }

    private void setAlarmEvening() {
        // Quote in Morning at 08:32:00 AM
        // Toast.makeText(this, "SetAlarm Evening Calling", Toast.LENGT
        //
        // H_SHORT).show();

     /*   Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 15);
        calendar1.set(Calendar.MINUTE, 47);
        calendar1.set(Calendar.SECOND, 0);
        Intent intent1 = new Intent(FoDashbord.this, AlarmReceiver.class);
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(FoDashbord.this, 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) FoDashbord.this.getSystemService(FoDashbord.this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent1);
*/

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);/*1;00*/
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 1);
        if (calendar.getTime().compareTo(new Date()) < 0) calendar.add(Calendar.HOUR_OF_DAY, 0);
        Intent intent = new Intent(getApplicationContext(), CheckOutAlarmReceiver.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        or
// add Intent.FLAG_ACTIVITY_MULTIPLE_TASK which start one more task your applications
//   where activity will not be cleared;
        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        PendingIntent pendingIntent = null;
        int pendingFlags;
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            pendingFlags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
        } else {
            pendingFlags = PendingIntent.FLAG_UPDATE_CURRENT;
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast
                    (this, 1, intent, pendingFlags);
        }
        else
        {
            pendingIntent = PendingIntent.getBroadcast
                    (this, 1, intent,pendingFlags);
        }
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
          /*  alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() +
                            60 * 1000, pendingIntent);*/

        }
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 12);
//        calendar.set(Calendar.MINUTE, 54);
//        calendar.set(Calendar.SECOND, 01);
//        if (calendar.getTime().compareTo(new Date()) < 0) calendar.add(Calendar.HOUR_OF_DAY, 0);
//        Intent intent = new Intent(getApplicationContext(), CheckOutAlarmReceiver.class);
////        PendingIntent pendingIntent = null;
//
//
//      /*  int pendingFlags;
//        if (android.os.Build.VERSION.SDK_INT >= 23) {
//            pendingFlags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
//        } else {
//            pendingFlags = PendingIntent.FLAG_UPDATE_CURRENT;
//        }
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
//            pendingIntent = PendingIntent.getBroadcast
//                    (this, 0, intent, pendingFlags);
//        }
//        else
//        {
//            pendingIntent = PendingIntent.getBroadcast
//                    (this, 0, intent,pendingFlags);
//        }*/
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        if (alarmManager != null) {
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
//        }


//        /******************************************/
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.set(Calendar.HOUR_OF_DAY, 21);
//        calendar1.set(Calendar.MINUTE, 0);
//        calendar1.set(Calendar.SECOND, 01);
//        if (calendar1.getTime().compareTo(new Date()) < 0) calendar1.add(Calendar.HOUR_OF_DAY, 0);
//        Intent intent1 = new Intent(getApplicationContext(), CheckOutAlarmReceiver.class);
//        PendingIntent pendingIntent1 = null;
//        int pendingFlags1;
//        if (android.os.Build.VERSION.SDK_INT >= 23) {
//            pendingFlags1 = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
//        } else {
//            pendingFlags1 = PendingIntent.FLAG_UPDATE_CURRENT;
//        }
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
//            pendingIntent1 = PendingIntent.getBroadcast
//                    (this, 0, intent1, pendingFlags1);
//        }
//        else
//        {
//            pendingIntent1 = PendingIntent.getBroadcast
//                    (this, 0, intent1,pendingFlags1);
//        }
////        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
//        if (alarmManager1 != null) {
//            alarmManager1.setRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent1);
//        }
//
//
//        /******************************************/
//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.set(Calendar.HOUR_OF_DAY, 22);
//        calendar2.set(Calendar.MINUTE, 0);
//        calendar2.set(Calendar.SECOND, 01);
//        if (calendar2.getTime().compareTo(new Date()) < 0) calendar2.add(Calendar.HOUR_OF_DAY, 0);
//        Intent intent2 = new Intent(getApplicationContext(), CheckOutAlarmReceiver.class);
//        PendingIntent pendingIntent2 = null;
//        int pendingFlags2;
//        if (android.os.Build.VERSION.SDK_INT >= 23) {
//            pendingFlags2 = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
//        } else {
//            pendingFlags2 = PendingIntent.FLAG_UPDATE_CURRENT;
//        }
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
//            pendingIntent2 = PendingIntent.getBroadcast
//                    (this, 0, intent2, pendingFlags2);
//        }
//        else
//        {
//            pendingIntent2 = PendingIntent.getBroadcast
//                    (this, 0, intent2,pendingFlags2);
//        }
////        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);
//        if (alarmManager2 != null) {
//            alarmManager2.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent2);
//        }
//
//
//
//        /******************************************/
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.HOUR_OF_DAY, 23);
//        calendar3.set(Calendar.MINUTE, 0);
//        calendar3.set(Calendar.SECOND, 01);
//        if (calendar3.getTime().compareTo(new Date()) < 0) calendar3.add(Calendar.HOUR_OF_DAY, 0);
//        Intent intent3 = new Intent(getApplicationContext(), CheckOutAlarmReceiver.class);
//        PendingIntent pendingIntent3 = null;
//        int pendingFlags3;
//        if (android.os.Build.VERSION.SDK_INT >= 23) {
//            pendingFlags3 = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
//        } else {
//            pendingFlags3 = PendingIntent.FLAG_UPDATE_CURRENT;
//        }
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
//            pendingIntent3 = PendingIntent.getBroadcast
//                    (this, 0, intent3, pendingFlags3);
//        }
//        else
//        {
//            pendingIntent3 = PendingIntent.getBroadcast
//                    (this, 0, intent3,pendingFlags3);
//        }
////        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager alarmManager3 = (AlarmManager) getSystemService(ALARM_SERVICE);
//        if (alarmManager3 != null) {
//            alarmManager3.setRepeating(AlarmManager.RTC_WAKEUP, calendar3.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent3);
//        }
//
//
//        /******************************************/
//        Calendar calendar4 = Calendar.getInstance();
//        calendar4.set(Calendar.HOUR_OF_DAY, 24);
//        calendar4.set(Calendar.MINUTE, 0);
//        calendar4.set(Calendar.SECOND, 01);
//        if (calendar4.getTime().compareTo(new Date()) < 0) calendar4.add(Calendar.HOUR_OF_DAY, 0);
//        Intent intent4 = new Intent(getApplicationContext(), CheckOutAlarmReceiver.class);
//        PendingIntent pendingIntent4 = null;
//        int pendingFlags4;
//        if (android.os.Build.VERSION.SDK_INT >= 23) {
//            pendingFlags4 = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
//        } else {
//            pendingFlags4 = PendingIntent.FLAG_UPDATE_CURRENT;
//        }
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
//            pendingIntent4 = PendingIntent.getBroadcast
//                    (this, 0, intent4, pendingFlags4);
//        }
//        else
//        {
//            pendingIntent4 = PendingIntent.getBroadcast
//                    (this, 0, intent4,pendingFlags4);
//        }
////        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager alarmManager4 = (AlarmManager) getSystemService(ALARM_SERVICE);
//        if (alarmManager4 != null) {
//            alarmManager4.setRepeating(AlarmManager.RTC_WAKEUP, calendar4.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent4);
//        }
//



//        Toast.makeText(this, "SetAlarm Morning Calling", Toast.LENGTH_SHORT).show();
//
//
//
//        Calendar calendar = Calendar.getInstance();
//
//        calendar.set(Calendar.HOUR_OF_DAY, 9);
//        calendar.set(Calendar.MINUTE, 35);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//
//        Log.d("time_evening", "setAlarm: " + Calendar.HOUR_OF_DAY + " " + Calendar.MINUTE);
//
//
//        Calendar cur = Calendar.getInstance();
//
//        if (cur.after(calendar)) {
//            calendar.add(Calendar.DATE, 1);
//        }
//
//        Intent myIntent = new Intent(FoDashbord.this, DailyReceiverEvening.class);
//        int ALARM1_ID = 20000;
//        PendingIntent pendingIntent = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
//            pendingIntent = PendingIntent.getBroadcast
//                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_MUTABLE);
//        }
//        else
//        {
//            pendingIntent = PendingIntent.getBroadcast
//                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        }
//      /*  PendingIntent pendingIntent = PendingIntent.getBroadcast(
//                FoDashbord.this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);*/
//        //  AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        AlarmManager alarmManager = (AlarmManager) getSystemService(MainActivity.ALARM_SERVICE);
//        assert alarmManager != null;
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                AlarmManager.INTERVAL_DAY, pendingIntent);


        /*/ Set the alarm to start at approximately 2:00 p.m.
Calendar calendar = Calendar.getInstance();
calendar.setTimeInMillis(System.currentTimeMillis());
calendar.set(Calendar.HOUR_OF_DAY, 14);

// With setInexactRepeating(), you have to use one of the AlarmManager interval
// constants--in this case, AlarmManager.INTERVAL_DAY.
alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
        AlarmManager.INTERVAL_DAY, alarmIntent);*/

    }

    private void MornigFirstAl() {
        //  Toast.makeText(this, "SetAlarm Morning Calling", Toast.LENGTH_SHORT).show();

        // Quote in Morning at 08:32:00 AM
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Log.d("time", "setAlarm: " + Calendar.HOUR_OF_DAY + " " + Calendar.MINUTE);

        // Toast.makeText(this, "time"+Calendar.HOUR_OF_DAY + " " + Calendar.MINUTE, Toast.LENGTH_SHORT).show();

        Calendar cur = Calendar.getInstance();

        if (cur.after(calendar)) {
            calendar.add(Calendar.DATE, 1);
        }

        Intent myIntent = new Intent(FoDashbord.this, MornigFirstAlarm.class);
        int ALARM1_ID = 10000;
        PendingIntent pendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast
                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_MUTABLE);
        }
        else
        {
            pendingIntent = PendingIntent.getBroadcast
                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
      /*  PendingIntent pendingIntent = PendingIntent.getBroadcast(
                FoDashbord.this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);*/
        //  AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(MainActivity.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }

    private void setAlarm11() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 14);/*1;00*/
        calendar.set(Calendar.MINUTE, 46);
        calendar.set(Calendar.SECOND, 01);
        if (calendar.getTime().compareTo(new Date()) < 0) calendar.add(Calendar.HOUR_OF_DAY, 0);
        Intent intent = new Intent(getApplicationContext(), CheckInAlarmReceiver.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        or
// add Intent.FLAG_ACTIVITY_MULTIPLE_TASK which start one more task your applications
//   where activity will not be cleared;
        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        PendingIntent pendingIntent = null;
        int pendingFlags;
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            pendingFlags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
        } else {
            pendingFlags = PendingIntent.FLAG_UPDATE_CURRENT;
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast
                    (this, 0, intent, pendingFlags);
        }
        else
        {
            pendingIntent = PendingIntent.getBroadcast
                    (this, 0, intent,pendingFlags);
        }
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
          /*  alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() +
                            60 * 1000, pendingIntent);*/

        }


    }

    private void setAlarm12() {
        //  Toast.makeText(this, "SetAlarm Morning Calling", Toast.LENGTH_SHORT).show();

        // Quote in Morning at 08:32:00 AM
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 58);
        calendar.set(Calendar.SECOND, 01);
        calendar.set(Calendar.MILLISECOND, 0);

        Log.d("time", "setAlarm: " + Calendar.HOUR_OF_DAY + " " + Calendar.MINUTE);

        // Toast.makeText(this, "time"+Calendar.HOUR_OF_DAY + " " + Calendar.MINUTE, Toast.LENGTH_SHORT).show();

        Calendar cur = Calendar.getInstance();

        if (cur.after(calendar)) {
            calendar.add(Calendar.DATE, 1);
        }

        Intent myIntent = new Intent(FoDashbord.this, CheckInAlarmReceiver.class);
        int ALARM1_ID = 0;
        PendingIntent pendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast
                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_MUTABLE);
        }
        else
        {
            pendingIntent = PendingIntent.getBroadcast
                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
       /* PendingIntent pendingIntent = PendingIntent.getBroadcast(
                FoDashbord.this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);*/
        //  AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(MainActivity.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }



    private void MornigTwoAl() {
        //  Toast.makeText(this, "SetAlarm Morning Calling", Toast.LENGTH_SHORT).show();

        // Quote in Morning at 08:32:00 AM
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 13);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Log.d("time", "setAlarm: " + Calendar.HOUR_OF_DAY + " " + Calendar.MINUTE);

        // Toast.makeText(this, "time"+Calendar.HOUR_OF_DAY + " " + Calendar.MINUTE, Toast.LENGTH_SHORT).show();

        Calendar cur = Calendar.getInstance();

        if (cur.after(calendar)) {
            calendar.add(Calendar.DATE, 1);
        }

        Intent myIntent = new Intent(FoDashbord.this, MornigTwoAlarm.class);
        int ALARM1_ID = 10000;
        PendingIntent pendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast
                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_MUTABLE);
        }
        else
        {
            pendingIntent = PendingIntent.getBroadcast
                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
       /* PendingIntent pendingIntent = PendingIntent.getBroadcast(
                FoDashbord.this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);*/
        //  AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(MainActivity.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }

    private void MornigThreeAl() {
        //  Toast.makeText(this, "SetAlarm Morning Calling", Toast.LENGTH_SHORT).show();

        // Quote in Morning at 08:32:00 AM
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Log.d("time", "setAlarm: " + Calendar.HOUR_OF_DAY + " " + Calendar.MINUTE);

        // Toast.makeText(this, "time"+Calendar.HOUR_OF_DAY + " " + Calendar.MINUTE, Toast.LENGTH_SHORT).show();

        Calendar cur = Calendar.getInstance();

        if (cur.after(calendar)) {
            calendar.add(Calendar.DATE, 1);
        }

        Intent myIntent = new Intent(FoDashbord.this, MornigThreeAlarm.class);
        int ALARM1_ID = 10000;
        PendingIntent pendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast
                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_MUTABLE);
        }
        else
        {
            pendingIntent = PendingIntent.getBroadcast
                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
       /* PendingIntent pendingIntent = PendingIntent.getBroadcast(
                FoDashbord.this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);*/
        //  AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(MainActivity.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }

    private void SelectImage() {

        final CharSequence[] items = {"Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(FoDashbord.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 4);
                } else if (items[i].equals("Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);
                } else if (items[i].equals("Cancel"))
                    dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void MornigFourAl() {
        //  Toast.makeText(this, "SetAlarm Morning Calling", Toast.LENGTH_SHORT).show();

        // Quote in Morning at 08:32:00 AM
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Log.d("time", "setAlarm: " + Calendar.HOUR_OF_DAY + " " + Calendar.MINUTE);

        // Toast.makeText(this, "time"+Calendar.HOUR_OF_DAY + " " + Calendar.MINUTE, Toast.LENGTH_SHORT).show();

        Calendar cur = Calendar.getInstance();

        if (cur.after(calendar)) {
            calendar.add(Calendar.DATE, 1);
        }

        Intent myIntent = new Intent(FoDashbord.this, MornigFourAlarm.class);
        int ALARM1_ID = 10000;
        PendingIntent pendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast
                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_MUTABLE);
        }
        else
        {
            pendingIntent = PendingIntent.getBroadcast
                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
       /* PendingIntent pendingIntent = PendingIntent.getBroadcast(
                FoDashbord.this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);*/
        //  AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(MainActivity.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }

    private void MornigFiveAl() {
        //  Toast.makeText(this, "SetAlarm Morning Calling", Toast.LENGTH_SHORT).show();

        // Quote in Morning at 08:32:00 AM
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Log.d("time", "setAlarm: " + Calendar.HOUR_OF_DAY + " " + Calendar.MINUTE);

        // Toast.makeText(this, "time"+Calendar.HOUR_OF_DAY + " " + Calendar.MINUTE, Toast.LENGTH_SHORT).show();

        Calendar cur = Calendar.getInstance();

        if (cur.after(calendar)) {
            calendar.add(Calendar.DATE, 1);
        }

        Intent myIntent = new Intent(FoDashbord.this, MornigFiveAlarm.class);
        int ALARM1_ID = 10000;
        PendingIntent pendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast
                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_MUTABLE);
        }
        else
        {
            pendingIntent = PendingIntent.getBroadcast
                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }

        AlarmManager alarmManager = (AlarmManager) getSystemService(MainActivity.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new
                        String[]{READ_EXTERNAL_STORAGE,
                        String.valueOf(Intent.FLAG_GRANT_READ_URI_PERMISSION),
                        String.valueOf(Intent.FLAG_GRANT_WRITE_URI_PERMISSION),
                        CAMERA,
                        WRITE_EXTERNAL_STORAGE, CALL_PHONE, READ_CONTACTS, SEND_SMS, STORAGE_SERVICE,
                        Manifest.permission.PROCESS_OUTGOING_CALLS,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_PHONE_NUMBERS,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                },
                1);
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


    @Override
    protected void onResume() {
        super.onResume();
        if ("true".equals(lo_flage)){
            lo_flage = "false";

            Log.d(TAG, "onCreate:---empid----- "+emp_id);

            WebService.getClient().chcek_or_not(emp_id).enqueue(new Callback<checkornot_model_ak>() {
                @Override
                public void onResponse(Call<checkornot_model_ak> call, Response<checkornot_model_ak> response) {

                    Toast.makeText(FoDashbord.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: ---"+response.body().getMsg());
                    if ("CHEKIN".equals(response.body().getMsg()) || "OK".equals(response.body().getMsg())){
                        getlocation();
                    }else   if ("CHEKIN NOT".equals(response.body().getMsg())) {
                        Log.d(TAG, "onResponse: CHEKIN NOT");
//                        setAlarm();
                    }

                    /*if ("CHEKOUT".equals(response.body().getMsg()) || "OK".equals(response.body().getMsg())){
                        getlocation();
                    }else{
                        setAlarmEvening();
                        Log.d(TAG, "onResponse: not working gps");
                    }*/
                }

                @Override
                public void onFailure(Call<checkornot_model_ak> call, Throwable throwable) {
                    Toast.makeText(FoDashbord.this, call.toString(), Toast.LENGTH_SHORT).show();

                }
            });

            WebService.getClient().check_out_check_or_not(emp_id).enqueue(new Callback<checkornot_model_ak>() {
                @Override
                public void onResponse(Call<checkornot_model_ak> call, Response<checkornot_model_ak> response) {

                    Toast.makeText(FoDashbord.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: ---"+response.body().getMsg());
                    if ("CHEKOUT".equals(response.body().getMsg()) || "OK".equals(response.body().getMsg())){
                        getlocation();
                    }else   if ("CHEKOUT NOT".equals(response.body().getMsg())) {
                        Log.d(TAG, "onResponse: CHEKOUT NOT");
                        setAlarmEvening();
//                        setAlarm();
                    }


                    /*if ("CHEKOUT".equals(response.body().getMsg()) || "OK".equals(response.body().getMsg())){
                        getlocation();
                    }else{
                        setAlarmEvening();
                        Log.d(TAG, "onResponse: not working gps");
                    }*/
                }

                @Override
                public void onFailure(Call<checkornot_model_ak> call, Throwable throwable) {
                    Toast.makeText(FoDashbord.this, call.toString(), Toast.LENGTH_SHORT).show();

                }
            });


        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_VERSION_UPDATE) {
            if (resultCode == Activity.RESULT_CANCELED) {
                // If the update is cancelled by the user,
                // you can request to start the update again.
                inAppUpdateManager.checkForAppUpdate();

                Log.d(TAG, "Update flow failed! Result code: " + resultCode);
            }
        }

        if (requestCode == 4) {
            Bundle bundle = data.getExtras();
            final Bitmap photo = (Bitmap) bundle.get("data");
            profile_image.setImageBitmap(photo);
            savebitmap(photo);
         /*   File file=new File(waypath);
            saveBitmaptoFile(file);*/



        } else if (requestCode == SELECT_FILE) {
            Uri selectedImageUri = data.getData();

            profile_image.setImageURI(selectedImageUri);
            uriclient = data.getData();
            Log.d("AadharImageUri", uriclient.toString());
            waypath = getFilePath(this, uriclient);
//            Log.d("AadharmageUri", waypathClient);
            uploadProfile(waypath);

        }
    }

    private void uploadProfile(String waypath) {
        pro.show();

//        progressDialog = new ProgressDialog(FoDashbord.this);
//        progressDialog.show();
//        progressDialog.setContentView(R.layout.progress_dialog);
//        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        if (waypath == null) {
            Utils.showErrorToast(FoDashbord.this, "Please upload Selfie Photo");
        }
        else {
            d("upload image", "onClick: " + waypath);
            File file1 = new File(waypath);
            final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
            MultipartBody.Part imagePartChckout = MultipartBody.Part.createFormData("image", file1.getName(), requestBody1);

            WebService.getClient().uploadImageProfile(
                    RequestBody.create(okhttp3.MediaType.parse("text/plain"), emp_id),
                    imagePartChckout).enqueue(new Callback<DataSubmitModel>() {
                @Override
                public void onResponse(@NotNull Call<DataSubmitModel> call,
                                       @NotNull Response<DataSubmitModel> response) {
                    pro.dismiss();

                    assert response.body() != null;
                    d("Response", response.body().toString());
                    Toast.makeText(FoDashbord.this, "" + response.body().getMsg(),
                            Toast.LENGTH_LONG).show();

                    if (response.body()!=null){
                        if (response.body().getSuccess()){
                            sp.edit().putString("emp_photo",response.body().getImage()).apply();
                        }
                    }



                }

                @Override
                public void onFailure(@NotNull Call<DataSubmitModel> call, @NotNull Throwable t) {
                    pro.dismiss();

                    d("Response2", t.getMessage());
                    Toast.makeText(FoDashbord.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }


    public String getFilePath(Context context, Uri uri) {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 22 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/all_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private File savebitmap(Bitmap bmp) {

        String extStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();/* Environment.getExternalStorageDirectory().getPath();*/
        FileOutputStream outStream = null;
        // String temp = null;

        final int random = new Random().nextInt(6000000) + 20;
        File file = new File(extStorageDirectory, random + ".png");

        waypath = getFilePath(this, Uri.fromFile(file));
       /* if (!file.exists())
        {
            // Make it, if it doesn't exit
            boolean success = file.getParentFile().mkdirs();
            if (!success)
            {
                file = null;
            }
        }else*/ if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, random + ".png");
            waypath = getFilePath(this, Uri.fromFile(file));
        }
        try {
            outStream = new FileOutputStream(file);
//            Bitmap scaledBitmap = scaleDown(bmp, 65, true);
//            bmp.compress(Bitmap.CompressFormat.PNG, 30, outStream);
//            scaledBitmap.compress(Bitmap.CompressFormat.PNG, 65, outStream);
            bmp.compress(Bitmap.CompressFormat.PNG, 70, outStream);
            outStream.flush();
            outStream.close();

            uploadProfile(waypath);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

}
