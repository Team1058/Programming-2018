package org.pvcpirates.frc2018.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import static org.pvcpirates.frc2018.util.RobotMap.Constants.ROBOT_TIMEOUT;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;

public class DriveForGyro extends Command{
    private double inches;
    private double encTicks;
    private double start;
    private boolean init;
    private double maxOutput =.75;
double direction;
Hardware h = Hardware.getInstance();
    public DriveForGyro(double inches) {
        this.inches = inches;
    }


    @Override
    public void init() {
        //FIXME TUNE PID
        Drivetrain.setPIDF(.022, 0.0, 0, 0);
        Hardware.setPIDF(0.027625, 0, 0, 0, h.leftDrive1);
        encTicks = (inches / (6 * Math.PI)) * 1024 * (11.25);
        h.leftDrive1.getSensorCollection().setQuadraturePosition(0, ROBOT_TIMEOUT);
        h.rightDrive1.getSensorCollection().setQuadraturePosition(0, ROBOT_TIMEOUT);
        //Manually change instead of super.init() b/c there is no command list

        h.rightDrive1.configClosedloopRamp(0, 10);
        h.leftDrive1.configClosedloopRamp(0, 10);
        init = false;
        setStatus(Status.EXEC);
        direction = (int) (Math.abs(inches)/inches);
    }

    @Override
    public void exec() {
    	if (this.status != Status.STOP){
	    	double rEnc = h.rightDrive1.getSensorCollection().getQuadraturePosition();
	    	double lEnc = h.leftDrive1.getSensorCollection().getQuadraturePosition();
	        double leftOutput = .5;
	        double rightOutput = .5;
	        //Balancing constant
	        if (!init){
	        	start =h.navx.getAngle();
	        	init = true;
	        }
	        double Kp = .00003;
	        double KGp = .05;
	        double current = h.navx.getAngle();
	        
	        leftOutput = Kp*(encTicks-lEnc);
	       rightOutput = Kp*(encTicks+rEnc);
	       
	       if(leftOutput>maxOutput){
	    	   leftOutput = maxOutput;
	  
	       }
	       if(leftOutput<-maxOutput){
	    	   
	    	   leftOutput = -maxOutput;
	       }
	       if(rightOutput>maxOutput){
	    	   rightOutput = maxOutput;
	  
	       }
	       if(rightOutput<-maxOutput){
	    	   
	    	   rightOutput = -maxOutput;
	       }
	       Drivetrain.setDrive(ControlMode.PercentOutput,-(leftOutput- KGp*(start+current)), rightOutput + KGp*(start+current));
	       
	       //Drivetrain.setDrive(ControlMode.PercentOutput,-(-KGp*(start+current)), KGp*(start+current));
	        boolean rInRange = false;
	    	boolean lInRange = false;
	    	
	        if(direction == -1){
	    		rInRange = (rEnc < encTicks + 1500); 
		    	lInRange = (lEnc < encTicks + 1500); 
	    	}else{
	    		rInRange = (rEnc > encTicks - 1500); 
		    	lInRange = (lEnc > encTicks - 1500);
	    	}
	        if(rInRange||lInRange){
	        	System.out.println("REEEEEEEEEEEEEEEEEEEEEEEEEEEE");
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
