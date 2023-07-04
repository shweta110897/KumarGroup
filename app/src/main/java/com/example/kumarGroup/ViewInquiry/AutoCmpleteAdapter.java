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

import com.example.kumarGroup.R;
import com.example.kumarGroup.Village;

import java.util.ArrayList;

public class AutoCmpleteAdapter extends ArrayAdapter<Village> implements Filterable {

    private LayoutInflater mInflater = null;
    private Context activity;

    private final String MY_DEBUG_TAG = "CustomerAdapter";
    private ArrayList<Village> items;
    private ArrayList<Village> itemsAll;
    private ArrayList<Village> suggestions;
    private int viewResourceId;



    public AutoCmpleteAdapter(Context context, ArrayList<Village> items) {
        super(context, 0, items);
        activity = context;
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.items = items;
        this.itemsAll = (ArrayList<Village>) items.clone();
        this.suggestions = new ArrayList<Village>();


    }



    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Village getItem(int position) {
        return items.get(position);
    }

    public static class ViewHolder {

        public TextView title;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        ViewHolder holder;
        Village village1 = getItem(position);


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


        holder.title.setText(village1.getVillage_name());

        return convertView;
    }


    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((Village)(resultValue)).getVillage_name();
            return str;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                suggestions.clear();
                String txt=constraint.toString().toLowerCase();
                Log.d(MY_DEBUG_TAG,"input_string:--"+txt);
                Log.d(MY_DEBUG_TAG,"itemsAll:--"+itemsAll);
                for (Village customer : itemsAll) {
                    if(customer.getVillage_name().toLowerCase().startsWith(txt)){
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
                ArrayList<Village> filteredList = (ArrayList<Village>) filterResults.values;

                Log.d(MY_DEBUG_TAG,"resultlistsize123:---"+itemsAll.size());

                return filterResults/*new FilterResults()*/;
            }
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<Village> filteredList = (ArrayList<Village>) results.values;
            if(results != null && results.count > 0) {
                clear();
                for (Village c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
            Log.d(MY_DEBUG_TAG, "publishResults2: "+filteredList);


        }
    };
}