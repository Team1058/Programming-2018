package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.subsystems.Arm;
import org.pvcpirates.frc2018.robot.subsystems.Grabber;

public class WristRotate extends Command {

	double degrees;
	boolean sequential;
	public WristRotate(double degrees) {
		this.degrees = degrees;
		sequential = false;
	}
	public WristRotate(double degrees,boolean sequential) {
		this.degrees = degrees;
		this.sequential = sequential;
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
		if (sequential||(Arm.getWristAngle() > degrees-3 && Arm.getWristAngle() < degrees+3 ))
			this.setStatus(Status.STOP);
	}

	@Override
	public void finished() {
		
	}
	

}
