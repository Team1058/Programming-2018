package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.subsystems.Arm;
import org.pvcpirates.frc2018.util.GamepadHelper;

public class MoveArmPolar extends TeleopCommand {
	
	public static final double EXTEND_INCREMENT = 10;
	public static final double PIVOT_INCREMENT = 40;
	double extend;
	double pivot;
	public MoveArmPolar(BaseGamepad gp) {
		super(gp);
	}
	
	@Override
	public void exec(){
		
		if (Math.abs(GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.LEFT_STICK_Y),.1))>.1 )
			extend = Arm.getArmExtension() + GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.LEFT_STICK_Y),.1) * EXTEND_INCREMENT;
		if (Math.abs(GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.RIGHT_STICK_Y),.1))>.1)
			pivot = Arm.getPivotAngle() +  GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.RIGHT_STICK_Y),.1) * PIVOT_INCREMENT;
		System.out.println("Extend:  "+extend);
		//Arm.pivotArm(pivot);
		//Arm.extendArm(extend);
		Arm.moveArmPolar(extend, pivot);
		//Arm.levelWrist();
	}

}
