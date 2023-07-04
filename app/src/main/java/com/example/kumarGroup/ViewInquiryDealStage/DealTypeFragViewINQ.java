package com.example.kumarGroup.ViewInquiryDealStage;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.kumarGroup.R;


public class DealTypeFragViewINQ extends Fragment {
    Spinner sp_dealtype ;
    Button btn_submit ;

    String Dealtype1;
    String[] DealType_list={"Select Deal Type","Exchange", "Fresh"};
    Dialog dialog;

    public DealTypeFragViewINQ(Dialog dialog) {
        this.dialog=dialog;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deal_type_frag_view_inq, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sp_dealtype =  view.findViewById(R.id.sp_dealtype);
        btn_submit =  view.findViewById(R.id.btn_submit);


        ArrayAdapter adapterPassing = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, DealType_list);
        sp_dealtype.setAdapter(adapterPassing);

        sp_dealtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( DealType_list[position]=="Select Deal Type"){
                    Dealtype1 = "";
                }
                else{
                    Dealtype1 = DealType_list[position];
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

                ((DealViewMainActivity) getActivity()). setCountMethod("","","","",Dealtype1,"","","");
                dialog.dismiss();
               /* Intent intent = new Intent(getContext(), CommonSearchActivity.class);
                intent.putExtra("passing_type",Passingtype);
                startActivity(intent);
                dialog.dismiss();*/
            }
        });

    }
}