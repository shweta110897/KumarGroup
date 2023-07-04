package com.example.kumarGroup.Dashboard;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.kumarGroup.Activity;
import com.example.kumarGroup.Attendance;
import com.example.kumarGroup.BookingDeliveryUpload.BookingDeliveryMainActivity;
import com.example.kumarGroup.CustomerProfileData.CustomerMainActivity;
import com.example.kumarGroup.DeliveryReport.DeliveryReportButtonMain;
import com.example.kumarGroup.EmployeeTracker.ak.Select_employee;
import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.Inquiry.InquiryMainActivity;
import com.example.kumarGroup.MyProfile.EmpMyProfileMainActivity;
import com.example.kumarGroup.OverTime.OverTimeMainActivity;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ReportCollection.ReportCollectionMainActivity;
import com.example.kumarGroup.ScoreBoard.ScoreBoardCategory;
import com.example.kumarGroup.ScoreBoard.ScoreBoardMainActivity;
import com.example.kumarGroup.TractorDoIt;
import com.example.kumarGroup.Traveling.TravelingMainActivity;
import com.example.kumarGroup.TrueValue.TrueValueMainActivity;
import com.example.kumarGroup.ViewInquiry.ViewInquiryMainActivity;
import com.example.kumarGroup.Wallet;
import com.example.kumarGroup.Workshop.WorkshopMainActivity;
import com.example.kumarGroup.cashbookAK.CashbookDashboard;
import com.example.kumarGroup.meetingRoomAK.MeetingRoomActivity;
import com.example.kumarGroup.myProfileNew.MyProfileMainActivity;


import java.util.ArrayList;


public class DashBoardAdapter extends ArrayAdapter<HomeItemss> {

        String emp_id;
        String module_name;
        SharedPreferences sp;


        public DashBoardAdapter(@NonNull Context context, ArrayList<HomeItemss> courseModelArrayList) {
                super(context, 0, courseModelArrayList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View listitemView = convertView;
                if (listitemView == null) {
                // Layout Inflater inflates each item to be displayed in GridView.
                        listitemView = LayoutInflater.from(getContext()).inflate(R.layout.item_dashboard, parent, false);
                }

                HomeItemss courseModel = getItem(position);
                TextView courseTV = listitemView.findViewById(R.id.tv_name);
                ImageView courseIV = listitemView.findViewById(R.id.iv_icon);
                CardView mainCard = listitemView.findViewById(R.id.ll_ganral_visit);

                sp = getContext().getSharedPreferences("Login", MODE_PRIVATE);

                emp_id = sp.getString("emp_id", "");
                module_name = sp.getString("module_name", "");

                courseTV.setText(courseModel.getItem_name());
                courseIV.setImageResource(courseModel.getItem_img());
                mainCard.setOnClickListener(v -> {
                        if (courseModel.getItem_name2().contains("cash_book")){
                                getContext().startActivity(new Intent(getContext(), CashbookDashboard.class));

                        }

                        if (courseModel.getItem_name2().contains("meeting_room")){
                                getContext().startActivity(new Intent(getContext(), MeetingRoomActivity.class)
                                        .putExtra("type","EMPLOYEE")
                                        .putExtra("id",emp_id));

//
                        }
                        if (courseModel.getItem_name2().contains("emp_tracking")){

                                getContext().startActivity(new Intent(getContext(), Select_employee.class));

                        }
                        if (courseModel.getItem_name2().contains("Data Bank")){

                                getContext().startActivity(new Intent(getContext(), TractorDoIt.class));

                        }
                        if (courseModel.getItem_name2().contains("Myprofile")){
                                getContext().startActivity(new Intent(getContext(), MyProfileMainActivity.class));

                        }
                        if (courseModel.getItem_name2().contains("Attendance")){

                                getContext().startActivity(new Intent(getContext(), Attendance.class));

                        }
                        if (courseModel.getItem_name2().contains("Activity")){

                                getContext().startActivity(new Intent(getContext(), Activity.class));

                        }
                        if (courseModel.getItem_name2().contains("Wallet")){
                                getContext().startActivity(new Intent(getContext(), Wallet.class));

//
                        }
                        if (courseModel.getItem_name2().contains("Overtime")){

                                getContext().startActivity(new Intent(getContext(), OverTimeMainActivity.class));
                        }
                        if (courseModel.getItem_name2().contains("payment collection")){
                                getContext().startActivity(new Intent(getContext(), ReportCollectionMainActivity.class));

                        }
                        if (courseModel.getItem_name2().contains("Booking_delivery")){
                                getContext().startActivity(new Intent(getContext(), BookingDeliveryMainActivity.class));

//
                        }
                        if (courseModel.getItem_name2().contains("Workshop")){
                                getContext().startActivity(new Intent(getContext(), WorkshopMainActivity.class));

//
                        }
                        if (courseModel.getItem_name2().contains("inquirymod")){
                                getContext().startActivity(new Intent(getContext(), InquiryMainActivity.class));

//
                        }
                        if (courseModel.getItem_name2().contains("Travaling")){
                                getContext().startActivity(new Intent(getContext(), TravelingMainActivity.class));

//
                        }
                        if (courseModel.getItem_name2().contains("Maintenance")){
                                getContext().startActivity(new Intent(getContext(), CustomerMainActivity.class));

//
                        }
                        if (courseModel.getItem_name2().contains("viewinquiry")){
                                getContext().startActivity(new Intent(getContext(), ViewInquiryMainActivity.class));

//
                        }

                        if (courseModel.getItem_name2().contains("viewProfile")){
                                getContext().startActivity(new Intent(getContext(), EmpMyProfileMainActivity.class));

//
                        }

                        if (courseModel.getItem_name2().contains("True Value")){
                                getContext().startActivity(new Intent(getContext(), TrueValueMainActivity.class));

//
                        }
                        if (courseModel.getItem_name2().contains("myscoreboard")){
                                FoDashbord.Score_Board_Check = false;
                                getContext().startActivity(new Intent(getContext(), ScoreBoardCategory.class));

//
                        }
                        if (courseModel.getItem_name2().contains("scoreboard_main")){
                                FoDashbord.Score_Board_Check = true;
                                getContext().startActivity(new Intent(getContext(), ScoreBoardMainActivity.class));

//
                        }
                        if (courseModel.getItem_name2().contains("delivery_report")){
                                getContext().startActivity(new Intent(getContext(), DeliveryReportButtonMain.class));

//
                        }
                });
                
                if (courseModel.getFlag()==1){
                        mainCard.setVisibility(View.VISIBLE);
                }

                return listitemView;
        }
}

