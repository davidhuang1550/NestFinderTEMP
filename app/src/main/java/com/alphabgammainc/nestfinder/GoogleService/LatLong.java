package com.alphabgammainc.nestfinder.GoogleService;

import com.alphabgammainc.nestfinder.Classes.Address;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.alphabgammainc.nestfinder.R.string.google_maps_key;

/**
 * Created by soutrikbarua on 2017-05-01.
 */

public class LatLong {
    private final static String geocode ="https://maps.googleapis.com/maps/api/geocode/json?" +
            "address=";

    private String address;
    private Address myAddress;

    private String createAddress(){

        address =myAddress.getStreetNumber() + "+" +myAddress.getStreetName() + ",+"
                + myAddress.getCity()+ ",+"+ myAddress.getProvince();

        return address;
    }

    private String createURL() throws UnsupportedEncodingException{
        String urladdress = URLEncoder.encode(address, "utf-8");
        return geocode + urladdress + "&key"+google_maps_key;
    }




}
