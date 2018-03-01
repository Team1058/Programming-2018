package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.SafeMoveArmPolarSetpoint;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;

public class MoveToSetpoint extends TeleopCommand {

    Command c = null;

    public MoveToSetpoint(BaseGamepad gp){
        super(gp);
    }

    @Override
    public void exec() {
        if (gamepad.getButton(GamepadEnum.A_BUTTON)) {
            //Intake Forward
            c = new SafeMoveArmPolarSetpoint(20, -60);
        } else if (gamepad.getButton(GamepadEnum.B_BUTTON)) {
            //Switch Forward
            c = new SafeMoveArmPolarSetpoint(0, -38);
        } else if (gamepad.getButton(GamepadEnum.X_BUTTON)) {
            //Scale Low Forward
            c = new SafeMoveArmPolarSetpoint(18, 72);
        } else if (gamepad.getButton(GamepadEnum.Y_BUTTON)) {
            //TODO
        } else if (gamepad.getDpad(GamepadEnum.DPAD_DOWN)) {
            //Intake Backward
            c = new SafeMoveArmPolarSetpoint(20, 240);
        } else if (gamepad.getDpad(GamepadEnum.DPAD_LEFT)) {
            //Switch Backward
            c = new SafeMoveArmPolarSetpoint(0, 218);
        } else if (gamepad.getDpad(GamepadEnum.DPAD_RIGHT)) {
            //Scale Low Backward
            c = new SafeMoveArmPolarSetpoint(18, 108);
        } else if (gamepad.getDpad(GamepadEnum.DPAD_UP)) {
            //TODO
        }



    }
}
