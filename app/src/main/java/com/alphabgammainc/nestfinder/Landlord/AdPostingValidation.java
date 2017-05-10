package com.alphabgammainc.nestfinder.Landlord;

/**
 * Created by davidhuang on 2017-05-09.
 */

public class AdPostingValidation {


    public static boolean checkForEmptyString(String value) {
        if( value.trim().equals("")) {
            return true;
        }
        return false;
    }

    public static boolean validatePostalCode(String postalCode){

        return false;
    }


}
