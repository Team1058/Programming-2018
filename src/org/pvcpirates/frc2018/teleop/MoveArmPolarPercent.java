package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Arm;
import org.pvcpirates.frc2018.util.GamepadHelper;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class MoveArmPolarPercent extends TeleopCommand {

	public MoveArmPolarPercent(BaseGamepad gp) {
		super(gp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec() {
		 if (Math.abs(GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.LEFT_STICK_Y), .1)) > .1)
	            Hardware.getInstance().armExtendMotor.set(ControlMode.PercentOutput, GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.LEFT_STICK_Y), .1)); 
	     if (Math.abs(GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.RIGHT_STICK_Y), .1)) > .1)
	    	 Hardware.getInstance().armExtendMotor.set(ControlMode.PercentOutput, GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.RIGHT_STICK_Y), .1));
	        
	        if (!Arm.running&&!((Math.abs(GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.RIGHT_STICK_Y), .1)) > .1)||(Math.abs(GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.LEFT_STICK_Y), .1)) > .1))){
	        	Arm.extendArm(Arm.getArmExtension());
	        	Arm.pivotArm(Arm.getPivotAngle());
	        }
	}
	

}
