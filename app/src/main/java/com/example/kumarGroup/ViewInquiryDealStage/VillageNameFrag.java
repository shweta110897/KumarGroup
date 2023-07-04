package com.example.kumarGroup.ViewInquiryDealStage;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kumarGroup.DataVillageeModel123;
import com.example.kumarGroup.DealstageAK.DealstageMainActivity;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ViewInquiry.AutoCmpleteAdapter;
import com.example.kumarGroup.Village;
import com.example.kumarGroup.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VillageNameFrag extends Fragment {

    Dialog dialog;
    ImageView deal_common_search;
    AutoCompleteTextView AutosearchView;


    String villagname,VillageId;

    public VillageNameFrag(Dialog dialog) {
        this.dialog=dialog;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        deal_common_search =  view.findViewById(R.id.deal_common_search);
        AutosearchView =  view.findViewById(R.id.AutosearchView);

        getVillageList( this.dialog);


    }

    private void getVillageList(Dialog dialog1) {
        WebService.getClient().getVillageList123().enqueue(new Callback<DataVillageeModel123>() {
            @Override
            public void onResponse(Call<DataVillageeModel123> call, @NonNull Response<DataVillageeModel123> response1) {
                assert response1.body() != null;
                Log.d("Village_Name123", String.valueOf(response1.body().getVillage()));
                if (response1.isSuccessful()) {
                    if (response1.body() != null) {

                        Log.d("DealstageActivity", "onResponse--villagelist: ---checkingggg");

                        AutoCmpleteAdapter adapter = new AutoCmpleteAdapter((Activity) getContext(),   response1.body().getVillage());
                        AutosearchView.setAdapter(adapter);



                        Log.e("autotext12",AutosearchView.getText().toString());


                        AutosearchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Object item = adapterView.getItemAtPosition(i);
                                if (item instanceof Village){
                                    Village village=(Village) item;
                                    AutosearchView.setText(village.getVillage_name());

                                    villagname=village.getVillage_name();
                                    Log.e("autotext",AutosearchView.getText().toString());
                                    Log.d("villagezIDDDD",village.getId());

                                    VillageId=village.getId();

//                                    setCountMethod(VillageId);
                                    adapter.notifyDataSetChanged();

                                }

                            }
                        });


                        deal_common_search.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                DealstageMainActivity.setData();
                                ((DealstageMainActivity) getActivity()). setCountMethod(VillageId, "","","","","","","");
//                                dialog1.dismiss();
                            }
                        });



                    }
                }
            }

            @Override
            public void onFailure(Call<DataVillageeModel123> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong... Please try again after sometime! \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_villagename_list, container, false);
    }
}
