package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.util.PIDF;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class TurnToAngle extends Command {
	
	double goal;
	double current;
	double p;
	double i;
	double d;
	
	PIDF pidf;
	
	public TurnToAngle(double goal) {
		super();
		this.goal = goal;
		
	}
	
	@Override
    public void init() {
        setStatus(Status.EXEC);
    }

    @Override
    public void exec() {
        double output = 0;
        current = Hardware.getInstance().navx.getPitch();
        if(Math.abs(goal-current) < 1) {
        	this.setStatus(Status.STOP);
        }
        output = (goal-current) / goal;
        Robot.getInstance().drivetrain.setDrive(ControlMode.PercentOutput, output, -output);
    }

    @Override
    public void finished() {
        Robot.getInstance().drivetrain.stopAll();
    }

}
