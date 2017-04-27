package com.vovatkach2427gmail.ukrainetravel.Fragment;


import android.app.ProgressDialog;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vovatkach2427gmail.ukrainetravel.Act.PlaceAct;
import com.vovatkach2427gmail.ukrainetravel.Adapter.RVAdapterNearPlaceList;
import com.vovatkach2427gmail.ukrainetravel.DB.DataBaseWorker;
import com.vovatkach2427gmail.ukrainetravel.Model.City;
import com.vovatkach2427gmail.ukrainetravel.Model.PlaceMain;
import com.vovatkach2427gmail.ukrainetravel.MyLocationListener;
import com.vovatkach2427gmail.ukrainetravel.R;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListNearPlace extends FragmentNearPlace {
    public static final String ARG_ID_CITY = "ARG_ID_CITY";
    public static final String ARG_RADIUS = "ARG_RADIUS";
    //-------------
    private int cityId;
    private int radius;
    private City currectCity;
    private List<PlaceMain> allPlaces;
    private List<PlaceMain> nearPlaces;
    //-------------
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    RVAdapterNearPlaceList adapter;
    TextView tvNotNearPlaces;
    Button btnError;
    //------------
    DilatingDotsProgressBar progressBar;

    //-----конструктор
    public static FragmentListNearPlace newInstance(int idCity, int radius) {
        Bundle args = new Bundle();
        args.putInt(ARG_RADIUS, radius);
        args.putInt(ARG_ID_CITY,idCity);
        FragmentListNearPlace fragment = new FragmentListNearPlace();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cityId =getArguments().getInt(ARG_ID_CITY,1);
        radius =getArguments().getInt(ARG_RADIUS,500);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_list_near_place, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.rvNearPlaceList);
        tvNotNearPlaces=(TextView)view.findViewById(R.id.tv_is_not_places);
        btnError=(Button)view.findViewById(R.id.btnListNearPlaceError);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        progressBar=(DilatingDotsProgressBar)view.findViewById(R.id.progressNearPlaceList);
       // showData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
        btnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });
    }

    public void setRadiusAndUpdate(int radius)
    {
        this.radius=radius;
        showData();
    }
    public void showData()
    {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvNotNearPlaces.setVisibility(View.INVISIBLE);
                        btnError.setVisibility(View.INVISIBLE);
                        progressBar.showNow();
                    }
                });
                //----
                if(allPlaces==null)
                {
                    allPlaces =new ArrayList<>();
               //     Log.d("myLog", "Викликав створення алл");
                }
                if(allPlaces.isEmpty()) {
                    DataBaseWorker dataBaseWorker = new DataBaseWorker(getContext());
                    allPlaces = dataBaseWorker.loadPlaces(cityId);
                    dataBaseWorker.close();
             //       Log.d("myLog", "Читаю дані");
                }
                if(nearPlaces==null) {
                    nearPlaces = new ArrayList<>();
              //      Log.d("myLog", "створюю близькі");
                }
                if (currectCity==null)
                {
                    DataBaseWorker dataBaseWorker=new DataBaseWorker(getContext());
                    currectCity=dataBaseWorker.loadCity(cityId);
                    dataBaseWorker.close();
                }
                //-----
            //    Log.d("myLog", "шукаю");
                selectNearPlaces();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.hideNow();
                        if(nearPlaces.isEmpty())
                        {
                            tvNotNearPlaces.setVisibility(View.VISIBLE);
                            btnError.setVisibility(View.VISIBLE);
                        }else
                        {
                            tvNotNearPlaces.setVisibility(View.INVISIBLE);
                        }
                        adapter=null;
                        adapter=new RVAdapterNearPlaceList(getActivity(),nearPlaces);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        });
        thread.start();
    }
    //----вибірка місць які входять в радіус
    private void selectNearPlaces()
    {
        if(!nearPlaces.isEmpty())
        {
            nearPlaces.clear();
        }
        Location userLoaction=MyLocationListener.getUserLocation();
        if(userLoaction!=null) {
            for (int i = 0; i < allPlaces.size(); i++) {
                allPlaces.get(i).setDistanceToUser(userLoaction, getContext(),currectCity.getName());
                if (allPlaces.get(i).getDistanceToUser() != null) {
                    if (allPlaces.get(i).getDistanceToUser() < radius) {
                        nearPlaces.add(allPlaces.get(i));
                    }
                }
            }
            if(!nearPlaces.isEmpty())
            {
                Collections.sort(nearPlaces,PlaceMain.COMPARATOR_BY_DISTANCE_TO_USER);
            }
        }else
            {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast toastError=Toast.makeText(getActivity(),"Не вдалося знайти ваше місцезнаходження. Включіть GPS",Toast.LENGTH_LONG);
                        toastError.show();
                    }
                });
            }
    }
}
