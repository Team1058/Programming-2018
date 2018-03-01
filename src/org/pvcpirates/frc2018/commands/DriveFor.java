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
    private boolean turn;
    private Hardware h = Hardware.getInstance();

    public DriveFor(double inches) {
        this.inches = inches;
        turn =false;
    }
    public DriveFor(double inches, boolean turn){
    	this.turn = turn;
    	this.inches = inches;
    }

    @Override
    public void init() {
        //FIXME TUNE PID
        Drivetrain.setPIDF(.022, 0.0, 0, 0);
        Hardware.setPIDF(0.027625, 0, 0, 0, h.leftDrive1);
        encTicks = (inches / (6 * Math.PI)) * 1024 * (17.3);
        //encTicks+=h.leftDrive1.getSensorCollection().getQuadraturePosition();
        h.leftDrive1.getSensorCollection().setQuadraturePosition(0, ROBOT_TIMEOUT);
        h.rightDrive1.getSensorCollection().setQuadraturePosition(0, ROBOT_TIMEOUT);
        //Manually change instead of super.init() b/c there is no command list
        
        h.rightDrive1.configClosedloopRamp(.2, 10);
        h.leftDrive1.configClosedloopRamp(.2, 10);
        
        setStatus(Status.EXEC);
        
    }

    @Override
    public void exec() {
    	int sign = turn?-1:1;
    		h.leftDrive1.set(ControlMode.Position, encTicks);
        	h.rightDrive1.set(ControlMode.Position, sign*-encTicks);
        	System.out.println("left "+h.leftDrive1.getSensorCollection().getQuadraturePosition());
        	System.out.println("right "+h.rightDrive1.getSensorCollection().getQuadraturePosition());
        	System.out.println("Target "+encTicks);
        if((h.leftDrive1.getSensorCollection().getQuadraturePosition() > encTicks - 150 && h.leftDrive1.getSensorCollection().getQuadraturePosition() < encTicks + 150)
        		&&(h.rightDrive1.getSensorCollection().getQuadraturePosition() > encTicks - 150 && h.rightDrive1.getSensorCollection().getQuadraturePosition() < encTicks + 150)){
        	setStatus(Status.STOP);
        }
    }

    @Override
    public void finished() {
    	h.rightDrive1.configClosedloopRamp(0, 10);
        h.leftDrive1.configClosedloopRamp(0, 10);
        Drivetrain.stopAll();
    }

}
