package org.pvcpirates.frc2018.state;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.DriverGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.gamepads.OperatorGamepad;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.controllers.Drivetrain;

import edu.wpi.first.wpilibj.Compressor;

public class TeleopState extends State{
    private DriverGamepad driverGamepad;
    private OperatorGamepad operatorGamepad;
    @Override
    public void init() {
    		driverGamepad = new DriverGamepad(0);
    		operatorGamepad = new OperatorGamepad(1);
    }

    @Override
    public void exec() {
    		driverGamepad.executeCommands();
    		operatorGamepad.executeCommands();
    }

    @Override
    public void stop() {
    }

}
