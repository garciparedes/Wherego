package com.garciparedes.wherego;

import android.util.Log;
import android.util.Pair;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by garciparedes on 20/09/14.
 */
public class GooglePlaces {

    private static final String LOG_TAG = "Wherego";

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String TYPE_DETAILS = "/details";
    private static final String OUT_JSON = "/json";

    private static final String API_KEY = "AIzaSyB_QJKVlosldp3IcUeXr8htwu943pVSS5g";

    private static HttpURLConnection conn = null;

    public static ArrayList<Pair> getDesRef(String input) {
        ArrayList<Pair> resultList = null;

        String description;
        String reference;
        Pair<String,String> pair;

        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?sensor=false&key=" + API_KEY);
            //sb.append("&components=country:uk");
            sb.append("&input=" + URLEncoder.encode(input, "utf8"));

            URL url = new URL(sb.toString());

            System.out.println(sb);

            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            resultList = new ArrayList<Pair>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {

                description = predsJsonArray.getJSONObject(i).getString("description");
                reference = predsJsonArray.getJSONObject(i).getString("reference");

                pair = Pair.create(description,reference);

                resultList.add(pair);


            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot process JSON results", e);
        }

        return resultList;


    }

    public static LatLng getLatLngFromReference (String reference) {
        LatLng resultLatLng = null;
        double lat;
        double lng;

        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_DETAILS + OUT_JSON);
            sb.append("?sensor=false&key=" + API_KEY);
            //sb.append("&components=country:uk");
            sb.append("&reference=" + URLEncoder.encode(reference, "utf8"));

            URL url = new URL(sb.toString());

            System.out.println(sb);

            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultLatLng;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultLatLng;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
            /*

            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            //JSONArray predsJsonArray = jsonObj.getJSONArray("results");


            lng = ((JSONArray)jsonObj.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lng");

            lat = ((JSONArray)jsonObj.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lat");


            */

            JSONObject jsonObj = new JSONObject(jsonResults.toString());

            JSONObject result = jsonObj.getJSONObject("result");
            JSONObject geometry = result.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");

            lat = location.getDouble("lat");
            lng = location.getDouble("lng");



            resultLatLng = new LatLng(lat,lng);
            System.out.println(lat);
            System.out.println(lng);


        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot process JSON results", e);
        }



        return resultLatLng;



    }

}
