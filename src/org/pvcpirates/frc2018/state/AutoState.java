package org.pvcpirates.frc2018.state;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.CommandFactory;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.controllers.Drivetrain;

import java.util.List;

public class AutoState extends State{
    private List<Command> commands;
    private SendableChooser <Command> chooser = new SendableChooser<>();
    //TODO commandGroup
    @Override
    public void init() {
        CommandFactory commandFactory = new CommandFactory();
        commands.add(commandFactory.generate(Robot.getInstance().drivetrain,0));
        chooser.addObject("",commands.get(commands.size()-1));
    }
    //TODO:Parallel/Sequential stuff
    @Override
    public void exec() {
        chooser.getSelected().cycle.exec();
    }

    @Override
    public void stop() {

    }
}
