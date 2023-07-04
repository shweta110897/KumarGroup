package com.example.kumarGroup.FollowUp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatShowMonth;
import com.example.kumarGroup.R;

import java.util.List;

public class DisplayMonthAdapter extends RecyclerView.Adapter<DisplayMonthAdapter.ViewHolder> {

    Context context;
    List<CatShowMonth> catShowMonths;
    String sms= "";//The message you want to text to the phone

    public DisplayMonthAdapter(Context context, List<CatShowMonth> catShowMonths) {
        this.context = context;
        this.catShowMonths = catShowMonths;
    }

    @NonNull
    @Override
    public DisplayMonthAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.monthly_display_row, parent, false);
        DisplayMonthAdapter.ViewHolder viewHolder = new DisplayMonthAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayMonthAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtMonthDisplayName.setText("Name: "+catShowMonths.get(position).getFname()+" "+catShowMonths.get(position).getLname());
        holder.txtMonthDisplayCustomer.setText("Category: "+catShowMonths.get(position).getCat_name());
        holder.txtMonthDisplayEmpName.setText("Employee Name: "+catShowMonths.get(position).getEmployee_name());
        holder.txtMonthDisplayMobile.setText("Mobile: "+catShowMonths.get(position).getMoblino());
        holder.txtMonthDisplayWhatsAppNumber.setText("WhatsApp Number: "+catShowMonths.get(position).getWhatsappno());
        holder.txtMonthDisplayDescription.setText("Description: "+catShowMonths.get(position).getReason());
        holder.txtMonthDisplayNextDate.setText("Next Date: "+catShowMonths.get(position).getVdate());
        holder.txtMonthDisplayVillage.setText("Village: "+catShowMonths.get(position).getVilage());

        holder.txtMonthDisplayHotCold.setVisibility(View.GONE);
        holder.txtMonthDisplayFinalAmount.setVisibility(View.GONE);

        holder.Lin_monthlyDailyWeeklyDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,DisplayMonthWeekDayActivity.class);
                i.putExtra("customer_name",catShowMonths.get(position).getFname()+" "+catShowMonths.get(position).getLname());
                i.putExtra("category",catShowMonths.get(position).getCat_name());
                i.putExtra("catId",catShowMonths.get(position).getCat_id());
                i.putExtra("sname",catShowMonths.get(position).getId());
                i.putExtra("id",catShowMonths.get(position).getAutoid());
                context.startActivity(i);
            }
        });


        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+catShowMonths.get(position).getMoblino()));
                Log.d("call", "onClick: "+catShowMonths.get(position).getMoblino());
                context.startActivity(intent);
            }
        });


        holder.lin_MDY_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", catShowMonths.get(position).getMoblino(), null));
                smsIntent.putExtra("sms_body",sms);
                context.startActivity(smsIntent);
            }
        });


        holder.lin_MDY_whapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean installed = appInstallOrNot("com.whatsapp");
                if (installed){

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+catShowMonths.get(position).getWhatsappno()));
                    context.startActivity(intent);
                }else {

                    Toast.makeText(context, "Whats app not installed on your device ", Toast.LENGTH_SHORT).show();
                }
            }

            private boolean appInstallOrNot(String url)
            {
                PackageManager packageManager = context.getPackageManager();
                boolean app_installed;
                try{

                    packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
                    app_installed = true;
                }catch (PackageManager.NameNotFoundException e){
                    app_installed =false;
                }
                return app_installed;
            }
        });


        holder.lin_MDY_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
                i.putExtra(android.content.Intent.EXTRA_TEXT,  "** Kumar Group ** \n" +
                        ""+"Customer Detail:\n" +
                        "Name: "+catShowMonths.get(position).getFname()+" "+catShowMonths.get(position).getLname()+"\n" +
                        "Category: "+catShowMonths.get(position).getCat_name()+"\n"+
                        "Employee Name: "+catShowMonths.get(position).getEmployee_name()+"\n" +
                        "Mobile: "+catShowMonths.get(position).getMoblino()+"\n"+
                        "WhatsApp Number: "+catShowMonths.get(position).getWhatsappno()+"\n"+
                        "Description: "+catShowMonths.get(position).getReason()+"\n"+
                        "Next Date: "+catShowMonths.get(position).getVdate());
                context.startActivity(Intent.createChooser(i,"Share via"));

            }
        });


        holder.lin_MDY_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,VisitFollowUpActivity.class);
                i.putExtra("sname",catShowMonths.get(position).getId());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return catShowMonths.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtMonthDisplayName,txtMonthDisplayCustomer,txtMonthDisplayMobile,txtMonthDisplayEmpName,
                txtMonthDisplayWhatsAppNumber,txtMonthDisplayDescription,txtMonthDisplayNextDate;

        TextView lin_MDY_call,lin_MDY_sms,lin_MDY_whapp,lin_MDY_share,lin_MDY_visit,txtMonthDisplayHotCold,
                txtMonthDisplayFinalAmount,txtMonthDisplayVillage;

        LinearLayout Lin_monthlyDailyWeeklyDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtMonthDisplayName=itemView.findViewById(R.id.txtMonthDisplayName);
            this.txtMonthDisplayCustomer=itemView.findViewById(R.id.txtMonthDisplayCustomer);
            this.txtMonthDisplayMobile=itemView.findViewById(R.id.txtMonthDisplayMobile);
            this.txtMonthDisplayWhatsAppNumber=itemView.findViewById(R.id.txtMonthDisplayWhatsAppNumber);
            this.txtMonthDisplayEmpName=itemView.findViewById(R.id.txtMonthDisplayEmpName );

            this.Lin_monthlyDailyWeeklyDetail=itemView.findViewById(R.id.Lin_monthlyDailyWeeklyDetail);
            this.txtMonthDisplayDescription=itemView.findViewById(R.id.txtMonthDisplayDescription);
            this.txtMonthDisplayNextDate=itemView.findViewById(R.id.txtMonthDisplayNextDate);

            this.txtMonthDisplayHotCold=itemView.findViewById(R.id.txtMonthDisplayHotCold);
            this.txtMonthDisplayFinalAmount=itemView.findViewById(R.id.txtMonthDisplayFinalAmount);
            this.txtMonthDisplayVillage=itemView.findViewById(R.id.txtMonthDisplayVillage);

            this.lin_MDY_call=itemView.findViewById(R.id.lin_MDY_call);
            this.lin_MDY_sms=itemView.findViewById(R.id.lin_MDY_sms);
            this.lin_MDY_whapp=itemView.findViewById(R.id.lin_MDY_whapp);
            this.lin_MDY_share=itemView.findViewById(R.id.lin_MDY_share);
            this.lin_MDY_visit=itemView.findViewById(R.id.lin_MDY_visit);
        }
    }
}
