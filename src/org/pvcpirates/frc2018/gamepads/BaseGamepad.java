package org.pvcpirates.frc2018.gamepads;

import edu.wpi.first.wpilibj.Joystick;
import org.pvcpirates.frc2018.teleop.TeleopCommand;

import java.util.Vector;

public abstract class BaseGamepad extends Joystick {

    public Vector<TeleopCommand> teleopCommands;

    public BaseGamepad(int port) {
        super(port);
        this.teleopCommands = new Vector<TeleopCommand>();
        mapCommandsToController();
    }

    abstract void mapCommandsToController();


    public void executeCommands() {
        for (TeleopCommand command : teleopCommands) {
            command.executeCommand();
        }
    }

    public boolean getPressable(Button button) {
        switch (button.buttonType) {
            case BUTTON:
                return getButton(button.buttonEnum);
            case DPAD:
                return getDpad(button.buttonEnum);
            case TRIGGER:
                return getTrigger(button.buttonEnum);
            default:
                return getButton(button.buttonEnum);
        }
    }

    public boolean getButton(GamepadEnum buttonEnum) {
        return getRawButton(buttonEnum.val);
    }

    public double getAxis(GamepadEnum axisEnum) {
        return getRawAxis(axisEnum.val);
    }

    public boolean getTrigger(GamepadEnum triggerEnum) {
        return (getRawAxis(triggerEnum.val) > .8);
    }

    public boolean getDpad(GamepadEnum dPadEnum) {
        switch (dPadEnum) {
            case DPAD_DOWN:
                return (getPOV(0) == 180);
            case DPAD_UP:
                return (getPOV(0) == 0);
            case DPAD_LEFT:
                return (getPOV(0) <= 315) || (getPOV(0) >= 225);
            case DPAD_RIGHT:
                return (getPOV(0) <= 135) || (getPOV(0) >= 45);
        }
        return false;
    }

}
