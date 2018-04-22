package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.robot.Status;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class MoveArmPerimeter extends Command {
    private double angle;
    private double startingAngle;
    private double goalMaxExtension;

    public MoveArmPerimeter(double angle) {
        this.angle = angle;
    }

    @Override
    public void init() {
    	this.startingAngle = Arm.getPivotAngle();
    	goalMaxExtension = Arm.getMaxExtension(angle); 
    }

    @Override
    public void exec() {
    	
    	
    }

    @Override
    public void finished() {
        Arm.stopAll();
    }
}
