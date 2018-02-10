package org.pvcpirates.frc2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.pvcpirates.frc2018.robot.Hardware;

public class Grabber extends BaseSubsystem {

    private Hardware hardware = Hardware.getInstance();
    private final DoubleSolenoid solenoid = hardware.cubeGrabberSolenoid;
    private final TalonSRX rightMotor = hardware.rightCubeGrabMotor;
    private final TalonSRX leftMotor  = hardware.leftCubeGrabMotor;

    public void openGrabber(){
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void closeGrabber(){
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    public void intakeRollers(){
        rightMotor.set(ControlMode.PercentOutput,-1);
        leftMotor.set(ControlMode.PercentOutput, .75);
    }
    public void holdRollers(){
        rightMotor.set(ControlMode.PercentOutput,-.1);
        leftMotor.set(ControlMode.PercentOutput, .1);
    }
    public void outtakeRollers(){
        rightMotor.set(ControlMode.PercentOutput,1);
        leftMotor.set(ControlMode.PercentOutput,-1);
    }
    public void stopRollers(){
        rightMotor.set(ControlMode.PercentOutput,0);
        leftMotor.set(ControlMode.PercentOutput,0);
    }
}
