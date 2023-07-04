package com.example.kumarGroup.DealstageAK;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.deal_negoatiation_web;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NegotiationDealActivity extends AppCompatActivity {
    String[] Negotiation_list={"Negotiation","Reminder", "2nd-Follow up","Dealer Meeting"};
    Spinner Negotiation_spinner;
    String store_spinnersData;
    String name,autoid,cat_id,sname,mobileNo,Vemp;
    Button submit_ne;
    ProgressDialog pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negotiation_deal);

        submit_ne = findViewById(R.id.submit_ne);
        Negotiation_spinner = findViewById(R.id.Negotiation_spinner);
        pro = new ProgressDialog(NegotiationDealActivity.this);

        cat_id=getIntent().getStringExtra("cat_id");
        sname=getIntent().getStringExtra("sname");
        Vemp=getIntent().getStringExtra("vemp");
        mobileNo=getIntent().getStringExtra("mobo");
        autoid=getIntent().getStringExtra("id");


        ArrayAdapter adapterFollowUp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Negotiation_list);
        Negotiation_spinner.setAdapter(adapterFollowUp);

        Negotiation_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( Negotiation_list[position]=="Negotiation"){
                    store_spinnersData = "";
                }
                else{
                    store_spinnersData = Negotiation_list[position];
                    if ("Reminder".equals(store_spinnersData)){

                        Intent i = new Intent(NegotiationDealActivity.this, DealFormGeneralActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("vemp", Vemp);
                        i.putExtra("cat_id", cat_id);
                        i.putExtra("sname", sname);
                        i.putExtra("mobo",mobileNo);
                        i.putExtra("id", autoid);
                        i.putExtra("nextplan", "Reminder");

                        startActivity(i);
                    }
                    else {
                        submit_ne.setVisibility(View.VISIBLE);
                    }
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
                if ("Negotiation".equals(store_spinnersData)){
                    Utils.showErrorToast(NegotiationDealActivity.this,"Please select");
                    return;
                }

                pro.show();
                pro.setCancelable(false);
                pro.setMessage("Please wait ..");

                WebService.getClient().dealstage_negoatioaion_web(autoid,store_spinnersData).enqueue(new Callback<deal_negoatiation_web>() {
                    @Override
                    public void onResponse(Call<deal_negoatiation_web> call, Response<deal_negoatiation_web> response) {
                        pro.dismiss();
                        startActivity(new Intent(NegotiationDealActivity.this,DealstageMainActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<deal_negoatiation_web> call, Throwable t) {
                        pro.dismiss();
                        Utils.showErrorToast(NegotiationDealActivity.this,t.getMessage());
                    }
                });
            }
        });
    }
}