package com.example.kumarGroup.EmployeeTracker.ak;

import static android.content.Context.MODE_PRIVATE;

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

import com.example.kumarGroup.Datastartloc;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.line_start_to_end_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterMapDetails extends RecyclerView.Adapter<AdapterMapDetails.viewHolder> {

    Context context;
    List<Datastartloc> mdetailslist;
    SharedPreferences sp;
    public AdapterMapDetails(Context context, List<Datastartloc> mdetailslist) {
        this.context = context;
        this.mdetailslist = mdetailslist;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mapdetails_row_file, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {

        viewHolder.start_location.setText(mdetailslist.get(0).getLocation());
        viewHolder.end_location.setText(mdetailslist.get(1).getLocation());
        viewHolder.start_time.setText(mdetailslist.get(0).getTime());
        viewHolder.endtime.setText(mdetailslist.get(1).getTime());
        viewHolder.datetracking.setText(mdetailslist.get(0).getDate());


        viewHolder.linedrawmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = context.getSharedPreferences("emp_location_id",MODE_PRIVATE);
                String emp_id = sharedPreferences.getString("id","");

           //     Toast.makeText(context, "id "+emp_id, Toast.LENGTH_SHORT).show();

                WebService.getClient().linedraw_start_to_end(emp_id).enqueue(new Callback<line_start_to_end_model>() {
                    @Override
                    public void onResponse(Call<line_start_to_end_model> call, Response<line_start_to_end_model> response) {

                        /*Log.d("TAG", "adpterinlog: "
                                +" lat0 "+response.body().getData().get(0).getLatitude()
                                +" long0 "+response.body().getData().get(0).getLongitude()
                                +" lat1 "+response.body().getData().get(1).getLatitude()
                                +" long1 "+response.body().getData().get(1).getLongitude()
                        );*/

                        context.startActivity(new Intent(context, LineDrawMapActivity.class)
                                .putExtra("lat0", response.body().getData().get(0).getLatitude())
                                .putExtra("long0", response.body().getData().get(0).getLongitude())

                                .putExtra("lat1", response.body().getData().get(1).getLatitude())
                                .putExtra("long1", response.body().getData().get(1).getLongitude())


                        );
                    }

                    @Override
                    public void onFailure(Call<line_start_to_end_model> call, Throwable t) {
                        Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        viewHolder.show_all_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, TotalLocationActivity.class));
            }
        });

        viewHolder.view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, FilterDateLocation.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView linedrawmap, show_all_location;
        TextView start_location, end_location, start_time, datetracking, endtime, view_more;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            linedrawmap = itemView.findViewById(R.id.linedrawmap);
            start_location = itemView.findViewById(R.id.start_location);
            end_location = itemView.findViewById(R.id.end_location);
            start_time = itemView.findViewById(R.id.start_time);
            datetracking = itemView.findViewById(R.id.datetracking);
            endtime = itemView.findViewById(R.id.endtime);
            show_all_location = itemView.findViewById(R.id.show_all_location);
            view_more = itemView.findViewById(R.id.view_more);
        }
    }
}
