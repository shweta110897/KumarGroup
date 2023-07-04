package com.example.kumarGroup.CustomerProfileData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CustomerProfile;
import com.example.kumarGroup.R;

import java.util.ArrayList;
import java.util.List;

public class DeleteCustomerProfileAdapter extends RecyclerView.Adapter<DeleteCustomerProfileAdapter.ViewHolder>
{
    Context context;
    boolean isCheck;
    public static ArrayList<CustomerProfile> modelArrayList;
    boolean ischk;

    @NonNull
    private RecyclerViewItemInterface viewItemInterface;

    public void setViewItemInterface(RecyclerViewItemInterface viewItemInterface) {
        this.viewItemInterface = viewItemInterface;
    }

    public DeleteCustomerProfileAdapter(Context context, ArrayList<CustomerProfile> customerProfiles, boolean isCheck) {
        this.context = context;
        this.modelArrayList = customerProfiles;
        this.isCheck = isCheck;
    }

    @NonNull
    @Override
    public DeleteCustomerProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.view_customer_data, parent, false);
        DeleteCustomerProfileAdapter.ViewHolder viewHolder = new DeleteCustomerProfileAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeleteCustomerProfileAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
       holder.tv_ProfileCustomer_name.setText("Category Name: "+modelArrayList.get(position).getCat_name());
       holder.tv_ProfileCustomer_fname.setText("First Name: "+modelArrayList.get(position).getFname());
       holder.tv_ProfileCustomer_lname.setText("Last Name: "+modelArrayList.get(position).getLname());
       holder.tv_ProfileCustomer_state.setText("State: "+modelArrayList.get(position).getState());
       holder.tv_ProfileCustomer_city.setText("City: "+modelArrayList.get(position).getCity());
       holder.tv_ProfileCustomer_distric.setText("District: "+modelArrayList.get(position).getDistric());
       holder.tv_ProfileCustomer_tehsil.setText("Taluko: "+modelArrayList.get(position).getTehsil());
       holder.tv_ProfileCustomer_vilage.setText("Village: "+modelArrayList.get(position).getVilage());
       holder.tv_ProfileCustomer_moblino.setText("Mobile No: "+modelArrayList.get(position).getMoblino());
       holder.tv_ProfileCustomer_employee_name.setText("Employee Name: "+modelArrayList.get(position).getEmployee_name());
       holder.tv_ProfileCustomer_whatsappno.setText("Whats App no: "+modelArrayList.get(position).getWhatsappno());
       holder.tv_ProfileCustomer_note.setText("Notes: "+modelArrayList.get(position).getNote());

       holder.tv_ProfileCustomer_AddEmp.setText("Add Employee Name: "+modelArrayList.get(position).getAddemp());
       holder.tv_ProfileCustomer_AddDate.setText("Add Date: "+modelArrayList.get(position).getAdd_date());
       holder.tv_ProfileCustomer_Drop.setText("Drop Data: "+modelArrayList.get(position).getDrop());


       holder.deleteItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               ischk=isChecked;
               if (viewItemInterface != null) {
                   viewItemInterface.onItemClick(holder.getAdapterPosition(),modelArrayList,isChecked);
               }
           }
       });



       if(modelArrayList.get(position).getApprove_delete()!=null) {

           if (modelArrayList.get(position).getApprove_delete().equals("1")) {
               holder.btnDelete.setText("Request Pending");
               holder.btnDelete.setEnabled(false);
               holder.btnDelete.setVisibility(View.VISIBLE);
           } else if (modelArrayList.get(position).getApprove_delete().equals("2")) {
               holder.btnDelete.setText("Delete");
               holder.btnDelete.setEnabled(false);
               holder.btnDelete.setVisibility(View.GONE);
           } else{
               holder.btnDelete.setVisibility(View.VISIBLE);
               holder.btnDelete.setText("Delete");
               holder.btnDelete.setEnabled(true);
           }
       }

       holder.btnDelete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if (viewItemInterface != null) {
                   viewItemInterface.onItemClick(holder.getAdapterPosition(),modelArrayList,false);
               }

           }
       });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        Button btnDelete;
        TextView tv_ProfileCustomer_name,tv_ProfileCustomer_fname,tv_ProfileCustomer_lname,
                tv_ProfileCustomer_state,
                tv_ProfileCustomer_city,tv_ProfileCustomer_distric,tv_ProfileCustomer_tehsil,
                tv_ProfileCustomer_vilage,
                tv_ProfileCustomer_moblino,tv_ProfileCustomer_employee_name,
                tv_ProfileCustomer_whatsappno,tv_ProfileCustomer_note;

        TextView tv_ProfileCustomer_AddEmp,tv_ProfileCustomer_AddDate,
                tv_ProfileCustomer_Drop;

        CheckBox deleteItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.deleteItem=itemView.findViewById(R.id.deleteItem);
            this.btnDelete=itemView.findViewById(R.id.btnDelete);

            this.tv_ProfileCustomer_name=itemView.findViewById(R.id.tv_ProfileCustomer_name);
            this.tv_ProfileCustomer_fname=itemView.findViewById(R.id.tv_ProfileCustomer_fname);
            this.tv_ProfileCustomer_lname=itemView.findViewById(R.id.tv_ProfileCustomer_lname);
            this.tv_ProfileCustomer_state=itemView.findViewById(R.id.tv_ProfileCustomer_state);

            this.tv_ProfileCustomer_city=itemView.findViewById(R.id.tv_ProfileCustomer_city);
            this.tv_ProfileCustomer_distric=itemView.findViewById(R.id.tv_ProfileCustomer_distric);
            this.tv_ProfileCustomer_tehsil=itemView.findViewById(R.id.tv_ProfileCustomer_tehsil);

            this.tv_ProfileCustomer_vilage=itemView.findViewById(R.id.tv_ProfileCustomer_vilage);
            this.tv_ProfileCustomer_moblino=itemView.findViewById(R.id.tv_ProfileCustomer_moblino);
            this.tv_ProfileCustomer_employee_name=itemView.findViewById(R.id.tv_ProfileCustomer_employee_name);
            this.tv_ProfileCustomer_whatsappno=itemView.findViewById(R.id.tv_ProfileCustomer_whatsappno);
            this.tv_ProfileCustomer_note=itemView.findViewById(R.id.tv_ProfileCustomer_note);

            this.tv_ProfileCustomer_AddEmp=itemView.findViewById(R.id.tv_ProfileCustomer_AddEmp);
            this.tv_ProfileCustomer_AddDate=itemView.findViewById(R.id.tv_ProfileCustomer_AddDate);
            this.tv_ProfileCustomer_Drop=itemView.findViewById(R.id.tv_ProfileCustomer_Drop);
        }
    }

    public interface RecyclerViewItemInterface {

        void onItemClick(int position,List<CustomerProfile> mcatlist,Boolean isCheck);
    }
}
