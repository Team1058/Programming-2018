package org.pvcpirates.frc2018.autonomous.command;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.autonomous.subcommands.AutoSubCommand;

import java.util.LinkedList;

public class AutoCommand {

    LinkedList<AutoSubCommand> subCommands;
    private AutoSubCommand currentSubCommand = null;

    public AutoCommand() {
        subCommands = new LinkedList<AutoSubCommand>();
    }

    public void addSubCommand(AutoSubCommand toAdd) {
        subCommands.add(toAdd);
    }

    public void executeCommand() {
        while (!subCommands.isEmpty()){
            runNextSubCommand();
        }
    }

    private void runNextSubCommand() {
        // Exec next command in stack
        if (!subCommands.isEmpty() && (currentSubCommand.status == Status.STOP || currentSubCommand == null)) {
            currentSubCommand = subCommands.remove();
            currentSubCommand.init();
        } else {
            //All subcommands done.
            currentSubCommand.exec();
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
