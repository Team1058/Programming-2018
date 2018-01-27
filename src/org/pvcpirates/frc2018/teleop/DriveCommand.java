package org.pvcpirates.frc2018.teleop;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.Robot;

public class DriveCommand extends TeleopCommand {

    public DriveCommand(BaseGamepad gp) {
        super(gp);
    }

    @Override
    public void executeCommand() {
        Robot.getInstance().drivetrain.setDrive(ControlMode.PercentOutput, gamepad.getAxis(GamepadEnum.LEFT_STICK_Y), -gamepad.getAxis(GamepadEnum.RIGHT_STICK_Y));
    }

}
