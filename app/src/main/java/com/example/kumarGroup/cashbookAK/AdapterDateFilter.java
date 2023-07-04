package com.example.kumarGroup.cashbookAK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Datafilter123;
/*import com.example.sonalikatrac.R;*/



import java.util.List;

public class AdapterDateFilter extends RecyclerView.Adapter<AdapterDateFilter.viewHolder> {
    Context context;
    List<Datafilter123> mdatefilterlist;

    public AdapterDateFilter(Context context, List<Datafilter123> mdatefilterlist) {
        this.context = context;
        this.mdatefilterlist = mdatefilterlist;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emp_cashbook_dataentry_row,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {

        Log.d("TAG", "adpostionion: "+position);

        holder.adapter_in.setText(mdatefilterlist.get(position).getInamount());
        holder.adapter_out.setText(mdatefilterlist.get(position).getOutamount());

        /*if (!"".equals(mdatefilterlist.get(position).getOutamount())){
            holder.adapter_out.setText(mdatefilterlist.get(position).getOutamount());
            holder.adapter_in.setBackgroundResource(R.drawable.cash_bank_round_file_white);
            holder.adapter_in.setTextColor(R.drawable.cash_bank_round_file_white);
            Log.d("TAG", "pos "+position+1+" outamount "+mdatefilterlist.get(position).getOutamount());
        }
        else {
            holder.adapter_in.setText(mdatefilterlist.get(position).getInamount());
            Log.d("TAG", "pos "+position+1+" inamount "+mdatefilterlist.get(position).getInamount());
            holder.adapter_out.setBackgroundResource(R.drawable.cash_bank_round_file_white);
            holder.adapter_out.setTextColor(R.drawable.cash_bank_round_file_white);
        }*/

        if(mdatefilterlist.get(position).getLeger_type().equals("Maintance")) {
            holder.maintenance.setText(mdatefilterlist.get(position).getLeger_type()+"/"+mdatefilterlist.get(position).getM_type());
            holder.vendrorType.setText(mdatefilterlist.get(position).getType()+"/"+mdatefilterlist.get(position).getLeger_name());

        }else{

            if (!mdatefilterlist.get(position).getLeger_type().equals("") && !mdatefilterlist.get(position).getLeger_name().equals("")) {
                holder.vendrorType.setText(mdatefilterlist.get(position).getLeger_type() + "/" + mdatefilterlist.get(position).getLeger_name());

            }else{
                holder.vendrorType.setVisibility(View.GONE);

            }
            holder.maintenance.setVisibility(View.GONE);
        }

        holder.adapter_description.setText(mdatefilterlist.get(position).getC_desc());



        holder.adapter_description.setText(mdatefilterlist.get(position).getC_desc());
        holder.adapter_type.setText(mdatefilterlist.get(position).getTran_type());
        holder.cash_das_date.setText(mdatefilterlist.get(position).getC_date()+" "+mdatefilterlist.get(position).getTime());

        if ("".equals(mdatefilterlist.get(position).getImg())){
            holder.imagelink.setVisibility(View.GONE);
        }
        else {
            String hellWrld = mdatefilterlist.get(position).getImg().replace("https://asworldtech.com/sonalika/cashbook/","");
            holder.imagelink.setText("Image : "+hellWrld);
        }

        holder.imagelink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openimage = new Intent("android.intent.action.VIEW", Uri.parse(mdatefilterlist.get(position).getImg()));
                context.startActivity(openimage);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdatefilterlist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView adapter_in,adapter_out,maintenance,adapter_type,adapter_description,imagelink
                ,cash_das_date,vendrorType;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            adapter_in = itemView.findViewById(R.id.adapter_in);
            adapter_out = itemView.findViewById(R.id.adapter_out);
            maintenance = itemView.findViewById(R.id.maintenance);
          //  adapter_time = itemView.findViewById(R.id.adapter_time);
            adapter_type = itemView.findViewById(R.id.adapter_type);
            adapter_description = itemView.findViewById(R.id.adapter_description);
            imagelink = itemView.findViewById(R.id.imagelink);
            cash_das_date = itemView.findViewById(R.id.cash_das_date);
            vendrorType = itemView.findViewById(R.id.vendrorType);
        }
    }
}
