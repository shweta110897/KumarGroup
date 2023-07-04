package com.example.kumarGroup;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class VillageListAdapter extends RecyclerView.Adapter<VillageListAdapter.ViewHolder>
        implements Filterable {

    List<Detailvillage> listData;
    List<Detailvillage> FilterdlistData;
    Context context;


    public VillageListAdapter(List<Detailvillage> listData,  Context context) {
        this.listData = listData;
        this.FilterdlistData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public VillageListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.village_item, parent, false);
        VillageListAdapter.ViewHolder viewHolder = new VillageListAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VillageListAdapter.ViewHolder holder, int position) {

        Detailvillage item = FilterdlistData.get(position);

        holder.tv_name.setText(item.getVillage_name());

    }

    @Override
    public int getItemCount() {
        return FilterdlistData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_name = itemView.findViewById(R.id.autoComplete);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    FilterdlistData = listData;
                } else {

                    List<Detailvillage> filteredList = new ArrayList<>();

                    for (Detailvillage row : listData) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName =row.getVillage_name();
                        String strID =row.getEid();

                        Log.d("TAG", "Data: "+row);

                        if(strFName == null)
                            strFName = " ";

                        if (strFName.toLowerCase().contains(charString.toLowerCase()))
                        {
                            filteredList.add(row);
                        }
                    }
                    FilterdlistData = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = FilterdlistData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                FilterdlistData = (ArrayList<Detailvillage>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
