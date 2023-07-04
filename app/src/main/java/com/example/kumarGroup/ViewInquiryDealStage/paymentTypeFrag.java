package com.example.kumarGroup.ViewInquiryDealStage;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kumarGroup.DealstageAK.DealstageMainActivity;
import com.example.kumarGroup.R;

import java.util.ArrayList;
import java.util.List;

public class paymentTypeFrag extends Fragment {

    Spinner sp_paymenttype ;
    Button btn_submit;
    List<String> modelname_list;

    Dialog dialog;

    public paymentTypeFrag(Dialog dialog) {
        this.dialog=dialog;
    }

    String Paymenttype;
    String[] PaymentType_list={"Select PaymentType","Loan", "Cash"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sp_paymenttype =  view.findViewById(R.id.sp_paymenttype);
        btn_submit =  view.findViewById(R.id.btn_submit);
        modelname_list = new ArrayList<>();

        ArrayAdapter adapterPayment = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, PaymentType_list);
        sp_paymenttype.setAdapter(adapterPayment);

        sp_paymenttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( PaymentType_list[position]=="Select PaymentType"){
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
               /* Intent intent = new Intent(getContext(), CommonSearchActivity.class);
                intent.putExtra("payment_type",Paymenttype);
                startActivity(intent);
                dialog.dismiss();*/
                Log.e("Paymenttype123",Paymenttype);

                ((DealstageMainActivity) getActivity()). setCountMethod("", "",Paymenttype,"","","","","");
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_list, container, false);
    }
}
