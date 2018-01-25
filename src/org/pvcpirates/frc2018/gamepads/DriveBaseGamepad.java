package org.pvcpirates.frc2018.gamepads;

import org.pvcpirates.frc2018.commands.SampleCommand;
import org.pvcpirates.frc2018.gamepads.Button.ButtonAction;
import org.pvcpirates.frc2018.robot.Robot;

public class DriverGamepad extends BaseGamepad {

	public DriverGamepad(int port) {
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
                //commandFactory.generate(Robot.getInstance().drivetrain,5).cycle.exec();
            		sampCommand.executeCommand();
            }
        });
	}
	
	@Override
	public void executeActions() {
		super.executeActions();
        Robot.getInstance().drivetrain.setDrive(getAxis(GamepadEnum.LEFT_STICK_Y),getAxis(GamepadEnum.RIGHT_STICK_Y));
	}
	
}
