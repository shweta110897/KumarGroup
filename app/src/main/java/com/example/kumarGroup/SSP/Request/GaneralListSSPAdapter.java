package com.example.kumarGroup.SSP.Request;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatReqGaneralSSP;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.ssp_delivery_buttion_model;
import com.example.kumarGroup.ssp_zeroday_id_send_model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GaneralListSSPAdapter extends RecyclerView.Adapter<GaneralListSSPAdapter.ViewHolder>
{

    Context context;
    List<CatReqGaneralSSP>  catReqGaneralSSPS;
    List<String> idlist = new ArrayList<>();
    public GaneralListSSPAdapter(Context context, List<CatReqGaneralSSP> catReqGaneralSSPS) {
        this.context = context;
        this.catReqGaneralSSPS = catReqGaneralSSPS;
    }

    @NonNull
    @Override
    public GaneralListSSPAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.ganeral_list_raw_ssp, parent, false);
        GaneralListSSPAdapter.ViewHolder viewHolder = new GaneralListSSPAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GaneralListSSPAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_Ganeral_prName.setText("Product Name: "+catReqGaneralSSPS.get(position).getProduct_name());
        holder.tv_Ganeral_Vendor.setText("Vendor Name: "+catReqGaneralSSPS.get(position).getVendor());
        holder.tv_Ganeral_CuName.setText("Customer Name: "+catReqGaneralSSPS.get(position).getCustomer_name());
        holder.tv_Ganeral_Mobile.setText("Mobile Number: "+catReqGaneralSSPS.get(position).getMobile());
        holder.tv_Ganeral_Village.setText("Village Name: "+catReqGaneralSSPS.get(position).getVillage());
        holder.tv_added_date.setText("Added date: "+catReqGaneralSSPS.get(position).getAdd_date());
        holder.tv_Ganeral_Status.setText("Status: "+catReqGaneralSSPS.get(position).getStatus());

        holder.btn_gen_accept.setVisibility(View.GONE);
        holder.btn_gen_cancel.setVisibility(View.GONE);

        if ("0".equals(catReqGaneralSSPS.get(position).getCount())){

            idlist.add(catReqGaneralSSPS.get(position).getId());

            if (catReqGaneralSSPS.size() == position+1){

                String zeroid = TextUtils.join(",", idlist);
                WebService.getClient().ssp_zeroday_send_web(zeroid).enqueue(new Callback<ssp_zeroday_id_send_model>() {
                    @Override
                    public void onResponse(Call<ssp_zeroday_id_send_model> call, Response<ssp_zeroday_id_send_model> response) {
                       // Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "zeroid: "+zeroid);
                    }

                    @Override
                    public void onFailure(Call<ssp_zeroday_id_send_model> call, Throwable t) {

                    }
                });
            }

        }

        if ("Accept".equals(catReqGaneralSSPS.get(position).getStatus())){
            holder.btn_gen_delevery.setVisibility(View.VISIBLE);
            holder.tv_Ganeral_count.setVisibility(View.VISIBLE);
            holder.tv_Ganeral_count.setText("Days : "+catReqGaneralSSPS.get(position).getCount());
        }

        if ("Delivery In Progress".equals(catReqGaneralSSPS.get(position).getStatus())){
            holder.btn_gen_delevery.setVisibility(View.GONE);
        }

        holder.btn_gen_delevery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebService.getClient().get_ssp_delivery_buttion_web(catReqGaneralSSPS.get(position).getId()).enqueue(new Callback<ssp_delivery_buttion_model>() {
                    @Override
                    public void onResponse(Call<ssp_delivery_buttion_model> call, Response<ssp_delivery_buttion_model> response) {
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context,GanerallistMainActivity.class));
                    }

                    @Override
                    public void onFailure(Call<ssp_delivery_buttion_model> call, Throwable throwable) {

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return catReqGaneralSSPS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout linBookingUploadMain;
        Button btn_gen_accept,btn_gen_cancel,btn_gen_delevery;

        TextView tv_Ganeral_prName,tv_Ganeral_Vendor,tv_Ganeral_CuName,tv_added_date,tv_Ganeral_count,
                tv_Ganeral_Mobile,tv_Ganeral_Village,tv_Ganeral_Status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            this.linBookingUploadMain= itemView.findViewById(R.id.linBookingUploadMain);
            this.btn_gen_accept= itemView.findViewById(R.id.btn_gen_accept);
            this.btn_gen_cancel= itemView.findViewById(R.id.btn_gen_cancel);
            this.tv_Ganeral_prName= itemView.findViewById(R.id.tv_Ganeral_prName);

            this.tv_Ganeral_Vendor= itemView.findViewById(R.id.tv_Ganeral_Vendor);
            this.tv_Ganeral_CuName= itemView.findViewById(R.id.tv_Ganeral_CuName);
            this.tv_Ganeral_Mobile= itemView.findViewById(R.id.tv_Ganeral_Mobile);
            this.tv_Ganeral_Village= itemView.findViewById(R.id.tv_Ganeral_Village);
            this.tv_Ganeral_Status= itemView.findViewById(R.id.tv_Ganeral_Status);
            this.tv_added_date= itemView.findViewById(R.id.tv_added_date);
            this.tv_Ganeral_count= itemView.findViewById(R.id.tv_Ganeral_count);
            this.btn_gen_delevery= itemView.findViewById(R.id.btn_gen_delevery);
        }
    }
}
