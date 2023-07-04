package com.example.kumarGroup.WalletOverTime;

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

import com.example.kumarGroup.Overtime;
import com.example.kumarGroup.R;

import java.util.List;

public class OverTimeDisplayAdapter extends RecyclerView.Adapter<OverTimeDisplayAdapter.ViewHolder>
{
    Context context;
    List<Overtime>  overtimes;

    int flag = 0;

    public OverTimeDisplayAdapter(Context context, List<Overtime> overtimes) {
        this.context = context;
        this.overtimes = overtimes;
    }

    @NonNull
    @Override
    public OverTimeDisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.over_time_wallet_raw, parent, false);
        OverTimeDisplayAdapter.ViewHolder viewHolder = new OverTimeDisplayAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OverTimeDisplayAdapter.ViewHolder holder, int position) {
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

        holder.txtOverTimeDate.setText(overtimes.get(position).getCdate() +"");
        holder.txtOverTimeRowCheckIn.setText(overtimes.get(position).getCheckin()+"");
        holder.txtOverTimeRowCheckOut.setText(overtimes.get(position).getCheckout()+"");
        holder.txtOverTimeRowWorkingHour.setText(overtimes.get(position).getWorkinghour()+" ");
        holder.txtOverTimeRowPayOut.setText(overtimes.get(position).getPayout()+" ");
    }

    @Override
    public int getItemCount() {
        return overtimes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtOverTimeDate,txtOverTimeRowCheckIn,txtOverTimeRowCheckOut,
                txtOverTimeRowWorkingHour,txtOverTimeRowPayOut;

        ImageView imgVisitArrowBelow;

        ConstraintLayout expandableLayoutVisit;

        LinearLayout Linemployess;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtOverTimeDate=itemView.findViewById(R.id.txtOverTimeDate);
            this.txtOverTimeRowCheckIn=itemView.findViewById(R.id.txtOverTimeRowCheckIn);
            this.txtOverTimeRowCheckOut=itemView.findViewById(R.id.txtOverTimeRowCheckOut);
            this.txtOverTimeRowWorkingHour=itemView.findViewById(R.id.txtOverTimeRowWorkingHour);
            this.txtOverTimeRowPayOut=itemView.findViewById(R.id.txtOverTimeRowPayOut);


            this.imgVisitArrowBelow=itemView.findViewById(R.id.imgVisitArrowBelow);
            this.expandableLayoutVisit=itemView.findViewById(R.id.expandableLayoutVisit);
            this.Linemployess=itemView.findViewById(R.id.Linemployess);
        }
    }
}
