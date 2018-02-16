package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.subsystems.Grabber;

public class GrabberToggle extends Command {
    private boolean grab;

    public GrabberToggle(boolean grab) {
        super();
        this.grab = grab;
    }

    @Override
    public void init() {
        if (grab)
            Grabber.intakeRollers();
        else
            Grabber.outtakeRollers();
        setStatus(Status.EXEC);
    }

    @Override
    public void exec() {
        if (Robot.getInstance().hardware.cubeLimitSwitch.get() == grab)
            setStatus(Status.STOP);
    }

    @Override
    public void finished() {
        Grabber.closeGrabber();
        if (grab)
            Grabber.holdRollers();
        else
            Grabber.stopRollers();
    }
}
