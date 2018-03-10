package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.subsystems.Arm;
import org.pvcpirates.frc2018.robot.subsystems.Grabber;

public class CubeGrab extends Command {

	public CubeGrab() {
	}
	
	
	@Override
	public void init(){
		setStatus(Status.EXEC);
	}
		
	@Override
	public void exec() {
	  if (Arm.getWristAngle()<80) {
		Grabber.intakeRollers();
		Grabber.closeGrabber();
		setStatus(Status.STOP);
	  while (Arm.getWristAngle()<80){
		commands.add(new DriveFor(6));	
	    } 
	  }
	}
			
	@Override
	public void finished() {				
	  System.out.println("Grabbed");
	}		
			

}