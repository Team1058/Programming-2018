package org.pvcpirates.frc2018.gamepads;

import edu.wpi.first.wpilibj.Joystick;

public class Gamepad extends BaseGamepad {

    public Gamepad(int port) {
        super(port);
    }

    @Override
    public boolean getButton(GamepadEnum buttonEnum) {
        return getRawButton(buttonEnum.val);
    }

    @Override
    public double getAxis(GamepadEnum axisEnum) {
        return getRawAxis(axisEnum.val);
    }

    @Override
    public boolean getTrigger(GamepadEnum triggerEnum) {
        return (getRawAxis(triggerEnum.val) > .8);
    }

    @Override
    public boolean getDpad(GamepadEnum dPadEnum) {
        switch (dPadEnum){
            case DPAD_DOWN:return (getPOV(0) == 180);
            case DPAD_UP:return (getPOV(0) == 0);
            case DPAD_LEFT:return (getPOV(0) <= 315) || (getPOV(0) >= 225);
            case DPAD_RIGHT:return (getPOV(0) <= 135) || (getPOV(0) >= 45);
        }
        return false;
    }
}
