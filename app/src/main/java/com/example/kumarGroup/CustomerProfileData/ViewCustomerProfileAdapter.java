package com.example.kumarGroup.CustomerProfileData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CustomerProfile;
import com.example.kumarGroup.R;

import java.util.List;

public class ViewCustomerProfileAdapter extends RecyclerView.Adapter<ViewCustomerProfileAdapter.ViewHolder>
{
    Context context;
    List<CustomerProfile>  customerProfiles;

    public ViewCustomerProfileAdapter(Context context, List<CustomerProfile> customerProfiles) {
        this.context = context;
        this.customerProfiles = customerProfiles;
    }

    @NonNull
    @Override
    public ViewCustomerProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.view_customer_data, parent, false);
        ViewCustomerProfileAdapter.ViewHolder viewHolder = new ViewCustomerProfileAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCustomerProfileAdapter.ViewHolder holder, int position) {
       holder.tv_ProfileCustomer_name.setText("Category Name: "+customerProfiles.get(position).getCat_name());
       holder.tv_ProfileCustomer_fname.setText("First Name: "+customerProfiles.get(position).getFname());
       holder.tv_ProfileCustomer_lname.setText("Last Name: "+customerProfiles.get(position).getLname());
       holder.tv_ProfileCustomer_state.setText("State: "+customerProfiles.get(position).getState());
       holder.tv_ProfileCustomer_city.setText("City: "+customerProfiles.get(position).getCity());
       holder.tv_ProfileCustomer_distric.setText("District: "+customerProfiles.get(position).getDistric());
       holder.tv_ProfileCustomer_tehsil.setText("Taluko: "+customerProfiles.get(position).getTehsil());
       holder.tv_ProfileCustomer_vilage.setText("Village: "+customerProfiles.get(position).getVilage());
       holder.tv_ProfileCustomer_moblino.setText("Mobile No: "+customerProfiles.get(position).getMoblino());
       holder.tv_ProfileCustomer_employee_name.setText("Employee Name: "+customerProfiles.get(position).getEmployee_name());
       holder.tv_ProfileCustomer_whatsappno.setText("Whats App no: "+customerProfiles.get(position).getWhatsappno());
       holder.tv_ProfileCustomer_note.setText("Notes: "+customerProfiles.get(position).getNote());

       holder.tv_ProfileCustomer_AddEmp.setText("Add Employee Name: "+customerProfiles.get(position).getAddemp());
       holder.tv_ProfileCustomer_AddDate.setText("Add Date: "+customerProfiles.get(position).getAdd_date());
       holder.tv_ProfileCustomer_Drop.setText("Drop Data: "+customerProfiles.get(position).getDrop());

       holder.tv_ProfileCustomer_maker.setText("Maker Name: "+customerProfiles.get(position).getMaker());
       holder.tv_ProfileCustomer_model.setText("Model Name: "+customerProfiles.get(position).getModel_name());
       holder.tv_ProfileCustomer_RegNo.setText("Registration NO: "+customerProfiles.get(position).getRegistration_no());
       holder.tv_ProfileCustomer_mfgYear.setText("MFG Year: "+customerProfiles.get(position).getManufacture_year());
    }

    @Override
    public int getItemCount() {
        return customerProfiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_ProfileCustomer_name,tv_ProfileCustomer_fname,tv_ProfileCustomer_lname,
                tv_ProfileCustomer_state,
                tv_ProfileCustomer_city,tv_ProfileCustomer_distric,tv_ProfileCustomer_tehsil,
                tv_ProfileCustomer_vilage,
                tv_ProfileCustomer_moblino,tv_ProfileCustomer_employee_name,
                tv_ProfileCustomer_whatsappno,tv_ProfileCustomer_note,tv_ProfileCustomer_maker,
                tv_ProfileCustomer_model,tv_ProfileCustomer_RegNo,tv_ProfileCustomer_mfgYear;

        TextView tv_ProfileCustomer_AddEmp,tv_ProfileCustomer_AddDate,
                tv_ProfileCustomer_Drop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

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

            this.tv_ProfileCustomer_maker=itemView.findViewById(R.id.tv_ProfileCustomer_maker);
            this.tv_ProfileCustomer_model=itemView.findViewById(R.id.tv_ProfileCustomer_model);
            this.tv_ProfileCustomer_RegNo=itemView.findViewById(R.id.tv_ProfileCustomer_RegNo);
            this.tv_ProfileCustomer_mfgYear=itemView.findViewById(R.id.tv_ProfileCustomer_mfgYear);
        }
    }
}
