package org.pvcpirates.frc2018.state;

import org.pvcpirates.frc2018.Scheduler;
import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.autonomous.AutoCommandFactory;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.ZeroArm;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Grabber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class AutoState extends State {
	public static AutoCommandFactory autoCommandFactory = new AutoCommandFactory();
	private Command zeroArm;

	@Override
	public void init() {
		//Zero Sensors
		Hardware.getInstance().leftDrive1.getSensorCollection().setQuadraturePosition(0, 10);
		Hardware.getInstance().rightDrive1.getSensorCollection().setQuadraturePosition(0, 10);
		Hardware.getInstance().rightDrive1.setNeutralMode(NeutralMode.Brake);
		Hardware.getInstance().rightDrive2.setNeutralMode(NeutralMode.Brake);
		Hardware.getInstance().leftDrive1.setNeutralMode(NeutralMode.Brake);
		Hardware.getInstance().leftDrive2.setNeutralMode(NeutralMode.Brake);
		//Zero Arm
		zeroArm = new ZeroArm();
		zeroArm.init();
		Hardware.getInstance().navx.reset();
		while (zeroArm.getStatus() != Status.STOP || Hardware.getInstance().navx.isCalibrating())
			zeroArm.exec();
		//Hold cube during auto
		Grabber.closeGrabber();
		Grabber.holdRollers();
		
		Hardware.getInstance().leftDrive1.configPeakOutputForward(.45, 0);
		Hardware.getInstance().rightDrive1.configPeakOutputForward(.42, 0);
        Hardware.getInstance().leftDrive1.configPeakOutputReverse(-.45, 0);
        Hardware.getInstance().rightDrive1.configPeakOutputReverse(-.42, 0);
		Scheduler.autoChooser.getSelected().init();
		
	}

	@Override
	public void exec() {
		Scheduler.autoChooser.getSelected().exec();
		//System.out.println("Inches "+((Hardware.getInstance().rightDrive1.getSensorCollection().getQuadraturePosition()/(1024 * 11.25))*(6 * Math.PI)));
	}

	@Override
	public void stop() {
		Scheduler.autoChooser.getSelected().finished();
	}
}
