package org.pvcpirates.frc2018.autonomous.subcommands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.autonomous.AutoSubCommand;
import org.pvcpirates.frc2018.autonomous.command.AutoCommand;
import org.pvcpirates.frc2018.robot.Robot;

public class DriveFor extends AutoSubCommand {
    private double inches;
    private double encTicks;

    public DriveFor(AutoCommand parent, double inches) {
        super(parent);
        this.inches = inches;
	}
	
	@Override
    public void init() {
        super.init();
        Robot.getInstance().drivetrain.setPIDF(.15,0,0,0);
        encTicks = (inches/(6 * Math.PI)) * 256;
        Robot.getInstance().hardware.leftDrive1.set(ControlMode.Position, encTicks);
        Robot.getInstance().hardware.rightDrive1.set(ControlMode.Position, encTicks);
    }

    @Override
    public void exec() {
        if (Robot.getInstance().hardware.leftDrive1.getSelectedSensorPosition(0) == encTicks)
            this.finished();
    }

    @Override
    public void finished() {
        super.finished();
        Robot.getInstance().drivetrain.stopAll();
    }
}
