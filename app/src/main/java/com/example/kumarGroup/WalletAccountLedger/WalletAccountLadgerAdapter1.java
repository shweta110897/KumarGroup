package com.example.kumarGroup.WalletAccountLedger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.Catnotattend;
import com.example.kumarGroup.Dataentry1;
import com.example.kumarGroup.R;

import java.util.List;

public class WalletAccountLadgerAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Dataentry1> mdataentrylist;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    String totalbalance;


    public WalletAccountLadgerAdapter1(Context context, List<Dataentry1> mdataentrylist/*,String totalbalance*/) {
        this.context = context;
        this.mdataentrylist = mdataentrylist;
        this.totalbalance = totalbalance;

    }

    @NonNull
    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            // Here Inflating your recyclerview item layout
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.emp_ladger_entry_row, parent, false);
            return new ItemViewHolder(itemView);
        } else if (viewType == TYPE_HEADER) {
            // Here Inflating your header view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_headeritem, parent, false);
            return new HeaderViewHolder(itemView);
        }
        else return null;
       /* View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emp_ladger_entry_row, parent, false);
        return new WalletAccountLadgerAdapter1.viewHolder(view);*/
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeaderViewHolder){
//            setheadersdata_flag = true;
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;

            // You have to set your header items values with the help of model class and you can modify as per your needs
           /* headerViewHolder.tv_balance.setVisibility(View.VISIBLE);
            headerViewHolder.tv_balance.setText("Total balance:-"+totalbalance);*/

        }
        else if (holder instanceof ItemViewHolder) {

            final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

            position=position-1;


            if (mdataentrylist.get(position).getCredit() != null && !mdataentrylist.get(position).getCredit().isEmpty() && !mdataentrylist.get(position).getCredit().equals("null")) {
                itemViewHolder.adapter_in.setText("₹" + mdataentrylist.get(position).getCredit());
                itemViewHolder.ll_credit.setBackgroundColor(context.getResources().getColor(R.color.back_green));

            } else {
                itemViewHolder.adapter_in.setText("");
//            holder.ll_credit.setBackgroundColor(context.getResources().getColor(R.color.back_green));
                itemViewHolder.ll_credit.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
            }

            if (mdataentrylist.get(position).getDebit() != null && !mdataentrylist.get(position).getDebit().isEmpty() && !mdataentrylist.get(position).getDebit().equals("null")) {
                itemViewHolder.adapter_out.setText("₹" + mdataentrylist.get(position).getDebit());
                itemViewHolder.ll_debit.setBackgroundColor(context.getResources().getColor(R.color.back_red));

            } else {
                itemViewHolder.adapter_out.setText("");
//            holder.ll_debit.setBackgroundColor(context.getResources().getColor(R.color.back_red));
                itemViewHolder.ll_debit.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
            }
//        holder.adapter_type.setVisibility(View.GONE);

            itemViewHolder.adapter_description.setText(mdataentrylist.get(position).getDescription());

//        holder.adapter_time.setText(mdataentrylist.get(position).getTime());
            itemViewHolder.cash_das_date.setText(mdataentrylist.get(position).getAdd_date());
            if (mdataentrylist.get(position).getCustomerName().equals("")){
                itemViewHolder.cust_name.setVisibility(View.GONE);
            }else{
                itemViewHolder.cust_name.setVisibility(View.VISIBLE);
                itemViewHolder.cust_name.setText(mdataentrylist.get(position).getCustomerName());
            }


        }

    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }


    @Override
    public int getItemCount() {
        return mdataentrylist.size()+1;
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView adapter_in, adapter_out, adapter_description, cash_das_date,tv_balance;
        LinearLayout ll_debit,ll_credit;


        public HeaderViewHolder(View view) {
            super(view);
            adapter_in = itemView.findViewById(R.id.adapter_in);
            adapter_out = itemView.findViewById(R.id.adapter_out);
            //  adapter_time = itemView.findViewById(R.id.adapter_time);
//            adapter_type = itemView.findViewById(R.id.adapter_type);
            adapter_description = itemView.findViewById(R.id.adapter_description);
//            imagelink = itemView.findViewById(R.id.imagelink);
            cash_das_date = itemView.findViewById(R.id.cash_das_date);
            ll_debit = itemView.findViewById(R.id.ll_debit);
            ll_credit = itemView.findViewById(R.id.ll_credit);
//            tv_balance = itemView.findViewById(R.id.tv_balance);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView adapter_in, adapter_out, adapter_description, cash_das_date,cust_name;
        LinearLayout ll_debit,ll_credit;

        public ItemViewHolder(View itemView) {
            super(itemView);

            adapter_in = itemView.findViewById(R.id.adapter_in);
            adapter_out = itemView.findViewById(R.id.adapter_out);
            //  adapter_time = itemView.findViewById(R.id.adapter_time);
//            adapter_type = itemView.findViewById(R.id.adapter_type);
            adapter_description = itemView.findViewById(R.id.adapter_description);
//            imagelink = itemView.findViewById(R.id.imagelink);
            cash_das_date = itemView.findViewById(R.id.cash_das_date);
            cust_name = itemView.findViewById(R.id.adapter_cust_name);
            ll_debit = itemView.findViewById(R.id.ll_debit);
            ll_credit = itemView.findViewById(R.id.ll_credit);

        }
    }

   /* public class viewHolder extends RecyclerView.ViewHolder {

        TextView adapter_in, adapter_out, adapter_description, cash_das_date;
        LinearLayout ll_debit,ll_credit;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            adapter_in = itemView.findViewById(R.id.adapter_in);
            adapter_out = itemView.findViewById(R.id.adapter_out);
            //  adapter_time = itemView.findViewById(R.id.adapter_time);
//            adapter_type = itemView.findViewById(R.id.adapter_type);
            adapter_description = itemView.findViewById(R.id.adapter_description);
//            imagelink = itemView.findViewById(R.id.imagelink);
            cash_das_date = itemView.findViewById(R.id.cash_das_date);
            ll_debit = itemView.findViewById(R.id.ll_debit);
            ll_credit = itemView.findViewById(R.id.ll_credit);


        }
    }

*/


    public interface RecyclerViewItemInterface {

        void onItemClick(int position, List<Catnotattend> mcatlist);

        void onCallclick(int position, List<Catnotattend> mcatlist);

    }
}
