package com.example.kumarGroup.EmployeeTracker.ak;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.Dataalldata;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.emp_filter_date_traking_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Totallocation_Adapter extends RecyclerView.Adapter<Totallocation_Adapter.viewHolder> {

    Context context;
    List<Dataalldata> empalldatalist;

    public Totallocation_Adapter(Context context, List<Dataalldata> empalldatalist) {
        this.context = context;
        this.empalldatalist = empalldatalist;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_data_row,parent,false);
        return new Totallocation_Adapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
      holder.day_time_location.setText(empalldatalist.get(position).getDate());
      holder.count_day_location.setText(String.valueOf(position+1));
      holder.daylocation.setText(empalldatalist.get(position).getLocation());
      holder.daytime.setText(empalldatalist.get(position).getTime());

      holder.show_all_location_filter.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
                context.startActivity(new Intent(context,ShowTripFilterActivity.class)
                .putExtra("date",empalldatalist.get(position).getDate()));
          }
      });

      holder.linedrawmap.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              SharedPreferences sharedPreferences = context.getSharedPreferences("emp_location_id",MODE_PRIVATE);
              String emp_id = sharedPreferences.getString("id","");

              WebService.getClient().emp_filterdate_traking_web(emp_id,empalldatalist.get(position).getDate()).enqueue(new Callback<emp_filter_date_traking_model>() {
                  @Override
                  public void onResponse(Call<emp_filter_date_traking_model> call, Response<emp_filter_date_traking_model> response) {

                      context.startActivity(new Intent(context, LineDrawMapActivity.class)
                              .putExtra("lat0", response.body().getData().get(0).getLatitude())
                              .putExtra("long0", response.body().getData().get(0).getLongitude())

                              .putExtra("lat1", response.body().getData().get(1).getLatitude())
                              .putExtra("long1", response.body().getData().get(1).getLongitude())

                      );
                  }

                  @Override
                  public void onFailure(Call<emp_filter_date_traking_model> call, Throwable t) {
                      Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                  }
              });
          }
      });
    }

    @Override
    public int getItemCount() {
        return empalldatalist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView day_time_location,count_day_location,daylocation,daytime;
        ImageView show_all_location_filter,linedrawmap;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            day_time_location = itemView.findViewById(R.id.day_time_location);
            count_day_location = itemView.findViewById(R.id.count_day_location);
            daylocation = itemView.findViewById(R.id.daylocation);
            daytime = itemView.findViewById(R.id.daytime);
            show_all_location_filter = itemView.findViewById(R.id.show_all_location_filter);
            linedrawmap = itemView.findViewById(R.id.linedrawmap);

        }
    }
}
