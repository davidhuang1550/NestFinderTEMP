package com.alphabgammainc.nestfinder.FrontPage;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphabgammainc.nestfinder.MapsActivity;
import com.alphabgammainc.nestfinder.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by davidhuang on 2017-04-25.
 */

public class FrontPage extends Fragment implements OnMapReadyCallback {
    private View mView;
    private Activity mActivity;


    private GoogleMap mMap;
    // Temporary until we actually start pulling data
    private AppBarLayout mAppBarLayout;
    private String[] desc = {"FUCKING SHIT BAG", " KILL YOUR SELF", "TOXIC MOTHER FUCKER", "DICK SUCKING FUCKER",
            "GOD KNOWS WHAT THE FUCK", "FUCKING SHIT", "SHIT FUCK AND THE OTHERS IS A CUNT FKING SHIT",
            "MOTHER CUNT FUCKING DICK SUCKING BITCH TITS", "SUGGAR BABY", "MOTHER CHODE", "HELLO WORLD"};


    /**
     * Get the instance of activity here if we do it somewhere else then we might run into issues
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
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


        RecyclerView recyclerView = (RecyclerView)mView.findViewById(R.id.rview);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setAdapter(new FrontPageAdapter(mActivity,desc));



        return mView;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
