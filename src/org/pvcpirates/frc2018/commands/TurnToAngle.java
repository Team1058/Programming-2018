package org.pvcpirates.frc2018.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2018.util.PIDF;

public class TurnToAngle extends Command {

    double goal;
    double current;
    double p;
    double i;
    double d;

    PIDF pidf;

    public TurnToAngle(double goal) {
        super();
        this.goal = goal;

    }

    @Override
    public void init() {
        current = Hardware.getInstance().navx.getYaw();
        pidf = new PIDF(p, i, d, 0, 0);
        setStatus(Status.EXEC);
    }

    @Override
    public void exec() {
        double output = 0;
        current = Hardware.getInstance().navx.getPitch();
        if (Math.abs(goal - current) < 1) {
            this.setStatus(Status.STOP);
        }
        output = (goal - current) / goal;
        Drivetrain.setDrive(ControlMode.PercentOutput, output, -output);
    }

    @Override
    public void finished() {
        Drivetrain.stopAll();
    }

}
