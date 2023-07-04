package com.example.kumarGroup.FeedbackCall;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.AdapterTaskinfo;
import com.example.kumarGroup.Cat1;
import com.example.kumarGroup.DealstageAK.DealstageHistoryActivity;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;

import java.util.ArrayList;
import java.util.List;

public class FeedbackCallAdapter extends RecyclerView.Adapter<FeedbackCallAdapter.ViewHolder> implements Filterable{


    Context context;
    List<com.example.kumarGroup.Cat1> Cat1;
    List<Cat1> Cat1_one;

    SharePref sp;
    String Mobilecall;
    String sms= "",emp;
    Activity activity;
    Utils utils;

    Cursor managedCursor;
    SharedPreferences sharedPreferences1;
    int vposition;
    String phonenumber,callerid;

    private AdapterTaskinfo.CallItemInterface callItemInterface;

    public FeedbackCallAdapter(Context context,Activity activity,List<Cat1> Cat1) {
        this.context = context;
        this.Cat1 = Cat1;
        this.Cat1_one = Cat1;

        utils = new Utils(activity);
        sp = new SharePref(context);
        this.activity = activity;
    }

    @NonNull
    @Override
    public FeedbackCallAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.feedbackcall_view,parent,false);
        FeedbackCallAdapter.ViewHolder viewHolder = new FeedbackCallAdapter.ViewHolder(view);

        return viewHolder;
    }

//    SharedPreferences shared = context.getSharedPreferences("Calling",Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = shared.edit();
    @Override
    public void onBindViewHolder(@NonNull FeedbackCallAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (Cat1.get(position).getFname()==null){
            holder.customer_name.setText("");
        }else {
            holder.customer_name.setText(Cat1.get(position).getFname());
        }
        if (Cat1.get(position).getMoblino()==null){
            holder.mobile_no.setText("");
        }else {
            holder.mobile_no.setText(Cat1.get(position).getMoblino());
        }
        if (Cat1.get(position).getVilage()==null){
            holder.village.setText("");
        }else {
            holder.village.setText(Cat1.get(position).getVilage());
        }
        if (Cat1.get(position).getAdd_date()==null){
            holder.date.setText("");
        }else {
            holder.date.setText(Cat1.get(position).getAdd_date());
        }
        if (Cat1.get(position).getCurrent_stage_name()==null){
            holder.deal_stage.setText("");
        }else {
            holder.deal_stage.setText(Cat1.get(position).getCurrent_stage_name().toString());
           // Log.d("TAG", "onBindViewHolder: Deal_stage"+Cat1.get(position).getCurrent_stage_name().toString());
        }
        if (Cat1.get(position).getEmployee_name()==null){
            holder.employee_vemp.setText("");
           //Log.d("TAG", "onBindViewHolder: Employee_name_feedback_call"+"empty");
        }else {
            holder.employee_vemp.setText(Cat1.get(position).getEmployee_name());
            //Log.d("TAG", "onBindViewHolder: Employee_name_feedback_call"+Cat1.get(0).getEmployee_name());
        }
        //Log.d("TAG", "onBindViewHolder: lin_MDY_call_check"+Cat1.get(0).getFeedback_call_done());

        if (Cat1.get(position).getFeedback_call_done().equals("0")){
            //Log.d("TAG", "onBindViewHolder: lin_MDY_call_check-1");
            holder.lin_MDY_call.setVisibility(View.VISIBLE);
        }else {
            //Log.d("TAG", "onBindViewHolder: lin_MDY_call_check-2");
            holder.lin_MDY_call.setVisibility(View.GONE);
        }
        //holder.lin_MDY_call.setVisibility(View.VISIBLE);
//        getLastNumber();

        holder.LinearLayout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Cat1.get(position).getFeedback_call_done().equals("1")){
                    Intent intent = new Intent(context,FeedbackCallAddActivity.class);
                    intent.putExtra("autoid", Cat1.get(position).getAutoid());
                    intent.putExtra("number",Cat1.get(position).getWhatsappno());
                    context.startActivity(intent);
                    activity.finish();
                }else {

                }

            }
        });

        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                phonenumber = "+91" + Cat1.get(position).getMoblino();
                callerid = Cat1.get(position).getAutoid();


                sharedPreferences1 = context.getSharedPreferences("Calling",Context.MODE_PRIVATE);
                sharedPreferences1.edit().putString("Phonenumber","+91" + Cat1.get(position).getMoblino()).apply();

                sharedPreferences1 = context.getSharedPreferences("Calling", Activity.MODE_PRIVATE);
                sharedPreferences1.edit().putString("CallerId", Cat1.get(position).getAutoid()).apply();

                sp.putSharedPref(sp.PHONE_NUMBER, "+91" + Cat1.get(position).getMoblino());
                sp.putSharedPref(sp.CALL_ID, Cat1.get(position).getAutoid());
                sp.putSharedPref(sp.task_type, "");

                if (!utils.userPermission.checkCallPermission()) {
                    utils.userPermission.requestCallPermission();
                } else if (!utils.userPermission.checkCallLogPermission()) {
                    utils.userPermission.requestCallLogPermission();
                } else {
                    Mobilecall = Cat1.get(position).getMoblino();

//                    getLastNumber();

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:+91" + Mobilecall));
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                  /*  activity.startActivity(intent);
                    activity.finish();*/

                    activity.startActivity(intent);

                    if (callItemInterface != null) {
                        callItemInterface.onItemClick(holder.getAdapterPosition(),Cat1.get(position).getAutoid());
                    }
                    Cat1.remove(position);
                    notifyDataSetChanged();
                    activity.finish();


                    vposition = Integer.parseInt(Cat1.get(position).getAutoid());


                    //FeedBackCall_Call button visible
//                    WebService.getClient().FeedBackCall_call(Cat1.get(position).getAutoid()).enqueue(new Callback<FeedBackCall_call>() {
//                        @Override
//                        public void onResponse(Call<FeedBackCall_call> call, Response<FeedBackCall_call> response) {
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<FeedBackCall_call> call, Throwable t) {
//
//                        }
//                    });

//                    WebService.getClient().getMyInqProfile_CSW(emp,
//                            mcatlist.get(position).getAutoid(),
//                            mcatlist.get(position).getMoblino(),
//                            "Call"
//                    ).enqueue(new Callback<myInqCswModel>() {
//                        @Override
//                        public void onResponse(@NotNull Call<myInqCswModel> call, @NotNull Response<myInqCswModel> response) {
//                            // Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                            Log.d("Call", "onResponse: "+response.body().getMsg());
//                        }
//
//                        @Override
//                        public void onFailure(@NotNull Call<myInqCswModel> call, @NotNull Throwable t) {
//
//                        }
//                    });
                }
            }
        });
        holder.lin_MDY_whapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean installed = appInstallOrNot("com.whatsapp");
                if (installed){

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="
                            +"+91"+Cat1.get(position).getWhatsappno()));
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
        holder.lin_MDY_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.fromParts("sms", Cat1.get(position).getMoblino(), null));
                smsIntent.putExtra("sms_body",sms);
                smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(smsIntent);

