package org.pvcpirates.frc2018.autonomous.subcommands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.autonomous.AutoSubCommand;
import org.pvcpirates.frc2018.autonomous.command.AutoCommand;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;

public class DriveUltra extends AutoSubCommand {
	
	private double currentPos;
	private Drivetrain drivetrain = Robot.getInstance().drivetrain;
	private Hardware hardware = Hardware.getInstance();
	private double inches = 0;
	
	public DriveUltra(AutoCommand parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	public DriveUltra(AutoCommand parent, double inches){
		super(parent);
		this.inches = inches;
	}

	@Override
	public void init() {
		super.init();
		Robot.getInstance().drivetrain.setPIDF(.15,0,0,0);
	}

	@Override
    public void exec() {
        super.exec();
        double ultrasonicInchesR = Robot.getInstance().hardware.leftUltrasonic.getRangeInches();
        double ultrasonicInchesL = Robot.getInstance().hardware.rightUltrasonic.getRangeInches();

        Robot.getInstance().drivetrain.setDrive(ControlMode.PercentOutput,1*((ultrasonicInchesL-inches))/ultrasonicInchesL,1*((ultrasonicInchesR-inches))/ultrasonicInchesR);
        if (ultrasonicInchesL == inches && ultrasonicInchesR == inches)
            this.finished();
    }

    @Override
    public void finished() {
        super.finished();
        Robot.getInstance().drivetrain.stopAll();
    }
}
