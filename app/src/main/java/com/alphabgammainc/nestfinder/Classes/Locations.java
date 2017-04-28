package com.alphabgammainc.nestfinder.Classes;

import android.app.Activity;
import android.location.Location;
import android.widget.ImageView;

import com.alphabgammainc.nestfinder.FrontPage.LoadMap;
import com.alphabgammainc.nestfinder.MapsActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by davidhuang on 2017-04-25.
 */

public class Locations {
    private double lon;
    private double lat;
    private String address;
    private String rentImage;
    private double price;

    public Locations() {

    }

    public Locations(double lon, double lat, String address, String rentImage, Double price) {
        this.lon = lon;
        this.lat = lat;
        this.address = address;
        this.rentImage = rentImage;
        this.price = price;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getRentImage() {
        return rentImage;
    }

    public void setRentImage(String rentImage) {
        this.rentImage = rentImage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    // the locations
}
