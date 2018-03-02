package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.subsystems.Grabber;

public class CubeGrabber extends TeleopCommand {


    boolean toggle = false;

    public CubeGrabber(BaseGamepad gp) {
        super(gp);
    }
    
    @Override
    public void exec() {
        if (gamepad.getButton(GamepadEnum.BACK_BUTTON )) {
        	if(!toggle){
	            Grabber.holdRollers();
	            Grabber.closeGrabber();
	            toggle = true;
        	}else{
        		toggle = false;
        	}
        } else if (gamepad.getButton(GamepadEnum.START_BUTTON)) {
            Grabber.openGrabber();
        } else if (gamepad.getTrigger(GamepadEnum.RIGHT_TRIGGER)) {
            Grabber.outtakeRollers();
        } else if (gamepad.getButton(GamepadEnum.RIGHT_BUMPER)){
        	Grabber.intakeRollers();
        	Grabber.noGrabber();
        }else{
        	if (toggle){
        		Grabber.holdRollers();
                Grabber.closeGrabber();
        	}else{	
	        	Grabber.stopRollers();
	        	Grabber.noGrabber();
        	}
        }

    }
}
