package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.subsystems.Grabber;

public class CubeGrabber extends TeleopCommand {


    boolean closed = false;

    public CubeGrabber(BaseGamepad gp) {
        super(gp);
    }

    @Override
    public void exec() {
        if (gamepad.getButton(GamepadEnum.B_BUTTON)) {
            Grabber.holdRollers();
        } else if (gamepad.getButton(GamepadEnum.START_BUTTON)) {
            Grabber.openGrabber();
        } else if (gamepad.getButton(GamepadEnum.RIGHT_TRIGGER)) {
            Grabber.outtakeRollers();
        }

    }
}
