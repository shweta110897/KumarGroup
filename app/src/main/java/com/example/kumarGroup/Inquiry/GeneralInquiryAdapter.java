package com.example.kumarGroup.Inquiry;

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

import com.example.kumarGroup.CatInquiryGen;
import com.example.kumarGroup.DealstageAK.DealstageMainActivity;
import com.example.kumarGroup.DealstageAK.DealstageMainNegotiationActivity;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ViewInquiry.ViewInqAllCatActivity;
import com.example.kumarGroup.ViewInquiryDealStage.DealViewMainActivity;
import com.example.kumarGroup.ViewInquiryDealStage.DealViewMainActivityNegotiation;
import com.example.kumarGroup.Village_List.Village_List_Activity;

import java.util.List;

public class GeneralInquiryAdapter extends RecyclerView.Adapter<GeneralInquiryAdapter.ViewHolder>
{
    Context context;
    List<CatInquiryGen> catInquiryGens;

    public GeneralInquiryAdapter(Context context, List<CatInquiryGen> catInquiryGens) {
        this.context = context;
        this.catInquiryGens = catInquiryGens;
    }

    @NonNull
    @Override
    public GeneralInquiryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        GeneralInquiryAdapter.ViewHolder viewHolder = new GeneralInquiryAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralInquiryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtWeekNameFollowUpRow.setText(catInquiryGens.get(position).getCat_list());
        holder.txtWeekValueFollowUpRow.setText(catInquiryGens.get(position).getCount());

        holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = context.getSharedPreferences("dealstage3cateid", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("catId_eid",catInquiryGens.get(position).getCat_id());
                editor.putString("catId_list",catInquiryGens.get(position).getCat_list());
                editor.apply();

                SharedPreferences sharedPreferences1 = context.getSharedPreferences("catid",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                editor1.putString("id",catInquiryGens.get(position).getCat_id());
                editor1.putString("model_name",catInquiryGens.get(position).getCat_list());
                editor1.apply();
                if (InquiryMainActivity.deal_stage_category_flag){
                    Log.d("Jemin", "onCreateViewHolder: GenerallnquiryAdapterCall-First if");

                    Intent i = new Intent(context, DealstageMainActivity.class);
                    i.putExtra("catId_eid", catInquiryGens.get(position).getCat_id());
                    context.startActivity(i);
                }

                else if (ViewInqAllCatActivity.deal_stage_viewINQ_flag){
                    Log.d("Jemin", "onCreateViewHolder: GenerallnquiryAdapterCall-Second if");

                    /*SharedPreferences sharedPreferences = context.getSharedPreferences("dealstage3cateid_ViewINQ", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("catId_eid",catInquiryGens.get(position).getCat_id());
                    editor.apply();*/

                    Intent i = new Intent(context, DealViewMainActivity.class);
//                    i.putExtra("catId_eid", catInquiryGens.get(position).getCat_id());
                    context.startActivity(i);
                }else if (ViewInqAllCatActivity.deal_stage_IDMunit_flag){
                    Log.d("Jemin", "onCreateViewHolder: GenerallnquiryAdapterCall-Second if1");

                    /*SharedPreferences sharedPreferences = context.getSharedPreferences("dealstage3cateid_ViewINQ", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("catId_eid",catInquiryGens.get(position).getCat_id());
                    editor.apply();*/

                    Intent i = new Intent(context, DealViewMainActivityNegotiation.class);
//                    i.putExtra("catId_eid", catInquiryGens.get(position).getCat_id());
                    context.startActivity(i);
                }else if (InquiryMainActivity.deal_stage_IDMunit_flag){
                    Log.d("Jemin", "onCreateViewHolder: GenerallnquiryAdapterCall-Second if2");

                    /*SharedPreferences sharedPreferences = context.getSharedPreferences("dealstage3cateid_ViewINQ", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("catId_eid",catInquiryGens.get(position).getCat_id());
                    editor.apply();*/

                    Intent i = new Intent(context, DealstageMainNegotiationActivity.class);
//                    i.putExtra("catId_eid", catInquiryGens.get(position).getCat_id());
                    context.startActivity(i);
                }else if(InquiryMainActivity.deal_stage_village_list || ViewInqAllCatActivity.deal_stage_Village_List){
                    Log.d("Jemin", "onCreateViewHolder: GenerallnquiryAdapterCall-forth if");
                    Intent intent = new Intent(context, Village_List_Activity.class);
                    context.startActivity(intent);

                } else {
                    Log.d("Jemin", "onCreateViewHolder: GenerallnquiryAdapterCall-Third if");
                    Intent i = new Intent(context, DisplayGeneralActivity.class);
                    i.putExtra("catId_eid", catInquiryGens.get(position).getCat_id());
                    context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return catInquiryGens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtWeekNameFollowUpRow,txtWeekValueFollowUpRow;
        LinearLayout lin_MainFollowUp;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.txtWeekNameFollowUpRow=itemView.findViewById(R.id.txtWeekNameFollowUpRow);
            this.txtWeekValueFollowUpRow=itemView.findViewById(R.id.txtWeekValueFollowUpRow);
            this.lin_MainFollowUp=itemView.findViewById(R.id.lin_MainFollowUp);
        }
    }
}
