package com.example.kumarGroup.Workshop.WsPayPen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.DataWsPayPen;
import com.example.kumarGroup.R;

import java.util.List;

public class WSPayPenAdapter extends RecyclerView.Adapter<WSPayPenAdapter.ViewHolder> {

    Context context;
    List<DataWsPayPen> dataWsPayPens;

    String sms= "";//The message you want to text to the phone


    public WSPayPenAdapter(Context context, List<DataWsPayPen> dataWsPayPens) {
        this.context = context;
        this.dataWsPayPens = dataWsPayPens;
    }


    @NonNull
    @Override
    public WSPayPenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.ws_payment_pending_row, parent, false);
        WSPayPenAdapter.ViewHolder viewHolder = new WSPayPenAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WSPayPenAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_WS_CustomerName.setText("Customer Name: "+dataWsPayPens.get(position).getCname());
        holder.tv_Ws_MobileNo.setText("Mobile No: "+dataWsPayPens.get(position).getMobileno());
        holder.tv_WS_Village.setText("Village: "+dataWsPayPens.get(position).getVillage());
        holder.tv_WS_LeftAmount.setText("Left Amount: "+dataWsPayPens.get(position).getLeft_amt());


        holder.tv_Nextdate_WS.setVisibility(View.GONE);
        holder.tv_Description_WS.setVisibility(View.GONE);

        holder.txt_WS_Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,PaymentWsActivity.class);
                i.putExtra("id",dataWsPayPens.get(position).getId());
                context.startActivity(i);
            }
        });



        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+dataWsPayPens.get(position).getMobileno()));
                Log.d("call", "onClick: "+dataWsPayPens.get(position).getMobileno());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


        holder.lin_MDY_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", dataWsPayPens.get(position).getMobileno(), null));
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
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+dataWsPayPens.get(position).getWhno()));
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

                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT,"Subject test");
                i.putExtra(Intent.EXTRA_TEXT,  "** Kumar Group ** \n" +
                        ""+"Customer Detail:\n" +
                        "Name: "+dataWsPayPens.get(position).getCname()+"\n" +
                        "Mobile: "+dataWsPayPens.get(position).getMobileno()+"\n"+
                        "WhatsApp Number: "+dataWsPayPens.get(position).getWhno()+"\n"+
                        "Village: "+dataWsPayPens.get(position).getVillage()+"\n"+
                        "Left Amount: "+dataWsPayPens.get(position).getLeft_amt());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Intent.createChooser(i,"Share via"));

            }
        });



    }

    @Override
    public int getItemCount() {
        return dataWsPayPens.size();
    }

   public  class  ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_WS_CustomerName,tv_Ws_MobileNo,tv_WS_Village,tv_WS_LeftAmount,tv_Nextdate_WS,tv_Description_WS;

        TextView txt_WS_Payment,txt_WS_Clear;


       TextView lin_MDY_call,lin_MDY_sms,lin_MDY_whapp,lin_MDY_share;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);

           this.tv_WS_CustomerName = itemView.findViewById(R.id.tv_WS_CustomerName);
           this.tv_Ws_MobileNo = itemView.findViewById(R.id.tv_Ws_MobileNo);
           this.tv_WS_Village = itemView.findViewById(R.id.tv_WS_Village);
           this.tv_WS_LeftAmount = itemView.findViewById(R.id.tv_WS_LeftAmount);

           this.txt_WS_Payment = itemView.findViewById(R.id.txt_WS_Payment);
           this.txt_WS_Clear = itemView.findViewById(R.id.txt_WS_Clear);

           this.tv_Nextdate_WS = itemView.findViewById(R.id.tv_Nextdate_WS);
           this.tv_Description_WS = itemView.findViewById(R.id.tv_Description_WS);

           this.lin_MDY_call=itemView.findViewById(R.id.lin_MDY_call);
           this.lin_MDY_sms=itemView.findViewById(R.id.lin_MDY_sms);
           this.lin_MDY_whapp=itemView.findViewById(R.id.lin_MDY_whapp);
           this.lin_MDY_share=itemView.findViewById(R.id.lin_MDY_share);
       }
   }

}
