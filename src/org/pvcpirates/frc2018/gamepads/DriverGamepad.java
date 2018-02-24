package org.pvcpirates.frc2018.gamepads;

import org.pvcpirates.frc2018.teleop.DriveOpenLoop;

public class DriverGamepad extends BaseGamepad {

    public DriverGamepad(int port) {
        super(port);
    }

    void mapCommandsToController() {
        //teleopCommands.add(new DriveCommand(this));
        //teleopCommands.add(new DriveOpenLoop(this));
        teleopCommands.add(new DriveOpenLoop(this));
    }


}
