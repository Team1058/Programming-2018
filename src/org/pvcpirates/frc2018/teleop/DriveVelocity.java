package org.pvcpirates.frc2018.teleop;

import com.ctre.phoenix.motorcontrol.ControlMode;

import static org.pvcpirates.frc2018.util.RobotMap.Constants.MAX_VELOCITY;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2018.util.GamepadHelper;

public class DriveVelocity extends TeleopCommand {
    //FIXME PID REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
    private static final double kP = 0;
    private static final double kI = 0;
    private static final double kD = 0;
    // (100% * 1023) / vel
    private static final double kF = .021717901;

    public DriveVelocity(BaseGamepad gp) {
        super(gp);
    }

    @Override
    public void exec() {
        Drivetrain.setPIDF(kP, kI, kD, kF);
        //ticks per 100 milliseconds
        double vel = MAX_VELOCITY * 1024 * 6;
        double fb = GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.LEFT_STICK_Y), .1);
        double lr = GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.RIGHT_STICK_X), .1);
        double lspd = fb - lr;
        double rspd = fb + lr;
        Drivetrain.setDrive(ControlMode.Velocity, vel * lspd, vel * rspd);
    }
}
