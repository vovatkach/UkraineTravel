package com.vovatkach2427gmail.ukrainetravel.Fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.*;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vovatkach2427gmail.ukrainetravel.Act.WelcomeAct;
import com.vovatkach2427gmail.ukrainetravel.Adapter.RVAdapterNearPlaceList;
import com.vovatkach2427gmail.ukrainetravel.DB.DataBaseWorker;
import com.vovatkach2427gmail.ukrainetravel.Model.City;
import com.vovatkach2427gmail.ukrainetravel.Model.PlaceMain;
import com.vovatkach2427gmail.ukrainetravel.MyLocationListener;
import com.vovatkach2427gmail.ukrainetravel.R;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMapNearPlace extends FragmentNearPlace {
    public static final String ARG_ID_CITY = "ARG_ID_CITY";
    public static final String ARG_RADIUS = "ARG_RADIUS";
    //-------------
    private int cityId;
    private int radius;
    private City currectCity;
    private List<PlaceMain> allPlaces;
    private List<PlaceMain> nearPlaces;
    TextView tvNotNearPlaces;
    Button btnError;
    //-------------
    MapView mapView;
    private GoogleMap mMap;
    DilatingDotsProgressBar progressBar;
    Location userLocation;

    //-----конструктор
    public static FragmentMapNearPlace newInstance(int idCity, int radius) {
        Bundle args = new Bundle();
        args.putInt(ARG_RADIUS, radius);
        args.putInt(ARG_ID_CITY, idCity);
        FragmentMapNearPlace fragment = new FragmentMapNearPlace();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cityId = getArguments().getInt(ARG_ID_CITY, 1);
        radius = getArguments().getInt(ARG_RADIUS, 500);
        userLocation = MyLocationListener.getUserLocation();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_map_near_place, container, false);
        tvNotNearPlaces = (TextView) view.findViewById(R.id.tvMapNearPlaceError);
        btnError = (Button) view.findViewById(R.id.btnMapNearPlaceError);
        progressBar = (DilatingDotsProgressBar) view.findViewById(R.id.progressNearPlaceMap);
        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
            mapView.getMapAsync(onMapReadyCallback);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putPlacesOnMap();
            }
        });
    }

    public void setRadiusAndUpdate(int radius) {
        this.radius = radius;
        putPlacesOnMap();
    }

    OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mMap.setTrafficEnabled(true);
            mMap.getUiSettings().setAllGesturesEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            // For showing a move to my location button
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            }
            mMap.setMyLocationEnabled(true);
            putPlacesOnMap();

        }
    };

    //-------------------------------
    private void putPlacesOnMap() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMap.clear();
                        progressBar.showNow();
                        tvNotNearPlaces.setVisibility(View.INVISIBLE);
                        btnError.setVisibility(View.INVISIBLE);
                    }
                });
                //-------
                //----
                if (allPlaces == null) {
                    allPlaces = new ArrayList<>();
                    //     Log.d("myLog", "Викликав створення алл");
                }
                if (allPlaces.isEmpty()) {
                    DataBaseWorker dataBaseWorker = new DataBaseWorker(getContext());
                    allPlaces = dataBaseWorker.loadPlaces(cityId);
                    dataBaseWorker.close();
                    //       Log.d("myLog", "Читаю дані");
                }
                if (nearPlaces == null) {
                    nearPlaces = new ArrayList<>();
                    //      Log.d("myLog", "створюю близькі");
                }
                if (currectCity == null) {
                    DataBaseWorker dataBaseWorker = new DataBaseWorker(getContext());
                    currectCity = dataBaseWorker.loadCity(cityId);
                    dataBaseWorker.close();
                }
                //-----
                //    Log.d("myLog", "шукаю");
                //------
                selectNearPlaces();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.hideNow();
                        if (userLocation != null && (!nearPlaces.isEmpty())) {
                            int zoom = 14;
                            if (radius > 2000) zoom = 12;
                            LatLng userCoordinates = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(userCoordinates).title("Ви").snippet("Ваше місцезнаходження").icon(BitmapDescriptorFactory.fromResource(R.drawable.z_map_user)));
                            CameraPosition cameraPosition = new CameraPosition.Builder().target(userCoordinates).zoom(zoom).build();
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            for (int i = 0; i < nearPlaces.size(); i++) {
                                LatLng coordinates = new LatLng(nearPlaces.get(i).getCoordinates().latitude, nearPlaces.get(i).getCoordinates().longitude);
                                mMap.addMarker(new MarkerOptions().position(coordinates).title(nearPlaces.get(i).getName()).snippet(nearPlaces.get(i).getAddress()).icon(BitmapDescriptorFactory.fromResource(R.drawable.z_map_place)));
                            }
                        } else {
                            tvNotNearPlaces.setVisibility(View.VISIBLE);
                            btnError.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
        thread.start();
    }

    //----вибірка місць які входять в радіус
    private void selectNearPlaces() {
        if (!nearPlaces.isEmpty()) {
            nearPlaces.clear();
        }
        if (userLocation != null) {
            for (int i = 0; i < allPlaces.size(); i++) {
                allPlaces.get(i).setDistanceToUser(userLocation, getContext(), currectCity.getName());
                if (allPlaces.get(i).getDistanceToUser() != null) {
                    if (allPlaces.get(i).getDistanceToUser() < radius) {
                        nearPlaces.add(allPlaces.get(i));
                    }
                }
            }
        }
    }
//-------------------------------------------------------------------------
}
