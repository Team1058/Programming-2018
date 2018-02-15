package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;

public abstract class TeleopCommand extends Command {

    public BaseGamepad gamepad;
    public Hardware hardware = Robot.getInstance().hardware;
    public TeleopCommand(BaseGamepad gp) {
        gamepad = gp;
    }


}
