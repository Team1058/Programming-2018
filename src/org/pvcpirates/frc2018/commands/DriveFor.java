package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.util.RobotMap.Constants;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;


public class DriveFor extends Command {
    private double rInches;
    private double lInches;
    private double encTicksR;
    private double encTicksL;
    private Hardware h = Hardware.getInstance();

    public DriveFor(double inches) {
        this.rInches = inches;
        this.lInches = inches;
        
    }
    public DriveFor(double rInches, double lInches){
    	this.rInches = rInches;
        this.lInches = lInches;
    }
    

    public DriveFor(){}
    int cntr;
    int direction;
    @Override
    public void init() {
    	while (Hardware.getInstance().leftDrive1.getSensorCollection().getQuadraturePosition()!=0){
    		Hardware.getInstance().leftDrive1.getSensorCollection().setQuadraturePosition(0, 10);
    		Hardware.getInstance().rightDrive1.getSensorCollection().setQuadraturePosition(0, 10);
    	}
        Drivetrain.setPIDF(.022, 0.0, 0, 0);
        Hardware.setPIDF(0.027625, 0, 0, 0, h.leftDrive1);
    	//Drivetrain.setPIDF(0, 0.0, 0, 1);
        
        //Drops 1/2 inch per foot
        encTicksR = (rInches / (6 * Math.PI)) * 1024 * (11.25);
        encTicksL = (rInches / (6 * Math.PI)) * 1024 * (11.25);
        //encTicks+=h.leftDrive1.getSensorCollection().getQuadraturePosition();
        h.leftDrive1.getSensorCollection().setQuadraturePosition(0, Constants.ROBOT_TIMEOUT);
        h.rightDrive1.getSensorCollection().setQuadraturePosition(0, Constants.ROBOT_TIMEOUT);
        //Manually change instead of super.init() b/c there is no command list
        
        h.rightDrive1.configClosedloopRamp(0, 10);
        h.leftDrive1.configClosedloopRamp(0, 10);
        h.leftDrive1.configPeakOutputForward(.75, 0);
        h.rightDrive1.configPeakOutputForward(.72, 0);
        h.leftDrive1.configPeakOutputReverse(-.75, 0);
        h.rightDrive1.configPeakOutputReverse(-.72, 0);
        setStatus(Status.EXEC);
        direction = (int) (Math.abs(rInches)/rInches);
        
    }

    @Override
    public void exec() {
    	if(this.status != Status.STOP){
	    	boolean rInRange = false;
	    	boolean lInRange = false;
	    	double rEnc = h.rightDrive1.getSensorCollection().getQuadraturePosition();
	    	double lEnc = h.leftDrive1.getSensorCollection().getQuadraturePosition();
	    	
	    	
	    	
			h.leftDrive1.set(ControlMode.Position, -encTicksR);
	    	h.rightDrive1.set(ControlMode.Position, encTicksL);
	    	//h.leftDrive1.set(ControlMode.Velocity, direction*300);
	    	//h.rightDrive1.set(ControlMode.Velocity, direction*-300);
	    	
	    	
	    	if(direction == -1){
	    		rInRange = (rEnc < encTicksR + 1500); 
		    	lInRange = (lEnc < encTicksL + 1500); 
	    	}else{
	    		rInRange = (rEnc > encTicksR - 1500); 
		    	lInRange = (lEnc > encTicksL - 1500);
	    	}
	        if(rInRange||lInRange){
	        	setStatus(Status.STOP);
	        	this.finished();
	        }
    	}
    }

    @Override
    public void finished() {
    	h.rightDrive1.configClosedloopRamp(0, 10);
        h.leftDrive1.configClosedloopRamp(0, 10);
        Drivetrain.stopAll();
    }

}
