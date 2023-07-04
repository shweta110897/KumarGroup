package com.example.kumarGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class Adapterattemdancewallet extends RecyclerView.Adapter<Adapterattemdancewallet.ViewHolder> {
    List<WalletList> listData;
    private Context context;
    int flag = 1;

    public Adapterattemdancewallet( List<WalletList> listData, Context context)   {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapterattemdancewallet.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.activity_adapterattemdancewallet, parent, false);
        Adapterattemdancewallet.ViewHolder viewHolder = new Adapterattemdancewallet.ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       // holder.tv_name.setText("Name : " + cat.getFname()+ cat.getLname());
         int Present = Color.parseColor("#148833");
         int Absent = Color.parseColor("#8B0000");

        {
            Log.d("OnBindViewHolder", "onBindViewHolder: calling "+listData);
            for (int i = 0; i <= position; i++) {
                Log.d("nd", "onBindViewHolder: " + i);
            }

            WalletList list = listData.get(position);
          //  Loan_EmployeeList list = listData.get(position);
            holder.txtdate.setText(list.getDate());

            if (list.getStatus().contains("Present")) {
                holder.txtpresent.setText(list.getStatus());
                holder.txtpresent.setTextColor(Present);

            }else if (list.getStatus().contains("Absent")){
                holder.txtpresent.setText(list.getStatus());
                holder.txtpresent.setTextColor(Absent);

            }

          //  holder.txtpresent.setText(list.getStatus());
            holder.txtwork.setText(list.getWorkingHour()+" Hour");
            holder.txtpayout.setText(String.valueOf(list.getPayout()));
            holder.txtchackin.setText(list.getCheckin());
            holder.txtemergencycall.setText(list.getLunchETime());
            holder.txtcheckout.setText(list.getCheckoutTime());
            holder.txtovertimestart.setText(list.getOvertimeTime());
            holder.txtovertimeend.setText(list.getOvertimeEndTime());
            holder.txtlocation.setText(list.getLocation());
            holder.txticard.setText(list.getIcard());
            holder.txtuniform.setText(list.getUniform());
        //    id=list.getPersonid();
            boolean isExpanded = listData.get(position).isExpanded();
            holder.expandableLayoutemployee.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
          //  holder.imgediteemployee.setVisibility(isExpanded? View.VISIBLE : View.GONE);


        }

    /*    WalletList list = listData.get(position);


        holder.tv_checkin.setText("Chack in: "+list.getCheckin());
        holder.tv_checkout_time.setText("Chack out Time: "+list.getCheckoutTime());
        holder.tv_overtime_time.setText("Over Time: "+list.getOvertimeTime());
        holder.tv_overtime_end_time.setText("Over Time End: "+list.getOvertimeEndTime());
        holder.tv_working_hour.setText( "Working Hour: "+list.getWorkingHour());
        holder.tv_location.setText("Loaction: "+list.getLocation());
        holder.tv_icard.setText("Icard: "+list.getIcard());
        holder.tv_uniform.setText("Uniform: "+list.getUniform());*/

     /*   if (listData.get(position).getStatus().contains("Present")) {
            holder.tv_payout.setText("Payout: "+listData.get(position).getPayout());
            holder.tv_payout.setBackgroundColor(Present);*/

/*
        }else if (listData.get(position).getStatus().contains("Absent")){
*/
  //          holder.tv_payout.setText("Payout: "+listData.get(position).getPayout());
           // holder.tv_payout.setBackgroundColor(Absent);

      /*  }*/

    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtdate,txtpresent,txtwork,txtpayout,txtchackin,txtemergencycall,txtcheckout,txtovertimestart,txtovertimeend,txtlocation,txticard,txtuniform;
      //  Button tv_payout;
      ConstraintLayout expandableLayoutemployee;

        ImageView imgediteemployee;

        LinearLayout lin_Main,activity_adapterfo_personal_detail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtdate=itemView.findViewById(R.id.txtdate);
            this.txtpresent=itemView.findViewById(R.id.txtpresent);
            this.txtwork=itemView.findViewById(R.id.txtwork);
            this.txtpayout=itemView.findViewById(R.id.txtpayout);
            this.txtchackin=itemView.findViewById(R.id.txtchackin);
            this.txtemergencycall=itemView.findViewById(R.id.txtemergencycall);
            this.txtcheckout=itemView.findViewById(R.id.txtcheckout);
            this.txtovertimestart=itemView.findViewById(R.id.txtovertimestart);
            this.txtovertimeend=itemView.findViewById(R.id.txtovertimeend);
            this.txtlocation=itemView.findViewById(R.id.txtlocation);
            this.txticard=itemView.findViewById(R.id.txticard);
            this.txtuniform=itemView.findViewById(R.id.txtuniform);
            this.imgediteemployee=itemView.findViewById(R.id.imgediteemployee);
            this.expandableLayoutemployee=itemView.findViewById(R.id.expandableLayoutemployee);


            imgediteemployee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                /*    if (flag == 1) {
                        Log.d("TAG", "onClick: "+flag);
//                        Toast.makeText(context, flag, Toast.LENGTH_SHORT).show();
                   //     imgediteemployee.setImageResource(R.drawable.arrow_down);
                        imgediteemployee.setImageResource(R.drawable.arrow_down);

                        flag = 0;
                    //    flag = 1;
                    } else if (flag == 0) {
               //         Toast.makeText(context, flag, Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "onClick: "+flag);

              //          imgediteemployee.setImageResource(R.drawable.arrow_up);
                        imgediteemployee.setImageResource(R.drawable.arrow_up);

                        flag = 1;
                    //    flag = 0;
                    }*/
                    WalletList movie = listData.get(getAdapterPosition());
                    movie.setExpanded(!movie.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}