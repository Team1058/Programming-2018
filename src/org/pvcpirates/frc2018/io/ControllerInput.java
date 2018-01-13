package org.pvcpirates.frc2018.io;

import org.pvcpirates.frc2018.util.BezierCurve;
import org.pvcpirates.frc2018.util.LogitechF310Gamepad;
import org.pvcpirates.frc2018.util.Point;

public class ControllerInput {
    private static ControllerInput ourInstance = new ControllerInput();

    public static ControllerInput getInstance() {
        return ourInstance;
    }

    private LogitechF310Gamepad driver;
    private LogitechF310Gamepad operator;

    //1114's points
    private Point zero = new Point(0,0);
    private Point one = new Point(1,1);
    private Point xP1 = new Point(0.0,0.2);
    private Point xP2 = new Point(0.55,-0.1);
    private Point yP1 = new Point(0.0,0.54);
    private Point yP2 = new Point(0.45,-0.07);

    private BezierCurve xCurve = new BezierCurve(zero,xP1,xP2,one);
    private BezierCurve yCurve = new BezierCurve(zero,yP1,yP2,one);

    private ControllerInput() {
        this.driver = new LogitechF310Gamepad(0);
        this.operator = new LogitechF310Gamepad(1);
    }
    /*
     * ADD CONTROLS HERE
     */
    public double getDriverLeftX(){
        return this.driver.getLeftX();
    }
    public double getDriverLeftY(){
        return this.driver.getLeftY();
    }
    public double getDriverRightX(){
        return this.driver.getRightX();
    }
    public double getDriverRightY(){
        return this.driver.getRightY();
    }

    public double getMappedDriverLeftX(){
        return xCurve.getY(this.driver.getLeftX());
    }
    public double getMappedDriverLeftY(){
        return yCurve.getY(this.driver.getLeftY());
    }
    public double getMappedDriverRightX(){
        return xCurve.getY(this.driver.getRightX());
    }
    public double getMappedDriverRightY(){
        return yCurve.getY(this.driver.getRightY());
    }
}