package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class MoveArmPerimeter extends Command {
    public double height;
    private double lastHeight = 0;
    private double change;

    public MoveArmPerimeter(double height) {
        this.height = height;
    }

    @Override
    public void init() {
        change = 1;
    }

    @Override
    public void exec() {
        // When gp.something.......
        int sign = (int) (Arm.getArmY() - height) / (int) Math.abs(Arm.getArmY() - height);
        change = Arm.getArmY() - lastHeight;

        Arm.moveCurveMax(Arm.getArmY() + (sign * change));
        lastHeight = Arm.getArmY();
        if (Arm.getArmY() == height) {
            this.setStatus(Status.STOP);
        }
    }

    @Override
    public void finished() {
        Arm.stopAll();
    }
}
