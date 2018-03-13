package org.pvcpirates.frc2018.util;

import org.pvcpirates.frc2018.robot.Hardware;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RobotLogger {
    long initTime;
    public void start(){
        initTime = System.nanoTime();
    }
    public void log(String mode){
        String time = new SimpleDateFormat("mm:ss").format(new Date(TimeUnit.SECONDS.toSeconds(System.nanoTime()-initTime)));
        Logger.getLogger("RoboLogger").log(Level.INFO,"");
    }
}
