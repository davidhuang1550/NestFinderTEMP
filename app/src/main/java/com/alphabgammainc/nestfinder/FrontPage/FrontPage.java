package com.alphabgammainc.nestfinder.FrontPage;

import android.app.Activity;
import android.app.Fragment;
import android.location.Location;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphabgammainc.nestfinder.Classes.Locations;
import com.alphabgammainc.nestfinder.MapsActivity;
import com.alphabgammainc.nestfinder.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Observer;

/**
 * Created by davidhuang on 2017-04-25.
 */

public class FrontPage extends Fragment implements OnMapReadyCallback , LoadMap {
    private View mView;
    private Activity mActivity;
    private MapManager mapManager;
    private GoogleMap mMap;
    private RecyclerView listView;
    private ArrayList<Locations> locations;
    private FrontPageAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    // Temporary until we actually start pulling data
    private AppBarLayout mAppBarLayout;

    /**
     * Get the instance of activity here if we do it somewhere else then we might run into issues
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mapManager = new MapManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.front_page, container, false);

        MapView mMapView = (MapView) mView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);

        // inproper way atm because we can make the support bar not collaspe.
        ((MapsActivity)mActivity).getSupportActionBar().hide();

        mAppBarLayout = (AppBarLayout) mView.findViewById(R.id.app_bar);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = new AppBarLayout.Behavior();
        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
            @Override
            public boolean canDrag(AppBarLayout appBarLayout) {
                return false;
            }
        });
        params.setBehavior(behavior);

       // swipeRefreshLayout = (SwipeRefreshLayout)mView.findViewById(R.id.swipe);
        //swipeRefreshLayout.setRefreshing(true);
        //swipeRefreshLayout.setEnabled(false);

        locations = new ArrayList<>();
        adapter = new FrontPageAdapter(mActivity, locations);
        listView = (RecyclerView)mView.findViewById(R.id.rview);

        listView.setLayoutManager(new LinearLayoutManager(mActivity));

        listView.setAdapter(adapter);



        return mView;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MarkerManager markerManager = new MarkerManager(mActivity , this, locations, adapter);

      //  LatLng location = new LatLng(43.887501 , -79.428406);
        markerManager.fetchMarkers(43.887501 , -79.428406);

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(43.887501 , -79.428406), 10);
        mMap.animateCamera(cameraUpdate);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                System.out.println(marker);

                return false;
            }
        });

    }

    /**
     * callback once we fetch all the locations on the given marker
     * @param locations
     */
    @Override
    public void LoadMap(ArrayList<Locations> locations) {
        // Add a marker in Sydney and move the camera
        for(Locations location : locations){
            LatLng position = new LatLng(location.getLat(),location.getLon());
            Marker marker = mMap.addMarker(new MarkerOptions().position(position).title(location.getAddress()));
            ListMarker listMarker = new ListMarker(location.getAddress());
            mapManager.addMapMarker(marker, listMarker);
        }

       /* FrontPageAdapter adapter = new FrontPageAdapter(mActivity, locations);
        listView = (RecyclerView)mView.findViewById(R.id.rview);

        listView.setLayoutManager(new LinearLayoutManager(mActivity));

        listView.setAdapter(adapter);*/
    //    swipeRefreshLayout.setRefreshing(false);
    }
}
