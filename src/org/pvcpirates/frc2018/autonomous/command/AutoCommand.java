package org.pvcpirates.frc2018.autonomous.command;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.autonomous.subcommands.AutoSubCommand;

import java.util.LinkedList;

public class AutoCommand {

    LinkedList<AutoSubCommand> subCommands;

    public AutoCommand() {
        subCommands = new LinkedList<AutoSubCommand>();
    }

    public void addSubCommand(AutoSubCommand toAdd) {
        subCommands.add(toAdd);
    }

    public void executeCommand() {
        runNextSubCommand();
    }

    private void runNextSubCommand() {
        // Exec next command in stack
        if (!subCommands.isEmpty()) {
            AutoSubCommand nextCommand = subCommands.remove();
            nextCommand.exec();
        } else {
            //All subcommands done.
        }
    }

    public void statusChanged(Status status) {
        if (status == Status.STOP) {
            runNextSubCommand();
        } else if (status == Status.ERROR) {
            // Default behaviour of statusChanged will kill rest of commands if one errors out
            subCommands.clear();
        }
    }

}
