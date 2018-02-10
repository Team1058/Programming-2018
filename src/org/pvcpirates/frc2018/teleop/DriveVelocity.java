package org.pvcpirates.frc2018.teleop;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.util.GamepadHelper;

public class DriveVelocity extends TeleopCommand {
    public static final double kP = .15;
    public static final double kI = 0;
    public static final double kD = 0;
    public static final double kF = 0;

    public DriveVelocity(BaseGamepad gp) {
        super(gp);
    }

    @Override
    public void executeCommand() {
        Robot.getInstance().drivetrain.setPIDF(kP, kI, kD, kF);
        //ticks per 100 milliseconds
        double vel = 9.2 * 512 * 10;
        double fb = GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.LEFT_STICK_Y),.1);
        double lr = GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.RIGHT_STICK_X),.1);
        double lspd = fb + lr;
        double rspd = fb - lr;
        Robot.getInstance().drivetrain.setDrive(ControlMode.Velocity, vel * lspd, vel * rspd);
        SmartDashboard.putNumber("rspd",vel*rspd);
        SmartDashboard.putNumber("lspd",vel*lspd);
    }
}
