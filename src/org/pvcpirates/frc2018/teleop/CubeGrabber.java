package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.subsystems.Grabber;

public class CubeGrabber extends TeleopCommand {


    public CubeGrabber(BaseGamepad gp) {
        super(gp);
    }

    boolean closed = false;
    @Override
    public void exec() {
        if (gamepad.getButton(GamepadEnum.A_BUTTON)) {
            Grabber.intakeRollers();
            
        } else if (gamepad.getButton(GamepadEnum.Y_BUTTON)) {
            Grabber.outtakeRollers();
        } else if (gamepad.getButton(GamepadEnum.X_BUTTON)) {
            Grabber.holdRollers();
        } else if(gamepad.getButton(GamepadEnum.RIGHT_BUMPER)){
        	Grabber.openGrabber();
            closed = false;
        } else if (!closed){
        	closed = true;
            Grabber.stopRollers();
            Grabber.closeGrabber();
        }
        
    }
}
