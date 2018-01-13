package org.pvcpirates.frc2018.io;

public class RobotOutput {
    private static RobotOutput ourInstance = new RobotOutput();

    public static RobotOutput getInstance() {
        return ourInstance;
    }

    private RobotOutput() {
        //init devices that change the state of the robot
    }

    public void stopAll(){
        //shut everything off
    }
}