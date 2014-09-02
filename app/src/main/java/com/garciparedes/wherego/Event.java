package com.garciparedes.wherego;


import java.util.Date;

/**
 * Created by garciparedes on 21/07/14.
 */
public class Event {

    private String name;
    private String description;
    private String type;

    private Date date;

    private Coordinate coordinate;


    //private Calendar date;


    public Event(String name, String description, String type, Date date, Coordinate coordinate){
        this.name = name;
        this.description = description;
        this.type = type;
        this.date = date;
        this.coordinate = coordinate;

    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public Date getDate(){
        return date;
    }
    public Coordinate getCoordinate(){
        return coordinate;
    }

}
