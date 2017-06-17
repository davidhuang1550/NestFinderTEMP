package com.alphabgammainc.nestfinder.GoogleService;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.alphabgammainc.nestfinder.DetailsPage.DataParserforPlaces;
import com.alphabgammainc.nestfinder.DetailsPage.DetailsPageInterface;
import com.alphabgammainc.nestfinder.DetailsPage.DownloadUrl;
import com.alphabgammainc.nestfinder.GoogleService.Caching.PlacesCachingManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by soutrikbarua on 2017-05-21.
 */

public class GetNearbyPlacesData extends AsyncTask<Object,String,String> {
    private String googlePlacesData;
    private GoogleMap mMap;
    private String Url;
    private DetailsPageInterface callback;
    private double lat;
    private double lon;
    private String place;
    private Activity mActivity;
    @Override
    protected String doInBackground(Object... params) {


        try {
            mMap =(GoogleMap)params[0];
            Url=(String)params[1];
            callback = (DetailsPageInterface)params[2];
            lat = (double)params[3];
            lon = (double)params[4];
            place = (String)params[5];
            mActivity = (Activity)params[6];

            DownloadUrl downloadUrl = new DownloadUrl();
            googlePlacesData = downloadUrl.readUrl(Url);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s) {
        List<HashMap<String,String>> nearbyPlacesList = null;
        DataParserforPlaces dataParserforPlaces =new DataParserforPlaces();
        nearbyPlacesList = dataParserforPlaces.parse(s);
        callback.setPlaces(nearbyPlacesList);
        //ShowNearbyPlaces(nearbyPlacesList);

       // super.onPostExecute(s);
    }

    /**
     *This method adds the markers of the places in the category the user searched for
     * @param nearbyPlacesList
     */
    private void ShowNearbyPlaces(List<HashMap<String, String>> nearbyPlacesList) {
        /*if(nearbyPlacesList.size() > 0) {
            PlacesCachingManager placesCachingManager = PlacesCachingManager.getmInstance(mActivity);
            double locationId = placesCachingManager.cacheLocation(this.lat, this.lon, this.place);

            for (int i = 0; i < nearbyPlacesList.size(); i++) {
                Log.d("onPostExecute", "Entered into showing locations");
                MarkerOptions markerOptions = new MarkerOptions();
                HashMap<String, String> googlePlace = nearbyPlacesList.get(i);
                double lat = Double.parseDouble(googlePlace.get("lat"));
                double lng = Double.parseDouble(googlePlace.get("lng"));
                String placeName = googlePlace.get("place_name");
                String vicinity = googlePlace.get("vicinity");
                LatLng latLng = new LatLng(lat, lng);
                markerOptions.position(latLng);
                markerOptions.title(placeName + " : " + vicinity);
                mMap.addMarker(markerOptions);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                placesCachingManager.cachePlace(googlePlace, locationId);
                //move map camera
                // mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                // mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
            }
        }*/
    }
}
