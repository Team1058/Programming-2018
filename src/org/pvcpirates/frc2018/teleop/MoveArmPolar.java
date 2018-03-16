package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Arm;
import org.pvcpirates.frc2018.util.GamepadHelper;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class MoveArmPolar extends TeleopCommand {
	public static final double EXTEND_INCREMENT = -.5;
    public static final double PIVOT_INCREMENT = 3;
    double extend;
    double pivot = 90;
    public MoveArmPolar(BaseGamepad gp) {
        super(gp);
    }


    @Override
    public void exec() {
    	extend = Arm.getArmExtension();
        pivot = Arm.getPivotAngle();

        if (Math.abs(GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.LEFT_STICK_Y), .1)) > .1)
            extend = Arm.getArmExtensionClosedLoopTarget() + GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.LEFT_STICK_Y), .1) * EXTEND_INCREMENT;
        if (Math.abs(GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.RIGHT_STICK_Y), .1)) > .1)
            pivot = Arm.getPivotAngleClosedLoopTarget() + GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.RIGHT_STICK_Y), .1) * PIVOT_INCREMENT;
        
        if ((Math.abs(GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.RIGHT_STICK_Y), .1)) > .1)||(Math.abs(GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.LEFT_STICK_Y), .1)) > .1)){
        	Arm.moveArmPolar(extend, pivot);
        	Arm.levelWrist();
        	System.out.println("LEVELLLLLLLLLLLL");
        }
        
    }

}
