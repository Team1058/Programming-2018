package org.pvcpirates.frc2018.state;

import org.pvcpirates.frc2018.robot.Status;

public abstract class State {
    public Status status;

    public abstract void init();

    public abstract void exec();

    public abstract void stop();
}
