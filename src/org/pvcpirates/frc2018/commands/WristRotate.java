package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.subsystems.Arm;
import org.pvcpirates.frc2018.robot.subsystems.Grabber;

public class WristRotate extends Command {

	double degrees;
	public WristRotate(double degrees) {
		this.degrees = degrees;
	}

	@Override
	public void init() {
		setStatus(Status.EXEC);
	}

	@Override
	public void exec() {
		//Hold whatever we have
		Grabber.holdRollers();
		Grabber.closeGrabber();
		//rotate
		Arm.wristRotate(degrees);
		
		this.setStatus(Status.STOP);
	}

	@Override
	public void finished() {
		
	}
	

}
