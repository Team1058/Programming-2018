package org.pvcpirates.frc2018.autonomous.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.autonomous.Command;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.controllers.GrabberController;

public class GrabberToggle extends Command {
    private boolean grab;
    private GrabberController grabberController;
    
    public GrabberToggle(boolean grab) {
        super();
        this.grab = grab;
    }

    @Override
    public void init() {
        grabberController = new GrabberController();
        if (grab)
            grabberController.intakeRollers();
        else
            grabberController.outtakeRollers();
        setStatus(Status.EXEC);
    }

    @Override
    public void exec() {
        if (Robot.getInstance().hardware.cubeLimitSwitch.get() == grab)
            setStatus(Status.STOP);
    }

    @Override
    public void finished() {
        grabberController.closeGrabber();
        if (grab)
            grabberController.holdRollers();
        else
            grabberController.stopRollers();
    }
}
