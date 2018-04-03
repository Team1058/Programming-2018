package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.commands.MoveArmPerimeter;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;

public class MoveArmSetpoints extends TeleopCommand {
    boolean run;
    private MoveArmPerimeter currCommand = null;
    private double setpoint;

    public MoveArmSetpoints(BaseGamepad gp) {
        super(gp);
    }

    @Override
    public void exec() {
//        run = true;
//        //FIXME SETPOINTS NEED TO BE DID
//        if (gamepad.getButton(GamepadEnum.A_BUTTON))
//            setpoint = 40;
//        else if (gamepad.getButton(GamepadEnum.B_BUTTON))
//            setpoint = 10;
//        else
//            run = false;
//
//        if (run) {
//            if (currCommand.getStatus() == Status.EXEC)
//                currCommand.finished();
//            else if (currCommand.height != setpoint)
//                currCommand = new MoveArmPerimeter(setpoint);
//            currCommand.exec();
//        }

    }
}
