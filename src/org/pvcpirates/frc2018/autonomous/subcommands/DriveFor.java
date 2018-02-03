package org.pvcpirates.frc2018.autonomous.subcommands;

import org.pvcpirates.frc2018.autonomous.command.AutoCommand;
import org.pvcpirates.frc2018.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class DriveFor extends AutoSubCommand {

	private double goal;
	private Hardware hardware = Hardware.getInstance();
	
	public DriveFor(AutoCommand parent, double goal){
		super(parent);
		//6in per rotation times 256 encoder ticks per rotation
		this.goal = goal/6.0 * 256;
	}
	
	@Override
	public void exec() {
		super.exec();
		hardware.leftDrive1.set(ControlMode.Position,goal);
		hardware.rightDrive1.set(ControlMode.Position,goal);
	}
}
