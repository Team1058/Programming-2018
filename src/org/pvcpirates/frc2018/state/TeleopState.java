package org.pvcpirates.frc2018.state;

import org.pvcpirates.frc2018.commands.PivotArm;
import org.pvcpirates.frc2018.gamepads.DriverGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.gamepads.OperatorGamepad;

import org.pvcpirates.frc2018.gamepads.VJoyKeyboard;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class TeleopState extends State {
    private DriverGamepad driverGamepad;
    private OperatorGamepad operatorGamepad;
    private VJoyKeyboard vJoyKeyboard;
    private Hardware hardware;
    
    boolean noWrist = false;
    @Override
    public void init() {
        driverGamepad = new DriverGamepad(0);
        operatorGamepad = new OperatorGamepad(1);
        vJoyKeyboard = new VJoyKeyboard(2);
        
        hardware = Hardware.getInstance();
        //Reset ramp set in auto
        hardware.rightDrive1.configClosedloopRamp(0, 10);
        hardware.leftDrive1.configClosedloopRamp(0, 10);
        
        hardware.leftDrive1.configPeakOutputForward(1, 10);
        hardware.rightDrive1.configPeakOutputForward(1, 10);
        hardware.leftDrive1.configPeakOutputReverse(-1, 10);
        hardware.rightDrive1.configPeakOutputReverse(-1, 10);
        
        //Change motors to coast mode
        hardware.rightDrive1.setNeutralMode(NeutralMode.Coast);
        hardware.rightDrive2.setNeutralMode(NeutralMode.Coast);
        hardware.leftDrive1.setNeutralMode(NeutralMode.Coast);
        hardware.leftDrive2.setNeutralMode(NeutralMode.Coast);
       
        hardware.leftDrive1.getSensorCollection().setQuadraturePosition(0, 10);
        hardware.rightDrive1.getSensorCollection().setQuadraturePosition(0, 10);
        
    }

    @Override
    public void exec() {
        //driverGamepad.executeCommands();
        operatorGamepad.executeCommands();
        vJoyKeyboard.executeCommands();
        System.out.println("Angle of arm "+Arm.getPivotAngle());
        System.out.println("Help: "+hardware.armPivotMotor.getSensorCollection().getAnalogInRaw());
        
        hardware.armExtendMotor.set(ControlMode.PercentOutput, driverGamepad.getAxis(GamepadEnum.LEFT_STICK_Y));
        
        
    }

    @Override
    public void stop() {
    }

}
