package com.example.kumarGroup.ReportCollection.ByVillageList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.R;
import com.example.kumarGroup.VillageList1;

import java.util.List;


public class Village_List_Adapter extends RecyclerView.Adapter<Village_List_Adapter.ViewHolder>{

    Context context;
    List<VillageList1.Detail> Cat1;
    Activity activity;

    public Village_List_Adapter(Context context, Activity activity,List<VillageList1.Detail> cat1) {
        this.context = context;
        this.Cat1 = cat1;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Village_List_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.paymentvillagelist_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Village_List_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtPaymentCollection.setText(Cat1.get(position).getVillage());
        holder.txtPaymentCollectionCount.setText(Cat1.get(position).getCount());

        holder.txtPaymentCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,VillageListShow2Activity.class);
                intent.putExtra("type",Cat1.get(position).getType());
                intent.putExtra("vname",Cat1.get(position).getVillage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Cat1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPaymentCollection,txtPaymentCollectionCount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtPaymentCollection=itemView.findViewById(R.id.txtPaymentCollection);
            this.txtPaymentCollectionCount=itemView.findViewById(R.id.txtPaymentCollectionCount);

        }
    }
}
