package org.pvcpirates.frc2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.RobotMap;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.BaseController;

//import com.kauailabs.navx.frc.AHRS;

public class Drivetrain extends BaseSubsystem {
    //14:50:12 14:50:60
    private static Hardware hardware = Hardware.getInstance();

    public Drivetrain() {
        //TODO? SHOULD THIS BE IN HARDWARE INIT
    }

    public static void stopAll() {
        //shut everything off
        hardware.leftDrive1.set(ControlMode.PercentOutput, 0);
        hardware.rightDrive1.set(ControlMode.PercentOutput, 0);
    }

    public static void setDrive(ControlMode controlMode, double left, double right) {
        hardware.leftDrive1.set(controlMode, left);
        hardware.rightDrive1.set(controlMode, right);
    }

    public static void setPIDF(double p, double i, double d, double f) {
        //Why ctre whyyyyyy
        hardware.leftDrive1.config_kP(0, p, RobotMap.Constants.ROBOT_TIMEOUT);
        hardware.leftDrive1.config_kI(0, i, RobotMap.Constants.ROBOT_TIMEOUT);
        hardware.leftDrive1.config_kD(0, d, RobotMap.Constants.ROBOT_TIMEOUT);
        hardware.leftDrive1.config_kF(0, f, RobotMap.Constants.ROBOT_TIMEOUT);

        hardware.rightDrive1.config_kP(0, p, RobotMap.Constants.ROBOT_TIMEOUT);
        hardware.rightDrive1.config_kI(0, i, RobotMap.Constants.ROBOT_TIMEOUT);
        hardware.rightDrive1.config_kD(0, d, RobotMap.Constants.ROBOT_TIMEOUT);
        hardware.rightDrive1.config_kF(0, f, RobotMap.Constants.ROBOT_TIMEOUT);
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
