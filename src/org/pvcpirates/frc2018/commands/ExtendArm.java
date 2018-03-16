package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class ExtendArm extends Command {
	double inches;
	public ExtendArm(double inches) {
		// TODO Auto-generated constructor stub
		this.inches = inches;
	}
	@Override
	public void exec() {
		// TODO Auto-generated method stub
		Arm.extendArm(inches);
		System.out.println("Extend Arm");
		if (Arm.getArmExtension() > inches-2);
			this.setStatus(Status.STOP);
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		this.setStatus(Status.EXEC);		
	}
	@Override
	public void finished() {
		// TODO Auto-generated method stub
		this.setStatus(Status.STOP);
	}
	
	

}
