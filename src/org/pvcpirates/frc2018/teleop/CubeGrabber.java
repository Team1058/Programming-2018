package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;

import org.pvcpirates.frc2018.robot.subsystems.Grabber;

public class CubeGrabber extends TeleopCommand{



    Grabber grabber = new Grabber();
	public CubeGrabber(BaseGamepad gp) {
		super(gp);
	}
	

	@Override
	public void executeCommand() {
		if(gamepad.getButton(GamepadEnum.A_BUTTON)){
			grabber.intakeRollers();
			grabber.openGrabber();
		}else if(gamepad.getButton(GamepadEnum.Y_BUTTON)){
			grabber.outtakeRollers();
		}else{
			grabber.holdRollers();
			grabber.closeGrabber();
		}
	}
}
