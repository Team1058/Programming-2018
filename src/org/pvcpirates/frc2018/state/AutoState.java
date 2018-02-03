package org.pvcpirates.frc2018.state;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.pvcpirates.frc2018.autonomous.AutoCommandFactory;
import org.pvcpirates.frc2018.autonomous.AutoType;
import org.pvcpirates.frc2018.autonomous.StartingLocation;
import org.pvcpirates.frc2018.autonomous.command.AutoCommand;
import org.pvcpirates.frc2018.autonomous.command.SwitchAuto;
import org.pvcpirates.frc2018.autonomous.subcommands.DriveFor;
import org.pvcpirates.frc2018.autonomous.subcommands.DriveUltra;
import org.pvcpirates.frc2018.robot.Robot;

import java.util.List;

public class AutoState extends State {
    private List<AutoCommand> commands;
    private SendableChooser<AutoCommand> chooser = new SendableChooser<>();

    //TODO commandGroup
    @Override
    public void init() {
        AutoCommandFactory commandFactory = new AutoCommandFactory();

        AutoCommand ultraAutoCommand = new AutoCommand();
        DriveUltra driveUltra = new DriveUltra(ultraAutoCommand,20);
        ultraAutoCommand.addSubCommand(driveUltra);

        AutoCommand driveForCommand = new AutoCommand();
        DriveFor driveFor = new DriveFor(driveForCommand,100);
        driveForCommand.addSubCommand(driveFor);
        //chooser.addObject("Switch",commandFactory.generate(StartingLocation.CENTER, new SwitchAuto(),AutoType.SWITCH ));
        chooser.addDefault("DriveUltra",ultraAutoCommand);
        chooser.addObject("DriveFor",driveForCommand);
        SmartDashboard.putData("Auto",chooser);
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
