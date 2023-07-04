package com.example.kumarGroup.DealstageAK;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.kumarGroup.Common_Search.CommonSearchActivity;
import com.example.kumarGroup.Common_Search.CommonSearchActivity1;
import com.example.kumarGroup.ModelNameProductModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewInquiryDealStage.DealTypeFragViewINQ;
import com.example.kumarGroup.ViewInquiryDealStage.DeliveryButtonActivityINQ;
import com.example.kumarGroup.ViewInquiryDealStage.ModelFilterFragViewINQ;
import com.example.kumarGroup.ViewInquiryDealStage.ModelNameFragViewINQ;
import com.example.kumarGroup.ViewInquiryDealStage.VillageNameFragViewINQ;
import com.example.kumarGroup.ViewInquiryDealStage.passingTypeFragViewINQ;
import com.example.kumarGroup.ViewInquiryDealStage.paymentTypeFragViewINQ;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.deal_set_count_model;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FinalINQ extends Fragment {

    String emp_id,emp_id1,catId_eid,catId_list,VillageId="",ModelName="",ProductName,catID;
    SharedPreferences sp;

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    Dialog dialog,dialog1;

    TextView deal_bookingInquiry,deal_bookingInquiry_count
            ,deal_deliveryInquiry,deal_deliveryInquiry_count
            ,deal_sell_lost_Inquiry,deal_sell_lost_Inquiry_count,
            deal_dropInquiry,deal_dropInquiry_count,
            deal_common_search,deal_filter,deal_filtercommon_search,clear_filter;

    List<String> modelname_list;
    private BookingFragmentAdapter adapter;
    private FilterFragmentAdapter adapter1;

    String Maker,Model,MFGYear;

    String Passingtype="";

    String DealType="";

    String Paymenttype="";
    String[] PaymentType_list={"Select PaymentType","Loan", "Cash"};

    public FinalINQ() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_final_inq, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences2 = getContext().getSharedPreferences("dealstage3cateid", MODE_PRIVATE);
        catId_eid = sharedPreferences2.getString("catId_eid","");
        catId_list = sharedPreferences2.getString("catId_list","");


        SharedPreferences sharedPreferences =  getContext().getSharedPreferences("catid",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id",catId_eid);
        editor.apply();
        //Toast.makeText(getContext(), "catid "+catId_eid, Toast.LENGTH_SHORT).show();
        modelname_list = new ArrayList<>();

        getModelList();

        deal_common_search = view.findViewById(R.id.deal_common_search);
        deal_filter = view.findViewById(R.id.deal_filter);
        deal_filtercommon_search = view.findViewById(R.id.deal_filtercommon_search);
        clear_filter = view.findViewById(R.id.clear_filter);

        deal_dropInquiry_count = view.findViewById(R.id.deal_dropInquiry_count);
        deal_sell_lost_Inquiry_count = view.findViewById(R.id.deal_sell_lost_Inquiry_count);
        deal_deliveryInquiry_count = view.findViewById(R.id.deal_deliveryInquiry_count);
        deal_bookingInquiry_count = view.findViewById(R.id.deal_bookingInquiry_count);


        deal_bookingInquiry = view.findViewById(R.id.deal_bookingInquiry);
        deal_deliveryInquiry = view.findViewById(R.id.deal_deliveryInquiry);
        deal_sell_lost_Inquiry = view.findViewById(R.id.deal_sell_lost_Inquiry);
        deal_dropInquiry = view.findViewById(R.id.deal_dropInquiry);


        sp = getContext().getSharedPreferences("Login", MODE_PRIVATE);
        emp_id = sp.getString("emp_id", "");

        setCountMethod(VillageId,ModelName,Paymenttype,Passingtype,DealType,Maker,Model,MFGYear);

        clear_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCountMethod("","","","","","","","");
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
                deal_filter.setVisibility(View.VISIBLE);
                deal_filtercommon_search.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getContext(), CommonSearchActivity1.class);
                intent.putExtra("VillageId",VillageId);
                intent.putExtra("FilterText",Passingtype);
                startActivity(intent);
            }
        });

        deal_bookingInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealstageRecyclerActivity.deal_bookingInquiry_flag = true;
                startActivity(new Intent(getContext(),DealstageRecyclerActivity.class)
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
                // DealstageRecyclerActivity.deal_deliveryInquiry_flag = true;
                startActivity(new Intent(getContext(), DeliveryButtonActivityINQ.class).putExtra("VillageId",VillageId)
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
                DealstageRecyclerActivity.deal_sell_lost_Inquiry_flag = true;
                startActivity(new Intent(getContext(),DealstageRecyclerActivity.class)
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
                DealstageRecyclerActivity.deal_dropInquiry_flag = true;
                startActivity(new Intent(getContext(),DealstageRecyclerActivity.class)
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

        SharedPreferences sharedPreferences2 = getContext().getSharedPreferences("catid",MODE_PRIVATE);
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

        dialog1 = new Dialog(getContext());
        dialog1.setContentView(R.layout.filter_payment);/*filter_payment*/


        TabLayout tabLayout = (TabLayout) dialog1.findViewById(R.id.dr_booking_TabLayout);
        ViewPager2 viewPager = (ViewPager2) dialog1.findViewById(R.id.user_booking_ViewPager);


        FragmentManager fm =getParentFragmentManager();
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
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.filter_payment);/*filter_payment*/


        tabLayout = (TabLayout) dialog.findViewById(R.id.dr_booking_TabLayout);
        viewPager2 = (ViewPager2) dialog.findViewById(R.id.user_booking_ViewPager);


//        setupViewPager(viewPager2);
        FragmentManager fm =getParentFragmentManager();
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


        ArrayAdapter adapterPayment = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, PaymentType_list);
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
                   /* Intent intent = new Intent(getContext(), CommonSearchActivity.class);
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
                Intent intent = new Intent(getContext(), CommonSearchActivity.class);
                intent.putExtra("Paymenttype",Paymenttype);
                startActivity(intent);
            }
        });

        dialog.show();

    }

    public class BookingFragmentAdapter extends FragmentStateAdapter {
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
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
        String selectemp = sharedPreferences.getString("Slected_EMPID","");
        String selectemp1 = sharedPreferences.getString("Slected_EMPID1","");

      /*  if (modelName==null){
            Model=model;
        }else{
            Model=modelName;
        }*/

        WebService.getClient().deal_setCount_web(emp_id,selectemp1,catId_eid,villageId,ModelName,Paymenttype,Passingtype,DealType,Maker,Model,MFGYear).enqueue(new Callback<deal_set_count_model>() {

            @Override
            public void onResponse(Call<deal_set_count_model> call, Response<deal_set_count_model> response) {


                deal_dropInquiry_count.setText(response.body().getDrop_inq());
                deal_sell_lost_Inquiry_count.setText(response.body().getSelllost());
                deal_deliveryInquiry_count.setText(response.body().getDelivry());
                deal_bookingInquiry_count.setText(response.body().getBooking());

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
                Utils.showErrorToast(getContext(),t.getMessage());
            }
        });
    }


    public class FilterFragmentAdapter extends FragmentStateAdapter {


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