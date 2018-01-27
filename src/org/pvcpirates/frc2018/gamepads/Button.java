package org.pvcpirates.frc2018.gamepads;

public class Button {
	
    public static enum ButtonTypes{
        BUTTON,DPAD,TRIGGER
    }

    GamepadEnum buttonEnum;
    ButtonTypes buttonType;

    public Button(GamepadEnum buttonEnum, ButtonTypes buttonType) {
        this.buttonEnum = buttonEnum;
        this.buttonType = buttonType;
    }

    public abstract class ButtonAction {
        public Button button = null;
        public BaseGamepad gamepad;
        public abstract Button setButton();
        public abstract void execute();
        public void setGamepad(BaseGamepad gp) {
        	  gamepad = gp;
        }
        public void check(){
            if (button == null)
                button = setButton();
            else if (gamepad.getPressable(button))
                execute();
        }
    }
}
