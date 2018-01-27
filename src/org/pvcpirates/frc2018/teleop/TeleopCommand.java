package org.pvcpirates.frc2018.teleop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.PriorityQueue;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;

public abstract class TeleopCommand {

	public BaseGamepad gamepad;
	
	public TeleopCommand(BaseGamepad gp) {
		gamepad = gp;
	}

	public abstract void executeCommand();
	
}
