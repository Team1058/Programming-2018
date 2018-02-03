package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.pvcpirates.frc2018.robot.controllers.GrabberController;

public class CubeGrabber extends TeleopCommand{



    GrabberController grabberController = new GrabberController();
	public CubeGrabber(BaseGamepad gp) {
		super(gp);
	}
	

	@Override
	public void executeCommand() {
		if(gamepad.getButton(GamepadEnum.A_BUTTON)){
			grabberController.intakeRollers();
			grabberController.openGrabber();
		}else if(gamepad.getButton(GamepadEnum.X_BUTTON)){
			grabberController.outtakeRollers();
			//openGrabber();
		}else if(gamepad.getButton(GamepadEnum.B_BUTTON)){
			grabberController.holdRollers();
		}else{
			grabberController.stopRollers();
			grabberController.closeGrabber();
		}
	}
}
