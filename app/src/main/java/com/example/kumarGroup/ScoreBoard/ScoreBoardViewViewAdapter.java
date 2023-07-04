package com.example.kumarGroup.ScoreBoard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.R;
import com.example.kumarGroup.ScoreBoardView;

import java.util.List;

public class ScoreBoardViewViewAdapter extends RecyclerView.Adapter<ScoreBoardViewViewAdapter.ViewHolder> {

    ScoreBoardViewViewActivity activity;
    Context context;
    List<ScoreBoardView.Cat> cat1;


    public ScoreBoardViewViewAdapter(ScoreBoardViewViewActivity activity,Context context,List<ScoreBoardView.Cat> cat1) {
        Log.d("TAG", "ScoreBoardViewViewAdapter: checheuhjsf ");
        this.activity = activity;
        this.context = context;
        this.cat1 = cat1;
    }

    @NonNull
    @Override
    public ScoreBoardViewViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View listItem = inflater.inflate(R.layout.scoreboaedview_viewdesign, parent, false);
//        Log.d("Jemin", "onCreateViewHolder: GenerallnquiryAdapterCall");
//        ScoreBoardViewViewAdapter.ViewHolder viewHolder = new ScoreBoardViewViewAdapter.ViewHolder(listItem);
//        return viewHolder;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scoreboaedview_viewdesign,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreBoardViewViewAdapter.ViewHolder holder, int position) {

        Log.d("TAG", "onBindViewHolder: Check_type "+ position + "\n" +cat1.get(position).getCategory());

