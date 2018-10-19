package com.example.funflavour;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.*;

import static com.example.funflavour.R.color.cardview_dark_background;

public class RecordListAdapter extends RecyclerView.Adapter<RecordListAdapter.ViewHolder> {
    public List<Record> mList;

    Resources resources;
    public RecordListAdapter(List<Record> mList, Context context) {
        resources=context.getResources();
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        int amtValue=(int)mList.get(position).getTotal_amount();
        int color;
        if (amtValue<1000){
                color=resources.getColor(R.color.turqoise);
        }else if (amtValue<1500){
            color=resources.getColor(R.color.colorAccent);
        } else if (amtValue<2000){
            color=resources.getColor(R.color.lightGreen);

        } else{
            color=resources.getColor(R.color.gold);

        }
        holder.linearLayout.setBackgroundColor(color);
        String amt=String.valueOf(amtValue);
        String lowVol=String.valueOf(mList.get(position).getLow_vol());
        String highVol=String.valueOf(mList.get(position).getHigh_vol());
        String mango=String.valueOf(mList.get(position).getMango());
        holder.lowVolText.setText(lowVol);
        holder.highVolText.setText(highVol);
        holder.mangoText.setText(mango);
        holder.amtText.setText(" Total Amount : "+amt);
        holder.dateText.setText("Date : "+trim(mList.get(position).getTime()));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    private String trim(Date s){
      String date=s.toString();

        return date.substring(0,20);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView dateText;
        TextView amtText;
        TextView lowVolText;
        TextView highVolText;
        TextView mangoText;
        private LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            dateText=mView.findViewById(R.id.r_date);
            amtText=mView.findViewById(R.id.r_amt);
            lowVolText=mView.findViewById(R.id.r_low_vol);
            highVolText=mView.findViewById(R.id.r_high_vol);
            mangoText=mView.findViewById(R.id.r_mango);
            linearLayout = mView.findViewById(R.id.list_item_layout);
        }
    }
}
