package com.example.kumarGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterFoSalesInfo extends RecyclerView.Adapter<AdapterFoSalesInfo.ViewHolder>
        implements Filterable {


    List<DataCatagoryDetailModeljava.Cat> listData;
    List<DataCatagoryDetailModeljava.Cat> FilterdlistData;
    Context context;
  //  ValueFilter valueFilter;

    public AdapterFoSalesInfo(Context context, List<DataCatagoryDetailModeljava.Cat> cat) {
        this.listData = cat;
        this.FilterdlistData = cat;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterFoSalesInfo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.activity_adapter_fo_sales_info, parent, false);
        AdapterFoSalesInfo.ViewHolder viewHolder = new AdapterFoSalesInfo.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFoSalesInfo.ViewHolder holder, int position) {

        DataCatagoryDetailModeljava.Cat cat = FilterdlistData.get(position);
//        for (int i = 0; i <= position; i++) {
//            Log.d("kk", "onBindViewHolder: " + i);
//            Log.d("cat", "onBindViewHolder: " + FilterdlistData);
//        }

        holder.tv_name.setText("Name : " + cat.getFname() + cat.getLname());
        holder.tv_do_it.setText("Customer Do it  : " + cat.getCatName());
        holder.tv_mobile_no.setText("Mobile : " + cat.getMoblino());
        holder.tv_Whapp_Number.setText("Whapp Number : " + cat.getWhatsappno());
      //holder.tv_district.setText("District : " + cat.getDistric());
        holder.tv_village.setText("Village : " + cat.getVilage());
        holder.tv_district.setText("Description : " + cat.getDesc());

        holder.data_recyclersalesinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, FoDeliveryDetails.class);

                i.putExtra("FirstName", "First Name : " + cat.getFname());
                i.putExtra("LastName", "Last Name : " + cat.getLname());
                i.putExtra("Mobile", "Mobile Number : " + cat.getMoblino());
                i.putExtra("Mobilecall", cat.getMoblino());
                i.putExtra("Whappnumber", "Whapp Number : " + cat.getWhatsappno());
                i.putExtra("Whappnumbercall", cat.getWhatsappno());
                i.putExtra("Customordoit", "Customer Do it : " + cat.getCatName());
                i.putExtra("Village", "Village : " + cat.getVilage());
                i.putExtra("district", "District : " + cat.getDistric());
                i.putExtra("Taluko", "Taluko : " + cat.getTehsil());
                i.putExtra("Id",cat.getAuto_id());
                i.putExtra("cat_id",cat.getCatId());
                i.putExtra("eid",cat.getEid());

                context.startActivities(new Intent[]{i});
            }
        });
    }

    @Override
    public int getItemCount() {
      //  return listData.size();
        return FilterdlistData.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_do_it, tv_mobile_no, tv_village, tv_Whapp_Number, tv_district;
        LinearLayout data_recyclersalesinfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_do_it = itemView.findViewById(R.id.tv_do_it);
            this.tv_mobile_no = itemView.findViewById(R.id.tv_mobile_no);
            this.tv_village = itemView.findViewById(R.id.tv_village);
            this.tv_Whapp_Number = itemView.findViewById(R.id.tv_Whapp_Number);
            this.tv_district = itemView.findViewById(R.id.tv_district);
            data_recyclersalesinfo = itemView.findViewById(R.id.data_recyclersalesinfo);
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

                    List<DataCatagoryDetailModeljava.Cat> filteredList = new ArrayList<>();

                    for (DataCatagoryDetailModeljava.Cat row : listData) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                  //   row.getVilage().toString();

                        String strFName =row.getFname();
                        String strMobile =row.getMoblino();
                        String strVName =row.getVilage();
                        String strLName =row.getLname();
                        String strPaymentType =row.getPaymentType();
                        String strPassingType =row.getPassingType();
                        Log.d("TAG", "Data: "+row);

                        if(strFName == null)
                            strFName = " ";
                        if(strMobile == null)
                            strMobile = " ";
                        if(strVName == null)
                            strVName = " ";
                        if(strLName == null)
                            strLName = " ";
                        if(strPaymentType == null)
                            strPaymentType = " ";
                        if(strPassingType == null)
                            strPassingType = " ";

                        if (strFName.toLowerCase().contains(charString.toLowerCase())
                                || strMobile.toLowerCase().contains(charString.toLowerCase())
                               /* || row.getDistric().toLowerCase().contains(charString.toLowerCase())*/
                                || strVName.toLowerCase().contains(charString.toLowerCase())
                                || strPassingType.toLowerCase().contains(charString.toLowerCase())
                                || strPaymentType.toLowerCase().contains(charString.toLowerCase())
                                || strLName.toLowerCase().contains(charString.toLowerCase()))
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
                FilterdlistData = (ArrayList<DataCatagoryDetailModeljava.Cat>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}

