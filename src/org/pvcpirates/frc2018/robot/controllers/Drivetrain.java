package org.pvcpirates.frc2018.robot.controllers;

import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.kauailabs.navx.frc.AHRS;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.RobotMap;

public class Drivetrain extends BaseController {

    Hardware hardware = Robot.getInstance().hardware;
    public Drivetrain() {

       hardware.LD2.follow(hardware.LD1);
       hardware.RD2.follow(hardware.RD1);
       //TODO: Extract navx and encoders
        //hardware.navx.reset();

        hardware.leftEncoder.setDistancePerPulse(RobotMap.Constants.DRIVE_DISTANCE_PER_TICK);
        hardware.rightEncoder.setDistancePerPulse(RobotMap.Constants.DRIVE_DISTANCE_PER_TICK);
    }

    public void stopAll(){
        //shut everything off
        hardware.LD1.set(ControlMode.PercentOutput,0);
        hardware.LD1.set(ControlMode.PercentOutput,0);
    }

    public void setDrive(double left, double right){
        hardware.LD1.set(ControlMode.PercentOutput,left);
        hardware.RD1.set(ControlMode.PercentOutput,-right);
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
