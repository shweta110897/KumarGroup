package com.example.kumarGroup.Workshop.FeedBackCall;

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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.DataFeedBack;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class FeedbackMainAdapter extends RecyclerView.Adapter<FeedbackMainAdapter.ViewHolder>
{

    Context context;
    List<DataFeedBack> feedbackCallWSModels;

    private Activity activity;
    private Utils utils;
    private SharePref sp;
    SharedPreferences sp1;
    String emp;
    String Mobilecall;

    public FeedbackMainAdapter( Activity activity, List<DataFeedBack> feedbackCallWSModels) {
       // this.context = context;
        this.feedbackCallWSModels = feedbackCallWSModels;
        this.activity = activity;
        utils = new Utils(activity);
        sp = new SharePref(activity);
        context = activity.getApplicationContext();

        sp1 = activity.getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp1.getString("emp_id", "");

    }

    @NonNull
    @Override
    public FeedbackMainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.feedback_call_ws_raw, parent, false);
        FeedbackMainAdapter.ViewHolder viewHolder = new FeedbackMainAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackMainAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.tv_Customer_name_feedback.setText("Customer Name: "+feedbackCallWSModels.get(position).getCuname());
            holder.tv_work_ser_feedback.setText("Work Type: "+feedbackCallWSModels.get(position).getWorks_ser());
            holder.tv_mobile_no_feedback.setText("Mobile No: "+feedbackCallWSModels.get(position).getMobileno());
            holder.tv_Village_feedback.setText("Village: "+feedbackCallWSModels.get(position).getVillage());
            holder.tv_mechanicName_feedback.setText("Mechanic Name: "+feedbackCallWSModels.get(position).getMacanic());

        sp1 = activity.getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp1.getString("emp_id", "");


            holder.lin_CallCustomer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sp.putSharedPref(sp.PHONE_NUMBER, "+91" + feedbackCallWSModels.get(position).getMobileno());
                    sp.putSharedPref(sp.CALL_ID, feedbackCallWSModels.get(position).getId());

                    Log.d("SPCallLog", "onClick: " + feedbackCallWSModels.get(position).getMobileno());

                    if (!utils.userPermission.checkCallPermission()) {
                        utils.userPermission.requestCallPermission();
                    } else if (!utils.userPermission.checkCallLogPermission()) {
                        utils.userPermission.requestCallLogPermission();
                    } else {

                        Mobilecall = feedbackCallWSModels.get(position).getMobileno();
                        // Mobilecall1 = reaplace;

                        Log.d("Mobilecall", "onClick: " + Mobilecall);

                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:+91" + Mobilecall));
                        // intent.setData(Uri.parse("tel:+91" + Mobilecall1));
                        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        activity.startActivity(intent);
                        activity.finish();
                    }
                }
            });


            holder.lin_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context,RemoveCallActivity.class);
                    i.putExtra("Id",feedbackCallWSModels.get(position).getId());
                    i.putExtra("MobileNo",feedbackCallWSModels.get(position).getMobileno());
                    activity.startActivity(i);

                    /*WebService.getClient().getDeleteCallWs(emp,feedbackCallWSModels.get(position).getId(),
                            feedbackCallWSModels.get(position).getMobileno())
                            .enqueue(new Callback<DeleteCallWorkshopModel>() {
                        @Override
                        public void onResponse(@NotNull Call<DeleteCallWorkshopModel> call,
                                               @NotNull Response<DeleteCallWorkshopModel> response) {

                            assert response.body() != null;
                            Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(@NotNull Call<DeleteCallWorkshopModel> call, @NotNull Throwable t) {
                            Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show();
                        }
                    });*/
                }
            });
    }

    @Override
    public int getItemCount() {
        return feedbackCallWSModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_Customer_name_feedback,tv_work_ser_feedback,tv_mobile_no_feedback,tv_Village_feedback,
                lin_CallCustomer,lin_remove,tv_mechanicName_feedback;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_Customer_name_feedback = itemView.findViewById(R.id.tv_Customer_name_feedback);
            this.tv_work_ser_feedback = itemView.findViewById(R.id.tv_work_ser_feedback);
            this.tv_mobile_no_feedback = itemView.findViewById(R.id.tv_mobile_no_feedback);
            this.tv_Village_feedback = itemView.findViewById(R.id.tv_Village_feedback);
            this.lin_CallCustomer = itemView.findViewById(R.id.lin_CallCustomer);
            this.lin_remove = itemView.findViewById(R.id.lin_remove);
            this.tv_mechanicName_feedback = itemView.findViewById(R.id.tv_mechanicName_feedback);

        }
    }
}
