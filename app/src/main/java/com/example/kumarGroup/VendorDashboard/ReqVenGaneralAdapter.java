package com.example.kumarGroup.VendorDashboard;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.Accept_butiion_model;
import com.example.kumarGroup.CatVendorReqGaneral;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReqVenGaneralAdapter extends RecyclerView.Adapter<ReqVenGaneralAdapter.ViewHolder>
{
    Context context;
    List<CatVendorReqGaneral>  catVendorReqGanerals;
    SharedPreferences sp1;
    String emp;

    public ReqVenGaneralAdapter(Context context, List<CatVendorReqGaneral> catVendorReqGanerals) {
        this.context = context;
        this.catVendorReqGanerals = catVendorReqGanerals;
    }

    @NonNull
    @Override
    public ReqVenGaneralAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.ganeral_list_raw_ssp, parent, false);
        ReqVenGaneralAdapter.ViewHolder viewHolder = new ReqVenGaneralAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReqVenGaneralAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_Ganeral_prName.setText("Product Name: "+catVendorReqGanerals.get(position).getProduct_name());
        holder.tv_Ganeral_Vendor.setText("Vendor Name: "+catVendorReqGanerals.get(position).getVendor());
        holder.tv_Ganeral_CuName.setText("Customer Name: "+catVendorReqGanerals.get(position).getCustomer_name());
        holder.tv_Ganeral_Mobile.setText("Mobile Number: "+catVendorReqGanerals.get(position).getMobile());
        holder.tv_Ganeral_Village.setText("Village Name: "+catVendorReqGanerals.get(position).getVillage());
        holder.tv_added_date.setText("Added date: "+catVendorReqGanerals.get(position).getAdd_date());
        holder.tv_Ganeral_Status.setText("Status: "+catVendorReqGanerals.get(position).getStatus());

        if ("Pending".equals(catVendorReqGanerals.get(position).getStatus())){
            holder.tv_Ganeral_Mobile.setVisibility(View.GONE);
            holder.btn_gen_accept.setVisibility(View.VISIBLE);
            holder.btn_gen_cancel.setVisibility(View.VISIBLE);
        }
        else if ("Accept".equals(catVendorReqGanerals.get(position).getStatus())){
            holder.tv_Ganeral_Mobile.setVisibility(View.VISIBLE);
            holder.btn_gen_accept.setVisibility(View.GONE);
            holder.btn_gen_cancel.setVisibility(View.GONE);
            holder.tv_Ganeral_Status.setText("Status: "+catVendorReqGanerals.get(position).getStatus());
        }
        else if ("Delivery In Progress".equals(catVendorReqGanerals.get(position).getStatus())){
            holder.btn_gen_accept.setVisibility(View.GONE);
            holder.btn_gen_cancel.setVisibility(View.GONE);
            holder.btn_gen_delevery.setVisibility(View.VISIBLE);
        }

        holder.btn_gen_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ("Cancel In Progress".equals(catVendorReqGanerals.get(position).getStatus()))
                {
                    Toast.makeText(context, "Cancel In Progress", Toast.LENGTH_SHORT).show();
                }
                else {
                    context.startActivity(new Intent(context, CancelButtionActivity.class)
                            .putExtra("id", catVendorReqGanerals.get(position).getId()));
                }
            }
        });

        holder.btn_gen_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog pro = new ProgressDialog(context);
                pro.show();
                pro.setMessage("Please wait..");
                pro.setCancelable(false);

                WebService.getClient().get_accept_buttion_web(catVendorReqGanerals.get(position).getId()).enqueue(new Callback<Accept_butiion_model>() {
                    @Override
                    public void onResponse(Call<Accept_butiion_model> call, Response<Accept_butiion_model> response) {
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        pro.dismiss();

                        context.startActivity(new Intent(context,ReqVenGaneralListActivity.class));

                    }

                    @Override
                    public void onFailure(Call<Accept_butiion_model> call, Throwable throwable) {
                        pro.dismiss();
                    }
                });
            }
        });

        holder.btn_gen_delevery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, catVendorReqGanerals.get(position).getId(), Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context,DeliveryButtonActivity.class)
                .putExtra("id",catVendorReqGanerals.get(position).getId()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return catVendorReqGanerals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        LinearLayout linBookingUploadMain;
        Button btn_gen_accept,btn_gen_cancel,btn_gen_delevery;

        TextView tv_Ganeral_prName,tv_Ganeral_Vendor,tv_Ganeral_CuName,tv_added_date,
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
            this.tv_added_date= itemView.findViewById(R.id.tv_added_date);
            this.tv_Ganeral_Status= itemView.findViewById(R.id.tv_Ganeral_Status);
            this.btn_gen_delevery= itemView.findViewById(R.id.btn_gen_delevery);
        }
    }
}
