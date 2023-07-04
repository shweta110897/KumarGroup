package com.example.kumarGroup.ViewInquiry;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatViewInqGenCat;
import com.example.kumarGroup.R;

import java.util.List;

public class GeneralViewInquiryOneAdapter extends RecyclerView.Adapter<GeneralViewInquiryOneAdapter.ViewHolder> {

   Context context;
    List<CatViewInqGenCat>  catViewInqGenCats;

    public GeneralViewInquiryOneAdapter(Context context, List<CatViewInqGenCat> catViewInqGenCats) {
        this.context = context;
        this.catViewInqGenCats = catViewInqGenCats;
    }

    @NonNull
    @Override
    public GeneralViewInquiryOneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        GeneralViewInquiryOneAdapter.ViewHolder viewHolder = new GeneralViewInquiryOneAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralViewInquiryOneAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtWeekNameFollowUpRow.setText(catViewInqGenCats.get(position).getCat_list());
        holder.txtWeekValueFollowUpRow.setText(catViewInqGenCats.get(position).getCount());

        holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, GenViewInqTwoDataActivity.class);
                i.putExtra("catId_eid",catViewInqGenCats.get(position).getCat_id());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catViewInqGenCats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtWeekNameFollowUpRow,txtWeekValueFollowUpRow;
        LinearLayout lin_MainFollowUp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtWeekNameFollowUpRow=itemView.findViewById(R.id.txtWeekNameFollowUpRow);
            this.txtWeekValueFollowUpRow=itemView.findViewById(R.id.txtWeekValueFollowUpRow);
            this.lin_MainFollowUp=itemView.findViewById(R.id.lin_MainFollowUp);
        }
    }
}
