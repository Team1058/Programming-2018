package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;

public class Command {

    private Status status = Status.INIT;
    private boolean parallel;
    public Cycle cycle;
    public Command(boolean parallel,Cycle cycle) {
        this.parallel = parallel;
        this.cycle = cycle;
    }
    public interface Cycle {
        void exec();
    }
}
