package com.alphabgammainc.nestfinder.DetailsPage;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphabgammainc.nestfinder.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by soutrikbarua on 2017-04-26.
 */

public class DetailsPage extends Fragment implements OnMapReadyCallback {

    private View mView;
    private Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.details_page, container ,false);

        MapView mMapView = (MapView) mView.findViewById(R.id.map);

        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(mActivity.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);


        return mView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(43.887501 , -79.428406), 15);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            MapStyleOptions style =MapStyleOptions.loadRawResourceStyle(this.getContext(),R.raw.mapstyle);
            googleMap.setMapStyle(style);
        }

        googleMap.animateCamera(cameraUpdate);
    }
}
/**
 *    private View mView;
 private Activity mActivity;
 private GoogleMap mMap;
 private String apartDesc = "This is Faiq's home.He lives alone with his birds.What a sad life!!";
 private MapView mMapView;


 @Override
 public void onCreate(@Nullable Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 mActivity = getActivity();
 }

 @Nullable
 @Override
 public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
 mView = inflater.inflate(R.layout.details_page, container, false);
 mMapView = (MapView) mView.findViewById(R.id.mapsFragment);
 mMapView.onCreate(savedInstanceState);
 mMapView.onResume();

 try {
 MapsInitializer.initialize(getActivity().getApplicationContext());
 } catch (Exception e) {
 e.printStackTrace();
 }

 mMapView.getMapAsync(new OnMapReadyCallback() {
 @Override
 public void onMapReady(GoogleMap googleMap) {
 mMap = googleMap;
 LatLng sydney = new LatLng(-34, 151);
 mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
 mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
 }
 });
 return mView;
 }
 */
