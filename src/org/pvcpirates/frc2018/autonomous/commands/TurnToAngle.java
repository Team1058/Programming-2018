package org.pvcpirates.frc2018.autonomous.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.autonomous.Command;
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
        current = Hardware.getInstance().navx.getYaw();
        pidf = new PIDF(p,i,d,0,0);
        setStatus(Status.EXEC);
    }

    @Override
    public void exec() {
        double output = 0;
        current = Hardware.getInstance().navx.getYaw();
        if(Math.abs(goal-current) < 1) {
        	this.setStatus(Status.STOP);
        }
        output = pidf.calculate(goal-current);
        Robot.getInstance().drivetrain.setDrive(ControlMode.PercentOutput, output, -output);
    }

    @Override
    public void finished() {
        Robot.getInstance().drivetrain.stopAll();
    }

}
