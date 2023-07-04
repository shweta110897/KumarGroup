package com.example.kumarGroup.ProfileVillage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.Catlist;
import com.example.kumarGroup.R;

import java.util.List;

public class CatlistAdapter extends RecyclerView.Adapter<CatlistAdapter.ViewHolder> {

    CatlistActivity activity;
    Context context;
    List<Catlist.Cat> cat;

    public CatlistAdapter(CatlistActivity activity, Context context, List<Catlist.Cat> cat) {
        this.activity = activity;
        this.context = context;
        this.cat = cat;


    }

    @NonNull
    @Override
    public CatlistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listitem = inflater.inflate(R.layout.month_week_day_row_follow_up,parent,false);
        CatlistAdapter.ViewHolder viewHolder = new CatlistAdapter.ViewHolder(listitem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatlistAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtWeekNameFollowUpRow.setText(cat.get(position).getCat_list());
        holder.txtWeekValueFollowUpRow.setText(cat.get(position).getCount());

        holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: CatId_Check" +cat.get(position).getCat_id());
                SharedPreferences sharedPreferences = context.getSharedPreferences("dealstage3cateid", context.MODE_PRIVATE);
                sharedPreferences.edit().putString("catId_eid", cat.get(position).getCat_id()).apply();

                Intent intent = new Intent(context, VillageListProfileActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


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
