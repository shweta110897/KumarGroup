package com.example.kumarGroup.Workshop.General.CompalinFragment;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.BaseFragment;
import com.example.kumarGroup.CatCBDisplay;
import com.example.kumarGroup.ComplainBoxDisplayModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.Workshop.General.ComplainBoxDisplayAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllComplainFragment extends BaseFragment {

    RecyclerView rv_complain;
    String date1,date2;
    List<CatCBDisplay> catShowRCGVS;
    ComplainBoxDisplayAdapter allEntryMonthWeekDayAdapter;

    public AllComplainFragment() {
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
        View view = inflater.inflate(R.layout.fragment_all_complain, container, false);
        date1 = getArguments().getString("date1");
        date2 = getArguments().getString("date2");

        rv_complain=view.findViewById(R.id.rv_complain);
        rv_complain.setHasFixedSize(true);
        rv_complain.setLayoutManager(new LinearLayoutManager(getContext()));
        showDialog("");
        WebService.getClient().getComplainBoxDisplay(date1,date2).enqueue(new Callback<ComplainBoxDisplayModel>() {
            @Override
            public void onResponse(@NotNull Call<ComplainBoxDisplayModel> call,
                                   @NotNull Response<ComplainBoxDisplayModel> response)
            {



              /*  if(response.body().getCat().size() == 0){
                    Utils.showErrorToast(ComplainRegisterActivity.this,"No Data Available");
                }
                else {
                    ComplainBoxDisplayAdapter adapter = new ComplainBoxDisplayAdapter(ComplainRegisterActivity.this,
                            response.body().getCat());
                    rclvComplainBoxDisplay.setAdapter(adapter);
                }*/


                dismissDialog();

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    catShowRCGVS = response.body().getCat();
                    if (catShowRCGVS.size() == 0) {
                        Utils.showErrorToast(getContext(),"No Data Found");

                    } else {
                        allEntryMonthWeekDayAdapter = new ComplainBoxDisplayAdapter(getContext(),
                                catShowRCGVS);
                        rv_complain.setAdapter(allEntryMonthWeekDayAdapter);
                    }
                }
                else {
                    Utils.showNormalToast(getContext(),"Server error please try again later");
                }

            }

            @Override
            public void onFailure(@NotNull Call<ComplainBoxDisplayModel> call, @NotNull Throwable t) {
               dismissDialog();
            }
        });


        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}