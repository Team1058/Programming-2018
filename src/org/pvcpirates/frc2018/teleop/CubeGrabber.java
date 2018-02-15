package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;

import org.pvcpirates.frc2018.robot.subsystems.Grabber;

public class CubeGrabber extends TeleopCommand{




	public CubeGrabber(BaseGamepad gp) {
		super(gp);
	}
	

	@Override
	public void exec() {
		if(gamepad.getButton(GamepadEnum.A_BUTTON)){
			Grabber.intakeRollers();
			Grabber.openGrabber();
		}else if(gamepad.getButton(GamepadEnum.X_BUTTON)){
			Grabber.outtakeRollers();
			//openGrabber();
		}else if(gamepad.getButton(GamepadEnum.B_BUTTON)){
			Grabber.holdRollers();
		}else{
			Grabber.stopRollers();
			Grabber.closeGrabber();
		}
	}
}
