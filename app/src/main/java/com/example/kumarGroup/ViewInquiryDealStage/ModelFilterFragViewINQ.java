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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kumarGroup.DatabankMakeModel;
import com.example.kumarGroup.DatabankModelListModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelFilterFragViewINQ extends Fragment {

    Dialog dialog;
    Spinner sp_make_name, sp_model_name;
    Button btn_submit;
    List<String> modelname_list;
    EditText edt_MfgYear_EP;

    List<String> l_make_name = new ArrayList<>();
    List<String> l_model_list = new ArrayList<>();


    String  makedata = "", modellistdata = "";


    String String_modelget;



    public ModelFilterFragViewINQ(Dialog dialog) {
        this.dialog=dialog;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sp_make_name =  view.findViewById(R.id.sp_make_name);
        sp_model_name =  view.findViewById(R.id.sp_model_name);
        edt_MfgYear_EP =  view.findViewById(R.id.edt_MfgYear_EP);
        btn_submit =  view.findViewById(R.id.btn_submit);

        modelname_list = new ArrayList<>();


        WebService.getClient().getDatabankMake().enqueue(new Callback<DatabankMakeModel>() {
            @Override
            public void onResponse(Call<DatabankMakeModel> call, Response<DatabankMakeModel> response1) {
                Log.d("MakeName", response1.body().toString());
                if (response1.isSuccessful()) {
                    if (response1.body() != null) {
                        l_make_name.clear();
                        l_make_name.add("Select Make");

                        for (int i = 0; i < response1.body().getState().size(); i++)
                        {
                            l_make_name.add(response1.body().getState().get(i).getMake());
                        }

                        ArrayAdapter adapter2 = new ArrayAdapter(getContext(),
                                android.R.layout.simple_spinner_dropdown_item, l_make_name);
                        sp_make_name.setAdapter(adapter2);

                        sp_make_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                makedata = l_make_name.get(position);  //
                                Log.d("ModelList", makedata);

                                if(!makedata.equals("Select Make")) {
                                    WebService.getClient().getDatabankModelList(makedata).enqueue(new Callback<DatabankModelListModel>() {
                                        @Override
                                        public void onResponse(Call<DatabankModelListModel> call, Response<DatabankModelListModel> response2) {
                                            l_model_list.clear();

                                            if (response2.isSuccessful()) {
                                                if (response2.body() != null) {
                                                    l_model_list.add("Select Model");
                                                    int xz = response2.body().getModel().size();
                                                    Log.d("ModelList", "" + xz +"");
                                                    for (int j = 0; j < response2.body().getModel().size();j++)
                                                    {
                                                        l_model_list.add(response2.body().getModel().get(j).getModelName());
                                                        Log.d("ModelList", "onResponse: " + response2.body().getModel().get(j).getModelName());
                                                    }

                                                    ArrayAdapter adapter3 = new ArrayAdapter(getContext(),
                                                            android.R.layout.simple_spinner_dropdown_item, l_model_list);
                                                    sp_model_name.setAdapter(adapter3);


                                                    sp_model_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override
                                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                            modellistdata = l_model_list.get(i);
                                                        }

                                                        @Override
                                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                                        }
                                                    });
                                                }
                                            }

                                        }

                                        @Override
                                        public void onFailure(Call<DatabankModelListModel> call, Throwable t) {
                                            Toast.makeText(getContext(), "Something went wrong... Please try again after sometime! \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    l_model_list.clear();
                                    l_model_list.add("Select Model");
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<DatabankMakeModel> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong... Please try again after sometime! \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (modellistdata.equals("Select Model")){
                    modellistdata="";
                }
                ((DealViewMainActivity) getActivity()). setCountMethod("", String_modelget,"","","",makedata,modellistdata,edt_MfgYear_EP.getText().toString());
//                dialog.dismiss();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modelname_list1, container, false);
    }
}
