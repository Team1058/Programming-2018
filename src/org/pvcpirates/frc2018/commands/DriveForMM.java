package org.pvcpirates.frc2018.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

import static org.pvcpirates.frc2018.util.RobotMap.Constants.ROBOT_TIMEOUT;

import org.pvcpirates.frc2018.robot.Status;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;


public class DriveForMM extends Command {
    private double rInches;
    private double lInches;
    private double encTicksR;
    private double encTicksL;
    private Hardware h = Hardware.getInstance();

    public DriveForMM(double inches) {
        this.rInches = inches;
        this.lInches = inches;
        
    }
    public DriveForMM(double rInches, double lInches){
    	this.rInches = rInches;
        this.lInches = lInches;
    }
    

    public DriveForMM(){}
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
        h.leftDrive1.getSensorCollection().setQuadraturePosition(0, ROBOT_TIMEOUT);
        h.rightDrive1.getSensorCollection().setQuadraturePosition(0, ROBOT_TIMEOUT);
        //Manually change instead of super.init() b/c there is no command list
        
        h.rightDrive1.configClosedloopRamp(0, 10);
        h.leftDrive1.configClosedloopRamp(0, 10);
        h.leftDrive1.configPeakOutputForward(.666, 0);
        h.rightDrive1.configPeakOutputForward(.666, 0);
        h.leftDrive1.configPeakOutputReverse(-.666, 0);
        h.rightDrive1.configPeakOutputReverse(-.666, 0);
        
        h.leftDrive1.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 20, 20);
        h.rightDrive1.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 20, 20);
        
        
        h.leftDrive1.configMotionCruiseVelocity(5750, 10);
        h.rightDrive1.configMotionCruiseVelocity(5750, 10);
        
        h.leftDrive1.configMotionAcceleration(7000, 10);
        h.rightDrive1.configMotionAcceleration(7000, 10);
        
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
	    	
			h.leftDrive1.set(ControlMode.MotionMagic, -encTicksL);
	    	h.rightDrive1.set(ControlMode.MotionMagic, encTicksR);
	    	//h.leftDrive1.set(ControlMode.Velocity, direction*300);
	    	//h.rightDrive1.set(ControlMode.Velocity, direction*-300);
	    	if(direction == -1){
	    		rInRange = (-rEnc < encTicksR + 1500); 
		    	lInRange = (lEnc < encTicksL + 1500); 
	    	}else{
	    		rInRange = (-rEnc > encTicksR - 1500); 
		    	lInRange = (lEnc > encTicksL - 1500);
	    	}

	    	
	        if((rInRange&&lInRange)){
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
