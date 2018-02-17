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
        //driverGamepad = new DriverGamepad(0);
        operatorGamepad = new OperatorGamepad(1);
    	
    	//zeroArm.init();
    	
    	Arm.configurePID();
    }

    @Override
    public void exec() {
    	//driverGamepad.executeCommands();
        //operatorGamepad.executeCommands();
    	
    	//zeroArm.exec();
    	
    	
    	System.out.println(("Pot Value: "+ Hardware.getInstance().armPivotMotor.getSensorCollection().getAnalogIn()));
    	System.out.println("Arm degrees: "+Arm.getPivotAngle());
    	
        h.armPivotMotor.set(ControlMode.PercentOutput, operatorGamepad.getAxis(GamepadEnum.LEFT_STICK_Y));
        //h.armPivotMotor.set(ControlMode.Position, 330);
        
    }

    @Override
    public void stop() {
    }

}
