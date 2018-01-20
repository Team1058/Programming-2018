package org.pvcpirates.frc2018.gamepads;

import edu.wpi.first.wpilibj.Joystick;

import java.util.Vector;

public abstract class BaseGamepad extends Joystick{
    //Seperate file?
    public static enum ButtonTypes{
        BUTTON,DPAD,TRIGGER
    }
    //Move to seperate file?
    public static class Button{
        GamepadEnum buttonEnum;
        ButtonTypes buttonType;

        public Button(GamepadEnum buttonEnum, ButtonTypes buttonType) {
            this.buttonEnum = buttonEnum;
            this.buttonType = buttonType;
        }
    }
    public Vector<ButtonAction> buttonActions;

    public BaseGamepad(int port) {
        super(port);
        this.buttonActions = new Vector<>();
    }

    public static abstract class ButtonAction {
        public Button button = null;
        public abstract Button setButton();
        public abstract void execute();

        public void check(BaseGamepad gamepad){
            if (button == null)
                button = setButton();
            else if (gamepad.getPressable(button))
                execute();
        }
    }

    public void addListener(ButtonAction buttonAction){
        buttonActions.add(buttonAction);
    }

    public boolean getPressable(Button button){
        switch (button.buttonType){
            case BUTTON:return getButton(button.buttonEnum);
            case DPAD:return getDpad(button.buttonEnum);
            case TRIGGER:return getTrigger(button.buttonEnum);
            default:return  getButton(button.buttonEnum);
        }
    }
    public abstract boolean getButton(GamepadEnum buttonEnum);
    public abstract double getAxis(GamepadEnum axisEnum);
    public abstract boolean getTrigger(GamepadEnum triggerEnum);
    public abstract boolean getDpad(GamepadEnum dPadEnum);

}
