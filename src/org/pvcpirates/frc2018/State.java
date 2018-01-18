package org.pvcpirates.frc2018;

public abstract class State {
    public enum Status{
        INIT,EXEC,STOP;
    }
    public Status status;
    public abstract void init();
    public abstract void exec();
    public abstract void stop();
}
