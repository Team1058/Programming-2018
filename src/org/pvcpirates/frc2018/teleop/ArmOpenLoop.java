package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class ArmOpenLoop extends TeleopCommand{

	Hardware h = Hardware.getInstance();
	
	public ArmOpenLoop(BaseGamepad gp) {
		super(gp);
	}
	
	@Override
	public void exec(){
		h.armPivotMotor.set(ControlMode.PercentOutput, gamepad.getAxis(GamepadEnum.LEFT_STICK_Y));
		h.armExtendMotor.set(ControlMode.PercentOutput, gamepad.getAxis(GamepadEnum.LEFT_STICK_X));
		h.wristPivotMotor.set(ControlMode.PercentOutput, gamepad.getAxis(GamepadEnum.RIGHT_STICK_Y));
	}

}
