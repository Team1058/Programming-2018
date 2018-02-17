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

    public static void zeroArm() {
        hardware.armExtendMotor.set(ControlMode.Position, 0);
        hardware.armPivotMotor.set(ControlMode.Position, 0);
        hardware.wristPivotMotor.set(ControlMode.Position, THE_MIDDLE);
    }

    public static void levelWrist() {
        double pivotAngle = getPivotAngle();
        double suppliment = 180 - pivotAngle;
        hardware.wristPivotMotor.set(ControlMode.Position, (suppliment / 360) * 1024);

    }

    public static void wristRotate(double angleSetpoint) {
        if (angleSetpoint < RobotMap.Ranges.WRIST_ENCODER_MAX && angleSetpoint < RobotMap.Ranges.WRIST_ENCODER_MIN)
            hardware.wristPivotMotor.set(ControlMode.Position, angleSetpoint);
    }

    public static void extendArm(double distance) {
        if (distance < RobotMap.Constants.ARM_DISTANCE && distance >= 0) {
            distance = (distance / (SPROCKET_DIAMETER * Math.PI)) * 1024;
            hardware.armExtendMotor.set(ControlMode.Position, distance);
        }

    }

    public static double getArmExtension() {
        return (hardware.armPivotMotor.getSensorCollection().getQuadraturePosition() / 1024) / (SPROCKET_DIAMETER * Math.PI);
    }

    public static double getArmY() {
        return Math.sin(getPivotAngle()) * getArmExtension();
    }

    public static double getArmX() {
        return Math.cos(getPivotAngle()) * getArmExtension();
    }

    public static void pivotArm(double angleSetpoint) {
        //DEGREES WE CAN ROTATE
        angleSetpoint = (1019 * (angleSetpoint / 270) + 5);
        if (angleSetpoint < RobotMap.Ranges.PIVOT_ENCODER_MAX && angleSetpoint < RobotMap.Ranges.PIVOT_ENCODER_MIN)
            hardware.armPivotMotor.set(ControlMode.Position, angleSetpoint);
    }

    public static double getPivotAngle() {
        return ((hardware.armPivotMotor.getSelectedSensorPosition(0) - 5) / 1019) * 270;
    }

    public static void moveXY(double x, double y) {
        double angle;
        double hyp;
        //Make sure where within the perimeter either side
        if (Math.abs(x) > PIVOT_TO_MAX_PERIM) {
            x = PIVOT_TO_MAX_PERIM*(Math.abs(x)/x);
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
