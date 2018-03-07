package org.pvcpirates.frc2018.state;

import org.pvcpirates.frc2018.Scheduler;
import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.autonomous.AutoCommandFactory;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.ZeroArm;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.subsystems.Grabber;

public class AutoState extends State {
	public static AutoCommandFactory autoCommandFactory = new AutoCommandFactory();
	private Command zeroArm;

	@Override
	public void init() {
		//Zero Sensors
		Robot.getInstance().hardware.navx.reset();
		Hardware.getInstance().leftDrive1.getSensorCollection().setQuadraturePosition(0, 10);
		Hardware.getInstance().rightDrive1.getSensorCollection().setQuadraturePosition(0, 10);
		//Zero Arm
		zeroArm = new ZeroArm();
		zeroArm.init();
		while (zeroArm.getStatus() != Status.STOP && !Hardware.getInstance().navx.isCalibrating())
			zeroArm.exec();
		//Hold cube during auto
		Grabber.closeGrabber();
		Grabber.holdRollers();
		
		
		Scheduler.autoChooser.getSelected().init();
		
	}

	@Override
	public void exec() {
		Scheduler.autoChooser.getSelected().exec();

	}

	@Override
	public void stop() {
		Scheduler.autoChooser.getSelected().finished();
	}
}
