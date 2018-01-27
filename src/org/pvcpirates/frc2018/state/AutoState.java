package org.pvcpirates.frc2018.state;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import org.pvcpirates.frc2018.AutoType;
import org.pvcpirates.frc2018.StartingLocation;
import org.pvcpirates.frc2018.autonomous.AutoCommand;
import org.pvcpirates.frc2018.autonomous.AutoCommandFactory;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.controllers.Drivetrain;

import java.util.List;

public class AutoState extends State{
    private List<AutoCommand> commands;
    private SendableChooser <AutoCommand> chooser = new SendableChooser<>();
    //TODO commandGroup
    @Override
    public void init() {
        AutoCommandFactory commandFactory = new AutoCommandFactory();
        chooser.addObject("Switch",commandFactory.generate(StartingLocation.CENTER, new SwitchAuto(),AutoType.SWITCH ));
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
