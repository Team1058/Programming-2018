package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.io.Drivetrain;
import org.pvcpirates.frc2018.util.GamepadHelper;
import org.pvcpirates.frc2018.util.LogitechF310Gamepad;
import org.pvcpirates.frc2018.util.PIDF;
import org.pvcpirates.frc2018.util.RobotConstants;

public class TeleopDrive implements TeleopComponent{
    private static TeleopDrive ourInstance = new TeleopDrive();

    private Drivetrain drivetrain;
    private LogitechF310Gamepad driver;
    private DriveMode driveMode = DriveMode.VELOCITY;
    private GamepadHelper gamepadHelper = new GamepadHelper();
    private PIDF leftPIDF;
    private PIDF rightPIDF;

    public static TeleopDrive getInstance() {
        return ourInstance;
    }

    private TeleopDrive() {
        drivetrain = Drivetrain.getInstance();
        driver = TeleopControl.getInstance().driver;
        leftPIDF = new PIDF(RobotConstants.DRIVE_P,RobotConstants.DRIVE_I,RobotConstants.DRIVE_D,RobotConstants.DRIVE_F,0);
        rightPIDF = new PIDF(RobotConstants.DRIVE_P,RobotConstants.DRIVE_I,RobotConstants.DRIVE_D,RobotConstants.DRIVE_F,0);

    }

    public enum DriveMode{
        DIRECT,VELOCITY
    }

    @Override
    public void calculate(){
        double x = gamepadHelper.applyDeadBand(driver.getRightX(),0.15);
        double y = gamepadHelper.applyDeadBand(driver.getLeftY(),0.15);
        double left = y+x;
        double right = y-x;
        if(driver.getAButton()) {
            toggleDriveMode();
        }
        if(driveMode == DriveMode.VELOCITY){
            velocityDrive(left,right);
        }else{
            directDrive(left,right);
        }
    }

    @Override
    public void disable(){
        this.drivetrain.setDrive(0,0);
    }

    private void toggleDriveMode(){
        if(driveMode == DriveMode.DIRECT){
            driveMode = DriveMode.VELOCITY;
        }else {
            driveMode = DriveMode.DIRECT;
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

        leftOut = leftPIDF.calculate(velLeft-drivetrain.getLeftVelocity());
        rightOut = rightPIDF.calculate(velRight-drivetrain.getRightVelocity());

        drivetrain.setDrive(leftOut,rightOut);
    }

    private void directDrive(double left, double right){
        drivetrain.setDrive(left,right);
    }

}
