package org.pvcpirates.frc2018.commands;

public class DriveAndPivot extends Command{
	
	
	public DriveAndPivot(double distance, double armAngle, double wristAngle){
		parallel = true;
		commands.add(new DriveFor(distance));
		commands.add(new PivotArm(armAngle));
		commands.add(new PivotWrist(wristAngle));
	}
}
