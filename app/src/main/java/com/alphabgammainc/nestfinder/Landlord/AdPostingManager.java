package com.alphabgammainc.nestfinder.Landlord;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.alphabgammainc.nestfinder.Classes.Address;
import com.alphabgammainc.nestfinder.Classes.Locations;
import com.alphabgammainc.nestfinder.R;

import java.util.ArrayList;

/**
 * Created by davidhuang on 2017-05-09.
 */

public class AdPostingManager extends FragmentActivity {

    private Locations location;
    private Address address;

    private AdPostingPageOne adPostingPageOne = new AdPostingPageOne();
    private AdPostingPageTwo adPostingPageTwo = new AdPostingPageTwo();
    private AdPostingPageThree adPostingPageThree = new AdPostingPageThree();
    private AdPostingPageFour adPostingPageFour= new AdPostingPageFour();


    private ArrayList<Pages> pages = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_posting_container);


        // register the pages

        pages.add(adPostingPageOne);
        pages.add(adPostingPageTwo);
        pages.add(adPostingPageThree);
        pages.add(adPostingPageFour);

        location = new Locations();
        address = new Address();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        ft.add(R.id.content_frame, (android.support.v4.app.Fragment) pages.get(3),"fragment").commit();
    }

    /**
     * used to modify object in different fragments
     * @return
     */
    public Locations getlocation(){
        return location;
    }

    public Address getAddress(){return address;}

    public void setLocation(Locations location){
        this.location = location;
    }

    public void setAddress(Address address){
        this.address = address;
    }

    /**
     * change view
     */
    public void nextView(int position){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        ft.replace(R.id.content_frame, (android.support.v4.app.Fragment) pages.get(position),"fragment").commit();
    }

}
