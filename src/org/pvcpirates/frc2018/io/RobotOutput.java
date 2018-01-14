package org.pvcpirates.frc2018.io;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.pvcpirates.frc2018.util.RobotConstants;

public class RobotOutput {
    private static RobotOutput ourInstance = new RobotOutput();

    private WPI_TalonSRX driveLeft1;
    private WPI_TalonSRX driveLeft2;
    private WPI_TalonSRX driveRight1;
    private WPI_TalonSRX driveRight2;

    public static RobotOutput getInstance() {
        return ourInstance;
    }

    private RobotOutput() {
        //init devices that change the state of the robot
        driveLeft1 = new WPI_TalonSRX(RobotConstants.DRIVE_LEFT_1_ID);
        driveLeft2 = new WPI_TalonSRX(RobotConstants.DRIVE_LEFT_2_ID);
        driveRight1 = new WPI_TalonSRX(RobotConstants.DRIVE_RIGHT_1_ID);
        driveRight2 = new WPI_TalonSRX(RobotConstants.DRIVE_RIGHT_2_ID);
        driveLeft2.follow(driveLeft1);
        driveRight2.follow(driveRight1);
    }

    public void stopAll(){
        //shut everything off
        driveLeft1.set(0);
        driveRight1.set(0);
    }

    public void setDrive(double left, double right){
        driveLeft1.set(left);
        driveRight1.set(-right);
    }
}