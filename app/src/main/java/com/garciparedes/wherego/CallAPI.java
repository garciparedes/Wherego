package com.garciparedes.wherego;


import com.google.android.gms.maps.model.LatLng;

import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Created by garciparedes on 28/08/14.
 */
public class CallAPI {

    private static long time = new Date().getTime();

    private static Event[] lista =
            new Event[]{
                    new Event("Fiestas Becerril", "Bobada", "Fiesta", new Date(time), new LatLng( 42.0088161, -4.5269538)),
                    new Event("Fiestas Amusco", "Bobada", "Fiesta", new Date(time), new LatLng( 42.0088161, -4.5269538)),
                    new Event("Fiestas Paredes", "Bobada", "Fiesta", new Date(1409650562875L), new LatLng( 42.0088161, -4.5269538)),
                    new Event("Fiestas San cebrian", "Bobada", "Fiesta", new Date(1409650562875L), new LatLng( 42.0088161, -4.5269538)),
                    new Event("Fiestas Villatoquite", "Bobada", "Fiesta", new Date(1409650562875L), new LatLng( 42.0088161, -4.5269538)),
                    new Event("Fiestas Santander", "Bobada", "Fiesta", new Date(1409650562875L), new LatLng( 42.0088161, -4.5269538)),
                    new Event("Fiestas Palencia", "Bobada", "Fiesta", new Date(1409650562875L), new LatLng( 42.0088161, -4.5269538)),
                    new Event("Fiestas vega", "Bobada", "Fiesta", new Date(1409650562875L), new LatLng( 42.0088161, -4.5269538)),
                    new Event("Fiestas herrera", "Bobada", "Fiesta", new Date(1409650562875L), new LatLng( 42.0088161, -4.5269538)),
                    new Event("Fiestas de carejas", "Bobada", "Fiesta", new Date(1409650562875L), new LatLng( 42.0088161, -4.5269538)),

            };
    static ArrayList<Event> eventArray = new ArrayList<Event>(Arrays.asList(lista));

    public static ArrayList<Event> getEventList() {
        return eventArray;
    }


    public static void addEvent(Event event) {
        eventArray.add(event);
    }
}
