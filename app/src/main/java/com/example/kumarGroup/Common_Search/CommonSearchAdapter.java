package com.example.kumarGroup.Common_Search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CommonSearch;
import com.example.kumarGroup.DealstageAK.DealstageRecyclerActivity;
import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ViewInquiryDealStage.DealstageRecyclerActivityViewINQ1;

import java.util.ArrayList;
import java.util.List;

public class CommonSearchAdapter extends RecyclerView.Adapter<CommonSearchAdapter.ViewHolder> implements Filterable {

    CommonSearchActivity activity;
    Context context;
    List<CommonSearch.commonSearch>Cat;
    List<CommonSearch.commonSearch> Cat_one;

    String click_id;
    public static boolean Common_Search_AutoFill_Search_Check = false;

    public CommonSearchAdapter(Context context,/* CommonSearchActivity activity,*/ List<CommonSearch.commonSearch> Cat) {
//        this.activity = activity;
        this.context = context;
        this.Cat = Cat;
        this.Cat_one = Cat;
    }

    @NonNull
    @Override
    public CommonSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.common_search,parent,false);
        CommonSearchAdapter.ViewHolder viewHolder = new CommonSearchAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommonSearchAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_name_show_rc_gv.setText("Name: "+Cat.get(position).getFname()+" "+Cat.get(position).getLname());

//        holder.tv_name_show_rc_gv.setText("Name: "+Cat.get(position).getFname());
        Log.d("TAG", "onBindViewHolder: Name_employee"+Cat.get(position).getFname());
        holder.tv_category_it_show_rc_gv.setText("Category: "+Cat.get(position).getCat_name());
        holder.tv_mobile_no_show_rc_gv.setText("Mobile: "+Cat.get(position).getMoblino());
        holder.tv_mobile_no_show_rc_gv_other.setText("Other Mobile: "+Cat.get(position).getOther_mobile());
//         holder.tv_Whapp_Number_show_rc_gv.setText("WhatsApp Number: "+Cat.get(position).getWhatsappno());
        holder.tv_village_show_rc_gv.setText("Village: "+Cat.get(position).getVilage());
        holder.tv_district_show_rc_gv.setText(Cat.get(position).getDesc().toString());
        holder.tv_NextDate_show_rc_gv.setText("Next Date: "+Cat.get(position).getVdate());
        holder.added_days.setText("Added :"+Cat.get(position).getAdded()+" Days");
        holder.source_ofinquire.setText("Source of Inquire : "+Cat.get(position).getSor_of_inq());
        holder.employe_name_ink.setText(Cat.get(position).getEmployee_name());
        holder.model_added.setText(String.valueOf(Cat.get(position).getModel_name()));
        holder.follow_type.setText(Cat.get(position).getFollow_up_type().toString());
        holder.latactivity.setText("Last Activity : "+Cat.get(position).getFollow_up_type());
        holder.file_count.setText("+ Files ( "+Cat.get(position).getFile_count()+" )");
        if (Cat.get(position).getCurrent_stage_name()==null){
            holder.textview_current_stage.setText("");
        }else {
            holder.textview_current_stage.setText(Cat.get(position).getCurrent_stage_name().toString());
            Log.e("curdealstage",Cat.get(position).getCurrent_stage_name().toString());

        }


        holder.payment_type.setText("Payment Type: "+Cat.get(position).getPayment_type());
        /*passing_type*/
//        if (mcatlist.get(position).getPassing_type()!=null || !mcatlist.get(position).getPassing_type().equals("")){
        holder.passing_type.setText(Cat.get(position).getPassing_type());
//            if (mcatlist.get(position).getPassing_type().toString().equals("Aggriculture")){
        if ( holder.passing_type.getText().toString().equals("Agriculture")){
//            holder.passing_type.setBackgroundColor(Color.parseColor(R.color.Green));
            holder.passing_type.setTextColor(context.getResources().getColor(R.color.Green));
        }else {
            holder.passing_type.setTextColor(context.getResources().getColor(R.color.Yellow));
        }
