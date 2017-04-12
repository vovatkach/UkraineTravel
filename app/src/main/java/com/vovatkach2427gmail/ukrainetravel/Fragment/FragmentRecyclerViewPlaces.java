package com.vovatkach2427gmail.ukrainetravel.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vovatkach2427gmail.ukrainetravel.Adapter.RVAdapterMainPlace;
import com.vovatkach2427gmail.ukrainetravel.DB.DataBaseWorker;
import com.vovatkach2427gmail.ukrainetravel.Model.PlaceMain;
import com.vovatkach2427gmail.ukrainetravel.R;

import java.util.List;

/**
 * Created by vovat on 10.04.2017.
 */

public class FragmentRecyclerViewPlaces extends Fragment {
    public static final String ARG_TYPE = "ARG_TYPE";
    public static final String ARG_ID_CITY = "ARG_ID_CITY";

    private String placeType;
    private int idCity;
    RecyclerView rvPlace;
    List<PlaceMain> places;

    public static FragmentRecyclerViewPlaces newInstance(String nameType, int idCity) {
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, nameType);
        args.putInt(ARG_ID_CITY,idCity);
        FragmentRecyclerViewPlaces fragment = new FragmentRecyclerViewPlaces();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        placeType = getArguments().getString(ARG_TYPE);
        idCity=getArguments().getInt(ARG_ID_CITY);
        DataBaseWorker db=new DataBaseWorker(getContext());
        places=db.loadPlaces(idCity,placeType);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view_places, container, false);
        rvPlace=(RecyclerView)view.findViewById(R.id.rvMainPlace);
        rvPlace.setHasFixedSize(true);
        LinearLayoutManager llv=new LinearLayoutManager(getActivity());
        rvPlace.setLayoutManager(llv);
        RVAdapterMainPlace adapter=new RVAdapterMainPlace(places);
        rvPlace.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
