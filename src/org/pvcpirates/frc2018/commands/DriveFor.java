package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.autonomous.Command;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
public class DriveFor extends Command {
    private double inches;
    private double encTicks;


    public DriveFor(double inches) {
    	super();
        this.inches = inches;
	}
	
	@Override
    public void init() {
    	Robot.getInstance().drivetrain.setPIDF(3, 0.0, 1, 0);
        encTicks = (inches/(6 * Math.PI)) * 1024 *(17.3);
        Hardware.getInstance().leftDrive1.getSensorCollection().setQuadraturePosition(0, 10);
        Hardware.getInstance().rightDrive1.getSensorCollection().setQuadraturePosition(0, 10);
        System.out.println("INIT");
        //Manually change instead of super.init() b/c there is no command list
        setStatus(Status.EXEC);
    }

    @Override
    public void exec() {
    	Robot.getInstance().hardware.leftDrive1.set(ControlMode.Position, encTicks);
        Robot.getInstance().hardware.rightDrive1.set(ControlMode.Position, encTicks);
    }

    @Override
    public void finished() {
        Robot.getInstance().drivetrain.stopAll();
    }


}
