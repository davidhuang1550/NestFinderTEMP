package com.alphabgammainc.nestfinder.FrontPage;

import com.alphabgammainc.nestfinder.Classes.Locations;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by davidhuang on 2017-04-25.
 */

public class MapManager {

    HashMap<Marker, Locations> mapMarkers;
    HashMap<Locations, Marker> listMarkers;


    public MapManager(){
        mapMarkers = new HashMap<>();
        listMarkers = new HashMap<>();
    }

    /**
     *  save both markers as reference so we can access them both.
     * @param mapMarker google maps marker
     * @param listMarker listview item
     */
    public synchronized void addMapMarker(Marker mapMarker, Locations listMarker){
        mapMarkers.put(mapMarker,listMarker);
        listMarkers.put(listMarker,mapMarker);
    }

    public synchronized Locations getListMarker(Marker marker){
        return mapMarkers.get(marker);
    }

    public synchronized Marker getMapmarker(Locations listMarker){
        return listMarkers.get(listMarker);
    }


}