//        }
        holder.LinShowDetailRcGv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CommonSearchAdapter.Common_Search_AutoFill_Search_Check = true;

                click_id = Cat.get(position).getAutoid();

                if(Cat.get(position).getCurrent_stage_name()==null){
                    Toast.makeText(context.getApplicationContext(), "Deal Stage Is Null", Toast.LENGTH_SHORT).show();
                    CommonSearchAdapter.Common_Search_AutoFill_Search_Check = false;
                } else if (FoDashbord.Common_Search_Check && Cat.get(position).getCurrent_stage_name()!=null){
                    Log.d("TAG", "onClick: MyInquire"+FoDashbord.Common_Search_Check);
                    if (Cat.get(position).getCurrent_stage_name().equals("Not Attand")){
                        DealstageRecyclerActivity.notattend_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivity.class)
                                .putExtra("actionbar","Not attend").putExtra("click_id",click_id));


                    }else if (Cat.get(position).getCurrent_stage_name().equals("First Metting")){
                        DealstageRecyclerActivity.firstmeeting_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivity.class)
                                .putExtra("actionbar","first meeting").putExtra("click_id",click_id));
                    }else if (Cat.get(position).getCurrent_stage_name().equals("Make An Offer(HOT)") || Cat.get(position).getInq_type().equals("HOT")){
                        DealstageRecyclerActivity.hotInquiry_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivity.class)
                                .putExtra("actionbar","Make An Offer").putExtra("click_id",click_id));
                    }else if (Cat.get(position).getCurrent_stage_name().equals("Next Activity Plan(WARM)") || Cat.get(position).getInq_type().equals("WARM")){
                        DealstageRecyclerActivity.deal_nextactivityplanInquiry_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivity.class)
                                .putExtra("actionbar","Next Activity Plan").putExtra("click_id",click_id));
                    }else if (Cat.get(position).getCurrent_stage_name().equals("Second Metting(COLD)") || Cat.get(position).getInq_type().equals("COLD")){
                        DealstageRecyclerActivity.deal_coldInquiry_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivity.class)
                                .putExtra("actionbar","Second meeting").putExtra("click_id",click_id));
                    }else {
                        Toast.makeText(context.getApplicationContext(), "Deal Stage Is Null", Toast.LENGTH_SHORT).show();
                        CommonSearchAdapter.Common_Search_AutoFill_Search_Check = false;
                    }
                } else if(FoDashbord.Common_Search_Check == false && Cat.get(position).getCurrent_stage_name()!=null){
                    Log.d("TAG", "onClick: Check_id"+click_id);
                    if (Cat.get(position).getCurrent_stage_name().equals("Not Attand")){

                        DealstageRecyclerActivityViewINQ1.notattend_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivityViewINQ1.class)
                                .putExtra("actionbar","Not attend").putExtra("click_id",click_id));

                    }else if (Cat.get(position).getCurrent_stage_name().equals("First Metting")){
                        DealstageRecyclerActivityViewINQ1.firstmeeting_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivityViewINQ1.class)
                                .putExtra("actionbar","first meeting").putExtra("click_id",click_id));

                    }else if (Cat.get(position).getCurrent_stage_name().equals("Make An Offer(HOT)")|| Cat.get(position).getInq_type().equals("HOT")){
                        DealstageRecyclerActivityViewINQ1.hotInquiry_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivityViewINQ1.class)
                                .putExtra("actionbar","Make An Offer").putExtra("click_id",click_id));


                    }else if (Cat.get(position).getCurrent_stage_name().equals("Next Activity Plan(WARM)")|| Cat.get(position).getInq_type().equals("WARM")){
                        DealstageRecyclerActivityViewINQ1.deal_nextactivityplanInquiry_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivityViewINQ1.class)
                                .putExtra("actionbar","Next Activity Plan").putExtra("click_id",click_id));

                    }else if (Cat.get(position).getCurrent_stage_name().equals("Second Metting(COLD)")|| Cat.get(position).getInq_type().equals("COLD")){
                        DealstageRecyclerActivityViewINQ1.deal_coldInquiry_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivityViewINQ1.class)
                                .putExtra("actionbar","Second meeting").putExtra("click_id",click_id));

                    }else {
                        Toast.makeText(context.getApplicationContext(), "Deal Stage Is Null", Toast.LENGTH_SHORT).show();
                        CommonSearchAdapter.Common_Search_AutoFill_Search_Check = false;
                    }
                }else {
                    Toast.makeText(context.getApplicationContext(), "Deal Stage Is Null", Toast.LENGTH_SHORT).show();
                    CommonSearchAdapter.Common_Search_AutoFill_Search_Check = false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Cat.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name_show_rc_gv,tv_category_it_show_rc_gv,tv_mobile_no_show_rc_gv,tv_Whapp_Number_show_rc_gv,
                tv_village_show_rc_gv,tv_district_show_rc_gv,tv_NextDate_show_rc_gv,tv_HotCold_show_rc_gv
                ,added_days,model_added,source_ofinquire,employe_name_ink,follow_type,latactivity,new_design,file_count,
                txtremider,txtdeal,delivery_date,textview_current_stage,textview_Skip,passing_type,payment_type,tv_mobile_no_show_rc_gv_other;

        LinearLayout lin_detailrc,image_button,ringid,resentNote;
        RelativeLayout LinShowDetailRcGv;
        ImageView lin_MDY_call,lin_MDY_sms,lin_MDY_whapp,remider,addfile,design_deal,hot_cold_harm,
                homeIcon;
        CardView newdesign_color;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_name_show_rc_gv= itemView.findViewById(R.id.tv_name_show_rc_gv);
            this.tv_category_it_show_rc_gv= itemView.findViewById(R.id.tv_category_it_show_rc_gv);
            this.tv_mobile_no_show_rc_gv= itemView.findViewById(R.id.tv_mobile_no_show_rc_gv);
            this.tv_mobile_no_show_rc_gv_other= itemView.findViewById(R.id.tv_mobile_no_show_rc_gv_other);
//            this.tv_Whapp_Number_show_rc_gv= itemView.findViewById(R.id.tv_Whapp_Number_show_rc_gv);
            this.tv_village_show_rc_gv= itemView.findViewById(R.id.tv_village_show_rc_gv);
            this.tv_district_show_rc_gv= itemView.findViewById(R.id.tv_district_show_rc_gv);

            this.tv_NextDate_show_rc_gv= itemView.findViewById(R.id.tv_NextDate_show_rc_gv);
            this.tv_HotCold_show_rc_gv= itemView.findViewById(R.id.tv_HotCold_show_rc_gv);

            this.LinShowDetailRcGv= itemView.findViewById(R.id.LinShowDetailRcGv);
            this.lin_detailrc= itemView.findViewById(R.id.lin_detailrc);

            //   this.lin_MDY_share= itemView.findViewById(R.id.lin_MDY_share);
            this.lin_MDY_call= itemView.findViewById(R.id.lin_MDY_call);
            this.lin_MDY_sms= itemView.findViewById(R.id.lin_MDY_sms);
            this.lin_MDY_whapp= itemView.findViewById(R.id.lin_MDY_whapp);
            this.added_days= itemView.findViewById(R.id.added_days);
            this.model_added= itemView.findViewById(R.id.model_added);
            this.source_ofinquire= itemView.findViewById(R.id.source_ofinquire);
            this.employe_name_ink= itemView.findViewById(R.id.employe_name_ink);
            this.remider= itemView.findViewById(R.id.remider);
            this.follow_type= itemView.findViewById(R.id.follow_type);
            this.latactivity= itemView.findViewById(R.id.latactivity);
            this.addfile= itemView.findViewById(R.id.addfile);
            this.design_deal= itemView.findViewById(R.id.design_deal);
            this.new_design= itemView.findViewById(R.id.new_design);
            this.hot_cold_harm= itemView.findViewById(R.id.hot_cold_harm);
            this.image_button= itemView.findViewById(R.id.image_button);
            this.homeIcon= itemView.findViewById(R.id.homeIcon);
            this.file_count= itemView.findViewById(R.id.file_count);
            this.newdesign_color= itemView.findViewById(R.id.newdesign_color);
            this.txtremider= itemView.findViewById(R.id.txtremider);
            this.txtdeal= itemView.findViewById(R.id.txtdeal);
            this.ringid= itemView.findViewById(R.id.ringid);
            this.resentNote= itemView.findViewById(R.id.resentNote);
            this.delivery_date= itemView.findViewById(R.id.delivery_date);
            this.textview_current_stage= itemView.findViewById(R.id.textview_current_stage);
            this.passing_type= itemView.findViewById(R.id.passing_type);
            this.payment_type= itemView.findViewById(R.id.payment_type);
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();
                Log.d("TAG", "performFiltering: "+charString);

                if (charString.isEmpty()) {
                    //  catShowRCGVS_one = catShowRCGVS;
                    // FilterdlistData = listData;
                    Cat = Cat_one;
                } else {

                    List<CommonSearch.commonSearch> filteredList = new ArrayList<>();

                    for (CommonSearch.commonSearch row : Cat_one) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName =row.getFname();
                        String stradded =row.getAdded();
                        String strMobile =row.getMoblino();
                        String strVName =row.getVilage();
                        //String strLName =row.getLname();
                        String strWtaNumber =row.getWhatsappno();
                        String strModel = String.valueOf(row.getModel_name());
                        String strPaymentType =row.getPayment_type();
                        String strPasingType =row.getPassing_type();
                        String strDealStage= (String) row.getCurrent_stage_name();
                        //   Log.d("TAG", "Data: "+row);

                        if(strFName == null)
                            strFName = " ";
                        if(strDealStage == null)
                            strDealStage = " ";
                        if(strMobile == null)
                            strMobile = " ";
                        if(strVName == null)
                            strVName = " ";
                       /* if(strLName == null)
                            strLName = " ";
*/
                        if(strWtaNumber == null)
                            strWtaNumber = " ";

                        if(strModel == null)
                            strModel = " ";

                        if(strPaymentType == null)
                            strPaymentType = " ";
                        if(strPasingType == null)
                            strPasingType = " ";
                        if(stradded == null)
                            stradded = " ";


                        if (strFName.toLowerCase().contains(charString.toLowerCase())
                                || strMobile.toLowerCase().contains(charString.toLowerCase())
                                || strWtaNumber.toLowerCase().contains(charString.toLowerCase())
                                || strVName.toLowerCase().contains(charString.toLowerCase())
                                || strPaymentType.toLowerCase().contains(charString.toLowerCase())
                                || strPasingType.toLowerCase().contains(charString.toLowerCase())
                                || strDealStage.toLowerCase().contains(charString.toLowerCase())
                                || stradded.toLowerCase().contains(charString.toLowerCase())
                                || strModel.toLowerCase().contains(charString.toLowerCase())
                            // || strLName.toLowerCase().contains(charString.toLowerCase())
                        )
                        {
                            filteredList.add(row);
                        }
                    }
                    Cat = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = Cat;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Cat = (List<CommonSearch.commonSearch>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public Filter getFilter1() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();
                Log.d("TAG", "performFiltering: "+charString);

                if (charString.isEmpty()) {
                    Cat = Cat_one;
                } else {

                    List<CommonSearch.commonSearch> filteredList = new ArrayList<>();

                    for (CommonSearch.commonSearch row : Cat_one) {

                        String strAded =row.getAdded();
                        if(strAded == null)
                            strAded = " ";

                        if (Integer.parseInt(strAded.toLowerCase())>Integer.parseInt(charString.toLowerCase())-90 && Integer.parseInt(strAded.toLowerCase())<=Integer.parseInt(charString.toLowerCase()) )
                        {
                            filteredList.add(row);
                        }
                    }
                    Cat = filteredList;
                    Log.d("Catsize", String.valueOf(Cat.size()));
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = Cat;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Cat = (List<CommonSearch.commonSearch>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
