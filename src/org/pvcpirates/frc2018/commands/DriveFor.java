package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.robot.Status;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;

public class DriveFor extends Command {
	private double goal;
	private Hardware h = Hardware.getInstance();

	public DriveFor(double inches) {
		this.goal = inches;
	}

	@Override
	public void init() {
		Drivetrain.zeroEncoders();
		Hardware.setPIDF(0.022, 0, 0, 0, h.rightDrive1);
		Hardware.setPIDF(0.027625, 0, 0, 0, h.leftDrive1);

		setStatus(Status.EXEC);
	}

	@Override
	public void exec() {
		Drivetrain.setLinearPostion(goal);

		// 1500 ticks ~= 2.5 inches
		Drivetrain.configureAllowableError(2.5);

		// finish the command when the drivetrain stops moving
		if (Drivetrain.getLeftVelocity() == 0 && Drivetrain.getRightVelocity() == 0) {
			setStatus(Status.STOP);
			this.finished();
		}
	}

	@Override
	public void finished() {
		Drivetrain.stopAll();
	}

}
