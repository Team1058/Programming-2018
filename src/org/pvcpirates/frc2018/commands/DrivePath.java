package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2018.util.PathCreator;

import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;

public class DrivePath extends Command {
	EncoderFollower l;
	EncoderFollower r;
	DriveForGyro df;
	public DrivePath(Waypoint[] waypoints){
		Trajectory[] trajs = PathCreator.genPath(waypoints);
		l = new EncoderFollower(trajs[0]);
		r = new EncoderFollower(trajs[1]);
		l.configureEncoder(Hardware.getInstance().leftDrive1.getSensorCollection().getQuadraturePosition(), 4096, .1254);
		r.configureEncoder(Hardware.getInstance().rightDrive1.getSensorCollection().getQuadraturePosition(), 4096, .1254);
		l.configurePIDVA(0, 0, 0, 0, 0);
		r.configurePIDVA(0, 0, 0, 0, 0);
	}
	
	@Override
	public void init() {
		double left = l.calculate(Hardware.getInstance().leftDrive1.getSensorCollection().getQuadraturePosition());
		double right = r.calculate(Hardware.getInstance().rightDrive1.getSensorCollection().getQuadraturePosition());
		double h = l.getHeading();
		df = new DriveForGyro(left,h);
		df.init();
		df.setPosition(left, right);
		setStatus(Status.EXEC);
	}

	@Override
	public void exec() {
		double left = l.calculate(Hardware.getInstance().leftDrive1.getSensorCollection().getQuadraturePosition());
		double right = r.calculate(Hardware.getInstance().rightDrive1.getSensorCollection().getQuadraturePosition());
		double h = l.getHeading();
		df.setPosition(left, right);
		df.setAngle(h);
		df.exec();
		if(l.isFinished())
			this.finished();
	}

	@Override
	public void finished() {
		// TODO Auto-generated method stub
		setStatus(Status.STOP);
		Drivetrain.stopAll();
		
	}
	
}
