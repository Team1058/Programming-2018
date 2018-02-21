package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class ZeroArm extends Command {
	
	public ZeroArm(){
		setStatus(Status.INIT);
	}
	
	@Override
	public void init(){
		
		setStatus(Status.EXEC);
	}
	
	@Override
	public void exec(){
		
		if(Hardware.getInstance().armExtendMotor.getSensorCollection().isRevLimitSwitchClosed()){
			setStatus(Status.STOP);
			finished();
			return;
		}else{
			Hardware.getInstance().armExtendMotor.set(ControlMode.PercentOutput, -.25);
		}
	}
	
	@Override
	public void finished(){
		Hardware.getInstance().armExtendMotor.set(ControlMode.PercentOutput, 0);
		System.out.println("F");
	}
}
