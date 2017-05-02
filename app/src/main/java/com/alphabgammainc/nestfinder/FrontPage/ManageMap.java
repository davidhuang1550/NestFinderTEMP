package com.alphabgammainc.nestfinder.FrontPage;

import android.location.Location;

import com.alphabgammainc.nestfinder.Classes.Locations;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

/**
 * Created by davidhuang on 2017-04-25.
 */

public interface ManageMap {

    void LoadMap(ArrayList<Locations> locations);

    void setMarkerFocusCallback(Locations Locations);

}
