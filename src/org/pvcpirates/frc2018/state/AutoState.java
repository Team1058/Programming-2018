package org.pvcpirates.frc2018.state;


import java.util.List;

import org.pvcpirates.frc2018.Scheduler;
import org.pvcpirates.frc2018.autonomous.Command;
import org.pvcpirates.frc2018.autonomous.CommandFactory;

public class AutoState extends State {
    private List<Command> commands;
    public static CommandFactory commandFactory = new CommandFactory();
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
