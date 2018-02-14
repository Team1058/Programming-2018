package org.pvcpirates.frc2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.RobotMap;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;

import static org.pvcpirates.frc2018.RobotMap.Ranges.THE_MIDDLE;

public class Arm extends BaseSubsystem {
    Hardware hardware = Robot.getInstance().hardware;
    double currArmAngle;
    double currExt;
    double currWristAngle;
    
    
    public Arm() {
        zeroArm();
    }

    /*
    Encoder/pot stuff
    pivot - pot
    rest encoders
     */

    public void zeroArm(){
        hardware.armExtendMotor.set(ControlMode.Position,0);
        hardware.armPivotMotor.set(ControlMode.Position,0);
        hardware.wristPivotMotor.set(ControlMode.Position,THE_MIDDLE);
    }

    public void levelWrist(){
    	hardware.wristPivotMotor.set(ControlMode.Position,THE_MIDDLE);
    }

    public void wristRotate(double angleSetpoint){
        if (angleSetpoint < RobotMap.Ranges.WRIST_MAX && angleSetpoint < RobotMap.Ranges.WRIST_MIN)
            hardware.wristPivotMotor.set(ControlMode.Position,angleSetpoint);
    }

    public void extendArm(double distance){
        if (distance < RobotMap.Constants.ARM_DISTANCE && distance >=0) {
            distance = (distance /(1.751*Math.PI)) * 256;
            hardware.armExtendMotor.set(ControlMode.Position, distance);
        }
    }

    public void pivotArm(double angleSetpoint){
        if (angleSetpoint <RobotMap.Ranges.POTENTIOMETER_MAX && angleSetpoint < RobotMap.Ranges.POTENTIOMETER_MIN) {
            angleSetpoint = (1019*(angleSetpoint/270)+5);
            hardware.armPivotMotor.set(ControlMode.Position, angleSetpoint);
        }
    }

    public void moveXY(double x, double y){
        double angle = Math.atan2(y,x);
        double hyp = y/Math.sin(angle);
        pivotArm(angle);
        extendArm(hyp);
    }

    public void moveCurve(){

    }

}
