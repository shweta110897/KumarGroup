package com.example.kumarGroup.WalletTraveling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Travaling;

import java.util.List;

public class WalletTravelingDataAdapter extends RecyclerView.Adapter<WalletTravelingDataAdapter.ViewHolder>
{
    Context context;
    List<Travaling> travalings;

    int flag = 0;

    public WalletTravelingDataAdapter(Context context, List<Travaling> travalings) {
        this.context = context;
        this.travalings = travalings;
    }

    @NonNull
    @Override
    public WalletTravelingDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.traveling_data_display_raw, parent, false);
        WalletTravelingDataAdapter.ViewHolder viewHolder = new WalletTravelingDataAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WalletTravelingDataAdapter.ViewHolder holder, int position) {
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

        holder.txtTravelingDate.setText(travalings.get(position).getCdate() +"");
        holder.txtTravelingRowCheckIn.setText(travalings.get(position).getCheckin()+"");
        holder.txtTravelingRowCheckOut.setText(travalings.get(position).getCheckout()+"");
        holder.txtTravelingCheckInKm.setText(travalings.get(position).getCh_km()+" KM ");
        holder.txtTravelingCheckOutKm.setText(travalings.get(position).getChout_km()+" KM ");
        holder.txtTravelingRowPayOut.setText(String.valueOf(travalings.get(position).getPayout()));
        holder.txtTravelingTotalKm.setText(travalings.get(position).getTotalkm()+" KM ");
    }

    @Override
    public int getItemCount() {
        return travalings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView txtTravelingDate,txtTravelingRowCheckIn,txtTravelingRowCheckOut,txtTravelingRowPayOut,
                txtTravelingCheckInKm,txtTravelingCheckOutKm,txtTravelingTotalKm;

        ImageView imgVisitArrowBelow;

        ConstraintLayout expandableLayoutVisit;

        LinearLayout Linemployess;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtTravelingDate=itemView.findViewById(R.id.txtTravelingDate);
            this.txtTravelingRowCheckIn=itemView.findViewById(R.id.txtTravelingRowCheckIn);
            this.txtTravelingRowCheckOut=itemView.findViewById(R.id.txtTravelingRowCheckOut);
            this.txtTravelingRowPayOut=itemView.findViewById(R.id.txtTravelingRowPayOut);


            this.txtTravelingCheckInKm=itemView.findViewById(R.id.txtTravelingCheckInKm);
            this.txtTravelingCheckOutKm=itemView.findViewById(R.id.txtTravelingCheckOutKm);
            this.txtTravelingTotalKm=itemView.findViewById(R.id.txtTravelingTotalKm);


            this.imgVisitArrowBelow=itemView.findViewById(R.id.imgVisitArrowBelow);
            this.expandableLayoutVisit=itemView.findViewById(R.id.expandableLayoutVisit);
            this.Linemployess=itemView.findViewById(R.id.Linemployess);
        }
    }
}
