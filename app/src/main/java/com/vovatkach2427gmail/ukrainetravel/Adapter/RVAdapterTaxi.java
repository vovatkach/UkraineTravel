package com.vovatkach2427gmail.ukrainetravel.Adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vovatkach2427gmail.ukrainetravel.Call;
import com.vovatkach2427gmail.ukrainetravel.Model.Taxi;
import com.vovatkach2427gmail.ukrainetravel.R;

import java.util.List;

/**
 * Created by vovat on 06.04.2017.
 */

public class RVAdapterTaxi extends RecyclerView.Adapter<RVAdapterTaxi.TaxiViewHolder> {
    public static class TaxiViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        TextView tvName;
        LinearLayout llNumber1;
        LinearLayout llNumber2;
        LinearLayout llNumber3;
        LinearLayout llNumber4;
        TextView tvNumber1;
        TextView tvNumber2;
        TextView tvNumber3;
        TextView tvNumber4;
        public TaxiViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.cv_taxi_card);
            tvName=(TextView)itemView.findViewById(R.id.cv_taxi_name);
            tvNumber1=(TextView)itemView.findViewById(R.id.cv_taxi_number1);
            tvNumber2=(TextView)itemView.findViewById(R.id.cv_taxi_number2);
            tvNumber3=(TextView)itemView.findViewById(R.id.cv_taxi_number3);
            tvNumber4=(TextView)itemView.findViewById(R.id.cv_taxi_number4);
            llNumber1=(LinearLayout)itemView.findViewById(R.id.cv_taxi_layout_number1);
            llNumber2=(LinearLayout)itemView.findViewById(R.id.cv_taxi_layout_number2);
            llNumber3=(LinearLayout)itemView.findViewById(R.id.cv_taxi_layout_number3);
            llNumber4=(LinearLayout)itemView.findViewById(R.id.cv_taxi_layout_number4);
        }
    }
    private List<Taxi> taxis;
    private Context context;
    public RVAdapterTaxi(List<Taxi> taxis, Context context)
    {
       this.taxis=taxis;
        this.context=context;
    }
    @Override
    public TaxiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_taxi, parent, false);
        TaxiViewHolder taxiViewHolder=new TaxiViewHolder(v);
        return taxiViewHolder;

    }

    @Override
    public void onBindViewHolder(final TaxiViewHolder holder, final int position) {
        holder.tvName.setText(taxis.get(position).getName());
        holder.tvNumber1.setText(taxis.get(position).getNumbers()[0]);
        holder.tvNumber2.setText(taxis.get(position).getNumbers()[1]);
        holder.tvNumber3.setText(taxis.get(position).getNumbers()[2]);
        if(taxis.get(position).getNumbers().length>3) holder.tvNumber4.setText(taxis.get(position).getNumbers()[3]);else holder.llNumber4.setVisibility(View.INVISIBLE);
        holder.llNumber1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call.call(context,holder.llNumber1,taxis.get(position).getNumbers()[0]);
            }
        });
        holder.llNumber2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call.call(context,holder.llNumber2,taxis.get(position).getNumbers()[1]);
            }
        });
        holder.llNumber3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call.call(context,holder.llNumber3,taxis.get(position).getNumbers()[2]);
            }
        });
        holder.llNumber4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call.call(context,holder.llNumber4,taxis.get(position).getNumbers()[3]);
            }
        });

    }

    @Override
    public int getItemCount() {
        return taxis.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
