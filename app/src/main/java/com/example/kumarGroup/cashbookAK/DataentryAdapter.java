package com.example.kumarGroup.cashbookAK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.Dataentry;
import com.example.kumarGroup.R;

import java.util.List;

public class DataentryAdapter extends RecyclerView.Adapter<DataentryAdapter.viewHolder> {
    Context context;
    List<Dataentry> mdataentrylist;

    public DataentryAdapter(Context context, List<Dataentry> mdataentrylist) {
        this.context = context;
        this.mdataentrylist = mdataentrylist;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emp_cashbook_dataentry_row, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.adapter_out.setText(mdataentrylist.get(position).getOutamount());
        holder.adapter_in.setText(mdataentrylist.get(position).getInamount());

        if(mdataentrylist.get(position).getLeger_type().equals("Maintenance")) {
            holder.maintenance.setText(mdataentrylist.get(position).getLeger_type()+"/"+mdataentrylist.get(position).getM_type());
            holder.vendrorType.setText(mdataentrylist.get(position).getType()+"/"+mdataentrylist.get(position).getLeger_name());

        }else{
            if (!mdataentrylist.get(position).getLeger_type().equals("") && !mdataentrylist.get(position).getLeger_name().equals("")) {
                holder.vendrorType.setText(mdataentrylist.get(position).getLeger_type() + "/" + mdataentrylist.get(position).getLeger_name());
            }else{
                holder.vendrorType.setVisibility(View.GONE);
            }
            holder.maintenance.setVisibility(View.GONE);
        }

        holder.adapter_type.setText(mdataentrylist.get(position).getTran_type());
        holder.cash_das_date.setText(mdataentrylist.get(position).getC_date()+" "+mdataentrylist.get(position).getTime());


        holder.adapter_description.setText(mdataentrylist.get(position).getC_desc());
        holder.adapter_type.setText(mdataentrylist.get(position).getTran_type());
//        holder.adapter_time.setText(mdataentrylist.get(position).getTime());
        holder.cash_das_date.setText(mdataentrylist.get(position).getC_date() + " " + mdataentrylist.get(position).getTime());

        if ("".equals(mdataentrylist.get(position).getImg())) {
            holder.imagelink.setVisibility(View.GONE);
        } else {
            try {
                String hellWrld = mdataentrylist.get(position).getImg().replace("https://asworldtech.com/sonalika/cashbook/", "");
                holder.imagelink.setText("Image : " + hellWrld);
            } catch (Exception e) {

            }
        }


        holder.imagelink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openimage = new Intent("android.intent.action.VIEW", Uri.parse(mdataentrylist.get(position).getImg()));
                context.startActivity(openimage);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mdataentrylist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView adapter_in, adapter_out, main_type,vendrorType,maintenance,vendrorTypename, adapter_type, adapter_description, imagelink, cash_das_date;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            adapter_in = itemView.findViewById(R.id.adapter_in);
            adapter_out = itemView.findViewById(R.id.adapter_out);
            maintenance = itemView.findViewById(R.id.maintenance);
            vendrorTypename = itemView.findViewById(R.id.vendrorTypename);
            main_type = itemView.findViewById(R.id.main_type);
            vendrorType = itemView.findViewById(R.id.vendrorType);
            //  adapter_time = itemView.findViewById(R.id.adapter_time);
            adapter_type = itemView.findViewById(R.id.adapter_type);
            adapter_description = itemView.findViewById(R.id.adapter_description);
            imagelink = itemView.findViewById(R.id.imagelink);
            cash_das_date = itemView.findViewById(R.id.cash_das_date);
        }
    }
}
