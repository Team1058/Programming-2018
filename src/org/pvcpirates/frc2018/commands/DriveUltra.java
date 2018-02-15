package org.pvcpirates.frc2018.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Robot;

public class DriveUltra extends Command {
	
	private double inches = 0;
	
	public DriveUltra() {
		super();
	}
	public DriveUltra(double inches){
		this.inches = inches;
	}

	@Override
	public void init() {
		Robot.getInstance().drivetrain.setPIDF(.15,0,0,0);
		this.setStatus(Status.EXEC);
	}

	@Override
    public void exec() {
        super.exec();
        double ultrasonicInchesR = Robot.getInstance().hardware.leftUltrasonic.getRangeInches();
        double ultrasonicInchesL = Robot.getInstance().hardware.rightUltrasonic.getRangeInches();

        Robot.getInstance().drivetrain.setDrive(ControlMode.PercentOutput,((ultrasonicInchesL-inches))/ultrasonicInchesL,((ultrasonicInchesR-inches))/ultrasonicInchesR);
        if (ultrasonicInchesL == inches && ultrasonicInchesR == inches)
            this.setStatus(Status.STOP);
    }

    @Override
    public void finished() {
        super.finished();
        Robot.getInstance().drivetrain.stopAll();
    }
}
