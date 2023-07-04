package com.example.kumarGroup.Loan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.LoanDisplay;
import com.example.kumarGroup.R;

import java.util.List;

public class DisplayLoanAdapter extends RecyclerView.Adapter<DisplayLoanAdapter.ViewHolder> {

    Context context;
    List<LoanDisplay> displayLoan;

    public DisplayLoanAdapter(Context context, List<LoanDisplay> displayLoan) {
        this.context = context;
        this.displayLoan = displayLoan;
    }

    @NonNull
    @Override
    public DisplayLoanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.display_loan_row, parent, false);
        DisplayLoanAdapter.ViewHolder viewHolder = new DisplayLoanAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayLoanAdapter.ViewHolder holder, int position) {

        String bala1 = "₹ ";
        Double bala2 = displayLoan.get(position).getBalance();

        String amt1 = "₹ ";
        Double amt2 = displayLoan.get(position).getTotal_amount();

        String per1 = "₹ ";
        Double per2 = displayLoan.get(position).getInterest();


        holder.txtDLRLoanName.setText("Employee Name:  "+displayLoan.get(position).getEmp_name());
        holder.txtDLRLoanDate.setText("Date:  "+displayLoan.get(position).getDate());
        holder.txtDLRLoanDesc.setText("Description:  "+displayLoan.get(position).getDesc());
        holder.txtDLRLoanDebit.setText("Debit:  "+displayLoan.get(position).getDebit());
        holder.txtDLRLoanCredit.setText("Credit:  "+displayLoan.get(position).getCredit());
        holder.txtDLRLoanBalance.setText("Balance:  "+bala1+bala2);
        holder.txtDLRLoanInterest.setText("Interest:  "+per1+per2);
        holder.txtDLRLoanTotal_Amount.setText("Total Amount:  "+amt1+amt2);

         //Log.d("EmployeeName","msg"+displayLoan.get(position).getEmp_name());
    }

    @Override
    public int getItemCount() {
        return displayLoan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView txtDLRLoanName,txtDLRLoanDate1,txtDLRLoanDesc,txtDLRLoanDebit,txtDLRLoanCredit,
                txtDLRLoanBala,txtDLRLoanAmt,txtDLRLoanDate,txtDLRLoaninterest_f,txtDLRLoanDebit_f,
                txtDLRLoanCredit_f,txtDLRLoanBalance,txtDLRLoanper,txtDLRLoant_amt,txtDLRLoanName1,
                txtDLRLoanInterest,txtDLRLoanTotal_Amount;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtDLRLoanName = itemView.findViewById(R.id.txtDLRLoanName);
            this.txtDLRLoanDesc = itemView.findViewById(R.id.txtDLRLoanDesc);
            this.txtDLRLoanDebit = itemView.findViewById(R.id.txtDLRLoanDebit);
            this.txtDLRLoanCredit = itemView.findViewById(R.id.txtDLRLoanCredit);
            this.txtDLRLoanDate = itemView.findViewById(R.id.txtDLRLoanDate);
            this.txtDLRLoanBalance = itemView.findViewById(R.id.txtDLRLoanBalance);
            this.txtDLRLoanInterest = itemView.findViewById(R.id.txtDLRLoanInterest);
            this.txtDLRLoanTotal_Amount = itemView.findViewById(R.id.txtDLRLoanTotal_Amount);

        }
    }
}
