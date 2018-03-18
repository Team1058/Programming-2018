package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.commands.MoveArmPolarSetpoint;
import org.pvcpirates.frc2018.commands.ZeroArm;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.VJoyEnum;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class MoveToExtraSetpoints extends TeleopCommand {

	MoveArmPolarSetpoint moveSetpoint = null;
	ZeroArm zeroArm = new ZeroArm();
	
	public MoveToExtraSetpoints(BaseGamepad gp) {
		super(gp);
		moveSetpoint = new MoveArmPolarSetpoint();
	}
	
	@Override
	public void exec(){
		if(gamepad.getButton(VJoyEnum.SPACE)){
			//post scale scoring
			moveSetpoint.set(0, 90, 0);
		}else if(gamepad.getButton(VJoyEnum.H)){
			//intake front
			moveSetpoint.set(20, -60);
		}else if(gamepad.getButton(VJoyEnum.V)){
			//intake back
			moveSetpoint.set(20, 240);
		}else if(gamepad.getButton(VJoyEnum.LMENU)){
			//switch front
			moveSetpoint.set(0, -10);
		}else if(gamepad.getButton(VJoyEnum.LCONTROL)){
			//starting front
			moveSetpoint.set(0, 83);
		}else if(gamepad.getButton(VJoyEnum.LEFTSHIFTKEY)){
			//scale ready front
			moveSetpoint.set(22, 83);
		}else if(gamepad.getButton(VJoyEnum.TAB)){
			//scale high front
			moveSetpoint.set(31, 83,20);
		}else if(gamepad.getButton(VJoyEnum.O)){
			//scale high back
			moveSetpoint.set(31, 97,-20);
		}else if(gamepad.getButton(VJoyEnum.G)){
			//scale ready back
			moveSetpoint.set(22, 97);
		}else if(gamepad.getButton(VJoyEnum.OEMCOMMA)){
			//starting back
			moveSetpoint.set(0, 83);
		}else if(gamepad.getButton(VJoyEnum.OEMPERIOD)){
			//switch back
			moveSetpoint.set(0, 190);
		}else if(gamepad.getButton(VJoyEnum.VOLUMEDOWN)){
			//climb set
			moveSetpoint.set(34, 75, -90);
		}else if(gamepad.getButton(VJoyEnum.VOLUMEUP)){
			//climb up
			moveSetpoint.set(8, 75, -90);
		}else if(gamepad.getButton(VJoyEnum.ESCAPE)){
			if (zeroArm.getStatus() == Status.STOP)
        		zeroArm = new ZeroArm();        	
        	zeroArm.exec();
        	moveSetpoint.set(0, 90,-90);
		}else if (moveSetpoint.getStatus() == Status.EXEC){
        	moveSetpoint.exec();
        }
		
		if(gamepad.getButton(VJoyEnum.VOLUMEMUTE)){
			//climb ready
			//moveSetpoint.set(34, 95, -90);
			Arm.wristRotate(-90);
		}
		
		
	}

}
