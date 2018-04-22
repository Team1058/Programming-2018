package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;

import java.util.LinkedList;

public class Command {

    public LinkedList<Command> commands;
    public boolean parallel = false;
    Status status = Status.INIT;
    private Command current;

    public Command() {
        commands = new LinkedList<Command>();
    }

    public void init() {
        current = commands.getFirst();
        setStatus(Status.EXEC);
    }

    public void exec() {
        //LONG COMMANDS WILL NOT WORK WITH PARALLEL
        if (parallel) {
            for (Command cmd : commands) {
                if (cmd.getStatus() == Status.INIT) {
                    cmd.init();
                } else if (cmd.getStatus() == Status.EXEC) {
                    cmd.exec();
                }
                if (cmd.getStatus() == Status.STOP) {
                    cmd.finished();
                    commands.remove(cmd);
                }
            }
            if (commands.isEmpty()) {
                setStatus(Status.STOP);
            }
        } else {
            if (!commands.isEmpty()) {
                if (current.getStatus() == Status.STOP) {
                    current.finished();
                    commands.removeFirst();
                    if (commands.isEmpty()) {
                        this.finished();
                    } else {
                        current = commands.getFirst();
                    }
                }
                if (current.getStatus() == Status.INIT) {
                    current.init();
                }
                if (current.getStatus() == Status.EXEC) {
                    current.exec();
                }
            } else {
                setStatus(Status.STOP);
            }
        }
    }

    public void finished() {
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}