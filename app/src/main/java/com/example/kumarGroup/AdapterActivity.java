package com.example.kumarGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class AdapterActivity  extends RecyclerView.Adapter<AdapterActivity.ViewHolder> {
    List<DataTask> listTask;
    private Context context;
    private Activity activity;
    private Utils utils;
    private SharePref sp;
    SharedPreferences sp1;
    String emp;
    String pos = null;

    String whapppos = null;
    String reaplace;
    String whappreaplace;
    String Mobilecall;

    String sms = "";//The message you want to text to the phone
    public AdapterActivity(List<DataTask> listTask, Activity activity) {
        this.listTask = listTask;
        this.activity = activity;
        utils = new Utils(activity);
        sp =  new SharePref(activity);
        context = activity.getApplicationContext();
        sp1 = activity.getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");
    }
    @NonNull
    @Override
    public AdapterActivity.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.activity_adapter, parent, false);
        AdapterActivity.ViewHolder viewHolder = new AdapterActivity.ViewHolder(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

    if (listTask.get(position).getTask().contains("Call")) {
        holder.tv_Customer_name.setText(listTask.get(position).getTask_take_name());
        holder.tv_mobile_nocustomer.setText(listTask.get(position).getMobile());
        holder.tv_village.setText(listTask.get(position).getVillage());
        holder.lin_callcustomer.setVisibility(View.VISIBLE);
        holder.lin_smscustomer.setVisibility(View.GONE);
        holder.lin_whappcustomer.setVisibility(View.GONE);


        holder.lin_replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Replace Number");
                builder.setMessage("Are sure want to replace number");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Log.d("empid", "replaceNumber: " + emp);
                        WebService.getClient().getreplacenumber(emp,
                                listTask.get(position).getCat_id(),
                                listTask.get(position).getCall_id(),
                                listTask.get(position).getTask_type(),
                                listTask.get(position).getTask()

                        ).enqueue(new Callback<DataReaplceModel>() {
                            @Override
                            public void onResponse(@NotNull Call<DataReaplceModel> call, @NotNull Response<DataReaplceModel> response) {
                                /*holder.tv_Customer_name.setText(response.body().getTask().get(0).getTask_take_name());
                                holder.tv_mobile_nocustomer.setText(response.body().getTask().get(0).getMobileno());
                                holder.tv_village.setText(response.body().getTask().get(0).getVilage());
                                pos = listTask.get(position).getMobile();
                                reaplace = response.body().getTask().get(0).getMobileno();*/

                                Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(@NotNull Call<DataReaplceModel> call, @NotNull Throwable t) {
                                Toast.makeText(context, "Error in replacing", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });

        holder.lin_callcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.putSharedPref(sp.PHONE_NUMBER, "+91" + listTask.get(position).getMobile());
                sp.putSharedPref(sp.CALL_ID, listTask.get(position).getCall_id());
                sp.putSharedPref(sp.task_type, listTask.get(position).getTask());

                if (!utils.userPermission.checkCallPermission()) {
                    utils.userPermission.requestCallPermission();
                } else if (!utils.userPermission.checkCallLogPermission()) {
                    utils.userPermission.requestCallLogPermission();
                } else {
                    Mobilecall = listTask.get(position).getMobile();

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:+91" + Mobilecall));
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    activity.startActivity(intent);
                    activity.finish();
                }
            }
        });


    } else if (listTask.get(position).getTask().contains("Whatsapp")) {
        holder.tv_Customer_name.setText(listTask.get(position).getTask_take_name());
        holder.tv_mobile_nocustomer.setText(listTask.get(position).getWhatsappno());
        holder.tv_village.setText(listTask.get(position).getVillage());
        holder.lin_callcustomer.setVisibility(View.GONE);
        holder.lin_smscustomer.setVisibility(View.GONE);
        holder.lin_whappcustomer.setVisibility(View.VISIBLE);
        holder.lin_whappcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean installed = appInstallOrNot("com.whatsapp");
                if (installed) {
                    String Whapp = holder.tv_mobile_nocustomer.getText().toString();
                    int po = holder.getAdapterPosition();

                    WebService.getClient().removewhappassignment(listTask.get(position).getCall_id(), "",
                            listTask.get(position).getWhatsappno(),
                            emp,
                            listTask.get(position).getTask()).enqueue(new Callback<DataremoveassignmentModel>() {
                        @Override
                        public void onResponse(@NotNull Call<DataremoveassignmentModel> call,
                                               @NotNull Response<DataremoveassignmentModel> response) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+91" + Whapp));
                            activity.startActivities(new Intent[]{intent});

                        }

                        @Override
                        public void onFailure(@NotNull Call<DataremoveassignmentModel> call, @NotNull Throwable t) {

                        }
                    });
                    listTask.remove(po);
                    notifyItemRemoved(po);
                    notifyItemRangeChanged(po, listTask.size());

                    //   startActivity(intent);
                } else {

                    //  Utils.showNormalToast("Whats app not installed on your device ");

                    //   Utils.showErrorToast(AdapterTaskinfo.this,"Whats app not installed on your device ");

                    // Toast.makeText(Context.this, "Whats app not installed on your device ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        holder.lin_replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Replace Number");
                builder.setMessage("Are sure want to replace number");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //   replaceNumber(call_id[position]);

                        WebService.getClient().getreplacenumber(emp,
                                listTask.get(position).getCat_id(),
                                listTask.get(position).getCall_id(),
                                listTask.get(position).getTask_type(),
                                listTask.get(position).getTask()
                        ).enqueue(new Callback<DataReaplceModel>() {
                            @Override
                            public void onResponse(@NotNull Call<DataReaplceModel> call, @NotNull Response<DataReaplceModel> response) {
                               /* holder.tv_Customer_name.setText(response.body().getTask().get(0).getTask_take_name());
                                holder.tv_mobile_nocustomer.setText(response.body().getTask().get(0).getWhatsappno());
                                holder.tv_village.setText(response.body().getTask().get(0).getVilage());

                                whapppos = listTask.get(position).getWhatsappno();
                                whappreaplace = response.body().getTask().get(0).getWhatsappno();*/
                                //  holder.tv_Customer_name.setText( listTask.get(position).getTask_take_name().replace(listTask.get(position).getTask_take_name(),response.body().getTask().get(0).getTask_take_name()));
                                //  holder.tv_mobile_nocustomer.setText( listTask.get(position).getWhatsappno().replace(listTask.get(position).getMobile(),response.body().getTask().get(0).getWhatsappno()));
                                //  holder.tv_village.setText( listTask.get(position).getVillage().replace(listTask.get(position).getVillage(),response.body().getTask().get(0).getVilage()));

                                Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();


                            }

                            @Override
                            public void onFailure(@NotNull Call<DataReaplceModel> call, @NotNull Throwable t) {

                                Toast.makeText(context, "Error in replacing", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });
    } else if (listTask.get(position).getTask().contains("Sms")) {
        holder.tv_Customer_name.setText(listTask.get(position).getTask_take_name());
        holder.tv_mobile_nocustomer.setText(listTask.get(position).getMobile());
        holder.tv_village.setText(listTask.get(position).getVillage());

        holder.lin_callcustomer.setVisibility(View.GONE);
        holder.lin_smscustomer.setVisibility(View.VISIBLE);
        holder.lin_whappcustomer.setVisibility(View.GONE);
        holder.lin_smscustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Mobilecall = holder.tv_mobile_nocustomer.getText().toString();

                /*Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", Mobilecall, null));
                smsIntent.putExtra("sms_body", sms);
                activity.startActivities(new Intent[]{smsIntent});*/
                // startActivity(smsIntent);

                int po = holder.getAdapterPosition();

                WebService.getClient().removeWhapp(listTask.get(position).getCall_id(),
                        listTask.get(position).getMobile(),
                        listTask.get(position).getMobile(),
                        emp,
                        listTask.get(position).getTask()).enqueue(new Callback<RemoveWhatappModel>() {
                    @Override
                    public void onResponse(@NotNull Call<RemoveWhatappModel> call,
                                           @NotNull Response<RemoveWhatappModel> response) {

                        Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", Mobilecall, null));
                        smsIntent.putExtra("sms_body", sms);
                        activity.startActivities(new Intent[]{smsIntent});
                        // startActivity(smsIntent);
                    }

                    @Override
                    public void onFailure(@NotNull Call<RemoveWhatappModel> call, @NotNull Throwable t) {

                    }
                });
                listTask.remove(po);
                notifyItemRemoved(po);
                notifyItemRangeChanged(po, listTask.size());

            }
        });
        holder.lin_replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Replace Number");
                builder.setMessage("Are sure want to replace number");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Log.d("empid", "replaceNumber: " + emp);
                        WebService.getClient().getreplacenumber(emp,
                                listTask.get(position).getCat_id(),
                                listTask.get(position).getCall_id(),
                                listTask.get(position).getTask_type(),
                                listTask.get(position).getTask()
                        ).enqueue(new Callback<DataReaplceModel>() {
                            @Override
                            public void onResponse(@NotNull Call<DataReaplceModel> call, Response<DataReaplceModel> response) {
                               /* holder.tv_Customer_name.setText(response.body().getTask().get(0).getTask_take_name());
                                holder.tv_mobile_nocustomer.setText(response.body().getTask().get(0).getMobileno());
                                holder.tv_village.setText(response.body().getTask().get(0).getVilage());

                                pos = listTask.get(position).getMobile();
                                reaplace = response.body().getTask().get(0).getMobileno();*/
                                //     holder.tv_Customer_name.setText( listTask.get(position).getTask_take_name().replace(listTask.get(position).getTask_take_name(),response.body().getTask().get(0).getTask_take_name()));
                                //      holder.tv_mobile_nocustomer.setText( listTask.get(position).getMobile().replace(listTask.get(position).getMobile(),response.body().getTask().get(0).getMobileno()));
                                //    holder.tv_village.setText( listTask.get(position).getVillage().replace(listTask.get(position).getVillage(),response.body().getTask().get(0).getVilage()));

                                Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();


                            }

                            @Override
                            public void onFailure(Call<DataReaplceModel> call, Throwable t) {
                                Toast.makeText(context, "Error in replacing", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });
    }



       /* else {

            holder.btn_nexttask.setVisibility(View.VISIBLE);
            holder.btn_nexttask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (listTask != null) {

                        Toast.makeText(activity, "Please Complet TAsk", Toast.LENGTH_SHORT).show();

                    } else {
                        Intent i = new Intent(activity, LanchBreak.class);
                        activity.startActivity(new Intent(i));

                    }
                }
            });
            if (holder.getAdapterPosition() <= 0) {
                Log.d("taskinfo", "onBindViewHolder: " + holder.getAdapterPosition());
                Log.d("taskinfo", "onBindViewHolder: " + listTask.size());
                holder.txtvhide.setVisibility(View.VISIBLE);
                holder.txtvhide.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "textview clicked", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }*/
    }


    private boolean appInstallOrNot(String s) {
        return true;
    }
    @Override
    public int getItemCount() {
        return listTask.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Customer_name,tv_mobile_nocustomer,lin_callcustomer,lin_whappcustomer,lin_smscustomer,tv_village,lin_replace,txtvhide;
        LinearLayout data_recyclerTask;
        Button btn_nexttask;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_Customer_name=itemView.findViewById(R.id.tv_Customer_name);
            this.tv_village=itemView.findViewById(R.id.tv_village);
            this.lin_replace=itemView.findViewById(R.id.lin_replace);
            this.tv_mobile_nocustomer=itemView.findViewById(R.id.tv_mobile_nocustomer);
            this.lin_callcustomer=itemView.findViewById(R.id.lin_callcustomer);
            this.lin_smscustomer=itemView.findViewById(R.id.lin_smscustomer);
            this.lin_whappcustomer=itemView.findViewById(R.id.lin_whappcustomer);
            this.data_recyclerTask=itemView.findViewById(R.id.data_recyclerTask);
            this.txtvhide=itemView.findViewById(R.id.txtvhide);
        }
    }
}