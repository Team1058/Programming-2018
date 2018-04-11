package org.pvcpirates.frc2018.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import static org.pvcpirates.frc2018.util.RobotMap.Constants.ROBOT_TIMEOUT;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;

public class DriveForGyro extends Command {
	private double inches;
	private double encTicks;
	private double lEncTicks;
	private double rEncTicks;
	private double start;
	private boolean init;
	private double maxOutput = .85;
	private double Kp;
	double direction;
	Hardware h = Hardware.getInstance();

	public DriveForGyro(double inches) {
		this.inches = inches;
		Kp =(Math.abs(inches) / inches)>=0? .000005:.0001;
		
	}
	public DriveForGyro(double inches,double Kp) {
		this.inches = inches;
		this.Kp = Kp;
	}
	public DriveForGyro(double inches,double Kp,double maxOutput) {
		this.inches = inches;
		this.Kp = Kp;
		this.maxOutput = maxOutput;
	}
	@Override
	public void init() {
		
		encTicks = (inches / (6.0 * Math.PI)) * 1024.0 * (11.25);
		// h.leftDrive1.getSensorCollection().setQuadraturePosition(0,
		// ROBOT_TIMEOUT);
		// h.rightDrive1.getSensorCollection().setQuadraturePosition(0,
		// ROBOT_TIMEOUT);
		// Manually change instead of super.init() b/c there is no command list
		rEncTicks = encTicks;
		lEncTicks = encTicks;
		lEncTicks += h.leftDrive1.getSensorCollection().getQuadraturePosition();
		rEncTicks -= h.rightDrive1.getSensorCollection().getQuadraturePosition();
		init = false;
		setStatus(Status.EXEC);
		direction = (Math.abs(inches) / inches);
	}

	@Override
	public void exec() {
		if (this.status != Status.STOP) {
			double rEnc = h.rightDrive1.getSensorCollection().getQuadraturePosition();
			double lEnc = h.leftDrive1.getSensorCollection().getQuadraturePosition();
			double leftOutput = .5;
			double rightOutput = .5;
			// Balancing constant
			System.out.println(" STARTO" + start);
			if (!init) {
				start = h.navx.getAngle();

				init = true;
			} else {
				
				
				double KGp = .04;
				double current = h.navx.getAngle();

				leftOutput = Kp * (encTicks + direction*lEnc);
				rightOutput = Kp * (encTicks - direction*rEnc);
				
				leftOutput = -(leftOutput - KGp * (start - current));
				rightOutput = (rightOutput + KGp * (start - current));
				
				if (leftOutput > maxOutput) {
					leftOutput = maxOutput;

				}
				if (leftOutput < -maxOutput) {

					leftOutput = -maxOutput;
				}
				if (rightOutput > maxOutput) {
					rightOutput = maxOutput;

				}
				if (rightOutput < -maxOutput) {

					rightOutput = -maxOutput;
				}
				Drivetrain.setDrive(ControlMode.PercentOutput, leftOutput,rightOutput);

				// Drivetrain.setDrive(ControlMode.PercentOutput,-(-KGp*(start+current)),
				// KGp*(start+current));
				boolean rInRange = false;
				boolean lInRange = false;
				System.out.println(-rEnc + "R" + encTicks);
				System.out.println(lEnc + "L" + encTicks);
				System.out.println(start + " " + current);
				System.out.println("R" + ((rightOutput + KGp * (start - current))));
				System.out.println("L" + (-(leftOutput - KGp * (start - current))));
				System.out.println("MOD"+(rightOutput + KGp * (start - current)));
				System.out.println(direction + "D   " + leftOutput + "     " + rightOutput);
				if (direction == -1) {
					rInRange = (-rEnc < rEncTicks + 1500);
					lInRange = (lEnc < lEncTicks + 1500);
				} else {
					rInRange = (-rEnc > rEncTicks - 1500);
					lInRange = (lEnc > lEncTicks - 1500);
				}
				if (rInRange || lInRange) {
					System.out.println("REEEEEEEEEEEEEEEEEEEEEEEEEEEE");
					setStatus(Status.STOP);
					this.finished();
				}
			}
		}
	}

	@Override
	public void finished() {
		h.rightDrive1.configClosedloopRamp(0, 10);
		h.leftDrive1.configClosedloopRamp(0, 10);
		Drivetrain.stopAll();
	}

}