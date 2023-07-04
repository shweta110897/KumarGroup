package com.example.kumarGroup.WalletSlarySlip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Salarysleep;

import java.util.List;

public class SalarySlipAdapter extends RecyclerView.Adapter<SalarySlipAdapter.ViewHolder> {


    Context context;
    List<Salarysleep> salarysleeps;

    public SalarySlipAdapter(Context context, List<Salarysleep> salarysleeps) {
        this.context = context;
        this.salarysleeps = salarysleeps;
    }

    @NonNull
    @Override
    public SalarySlipAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.salary_slip_row, parent, false);
        SalarySlipAdapter.ViewHolder viewHolder = new SalarySlipAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SalarySlipAdapter.ViewHolder holder, int position) {

        String lf1 ="Location Fine: ";
        String lf2 = String.valueOf(salarysleeps.get(position).getLocation_fine());

        String uf1 = "Uniform Fine: ";
        String uf2 = String.valueOf(salarysleeps.get(position).getUniform_fine());


        String cf1="Icard Fine: ";
        String cf2 = String.valueOf(salarysleeps.get(position).getIcard_fine());

        String payout1 ="Payout: ";
        String ExtraPay ="Extra Pay: ";
        String payout2 = String.valueOf(salarysleeps.get(position).getPayout());

        holder.tvSalarySlipName.setText(salarysleeps.get(position).getEmployee_name());
      //  holder.tvSalarySlipTotSalary.setText(salarysleeps.get(position).getTotal_salary());
        holder.tvSalarySlipMobile.setText(salarysleeps.get(position).getMobile());
        holder.tvSalarySlipPresent.setText("Present: "+salarysleeps.get(position).getPresent());
        holder.tvSalarySlipAbsent.setText("Absent: "+salarysleeps.get(position).getAbsent());
      //  holder.tvSalarySlipLocation_fine.setText(String.valueOf(salarysleeps.get(position).getLocation_fine()));
        holder.tvSalarySlipLocation_fine.setText(lf1+lf2);
      //  holder.tvSalarySlipUniform_fine.setText(String.valueOf(salarysleeps.get(position).getUniform_fine()));
        holder.tvSalarySlipUniform_fine.setText(uf1+uf2);
       // holder.tvSalarySlipIcard_fine.setText(String.valueOf(salarysleeps.get(position).getIcard_fine()));
        holder.tvSalarySlipIcard_fine.setText(cf1+cf2);
       // holder.tvSalarySlipIPayout.setText(String.valueOf(salarysleeps.get(position).getPayout()));
        holder.tvSalarySlipIPayout.setText(payout2);

        holder.tvSalarySlipEmployeeType.setText(salarysleeps.get(position).getType());

        holder.tvSalarySlipReportGenDate.setText(salarysleeps.get(position).getCdate());
        holder.tvSalarySlipPSDate.setText(salarysleeps.get(position).getCdate().substring(0,10));

        holder.tvSalarySlipReportDisDate.setText("Report Date "+"("+salarysleeps.get(position).getFdate()+" - "+salarysleeps.get(position).getEdate()+")");
        holder.tvSalarySlipReportDisDate1.setText("Report Date "+"("+salarysleeps.get(position).getFdate()+" - "+salarysleeps.get(position).getEdate()+")");

        try{
            holder.tvSalarySlipExtraPayment.setText("ExtraPay: "+salarysleeps.get(position).getExtrapay());
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.tvSalarySlipTotal.setText("Total: "+salarysleeps.get(position).getE_total_salary());


        //  if(salarysleeps.get(position).getExtrapay().equals("")){
       /* if(holder.getAdapterPosition()==0){
            holder.tvSalarySlipExtraPayment.setText("ExtraPay: "+"0");
        }
        else{
            holder.tvSalarySlipExtraPayment.setText("ExtraPay: "+salarysleeps.get(position).getExtrapay());
        }*/

    }

    @Override
    public int getItemCount() {
        return salarysleeps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvSalarySlipName,tvSalarySlipTotSalary,tvSalarySlipMobile,tvSalarySlipPresent,
                tvSalarySlipAbsent,tvSalarySlipLocation_fine,tvSalarySlipUniform_fine,tvSalarySlipIcard_fine,
                tvSalarySlipIPayout,tvSalarySlipReportDisDate,tvSalarySlipReportGenDate,tvSalarySlipReportDisDate1,
                tvSalarySlipEmployeeType,tvSalarySlipPSDate,tvSalarySlipExtraPayment;


        TextView tvSalarySlipTotal;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.tvSalarySlipName=itemView.findViewById(R.id.tvSalarySlipName);
        //    this.tvSalarySlipTotSalary=itemView.findViewById(R.id.tvSalarySlipTotSalary);
            this.tvSalarySlipMobile=itemView.findViewById(R.id.tvSalarySlipMobile);
            this.tvSalarySlipPresent=itemView.findViewById(R.id.tvSalarySlipPresent);
            this.tvSalarySlipAbsent=itemView.findViewById(R.id.tvSalarySlipAbsent);
            this.tvSalarySlipLocation_fine=itemView.findViewById(R.id.tvSalarySlipLocation_fine);
            this.tvSalarySlipUniform_fine=itemView.findViewById(R.id.tvSalarySlipUniform_fine);
            this.tvSalarySlipIcard_fine=itemView.findViewById(R.id.tvSalarySlipIcard_fine);
            this.tvSalarySlipIPayout=itemView.findViewById(R.id.tvSalarySlipIPayout);
            this.tvSalarySlipReportDisDate=itemView.findViewById(R.id.tvSalarySlipReportDisDate);
            this.tvSalarySlipReportGenDate=itemView.findViewById(R.id.tvSalarySlipReportGenDate);
            this.tvSalarySlipReportDisDate1=itemView.findViewById(R.id.tvSalarySlipReportDisDate1);
            this.tvSalarySlipEmployeeType=itemView.findViewById(R.id.tvSalarySlipEmployeeType);
            this.tvSalarySlipPSDate=itemView.findViewById(R.id.tvSalarySlipPSDate);
            this.tvSalarySlipExtraPayment=itemView.findViewById(R.id.tvSalarySlipExtraPayment);
            this.tvSalarySlipTotal=itemView.findViewById(R.id.tvSalarySlipTotal);
        }
    }
}
