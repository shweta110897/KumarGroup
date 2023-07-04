package com.example.kumarGroup.DeliveryReport;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.DataDeliveryReport;
import com.example.kumarGroup.FirstServiceGLModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class DeliveryReportMainAdpater extends RecyclerView.Adapter<DeliveryReportMainAdpater.ViewHolder>
{

    Context context;
    List<DataDeliveryReport>  deliveryReports;

    SharedPreferences sharedPreferences,sp1;
    String emp;
    ProgressDialog progressDialog;

    public DeliveryReportMainAdpater(Context context, List<DataDeliveryReport> deliveryReports) {
        this.context = context;
        this.deliveryReports = deliveryReports;


        sp1 = context.getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

    }

    @NonNull
    @Override
    public DeliveryReportMainAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.delivery_report_raw, parent, false);
        DeliveryReportMainAdpater.ViewHolder viewHolder = new DeliveryReportMainAdpater.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryReportMainAdpater.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


            holder.txt_cat_name.setText("Category Name:"+deliveryReports.get(position).getCat_name());
            holder.txt_fname.setText("Name:"+deliveryReports.get(position).getFname());
            holder.txt_state.setText("State:"+deliveryReports.get(position).getState());
            holder.txt_city.setText("City:"+deliveryReports.get(position).getCity());
            holder.txt_tehsil.setText("Tehsil:"+deliveryReports.get(position).getTehsil());
            holder.txt_vilage.setText("Village:"+deliveryReports.get(position).getVilage());
            holder.txt_moblino.setText("Mobile No:"+deliveryReports.get(position).getMoblino());
            holder.txt_whatsappno.setText("WhatsApp No:"+deliveryReports.get(position).getWhatsappno());
            holder.txt_employee_name.setText("Employee Name:"+deliveryReports.get(position).getEmployee_name());
            holder.txt_desc.setText("Description:"+deliveryReports.get(position).getDesc());

            holder.Lin_DeliveryReportMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, GaneralListFormActivity.class);
                    i.putExtra("id",deliveryReports.get(position).getAutoid());
                    i.putExtra("cuid",deliveryReports.get(position).getId());
                    context.startActivity(i);
                }
            });


            if(deliveryReports.get(position).getFisrt_ser().equals("0")){
                holder.txt_firstService.setVisibility(View.VISIBLE);

                holder.txt_firstService.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        progressDialog= new ProgressDialog(context);
                        progressDialog.show();
                        progressDialog.setContentView(R.layout.progress_dialog);
                        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                        WebService.getClient().getFirstServiceGL(emp,
                                deliveryReports.get(position).getId(),
                                deliveryReports.get(position).getAutoid()).enqueue(new Callback<FirstServiceGLModel>() {
                            @Override
                            public void onResponse(@NotNull Call<FirstServiceGLModel> call,
                                                   @NotNull Response<FirstServiceGLModel> response) {

                                progressDialog.dismiss();
                                assert response.body() != null;
                                Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(@NotNull Call<FirstServiceGLModel> call, @NotNull Throwable t) {
                                progressDialog.dismiss();
                            }
                        });

                    }
                });

            }
            else{
                holder.txt_firstService.setVisibility(View.GONE);
            }

    }

    @Override
    public int getItemCount() {
        return deliveryReports.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView txt_cat_name,txt_fname,txt_state,txt_city,txt_tehsil,txt_vilage,
                txt_moblino,txt_whatsappno,txt_employee_name,txt_desc,
                txt_firstService;


        LinearLayout Lin_DeliveryReportMain;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            this.txt_cat_name = itemView.findViewById(R.id.txt_cat_name);
            this.txt_fname = itemView.findViewById(R.id.txt_fname);
            this.txt_state = itemView.findViewById(R.id.txt_state);
            this.txt_city = itemView.findViewById(R.id.txt_city);
            this.txt_tehsil = itemView.findViewById(R.id.txt_tehsil);
            this.txt_vilage = itemView.findViewById(R.id.txt_vilage);
            this.txt_moblino = itemView.findViewById(R.id.txt_moblino);
            this.txt_whatsappno = itemView.findViewById(R.id.txt_whatsappno);
            this.txt_employee_name = itemView.findViewById(R.id.txt_employee_name);
            this.txt_desc = itemView.findViewById(R.id.txt_desc);
            this.txt_firstService = itemView.findViewById(R.id.txt_firstService);

            this.Lin_DeliveryReportMain = itemView.findViewById(R.id.Lin_DeliveryReportMain);
        }
    }
}
