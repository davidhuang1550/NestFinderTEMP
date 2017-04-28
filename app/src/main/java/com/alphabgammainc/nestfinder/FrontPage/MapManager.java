package com.alphabgammainc.nestfinder.FrontPage;

import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by davidhuang on 2017-04-25.
 */

public class MapManager {

    HashMap<Marker, ListMarker> mapMarkers;
    HashMap<ListMarker, Marker> listMarkers;


    public MapManager(){
        mapMarkers = new HashMap<>();
        listMarkers = new HashMap<>();
    }

    /**
     *  save both markers as reference so we can access them both.
     * @param mapMarker google maps marker
     * @param listMarker listview item
     */
    public synchronized void addMapMarker(Marker mapMarker, ListMarker listMarker){
        mapMarkers.put(mapMarker,listMarker);
        listMarkers.put(listMarker,mapMarker);
    }

    public synchronized ListMarker getListMarker(Marker marker){
        return mapMarkers.get(marker);
    }

    public Marker getMapmarker(ListMarker listMarker){
        return listMarkers.get(listMarker);
    }


}
