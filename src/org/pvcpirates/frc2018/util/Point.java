package org.pvcpirates.frc2018.util;

public class Point {
    private double x,y;
    public Point(){
        x = 0.0;
        y = 0.0;
    }
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }
}