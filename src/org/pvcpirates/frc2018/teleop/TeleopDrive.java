package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.io.ControllerInput;
import org.pvcpirates.frc2018.io.RobotOutput;
import org.pvcpirates.frc2018.io.SensorInput;
import org.pvcpirates.frc2018.util.GamepadHelper;
import org.pvcpirates.frc2018.util.PIDF;
import org.pvcpirates.frc2018.util.RobotConstants;

public class TeleopDrive implements TeleopComponent{
    private static TeleopDrive ourInstance = new TeleopDrive();

    private RobotOutput robotOutput;
    private SensorInput sensorInput;
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
        sensorInput = SensorInput.getInstance();
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
        double left = y+x;
        double right = y-x;
        toggleDriveMode();
        if(driveMode == DriveMode.VELOCITY){
            velocityDrive(left,right);
        }else{
            directDrive(left,right);
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

    private void velocityDrive(double left, double right){
        double velLeft;
        double velRight;
        double leftOut;
        double rightOut;

        velLeft = RobotConstants.DRIVE_MAX_FORWARD_VEL * left;
        velRight = RobotConstants.DRIVE_MAX_FORWARD_VEL * right;
        leftPIDF.setValue(velLeft);
        rightPIDF.setValue(velRight);

        leftOut = leftPIDF.calculate(velLeft-sensorInput.getLeftDriveVelocity());
        rightOut = rightPIDF.calculate(velRight-sensorInput.getRightDriveVelocity());

        robotOutput.setDrive(leftOut,rightOut);

    }

    private void directDrive(double left, double right){
        robotOutput.setDrive(left,right);
    }

}
