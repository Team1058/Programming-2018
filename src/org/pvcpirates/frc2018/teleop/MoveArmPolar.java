package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.subsystems.Arm;
import org.pvcpirates.frc2018.util.GamepadHelper;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class MoveArmPolar extends TeleopCommand {

    double extend;
    double pivot = 90;
    public MoveArmPolar(BaseGamepad gp) {
        super(gp);
    }


    @Override
    public void exec() {
    	//Move arm using percent output (as well as apply a deadband)
       hardware.armExtendMotor.set(ControlMode.PercentOutput, GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.LEFT_STICK_Y), .1)); 
       hardware.armPivotMotor.set(ControlMode.PercentOutput, GamepadHelper.applyDeadBand(gamepad.getAxis(GamepadEnum.RIGHT_STICK_Y), .1));
        
        
       extend = Arm.getArmExtension();
       pivot = Arm.getPivotAngle();
       //Hold the current position of the arm
       Arm.moveArmPolar(extend, pivot);
    }

}
