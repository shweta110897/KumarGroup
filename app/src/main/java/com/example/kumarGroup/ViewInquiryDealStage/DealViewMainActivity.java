package com.example.kumarGroup.ViewInquiryDealStage;

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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.Catnotattend;
import com.example.kumarGroup.Common_Search.CommonSearchActivity1;
import com.example.kumarGroup.DataVillageeModel123;
import com.example.kumarGroup.ModelNameProductModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Common_Search.CommonSearchActivity;
import com.example.kumarGroup.DealstageAK.DeliveryButtonActivity;
import com.example.kumarGroup.FeedbackCall.FeedbackCallActivity;

import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewInquiry.AutoCmpleteAdapter;
import com.example.kumarGroup.Village;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.Deal_notattend_model;
import com.example.kumarGroup.deal_set_count_model;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DealViewMainActivity extends AppCompatActivity {

    //View InquiryPaymentType

    String emp_id,emp_id1,catId_eid,catId_list,VillageId="",ModelName="",ProductName,catID,villageIdFrag;
    SharedPreferences sp;

    AutoCompleteTextView textView;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    static Dialog dialog;
    Dialog dialog1;

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
            ,deal_2meetingInquiry,deal_2meetingInquiry_count,
            deal_NegotiationandfinalInquiry, deal_negotiation_count,
            deal_DealerMeeting_Inquiry,deal_DealerMeeting_count,
            deal_followup_Inquiry,deal_followup_count,
            deal_followup_Inquiry2,deal_followup_count2,
            deal_followup_Inquiry3,deal_followup_count3,
    deal_perfomance,deal_perfomancecount,deal_feedback_call,deal_feedbackcall_count,
            deal_input_miss_Inquiry,deal_input_miss_Inquiry_count,
            deal_common_search,deal_filter,deal_filtercommon_search,clear_filter,deal_more;

    static List<String> modelname_list;
    private BookingFragmentAdapter adapter;
    private FilterFragmentAdapter adapter1;

    String Maker,Model,MFGYear;

    String Passingtype="";
    String[] PassingType_list={"Select PassingType","Agriculture", "Commercial"};

    String DealType="";
    String[] DealType_list={"Select Deal Type","Exchange", "Fresh"};

    String Paymenttype="";
    String[] PaymentType_list={"Select PaymentType","Loan", "Cash"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_view_main);


        getSupportActionBar().setTitle("Deal Stage View Inquiry");
        //  catId_eid=getIntent().getStringExtra("catId_eid");

        SharedPreferences sharedPreferences2 = getSharedPreferences("dealstage3cateid", Context.MODE_PRIVATE);
        catId_eid = sharedPreferences2.getString("catId_eid","");
        catId_list = sharedPreferences2.getString("catId_list","");

        //Toast.makeText(DealViewMainActivity.this, "catid "+catId_eid, Toast.LENGTH_SHORT).show();
        textView =findViewById(R.id.AutosearchView);
        modelname_list = new ArrayList<>();

        getModelList();


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
        deal_feedbackcall_count = findViewById(R.id.deal_feedbackcall_count);
        deal_common_search = findViewById(R.id.deal_common_search);
        deal_filter = findViewById(R.id.deal_filter);
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
        deal_feedback_call = findViewById(R.id.deal_feedback_call);
        deal_input_miss_Inquiry = findViewById(R.id.deal_input_miss_Inquiry);

        sp = getSharedPreferences("Login", MODE_PRIVATE);

        emp_id1 = sp.getString("emp_id", "");
        SharedPreferences sharedPreferencesS = getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
        emp_id = sharedPreferencesS.getString("Slected_EMPID","");

        setCountMethod(VillageId,ModelName,Paymenttype,Passingtype,DealType,Maker,Model,MFGYear);
        getVillageList();

        clear_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCountMethod("","","","","","","","");
            }
        });

        deal_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DealViewMainActivity.this, DealViewItemMenu.class);
                startActivity(intent);
            }
        });
        deal_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VillageId="";
                ModelName="";

                showDialogModelVillage();

            }
        });


        deal_filtercommon_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VillageId="";
                ModelName="";
                showDialogPayment();

            }
        });

        deal_common_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setVisibility(View.GONE);
                deal_filter.setVisibility(View.VISIBLE);
                deal_filtercommon_search.setVisibility(View.VISIBLE);
                Intent intent = new Intent(DealViewMainActivity.this, CommonSearchActivity1.class);
                intent.putExtra("VillageId",VillageId);
                intent.putExtra("FilterText",Passingtype);
                startActivity(intent);
            }
        });




        deal_feedback_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DealViewMainActivity.this, FeedbackCallActivity.class);
                intent.putExtra("VillageId",VillageId);
                intent.putExtra("model",ModelName);
                intent.putExtra("Paymenttype",Paymenttype);
                intent.putExtra("Passingtype",Passingtype);
                intent.putExtra("DealType",DealType);
                intent.putExtra("Maker",Maker);
                intent.putExtra("Model",Model);
                intent.putExtra("MFGYear",MFGYear);
                startActivity(intent);
                finish();
            }
        });

        deal_perfomance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeliveryButtonActivity.perfomanceFlag_INQ = true;
                startActivity(new Intent(DealViewMainActivity.this,DeliveryButtonActivity.class)
                        .putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });

        deal_followup_Inquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivityViewINQ1.deal_followup_Inquiry = true;
                startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                        .putExtra("actionbar","Follow up").putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });

        deal_followup_Inquiry2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivityViewINQ1.deal_followup_Inquiry2 = true;
                startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                        .putExtra("actionbar","Follow up2").putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });

        deal_followup_Inquiry3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivityViewINQ1.deal_followup_Inquiry3 = true;
                startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                        .putExtra("actionbar","Follow up3").putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });
        deal_DealerMeeting_Inquiry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DealstageRecyclerActivityViewINQ1.deal_dealer_Inquiry = true;
                    startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                            .putExtra("actionbar","Dealer Meeting").putExtra("VillageId",VillageId)
                            .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                            .putExtra("DealType",DealType)
                            .putExtra("Maker",Maker)
                            .putExtra("Model",Model)
                            .putExtra("MFGYear",MFGYear)
                            .putExtra("Passingtype",Passingtype));
                }
            });

        deal_NegotiationandfinalInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivityViewINQ1.deal_NegotiationandfinalInquiry_flag = true;
                startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                        .putExtra("actionbar","Negotiation And Finalized").putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });

        deal_notAttend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivityViewINQ1.notattend_flag = true;
                startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                        .putExtra("actionbar","Not attend").putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });

        deal_hotInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivityViewINQ1.hotInquiry_flag = true;
                startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                        .putExtra("actionbar","Hot Inquire").putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });

        deal_input_miss_Inquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivityViewINQ1.deal_input_miss_Inquiry_flag = true;
                startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                        .putExtra("actionbar","Hot Inquire").putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });

        deal_makeandoffInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivityViewINQ1.hotInquiry_flag = true;
                startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                        .putExtra("actionbar","Make An Offer").putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });

        deal_firstmeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivityViewINQ1.firstmeeting_flag = true;
                startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                        .putExtra("actionbar","first meeting").putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });

        deal_overDue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivityViewINQ1.deal_overDue_flag = true;
                startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                        .putExtra("actionbar","over Due").putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });

        deal_warmInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivityViewINQ1.deal_warmInquiry_flag = true;
                startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                        .putExtra("actionbar","Warm Inquire").putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });

        deal_nextactivityplanInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivityViewINQ1.deal_nextactivityplanInquiry_flag = true;
                startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                        .putExtra("actionbar","Next Activity Plan").putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });

        deal_coldInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivityViewINQ1.deal_coldInquiry_flag = true;
                startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                        .putExtra("actionbar","Cold Inquire").putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });

        deal_2meetingInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivityViewINQ1.deal_coldInquiry_flag = true;
                startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                        .putExtra("actionbar","Second meeting").putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });

        deal_bookingInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivityViewINQ1.deal_bookingInquiry_flag = true;
                startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                        .putExtra("actionbar","booking").putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });

        deal_deliveryInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // DealstageRecyclerActivityViewINQ1.deal_deliveryInquiry_flag = true;
                startActivity(new Intent(DealViewMainActivity.this, DeliveryButtonActivityINQ.class).putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
                 //       .putExtra("actionbar","Delivery"));
            }
        });

        deal_sell_lost_Inquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivityViewINQ1.deal_sell_lost_Inquiry_flag = true;
                startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                        .putExtra("actionbar","sell lost").putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });

        deal_dropInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivityViewINQ1.deal_dropInquiry_flag = true;
                startActivity(new Intent(DealViewMainActivity.this,DealstageRecyclerActivityViewINQ1.class)
                        .putExtra("actionbar","drop").putExtra("VillageId",VillageId)
                        .putExtra("model",ModelName).putExtra("Paymenttype",Paymenttype)
                        .putExtra("DealType",DealType)
                        .putExtra("Maker",Maker)
                        .putExtra("Model",Model)
                        .putExtra("MFGYear",MFGYear)
                        .putExtra("Passingtype",Passingtype));
            }
        });
    }



    private List<String> getModelList() {

        SharedPreferences sharedPreferences2 = getSharedPreferences("catid",MODE_PRIVATE);
        catID = sharedPreferences2.getString("id","");
        catId_list = sharedPreferences2.getString("model_name","");


        if (catId_list!=null || !catId_list.equals("")){
//            ProductName=catId_list;
            if (catId_list.contains("Rotavetor")){
                ProductName="Implement";
            }else if (catId_list.contains("Old Tractor")){
                ProductName="Old Tractor";
            }else if (catId_list.contains("New Tractor")){
                ProductName="New Tractor";
            }else if (catId_list.contains("TIGER Inquiry")){
                ProductName="New Tractor";
            }else {
                ProductName="";
            }

            Log.e("ProductName",ProductName);
        }

        WebService.getClient().getModelName(ProductName).enqueue(new Callback<ModelNameProductModel>() {
            @Override
            public void onResponse(@NotNull Call<ModelNameProductModel> call, @NotNull Response<ModelNameProductModel> response)
            {
                if(response.isSuccessful()) {
                    if (response.body() != null) {
                        modelname_list.clear();
//                        ModelID.clear();

                        modelname_list.add("Select Model");
//                        ModelID.add("0");

                        Log.d("product", response.body().toString());

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            modelname_list.add(response.body().getData().get(i).getModel_name());
//                            ModelID.add(response.body().getData().get(i).getId());
                        }
                        Log.d("ProductS123", "onResponse: "+modelname_list);
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<ModelNameProductModel> call, @NotNull Throwable t) {

            }

        });
        Log.d("ProductS12344", "onResponse: "+modelname_list);
        return modelname_list;
    }

    private void showDialogModelVillage() {

        Log.d("testtttt","openFilterdialog");

        dialog1 = new Dialog(DealViewMainActivity.this);
        dialog1.setContentView(R.layout.filter_payment);/*filter_payment*/


        TabLayout tabLayout = (TabLayout) dialog1.findViewById(R.id.dr_booking_TabLayout);
        ViewPager2 viewPager = (ViewPager2) dialog1.findViewById(R.id.user_booking_ViewPager);


        FragmentManager fm =getSupportFragmentManager();
        adapter1 = new FilterFragmentAdapter(fm,getLifecycle(),dialog1);
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

    private void showDialogPayment() {
        dialog = new Dialog(DealViewMainActivity.this);
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
//        modelname_list = new ArrayList<>();


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
                   /* Intent intent = new Intent(DealViewMainActivity.this, CommonSearchActivity.class);
                    intent.putExtra("Paymenttype",Paymenttype);
                    startActivity(intent);*/
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
                  Intent intent = new Intent(DealViewMainActivity.this, CommonSearchActivity.class);
                    intent.putExtra("Paymenttype",Paymenttype);
                    startActivity(intent);
            }
        });

        dialog.show();

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
                fragment = new paymentTypeFragViewINQ(dialog);
            }
            else if (position == 1)
            {
                fragment = new passingTypeFragViewINQ(dialog);
            }
            else if (position == 2)
            {
                fragment = new DealTypeFragViewINQ(dialog);
            }
            return fragment;

        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }

    public void setCountMethod(String villageId, String modelName,String Paymenttype1,String PassingType1,String DealType1,String maker,String model,String mfgyear) {
        ModelName=modelName;
        Paymenttype=Paymenttype1;
        Passingtype=PassingType1;
        DealType=DealType1;
        Maker=maker;
        Model=model;
        MFGYear=mfgyear;
        Log.e("Paymenttype123",Paymenttype1);
        Log.e("DealType",DealType);
        SharedPreferences sharedPreferences = getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
        String selectemp = sharedPreferences.getString("Slected_EMPID","");
        String selectemp1 = sharedPreferences.getString("Slected_EMPID1","");

       /* if (modelName==null){
            Model=model;
        }else{
            Model=modelName;
        }
*/
        WebService.getClient().deal_setCount_web(emp_id,selectemp1,catId_eid,villageId,ModelName,Paymenttype,Passingtype,DealType,Maker,Model,MFGYear).enqueue(new Callback<deal_set_count_model>() {
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
                deal_DealerMeeting_count.setText(response.body().getDeller_meeting());
                deal_followup_count2.setText(response.body().getFollow_up());
                deal_followup_count.setText(response.body().getFollow_first());
                deal_followup_count3.setText(response.body().getFollow_third());

                WebService.getClient().deal_inputmiss_web(emp_id,emp_id1,catID).enqueue(new Callback<Deal_notattend_model>() {
                    @Override
                    public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                        if (0 == response.body().getCat().size()){
                            Utils.showErrorToast(DealViewMainActivity.this,"No data found");
                            Log.d("Jemin", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-10");

                        }
                        else {
                            catShowRCGVS = response.body().getCat();
                            Log.d("missingList==>>before", String.valueOf(catShowRCGVS)+"\nlenght catShowRCGVS :"+catShowRCGVS.size());

                            for (int i=0;i<catShowRCGVS.size();i++){
                                Catnotattend catnotattend=catShowRCGVS.get(i);
                                if (catnotattend.getCat_name()==null || catnotattend.getCat_name().isEmpty() ||catnotattend.getName()==null  || catnotattend.getName().isEmpty() || catnotattend.getVilage()==null  || catnotattend.getVilage().isEmpty()||
                                        catnotattend.getMoblino()==null || catnotattend.getMoblino().isEmpty() || catnotattend.getWhatsappno()==null  || catnotattend.getWhatsappno().isEmpty()||catnotattend.getModel_name()==null  || catnotattend.getModel_name().equals("")||
                                        catnotattend.getSor_of_inq()==null  || catnotattend.getSor_of_inq().isEmpty() || catnotattend.getPassing_type()==null  || catnotattend.getPassing_type().isEmpty() ||catnotattend.getPayment_type()==null || catnotattend.getPayment_type().isEmpty()||catnotattend.getDeal_type()==null  || catnotattend.getDeal_type().isEmpty()){
                                    catsWithMissingParams.add(catnotattend);
                                }
                            }
                            Log.d("missingList==>>after", String.valueOf(catsWithMissingParams.size()));
                            deal_input_miss_Inquiry_count.setText(String.valueOf(catsWithMissingParams.size()));

                        }
                    }

                    @Override
                    public void onFailure(Call<Deal_notattend_model> call, Throwable t) {

                        Utils.showErrorToast(DealViewMainActivity.this,t.getMessage());
                    }
                });


//                deal_feedbackcall_count.setText(response.body());

                if (dialog1!=null){
                    VillageId=villageId;
                    dialog1.dismiss();

                }

                if (dialog!=null){
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<deal_set_count_model> call, Throwable t) {
                Utils.showErrorToast(DealViewMainActivity.this,t.getMessage());
            }
        });
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

                        AutoCmpleteAdapter adapter = new AutoCmpleteAdapter(DealViewMainActivity.this,   response1.body().getVillage());
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
                                    setCountMethod(VillageId, ModelName,Paymenttype,Passingtype,DealType,Maker,Model,MFGYear);
                                    adapter.notifyDataSetChanged();

                                }

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



    @Override
    protected void onResume() {
        super.onResume();

    }

    public static class FilterFragmentAdapter extends FragmentStateAdapter {


        public FilterFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Dialog dialog) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment1 = null;
            if (position == 0)
            {
                fragment1 = new VillageNameFragViewINQ(dialog);
            }
            else if (position == 1)
            {
                fragment1 = new ModelNameFragViewINQ(dialog,modelname_list);
            }
            else if (position == 2)
            {
                fragment1 = new ModelFilterFragViewINQ(dialog);
            }
            return fragment1;

        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}