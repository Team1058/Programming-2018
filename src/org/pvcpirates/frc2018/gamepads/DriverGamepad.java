package org.pvcpirates.frc2018.gamepads;

import org.pvcpirates.frc2018.commands.SampleCommand;
import org.pvcpirates.frc2018.gamepads.Button.ButtonAction;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.teleop.DriveCommand;

public class DriverGamepad extends BaseGamepad {

	public DriverGamepad(int port) {
		super(port);
		// TODO: Do the concrete abstract methods get called by the super class constructor??
		//mapControlsToCommands();
	}
	
	void mapCommandsToController() {
		teleopCommands.add(new DriveCommand(this));
	}

}