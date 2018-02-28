package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.subsystems.Grabber;

public class SpitCube extends Command {

	SpitCube() {
	}
	@Override
	public void init() {
		
		setStatus(Status.EXEC);
	}

	@Override					  
	public void exec()				{
		Grabber.outtakeRollers();
		setStatus(Status.STOP);						  
									}

	@Override
	public void finished() {
		System.out.println("Cube");

	}
}
