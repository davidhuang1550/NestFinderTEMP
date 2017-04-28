package com.alphabgammainc.nestfinder.Landlord;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.alphabgammainc.nestfinder.R;

import java.sql.Timestamp;

/**
 * Created by soutrikbarua on 2017-04-26.
 */

public class AdPostingPage extends Fragment{


    /**
     * All the form varable exists here
     */
    private EditText adTitle;
    private EditText streetNumber;
    private EditText streetName;
    private EditText postalCode;
    private EditText bedRooms;
    private EditText bathRooms;
    private CheckBox isFurnished;
    private CheckBox pets;
    private Button postAd;

    /**
     * All other variable for this fragment exists here
     */
    private View mView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Get the respective views
         */
        adTitle =(EditText)getView().findViewById(R.id.adTitle);
        streetNumber=(EditText)getView().findViewById(R.id.adstreetNumber);
        streetName=(EditText)getView().findViewById(R.id.streetName);
        postalCode=(EditText)getView().findViewById(R.id.postalCode);
        bedRooms=(EditText)getView().findViewById(R.id.bedrooms);
        bathRooms=(EditText)getView().findViewById(R.id.bathrooms);
        isFurnished=(CheckBox) getView().findViewById(R.id.furnished);
        pets=(CheckBox)getView().findViewById(R.id.pets);

        postAd =(Button)getView().findViewById(R.id.postAd);

        postAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post_ad_to_database();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            mView = inflater.inflate(R.layout.ad_posting_page,container,false);
        return mView;
    }

    /**
     * Helper methods
     */
    public void post_ad_to_database()
    {
        // Adds all the information to firebase here
    }
}
