package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class SafeMoveArmPolarSetpoint extends Command {
    private double ext;
    private double angle;

    public SafeMoveArmPolarSetpoint(double ext, double angle) {
        this.ext = ext;
        this.angle = angle;
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
        Arm.extendArm(0);
        this.setStatus(Status.INIT);
    }

    @Override
    public void exec() {
        Arm.pivotArm(angle);
        if (Arm.getPivotAngle() <= angle + 1 && Arm.getPivotAngle() >= angle - 1) {
            this.setStatus(Status.STOP);
            this.finished();
        }
    }

    @Override
    public void finished() {
        // TODO Auto-generated method stub
        Arm.moveArmPolar(ext, angle);
    }


}
