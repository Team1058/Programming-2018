package org.pvcpirates.frc2018.state;


import org.pvcpirates.frc2018.Scheduler;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.pvcpirates.frc2018.autonomous.AutoCommandFactory;
import org.pvcpirates.frc2018.autonomous.command.AutoCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.pvcpirates.frc2018.AutoType;
import org.pvcpirates.frc2018.StartingLocation;
import org.pvcpirates.frc2018.autonomous.AutoCommand;
import org.pvcpirates.frc2018.autonomous.AutoCommandFactory;
import org.pvcpirates.frc2018.autonomous.DriveUltra;
import org.pvcpirates.frc2018.autonomous.command.SwitchAuto;
import org.pvcpirates.frc2018.autonomous.subcommands.DriveFor;
import org.pvcpirates.frc2018.autonomous.subcommands.DriveUltra;
import org.pvcpirates.frc2018.robot.Robot;

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
