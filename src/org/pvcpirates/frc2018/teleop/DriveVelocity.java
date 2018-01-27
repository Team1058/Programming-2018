package org.pvcpirates.frc2018.teleop;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.Robot;

public class DriveVelocity extends TeleopCommand {
    public static final double kP = 0;
    public static final double kI = 0;
    public static final double kD = .15;
    public static final double kF = 0;

    public DriveVelocity(BaseGamepad gp) {
        super(gp);
    }

    @Override
    public void executeCommand() {
        Robot.getInstance().drivetrain.setPIDF(kP, kI, kD, kF);

        double vel = 9.2 * 512 * 1000;
        double fb = Math.abs(gamepad.getAxis(GamepadEnum.LEFT_STICK_Y));
        double lr = Math.abs(gamepad.getAxis(GamepadEnum.RIGHT_STICK_X));
        double lspd = fb - lr;
        double rspd = fb + lr;

        Robot.getInstance().drivetrain.setDrive(ControlMode.Velocity, vel * lspd, vel + rspd);
    }
}
