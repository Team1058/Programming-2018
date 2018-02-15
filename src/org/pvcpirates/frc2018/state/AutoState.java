package org.pvcpirates.frc2018.state;


import java.util.List;

import org.pvcpirates.frc2018.Scheduler;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.autonomous.AutoCommandFactory;

public class AutoState extends State {
    private List<Command> commands;
    public static AutoCommandFactory autoCommandFactory = new AutoCommandFactory();
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
