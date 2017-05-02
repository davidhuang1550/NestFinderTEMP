package com.alphabgammainc.nestfinder.Landlord;

import android.app.Activity;
import android.app.Fragment;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.alphabgammainc.nestfinder.Classes.Address;
import com.alphabgammainc.nestfinder.FirebaseConnection.DataBaseConnectionPresenter;
import com.alphabgammainc.nestfinder.R;

import java.sql.Timestamp;

/**
 * Created by soutrikbarua on 2017-04-26.
 */

public class AdPostingPage extends Fragment{


    /**
     * All the form variable exists here
     */
    private EditText adTitle;
    private EditText streetNumber;
    private EditText streetName;
    private EditText postalCode;
    private EditText city;
    private EditText country;
    private EditText province;
    private EditText bedRooms;
    private EditText bathRooms;
    private CheckBox isFurnished;
    private CheckBox pets;
    private Button postAd;


    /**
     * placeholders for  the  above variables converted to their respected data types
     */
    private String madTitle;
    private String mstreetNumber;
    private String mstreetName;
    private String mCity;
    private String mCountry;
    private String mProvince;
    private String mpostalCode;
    private int mbedRooms;
    private int mbathRooms;
    private boolean misFurnished;
    private boolean mPets;
    /**
     * All other variable for this fragment exists here
     */
    private View mView;
    private Activity mActivity;
    private Location adLocation;
    private Address mAddress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity =getActivity();
        /**
         * Get the respective views
         */
        adTitle =(EditText)getView().findViewById(R.id.title);
        streetNumber=(EditText)getView().findViewById(R.id.streetNumber);
        streetName=(EditText)getView().findViewById(R.id.streetName);
        postalCode=(EditText)getView().findViewById(R.id.postalCode);
        city=(EditText)getView().findViewById(R.id.city);
        country=(EditText)getView().findViewById(R.id.country);
        province =(EditText)getView().findViewById(R.id.province);
        bedRooms=(EditText)getView().findViewById(R.id.bedrooms);
        bathRooms=(EditText)getView().findViewById(R.id.bathrooms);
        isFurnished=(CheckBox) getView().findViewById(R.id.furnished);
        pets=(CheckBox)getView().findViewById(R.id.pets);

        /**
         * Convert the fetched data to be passed in the Locations object
         */
        madTitle= adTitle.toString();
        mstreetName=streetName.toString();
        mstreetNumber=streetNumber.toString();
        mpostalCode = postalCode.toString();
        mCity =city.toString();
        mCountry=country.toString();
        mProvince=province.toString();

        /**
         * Additional information about the apartment
         */

        mbedRooms =Integer.parseInt(bedRooms.toString());
        mbathRooms = Integer.parseInt(bathRooms.toString());
        misFurnished=Boolean.valueOf(isFurnished.toString());
        mPets =Boolean.valueOf(pets.toString());

        /**
         * send the data into an address object
         */

        mAddress =new Address(mstreetNumber,mstreetName,mCity,mProvince,mpostalCode,mCountry);

       // adLocation = new Location() instantiate the new location here

        /**
         * Submit button;
         */
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
// Add check to use