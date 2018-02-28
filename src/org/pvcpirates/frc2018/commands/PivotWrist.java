package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class PivotWrist extends Command {

	double angle;
	
	PivotWrist(double angle){
		this.angle = angle;
	}
	
	@Override
	public void init(){
		setStatus(Status.EXEC);
	}
	
	@Override
	public void exec(){
		Arm.wristRotate(angle);
		setStatus(Status.STOP);
	}
	
	@Override
	public void finished(){
		
	}
	
}
