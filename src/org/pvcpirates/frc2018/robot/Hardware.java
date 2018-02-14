package org.pvcpirates.frc2018.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;

import org.pvcpirates.frc2018.RobotMap;

import static org.pvcpirates.frc2018.RobotMap.Constants.*;


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
    public final TalonSRX armExtendMotorFollower = new TalonSRX(RobotMap.CANTalonIds.ARM_EXTEND_TALON_FOLLOWER);
    public final TalonSRX wristPivotMotor = new TalonSRX(RobotMap.CANTalonIds.WRIST_PIVOT_MOTOR);
    public final ADXL345_I2C wristAccel = new ADXL345_I2C(I2C.Port.kOnboard, Range.k4G);

    public final DoubleSolenoid cubeGrabberSolenoid = new DoubleSolenoid(RobotMap.PneumaticIds.GRABBER_1,
    																		RobotMap.PneumaticIds.GRABBER_2);
    public final DigitalInput cubeLimitSwitch = new DigitalInput(RobotMap.SensorIDs.CUBE_LIMIT_SWITCH);
    
    public AHRS navx =  new AHRS(SPI.Port.kMXP);
    //replace channels with enums
    public final Ultrasonic leftUltrasonic = new Ultrasonic(0, 1);
    public final Ultrasonic rightUltrasonic = new Ultrasonic(2, 3);
    public final Compressor compressor = new Compressor(0);
    
    

    public static Hardware getInstance() {
        if (ourInstance == null) {
            ourInstance = new Hardware();
        }
        return ourInstance;
    }

    private Hardware() {
    	compressor.setClosedLoopControl(true);
    	leftUltrasonic.setAutomaticMode(true);
    	leftDrive1.setSensorPhase(false);
    	leftDrive1.setInverted(true);
    	leftDrive2.setInverted(true);

    	//rightUltrasonic.setAutomaticMode(true);
        leftDrive1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.Constants.DRIVEBASE_TIMEOUT);
        rightDrive1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.Constants.DRIVEBASE_TIMEOUT);
        leftDrive2.follow(leftDrive1);
        rightDrive2.follow(rightDrive1);

        armExtendMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0, RobotMap.Constants.DRIVEBASE_TIMEOUT);

        armPivotMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog,0, RobotMap.Constants.DRIVEBASE_TIMEOUT);
        armPivotMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,DRIVEBASE_TIMEOUT);
        armPivotMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,DRIVEBASE_TIMEOUT);
        armPivotMotor.configForwardSoftLimitThreshold(RobotMap.Ranges.POTENTIOMETER_MAX,DRIVEBASE_TIMEOUT);
        armPivotMotor.configReverseSoftLimitThreshold(RobotMap.Ranges.POTENTIOMETER_MIN,DRIVEBASE_TIMEOUT);

        armPivotMotor.configForwardSoftLimitEnable(true,DRIVEBASE_TIMEOUT);
        armPivotMotor.configReverseSoftLimitEnable(true,DRIVEBASE_TIMEOUT);


        armExtendMotorFollower.follow(armExtendMotor);
    }
    
    public static void setPIDF(double p, double i, double d, double f,TalonSRX talonSRX){
        talonSRX.config_kP(0,p,DRIVEBASE_TIMEOUT);
        talonSRX.config_kI(0,i,DRIVEBASE_TIMEOUT);
        talonSRX.config_kD(0,d,DRIVEBASE_TIMEOUT);
        talonSRX.config_kD(0,f,DRIVEBASE_TIMEOUT);
    }
}
