package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;

public abstract class TeleopCommand {

    public BaseGamepad gamepad;

    public TeleopCommand(BaseGamepad gp) {
        gamepad = gp;
    }

    public abstract void executeCommand();

}
