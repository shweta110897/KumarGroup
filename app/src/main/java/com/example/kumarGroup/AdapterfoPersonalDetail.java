package com.example.kumarGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterfoPersonalDetail extends RecyclerView.Adapter<AdapterfoPersonalDetail.ViewHolder> {

    List<Catemp> listData;
    private Context context;

    public AdapterfoPersonalDetail( List<Catemp> listData, Context context)   {
        this.listData = listData;
        this.context = context;
    }
    @NonNull
    @Override
    public AdapterfoPersonalDetail.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.activity_adapterfo_personal_detail, parent, false);
        AdapterfoPersonalDetail.ViewHolder viewHolder = new AdapterfoPersonalDetail.ViewHolder(listItem);

        return viewHolder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull AdapterfoPersonalDetail.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_category_list.setText(listData.get(position).getCat_list());
        holder.tv_count.setText(listData.get(position).getCount());
        holder.lin_Main.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View view) {
               Intent i = new Intent(context,FoVisit.class);

                 i.putExtra("cat_id",listData.get(position).getCat_id());
              //   i.putExtra("emp",listData.get(position).get)
                 i.putExtra("cat_list",listData.get(position).getCat_list());
                 i.putExtra("count",listData.get(position).getCount());
               //  context.startActivities(new Intent[]{i});
                 context.startActivity(i);
         }

      });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_category_list,tv_count;
        LinearLayout lin_Main,activity_adapterfo_personal_detail;


         ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_category_list=itemView.findViewById(R.id.tv_category_list);
            this.activity_adapterfo_personal_detail=itemView.findViewById(R.id.data_recyclerMRAL);
            this.tv_count=itemView.findViewById(R.id.tv_count);
             lin_Main=itemView.findViewById(R.id.lin_Main);

         }
    }
}
