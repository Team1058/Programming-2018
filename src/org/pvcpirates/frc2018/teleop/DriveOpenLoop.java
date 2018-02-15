package org.pvcpirates.frc2018.teleop;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2018.util.GamepadHelper;

public class DriveOpenLoop extends TeleopCommand {

    public DriveOpenLoop(BaseGamepad gp) {
        super(gp);
    }

    @Override
    public void exec() {
    	 double fb = GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.LEFT_STICK_Y),.1);
         double lr = GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.RIGHT_STICK_X),.1);
         double lspd = fb - lr;
         double rspd = fb + lr;
         Drivetrain.setDrive(ControlMode.PercentOutput,lspd ,-rspd);
    }

}