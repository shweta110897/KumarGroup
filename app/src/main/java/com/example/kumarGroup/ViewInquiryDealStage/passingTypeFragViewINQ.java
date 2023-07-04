package com.example.kumarGroup.ViewInquiryDealStage;

import android.app.Dialog;
import android.os.Bundle;
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

import com.example.kumarGroup.R;

public class passingTypeFragViewINQ extends Fragment {
    Spinner sp_passingtype ;
    Button btn_submit ;


    String Passingtype;
    String[] PassingType_list={"Select PassingType","Agriculture", "Commercial"};
    Dialog dialog;

    public passingTypeFragViewINQ(Dialog dialog) {
        this.dialog=dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sp_passingtype =  view.findViewById(R.id.sp_paymenttype);
        btn_submit =  view.findViewById(R.id.btn_submit);


        ArrayAdapter adapterPassing = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, PassingType_list);
        sp_passingtype.setAdapter(adapterPassing);

        sp_passingtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( PassingType_list[position]=="Select PassingType"){
                    Passingtype = "";
                }
                else{
                    Passingtype = PassingType_list[position];
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

                ((DealViewMainActivity) getActivity()). setCountMethod("", "","",Passingtype,"","","","");
               /* Intent intent = new Intent(getContext(), CommonSearchActivity.class);
                intent.putExtra("passing_type",Passingtype);
                startActivity(intent);
                dialog.dismiss();*/
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
