package org.pvcpirates.frc2018.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
<<<<<<< HEAD

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SPI;
=======
>>>>>>> 7db7131138724ea89896ca315052e335daa7067c
import edu.wpi.first.wpilibj.Ultrasonic;
import org.pvcpirates.frc2018.RobotMap;

public class Hardware {

    //possibly move instance creation to constructor??

    private static Hardware ourInstance;
    public final TalonSRX leftDrive1 = new TalonSRX(RobotMap.CANTalonIds.LEFT_DRIVE_1);
    public final TalonSRX rightDrive1 = new TalonSRX(RobotMap.CANTalonIds.RIGHT_DRIVE_1);
    public final TalonSRX leftDrive2 = new TalonSRX(RobotMap.CANTalonIds.LEFT_DRIVE_2);
    public final TalonSRX rightDrive2 = new TalonSRX(RobotMap.CANTalonIds.RIGHT_DRIVE_2);

    //public AHRS navx =  new AHRS(SPI.Port.kMXP);
    //replace channels with enums
    public final Ultrasonic ultrasonic = new Ultrasonic(0, 1);


<<<<<<< HEAD
    public final Compressor compressor = new Compressor(0);
    
    private static Hardware ourInstance;
=======
    private Hardware() {
>>>>>>> 7db7131138724ea89896ca315052e335daa7067c

    }

    public static Hardware getInstance() {
        if (ourInstance == null) {
            ourInstance = new Hardware();
        }
        return ourInstance;
    }

<<<<<<< HEAD
    private Hardware() {
    	compressor.setClosedLoopControl(true);
    }
    
    private double getUltraDistance(){
    	return ultrasonic.getRangeInches();
=======
    private double getUltraDistance() {
        return ultrasonic.getRangeInches();
>>>>>>> 7db7131138724ea89896ca315052e335daa7067c
    }

}
