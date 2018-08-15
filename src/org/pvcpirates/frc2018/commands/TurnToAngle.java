
package org.pvcpirates.frc2018.commands;


import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;

public class TurnToAngle extends Command {

	double goal;
	double current;
	boolean init;
	double Kg = .093;
	double kI = .015;
	double cummulative = 0;
	int sign = 1;
	int oldSign = 1;
	double timeOut=5;
	Timer timer = new Timer();

	public TurnToAngle(double goal) {
		this.goal = goal;
		this.Kg = .093;
		this.kI = .014;
	}
	
	
	public TurnToAngle(double goal, double Kg) {
		this.goal = goal;
		this.Kg = Kg;
	}
	
	public TurnToAngle(double goal, double timeOut, boolean isTimeOut) {
		this.goal = goal;
		
		this.timeOut = timeOut;
		if (isTimeOut)
			System.out.println("Time out");
		this.Kg = .093;
		this.kI = .014;  
	}

	@Override
	public void init() {
		if (goal == 0) {
			goal = .01;
		}

		current = Hardware.getInstance().navx.getAngle() + 1;
		// if (goal)
		setStatus(Status.EXEC);
		Hardware.getInstance().leftDrive1.configPeakOutputForward(.75, 10);
		Hardware.getInstance().rightDrive1.configPeakOutputForward(.75, 10);
		Hardware.getInstance().leftDrive1.configPeakOutputReverse(-.75, 10);
		Hardware.getInstance().rightDrive1.configPeakOutputReverse(-.75, 10);
		timer.start();

	}

	@Override
	public void exec() {
		if (this.status != Status.STOP) {
			sign = (int) (Math.abs(goal - current) / (goal - current));
			if(oldSign != sign){
				cummulative = 0;
				oldSign = sign;
			}
			current = Hardware.getInstance().navx.getAngle() + 1;

			double output = 0;
			System.out.println("Angle: "+Hardware.getInstance().navx.getAngle());
			System.out.println("Cummulative: "+cummulative);
			System.out.println("Sign: "+sign);
			System.out.println("Timer: "+timer.get());
			
			if ((Math.abs(goal - current) < 1 && Math.abs(Hardware.getInstance().leftDrive1.getSensorCollection().getQuadratureVelocity()) < 20) || timer.get() > timeOut) {
				this.setStatus(Status.STOP);
				this.finished();
			} else {
				cummulative += kI;
				output = Math.abs(Kg * (goal - current) + cummulative);
				if (sign == -1) {
					Drivetrain.setDrive(ControlMode.Velocity, 10000 * output, 10000 * output);
				} else {
					Drivetrain.setDrive(ControlMode.Velocity, -10000 * output, -10000 * output);
				}
			}

		}
	}

	@Override
	public void finished() {
		Drivetrain.stopAll();
		timer.stop();
		Hardware.getInstance().leftDrive1.configPeakOutputForward(1, 10);
		Hardware.getInstance().rightDrive1.configPeakOutputForward(1, 10);
		Hardware.getInstance().leftDrive1.configPeakOutputReverse(-1, 10);
		Hardware.getInstance().rightDrive1.configPeakOutputReverse(-1, 10);
	}

}
