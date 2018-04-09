package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.subsystems.Arm;
import org.pvcpirates.frc2018.robot.subsystems.Grabber;

public class SpitCube extends Command {
	public static enum SPEEDS{
		FULL,HALF,QUARTER,ZERO;
	}
	SPEEDS speed;
	boolean open;
	public SpitCube() {
		speed = SPEEDS.QUARTER;
		open = true;
	}
	
	public SpitCube(SPEEDS speed,boolean open){
		this.speed = speed;
		this.open = open;
	}
	@Override
	public void init() {
		
		setStatus(Status.EXEC);
	}

	@Override					  
	public void exec()				{
		//if (Arm.getWristAngle()>80){
		switch(speed){
		case ZERO:Grabber.stopRollers();break;
		case QUARTER:Grabber.outtakeRollersQuarter();break;
		case HALF:Grabber.outtakeRollersHalf();break;
		case FULL:Grabber.outtakeRollers();break;
		}
		if (open)
			Grabber.openGrabber();
		setStatus(Status.STOP);		
		//}
		
									}

	@Override
	public void finished() {
		System.out.println("Cube");

	}
}
