package org.pvcpirates.frc2018.gamepads;

import edu.wpi.first.wpilibj.Joystick;

public class Gamepad extends BaseGamepad {
    //TODO: Button Scheduler so that way we dont have to rely on wpilib gross commands
    public static final class  GamepadPorts{
        public enum Buttons{
            X_BUTTON,
            A_BUTTON,
            B_BUTTON,
            Y_BUTTON,
            LEFT_BUMPER,
            RIGHT_BUMPER,
            BACK_BUTTON,
            START_BUTTON

        }
    }

    public Gamepad(int port) {
        super(port);
    }

    @Override
    public boolean getBooper(int buttonNum) {
        return false;
    }

    @Override
    public double getAxis(int axisNum) {
        return 0;
    }

    @Override
    public boolean getTrigger(int TriggerNum) {
        return false;
    }

    @Override
    public boolean getDpad(int dPadNum) {
        return false;
    }
}
