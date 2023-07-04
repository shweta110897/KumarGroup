package com.example.kumarGroup.ViewInquiry;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.kumarGroup.CatInquiryGenDetail;
import com.example.kumarGroup.R;

import java.util.ArrayList;

public class NumberAutoCmpleteAdapter extends ArrayAdapter<CatInquiryGenDetail> implements Filterable {

    private LayoutInflater mInflater = null;
    private Context activity;

    private final String MY_DEBUG_TAG = "CustomerAdapter";
    private ArrayList<CatInquiryGenDetail> items;
    private ArrayList<CatInquiryGenDetail> itemsAll;
    private ArrayList<CatInquiryGenDetail> suggestions;
    private int viewResourceId;



    public NumberAutoCmpleteAdapter(Context context, ArrayList<CatInquiryGenDetail> items, ArrayList<CatInquiryGenDetail> itemsAll1) {
        super(context, 0, items);
        activity = context;
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.items = items;
//        this.itemsAll = itemsAll1;
        this.itemsAll = (ArrayList<CatInquiryGenDetail>) items.clone();
        Log.d("itemsAll12",itemsAll.toString());
        this.suggestions = new ArrayList<CatInquiryGenDetail>();

    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public CatInquiryGenDetail getItem(int position) {
        return items.get(position);
    }

    public static class ViewHolder {

        public TextView title;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        ViewHolder holder;
        CatInquiryGenDetail village1 = getItem(position);


        if (convertView == null) {

            holder = new ViewHolder();

            convertView = mInflater.inflate(R.layout.village_item,
                    parent, false);
            holder.title = (TextView) convertView
                    .findViewById(R.id.autoComplete);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.title.setText(village1.getMoblino());

        return convertView;
    }


    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((CatInquiryGenDetail)(resultValue)).getMoblino();
            return str;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.d("constraint", String.valueOf(constraint));
            if(constraint != null) {
                suggestions.clear();
                String txt=constraint.toString();
                Log.d(MY_DEBUG_TAG,"input_string:--"+txt);
                Log.d("itemsAll",itemsAll.toString());
                for (CatInquiryGenDetail customer : itemsAll) {
//                    if(customer.getMoblino().contains(txt)){
                    if(customer.getMoblino() != null && customer.getMoblino().startsWith(txt)){
                        suggestions.add(customer);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();

                return filterResults;
            } else {


                /*my code for auto clear*/
                FilterResults filterResults = new FilterResults();
                filterResults.values = itemsAll;
                filterResults.count = itemsAll.size();
                ArrayList<CatInquiryGenDetail> filteredList = (ArrayList<CatInquiryGenDetail>) filterResults.values;

                Log.d(MY_DEBUG_TAG,"resultlistsize123:---"+itemsAll.size());

                return filterResults/*new FilterResults()*/;
            }
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<CatInquiryGenDetail> filteredList = (ArrayList<CatInquiryGenDetail>) results.values;
            if(results != null && results.count > 0) {
                clear();
                for (CatInquiryGenDetail c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
            Log.d(MY_DEBUG_TAG, "publishResults2: "+filteredList);


        }
    };
}