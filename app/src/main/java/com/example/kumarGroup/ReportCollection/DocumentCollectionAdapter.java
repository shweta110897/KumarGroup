package com.example.kumarGroup.ReportCollection;

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

import com.example.kumarGroup.CatDocBoxDisplay;
import com.example.kumarGroup.R;

import java.util.List;

public class DocumentCollectionAdapter extends RecyclerView.Adapter<DocumentCollectionAdapter.ViewHolder> {

    Context context;
    List<CatDocBoxDisplay> documentBoxDisplayModels;

    String sms=" ";

    public DocumentCollectionAdapter(Context context, List<CatDocBoxDisplay> documentBoxDisplayModels) {
        this.context = context;
        this.documentBoxDisplayModels = documentBoxDisplayModels;
    }

    @NonNull
    @Override
    public DocumentCollectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.compalin_box_display_raw, parent, false);
        DocumentCollectionAdapter.ViewHolder viewHolder = new DocumentCollectionAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentCollectionAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtWSGenNextDate.setText("Category Name: " + documentBoxDisplayModels.get(position).getCat_name());
        holder.txtWSGenRemark.setText("Customer Name: " + documentBoxDisplayModels.get(position).getFname());
        holder.txtWSGenCuName.setText("City: " + documentBoxDisplayModels.get(position).getCity());
        holder.txtWSGenWOrkSer.setText("District: " + documentBoxDisplayModels.get(position).getDistric());
        holder.txtWSGenMobileNo.setText("Mobile Number: " + documentBoxDisplayModels.get(position).getMoblino());
        holder.txtWSGenVillage.setText("Village: " + documentBoxDisplayModels.get(position).getVilage());

        holder.txtWSGenCStatus.setText("WhatsApp num: " + documentBoxDisplayModels.get(position).getWhatsappno());
        holder.txtWSGenLoginName.setText("Document: " + documentBoxDisplayModels.get(position).getDocument());
        holder.txtWSGenAddDate.setText("Description: " + documentBoxDisplayModels.get(position).getD_desc());
        holder.txtWSGenComplainNum.setText("Taluko: " + documentBoxDisplayModels.get(position).getTehsil());

        holder.txtWSGenAdddate.setText( "Add Date: "+ documentBoxDisplayModels.get(position).getAdd_date());
        holder.txtWSGenStatus .setText("Status: "+ documentBoxDisplayModels.get(position).getStatus());

        holder.txtWSGenNExtdate.setText("Next Date: " + documentBoxDisplayModels.get(position).getNext_date());


        holder.Lin_monthlyDailyWeeklyDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,DocumentCollecationFormActivity.class);

                i.putExtra("category",documentBoxDisplayModels.get(position).getCat_name());
                i.putExtra("customer_name",documentBoxDisplayModels.get(position).getFname());
                i.putExtra("catId",documentBoxDisplayModels.get(position).getTableid());
                i.putExtra("MobileNo",documentBoxDisplayModels.get(position).getMoblino());
                context.startActivity(i);
            }
        });


        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + documentBoxDisplayModels.get(position).getMoblino()));
                Log.d("call", "onClick: " + documentBoxDisplayModels.get(position).getMoblino());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.lin_MDY_visit.setVisibility(View.GONE);

        holder.lin_MDY_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",
                        documentBoxDisplayModels.get(position).getMoblino(), null));
                smsIntent.putExtra("sms_body",sms);
                smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(smsIntent);
            }
        });


        holder.lin_MDY_whapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean installed = appInstallOrNot("com.whatsapp");
                if (installed){

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+
                            documentBoxDisplayModels.get(position).getWhatsappno()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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

                Intent i= new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
                i.putExtra(android.content.Intent.EXTRA_TEXT,  "** Kumar Group ** \n" +
                        ""+"Customer Detail:\n" +
                        "Customer Name: "+documentBoxDisplayModels.get(position).getFname()+"\n" +
                        "Document: "+documentBoxDisplayModels.get(position).getDocument()+"\n"+
                        "Mobile: "+documentBoxDisplayModels.get(position).getMoblino()+"\n"+
                        "Village: "+documentBoxDisplayModels.get(position).getVilage()+"\n"+
                        "Category Name: "+documentBoxDisplayModels.get(position).getCat_name()+"\n"+
                        "District: "+documentBoxDisplayModels.get(position).getDistric());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Intent.createChooser(i,"Share via"));

            }
        });


    }

    @Override
    public int getItemCount() {
        return documentBoxDisplayModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtWSGenNextDate, txtWSGenRemark, txtWSGenCuName, txtWSGenWOrkSer,
                txtWSGenMobileNo, txtWSGenVillage;

        TextView lin_MDY_call, lin_MDY_sms, lin_MDY_whapp, lin_MDY_share, lin_MDY_visit;

        LinearLayout Lin_monthlyDailyWeeklyDetail;


        TextView txtWSGenCStatus, txtWSGenLoginName, txtWSGenAddDate,
                txtWSGenComplainNum,txtWSGenNExtdate,
                txtWSGenAdddate,txtWSGenStatus;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtWSGenNextDate = itemView.findViewById(R.id.txtWSGenNextDate);
            this.txtWSGenRemark = itemView.findViewById(R.id.txtWSGenRemark);
            this.txtWSGenCuName = itemView.findViewById(R.id.txtWSGenCuName);
            this.txtWSGenWOrkSer = itemView.findViewById(R.id.txtWSGenWOrkSer);
            this.txtWSGenMobileNo = itemView.findViewById(R.id.txtWSGenMobileNo);
            this.txtWSGenVillage = itemView.findViewById(R.id.txtWSGenVillage);

            this.txtWSGenCStatus = itemView.findViewById(R.id.txtWSGenCStatus);
            this.txtWSGenLoginName = itemView.findViewById(R.id.txtWSGenLoginName);
            this.txtWSGenAddDate = itemView.findViewById(R.id.txtWSGenAddDate);
            this.txtWSGenComplainNum = itemView.findViewById(R.id.txtWSGenComplainNum);
            this.txtWSGenNExtdate = itemView.findViewById(R.id.txtWSGenNExtdate);
            this.txtWSGenAdddate = itemView.findViewById(R.id.txtWSGenAdddate);
            this.txtWSGenStatus = itemView.findViewById(R.id.txtWSGenStatus);

            this.Lin_monthlyDailyWeeklyDetail = itemView.findViewById(R.id.Lin_monthlyDailyWeeklyDetail);

            this.lin_MDY_call = itemView.findViewById(R.id.lin_MDY_call);
            this.lin_MDY_sms = itemView.findViewById(R.id.lin_MDY_sms);
            this.lin_MDY_whapp = itemView.findViewById(R.id.lin_MDY_whapp);
            this.lin_MDY_share = itemView.findViewById(R.id.lin_MDY_share);
            this.lin_MDY_visit = itemView.findViewById(R.id.lin_MDY_visit);
        }
    }
}
