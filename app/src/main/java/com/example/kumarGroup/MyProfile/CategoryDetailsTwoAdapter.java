package com.example.kumarGroup.MyProfile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatViewProfDetail;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CategoryDetailsTwoAdapter extends RecyclerView.Adapter<CategoryDetailsTwoAdapter.ViewHolder>
    implements Filterable
{

    Context context;
    List<CatViewProfDetail> catViewProfDetails;
    List<CatViewProfDetail> catViewProfDetails_one;

    String sms= "";//The message you want to text to the phone

    SharePref sp;
    SharedPreferences sharedPreferences,sp1;
    Utils utils;
    Activity activity;

    String Mobilecall;
    String emp;


    public CategoryDetailsTwoAdapter(Context context, List<CatViewProfDetail> catViewProfDetails) {
        this.context = context;
        this.catViewProfDetails = catViewProfDetails;
        this.catViewProfDetails_one = catViewProfDetails;

        activity = (Activity) context;

        utils = new Utils(activity);
        sp = new SharePref(activity);
        context = activity.getApplicationContext();

        sp1 = activity.getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");
    }

    @NonNull
    @Override
    public CategoryDetailsTwoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.gen_inq_view_data_raw, parent, false);
        CategoryDetailsTwoAdapter.ViewHolder viewHolder = new CategoryDetailsTwoAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryDetailsTwoAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_fname_genVI.setText("Name: "+catViewProfDetails.get(position).getFname()+""+catViewProfDetails.get(position).getLname());
        holder.tv_cat_name_genVI.setText("Category: "+catViewProfDetails.get(position).getCat_name());
        holder.tv_mobile_no_genVI.setText("Mobile: "+catViewProfDetails.get(position).getMoblino());
        holder.tv_Whapp_Number_genVI.setText("WhatsApp Number: "+catViewProfDetails.get(position).getWhatsappno());
        holder.tv_state_genVI.setText("State: "+catViewProfDetails.get(position).getState());
        holder.tv_city_genVI.setText("City: "+catViewProfDetails.get(position).getCity());
        holder.tv_district_genVI.setText("District: "+catViewProfDetails.get(position).getDistric());
        holder.tv_taluko_genVI.setText("Taluko: "+catViewProfDetails.get(position).getTehsil());

        holder.tv_village_genVI.setText("Village: "+catViewProfDetails.get(position).getVilage());
        holder.tv_EmpName_genVI.setText("Employee Name: "+catViewProfDetails.get(position).getEmployee_name());
        holder.tv_Desc_genVI.setText("Description: "+catViewProfDetails.get(position).getDesc());

        holder.tv_NextDate_genVI.setVisibility(View.GONE);
        holder.tv_type_inq_genVI.setVisibility(View.GONE);


        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+catViewProfDetails.get(position).getMoblino()));
                Log.d("call", "onClick: "+catViewProfDetails.get(position).getMoblino());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);*/

                sp.putSharedPref(sp.PHONE_NUMBER, "+91" + catViewProfDetails.get(position).getMoblino());
                sp.putSharedPref(sp.CALL_ID, catViewProfDetails.get(position).getCat_id());
                sp.putSharedPref(sp.task_type, "");

                if (!utils.userPermission.checkCallPermission()) {
                    utils.userPermission.requestCallPermission();
                } else if (!utils.userPermission.checkCallLogPermission()) {
                    utils.userPermission.requestCallLogPermission();
                } else {
                    Mobilecall = catViewProfDetails.get(position).getMoblino();

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:+91" + Mobilecall));
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    activity.startActivity(intent);
                    activity.finish();
                }


            }
        });


        holder.lin_MDY_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",
                        catViewProfDetails.get(position).getMoblino(), null));
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
                            catViewProfDetails.get(position).getWhatsappno()));
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
                        "Customer Name: "+catViewProfDetails.get(position).getFname()+"\n" +
                        "Mobile: "+catViewProfDetails.get(position).getMoblino()+"\n"+
                        "Village: "+catViewProfDetails.get(position).getVilage()+"\n"+
                        "Category: "+catViewProfDetails.get(position).getCat_name());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Intent.createChooser(i,"Share via"));

            }
        });

        holder.lin_MDY_visit.setVisibility(View.GONE);



        holder.LinShowDetailGenVI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewProfileDetailsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("FirstName", "First Name : " + catViewProfDetails.get(position).getFname());
                i.putExtra("LastName", "Last Name : " + catViewProfDetails.get(position).getLname());
                i.putExtra("Mobile", "Mobile Number : " + catViewProfDetails.get(position).getMoblino());
                i.putExtra("Mobilecall", catViewProfDetails.get(position).getMoblino());
                i.putExtra("Whappnumber", "Whapp Number : " + catViewProfDetails.get(position).getWhatsappno());
                i.putExtra("Whappnumbercall", catViewProfDetails.get(position).getWhatsappno());
                i.putExtra("Customordoit", "Customer Do it : " + catViewProfDetails.get(position).getCat_name());
                i.putExtra("Village", "Village : " + catViewProfDetails.get(position).getVilage());
                i.putExtra("district", "District : " + catViewProfDetails.get(position).getDistric());
                i.putExtra("Taluko", "Taluko : " + catViewProfDetails.get(position).getTehsil());
                i.putExtra("Id",catViewProfDetails.get(position).getAutoid());
                i.putExtra("cat_id",catViewProfDetails.get(position).getCat_id());
                i.putExtra("eid",catViewProfDetails.get(position).getEid());

                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return catViewProfDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_cat_name_genVI,tv_fname_genVI,tv_mobile_no_genVI,
                tv_Whapp_Number_genVI,tv_state_genVI,tv_city_genVI,tv_district_genVI,
                tv_taluko_genVI,tv_village_genVI,tv_EmpName_genVI,tv_Desc_genVI,tv_NextDate_genVI,
                tv_type_inq_genVI;

        LinearLayout LinShowDetailGenVI,lin_inqview_CSWS;

        TextView lin_MDY_call, lin_MDY_sms, lin_MDY_whapp, lin_MDY_share, lin_MDY_visit;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_cat_name_genVI= itemView.findViewById(R.id.tv_cat_name_genVI);
            this.tv_fname_genVI= itemView.findViewById(R.id.tv_fname_genVI);
            this.tv_mobile_no_genVI= itemView.findViewById(R.id.tv_mobile_no_genVI);
            this.tv_Whapp_Number_genVI= itemView.findViewById(R.id.tv_Whapp_Number_genVI);
            this.tv_state_genVI= itemView.findViewById(R.id.tv_state_genVI);
            this.tv_city_genVI= itemView.findViewById(R.id.tv_city_genVI);

            this.tv_district_genVI= itemView.findViewById(R.id.tv_district_genVI);
            this.tv_taluko_genVI= itemView.findViewById(R.id.tv_taluko_genVI);
            this.tv_village_genVI= itemView.findViewById(R.id.tv_village_genVI);
            this.tv_EmpName_genVI= itemView.findViewById(R.id.tv_EmpName_genVI);
            this.tv_Desc_genVI= itemView.findViewById(R.id.tv_Desc_genVI);
            this.tv_NextDate_genVI= itemView.findViewById(R.id.tv_NextDate_genVI);
            this.tv_type_inq_genVI= itemView.findViewById(R.id.tv_type_inq_genVI);
            this.LinShowDetailGenVI= itemView.findViewById(R.id.LinShowDetailGenVI);


            this.lin_MDY_call = itemView.findViewById(R.id.lin_MDY_call);
            this.lin_MDY_sms = itemView.findViewById(R.id.lin_MDY_sms);
            this.lin_MDY_whapp = itemView.findViewById(R.id.lin_MDY_whapp);
            this.lin_MDY_share = itemView.findViewById(R.id.lin_MDY_share);
            this.lin_MDY_visit = itemView.findViewById(R.id.lin_MDY_visit);

            this.lin_inqview_CSWS = itemView.findViewById(R.id.lin_inqview_CSWS);


        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    //  catShowRCGVS_one = catShowRCGVS;
                    // FilterdlistData = listData;
                    catViewProfDetails = catViewProfDetails_one;
                } else {

                    List<CatViewProfDetail> filteredList = new ArrayList<>();

                    for (CatViewProfDetail row : catViewProfDetails_one) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName =row.getFname();
                        String strMobile =row.getMoblino();
                        String strVName =row.getVilage();
                        Log.d("TAG", "Data: "+row);

                        if(strFName == null)
                            strFName = " ";
                        if(strMobile == null)
                            strMobile = " ";
                        if(strVName == null)
                            strVName = " ";


                        if (strFName.toLowerCase().contains(charString.toLowerCase())
                                || strMobile.toLowerCase().contains(charString.toLowerCase())
                                /* || row.getDistric().toLowerCase().contains(charString.toLowerCase())*/
                                || strVName.toLowerCase().contains(charString.toLowerCase()))
                        {
                            filteredList.add(row);
                        }
                    }
                    catViewProfDetails = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = catViewProfDetails;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                catViewProfDetails = (ArrayList<CatViewProfDetail>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
