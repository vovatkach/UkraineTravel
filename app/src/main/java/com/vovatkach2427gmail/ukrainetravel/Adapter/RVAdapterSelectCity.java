package com.vovatkach2427gmail.ukrainetravel.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vovatkach2427gmail.ukrainetravel.Act.MainAct;
import com.vovatkach2427gmail.ukrainetravel.Act.SelectCity;
import com.vovatkach2427gmail.ukrainetravel.Model.City;
import com.vovatkach2427gmail.ukrainetravel.R;

import java.util.List;

/**
 * Created by vovat on 03.04.2017.
 */

public class RVAdapterSelectCity extends RecyclerView.Adapter<RVAdapterSelectCity.CityViewHolder>{
    private Context context;
    private List<City> cities;
    public static class CityViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        TextView tvCityName;
        ImageView imgCity;
        public CityViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView) itemView.findViewById(R.id.cvCity);
            tvCityName=(TextView)itemView.findViewById(R.id.tvCityNameCardCity);
            imgCity=(ImageView)itemView.findViewById(R.id.ivImgCardCity);
        }
    }
    public RVAdapterSelectCity(Context context, List<City> cities)
    {
        this.context=context;
        this.cities=cities;
    }
    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        CityViewHolder cityViewHolder = new CityViewHolder(v);
        return cityViewHolder;
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, final int position) {
        //-------------------------------------
        if(position==0) {
        holder.tvCityName.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }else {holder.tvCityName.setTextColor(context.getResources().getColor(R.color.colorWhite));}
        //------------------------------------------
        holder.imgCity.setImageResource(cities.get(position).getImgs()[0]);
        holder.tvCityName.setText(cities.get(position).getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences=context.getSharedPreferences("work",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putInt("city_id",cities.get(position).getId());
                editor.commit();
                Intent intent=new Intent(context, MainAct.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
