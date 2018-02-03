package org.pvcpirates.frc2018.autonomous.subcommands;

import org.pvcpirates.frc2018.autonomous.AutoSubCommand;
import org.pvcpirates.frc2018.autonomous.command.AutoCommand;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.subsystems.Grabber;

public class GrabberToggle extends AutoSubCommand {
    private boolean grab;
    private Grabber grabberController;
    public GrabberToggle(AutoCommand parent,boolean grab) {
        super(parent);
        this.grab = grab;
    }

    @Override
    public void init() {
        super.init();
        grabberController = new Grabber();
        if (grab)
            grabberController.intakeRollers();
        else
            grabberController.outtakeRollers();
    }

    @Override
    public void exec() {
        super.exec();
        if (Robot.getInstance().hardware.cubeLimitSwitch.get() == grab)
            this.finished();
    }

    @Override
    public void finished() {
        super.finished();
        grabberController.closeGrabber();
        if (grab)
            grabberController.holdRollers();
        else
            grabberController.stopRollers();
    }
}
