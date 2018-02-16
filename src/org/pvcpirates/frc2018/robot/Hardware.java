package org.pvcpirates.frc2018.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import org.pvcpirates.frc2018.RobotMap;

import static org.pvcpirates.frc2018.RobotMap.Constants.ROBOT_TIMEOUT;
import static org.pvcpirates.frc2018.RobotMap.Ranges.ARM_EXTEND_MAX;
import static org.pvcpirates.frc2018.RobotMap.Ranges.ARM_EXTEND_MIN;


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
    //replace channels with enums
    public final Ultrasonic leftUltrasonic = new Ultrasonic(0, 1);
    public final Ultrasonic rightUltrasonic = new Ultrasonic(2, 3);
    public final Compressor compressor = new Compressor(0);
    public AHRS navx = new AHRS(SPI.Port.kMXP);


    private Hardware() {
        compressor.setClosedLoopControl(true);
        leftUltrasonic.setAutomaticMode(true);
        leftDrive1.setSensorPhase(false);
        leftDrive1.setInverted(true);
        leftDrive2.setInverted(true);//rightUltrasonic.setAutomaticMode(true);
        leftDrive1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.Constants.ROBOT_TIMEOUT);
        rightDrive1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.Constants.ROBOT_TIMEOUT);
        leftDrive2.follow(leftDrive1);
        rightDrive2.follow(rightDrive1);

        //ALWAYS USE PROTECTION
        armExtendMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.Constants.ROBOT_TIMEOUT);
        armExtendMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, ROBOT_TIMEOUT);
        armExtendMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, ROBOT_TIMEOUT);
        armExtendMotor.configForwardSoftLimitThreshold(ARM_EXTEND_MAX, ROBOT_TIMEOUT);
        armExtendMotor.configReverseSoftLimitThreshold(ARM_EXTEND_MIN, ROBOT_TIMEOUT);
        armExtendMotor.configForwardSoftLimitEnable(true, ROBOT_TIMEOUT);
        armExtendMotor.configReverseSoftLimitEnable(true, ROBOT_TIMEOUT);

        armPivotMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, RobotMap.Constants.ROBOT_TIMEOUT);
        armPivotMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, ROBOT_TIMEOUT);
        armPivotMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, ROBOT_TIMEOUT);
        armPivotMotor.configForwardSoftLimitThreshold(RobotMap.Ranges.POTENTIOMETER_MAX, ROBOT_TIMEOUT);
        armPivotMotor.configReverseSoftLimitThreshold(RobotMap.Ranges.POTENTIOMETER_MIN, ROBOT_TIMEOUT);
        armPivotMotor.configForwardSoftLimitEnable(true, ROBOT_TIMEOUT);
        armPivotMotor.configReverseSoftLimitEnable(true, ROBOT_TIMEOUT);


        armExtendMotorFollower.follow(armExtendMotor);
    }

    public static Hardware getInstance() {
        if (ourInstance == null) {
            ourInstance = new Hardware();
        }
        return ourInstance;
    }

    public static void setPIDF(double p, double i, double d, double f, TalonSRX talonSRX) {
        talonSRX.config_kP(0, p, ROBOT_TIMEOUT);
        talonSRX.config_kI(0, i, ROBOT_TIMEOUT);
        talonSRX.config_kD(0, d, ROBOT_TIMEOUT);
        talonSRX.config_kD(0, f, ROBOT_TIMEOUT);
    }
}
