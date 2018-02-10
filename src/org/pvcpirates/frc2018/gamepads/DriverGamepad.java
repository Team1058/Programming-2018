package org.pvcpirates.frc2018.gamepads;

import org.pvcpirates.frc2018.autonomous.commands.TurnToAngle;
import org.pvcpirates.frc2018.teleop.DriveOpenLoop;

public class DriverGamepad extends BaseGamepad {

	public DriverGamepad(int port) {
		super(port);
		// TODO: Do the concrete abstract methods get called by the super class constructor??
		//mapControlsToCommands();
	}
	
	void mapCommandsToController() {
		//teleopCommands.add(new DriveCommand(this));
		//teleopCommands.add(new DriveOpenLoop(this));
	}
    

}
