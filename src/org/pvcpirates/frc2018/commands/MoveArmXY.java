package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class MoveArmXY extends Command {
    private double changeX = 0, changeY = 0, lastX = 0, lastY = 0, x, y;

    public MoveArmXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void exec() {
        int signY = (int) (Arm.getArmY() - y) / (int) Math.abs(Arm.getArmY() - y);
        int signX = (int) (Arm.getArmX() - x) / (int) Math.abs(Arm.getArmX() - x);

        if (changeX == 0)
            changeX = 80;
        else
            changeX = Arm.getArmX() - lastX;

        if (changeY == 0)
            changeY = 80;
        else
            changeY = Arm.getArmY() - lastY;


        Arm.moveXY(Arm.getArmX() + (changeX * signX), Arm.getArmY() + (changeY * signY));
        if (y == Arm.getArmY() && x == Arm.getArmX())
            setStatus(Status.STOP);
        lastX = Arm.getArmX();
        lastY = Arm.getArmY();
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
        this.setStatus(Status.EXEC);
    }

    @Override
    public void finished() {
        Arm.stopAll();
    }
}
