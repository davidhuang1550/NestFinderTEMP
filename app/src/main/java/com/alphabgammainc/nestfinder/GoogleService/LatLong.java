package com.alphabgammainc.nestfinder.GoogleService;

import android.os.AsyncTask;
import android.util.Log;

import com.alphabgammainc.nestfinder.Classes.Address;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import static com.alphabgammainc.nestfinder.R.string.google_maps_key;

/**
 * Created by soutrikbarua on 2017-05-01.
 */

public class LatLong {
    private final static String geocode ="https://maps.googleapis.com/maps/api/geocode/json?" +
            "address=";

    private String address;
    private Address myAddress;
    private Double lat;
    private Double lng;
    public LatLong(){

    }
    public LatLong(Address myAddress){
        this.myAddress=myAddress;
    }
    public String createAddress(){

        address =myAddress.getStreetNumber() + "+" +myAddress.getStreetName() + ",+"
                + myAddress.getCity()+ ",+"+ myAddress.getProvince();

        return address;
    }

    public String createURL() throws UnsupportedEncodingException{
        String urladdress = URLEncoder.encode(address, "utf-8");
        return geocode + urladdress + "&key"+google_maps_key;
    }

    public void execute() throws UnsupportedEncodingException {
        new requestAddressToGoogle().execute(createURL());

    }


    /**
     * @DownloadRawData : this class is used to send the HTTPS URL request to google
     * to fetch the JSON file for the specific ad posting address the Landlord inputs
     */

    private class requestAddressToGoogle extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String link = params[0];
            try {
                URL url = new URL(link);
                InputStream inputStream =url.openConnection().getInputStream();
                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line = reader.readLine()) != null)
                {
                    buffer.append(line+"\n");
                }
                //Log.e(TAG, "Response from url: " + buffer.toString());
                JSONParser(buffer.toString());


                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }
    private void JSONParser (String s) throws JSONException {

        JSONObject myJSONObject = new JSONObject(s);
        JSONObject mylocation = myJSONObject.getJSONArray("results").getJSONObject(0).
                getJSONObject("geometry").getJSONObject("location");
        lat = mylocation.getDouble("lat");
        lng =mylocation.getDouble("lng");
    }

    public Double latitude(){
        return lat;
    }
    public Double longitude(){
        return lng;
    }


}




