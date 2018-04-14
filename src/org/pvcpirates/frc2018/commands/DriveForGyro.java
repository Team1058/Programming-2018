package org.pvcpirates.frc2018.commands;

import static org.pvcpirates.frc2018.util.RobotMap.Constants.ROBOT_TIMEOUT;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;

public class DriveForGyro extends Command {
	/*
	 * Used in auto to hold the current heading while driving straight
	 */
	private double inches;
	private double encTicks;
	private double lEncTicks;
	private double rEncTicks;
	private double start;
	private boolean init;
	private double maxOutput = .85;
	private double Kp;
	double direction;
	Timer timer = new Timer();
	Hardware h = Hardware.getInstance();

	public DriveForGyro(double inches) {
		this.inches = inches;
		Kp = .000005;

	}

	public DriveForGyro(double inches, double Kp) {
		this.inches = inches;
		this.Kp = Kp;
	}

	public DriveForGyro(double inches, double Kp, double maxOutput) {
		this.inches = inches;
		this.Kp = Kp;
		this.maxOutput = maxOutput;
	}

	@Override
	public void init() {
		timer.start();
		System.out.println("Init driveforGyro");
		encTicks = (inches / (6.0 * Math.PI)) * 1024.0 * (11.25);
		
		h.leftDrive1.getSensorCollection().setQuadraturePosition(0,ROBOT_TIMEOUT);
		h.rightDrive1.getSensorCollection().setQuadraturePosition(0,ROBOT_TIMEOUT);
		while(h.rightDrive1.getSensorCollection().getQuadraturePosition() != 0 || h.leftDrive1.getSensorCollection().getQuadraturePosition() != 0){
			h.rightDrive1.getSensorCollection().setQuadraturePosition(0,ROBOT_TIMEOUT);
			h.leftDrive1.getSensorCollection().setQuadraturePosition(0,ROBOT_TIMEOUT);
			System.out.println("LOOPING ... "+h.leftDrive1.getSensorCollection().getPulseWidthPosition()+" "+h.rightDrive1.getSensorCollection().getPulseWidthPosition());
			if(timer.get() > 5){
				break;
			}
		}
		
		// Manually change instead of super.init() b/c there is no command list
		rEncTicks = encTicks;
		lEncTicks = encTicks;
		lEncTicks += h.leftDrive1.getSensorCollection().getQuadraturePosition();
		rEncTicks -= h.rightDrive1.getSensorCollection().getQuadraturePosition();
		init = false;
		setStatus(Status.EXEC);
		direction = (Math.abs(inches) / inches);
		Hardware.getInstance().leftDrive1.configOpenloopRamp(0, 0);
		Hardware.getInstance().rightDrive1.configOpenloopRamp(0, 0);
		
	}

	@Override
	public void exec() {
		System.out.println("DFG exec");
		if (this.status != Status.STOP) {
			//get sensor input
			double rEnc = h.rightDrive1.getSensorCollection().getQuadraturePosition();
			double lEnc = h.leftDrive1.getSensorCollection().getQuadraturePosition();
			double leftOutput = 0;
			double rightOutput = 0;
			boolean rInRange = false;
			boolean lInRange = false;
			double KGp = .04;
			double current = h.navx.getAngle();

			
			if (!init) {
				//make sure the starting angle is initialized
				start = h.navx.getAngle();
				init = true;
			} else {
				//compute output without gyro heading
				leftOutput = Kp * (encTicks + direction * lEnc);
				rightOutput = Kp * (encTicks - direction * rEnc);
				
				//adjust output to hold a constant heading
				leftOutput = -(leftOutput + KGp * (start - current));
				rightOutput = (rightOutput - KGp * (start - current));

				
				//adjust output to be within than our max and min
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
				
				//Drive if we are not in range
				Drivetrain.setDrive(ControlMode.PercentOutput, leftOutput, rightOutput);
				
				System.out.println("R Goal: "+rEncTicks);
				System.out.println("L Goal: "+lEncTicks);
				System.out.println("R: Pos"+rEnc);
				System.out.println("L: Pos"+lEnc);
				
				//compute range based on direction
				if (direction == -1) {
					rInRange = (-rEnc < rEncTicks + 1500);
					lInRange = (lEnc < lEncTicks + 1500);
				} else {
					rInRange = (-rEnc > rEncTicks - 1500);
					lInRange = (lEnc > lEncTicks - 1500);
				}

				
				//If Left or right are in range stop
				if (rInRange || lInRange) {
					setStatus(Status.STOP);
					this.finished();
				}
				
				
				
			}
		}
	}

	@Override
	public void finished() {
		h.rightDrive1.configOpenloopRamp(0, 10);
		h.leftDrive1.configOpenloopRamp(0, 10);
		Drivetrain.stopAll();
		System.out.println("DFG finished");
		
	}

}