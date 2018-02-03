package org.pvcpirates.frc2018.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.*;
import org.pvcpirates.frc2018.RobotMap;


public class Hardware {

    //possibly move instance creation to constructor??

    private static Hardware ourInstance;
    public final TalonSRX leftDrive1 = new TalonSRX(RobotMap.CANTalonIds.LEFT_DRIVE_1);
    public final TalonSRX rightDrive1 = new TalonSRX(RobotMap.CANTalonIds.RIGHT_DRIVE_1);
    public final TalonSRX leftDrive2 = new TalonSRX(RobotMap.CANTalonIds.LEFT_DRIVE_2);
    public final TalonSRX rightDrive2 = new TalonSRX(RobotMap.CANTalonIds.RIGHT_DRIVE_2);
    
    public final TalonSRX rightCubeGrabMotor = new TalonSRX(RobotMap.CANTalonIds.RIGHT_CUBE_GRABBER);
    public final TalonSRX leftCubeGrabMotor = new TalonSRX(RobotMap.CANTalonIds.LEFT_CUBE_GRABBER);

    public final TalonSRX armPivotMotor = new TalonSRX(RobotMap.CANTalonIds.ARM_PIVOT_TALON);
    public final TalonSRX armExtendMotor = new TalonSRX(RobotMap.CANTalonIds.ARM_EXTEND_TALON);

    //public final AnalogPotentiometer pivotPot = new AnalogPotentiometer(RobotMap.SensorIDs.PIVOT_POT);
    //public final AnalogPotentiometer wristPot = new AnalogPotentiometer(RobotMap.SensorIDs.WRIST_POT);

    public final DoubleSolenoid cubeGrabberSolenoid = new DoubleSolenoid(RobotMap.PneumaticIds.GRABBER_1,
    																		RobotMap.PneumaticIds.GRABBER_2);

    public final DigitalInput cubeLimitSwitch = new DigitalInput(RobotMap.SensorIDs.CUBE_LIMIT_SWITCH);
    public AHRS navx =  new AHRS(SPI.Port.kMXP);
    //replace channels with enums
    public final Ultrasonic leftUltrasonic = new Ultrasonic(2, 3);
    public final Ultrasonic rightUltrasonic = new Ultrasonic(0, 1);
    public final Compressor compressor = new Compressor(0);
    
    

    public static Hardware getInstance() {
        if (ourInstance == null) {
            ourInstance = new Hardware();
        }
        return ourInstance;
    }

    private Hardware() {
    	compressor.setClosedLoopControl(true);

    }
    
    private double getUltraDistance(){
    	return leftUltrasonic.getRangeInches();
    }

}
