package org.pvcpirates.frc2018.io;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;

public class SensorInput {
    private static SensorInput ourInstance = new SensorInput();

    public static SensorInput getInstance() {
        return ourInstance;
    }

    private AHRS navx;

    private SensorInput() {
        //init sensors
        navx = new AHRS(SPI.Port.kMXP);
        navx.reset();

        this.reset();
    }

    public void reset(){
        //Resets all sensors
    }

    public void update(){
        //updates the state of the robot and its various subsystems
    }
}