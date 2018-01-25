package org.pvcpirates.frc2018.state;

import org.pvcpirates.frc2018.commands.CommandFactory;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.DriverGamepad;
import org.pvcpirates.frc2018.gamepads.Gamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.gamepads.OperatorGamepad;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.controllers.Drivetrain;

public class TeleopState extends State{
    private DriverGamepad driverGamepad = new DriverGamepad(0);
    private OperatorGamepad operatorGamepad = new OperatorGamepad(1);
    @Override
    public void init() {
        CommandFactory commandFactory = new CommandFactory();
    }

    @Override
    public void exec() {
    		driverGamepad.executeActions();
    		operatorGamepad.executeActions();
    }

    @Override
    public void stop() {
    }

}
