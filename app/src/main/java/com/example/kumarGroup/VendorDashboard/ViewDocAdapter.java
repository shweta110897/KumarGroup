package com.example.kumarGroup.VendorDashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatViewDocVen;
import com.example.kumarGroup.R;

import java.util.List;

public class ViewDocAdapter extends RecyclerView.Adapter<ViewDocAdapter.ViewHolder> {

    Context context;
    List<CatViewDocVen> catViewDocVens;

    public ViewDocAdapter(Context context, List<CatViewDocVen> catViewDocVens) {
        this.context = context;
        this.catViewDocVens = catViewDocVens;
    }

    @NonNull
    @Override
    public ViewDocAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.view_doc_vendor_raw, parent, false);
        ViewDocAdapter.ViewHolder viewHolder = new ViewDocAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewDocAdapter.ViewHolder holder, int position) {

        holder.txt_SalesDate.setText("Sales Date: "+catViewDocVens.get(position).getSales_date());
        holder.txt_CuName.setText("Cu Name: "+catViewDocVens.get(position).getCu_name());
        holder.txt_Village.setText("Village: "+catViewDocVens.get(position).getVillage());
        holder.txt_DealPrice.setText("Deal Price: "+catViewDocVens.get(position).getDealprice());
    }

    @Override
    public int getItemCount() {
        return catViewDocVens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        LinearLayout Lin_ViewDocDetail;
        TextView  txt_SalesDate,txt_CuName,txt_Village,
                txt_DealPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.Lin_ViewDocDetail = itemView.findViewById(R.id.Lin_ViewDocDetail);
            this.txt_SalesDate = itemView.findViewById(R.id.txt_SalesDate);
            this.txt_CuName = itemView.findViewById(R.id.txt_CuName);
            this.txt_Village = itemView.findViewById(R.id.txt_Village);
            this.txt_DealPrice = itemView.findViewById(R.id.txt_DealPrice);
        }
    }
}
