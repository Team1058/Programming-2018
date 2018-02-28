package org.pvcpirates.frc2018.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;

public class TurnToAngle extends Command {

    double goal;
    double current;

    public TurnToAngle(double goal) {
        this.goal = goal;
    }

    @Override
    public void init() {
        current = Hardware.getInstance().navx.getPitch();
        setStatus(Status.EXEC);
    }

    @Override
    public void exec() {
        double output = 0;
        current = Hardware.getInstance().navx.getYaw();
        System.out.println(current);
        if (Math.abs(goal - current) < 1) {
            this.setStatus(Status.STOP);
        } else {
            output = (goal - current) / goal + .09;
            Drivetrain.setDrive(ControlMode.PercentOutput, output, output);
        }
    }

    @Override
    public void finished() {
        Drivetrain.stopAll();
    }

}
