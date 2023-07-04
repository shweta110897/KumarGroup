package com.example.kumarGroup.Dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.example.kumarGroup.R;

import java.util.ArrayList;

public class DashboardDemoActivity extends AppCompatActivity {

    GridView mainGrid;
    DashBoardAdapter dashBoardAdapter;
    String emp_id;
    String module_name;
    SharedPreferences sp;
    ArrayList<HomeItemss> homeItemLists;
    ArrayList<HomeItemss> homeItemLists1=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_demo);

        sp = getSharedPreferences("Login", MODE_PRIVATE);

        emp_id = sp.getString("emp_id", "");
        module_name = sp.getString("module_name", "");
        Log.d("module_name",module_name);

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
        homeItemLists.add(new HomeItemss("Score Board","myscoreboard", R.drawable.ic_baseline_bar_chart_24,17,0));
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


    }
}