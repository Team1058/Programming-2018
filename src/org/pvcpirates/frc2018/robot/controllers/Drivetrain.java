package org.pvcpirates.frc2018.robot.controllers;

import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.kauailabs.navx.frc.AHRS;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.RobotMap;

public class Drivetrain extends BaseController {

    private Hardware hardware = Hardware.getInstance();
    public Drivetrain() {

       hardware.leftDrive2.follow(hardware.leftDrive1);
       hardware.rightDrive2.follow(hardware.rightDrive1);
       //TODO: Extract navx and encoders
        //hardware.navx.reset();

        hardware.leftEncoder.setDistancePerPulse(RobotMap.Constants.DRIVE_DISTANCE_PER_TICK);
        hardware.rightEncoder.setDistancePerPulse(RobotMap.Constants.DRIVE_DISTANCE_PER_TICK);
    }

    public void stopAll(){
        //shut everything off
        hardware.leftDrive1.set(ControlMode.PercentOutput,0);
        hardware.leftDrive1.set(ControlMode.PercentOutput,0);
    }

    public void setDrive(double left, double right){
        hardware.leftDrive1.set(ControlMode.PercentOutput,left);
        hardware.rightDrive1.set(ControlMode.PercentOutput,-right);
    }
    public void setPIDF(double p,double i, double d, double f){
        //Why ctre whyyyyyy
        hardware.leftDrive1.config_kP(0,p,RobotMap.Constants.DRIVEBASE_TIMEOUT);
        hardware.leftDrive1.config_kI(0,i,RobotMap.Constants.DRIVEBASE_TIMEOUT);
        hardware.leftDrive1.config_kD(0,d,RobotMap.Constants.DRIVEBASE_TIMEOUT);
        hardware.leftDrive1.config_kF(0,f,RobotMap.Constants.DRIVEBASE_TIMEOUT);

        hardware.rightDrive1.config_kP(0,p,RobotMap.Constants.DRIVEBASE_TIMEOUT);
        hardware.rightDrive1.config_kI(0,i,RobotMap.Constants.DRIVEBASE_TIMEOUT);
        hardware.rightDrive1.config_kD(0,d,RobotMap.Constants.DRIVEBASE_TIMEOUT);
        hardware.rightDrive1.config_kF(0,f,RobotMap.Constants.DRIVEBASE_TIMEOUT);

    }
    /*
    TODO:Move to encoder subsystem
    public double getLeftVelocity(){
        return leftEncoder.getRate();
    }
    public double getRightVelocity(){
        return rightEncoder.getRate();
    }
    */


}
