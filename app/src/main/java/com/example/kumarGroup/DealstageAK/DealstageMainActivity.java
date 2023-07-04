package com.example.kumarGroup.DealstageAK;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.Catnotattend;
import com.example.kumarGroup.Common_Search.CommonSearchActivity;
import com.example.kumarGroup.Common_Search.CommonSearchActivity1;
import com.example.kumarGroup.DataVillageeModel123;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewInquiry.AutoCmpleteAdapter;
import com.example.kumarGroup.ViewInquiryDealStage.DealTypeFrag;
import com.example.kumarGroup.ViewInquiryDealStage.ModelFilterFrag;
import com.example.kumarGroup.ViewInquiryDealStage.ModelNameFrag;

import com.example.kumarGroup.ViewInquiryDealStage.VillageNameFrag;
import com.example.kumarGroup.ViewInquiryDealStage.passingTypeFrag;
import com.example.kumarGroup.ViewInquiryDealStage.paymentTypeFrag;
import com.example.kumarGroup.Village;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.Deal_notattend_model;
import com.example.kumarGroup.deal_set_count_model;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DealstageMainActivity extends AppCompatActivity {

    //My Inquire

    String emp_id;
    String catId_eid;
    static String VillageId="";
    String catId_list;
    static String Modelname="";
    SharedPreferences sp,sp2;

    SearchView searchView;
    AutoCompleteTextView textView;

    List<Catnotattend> catShowRCGVS;
    List<Catnotattend> catsWithMissingParams = new ArrayList<>();

    TextView deal_notAttend,deal_notAttend_count
            ,deal_hotInquiry,deal_hotInquiry_count
            ,deal_firstmeeting,deal_firstmeeting_count
            ,deal_overDue,deal_overDue_count
            ,deal_warmInquiry,deal_warmInquiry_count,
            deal_coldInquiry,deal_coldInquiry_count
            ,deal_bookingInquiry,deal_bookingInquiry_count
            ,deal_deliveryInquiry,deal_deliveryInquiry_count
            ,deal_sell_lost_Inquiry,deal_sell_lost_Inquiry_count,
            deal_dropInquiry,deal_dropInquiry_count
            ,deal_makeandoffInquiry,deal_makeandoffInquiry_count
            ,deal_nextactivityplanInquiry,deal_nextactivityplanInquiry_count
            ,deal_2meetingInquiry,deal_2meetingInquiry_count,deal_NegotiationandfinalInquiry,
            deal_negotiation_count,
            deal_DealerMeeting_Inquiry,deal_DealerMeeting_count,
            deal_followup_Inquiry,deal_followup_count,
            deal_followup_Inquiry2,deal_followup_count2,
            deal_followup_Inquiry3,deal_followup_count3,
            deal_perfomance,deal_perfomancecount,feedback_call,feedback_callcount,
            deal_input_miss_Inquiry_count,deal_input_miss_Inquiry,
            deal_common_search,deal_filter,deal_filtercommon_search,clear_filter,deal_more;

    static Dialog dialog;
    Dialog dialog1;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private BookingFragmentAdapter adapter;
    static List<String> modelname_list;

    FilterFragmentAdapter adapter1;

    String Passingtype="",Maker,Model,MFGYear;
    String[] PassingType_list={"Select PassingType","Agriculture", "Commercial"};

    String DealType="";
    String[] DealType_list={"Select Deal Type","Exchange", "Fresh"};

    String Paymenttype="";
    String[] PaymentType_list={"Select PaymentType","Loan", "Cash"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealstage_main);

        Log.d("TAG", "onCreate: checkPage " );

        getSupportActionBar().setTitle("Deal Stage");
//        catId_eid=getIntent().getStringExtra("catId_eid");

        SharedPreferences sharedPreferences2 = getSharedPreferences("dealstage3cateid", Context.MODE_PRIVATE);
        catId_eid = sharedPreferences2.getString("catId_eid","");
        catId_list = sharedPreferences2.getString("catId_list","");

        deal_2meetingInquiry_count = findViewById(R.id.deal_2meetingInquiry_count);
        deal_nextactivityplanInquiry_count = findViewById(R.id.deal_nextactivityplanInquiry_count);
        deal_makeandoffInquiry_count = findViewById(R.id.deal_makeandoffInquiry_count);
        deal_dropInquiry_count = findViewById(R.id.deal_dropInquiry_count);
        deal_sell_lost_Inquiry_count = findViewById(R.id.deal_sell_lost_Inquiry_count);
        deal_deliveryInquiry_count = findViewById(R.id.deal_deliveryInquiry_count);
        deal_bookingInquiry_count = findViewById(R.id.deal_bookingInquiry_count);
        deal_coldInquiry_count = findViewById(R.id.deal_coldInquiry_count);
        deal_warmInquiry_count = findViewById(R.id.deal_warmInquiry_count);
        deal_overDue_count = findViewById(R.id.deal_overDue_count);
        deal_firstmeeting_count = findViewById(R.id.deal_firstmeeting_count);
        deal_hotInquiry_count = findViewById(R.id.deal_hotInquiry_count);
        deal_notAttend_count = findViewById(R.id.deal_notAttend_count);
        deal_negotiation_count = findViewById(R.id.deal_negotiation_count);
        deal_DealerMeeting_count = findViewById(R.id.deal_DealerMeeting_count);
        deal_followup_count = findViewById(R.id.deal_followup_count);
        deal_followup_count2 = findViewById(R.id.deal_followup_count2);
        deal_followup_count3 = findViewById(R.id.deal_followup_count3);
        deal_perfomancecount = findViewById(R.id.deal_perfomancecount);
        feedback_callcount = findViewById(R.id.feedback_callcount);
        deal_common_search = findViewById(R.id.deal_common_search);
        deal_filter = findViewById(R.id.deal_filter);
        searchView = findViewById(R.id.searchView);
        deal_filtercommon_search = findViewById(R.id.deal_filtercommon_search);
        clear_filter = findViewById(R.id.clear_filter);
        deal_input_miss_Inquiry_count = findViewById(R.id.deal_input_miss_Inquiry_count);
        deal_more = findViewById(R.id.deal_more);

        deal_notAttend = findViewById(R.id.deal_notAttend);
        deal_hotInquiry = findViewById(R.id.deal_hotInquiry);
        deal_firstmeeting = findViewById(R.id.deal_firstmeeting);
        deal_overDue = findViewById(R.id.deal_overDue);
        deal_warmInquiry = findViewById(R.id.deal_warmInquiry);
        deal_coldInquiry = findViewById(R.id.deal_coldInquiry);
        deal_bookingInquiry = findViewById(R.id.deal_bookingInquiry);
        deal_deliveryInquiry = findViewById(R.id.deal_deliveryInquiry);
        deal_sell_lost_Inquiry = findViewById(R.id.deal_sell_lost_Inquiry);
        deal_dropInquiry = findViewById(R.id.deal_dropInquiry);
        deal_makeandoffInquiry = findViewById(R.id.deal_makeandoffInquiry);
        deal_nextactivityplanInquiry = findViewById(R.id.deal_nextactivityplanInquiry);
        deal_2meetingInquiry = findViewById(R.id.deal_2meetingInquiry);
        deal_NegotiationandfinalInquiry = findViewById(R.id.deal_NegotiationandfinalInquiry);
        deal_DealerMeeting_Inquiry = findViewById(R.id.deal_DealerMeeting_Inquiry);
        deal_followup_Inquiry = findViewById(R.id.deal_followup_Inquiry);
        deal_followup_Inquiry2 = findViewById(R.id.deal_followup_Inquiry2);
        deal_followup_Inquiry3 = findViewById(R.id.deal_followup_Inquiry3);
        deal_perfomance = findViewById(R.id.deal_perfomance);
        feedback_call = findViewById(R.id.feedback_call);
        deal_input_miss_Inquiry = findViewById(R.id.deal_input_miss_Inquiry);

        sp = getSharedPreferences("Login", MODE_PRIVATE);

        emp_id = sp.getString("emp_id", "");

        textView =findViewById(R.id.AutosearchView);

        setCountMethod(VillageId,Modelname,Paymenttype,Passingtype,DealType,Maker,Model,MFGYear);
        getVillageList();

        clear_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCountMethod("","","","","","","","");
            }
        });

        deal_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DealstageMainActivity.this, DealStageItemMenu.class);
                startActivity(intent);
            }
        });

        deal_common_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setVisibility(View.GONE);
                deal_filter.setVisibility(View.VISIBLE);
                deal_filtercommon_search.setVisibility(View.VISIBLE);
                Intent intent = new Intent(DealstageMainActivity.this, CommonSearchActivity1.class);
                intent.putExtra("VillageId",VillageId);
                intent.putExtra("payment_type","");
                intent.putExtra("passing_type","");
                startActivity(intent);
            }
        });


        deal_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* textView.setVisibility(View.VISIBLE);
                deal_filter.setVisibility(View.GONE);
                deal_filtercommon_search.setVisibility(View.GONE);
