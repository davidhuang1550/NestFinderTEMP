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
    private String adTitle;
    private int bedRooms;
    private int bathRooms;
    private boolean isFurnished;
    private boolean pets;
    private String rentImage;
    private double price;
    private String address;
    private Address mAddress;

    public Locations() {

    }

    public Locations(double lon, double lat,String adTitle,Address mAddress,
                     int bedRooms,int bathRooms, boolean isFurnished,boolean pets,int price){
        this.lon = lon;
        this.lat = lat;
        this.adTitle = adTitle;
        this.mAddress=mAddress;
        this.bathRooms =bathRooms;
        this.isFurnished =isFurnished;
        this.pets =pets;
        this.price = price;


        this.address =address;  //for the time being

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

    public String getRentImage() {
        return rentImage;
    }

    public void setRentImage(String rentImage) {
        this.rentImage = rentImage;
    }

    public Address getmAddress() {
        return mAddress;
    }

    public void setmAddress(Address mAddress) {
        this.mAddress = mAddress;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAdTitle() { return adTitle; }

    public void setAdTitle(String adTitle) { this.adTitle = adTitle; }

    public int getBedRooms() {
        return bedRooms;
    }

    public void setBedRooms(int bedRooms) {
        this.bedRooms = bedRooms;
    }

    public int getBathRooms() {
        return bathRooms;
    }

    public void setBathRooms(int bathRooms) {
        this.bathRooms = bathRooms;
    }

    public boolean isFurnished() {
        return isFurnished;
    }

    public void setFurnished(boolean furnished) {
        isFurnished = furnished;
    }

    public boolean isPets() {
        return pets;
    }

    public void setPets(boolean pets) {
        this.pets = pets;
    }

    // the locations

    // for the time being
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
