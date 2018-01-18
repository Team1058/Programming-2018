package org.pvcpirates.frc2018.io;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import org.pvcpirates.frc2018.util.RobotConstants;

public class Drivetrain {
    private static Drivetrain ourInstance = new Drivetrain();
    private WPI_TalonSRX leftMotor1;
    private WPI_TalonSRX leftMotor2;
    private WPI_TalonSRX rightMotor1;
    private WPI_TalonSRX rightMotor2;
    private AHRS navx;
    private Encoder leftEncoder;
    private Encoder rightEncoder;

    public static Drivetrain getInstance() {
        return ourInstance;
    }

    private Drivetrain() {
        leftMotor1 = new WPI_TalonSRX(RobotConstants.DRIVE_LEFT_1_ID);
        leftMotor2 = new WPI_TalonSRX(RobotConstants.DRIVE_LEFT_2_ID);
        rightMotor1 = new WPI_TalonSRX(RobotConstants.DRIVE_RIGHT_1_ID);
        rightMotor2 = new WPI_TalonSRX(RobotConstants.DRIVE_RIGHT_2_ID);
        leftMotor2.follow(leftMotor1);
        rightMotor2.follow(rightMotor1);

        navx = new AHRS(SPI.Port.kMXP);
        navx.reset();

        leftEncoder = new Encoder(RobotConstants.LEFT_DRIVE_ENCODER_PORTS[0],RobotConstants.LEFT_DRIVE_ENCODER_PORTS[1],false,Encoder.EncodingType.k4X);
        rightEncoder = new Encoder(RobotConstants.RIGHT_DRIVE_ENCODER_PORTS[0],RobotConstants.RIGHT_DRIVE_ENCODER_PORTS[1],false,Encoder.EncodingType.k4X);

        leftEncoder.setDistancePerPulse(RobotConstants.DRIVE_DISTANCE_PER_TICK);
        rightEncoder.setDistancePerPulse(RobotConstants.DRIVE_DISTANCE_PER_TICK);
    }

    public void stopAll(){
        //shut everything off
        leftMotor1.set(0);
        rightMotor1.set(0);
    }

    public void setDrive(double left, double right){
        leftMotor1.set(left);
        rightMotor1.set(-right);
    }

    public double getLeftVelocity(){
        return leftEncoder.getRate();
    }
    public double getRightVelocity(){
        return rightEncoder.getRate();
    }


}
