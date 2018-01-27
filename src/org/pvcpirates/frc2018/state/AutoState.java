package org.pvcpirates.frc2018.state;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import org.pvcpirates.frc2018.autonomous.AutoCommand;
import org.pvcpirates.frc2018.autonomous.AutoCommandFactory;
import org.pvcpirates.frc2018.robot.Robot;

import java.util.List;

public class AutoState extends State {
    private List<AutoCommand> commands;
    private SendableChooser<AutoCommand> chooser = new SendableChooser<>();

    //TODO commandGroup
    @Override
    public void init() {
        AutoCommandFactory commandFactory = new AutoCommandFactory();
        commands.add(commandFactory.generate(Robot.getInstance().drivetrain, 0));
        chooser.addObject("", commands.get(commands.size() - 1));
    }

    //TODO:Parallel/Sequential stuff
    @Override
    public void exec() {
        chooser.getSelected().executeCommand();
    }

    @Override
    public void stop() {

    }
}
