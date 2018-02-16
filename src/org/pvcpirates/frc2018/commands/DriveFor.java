package org.pvcpirates.frc2018.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;

public class DriveFor extends Command {
    private double inches;
    private double encTicks;


    public DriveFor(double inches) {
        super();
        this.inches = inches;
    }

    @Override
    public void init() {
        //FIXME PID
        Drivetrain.setPIDF(.15, 0, 0, 0);
        //FIXME ENCODER CONVERSIONS
        encTicks = (inches / (6 * Math.PI)) * 256;
        //Manually change instead of super.init() b/c there is no command list
        setStatus(Status.EXEC);
    }

    @Override
    public void exec() {
        Robot.getInstance().hardware.leftDrive1.set(ControlMode.Position, encTicks);
        Robot.getInstance().hardware.rightDrive1.set(ControlMode.Position, encTicks);
        if (Robot.getInstance().hardware.leftDrive1.getSelectedSensorPosition(0) == encTicks)
            setStatus(Status.STOP);
    }

    @Override
    public void finished() {
        Robot.getInstance().drivetrain.stopAll();
    }

}
