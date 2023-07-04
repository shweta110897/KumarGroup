package com.example.kumarGroup.cashbookAK;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.Datahistory;
import com.example.kumarGroup.R;

import java.util.List;

public class AdapterHistroyTransaction extends RecyclerView.Adapter<AdapterHistroyTransaction.viewHolder> {

    Context context;
    List<Datahistory> datahistoryList;

    public AdapterHistroyTransaction(Context context, List<Datahistory> datahistoryList) {
        this.context = context;
        this.datahistoryList = datahistoryList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cashbook_transfer_histroy,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String dateandtime = datahistoryList.get(position).getC_date() +" "+ datahistoryList.get(position).getTime();
        String converto = "Transfer "+datahistoryList.get(position).getC_from()+" To " + datahistoryList.get(position).getC_to();
        holder.histroy_description.setText(datahistoryList.get(position).getC_desc());
        holder.histroy_time.setText(dateandtime);
        holder.histroy_convert.setText(converto);
        holder.histroy_amount.setText(datahistoryList.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return datahistoryList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView histroy_description,histroy_time,histroy_convert,histroy_amount;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            histroy_description = itemView.findViewById(R.id.histroy_description);
            histroy_time = itemView.findViewById(R.id.histroy_time);
            histroy_convert = itemView.findViewById(R.id.histroy_convert);
            histroy_amount = itemView.findViewById(R.id.histroy_amount);
        }
    }
}
