package org.pvcpirates.frc2018.autonomous;

import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.controllers.Drivetrain;

public class DriveUntil extends AutoSubCommand{

	private double currentPos;
	private Drivetrain drivetrain = Robot.getInstance().drivetrain;
	private Hardware hardware = Hardware.getInstance();
	
	public DriveUntil(AutoCommand parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	public DriveUntil(AutoCommand parent, double inches){
		super(parent);
	}
	
	@Override
	public void init(){
		currentPos = 
	}

}
