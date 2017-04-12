package com.vovatkach2427gmail.ukrainetravel.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vovatkach2427gmail.ukrainetravel.Model.PlaceMain;
import com.vovatkach2427gmail.ukrainetravel.R;

import java.util.List;

/**
 * Created by vovat on 11.04.2017.
 */

public class RVAdapterMainPlace extends RecyclerView.Adapter<RVAdapterMainPlace.PlaceViewHolder>  {
    private List<PlaceMain> places;
    public static class PlaceViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        TextView tvNamePlace;
        ImageView ivImgPlace;
        ImageView ivIsTop;
        public PlaceViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.cvPlace);
            tvNamePlace=(TextView)itemView.findViewById(R.id.tvPlaceNameCardPlace);
            ivImgPlace=(ImageView)itemView.findViewById(R.id.ivImgCardPlace);
            ivIsTop=(ImageView)itemView.findViewById(R.id.ivPlaceIsTop);
        }
    }
    public RVAdapterMainPlace(List<PlaceMain> places)
    {
      this.places=places;
    }
    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        PlaceViewHolder pvh = new PlaceViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        holder.tvNamePlace.setText(places.get(position).getName());
        holder.ivImgPlace.setImageResource(places.get(position).getImgs()[0]);
        if(places.get(position).getTop()==1) holder.ivIsTop.setVisibility(View.VISIBLE);else holder.ivIsTop.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
