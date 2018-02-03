package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;

import org.pvcpirates.frc2018.robot.subsystems.Grabber;

public class CubeGrabber extends TeleopCommand{



    Grabber grabberController = new Grabber();
	public CubeGrabber(BaseGamepad gp) {
		super(gp);
	}
	

	@Override
	public void executeCommand() {
		if(gamepad.getButton(GamepadEnum.A_BUTTON)){
			grabberController.intakeRollers();
			grabberController.openGrabber();
		}else if(gamepad.getButton(GamepadEnum.X_BUTTON)){
			grabberController.outtakeRollers();
			//openGrabber();
		}else if(gamepad.getButton(GamepadEnum.B_BUTTON)){
			grabberController.holdRollers();
		}else{
			grabberController.stopRollers();
			grabberController.closeGrabber();
		}
	}
}
