package org.pvcpirates.frc2018.util;

public class GamepadHelper {

    public double applyDeadBand(double initial, double deadBand){
        if(Math.abs(initial) < Math.abs(deadBand)){
            return 0;
        }else{
            return initial;
        }
    }

    public double mapToCurve(double initial, BezierCurve curve){
        return curve.getY(initial);
    }
}
