package org.pvcpirates.frc2018.robot.subsystems;

import org.pvcpirates.frc2018.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Grabber extends BaseSubsystem {
	/*
	 * Subsystem that holds static functions that control the intake
	 */
    private static Hardware hardware = Hardware.getInstance();
    private static final DoubleSolenoid solenoid = hardware.cubeGrabberSolenoid;
    private static final VictorSPX rightMotor = hardware.rightCubeGrabMotor;
    private static final VictorSPX leftMotor = hardware.leftCubeGrabMotor;

    public static void openGrabber() {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

    public static void closeGrabber() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    public static void noGrabber(){
    	solenoid.set(DoubleSolenoid.Value.kOff);
    }

    public static void intakeRollers() {
    	//make one side slower so cube will spin
        rightMotor.set(ControlMode.PercentOutput, 1);
        leftMotor.set(ControlMode.PercentOutput, -.75);
    }

    public static void holdRollers() {
        rightMotor.set(ControlMode.PercentOutput, .15);
        leftMotor.set(ControlMode.PercentOutput, -.15);
    }

    public static void outtakeRollers() {
        rightMotor.set(ControlMode.PercentOutput, -1);
        leftMotor.set(ControlMode.PercentOutput, 1);
    }
    public static void outtakeRollersHalf() {
        rightMotor.set(ControlMode.PercentOutput, -.7);
        leftMotor.set(ControlMode.PercentOutput, .7);
    }
    public static void outtakeRollersQuarter() {
        rightMotor.set(ControlMode.PercentOutput, -.25);
        leftMotor.set(ControlMode.PercentOutput, .25);
    }
    public static void stopRollers() {
        rightMotor.set(ControlMode.PercentOutput, 0);
        leftMotor.set(ControlMode.PercentOutput, 0);
    }
}
