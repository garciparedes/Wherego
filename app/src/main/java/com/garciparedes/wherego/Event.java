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


    public Event(String name, String type, String description, Date date, Coordinate coordinate){
        this.name = name;
        this.type = type;
        this.description = description;
        this.date = date;
        this.coordinate = coordinate;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getName(){
        return name;
    }

    public String getType() {
        return type;
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
