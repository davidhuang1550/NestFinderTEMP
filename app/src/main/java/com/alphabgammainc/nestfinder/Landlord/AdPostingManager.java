package com.alphabgammainc.nestfinder.Landlord;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.alphabgammainc.nestfinder.Classes.Locations;
import com.alphabgammainc.nestfinder.R;

import java.util.ArrayList;

/**
 * Created by davidhuang on 2017-05-09.
 */

public class AdPostingManager extends FragmentActivity {

    private Locations location;
    private int position = 0;

    private AdPostingPageOne adPostingPageOne;
    private AdPostingPageTwo adPostingPageTwo;
    private AdPostingPageThree adPostingPageThree;


    private ArrayList<Pages> pages;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_posting_container);


        // register the pages
        pages.add(adPostingPageOne);
        pages.add(adPostingPageTwo);
        pages.add(adPostingPageThree);

        location = new Locations();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.content_frame, new AdPostingPageOne());
    }

    /**
     * used to modify object in different fragments
     * @return
     */
    public Locations getlocation(){
        return location;
    }

    /**
     * change view
     */
    public void nextView(){

    }

}
