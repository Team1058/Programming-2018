package org.pvcpirates.frc2018.util;

import edu.wpi.first.wpilibj.Joystick;

public class LogitechF310Gamepad {

    private Joystick joystick;

    public LogitechF310Gamepad(int portNumber){
        this.joystick = new Joystick(portNumber);
    }

    public double getLeftX() {
        return this.joystick.getRawAxis(0);
    }

    public double getLeftY() {
        return this.joystick.getRawAxis(1);
    }

    public double getLeftTrigger() {
        return this.joystick.getRawAxis(2);
    }

    public double getRightTrigger(){
        return this.joystick.getRawAxis(3);
    }

    public double getRightX() {
        return this.joystick.getRawAxis(4);
    }

    public double getRightY() {
        return this.joystick.getRawAxis(5);
    }

    public boolean getButton(int btnNum) {
        return this.joystick.getRawButton(btnNum);
    }

    public boolean getAButton() {
        return this.joystick.getRawButton(1);
    }

    public boolean getXButton() {
        return this.joystick.getRawButton(3);
    }

    public boolean getBButton() {
        return this.joystick.getRawButton(2);
    }

    public boolean getYButton() {
        return this.joystick.getRawButton(4);
    }

    public boolean getBackButton() {
        return this.joystick.getRawButton(7);
    }

    public boolean getStartButton() {
        return this.joystick.getRawButton(8);
    }

    public boolean getLeftBumper() {
        return this.joystick.getRawButton(5);
    }

    public boolean getRightBumper() {
        return this.joystick.getRawButton(6);
    }

    public boolean getLeftStickClick() {
        return this.joystick.getRawButton(9);
    }

    public boolean getRightStickClick() {
        return this.joystick.getRawButton(10);
    }

    public int getPOVVal(){
        return this.joystick.getPOV(0);
    }

    public boolean getPOVDown(){
        return (this.joystick.getPOV(0) == 180);
    }

    public boolean getPOVRight(){
        return (this.joystick.getPOV(0) == 90);
    }

    public boolean getPOVUp(){
        return (this.joystick.getPOV(0) == 0);
    }

    public boolean getPOVLeft(){
        return (this.joystick.getPOV(0) == 270);
    }

}