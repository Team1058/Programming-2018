package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class MoveArmPerimeter extends Command {
    private double lastHeight=0;
    private double change;
    public double height;
    public MoveArmPerimeter(double height) {
        this.height = height;
    }

    @Override
    public void exec() {
        // When gp.something.......
        int sign =(int) (Arm.getArmY() -height)/(int)Math.abs(Arm.getArmY() -height);
        if (change == 0)
            change = 80;
        else {
            change = Arm.getArmY() - lastHeight;
            lastHeight = Arm.getArmY();
        }

        Arm.moveCurveMax(Arm.getArmY()+(sign*change));
        if (Arm.getArmY() == height)
            this.setStatus(Status.STOP);
    }

    @Override
    public void finished() {
        Arm.stopAll();
    }
}
