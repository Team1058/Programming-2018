package org.pvcpirates.frc2018.robot.subsystems;

import static org.pvcpirates.frc2018.RobotMap.Constants.ARM_DISTANCE;
import static org.pvcpirates.frc2018.RobotMap.Constants.GROUND_TO_PIVOT;
import static org.pvcpirates.frc2018.RobotMap.Constants.MAX_ARM_HEIGHT;
import static org.pvcpirates.frc2018.RobotMap.Constants.PIVOT_HEIGHT;
import static org.pvcpirates.frc2018.RobotMap.Constants.PIVOT_TO_MAX_PERIM;
import static org.pvcpirates.frc2018.RobotMap.Constants.SPROCKET_DIAMETER;
import static org.pvcpirates.frc2018.RobotMap.Ranges.THE_MIDDLE;

import org.pvcpirates.frc2018.RobotMap;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Arm extends BaseSubsystem {
    private static Hardware hardware = Robot.getInstance().hardware;


    public static void configurePID() {
    	double[] pid = new double[4];
    	//SmartDashboard.getNumberArray("PID", pid);
        //FIXME PID VALS
        Hardware.setPIDF(16, 0, 0, 0, hardware.armPivotMotor);
    	//Hardware.setPIDF(pid[0], pid[1], pid[2], pid[3], hardware.armPivotMotor);
        Hardware.setPIDF(1, 0, 0, 0, hardware.armExtendMotor);
        Hardware.setPIDF(1, 0, 0, 0, hardware.wristPivotMotor);

    }

    public static void zeroArm() {
    	
        hardware.armExtendMotor.set(ControlMode.Position, 0);
        hardware.armPivotMotor.set(ControlMode.Position, 0);
        hardware.wristPivotMotor.set(ControlMode.Position, THE_MIDDLE);
    }

    public static void levelWrist() {
       
    }

    public static void wristRotate(double angleSetpoint) {
    	angleSetpoint = -1 * angleSetpoint * 2111.0 / 180.0;
    	hardware.wristPivotMotor.set(ControlMode.Position, angleSetpoint);
    }

    public static void extendArm(double distance) {
    	distance = (distance / (SPROCKET_DIAMETER * Math.PI)) * 1024;
        if (distance < RobotMap.Ranges.ARM_EXTEND_ENCODER_MAX && distance > RobotMap.Ranges.ARM_EXTEND_ENCODER_MIN)
            hardware.armExtendMotor.set(ControlMode.Position, distance);

    }

    public static double getArmExtension() {
        return (hardware.armPivotMotor.getSensorCollection().getQuadraturePosition() / 1024.0) / (SPROCKET_DIAMETER * Math.PI);
    }

    public static double getArmY() {
        return Math.sin(getPivotAngle()) * getArmExtension();
    }

    public static double getArmX() {
        return Math.cos(getPivotAngle()) * getArmExtension();
    }

    public static void pivotArm(double angleSetpoint) {
        //DEGREES WE CAN ROTATE
        angleSetpoint = (angleSetpoint*(512.0/180.0))+256;
        if (angleSetpoint < RobotMap.Ranges.PIVOT_ENCODER_MAX && angleSetpoint > RobotMap.Ranges.PIVOT_ENCODER_MIN)
            hardware.armPivotMotor.set(ControlMode.Position, angleSetpoint);
    }
 
    public static double getPivotAngle() {
    	return (hardware.armPivotMotor.getSensorCollection().getAnalogIn() * (180.0/512.0))-90;
    }

    //min -425
    //max 929
    //horiz 757
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

	public static double getWristAngle() {
		// TODO Auto-generated method stub
		return (hardware.wristPivotMotor.getSensorCollection().getQuadraturePosition() * 180.0/ 2071.0)-90;
	}


    //TODO SHOOT CUBE OVER ANOTHER CUBE ON THE SCALE
}
