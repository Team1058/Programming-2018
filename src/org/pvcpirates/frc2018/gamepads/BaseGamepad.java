package org.pvcpirates.frc2018.gamepads;

import edu.wpi.first.wpilibj.Joystick;

import java.util.Vector;

public abstract class BaseGamepad extends Joystick{
    public static enum ButtonTypes{
        BUTTON,DPAD,TRIGGER
    }
    public class Button{
        int buttonNum;
        ButtonTypes buttonType;
    }
    public Vector<ButtonListener> buttonListeners;

    public BaseGamepad(int port) {
        super(port);
        this.buttonListeners = new Vector<>();
    }

    public static abstract class ButtonListener{
        public Button button = null;
        public abstract Button setButton();
        public abstract void execute();

        public void check(BaseGamepad gamepad){
            if (button == null)
                button = setButton();
            else if (gamepad.getButton(button))
                execute();
        }
    }
    public void addListener(ButtonListener buttonListener){
        buttonListeners.add(buttonListener);
    }
    public boolean getButton(Button button){
        switch (button.buttonType){
            case BUTTON:return getBooper(button.buttonNum);
            case DPAD:return getDpad(button.buttonNum);
            case TRIGGER:return getTrigger(button.buttonNum);
            default:return  getBooper(button.buttonNum);
        }
    }
    public abstract boolean getBooper(int buttonNum);
    public abstract double getAxis(int axisNum);
    public abstract boolean getTrigger(int TriggerNum);
    public abstract boolean getDpad(int dPadNum);

}
