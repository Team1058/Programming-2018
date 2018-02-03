package org.pvcpirates.frc2018.state;

import org.pvcpirates.frc2018.Scheduler;
import org.pvcpirates.frc2018.autonomous.AutoCommandFactory;
import org.pvcpirates.frc2018.autonomous.command.AutoCommand;
import java.util.List;

public class AutoState extends State {
    private List<AutoCommand> commands;
    public static AutoCommandFactory commandFactory = new AutoCommandFactory();
    //TODO commandGroup
    @Override
    public void init() {
    }

    //TODO:Parallel/Sequential stuff
    @Override
    public void exec() {
		Scheduler.chooser.getSelected().executeCommand();
    }

    @Override
    public void stop() {

    }
}
