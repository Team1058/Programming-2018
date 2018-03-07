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
        double[] pid = new double[4];
        //SmartDashboard.getNumberArray("PID", pid);
        //FIXME PID VALS
        Hardware.setPIDF(16, 0, 0, 0, hardware.armPivotMotor);
        //Hardware.setPIDF(pid[0], pid[1], pid[2], pid[3], hardware.armPivotMotor);
        Hardware.setPIDF(.7, 0, 0, 0, hardware.armExtendMotor);
        Hardware.setPIDF(1.8, 0, 0, 0, hardware.wristPivotMotor);

    }

    public static void zeroArm() {

        hardware.armExtendMotor.set(ControlMode.Position, 0);
        hardware.armPivotMotor.set(ControlMode.Position, 0);
        hardware.wristPivotMotor.set(ControlMode.Position, THE_MIDDLE);
    }

    public static void levelWrist() {
        if (getPivotAngle() < 85)
            wristRotate(getPivotAngle());
        else if (getPivotAngle() > 95)
            wristRotate(getPivotAngle() - 180);
    }

    public static void wristRotate(double angleSetpoint) {
        angleSetpoint += 90;
        angleSetpoint = ((angleSetpoint * RobotMap.Ranges.WRIST_ENCODER_MAX / 180.0));
        hardware.wristPivotMotor.set(ControlMode.Position, angleSetpoint);
    }

    public static void extendArm(double distance) {
        //distance += 4;
        distance = (distance / (SPROCKET_DIAMETER * Math.PI)) * 4096;
        hardware.armExtendMotor.set(ControlMode.Position, distance);

    }

    public static double getArmExtension() {
        return (hardware.armExtendMotor.getSensorCollection().getQuadraturePosition() / 4096.0) * (SPROCKET_DIAMETER * Math.PI);
    }

    public static double getArmY() {
        return Math.sin(getPivotAngle()) * getArmExtension();
    }

    public static double getArmX() {
        double rad = Arm.getPivotAngle() * Math.PI / 180.0;
        double ext = Arm.getArmExtension();

        return Math.abs((Math.cos(rad) * (ext + 15)));
    }

    public static void pivotArm(double angleSetpoint) {
        //DEGREES WE CAN ROTATE
        angleSetpoint = (angleSetpoint * (512.0 / 180.0)) + 256;
        System.out.println(angleSetpoint);
        //if (angleSetpoint < RobotMap.Ranges.PIVOT_ENCODER_MAX && angleSetpoint > RobotMap.Ranges.PIVOT_ENCODER_MIN)
            hardware.armPivotMotor.set(ControlMode.Position, angleSetpoint);
    }

    public static double getPivotAngle() {
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
        //PIVOT TO TIP CALC

        double x = (Math.cos(angle*(Math.PI/180))*ext)+ Arm.getWristX(); //Arm.getArmX() + Arm.getWristX();


        if (x > PIVOT_TO_MAX_PERIM) {
            double radAngle = (Arm.getPivotAngle() > 90 ? 180 - Arm.getPivotAngle() : Arm.getPivotAngle()) * (Math.PI / 180);
            ext = ((PIVOT_TO_MAX_PERIM - Math.abs(Arm.getWristX())) / Math.cos(radAngle)) - 15;
            //System.out.println("Set to "+(PIVOT_TO_MAX_PERIM-Arm.getWristX()  )/(Math.cos(angle) )     );

            //System.out.println((((PIVOT_TO_MAX_PERIM-Math.abs(Arm.getWristX())) / Math.cos(radAngle))-15));
            Arm.extendArm(ext);
        }else{
            Arm.pivotArm(angle);
            Arm.extendArm(ext);
        }

        //System.out.println("Currently at "+Arm.getArmExtension());


        //if (x > PIVOT_TO_MAX_PERIM)
        //	Arm.pivotArm(Arm.getPivotAngle());
        //else

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
        // TODO Auto-generated method stub
        return (hardware.wristPivotMotor.getSensorCollection().getQuadraturePosition() * 180.0 / 2071.0) - 90;
    }


    //TODO SHOOT CUBE OVER ANOTHER CUBE ON THE SCALE
}
