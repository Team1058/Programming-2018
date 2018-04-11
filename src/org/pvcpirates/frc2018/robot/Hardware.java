package org.pvcpirates.frc2018.robot;

import static org.pvcpirates.frc2018.util.RobotMap.Constants.ROBOT_TIMEOUT;

import org.opencv.core.Mat;
import org.pvcpirates.frc2018.util.RobotMap;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;


public class Hardware {

    //possibly move instance creation to constructor??

    private static Hardware ourInstance;

    public final TalonSRX leftDrive1 = new TalonSRX(RobotMap.CANTalonIds.LEFT_DRIVE_1);
    public final TalonSRX rightDrive1 = new TalonSRX(RobotMap.CANTalonIds.RIGHT_DRIVE_1);
    public final TalonSRX leftDrive2 = new TalonSRX(RobotMap.CANTalonIds.LEFT_DRIVE_2);
    public final TalonSRX rightDrive2 = new TalonSRX(RobotMap.CANTalonIds.RIGHT_DRIVE_2);

    public final VictorSPX rightCubeGrabMotor = new VictorSPX(RobotMap.CANTalonIds.RIGHT_CUBE_GRABBER);
    public final VictorSPX leftCubeGrabMotor = new VictorSPX(RobotMap.CANTalonIds.LEFT_CUBE_GRABBER);

    public final TalonSRX armPivotMotor = new TalonSRX(RobotMap.CANTalonIds.ARM_PIVOT_TALON);
    public final TalonSRX armExtendMotor = new TalonSRX(RobotMap.CANTalonIds.ARM_EXTEND_TALON);
    public final TalonSRX armExtendMotorFollower = new TalonSRX(RobotMap.CANTalonIds.ARM_EXTEND_TALON_FOLLOWER);
    public final TalonSRX wristPivotMotor = new TalonSRX(RobotMap.CANTalonIds.WRIST_PIVOT_MOTOR);

    public final ADXL345_I2C wristAccel = new ADXL345_I2C(I2C.Port.kOnboard, Range.k4G);

    //public final UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();

    public final DoubleSolenoid cubeGrabberSolenoid = new DoubleSolenoid(RobotMap.PneumaticIds.GRABBER_1,
            RobotMap.PneumaticIds.GRABBER_2);

    //public final Solenoid climberSolenoid = new Solenoid(RobotMap.PneumaticIds.CLIMBER);

    //replace channels with enums
    public final Ultrasonic leftUltrasonic = new Ultrasonic(0, 1);
    public final Ultrasonic rightUltrasonic = new Ultrasonic(2, 3);
    public final Compressor compressor = new Compressor(0);
    public AHRS navx = new AHRS(SPI.Port.kMXP);


    private Hardware() {

        compressor.setClosedLoopControl(true);
        navx.reset();

        leftDrive1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.Constants.ROBOT_TIMEOUT);
        leftDrive1.setSensorPhase(false);

        rightDrive1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.Constants.ROBOT_TIMEOUT);
        rightDrive1.setSensorPhase(false);
        rightDrive2.setSensorPhase(false);


        rightDrive1.setInverted(true);
        rightDrive2.setInverted(true);

        leftDrive1.setInverted(true);
        leftDrive2.setInverted(true);


        leftDrive1.getSensorCollection().setQuadraturePosition(0, ROBOT_TIMEOUT);
        rightDrive1.getSensorCollection().setQuadraturePosition(0, ROBOT_TIMEOUT);


        leftDrive2.follow(leftDrive1);
        rightDrive2.follow(rightDrive1);


        // Set hard limit (limit switch) so that we don't attempt to retract further than physically possible
        armExtendMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.Constants.ROBOT_TIMEOUT);
        armExtendMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed, ROBOT_TIMEOUT);
        armExtendMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed, ROBOT_TIMEOUT);
        // Set soft limits so that we don't over extend the arm
        armExtendMotor.configForwardSoftLimitThreshold(RobotMap.Ranges.ARM_EXTEND_ENCODER_MAX, ROBOT_TIMEOUT);
        armExtendMotor.configForwardSoftLimitEnable(false, ROBOT_TIMEOUT);

        armExtendMotor.setInverted(true);
        armExtendMotor.setSensorPhase(true);
        // Zero out encoder position if limit switch is hit
        armExtendMotor.configSetParameter(ParamEnum.eClearPositionOnLimitR, 0, 0, 0, ROBOT_TIMEOUT);
        armExtendMotor.setNeutralMode(NeutralMode.Brake);

        armExtendMotor.configClosedloopRamp(0, 0);


        armExtendMotorFollower.setInverted(true);
        armExtendMotorFollower.follow(armExtendMotor);


        armPivotMotor.setNeutralMode(NeutralMode.Brake);
        armPivotMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, RobotMap.Constants.ROBOT_TIMEOUT);
        armPivotMotor.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, ROBOT_TIMEOUT);


        armPivotMotor.setSensorPhase(false);
        armPivotMotor.configForwardSoftLimitThreshold(RobotMap.Ranges.PIVOT_ENCODER_MAX, ROBOT_TIMEOUT);
        armPivotMotor.configReverseSoftLimitThreshold(RobotMap.Ranges.PIVOT_ENCODER_MIN, ROBOT_TIMEOUT);
        armPivotMotor.configForwardSoftLimitEnable(true, ROBOT_TIMEOUT);
        armPivotMotor.configReverseSoftLimitEnable(true, ROBOT_TIMEOUT);


        wristPivotMotor.setNeutralMode(NeutralMode.Brake);
        wristPivotMotor.setInverted(true);
        wristPivotMotor.setSensorPhase(true);

        wristPivotMotor.getSensorCollection().setQuadraturePosition(0, ROBOT_TIMEOUT);

        //wristPivotMotor.configForwardSoftLimitThreshold(RobotMap.Ranges.WRIST_ENCODER_MAX, 0);
        //wristPivotMotor.configReverseSoftLimitThreshold(RobotMap.Ranges.WRIST_ENCODER_MIN,0);
        wristPivotMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed, 0);
        wristPivotMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed, 0);
        wristPivotMotor.configSetParameter(ParamEnum.eClearPosOnLimitR, 0, 0, 0, 0);

        rightCubeGrabMotor.setInverted(false);
        leftCubeGrabMotor.setInverted(true);
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