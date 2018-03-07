package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.subsystems.Arm;
import org.pvcpirates.frc2018.robot.subsystems.Grabber;

public class SpitCube extends Command {

	public SpitCube() {
	}
	@Override
	public void init() {
		
		setStatus(Status.EXEC);
	}

	@Override					  
	public void exec()				{
		if (Arm.getWristAngle()>80){
		Grabber.outtakeRollers();
		Grabber.openGrabber();
		setStatus(Status.STOP);		
		}
		
									}

	@Override
	public void finished() {
		System.out.println("Cube");

	}
}
