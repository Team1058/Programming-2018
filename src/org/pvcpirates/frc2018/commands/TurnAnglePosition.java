package org.pvcpirates.frc2018.commands;

public class TurnAnglePosition extends Command {

	double angle;
	Command drive;
	public TurnAnglePosition(double angle) {
		this.angle = angle;
	}
	@Override
	public void init() {
		double feet =((angle/360.0)*(23.0*Math.PI))*2.0;
		drive = new DriveFor(feet,true);
		drive.init();
	}
	@Override
	public void exec() {
		drive.exec();
	}
	@Override
	public void finished() {
		drive.finished();
	}
	
	
}
