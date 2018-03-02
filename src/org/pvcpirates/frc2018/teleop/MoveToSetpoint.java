package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.commands.SafeMoveArmPolarSetpoint;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class MoveToSetpoint extends TeleopCommand {

    SafeMoveArmPolarSetpoint moveSetpoint = null;
    double wrist;
    public MoveToSetpoint(BaseGamepad gp){
        super(gp);
        moveSetpoint = new SafeMoveArmPolarSetpoint();
        
    }

    @Override
    public void exec() {
    	Arm.levelWrist();
        if (gamepad.getButton(GamepadEnum.A_BUTTON)) {
            //Intake Forward
            moveSetpoint.set(20, -60);
        } else if (gamepad.getButton(GamepadEnum.B_BUTTON)) {
            //Switch Forward
            moveSetpoint.set(0, -38);
        } else if (gamepad.getButton(GamepadEnum.X_BUTTON)) {
            //Scale Low Forward
            moveSetpoint.set(18, 72);
            System.out.println("X");
        } else if (gamepad.getButton(GamepadEnum.Y_BUTTON)) {
            //Scale High Front
        	moveSetpoint.set(30.8, 83,60);
        } else if (gamepad.getDpad(GamepadEnum.DPAD_DOWN)) {
            //Intake Backward
            moveSetpoint.set(20, 240);
        } else if (gamepad.getDpad(GamepadEnum.DPAD_LEFT)) {
            //Switch Backward
            moveSetpoint.set(0, 218);
        } else if (gamepad.getDpad(GamepadEnum.DPAD_RIGHT)) {
            //Scale Low Backward
            moveSetpoint.set(18, 108);
        } else if (gamepad.getDpad(GamepadEnum.DPAD_UP)) {
        	//Scale High Backward
            moveSetpoint.set(30.8, 97,-60);
        }else if (moveSetpoint.getStatus() == Status.EXEC){
        	moveSetpoint.exec();
        }


    }
}
