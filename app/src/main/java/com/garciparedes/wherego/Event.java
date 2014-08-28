package com.garciparedes.wherego;

/**
 * Created by garciparedes on 21/07/14.
 */
public class Event {

    private String name;
    private String description;

    private int day;
    private int month;
    private int year;


    public Event(String name, String description, int day, int month, int year){
        this.name = name;
        this.description = description;
        this.day = day;
        this.month = month;
        this.year = year;

    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getDay(){
        return day;
    }

    public int getMonth(){
        return month;
    }

    public int getYear(){
        return year;
    }
}
