package com.vovatkach2427gmail.ukrainetravel.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vovatkach2427gmail.ukrainetravel.Model.MyWeather;
import com.vovatkach2427gmail.ukrainetravel.R;

import java.util.List;

/**
 * Created by vovat on 27.04.2017.
 */

public class RVAdapterWeather extends RecyclerView.Adapter<RVAdapterWeather.WeatherViewHolder> {
    private List<MyWeather> myWeathers;

    public RVAdapterWeather(List<MyWeather> myWeatherList)
    {
        this.myWeathers=myWeatherList;
    }

    public static class WeatherViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout lnWeather;
        TextView tvWeatherDate;
        ImageView ivWeatherIcon;
        TextView tvWeatherTemp;
        TextView tvWeatherDesc;
        public WeatherViewHolder(View itemView) {
            super(itemView);
            lnWeather=(LinearLayout) itemView.findViewById(R.id.llCardWeather);
            tvWeatherDate=(TextView)itemView.findViewById(R.id.tvWeatherDate);
            ivWeatherIcon=(ImageView)itemView.findViewById(R.id.ivWeatherIcon);
            tvWeatherTemp=(TextView)itemView.findViewById(R.id.tvWeatherTemp);
            tvWeatherDesc=(TextView)itemView.findViewById(R.id.tvWeatherDesc);
        }
    }
//--------------------------------------------------------------------------------
    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather,parent,false);
        WeatherViewHolder viewHolder=new WeatherViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        holder.tvWeatherDate.setText(myWeathers.get(position).getDate());
        holder.tvWeatherTemp.setText(myWeathers.get(position).getTemperature());
        holder.tvWeatherDesc.setText(myWeathers.get(position).getWeather());
        switch (myWeathers.get(position).getWeather())
        {
            case "Хмарно":
                holder.ivWeatherIcon.setImageResource(R.drawable.w_clouds);
                break;
            case "Сонячно":
                holder.ivWeatherIcon.setImageResource(R.drawable.w_sun);
                break;
            case "Дощ":
                holder.ivWeatherIcon.setImageResource(R.drawable.w_rain);
                break;
            case "Сніг":
                holder.ivWeatherIcon.setImageResource(R.drawable.w_snow);
                break;
            default:
                holder.ivWeatherIcon.setImageResource(R.drawable.w_extreme);
                break;
        }
        String hourString=myWeathers.get(position).getDate().split(" ")[1].split(":")[0];
        int hour=Integer.parseInt(hourString);
        Log.d("myLog",Integer.toString(hour));
        if(hour<4)
        {
            holder.lnWeather.setBackgroundResource(R.drawable.w_night_background);
        }else
            {
                if (hour<10)
                {
                    holder.lnWeather.setBackgroundResource(R.drawable.w_morning_background);
                }else
                    {
                        if (hour<16)
                        {
                            holder.lnWeather.setBackgroundResource(R.drawable.w_day_background);
                        }else
                            {
                                if(hour<22)
                                {
                                    holder.lnWeather.setBackgroundResource(R.drawable.w_evening_background);
                                }
                            }
                    }
            }
    }

    @Override
    public int getItemCount() {
        return myWeathers.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
