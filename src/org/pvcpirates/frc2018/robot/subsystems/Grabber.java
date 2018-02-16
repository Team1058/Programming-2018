package org.pvcpirates.frc2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.pvcpirates.frc2018.robot.Hardware;

public class Grabber extends BaseSubsystem {

    private static Hardware hardware = Hardware.getInstance();
    private static final DoubleSolenoid solenoid = hardware.cubeGrabberSolenoid;
    private static final TalonSRX rightMotor = hardware.rightCubeGrabMotor;
    private static final TalonSRX leftMotor = hardware.leftCubeGrabMotor;

    public static void openGrabber() {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

    public static void closeGrabber() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public static void intakeRollers() {
        rightMotor.set(ControlMode.PercentOutput, -1);
        leftMotor.set(ControlMode.PercentOutput, .75);
    }

    public static void holdRollers() {
        rightMotor.set(ControlMode.PercentOutput, -.1);
        leftMotor.set(ControlMode.PercentOutput, .1);
    }

    public static void outtakeRollers() {
        rightMotor.set(ControlMode.PercentOutput, 1);
        leftMotor.set(ControlMode.PercentOutput, -1);
    }

    public static void stopRollers() {
        rightMotor.set(ControlMode.PercentOutput, 0);
        leftMotor.set(ControlMode.PercentOutput, 0);
    }
}
