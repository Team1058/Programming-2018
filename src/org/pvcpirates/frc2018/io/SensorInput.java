package org.pvcpirates.frc2018.io;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import org.pvcpirates.frc2018.util.RobotConstants;

public class SensorInput {
    private static SensorInput ourInstance = new SensorInput();

    public static SensorInput getInstance() {
        return ourInstance;
    }

    private AHRS navx;
    private Encoder leftDriveEnc;
    private Encoder rightDriveEnc;

    private SensorInput() {
        //init sensors
        navx = new AHRS(SPI.Port.kMXP);
        leftDriveEnc = new Encoder(RobotConstants.LEFT_DRIVE_ENCODER_PORTS[0],RobotConstants.LEFT_DRIVE_ENCODER_PORTS[1],false,Encoder.EncodingType.k4X);
        rightDriveEnc = new Encoder(RobotConstants.RIGHT_DRIVE_ENCODER_PORTS[0],RobotConstants.RIGHT_DRIVE_ENCODER_PORTS[1],false,Encoder.EncodingType.k4X);

        leftDriveEnc.setDistancePerPulse(RobotConstants.DRIVE_DISTANCE_PER_TICK);
        rightDriveEnc.setDistancePerPulse(RobotConstants.DRIVE_DISTANCE_PER_TICK);
        reset();
    }

    public void reset(){
        //Resets all sensors
        navx.reset();
    }

    public void update(){
        //updates the state of the robot and its various subsystems
    }

    public double getLeftDriveVelocity(){
        return leftDriveEnc.getRate();
    }
    public double getRightDriveVelocity(){
        return rightDriveEnc.getRate();
    }


}