//                if (DealstageRecyclerActivity.notattend_flag_sms_call_what) {
//
//                    WebService.getClient().deal_send_call_sms_what_web(mcatlist.get(position).getAutoid()).enqueue(new Callback<deal_stage_call_sms_what_model>() {
//                        @Override
//                        public void onResponse(Call<deal_stage_call_sms_what_model> call, Response<deal_stage_call_sms_what_model> response) {
//                            Log.d("TAG", "call_sms_what " + response.body().getMsg());
//                        }
//
//                        @Override
//                        public void onFailure(Call<deal_stage_call_sms_what_model> call, Throwable t) {
//
//                        }
//                    });
//                }
//                WebService.getClient().getMyInqProfile_CSW(emp,
//                        mcatlist.get(position).getAutoid(),
//                        mcatlist.get(position).getMoblino(),
//                        "Sms"
//                ).enqueue(new Callback<myInqCswModel>() {
//                    @Override
//                    public void onResponse(@NotNull Call<myInqCswModel> call, @NotNull Response<myInqCswModel> response) {
//                        //Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                        Log.d("Call", "onResponse: "+response.body().getMsg());
//
//                    }
//
//                    @Override
//                    public void onFailure(@NotNull Call<myInqCswModel> call, @NotNull Throwable t) {
//
//                    }
//                });
            }
        });
        holder.homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, DealstageHistoryActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //   i.putExtra("sname",catAllEntryMWDS.get(position).getId());
                i.putExtra("sname",Cat1.get(position).getId());
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return Cat1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView customer_name,mobile_no,village,date,deal_stage,employee_vemp;
        ImageView lin_MDY_call,lin_MDY_whapp,lin_MDY_sms,homeIcon;
        LinearLayout LinearLayout_button;

        

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customer_name=itemView.findViewById(R.id.customer_name);
            mobile_no=itemView.findViewById(R.id.mobile_number);
            village=itemView.findViewById(R.id.village);
            date=itemView.findViewById(R.id.date);
            deal_stage=itemView.findViewById(R.id.deal_stage);
            employee_vemp=itemView.findViewById(R.id.employee_vemp);
            lin_MDY_call=itemView.findViewById(R.id.lin_MDY_call);
            lin_MDY_whapp=itemView.findViewById(R.id.lin_MDY_whapp);
            lin_MDY_sms=itemView.findViewById(R.id.lin_MDY_sms);
            homeIcon=itemView.findViewById(R.id.homeIcon);
            LinearLayout_button=itemView.findViewById(R.id.LinearLayout_button);


        }
    }


