package org.pvcpirates.frc2018.gamepads;

public enum GamepadEnum {

    X_BUTTON(3),
    A_BUTTON(1),
    B_BUTTON(2),
    Y_BUTTON(4),
    LEFT_BUMPER(5),
    RIGHT_BUMPER(6),
    BACK_BUTTON(7),
    START_BUTTON(8),
    LEFT_STICK_PRESS(9),
    RIGHT_STICK_PRESS(10),
    DPAD_UP(1),
    DPAD_DOWN(2),
    DPAD_LEFT(3),
    DPAD_RIGHT(4),
    LEFT_STICK_Y(1),
    LEFT_STICK_X(0),
    LEFT_TRIGGER(2),
    RIGHT_TRIGGER(3),
    RIGHT_STICK_Y(5),
    RIGHT_STICK_X(4);

    int val;

    GamepadEnum(int val) {
        this.val = val;
    }

}
