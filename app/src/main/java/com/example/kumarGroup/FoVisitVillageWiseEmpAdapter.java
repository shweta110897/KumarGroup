package com.example.kumarGroup;

import android.annotation.SuppressLint;
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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FoVisitVillageWiseEmpAdapter  extends RecyclerView.Adapter<FoVisitVillageWiseEmpAdapter.ViewHolder>
    implements Filterable
{

    Context context;
    List<CatMyProVillage> catMyProVillages;
    List<CatMyProVillage> catMyProVillages_one;

    public FoVisitVillageWiseEmpAdapter(Context context, List<CatMyProVillage> catMyProVillages) {
        this.context = context;
        this.catMyProVillages = catMyProVillages;
        this.catMyProVillages_one = catMyProVillages;
    }

    @NonNull
    @Override
    public FoVisitVillageWiseEmpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.activity_adapter_fo_sales_info, parent, false);
        FoVisitVillageWiseEmpAdapter.ViewHolder viewHolder = new FoVisitVillageWiseEmpAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull FoVisitVillageWiseEmpAdapter.ViewHolder holder, int position) {
        holder.tv_name.setText("Name : " + catMyProVillages.get(position).getFname() +" "+
                catMyProVillages.get(position).getLname());
        holder.tv_do_it.setText("Customer Do it  : " + catMyProVillages.get(position).getCat_name());
        holder.tv_mobile_no.setText("Mobile : " + catMyProVillages.get(position).getMoblino());
        holder.tv_Whapp_Number.setText("Whapp Number : " + catMyProVillages.get(position).getWhatsappno());
        //holder.tv_district.setText("District : " + cat.getDistric());
        holder.tv_village.setText("Village : " + catMyProVillages.get(position).getVilage());
        holder.tv_district.setText("Description : " + catMyProVillages.get(position).getDesc());


        holder.data_recyclersalesinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, FoDeliveryDetails.class);

                i.putExtra("FirstName", "First Name : " + catMyProVillages.get(position).getFname());
                i.putExtra("LastName", "Last Name : " + catMyProVillages.get(position).getLname());
                i.putExtra("Mobile", "Mobile Number : " + catMyProVillages.get(position).getMoblino());
                i.putExtra("Mobilecall", catMyProVillages.get(position).getMoblino());
                i.putExtra("Whappnumber", "Whapp Number : " + catMyProVillages.get(position).getWhatsappno());
                i.putExtra("Whappnumbercall", catMyProVillages.get(position).getWhatsappno());
                i.putExtra("Customordoit", "Customer Do it : " + catMyProVillages.get(position).getCat_name());
                i.putExtra("Village", "Village : " + catMyProVillages.get(position).getVilage());
                i.putExtra("district", "District : " + catMyProVillages.get(position).getDistric());
                i.putExtra("Taluko", "Taluko : " + catMyProVillages.get(position).getTehsil());
                i.putExtra("Id",catMyProVillages.get(position).getAuto_id());
                i.putExtra("cat_id",catMyProVillages.get(position).getCat_id());
                i.putExtra("eid",catMyProVillages.get(position).getEid());

                context.startActivities(new Intent[]{i});
            }
        });

    }

    @Override
    public int getItemCount() {
        return catMyProVillages.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder
    {
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
                    catMyProVillages_one =  catMyProVillages;
                } else {

                    List<CatMyProVillage> filteredList = new ArrayList<>();

                    for (CatMyProVillage row :  catMyProVillages) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName =row.getFname();
                        String strMobile =row.getMoblino();
                        String strVName =row.getVilage();
                        String strLName =row.getLname();
                        Log.d("TAG", "Data: "+row);

                        if(strFName == null)
                            strFName = " ";
                        if(strMobile == null)
                            strMobile = " ";
                        if(strVName == null)
                            strVName = " ";
                        if(strLName == null)
                            strLName = " ";

                        if (strFName.toLowerCase().contains(charString.toLowerCase())
                                || strMobile.toLowerCase().contains(charString.toLowerCase())
                                /* || row.getDistric().toLowerCase().contains(charString.toLowerCase())*/
                                || strVName.toLowerCase().contains(charString.toLowerCase())
                                || strLName.toLowerCase().contains(charString.toLowerCase()))
                        {
                            filteredList.add(row);
                        }
                    }
                    catMyProVillages_one = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = catMyProVillages_one;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                catMyProVillages_one = (ArrayList<CatMyProVillage>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}

