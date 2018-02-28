package org.pvcpirates.frc2018.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;

import static org.pvcpirates.frc2018.RobotMap.Constants.ROBOT_TIMEOUT;


public class DriveFor extends Command {
    private double inches;
    private double encTicks;


    public DriveFor(double inches) {
        this.inches = inches;
    }

    @Override
    public void init() {
        //FIXME TUNE PID
        Drivetrain.setPIDF(.022, 0.0, 0, 0);
        Hardware.setPIDF(0.027625, 0, 0, 0, Robot.getInstance().hardware.leftDrive1);
        encTicks = (inches / (6 * Math.PI)) * 1024 * (17.3);
        Robot.getInstance().hardware.leftDrive1.getSensorCollection().setQuadraturePosition(0, ROBOT_TIMEOUT);
        Robot.getInstance().hardware.rightDrive1.getSensorCollection().setQuadraturePosition(0, ROBOT_TIMEOUT);
        //Manually change instead of super.init() b/c there is no command list
        
        Robot.getInstance().hardware.rightDrive1.configClosedloopRamp(.2, 10);
        Robot.getInstance().hardware.leftDrive1.configClosedloopRamp(.2, 10);
        
        setStatus(Status.EXEC);
    }

    @Override
    public void exec() {
        Robot.getInstance().hardware.leftDrive1.set(ControlMode.Position, encTicks);
        Robot.getInstance().hardware.rightDrive1.set(ControlMode.Position, -encTicks);
        if(Robot.getInstance().hardware.leftDrive1.getSensorCollection().getQuadraturePosition() > encTicks - 500){
        	setStatus(Status.STOP);
        }
    }

    @Override
    public void finished() {
    	Robot.getInstance().hardware.rightDrive1.configClosedloopRamp(0, 10);
        Robot.getInstance().hardware.leftDrive1.configClosedloopRamp(0, 10);
        Drivetrain.stopAll();
    }

}
