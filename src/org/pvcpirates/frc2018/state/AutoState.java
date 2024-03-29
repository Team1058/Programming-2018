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
		Hardware.getInstance().leftDrive1.getSensorCollection().setQuadraturePosition(0, 10);
		Hardware.getInstance().rightDrive1.getSensorCollection().setQuadraturePosition(0, 10);
		Hardware.getInstance().rightDrive1.setNeutralMode(NeutralMode.Brake);
		Hardware.getInstance().rightDrive2.setNeutralMode(NeutralMode.Brake);
		Hardware.getInstance().leftDrive1.setNeutralMode(NeutralMode.Brake);
		Hardware.getInstance().leftDrive2.setNeutralMode(NeutralMode.Brake);
		// Zero Arm
		zeroArm = new ZeroArm();
		zeroArm.init();

		Hardware.getInstance().navx.reset();

		while (zeroArm.getStatus() != Status.STOP || Hardware.getInstance().navx.isCalibrating())
			zeroArm.exec();
		// Hold cube during auto
		Grabber.closeGrabber();
		Grabber.holdRollers();

		Hardware.getInstance().leftDrive1.configPeakOutputForward(1, 0);
		Hardware.getInstance().rightDrive1.configPeakOutputForward(1, 0);
		Hardware.getInstance().leftDrive1.configPeakOutputReverse(-1, 0);
		Hardware.getInstance().rightDrive1.configPeakOutputReverse(-1, 0);
		Scheduler.autoChooser.getSelected().init();
		System.out.println("Auto init fam");

	}

	@Override
	public void exec() {
		System.out.println("STATUS"+Scheduler.autoChooser.getSelected().getStatus());
		if (Scheduler.autoChooser.getSelected().getStatus()!=Status.STOP)
			Scheduler.autoChooser.getSelected().exec();
		else 
			Scheduler.autoChooser.getSelected().finished();

	}

	@Override
	public void stop() {
		Scheduler.autoChooser.getSelected().finished();
	}
}
