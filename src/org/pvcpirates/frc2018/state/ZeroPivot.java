package org.pvcpirates.frc2018.state;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class ZeroPivot extends Command {

	@Override
	public void init(){
		setStatus(Status.EXEC);
	}
	
	@Override
	public void exec(){
		Hardware.getInstance().armPivotMotor.set(ControlMode.Position, 0);
		setStatus(Status.STOP);
	}
	
	@Override
	public void finished(){
		
	}
	
}
