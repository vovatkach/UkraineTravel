package com.vovatkach2427gmail.ukrainetravel.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vovatkach2427gmail.ukrainetravel.Fragment.FragmentRecyclerViewPlaces;

/**
 * Created by vovat on 10.04.2017.
 */

public class ViewPagerMainAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 9;
    private String tabTitles[] = new String[] {"ТОП","Готелі","Пам'ятки культури","Торгові центри", "Ресторани","Театри","Кафе","Хостели","Розваги"};
    private Context context;
    private int IdCity;
    public ViewPagerMainAdapter(FragmentManager fm, Context context, int idCity)
    {
        super(fm);
        this.context=context;
        this.IdCity=idCity;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentRecyclerViewPlaces.newInstance(tabTitles[position],IdCity);
    }

    @Override
    public int getCount() {
       return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
