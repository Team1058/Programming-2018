package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.commands.MoveArmPolarSetpoint;
import org.pvcpirates.frc2018.commands.ZeroArm;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;

public class MoveToSetpoint extends TeleopCommand {

    MoveArmPolarSetpoint moveSetpoint = null;
    ZeroArm zeroArm = new ZeroArm();
    double wrist;
    public MoveToSetpoint(BaseGamepad gp){
        super(gp);
        moveSetpoint = new MoveArmPolarSetpoint();
    }

    @Override
    public void exec() {
    	//Level the wrist after a setpoint is hit
        if (gamepad.getButton(GamepadEnum.A_BUTTON)) {
            //Intake Forward
            moveSetpoint.set(22, -60);
        } else if (gamepad.getButton(GamepadEnum.B_BUTTON)) {
            //Switch Forward
            moveSetpoint.set(0, -10);
        } else if (gamepad.getButton(GamepadEnum.X_BUTTON)) {
            //Scale Low Forward
            moveSetpoint.set(18, 72);
        } else if (gamepad.getButton(GamepadEnum.Y_BUTTON)) {
            //Scale High Front
        	moveSetpoint.set(31, 83,20);
        } else if (gamepad.getDpad(GamepadEnum.DPAD_DOWN)) {
            //Intake Backward
            moveSetpoint.set(22, 240);
        } else if (gamepad.getDpad(GamepadEnum.DPAD_LEFT)) {
            //Switch Backward
            moveSetpoint.set(0, 190);
        } else if (gamepad.getDpad(GamepadEnum.DPAD_RIGHT)) {
            //Scale Low Backward
            moveSetpoint.set(18, 108);
        } else if (gamepad.getDpad(GamepadEnum.DPAD_UP)) {
        	//Scale High Backward
            moveSetpoint.set(31, 97,-20);
        }else if(gamepad.getTrigger(GamepadEnum.LEFT_TRIGGER)){
        	if (zeroArm.getStatus() == Status.STOP)
        		zeroArm = new ZeroArm();        	
        	zeroArm.exec();
        	
        }else if (moveSetpoint.getStatus() == Status.EXEC){
        	moveSetpoint.exec();
        }
    }
}
