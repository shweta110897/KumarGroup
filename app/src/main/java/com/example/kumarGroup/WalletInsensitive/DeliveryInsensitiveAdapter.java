package com.example.kumarGroup.WalletInsensitive;

import static com.example.kumarGroup.Utils.isDate;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.DataInsensitive;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.WithdrawalWalletModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryInsensitiveAdapter extends RecyclerView.Adapter<DeliveryInsensitiveAdapter.ViewHolder>
{
    Context context;
    List<DataInsensitive> dataInsensitiveList;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public DeliveryInsensitiveAdapter(Context context, List<DataInsensitive> dataInsensitiveList) {
        this.context = context;
        this.dataInsensitiveList = dataInsensitiveList;

        sp = context.getSharedPreferences("Login", Context.MODE_PRIVATE);

    }

    @NonNull
    @Override
    public DeliveryInsensitiveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.insensitive_raw, parent, false);
        DeliveryInsensitiveAdapter.ViewHolder viewHolder = new DeliveryInsensitiveAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryInsensitiveAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_employee_Insen.setText("Employee: "+dataInsensitiveList.get(position).getEmployee());
        holder.tv_product_Insen.setText("Category Name: "+dataInsensitiveList.get(position).getProduct());
        holder.tv_Model_Insen.setText("Model: "+dataInsensitiveList.get(position).getModel());
        holder.tv_amount_Insen.setText("Withdrawal Amount: "+dataInsensitiveList.get(position).getAmount());
        holder.tv_Date_Insen.setText("Delivery Date: "+dataInsensitiveList.get(position).getDate());
        holder.tv_Cuname_Insen.setText("Customer Name: "+dataInsensitiveList.get(position).getCuname());
        holder.tv_Village_Insen.setText("Village: "+dataInsensitiveList.get(position).getVillage());
        holder.tv_Village_WithdrawDate.setText("Withdrawal Date: "+dataInsensitiveList.get(position).getWithdrawel_date());
        holder.txt_payment_pending.setText("Payment: "+"\n"+dataInsensitiveList.get(position).getFinal_amount());


        if (!isDate(dataInsensitiveList.get(position).getFinal_amount())) {
            if (dataInsensitiveList.get(position).getFinal_amount() == null) {
                holder.txt_Withdraw_Insen.setVisibility(View.GONE);
                holder.txt_payment_pending.setVisibility(View.VISIBLE);

            } else if (Integer.parseInt(dataInsensitiveList.get(position).getFinal_amount()) > 0) {
                holder.txt_Withdraw_Insen.setVisibility(View.GONE);
                holder.txt_payment_pending.setVisibility(View.VISIBLE);
            } else if (dataInsensitiveList.get(position).getFinal_amount().equals("0")) {
                holder.txt_Withdraw_Insen.setVisibility(View.VISIBLE);
                holder.txt_payment_pending.setVisibility(View.GONE);
                Log.d("ifff===>", "0");
            }
        }else{
            holder.txt_Withdraw_Insen.setVisibility(View.GONE);
            holder.txt_payment_pending.setVisibility(View.VISIBLE);

        }

        emp = sp.getString("emp_id", "");

        if (dataInsensitiveList.get(position).getFlag().equals("2")){
            Log.d("getWithdraw",dataInsensitiveList.get(position).getFlag());
            holder.txt_Withdraw_Insen.setText("In Process");
            holder.txt_Withdraw_Insen.setEnabled(false);
            holder.txt_Withdraw_Insen.setBackground(context.getResources().getDrawable(R.drawable.button_gray));
        }else{
            holder.txt_Withdraw_Insen.setEnabled(true);
            Log.d("amount",dataInsensitiveList.get(position).getAmount());
//            if(dataInsensitiveList.get(position).getAmount().equals("1")){
//                holder.txt_Withdraw_Insen.setVisibility(View.VISIBLE);
//            }
//            else{
//                holder.txt_Withdraw_Insen.setVisibility(View.GONE);
//            }
        }


        holder.txt_Withdraw_Insen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog= new ProgressDialog(context);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                WebService.getClient().getWithDraw(dataInsensitiveList.get(position).getId(),
                        emp
                        ).enqueue(new Callback<WithdrawalWalletModel>() {
                    @Override
                    public void onResponse(@NotNull Call<WithdrawalWalletModel> call,
                                           @NotNull Response<WithdrawalWalletModel> response) {
                        progressDialog.dismiss();
                        Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        if (mListener != null) {
                            mListener.onItemClick(position,dataInsensitiveList,response.body().getEmail());
                        }

//                        Intent intent=new Intent(context, WalletOtp.class);
//                        intent.putExtra("id",dataInsensitiveList.get(position).getId());
//                        intent.putExtra("name","Delivery");
//                        context.startActivity(intent);
//                        ((Activity)context).finish();



                    }

                    @Override
                    public void onFailure(@NotNull Call<WithdrawalWalletModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataInsensitiveList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_employee_Insen,tv_product_Insen,tv_Model_Insen,tv_amount_Insen,tv_Date_Insen;
        TextView txt_Withdraw_Insen,tv_Cuname_Insen,tv_Village_Insen,txt_payment_pending;
        TextView tv_Village_WithdrawDate;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            this.tv_employee_Insen = itemView.findViewById(R.id.tv_employee_Insen);
            this.tv_product_Insen = itemView.findViewById(R.id.tv_product_Insen);
            this.tv_Model_Insen = itemView.findViewById(R.id.tv_Model_Insen);
            this.tv_amount_Insen = itemView.findViewById(R.id.tv_amount_Insen);
            this.tv_Date_Insen = itemView.findViewById(R.id.tv_Date_Insen);
            this.txt_Withdraw_Insen = itemView.findViewById(R.id.txt_Withdraw_Insen);
            this.tv_Cuname_Insen = itemView.findViewById(R.id.tv_Cuname_Insen);
            this.tv_Village_Insen = itemView.findViewById(R.id.tv_Village_Insen);
            this.tv_Village_WithdrawDate = itemView.findViewById(R.id.tv_Village_WithdrawDate);
            this.txt_payment_pending = itemView.findViewById(R.id.txt_payment_pending);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, List<DataInsensitive> dataInsensitiveList,String email);
    }

}
