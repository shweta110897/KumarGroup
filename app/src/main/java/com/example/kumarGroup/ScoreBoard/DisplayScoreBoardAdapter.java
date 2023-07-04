package com.example.kumarGroup.ScoreBoard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatScoreBoard;
import com.example.kumarGroup.R;

import java.util.List;

public class DisplayScoreBoardAdapter extends RecyclerView.Adapter<DisplayScoreBoardAdapter.ViewHolder>
{
    Context context;
    List<CatScoreBoard> catScoreBoards;

    public DisplayScoreBoardAdapter(Context context, List<CatScoreBoard> catScoreBoards) {
        this.context = context;
        this.catScoreBoards = catScoreBoards;
    }

    @NonNull
    @Override
    public DisplayScoreBoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.display_scoreboard_raw, parent, false);
        DisplayScoreBoardAdapter.ViewHolder viewHolder = new DisplayScoreBoardAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayScoreBoardAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.DataBank.setText(catScoreBoards.get(position).getDatabank());
        holder.DataBankCount.setText(catScoreBoards.get(position).getDatabankcount());
        holder.textView_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,ScoreBoardViewViewActivity.class);
                intent.putExtra("sbname",catScoreBoards.get(position).getDatabank());
                context.startActivity(intent);

            }
        });


       /* double i2=catScoreBoards.get(position).getDatabankpoint()/60000;
        holder.DataBankPoint.setText(new DecimalFormat("##.##").format(i2));*/


        holder.DataBankPoint.setText(catScoreBoards.get(position).getDatabankpoint()+"");
        
        /*if(catScoreBoards.get(position).getDatabank()==null ||
                catScoreBoards.get(position).getDatabankcount()==null ){
            holder.linMain.setVisibility(View.GONE);
        }*/

    }

    @Override
    public int getItemCount() {
        return catScoreBoards.size()-1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView DataBank,DataBankCount,DataBankPoint,textView_View;
        LinearLayout linMain;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.DataBank= itemView.findViewById(R.id.DataBank);
            this.DataBankCount= itemView.findViewById(R.id.DataBankCount);
            this.DataBankPoint= itemView.findViewById(R.id.DataBankPoint);
            this.textView_View= itemView.findViewById(R.id.textView_View);
            this.linMain= itemView.findViewById(R.id.linMain);
        }
    }
}
