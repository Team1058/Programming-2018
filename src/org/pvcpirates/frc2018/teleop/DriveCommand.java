package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.Robot;

public class DriveCommand extends TeleopCommand {

	public DriveCommand(BaseGamepad gp) {
		super(gp);
	}

	@Override
	public void executeCommand() {
        Robot.getInstance().drivetrain.setDrive(gamepad.getAxis(GamepadEnum.LEFT_STICK_Y),gamepad.getAxis(GamepadEnum.RIGHT_STICK_Y));
	}

}
