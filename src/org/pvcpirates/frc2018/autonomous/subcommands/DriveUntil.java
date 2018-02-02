package org.pvcpirates.frc2018.autonomous.subcommands;

import org.pvcpirates.frc2018.autonomous.AutoSubCommand;
import org.pvcpirates.frc2018.autonomous.command.AutoCommand;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.controllers.Drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class DriveUntil extends AutoSubCommand {
	
	private double goal;
	private Drivetrain drivetrain = Robot.getInstance().drivetrain;
	private Hardware hardware = Hardware.getInstance();
	
	public DriveUntil(AutoCommand parent, double goal){
		super(parent);
		//6in per rotation times 256 encoder ticks per rotation
		this.goal = goal/6.0 * 256;
	}
	
	@Override
	public void init(){
	}
	
	@Override
	public void exec() {
		hardware.leftDrive1.set(ControlMode.Position,goal);
		hardware.rightDrive1.set(ControlMode.Position,goal);
	}
    

}
