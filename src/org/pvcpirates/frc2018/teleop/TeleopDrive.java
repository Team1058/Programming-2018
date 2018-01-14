package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.Robot;
import org.pvcpirates.frc2018.io.ControllerInput;
import org.pvcpirates.frc2018.io.RobotOutput;
import org.pvcpirates.frc2018.util.GamepadHelper;
import org.pvcpirates.frc2018.util.PIDF;
import org.pvcpirates.frc2018.util.RobotConstants;

public class TeleopDrive implements TeleopComponent{
    private static TeleopDrive ourInstance = new TeleopDrive();

    private RobotOutput robotOutput;
    private ControllerInput controllerInput;
    private DriveMode driveMode = DriveMode.VELOCITY;
    private GamepadHelper gamepadHelper = new GamepadHelper();
    private PIDF leftPIDF;
    private PIDF rightPIDF;

    public static TeleopDrive getInstance() {
        return ourInstance;
    }

    private TeleopDrive() {
        robotOutput = RobotOutput.getInstance();
        controllerInput = ControllerInput.getInstance();
        leftPIDF = new PIDF(RobotConstants.DRIVE_P,RobotConstants.DRIVE_I,RobotConstants.DRIVE_D,RobotConstants.DRIVE_F,0);
        rightPIDF = new PIDF(RobotConstants.DRIVE_P,RobotConstants.DRIVE_I,RobotConstants.DRIVE_D,RobotConstants.DRIVE_F,0);
    }

    public enum DriveMode{
        DIRECT,VELOCITY
    }

    @Override
    public void calculate(){
        double x = gamepadHelper.applyDeadBand(controllerInput.getDriverRightX(),0.15);
        double y = gamepadHelper.applyDeadBand(controllerInput.getDriverLeftY(),0.15);
        toggleDriveMode();
        if(driveMode == DriveMode.VELOCITY){
            velocityDrive(x,y);
        }else{
            directDrive(x,y);
        }
    }

    @Override
    public void disable(){
        this.robotOutput.setDrive(0,0);
    }

    private void toggleDriveMode(){
        if(controllerInput.getDriveMode()){
            if(driveMode == DriveMode.DIRECT){
                driveMode = DriveMode.VELOCITY;
            }else{
                driveMode = DriveMode.DIRECT;
            }
        }
    }

    private void velocityDrive(double x, double y){
        double velLeft;
        double velRight;
        double leftOut = 0;
        double rightOut = 0;

        velLeft = (y * RobotConstants.DRIVE_MAX_FORWARD_VEL) + (x * RobotConstants.DRIVE_MAX_TURN_VEL);
        velRight = (y * RobotConstants.DRIVE_MAX_FORWARD_VEL) - (x * RobotConstants.DRIVE_MAX_TURN_VEL);
        leftPIDF.setValue(velLeft);
        rightPIDF.setValue(velRight);

        //robotOutput.setDrive(leftPIDF.calculate());

    }

    private void directDrive(double x, double y){
        robotOutput.setDrive(y+x,y-x);
    }

}
