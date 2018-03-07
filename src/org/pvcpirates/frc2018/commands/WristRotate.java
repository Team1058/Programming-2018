package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.subsystems.Arm;
import org.pvcpirates.frc2018.robot.subsystems.Grabber;

public class WristRotate extends Command {

	double degrees;
	public WristRotate(double degrees) {
		// TODO Auto-generated constructor stub
		this.degrees = degrees;
	}

	@Override
	public void init() {
		setStatus(Status.EXEC);
	}

	@Override
	public void exec() {
		// TODO Auto-generated method stub
		Arm.wristRotate(degrees);
		Grabber.holdRollers();
		Grabber.closeGrabber();
		this.setStatus(Status.STOP);
	}

	@Override
	public void finished() {
		// TODO Auto-generated method stub
		
	}
	

}
