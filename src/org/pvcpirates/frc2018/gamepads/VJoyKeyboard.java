package org.pvcpirates.frc2018.gamepads;

import org.pvcpirates.frc2018.teleop.MoveToExtraSetpoints;

public class VJoyKeyboard extends BaseGamepad {

	public VJoyKeyboard(int port) {
		super(port);
	}

	@Override
	void mapCommandsToController() {
		teleopCommands.add(new MoveToExtraSetpoints(this));
	}

}
