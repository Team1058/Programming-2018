package org.pvcpirates.frc2018.gamepads;

import org.pvcpirates.frc2018.teleop.CubeGrabber;
import org.pvcpirates.frc2018.teleop.MoveArmPolarPercent;
import org.pvcpirates.frc2018.teleop.MoveArmPolar;
import org.pvcpirates.frc2018.teleop.MoveToSetpoint;

public class OperatorGamepad extends BaseGamepad {

    public OperatorGamepad(int port) {
        super(port);
    }

    @Override
    void mapCommandsToController() {
        teleopCommands.add(new CubeGrabber(this));
        //Manual Movement
        teleopCommands.add(new MoveArmPolar(this));
        //Old Setpoints
        //teleopCommands.add(new MoveToSetpoint(this));
       
    }
}
