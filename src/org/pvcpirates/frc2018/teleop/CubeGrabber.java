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
        if (gamepad.getButton(GamepadEnum.RIGHT_BUMPER )) {
        	//Hold a cube
        	Grabber.holdRollers();
        	Grabber.closeGrabber();
        } else if (gamepad.getTrigger(GamepadEnum.RIGHT_TRIGGER)) {
            Grabber.openGrabber();
        } else if (gamepad.getButton(GamepadEnum.START_BUTTON)) {
            Grabber.outtakeRollers();
        } else if (gamepad.getButton(GamepadEnum.BACK_BUTTON)){
        	Grabber.intakeRollers();
        	Grabber.noGrabber();
        }else{
        	//If no input then stop
	        Grabber.stopRollers();
	        Grabber.noGrabber();
        }

    }
}
