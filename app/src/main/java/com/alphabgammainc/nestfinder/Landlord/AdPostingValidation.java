package com.alphabgammainc.nestfinder.Landlord;

/**
 * Created by davidhuang on 2017-05-09.
 */

public class AdPostingValidation {


    public static boolean checkForEmptyString(String value) {
        if(!value.trim().equals("")) {
            return true;
        }
        return false;
    }

    public static boolean validatePostalCode(String postalCode){
        // for now just return true until we figure out a valid way to check postal.
        return true;
    }


}
