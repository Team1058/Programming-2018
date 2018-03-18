package org.pvcpirates.frc2018.teleop;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2018.util.GamepadHelper;

public class DriveOpenLoop extends TeleopCommand {

    Hardware h = Hardware.getInstance();

    public DriveOpenLoop(BaseGamepad gp) {
        super(gp);
    }

    @Override
    public void exec() {
        double fb = GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.LEFT_STICK_Y), .15);
        double lr = GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.RIGHT_STICK_X), .15);

        double lspd = fb - lr;
        double rspd = fb + lr;
        if (gamepad.getButton(GamepadEnum.LEFT_BUMPER))
        	Drivetrain.setDrive(ControlMode.PercentOutput, lspd*.5, -rspd*.5);
        else if (gamepad.getButton(GamepadEnum.RIGHT_BUMPER))
        	Drivetrain.setDrive(ControlMode.PercentOutput, lspd, -rspd);
        else
        	Drivetrain.setDrive(ControlMode.PercentOutput, lspd*.85, -rspd*.85);
    }

}
