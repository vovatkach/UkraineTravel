package com.vovatkach2427gmail.ukrainetravel.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vovatkach2427gmail.ukrainetravel.Act.PlaceAct;
import com.vovatkach2427gmail.ukrainetravel.Model.PlaceMain;
import com.vovatkach2427gmail.ukrainetravel.R;

import java.util.List;

/**
 * Created by vovat on 20.04.2017.
 */

public class RVAdapterNearPlaceList extends RecyclerView.Adapter<RVAdapterNearPlaceList.NearPlaceListViewHolder> {
    private List<PlaceMain> places;
    private Activity activity;
    public RVAdapterNearPlaceList(Activity activity ,List<PlaceMain> places)
    {
        this.places=places;
        this.activity=activity;
    }
    public static class NearPlaceListViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        ImageView imageView;
        TextView tvName;
        TextView tvDistance;
        TextView tvType;
        ImageView ivIsTop;
        public NearPlaceListViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.iv_img_card_place_near_act);
            tvName=(TextView)itemView.findViewById(R.id.tv_place_name_card_place_near_act);
            tvDistance=(TextView)itemView.findViewById(R.id.tv_place_distance_card_place_near_act);
            tvType=(TextView)itemView.findViewById(R.id.tv_place_type_card_place_near_act);
            ivIsTop=(ImageView)itemView.findViewById(R.id.iv_place_is_top_near_act);
            cardView=(CardView)itemView.findViewById(R.id.cv_place_near_act);
        }
    }
    @Override
    public NearPlaceListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_near_place, parent, false);
        NearPlaceListViewHolder pvh = new NearPlaceListViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(NearPlaceListViewHolder holder, final int position) {
        holder.imageView.setImageResource(places.get(position).getImgs()[0]);
        holder.tvName.setText(places.get(position).getName());
        holder.tvType.setText(places.get(position).getType());
        if(places.get(position).getTop()==1) holder.ivIsTop.setVisibility(View.VISIBLE);else holder.ivIsTop.setVisibility(View.INVISIBLE);
        holder.tvDistance.setText("Відстань "+Integer.toString(places.get(position).getDistanceToUser())+"м");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences=activity.getSharedPreferences("work", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putInt("place_id",places.get(position).getId());
                editor.commit();
                Intent intent=new Intent(activity, PlaceAct.class);
                intent.putExtra("father","NearPlaceActivity");
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.in_left,R.anim.out_right);

            }
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public void upDatePlaces(List<PlaceMain> places)
    {
        this.places.clear();
        this.places=places;
    }
}
