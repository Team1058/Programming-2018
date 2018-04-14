package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.subsystems.Grabber;

public class CubeGrabber extends TeleopCommand {
	/*
	 * Teleop Command that manages intake functions
	 * 
	 */

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
        } else if (gamepad.getButton(GamepadEnum.Y_BUTTON)) {
        	//open intake
            Grabber.openGrabber();
        } else if (gamepad.getButton(GamepadEnum.A_BUTTON)) {
        	//spit cube
            Grabber.outtakeRollers();
        } else if (gamepad.getButton(GamepadEnum.X_BUTTON)){
        	//intake cube
        	Grabber.intakeRollers();
        	Grabber.noGrabber();
        }else if (gamepad.getButton(GamepadEnum.B_BUTTON)){
        	//slow spit
        	Grabber.outtakeRollersHalf();
        }else{
        	//If no input then stop
	        Grabber.stopRollers();
	        Grabber.noGrabber();
        }

    }
}
