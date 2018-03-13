package org.pvcpirates.frc2018.state;

import org.pvcpirates.frc2018.gamepads.DriverGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.gamepads.OperatorGamepad;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

import com.ctre.phoenix.motorcontrol.NeutralMode;

public class TeleopState extends State {
    private DriverGamepad driverGamepad;
    private OperatorGamepad operatorGamepad;
    private Hardware hardware;

    boolean noWrist = false;
    @Override
    public void init() {
        driverGamepad = new DriverGamepad(0);
        operatorGamepad = new OperatorGamepad(1);
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

    }

    @Override
    public void exec() {
        driverGamepad.executeCommands();
        operatorGamepad.executeCommands();
        
        //If the drive hits that back button toggle the wrist moving backwards
    	if (driverGamepad.getButton(GamepadEnum.BACK_BUTTON)){
    		noWrist = !noWrist;
    	}
    	
        if(noWrist){
        	Arm.wristRotate(-90);
        }else{
        	Arm.levelWrist();
        }
    }

    @Override
    public void stop() {
    }

}
