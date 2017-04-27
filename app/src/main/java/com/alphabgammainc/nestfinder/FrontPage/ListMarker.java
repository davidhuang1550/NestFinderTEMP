package com.alphabgammainc.nestfinder.FrontPage;

/**
 * Created by davidhuang on 2017-04-25.
 * .1 in both lat and lon is about 10km
 */

public class ListMarker {

    String Address;
    // anything else

    public ListMarker(String Address){
        this.Address = Address;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }


}