        holder.txtDisplayName.setVisibility(View.GONE);
        holder.txtDisplayCategory.setVisibility(View.GONE);
        holder.txtDisplayEmpName.setVisibility(View.GONE);
        holder.txtDisplayMobile.setVisibility(View.GONE);
        holder.txtDisplayWhatsAppNumber.setVisibility(View.GONE);
        holder.txtDisplayDescription.setVisibility(View.GONE);
        holder.txtstate.setVisibility(View.GONE);
        holder.txtCityName.setVisibility(View.GONE);
        holder.txtdistrict.setVisibility(View.GONE);
        holder.txtDisplayVillage.setVisibility(View.GONE);
        holder.txtLoginEmployee.setVisibility(View.GONE);
        holder.txtDate.setVisibility(View.GONE);
        holder.txtEnteryDate.setVisibility(View.GONE);
        holder.txt_DealPrice.setVisibility(View.GONE);
        holder.txtFollowuptype.setVisibility(View.GONE);
        holder.txtDisplayNextDate.setVisibility(View.GONE);
        holder.txtDisplayInquiryType.setVisibility(View.GONE);
        holder.txtWorkTypeService.setVisibility(View.GONE);
        holder.txtModelName.setVisibility(View.GONE);
        holder.txtDelivery.setVisibility(View.GONE);
        holder.txtSellLost.setVisibility(View.GONE);
        holder.txtSellModel.setVisibility(View.GONE);
        holder.txtDropInquire.setVisibility(View.GONE);
        holder.txtCgst.setVisibility(View.GONE);
        holder.txtSgst.setVisibility(View.GONE);
        holder.txtTehsil.setVisibility(View.GONE);
        holder.txtMakeName.setVisibility(View.GONE);
        holder.txtModelYear.setVisibility(View.GONE);
        holder.txtSourceOfInquire.setVisibility(View.GONE);
        holder.txtDeliveryModelName.setVisibility(View.GONE);

        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("DataBank")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtDisplayCategory.setVisibility(View.VISIBLE);
            holder.txtDisplayEmpName.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtDisplayWhatsAppNumber.setVisibility(View.VISIBLE);
            holder.txtDisplayDescription.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtLoginEmployee.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);


            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getFname()+" "+cat1.get(position).getLname());
            holder.txtDisplayCategory.setText("Category name: "+cat1.get(position).getCat_name());
            holder.txtDisplayEmpName.setText("Employee Name: "+cat1.get(position).getEname());
            holder.txtDisplayMobile.setText("Mobile: "+cat1.get(position).getMoblino());
            holder.txtDisplayWhatsAppNumber.setText("Whats App Number: "+cat1.get(position).getWhatsappno());
            holder.txtDisplayDescription.setText("Description: "+cat1.get(position).getDescription());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVilage());
            holder.txtLoginEmployee.setText("Login Employee: "+cat1.get(position).getLoginname());
            holder.txtEnteryDate.setText("Entery Date: "+cat1.get(position).getCuu_date());
        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("InquiryBank")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtDisplayCategory.setVisibility(View.VISIBLE);
            holder.txtDisplayEmpName.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtDisplayWhatsAppNumber.setVisibility(View.VISIBLE);
            holder.txtDisplayDescription.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtLoginEmployee.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
            holder.txtFollowuptype.setVisibility(View.VISIBLE);
            holder.txtDisplayNextDate.setVisibility(View.VISIBLE);
            holder.txtDisplayInquiryType.setVisibility(View.VISIBLE);


            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getFname()+cat1.get(position).getLname());
            holder.txtDisplayCategory.setText("Category name: "+cat1.get(position).getCat_name());
            holder.txtDisplayEmpName.setText("Employee Name: "+cat1.get(position).getEname());
            holder.txtDisplayMobile.setText("Mobile: "+cat1.get(position).getMoblino());
            holder.txtDisplayWhatsAppNumber.setText("Whats App Number: "+cat1.get(position).getWhatsappno());
            holder.txtDisplayDescription.setText("Description: "+cat1.get(position).getDescription());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVilage());
            holder.txtLoginEmployee.setText("Login Employee: "+cat1.get(position).getLoginname());
            holder.txtEnteryDate.setText("Entery Date: "+cat1.get(position).getCuu_date());
            holder.txtFollowuptype.setText("Follow Up Type: "+cat1.get(position).getFollow_up_type());
            holder.txtDisplayNextDate.setText("Next Date: "+cat1.get(position).getNext_date());
            holder.txtDisplayInquiryType.setText("Inquire Type: "+cat1.get(position).getType_inq());
        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("services")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtstate.setVisibility(View.VISIBLE);
            holder.txtCityName.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtdistrict.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtCgst.setVisibility(View.VISIBLE);
            holder.txtSgst.setVisibility(View.VISIBLE);
            holder.txtWorkTypeService.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
            holder.txt_DealPrice.setVisibility(View.VISIBLE);

            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getCname());
            holder.txtstate.setText("State: "+cat1.get(position).getState());
            holder.txtCityName.setText("City: "+cat1.get(position).getCity());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVillage());
            holder.txtdistrict.setText("Distric: "+cat1.get(position).getDistric());
            holder.txtDisplayMobile.setText("Mobile Number: "+cat1.get(position).getMobile());
            holder.txtCgst.setText("C Gst: "+cat1.get(position).getCgst_val());
            holder.txtSgst.setText("S Gst: "+cat1.get(position).getSgst_val());
            holder.txtWorkTypeService.setText("Work Type Service: "+cat1.get(position).getWork_ser());
            holder.txtEnteryDate.setText("Entry Date: "+cat1.get(position).getEntry_date());
            holder.txt_DealPrice.setText("Deal Price: "+cat1.get(position).getDeal_price());


        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("complain")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtstate.setVisibility(View.VISIBLE);
            holder.txtCityName.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtdistrict.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtCgst.setVisibility(View.VISIBLE);
            holder.txtSgst.setVisibility(View.VISIBLE);
            holder.txtWorkTypeService.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
            holder.txt_DealPrice.setVisibility(View.VISIBLE);


            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getCname());
            holder.txtstate.setText("State: "+cat1.get(position).getState());
            holder.txtCityName.setText("City: "+cat1.get(position).getCity());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVillage());
            holder.txtdistrict.setText("Distric: "+cat1.get(position).getDistric());
            holder.txtDisplayMobile.setText("Mobile Number: "+cat1.get(position).getMobile());
            holder.txtCgst.setText("C Gst: "+cat1.get(position).getCgst_val());
            holder.txtSgst.setText("S Gst: "+cat1.get(position).getSgst_val());
            holder.txtWorkTypeService.setText("Work Type Service: "+cat1.get(position).getWork_ser());
            holder.txtEnteryDate.setText("Entry Date: "+cat1.get(position).getEntry_date());
            holder.txt_DealPrice.setText("Deal Price: "+cat1.get(position).getDeal_price());
        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("healthcheckup")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtstate.setVisibility(View.VISIBLE);
            holder.txtCityName.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtdistrict.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtCgst.setVisibility(View.VISIBLE);
            holder.txtSgst.setVisibility(View.VISIBLE);
            holder.txtWorkTypeService.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
            holder.txt_DealPrice.setVisibility(View.VISIBLE);


            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getCname());
            holder.txtstate.setText("State: "+cat1.get(position).getState());
            holder.txtCityName.setText("City: "+cat1.get(position).getCity());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVillage());
            holder.txtdistrict.setText("Distric: "+cat1.get(position).getDistric());
            holder.txtDisplayMobile.setText("Mobile Number: "+cat1.get(position).getMobile());
            holder.txtCgst.setText("C Gst: "+cat1.get(position).getCgst_val());
            holder.txtSgst.setText("S Gst: "+cat1.get(position).getSgst_val());
            holder.txtWorkTypeService.setText("Work Type Service: "+cat1.get(position).getWork_ser());
            holder.txtEnteryDate.setText("Entry Date: "+cat1.get(position).getEntry_date());
            holder.txt_DealPrice.setText("Deal Price: "+cat1.get(position).getDeal_price());
        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("installation")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtstate.setVisibility(View.VISIBLE);
            holder.txtCityName.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtdistrict.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtCgst.setVisibility(View.VISIBLE);
            holder.txtSgst.setVisibility(View.VISIBLE);
            holder.txtWorkTypeService.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
            holder.txt_DealPrice.setVisibility(View.VISIBLE);


            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getCname());
            holder.txtstate.setText("State: "+cat1.get(position).getState());
            holder.txtCityName.setText("City: "+cat1.get(position).getCity());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVillage());
            holder.txtdistrict.setText("Distric: "+cat1.get(position).getDistric());
            holder.txtDisplayMobile.setText("Mobile Number: "+cat1.get(position).getMobile());
            holder.txtCgst.setText("C Gst: "+cat1.get(position).getCgst_val());
            holder.txtSgst.setText("S Gst: "+cat1.get(position).getSgst_val());
            holder.txtWorkTypeService.setText("Work Type Service: "+cat1.get(position).getWork_ser());
            holder.txtEnteryDate.setText("Entry Date: "+cat1.get(position).getEntry_date());
            holder.txt_DealPrice.setText("Deal Price: "+cat1.get(position).getDeal_price());
        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("afterdeliverydiscuss")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtstate.setVisibility(View.VISIBLE);
            holder.txtCityName.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtdistrict.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtCgst.setVisibility(View.VISIBLE);
            holder.txtSgst.setVisibility(View.VISIBLE);
            holder.txtWorkTypeService.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
            holder.txt_DealPrice.setVisibility(View.VISIBLE);


            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getCname());
            holder.txtstate.setText("State: "+cat1.get(position).getState());
            holder.txtCityName.setText("City: "+cat1.get(position).getCity());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVillage());
            holder.txtdistrict.setText("Distric: "+cat1.get(position).getDistric());
            holder.txtDisplayMobile.setText("Mobile Number: "+cat1.get(position).getMobile());
            holder.txtCgst.setText("C Gst: "+cat1.get(position).getCgst_val());
            holder.txtSgst.setText("S Gst: "+cat1.get(position).getSgst_val());
            holder.txtWorkTypeService.setText("Work Type Service: "+cat1.get(position).getWork_ser());
            holder.txtEnteryDate.setText("Entry Date: "+cat1.get(position).getEntry_date());
            holder.txt_DealPrice.setText("Deal Price: "+cat1.get(position).getDeal_price());
        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("counter sell")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtstate.setVisibility(View.VISIBLE);
            holder.txtCityName.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtdistrict.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtCgst.setVisibility(View.VISIBLE);
            holder.txtSgst.setVisibility(View.VISIBLE);
            holder.txtWorkTypeService.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
            holder.txt_DealPrice.setVisibility(View.VISIBLE);


            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getCname());
            holder.txtstate.setText("State: "+cat1.get(position).getState());
            holder.txtCityName.setText("City: "+cat1.get(position).getCity());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVillage());
            holder.txtdistrict.setText("Distric: "+cat1.get(position).getDistric());
            holder.txtDisplayMobile.setText("Mobile Number: "+cat1.get(position).getMobile());
            holder.txtCgst.setText("C Gst: "+cat1.get(position).getCgst_val());
            holder.txtSgst.setText("S Gst: "+cat1.get(position).getSgst_val());
            holder.txtWorkTypeService.setText("Work Type Service: "+cat1.get(position).getWork_ser());
            holder.txtEnteryDate.setText("Entry Date: "+cat1.get(position).getEntry_date());
            holder.txt_DealPrice.setText("Deal Price: "+cat1.get(position).getDeal_price());
        }
        if (cat1.get(position).getCategory()!=null  &&cat1.get(position).getCategory().equals("job card")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtstate.setVisibility(View.VISIBLE);
            holder.txtCityName.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtdistrict.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtCgst.setVisibility(View.VISIBLE);
            holder.txtSgst.setVisibility(View.VISIBLE);
            holder.txtWorkTypeService.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
            holder.txt_DealPrice.setVisibility(View.VISIBLE);


            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getCname());
            holder.txtstate.setText("State: "+cat1.get(position).getState());
            holder.txtCityName.setText("City: "+cat1.get(position).getCity());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVillage());
            holder.txtdistrict.setText("Distric: "+cat1.get(position).getDistric());
            holder.txtDisplayMobile.setText("Mobile Number: "+cat1.get(position).getMobile());
            holder.txtCgst.setText("C Gst: "+cat1.get(position).getCgst_val());
            holder.txtSgst.setText("S Gst: "+cat1.get(position).getSgst_val());
            holder.txtWorkTypeService.setText("Work Type Service: "+cat1.get(position).getWork_ser());
            holder.txtEnteryDate.setText("Entry Date: "+cat1.get(position).getEntry_date());
            holder.txt_DealPrice.setText("Deal Price: "+cat1.get(position).getDeal_price());
        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("jobcard feedback Call")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtstate.setVisibility(View.VISIBLE);
            holder.txtCityName.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtdistrict.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtCgst.setVisibility(View.VISIBLE);
            holder.txtSgst.setVisibility(View.VISIBLE);
            holder.txtWorkTypeService.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
            holder.txt_DealPrice.setVisibility(View.VISIBLE);


            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getCname());
            holder.txtstate.setText("State: "+cat1.get(position).getState());
            holder.txtCityName.setText("City: "+cat1.get(position).getCity());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVillage());
            holder.txtdistrict.setText("Distric: "+cat1.get(position).getDistric());
            holder.txtDisplayMobile.setText("Mobile Number: "+cat1.get(position).getMobile());
            holder.txtCgst.setText("C Gst: "+cat1.get(position).getCgst_val());
            holder.txtSgst.setText("S Gst: "+cat1.get(position).getSgst_val());
            holder.txtWorkTypeService.setText("Work Type Service: "+cat1.get(position).getWork_ser());
            holder.txtEnteryDate.setText("Entry Date: "+cat1.get(position).getEntry_date());
            holder.txt_DealPrice.setText("Deal Price: "+cat1.get(position).getDeal_price());
        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("Call")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtDisplayCategory.setVisibility(View.VISIBLE);
            holder.txtDisplayEmpName.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtDisplayWhatsAppNumber.setVisibility(View.VISIBLE);
            holder.txtDisplayDescription.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtLoginEmployee.setVisibility(View.VISIBLE);
            holder.txtMakeName.setVisibility(View.VISIBLE);
            holder.txtModelName.setVisibility(View.VISIBLE);
            holder.txtModelYear.setVisibility(View.VISIBLE);
            holder.txtSourceOfInquire.setVisibility(View.VISIBLE);



            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getFname()+cat1.get(position).getLname());
            holder.txtDisplayCategory.setText("Category name: "+cat1.get(position).getCat_name());
            holder.txtDisplayEmpName.setText("Employee Name: "+cat1.get(position).getEname());
            holder.txtDisplayMobile.setText("Mobile: "+cat1.get(position).getMoblino());
            holder.txtDisplayWhatsAppNumber.setText("Whats App Number: "+cat1.get(position).getWhatsappno());
            holder.txtDisplayDescription.setText("Description: "+cat1.get(position).getDescription());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVilage());
            holder.txtLoginEmployee.setText("Login Employee: "+cat1.get(position).getLoginname());
            holder.txtEnteryDate.setText("Entery Date: "+cat1.get(position).getCuu_date());
            holder.txtMakeName.setText("Make Name: "+cat1.get(position).getMake_name());
            holder.txtModelName.setText("Model Name: "+cat1.get(position).getModel());
            holder.txtModelYear.setText("Model Year: "+cat1.get(position).getModel_y());
            holder.txtSourceOfInquire.setText("Source Of Inquire: "+cat1.get(position).getSor_of_inq());

        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("Sms")){
            Log.d("TAG", "onBindViewHolder: Check_sms "+cat1.get(0).getFname());
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtTehsil.setVisibility(View.VISIBLE);
            holder.txtDisplayWhatsAppNumber.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtdistrict.setVisibility(View.VISIBLE);
            holder.txtMakeName.setVisibility(View.VISIBLE);
            holder.txtModelName.setVisibility(View.VISIBLE);
            holder.txtModelYear.setVisibility(View.VISIBLE);
            holder.txtDisplayEmpName.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtSourceOfInquire.setVisibility(View.VISIBLE);


            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getFname()+cat1.get(position).getLname());
            holder.txtDisplayCategory.setText("Category name: "+cat1.get(position).getCat_name());
            holder.txtDisplayEmpName.setText("Employee Name: "+cat1.get(position).getEname());
            holder.txtDisplayMobile.setText("Mobile: "+cat1.get(position).getMoblino());
            holder.txtdistrict.setText("District: "+cat1.get(position).getDistrict());
            holder.txtTehsil.setText("Tehsil: "+cat1.get(position).getTehsil());
            holder.txtMakeName.setText("Make Name: "+cat1.get(position).getMake_name());
            holder.txtModelName.setText("Model Name: "+cat1.get(position).getModel());
            holder.txtModelYear.setText("Model Year: "+cat1.get(position).getModel_y());
            holder.txtDisplayWhatsAppNumber.setText("Whats App Number: "+cat1.get(position).getWhatsappno());
            holder.txtDisplayDescription.setText("Description: "+cat1.get(position).getDescription());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVilage());
            holder.txtLoginEmployee.setText("Login Employee: "+cat1.get(position).getLoginname());
            holder.txtEnteryDate.setText("Entery Date: "+cat1.get(position).getCuu_date());
            holder.txtFollowuptype.setText("Follow Up Type: "+cat1.get(position).getFollow_up_type());
            holder.txtDisplayNextDate.setText("Next Date: "+cat1.get(position).getNext_date());
            holder.txtDisplayInquiryType.setText("Inquire Type: "+cat1.get(position).getType_inq());
            holder.txtSourceOfInquire.setText("Source Of Inquire: "+cat1.get(position).getSor_of_inq());
        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("whatsapp")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtDisplayCategory.setVisibility(View.VISIBLE);
            holder.txtDisplayEmpName.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtDisplayWhatsAppNumber.setVisibility(View.VISIBLE);
            holder.txtDisplayDescription.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtLoginEmployee.setVisibility(View.VISIBLE);
            holder.txtMakeName.setVisibility(View.VISIBLE);
            holder.txtModelName.setVisibility(View.VISIBLE);
            holder.txtModelYear.setVisibility(View.VISIBLE);
            holder.txtSourceOfInquire.setVisibility(View.VISIBLE);



            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getFname()+cat1.get(position).getLname());
            holder.txtDisplayCategory.setText("Category name: "+cat1.get(position).getCat_name());
            holder.txtDisplayEmpName.setText("Employee Name: "+cat1.get(position).getEname());
            holder.txtDisplayMobile.setText("Mobile: "+cat1.get(position).getMoblino());
            holder.txtDisplayWhatsAppNumber.setText("Whats App Number: "+cat1.get(position).getWhatsappno());
            holder.txtDisplayDescription.setText("Description: "+cat1.get(position).getDescription());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVilage());
            holder.txtLoginEmployee.setText("Login Employee: "+cat1.get(position).getLoginname());
            holder.txtEnteryDate.setText("Entery Date: "+cat1.get(position).getCuu_date());
            holder.txtMakeName.setText("Make Name: "+cat1.get(position).getMake_name());
            holder.txtModelName.setText("Model Name: "+cat1.get(position).getModel());
            holder.txtModelYear.setText("Model Year: "+cat1.get(position).getModel_y());
            holder.txtSourceOfInquire.setText("Source Of Inquire: "+cat1.get(position).getSor_of_inq());
        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("delivery")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtDisplayCategory.setVisibility(View.VISIBLE);
            holder.txtDisplayEmpName.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtDisplayWhatsAppNumber.setVisibility(View.VISIBLE);
            holder.txtDisplayDescription.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtLoginEmployee.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
            holder.txtDelivery.setVisibility(View.VISIBLE);
            holder.txtDeliveryModelName.setVisibility(View.VISIBLE);


            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getFname()+cat1.get(position).getLname());
            holder.txtDisplayCategory.setText("Category name: "+cat1.get(position).getCat_name());
            holder.txtDisplayEmpName.setText("Employee Name: "+cat1.get(position).getEname());
            holder.txtDisplayMobile.setText("Mobile: "+cat1.get(position).getMoblino());
            holder.txtDisplayWhatsAppNumber.setText("Whats App Number: "+cat1.get(position).getWhatsappno());
            holder.txtDisplayDescription.setText("Description: "+cat1.get(position).getDescription());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVilage());
            holder.txtLoginEmployee.setText("Login Employee: "+cat1.get(position).getLoginname());
            holder.txtEnteryDate.setText("Entery Date: "+cat1.get(position).getCuu_date());
            holder.txtFollowuptype.setText("Follow Up Type: "+cat1.get(position).getFollow_up_type());
            holder.txtDisplayNextDate.setText("Next Date: "+cat1.get(position).getNext_date());
            holder.txtDisplayInquiryType.setText("Inquire Type: "+cat1.get(position).getType_inq());
            holder.txtDelivery.setText("Delivery: "+cat1.get(position).getDelivery());
            holder.txtDeliveryModelName.setText("Delivery Model Name: "+cat1.get(position).getDe_model_name());

        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("selllost")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtDisplayCategory.setVisibility(View.VISIBLE);
            holder.txtDisplayEmpName.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtDisplayWhatsAppNumber.setVisibility(View.VISIBLE);
            holder.txtDisplayDescription.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtLoginEmployee.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
            holder.txtDelivery.setVisibility(View.VISIBLE);
            holder.txtDeliveryModelName.setVisibility(View.VISIBLE);


            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getFname()+cat1.get(position).getLname());
            holder.txtDisplayCategory.setText("Category name: "+cat1.get(position).getCat_name());
            holder.txtDisplayEmpName.setText("Employee Name: "+cat1.get(position).getEname());
            holder.txtDisplayMobile.setText("Mobile: "+cat1.get(position).getMoblino());
            holder.txtDisplayWhatsAppNumber.setText("Whats App Number: "+cat1.get(position).getWhatsappno());
            holder.txtDisplayDescription.setText("Description: "+cat1.get(position).getDescription());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVilage());
            holder.txtLoginEmployee.setText("Login Employee: "+cat1.get(position).getLoginname());
            holder.txtEnteryDate.setText("Entery Date: "+cat1.get(position).getCuu_date());
            holder.txtFollowuptype.setText("Follow Up Type: "+cat1.get(position).getFollow_up_type());
            holder.txtDisplayNextDate.setText("Next Date: "+cat1.get(position).getNext_date());
            holder.txtDisplayInquiryType.setText("Inquire Type: "+cat1.get(position).getType_inq());
            holder.txtDelivery.setText("Sell Lost: "+cat1.get(position).getSell_lost());
            holder.txtDeliveryModelName.setText("Sell Model Name: "+cat1.get(position).getSell_model());
        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("dropInquiry")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtDisplayCategory.setVisibility(View.VISIBLE);
            holder.txtDisplayEmpName.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtDisplayWhatsAppNumber.setVisibility(View.VISIBLE);
            holder.txtDisplayDescription.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtLoginEmployee.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
            holder.txtDelivery.setVisibility(View.VISIBLE);


            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getFname()+cat1.get(position).getLname());
            holder.txtDisplayCategory.setText("Category name: "+cat1.get(position).getCat_name());
            holder.txtDisplayEmpName.setText("Employee Name: "+cat1.get(position).getEname());
            holder.txtDisplayMobile.setText("Mobile: "+cat1.get(position).getMoblino());
            holder.txtDisplayWhatsAppNumber.setText("Whats App Number: "+cat1.get(position).getWhatsappno());
            holder.txtDisplayDescription.setText("Description: "+cat1.get(position).getDescription());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVilage());
            holder.txtLoginEmployee.setText("Login Employee: "+cat1.get(position).getLoginname());
            holder.txtEnteryDate.setText("Entery Date: "+cat1.get(position).getCuu_date());
            holder.txtFollowuptype.setText("Follow Up Type: "+cat1.get(position).getFollow_up_type());
            holder.txtDisplayNextDate.setText("Next Date: "+cat1.get(position).getNext_date());
            holder.txtDisplayInquiryType.setText("Inquire Type: "+cat1.get(position).getType_inq());
            holder.txtDelivery.setText("Drop Inquire: "+cat1.get(position).getDrop_inq());
        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("Document collection")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtstate.setVisibility(View.VISIBLE);
            holder.txtCityName.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtdistrict.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtCgst.setVisibility(View.VISIBLE);
            holder.txtSgst.setVisibility(View.VISIBLE);
            holder.txtWorkTypeService.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
            holder.txt_DealPrice.setVisibility(View.VISIBLE);
            holder.txtSellLost.setVisibility(View.VISIBLE);
            holder.txtDropInquire.setVisibility(View.VISIBLE);
            holder.txtDisplayCategory.setVisibility(View.VISIBLE);
            holder.txtDisplayDescription.setVisibility(View.VISIBLE);
            holder.txtDisplayWhatsAppNumber.setVisibility(View.VISIBLE);

            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getCname());
            holder.txtDisplayCategory.setText("Category name: "+cat1.get(position).getCatname());
            holder.txtDisplayDescription.setText("Description: "+cat1.get(position).getDesc());
            holder.txtstate.setText("State: "+cat1.get(position).getState());
            holder.txtCityName.setText("City: "+cat1.get(position).getCity());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVillage());
            holder.txtdistrict.setText("Distric: "+cat1.get(position).getDistric());
            holder.txtDisplayMobile.setText("Mobile Number: "+cat1.get(position).getMno());
            holder.txtDisplayWhatsAppNumber.setText("Whats App Number: "+cat1.get(position).getWho());
            holder.txtCgst.setText("C Gst: "+cat1.get(position).getCgst_val());
            holder.txtSgst.setText("S Gst: "+cat1.get(position).getSgst_val());
            holder.txtWorkTypeService.setText("Work Type Service: "+cat1.get(position).getWork_ser());
            holder.txtEnteryDate.setText("Entry Date: "+cat1.get(position).getEntry_date());
            holder.txt_DealPrice.setText("Deal Price: "+cat1.get(position).getDeal_price());
            holder.txtModelName.setText("Deal Model Name: "+cat1.get(position).getDe_model_name());
            holder.txtDelivery.setText("Delivery: "+cat1.get(position).getDelivery());
            holder.txtSellLost.setText("Sell Lost: "+cat1.get(position).getSell_lost());
            holder.txtSellModel.setText("Sell Model: "+cat1.get(position).getSell_model());
            holder.txtDropInquire.setText("Drop Inquire: "+cat1.get(position).getDrop_inq());
        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("New Complain")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtDisplayCategory.setVisibility(View.VISIBLE);
            holder.txtDisplayEmpName.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtDisplayWhatsAppNumber.setVisibility(View.VISIBLE);
            holder.txtDisplayDescription.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtLoginEmployee.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
            holder.txtstate.setVisibility(View.VISIBLE);
            holder.txtCityName.setVisibility(View.VISIBLE);
            holder.txtdistrict.setVisibility(View.VISIBLE);
            holder.txtTehsil.setVisibility(View.VISIBLE);


            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getFname()+cat1.get(position).getLname());
            holder.txtDisplayCategory.setText("Category name: "+cat1.get(position).getCatname());
            holder.txtDisplayEmpName.setText("Employee Name: "+cat1.get(position).getEname());
            holder.txtDisplayMobile.setText("Mobile: "+cat1.get(position).getMno());
            holder.txtDisplayWhatsAppNumber.setText("Whats App Number: "+cat1.get(position).getWho());
            holder.txtDisplayDescription.setText("Description: "+cat1.get(position).getDesc());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVillage());
            holder.txtLoginEmployee.setText("Login Employee: "+cat1.get(position).getLoginname());
            holder.txtEnteryDate.setText("Entery Date: "+cat1.get(position).getCuu_date());
            holder.txtFollowuptype.setText("Follow Up Type: "+cat1.get(position).getFollow_up_type());
            holder.txtDisplayNextDate.setText("Next Date: "+cat1.get(position).getNext_date());
            holder.txtDisplayInquiryType.setText("Inquire Type: "+cat1.get(position).getType_inq());
            holder.txtstate.setText("State: "+cat1.get(position).getState());
            holder.txtCityName.setText("City: "+cat1.get(position).getCity());
            holder.txtdistrict.setText("District: "+cat1.get(position).getDistric());
            holder.txtTehsil.setText("Tehsil: "+cat1.get(position).getDistric());
        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("Add Profile")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtDisplayEmpName.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtDisplayWhatsAppNumber.setVisibility(View.VISIBLE);
            holder.txtDisplayDescription.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtLoginEmployee.setVisibility(View.VISIBLE);
            holder.txtstate.setVisibility(View.VISIBLE);


            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getFname()+cat1.get(position).getLname());
            holder.txtDisplayEmpName.setText("Employee Name: "+cat1.get(position).getEname());
            holder.txtDisplayMobile.setText("Mobile: "+cat1.get(position).getMno());
            holder.txtDisplayWhatsAppNumber.setText("Whats App Number: "+cat1.get(position).getWho());
            holder.txtDisplayDescription.setText("Description: "+cat1.get(position).getDesc());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVillage());
            holder.txtLoginEmployee.setText("Login Employee: "+cat1.get(position).getLoginname());
            holder.txtFollowuptype.setText("Follow Up Type: "+cat1.get(position).getFollow_up_type());
            holder.txtDisplayNextDate.setText("Next Date: "+cat1.get(position).getNext_date());
            holder.txtDisplayInquiryType.setText("Inquire Type: "+cat1.get(position).getType_inq());
            holder.txtstate.setText("State: "+cat1.get(position).getState());
        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("Edit Profile")){
            Log.d("TAG", "onBindViewHolder: Check_Tyep_of "+ cat1.get(position).getCatname());
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtstate.setVisibility(View.VISIBLE);
            holder.txtCityName.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtdistrict.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtDisplayDescription.setVisibility(View.VISIBLE);
            holder.txtDisplayWhatsAppNumber.setVisibility(View.VISIBLE);

            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getFname()+" "+cat1.get(position).getLname());
            holder.txtDisplayDescription.setText("Description: "+cat1.get(position).getDesc());
            holder.txtstate.setText("State: "+cat1.get(position).getState());
            holder.txtCityName.setText("City: "+cat1.get(position).getCity());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVillage());
            holder.txtdistrict.setText("Distric: "+cat1.get(position).getDistric());
            holder.txtDisplayMobile.setText("Mobile Number: "+cat1.get(position).getMno());
            holder.txtDisplayWhatsAppNumber.setText("Whats App Number: "+cat1.get(position).getWho());
            holder.txtModelName.setText("Deal Model Name: "+cat1.get(position).getDe_model_name());
            holder.txtDelivery.setText("Delivery: "+cat1.get(position).getDelivery());
            holder.txtSellModel.setText("Sell Model: "+cat1.get(position).getSell_model());
        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("Present")){
            holder.txtLoginEmployee.setVisibility(View.VISIBLE);
            holder.txtDate.setVisibility(View.VISIBLE);

            holder.txtLoginEmployee.setText("Login Name: "+cat1.get(position).getLoginname());
            holder.txtDate.setText("Date: "+cat1.get(position).getDate());

        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("Travaling")){
            holder.txtLoginEmployee.setVisibility(View.VISIBLE);
            holder.txtDate.setVisibility(View.VISIBLE);

            holder.txtLoginEmployee.setText("Login Name: "+cat1.get(position).getLoginname());
            holder.txtDate.setText("Date: "+cat1.get(position).getDate());

        }
        if (cat1.get(position).getCategory()!=null  && cat1.get(position).getCategory().equals("followup")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtDisplayCategory.setVisibility(View.VISIBLE);
            holder.txtDisplayEmpName.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtDisplayWhatsAppNumber.setVisibility(View.VISIBLE);
            holder.txtDisplayDescription.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtLoginEmployee.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
            holder.txtFollowuptype.setVisibility(View.VISIBLE);
            holder.txtDisplayNextDate.setVisibility(View.VISIBLE);
            holder.txtDisplayInquiryType.setVisibility(View.VISIBLE);


            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getFname()+cat1.get(position).getLname());
            holder.txtDisplayCategory.setText("Category name: "+cat1.get(position).getCat_name());
            holder.txtDisplayEmpName.setText("Employee Name: "+cat1.get(position).getEname());
            holder.txtDisplayMobile.setText("Mobile: "+cat1.get(position).getMoblino());
            holder.txtDisplayWhatsAppNumber.setText("Whats App Number: "+cat1.get(position).getWhatsappno());
            holder.txtDisplayDescription.setText("Description: "+cat1.get(position).getDescription());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVilage());
            holder.txtLoginEmployee.setText("Login Employee: "+cat1.get(position).getLoginname());
            holder.txtEnteryDate.setText("Entery Date: "+cat1.get(position).getCuu_date());
            holder.txtFollowuptype.setText("Follow Up Type: "+cat1.get(position).getFollow_up_type());
            holder.txtDisplayNextDate.setText("Next Date: "+cat1.get(position).getNext_date());
            holder.txtDisplayInquiryType.setText("Inquire Type: "+cat1.get(position).getType_inq());
        }


        if (cat1.get(position).getFilter_type3()!=null  && cat1.get(position).getFilter_type3().equals("new_visit")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtDisplayCategory.setVisibility(View.VISIBLE);
            holder.txtDisplayEmpName.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtDisplayWhatsAppNumber.setVisibility(View.VISIBLE);
            holder.txtDisplayDescription.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtLoginEmployee.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
           /* holder.txtFollowuptype.setVisibility(View.VISIBLE);
            holder.txtDisplayNextDate.setVisibility(View.VISIBLE);
            holder.txtDisplayInquiryType.setVisibility(View.VISIBLE);*/


            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getFname()+" "+cat1.get(position).getLname());
            holder.txtDisplayCategory.setText("Category name: "+cat1.get(position).getCat());
            holder.txtDisplayEmpName.setText("Employee Name: "+cat1.get(position).getEname());
            holder.txtDisplayMobile.setText("Mobile: "+cat1.get(position).getMobile());
            holder.txtDisplayWhatsAppNumber.setText("Whats App Number: "+cat1.get(position).getWhatsappno());
            holder.txtDisplayDescription.setText("Description: "+cat1.get(position).getDescription());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVillage());
            holder.txtLoginEmployee.setText("Login Employee: "+cat1.get(position).getDataadd());
            holder.txtEnteryDate.setText("Entery Date: "+cat1.get(position).getCdate());
            holder.txtFollowuptype.setText("Follow Up Type: "+cat1.get(position).getFollow_up_type());
            holder.txtDisplayNextDate.setText("Next Date: "+cat1.get(position).getNext_date());
            holder.txtDisplayInquiryType.setText("Inquire Type: "+cat1.get(position).getType_inq());
        }

        if (cat1.get(position).getFilter_type3()!=null  && cat1.get(position).getFilter_type3().equals("waking_entry")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtDisplayCategory.setVisibility(View.VISIBLE);
            holder.txtDisplayEmpName.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtDisplayWhatsAppNumber.setVisibility(View.VISIBLE);
            holder.txtDisplayDescription.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtLoginEmployee.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
         /*   holder.txtFollowuptype.setVisibility(View.VISIBLE);
            holder.txtDisplayNextDate.setVisibility(View.VISIBLE);
            holder.txtDisplayInquiryType.setVisibility(View.VISIBLE);
*/

            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getFname()+" "+cat1.get(position).getLname());
            holder.txtDisplayCategory.setText("Category name: "+cat1.get(position).getCat());
            holder.txtDisplayEmpName.setText("Employee Name: "+cat1.get(position).getEname());
            holder.txtDisplayMobile.setText("Mobile: "+cat1.get(position).getMobile());
            holder.txtDisplayWhatsAppNumber.setText("Whats App Number: "+cat1.get(position).getWhatsappno());
            holder.txtDisplayDescription.setText("Description: "+cat1.get(position).getDescription());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVillage());
            holder.txtLoginEmployee.setText("Login Employee: "+cat1.get(position).getDataadd());
            holder.txtEnteryDate.setText("Entery Date: "+cat1.get(position).getCdate());
            holder.txtFollowuptype.setText("Follow Up Type: "+cat1.get(position).getFollow_up_type());
            holder.txtDisplayNextDate.setText("Next Date: "+cat1.get(position).getNext_date());
            holder.txtDisplayInquiryType.setText("Inquire Type: "+cat1.get(position).getType_inq());
        }

        if (cat1.get(position).getFilter_type3()!=null  && cat1.get(position).getFilter_type3().equals("activity")){
            holder.txtDisplayName.setVisibility(View.VISIBLE);
            holder.txtDisplayCategory.setVisibility(View.VISIBLE);
            holder.txtDisplayEmpName.setVisibility(View.VISIBLE);
            holder.txtDisplayMobile.setVisibility(View.VISIBLE);
            holder.txtDisplayWhatsAppNumber.setVisibility(View.VISIBLE);
            holder.txtDisplayDescription.setVisibility(View.VISIBLE);
            holder.txtDisplayVillage.setVisibility(View.VISIBLE);
            holder.txtLoginEmployee.setVisibility(View.VISIBLE);
            holder.txtEnteryDate.setVisibility(View.VISIBLE);
           /* holder.txtFollowuptype.setVisibility(View.VISIBLE);
            holder.txtDisplayNextDate.setVisibility(View.VISIBLE);
            holder.txtDisplayInquiryType.setVisibility(View.VISIBLE);*/


//            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getFname()+" "+cat1.get(position).getLname());
            holder.txtDisplayName.setText("Customer Name: "+cat1.get(position).getFname().concat(" ").concat(cat1.get(position).getLname()));
            holder.txtDisplayCategory.setText("Category name: "+cat1.get(position).getCat());
            holder.txtDisplayEmpName.setText("Employee Name: "+cat1.get(position).getEname());
            holder.txtDisplayMobile.setText("Mobile: "+cat1.get(position).getMobile());
            holder.txtDisplayWhatsAppNumber.setText("Whats App Number: "+cat1.get(position).getWhatsappno());
            holder.txtDisplayDescription.setText("Description: "+cat1.get(position).getDescription());
            holder.txtDisplayVillage.setText("Village: "+cat1.get(position).getVillage());
            holder.txtLoginEmployee.setText("Login Employee: "+cat1.get(position).getDataadd());
            holder.txtEnteryDate.setText("Entery Date: "+cat1.get(position).getCdate());
            holder.txtFollowuptype.setText("Follow Up Type: "+cat1.get(position).getFollow_up_type());
            holder.txtDisplayNextDate.setText("Next Date: "+cat1.get(position).getNext_date());
            holder.txtDisplayInquiryType.setText("Inquire Type: "+cat1.get(position).getType_inq());
        }
    }

    @Override
    public int getItemCount() {
        Log.d("TAG", "getItemCount: Check_Date: " + cat1.size());
        return cat1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtDisplayName,txtDisplayCategory,txtDisplayEmpName,txtDisplayMobile,txtDisplayWhatsAppNumber,txtDisplayDescription
                ,txtDisplayVillage,txtDisplayNextDate,txtDisplayTractorName,txtDisplayInquiryType,txtLoginEmployee,txtFollowuptype
                ,txtEnteryDate,txtCityName,txtstate,txtdistrict,txtCgst,txtSgst,txtWorkTypeService,txt_DealPrice,txtModelName,txtDelivery
                ,txtSellLost,txtSellModel,txtDropInquire,txtDate,txtTehsil,txtMakeName,txtModelYear,txtSourceOfInquire,txtDeliveryModelName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDisplayName = itemView.findViewById(R.id.txtDisplayName);
            txtDisplayCategory = itemView.findViewById(R.id.txtDisplayCategory);
            txtDisplayEmpName = itemView.findViewById(R.id.txtDisplayEmpName);
            txtDisplayMobile = itemView.findViewById(R.id.txtDisplayMobile);
            txtDisplayWhatsAppNumber = itemView.findViewById(R.id.txtDisplayWhatsAppNumber);
            txtDisplayDescription = itemView.findViewById(R.id.txtDisplayDescription);
            txtDisplayVillage = itemView.findViewById(R.id.txtDisplayVillage);
            txtEnteryDate = itemView.findViewById(R.id.txtEnteryDate);
            txtDisplayTractorName = itemView.findViewById(R.id.txtDisplayTractorName);
            txtDisplayInquiryType = itemView.findViewById(R.id.txtDisplayInquiryType);
            txtLoginEmployee = itemView.findViewById(R.id.txtLoginEmployee);
            txtFollowuptype = itemView.findViewById(R.id.txtFollowuptype);
            txtDisplayNextDate = itemView.findViewById(R.id.txtDisplayNextDate);
            txtCityName = itemView.findViewById(R.id.txtCityName);
            txtstate = itemView.findViewById(R.id.txtstate);
            txtdistrict = itemView.findViewById(R.id.txtdistrict);
            txtCgst = itemView.findViewById(R.id.txtCgst);
            txtSgst = itemView.findViewById(R.id.txtSgst);
            txtWorkTypeService = itemView.findViewById(R.id.txtWorkTypeService);
            txt_DealPrice = itemView.findViewById(R.id.txt_DealPrice);
            txtModelName = itemView.findViewById(R.id.txtModelName);
            txtDelivery = itemView.findViewById(R.id.txtDelivery);
            txtSellLost = itemView.findViewById(R.id.txtSellLost);
            txtSellModel = itemView.findViewById(R.id.txtSellModel);
            txtDropInquire = itemView.findViewById(R.id.txtDropInquire);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtTehsil = itemView.findViewById(R.id.txtTehsil);
            txtMakeName = itemView.findViewById(R.id.txtMakeName);
            txtModelYear = itemView.findViewById(R.id.txtModelYear);
            txtSourceOfInquire = itemView.findViewById(R.id.txtSourceOfInquire);
            txtDeliveryModelName = itemView.findViewById(R.id.txtDeliveryModelName);
        }
    }
}
