package org.pvcpirates.frc2018.gamepads;

import org.pvcpirates.frc2018.teleop.ArmOpenLoop;
import org.pvcpirates.frc2018.teleop.CubeGrabber;
import org.pvcpirates.frc2018.teleop.MoveArmPolar;

public class OperatorGamepad extends BaseGamepad {

    public OperatorGamepad(int port) {
        super(port);
    }

    @Override
    void mapCommandsToController() {
        teleopCommands.add(new CubeGrabber(this));
        teleopCommands.add(new MoveArmPolar(this));
    }
}
