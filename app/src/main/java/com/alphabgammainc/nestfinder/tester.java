package com.alphabgammainc.nestfinder;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alphabgammainc.nestfinder.Classes.Locations;
import com.alphabgammainc.nestfinder.FirebaseConnection.DataBaseConnectionPresenter;
import com.alphabgammainc.nestfinder.Ulilities.FragmentCallback;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by davidhuang on 2017-04-25.
 */

public class tester extends Fragment implements FragmentCallback{
    private Activity mActivity;
    private View mView;
    private double lat = 43.887501;
    private double lon = -79.428406;

    private String[] address = {"70 King street", "80 Balkar Street", "90 Hope Street",
                                "213 Santa Street", "23 Maple street", "23 Hasan street",
                                "23 kitchener street", "90 richard Road", "291 Rapport main",
                                "2133 heuron maple drive"};
    private Double[] price = {745.34,234.34,654.54,543.45,647.99,543.54,354.56,2345.65,
                                435.65,768.65};

    private static int position = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.tester,container, false);

        Button addHome = (Button)mView.findViewById(R.id.addhome);

        ((MapsActivity)mActivity).setFragmentCallback(this);
        addHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((MapsActivity)mActivity).checkReadExternalPermission()) {
                    try {
                        Intent galleryIntent = new Intent();
                        galleryIntent.setType("image/*");
                        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                        mActivity.startActivityForResult(galleryIntent, 1);
                    }catch(RuntimeException e){e.printStackTrace();}
                }else{((MapsActivity)mActivity).requestForSpecificPermission();}
            }
        });



        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity =getActivity();
    }

    @Override
    public void callback(String key) {
        DatabaseReference reference = DataBaseConnectionPresenter.getInstance().getDbReference().child("Locations").push();
        reference.setValue(new Locations(lon,lat,address[position],key,price[position]));
        lat -= 0.01;
        lon -= 0.01;
        position++;
    }
}
