package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.MoveArmPerimeter;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;

public class MoveArmSetpoints extends TeleopCommand {
    private MoveArmPerimeter currCommand = null;
    private double setpoint;
    boolean run;
    public MoveArmSetpoints(BaseGamepad gp) {
        super(gp);
    }

    @Override
    public void exec() {
        run= true;
        if (gamepad.getButton(GamepadEnum.A_BUTTON))
            setpoint = 40;
        else if(gamepad.getButton(GamepadEnum.B_BUTTON))
            setpoint = 10;
        else
            run = false;

        if (run){
            if (currCommand.getStatus() == Status.EXEC)
                currCommand.finished();
            else if (currCommand.height != setpoint)
                currCommand = new MoveArmPerimeter(setpoint);
            currCommand.exec();
        }

    }
}
