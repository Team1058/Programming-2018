package org.pvcpirates.frc2018.state;

import org.pvcpirates.frc2018.commands.CommandFactory;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.Gamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.controllers.Drivetrain;

public class TeleopState extends State{
    private Gamepad gamepad = new Gamepad(0);
    private Gamepad gamepad2 = new Gamepad(1);
    @Override
    public void init() {
        CommandFactory commandFactory = new CommandFactory();
        gamepad.addListener(new BaseGamepad.ButtonAction() {
            @Override
            public BaseGamepad.Button setButton() {
                return new BaseGamepad.Button(GamepadEnum.A_BUTTON, BaseGamepad.ButtonTypes.BUTTON);
            }

            @Override
            public void execute() {
                commandFactory.generate(Robot.getInstance().drivetrain,5).cycle.exec();
            }
        });
    }

    @Override
    public void exec() {
        for(BaseGamepad.ButtonAction buttonAction: gamepad.buttonActions){
            //TODO fix having to pass in gamepad instance
            buttonAction.check(gamepad);
        }
        for(BaseGamepad.ButtonAction buttonAction: gamepad2.buttonActions){
            //TODO fix having to pass in gamepad instance
            buttonAction.check(gamepad2);
        }
        Robot.getInstance().drivetrain.setDrive(gamepad.getAxis(GamepadEnum.LEFT_STICK_X),gamepad.getAxis(GamepadEnum.RIGHT_STICK_X));
    }

    @Override
    public void stop() {
    }
}
