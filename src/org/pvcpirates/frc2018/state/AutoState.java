package org.pvcpirates.frc2018.state;


import org.pvcpirates.frc2018.Scheduler;
import org.pvcpirates.frc2018.autonomous.CommandFactory;


public class AutoState extends State {
    public static CommandFactory commandFactory = new CommandFactory();
    //TODO commandGroup
    
    @Override
    public void init() {
    	Scheduler.chooser.getSelected().init();
    }

    //TODO:Parallel/Sequential stuff
    @Override
    public void exec() {
		Scheduler.chooser.getSelected().exec();
    }

    @Override
    public void stop() {
    	Scheduler.chooser.getSelected().finished();
    }
}
