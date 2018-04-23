package org.pvcpirates.frc2018.robot.subsystems;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.util.RobotMap;

//import com.kauailabs.navx.frc.AHRS;

public class Drivetrain extends BaseSubsystem {
    //14:50:12 14:50:60
    private static Hardware hardware = Hardware.getInstance();

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
    
    public static void zeroEncoders() {
    	//WARNING: WILL BLOCK
    	while (Hardware.getInstance().leftDrive1.getSensorCollection().getQuadraturePosition()!=0 ||Hardware.getInstance().rightDrive1.getSensorCollection().getQuadraturePosition()!= 0){
    		Hardware.getInstance().leftDrive1.getSensorCollection().setQuadraturePosition(0, 10);
    		Hardware.getInstance().rightDrive1.getSensorCollection().setQuadraturePosition(0, 10);
    	}
    }
    
    public static double getRightInches() {
    	return ticksToInches(hardware.rightDrive1.getSensorCollection().getQuadraturePosition());
    }
    
    public static double getRightVelocity() {
    	//inches per 100ms * 10 = inches per second
    	return ticksToInches(hardware.rightDrive1.getSensorCollection().getQuadratureVelocity() * 10);
    }
    
    public static double getLeftVelocity() {
    	//inches per 100ms * 10 = inches per second
    	return ticksToInches(hardware.leftDrive1.getSensorCollection().getQuadratureVelocity() * 10);
    }
    
    public static double getLeftInches() {
    	return ticksToInches(hardware.leftDrive1.getSensorCollection().getQuadraturePosition());
    }
    
    public static void setPostion(double leftInches, double rightInches) {
    	double rightGoal = inchesToTicks(rightInches);
    	double leftGoal = -inchesToTicks(leftInches);
    	
    	setDrive(ControlMode.Position,leftGoal,rightGoal);
    }
    
    
    private static double inchesToTicks(double inches) {
    	//6pi inches per rotation
    	//11.25 wheel rotations to encoder rotations
    	//1024 ticks per rotation
    	return inches / (6.0 * Math.PI) * 1024.0 * 11.25;
    }
    
    private static double ticksToInches(double ticks) {
    	//1024 ticks per rotation
    	//11.25 rotations per wheel rotation
    	// Circumference of 6pi inches
    	return ticks / 1024.0 / 11.25 * 6.0 * Math.PI;
    }
    
    public static void configureAllowableError(double inches) {
    	//sets the allowable error
    	hardware.leftDrive1.configSetParameter(ParamEnum.eProfileParamSlot_AllowableErr,inchesToTicks(inches), 0, 0, 0);
    	hardware.rightDrive1.configSetParameter(ParamEnum.eProfileParamSlot_AllowableErr,inchesToTicks(inches), 0, 0, 0);
    }
}
