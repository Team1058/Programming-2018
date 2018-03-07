package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.commands.SafeMoveArmPolarSetpoint;
import org.pvcpirates.frc2018.commands.ZeroArm;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class MoveToSetpoint extends TeleopCommand {

    SafeMoveArmPolarSetpoint moveSetpoint = null;
    ZeroArm zeroArm = new ZeroArm();
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
            moveSetpoint.set(22, -60);
        } else if (gamepad.getButton(GamepadEnum.B_BUTTON)) {
            //Switch Forward
            moveSetpoint.set(0, -10);
        } else if (gamepad.getButton(GamepadEnum.X_BUTTON)) {
            //Scale Low Forward
            moveSetpoint.set(18, 72);
        } else if (gamepad.getButton(GamepadEnum.Y_BUTTON)) {
            //Scale High Front
        	moveSetpoint.set(31, 83,60);
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
            moveSetpoint.set(31, 97,-60);
        }else if(gamepad.getTrigger(GamepadEnum.LEFT_TRIGGER)){
        	zeroArm.exec();
        }else if (moveSetpoint.getStatus() == Status.EXEC){
        	moveSetpoint.exec();
        	Arm.running = true;
        }else{
        	Arm.running = false;
        }


    }
}