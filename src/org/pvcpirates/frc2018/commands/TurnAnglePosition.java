package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;

public class TurnAnglePosition extends Command {

	double angle;
	Command drive;
	public TurnAnglePosition(double angle) {
		this.angle = angle;
	}
	@Override
	public void init() {
		double feet =((angle/360.0)*(20.96*Math.PI));
		drive = new DriveFor(feet,true);
		drive.init();
		//pid must be after reeee
		Drivetrain.setPIDF(.1, 0.0, 0, 0);
		Hardware.setPIDF(0.07, 0, 0, 0, Hardware.getInstance().leftDrive1);
	}
	@Override
	public void exec() {		
	}
	@Override
	public void finished() {
		drive.finished();
	}
	
	
}
