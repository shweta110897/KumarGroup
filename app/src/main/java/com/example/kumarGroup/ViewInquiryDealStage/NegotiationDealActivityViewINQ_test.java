package com.example.kumarGroup.ViewInquiryDealStage;

import static com.example.kumarGroup.ViewInquiryDealStage.DealstageRecyclerActivityViewINQ1.deal_NegotiationandfinalInquiry_flag_other;
import static com.example.kumarGroup.ViewInquiryDealStage.DealstageRecyclerActivityViewINQ1.deal_dealer_Inquiry_other;
import static com.example.kumarGroup.ViewInquiryDealStage.DealstageRecyclerActivityViewINQ1.deal_followup_Inquiry2_other;
import static com.example.kumarGroup.ViewInquiryDealStage.DealstageRecyclerActivityViewINQ1.deal_followup_Inquiry3_other;
import static com.example.kumarGroup.ViewInquiryDealStage.DealstageRecyclerActivityViewINQ1.deal_followup_Inquiry_other;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.deal_negoatiation_web;
import com.example.kumarGroup.model_msg;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NegotiationDealActivityViewINQ_test extends AppCompatActivity {

    String[] Negotiation_list={"Negotiation","Reminder", "1st-Follow up","Dealer Meeting"};
    String[] Negotiation_list1={"Negotiation","Reminder", "2nd-Follow up","Dealer Meeting"};
    String[] Negotiation_list2={"Negotiation","Reminder", "3rd-Follow up","Dealer Meeting"};
    String[] Negotiation_list3={"Negotiation","Reminder","Dealer Meeting"};
    Spinner Negotiation_spinner;
    String store_spinnersData;
    String name,autoid,cat_id,sname,mobileNo,Vemp,Whatsappnumber,message;
    Button submit_ne;
    ProgressDialog pro;

    String catID,catId_list;


    List<String> modelname_list = new ArrayList<>();

    String[] Products_List = {"Select Product", "New Tractor","Old Tractor","Implement"};
    List<String> ModelName = new ArrayList<>();
    List<String> ModelID = new ArrayList<>();
    String dealType,ProductName,strModel,model_name,model_id,String_modelget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negotiation_deal_view_inq);

        submit_ne = findViewById(R.id.submit_ne);
        Negotiation_spinner = findViewById(R.id.Negotiation_spinner);
        pro = new ProgressDialog(NegotiationDealActivityViewINQ_test.this);

        SharedPreferences sharedPreferences2 = getSharedPreferences("catid",MODE_PRIVATE);
        catID = sharedPreferences2.getString("id","");
        catId_list = sharedPreferences2.getString("model_name","");

        cat_id=getIntent().getStringExtra("cat_id");
        sname=getIntent().getStringExtra("sname");
        Vemp=getIntent().getStringExtra("vemp");
        mobileNo=getIntent().getStringExtra("mobo");
        autoid=getIntent().getStringExtra("id");
        Whatsappnumber=getIntent().getStringExtra("Whatsappnumber");

        ArrayAdapter adapterFollowUp = null;
        if (deal_NegotiationandfinalInquiry_flag_other || deal_dealer_Inquiry_other){
            adapterFollowUp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Negotiation_list);

        }else if(deal_followup_Inquiry_other){
            adapterFollowUp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Negotiation_list1);

        }else if(deal_followup_Inquiry2_other){
            adapterFollowUp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Negotiation_list2);

        }else if(deal_followup_Inquiry3_other){
            adapterFollowUp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Negotiation_list3);

        }

        Negotiation_spinner.setAdapter(adapterFollowUp);

        Negotiation_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (deal_NegotiationandfinalInquiry_flag_other || deal_dealer_Inquiry_other ) {
                   /* if (Negotiation_list[position] == "Negotiation") {
                        store_spinnersData = "";
                    } else {*/
                    store_spinnersData = Negotiation_list[position];
                    if ("Reminder".equals(store_spinnersData)) {

                        Intent i = new Intent(NegotiationDealActivityViewINQ_test.this, DealFormGeneralActivityViewINQ.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("vemp", Vemp);
                        i.putExtra("cat_id", cat_id);
                        i.putExtra("sname", sname);
                        i.putExtra("mobo", mobileNo);
                        i.putExtra("id", autoid);
                        i.putExtra("nextplan", "Reminder");
                        i.putExtra("cat_name", catId_list);

                        startActivity(i);
                    } else {
                        submit_ne.setVisibility(View.VISIBLE);
                    }/*
                    }*/
                }else if (deal_followup_Inquiry_other) {
                   /* if (Negotiation_list1[position] == "Negotiation") {
                        store_spinnersData = "";
                    } else {*/
                    store_spinnersData = Negotiation_list1[position];
                    if ("Reminder".equals(store_spinnersData)) {

                        Intent i = new Intent(NegotiationDealActivityViewINQ_test.this, DealFormGeneralActivityViewINQ.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("vemp", Vemp);
                        i.putExtra("cat_id", cat_id);
                        i.putExtra("sname", sname);
                        i.putExtra("mobo", mobileNo);
                        i.putExtra("id", autoid);
                        i.putExtra("nextplan", "Reminder");
                        i.putExtra("cat_name", catId_list);

                        startActivity(i);
                    }else {
                        submit_ne.setVisibility(View.VISIBLE);
                    }
                    /*  }*/
                }else if (deal_followup_Inquiry2_other) {
//                    if (Negotiation_list2[position] == "Negotiation") {
                    store_spinnersData = "";
//                    } else {
                    store_spinnersData = Negotiation_list2[position];
                    if ("Reminder".equals(store_spinnersData)) {

                        Intent i = new Intent(NegotiationDealActivityViewINQ_test.this, DealFormGeneralActivityViewINQ.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("vemp", Vemp);
                        i.putExtra("cat_id", cat_id);
                        i.putExtra("sname", sname);
                        i.putExtra("mobo", mobileNo);
                        i.putExtra("id", autoid);
                        i.putExtra("nextplan", "Reminder");
                        i.putExtra("cat_name", catId_list);

                        startActivity(i);
                    } else {
                        submit_ne.setVisibility(View.VISIBLE);
                    }
                    /*  }*/
                }else if (deal_followup_Inquiry3_other) {
//                    if (Negotiation_list3[position] == "Negotiation") {
//                        store_spinnersData = "";
//                    } else {
                    store_spinnersData = Negotiation_list3[position];
                    if ("Reminder".equals(store_spinnersData)) {

                        Intent i = new Intent(NegotiationDealActivityViewINQ_test.this, DealFormGeneralActivityViewINQ1.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("vemp", Vemp);
                        i.putExtra("cat_id", cat_id);
                        i.putExtra("sname", sname);
                        i.putExtra("mobo", mobileNo);
                        i.putExtra("id", autoid);
                        i.putExtra("nextplan", "Reminder");
                        i.putExtra("cat_name", catId_list);

                        startActivity(i);
                    } else {
                        submit_ne.setVisibility(View.VISIBLE);
                    }
                    /*   }*/
                }else{
//                    if (Negotiation_list1[position] == "Negotiation") {
//                        store_spinnersData = "";
//                    } else {
                    store_spinnersData = Negotiation_list1[position];
                    if ("Reminder".equals(store_spinnersData)) {

                        Intent i = new Intent(NegotiationDealActivityViewINQ_test.this, DealFormGeneralActivityViewINQ.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("vemp", Vemp);
                        i.putExtra("cat_id", cat_id);
                        i.putExtra("sname", sname);
                        i.putExtra("mobo", mobileNo);
                        i.putExtra("id", autoid);
                        i.putExtra("nextplan", "Reminder");
                        i.putExtra("cat_name", catId_list);

                        startActivity(i);
                    }else {
                        submit_ne.setVisibility(View.VISIBLE);
                    }
//                    }
                }
                //  TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit_ne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if ("Negotiation".equals(store_spinnersData)){
                    Utils.showErrorToast(NegotiationDealActivityViewINQ_test.this,"Please select");
                    return;
                }*/

                WhatsappMessage1();

                pro.show();
                pro.setCancelable(false);
                pro.setMessage("Please wait ..");

                WebService.getClient().dealstage_negoatioaion_web(autoid,store_spinnersData).enqueue(new Callback<deal_negoatiation_web>() {
                    @Override
                    public void onResponse(Call<deal_negoatiation_web> call, Response<deal_negoatiation_web> response) {
                        pro.dismiss();
                        startActivity(new Intent(NegotiationDealActivityViewINQ_test.this, DealViewMainActivityNegotiation.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<deal_negoatiation_web> call, Throwable t) {
                        pro.dismiss();
                        Utils.showErrorToast(NegotiationDealActivityViewINQ_test.this,t.getMessage());
                    }
                });
            }
        });
    }

    private void WhatsappMessage() {

        dealType="First Metting";

        WebService.getClient().dealstage_msg(dealType).enqueue(new Callback<model_msg>() {
            @Override
            public void onResponse(Call<model_msg> call, Response<model_msg> response) {
                pro.dismiss();


                Log.e("respose",response.body().toString());
                if (response.body().getData().size()==0){
//                    Toast.makeText(DealFormGeneralActivityViewINQ1.this, "No Data Found !", Toast.LENGTH_SHORT).show();
                    WhatsappMessage1();
//
                }else {

                    String message = "प्रिय किसान मित्र।\n" +
                            "\n" +
                            "     आपके बहुमूल्य समय के लिए बहुत-बहुत धन्यवाद, हम भविष्य में आपको संतोषजनक सेवाएं प्रदान करेंगे, और आपके बहुमूल्य सुझावों की प्रतीक्षा रहेगी।\n" +
                            "\n" +
                            "नमस्ते...\n" +
                            "कुमार ऑटोमोबाइल्स (सोनालिका ट्रैक्टर शोरूम)\n" +
                            "\n" +
                            "अधिक जानकारी के लिए संपर्क करें।\n" +
                            "सेल्स MO:- 7500567770\n" +
                            "सर्विस MO:-7505786792";


                    for (int i=0;i<response.body().getData().size();i++){
                        if (response.body().getData().get(i).getVideo_link1() != null) {
                            message += "\n" + response.body().getData().get(i).getVideo_link1()+"\n";
                        }
                        if (response.body().getData().get(i).getVideo_link2() != null) {
                            message += "\n" + response.body().getData().get(i).getVideo_link2()+"\n";
                        }
                        if (response.body().getData().get(i).getVideo_link3() != null) {
                            message += "\n" + response.body().getData().get(i).getVideo_link3()+"\n";
                        }
                        if (response.body().getData().get(i).getVideo_link4() != null) {
                            message += "\n" + response.body().getData().get(i).getVideo_link4()+"\n";
                        }
                        if (response.body().getData().get(i).getVideo_link5() != null) {
                            message += "\n" + response.body().getData().get(i).getVideo_link5()+"\n";
                        }
                    }



                    Log.d("TAG", "onResponse: Whatsapp_message" + message);


                    startActivity(
                            new Intent(Intent.ACTION_VIEW,
                                    Uri.parse(
                                            String.format("https://api.whatsapp.com/send?phone=%s&text=%s", "+91" + Whatsappnumber, message)
                                    )
                            )
                    );
                }

            }

            @Override
            public void onFailure(Call<model_msg> call, Throwable t) {
                pro.dismiss();
                Utils.showErrorToast(NegotiationDealActivityViewINQ_test.this,t.getMessage());

            }
        });

    }


    private void WhatsappMessage1() {

        message = "प्रिय किसान मित्र।\n" +
                "\n" +
                "     आपके बहुमूल्य समय के लिए बहुत-बहुत धन्यवाद, हम भविष्य में आपको संतोषजनक सेवाएं प्रदान करेंगे, और आपके बहुमूल्य सुझावों की प्रतीक्षा रहेगी।\n" +
                "\n" +
                "नमस्ते...\n" +
                "कुमार ऑटोमोबाइल्स (सोनालिका ट्रैक्टर शोरूम)\n" +
                "\n" +
                "अधिक जानकारी के लिए संपर्क करें।\n" +
                "सेल्स MO:- 7500567770\n" +
                "सर्विस MO:-7505786792";

        Log.d("TAG", "WhatsappMessage: Check_whatsapp_Message-6"+Whatsappnumber);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+91"+Whatsappnumber + "&text=" + message));
        startActivity(intent);

//        startActivity(
//                new Intent(Intent.ACTION_VIEW,
//                        Uri.parse(
//                                String.format("https://api.whatsapp.com/send?phone=%s&text=%s", "+91" + Whatsappnumber, message)
//                        )
//                )
//        );

    }
}