//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void getLastNumber() {
//        Uri contacts = CallLog.Calls.CONTENT_URI;
//        Bundle bundle = new Bundle();
//        bundle.putInt(ContentResolver.QUERY_ARG_LIMIT, 100);
//
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG)
//                != PackageManager.PERMISSION_GRANTED) {
//            utils.userPermission.requestCallLogPermission();
//        }
//
//        try {
//            managedCursor = context.getApplicationContext().getContentResolver().query(contacts, null, null, null, android.provider.CallLog.Calls.DATE + " DESC limit 1;");
//        }
//        catch (Exception e){
//
//            managedCursor = context.getApplicationContext().getContentResolver().query(contacts, null, bundle, null);
//        }
//
//        if (managedCursor != null && managedCursor.getCount() != 0) {
//
//
//            int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
//            int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
//            int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
//            int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
//
//            managedCursor.moveToNext();
//            String phNumber = managedCursor.getString(number);
//            String callType = managedCursor.getString(type);
//            String callDate = managedCursor.getString(date);
//            String callDayTime = new Date(Long.parseLong(callDate)).toString();
//            int callDuration = managedCursor.getInt(duration);
//
//            Log.d("TAG", "getLastNumber: Callduration"+callDuration);
//            managedCursor.close();
//
//            int dircode = Integer.parseInt(callType);
//            utils.printLog("Details", "Phone Number:--- " + phNumber + " " +
//                    ",Call Date:--- " + callDayTime + " ,Call duration in sec :--- " + callDuration);
//
////            call_id = new String[dataTaskModel.getTask().size()];
////            for (int i = 0; i < dataTaskModel.getTask().size(); i++){
////                call_id[i] = dataTaskModel.getTask().get(i).getCall_id();
////            }
////            int pos = new ArrayList<>(Arrays.asList(call_id)).indexOf(sp.getSharedPref(sp.CALL_ID));
//
//            if (callerid != null
//                    && phonenumber != null ) {
//                if (dircode == CallLog.Calls.OUTGOING_TYPE && callDuration > 0 && phonenumber.equals(phNumber)) {
////                float callDurationMinut = (float) callDuration / 60;
//                    //fillAttendance(sp.getSharedPref(sp.CALL_ID), phNumber,sp.getSharedPref(sp.task_type));
//                    Log.d("TAG", "getLastNumber: Call_doneaj"+callDuration);
//
////                    WebService.getClient().FeedBackCall_call(Cat1.get(vposition).getAutoid()).enqueue(new Callback<FeedBackCall_call>() {
////                        @Override
////                        public void onResponse(Call<FeedBackCall_call> call, Response<FeedBackCall_call> response) {
////
////                        }
////
////                        @Override
////                        public void onFailure(Call<FeedBackCall_call> call, Throwable t) {
////
////                        }
////                    });
//                }
//            }
//
//        }
//        else {
//            Toast.makeText(context.getApplicationContext(), "Log is Blank !!", Toast.LENGTH_SHORT).show();
//        }
//
//
//    }

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
                    Cat1 = Cat1_one;
                } else {

                    List<Cat1> filteredList = new ArrayList<>();

                    for (Cat1 row : Cat1_one) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName =row.getFname();
                        String strMobile =row.getMoblino();
                        String strVName =row.getVilage();
                        String strModel = (String) row.getModel();
                        //String strLName =row.getLname();
                        String strWtaNumber =row.getWhatsappno();
                        //   Log.d("TAG", "Data: "+row);

                        if(strFName == null)
                            strFName = " ";
                        if(strMobile == null)
                            strMobile = " ";
                        if(strVName == null)
                            strVName = " ";
                        if(strModel == null)
                            strModel = " ";
                       /* if(strLName == null)
                            strLName = " ";
*/
                        if(strWtaNumber == null)
                            strWtaNumber = " ";


                        if (strFName.toLowerCase().contains(charString.toLowerCase())
                                || strMobile.toLowerCase().contains(charString.toLowerCase())
                                || strWtaNumber.toLowerCase().contains(charString.toLowerCase())
                                || strModel.toLowerCase().contains(charString.toLowerCase())
                                || strVName.toLowerCase().contains(charString.toLowerCase())
                            // || strLName.toLowerCase().contains(charString.toLowerCase())
                        )
                        {
                            filteredList.add(row);
                        }
                    }
                    Cat1 = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = Cat1;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Cat1 = (ArrayList<Cat1>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
