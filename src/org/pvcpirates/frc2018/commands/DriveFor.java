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
        Drivetrain.setPIDF(.15, 0.0, 0, 0);
        encTicks = (inches / (6 * Math.PI)) * 1024 * (17.3);
        Robot.getInstance().hardware.leftDrive1.getSensorCollection().setQuadraturePosition(0, 10);
        Robot.getInstance().hardware.rightDrive1.getSensorCollection().setQuadraturePosition(0, 10);
        System.out.println("INIT");
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
        Drivetrain.stopAll();
    }

}
