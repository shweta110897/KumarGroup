package com.example.kumarGroup.ViewInquiry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatVisitVI;
import com.example.kumarGroup.R;

import java.util.List;

public class VisitViAdapter  extends RecyclerView.Adapter<VisitViAdapter.ViewHolder> {

    Context context;
    List<CatVisitVI>  catVisitVIS;

    int flag = 0;

    public VisitViAdapter(Context context, List<CatVisitVI> catVisitVIS) {
        this.context = context;
        this.catVisitVIS = catVisitVIS;
    }

    @NonNull
    @Override
    public VisitViAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.visit_vi_raw, parent, false);
        VisitViAdapter.ViewHolder viewHolder = new VisitViAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VisitViAdapter.ViewHolder holder, int position) {
        holder.imgVisitArrowBelow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag == 0) {
                    holder.expandableLayoutVisit.setVisibility(View.GONE);
                    flag = 1;
                } else if (flag == 1) {
                    holder.expandableLayoutVisit.setVisibility(View.VISIBLE);
                    flag = 0;
                }

            }
        });


        holder.txtVFUVisitDate.setText("Visit Date: "+catVisitVIS.get(position).getVdate());
        holder.txtVisitRowReason.setText(catVisitVIS.get(position).getReason());
        holder.txtVisitRowBookingStatus.setText(catVisitVIS.get(position).getBooking());

        holder.txtVisitRowPaymentAmount.setText(catVisitVIS.get(position).getFollow_up_status());
        holder.txtVisitRowBookingAmount.setText(catVisitVIS.get(position).getSell_model());

        holder.txtVisitRowdelivery.setText(catVisitVIS.get(position).getDelivry()+"");
        holder.txtVisitRowDeliveryModel.setText(catVisitVIS.get(position).getModel_name());


    }

    @Override
    public int getItemCount() {
        return catVisitVIS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtVFUVisitDate,txtVisitRowReason,
                txtVisitRowBookingStatus,txtVisitRowPaymentStatus,
                txtVisitRowBookingAmount,txtVisitRowPaymentAmount,
                txtVisitRowDeliveryModel,txtVisitRowdelivery,
                txtVisitRowSellLost,txtVisitRowAddgestingCustomer,
                txtVisitRowCategoryName,
                txtVisitRowName,txtVisitRowMobileNo,txtVisitRowWhatsAppNo,txtVisitRowEmpName;

        ConstraintLayout expandableLayoutVisit;

        ImageView imgVisitArrowBelow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtVFUVisitDate=itemView.findViewById(R.id.txtVFUVisitDate);
            this.txtVisitRowReason=itemView.findViewById(R.id.txtVisitRowReason);
            this.txtVisitRowBookingStatus=itemView.findViewById(R.id.txtVisitRowBookingStatus);
            this.txtVisitRowPaymentStatus=itemView.findViewById(R.id.txtVisitRowPaymentStatus);
            this.txtVisitRowBookingAmount=itemView.findViewById(R.id.txtVisitRowBookingAmount);
            this.txtVisitRowPaymentAmount=itemView.findViewById(R.id.txtVisitRowPaymentAmount);
            this.txtVisitRowDeliveryModel=itemView.findViewById(R.id.txtVisitRowDeliveryModel);
            this.txtVisitRowdelivery=itemView.findViewById(R.id.txtVisitRowdelivery);




            /*  this.txtVisitRowCategoryName=itemView.findViewById(R.id.txtVisitRowCategoryName);
            this.txtVisitRowName=itemView.findViewById(R.id.txtVisitRowName);
            this.txtVisitRowMobileNo=itemView.findViewById(R.id.txtVisitRowMobileNo);
            this.txtVisitRowWhatsAppNo=itemView.findViewById(R.id.txtVisitRowWhatsAppNo);
            this.txtVisitRowEmpName=itemView.findViewById(R.id.txtVisitRowEmpName);*/

            this.expandableLayoutVisit=itemView.findViewById(R.id.expandableLayoutVisit);
            // expandableLayoutVisit.setVisibility(View.GONE);
            this.imgVisitArrowBelow=itemView.findViewById(R.id.imgVisitArrowBelow);
           /* this.txtVisitRowSellLost=itemView.findViewById(R.id.txtVisitRowSellLost);
            this.txtVisitRowAddgestingCustomer=itemView.findViewById(R.id.txtVisitRowAddgestingCustomer);*/

        }
    }
}
