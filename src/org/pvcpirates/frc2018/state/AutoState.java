package org.pvcpirates.frc2018.state;

import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.CommandFactory;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.controllers.Drivetrain;

import java.util.List;

public class AutoState extends State{
    private List<Command> commands;
    @Override
    public void init() {
        CommandFactory commandFactory = new CommandFactory();
        commands.add(commandFactory.generate(Robot.getInstance().drivetrain,0));
    }
    //TODO:Parallel/Sequential stuff
    @Override
    public void exec() {

        for (Command command:commands){
            command.cycle.exec();
        }
    }

    @Override
    public void stop() {

    }
}
