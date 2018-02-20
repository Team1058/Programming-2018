package org.pvcpirates.frc2018.state;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.ZeroArm;
import org.pvcpirates.frc2018.gamepads.DriverGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.gamepads.OperatorGamepad;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class TeleopState extends State {
    private DriverGamepad driverGamepad;
    private OperatorGamepad operatorGamepad;
    private Hardware h = Hardware.getInstance();
    private Command zeroArm = new ZeroArm();

    @Override
    public void init() {
        driverGamepad = new DriverGamepad(0);
        operatorGamepad = new OperatorGamepad(1);
    	
    	//zeroArm.init();
    	
    	Arm.configurePID();
    }

    @Override
    public void exec() {
    	//driverGamepad.executeCommands();
        //operatorGamepad.executeCommands();
    	
    	
    	System.out.println("Wrist A: "+Arm.getWristAngle());
    	System.out.println("Arm"+ Arm.getPivotAngle());
    	System.out.println("Wrist Enc: "+ h.wristPivotMotor.getSensorCollection().getQuadraturePosition());
    	
    	if(operatorGamepad.getButton(GamepadEnum.A_BUTTON)){
    		h.armPivotMotor.set(ControlMode.PercentOutput, operatorGamepad.getAxis(GamepadEnum.LEFT_STICK_Y));
    		h.armExtendMotor.set(ControlMode.PercentOutput, operatorGamepad.getAxis(GamepadEnum.LEFT_STICK_X));
    		h.wristPivotMotor.set(ControlMode.PercentOutput, operatorGamepad.getAxis(GamepadEnum.RIGHT_STICK_Y));
    		//Arm.levelWrist();
    	}else{
    		//Arm.wristRotate(90);
    	}
        
    }

    @Override
    public void stop() {
    }

}