*/
                VillageId="";
                Modelname="";
                showDialogModelVillage();
            }
        });

        deal_filtercommon_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VillageId="";
                Modelname="";
                showDialogPayment();

            }
        });

        feedback_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeliveryButtonActivity.perfomanceFlag = true;
                startActivity(new Intent(DealstageMainActivity.this,DeliveryButtonActivity.class) .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear).putExtra("VillageId",VillageId).putExtra("model",Modelname));

            }
        });

        deal_perfomance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeliveryButtonActivity.perfomanceFlag = true;
                startActivity(new Intent(DealstageMainActivity.this,DeliveryButtonActivity.class)
                        .putExtra("VillageId",VillageId).putExtra("model",Modelname) .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)
                        .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear));
            }
        });

        deal_DealerMeeting_Inquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.deal_dealer_Inquiry = true;
                startActivity(new Intent(DealstageMainActivity.this,DealstageRecyclerActivity.class)
                        .putExtra("actionbar","Dealer Meeting").putExtra("VillageId",VillageId)
                        .putExtra("model",Modelname).putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear));
            }
        });

        deal_followup_Inquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.deal_followup_Inquiry = true;
                startActivity(new Intent(DealstageMainActivity.this,DealstageRecyclerActivity.class)
                        .putExtra("actionbar","Follow up").putExtra("VillageId",VillageId).putExtra("model",Modelname) .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear));
            }
        });

        deal_followup_Inquiry2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.deal_followup_Inquiry2 = true;
                startActivity(new Intent(DealstageMainActivity.this,DealstageRecyclerActivity.class)
                        .putExtra("actionbar","Follow up").putExtra("VillageId",VillageId).putExtra("model",Modelname) .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear));
            }
        });

        deal_followup_Inquiry3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.deal_followup_Inquiry3 = true;
                startActivity(new Intent(DealstageMainActivity.this,DealstageRecyclerActivity.class)
                        .putExtra("actionbar","Follow up").putExtra("VillageId",VillageId).putExtra("model",Modelname) .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear));
            }
        });

        deal_input_miss_Inquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.deal_input_miss_Inquiry_flag = true;
                startActivity(new Intent(DealstageMainActivity.this,DealstageRecyclerActivity.class)
                        .putExtra("actionbar","Hot Inquire").putExtra("VillageId",VillageId)
                        .putExtra("model",Modelname).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });



        deal_NegotiationandfinalInquiry.setEnabled(false);


        deal_notAttend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.notattend_flag = true;
                startActivity(new Intent(DealstageMainActivity.this,DealstageRecyclerActivity.class)
                        .putExtra("actionbar","Not attend").putExtra("VillageId",VillageId) .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype).putExtra("model",Modelname)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear));
            }
        });

        deal_hotInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.hotInquiry_flag = true;
                startActivity(new Intent(DealstageMainActivity.this,DealstageRecyclerActivity.class)
                        .putExtra("actionbar","Hot Inquire").putExtra("VillageId",VillageId)
                        .putExtra("model",Modelname)
                        .putExtra("DealType",DealType)
                        .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("MFGYear",MFGYear)


                );
            }
        });

        deal_makeandoffInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.hotInquiry_flag = true;
                startActivity(new Intent(DealstageMainActivity.this,DealstageRecyclerActivity.class)
                .putExtra("actionbar","Make An Offer").putExtra("VillageId",VillageId).putExtra("model",Modelname) .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear));
            }
        });

        deal_firstmeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.firstmeeting_flag = true;
                startActivity(new Intent(DealstageMainActivity.this,DealstageRecyclerActivity.class)
                        .putExtra("actionbar","first meeting").putExtra("VillageId",VillageId).putExtra("model",Modelname) .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear));
            }
        });

        deal_overDue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.deal_overDue_flag = true;
                startActivity(new Intent(DealstageMainActivity.this,DealstageRecyclerActivity.class)
                        .putExtra("actionbar","over Due").putExtra("VillageId",VillageId).putExtra("model",Modelname) .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear));
            }
        });

        deal_warmInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.deal_warmInquiry_flag = true;
                startActivity(new Intent(DealstageMainActivity.this,DealstageRecyclerActivity.class)
                        .putExtra("actionbar","Warm Inquire").putExtra("VillageId",VillageId).putExtra("model",Modelname) .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear));
            }
        });

        deal_nextactivityplanInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.deal_nextactivityplanInquiry_flag = true;
                startActivity(new Intent(DealstageMainActivity.this,DealstageRecyclerActivity.class)
                        .putExtra("actionbar","Next Activity Plan") .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear).putExtra("VillageId",VillageId).putExtra("model",Modelname));
            }
        });

        deal_coldInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.deal_coldInquiry_flag = true;
                startActivity(new Intent(DealstageMainActivity.this,DealstageRecyclerActivity.class)
                        .putExtra("actionbar","Cold Inquire") .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear).putExtra("VillageId",VillageId).putExtra("model",Modelname));
            }
        });

        deal_2meetingInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.deal_coldInquiry_flag = true;
                startActivity(new Intent(DealstageMainActivity.this,DealstageRecyclerActivity.class)
                        .putExtra("actionbar","Second meeting") .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear).putExtra("VillageId",VillageId).putExtra("model",Modelname));
            }
        });

        deal_bookingInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.deal_bookingInquiry_flag = true;
                startActivity(new Intent(DealstageMainActivity.this,DealstageRecyclerActivity.class)
                        .putExtra("actionbar","booking") .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear).putExtra("VillageId",VillageId).putExtra("model",Modelname));
            }
        });

        deal_deliveryInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DealstageRecyclerActivity.deal_deliveryInquiry_flag = true;
                startActivity(new Intent(DealstageMainActivity.this, DeliveryButtonActivity.class) .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear).putExtra("VillageId",VillageId).putExtra("model",Modelname));
                        //.putExtra("actionbar","Delivery"));
            }
        });

        deal_sell_lost_Inquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.deal_sell_lost_Inquiry_flag = true;
                startActivity(new Intent(DealstageMainActivity.this,DealstageRecyclerActivity.class)
                        .putExtra("actionbar","sell lost") .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear).putExtra("VillageId",VillageId).putExtra("model",Modelname));
            }
        });

        deal_dropInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.deal_dropInquiry_flag = true;
                startActivity(new Intent(DealstageMainActivity.this,DealstageRecyclerActivity.class)
                        .putExtra("actionbar","drop") .putExtra("Paymenttype",Paymenttype)
                        .putExtra("Passingtype",Passingtype)   .putExtra("Maker",Maker)
                        .putExtra("Model1",Model)
                        .putExtra("DealType",DealType)
                        .putExtra("MFGYear",MFGYear).putExtra("VillageId",VillageId).putExtra("model",Modelname));
            }
        });
    }

    private void showDialogPayment() {
        dialog = new Dialog(DealstageMainActivity.this);
        dialog.setContentView(R.layout.filter_payment);/*filter_payment*/


        tabLayout = (TabLayout) dialog.findViewById(R.id.dr_booking_TabLayout);
        viewPager2 = (ViewPager2) dialog.findViewById(R.id.user_booking_ViewPager);


//        setupViewPager(viewPager2);
        FragmentManager fm =getSupportFragmentManager();
        adapter = new BookingFragmentAdapter(fm,getLifecycle(),dialog);
        viewPager2.setAdapter(adapter);


        tabLayout.addTab(tabLayout.newTab().setText("Payment Type"));
        tabLayout.addTab(tabLayout.newTab().setText("PassingType"));
        tabLayout.addTab(tabLayout.newTab().setText("Deal Type"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });



        Spinner sp_paymenttype =dialog. findViewById(R.id.sp_paymenttype);
        Spinner sp_passingtype =dialog. findViewById(R.id.sp_passingtype);
        Button btn_submit =dialog. findViewById(R.id.btn_submit);
        modelname_list = new ArrayList<>();


        ArrayAdapter adapterPayment = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, PaymentType_list);
        sp_paymenttype.setAdapter(adapterPayment);

        sp_paymenttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( PaymentType_list[position]=="Select FollowUp"){
                    Paymenttype = "";
                }
                else{
                    Paymenttype = PaymentType_list[position];
                    Log.e("Paymenttype123",Paymenttype);
                }
                //  TypeInq = Type_inq[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent(DealstageMainActivity.this, CommonSearchActivity.class);
                intent.putExtra("Paymenttype",Paymenttype);
                startActivity(intent);
            }
        });

        dialog.show();

    }

    private void showDialogModelVillage() {

        Log.d("testtttt","openFilterdialog");

        dialog1 = new Dialog(DealstageMainActivity.this);
        dialog1.setContentView(R.layout.filter_payment);/*filter_payment*/


        TabLayout tabLayout = (TabLayout) dialog1.findViewById(R.id.dr_booking_TabLayout);
        ViewPager2 viewPager = (ViewPager2) dialog1.findViewById(R.id.user_booking_ViewPager);


        FragmentManager fm =getSupportFragmentManager();
        adapter1 = new FilterFragmentAdapter(fm,getLifecycle(),dialog1,"Deal Stage");
        viewPager.setAdapter(adapter1);


        tabLayout.addTab(tabLayout.newTab().setText("Village"));
        tabLayout.addTab(tabLayout.newTab().setText("Model Name(New)"));

        tabLayout.addTab(tabLayout.newTab().setText("Exchange"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        dialog1.show();
    }

    public static class FilterFragmentAdapter extends FragmentStateAdapter {


        public FilterFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Dialog dialog,String act) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment1 = null;
            if (position == 0)
            {
                VillageId="";
                fragment1 = new VillageNameFrag(dialog);
            }
            else if (position == 1)
            {
                Modelname="";
                fragment1 = new ModelNameFrag(dialog,modelname_list);
            }
            else if (position == 2)
            {
                fragment1 = new ModelFilterFrag(dialog);
            }
            assert fragment1 != null;
            return fragment1;

        }


        @Override
        public int getItemCount() {
            return 3;
        }
    }

    public static class BookingFragmentAdapter extends FragmentStateAdapter {
        public BookingFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Dialog dialog) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment = null;
            if (position == 0)
            {
                fragment = new paymentTypeFrag(dialog);
            }
            else if (position == 1)
            {
                fragment = new passingTypeFrag(dialog);
            }
            else if (position == 2)
            {
                fragment = new DealTypeFrag(dialog);
            }
            return fragment;

        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }

    private void getVillageList() {

        WebService.getClient().getVillageList123().enqueue(new Callback<DataVillageeModel123>() {
            @Override
            public void onResponse(Call<DataVillageeModel123> call, @NonNull Response<DataVillageeModel123> response1) {
                assert response1.body() != null;
                Log.d("Village_Name123", String.valueOf(response1.body().getVillage()));
                if (response1.isSuccessful()) {
                    if (response1.body() != null) {

                        Log.d("DealstageActivity", "onResponse--villagelist: ---checkingggg");

                        AutoCmpleteAdapter adapter = new AutoCmpleteAdapter(DealstageMainActivity.this,   response1.body().getVillage());
                        textView.setAdapter(adapter);



                        Log.e("autotext12",textView.getText().toString());


                        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Object item = adapterView.getItemAtPosition(i);
                                if (item instanceof Village){
                                    Village village=(Village) item;
                                    textView.setText(village.getVillage_name());
                                    Log.e("autotext",textView.getText().toString());
                                    Log.d("villagezIDDDD",village.getId());

                                    VillageId=village.getId();
                                    setCountMethod(VillageId,Modelname,Paymenttype,Passingtype,DealType,Maker,Model,MFGYear);
                                    adapter.notifyDataSetChanged();

                                }

                            }
                        });


                        textView.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                Log.e("beforeTextChanged", String.valueOf(charSequence));
                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                Log.e("onTextChanged-autotext", String.valueOf(charSequence));

                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                Log.e("afterTextChanged",editable.toString());
                                Log.e("autotext12345",textView.getText().toString());
                                if(textView.getText().toString().equals(""))
                                {
                                    Log.e("ifff--afterTextChanged",VillageId);
                                    VillageId="";
                                    setCountMethod(VillageId,Modelname,Paymenttype,Passingtype,DealType,Maker,Model,MFGYear);
                                    adapter.notifyDataSetChanged();
                                }else{
                                    Log.e("elsee_VillageId",VillageId);
//                                    setCountMethod(VillageId);
                                }
//                                adapter.notifyDataSetChanged();

                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<DataVillageeModel123> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong... Please try again after sometime! \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

//    public void
//    setCountMethod(String VillageId1,String Modelname1,String Paymenttype1,String PassingType1,String DealType1,String Maker1,String Model1, String MFGYear1) {
    public void setCountMethod(String VillageId1, String Modelname1,String Paymenttype1,String PassingType1,String DealType1,String maker,String model,String mfgyear) {
        Modelname=Modelname1;
        Paymenttype=Paymenttype1;
        Passingtype=PassingType1;
        DealType=DealType1;
        Maker=maker;
        Model=model;
        MFGYear=mfgyear;
        SharedPreferences sharedPreferences = getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
        String selectemp = sharedPreferences.getString("Slected_EMPID","");
        String selectemp1 = sharedPreferences.getString("Slected_EMPID1","");

       /* if (Modelname1==null){
            Model=model;
        }else{
            Model=Modelname1;
        }*/

//        Log.e("emp_id:-",emp_id+"\n,catId_eid:-"+catId_eid+"\n,VillageId:-"+VillageId);

        WebService.getClient().deal_setCount_web(emp_id,selectemp1,catId_eid,VillageId1,Modelname,Paymenttype,Passingtype,DealType,Maker,Model,MFGYear).enqueue(new Callback<deal_set_count_model>() {
            @Override
            public void onResponse(Call<deal_set_count_model> call, Response<deal_set_count_model> response) {

                deal_2meetingInquiry_count.setText(response.body().getSecond_metting());
                deal_nextactivityplanInquiry_count.setText(response.body().getNext_activity());
                deal_makeandoffInquiry_count.setText(response.body().getMake_an_Offer());
                deal_dropInquiry_count.setText(response.body().getDrop_inq());
                deal_sell_lost_Inquiry_count.setText(response.body().getSelllost());
                deal_deliveryInquiry_count.setText(response.body().getDelivry());
                deal_bookingInquiry_count.setText(response.body().getBooking());
                deal_coldInquiry_count.setText(response.body().getCold());
                deal_warmInquiry_count.setText(response.body().getWarm());
                deal_overDue_count.setText(response.body().getOverdue());
                deal_firstmeeting_count.setText(response.body().getFirst_meeting());
                deal_hotInquiry_count.setText(response.body().getHot());
                deal_notAttend_count.setText(response.body().getNot_attand());
                deal_negotiation_count.setText(response.body().getNegotiation());
                deal_followup_count2.setText(response.body().getFollow_up());
                deal_DealerMeeting_count.setText(response.body().getDeller_meeting());
                deal_followup_count.setText(response.body().getFollow_first());
                deal_followup_count3.setText(response.body().getFollow_third());


                WebService.getClient().deal_inputmiss_web(emp_id,emp_id,catId_eid).enqueue(new Callback<Deal_notattend_model>() {
                    @Override
                    public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                        if (0 == response.body().getCat().size()){
                            Utils.showErrorToast(DealstageMainActivity.this,"No data found");
                            Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-10");

                        }
                        else {
                            catShowRCGVS = response.body().getCat();
                            Log.d("missingList==>>before", String.valueOf(catShowRCGVS)+"\nlenght catShowRCGVS :"+catShowRCGVS.size());

                            for (int i=0;i<catShowRCGVS.size();i++){
                                Catnotattend catnotattend=catShowRCGVS.get(i);
                                if (catnotattend.getCat_name()==null || catnotattend.getCat_name().isEmpty() ||catnotattend.getName()==null  || catnotattend.getName().isEmpty() || catnotattend.getVilage()==null  || catnotattend.getVilage().isEmpty()||
                                        catnotattend.getMoblino()==null || catnotattend.getMoblino().isEmpty() || catnotattend.getWhatsappno()==null  || catnotattend.getWhatsappno().isEmpty()|| catnotattend.getModel_name()==null  || catnotattend.getModel_name().equals("")||
                                        catnotattend.getSor_of_inq()==null  || catnotattend.getSor_of_inq().isEmpty() || catnotattend.getPassing_type()==null  || catnotattend.getPassing_type().isEmpty() ||catnotattend.getPayment_type()==null || catnotattend.getPayment_type().isEmpty()||catnotattend.getDeal_type()==null  || catnotattend.getDeal_type().isEmpty()){
                                    catsWithMissingParams.add(catnotattend);
                                }
                            }

                            Log.d("missingList==>>after", String.valueOf(catShowRCGVS)+"\nlenght catShowRCGVS :"+catShowRCGVS.size());

                            deal_input_miss_Inquiry_count.setText(String.valueOf(catsWithMissingParams.size()));

                        }
                    }

                    @Override
                    public void onFailure(Call<Deal_notattend_model> call, Throwable t) {

                        Utils.showErrorToast(DealstageMainActivity.this,t.getMessage());
                    }
                });


                if (dialog1!=null){
                    VillageId=VillageId1;
                    dialog1.dismiss();

                }

                if (dialog!=null){
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<deal_set_count_model> call, Throwable t) {
                Utils.showErrorToast(DealstageMainActivity.this,t.getMessage());
            }
        });
    }


}