package org.pvcpirates.frc2018.state;


import org.pvcpirates.frc2018.Scheduler;
import org.pvcpirates.frc2018.autonomous.AutoCommandFactory;
import org.pvcpirates.frc2018.commands.Command;

public class AutoState extends State {
    public static AutoCommandFactory autoCommandFactory = new AutoCommandFactory();
    private Command command;
    //TODO commandGroup

    @Override
    public void init() {
        Scheduler.autoChooser.getSelected().init();

    }

    //TODO:Parallel/Sequential stuff
    @Override
    public void exec() {
        Scheduler.autoChooser.getSelected().exec();
    }

    @Override
    public void stop() {
        Scheduler.autoChooser.getSelected().finished();
    }
}
