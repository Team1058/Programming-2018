package org.pvcpirates.frc2018.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Ultrasonic;
import org.pvcpirates.frc2018.RobotMap;

public class Hardware {

    //possibly move instance creation to constructor??

    public final TalonSRX LD1 = new TalonSRX(RobotMap.CANTalonIds.LEFT_DRIVE_1);
    public final TalonSRX RD1 = new TalonSRX(RobotMap.CANTalonIds.RIGHT_DRIVE_1);

    public final TalonSRX LD2 = new TalonSRX(RobotMap.CANTalonIds.LEFT_DRIVE_2);
    public final TalonSRX RD2 = new TalonSRX(RobotMap.CANTalonIds.RIGHT_DRIVE_2);

    //replace channels with enums
    public final Ultrasonic ultrasonic = new Ultrasonic(0,1);

    //public AHRS navx =  new AHRS(SPI.Port.kMXP);
    public Encoder leftEncoder = new Encoder(RobotMap.SensorIDs.LEFT_DRIVE_ENCODER_PORTS[0], RobotMap.SensorIDs.LEFT_DRIVE_ENCODER_PORTS[1],false,Encoder.EncodingType.k4X);;
    public Encoder rightEncoder= new Encoder(RobotMap.SensorIDs.RIGHT_DRIVE_ENCODER_PORTS[0],RobotMap.SensorIDs.RIGHT_DRIVE_ENCODER_PORTS[1],false,Encoder.EncodingType.k4X);;

    private static Hardware ourInstance = new Hardware();


    public static Hardware getInstance() {
        return ourInstance;
    }

    private Hardware() {

    }
}
