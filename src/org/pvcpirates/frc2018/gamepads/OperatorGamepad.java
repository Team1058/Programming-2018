package org.pvcpirates.frc2018.gamepads;

import org.pvcpirates.frc2018.teleop.CubeGrabber;

public class OperatorGamepad extends BaseGamepad {

    public OperatorGamepad(int port) {
        super(port);
        // TODO: Do the concrete abstract methods get called by the super class constructor??
        //mapControlsToCommands();
    }

    @Override
    void mapCommandsToController() {
    	teleopCommands.add(new CubeGrabber(this));
    }
}
