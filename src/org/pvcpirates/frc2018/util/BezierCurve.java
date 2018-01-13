package org.pvcpirates.frc2018.util;

public class BezierCurve {

    private Point p0, p1, p2, p3;

    public BezierCurve(Point p0, Point p1, Point p2, Point p3){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p3 = p3;
    }

    public double getY(double t){
        if(t<0){
            t *= -1;
            return -1*(Math.pow((1-t),3)*p0.getY() + 3*Math.pow((1-t),2)*t*p1.getY() + 3*(1-t)*Math.pow(t,2)*p2.getY() + Math.pow(t,3)*p3.getY());
        }
        return Math.pow((1-t),3)*p0.getY() + 3*Math.pow((1-t),2)*t*p1.getY() + 3*(1-t)*Math.pow(t,2)*p2.getY() + Math.pow(t,3)*p3.getY();
    }
    public double getX(double t){
        if(t<0){
            t *= -1;
            return (Math.pow((1-t),3)*p0.getX() + 3*Math.pow((1-t),2)*t*p1.getX() + 3*(1-t)*Math.pow(t,2)*p2.getX() + Math.pow(t,3)*p0.getX())*-1;
        }
        return Math.pow((1-t),3)*p0.getX() + 3*Math.pow((1-t),2)*t*p1.getX() + 3*(1-t)*Math.pow(t,2)*p2.getX() + Math.pow(t,3)*p0.getX();
    }

}