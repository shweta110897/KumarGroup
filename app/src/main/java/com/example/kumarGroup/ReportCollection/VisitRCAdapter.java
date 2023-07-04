package com.example.kumarGroup.ReportCollection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatShowDetailVisitRC;
import com.example.kumarGroup.R;

import java.util.List;

public class VisitRCAdapter extends RecyclerView.Adapter<VisitRCAdapter.ViewHolder> {

    int flag = 0;

    Context context;
    List<CatShowDetailVisitRC>  catShowDetailVisitRCS;

    public VisitRCAdapter(Context context, List<CatShowDetailVisitRC> catShowDetailVisitRCS) {
        this.context = context;
        this.catShowDetailVisitRCS = catShowDetailVisitRCS;
    }

    @NonNull
    @Override
    public VisitRCAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.paymenytcollection_visit_raw, parent, false);
        VisitRCAdapter.ViewHolder viewHolder = new VisitRCAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VisitRCAdapter.ViewHolder holder, int position) {
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

                /*if(holder.expandableLayoutVisit.getVisibility() == View.GONE){
                    holder.expandableLayoutVisit.setVisibility(View.VISIBLE);
                } else {
                    holder.expandableLayoutVisit.setVisibility(View.GONE);
                }*/

                //    holder.expandableLayoutVisit.setVisibility(View.VISIBLE);
            }
        });

        holder.txtVFUVisitDate.setText("Visit Date: "+catShowDetailVisitRCS.get(position).getCudate());
        holder.txtVisitRowReason.setText(catShowDetailVisitRCS.get(position).getReason());
        holder.txtVisitRowBookingStatus.setText(catShowDetailVisitRCS.get(position).getPayment_collection());
        holder.txtVisitRowBookingAmount.setText(catShowDetailVisitRCS.get(position).getAmount());

       /* holder.txtVisitRowPaymentAmount.setText(catShowDetailVisitRCS.get(position).getAmount());
        holder.txtVisitRowBookingAmount.setText(catShowDetailVisitRCS.get(position).getBooking_amt());*/
    /*    holder.txtVisitRowSellLost.setText(catVisitFollows.get(position).getSell_lost());
        holder.txtVisitRowAddgestingCustomer.setText(catVisitFollows.get(position).getAddgcust());*/

       /* holder.txtVisitRowCategoryName.setText(catVisitFollows.get(position).getCat_name());
        holder.txtVisitRowName.setText(catVisitFollows.get(position).getFname()+""+catVisitFollows.get(position).getLname());
        holder.txtVisitRowMobileNo.setText(catVisitFollows.get(position).getMoblino());
        holder.txtVisitRowWhatsAppNo.setText(catVisitFollows.get(position).getWhatsappno());*/
    }

    @Override
    public int getItemCount() {
        return catShowDetailVisitRCS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtVFUVisitDate,txtVisitRowReason,
                txtVisitRowBookingStatus,txtVisitRowPaymentStatus,
                txtVisitRowBookingAmount,txtVisitRowPaymentAmount,
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
