package org.pvcpirates.frc2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.RobotMap;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;

import static org.pvcpirates.frc2018.RobotMap.Constants.*;
import static org.pvcpirates.frc2018.RobotMap.Ranges.THE_MIDDLE;

public class Arm extends BaseSubsystem {
    private static Hardware hardware = Robot.getInstance().hardware;


    public static void configurePID() {
		Hardware.setPIDF(0, 0, 0, 0, hardware.armPivotMotor);
		Hardware.setPIDF(0, 0, 0, 0, hardware.armExtendMotor);
		Hardware.setPIDF(0, 0, 0, 0, hardware.wristPivotMotor);

	}

	public static void zeroArm(){
        hardware.armExtendMotor.set(ControlMode.Position,0);
        hardware.armPivotMotor.set(ControlMode.Position,0);
        hardware.wristPivotMotor.set(ControlMode.Position,THE_MIDDLE);
    }

    public static void levelWrist() {
        double pivotAngle = getPivotAngle();
        double suppliment = 180 - pivotAngle;
        hardware.wristPivotMotor.set(ControlMode.Position, (suppliment / 360) * 1024);

    }

    public static void wristRotate(double angleSetpoint) {
        if (angleSetpoint < RobotMap.Ranges.WRIST_MAX && angleSetpoint < RobotMap.Ranges.WRIST_MIN)
            hardware.wristPivotMotor.set(ControlMode.Position, angleSetpoint);
    }

    public static void extendArm(double distance) {
        if (distance < RobotMap.Constants.ARM_DISTANCE && distance >= 0) {
            //FIXME MAKE THIS A CONSTANT
            distance = (distance / (1.751 * Math.PI)) * 1025;
            hardware.armExtendMotor.set(ControlMode.Position, distance);
        }

    }

    public static double getArmExtension() {
        //FIXME THESE MAGIC NUMBERS
        return (hardware.armPivotMotor.getSensorCollection().getQuadraturePosition() / 4096) / (1.751 * Math.PI);
    }

    public static double getArmY() {
        return Math.sin(getPivotAngle()) * getArmExtension();
    }

    public static double getArmX() {
        return Math.cos(getPivotAngle()) * getArmExtension();
    }

    public static void pivotArm(double angleSetpoint) {
        //FIXME MAGIC NUMBERS REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
        angleSetpoint = (1019 * (angleSetpoint / 270) + 5);
        if (angleSetpoint < RobotMap.Ranges.POTENTIOMETER_MAX && angleSetpoint < RobotMap.Ranges.POTENTIOMETER_MIN)
            hardware.armPivotMotor.set(ControlMode.Position, angleSetpoint);
    }

    public static double getPivotAngle() {
        return ((hardware.armPivotMotor.getSelectedSensorPosition(0) - 5) / 1019) * 270;
    }

    public static void moveXY(double x, double y) {
        double angle;
        double hyp;
        //DONT RUN THE ARM INTO THE GROUND
        if (y < 0)
            y = 25;
        //FIXME ENUM
        //Distance from ground to pivot
        y -= 38;
        angle = Math.atan2(y, x);
        hyp = y / Math.sin(angle);
        if (x > PIVOT_TO_MAX_PERIM) {
            pivotArm(angle);
            extendArm(hyp);
        }
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


    //TODO SHOOT CUBE OVER ANOTHER CUBE ON THE SCALE
}
