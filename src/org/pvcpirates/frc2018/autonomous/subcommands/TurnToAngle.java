package org.pvcpirates.frc2018.autonomous.subcommands;

import org.pvcpirates.frc2018.autonomous.command.AutoCommand;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.util.PIDF;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class TurnToAngle extends AutoSubCommand {
	
	double goal;
	double current;
	double p;
	double i;
	double d;
	
	PIDF pidf;
	
	public TurnToAngle(AutoCommand parent, double goal) {
		super(parent);
		
		this.goal = goal;
		
	}
	
	@Override
    public void init() {
		super.init();
        current = Hardware.getInstance().navx.getYaw();
        pidf = new PIDF(p,i,d,0,0);
    }

    @Override
    public void exec() {
        super.exec();
        double output = 0;
        current = Hardware.getInstance().navx.getYaw();
        if(Math.abs(goal-current) < 1) {
        	finished();
        }
        output = pidf.calculate(goal-current);
        Robot.getInstance().drivetrain.setDrive(ControlMode.PercentOutput, output, -output);
    }

    @Override
    public void finished() {
        super.finished();
        Robot.getInstance().drivetrain.stopAll();
        
    }

}
