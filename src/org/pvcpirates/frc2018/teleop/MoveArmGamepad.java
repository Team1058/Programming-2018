package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;

public class MoveArmGamepad extends TeleopCommand {
    private static final double MOVE_INCREMENT = 1;
    public MoveArmGamepad(BaseGamepad gp) {
        super(gp);
    }

    @Override
    public void exec() {

    }
}
