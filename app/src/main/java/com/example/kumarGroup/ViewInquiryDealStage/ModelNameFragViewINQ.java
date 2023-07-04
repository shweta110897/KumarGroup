package com.example.kumarGroup.ViewInquiryDealStage;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kumarGroup.ModelNameProductModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelNameFragViewINQ extends Fragment {

    Dialog dialog;
    Spinner sp_modelnamelist ;
    Button btn_submit;
    List<String> modelname_list;


    String catId_list,catID,ProductName,String_modelget;

    public ModelNameFragViewINQ(Dialog dialog, List<String> modelname_list) {
        this.dialog=dialog;
        this.modelname_list=modelname_list;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sp_modelnamelist =  view.findViewById(R.id.sp_modelnametype);
        btn_submit =  view.findViewById(R.id.btn_submit);

        modelname_list = new ArrayList<>();

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

                        Log.d("ProductS", "onResponse: "+response.body().getData().size());

                        //   Log.d("MProduct", ModelName.toString());

                        ArrayAdapter adapter2 = new ArrayAdapter(getContext(),
                                android.R.layout.simple_spinner_dropdown_item, modelname_list);
                        sp_modelnamelist.setAdapter(adapter2);

                        sp_modelnamelist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                                model_name = ModelName.get(position);
//                                model_id = ModelID.get(position);

                                if ("Select Model".equals(modelname_list.get(i))){
                                    String_modelget = "";
                                }
                                else {
                                    String_modelget = modelname_list.get(i);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<ModelNameProductModel> call, @NotNull Throwable t) {

            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("String_modelget",String_modelget);
                ((DealViewMainActivity) getActivity()). setCountMethod("", String_modelget,"","","","","","");
//                dialog.dismiss();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modelname_list, container, false);
    }
}
