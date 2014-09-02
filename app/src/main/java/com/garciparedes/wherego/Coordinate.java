package com.garciparedes.wherego;

/**
 * Created by garciparedes on 01/09/14.
 */
public class Coordinate {

    private String title;

    private double x;
    private double y;

    public Coordinate(String title, double x, double y){
        this.title = title;
        this.x = x;
        this.y = y;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getTitle() {
        return title;
    }

    public  double getX(){
        return x;
    }

    public double getY() {
        return y;
    }
}
