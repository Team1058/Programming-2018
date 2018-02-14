package org.pvcpirates.frc2018.state;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.pvcpirates.frc2018.gamepads.DriverGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.gamepads.OperatorGamepad;
import edu.wpi.first.wpilibj.Compressor;

import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.util.GamepadHelper;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class TeleopState extends State {
    private DriverGamepad driverGamepad;
    private OperatorGamepad operatorGamepad;

    @Override
    public void init() {
        driverGamepad = new DriverGamepad(0);
        operatorGamepad = new OperatorGamepad(1);
    }

    @Override
    public void exec() {
        SmartDashboard.putNumber("Ultra",Robot.getInstance().hardware.leftUltrasonic.getRangeInches());
        driverGamepad.executeCommands();
        operatorGamepad.executeCommands();
    }

    @Override
    public void stop() {
    }

}
