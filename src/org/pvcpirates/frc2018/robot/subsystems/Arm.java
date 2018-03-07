package org.pvcpirates.frc2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import static org.pvcpirates.frc2018.util.RobotMap.Constants.*;
import static org.pvcpirates.frc2018.util.RobotMap.Ranges.THE_MIDDLE;

import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.util.RobotMap;

public class Arm extends BaseSubsystem {
    private static Hardware hardware = Robot.getInstance().hardware;
    public static boolean running = false;

    public static void configurePID() {
        Hardware.setPIDF(16, 0, 0, 0, hardware.armPivotMotor);
        Hardware.setPIDF(.7, 0, 0, 0, hardware.armExtendMotor);
        Hardware.setPIDF(1.8, 0, 0, 0, hardware.wristPivotMotor);
    }

    public static void levelWrist() {
    	//flips wrist when arm is on the opposite side
        if (getPivotAngle() < 85)
            wristRotate(getPivotAngle());
        else if (getPivotAngle() > 95)
            wristRotate(getPivotAngle() - 180);
    }

    public static void wristRotate(double angleSetpoint) {
    	//allow for -90 to 90 range
        angleSetpoint += 90;
        angleSetpoint = ((angleSetpoint * RobotMap.Ranges.WRIST_ENCODER_MAX / 180.0));
        hardware.wristPivotMotor.set(ControlMode.Position, angleSetpoint);
    }

    public static void extendArm(double distance) {
    	//convert inches to encoder ticks (4096 ticks per rotation)
        distance = (distance / (SPROCKET_DIAMETER * Math.PI)) * 4096;
        hardware.armExtendMotor.set(ControlMode.Position, distance);
    }

    public static double getArmExtension() {
    	//convert encoder ticks to inches (4096 encoder ticks per rotation)
        return (hardware.armExtendMotor.getSensorCollection().getQuadraturePosition() / 4096.0) * (SPROCKET_DIAMETER * Math.PI);
    }

    public static double getArmY() {
        return Math.sin(getPivotAngle()) * getArmExtension();
    }

    public static double getArmX() {
        double rad = Arm.getPivotAngle() * Math.PI / 180.0;
        double ext = Arm.getArmExtension();
        //add 15in to compensate for the min extension
        return Math.abs((Math.cos(rad) * (ext + 15)));
    }

    public static void pivotArm(double angleSetpoint) {
        //convert angle setpoint to encoder ticks
        angleSetpoint = (angleSetpoint * (512.0 / 180.0)) + 256;
        hardware.armPivotMotor.set(ControlMode.Position, angleSetpoint);
    }

    public static double getPivotAngle() {
    	//512 ticks in 180 degrees
    	//subtract 90 to make arm behave like the unit circle
        return (hardware.armPivotMotor.getSensorCollection().getAnalogIn() * (180.0 / 512.0)) - 90;
    }

    //min -425
    //max 929
    //horiz 757
    public static void moveXY(double x, double y) {
        double angle;
        double hyp;

        //Make sure where within the perimeter either side
        if (Math.abs(x) > PIVOT_TO_MAX_PERIM) {
            x = PIVOT_TO_MAX_PERIM * (Math.abs(x) / x);
        }
        //DONT RUN THE ARM INTO THE GROUND
        if (y < 0)
            y = 25;
        y -= GROUND_TO_PIVOT;
        angle = Math.atan2(y, x);
        hyp = y / Math.sin(angle);

        extendArm(hyp);
        pivotArm(angle);
    }

    public static void moveArmPolar(double ext, double angle) {

        double x = (Math.cos(angle*(Math.PI/180))*ext)+ Arm.getWristX();


        if (x > PIVOT_TO_MAX_PERIM) {
            double radAngle = (Arm.getPivotAngle() > 90 ? 180 - Arm.getPivotAngle() : Arm.getPivotAngle()) * (Math.PI / 180);
            ext = ((PIVOT_TO_MAX_PERIM - Math.abs(Arm.getWristX())) / Math.cos(radAngle)) - 15;
            Arm.extendArm(ext);
        }else{
            Arm.pivotArm(angle);
            Arm.extendArm(ext);
        }
    }


    public static double getWristX() {
        double rad = (Math.abs(getWristAngle())) * Math.PI / 180;

        return (Math.cos(rad) * 13.0);

    }

    public static void moveCurveMax(double y) {
        // Distance between pivot point and maximum robot extension size
        double x = PIVOT_TO_MAX_PERIM;
        //if you need to start curving back throw out x since its gonna be less

        if (y > MAX_ARM_HEIGHT)
            x = Math.sqrt(Math.pow(ARM_DISTANCE, 2) - Math.pow(y - PIVOT_HEIGHT, 2));
        moveXY(x, y);
    }

    public static void stopAll() {
        hardware.armExtendMotor.set(ControlMode.PercentOutput, 0);
        hardware.armPivotMotor.set(ControlMode.PercentOutput, 0);
    }

    public static double getWristAngle() {
        // 2071 ticks per 180 degrees
        return (hardware.wristPivotMotor.getSensorCollection().getQuadraturePosition() * 180.0 / 2071.0) - 90;
    }
}
