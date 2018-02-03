package org.pvcpirates.frc2018.autonomous.subcommands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.autonomous.command.AutoCommand;

public class AutoSubCommand {
    AutoCommand parentCommand;
    Status status;

    public AutoSubCommand(AutoCommand parent) {
        parentCommand = parent;
    }

    public void changeStatus(Status stat) {
        parentCommand.statusChanged(status);
    }

    public void init() {
        changeStatus(Status.INIT);
    }

    public void exec() {
        changeStatus(Status.EXEC);
    }

    public void finished() {
        changeStatus(Status.STOP);
    }

}
