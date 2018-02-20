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
        double fb = gamepad.getAxis(GamepadEnum.LEFT_STICK_Y);
        double lr = gamepad.getAxis(GamepadEnum.RIGHT_STICK_X);
        
        double lspd = fb - lr;
        double rspd = fb + lr;
        
        
        System.out.println("Left: "+lspd+"\tRight: "+rspd);
        System.out.println("L1: "+h.leftDrive1.getMotorOutputPercent());
        Drivetrain.setDrive(ControlMode.PercentOutput, lspd, rspd);
    }

}
