package org.pvcpirates.frc2018.gamepads;

import org.pvcpirates.frc2018.commands.SampleCommand;
import org.pvcpirates.frc2018.gamepads.Button.ButtonAction;

public class OperatorGamepad extends BaseGamepad {
	
	public OperatorGamepad(int port) {
		super(port);
		// TODO: Do the concrete abstract methods get called by the super class constructor??
		//mapControlsToCommands();
	}
	
	void mapControlsToCommands() {
        addListener(new ButtonAction() {
        		SampleCommand sampCommand = new SampleCommand();
            @Override
            public Button setButton() {
                return new Button(GamepadEnum.A_BUTTON, Button.ButtonTypes.BUTTON);
            }

            @Override
            public void execute() {
            		sampCommand.executeCommand();
            }
        });
	}
}
