package com.example.kumarGroup.ViewInquiry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Village;

import java.util.ArrayList;

public class AgeingAdapter extends ArrayAdapter<Village> {

    public AgeingAdapter(Context context,
                            ArrayList<Village> algorithmList)
    {
        super(context, 0, algorithmList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView,
                          ViewGroup parent)
    {
        // It is used to set our custom view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.village_item, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.autoComplete);
        TextView count = convertView.findViewById(R.id.count);
        count.setVisibility(View.VISIBLE);
        Village currentItem = getItem(position);
        // It is used the name to the TextView when the
        // current item is not null.
        if (currentItem != null) {
            textViewName.setText(currentItem.getVillage_name());
            count.setText(currentItem.getDays());
        }
        return convertView;
    }
}


