package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.robot.Status;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class PivotArm extends Command {
	
	double angle;
	boolean sequential;
	public PivotArm(double angle){
		this.angle = angle;
		setStatus(Status.INIT);
		sequential = false;
	}
	public PivotArm(double angle,boolean sequential){
		this.angle = angle;
		setStatus(Status.INIT);
		this.sequential = sequential;
	}
	@Override
	public void init(){
		setStatus(Status.EXEC);
		
	}
	
	@Override
	public void exec(){
		Arm.pivotArm(angle);
		if (sequential||Arm.getPivotAngle() > angle-3
				&& Arm.getPivotAngle() < angle+3 )
		setStatus(Status.STOP);
	}
	
	@Override
	public void finished(){
	}

}
